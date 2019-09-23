package cn.xuhuiqiang.creative.common.result;

import java.io.Serializable;

public  class ResultDTO<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6106703643689571591L;
	private boolean failure;
	private Integer errorCode;
	private String errorMes;
	private T result;
	public boolean isFailure() {
		return failure;
	}
	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMes() {
		return errorMes;
	}
	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	public boolean isSuccess() {
		return !failure;
	}
	
	public static <T> ResultDTO<T> getFailureResult(ErrorMsg errorMsg) {
		ResultDTO<T> failure = new ResultDTO<T>();
		failure.errorCode = errorMsg.getCode();
		failure.errorMes = errorMsg.getMsg();
		failure.setFailure(true);
		return failure;
	}
	
	public static <T> ResultDTO<T> getSuccessResult(T t) {
		ResultDTO<T> success = new ResultDTO<T>();
		success.setResult(t);
		success.setFailure(false);
		return success;
	}
	@Override
	public String toString() {
		return "ResultDTO [failure=" + failure + ", errorCode=" + errorCode + ", errorMes=" + errorMes + ", result="
				+ result.toString() + "]";
	}
	
	
}
