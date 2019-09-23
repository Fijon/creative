package cn.xuhuiqiang.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Company extends BaseDO{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6967665627519263506L;

	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "lot_size")
	private Integer lotSize;

	

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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	@Override
	public String toString() {
		return "Company [id=" + this.getId() + ", code=" + code + ", name=" + name + ", region=" + region + ", lotSize=" + lotSize
				+ "]";
	}
	

}
