$(function () {
    initLockSystem();
    $("#content-main").css("height", tableHeight()-153);
})

function indexHome(){
}
function clickAddTab(url,title,id){
	if(null == url || url == ''){
		return;
	}	
	$('#triggerID').attr('data-index',id);
	$('#triggerID').attr('rootId',id);
	$('#triggerID').attr('idBu',id);
	$('#triggerID').attr('href',url);
	$('#triggerSpanID').text(title);
	document.getElementById("triggerSpanID").click();
}
function doActive(id,idList,tabIdList){
	try{
    	//清空之前选中样式
    	if(typeof(tabIdList) == "undefined" || null == tabIdList || '' == tabIdList){
    		tabIdList = Addtabs.tabIdList();
    	}
    	for(var i = 0; i < tabIdList.length; i++){
    		var tabIdTemp = tabIdList[i];
    		if(null != tabIdTemp && '' != tabIdTemp){
    			var tabIdArray = tabIdTemp.split(',');
    			for(var j = 0; j < tabIdArray.length; j++){
    				$('#menu'+tabIdArray[j]).removeClass('start active open');
    			}
    		}
    	}
        //选中菜单样式开始
        if(null != idList && '' != idList){
        	var idArray = idList.split(',');
        	for(var i = 0; i < idArray.length; i++){
        		$('#menu'+idArray[i]).addClass('start active open');
            }
        }
        if(null != id && '' != id){
        	$('#menu'+id).addClass('start active open');
        }
    	//选中菜单样式结束
    }catch (e) {
	}
}

/**注销**/
function loginout(){
	msgTishCallFnBoot("确定要注销平台？",function(){
		$.ajax({
			url:'../login/loginout',
	        type:'POST',
	        success:function(result){
	        	toastrBoot(3,"注销平台成功!");
	        	var win = top;
				if(window.opener != null) {win=opener.top; window.close();}
				win.location.href=basePath;
	        }, 
	        error:function(){
	        	toastrBoot(4,"注销平台失败!");
	        }
	    })
	})
}

/////////////修改密码开始///////////////////
function updatePwd(){
	$('#updatePwdModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
		$("#updatePwdModalDialog").css({'width':'420px'}); 
    });
	$('#updatePwdForm').bootstrapValidator({
	  message:'此值不是有效的',
	  feedbackIcons:{
	  },
	  fields:{
		  oldPwd:{
	          validators:{
	              notEmpty:{
	                  message:'旧密码不能为空'
	              },
	              stringLength:{
	                  min:1,
	                  max:30,
	                  message:'用户账号字符长度不能超过30个'
	              }
	          }
	      },
	      newPwd:{
	         validators:{
	             notEmpty:{
	                 message:'新密码不能为空'
	             },
	             stringLength:{
	                 min:1,
	                 max:20,
	                 message:'新密码长度不能超过20个'
	             }
	         }
	     },
	     surePwd:{
	         validators:{
	             notEmpty:{
	                 message:'确认密码不能为空'
	             },
	             stringLength:{
	            	 min:1,
	                 max:20,
	                 message:'确认密码长度不能超过20个'
	             }
	         }
	     }
	  }
	});
}
function doUpdate(){
	var updatePwdForm =  $('#updatePwdForm');
	if(typeof(updatePwdForm) == "undefined" ||null == updatePwdForm || '' == updatePwdForm){
		window.parent.toastrBoot(4,"未能获取到form对象!");
		return;
	}
	var boostrapValidator =updatePwdForm.data('bootstrapValidator');
	boostrapValidator.validate();
	//验证有效开启发送异步请求
	if(boostrapValidator.isValid()){
		var newPwd = $('#newPwd').val();
		var surePwd = $('#surePwd').val();
		if(newPwd != surePwd){
			window.parent.toastrBoot(4,"两次输入的密码不一样,请重新输入!");
			return;
		}
		msgTishCallFnBoot("确定要修改密码？",function(){
			$.ajax({
				url:basePath+'/index/updatePwd',
	            type:'POST',//PUT DELETE POST
	            data:updatePwdForm.serialize(),
	            success:function(result){
	            	var obj = eval("(" + result + ")");
	            	console.info(obj);
	            	if(obj.success == false){
	            		window.parent.toastrBoot(4,obj.msg);
	            		return;
	            	}
	            	msgTishCallFnBoot("修改密码成功，请重新登录该平台！",function(){
	            		var win = top;
						if(window.opener != null){win=opener.top; window.close();}
						win.location.href=basePath;
	            	})
	            }, 
	            error:function(){
	            	window.parent.toastrBoot(4,"修改密码失败!");
	            }
	        })
		});
	}else{
		window.parent.toastrBoot(4,"存在不合法的字段!");
	}
}
//////////////////修改密码结束////////////////
//////////////////初始化锁屏开始//////////////
function initLockSystem(flag){
	if(getCookie("syslock") == 1){
		$('#lockModal').modal({backdrop: 'static', keyboard: false}).on("shown.bs.modal",function(){  
			$("#lockModalDialog").css({'width':'420px'}); 
	    });
		setCookie("syslock", '1', 240);
	}else{
		if(flag == 1){
			$('#lockModal').modal({backdrop: 'static', keyboard: false}).on("shown.bs.modal",function(){  
				$("#lockModalDialog").css({'width':'420px'}); 
		    });
			setCookie("syslock", '1', 240);
		}
	}
}
//////////////////初始化锁屏结束//////////////

//////////////////执行解锁功能开始////////////
function unlockSystem() {
	var lockForm =  $('#lockForm');
	var password = $('#password').val();
	if(null == password || '' == password){
		window.parent.toastrBoot(4,"请输入解锁密码！");
		return;
	}
	$.ajax({
		url:basePath+'/index/validatePassword',
        type:'POST',//PUT DELETE POST
        data:lockForm.serialize(),
        success:function(result){
        	var obj = eval("(" + result + ")");
        	if(obj.msg == '1'){
        		setCookie("syslock", '0', 240);
        		$('#lockModal').modal('hide');
        	}else{
        		window.parent.toastrBoot(4,"密码错误！");
        	}
        }, 
        error:function(){
        	window.parent.toastrBoot(4,"解锁失败!");
        }
    })
}
//////////////////解锁结束///////////////////
//关键字搜索
function search(){
	//采用Tab页打开方式
	//clickAddTab(basePath+'/xtSearchController/loadXtSearch?keywords='+encodeURI($('#keywords').val()),'关键词检索','search_page_');
	//直接打开新窗体方式
	window.open('../xtSearchController/loadXtSearch?keywords='+encodeURI($('#keywords').val()), "_blank");
}

function updateUserPic(){
	toastrBoot(1,'该功能暂未开放');
}

//判断iframe是否加载完毕
var xtIframe;
function loadXtIframeComplete(id,dt1,text){
	/**开启多个Tab目的**/
	xtIframe = document.getElementById("iframe"+id);
	if(xtIframe == null){
		var dt2 = nowTimestamp();
		//执行监控页面信息操作
		loadinfo(text,dt1,dt2);
	}else{
		if(xtIframe.attachEvent){ 
			xtIframe.attachEvent("onload", function(){ 
				//执行监控页面信息操作
				loadinfo(text,dt1,dt2);
			}); 
		}else{ 
			xtIframe.onload = function(){ 
				var dt2 = nowTimestamp();
				//执行监控页面信息操作
				loadinfo(text,dt1,dt2);
			}; 
		} 
	}
}

//返回当前时间戳
function nowTimestamp(){
	return new Date().getTime();
}
function dt(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	var dts = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
	return dts;
}
//执行监控页面信息操作
function loadinfo(text,dt1,dt2){
	$.ajax({
        url:'../xtLoadinfoController/addXtLoadinfo',
        type:'POST',
        data:{xt_loadinfo_begtime:dt1,xt_loadinfo_endtime:dt2,xt_loadinfo_modules:text},
        success:function(result){
        }, 
        error:function(){
        }
    })
}
//切换肤色
function displayTheme(){
	var themecss = getCookie("css");
	if(null != themecss && "" != themecss){
//		$('#chatheme').val(themecss);
		if(themecss == 'defaultClass'){
			$("#chatheme1").attr("checked","checked");
		}else if(themecss == 'whiteClass'){
			$("#chatheme2").attr("checked","checked");
		}else if(themecss == 'BlackminiClass'){
			$("#chatheme3").attr("checked","checked");
		}else if(themecss == 'WhiteminiClass'){
			$("#chatheme4").attr("checked","checked");
		}else if(themecss == 'BlackminiClassshrink'){
			$("#chatheme5").attr("checked","checked");
		}else if(themecss == 'WhiteminiClassshrink'){
			$("#chatheme6").attr("checked","checked");
		}
	}else{
		$("#chatheme1").attr("checked","checked");
	}
	$('#themeModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
		$("#themeModalDialog").css({'width':'420px'}); 
    });
}
//保存肤色
function changeTheme(){
//	var chatheme = $('#chatheme').val();
	var chatheme = $('input[name="chatheme"]:checked').val();
	msgTishCallFnBoot("切换肤色之后平台将重新刷新整个页面,确定要切换肤色吗？",function(){
		 var date=new Date();
      	 date.setTime(date.getTime() + 365*24*3066*1000);
		 window.document.cookie="css=" + chatheme + ";expires=" + date.toGMTString();
      	 window.document.cookie="css=" + chatheme + ";expires=" + date.toGMTString()+";path=/";
		 window.location.href=basePath+"/index/index.html";
	})
}
/*//获取通知信息
function geDyRemind(){
	ajaxBRequestCallFn('../xtDyRemindController/getXtDyRemindList',null,function(result){
		////////////////我的通知开始////////////////
		var xtNotifyReceiver=result.xtNotifyReceiverList;
		if(null == xtNotifyReceiver){
			return;
		}
		$('#notifyNum').html(xtNotifyReceiver.length);
		$('#notifyNumright').html(xtNotifyReceiver.length);
		$("#notify").empty();
		if(xtNotifyReceiver.length>15){
			for(var i=0;i<15;i++){
				var xt_notify = xtNotifyReceiver[i];
				var xt_notify_receiver_id=xt_notify.xt_notify_receiver_id;
				var xt_notify_title=xt_notify.xt_notify_title;
				var sub_xt_notify_title;
				if(xt_notify_title.length>10){
					sub_xt_notify_title=xt_notify_title.substr(0,10)+"...";
				}else{
					sub_xt_notify_title=xt_notify_title;
				};
				var receive_time=new Date(xt_notify.receive_time);
				receive_time = receive_time.toLocaleStringMDHM();
				$("#notify").append("<li><a target='_blank' onclick=\"toXtReceiverDetail('"+xt_notify_receiver_id+"')\"><span class='time'>"+receive_time+"</span><span class='details' title='"+xt_notify_title+"'><span class='label label-sm label-icon label-success'><i class='fa fa-plus'></i></span>"+sub_xt_notify_title+"</span></a></li>")
			}
			$("#notify").append("<li class='text-center'><a href='../xtNotifyController/loadXtNotify' target='_blank'>查看更多</a></li>")
		}else{
			for(var i=0;i<xtNotifyReceiver.length;i++){
				var xt_notify = xtNotifyReceiver[i];
				var xt_notify_receiver_id=xt_notify.xt_notify_receiver_id;
				var xt_notify_title=xt_notify.xt_notify_title;
				var sub_xt_notify_title;
				if(xt_notify_title.length>10){
					sub_xt_notify_title=xt_notify_title.substr(0,10)+"...";
				}else{
					sub_xt_notify_title=xt_notify_title;
				};
				var receive_time=new Date(xt_notify.receive_time);
				receive_time = receive_time.toLocaleStringMDHM();
				$("#notify").append("<li><a target='_blank' onclick=\"toXtReceiverDetail('"+xt_notify_receiver_id+"')\"><span class='time'>"+receive_time+"</span><span class='details' title='"+xt_notify_title+"'><span class='label label-sm label-icon label-success'><i class='fa fa-plus'></i></span>"+sub_xt_notify_title+"</span></a></li>")
			}
		}
		////////////////我的通知结束////////////////
		
		
		
		////////////////我的消息开始////////////////
		var xtMessageList=result.xtMessageList;
		if(null == xtMessageList){
			return;
		}
		$('#messageNum').html(xtMessageList.length);
		$('#messageNumright').html(xtMessageList.length);
		$("#message").empty();
		if(xtMessageList.length>15){
			for(var i=0;i<15;i++){
				var xtMessage = xtMessageList[i];
				var xt_message_id=xtMessage.xt_message_id;
				var xt_meesage_content=xtMessage.xt_meesage_content;
				var sub_xt_meesage_content;
				if(xt_meesage_content.length>10){
					sub_xt_meesage_content=xt_meesage_content.substr(0,10)+"...";
				}else{
					sub_xt_meesage_content=xt_meesage_content;
				};
				var ctime=new Date(xtMessage.ctime);
				ctime = ctime.toLocaleStringMDHM();
				var fromName = xtMessage.fromName;
				$("#message").append(
					"<li>"+
		                "<a href='#' target='_blank' onclick=\"toXtMessageDetail('"+xt_message_id+"')\">"+
		                    '<span class="photo"><img src="../deng/images/default/user.png" class="img-circle" alt=""> </span>'+
		                    '<span class="subject">'+
		                        '<span class="from"> '+fromName+' </span>'+
		                        '<span class="time">'+ctime+' </span>'+
		                    '</span>'+
		                    '<span class="message">'+sub_xt_meesage_content+'</span>'+
		                '</a>'+
		            '</li>'
	            )
				//$("#message").append("<li><a target='_blank' onclick=\"toXtMessageDetail('"+xt_message_id+"')\"><span class='time'>"+ctime+"</span><span class='details' title='"+xt_meesage_content+"'><span class='label label-sm label-icon label-success'><i class='fa fa-plus'></i></span>"+sub_xt_meesage_content+"</span></a></li>")
			}
			$("#message").append("<li class='text-center'><a href='../xtMessageController/loadXtMessage' target='_blank'>查看更多</a></li>")
		}else{
			for(var i=0;i<xtMessageList.length;i++){
				var xtMessage = xtMessageList[i];
				var xt_message_id=xtMessage.xt_message_id;
				var xt_meesage_content=xtMessage.xt_meesage_content;
				var fromName = xtMessage.fromName;
				if(xt_meesage_content.length>10){
					sub_xt_meesage_content=xt_meesage_content.substr(0,10)+"...";
				}else{
					sub_xt_meesage_content=xt_meesage_content;
				};
				var ctime=new Date(xtMessage.ctime);
				ctime = ctime.toLocaleStringMDHM();
				$("#message").append(
					"<li>"+
		                "<a href='#' target='_blank' onclick=\"toXtMessageDetail('"+xt_message_id+"')\">"+
		                    '<span class="photo"><img src="../deng/images/default/user.png" class="img-circle" alt=""> </span>'+
		                    '<span class="subject">'+
		                        '<span class="from"> '+fromName+' </span>'+
		                        '<span class="time">'+ctime+' </span>'+
		                    '</span>'+
		                    '<span class="message">'+sub_xt_meesage_content+'</span>'+
		                '</a>'+
		            '</li>'
	            )
				//$("#message").append("<li><a target='_blank' onclick=\"toXtMessageDetail('"+xt_message_id+"')\"><span class='time'>"+ctime+"</span><span class='details' title='"+xt_meesage_content+"'><span class='label label-sm label-icon label-success'><i class='fa fa-plus'></i></span>"+sub_xt_meesage_content+"</span></a></li>")
			}
		}
		////////////////我的消息结束////////////////
	},{isShowWating:false});
}
geDyRemind();
setInterval(geDyRemind,50000);

Date.prototype.toLocaleStringMDHM = function() {
    return (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes();
};*/
function toXtReceiverDetail(xt_notify_receiver_id){
	window.open('../xtNotifyReceiverController/toXtReceiverDetail?xt_notify_receiver_id='+xt_notify_receiver_id,"_blank"); 
	setTimeout(geDyRemind,500)
}

function toXtMessageDetail(xt_message_id){
	window.open('../xtMessageController/toXtMessageDetail?type=1&xt_message_id='+xt_message_id,"_blank"); 
	setTimeout(geDyRemind,500)
}