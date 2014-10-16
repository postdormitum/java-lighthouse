package com.zdwl.bo;

import java.io.Serializable;
import java.util.Date;

import com.zdwl.bo.utils.Page;

public class SysMenuTbl implements Serializable{
	
	private static final long serialVersionUID = 233559895466038160L;  
	private int id;
	private String actionName;
	private String actionType;
	private String doAction;
	private int opMan;
	private Date opTime;
	private int isDel;
	private Page page;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getDoAction() {
		return doAction;
	}
	public void setDoAction(String doAction) {
		this.doAction = doAction;
	}
	public int getOpMan() {
		return opMan;
	}
	public void setOpMan(int opMan) {
		this.opMan = opMan;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
