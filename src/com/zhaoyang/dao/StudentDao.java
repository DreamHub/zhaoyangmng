package com.zhaoyang.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhaoyang.data.Student;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class StudentDao {
	private RuleDao ruleDao;
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public List<Student> findAll() throws Exception{
		Rule rule =ruleDao.findRuleByRuleId("StudentsList");
		JSONArray array=new JSONArray(rule.getRuleDef());
		List<Student> students=new ArrayList<Student>();
		for(int i=0;i<array.length();i++){
			JSONObject jobj=(JSONObject)array.get(i);
			Student student=new Student();
			student.setId(jobj.getLong("id"));
			student.setStuName(jobj.getString("stuName"));
			student.setFromSchool(jobj.getString("fromSchool"));
			student.setToSchool(jobj.getString("toSchool"));
			student.setScore(jobj.getInt("score"));
			student.setDesc(jobj.getString("desc"));
			student.setImgPath(jobj.getString("imgPath"));
			students.add(student);
		}
		return students;
	}
	public void save(Student studentTmp) throws Exception{
		List<Student> students=findAll();
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
		ruleDao.update("StudentsList", AAA);
	}
	public boolean delete(Long id)throws Exception{
		List<Student> students=findAll();
		for (Student student : students) {
			if(student.getId().equals(id)){
				students.remove(student);
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(students);
				String AAA=jsonArray.toString();
				ruleDao.update("StudentsList", AAA);
				return true;
			}
		}
		return false;
	}
	public boolean update(Student studentTmp)throws Exception{
		List<Student> students=findAll();
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).getId().equals(studentTmp.getId())){
				if(studentTmp.getStuName()!=null){
					students.get(i).setStuName(studentTmp.getStuName());
				}
				if(studentTmp.getFromSchool()!=null){
					students.get(i).setFromSchool(studentTmp.getFromSchool());
				}
				if(studentTmp.getToSchool()!=null){
					students.get(i).setToSchool(studentTmp.getToSchool());
				}
				if(studentTmp.getDesc()!=null){
					students.get(i).setToSchool(studentTmp.getToSchool());
				}
				if(studentTmp.getImgPath()!=null){
					students.get(i).setImgPath(studentTmp.getImgPath());
				}
				if(studentTmp.getScore()!=null){
					students.get(i).setScore(studentTmp.getScore());
				}
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(students);
				String AAA=jsonArray.toString();
				ruleDao.update("StudentsList", AAA);
				return true;
			}
		}
		return false;
		
	}

	public Student findById(Long id) throws Exception{
		List<Student> students=findAll();
		for (Student student : students) {
			if(student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}

}
