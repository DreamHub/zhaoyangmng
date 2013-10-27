package com.zhaoyang.action;

import java.io.File;
import java.util.List;

import com.zhaoyang.dao.IndexImgLoopDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.data.IndexImgLoop;

public class IndexSettingAction extends AbstractActionSupport {

	private IndexImgLoopDao indexImgLoopDao;

	public IndexImgLoopDao getIndexImgLoopDao() {
		return indexImgLoopDao;
	}

	public void setIndexImgLoopDao(IndexImgLoopDao indexImgLoopDao) {
		this.indexImgLoopDao = indexImgLoopDao;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private List<IndexImgLoop> indexImgLoops;

	public List<IndexImgLoop> getIndexImgLoops() {
		return indexImgLoops;
	}

	public void setIndexImgLoops(List<IndexImgLoop> indexImgLoops) {
		this.indexImgLoops = indexImgLoops;
	}

	private IndexImgLoop indexImgLoop;
	private File newImg;
	private String newImgFileName;

	public File getNewImg() {
		return newImg;
	}

	public void setNewImg(File newImg) {
		this.newImg = newImg;
	}

	public String getNewImgFileName() {
		return newImgFileName;
	}

	public void setNewImgFileName(String newImgFileName) {
		this.newImgFileName = newImgFileName;
	}

	public IndexImgLoop getIndexImgLoop() {
		return indexImgLoop;
	}

	public void setIndexImgLoop(IndexImgLoop indexImgLoop) {
		this.indexImgLoop = indexImgLoop;
	}

	public String indexImgLoopMng() throws Exception {
		// TODO Auto-generated method stub
		indexImgLoops = indexImgLoopDao.findAll();
		return SUCCESS;
	}

	public String indexImgLoopAdd() throws Exception {
		// TODO Auto-generated method stub
		if (newImg == null) {
			setErrMsg("图片不能为空");
		}
		indexImgLoop.setImgPath(saveImg(newImg, "image/index", newImgFileName));
		indexImgLoopDao.save(indexImgLoop);
		setSucMsg("添加成功");
		return SUCCESS;
	}

	public String indexImgLoopEdit() throws Exception {
		// TODO Auto-generated method stub
		if (newImg != null) {
			indexImgLoop.setImgPath(saveImg(newImg, "image/index",newImgFileName));
		}
		indexImgLoopDao.update(indexImgLoop);
		setSucMsg("修改成功");
		return SUCCESS;
	}
	public String indexImgLoopDel() throws Exception {
		// TODO Auto-generated method stub
		
		if(indexImgLoopDao.delete(indexImgLoop.getId())){
			setSucMsg("删除成功");
		}else{
			setErrMsg("删除失败");
		}
		
		return SUCCESS;
	}
	private String[] sevenReasons;
	
	public String[] getSevenReasons() {
		return sevenReasons;
	}

	public void setSevenReasons(String[] sevenReasons) {
		this.sevenReasons = sevenReasons;
	}
	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public String sevenReasonMng()throws Exception {
		String ruledef=ruleDao.findRuleByRuleId("SevenReason").getRuleDef();
		sevenReasons=ruledef.split(";");
		return SUCCESS;
	}
	private String indexAdvImgPath1;
	private String indexAdvImgPath2;
	
	public String getIndexAdvImgPath1() {
		return indexAdvImgPath1;
	}

	public void setIndexAdvImgPath1(String indexAdvImgPath1) {
		this.indexAdvImgPath1 = indexAdvImgPath1;
	}

	public String getIndexAdvImgPath2() {
		return indexAdvImgPath2;
	}

	public void setIndexAdvImgPath2(String indexAdvImgPath2) {
		this.indexAdvImgPath2 = indexAdvImgPath2;
	}

	public String indexAdvImgMng()throws Exception {
		indexAdvImgPath1=ruleDao.findRuleByRuleId("IndexAdvImg1").getRuleDef();
		indexAdvImgPath2=ruleDao.findRuleByRuleId("IndexAdvImg2").getRuleDef();
		return SUCCESS;
	}
	
	public String sevenReasonEdit()throws Exception {
		StringBuffer sb=new StringBuffer();
		for (String reason: sevenReasons) {
			if(reason==null||"".equals(reason)){
				setErrMsg("理由不能为空");
				return SUCCESS;
			}
			sb.append(reason+";");
		}
		sb.deleteCharAt(sb.length()-1);
		ruleDao.update("SevenReason", sb.toString());
		setSucMsg("修改成功");
		return SUCCESS;
	}
	
	public String indexAdvImgEdit()throws Exception {
		
		indexAdvImgPath1=ruleDao.findRuleByRuleId("IndexAdvImg1").getRuleDef();
		indexAdvImgPath2=ruleDao.findRuleByRuleId("IndexAdvImg2").getRuleDef();
		return SUCCESS;
	}
}
