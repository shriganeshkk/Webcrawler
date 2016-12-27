package com.ganesh.onlyphones.model;
/**
 * @author shriganesh
 *
 */
public class CurrentStatus {
	private long successCount=0;
	private long failureCount=0;
	private String currentBrand=null;
	private String currentPage=null;
	
	public long getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}
	public long getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(long failureCount) {
		this.failureCount = failureCount;
	}
	public String getCurrentBrand() {
		return currentBrand;
	}
	public void setCurrentBrand(String currentBrand) {
		this.currentBrand = currentBrand;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public CurrentStatus add(CurrentStatus tempStatus){
		this.setSuccessCount(tempStatus.getSuccessCount());
		this.setFailureCount(tempStatus.getFailureCount());
		return this;
	}
}
