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

	public List<Notice> findAll() {
		List<Notice> newses = this.getHibernateTemplate().find("from Notice c order by c.createTime desc");
		if (newses != null && newses.size() > 0) {
			return newses;
		}
		return null;
	}

	public List<Notice> findNotices(final Integer pageNum, final String pageSize) {
		final String hql = "from Notice";
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNum - 1) * Integer.parseInt(pageSize));
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

	public Long noticeCount() {
		final Long[] counts = new Long[1];
		counts[0] = 0l;
		final String sql = "select count(*) as count from notice";
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Statement stmt = session.connection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					counts[0] = rs.getLong("count");
				}
				rs.close();
				stmt.close();
				return null;
			}
		});
		return counts[0];
	}

	public String delete(Long id) {
		Notice news = (Notice) this.getHibernateTemplate()
				.get(Notice.class, id);
		this.getHibernateTemplate().delete(news);
		return news.getTitle();
	}

	public Notice findById(Long id) {
		return (Notice) this.getHibernateTemplate().get(Notice.class, id);
	}
}
