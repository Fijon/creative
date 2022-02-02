package cn.xuhuiqiang.crawler.fund;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xuhuiqiang.crawler.CrawlerRunnable;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.client.constant.FundTypeEnum;
import cn.xuhuiqiang.stock.domain.FundMesDO;
import cn.xuhuiqiang.stock.domain.FundShareDO;
import cn.xuhuiqiang.stock.repository.FundRepository;
import us.codecraft.webmagic.Page;

@Component
public class FundSumCrawlerRunnable extends CrawlerRunnable {

	/** 初始默认数值 */
	private static final String INIT_URL = "http://fund.eastmoney.com/js/fundcode_search.js";

	private static final String SUP_URL_FORMAT = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=%s&topline=200&year=%d";

	{
		super.setInitUrl(INIT_URL);
	}
	@Autowired
	private FundRepository fundRepository;
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###%");


	private final static List<Integer> YEAR = new ArrayList<Integer>() { 

		{
			add(2020);
			//add(2019);
			//add(2018);
		}
	};

	@Override
	protected void doCrawler(Page page) {
		if (Objects.equals(page.getUrl().get(), INIT_URL)) {
			String rawText = page.getRawText().split("=")[1];
			JSONArray jsonArray = JSONArray.parseArray(rawText.substring(0, rawText.length() - 1));
			FundMesDO mes = null;
			List<FundMesDO> data = new ArrayList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONArray array = jsonArray.getJSONArray(i);
				mes = new FundMesDO();
				mes.setCode(array.getString(0));
				mes.setName(array.getString(2));
				FundTypeEnum typeEnum = FundTypeEnum.fromName(array.getString(3));
				if (null == typeEnum) {
					log.error("new type: {} for Fund, need to define", array.getString(3));
					continue;
				} else {
					mes.setType(typeEnum.name());
				}
				data.add(mes);
			}
			if (!CheckUtil.isEmpty(data)) {
				System.out.println("value...");
				try {
					for (FundMesDO tmp : data) {
						for (Integer year : YEAR) {
							String request = String.format(SUP_URL_FORMAT, tmp.getCode(), year);
							page.addTargetRequest(request);
						}
					}
				} catch (Exception e) {
					log.error("save error, data.size: {}", data.size(), e);
				}
			} else {
				log.error("data is empty");
			}
		} else {
			String rawText = page.getRawText();
			String[] urlParam = page.getUrl().get().split("\\?")[1].split("&");
			String fundCode = null;
			for(String param: urlParam) {
				String[] keyValue = param.split("=");
				if(Objects.equals("code", keyValue[0])) {
					fundCode = keyValue[1];
					break;
				}
			}
			if(null == fundCode) {
				return ;
			}
			
			
			String[] returnResult = rawText.split("apidata=");
			if(returnResult.length == 2) {
				JSONObject object = JSONObject.parseObject(returnResult[1].substring(0, returnResult[1].length() - 1));
				String content = object.getString("content");
				Document doc = Jsoup.parse(content);
				//抽取时间
				Elements timeArray = doc.select("h4[class=t] > label > font[class=px12]");
				// 抽取内容
				Elements contentArray = doc.select("div[class=box] > div > table > tbody");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(int i = 0; i<timeArray.size(); i++) {
					String time = timeArray.get(i).html();
					Element tbody = contentArray.get(i);
					Elements trList = tbody.select("tr");
					List<FundShareDO> dataList = new ArrayList<FundShareDO>();
					for(int j = 0; j < trList.size(); j++) {
						Element tr = trList.get(j);
						Elements tdList = tr.select("td");
						FundShareDO shareDO = new FundShareDO();
						shareDO.setFundCode(fundCode);
						try {
							shareDO.setBizDate(sdf.parse(time));
							shareDO.setCode(tdList.get(1).select("a").text());
							shareDO.setName(tdList.get(2).select("a").text());
							Number num = DECIMAL_FORMAT.parse(tdList.get(6).text());
							shareDO.setProportion(new BigDecimal(num.toString()));
							shareDO.setPossession(new BigDecimal(tdList.get(7).text().replace(",", "")));
							shareDO.setValue(new BigDecimal(tdList.get(8).text().replace(",", "")));
							dataList.add(shareDO);
						} catch (ParseException e) {
							log.error("bizDate parse error, date: {}", time, e);
							break;
						}
						fundRepository.saveAll(dataList);
						
					}
				}
				
				
			}
		}
	}

}
