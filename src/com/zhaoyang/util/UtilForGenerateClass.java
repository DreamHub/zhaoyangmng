package com.zhaoyang.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Array;

import com.zhaoyang.action.AbstractActionSupport;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.SubjectDao;
import com.zhaoyang.dao.TeacherDao;
import com.zhaoyang.dao.ZYClassDao;
import com.zhaoyang.data.Student;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;
import com.zhaoyang.orm.Subject;
import com.zhaoyang.orm.ZYClass;

public class UtilForGenerateClass {
	public void generateAllClass(AbstractActionSupport action) throws Exception{
		SubjectDao subjectDao=DaoGenerate.getSubjectDao();
		ZYClassDao zyClassDao=DaoGenerate.getZyClassDao();
		//存放最后的buffer
		StringBuffer lastBuf = new StringBuffer();
		lastBuf.append("[");
		//存放小学的科目信息
		StringBuffer littleBuf = new StringBuffer();
		littleBuf.append("{\"schoolGrade\":\"小学\", \"schoolContent\":[");
		boolean litHasData = false;
		//存放初中的科目信息
		StringBuffer middleBuf = new StringBuffer();
		middleBuf.append("{\"schoolGrade\":\"初中\", \"schoolContent\":[");
		boolean midHasData = false;
		//存放高中的科目信息
		StringBuffer highBuf = new StringBuffer();
		highBuf.append("{\"schoolGrade\":\"高中\", \"schoolContent\":[");
		boolean higHasData = false;
		//各个年级的信息都存放在map里面
		Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
		for (int i = 1; i < 15; i++) {
			List<Subject> list = subjectDao.findByGrade(i);
			if(list != null && list.size() > 0) {
				if (i < 7) {
					littleBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
					litHasData = true;
				} else if (i < 10) {
					middleBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
					midHasData = true;
				} else if (i < 15) {
					highBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
					higHasData = true;
				}
			}	
		}
		
		String forLastString = "  [";
		if (litHasData) {
			String resLitBuf = littleBuf.substring(0, (littleBuf.length()-2));
			forLastString += resLitBuf + "}]},";
		}
		if (midHasData) {
			String resMidBuf = middleBuf.substring(0, (middleBuf.length()-2));
			forLastString += resMidBuf + "}]},";
		}
		if (higHasData) {
			String resHigBuf = highBuf.substring(0, (highBuf.length()-2));
			forLastString += resHigBuf + "}]},";
		}
		
		String lastString = forLastString.substring(0, (forLastString.length()-2)) + "}]";
		System.out.println(lastString);
		
		String realPath = ServletActionContext.getServletContext().getRealPath("/js/class/datasrc_class.js");
		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(lastString);
		bw.flush();
		bw.close();
		
		//生成特色课程的js，分别是小学、初中和高中
		
		//小学
		StringBuffer chaLitBuf = new StringBuffer("[");
		//初中
		StringBuffer chaMidBuf = new StringBuffer("[");
		//高中
		StringBuffer chaHigBuf = new StringBuffer("[");
		List<ZYClass> zyClasses = zyClassDao.findClassesForType("characteristic");
		if (zyClasses != null) {
			for (ZYClass zyClass : zyClasses) {
				int gradeCode = zyClass.getSubject().getGradeCode(); 
				String subjectName = zyClass.getSubject().getSubjectName();
				String volumn = zyClass.getSubject().getGrade() + (zyClass.getVolumn()==1?"上册":"下册");
				String dealDetail = zyClass.getDetail().replaceAll("\"", "\'");
				if (gradeCode > 0 && gradeCode < 7) {
					//生成小学特色课程的js内容
					chaLitBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
					append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
					append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
					append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
					append("\"},");
				} else if (gradeCode > 6 && gradeCode < 11) {
					//生成初学特色课程的js内容
					chaMidBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
					append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
					append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
					append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
					append("\"},");
				} else if (gradeCode > 10 && gradeCode < 15) {
					//生成高中特色课程的js内容				
					chaHigBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
					append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
					append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
					append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
					append("\"},");
				}
			}
		}
//		String realPath1 = ServletActionContext.getServletContext().getRealPath("/js/class/cba_class.js");
		String classDir = action.absolutePath("/js");
		if (chaLitBuf.length() >= 2) {
			String chaLitResult = null;	
			chaLitResult = chaLitBuf.substring(0, (chaLitBuf.length() - 1)) + "]";
			File chaLitFile = new File(classDir + "\\class\\chaLitClass.js");
			BufferedWriter chaLitBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaLitFile)));
			chaLitBw.write(chaLitResult);
			chaLitBw.flush();
			chaLitBw.close();
		}
		if (chaMidBuf.length() >= 2) {
			String chaMidResult = null;
			chaMidResult = chaMidBuf.substring(0, (chaMidBuf.length() - 1)) + "]";
			File chaMidFile = new File(classDir + "\\class\\chaMidClass.js");
			BufferedWriter chaMidBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaMidFile)));
			chaMidBw.write(chaMidResult);
			chaMidBw.flush();
			chaMidBw.close();
		}
		if (chaHigBuf.length() >= 2) {
			String chaHigResult = null;
			chaHigResult = chaHigBuf.substring(0, (chaHigBuf.length() - 1)) + "]";
			File chaHigFile = new File(classDir + "\\class\\chaHigClass.js");
			BufferedWriter chaHigBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaHigFile)));
			chaHigBw.write(chaHigResult);
			chaHigBw.flush();
			chaHigBw.close();
		}
		
		//同时生成存放检索条件的js
		StringBuffer conBuf = new StringBuffer("  [");
		// 生成普通课程的js   
		//格式：srclass_jsId.js
		List<Subject> subjects = subjectDao.findAllSubjects();
		if (subjects != null) {
			for (Subject subject : subjects) {
				Set<ZYClass> classes = subject.getZyClasses();
				String subjectName = subject.getSubjectName();
				StringBuffer sonDataJsBuf = new StringBuffer("[");
				
				for (ZYClass zyClass : classes) {
					String dealDetail = zyClass.getDetail().replaceAll("\"", "\'");
					String volumn = subject.getGrade() + (zyClass.getVolumn()==1?"上册":"下册");
					sonDataJsBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
					append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
					append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
					append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
					append("\"},");
				}
				String sonDataJsResult = null;
				sonDataJsResult = sonDataJsBuf.substring(0, (sonDataJsBuf.length() - 1)) + "]";
				File chaMidFile = new File(classDir + "\\class\\srclass_" + subject.getId() +".js");
				BufferedWriter sonDataJsBw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(chaMidFile)));
				sonDataJsBw.write(sonDataJsResult);
				sonDataJsBw.flush();
				sonDataJsBw.close();
				
				conBuf.append("{\"pageId\":\"").append(subject.getId()).append("\",\"condition\":\"").
				append("检索条件:").append(subject.getGrade()).append("+").append(subject.getSubjectName()).
				append("   为您找到").append(subject.getZyClasses().size()).append("门课程\"},");
			}
		}
		//[{"pageId":, "condition":},{}]
		String conString = conBuf.substring(0, (conBuf.length()-1)) + "]";
		File conFile = new File(classDir + "\\class\\search_condition.js");
		BufferedWriter conBw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(conFile)));
		conBw.write(conString);
		conBw.flush();
		conBw.close();
	}
	private StringBuffer getBuf(List<Subject> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		int listLen = (list.size() - 1);
		for (int i = 0; i < list.size(); i++) {
			Subject subject = list.get(i);
			buffer.append("{\"subject\":\"").append(subject.getSubjectName()).
			append("\",\"jsId\":\"").append(subject.getId()).append("\"}");
			if (i != listLen) {
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer;
	}
}
