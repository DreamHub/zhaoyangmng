package com.zhaoyang.orm;
import java.util.Date;

public class News implements java.io.Serializable {
	
	private Long id;
	private String content;
	private String title;
	private String newsDesc;
	private Date createTime;
	private String newsKeyword;
	
	public News() {
		super();
	}
	public News(String content, String title) {
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
	public String getNewsDesc() {
		return newsDesc;
	}
	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getNewsKeyword() {
		return newsKeyword;
	}
	public void setNewsKeyword(String newsKeyword) {
		this.newsKeyword = newsKeyword;
	}
	
}