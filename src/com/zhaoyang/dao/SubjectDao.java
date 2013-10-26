package com.zhaoyang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Subject;

public class SubjectDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(SubjectDao.class);

	public void save(Subject subject) {
		this.getHibernateTemplate().save(subject);
	}

	public List<Subject> findAll() {
		List<Subject> subjects = this.getHibernateTemplate().find("from Subject c");
		if (subjects != null && subjects.size() > 0) {
			return subjects;
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
}
