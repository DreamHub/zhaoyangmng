package com.zhaoyang.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.Rule;

public class SchoolSettingAction extends AbstractActionSupport {
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

	public String schoolDescSetPre() throws Exception {
		// TODO Auto-generated method stub
		content=ruleDao.findRuleByRuleId("SchoolDescContent").getRuleDef();
		return SUCCESS;
	}
	public String schoolDescSet() throws Exception {
		// TODO Auto-generated method stub
		if(content==null||"".equals(content)){
			setErrMsg("简介不能为空");
			return SUCCESS;
		}
		ruleDao.update("SchoolDescContent", content);
		//content=ruleDao.findRuleByRuleId("").getRuleDef();
		setSucMsg("简介修改成功,<a href='WatchAboutHTMLAction' target='_blank'>去看看</a>");
		return SUCCESS;
	}
	public String watchAboutHTML()throws Exception {
		content=ruleDao.findRuleByRuleId("SchoolDescContent").getRuleDef();
		return SUCCESS;
	}
	
	
	

	
}
