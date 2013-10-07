package com.zhaoyang.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;

public class NoticeMngAction extends AbstractActionSupport {
	private NoticeDao noticeDao;
	private RuleDao ruleDao;
	private String title;
	private String content;
	private Long id;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

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
		notices = noticeDao.findNotices(getPageNum(), getSysConfigParameter().getPageSize());
		Long max=noticeDao.noticeCount();
		initMaxPage(max);
		return SUCCESS;
	}
	public String noticeAdd() throws Exception {
		Notice notice= new Notice();
		if (title == null || "".equals(title)) {
			setErrMsg("标题不能为空");
			return SUCCESS;
		}
		if (content == null || "".equals(content)) {
			setErrMsg("内容不能为空");
			return SUCCESS;
		}
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreateTime(new Date());
		noticeDao.save(notice);
		setSucMsg("新增成功，<a href='NoticeMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	public String noticeAddPre() throws Exception {
		return SUCCESS;
	}
	public String noticeDel() throws Exception {
		if(id==null||id==0l){
			setErrMsg("你尚未选择要删除的记录");
		}else{
			try{
				String title=noticeDao.delete(id);
				setSucMsg("删除["+title+"]成功!");
			}catch(Exception e){
				setErrMsg("操作失败,请确定您正常操作");
				//return SUCCESS;
			}
			
		}
		forward("NoticeMngAction?pageNum=1", ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return SUCCESS;
	}
	private String noticePanelList;
	
	public String getNoticePanelList() {
		return noticePanelList;
	}

	public void setNoticePanelList(String noticePanelList) {
		this.noticePanelList = noticePanelList;
	}

	public String noticePanelSetPre() throws Exception {
		//imgPath=ruleDao.findRuleByRuleId("HotNewsBgImg").getRuleDef();
		noticePanelList=ruleDao.findRuleByRuleId("NoticePanelList").getRuleDef();
		return SUCCESS;
	}
	public String noticePanelSet() throws Exception {
		//imgPath=ruleDao.findRuleByRuleId("HotNewsBgImg").getRuleDef();
		if(noticePanelList==null||"".equals(noticePanelList)){
			setErrMsg("公告栏列表不能为空");
		}else{
			ruleDao.update("NoticePanelList",noticePanelList);
			setSucMsg("公告栏列表修改成功");
		}
		return SUCCESS;
	}
	
	
}
