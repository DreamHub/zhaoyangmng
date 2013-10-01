package com.zhaoyang.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhaoyang.config.SysConfigParameter;

public abstract class AbstractActionSupport extends ActionSupport{
	private SysConfigParameter sysConfigParameter;
	private Integer maxPage;
	private Integer pageNum;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
		
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public SysConfigParameter getSysConfigParameter() {
		return sysConfigParameter;
	}
	public void setSysConfigParameter(SysConfigParameter sysConfigParameter) {
		this.sysConfigParameter = sysConfigParameter;
	}
	public void setErrMsg(String errMsg){
		ServletActionContext.getRequest().setAttribute("errMsg", errMsg);
	}
	public void setSucMsg(String sucMsg){
		ServletActionContext.getRequest().setAttribute("sucMsg", sucMsg);
	}
	public void redirect(String toPath) throws IOException{
		ServletActionContext.getResponse().sendRedirect(toPath);
	}
	public void forward(String toPath,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		ServletActionContext.getRequest().getRequestDispatcher(toPath).forward(request, response);
	}
	public void forward(String toPath) throws IOException, ServletException{
		ServletActionContext.getRequest().getRequestDispatcher(toPath).forward(ServletActionContext.getRequest(), ServletActionContext.getResponse());
	}
	public void initMaxPage(Long maxCount){
		Long pageSize=Long.parseLong(getSysConfigParameter().getPageSize());
		if(maxCount%pageSize==0){
			setMaxPage((new Long(maxCount/pageSize)).intValue());
		}else{
			setMaxPage((new Long(maxCount/pageSize)).intValue()+1);
		}
	}
}
