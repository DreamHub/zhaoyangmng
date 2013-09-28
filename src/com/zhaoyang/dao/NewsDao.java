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

public class NewsDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(NewsDao.class);

	public void save(News news) {
		this.getHibernateTemplate().save(news);
	}

	public List<News> findAll() {
		List<News> newses = this.getHibernateTemplate().find("from News");
		if (newses != null && newses.size() > 0) {
			return newses;
		}
		return null;
	}

	public List<News> findNews(final Integer pageNum,final String pageSize) {
		final String hql = "from News";
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
	public Long newsCount(){
		final Long[] counts=new Long[1];
		counts[0]=0l;
		final String sql="select count(*) as count from zynews";
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
		News news = (News) this.getHibernateTemplate().get(News.class, id);
		this.getHibernateTemplate().delete(news);
		return news.getTitle();
	}

	public News findById(Long id) {
		// TODO Auto-generated method stub
		News news=(News)this.getHibernateTemplate().get(News.class, id);
		return news;
	}

	public void updateNews(News news) {
		// TODO Auto-generated method stub
		News dbnews=(News)getHibernateTemplate().get(News.class, news.getId());
		dbnews.setTitle(news.getTitle());
		dbnews.setContent(news.getContent());
		dbnews.setCreateTime(news.getCreateTime());
		dbnews.setNewsDesc(news.getNewsDesc());
		dbnews.setNewsKeyword(news.getNewsKeyword());
		getHibernateTemplate().update(dbnews);
	}
}
