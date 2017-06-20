//根据条件进行权限
function xtFunctionStrByCondition(store,grid,contextmenu){
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
				if(topitems.items[j].xtype == 'button' && topitems.items[j].authUneed != true){
					tbar_btn.push(topitems.items[j]);
				}
			}
		}
		//左边tbar
		var leftDockItems = grid.getDockedItems('toolbar[dock="left"]');
		for(var i = 0; i < leftDockItems.length; i++){
			var leftitems = leftDockItems[i].items;
			for(var j = 0; j < leftitems.length; j++){
				if(leftitems.items[j].xtype == 'button' && topitems.items[j].authUneed != true){
					tbar_btn.push(leftitems.items[j]);
				}
			}
		}
		//右边tbar
		var rightDockItems = grid.getDockedItems('toolbar[dock="right"]');
		for(var i = 0; i < rightDockItems.length; i++){
			var rightitems = rightDockItems[i].items;
			for(var j = 0; j < rightitems.length; j++){
				if(rightitems.items[j].xtype == 'button' && topitems.items[j].authUneed != true){
					tbar_btn.push(rightitems.items[j]);
				}
			}
		}
		//初始化禁用所有Ext组件按钮
		for(var i =0; i < tbar_btn.length; i++){
//			tbar_btn[i].setDisabled(true);//禁用按钮
//			tbar_btn[i].setVisible(false);//隐藏按钮
			tbar_btn[i].setVisible(false);
		}
		//恢复tbar中按钮
		for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < tbar_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
					tbar_btn[j].setVisible(true);//显示按钮
				}
			}
		}
		//右键菜单权限禁用
		initcontextmenuforData(grid,contextmenu);
	});
}


//禁用window中tbar按钮
function xtFunctionWindowTbarStr(window){
	var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
	var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
	var tbar_btn = new Array();
	//头部tbar
	var topDockItems = window.getDockedItems('toolbar[dock="top"]');
	for(var i = 0 ; i < topDockItems.length; i++){
		var topitems = topDockItems[i].items;
		for(var j = 0; j < topitems.length; j++){
			if(topitems.items[j].xtype == 'button' && topitems.items[j].id != 'moretext' && topitems.items[j].id != 'search' && topitems.items[j].id != 'rest'){
				tbar_btn.push(topitems.items[j]);
			}
		}
	}
	//左边tbar
	var leftDockItems = window.getDockedItems('toolbar[dock="left"]');
	for(var i = 0; i < leftDockItems.length; i++){
		var leftitems = leftDockItems[i].items;
		for(var j = 0; j < leftitems.length; j++){
			if(leftitems.items[j].xtype == 'button' && leftitems.items[j].id != 'moretext' && topitems.items[j].id != 'search' && topitems.items[j].id != 'rest'){
				tbar_btn.push(leftitems.items[j]);
			}
		}
	}
	//右边tbar
	var rightDockItems = window.getDockedItems('toolbar[dock="right"]');
	for(var i = 0; i < rightDockItems.length; i++){
		var rightitems = rightDockItems[i].items;
		for(var j = 0; j < rightitems.length; j++){
			if(rightitems.items[j].xtype == 'button' && rightitems.items[j].id != 'moretext' && topitems.items[j].id != 'search' && topitems.items[j].id != 'rest'){
				tbar_btn.push(rightitems.items[j]);
			}
		}
	}
	
	//底部tbar
	var bottomDockItems = window.getDockedItems('toolbar[dock="bottom"]');
	for(var i = 0; i < bottomDockItems.length; i++){
		var bottomitems = bottomDockItems[i].items;
		for(var j = 0; j < bottomitems.length; j++){
			if(bottomitems.items[j].xtype == 'button' && bottomitems.items[j].id != 'moretext' && topitems.items[j].id != 'search' && topitems.items[j].id != 'rest'){
				tbar_btn.push(bottomitems.items[j]);
			}
		}
	}
	//初始化禁用所有Ext组件按钮
	for(var i =0; i < tbar_btn.length; i++){
//		tbar_btn[i].setDisabled(true);//禁用按钮
//		tbar_btn[i].setVisible(false);//隐藏按钮
		tbar_btn[i].setVisible(false);
	}
	//恢复tbar中按钮
	for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
		for(var j = 0; j < tbar_btn.length; j++){
			if(xtFunctionInfoBtnStrArray[i] == tbar_btn[j].id ){
//				tbar_btn[i].setDisabled(false);//解除禁用按钮
				tbar_btn[j].setVisible(true);//显示按钮
			}
		}
	}
}

//禁用右键相关按钮 及恢复
function initcontextmenuforData(grid,contextmenu){
	var contextmenu_btn = new Array();
	var contextmenu_btn_array = contextmenu.items.items;
	var xtFunctionInfoBtnStr = document.getElementById('xtFunctionInfoBtnStr').value;//系统功能字符串
	var xtFunctionInfoBtnStrArray = xtFunctionInfoBtnStr.split(",");//系统功能数组
	for(var i = 0; i < contextmenu_btn_array.length; i++){
		if(contextmenu_btn_array[i].xtype == 'menuitem' && contextmenu_btn_array[i].authUneed != true){
			contextmenu_btn.push(contextmenu_btn_array[i]);
		}
	}
	//禁用右键相关按钮
	//初始化禁用所有Ext组件按钮
	grid.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault();
		for(var i =0; i < contextmenu_btn.length; i++){
			contextmenu_btn[i].setDisabled(true);//禁用按钮
		}
		contextmenu.showAt(e.getXY());
	});
	grid.on("headercontextmenu", function(view,colIndex,e){
		grid.getSelectionModel().clearSelections();
		grid.getView().refresh();
    	e.preventDefault(); 
    	for(var i =0; i < contextmenu_btn.length; i++){
    		contextmenu_btn[i].setDisabled(true);//禁用按钮
    	}
		contextmenu.showAt(e.getXY());
	});
	grid.on("containercontextmenu", function(view,e,opt){
		grid.getSelectionModel().clearSelections();
		grid.getView().refresh();
    	e.preventDefault(); 
    	for(var i =0; i < contextmenu_btn.length; i++){
    		contextmenu_btn[i].setDisabled(true);//禁用按钮
    	}
		contextmenu.showAt(e.getXY());
	});
	
	
	//恢复
	grid.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault();
		for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < contextmenu_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i]+'Item' == contextmenu_btn[j].id ){
					contextmenu_btn[j].setDisabled(false);//解禁按钮
				}
			}
		}
		contextmenu.showAt(e.getXY());
	});
	grid.on("headercontextmenu", function(view,colIndex,e){
		grid.getSelectionModel().clearSelections();
		grid.getView().refresh();
    	e.preventDefault(); 
    	for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < contextmenu_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i]+'Item' == contextmenu_btn[j].id ){
					contextmenu_btn[j].setDisabled(false);//解禁按钮
				}
			}
		}
		contextmenu.showAt(e.getXY());
	});
	grid.on("containercontextmenu", function(view,e,opt){
		grid.getSelectionModel().clearSelections();
		grid.getView().refresh();
    	e.preventDefault(); 
    	for(var i = 0; i < xtFunctionInfoBtnStrArray.length;i++){
			for(var j = 0; j < contextmenu_btn.length; j++){
				if(xtFunctionInfoBtnStrArray[i]+'Item' == contextmenu_btn[j].id ){
					contextmenu_btn[j].setDisabled(false);//解禁按钮
				}
			}
		}
		contextmenu.showAt(e.getXY());
	});
}
