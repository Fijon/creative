package cn.xuhuiqiang.stock.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xuhuiqiang.creative.common.query.PageQuery;
import cn.xuhuiqiang.creative.common.result.ErrorMsg;
import cn.xuhuiqiang.creative.common.result.PageResultDTO;
import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.client.dto.biz.RepurchasedDTO;
import cn.xuhuiqiang.stock.client.service.IRepurchaseService;

@RestController
public class RepurchasedController implements IRepurchaseService {
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/company/repurchased/{code}", method = RequestMethod.GET)
	@Override
	public ResultDTO<RepurchasedDTO> findByCode(@PathVariable String code) {
		if(CheckUtil.isEmpty(code)) {
			return ResultDTO.getFailureResult(ErrorMsg.PARAM_NULL);
		}
		return ResultDTO.getSuccessResult(new RepurchasedDTO());
		
	}

	@Override
	public PageResultDTO<List<RepurchasedDTO>> findByName(PageQuery<String> name) {
		if(CheckUtil.isEmpty(name)) {
			return PageResultDTO.getFailurePageResult(ErrorMsg.PARAM_NULL);
		}
		return null;
	}
}
