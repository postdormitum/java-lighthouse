package com.zdwl.webc;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;
import com.zdwl.service.ISerSysMenu;

@ParentPackage("struts-default")
@Namespace("/sys")
@Controller()
@Scope("prototype")
@Action("login")
@SuppressWarnings({"unchecked","unused"})
public class LoginAction extends ActionSupport implements ServletResponseAware,
						ServletRequestAware, SessionAware {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	private String username;
	private String password;
	@Resource
	private ISerSysMenu serSysMenu;
	
	public String doLogin(){
		System.out.println("hi-----------------------------------------------------");
//		SysMenuApp app = new SysMenuApp();
//		try {
//			app.test();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
//			serSysMenu.getSysMenu();
			serSysMenu.insertSysMenu(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (username.equals("struts2")) {
			return "loginSuccess";
		} else {
			return "loginFailure";
		}
	}
	
	public String menuLists(){
		System.out.println("加载菜单");
		return null;
	}
	
	public LoginAction(){
		super();
		System.out.println("iniaaaaa LoginAction ........");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public ISerSysMenu getSerSysMenu() {
		return serSysMenu;
	}

	public void setSerSysMenu(ISerSysMenu serSysMenu) {
		this.serSysMenu = serSysMenu;
	}
}

