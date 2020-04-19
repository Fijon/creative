package cn.xuhuiqiang.crawler.fund;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.creative.common.util.DateUtil;
import cn.xuhuiqiang.stock.domain.FundDO;
import cn.xuhuiqiang.stock.repository.FundRepository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class FundCrawlerRunnable implements Runnable, PageProcessor {

	private Logger log = LoggerFactory.getLogger("crawler");
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###%");
	@Autowired
	FundRepository fundRepository;

	@Override
	public void run() {
		log.info("begin...");
		Spider.create(this).addUrl("http://fund.ijijin.cn/data/Net/info/all_rate_desc_0_0_1_9999_0_0_0_jsonp_g.html")
				.thread(1).run();
		log.info("end...");
	}

	public void process(Page page) {
		if(page.getStatusCode() == 403) {
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (page.getUrl().regex("http://fund\\.10jqka\\.com\\.cn/[0-9]{6}/portfolioindex\\.html").match()) {
			String fundCode = page.getUrl().get().split("/")[3];
			// 获取公布时间
			String tmpBizDate = page.getHtml()
					.xpath("//*[@id=\"zcgList\"]/div[@class=\"o-title\"]/p[@class=\"date\"]/text()").toString();
			if(CheckUtil.isEmpty(tmpBizDate)) {
				log.warn("does not have stockList, fundCode: {}", fundCode);
				return;
			}
			Selectable select = page.getHtml()
					.xpath("//*[@id=\"zcgList\"]/div[@class=\"s-list\"]/ul[@class=\"data\"]/li");
			
			String bizDate = null;
			if (CheckUtil.isNotEmpty(tmpBizDate)) {
				bizDate = tmpBizDate.split("\\s+")[1];
			}
			if (CheckUtil.isEmpty(bizDate)) {
				log.warn("could not parse tmpBizDate: {}, code: {}", tmpBizDate, fundCode);
			}

			Date localDate = DateUtil.localDate2Date(LocalDate.parse(bizDate));
			Long count = fundRepository.countByFundCodeAndBizDate(fundCode, localDate);
			log.info("count: {}, fundCode: {}, localDate: {}", count, fundCode, localDate );
			if (count > 0L) {
				log.warn("bizDate:{}, fundCode: {} , count:{} has been crawler, and return", bizDate, fundCode, count);
				return;
			}
			List<Selectable> li = select.nodes();
			for (int i = 0; i < li.size();) {
				FundDO fund = new FundDO();
				i++;
				fund.setFundCode(fundCode);
				fund.setCode(li.get(i++).xpath("//a/text()").toString());
				fund.setName(li.get(i++).xpath("//a/text()").get());
				String possession = li.get(i++).xpath("/li/text()").get();
				String value = li.get(i++).xpath("/li/text()").get();
				String propotion = li.get(i++).xpath("/li/text()").get();
				try {
					fund.setBizDate(localDate);
					fund.setPossession(new BigDecimal(possession));
					fund.setValue(new BigDecimal(value));
					Number num = DECIMAL_FORMAT.parse(propotion);
					fund.setProportion(new BigDecimal(num.toString()));
					fundRepository.save(fund);
				}  catch (Exception e) {
					log.error("convert2Bigdecimal failure, bizDate:{}, posssesion: {}, value: {}, proportion: {}, pageurl: {}, fundDO:",
							bizDate, possession, value, propotion, page.getUrl(),fund,e);
				}
			}
		} else {
			// 解析首页
			String html = page.getHtml().xpath("//body/text()").get();
			html = html.substring(2, html.length() - 1);
			try {
				JSONObject json = JSON.parseObject(html);
				JSONObject data = json.getJSONObject("data").getJSONObject("data");
				Set<String> keySet = data.keySet();
				String request = "http://fund.10jqka.com.cn/%s/portfolioindex.html";
				for (String item : keySet) {
					page.addTargetRequest(String.format(request, item.substring(1, item.length())));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Site getSite() {
		return site;
	}

}
