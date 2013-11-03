package com.zhaoyang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Subject;

public class SubjectDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(SubjectDao.class);

	public void save(Subject subject) {
		this.getHibernateTemplate().save(subject);
	}
	
	//查出所有的学科信息
	public List<Subject> findAllSubjects() {
		List<Subject> subjects = this.getHibernateTemplate().find("from Subject c order by c.gradeCode");
		if (subjects != null && subjects.size() > 0) {
			return subjects;
		}
		return null;
	}

	//找出所有年级和年级code
	public List<Subject> findAll() {
		final List<Subject> subjects = new ArrayList<Subject>();
		final String sql = "select distinct(gradeCode), grade from subject";
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Statement stmt=session.connection().createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					Subject subject = new Subject();
					subject.setGrade(rs.getString("grade"));
					subject.setGradeCode(rs.getInt("gradeCode"));
					subjects.add(subject);
				}
				rs.close();
				stmt.close();
				return null;
			}
		});
		if (subjects != null && subjects.size() > 0) {
			return subjects;
		}
		return null;
	}
	
	public List<Subject> findByGrade(final Integer gradeCode) {
		final List<Subject> subjects = new ArrayList<Subject>();
		//有问题，至少挺危险！
//		final String sql = "select distinct(subjectname), id from subject where gradeCode=" + gradeCode;
		final String sql = "select grade, subjectname, id from subject where gradeCode=" + gradeCode;
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Statement stmt=session.connection().createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					Subject subject = new Subject();
					subject.setId(rs.getLong("id"));
					subject.setGrade(rs.getString("grade"));
					subject.setSubjectName(rs.getString("subjectname"));
//					subject.setGradeCode(rs.getInt("gradeCode"));
					subjects.add(subject);
				}
				rs.close();
				stmt.close();
				return null;
			}
		});
		if (subjects != null && subjects.size() > 0) {
			return subjects;
		}
		return null;
	}
	
	//根据小学、初中、高中三个条件对应的不同gradeCode进行查询
	public List<Subject> findByTwoGrade(final Integer startGrade, final Integer endGrade) {
		final List<Subject> subjects = new ArrayList<Subject>();
		//有问题，至少挺危险！
//		final String sql = "select distinct(subjectname), id from subject where gradeCode=" + gradeCode;
		final String sql = "select grade, subjectname, id from subject where gradeCode>" + startGrade + " and gradeCode<" + endGrade;
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Statement stmt=session.connection().createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					Subject subject = new Subject();
					subject.setId(rs.getLong("id"));
					subject.setGrade(rs.getString("grade"));
					subject.setSubjectName(rs.getString("subjectname"));
//					subject.setGradeCode(rs.getInt("gradeCode"));
					subjects.add(subject);
				}
				rs.close();
				stmt.close();
				return null;
			}
		});
		if (subjects != null && subjects.size() > 0) {
			return subjects;
		}
		return null;
	}
	
	public List<Subject> findSubjects2(final Integer startGrade,final Integer endGrade) {
		final String hql = "from Subject s where s.gradeCode>:startGrade and s.gradeCode<:endGrade";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setInteger("startGrade", startGrade);
				query.setInteger("endGrade", endGrade);
				List list = query.list();
				return list;
			}
		});
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public List<Subject> findSubjects(final Integer pageNum,final String pageSize) {
		final String hql = "from Subject";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNum-1)*Integer.parseInt(pageSize));
				query.setMaxResults(Integer.parseInt(pageSize));
				List list = query.list();
				return list;
			}
		});
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	public Long subjectsCount(){
		final Long[] counts=new Long[1];
		counts[0]=0l;
		final String sql="select count(*) as count from Subject";
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Statement stmt=session.connection().createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()){
					counts[0]=rs.getLong("count");
				}
				rs.close();
				stmt.close();
				return null;
			}
		});
		return counts[0];
	}
	public String delete(Long id) {
		Subject subject = (Subject) this.getHibernateTemplate().get(Subject.class, id);
		this.getHibernateTemplate().delete(subject);
		return subject.getSubjectName();
	}

	public Subject findById(Long id) {
		// TODO Auto-generated method stub
		Subject subject=(Subject)this.getHibernateTemplate().get(Subject.class, id);
		return subject;
	}

	public void updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		//???为什么还要找出来，直接update subject不行？
		Subject subject2=(Subject)getHibernateTemplate().get(Subject.class, subject.getId());
		subject2.setGrade(subject.getGrade());
		subject2.setGradeCode(subject.getGradeCode());
		subject2.setSubjectName(subject.getSubjectName());
		getHibernateTemplate().update(subject2);
	}
	
	//执行增加操作前，根据“年级”和“学科名称”判断数据库中是否有该组数据，
	//若有，就不执行增加操作，返回true
	public boolean findByGradeAndSubName(final String grade, final String subjectName) {
		
		boolean result = false;
		final String hql = "from Subject s where s.grade=:grade and s.subjectName=:subjectName";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setString("grade", grade);
				query.setString("subjectName", subjectName);
				List list = query.list();
				return list;
			}
		});
		if (list != null && list.size() > 0) {
			result = true;
		}
		return result;
	}
	
	//执行更新操作前，根据id、“年级”和“学科名称”判断数据库中是否有该组数据，
	//若有，就不执行增加操作，返回true
	public boolean findByIdAndGradeAndSubName(final Long id, final String grade, final String subjectName) {
		
		boolean result = false;
		final String hql = "from Subject s where s.id!=:id and s.grade=:grade and s.subjectName=:subjectName";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setLong("id", id);
				query.setString("grade", grade);
				query.setString("subjectName", subjectName);
				List list = query.list();
				return list;
			}
		});
		if (list != null && list.size() > 0) {
			result = true;
		}
		return result;
	}
}
