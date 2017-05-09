var themeCombo = Ext.form.ComboBox({
	xtype:'combo',  
	id:'themecombo',
	//fieldLabel:'切换主题',
    emptyText:'切换肤色',
    //labelWidth:70,
    anchor:'100%',
    displayField:'name',  
    valueField:'value',  
    queryMode:'local',  
    editable:false,
    store:Ext.create('Ext.data.Store',{  
       fields:['value','name'],  
       data:[ 
       		['triton','清爽主题'], 
       		['crisp','默认主题'],  
            ['crisp-touch','默认触屏']
            /**,
           	['neptune','蓝色主题' ],  
            ['neptune-touch','蓝色触屏' ]
            ['classic','经典主题' ],  
            ['gray','灰色主题' ]  
            **/
       ]  
    }),  
    listeners:{  
       select:function(combo){  
         var name = combo.getValue();
       	 var date=new Date();
       	 date.setTime(date.getTime() + 365*24*3066*1000);
       	 Ext.Msg.confirm("提示", "更换主题之后平台将重新刷新整个页面,确定要更换主题吗？", function(btn) {
			if(btn == 'yes'){
		       	 window.document.cookie="css=" + name + ";expires=" + date.toGMTString();
		       	 window.document.cookie="css=" + name + ";expires=" + date.toGMTString()+";path=/";
				 window.location.href=basePath+"/index/index.html";
			}
		 });
       }  
    },
	initEvents:function(){
		 var theme = Ext.getCmp('themecombo').getValue();
		 var cookiesArr = window.parent.document.cookie.split(";");
		 var cssName = "";
		 for(var i=0; i<cookiesArr.length; i++){
		 	var arr = cookiesArr[i].split("=");
		 	var values = arr[0];
		 	if('undefind' != typeof(arr[0])){
		 		values = Ext.util.Format.trim(values);
		 	}
	    	if(values == 'css'){
	    		cssName = arr[1];
	        	break;
		    }
		 };
		 if(cssName != "" && cssName != null){
		 	Ext.getCmp('themecombo').setValue(cssName);
		}else{
		 	Ext.getCmp('themecombo').setValue('triton');
		}
	 }
});

/**初始化调用**/
Ext.onReady(function(){
	/**
	var cookiesArr = window.parent.document.cookie.split(";");
	var cssName = "";
	for(var i=0; i<cookiesArr.length; i++){
		var arr = cookiesArr[i].split("=");
	 	var values = arr[0];
	 	
	 	if('undefind' != typeof(arr[0])){
	 		values = Ext.util.Format.trim(values);
	 	}
	   	if(values == 'css'){
	   		cssName = arr[1];
	       	break;
	    }
	};
	if(cssName != "" && cssName != null){
		window.document.getElementsByTagName("link")[1].href = basePath+'/deng/source/plugins/e5/packages/ext-theme-'+cssName+'/build/resources/ext-theme-'+cssName+'-all.css';
	}else{
		window.document.getElementsByTagName("link")[1].href = basePath+'/deng/source/plugins/e5/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css';
	}
	**/
});