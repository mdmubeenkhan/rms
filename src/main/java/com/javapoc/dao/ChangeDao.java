package com.javapoc.dao;

public interface ChangeDao {

	public String getShowChangeData(String value);
	public String getEditData(String value);
	public String getChangeData();
	public int updateChangeData(String id, String summary, String name, String project, String type, String severity, String priority, String description, String created_by, String created_at, String status);
	public int editChangeData(String id, int version);

}
