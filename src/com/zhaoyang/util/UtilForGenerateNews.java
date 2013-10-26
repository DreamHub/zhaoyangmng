package com.zhaoyang.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateNews {
	public News findById(Long id){
		return DaoGenerate.getNewsDao().findById(id);
	}
	/**
	 * 公告栏
	 * @return
	 */
	public List<Notice> gonggaolan(){
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		NoticeDao noticeDao=DaoGenerate.getNoticeDao();
		Rule rule=ruleDao.findRuleByRuleId("NoticePanelList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<Notice> notices=new ArrayList<Notice>();
		for (int i = 0; i < ids.length; i++) {
			Notice notice=noticeDao.findById(Long.parseLong(ids[i]));
			notices.add(notice);
		}
		return notices;
	}
	/**
	 * 热门新闻
	 * @return
	 */
	public List<News> hotNewsList(){
		NewsDao newsDao=DaoGenerate.getNewsDao();
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("HotNewsList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<News> newses=new ArrayList<News>();
		for (int i = 0; i < ids.length; i++) {
			News news=newsDao.findById(Long.parseLong(ids[i]));
			newses.add(news);
		}
		return newses;
	}
	/**
	 * 热门新闻背景图
	 * @return
	 */
	public String lableBgImg(){
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("HotNewsBgImg");
		return rule.getRuleDef();
	}
	/**
	 * 首页新闻展示
	 * @return
	 */
	public List<News> indexNewsList(){
		NewsDao newsDao=DaoGenerate.getNewsDao();
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("IndexNewsList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<News> newses=new ArrayList<News>();
		for (int i = 0; i < ids.length; i++) {
			News news=newsDao.findById(Long.parseLong(ids[i]));
			newses.add(news);
		}
		return newses;
	}
	
}
