package com.javapoc.dao;

public interface ReviewDao {

	public String getReviewData(String value);
	public String getShowReviewData(String value);
	public int approveReviewData(String value);
	public String getApproveData(String value);
	public int rejectReviewData(String value);
	public String getRejectData(String value);
	public int modifyReviewData(String value);
	public String getModifyData(String value);

}
