package com.zhaoyang.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhaoyang.data.DownloadNotice;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class TeacherDao {
	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public List<Teacher> findAll() throws Exception{
		Rule rule =ruleDao.findRuleByRuleId("TeachersList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		List<Teacher> teachers=new ArrayList<Teacher>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			Teacher teacher=new Teacher();
			teacher.setId(jobj.getLong("id"));
			teacher.setTeaName(jobj.getString("teaName"));
			teacher.setService(jobj.getString("service"));
			teacher.setFeature(jobj.getString("feature"));
			teacher.setProject(jobj.getString("project"));
			teacher.setPhotoImg(jobj.getString("photoImg"));
			teachers.add(teacher);
		}
		return teachers;
	}
	public void save(Teacher teacherTmp) throws Exception{
		List<Teacher> teachers=findAll();
		Long newid;
		if(teachers.size()>0){
			newid=teachers.get(teachers.size()-1).getId()+1;
		}else{
			newid=1l;
		}
		teacherTmp.setId(newid);
		teachers.add(teacherTmp);
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(teachers);
		
		String AAA=jsonArray.toString();
		ruleDao.update("TeachersList", AAA);
	}
	public boolean delete(Long id)throws Exception{
		List<Teacher> teachers=findAll();
		for (Teacher teacher : teachers) {
			if(teacher.getId().equals(id)){
				teachers.remove(teacher);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(teachers);
				String AAA=jsonArray.toString();
				ruleDao.update("TeachersList", AAA);
				return true;
			}
		}
		return false;
	}
	public boolean update(Teacher teacherTmp)throws Exception{
		List<Teacher> teachers=findAll();
		for (int i = 0; i < teachers.size(); i++) {
			if(teachers.get(i).getId().equals(teacherTmp.getId())){
				if(teacherTmp.getTeaName()!=null){
					teachers.get(i).setTeaName(teacherTmp.getTeaName());
				}
				if(teacherTmp.getService()!=null){
					teachers.get(i).setService(teacherTmp.getService());
				}
				if(teacherTmp.getFeature()!=null){
					teachers.get(i).setFeature(teacherTmp.getFeature());
				}
				if(teacherTmp.getProject()!=null){
					teachers.get(i).setProject(teacherTmp.getProject());
				}
				if(teacherTmp.getPhotoImg()!=null){
					teachers.get(i).setPhotoImg(teacherTmp.getPhotoImg());
				}
				
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(teachers);
				String AAA=jsonArray.toString();
				ruleDao.update("TeachersList", AAA);
				return true;
			}
		}
		return false;
		
	}

}
