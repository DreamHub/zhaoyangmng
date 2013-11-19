package com.zhaoyang.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	
	private String classType;
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}


	private String myClassName;
	private String imgUrl;
	private String teacherName;
	private Integer volumn;
	
	private File classImg;
	private String classImgFileName;
	private String detail;
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	private List<Subject> subjects;
//	private List grade
	
	private Long subjectId;
	
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getClassImgFileName() {
		return classImgFileName;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public void setClassImgFileName(String classImgFileName) {
		this.classImgFileName = classImgFileName;
	}

	public ZYClassDao getZyClassDao() {
		return zyClassDao;
	}

	public File getClassImg() {
		return classImg;
	}

	public void setClassImg(File classImg) {
		this.classImg = classImg;
	}

	public void setZyClassDao(ZYClassDao zyClassDao) {
		this.zyClassDao = zyClassDao;
	}

	public String getMyClassName() {
		return myClassName;
	}

	public void setMyClassName(String myClassName) {
		this.myClassName = myClassName;
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
		subjects = zyClassDao.getSubjectDao().findAll();
		return SUCCESS;
	}

	public String zyClassAdd() throws Exception {
		ZYClass zyClass = new ZYClass();
		
		//???不确定能不能返回int类型
		if (gradeCode == 0) {
			setErrMsg("年级不能为空");
			return SUCCESS;
		}
		if (subjectId == null || "".equals(subjectId)) {
			setErrMsg("学科名称不能为空");
			return SUCCESS;
		}
		if (myClassName == null || "".equals(myClassName)) {
			setErrMsg("课程名称不能为空");
			return SUCCESS;
		}
		if (classImg == null) {
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
		if (detail == null || "".equals(detail)) {
			setErrMsg("详细信息不能为空");
			return SUCCESS;
		}
		grade = getGradeName(gradeCode);

		String realPath = ServletActionContext.getServletContext().getRealPath("/image/class");
		String exp=classImgFileName.substring(classImgFileName.lastIndexOf('.')+1);
		String fileName="/class_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(classImg, file);
		
		imgUrl="image/class"+fileName;
		setSucMsg("课程修改成功");
		
		//为什么还用这个？
//		forward("ZYClassEditPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());
		Subject subject = new Subject();
		subject.setId(subjectId);
		
		zyClass.setClassType(classType);
		zyClass.setClassName(myClassName);
		zyClass.setImgUrl(imgUrl);
		zyClass.setTeacherName(teacherName);
		zyClass.setVolumn(volumn);
		zyClass.setDetail(detail);
		
		zyClass.setSubject(subject);
		
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
		subjects = zyClassDao.getSubjectDao().findAll();
		ZYClass zyClass=zyClassDao.findById(id);
		if(zyClass!=null){
			setClassType(zyClass.getClassType());
			setMyClassName(zyClass.getClassName());
			setGrade(getGradeName(zyClass.getSubject().getGradeCode()));
			setGradeCode(zyClass.getSubject().getGradeCode());
			setSubjectName(zyClass.getSubject().getSubjectName());
			setImgUrl(zyClass.getImgUrl());
			setTeacherName(zyClass.getTeacherName());
			setVolumn(zyClass.getVolumn());
			setDetail(zyClass.getDetail());
		}
		return SUCCESS;
	}
	
	private String getGradeName(Integer gradeCode) {
		grade = "";
		switch(gradeCode) {
		case 1:grade = "一年级";break;
		case 2:grade = "二年级";break;
		case 3:grade = "三年级";break;
		case 4:grade = "四年级";break;
		case 5:grade = "五年级";break;
		case 6:grade = "六年级";break;
		
		case 7:grade = "七年级";break;
		case 8:grade = "八年级";break;
		case 9:grade = "九年级";break;
		case 10:grade = "中考复习专区";
		
		case 11:grade = "高一";break;
		case 12:grade = "高二";break;
		case 13:grade = "高三";break;
		case 14:grade = "高考复习专区";break;
		}
		return grade;
	}
	
	public String zyClassEdit() throws Exception {
		//???不确定能不能返回int类型
		if (myClassName == null || "".equals(myClassName)) {
			setErrMsg("课程名称不能为空");
			return ERROR;
		}
		
		if (classImg == null) {
			setErrMsg("上传图片不能为空");
			return ERROR;
		}
		if (teacherName == null || "".equals(teacherName)) {
			setErrMsg("教师姓名不能为空");
			return ERROR;
		}
		if (volumn == 0) {
			setErrMsg("请填写学期");
			return ERROR;
		}
		if (detail == null || "".equals(detail)) {
			setErrMsg("详细信息不能为空");
			return ERROR;
		}
//		grade = getGradeName(gradeCode);

		String realPath = ServletActionContext.getServletContext().getRealPath("/image/class");
		String exp=classImgFileName.substring(classImgFileName.lastIndexOf('.')+1);
		String fileName="/class_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(classImg, file);
		
		imgUrl="image/class"+fileName;
		setSucMsg("课程修改成功");
		
		//为什么还用这个？
//		forward("ZYClassEditPreAction", ServletActionContext.getRequest(),ServletActionContext.getResponse());

		ZYClass zyClass = new ZYClass();
		zyClass.setId(id);
		zyClass.setClassType(classType);
		zyClass.setClassName(myClassName);
		zyClass.setImgUrl(imgUrl);
		zyClass.setTeacherName(teacherName);
		zyClass.setVolumn(volumn);
		zyClass.setDetail(detail);
		
		Subject subject = new Subject();
		subject.setId(subjectId);
		
		zyClass.setSubject(subject);
		
		zyClassDao.updateZYClass(zyClass);
		System.out.println("修改成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		setSucMsg("修改成功，<a href='ZYClassMngAction?pageNum=1'>去看看</a>");
		return SUCCESS;
	}
	
	
	public void zyClassAddGetName() throws IOException {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		StringBuffer gradeBuf = new StringBuffer("[");
		List<Subject> subjects = zyClassDao.getSubjectDao().findByGrade(gradeCode);
		int subLength = subjects.size();
		for (int i = 0; i < (subLength-1); i++) {
			gradeBuf.append("{\"name\":\"").append(subjects.get(i).getSubjectName()).append("\",\"id\":\"").append(subjects.get(i).getId()).append("\"},");
		}
		gradeBuf.append("{\"name\":\"").append(subjects.get((subLength-1)).getSubjectName()).append("\",\"id\":\"").append(subjects.get(subLength-1).getId()).append("\"}");
		gradeBuf.append("]");
		ServletActionContext.getResponse().getWriter().write(gradeBuf.toString());
	}
	
	public void zyClassAddGetGrade() throws IOException {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		StringBuffer gradeBuf = new StringBuffer("[");
		List<Subject> subjects = zyClassDao.getSubjectDao().findByGrade(gradeCode);
		int subLength = subjects.size();
		for (int i = 0; i < (subLength-1); i++) {
			gradeBuf.append("{\"name\":\"").append(subjects.get(i).getGrade()).append("\"},");
		}
		gradeBuf.append("{\"name\":\"").append(subjects.get((subLength-1)).getGrade()).append("\"}");
		gradeBuf.append("]");
		ServletActionContext.getResponse().getWriter().write(gradeBuf.toString());
	}
	
	
	/*
	public String watchNewsHTML() throws Exception {
		newses=new UtilForNewsDetail().hotNewsList();
		return SUCCESS;
	}*/
	
}
