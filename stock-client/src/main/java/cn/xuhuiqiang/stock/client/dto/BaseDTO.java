package cn.xuhuiqiang.stock.client.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public abstract class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522463887177156700L;
	
	private Long id;

}
