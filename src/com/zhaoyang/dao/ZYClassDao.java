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
import com.zhaoyang.orm.ZYClass;

public class ZYClassDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(ZYClassDao.class);
	
	private SubjectDao subjectDao;

	public SubjectDao getSubjectDao() {
		return subjectDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}
	
	
	

	public void save(ZYClass zyClass) {
		this.getHibernateTemplate().save(zyClass);
	}

	public List<ZYClass> findAll() {
		List<ZYClass> zyClasses = this.getHibernateTemplate().find("from ZYClass c");
		if (zyClasses != null && zyClasses.size() > 0) {
			return zyClasses;
		}
		return null;
	}

	public List<ZYClass> findZYClasses(final Integer pageNum,final String pageSize) {
		final String hql = "from ZYClass";
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
	public Long zyClassesCount(){
		final Long[] counts=new Long[1];
		counts[0]=0l;
		final String sql="select count(*) as count from ZYClass";
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
		ZYClass zyClass = (ZYClass) this.getHibernateTemplate().get(ZYClass.class, id);
		this.getHibernateTemplate().delete(zyClass);
		return zyClass.getClassName();
	}

	public ZYClass findById(Long id) {
		// TODO Auto-generated method stub
		ZYClass zyClass=(ZYClass)this.getHibernateTemplate().get(ZYClass.class, id);
		return zyClass;
	}

	public void updateZYClass(ZYClass zyClass) {
		// TODO Auto-generated method stub
		//???为什么还要找出来，直接update subject不行？
		ZYClass zyClass2=(ZYClass)getHibernateTemplate().get(ZYClass.class, zyClass.getId());
		zyClass2.setClassType(zyClass.getClassType());
		zyClass2.setClassName(zyClass.getClassName());
		zyClass2.setImgUrl(zyClass.getImgUrl());
		zyClass2.setTeacherName(zyClass.getTeacherName());
		zyClass2.setVolumn(zyClass.getVolumn());
		getHibernateTemplate().update(zyClass2);
	}
	

	//根据课程类别，查找课程
	public List<ZYClass> findClassesForType(final String classType) {
		final String hql = "from ZYClass s where s.classType=:classType";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setString("classType", classType);
				List list = query.list();
				return list;
			}
		});
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
