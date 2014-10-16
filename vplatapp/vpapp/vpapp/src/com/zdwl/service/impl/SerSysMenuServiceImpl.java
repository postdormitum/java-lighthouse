package com.zdwl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdwl.bo.SysMenuTbl;
import com.zdwl.bo.utils.Page;
import com.zdwl.dao.ISysMenuMapper;
import com.zdwl.service.ISerSysMenu;
import com.zdwl.vo.VoSysMenu;

@Service
@Transactional(rollbackFor = Exception.class)
public class SerSysMenuServiceImpl implements ISerSysMenu {
	
	private static Logger logger = Logger.getLogger(SerSysMenuServiceImpl.class);

	@Resource
	private ISysMenuMapper iSysMenuMapper;

	public void getSysMenu() throws Exception {
		logger.info("come on in++++++++++++++++++++++++++");
		SysMenuTbl obj = iSysMenuMapper.getSysMenuByName("菜单1");
		System.out.println(obj.getActionName());
		
		Page<SysMenuTbl> page = new Page<SysMenuTbl>();
		page.setPageNo(2);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("actionName", "菜单");
		page.setParams(params);
		List<SysMenuTbl> lists = iSysMenuMapper.getSysMenuByNameForPage(page);
		System.out.println(lists.size());
	}
	
	public VoSysMenu listSysMenuForPage(int pageIndex,int pageSize,String actionName) throws Exception{
		Page<SysMenuTbl> page = new Page<SysMenuTbl>();
		page.setPageNo(pageIndex);
		page.setPageSize(pageSize);
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		if(actionName != null){
			params.put("actionName", actionName);
		}
		page.setParams(params);
		//查询
		List<SysMenuTbl> lists = iSysMenuMapper.getSysMenuByNameForPage(page);
		VoSysMenu voSysMenu = new VoSysMenu();
		voSysMenu.setLists(lists);
		voSysMenu.setPageObj(page);
		return voSysMenu;
	}
	
	
	public void insertSysMenu(SysMenuTbl sysMenu) throws Exception {
//		SysMenuTbl insertObj = new SysMenuTbl();
//		insertObj.setActionName("菜单"+new Date());
//		insertObj.setActionType("1");
//		insertObj.setDoAction("#########");
		sysMenu.setIsDel(0);
		sysMenu.setOpTime(new Date());
		iSysMenuMapper.insertSysMenu(sysMenu);
		System.out.println(sysMenu.toString());
		getSysMenu();
	}



}
