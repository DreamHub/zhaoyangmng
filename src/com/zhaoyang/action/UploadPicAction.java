package com.zhaoyang.action;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadPicAction extends ActionSupport{

	private File filedata;
	private String filedataFileName;
	
	

	public String getFiledataFileName() {
		return filedataFileName;
	}



	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}



	public File getFiledata() {
		return filedata;
	}



	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}



	@Override
	public String execute() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw=response.getWriter();
		String realPath = ServletActionContext.getServletContext().getRealPath("/image/upload");
		String exp=filedataFileName.substring(filedataFileName.lastIndexOf('.')+1);
		String path=realPath+"/news_"+System.currentTimeMillis()+"."+exp;
		File file=new File(path);
		FileUtils.copyFile(filedata,file);
		String photoPath="/zhaoyang/image/upload/"+file.getName();
		pw.print("{\"err\":\"\",\"msg\":\""+photoPath+"\"}");
		pw.flush();
		pw.close();
		return NONE;
	}
	
}
