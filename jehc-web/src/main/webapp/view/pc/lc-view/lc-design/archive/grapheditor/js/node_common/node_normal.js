//一般配置
var nodeNormalForm;
function initNodeNormalForm(cell,flag){
	/**
	  *一般配置信息
	**/
	if(flag == 1){
		nodeNormalForm = new Ext.FormPanel({
	        labelWidth:68,
	        labelAlign:'right',
	        frame:false,
	        border:false,
	        items:[{
	         	xtype:'textfield',  
	            fieldLabel:'名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',  
	           	name:'nodeName',
	           	id:'nodeName',  
	           	anchor:'100%'
	         },{
	         	xtype:'textfield',  
	            fieldLabel:'编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',  
	           	name:'nodeID',
	           	id:'nodeID',  
	           	anchor:'100%'
	         },{
	         	xtype:'checkbox',
				inputValue:"0", 
	            fieldLabel:'是否异步',  
	           	name:'asynchronous',
	           	id:'asynchronous',  
	           	anchor:'100%'
	         },{
	         	xtype:'checkbox',
				inputValue:"0",
	            fieldLabel:'是否专属',  
	           	name:'exclusive',
	           	id:'exclusive',  
	           	anchor:'100%'
	         },{
	       	    xtype:'checkbox',
				inputValue:"1",
	            fieldLabel:'赔偿边界',  
	           	name:'isForCompensation', 
	           	id:'isForCompensation', 
	           	anchor:'100%'
	         },{
	         	xtype:'textareafield',  
	            fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',  
	           	name:'documentation',
	           	id:'documentation',  
	           	anchor:'100%'
	         }]
	    });
	 	var asynchronous = cell.asynchronous;
	 	var exclusive = cell.exclusive;
	 	var isForCompensation = cell.isForCompensation;
	    if(asynchronous == 0){
	    	Ext.getCmp("asynchronous").setValue(true);
	    }else{
	    	Ext.getCmp("asynchronous").setValue(false);
	    }
	    if(exclusive == 0){
	    	Ext.getCmp("exclusive").setValue(true);
	    }else{
	    	Ext.getCmp("exclusive").setValue(false);
	    }
	    if(isForCompensation == 1){
	    	Ext.getCmp("isForCompensation").setValue(true);
	    }else{
	    	Ext.getCmp("isForCompensation").setValue(false);
	    }
	}else if(flag == 2){
		nodeNormalForm = new Ext.FormPanel({
	        labelWidth:68,
	        labelAlign:'right',
	        frame:false,
	        border:false,
	        items:[{
	         	xtype:'textfield',  
	            fieldLabel:'名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',  
	           	name:'nodeName',
	           	id:'nodeName',  
	           	anchor:'100%'
	         },{
	         	xtype:'textfield',  
	            fieldLabel:'编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',  
	           	name:'nodeID',
	           	id:'nodeID',  
	           	anchor:'100%'
	         },{
	         	xtype:'textareafield',  
	            fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',  
	           	name:'documentation',
	           	id:'documentation',  
	           	anchor:'100%'
	         }]
	    });
	}
	 /**取值**/
	var nodeName = cell.value;
 	var nodeID = cell.nodeID;
 	var documentation = cell.documentation;
 	/**赋值**/
 	Ext.getCmp('nodeName').setValue(nodeName);
    Ext.getCmp('nodeID').setValue(nodeID);
    Ext.getCmp('documentation').setValue(documentation);
}

/**赋值功能**/
function node_normal_setvalue(cell,flag){
	var nodeName = Ext.getCmp('nodeName').getValue();
	var nodeID = Ext.getCmp('nodeID').getValue();
	var documentation = Ext.getCmp('documentation').getValue();
	/**赋值**/
 	if(null != nodeName && '' != nodeName){
    	cell.value = nodeName;
    }else{
    	msgTishi('请输入名称!');
    	return false;
    }
    if(null != nodeID && '' != nodeID){
    	cell.nodeID = nodeID;
    }else{
    	msgTishi('请输入编号!');
    	return false;
    }
    if(null != documentation && '' != documentation){
    	cell.documentation = documentation;
    }
	if(flag == 1){
		/**取值**/
	 	var asynchronous = Ext.getCmp('asynchronous');
	 	var exclusive = Ext.getCmp('exclusive');
	 	var isForCompensation = Ext.getCmp('isForCompensation');
	 	/**赋值**/
	    if(asynchronous.checked==true){
	    	cell.asynchronous = Ext.getCmp('asynchronous').inputValue;
	    }else{
	    	cell.asynchronous = 1;
	    }
	    if(exclusive.checked==true){
	    	cell.exclusive = Ext.getCmp('exclusive').inputValue; 
	    }else{
	    	cell.exclusive = 1;
	    }
	    if(null != documentation && '' != documentation){
	    	cell.documentation = documentation;
	    }
   		if(isForCompensation.checked==true){
	    	cell.isForCompensation = 1;
	    }else{
	    	cell.isForCompensation = 2;
	    }
	}else if(flag == 2){
	 	
	}
}