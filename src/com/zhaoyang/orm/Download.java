package com.zhaoyang.orm;
import java.util.Date;

public class Download implements java.io.Serializable {
	
	private Long id;
	private String href;
	private String srcName;
	private Date uplTime;
	private Integer srcType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}
	public Date getUplTime() {
		return uplTime;
	}
	public void setUplTime(Date uplTime) {
		this.uplTime = uplTime;
	}
	public Integer getSrcType() {
		return srcType;
	}
	public void setSrcType(Integer srcType) {
		this.srcType = srcType;
	}
}