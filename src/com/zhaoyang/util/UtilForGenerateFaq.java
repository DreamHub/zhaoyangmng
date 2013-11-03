package com.zhaoyang.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.action.AbstractActionSupport;
import com.zhaoyang.dao.DownloadDao;
import com.zhaoyang.dao.FaqDao;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.data.DownloadNotice;
import com.zhaoyang.data.Faq;
import com.zhaoyang.data.SrcDownload;
import com.zhaoyang.orm.Download;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateFaq {
	
	public void generateAllFaqs(AbstractActionSupport action) throws Exception{
		
		//生成srfaq.js
		List<Faq> faqs=DaoGenerate.getFaqDao().findAll();
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(faqs);
		String AAA=jsonArray.toString();
		File file2 = new File(action.absolutePath("/js/faq/srfaq.js"));
		BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file2)));
		bw2.write(AAA);
		bw2.flush();
		bw2.close();
	}
	public List<Faq> indexFaqList() throws Exception{
		String indexDownloadList=DaoGenerate.getRuleDao().findRuleByRuleId("IndexFaqList").getRuleDef();
		FaqDao downloadDao=DaoGenerate.getFaqDao();
		String[] ids=indexDownloadList.split(",");
		List<Faq> downloads=new ArrayList<Faq>();
		for (int i = 0; i < ids.length; i++) {
			Faq download=downloadDao.findById(Long.parseLong(ids[i]));
			if(download!=null){
				downloads.add(download);
			}
		}
		return downloads;
	}
}
