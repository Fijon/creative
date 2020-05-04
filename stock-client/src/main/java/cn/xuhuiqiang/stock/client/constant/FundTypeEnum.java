package cn.xuhuiqiang.stock.client.constant;

import java.util.Objects;

public enum FundTypeEnum {

	STOCK_FOF("股票-FOF"),
	BREAK_EVEN("保本型"),
	ETF_INSIDE("ETF-场内"),
	FINANCING("理财型"),
	CURRENCY("货币型"),
	OTHER_INNOVATIONS("其他创新"),
	QDII_ETF("QDII-ETF"),
	MIX_FOF("混合-FOF"),
	SHARES("股票型"),
	DEFINITE_OPENING_BOND("定开债券"),
	QDII("QDII"),
	QDII_INDEX("QDII-指数"),
	SETP_LEVER("分级杠杆"),
	BOND("债券型"),
	FIX_INCOME("固定收益"),
	SHARES_INDEX("股票指数"),
	MIX("混合型"),
	FEEDER_FUND("联接基金"),
	BOND_INDEX("债券指数"),;

	private String name;

	private FundTypeEnum(String name) {
		this.name = name;
	}

	public static FundTypeEnum fromName(String name) {
		for(FundTypeEnum item: values()) {
			if(Objects.equals(name, item.name)) {
				return item;
			}
		}
		return null;
	}
}
