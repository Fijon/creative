package cn.xuhuiqiang.crawler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xuhuiqiang.crawler.util.UserAgent;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.statuscode.HttpStatusCode;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

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
	protected Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8").setCycleRetryTimes(UserAgent.getIpSize());
	protected Set<Cookie> cookies;
	protected String initUrl;
	protected String hostUrl;

	@Override
	public void run() {
		pre();
		if (CheckUtil.isEmpty(initUrl)) {
			throw new IllegalArgumentException("initUrl is empty");
		}
		log.info("begin...");
		parseCookie();
		Request requests = new Request(initUrl);
		if (CheckUtil.isNotEmpty(cookies)) {
			for (Cookie item : cookies) {
				site.addCookie(item.getName(), item.getValue());
				requests.addCookie(item.getName(), item.getValue());
			}
		}
		Spider spider = new Spider(this).addRequest(requests).addUrl(initUrl);
		//spider.setDownloader(getDownloader());
		spider.thread(1).run();
		log.info("end...");
	}
	
	protected void pre() {};

	@Override
	public void process(Page page) {
		HttpStatusCode code = HttpStatusCode.fromCode(page.getStatusCode());
		switch (code) {
		case CODE_200:
			doCrawler(page);
			break;
		case CODE_400:
			dealCode400(page);
			break;
		case CODE_403:
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				log.error("crawler url: {}, code: {}, pause failure", page.getUrl(), code, e);
			}
			break;
		default:
			log.error("crawler url:{}, getCode: {},content:{}", page.getUrl(), page.getStatusCode(), page.getRawText());
			return;
		}

	}

	private void parseCookie() {
		site.setDomain("https://xueqiu.com").setCharset("utf-8").setTimeOut(30000).setUserAgent(
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

		if (CheckUtil.isEmpty(hostUrl)) {
 			return;
		}
		WebDriver driver = new ChromeDriver();
		driver.get(hostUrl);
		setCookies(driver.manage().getCookies());
		driver.close();
	}

	protected void dealCode400(Page page) {

	}

	protected abstract void doCrawler(Page page);

	private Downloader getDownloader() {
		HttpClientDownloader downloader = new HttpClientDownloader() {
			@Override
			protected void onError(Request request) {
				 Map<String, String> header = request.getHeaders();
				 for(Map.Entry<String, String> item: header.entrySet()) {
					 System.out.println(item.getKey()+"  " + item.getValue());
				 }
				
				String[] ips = UserAgent.getIp().split(":");
				if (ips.length > 0) {
					List<Proxy> proxiesTemp = new LinkedList<Proxy>() {{add(new Proxy(ips[0], Integer.parseInt(ips[1])));}};
					setProxyProvider(new CrawlerProxyProvider(Collections.unmodifiableList(proxiesTemp)));
				}
			}
		};
		String[] ips = UserAgent.getIp().split(":");
		if (ips.length > 0) {
			downloader.setProxyProvider(SimpleProxyProvider.from(new Proxy(ips[0], Integer.parseInt(ips[1]))));
		}else {
			System.out.println("all proxy end...");
		}
		return downloader;
	}
	
	class CrawlerProxyProvider extends SimpleProxyProvider{

		public CrawlerProxyProvider(List<Proxy> proxies) {
			super(proxies);
		}

		@Override
		public void returnProxy(Proxy proxy, Page page, Task task) {
			super.returnProxy(proxy, page, task);
			System.out.println("change proxy and retry");
			page.addTargetRequest(page.getRequest());
			
		}
		
		
		
	}
	

	public Site getSite() {
		return site;
	}

	public String getInitUrl() {
		return initUrl;
	}

	public void setInitUrl(String initUrl) {
		this.initUrl = initUrl;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
}
