package com.zhaoyang.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.Rule;

public class NewsSettingAction extends AbstractActionSupport {
	private RuleDao ruleDao;
	private File newsImg;
	private String newsImgFileName;
	public File getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(File newsImg) {
		this.newsImg = newsImg;
	}
	public String getNewsImgFileName() {
		return newsImgFileName;
	}
	public void setNewsImgFileName(String newsImgFileName) {
		this.newsImgFileName = newsImgFileName;
	}
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	private String imgPath;
	

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	private String newsDesc;
	
	public String getNewsDesc() {
		return newsDesc;
	}
	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}
	private String newsNoList;
	
	public String getNewsNoList() {
		return newsNoList;
	}
	public void setNewsNoList(String newsNoList) {
		this.newsNoList = newsNoList;
	}
	public String indexNewsSetPre() throws Exception {
		// TODO Auto-generated method stub
		imgPath=ruleDao.findRuleByRuleId("IndexNewsSmallImg").getRuleDef();
		newsNoList=ruleDao.findRuleByRuleId("IndexNewsList").getRuleDef();
		newsDesc=ruleDao.findRuleByRuleId("IndexNewsDesc").getRuleDef();
		return SUCCESS;
	}
	public String hotNewsSetPre() throws Exception {
		// TODO Auto-generated method stub
		imgPath=ruleDao.findRuleByRuleId("HotNewsBgImg").getRuleDef();
		newsNoList=ruleDao.findRuleByRuleId("HotNewsList").getRuleDef();
		return SUCCESS;
	}
	public String indexNewsSet() throws Exception {
		if(newsImg==null){
			setErrMsg("上传图片不能为空");
			//forward("IndexNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
			return SUCCESS;
		}
		if(newsDesc==null||"".equals(newsDesc)){
			setErrMsg("说明不能为空");
			//forward("IndexNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
			return SUCCESS;
		}
		if(newsNoList==null||"".equals(newsNoList)){
			setErrMsg("新闻列表不能为空");
			//forward("IndexNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
			return SUCCESS;
		}
		// TODO Auto-generated method stub
		String realPath = ServletActionContext.getServletContext().getRealPath("/image/index");
		String exp=newsImgFileName.substring(newsImgFileName.lastIndexOf('.')+1);
		String fileName="/news_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(newsImg, file);
		ruleDao.update("IndexNewsSmallImg","image/index"+fileName);
		ruleDao.update("IndexNewsDesc",newsDesc);
		ruleDao.update("IndexNewsList",newsNoList);
		
		imgPath="image/index"+fileName;
		setSucMsg("首页新闻修改成功");
		//forward("IndexNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return SUCCESS;
	}
	public String hotNewsSet() throws Exception{
		if(newsImg==null){
			setErrMsg("上传图片不能为空");
			forward("HotNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
			return SUCCESS;
		}
		if(newsNoList==null||"".equals(newsNoList)){
			setErrMsg("新闻列表不能为空");
			forward("HotNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
			return SUCCESS;
		}
		String realPath = ServletActionContext.getServletContext().getRealPath("/image/news");
		String exp=newsImgFileName.substring(newsImgFileName.lastIndexOf('.')+1);
		String fileName="/news_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(newsImg, file);
		ruleDao.update("HotNewsBgImg","image/news"+fileName);
		ruleDao.update("HotNewsList",newsNoList);
		imgPath="image/news"+fileName;
		setSucMsg("热门新闻修改成功");
		forward("HotNewsSetPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return SUCCESS;
	}
	
}
