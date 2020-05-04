package cn.xuhuiqiang.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * @ClassName: FundMesDO 
 * @Description: 基金信息对象
 * @author huiqiangxu@126.com
 * @date 2020年5月1日 下午1:58:25 
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="fund_mes")
public class FundMesDO extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 基金编码
	 */
	@Column(name = "code")
	private String code;
	/**基金中文名称*/
	@Column(name = "name")
	private String name;
	/**基金类型*/
	@Column(name = "type")
	private String type;
	
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
