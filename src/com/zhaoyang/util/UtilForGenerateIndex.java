package com.zhaoyang.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaoyang.dao.IndexImgLoopDao;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.data.IndexImgLoop;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateIndex {
	public List<IndexImgLoop> indexImgLoopList() throws Exception{
		IndexImgLoopDao indexImgLoopDao=DaoGenerate.getIndexImgLoopDao();
		return indexImgLoopDao.findAll();
	}
	public String[] sevenReasonList() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		String ruledef=ruleDao.findRuleByRuleId("SevenReason").getRuleDef();
		return ruledef.split(";");
	}
	public String indexAdvImg1() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("IndexAdvImg1").getRuleDef();
	}
	public String indexAdvImg2() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("IndexAdvImg2").getRuleDef();
	}
	public String headBgImg() throws Exception{
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		return ruleDao.findRuleByRuleId("HeadBgImg").getRuleDef();
	}
}
