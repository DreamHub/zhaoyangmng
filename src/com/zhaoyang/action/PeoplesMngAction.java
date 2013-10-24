package com.zhaoyang.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.TeacherDao;
import com.zhaoyang.data.Student;
import com.zhaoyang.data.Teacher;

public class PeoplesMngAction extends AbstractActionSupport {
	public String peoplesMngPre() throws Exception {

		return SUCCESS;
	}
	private TeacherDao teacherDao;
	private StudentDao studentDao;
	
	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	private List<Teacher> teachers;
	private List<Student> students;
	

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String teacherMng() throws Exception {
		teachers=teacherDao.findAll();
		return SUCCESS;
	}

	public String studentMng() throws Exception {
		students=studentDao.findAll();
		return SUCCESS;
	}
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String teacherDel() throws Exception {
		boolean result=teacherDao.delete(id);
		if(result==false){
			setErrMsg("操作失败,请确定您正常操作");
		}else{
			setSucMsg("删除成功");
		}
		return SUCCESS;
	}
	private Teacher teacher;
	private File imgPhoto;
	private String imgPhotoFileName;
	
	
	public File getImgPhoto() {
		return imgPhoto;
	}

	public void setImgPhoto(File imgPhoto) {
		this.imgPhoto = imgPhoto;
	}

	public String getImgPhotoFileName() {
		return imgPhotoFileName;
	}

	public void setImgPhotoFileName(String imgPhotoFileName) {
		this.imgPhotoFileName = imgPhotoFileName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String teacherAddPre() throws Exception {
		return SUCCESS;
	}
	public String teacherAdd() throws Exception {
		if(imgPhoto==null){
			setErrMsg("上传图片不能为空");
			return SUCCESS;
		}
		if(teacher.getTeaName()==null||"".equals(teacher.getTeaName())){
			setErrMsg("姓名不能为空");
			return SUCCESS;
		}
		if(teacher.getService()==null||"".equals(teacher.getService())){
			setErrMsg("适合对象不能为空");
			return SUCCESS;
		}
		if(teacher.getProject()==null||"".equals(teacher.getProject())){
			setErrMsg("科目不能为空");
			return SUCCESS;
		}
		if(teacher.getFeature()==null||"".equals(teacher.getFeature())){
			setErrMsg("教学特点不能为空");
			return SUCCESS;
		}
		String realPath = ServletActionContext.getServletContext().getRealPath("/image/teacher");
		String exp=imgPhotoFileName.substring(imgPhotoFileName.lastIndexOf('.')+1);
		String fileName="/news_"+System.currentTimeMillis()+"."+exp;
		String newPath=realPath+fileName;
		File file=new File(newPath);
		FileUtils.copyFile(imgPhoto, file);
		teacher.setPhotoImg("image/teacher"+fileName);
		teacherDao.save(teacher);
		setSucMsg("新增成功,<a href='TeacherMngAction'>去看看</a>");
		return SUCCESS;
	}
	
	public String teacherEditPre() throws Exception {
		teacher=teacherDao.findById(id);
		return SUCCESS;
	}
	
	public String teacherEdit() throws Exception {
		if(teacher.getId()==null||"".equals(teacher.getId())){
			setErrMsg("参数不合法");
			return SUCCESS;
		}
		setId(teacher.getId());
		if(teacher.getTeaName()==null||"".equals(teacher.getTeaName())){
			setErrMsg("姓名不能为空");
			return SUCCESS;
		}
		if(teacher.getService()==null||"".equals(teacher.getService())){
			setErrMsg("适合对象不能为空");
			return SUCCESS;
		}
		if(teacher.getProject()==null||"".equals(teacher.getProject())){
			setErrMsg("科目不能为空");
			return SUCCESS;
		}
		if(teacher.getFeature()==null||"".equals(teacher.getFeature())){
			setErrMsg("教学特点不能为空");
			return SUCCESS;
		}
		if(imgPhoto!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/image/teacher");
			String exp=imgPhotoFileName.substring(imgPhotoFileName.lastIndexOf('.')+1);
			String fileName="/news_"+System.currentTimeMillis()+"."+exp;
			String newPath=realPath+fileName;
			File file=new File(newPath);
			FileUtils.copyFile(imgPhoto, file);
			teacher.setPhotoImg("image/teacher"+fileName);
		}
		teacherDao.update(teacher);
		setSucMsg("修改成功,<a href='TeacherMngAction'>去看看</a>");
		return SUCCESS;
	}

}
