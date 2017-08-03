package com.aspire.ssm.util;

import java.io.Serializable;

public class Page implements Serializable {
	
	private static final long serialVersionUID = -5891385672987071022L;
	private int pageNow = 1;
	private int pageSize = 4;
	private int totalCount;
	private int totalPageCount;
	@SuppressWarnings("unused")
	private int startPos;
	@SuppressWarnings("unused")
	private boolean hasFirst;
	@SuppressWarnings("unused")
	private boolean hasPre;
	@SuppressWarnings("unused")
	private boolean hasNext;
	@SuppressWarnings("unused")
	private boolean hasLast;
	
	public Page(int totalCount, int pageNow){
		this.totalCount = totalCount;
		this.pageNow = pageNow;
	}
	
	public int getTotalPageCount(){
		totalPageCount = getTotalCount()/getPageSize();
		return (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;
	}
	
	public void setTotalPageCount(int totalPageCount){
		this.totalPageCount = totalPageCount;
	}
	
	public int getPageNow(){
		return pageNow;
	}
	
	public void setPageNow(int pageNow){
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getStartPos(){
		return (pageNow - 1) * pageSize;
	}
	
	public void setStartPos(int startPos){
		this.startPos = startPos;
	}
	
	public boolean isHasFirst(){
		return (pageNow == 1) ? false : true;
	}
	
	public void setHasFirst(boolean hasFirst){
		this.hasFirst = hasFirst;
	}
	
	public boolean isHasPre(){
		return isHasFirst() ? true : false;
	}
	
	public void setHasPre(boolean hasPre){
		this.hasPre = hasPre;
	}
	
	public boolean isHasNext(){
		return isHasNext() ? true : false;
	}
	
	public void setHasNext(boolean hasNext){
		this.hasNext = hasNext;
	}
	
	public boolean isHasLast(){
		return (pageNow == getTotalCount()) ? false : true;
	}
	
	public void setHasLast(boolean hasLast){
		this.hasLast = hasLast;
	}

}
