package cn.xuhuiqiang.stock.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xuhuiqiang.creative.common.result.ErrorMsg;
import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.client.dto.biz.CompanyDTO;
import cn.xuhuiqiang.stock.client.service.ICompanyService;
import cn.xuhuiqiang.stock.domain.CompanyDO;
import cn.xuhuiqiang.stock.repository.CompanyRepository;

@RestController()
public class CompanyController implements ICompanyService {
	private Logger logger= LoggerFactory.getLogger(CompanyController.class);
	@Autowired
	private CompanyRepository companyRepository;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/company/{id}", method = RequestMethod.GET)
	@Override
	public ResultDTO<CompanyDTO> findById(@PathVariable  Long id) {
		if(CheckUtil.isEmpty(id)) {
			return ResultDTO.getFailureResult(ErrorMsg.PARAM_NULL);
		}
		Optional<CompanyDO> result =  companyRepository.findById(id);
		if(result.isPresent()) {			
			CompanyDO company = result.get();
			CompanyDTO dto = new CompanyDTO();
			BeanUtils.copyProperties(company, dto);
			return ResultDTO.getSuccessResult(dto);
		}
		return ResultDTO.getFailureResult(ErrorMsg.SYSTEM_ERROR);
	}
}
