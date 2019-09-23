package cn.xuhuiqiang.creative.common.query;

import java.io.Serializable;

public class PageQuery<T> implements Serializable {
	
	private Integer pageSize = 10;
	private Integer toPage = 1;
	private T query;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getToPage() {
		return toPage;
	}
	public void setToPage(Integer toPage) {
		this.toPage = toPage;
	}
	public T getQuery() {
		return query;
	}
	public void setQuery(T query) {
		this.query = query;
	}
	@Override
	public String toString() {
		return "PageQuery [pageSize=" + pageSize + ", toPage=" + toPage + ", query=" + query + "]";
	}
	
	

}
