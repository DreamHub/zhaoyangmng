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
import com.zhaoyang.orm.Notice;

public class NoticeDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(NoticeDao.class);

	public void save(Notice news) {
		this.getHibernateTemplate().save(news);
	}
}
