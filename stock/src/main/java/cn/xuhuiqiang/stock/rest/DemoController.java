package cn.xuhuiqiang.stock.rest;

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
import cn.xuhuiqiang.stock.domain.FundMesDO;
import cn.xuhuiqiang.stock.domain.FundShareDO;
import cn.xuhuiqiang.stock.repository.FundMesRepository;
import cn.xuhuiqiang.stock.repository.FundRepository;

@RestController
public class DemoController {
	@Autowired
	FundRepository fundRepository;
	@Autowired
	FundMesRepository fundMesRepository; 
	
	@RequestMapping("/demo")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
		 
        return "Hello, " + name;
    }
	
	@RequestMapping("/query/fund/{fundCode}")
    public ResultDTO<List<FundShareDO>> query(@PathVariable("fundCode") String fundCode) {
		List<FundShareDO> resultList =  fundRepository.findByFundCode(fundCode);
		if(CheckUtil.isEmpty(resultList)) {
			 
			return ResultDTO.getSuccessResult(new ArrayList<FundShareDO>());
		}
      
		return ResultDTO.getSuccessResult(resultList);
    }

	@RequestMapping("/query/fund/{fundCode}/{date}")
    public ResultDTO<Long> query(@PathVariable("fundCode") String fundCode, @PathVariable("date") String date) {
		
		Date localDate = DateUtil.localDate2Date(LocalDate.parse(date));
		Long count =  fundRepository.countByFundCodeAndBizDate(fundCode, localDate);
	      
		return ResultDTO.getSuccessResult(count);
    }

	@RequestMapping("/query/fundMes/{fundCode}/{date}")
    public ResultDTO<Long> addMes(@PathVariable("fundCode") String fundCode, @PathVariable("date") String date) {
		
		FundMesDO mesDO = new FundMesDO();
		mesDO.setCode("1");
		mesDO.setName("1");
		mesDO.setType("test");
		fundMesRepository.save(mesDO);
	
	      
		return ResultDTO.getSuccessResult(1L);
    }
	
	

}
