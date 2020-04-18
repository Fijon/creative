package cn.xuhuiqiang.stock.client.service;

import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.stock.client.dto.biz.CompanyDTO;

public interface ICompanyService {
	/**
	 * @Title: findById 
	 * @Description: TODO   
	 * @param id
	 * @return 
	 * ResultDTO<CompanyDTO>     
	 * @throws
	 */
	ResultDTO<CompanyDTO> findById(Long id);

}
