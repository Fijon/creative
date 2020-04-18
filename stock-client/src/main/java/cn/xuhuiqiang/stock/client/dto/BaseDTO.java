package cn.xuhuiqiang.stock.client.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522463887177156700L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
