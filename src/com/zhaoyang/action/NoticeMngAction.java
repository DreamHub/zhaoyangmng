package com.zhaoyang.action;

import java.util.List;

import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;

public class NoticeMngAction extends AbstractActionSupport {
	private NoticeDao noticeDao;
	private String title;
	private String content;
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	private List<Notice> notices;


	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
