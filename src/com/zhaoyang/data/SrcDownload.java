package com.zhaoyang.data;

import java.util.List;

import com.zhaoyang.orm.Download;

public class SrcDownload {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	private List<Download> list;

	public List<Download> getList() {
		return list;
	}

	public void setList(List<Download> list) {
		this.list = list;
	}
}
