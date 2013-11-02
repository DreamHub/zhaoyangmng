package com.zhaoyang.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.zhaoyang.dao.RuleDao;

public class SystemMngAction extends AbstractActionSupport {

	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	private int newPageSize;

	public int getNewPageSize() {
		return newPageSize;
	}

	public void setNewPageSize(int newPageSize) {
		this.newPageSize = newPageSize;
	}

	public String pageSizeEdit() throws Exception {
		if(newPageSize==0){
			setErrMsg("分页大小不能为0");
			return SUCCESS;
		}
		if(websiteDirPath==null||"".equals(websiteDirPath)){
			setErrMsg("路径不能为空");
			return SUCCESS;
		}
		String path = this.getClass().getClassLoader()
				.getResource("localdata.properties").getFile();
		FileInputStream fs = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(fs);
		fs.close();
		properties.setProperty("pageSize", String.valueOf(newPageSize));
		
		FileOutputStream fos = new FileOutputStream(path);
		properties.store(fos, "Copyright (c) Boxcode Studio");
		fos.flush();
		fos.close();
		this.getSysConfigParameter().setPageSize(String.valueOf(newPageSize));
		ruleDao.update("WebsiteDirPath", websiteDirPath);
		setSucMsg("修改成功");
		return SUCCESS;
	}
	private String websiteDirPath;
	
	public String getWebsiteDirPath() {
		return websiteDirPath;
	}

	public void setWebsiteDirPath(String websiteDirPath) {
		this.websiteDirPath = websiteDirPath;
	}

	public String pageSizeMng() throws Exception {

		String path = this.getClass().getClassLoader()
				.getResource("localdata.properties").getFile();
		FileInputStream fs = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(fs);
		fs.close();
		newPageSize = Integer.parseInt(properties.getProperty("pageSize"));
		websiteDirPath=ruleDao.findRuleByRuleId("WebsiteDirPath").getRuleDef();
		return SUCCESS;
	}

}
