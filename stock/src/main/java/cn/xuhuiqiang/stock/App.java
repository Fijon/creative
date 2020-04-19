package cn.xuhuiqiang.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EntityScan(basePackages = { "cn.xuhuiqiang.stock.domain" })
@ServletComponentScan(basePackages = { "cn.xuhuiqiang.stock.filter" })
@EnableJpaRepositories(basePackages = {"cn.xuhuiqiang.stock.repository"})
@EnableJpaAuditing(setDates=true)
public class App {


	private static Logger log = LoggerFactory.getLogger(App.class);
	private static Logger monitor = LoggerFactory.getLogger("monitor");

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		log.info("app start up success...");
		monitor.info("app start up success");
	}


}
