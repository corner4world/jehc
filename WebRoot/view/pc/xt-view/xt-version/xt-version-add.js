var xtVersionWinAdd;
var xtVersionFormAdd;
function addXtVersion(){
	initXtVersionFormAdd();
	xtVersionWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'添加信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtVersionFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtVersionFormAdd,'../xtVersionController/addXtVersion',grid,xtVersionWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtVersionWinAdd.show();
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	initFileRight('xt_attachment_id','xt_attachment_id_pic',1,1);
	/**初始化附件右键菜单结束**/

}
function initXtVersionFormAdd(){
	xtVersionFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'版本名称',
			xtype:'textfield',
			name:'xt_version_name',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'版本描述',
			xtype:'textareafield',
			name:'xt_version_remark',
			maxLength:2000,
			anchor:'100%'
		},
		{
			fieldLabel:'附件编号',
			xtype:'textfield',
			hidden:true,
			id:'xt_attachment_id',
			name:'xt_attachment_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'附件',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				width:80, 
				height:60, 
				id:'xt_attachment_id_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					/** 不采用右键时候直接用点击事件触发
					onclick:"optupload('xt_attachment_id','xt_attachment_id_pic',1)",
					**/
					src:bsdefimg
				}
			}
		}
		]
	});
}
