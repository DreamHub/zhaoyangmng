package com.zhaoyang.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.SubjectDao;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;
import com.zhaoyang.orm.Subject;

public class SubjectMngAction extends AbstractActionSupport {
	private SubjectDao subjectDao;
	private String grade;
	private Integer gradeCode;
	private String subjectName;

//	private List grade
	
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(Integer gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	private List<Subject> subjects;


	public SubjectDao getSubjectDao() {
		return subjectDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		subjects = subjectDao.findSubjects(getPageNum(), getSysConfigParameter().getPageSize());
		Long max=subjectDao.subjectsCount();
		initMaxPage(max);
		return SUCCESS;
	}
	
	public String subjectMngPre() throws Exception {
		return SUCCESS;
	}
	public String subjectAddPre() throws Exception {
		return SUCCESS;
	}

	public String subjectAdd() throws Exception {
		Subject subject = new Subject();
		
		//???不确定能不能返回int类型
		if (gradeCode == 0) {
			setErrMsg("年级不能为空");
			return SUCCESS;
		}
		if (subjectName == null || "".equals(subjectName)) {
			setErrMsg("学科名称不能为空");
			return SUCCESS;
		}

		grade = getGrade(gradeCode);		
		subject.setGrade(grade);
		subject.setGradeCode(gradeCode);
		subject.setSubjectName(subjectName);
		
		subjectDao.save(subject);
		System.out.println("新增成功，<a href='SubjectMngAction?pageNum=1'>去看看</a>");
		setSucMsg("新增成功，<a href='SubjectMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}

	private Long[] delids;
	
	public Long[] getDelids() {
		return delids;
	}

	public void setDelids(Long[] delids) {
		this.delids = delids;
	}

	public String subjectDel() throws Exception {
		setPageNum(1);
		if(delids!=null&&delids.length>0){
			int i=0;
			for (;i < delids.length; i++) {
				try{
					subjectDao.delete(delids[i]);
				}catch(Exception e){
					setErrMsg("操作失败,请确定您正常操作");
					break;
				}
			}
			if(i==delids.length){
				setSucMsg("删除成功!");
			}
			return SUCCESS;
		}
		if(id==null||id==0l){
			setErrMsg("你尚未选择要删除的记录");
		}else{
			try{
				String subjectName=subjectDao.delete(id);
				setSucMsg("删除["+subjectName+"]成功!");
			}catch(Exception e){
				setErrMsg("操作失败,请确定您正常操作");
			}
		}
		return SUCCESS;
	}
	public String subjectEditPre() throws Exception {
		Subject subject=subjectDao.findById(id);
		if(subject!=null){
			setSubjectName(subject.getSubjectName());
			setGrade(getGrade(subject.getGradeCode()));
			setGradeCode(subject.getGradeCode());
		}
		return SUCCESS;
	}
	
	private String getGrade(Integer gradeCode) {
		grade = "";
		switch(gradeCode) {
		case 1:grade = "一年级";break;
		case 2:grade = "二年级";break;
		case 3:grade = "三年级";break;
		case 4:grade = "四年级";break;
		case 5:grade = "五年级";break;
		case 6:grade = "六年级";break;
		
		case 7:grade = "初一";break;
		case 8:grade = "初二";break;
		case 9:grade = "初三";break;
		
		case 10:grade = "高一";break;
		case 11:grade = "高二";break;
		case 12:grade = "高三";break;
		}
		return grade;
	}
	
	public String subjectEdit() throws Exception {
		if(id==null){
			setErrMsg("请求参数不对");
			return ERROR;
		}
		if (gradeCode == 0) {
			setErrMsg("年级不能为空");
			return SUCCESS;
		}
		if (subjectName == null || "".equals(subjectName)) {
			setErrMsg("学科名称不能为空");
			return SUCCESS;
		}

		grade = getGrade(gradeCode);
		
		Subject subject = new Subject();
		
		subject.setId(id);
		subject.setGrade(grade);
		subject.setGradeCode(gradeCode);
		subject.setSubjectName(subjectName);

		subjectDao.updateSubject(subject);
		System.out.println("修改成功，<a href='SubjectMngAction?pageNum=1'>去看看</a>");
		setSucMsg("修改成功，<a href='SubjectMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	
	/*
	public String watchNewsHTML() throws Exception {
		newses=new UtilForNewsDetail().hotNewsList();
		return SUCCESS;
	}*/
	
}
