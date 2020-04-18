/**
 * 
 */
package cn.xuhuiqiang.stock.client.service;

import java.util.List;

import cn.xuhuiqiang.creative.common.query.PageQuery;
import cn.xuhuiqiang.creative.common.result.PageResultDTO;
import cn.xuhuiqiang.creative.common.result.ResultDTO;
import cn.xuhuiqiang.stock.client.dto.biz.RepurchasedDTO;

/** 
* @ClassName: IRepurchaseService 
* @Description: 查询回购信息
* @author huiqiangxu@126.com
* @date 2019年9月23日 下午10:26:32 
*  
*/
public interface IRepurchaseService {
	ResultDTO<RepurchasedDTO> findByCode(String code);
	PageResultDTO<List<RepurchasedDTO>> findByName(PageQuery<String> name);
}
