package com.zhaoyang.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.Rule;

public class ConditionSettingAction extends AbstractActionSupport {
	private RuleDao ruleDao;
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public String conditionSetPre() throws Exception {
		// TODO Auto-generated method stub
		content=ruleDao.findRuleByRuleId("EducationConditionContent").getRuleDef();
		return SUCCESS;
	}
	public String conditionSet() throws Exception {
		// TODO Auto-generated method stub
		if(content==null||"".equals(content)){
			setErrMsg("教育环境不能为空");
			return SUCCESS;
		}
		ruleDao.update("EducationConditionContent", content);
		//content=ruleDao.findRuleByRuleId("").getRuleDef();
		setSucMsg("教育环境修改成功,<a href='WatchConditionHTMLAction' target='_blank'>去看看</a>");
		return SUCCESS;
	}
	public String watchConditionHTML()throws Exception {
		content=ruleDao.findRuleByRuleId("EducationConditionContent").getRuleDef();
		return SUCCESS;
	}
	
	
	

	
}
