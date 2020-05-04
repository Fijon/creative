package cn.xuhuiqiang.stock.client.dto.biz;

import cn.xuhuiqiang.stock.client.dto.BaseDTO;

public class CompanyDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742241522782116985L;
	
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
