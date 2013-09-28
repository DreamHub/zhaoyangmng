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

public class DownloadMngAction extends AbstractActionSupport {
	private DownloadDao downloadDao;
	private RuleDao ruleDao;
	private String srcName;
	private Integer srcType;
	private File href;
	private String hrefFileName;
	
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}
	public Integer getSrcType() {
		return srcType;
	}
	public void setSrcType(Integer srcType) {
		this.srcType = srcType;
	}
	public File getHref() {
		return href;
	}
	public void setHref(File href) {
		this.href = href;
	}
	public String getHrefFileName() {
		return hrefFileName;
	}
	public void setHrefFileName(String hrefFileName) {
		this.hrefFileName = hrefFileName;
	}
	public RuleDao getRuleDao() {
		return ruleDao;
	}
	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	public DownloadDao getDownloadDao() {
		return downloadDao;
	}
	public void setDownloadDao(DownloadDao downloadDao) {
		this.downloadDao = downloadDao;
	}
	private Map<Integer,String> downtypes;
	public Map<Integer, String> getDowntypes() {
		return downtypes;
	}
	public void setDowntypes(Map<Integer, String> downtypes) {
		this.downtypes = downtypes;
	}
	private String[] ids;
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	private String[] names;
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	private String newtype;
	
	public String getNewtype() {
		return newtype;
	}
	public void setNewtype(String newtype) {
		this.newtype = newtype;
	}
	public String downloadSrcTypeMngPre() throws Exception {
		Rule rule =ruleDao.findRuleByRuleId("DownloadSrcType");
		ids=rule.getRuleDef().split(",");
		names=rule.getRuleType().split(",");
		downtypes=new HashMap<Integer, String>();
		for (int i = 0; i < ids.length; i++) {
			downtypes.put(Integer.parseInt(ids[i]), names[i]);
		}
		return SUCCESS;
	}
	public String newDownloadSrcType() throws Exception {
		if(newtype==null||"".equals(newtype)){
			setErrMsg("新增类型不能为空");
			return SUCCESS;
		}
		Rule rule =ruleDao.findRuleByRuleId("DownloadSrcType");
		ids=rule.getRuleDef().split(",");
		Integer newId=Integer.parseInt(ids[ids.length-1])+1;
		String newIds=rule.getRuleDef()+","+newId;
		String newNames=rule.getRuleType()+","+newtype;
		ruleDao.update("DownloadSrcType", newIds,newNames);
		setSucMsg("新增["+newtype+"]类型成功");
		return SUCCESS;
	}
	public String deleteDownloadSrcType()throws Exception {
		Rule rule =ruleDao.findRuleByRuleId("DownloadSrcType");
		String newIds=rule.getRuleDef().substring(0, rule.getRuleDef().lastIndexOf(","));
		String newNames=rule.getRuleType().substring(0, rule.getRuleType().lastIndexOf(","));
		ruleDao.update("DownloadSrcType", newIds,newNames);
		setSucMsg("删除类型成功");
		forward("DownloadSrcTypeMngPreAction");
		return SUCCESS;
	}
	public String downloadSrcTypeEdit()throws Exception {
		//if(ids==null||names==null){
		Rule rule =ruleDao.findRuleByRuleId("DownloadSrcType");
		if(names==null||names.length==0){
			setErrMsg("类型总数不能为空");
			forward("DownloadSrcTypeMngPreAction");
			return SUCCESS;
		}
		String newnames="";
		for (int i = 0; i < names.length; i++) {
			if(names[i]==null||"".equals(names[i])){
				setErrMsg("类型不能为空");
				forward("DownloadSrcTypeMngPreAction");
				return SUCCESS;
			}else{
				newnames+=names[i];
				if(i!=(names.length-1)){
					newnames+=",";
				}
			}
		}
		
		String[] oldIds=rule.getRuleDef().split(",");
		if(names.length!=oldIds.length){
			setErrMsg("类型数目不对");
			forward("DownloadSrcTypeMngPreAction");
			return SUCCESS;
		}
		ruleDao.updateRuleType("DownloadSrcType",newnames);
		setSucMsg("修改成功");
		forward("DownloadSrcTypeMngPreAction");
		return SUCCESS;
	}
	
	private List<Download> downloads;
	
	public List<Download> getDownloads() {
		return downloads;
	}
	public void setDownloads(List<Download> downloads) {
		this.downloads = downloads;
	}
	
	@Override
	public String execute() throws Exception {
		downloads = downloadDao.findDownloads(getPageNum(), getSysConfigParameter().getPageSize());
		Long max=downloadDao.downloadsCount();
		initMaxPage(max);
		//得到下载类型
		this.downloadSrcTypeMngPre();
		return SUCCESS;
	}
	public String downloadAddPre() throws Exception {
		return this.downloadSrcTypeMngPre();
	}
	public String downloadAdd() throws Exception {
		if(srcType==null||srcType==0){
			setErrMsg("资源类型不能为空");
			return ERROR;
		}
		if(srcName==null||"".equals(srcName)){
			setErrMsg("标题不能为空");
			return ERROR;
		}
		if(href==null){
			setErrMsg("资源文件不能为空");
			return ERROR;
		}
		String realPath = ServletActionContext.getServletContext().getRealPath("/downloads");
		String exp=hrefFileName.substring(hrefFileName.lastIndexOf('.')+1);
		String fileName="/ds_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(href, file);
		Download download=new Download();
		download.setHref("downloads"+fileName);
		download.setSrcName(srcName);
		download.setUplTime(new Date());
		download.setSrcType(srcType);
		downloadDao.save(download);
		setSucMsg("新增成功，<a href='DownloadMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String downloadDel() throws Exception {
		if(id==null||id==0l){
			setErrMsg("你尚未选择要删除的记录");
		}else{
			try{
				String srcname=downloadDao.delete(id);
				setSucMsg("删除["+srcname+"]成功!");
			}catch(Exception e){
				setErrMsg("操作失败,请确定您正常操作");
			}
			
		}
		setPageNum(1);
		return SUCCESS;
	}
	private String hrefPath;
	
	public String getHrefPath() {
		return hrefPath;
	}
	public void setHrefPath(String hrefPath) {
		this.hrefPath = hrefPath;
	}
	public String downloadEditPre() throws Exception {
		Download download=downloadDao.findById(id);
		if(download!=null){
			setSrcName(download.getSrcName());
			setSrcType(download.getSrcType());
			setHrefPath(download.getHref());
			setId(download.getId());
		}
		this.downloadSrcTypeMngPre();
		return SUCCESS;
	}
	public String downloadEdit() throws Exception {
		if (id == null) {
			setErrMsg("参数不对");
			return SUCCESS;
		}
		if (href == null) {
			setErrMsg("上传资源文件不能为空");
			return SUCCESS;
		}
		if (srcType == null || srcType==0) {
			setErrMsg("类型不能为空");
			return SUCCESS;
		}
		if (srcName == null || "".equals(srcName)) {
			setErrMsg("标题不能为空");
			return SUCCESS;
		}
		String realPath = ServletActionContext.getServletContext().getRealPath("/downloads");
		String exp=hrefFileName.substring(hrefFileName.lastIndexOf('.')+1);
		String fileName="/ds_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(href, file);
		Download download=new Download();
		download.setId(id);
		download.setHref("downloads"+fileName);
		download.setSrcName(srcName);
		download.setUplTime(new Date());
		download.setSrcType(srcType);
		downloadDao.updateDownload(download);
		setSucMsg("修改成功，<a href='DownloadMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
}
