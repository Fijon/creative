package cn.xuhuiqiang.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Repurchase")
public class RepurchasedDO extends BaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3105627482478455080L;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "company")
	private String company;
	@Column(name = "type_purchase")
	private String typePurchase;
	@Column(name = "price")
	private	String price;
	@Column(name = "lowest_price")
	private String lowestPrice;
	@Column(name = "total_paid")
	private String totalPaid;
	@Column(name = "method")
	private String method;
	@Column(name = "num_purchased")
	private String numPurchased;
	@Column(name = "num_purchased_exchange")
	private String numPurchasedExchange;
	@Column(name = "proportion_exchange")
	private String proportionExchange;
	@Column(name = "trading_date")
	private String tradingDate;
	
	
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTypePurchase() {
		return typePurchase;
	}
	public void setTypePurchase(String typePurchase) {
		this.typePurchase = typePurchase;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getNumPurchased() {
		return numPurchased;
	}
	public void setNumPurchased(String numPurchased) {
		this.numPurchased = numPurchased;
	}
	
	public String getProportionExchange() {
		return proportionExchange;
	}
	public void setProportionExchange(String proportionExchange) {
		this.proportionExchange = proportionExchange;
	}
	public String getTradingDate() {
		return tradingDate;
	}
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}
	
	
}
