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

import com.zhaoyang.orm.Download;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Rule;

public class DownloadDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(DownloadDao.class);

	public List<Download> findDownloads(final Integer pageNum,final String pageSize) {
		// TODO Auto-generated method stub
		final String hql = "from Download";
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

	public Long downloadsCount() {
		// TODO Auto-generated method stub
		final Long[] counts=new Long[1];
		counts[0]=0l;
		final String sql="select count(*) as count from download";
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

	public void save(Download download) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(download);
	}
	public String delete(Long id) {
		Download download = (Download) this.getHibernateTemplate().get(Download.class, id);
		this.getHibernateTemplate().delete(download);
		return download.getSrcName();
	}

	public Download findById(Long id) {
		
		return (Download)this.getHibernateTemplate().get(Download.class, id);
	}

	public void updateDownload(Download download) {
		// TODO Auto-generated method stub
		Download dbdownload=(Download)getHibernateTemplate().get(Download.class, download.getId());
		dbdownload.setHref(download.getHref());
		dbdownload.setSrcName(download.getSrcName());
		dbdownload.setSrcType(download.getSrcType());
		dbdownload.setUplTime(download.getUplTime());
		this.getHibernateTemplate().update(dbdownload);
	}

}
