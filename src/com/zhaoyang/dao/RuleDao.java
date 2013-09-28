package com.zhaoyang.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zhaoyang.orm.Rule;

public class RuleDao extends HibernateDaoSupport {
	private static Logger logger = Logger.getLogger(RuleDao.class);
	public Rule findRuleByRuleId(String ruleId){
		String hql="from Rule r where r.ruleId = '"+ruleId+"'";
		List list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return (Rule)list.get(0);
		}
		return null;
	}
	public void update(String ruleid, String ruledef) {
		String hql="from Rule r where r.ruleId = '"+ruleid+"'";
		List list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			Rule rule=(Rule)list.get(0);
			rule.setRuleDef(ruledef);
			this.getHibernateTemplate().update(rule);
		}
	}
	public void update(String ruleid, String ruledef, String ruletype) {
		// TODO Auto-generated method stub
		String hql="from Rule r where r.ruleId = '"+ruleid+"'";
		List list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			Rule rule=(Rule)list.get(0);
			rule.setRuleDef(ruledef);
			rule.setRuleType(ruletype);
			this.getHibernateTemplate().update(rule);
		}
	}
	public void updateRuleType(String ruleid, String ruletype) {
		// TODO Auto-generated method stub
		String hql="from Rule r where r.ruleId = '"+ruleid+"'";
		List list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			Rule rule=(Rule)list.get(0);
			rule.setRuleType(ruletype);
			this.getHibernateTemplate().update(rule);
		}
	}

}
