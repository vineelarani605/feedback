package com.techwl.feedo.handlers;

public class UserSurveyRequest {

	private String title;
	private int[] userIds;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int[] getUserIds() {
		return userIds;
	}
	public void setUserIds(int[] userIds) {
		this.userIds = userIds;
	}
	
	
}
