<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String actionType = request.getParameter("actionType");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menuEdit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="js/jquery/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="js/jquery/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="js/jquery/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
     <script src="js/jquery/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="js/jquery/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="js/jquery/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="js/jquery/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="js/jquery/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="js/jquery/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=path %>/js/busi/menuEdit.js?pid=9" type="text/javascript"></script>  
	
	 <script type="text/javascript">
        
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
	
	<script type="text/javascript">
		var basePath = '<%=path %>';
	</script>
	
  </head>
  
  <body>
  	<div style="width:100%;height:100%;border:1px solid red;text-align:center;padding-top:20px;">
	    <form name="form1" method="post" action="#" id="form1">
			<div></div>
	        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
	            <tr>
	                <td align="right" class="l-table-edit-td">
	                	菜单/功能名称:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                	<input name="txtActionName" type="text" id="txtName" ltype="text" 
	                		validate="{required:true,minlength:3,maxlength:10}" />
	                </td>
	                <td align="left"></td>
	            </tr>
	            <tr>
	                <td align="right" class="l-table-edit-td" valign="top">
	                	菜单类型:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                    <input id="rbtnl_0" type="radio" name="rbtnl" value="1" checked="checked" />
	                    	<label for="rbtnl_0">功能类</label> 
	                    <input id="rbtnl_1" type="radio" name="rbtnl" value="2" />
	                    	<label for="rbtnl_1">按钮类</label>
	                </td>
	                <td align="left"></td>
	            </tr>   
	             <tr>
	                <td align="right" class="l-table-edit-td">
	                	操作动作（指令）:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                	<input name="txtEmail" type="text" id="txtDoAction" 
	                		ltype="text" validate="{required:true,email:true}" /></td>
	                <td align="left"></td>
	            </tr>
	            <tr>
	                <td align="right" class="l-table-edit-td" valign="top">
	                	爱好:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                     <input id="CheckBoxList1_0" type="checkbox" name="CheckBoxList1$0" checked="checked" />
	                     	<label for="CheckBoxList1_0">篮球</label><br />
	                     <input id="CheckBoxList1_1" type="checkbox" name="CheckBoxList1$1" />
	                     	<label for="CheckBoxList1_1">网球</label> <br />
	                     <input id="CheckBox1" type="checkbox" name="CheckBoxList1$1" />
	                     	<label for="CheckBoxList1_1">足球</label>      
	                </td>
	                <td align="left"></td>
	            </tr>  
	                 
	            <tr>
	                <td align="right" class="l-table-edit-td">
	                	入职日期:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                    <input name="txtDate" type="text" id="txtDate" ltype="date" 
	                    	validate="{required:true}" />
	                </td>
	                <td align="left"></td>
	            </tr>
	            <tr>
	                <td align="right" class="l-table-edit-td">
	                	年龄:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                    <input name="txtAge" type="text" id="txtAge" ltype='spinner' 
	                    	ligerui="{type:'int'}" value="20" class="required" 
	                    		validate="{digits:true,min:1,max:100}" />
	                </td>
	                <td align="left"></td>
	            </tr>
	            <tr>
	                <td align="right" class="l-table-edit-td">
	                	部门:
	                </td>
	                <td align="left" class="l-table-edit-td">
	                	<select name="ddlDepart" id="ddlDepart" ltype="select">
							<option value="1">主席</option>
							<option value="2">研发中心</option>
							<option value="3">销售部</option>
							<option value="4">市场部</option>
							<option value="5">顾问组</option>
						</select>
	                </td>
	            </tr>
	            <tr>
	                <td align="right" class="l-table-edit-td">
	                	地址:
	                </td>
	                <td align="left" class="l-table-edit-td"> 
	                	<textarea cols="100" rows="4" class="l-textarea" id="address" 
	                		style="width:400px" validate="{required:true}" >
	                	</textarea>
	                </td>
	                <td align="left"></td>
	            </tr>
	        </table>
	 		<br />
			<input type="button" value="提交" id="toAdd" class="l-button l-button-submit" /> 
			<input type="button" value="测试" class="l-button l-button-test"/>
	    </form>
	</div>
    <div style="display:none">
    <!--  数据统计代码 --></div>
  </body>
</html>
