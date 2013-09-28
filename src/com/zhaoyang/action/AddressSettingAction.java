package com.zhaoyang.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.Rule;

public class AddressSettingAction extends AbstractActionSupport {
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

	public String addressSetPre() throws Exception {
		// TODO Auto-generated method stub
		content=ruleDao.findRuleByRuleId("SchoolAddressContent").getRuleDef();
		return SUCCESS;
	}
	public String addressSet() throws Exception {
		// TODO Auto-generated method stub
		if(content==null||"".equals(content)){
			setErrMsg("办学地址不能为空");
			return SUCCESS;
		}
		ruleDao.update("SchoolAddressContent", content);
		//content=ruleDao.findRuleByRuleId("").getRuleDef();
		setSucMsg("办学地址修改成功,<a href='WatchAddressHTMLAction' target='_blank'>去看看</a>");
		return SUCCESS;
	}
	public String watchAddressHTML()throws Exception {
		content=ruleDao.findRuleByRuleId("SchoolAddressContent").getRuleDef();
		return SUCCESS;
	}
	
	
	

	
}
