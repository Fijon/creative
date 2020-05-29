package cn.xuhuiqiang.stock.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "xq_cube_dynamic")
public class XueQiuCubeDynamicDO extends BaseDO {
	
	/**组合编码*/
	@Column(name = "cube_id")
	private Long cubeId;
	@Column(name = "stock")
	private String stockName;
	@Column(name = "code")
	private String code;
	@Column(name = "pre_persent")
	private BigDecimal prePersent;
	@Column(name = "now_persent")
	private BigDecimal nowPersent;
	@Column(name = "biz_date")
	private Date bizDate;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "rebalance_id")
	private Long rebalanceId;
	@Column(name = "record_id")
	private Long recordId;
	public Long getCubeId() {
		return cubeId;
	}
	public void setCubeId(Long cubeId) {
		this.cubeId = cubeId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getPrePersent() {
		return prePersent;
	}
	public void setPrePersent(BigDecimal prePersent) {
		this.prePersent = prePersent;
	}
	public BigDecimal getNowPersent() {
		return nowPersent;
	}
	public void setNowPersent(BigDecimal nowPersent) {
		this.nowPersent = nowPersent;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getRebalanceId() {
		return rebalanceId;
	}
	public void setRebalanceId(Long rebalanceId) {
		this.rebalanceId = rebalanceId;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	
	
	
	
}
