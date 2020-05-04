package cn.xuhuiqiang.crawler.fund;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.statuscode.HttpStatusCode;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/***
 * 具有初始入口得处理基类
 * @ClassName: CrawlerRunnable 
 * @Description: TODO
 * @author huiqiangxu@126.com
 * @date 2020年5月1日 上午11:35:52 
 *
 */
public abstract class CrawlerRunnable implements Runnable, PageProcessor {
	protected Logger log = LoggerFactory.getLogger("crawler");
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	protected Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8");

	protected String initUrl;

	@Override
	public void run() {
		if (CheckUtil.isEmpty(initUrl)) {
			throw new IllegalArgumentException("initUrl is empty");
		}
		log.info("begin...");
		Spider.create(this).addUrl(initUrl).thread(1).run();
		log.info("end...");
	}

	@Override
	public void process(Page page) {
		HttpStatusCode code = HttpStatusCode.fromCode(page.getStatusCode());
		switch (code) {
		case CODE_200:
			doCrawler(page);
			break;
		case CODE_403:
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				log.error("crawler url: {}, code: {}, pause failure", page.getUrl(), code, e);
			}
			break;
		default:
			log.error("crawler url:{}, getCode: {}", page.getUrl(), page.getStatusCode());
			return;
		}

	}

	protected abstract void doCrawler(Page page);

	public Site getSite() {
		return site;
	}

	public String getInitUrl() {
		return initUrl;
	}

	public void setInitUrl(String initUrl) {
		this.initUrl = initUrl;
	}

}
