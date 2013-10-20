package com.zhaoyang.data;

public class DownloadNotice {
	private Long id;
	private String href;
	private String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "DownloadNotice [id=" + id + ", href=" + href + ", content="
				+ content + "]";
	}
	
}
