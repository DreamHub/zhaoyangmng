package com.zhaoyang.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaoyang.dao.IndexImgLoopDao;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.TailLinkDao;
import com.zhaoyang.data.IndexImgLoop;
import com.zhaoyang.data.TailLink;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateOther {
	
	public List<TailLink> tailLinks() throws Exception{
		TailLinkDao tailLinkDao=DaoGenerate.getTailLinkDao();
		return tailLinkDao.findAll();
	}
	public String websiteStatement() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("WebsiteStatement").getRuleDef();
	}
	public String tailDescFirst() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("TailDescFirst").getRuleDef();
	}
	public String tailDescSecond() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("TailDescSecond").getRuleDef();
	}
	public String tailDescThird() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("TailDescThird").getRuleDef();
	}
}
