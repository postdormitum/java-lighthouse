package com.zdwl.service;

import java.util.List;

import com.zdwl.bo.SysMenuTbl;
import com.zdwl.vo.VoSysMenu;

public interface ISerSysMenu {
	
	public void getSysMenu() throws Exception;
	
	public void insertSysMenu(SysMenuTbl sysMenu) throws Exception ;
	
	/**
	 * 分页。查询菜单数据
	 * @param pageIndex		当前页
	 * @param pageSize		每页显示条数
	 * @param actionName	菜单名称
	 * @throws Exception
	 */
	public VoSysMenu listSysMenuForPage(int pageIndex,int pageSize,String actionName) throws Exception;
	
}
