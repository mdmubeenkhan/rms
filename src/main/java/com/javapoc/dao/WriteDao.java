package com.javapoc.dao;

public interface WriteDao {

	public int submitWrite(String summary,String name,String project,String type,String severity,String priority,String description, String created_by, String created_at, String status);
	public String getSubmitData();
	public int saveWrite(String username, String summary, String name, String project, String type, String severity,String priority, String description);
	public String getSaveData(String username);
	public int clearData(String username);

}
