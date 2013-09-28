package com.zhaoyang.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.orm.News;

public class NewsMngAction extends AbstractActionSupport {
	private NewsDao newsDao;
	private String title;
	private String content;
	private String newsDesc;
	private String newsKeyword;
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

	public String getNewsDesc() {
		return newsDesc;
	}

	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}

	public String getNewsKeyword() {
		return newsKeyword;
	}

	public void setNewsKeyword(String newsKeyword) {
		this.newsKeyword = newsKeyword;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	private List<News> newses;

	public List<News> getNewses() {
		return newses;
	}

	public void setNewses(List<News> newses) {
		this.newses = newses;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		newses = newsDao.findNews(getPageNum(), getSysConfigParameter().getPageSize());
		Long max=newsDao.newsCount();
		initMaxPage(max);
		return SUCCESS;
	}

	public String newsAddPre() throws Exception {
		return SUCCESS;
	}

	public String newsAdd() throws Exception {
		News news = new News();
		if (title == null || "".equals(title)) {
			setErrMsg("标题不能为空");
			return SUCCESS;
		}
		if (content == null || "".equals(content)) {
			setErrMsg("内容不能为空");
			return SUCCESS;
		}
		if (newsDesc == null || "".equals(newsDesc)) {
			setErrMsg("描述不能为空");
			return SUCCESS;
		}
		if (newsKeyword == null || "".equals(newsKeyword)) {
			setErrMsg("关键字不能为空");
			return SUCCESS;
		}
		news.setTitle(title);
		news.setContent(content);
		news.setCreateTime(new Date());
		news.setNewsKeyword(newsKeyword);
		news.setNewsDesc(newsDesc);
		newsDao.save(news);
		setSucMsg("新增成功，<a href='NewsMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}

	public String newsDel() throws Exception {
		if(id==null||id==0l){
			setErrMsg("你尚未选择要删除的记录");
		}else{
			try{
				String title=newsDao.delete(id);
				setSucMsg("删除["+title+"]成功!");
			}catch(Exception e){
				setErrMsg("操作失败,请确定您正常操作");
				//return SUCCESS;
			}
			
		}
		forward("NewsMngAction?pageNum=1", ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return SUCCESS;
	}
	public String newsEditPre() throws Exception {
		News news=newsDao.findById(id);
		if(news!=null){
			setTitle(news.getTitle());
			setContent(news.getContent());
			setNewsDesc(news.getNewsDesc());
			setNewsKeyword(news.getNewsKeyword());
		}
		return SUCCESS;
	}
	public String newsEdit() throws Exception {
		if(id==null){
			setErrMsg("请求参数不对");
			return ERROR;
		}
		if (title == null || "".equals(title)) {
			setErrMsg("标题不能为空");
			return ERROR;
		}
		if (content == null || "".equals(content)) {
			setErrMsg("内容不能为空");
			return ERROR;
		}
		if (newsDesc == null || "".equals(newsDesc)) {
			setErrMsg("描述不能为空");
			return ERROR;
		}
		if (newsKeyword == null || "".equals(newsKeyword)) {
			setErrMsg("关键字不能为空");
			return ERROR;
		}
		News news=new News();
		news.setId(id);
		news.setContent(content);
		news.setNewsDesc(newsDesc);
		news.setNewsKeyword(newsKeyword);
		news.setCreateTime(new Date());
		news.setTitle(title);
		newsDao.updateNews(news);
		setSucMsg("修改成功，<a href='NewsMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	
}
