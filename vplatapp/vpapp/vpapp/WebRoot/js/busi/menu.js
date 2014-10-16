var grid = null;
$(function () {
	var actionName = $('#actionName').val();
	grid = $("#maingrid").ligerGrid({
		datatype: "json", 
		url:"sys/menuAction!menuLists.do?actionName="+actionName,
         columns: [
         	{ display: '主键', name: 'id',hide:'true', align: 'left', width: 120 },
         	{ display: '动作名称', name: 'actionName',isSort:false,type:'string', align: 'left', width: 120 },
         	{ display: '动作类型', name: 'actionType',isSort:false, minWidth: 60 },
         	{ display: '操作时间', name: 'opTime',type:'string', minWidth: 60 },
         	{ display: '是否删除', name: 'isDel',isSort:false,type:'string', minWidth: 60 },
         	{ display: '操作', name: 'sc_op',width:100,render:myAction}
         ],
	     pageSize:15,
	     pager:jQuery('#pager2'),
	     onToNext:onToNext,
	     width: '100%',
	     height:'100%'
	});
	
	function onToNext(){
		//alert('next page');
	}
	
	$("#pageloading").hide();
	
	function myAction(cellValue){
		return '<a href="javascript:userBySee('+cellValue.id_2+')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:userByUpdate('+cellValue.id_2+')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:userByDelete('+cellValue.id_2+')">删除</a>';
	}
});
//查询方法
function f_search(){
	// jQuery('#maingrid').ligerGrid('setGridParam',{page:1});
	 //var roleid=document.getElementById("roleid").options[document.getElementById("roleid").options.selectedIndex].value;
	 var actionName = $('#actionName').val();
	 var url="actionmenu!lists.do";
	 grid.setOptions({newPage:1});
	 grid.setParm("actionName2",actionName);
	 grid.loadData(url); 
}

//添加
function f_openEditWin(aType){
    jQuery.ajax({
    	dataType:"json",
    	url: 'actionmenu!forEdit.do?actionType='+aType,
    	type: "POST",                   // 设置请求类型为"POST"，默认为"GET"
    	beforeSend: function()          // 设置表单提交前方法
    	{},
    	error: function(request) {      // 设置表单提交出错
    		alert("表单提交出错，请稍候再试");
    	},
    	success: function(data) { // 设置表单提交完成使用方法
    		if(data.actionType == "add"){
    			editMenu(data.actionType);//window.location = url;
    		}else{
    			alert(data.actionType);
    		}
		}
	});
}

function editMenu(aType){
	$.ligerDialog.open({
		title : '编辑菜单',
        url: 'view/menu/menuEdit.jsp?actionType='+aType,
        width: $(window).width() *0.7,
        height: $(window).height() * 0.9
    });
}

