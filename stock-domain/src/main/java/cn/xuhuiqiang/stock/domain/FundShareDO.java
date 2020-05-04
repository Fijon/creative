package cn.xuhuiqiang.stock.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "fund_shares")
public class FundShareDO extends BaseDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 基金编码 */
	@Column(name = "fund_code")
	private String fundCode;
	/** 股票代码 */
	@Column(name = "code")
	private String code;
	/** 股票名称 */
	@Column(name = "name")
	private String name;
	/** 持有量 */
	@Column(name = "possession")
	private BigDecimal possession;
	/** 市值 */
	@Column(name = "value")
	private BigDecimal value;
	/** 占市值比 */
	@Column(name = "proportion")
	private BigDecimal proportion;
	/** 公布时间 */
	@Temporal(TemporalType.DATE)
	@Column(name = "biz_date")
	private Date bizDate;

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

	public BigDecimal getPossession() {
		return possession;
	}

	public void setPossession(BigDecimal possession) {
		this.possession = possession;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

}
