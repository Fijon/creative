package cn.xuhuiqiang.creative.common.result;

public enum ErrorMsg  {
	SYSTEM_ERROR(1, "系统异常"),
	PARAM_NULL(2001, "参数不可为空");
	
	int code;
	String msg;
	private ErrorMsg(int code, String ms) {
		this.code = code;
		this.msg = ms;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	
	
	
}
