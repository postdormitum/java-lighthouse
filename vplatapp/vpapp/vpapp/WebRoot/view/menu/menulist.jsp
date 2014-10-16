<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>菜单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="js/jquery/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="js/jquery/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="js/jquery/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
	<script src="js/jquery/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
	<script type="text/javascript" src="<%=path %>/js/busi/menu.js?pid=1"></script>    
  </head>
  
	<body style="padding:6px; overflow:hidden;">
		<div id="searchbar">
		    模块名称：<input id="actionName" type="text" />
		    <input id="btnOK" type="button" value="查询" onclick="f_search()" />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    <input id="btnInser" type="button" value="添加" onclick = "editMenu('add')" />
		</div>
		
		<div id="maingrid" style="margin:0; padding:0"></div>
		<div id="pager2" />
		<div style="display:none;">
		  <!-- g data total ttt -->
		</div>
	 
	</body>
</html>
