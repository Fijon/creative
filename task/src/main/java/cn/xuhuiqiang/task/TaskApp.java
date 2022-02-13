package cn.xuhuiqiang.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EntityScan(basePackages = {"cn.xuhuiqiang.stock.domain"})
@EnableJpaRepositories(basePackages = {"cn.xuhuiqiang.stock.repository"})
@EnableJpaAuditing(setDates=true)
@EnableScheduling
@ComponentScan(basePackages = {"cn.xuhuiqiang.task.biz", "cn.xuhuiqiang.crawler"})
public class TaskApp {
	private static Logger log = LoggerFactory.getLogger(TaskApp.class);
	private static Logger monitor = LoggerFactory.getLogger("monitor");
	

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		SpringApplication.run(TaskApp.class, args);
		log.info("app start up success...");
		monitor.info("app start up success");
	}
	
}