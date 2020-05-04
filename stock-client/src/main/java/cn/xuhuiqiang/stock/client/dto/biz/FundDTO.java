package cn.xuhuiqiang.stock.client.dto.biz;

import cn.xuhuiqiang.stock.client.dto.BaseDTO;
 
public class FundDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 基金代码 */
	private String fundCode;
	/** 股票 */
	private String code;
	/** 股票名称 */
	private String name;
	/** 持有量 */
	private String possession;
	/** 市值 */
	private String value;
	/** 净值比 */
	private Double proportion;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
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
	public String getPossession() {
		return possession;
	}
	public void setPossession(String possession) {
		this.possession = possession;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Double getProportion() {
		return proportion;
	}
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}
	
	
	

}
