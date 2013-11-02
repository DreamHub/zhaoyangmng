package com.zhaoyang.action;

import java.io.File;
import java.util.List;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.TailLinkDao;
import com.zhaoyang.data.TailLink;

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
	private List<TailLink> tailLinks;

	public List<TailLink> getTailLinks() {
		return tailLinks;
	}

	public void setTailLinks(List<TailLink> tailLinks) {
		this.tailLinks = tailLinks;
	}
	private TailLinkDao tailLinkDao;
	private TailLink tailLink;
	
	public TailLink getTailLink() {
		return tailLink;
	}

	public void setTailLink(TailLink tailLink) {
		this.tailLink = tailLink;
	}

	public TailLinkDao getTailLinkDao() {
		return tailLinkDao;
	}

	public void setTailLinkDao(TailLinkDao tailLinkDao) {
		this.tailLinkDao = tailLinkDao;
	}

	public String tailLinkMng()throws Exception {
		tailLinks=tailLinkDao.findAll();
		return SUCCESS;
	}
	public String tailLinkAdd()throws Exception {
		if(tailLink==null){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		if(tailLink.getText()==null||"".equals(tailLink.getText())){
			setErrMsg("内容不能为空");
			return SUCCESS;
		}
		if(tailLink.getHref()==null||"".equals(tailLink.getHref())){
			setErrMsg("链接不能为空");
			return SUCCESS;
		}
		tailLinkDao.save(tailLink);
		return SUCCESS;
	}
	public String tailLinkDel()throws Exception {
		if(tailLink.getId()==null){
			setErrMsg("参数无效");
			return SUCCESS;
		}
		if(tailLinkDao.delete(tailLink.getId())){
			setSucMsg("删除成功");
		}else{
			setErrMsg("删除失败");
		}
		return SUCCESS;
	}
	private String tailDescFirst;
	private String tailDescSecond;
	private String tailDescThird;
	private String websiteStatement;
	


	public String getTailDescFirst() {
		return tailDescFirst;
	}

	public void setTailDescFirst(String tailDescFirst) {
		this.tailDescFirst = tailDescFirst;
	}

	public String getTailDescSecond() {
		return tailDescSecond;
	}

	public void setTailDescSecond(String tailDescSecond) {
		this.tailDescSecond = tailDescSecond;
	}

	public String getTailDescThird() {
		return tailDescThird;
	}

	public void setTailDescThird(String tailDescThird) {
		this.tailDescThird = tailDescThird;
	}

	public String getWebsiteStatement() {
		return websiteStatement;
	}

	public void setWebsiteStatement(String websiteStatement) {
		this.websiteStatement = websiteStatement;
	}

	public String tailDescAndStmtMng()throws Exception {
		tailDescFirst=ruleDao.findRuleByRuleId("TailDescFirst").getRuleDef();
		tailDescSecond=ruleDao.findRuleByRuleId("TailDescSecond").getRuleDef();
		tailDescThird=ruleDao.findRuleByRuleId("TailDescThird").getRuleDef();
		websiteStatement=ruleDao.findRuleByRuleId("WebsiteStatement").getRuleDef();
		return SUCCESS;
	}
	public String tailDescAndStmtEdit()throws Exception {
		if(tailDescFirst==null||"".equals(tailDescFirst)){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		if(tailDescSecond==null||"".equals(tailDescSecond)){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		if(tailDescThird==null||"".equals(tailDescThird)){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		if(websiteStatement==null||"".equals(websiteStatement)){
			setErrMsg("不能为空");
			return SUCCESS;
		}
		ruleDao.update("TailDescFirst", tailDescFirst);
		ruleDao.update("TailDescSecond", tailDescSecond);
		ruleDao.update("TailDescThird", tailDescThird);
		ruleDao.update("WebsiteStatement", websiteStatement);
		setSucMsg("修改成功");
		return SUCCESS;
	}
		
}
