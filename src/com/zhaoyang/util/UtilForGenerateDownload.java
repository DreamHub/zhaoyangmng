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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.action.AbstractActionSupport;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.data.DownloadNotice;
import com.zhaoyang.data.SrcDownload;
import com.zhaoyang.orm.Download;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateDownload {
	
	public void generateAllDownloads(AbstractActionSupport action) throws Exception{
		//生成notice.js
		String noticejson=DaoGenerate.getRuleDao().findRuleByRuleId("DownloadNoticeList").getRuleDef();
		File file = new File(action.absolutePath("/js/download/notice.js"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(noticejson);
		bw.flush();
		bw.close();
		//生成srdownload.js
		Rule rule = DaoGenerate.getRuleDao().findRuleByRuleId("DownloadSrcType");
		String[] ids = rule.getRuleDef().split(",");
		String[] names = rule.getRuleType().split(",");
		Map<Integer, String> downtypes = new HashMap<Integer, String>();
		for (int i = 0; i < ids.length; i++) {
			downtypes.put(Integer.parseInt(ids[i]), names[i]);
		}
		List<Download> downloads = DaoGenerate.getDownloadDao().findAll();
		List<SrcDownload> srcDownloads=new ArrayList<SrcDownload>(); 
		for (Map.Entry<Integer, String> entry: downtypes.entrySet()) {
			List<Download> tmpdownloads=new ArrayList<Download>();
			for (Download download : downloads) {
				if(download.getSrcType()==entry.getKey()){
					tmpdownloads.add(download);
				}
			}
			if(tmpdownloads.size()==0){
				continue;
			}
			SrcDownload srcDownload=new SrcDownload();
			srcDownload.setTitle(entry.getValue());
			srcDownload.setList(tmpdownloads);
			srcDownloads.add(srcDownload);
		} 
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(srcDownloads);
		String AAA=jsonArray.toString();
		File file2 = new File(action.absolutePath("/js/download/srdownload.js"));
		BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file2)));
		bw2.write(AAA);
		bw2.flush();
		bw2.close();
	}
}
