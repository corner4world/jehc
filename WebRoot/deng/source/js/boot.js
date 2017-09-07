//bootstrap提示
function toastrBoot(flag,msg){
	toastr.options = {
	  "closeButton":true,//是否显示关闭按钮
	  "debug":false,//是否使用debug模式
	  "positionClass":"toast-top-center",//弹出窗的位置
	  "onclick":null,
	  "showDuration":"1000",//显示的动画时间
	  "hideDuration":"1000",//消失的动画时间
	  "timeOut":"5000",//展现时间
	  "extendedTimeOut":"1000",//加长展示时间
	  "showEasing":"swing",//显示时的动画缓冲方式
	  "hideEasing":"linear",//消失时的动画缓冲方式
	  "showMethod":"fadeIn",//显示时的动画方式
	  "hideMethod":"fadeOut"//消失时的动画方式
	}
	if(flag == 1){//info
		toastr.info(msg, '提示');
	}else if(flag == 2){//
		toastr.warning(msg,'提示')//显示一个警告
	}else if(flag == 3){
		toastr.success(msg, '提示')//显示一个成功
	}else if(flag == 4){
		toastr.error(msg, '提示')//显示错误
	}else{
		toastr.info(msg, '提示');//缺省info
	}
	//清除当前的列表
	toastr.clear();
	//参数设置，若用默认值可以省略以下面代
    /*
	 toastr.options = {
	  "closeButton":true,//是否显示关闭按钮
	  "debug":false,//是否使用debug模式
	  "positionClass":"toast-top-center",//弹出窗的位置
	  "onclick":null,
	  "showDuration":"1000",//显示的动画时间
	  "hideDuration":"1000",//消失的动画时间
	  "timeOut":"5000",//展现时间
	  "extendedTimeOut":"1000",//加长展示时间
	  "showEasing":"swing",//显示时的动画缓冲方式
	  "hideEasing":"linear",//消失时的动画缓冲方式
	  "showMethod":"fadeIn",//显示时的动画方式
	  "hideMethod":"fadeOut"//消失时的动画方式
	}
	**/
}

//普通提示
function msgTishBoot(msg){
	zeroModal.alert(msg);
}
//普通提示带确认框按钮并返回事件
function msgTishCallFnBoot(msg,fn){
	zeroModal.alert({
		content:'提示',
		contentDetail:msg,
		okFn:function(){
			fn();
		}
	});
}
