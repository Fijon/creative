package cn.xuhuiqiang.stock.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CompanyController {
	@RequestMapping("/company/{code}")
    public String greeting(@PathVariable String code) {
        return "Hello, " + code;
    }
}
