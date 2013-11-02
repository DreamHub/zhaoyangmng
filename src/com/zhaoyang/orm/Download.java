package com.zhaoyang.orm;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONString;

public class Download implements java.io.Serializable ,JSONString{
	
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
	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		String datetime=new SimpleDateFormat("yyyy-MM-dd").format(uplTime);
		return "{\"name\":\""+srcName+"\",\"href\":\""+href+"\",\"datetime\":\""+datetime+"\"}";
	}
}