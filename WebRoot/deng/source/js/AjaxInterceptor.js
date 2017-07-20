//请求前拦截器
//Ext.Ajax.on("beforerequest", function(conn,options,eOpts){}); 
//完成时拦截器（用来处理登录超时、没有操作权限等）
Ext.Ajax.on('requestcomplete',function(conn,response,options){
	try{
		var obj= Ext.decode(response.responseText); 
		var status = obj.xt_pt_status;
		if(typeof(status) != "undefined"){
			if(status==888){ 
		    	//1.session失效 
		    	top.Ext.Msg.alert('提示', "您的账号由于长时间没有操作已经失效",function(){
					 var win = top;
					 if(window.opener != null) {win=opener.top; window.close();}
					 win.location=basePath; 
				});
		    }else if(status==777){
		    	//2.功能权限 
			    top.Ext.example.msg('提示', "您还没有该模块的操作权限,请与管理员联系");
		    }else if(status==001){
		    	//3.非法页面
		    	top.Ext.MessageBox.alert('提示',"您的请求访问，已经列入到黑名单中，我们建议您联系管理员，谢谢！");
		    }else if(status==500){
		    	//4.系统出现异常
//		    	top.Ext.example.msg('提示', "您操作的模块出现异常、查看明细？");
		    	showErrorLog(obj.xt_pt_error_msg);
//		    	top.Ext.Msg.confirm('提示','您操作的模块出现异常、查看明细？',function(btn){
//					if(btn == 'yes'){
//						showErrorLog(obj.xt_pt_error_msg);
//					}
//				});
		    }
		}
    }catch(e){
    	if(typeof(e.message) != "undefined" && (e.message).indexOf("You're trying to decode an invalid JSON String:")>0){
	     	top.Ext.Msg.confirm('提示','您操作的模块出现异常、查看明细？',function(btn){
				if(btn == 'yes'){
					showErrorLog(e);
				}
			});
		}
 	}
});
 
//异常拦截器
Ext.Ajax.on('requestexception',function(conn,response,options){ 
 	var status = response.status;
 	try{
 		if(status == 500){
 			//1.系统出现异常
//	     	top.Ext.Msg.confirm('提示','您操作的模块出现异常、查看明细？',function(btn){
//				if(btn == 'yes'){
//					showErrorLog(obj);
//				}
//			});
 			top.Ext.example.msg('提示',"服务器出现异常!");
     	}else if(status == 404){
 	 		//2.404异常
	     	top.Ext.example.msg('提示',"无法找到页面请稍后再试!");
     	}else if(status == 400 || status == 403 || status == 504){
     		//3.访问超时
	     	top.Ext.example.msg('提示',"访问超时,可能存在网络异常,检查后请重试!");
	    }else if(status == 0){
	    	//4.其他异常
	    	top.Ext.example.msg('提示',"无法连接网络!可能您访问的服务器已经关闭或者当机!请联系管理员!");
	    }else{
	    	//5.其他异常
	    	top.Ext.example.msg('提示',"其他异常!错误状态信息:"+status);
	    }
 	}catch(e){
// 		top.Ext.MessageBox.alert('提示',"您操作的模块内容服务器出现异常!请与管理员联系!");
//     	top.Ext.Msg.confirm('提示','您操作的模块出现异常、查看明细？',function(btn){
//			if(btn == 'yes'){
//				showErrorLog(e);
//			}
//		});
 		top.Ext.example.msg('提示',"服务器出现异常!");
 	}
});
/**
*显示更多异常信息
**/
var errorLogWin;
function showErrorLog(message){
	errorLogWin = new top.Ext.Window({
		layout:'fit',
		width:600,
		height:150,
		maximizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'异常信息',
		headerPosition:'left',
		items:[{
			xtype:'textareafield',
			id:'message',
			anchor:'100%'
		}]/*,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]*/
	});
	errorLogWin.show();
	top.Ext.getCmp('message').setValue(message);
}
//js错误捕捉
window.onerror = function (msg, url, line, col, error) {
    var errors = [];
    errors.push('消息：' + msg);
    errors.push('网址：' + url);
    errors.push('行：' + line);
    errors.push('列：' + col);
    top.Ext.example.msg('提示',errors.join('<br/>'));
    return true;
};

