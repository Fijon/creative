package cn.xuhuiqiang.stock.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import cn.xuhuiqiang.stock.client.service.IRepurchaseService;
import cn.xuhuiqiang.stock.domain.Repurchased;
import cn.xuhuiqiang.stock.repository.RepurchasedRepository;

@RestController
public class RepurchasedController implements IRepurchaseService {
	
	@Autowired
	private RepurchasedRepository repurchasedRepository;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/company/repurchased/{code}", method = RequestMethod.GET)
	@Override
	public ResultDTO<Repurchased> findByCode(@PathVariable String code) {
		if(CheckUtil.isEmpty(code)) {
			return ResultDTO.getFailureResult(ErrorMsg.PARAM_NULL);
		}
		Repurchased domain = repurchasedRepository.findByCode(code);
		return ResultDTO.getSuccessResult(domain);
	}

	@Override
	public PageResultDTO<List<Repurchased>> findByName(PageQuery<String> name) {
		if(CheckUtil.isEmpty(name)) {
			return PageResultDTO.getFailurePageResult(ErrorMsg.PARAM_NULL);
		}
		return null;
	}
}
