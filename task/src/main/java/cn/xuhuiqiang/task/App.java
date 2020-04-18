package cn.xuhuiqiang.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	private static Logger log = LoggerFactory.getLogger(App.class);
	private static Logger monitor = LoggerFactory.getLogger("monitor");
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
		log.info("app start up success...");
		monitor.info("app start up success");
	}
}