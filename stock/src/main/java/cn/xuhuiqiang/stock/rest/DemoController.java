package cn.xuhuiqiang.stock.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@RequestMapping("/demo")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello, " + name;
    }
}
