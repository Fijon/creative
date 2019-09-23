package cn.xuhuiqiang.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"cn.xuhuiqiang.stock.filter"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("start stock app success...");
	}
}
