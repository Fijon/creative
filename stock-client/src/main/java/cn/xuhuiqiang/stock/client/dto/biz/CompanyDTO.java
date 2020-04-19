package cn.xuhuiqiang.stock.client.dto.biz;

import cn.xuhuiqiang.stock.client.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CompanyDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742241522782116985L;
	
	private String code;
	private String name;

}
