package com.zhaoyang.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zhaoyang.data.IndexImgLoop;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class IndexImgLoopDao{
	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public List<IndexImgLoop> findAll() throws Exception {
		Rule rule = ruleDao.findRuleByRuleId("IndexImgLoop");
		JSONArray array = new JSONArray(rule.getRuleDef());
		List<IndexImgLoop> indexImgLoops = new ArrayList<IndexImgLoop>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject jobj = (JSONObject) array.get(i);
			IndexImgLoop indexImgLoop = new IndexImgLoop();
			indexImgLoop.setId(jobj.getLong("id"));
			indexImgLoop.setImgdesc(jobj.getString("imgdesc"));
			indexImgLoop.setHref(jobj.getString("href"));
			indexImgLoop.setImgPath(jobj.getString("imgPath"));
			indexImgLoops.add(indexImgLoop);
		}
		return indexImgLoops;
	}
	public IndexImgLoop findById(Long id) throws Exception{
		List<IndexImgLoop> indexImgLoops=findAll();
		for (IndexImgLoop indexImgLoop : indexImgLoops) {
			if(indexImgLoop.getId().equals(id)){
				return indexImgLoop;
			}
		}
		return null;
	}
	
	public void save(IndexImgLoop indexImgLoop) throws Exception{
		List<IndexImgLoop> indexImgLoops=findAll();
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
		ruleDao.update("IndexImgLoop", AAA);
	}
	public boolean delete(Long id)throws Exception{
		List<IndexImgLoop> indexImgLoops=findAll();
		for (IndexImgLoop indexImgLoop : indexImgLoops) {
			if(indexImgLoop.getId().equals(id)){
				indexImgLoops.remove(indexImgLoop);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(indexImgLoops);
				String AAA=jsonArray.toString();
				ruleDao.update("IndexImgLoop", AAA);
				return true;
			}
		}
		return false;
	}
	public boolean update(IndexImgLoop indexImgLoop)throws Exception{
		List<IndexImgLoop> indexImgLoops=findAll();
		for (int i = 0; i < indexImgLoops.size(); i++) {
			if(indexImgLoops.get(i).getId().equals(indexImgLoop.getId())){
				if(indexImgLoop.getHref()!=null){
					indexImgLoops.get(i).setHref(indexImgLoop.getHref());
				}
				if(indexImgLoop.getImgdesc()!=null){
					indexImgLoops.get(i).setImgdesc(indexImgLoop.getImgdesc());
				}
				if(indexImgLoop.getImgPath()!=null){
					indexImgLoops.get(i).setImgPath(indexImgLoop.getImgPath());
				}
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(indexImgLoops);
				String AAA=jsonArray.toString();
				ruleDao.update("IndexImgLoop", AAA);
				return true;
			}
		}
		return false;
		
	}

}
