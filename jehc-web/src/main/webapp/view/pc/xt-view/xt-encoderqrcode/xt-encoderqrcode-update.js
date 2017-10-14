var xtEncoderqrcodeWinEdit;
var xtEncoderqrcodeFormEdit;
function updateXtEncoderqrcode(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtEncoderqrcodeFormEdit();
	xtEncoderqrcodeWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'编辑信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtEncoderqrcodeFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtEncoderqrcodeFormEdit,'../xtEncoderqrcodeController/updateXtEncoderqrcode',grid,xtEncoderqrcodeWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtEncoderqrcodeWinEdit.show();
	loadFormData(xtEncoderqrcodeFormEdit,'../xtEncoderqrcodeController/getXtEncoderqrcodeById?xt_encoderqrcode_id='+ record.items[0].data.xt_encoderqrcode_id);
	Ext.getCmp('pic').getEl().dom.src = record.items[0].data.hssources_base_url+record.items[0].data.xt_attachmentPath;
}
function initXtEncoderqrcodeFormEdit(){
	xtEncoderqrcodeFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'二维码编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_encoderqrcode_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_encoderqrcode_title',
			allowBlank:false,
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'链接地址',
			xtype:'textfield',
			name:'xt_encoderqrcode_url',
			allowBlank:false,
			maxLength:1000,
			anchor:'100%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'xt_encoderqrcode_content',
			maxLength:500,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_encoderqrcode_ctime',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'图片编号',
			xtype:'textfield',
			name:'xt_attachment_id',
			allowBlank:false,
			maxLength:32,
			hidden:true,
			anchor:'100%'
		},
		{
		    xtype:'box',//或者xtype:'component',  
			fieldLabel:"图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片",
			id:'pic',
			padding:'2 5 4 80',
			autoEl:{  
		        tag:'img',			    
		        src:basePath+"/deng/images/default/default.jpg"
		    } 
		}
		]
	});
}
