package cn.xuhuiqiang.creative.common.result;

import java.io.Serializable;

public abstract class ResultDTO<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6106703643689571591L;
	private boolean failure;
	private String errorCode;
	private String errorMes;
	private T result;
	public boolean isFailure() {
		return failure;
	}
	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
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
	@Override
	public String toString() {
		return "ResultDTO [failure=" + failure + ", errorCode=" + errorCode + ", errorMes=" + errorMes + ", result="
				+ result.toString() + "]";
	}
	
	
}
