package cn.xuhuiqiang.task.biz.fund;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xuhuiqiang.crawler.fund.FundCrawlerTask;


@Component
@EnableScheduling
public class FundTask {
	@Scheduled(cron = "0/10 * * * * ?")
    public void runfirst(){
        System.out.println("********first job is ok******");
        Thread thread = new Thread(new FundCrawlerTask());
        thread.start();
    }
}
