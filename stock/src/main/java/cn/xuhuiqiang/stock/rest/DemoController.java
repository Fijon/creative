package cn.xuhuiqiang.stock.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.creative.common.util.DateUtil;
import cn.xuhuiqiang.stock.domain.FundDO;
import cn.xuhuiqiang.stock.repository.FundRepository;

@RestController
public class DemoController {
	@Autowired
	FundRepository fundRepository;
	@RequestMapping("/demo")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
		 
        return "Hello, " + name;
    }
	
	@RequestMapping("/query/fund/{fundCode}")
    public ResultDTO<List<FundDO>> query(@PathVariable("fundCode") String fundCode) {
		List<FundDO> resultList =  fundRepository.findByFundCode(fundCode);
		if(CheckUtil.isEmpty(resultList)) {
			 
			return ResultDTO.getSuccessResult(new ArrayList<FundDO>());
		}
      
		return ResultDTO.getSuccessResult(resultList);
    }

	@RequestMapping("/query/fund/{fundCode}/{date}")
    public ResultDTO<Long> query(@PathVariable("fundCode") String fundCode, @PathVariable("date") String date) {
		
		Date localDate = DateUtil.localDate2Date(LocalDate.parse(date));
		Long count =  fundRepository.countByFundCodeAndBizDate(fundCode, localDate);
	      
		return ResultDTO.getSuccessResult(count);
    }

	
	

}
