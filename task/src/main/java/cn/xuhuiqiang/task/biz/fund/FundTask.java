package cn.xuhuiqiang.task.biz.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xuhuiqiang.crawler.fund.FundCrawlerRunnable;
import cn.xuhuiqiang.crawler.fund.FundSumCrawlerRunnable;
import cn.xuhuiqiang.stock.repository.FundRepository;

@Component
public class FundTask {
	@Autowired
	FundCrawlerRunnable fundCrawlerRunnable;
	@Autowired
	FundSumCrawlerRunnable fundSumCrawlerRunnable;
	@Autowired
	FundRepository fundRepository;
	private Integer init = 1;
	
	//@Scheduled(cron = "0/1 * * * * ?")
	public void runFundCrawlerRunnable() {
		System.out.println("********first job is ok******");
		new Thread(fundCrawlerRunnable).start(); 
	}
	
	//@Scheduled(cron = "0/1 * * * * ?")
	public void runFundSumCrawlerRunnable() {
		
		System.out.println("********FundSumCrawlerRunnable job is ok******");
		if(init == 1) {
		new Thread(fundSumCrawlerRunnable).start();
		init ++;
		}
	}
}
