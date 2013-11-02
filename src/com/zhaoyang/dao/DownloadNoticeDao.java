package com.zhaoyang.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zhaoyang.data.DownloadNotice;
import com.zhaoyang.data.IndexImgLoop;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class DownloadNoticeDao{
	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public List<DownloadNotice> findAll() throws Exception {
		Rule rule =ruleDao.findRuleByRuleId("DownloadNoticeList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		List<DownloadNotice> downloadNotices=new ArrayList<DownloadNotice>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			DownloadNotice downloadNotice=new DownloadNotice();
			downloadNotice.setId(jobj.getLong("id"));
			downloadNotice.setContent(jobj.getString("content"));
			downloadNotice.setHref(jobj.getString("href"));
			downloadNotices.add(downloadNotice);
		}
		return downloadNotices;
	}
	public DownloadNotice findById(Long id) throws Exception{
		List<DownloadNotice> downloadNotices=findAll();
		for (DownloadNotice downloadNotice : downloadNotices) {
			if(downloadNotice.getId().equals(id)){
				return downloadNotice;
			}
		}
		return null;
	}
	
	public void save(DownloadNotice indexImgLoop) throws Exception{
		List<DownloadNotice> indexImgLoops=findAll();
		Long newid;
		if(indexImgLoops.size()>0){
			newid=indexImgLoops.get(indexImgLoops.size()-1).getId()+1;
		}else{
			newid=1l;
		}
		indexImgLoop.setId(newid);
		indexImgLoops.add(indexImgLoop);
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(indexImgLoops);
		String AAA=jsonArray.toString();
		ruleDao.update("DownloadNoticeList", AAA);
	}
	public boolean delete(Long id)throws Exception{
		List<DownloadNotice> indexImgLoops=findAll();
		for (DownloadNotice indexImgLoop : indexImgLoops) {
			if(indexImgLoop.getId().equals(id)){
				indexImgLoops.remove(indexImgLoop);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(indexImgLoops);
				String AAA=jsonArray.toString();
				ruleDao.update("DownloadNoticeList", AAA);
				return true;
			}
		}
		return false;
	}

}
