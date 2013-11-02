package com.zhaoyang.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class BackgroundAdminAuthorityInterceptor implements Interceptor {
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation actioninvocation) throws Exception {
		String result = null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userInfo = (String) session.getAttribute("userInfo");
		if (userInfo == null || !"admin".equals(userInfo)) {
			return "logout";
		} else {
			try {
				result = actioninvocation.invoke();
				return result;
			} catch (Exception e) {
				//System.out.println(e.);
				e.printStackTrace();
				return "error";
			}
		}
	}
}
