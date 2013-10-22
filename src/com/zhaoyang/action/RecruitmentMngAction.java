package com.zhaoyang.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.data.DownloadNotice;
import com.zhaoyang.data.RecruitmentInfo;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class RecruitmentMngAction extends AbstractActionSupport {
	private NoticeDao noticeDao;
	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	private List<Notice> notices;
	
	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public String watchRecruitmentHTML() throws Exception {
		Rule rule=ruleDao.findRuleByRuleId("NoticePanelList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		notices=new ArrayList<Notice>();
		for (int i = 0; i < ids.length; i++) {
			Notice notice=noticeDao.findById(Long.parseLong(ids[i]));
			notices.add(notice);
		}
		return SUCCESS;
	}
	public String recruitmentMngPre() throws Exception{
		return SUCCESS;
	}
	private List<RecruitmentInfo> recruitmentInfos;
	
	public List<RecruitmentInfo> getRecruitmentInfos() {
		return recruitmentInfos;
	}

	public void setRecruitmentInfos(List<RecruitmentInfo> recruitmentInfos) {
		this.recruitmentInfos = recruitmentInfos;
	}

	public String recruitmentInfoMng() throws Exception{
		Rule rule=ruleDao.findRuleByRuleId("RecruitmentInfoList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		recruitmentInfos=new ArrayList<RecruitmentInfo>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			RecruitmentInfo recruitmentInfo=new RecruitmentInfo();
			recruitmentInfo.setId(jobj.getLong("id"));
			recruitmentInfo.setContent(jobj.getString("content"));
			recruitmentInfos.add(recruitmentInfo);
		}
		return SUCCESS;
	}
	private String newcontent;
	
	public String getNewcontent() {
		return newcontent;
	}

	public void setNewcontent(String newcontent) {
		this.newcontent = newcontent;
	}

	/**
	 * 新增招聘信息
	 * @return
	 * @throws Exception
	 */
	public String recruitmentInfoAdd() throws Exception{
		if(newcontent==null||"".equals(newcontent)){
			setErrMsg("内容不能为空");
			return SUCCESS;
		}
		Rule rule=ruleDao.findRuleByRuleId("RecruitmentInfoList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		recruitmentInfos=new ArrayList<RecruitmentInfo>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			RecruitmentInfo recruitmentInfo=new RecruitmentInfo();
			recruitmentInfo.setId(jobj.getLong("id"));
			recruitmentInfo.setContent(jobj.getString("content"));
			recruitmentInfos.add(recruitmentInfo);
		}
		RecruitmentInfo recruitmentInfo=new RecruitmentInfo();
		recruitmentInfo.setContent(newcontent);
		Long newid;
		if(recruitmentInfos.size()>0){
			newid=recruitmentInfos.get(recruitmentInfos.size()-1).getId()+1;
		}else{
			newid=1l;
		}
		recruitmentInfo.setId(newid);
		recruitmentInfos.add(recruitmentInfo);
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(recruitmentInfos);
		String AAA=jsonArray.toString();
		ruleDao.update("RecruitmentInfoList", AAA);
		return SUCCESS;
	}
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 删除招聘信息
	 * @return
	 * @throws Exception
	 */
	public String recruitmentInfoDel()throws Exception {
		Rule rule =ruleDao.findRuleByRuleId("RecruitmentInfoList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		recruitmentInfos=new ArrayList<RecruitmentInfo>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			RecruitmentInfo recruitmentInfo=new RecruitmentInfo();
			recruitmentInfo.setId(jobj.getLong("id"));
			recruitmentInfo.setContent(jobj.getString("content"));
			recruitmentInfos.add(recruitmentInfo);
		}
		int flag=0;
		for (RecruitmentInfo recruitmentInfo : recruitmentInfos) {
			if(recruitmentInfo.getId().equals(id)){
				recruitmentInfos.remove(recruitmentInfo);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(recruitmentInfos);
				String AAA=jsonArray.toString();
				ruleDao.update("RecruitmentInfoList", AAA);
				setSucMsg("删除["+recruitmentInfo.getContent()+"]成功");
				flag=1;
				break;
			}
		}
		if(flag==0){
			setErrMsg("操作失败,请确定您正常操作");
		}
		return SUCCESS;
	}
	public String recruitmentInfoEdit()throws Exception {
		Rule rule =ruleDao.findRuleByRuleId("RecruitmentInfoList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		recruitmentInfos=new ArrayList<RecruitmentInfo>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			RecruitmentInfo recruitmentInfo=new RecruitmentInfo();
			recruitmentInfo.setId(jobj.getLong("id"));
			recruitmentInfo.setContent(jobj.getString("content"));
			recruitmentInfos.add(recruitmentInfo);
		}
		int flag=0;
		for (int i = 0; i < recruitmentInfos.size(); i++) {
			if(recruitmentInfos.get(i).getId().equals(id)){
				recruitmentInfos.get(i).setContent(newcontent);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(recruitmentInfos);
				String AAA=jsonArray.toString();
				ruleDao.update("RecruitmentInfoList", AAA);
				setSucMsg("修改成功");
				flag=1;
				break;
			}
		}
		if(flag==0){
			setErrMsg("操作失败,请确定您正常操作");
		}
		return SUCCESS;
	}
	
}
