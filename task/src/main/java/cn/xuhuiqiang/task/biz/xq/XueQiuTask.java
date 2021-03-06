package cn.xuhuiqiang.task.biz.xq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xuhuiqiang.crawler.CrawlerRunnable;
import cn.xuhuiqiang.crawler.util.UserAgent;

@Component
public class XueQiuTask {
	@Autowired
	private CrawlerRunnable cubeHistoryRunnable;
	private int init = 1;

	//@Scheduled(cron = "7 */12 * * * ?")
	@Scheduled(cron = "*/15 * * * *  ?")
	public void runFundSumCrawlerRunnable() {
		if (init == 1) {
			//UserAgent.initIP();
			System.out.println("********FundSumCrawlerRunnable job is ok******");
			cubeHistoryRunnable.setHostUrl("https://xueqiu.com");
			System.out.println("Init IP success");
			new Thread(cubeHistoryRunnable).start();
			init++;
		}
	}

}
