package com.zdwl.vo;

import java.util.List;
import java.util.Map;

import com.zdwl.bo.SysMenuTbl;
import com.zdwl.bo.utils.Page;

public class VoSysMenu {
	
	private SysMenuTbl item;
	private List<SysMenuTbl> lists;
	private Page pageObj;
	private Map paramMap;
	
	
	public SysMenuTbl getItem() {
		return item;
	}
	public void setItem(SysMenuTbl item) {
		this.item = item;
	}
	public List<SysMenuTbl> getLists() {
		return lists;
	}
	public void setLists(List<SysMenuTbl> lists) {
		this.lists = lists;
	}
	public Page getPageObj() {
		return pageObj;
	}
	public void setPageObj(Page pageObj) {
		this.pageObj = pageObj;
	}

}
