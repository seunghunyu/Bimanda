package com.bit.bimanda.vo;

public class TaskVo {
	private Long taskNo;
	private Long projNo;
	private String taskTitle;
	private Long taskState;
	private Long[] idx;
	private String UserId;
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public TaskVo() {
		super();
	}

	public Long[] getIdx() {
		return idx;
	}
	public void setIdx(Long[] idx) {
		this.idx = idx;
	}

	public Long getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(Long taskNo) {
		this.taskNo = taskNo;
	}


	public String getTaskTitle() {
		return taskTitle;
	}


	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}


	public Long getProjNo() {
		return projNo;
	}


	public void setProjNo(Long projNo) {
		this.projNo = projNo;
	}


	public Long getTaskState() {
		return taskState;
	}


	public void setTaskState(Long taskState) {
		this.taskState = taskState;
	}
	
	
	
	
}
