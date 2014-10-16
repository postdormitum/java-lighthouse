package com.zdwl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdwl.bo.SysMenuTbl;
import com.zdwl.bo.utils.Page;


public interface ISysMenuMapper {
	
	/**
	 * 新增
	 * @param sysMenu
	 */
	public void insertSysMenu(SysMenuTbl sysMenu);
	
	public SysMenuTbl getSysMenuByName(String actionName);
	
	public List<SysMenuTbl> getSysMenuByNameForPage(Page page);
	
	public HashMap getSysMenuByNameForHashMap(String actionName);
	
	public HashMap getSysMenuByMap(Map map);
	
	

}
