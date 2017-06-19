function xtFunctionStr(){
	//加载功能权限
	store.on('load',function(){
		var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
		var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
		var tbar_btn = new Array();
		//头部tbar
		var topDockItems = grid.getDockedItems('toolbar[dock="top"]');
		for(var i = 0 ; i < topDockItems.length; i++){
			var topitems = topDockItems[i].items;
			for(var j = 0; j < topitems.length; j++){
				if(topitems.items[j].xtype == 'button'){
					tbar_btn.push(topitems.items[j]);
				}
			}
		}
		//左边tbar
		var leftDockItems = grid.getDockedItems('toolbar[dock="left"]');
		for(var i = 0; i < leftDockItems.length; i++){
			var leftitems = leftDockItems[i].items;
			for(var j = 0; j < leftitems.length; j++){
				if(leftitems.items[j].xtype == 'button'){
					tbar_btn.push(leftitems.items[j]);
				}
			}
		}
		//右边tbar
		var rightDockItems = grid.getDockedItems('toolbar[dock="right"]');
		for(var i = 0; i < rightDockItems.length; i++){
			var rightitems = rightDockItems[i].items;
			for(var j = 0; j < rightitems.length; j++){
				if(rightitems.items[j].xtype == 'button'){
					tbar_btn.push(rightitems.items[j]);
				}
			}
		}
		
		//底部tbar
//		var bottomDockItems = grid.getDockedItems('toolbar[dock="bottom"]');
//		for(var i = 0; i < bottomDockItems.length; i++){
//			var bottomitems = bottomDockItems[i].items;
//			for(var j = 0; j < bottomitems.length; j++){
//				if(bottomitems.items[j].xtype == 'button'){
//					tbar_btn.push(bottomitems.items[j]);
//				}
//			}
//		}
		var actioncolumn_btnArray = new Array();//自定义按钮
		var actioncolumn_btn = document.getElementsByTagName('input');//获取自定义按钮
		for(var i = 0; i <actioncolumn_btn.length; i++ ){
			if(actioncolumn_btn[i].type == "button"){
				actioncolumn_btnArray.push(actioncolumn_btn[i]);
			}
		}
		var dep_btnArray = new Array();
		//初始化禁用所有Ext组件按钮
		for(var i =0; i < tbar_btn.length; i++){
			Ext.getCmp(tbar_btn[i].id).setDisabled(true);
		}
		//初始化禁用所有自定义按钮
		for(var j =0; j <= actioncolumn_btnArray.length;j++){
			if(typeof(actioncolumn_btnArray[j]) != 'undefined'){
				actioncolumn_btnArray[j].style.display="none"; 
			}
		}
		//恢复tbar中按钮
		for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < tbar_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
					Ext.getCmp(tbar_btn[j].id).setDisabled(false);
					dep_btnArray.push(xtFunctionInfoBtnStrArray[i]);
				}
			}
		}
		//恢复自定义中按钮
		for(var i =0; i < actioncolumn_btnArray.length; i++){
			for(var j =0; j <= dep_btnArray.length;j++){
				if(typeof(actioncolumn_btnArray[i]) != 'undefined'){
					if(dep_btnArray[j] == actioncolumn_btnArray[i].id){
						actioncolumn_btnArray[i].style.display="block"; 
					}
				}
			}
		}
	});
}

//treeGrid加载功能权限模块
function treeGridFunctionStr(){
	var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
	var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
	var tbar_btn = treeGrid.getTopToolbar().findByType('button');//tbar上按钮
	var actioncolumn_btnArray = new Array();//自定义按钮
	var actioncolumn_btn = document.getElementsByTagName('input');//获取自定义按钮
	for(var i = 0; i <actioncolumn_btn.length; i++ ){
		if(actioncolumn_btn[i].type == "button"){
			actioncolumn_btnArray.push(actioncolumn_btn[i]);
		}
	}
	var dep_btnArray = new Array();
	//初始化禁用所有Ext组件按钮
	for(var i =0; i < tbar_btn.length; i++){
		Ext.getCmp(tbar_btn[i].id).setDisabled(true);
	}
	//初始化禁用所有自定义按钮
	for(var j =0; j <= actioncolumn_btnArray.length;j++){
		if(typeof(actioncolumn_btnArray[j]) != 'undefined'){
			actioncolumn_btnArray[j].style.display="none"; 
		}
	}
	//恢复tbar中按钮
	for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
		for(var j = 0; j < tbar_btn.length; j++){
			if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
				Ext.getCmp(tbar_btn[j].id).setDisabled(false);
				dep_btnArray.push(xtFunctionInfoBtnStrArray[i]);
			}
		}
	}
	//恢复自定义中按钮
	for(var i =0; i < actioncolumn_btnArray.length; i++){
		for(var j =0; j <= dep_btnArray.length;j++){
			if(typeof(actioncolumn_btnArray[i]) != 'undefined'){
				if(dep_btnArray[j] == actioncolumn_btnArray[i].id){
					actioncolumn_btnArray[i].style.display="block"; 
				}
			}
		}
	}
}

//根据条件进行权限
function xtFunctionStrByCondition(store,grid){
	//加载功能权限
	store.on('load',function(){
		var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
		var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
		var tbar_btn = grid.getTopToolbar().findByType('button');//tbar上按钮
		var actioncolumn_btnArray = new Array();//自定义按钮
		var actioncolumn_btn = document.getElementsByTagName('input');//获取自定义按钮
		for(var i = 0; i <actioncolumn_btn.length; i++ ){
			if(actioncolumn_btn[i].type == "button"){
				actioncolumn_btnArray.push(actioncolumn_btn[i]);
			}
		}
		var dep_btnArray = new Array();
		//初始化禁用所有Ext组件按钮
		for(var i =0; i < tbar_btn.length; i++){
			Ext.getCmp(tbar_btn[i].id).setDisabled(true);
		}
		//初始化禁用所有自定义按钮
		for(var j =0; j <= actioncolumn_btnArray.length;j++){
			if(typeof(actioncolumn_btnArray[j]) != 'undefined'){
				actioncolumn_btnArray[j].style.display="none"; 
			}
		}
		//恢复tbar中按钮
		for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < tbar_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
					Ext.getCmp(tbar_btn[j].id).setDisabled(false);
					dep_btnArray.push(xtFunctionInfoBtnStrArray[i]);
				}
			}
		}
		//恢复自定义中按钮
		for(var i =0; i < actioncolumn_btnArray.length; i++){
			for(var j =0; j <= dep_btnArray.length;j++){
				if(typeof(actioncolumn_btnArray[i]) != 'undefined'){
					if(dep_btnArray[j] == actioncolumn_btnArray[i].id){
						actioncolumn_btnArray[i].style.display="block"; 
					}
				}
			}
		}
	});
}


//禁用window中tbar按钮
function xtFunctionWindowTbarStr(window){
	var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
	var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
	var tbar_btn = window.getTopToolbar().findByType('button');//tbar上按钮
	var actioncolumn_btnArray = new Array();//自定义按钮
	var actioncolumn_btn = document.getElementsByTagName('input');//获取自定义按钮
	for(var i = 0; i <actioncolumn_btn.length; i++ ){
		if(actioncolumn_btn[i].type == "button"){
			actioncolumn_btnArray.push(actioncolumn_btn[i]);
		}
	}
	var dep_btnArray = new Array();
	//初始化禁用所有Ext组件按钮
	for(var i =0; i < tbar_btn.length; i++){
		Ext.getCmp(tbar_btn[i].id).setDisabled(true);
	}
	//初始化禁用所有自定义按钮
	for(var j =0; j <= actioncolumn_btnArray.length;j++){
		if(typeof(actioncolumn_btnArray[j]) != 'undefined'){
			actioncolumn_btnArray[j].style.display="none"; 
		}
	}
	//恢复tbar中按钮
	for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
		for(var j = 0; j < tbar_btn.length; j++){
			if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
				Ext.getCmp(tbar_btn[j].id).setDisabled(false);
				dep_btnArray.push(xtFunctionInfoBtnStrArray[i]);
			}
		}
	}
	//恢复自定义中按钮
	for(var i =0; i < actioncolumn_btnArray.length; i++){
		for(var j =0; j <= dep_btnArray.length;j++){
			if(typeof(actioncolumn_btnArray[i]) != 'undefined'){
				if(dep_btnArray[j] == actioncolumn_btnArray[i].id){
					actioncolumn_btnArray[i].style.display="block"; 
				}
			}
		}
	}
}
