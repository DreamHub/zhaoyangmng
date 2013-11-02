package com.zhaoyang.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhaoyang.data.Student;
import com.zhaoyang.data.TailLink;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class TailLinkDao {
	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public List<TailLink> findAll() throws Exception{
		Rule rule =ruleDao.findRuleByRuleId("TailLinkList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		List<TailLink> students=new ArrayList<TailLink>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			TailLink student=new TailLink();
			student.setId(jobj.getLong("id"));
			student.setHref(jobj.getString("href"));
			student.setText(jobj.getString("text"));
			students.add(student);
		}
		return students;
	}
	public void save(TailLink studentTmp) throws Exception{
		List<TailLink> students=findAll();
		Long newid;
		if(students.size()>0){
			newid=students.get(students.size()-1).getId()+1;
		}else{
			newid=1l;
		}
		studentTmp.setId(newid);
		students.add(studentTmp);
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(students);
		String AAA=jsonArray.toString();
		ruleDao.update("TailLinkList", AAA);
	}
	public boolean delete(Long id)throws Exception{
		List<TailLink> students=findAll();
		for (TailLink student : students) {
			if(student.getId().equals(id)){
				students.remove(student);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(students);
				String AAA=jsonArray.toString();
				ruleDao.update("TailLinkList", AAA);
				return true;
			}
		}
		return false;
	}
	public boolean update(TailLink studentTmp)throws Exception{
		List<TailLink> students=findAll();
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).getId().equals(studentTmp.getId())){
				if(studentTmp.getHref()!=null){
					students.get(i).setHref(studentTmp.getHref());
				}
				if(studentTmp.getText()!=null){
					students.get(i).setText(studentTmp.getText());
				}
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(students);
				String AAA=jsonArray.toString();
				ruleDao.update("TailLinkList", AAA);
				return true;
			}
		}
		return false;
		
	}

	public TailLink findById(Long id) throws Exception{
		List<TailLink> students=findAll();
		for (TailLink student : students) {
			if(student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}

}
