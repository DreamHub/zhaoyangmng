package com.zhaoyang.orm;
import java.util.Date;

public class Notice implements java.io.Serializable {
	
	private Long id;
	private String content;
	private String title;
	private Date createTime;
	
	public Notice() {
		super();
	}
	public Notice(String content, String title) {
		super();
		this.content = content;
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}