package com.zhaoyang.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.DownloadDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.Download;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Rule;

public class OtherMngAction extends AbstractActionSupport {

	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	private String headImgPath;
	private File img;
	private String imgFileName;
	
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	public String headImgMng()throws Exception {
		headImgPath=ruleDao.findRuleByRuleId("HeadBgImg").getRuleDef();
		return SUCCESS;
	}
	public String headImgEdit()throws Exception {
		if(img==null){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		ruleDao.update("HeadBgImg", saveImg(img, "image", imgFileName));
		setSucMsg("修改成功");
		return SUCCESS;
	}
}
