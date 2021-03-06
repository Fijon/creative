package cn.xuhuiqiang.creative.common.result;

import java.util.List;

public  class PageResultDTO<T> extends ResultDTO<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6311771664006360310L;
	/**
	 * 总页数
	 */
	private Long totalCount;
	/**
	 * 每页条数
	 */
	private Integer perPage;
	/**
	 * 当前页数
	 */
	private Integer pageNum;
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public static <T>  PageResultDTO<List<T>> getFailurePageResult(ErrorMsg errorMsg) {
		PageResultDTO<List<T>> failure = new PageResultDTO<List<T>>();
		failure.setErrorCode(errorMsg.code);
		failure.setErrorMes(errorMsg.msg);
		failure.setFailure(true);
		return failure;
	}
	
	
	
}
