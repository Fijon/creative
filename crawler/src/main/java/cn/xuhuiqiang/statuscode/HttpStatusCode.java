/**
 * 
 */
package cn.xuhuiqiang.statuscode;

/** 
 * @ClassName: HttpStatusCode 
 * @Description: 网络异常访问编码
 * @author huiqiangxu@126.com
 * @date 2020年5月1日 上午11:53:47 
 *  
 */
public enum HttpStatusCode {
	/**正常访问编码*/
	CODE_200(200),
	CODE_400(400),
	CODE_403(403);
	
	private int code;

	private HttpStatusCode(int code) {
		this.code = code;
	}
	
	public static HttpStatusCode fromCode(int code) {
		for(HttpStatusCode item: values()) {
			if(item.code == code) {
				return item;
			}
		}
		return null;
	}
	

}
