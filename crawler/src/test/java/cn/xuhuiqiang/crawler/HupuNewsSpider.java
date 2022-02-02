package cn.xuhuiqiang.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.client.dto.biz.FundDTO;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @ClassName: HupuNewsSpider
 * @author LJH
 * @date 2017年11月27日 下午4:54:48
 */
public class HupuNewsSpider implements PageProcessor {

	private Logger log =  LoggerFactory.getLogger(HupuNewsSpider.class);
	
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	public Site getSite() {
		return site;
	}

	public void process(Page page)  {
		
		System.out.print(page.getUrl());
		if (page.getUrl().regex("http://fund\\.10jqka\\.com\\.cn/[0-9]{6}/portfolioindex\\.html").match()) {
			Selectable select = page.getHtml().xpath("//*[@id=\"zcgList\"]/div[@class=\"s-list\"]/ul[@class=\"data\"]/li");
			String bizDate = page.getHtml().xpath("//*[@id=\"zcgList\"]/div[@class=\"o-title\"]/p[@class=\"date\"]/text()").toString();
			
		    if(CheckUtil.isNotEmpty(bizDate)) {
		    	bizDate = bizDate.split("\\s+")[1];
		    }
		    System.out.println("----------" + bizDate);
			String fundCode = page.getUrl().get().split("/")[3];
			List<Selectable> li = select.nodes();
			NumberFormat nf=NumberFormat.getPercentInstance();
			for(int i = 0; i < li.size(); ) {
				FundDTO fund = new FundDTO();
				i++;
				fund.setFundCode(fundCode); 
				 
				fund.setCode(li.get(i++).xpath("//a/text()").toString()); 
				fund.setName(li.get(i++).xpath("//a/text()").get());
				fund.setPossession(li.get(i++).xpath("/li/text()").get()); 
				fund.setValue(li.get(i++).xpath("/li/text()").get()); 
				try {
					fund.setProportion(nf.parse(li.get(i++).xpath("/li/text()").get()).doubleValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    log.info(fund.toString());
			}
		} else {
			// 文章url
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

/*	public static void main(String[] args) {
		
		Spider.create(new HupuNewsSpider())
				.addUrl("http://fund.ijijin.cn/data/Net/info/all_rate_desc_0_0_1_9999_0_0_0_jsonp_g.html").thread(3)
				.run();
	}*/
}

// 自定义实现Pipeline接口
class MysqlPipeline implements Pipeline {

	public MysqlPipeline() {
	}

	public void process(ResultItems resultitems, Task task) {
		Map<String, Object> mapResults = resultitems.getAll();
		Iterator<Entry<String, Object>> iter = mapResults.entrySet().iterator();
		Map.Entry<String, Object> entry;
		// 输出到控制台
		while (iter.hasNext()) {
			entry = iter.next();
			System.out.println(entry.getKey() + "：" + entry.getValue());
		}
		// 持久化
		System.out.print(mapResults);
	}
}