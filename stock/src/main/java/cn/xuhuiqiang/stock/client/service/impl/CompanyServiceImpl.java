package cn.xuhuiqiang.stock.client.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xuhuiqiang.creative.common.result.ErrorMsg;
import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.creative.common.util.CheckUtil;
import cn.xuhuiqiang.stock.client.dto.biz.CompanyDTO;
import cn.xuhuiqiang.stock.client.service.ICompanyService;
import cn.xuhuiqiang.stock.domain.Company;
import cn.xuhuiqiang.stock.repository.CompanyRepository;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService{

	private Logger logger= LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public ResultDTO<CompanyDTO> findById(Long id) {
		if(CheckUtil.isEmpty(id)) {
			return ResultDTO.getFailureResult(ErrorMsg.PARAM_NULL);
		}
		Optional<Company> result =  companyRepository.findById(id);
		if(result.isPresent()) {			
			Company company = result.get();
			CompanyDTO dto = new CompanyDTO();
			BeanUtils.copyProperties(company, dto);
			return ResultDTO.getSuccessResult(dto);
		}
		return ResultDTO.getFailureResult(ErrorMsg.SYSTEM_ERROR);
	}
	
	

}
