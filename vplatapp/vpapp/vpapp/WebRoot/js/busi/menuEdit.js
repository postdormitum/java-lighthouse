var eee;
 $(function (){
 /**
     $.metadata.setType("attr", "validate");
     var v = $("form").validate({
         debug: true,
         errorPlacement: function (lable, element)
         {
             if (element.hasClass("l-textarea"))
             {
                 element.ligerTip({ content: lable.html(), target: element[0] }); 
             }
             else if (element.hasClass("l-text-field"))
             {
                 element.parent().ligerTip({ content: lable.html(), target: element[0] });
             }
             else
             {
                 lable.appendTo(element.parents("td:first").next("td"));
             }
         },
         success: function (lable)
         {
             lable.ligerHideTip();
             lable.remove();
         },
         submitHandler: function ()
         {
             $("form .l-text,.l-textarea").ligerHideTip();
             alert("Submitted!")
         }
     });
     $("form").ligerForm();
     $(".l-button-test").click(function (){
         alert(v.element($("#txtName")));
     });
   **/
      /**
     //处理提交
     $('#toAdd').click(function(){
   		$('form1').submit(function() {
   			var title = $('inpur[name=title]').val();
   			var content = $('textarea').val();
   			$('#form1').ajaxSubmit({
   				type: 'post', // 提交方式 get/post
          		url: basePath + '/sys/menuAction!save.do',
          		data:{
              		'title': title,
              		'content': content
              	},
          		success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
              		// 此处可对 data 作相关处理
              		alert('提交成功！');
          		}
      		});
      		return false; // 阻止表单自动提交事件
  		});
	});
	**/
	$('#toAdd').click(function(){
		var txtName = $("#txtName").attr("value");
		var actionType = $('input[name=rbtnl][checked]').val();
		var doAction = $("#txtDoAction").attr("value");
		$.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"sys/menuAction!save.do",
            //提交的数据
            data:{'voSysMenu.item.actionType':actionType,'voSysMenu.item.actionName':txtName,
            			'voSysMenu.item.doAction':doAction,'voSysMenu.paramMap':{'id':1,'name':2}},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:'',		//function(){$("#msg").html("logining");}
            //成功返回之后调用的函数             
            success:function(data){
           		alert('返回成功提示');            
            }   ,
            //调用执行后调用的函数
            success: function(result){
            	var ret = eval('('+result+')');  
				alert(ret.second);  
			},
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
	});
 });  