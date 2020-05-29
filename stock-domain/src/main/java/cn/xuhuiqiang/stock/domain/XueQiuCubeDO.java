package cn.xuhuiqiang.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "xq_cube")
public class XueQiuCubeDO extends BaseDO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**组合ID*/
	@Column(name = "cube_id")
	private Long cubeId;
	/**组合编码*/
	@Column(name = "symbol")
	private String symbol;
	/**组合名称*/
	@Column(name = "name")
	private String name;
	@Column(name = "owner_id")
	private String ownerId;
	@Column(name = "owner_name")
	private String ownerName;

	
	public Long getCubeId() {
		return cubeId;
	}

	public void setCubeId(Long cubeId) {
		this.cubeId = cubeId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
