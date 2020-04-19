package cn.xuhuiqiang.creative.common.util;

import java.util.List;

import cn.xuhuiqiang.creative.common.query.PageQuery;

public abstract class CheckUtil {
	public static boolean isEmpty(Long id) {
		return null == id || id == 0;
	}
	
	public static <T> boolean isEmpty(List<T> list) {
		return null == list || list.size() == 0;
	}
	
	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 判断字符串是否为空，如果null，或者空白字符串，或者空字符串，均返回true   
	 * @param param
	 * @return 
	 * boolean     
	 * @throws
	 */
	public static boolean isEmpty(String param) {
		return null==param || param.trim().length() ==0;
	}
	
	public static <T> boolean isEmpty(PageQuery<T> page) {
		if(null == page) {
			return false;
		}
		return true;
	}
	
	public static boolean isNotEmpty(String param) {
		return null != param && param.trim().length() >0;
	}
}
