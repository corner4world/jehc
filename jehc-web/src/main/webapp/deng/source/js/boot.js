//按钮白色图标
var addIcon = '../deng/images/grid/using/add.png';
var editIcon = '../deng/images/grid/using/edit.png';
var delIcon = '../deng/images/grid/using/del.png';
var copyPasteIcon = '../deng/images/grid/classic/copyPaste_new.png';
var detailIcon = '../deng/images/grid/using/detail.png';
var printIcon = '../deng/images/grid/using/print.png';
var dcIcon = '../deng/images/grid/using/dc.png';
var refreshIcon = '../deng/images/grid/using/refresh.png';
var searchIcon = '../deng/images/grid/using/search.png';
var clearSearchIcon = '../deng/images/grid/using/clearSearch.png';
var zkIcon = '../deng/images/grid/using/zk_new.png';
var cutIcon = '../deng/images/grid/using/cut_new.png';
var uploadIcon = '../deng/images/grid/using/upload.png';
var jhIcon = '../deng/images/grid/using/jh_new.png';
var backIcon = '../deng/images/grid/using/back.png';
var lingdangIcon = '../deng/images/grid/using/lingdang.png';
var clockIcon = '../deng/images/grid/using/clock_new.png';
var runIcon = '../deng/images/grid/using/run.png';
var saveIcon = '../deng/images/grid/using/save.png';
var listIcon = '../deng/images/grid/using/list_.png';
var indexlistIcon = '../deng/images/grid/otp/listround.png';
var payIcon = '../deng/images/grid/using/pay.png';
var phoneIcon = '../deng/images/grid/using/phone.png';
var collapsibleIcon = '../deng/images/grid/using/collapsible.png';
var taskIcon = '../deng/images/grid/using/task.png';
var homeIcon = '../deng/images/home/home.png';
var messageIcon = '../deng/images/top/message.png';
var lockIcon = '../deng/images/grid/otp/lock.png';

var indexlist = '../deng/images/top/index_list.png';
var indexNav = '../deng/images/grid/otp/list.png';
var indexUserIcon = '../deng/images/top/index_user.png';
var indexCollapseIcon = '../deng/images/top/index_collapse.png';
var indexSearchIcon = '../deng/images/top/index_search.png';
var indexRopot = '../deng/images/top/indexRopot.png';
var indexLingdang = '../deng/images/top/lingdang16x16.png';
var indexMail = '../deng/images/top/indexMail.png';
var indexIcon = '../deng/images/home/index.png';

var chattingIcon = '../deng/images/icons/user.png';
var designIcon = '../deng/images/icons/cog.png';
var iphoneIcon = '../deng/images/icons/battery_full.png';
var onoffIcon = "../deng/images/icons/on-off.png";
var bsdefimg= "../deng/images/default/add_d.png";
var uploadimg = '../deng/images/common/uploadFFF.png';
var uploadfaliimg = '../deng/images/default/upload_fail.png';
var uploadsucessimg = '../deng/images/default/upload_sucess.png';

var userIcon = '../deng/images/default/user.png';

/**
 * 设置Cookie
 * @param {} name
 * @param {} value
 */
function setCookie(name, value, minuts) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
    var expiration = new Date((new Date()).getTime() + minuts * 60000 * 60);
    window.parent.document.cookie = name
			+ "="
			+ escape(value)
			+ "; expires=" + expiration.toGMTString();
}
/**
 * 获取Cookie
 * @param {} Name
 * @return {}
 */
function getCookie(Name) {
	var search = Name + "="
	if (window.parent.document.cookie.length > 0) {
		offset = window.parent.document.cookie.indexOf(search)
		if (offset != -1) {
			offset += search.length
			end = window.parent.document.cookie.indexOf(";", offset)
			if (end == -1)
				end = window.parent.document.cookie.length
			return unescape(window.parent.document.cookie.substring(offset, end))
		} else
			return ""
	}
}
/**
 * 从缓存中清除Cookie
 * @param {} name
 */
function clearCookie(name) {
	var expdate = new Date();
	expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));
	setCookie(name, "", expdate);
}
/**
 * 删除Cookie
 * @param {} name
 */
function delCookie(name){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
	window.parent.document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}


//手机端校验
function phoneLogin(){
	if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i)){
		return 'phone';
	}else{
		return 'pc';
	}
}



/**
 * 从缓存中清除Cookie
 * @param {} name
 */
function clearCookie(name) {
	var expdate = new Date();
	expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));
	setCookie(name, "", expdate);
}

/**
 * 获取IE版本
 * return ieVison:0未获取到IE版本,-1非IE,其他返回IE版本 1表示火狐
 */
var ieVison = 6;
function getnavigator(){
	if(navigator.userAgent.indexOf("MSIE")>0){ 
		if(navigator.userAgent.indexOf("MSIE 6.0")>0){ 
			ieVison = 6;
		}else if(navigator.userAgent.indexOf("MSIE 7.0")>0){
			ieVison = 7;
		}else if(navigator.userAgent.indexOf("MSIE 8.0")>0){
			ieVison = 8;
		}else if(navigator.userAgent.indexOf("MSIE 9.0")>0){
			ieVison = 9;
		}else if(navigator.userAgent.indexOf("MSIE 10.0")>0){
			ieVison = 10;
		}else if(navigator.userAgent.indexOf("MSIE 11.0")>0){
			ieVison = 11;
		}else{
			ieVison = 0;
		}
	}else{
		if(navigator.userAgent.indexOf("Firefox")>0){
			ieVison = 1;
		}else{
			ieVison = -1;
		}
	} 
	return ieVison;
}


//bootstrap datatables 国际化分页提示信息
function callLang(){
	var lang = {
			//"sLengthMenu":"每页显示 _MENU_ 条记录",
			"sZeroRecords":"对不起，查询不到任何相关数据",
			"sInfo":"当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmtpy":"找不到相关数据",
			"sInfoFiltered":"数据表中共为 _MAX_ 条记录)",
			"sProcessing":"<i class='glyphicon glyphicon-dashboard'></i>正在加载中...",
			"sLoadingRecords":"载入中...",
			"sSearch":"搜索",
			"bAutoWidth":true,
		    "sLengthMenu":"每页显示 _MENU_ 条记录",
		    "sInfoEmpty":"显示0条记录",
		    "sInfoFiltered":"(全部记录数 _MAX_ 条)",
		    "sInfoPostFix":"",
			"sUrl":"", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
			"oPaginate":{
				"sFirst":"首页",
				"sPrevious":"上一页 ",
				"sNext":"下一页 ",
				"sLast":"尾页 ",
				"sJump":"跳转"
			},
			"oAria":{
			 "sSortAscending":":升序",
			 "sSortDescending":":降序"
			}
		};
	return lang;
}

//初始化分页配置
var DataTablesPaging = {
    language:callLang(),
    /**
     * 获取ajax分页options设置
     */
    pagingOptions:function(settings) {
        var options = {
//        		"sScrollX":"100%",//表格的宽度
    			"sScrollXInner":"100%",//表格的内容宽度
    			"bScrollCollapse":false,//当显示的数据不足以支撑表格的默认的高度时，依然显示纵向的滚动条。(默认是false) 
    			"bScrollCollapse":true,
    			"bFilter":false,//搜索栏
    			"bSort":false,//是否支持排序功能
    			"bInfo":true,//显示表格信息
    			"destroy":true,//销毁表格对象
    			"bAutoWidth":false,//自适应宽度
    			"serverSide":true,//启用服务器端分页
    			"searching":false,//禁用原生搜索
    			"orderMulti":false,//启用多列排序
    			//"aaSorting":[[2,"asc"]],//给列表排序 ，第一个参数表示数组 (由0开始)。1 表示Browser列。第二个参数为 desc或是asc
    			"bStateSave":true,//保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了
    			"oLanguage":callLang(),//多语言配置
    			"bPaginate":true,//是否显示分页
    			"pageLength":10,//首次加载的数据条数
    			"bLengthChange":true,//每页显示的记录数
    			"sPaginationType":"full_numbers",//分页，一共两种样式，full_numbers和two_button(默认)
    			"aLengthMenu":[[10, 25, 50, 100, 500 ],["10", "25","50", "100","500" ]],//设置每页显示记录的下拉菜单也可以设置为pageList
    			height:tableHeight(),//高度调整
    			bJQueryUI:true,//采用jQueryUI样式
	            order:settings.order,//[index,'asc|desc']
	            columns:settings.colums,
	            columnDefs:settings.columsdefs,
		        striped:true, //是否显示行间隔色
		        showRefresh:true,//刷新按钮
		        ajax:settings.ajax,
		        processing:true,//隐藏加载提示,自行处理
		        fnRowCallback:settings.fnRowCallback
        };
        return options;
    }
};

function datatablesCallBack(data, callback, settings,url,opt){
	 var formdata;
	 //缺省采用searchForm
	 if(null != opt){
		 formdata = $("#searchForm").serializeArray();
	 }else{
		 formdata = $("#"+opt.searchformId).serializeArray();
	 }
	 //封装请求参数
	 var param = {};
	 var paramdata = {};
	 param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
	 param.start = data.start;//开始的记录序号
	 param.page = (data.start/data.length)+1;//当前页码
	 for (var i = 0; i < formdata.length; i++) {
		 paramdata[formdata[i]['name']] = formdata[i]['value'];
     };
     param.searchJson = JSON.stringify(paramdata);
	 //ajax请求数据
	 $.ajax({
	 	  type:"GET",
		  url:url,
		  cache:false,//禁用缓存
		  data:param,//传入组装的参数
		  dataType:"json",
		  success:function (result){
			  try {
				  //setTimeout仅为测试延迟效果
				  setTimeout(function(){
					  result = eval("(" + result + ")");
					  //封装返回数据
					  var returnData = {};
					  returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
					  returnData.recordsTotal = result.total;//返回数据全部记录
					  returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
					  returnData.data = result.data;//返回的数据列表
					  //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
					  //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
					  callback(returnData);
				   },200);
			 } catch (e) {
				 
			 }
		  }
	});
	//datatables每页显示数量下拉框样式
	$("[class=dataTables_length]").find("select").each(function(index,element){
		$(element).attr("class","uneditable-input")
	});
}

//筛选数据信息（重新加载查询使用，默认采用grid作为datatables）
function search(datatablesid){
	var datatables = $('#'+datatablesid).DataTable();
	datatables.ajax.reload();
}

//清空查询条件
function resetAll(formid){
	if(null == formid){
		$('#searchForm')[0].reset();
	}else{
		$('#'+formid)[0].reset();
	}
}

//日期选择器渲染
function datetimeInit(){
	$(".form_datetime").datetimepicker({
		 format:"yyyy-mm-dd",
		 autoclose:true,
		 todayBtn:true,
		 todayHighlight:true,
		 showMeridian:true,
		 pickerPosition:"bottom-left",
		 language:'zh-CN',//中文，需要引用zh-CN.js包
		 startView:2,//月视图
		 minView:2//日期时间选择器所能够提供的最精确的时间选择视图
	}); 
}

//tableHeight函数
function tableHeight(){
    return $(window).height();
}

//bootstrap提示
function toastrBoot(flag,msg){
	window.parent.toastr.options = {
	  "closeButton":false,//是否显示关闭按钮
	  "debug":false,//是否使用debug模式
	  "positionClass":"toast-top-center",//弹出窗的位置
	  "onclick":null,
	  "showDuration":"1000",//显示的动画时间
	  "hideDuration":"1000",//消失的动画时间
	  "timeOut":"1000",//展现时间
	  "extendedTimeOut":"100",//加长展示时间
	  "showEasing":"swing",//显示时的动画缓冲方式
	  "hideEasing":"linear",//消失时的动画缓冲方式
	  "showMethod":"fadeIn",//显示时的动画方式
	  "hideMethod":"fadeOut"//消失时的动画方式
	}
	if(flag == 1){//info
		window.parent.toastr.info(msg, '提示');
	}else if(flag == 2){//
		window.parent.toastr.warning(msg,'提示')//显示一个警告
	}else if(flag == 3){
		window.parent.toastr.success(msg, '提示')//显示一个成功
	}else if(flag == 4){
		window.parent.toastr.error(msg, '提示')//显示错误
	}else{
		window.parent.toastr.info(msg, '提示');//缺省info
	}
}

//普通提示
function msgTishBoot(msg,fn){
	bootbox.alert({  
	    buttons:{  
	       ok:{  
	           label:'确认',  
	           className:"btn btn-success"
	        }  
	    },  
	    message:msg,  
	    callback:function() { 
	    	fn();
	    },  
	    title:"提示信息",  
	});  
}
//普通提示带确认框按钮并返回事件
function msgTishCallFnBoot(msg,fn){
	confirmB(msg,fn);
}

//确认提示
function confirmB(msg,fn){
	 bootbox.dialog({
		title:"提示",
		closeButton:true,
		animate:true,
		locale:"zh-CN",
		message:msg,
		buttons:{
			"success":{
				"label":"确认",
				"className":"btn btn-success",
				"callback":function(){
					fn();
				}
			},
			"cancel":{
				"label":"取消",
				"className":"btn btn-default",
				"callback":function() {
					
				}
			}
		}
	 });
}
//获取所有DataTables中复选框值集合
function returncheckIds(checkboxName){
	var id = [];
	if(null == checkboxName || checkboxName){
		//缺省采用checkId
		$('input[name="checkId"]:checked').each(function(){ 
	    	id.push($(this).val());
	    });
	}else{
		//缺省采用checkId
		$('input[name="'+checkboxName+'"]:checked').each(function(){ 
	    	id.push($(this).val());
	    });
	}
	return id;
}

//获取datatables中所有选中的checkbox数量
function returncheckedLength(className){
	if(null == className || '' == className){
		//缺省采用默认checkchild
		return $(".checkchild:checked").length;
	}else{
		return $("."+className+":checked").length;
	}
}

//实现全选和反选（classPchekall为全选按钮中的class  ,classCh为被全选的checkbox集合）
function docheckboxall(classPchekall,classCh){
	$("."+classPchekall).click(function () {
	      var check = $(this).prop("checked");
	      $("."+classCh).prop("checked",check);
	});
}

//实现单击行选中
function clickrowselected(datatablesid){
	$('#'+datatablesid+' tbody').on('click', 'tr', function() {
        $(this).toggleClass('row_selected');
	});
}

////////////////////////全局Ajax处理开始////////////////////////////
$(function(){  
    //设置jQuery Ajax全局的参数  
    $.ajaxSetup({  
        type:"POST",  
        complete:function(jqXHR,status){
        	//处理sucess和error之后的方法
        	try{
        		var obj = eval("(" + jqXHR.responseText + ")");
	        	var status = obj.xt_pt_status;
	        	//1.优先级判断访问成功后是否存在自定义非法信息提示
	    		if(typeof(status) != "undefined"){
	    			if(status==888){ 
	    		    	//1.session失效 
	    				msgTishCallFnBoot("您的账号由于长时间没有操作已经失效",function(){
	    					 var win = top;
	    					 if(window.opener != null) {win=opener.top; window.close();}
	    					 win.location=basePath; 
	    				});
	    				return;
	    		    }else if(status==777){
	    		    	//2.功能权限 
	    		    	window.parent.toastrBoot(2, "您还没有该模块的操作权限,请与管理员联系");
	    		    	return;
	    		    }else if(status==001){
	    		    	//3.非法页面
	    		    	window.parent.toastrBoot(2,"您的请求访问，已经列入到黑名单中，我们建议您联系管理员，谢谢！");
	    		    	return;
	    		    }else if(obj.xt_pt_status == 500){
	            		//只处理平台出现异常即拦截器抛出的异常json对象
	            		showBErrorLog(obj.xt_pt_error_msg);
	            	}
	    		}	
    	 	}catch(e){
    	 		window.parent.toastrBoot(4,'json转换出现异常');
    	 	}
        },
        error:function(jqXHR, textStatus, errorThrown){  
        	//2.判断是否出现服务器端异常
    		status = jqXHR.status;
    		if(status == 200){
    			return;
    		}
    		if(status == 500){
	 			//1.系统出现异常
    			window.parent.toastrBoot(4,"服务器出现异常!");
	     	}else if(status == 404){
	 	 		//2.404异常
	     		window.parent.toastrBoot(2,"无法找到页面请稍后再试!");
	     	}else if(status == 400 || status == 403 || status == 504 || status == 408){
	     		//3.访问超时
	     		window.parent.toastrBoot(2,"访问超时,可能存在网络异常,检查后请重试!");
		    }else if(status == 0){
		    	//4.其他异常
		    	window.parent.toastrBoot(2,"无法连接网络!");
		    }else{
		    	//5.其他异常
		    	window.parent.toastrBoot(2,"其他异常!错误状态信息:"+status);
		    }
        }
    });  
});  
////////////////////////全局Ajax处理结束////////////////////////////

//异常信息详细处理
function showBErrorLog(msg){
	msgTishBoot(msg,function(){
	});
}

//发送位置
function tlocation(url){
	document.location.href=url;
}

//发起提交表单
function submitBForm(formid,url,callUrl){
	if(null == formid || '' == formid){
		window.parent.toastrBoot(4,"未能获取到formid!");
		return;
	}
	var bootform =  $('#'+formid);
	if(typeof(bootform) == "undefined" ||null == bootform || '' == bootform){
		window.parent.toastrBoot(4,"未能获取到form对象!");
		return;
	}
	//验证
	var boostrapValidator =bootform.data('bootstrapValidator');
	boostrapValidator.validate();
	//验证有效开启发送异步请求
	if(boostrapValidator.isValid()){
		msgTishCallFnBoot("确定要提交该表单信息？",function(){
			$.ajax({
	            url:url,
//	            async:false,//同步，会阻塞操作
	            type:'POST',//PUT DELETE POST
	            data:bootform.serialize(),
	            success:function(result){
	            	try {
	            		result = eval("(" + result + ")");  
	            		if(typeof(result.success) != "undefined"){
	            			if(result.success){
			            		window.parent.toastrBoot(3,result.msg);
			        			if(null != callUrl){
			                		//默认返回页面
			                    	tlocation(callUrl);
			                	}
			        		}else{
			        			//失败还在原位置页面
			        			window.parent.toastrBoot(4,result.msg);
			        		}
	            		}
					} catch (e) {
						
					}
	            }, 
	            error:function(){
	            	
	            }
	        })
		})
	}else{
		window.parent.toastrBoot(4,"存在不合法的字段!");
	}
}

//发起提交表单并返回Fn方法
function submitBFormCallFn(formid,url,fn){
	if(null == formid || '' == formid){
		window.parent.toastrBoot(4,"未能获取到formid!");
		return;
	}
	var bootform =  $('#'+formid);
	if(typeof(bootform) == "undefined" ||null == bootform || '' == bootform){
		window.parent.toastrBoot(4,"未能获取到form对象!");
		return;
	}
	//验证
	var boostrapValidator =bootform.data('bootstrapValidator');
	boostrapValidator.validate();
	//验证有效开启发送异步请求
	if(boostrapValidator.isValid()){
		msgTishCallFnBoot("确定要提交该表单信息？",function(){
			$.ajax({
	            url:url,
//	            async:false,//同步，会阻塞操作
	            type:'POST',//PUT DELETE POST
	            data:bootform.serialize(),
	            success:function(result){
	                fn(result);
	            }, 
	            error:function(){
	            }
	        })
		})
	}else{
		window.parent.toastrBoot(4,"存在不合法的字段!");
	}
}

//ajax开启请求并返回地址（url处理地址，params参数，datatablesid数组 缺省默认）
function ajaxBReq(url,params,datatablesid){
	$.ajax({
        url:url,
//      async:false,//同步，会阻塞操作
        type:'POST',//PUT DELETE POST
        data:params,
        success:function(result){
        	try {
        		result = eval("(" + result + ")");  
        		if(typeof(result.success) != "undefined"){
        			if(result.success){
                		window.parent.toastrBoot(3,result.msg);
            		}else{
            			//失败还在原位置页面
            			window.parent.toastrBoot(4,result.msg);
            		}
                    //刷新datatables列表
                	if(null != datatablesid){
                		for(var i = 0; i < datatablesid.length; i++){
        		        	if(null != datatablesid[i] && "" != datatablesid[i]){
        		        		search(datatablesid[i]);
        			  		}
        		        }
                	}
        		}
			} catch (e) {
			}
        }, 
        error:function(){
        }
    })
}

//ajax开启请求并返回地址（url处理地址 callUrl处理成功返回的地址，params参数）
function ajaxBRequestCallUrl(url,callUrl,params){
	$.ajax({
        url:url,
//      async:false,//同步，会阻塞操作
        type:'POST',//PUT DELETE POST
        data:params,
        success:function(result){
        	try {
        		result = eval("(" + result + ")");  
        		if(typeof(result.success) != "undefined"){
        			if(result.success){
                		window.parent.toastrBoot(3,result.msg);
            			if(null != callUrl){
                    		//默认返回页面
                        	tlocation(callUrl);
                    	}
            		}else{
            			//失败还在原位置页面
            			window.parent.toastrBoot(4,result.msg);
            		}
        		}
			} catch (e) {
				
			}
        }, 
        error:function(){
        }
    })
}

//ajax开启请求并回调（url处理地址 ，params参数,fn返回的方法）
function ajaxBRequestCallFn(url,params,fn){
	$.ajax({
        url:url,
//      async:false,//同步，会阻塞操作
        type:'POST',//PUT DELETE POST
        data:params,
        success:function(result){
            fn(result);
        }, 
        error:function(){
        }
    })
}

///////////////////////省市区县统一处理开始//////////////////////////
function CallRegion(num){
	//默认绑定省
    getProvice(num);
    //绑定事件
    $("#provinceId_"+num).change(function(){
    	$("#cityId_"+num).html("");
	    var str = "<option value=''>请选择</option>";
	    $("#cityId_"+num).append(str);
        getCity(num);
        $("#districtId_"+num).html("");
	    var str = "<option value=''>请选择</option>";
	    $("#districtId_"+num).append(str);
    })
    $("#cityId_"+num).change(function(){
    	getCounties(num);
    })
}
function getProvice(num){
    //清空下拉数据
    $("#provinceId_"+num).html("");
    var str = "<option value=''>请选择</option>";
    $.ajax({
        type:"POST",
        url:"../xtAreaRegionController/getPList",
        dataType:"JSON",
        async:false,
        success:function(data){
            //从服务器获取数据进行绑定
            $.each(data, function(i, item){
            	 str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#provinceId_"+num).append(str);
        },
        error:function (){ alert("Error");}
    });
}
function getCity(num){
    var provinceId = $("#provinceId_"+num).val();
    //判断省份这个下拉框选中的值是否为空
    if(provinceId == ""){
        return;
    }
    $("#cityId_"+num).html("");
    var str = "<option value=''>请选择</option>";
    $.ajax({
        type:"POST",
        url:"../xtAreaRegionController/getCList",
        data:{"parentId":provinceId},
        dataType:"JSON",
        async:false,
        success:function (data) {
            //从服务器获取数据进行绑定
            $.each(data,function(i, item){
                str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#cityId_"+num).append(str);
        },
        error:function(){alert("Error");}
    });
}
function getCounties(num){
    var cityId = $("#cityId_"+num).val();
    //判断市这个下拉框选中的值是否为空
    if(cityId == ""){
        return;
    }
    $("#districtId_"+num).html("");
    var str = "<option value=''>请选择</option>";
    //将市的ID拿到数据库进行查询，查询出他的下级进行绑定
    $.ajax({
        type:"POST",
        url:"../xtAreaRegionController/getDList",
        data:{"parentId":cityId},
        dataType:"JSON",
        async:false,
        success:function(data){
            //从服务器获取数据进行绑定
            $.each(data,function (i, item) {
            	 str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#districtId_"+num).append(str);
        },
        error:function(){alert("Error");}
    });
}
///////////////////////省市区县统一处理结束//////////////////////////
function bUpload(fieldid,picid,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	/**
	//验证有效开启发送异步请求
	var jehcFile = $('#jehcFile').val();
	var jehcUploadForm = $('#jehcUploadForm');
	console.info(jehcFile);
	if(null != jehcFile){
		$.ajax({
			url:basePath+'/xtCommonController/upload',
            type:'POST',//PUT DELETE POST
            data:jehcUploadForm.serialize(),
            success:function(result){
            	var obj = eval("(" + result + ")");
            	console.info(obj);
            	if(obj.success == false){
            		window.parent.toastrBoot(4,obj.msg);
            		return;
            	}
            	//赋值
            	$("#"+picid).attr('src',obj.jsonValue); 
            	$("#"+fieldid).val(obj.jsonID);
            	//关闭上传窗口
            	$('#jehcUploadModal').modal('hide');
            	//并清空上传控件内容
            	$('#jehcFile').val('');
            }, 
            error:function(){
            	$('#jehcFile').val('');
            }
        })
	}else{
		window.parent.toastrBoot(4,"请选择上传的文件！");
	}
	**/
}

/////////////////////////////////////附件统一上传中心开始///////////////////////////////////
/**
 *ajaxBFilePathBackRequest表单中单个或多个附件路径回显
 *@param {} url,params
 */
function ajaxBFilePathBackRequest(url,params){
	$.ajax({
        url:url,
        type:'POST',//PUT DELETE POST
        data:params == null || '' == params ? {}:params, 
        success:function(result){
        	try {
        		var obj = eval("(" + result + ")");  
    	        for(var i = 0; i < obj.items.length; i++){
    	        	if(typeof($('#'+(obj.items[i].field_name+'_pic'))) != 'undefined'){
	        			$("#"+(obj.items[i].field_name+'_pic')).attr('src',obj.items[i].xt_attachmentPath); 
	        		}
    	        } 
			} catch (e) {
			}
        }, 
        error:function(){
        }
    })
}
/**
 * method:上传操作
 * fieldid:附件编号
 * picid:附件上传后回显图片对象编号
 * validateparameter:校验非法参数组装字符串
 * validateSize:校验大小
 * xt_path_absolutek:平台路径配置中心键（自定义上传对绝路径使用）
 * xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
 * xt_path_relativek:平台路径配置中心键（自定义上传相对路径）
 * llowedFileExtensions:['jpg','gif','png']
**/

function initBUpload(fieldid,picid,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	var allowedFileExtensions_ = ['jpg','gif','png','xls','xlsx','bmp','zip','docx','pptx','pdf','csv','txt','apk'];
	var maxFileSize_ = 0;
	if(null !=validateSize){
		maxFileSize_ = validateSize;
	}
	if(null != validateparameter){
		allowedFileExtensions_ = validateparameter;
	}
	$('#jehcUploadModal').modal({backdrop: 'static', keyboard: false});
	$("#jehcFile").fileinput({
		showUpload:true, //是否显示上传按钮
		showCaption:false,
		showPreview:true,
		uploadUrl:basePath+'/xtCommonController/upload',
		enctype:'multipart/form-data',
		language:'zh',
        allowedFileExtensions:allowedFileExtensions_,//接收的文件后缀
        minFileCount:1,
        uploadAsync:false,/**默认异步上传**/
        showCaption:true,/**是否显示标题**/
        maxFileSize:maxFileSize_,/**单位为kb，如果为0表示不限制文件大小**/
        maxFileCount:1,/**表示允许同时上传的最大文件个数**/
        enctype:'multipart/form-data',
        validateInitialCount:true,
        msgFilesTooMany:"选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        uploadExtraData:function (previewId, index) {    
            var data = {
            	validateparameter:(validateparameter != '' && null != validateparameter && typeof(validateparameter) != "undefined")?validateparameter:'',
            	validateSize:(validateSize != '' && null != validateSize && typeof(validateSize) != "undefined")?validateSize:'',
            	xt_path_absolutek:(xt_path_absolutek != '' && null != xt_path_absolutek && typeof(xt_path_absolutek) != "undefined")?xt_path_relativek:'',
            	xt_path_relativek:(xt_path_relativek != '' && null != xt_path_relativek && typeof(xt_path_relativek) != "undefined")?xt_path_relativek:'',
            	xt_path_urlk:(xt_path_urlk != '' && null != xt_path_urlk && typeof(xt_path_urlk) != "undefined")?xt_path_urlk:''
            };
            return data;
       }
     }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
 		 var obj = eval("(" + data.response + ")");
     	 if(obj.data.jsonID != 0){
     		//赋值
          	$("#"+picid).attr('src',obj.data.jsonValue); 
          	$("#"+fieldid).val(obj.data.jsonID);
      		//关闭上传窗口
          	$('#jehcUploadModal').modal('hide');
          	//并清空上传控件内容
          	$('#jehcFile').val('');
          	 window.parent.toastrBoot(3,obj.data.msg);
         }else{
        	 window.parent.toastrBoot(4,obj.data.msg);
         }
     });
}

/**
 *初始化附件右键
 *isUpAndDelete:表示是否拥有上传和删除功能1是2否（即明细页面使用） 如明细不需要上传 和删除功能
 *validateparameter:校验非法参数组装字符串
 *validateSize:校验大小
 *xt_path_absolutek:平台路径配置中心键（自定义上传绝对路径使用）
 *xt_path_relativek:平台路径配置中心键（自定义上传相对路径使用）
 *xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
**/
function initBFileRight(fieldid,picid,isUpAndDelete,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	if(isUpAndDelete == 2){
		var menu = new BootstrapMenu('#'+picid,{
			  actions:[
			    {
			      name:'下载',
			      iconClass:'fa-download',
			      onClick:function(){
			    	  var xt_attachment_id = $('#fieldid').val();
					  downOrExportB(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
			      }
			    }, 
			    {
			      name:'复制文件地址',
			      iconClass:'fa-clipboard',
				  onClick:function(){
					  var url_path = $("#"+picid)[0].src; 
					  msgTishCallFnBoot("文件地址："+url_path,function(){});
				  }
			    },
			    {
			      name:'查看',
			      iconClass:'fa-file-image-o',
				  onClick:function(){
					  var url_path = $("#"+picid)[0].src; 
					  getBimghw(url_path);
				  }
			    }
			    ]
		});
	}else{
		var menu = new BootstrapMenu('#'+picid,{
			  actions:[
			    {
			      name:'上传',
			      iconClass:'fa-upload',
			      onClick:function(){
			    	  initBUpload(fieldid,picid,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
			      }
			    }, 
			    {
			      name:'下载',
			      iconClass:'fa-download',
			      onClick:function(){
			    	  var xt_attachment_id = $('#fieldid').val();
					  downOrExportB(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
			      }
			    }, 
			    {
			      name:'删除',
			      iconClass:'fa-trash',
			      onClick:function(){
			    	  $("#"+picid).attr('src',bsdefimg); 
				      $('#'+fieldid).val('');
			      }
			    },
			    {
			      name:'复制文件地址',
			      iconClass:'fa-clipboard',
				  onClick:function(){
					  var url_path = $("#"+picid)[0].src; 
					  msgTishCallFnBoot("文件地址："+url_path,function(){});
				  }
			    },
			    {
			      name:'查看',
			      iconClass:'fa-file-image-o',
				  onClick:function(){
					  var url_path = $("#"+picid)[0].src; 
					  getBimghw(url_path);
				  }
			    }
			    ]
		});
	}
	/**
	$("#jehcUploadBtn").click(function(){
		bUpload(fieldid,picid,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
	});
	**/
}

/**
 * 通过iFrame实现类ajax文件下载
 */
function downOrExportB(url){
	/**
	var exportIframe = document.createElement('iframe');
	exportIframe.src = url;
	exportIframe.style.display = "none";
	document.body.appendChild(exportIframe);
	**/
	var frm = document.createElement('form');
    frm.id = 'frmDownOrExport';
    frm.name = grid.getId();
    frm.className = 'x-hidden';
    document.body.appendChild(frm);
    
//    Ext.Ajax.request({
//        disableCaching:true ,
//        url:url,
//        method:'POST',
//        isUpload:true,
//        timeout:600000,//十分钟
//        form:frm,
//        params:{
//        	exportOrDownloadSysFlag:'exportOrDownloadSysFlag'
//        }
//    });
}

function getBimghw(src){
	try{
		var img_url = src+'?'+Date.parse(new Date());
		//创建对象
		var img = new Image();
		//改变图片的src
		img.src = img_url;
		// 加载完成执行
		img.onload = function(){
			var w = img.width;
			var h = img.height;
			if(w>1000){
				w = 800;
			}
			if(h > 400){
				h = 400;
			}
			if(w < 100){
			    w = 260;
			}
			if(h < 100){
				h = 200;
			}
		};
		$('#jehcImagePreModal').modal({backdrop: 'static', keyboard: false});
		$("#jehcImagePre").attr('src',img_url); 
		img.onerror = function(){
			window.parent.toastrBoot(4,"该图片不能预览!");
		};
	}catch(e){
		//非法即不满足图片
		window.parent.toastrBoot(4,"该图片不能预览!");
	}
}
/////////////////////////////////////附件统一上传中心开始///////////////////////////////////
