package com.zhaoyang.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends AbstractActionSupport{
	private String userName;
	private String userPwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String execute() throws Exception {
		msg="0";
		if(userName==null||"".equals(userName)||userPwd==null||"".equals(userPwd)){
			setErrMsg("用户名或密码不能为空");
			msg="用户名或密码不能为空";
			return ERROR;
		}
		if(!(userName.equals("admin")&&userPwd.equals("123"))){
			setErrMsg("用户名或密码不正确");
			msg="用户名或密码不正确";
			return ERROR;
		}
		ServletActionContext.getRequest().getSession().setAttribute("userInfo", "admin");
		msg="1";
		return SUCCESS;
	}
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String logout() throws Exception{
		ServletActionContext.getRequest().getSession().removeAttribute("userInfo");
		return SUCCESS;
	}
	

}
