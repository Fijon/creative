package cn.xuhuiqiang.task.biz.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xuhuiqiang.crawler.fund.FundCrawlerRunnable;
import cn.xuhuiqiang.stock.repository.FundRepository;

@Component
public class FundTask {
	@Autowired
	FundCrawlerRunnable fundCrawlerRunnable;
	@Autowired
	FundRepository fundRepository;
	
	@Scheduled(cron = "0/1 * * * * ?")
	public void runfirst() {
		System.out.println("********first job is ok******");
		new Thread(fundCrawlerRunnable).start();
		fundRepository.count();
	}
}
