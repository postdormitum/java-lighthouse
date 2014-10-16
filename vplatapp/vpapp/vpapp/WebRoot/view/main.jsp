<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	
	<link href="js/jquery/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <script src="js/jquery/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="js/jquery/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
    <script src="js/jquery/ligerUI/js/plugins/ligerTab.js"></script>
    <script src="js/jquery/jquery.cookie.js"></script>
    <script src="js/jquery/json2.js"></script>
    <script src="js/jquery/indexdata.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
    /* 顶部 */ 
    .l-topmenu{ margin:0; padding:0; height:31px; line-height:31px; background:url('js/jquery/images/top.jpg') repeat-x bottom;  position:relative; border-top:1px solid #1D438B;  }
    .l-topmenu-logo{ color:#E7E7E7; padding-left:35px; line-height:26px;background:url('js/jquery/images/topicon.gif') no-repeat 10px 5px;}
    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;  right:30px; top:2px;color:#070A0C;}
    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:underline} 
     .body-gray2014 #framecenter{
        margin-top:3px;
    }
      .viewsourcelink {
         background:#B3D9F7;  display:block; position:absolute; right:10px; top:3px; padding:6px 4px; color:#333; text-decoration:underline;
    }
    .viewsourcelink-over {
        background:#81C0F2;
    }
    .l-topmenu-welcome label {color:white;
    }
    #skinSelect {
        margin-right: 6px;
    }
 </style>

  </head>
  
  <body style="padding:0px;background:#EAEEF5;">
    <div id="pageloading"></div>  
	<div id="topmenu" class="l-topmenu">
	    <div class="l-topmenu-logo">jQuery ligerUI Demos导航主页</div>
	    <div class="l-topmenu-welcome">
	        <label> 皮肤切换：</label>
	        <select id="skinSelect">
	            <option value="aqua">默认</option> 
	            <option value="silvery">Silvery</option>
	            <option value="gray">Gray</option>
	            <option value="gray2014">Gray2014</option>
	        </select>
	        <a href="index.aspx" class="l-link2">服务器版本</a>
	        <span class="space">|</span>
	        <a href="https://me.alipay.com/daomi" class="l-link2" target="_blank">捐赠</a> 
	        <span class="space">|</span>
	         <a href="http://bbs.ligerui.com" class="l-link2" target="_blank">论坛</a>
	    </div> 
	</div>
	  <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
	        <div position="left"  title="主要菜单" id="accordion1"> 
	                     <div title="功能列表" class="l-scroll">
	                         <ul id="tree1" style="margin-top:3px;">
	                    </div>
	                    <div title="应用场景">
	                    <div style=" height:7px;"></div>
	                         <a class="l-link" href="javascript:f_addTab('listpage','列表页面','demos/case/listpage.htm')">列表页面</a> 
	                        <a class="l-link" href="javascript:f_addTab('listpage','列表页面','demos/case/listpage2.htm')">列表页面2</a> 
	                         <a class="l-link" href="demos/dialog/win7.htm" target="_blank">模拟Window桌面</a> 
	                        <a class="l-link" href="javascript:f_addTab('week','工作日志','demos/case/week.htm')">工作日志</a> 
	                    </div>    
	                     <div title="实验室">
	                    <div style=" height:7px;"></div>
	                          <a class="l-link" href="lab/generate/index.htm" target="_blank">表格表单设计器</a> 
	                          <a class="l-link" href="lab/formdesign/index.htm" target="_blank">可视化表单设计</a> 
	                    </div> 
	        </div>
	        <div position="center" id="framecenter"> 
	            <div tabid="home" title="我的主页" style="height:300px" >
	                <iframe frameborder="0" name="home" id="home" src="welcome.htm"></iframe>
	            </div> 
	        </div> 
	        
	    </div>
	    <div  style="height:32px; line-height:32px; text-align:center;">
	            Copyright © 2011-2014 www.ligerui.com
	    </div>
    	<div style="display:none"></div>
  </body>
</html>
