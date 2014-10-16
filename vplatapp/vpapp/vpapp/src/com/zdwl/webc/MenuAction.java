package com.zdwl.webc;

import java.io.IOException;
import java.util.List;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zdwl.bo.SysMenuTbl;
import com.zdwl.global.AbsAction;
import com.zdwl.service.ISerSysMenu;
import com.zdwl.utils.DateUtility;
import com.zdwl.vo.VoSysMenu;

@ParentPackage("struts-default")
@Namespace("/sys")
@Controller()
@Scope("prototype")
@Action("menuAction")
@SuppressWarnings( { "unchecked", "unused" })
public class MenuAction extends AbsAction {
	private static final Logger logger = Logger.getLogger(MenuAction.class);
	private int page;
	private int pageSize;
	@Resource
	private ISerSysMenu serSysMenu;
	
	//测试前段 提交的 数据
	private String Name;
	private SysMenuTbl sysMenu;
	private VoSysMenu voSysMenu;
	//测试前段 提交的 数据------------------------
	
	/**
	 * 读取menu
	 * @return
	 */
	public String menuLists(){
		String actionName = this.request.getParameter("actionName2");
		VoSysMenu voSysMenu = null;
		try {
			voSysMenu = this.serSysMenu.listSysMenuForPage(page, pageSize, actionName);
			System.out.println(voSysMenu.getLists().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray=new JSONArray();
		try {
			for(SysMenuTbl menu:voSysMenu.getLists()){
				JSONObject jCell=new JSONObject();
				jCell.put("id", menu.getId());
				jCell.put("actionName", menu.getActionName());
				jCell.put("actionType", menu.getActionType());
				jCell.put("opTime", menu.getOpTime() == null?"":DateUtility.fullFormater(menu.getOpTime()));
				jCell.put("isDel", menu.getIsDel());
				jCell.put("id_2", menu.getId());
				
				JSONObject j=new JSONObject();
				j.put("pglId", menu.getId());
				j.put("cell",jCell.toString());
				jsonArray.add(jCell.toString());									
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject jj=new JSONObject();
		jj.put("total", voSysMenu.getPageObj().getTotalPage());
		jj.put("page", page);
		jj.put("Total", voSysMenu.getPageObj().getTotalRecord());
		jj.put("Rows", jsonArray.toArray());
		
		try {
			this.response.setCharacterEncoding("UTF-8");
			this.response.getWriter().print(jj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String save(){
		try {
			this.serSysMenu.insertSysMenu(voSysMenu.getItem());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject jo =new JSONObject();
		jo.put("first", "hi");
		jo.put("second", "我是");
		try {
			this.response.setCharacterEncoding("UTF-8");
			this.response.getWriter().print(jo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pageSize;
	}

	public void setPagesize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public SysMenuTbl getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenuTbl sysMenu) {
		this.sysMenu = sysMenu;
	}

	public VoSysMenu getVoSysMenu() {
		return voSysMenu;
	}

	public void setVoSysMenu(VoSysMenu voSysMenu) {
		this.voSysMenu = voSysMenu;
	}

}
