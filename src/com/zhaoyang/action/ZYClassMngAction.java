package com.zhaoyang.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.SubjectDao;
import com.zhaoyang.dao.ZYClassDao;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;
import com.zhaoyang.orm.Subject;
import com.zhaoyang.orm.ZYClass;

public class ZYClassMngAction extends AbstractActionSupport {
	private ZYClassDao zyClassDao;
	private String grade;
	private Integer gradeCode;
	private String subjectName;
	
	private String className;
	private String imgUrl;
	private String teacherName;
	private Integer volumn;
	
//	private List grade
	
	
	public ZYClassDao getZyClassDao() {
		return zyClassDao;
	}

	public void setZyClassDao(ZYClassDao zyClassDao) {
		this.zyClassDao = zyClassDao;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getVolumn() {
		return volumn;
	}

	public void setVolumn(Integer volumn) {
		this.volumn = volumn;
	}

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


	private List<ZYClass> zyClasses;


	public List<ZYClass> getZyClasses() {
		return zyClasses;
	}

	public void setZyClasses(List<ZYClass> zyClasses) {
		this.zyClasses = zyClasses;
	}

	@Override
	public String execute() throws Exception {
		zyClasses = zyClassDao.findZYClasses(getPageNum(), getSysConfigParameter().getPageSize());
		Long max=zyClassDao.zyClassesCount();
		initMaxPage(max);
		return SUCCESS;
	}
	
	public String zyClassMngPre() throws Exception {
		return SUCCESS;
	}
	public String zyClassAddPre() throws Exception {
		return SUCCESS;
	}

	public String zyClassAdd() throws Exception {
		ZYClass zyClass = new ZYClass();
		
		//???不确定能不能返回int类型
		if (gradeCode == 0) {
			setErrMsg("年级不能为空");
			return SUCCESS;
		}
		if (subjectName == null || "".equals(subjectName)) {
			setErrMsg("学科名称不能为空");
			return SUCCESS;
		}
		if (imgUrl == null || "".equals(imgUrl)) {
			setErrMsg("上传图片不能为空");
			return SUCCESS;
		}
		if (teacherName == null || "".equals(teacherName)) {
			setErrMsg("教师姓名不能为空");
			return SUCCESS;
		}
		if (volumn == 0) {
			setErrMsg("请填写学期");
			return SUCCESS;
		}
		grade = getGrade(gradeCode);		
		zyClass.setClassName(className);
		zyClass.setImgUrl(imgUrl);
		zyClass.setTeacherName(teacherName);
		zyClass.setVolumn(volumn);
		
		zyClassDao.save(zyClass);
		System.out.println("新增成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		setSucMsg("新增成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}

	private Long[] delids;
	
	public Long[] getDelids() {
		return delids;
	}

	public void setDelids(Long[] delids) {
		this.delids = delids;
	}

	public String zyClassDel() throws Exception {
		setPageNum(1);
		if(delids!=null&&delids.length>0){
			int i=0;
			for (;i < delids.length; i++) {
				try{
					zyClassDao.delete(delids[i]);
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
				String subjectName=zyClassDao.delete(id);
				setSucMsg("删除["+subjectName+"]成功!");
			}catch(Exception e){
				setErrMsg("操作失败,请确定您正常操作");
			}
		}
		return SUCCESS;
	}
	public String zyClassEditPre() throws Exception {
		ZYClass zyClass=zyClassDao.findById(id);
		if(zyClass!=null){
			setClassName(zyClass.getClassName());
			setGrade(getGrade(zyClass.getSubject().getGradeCode()));
			setGradeCode(zyClass.getSubject().getGradeCode());
			setImgUrl(zyClass.getImgUrl());
			setTeacherName(zyClass.getTeacherName());
			setVolumn(zyClass.getVolumn());
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
		//???不确定能不能返回int类型
		if (gradeCode == 0) {
			setErrMsg("年级不能为空");
			return SUCCESS;
		}
		if (subjectName == null || "".equals(subjectName)) {
			setErrMsg("学科名称不能为空");
			return SUCCESS;
		}
		if (imgUrl == null || "".equals(imgUrl)) {
			setErrMsg("上传图片不能为空");
			return SUCCESS;
		}
		if (teacherName == null || "".equals(teacherName)) {
			setErrMsg("教师姓名不能为空");
			return SUCCESS;
		}
		if (volumn == 0) {
			setErrMsg("请填写学期");
			return SUCCESS;
		}
		grade = getGrade(gradeCode);
		ZYClass zyClass = new ZYClass();
		zyClass.setClassName(className);
		zyClass.setImgUrl(imgUrl);
		zyClass.setTeacherName(teacherName);
		zyClass.setVolumn(volumn);

		zyClassDao.updateZYClass(zyClass);
		System.out.println("修改成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		setSucMsg("修改成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	
	/*
	public String watchNewsHTML() throws Exception {
		newses=new UtilForNewsDetail().hotNewsList();
		return SUCCESS;
	}*/
	
}
