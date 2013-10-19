package com.zhaoyang.util;

import java.util.ArrayList;
import java.util.List;

import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForNewsDetail {
	public News findById(Long id){
		return DaoGenerate.getNewsDao().findById(id);
	}
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
}
