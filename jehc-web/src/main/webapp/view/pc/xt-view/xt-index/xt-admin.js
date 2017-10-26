$(function () {
    $('#tabs').addtabs();
    //clickAddTab('/index/desk.html','平台首页','home_index',false);
    initLockSystem();
})

function indexHome(){
	 $('#tab_home').addClass('active');
     $('#home').addClass('active');
     Addtabs.drop();
}
$(function () {  
	  $('#myTab a:last').tab('show');//初始化显示哪个tab  
	  $('#myTab a').click(function (e) {  
	    e.preventDefault();//阻止a链接的跳转行为  
	    $(this).tab('show');//显示当前选中的链接及关联的content  
	  })  
	}) 

function clickAddTab(url,title,id,close){
	var tabNumbs = Addtabs.tabList();
	if(tabNumbs > 5){
		toastrBoot(4,"打开选项卡超过6个，请关闭操作!");
		return;
	}
	if(null == url || url == ''){
		return;
	}
	var closeable = close;
	if(closeable == false){
		closeable=false;
	}else{
		closeable=true;
	}
	var dt1 = nowTimestamp();
	Addtabs.add({
        id:id,
        title:title,
        close:closeable,
        url:"../"+url
    })
    loadXtIframeComplete(id,dt1,title);
}

/**注销**/
function loginout(){
	msgTishCallFnBoot("确定要注销平台？",function(){
		$.ajax({
			url:'../login/loginout',
	        type:'POST',
	        success:function(result){
	        	toastrBoot(3,"注销平台失败!");
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
	$('#updatePwdModal').modal();
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
		$('#lockModal').modal({backdrop: 'static', keyboard: false});
		setCookie("syslock", '1', 240);
	}else{
		if(flag == 1){
			$('#lockModal').modal({backdrop: 'static', keyboard: false});
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
	clickAddTab('/xtSearchController/loadXtSearch?keywords='+encodeURI($('#keywords').val()),'关键字搜索结果','search_page_');
}

function updateUserPic(){
	toastrBoot(1,'该功能暂未开放');
}

//关闭所有选项卡
function closeAllTab(){
	Addtabs.closeAll();
}

//关闭当前选项卡
function closeCruTab(){
	var id = $("li.active").children("a")[0].href.split("#")[1];
	if(id != 'home'){
		Addtabs.close(id);
	    Addtabs.drop();
	    $('#popMenu').fadeOut();
	}
}

function refreshCruTab(){
	var jehchref = $($("li.active").children("a")[0]).attr('jehchref');
	var jehcid = $($("li.active").children("a")[0]).attr('jehcid');
	if(null == jehchref){
		return;
	}
	Addtabs.add({'id':jehcid,'url':jehchref});
}

//关闭左侧选项卡
function closeLeftTab(){
	
}
//关闭右侧选项卡
function closeRightTab(){
	var tab_id = $("li.active").children("a")[0].href.split("#")[1];
	if(tab_id != 'home'){
	    $('#tab_' + tab_id).nextUntil().each(function () {
	        var id = $(this).attr('id');
	        if (id && id != 'tab_' + tab_id) {
	            Addtabs.close($(this).children('a').attr('aria-controls'));
	        }
	    });
	    Addtabs.drop();
	    $('#popMenu').fadeOut();
	}
}

///**
// * 增加标签页
// */
//function addTab(options) {
//    //option:
//    //tabMainName:tab标签页所在的容器
//    //tabName:当前tab的名称
//    //tabTitle:当前tab的标题
//    //tabUrl:当前tab所指向的URL地址
//    var exists = checkTabIsExists(options.tabMainName, options.tabName);
//    if(exists){
//        $("#tab_a_"+options.tabName).click();
//    } else {
//        $("#"+options.tabMainName).append('<li id="tab_li_'+options.tabName+'"><a href="#tab_content_'+options.tabName+'" data-toggle="tab" id="tab_a_'+options.tabName+'"><button class="close closeTab" type="button" onclick="closeTab(this);">×</button>'+options.tabTitle+'</a></li>');
//        //固定TAB中IFRAME高度
//        mainHeight = $(document.body).height() - 5;
//        var content = '';
//        if(options.content){
//            content = option.content;
//        } else {
//            content = '<iframe src="' + options.tabUrl + '" width="100%" height="'+mainHeight+'px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
//        }
//        $("#"+options.tabContentMainName).append('<div id="tab_content_'+options.tabName+'" role="tabpanel" class="tab-pane" id="'+options.tabName+'">'+content+'</div>');
//        $("#tab_a_"+options.tabName).click();
//    }
//}
// 
///**
// * 关闭标签页
// * @param button
// */
//function closeTab (button) {
//    //通过该button找到对应li标签的id
//    var li_id = $(button).parent().parent().attr('id');
//    var id = li_id.replace("tab_li_","");
//    //如果关闭的是当前激活的TAB，激活他的前一个TAB
//    if ($("li.active").attr('id') == li_id) {
//        $("li.active").prev().find("a").click();
//    }
//    //关闭TAB
//    $("#" + li_id).remove();
//    $("#tab_content_" + id).remove();
//};
///**
// * 判断是否存在指定的标签页
// * @param tabMainName
// * @param tabName
// * @returns {Boolean}
// */
//function checkTabIsExists(tabMainName, tabName){
//    var tab = $("#"+tabMainName+" > #tab_li_"+tabName);
//    return tab.length > 0;
//}


//判断iframe是否加载完毕
var xtIframe;
function loadXtIframeComplete(id,dt1,text){
	/**开启多个Tab目的**/
	xtIframe = document.getElementById("iframe_id_"+id);
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