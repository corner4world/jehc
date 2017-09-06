var xtPostWinDetail;
var xtPostFormDetail;
function detailXtPost(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtPostFormDetail();
	xtPostWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		modal:true,
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtPostFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtPostWinDetail.show();
	loadFormData(xtPostFormDetail,'../xtPostController/getXtPostById?xt_post_id='+ record.items[0].data.xt_post_id);
}
function initXtPostFormDetail(){
	xtPostFormDetail = Ext.create('Ext.FormPanel',{
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
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'序列号',
			xtype:'textfield',
			hidden:true,
			name:'xt_post_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门',
			xtype:'treepicker',
			displayField:'text',
			anchor:'40%',
			hiddenName:'xt_departinfo_id',
			name:'xt_departinfo_id',
			minPickerHeight:200,
			maxHeight:200,
			editable:false,
			rootVisible:false, 
			store:Ext.create('Ext.data.TreeStore',{
				fields:['id','text'],
				root:{
					text:'请选择',
					id:'0',
					expanded:true
				},
				proxy:{
					type:'ajax',
					url:'../xtDepartinfoController/getXtDepartinfoTree',
					reader:{
						type:'json'
					}
				}
			})
		},
		{
			fieldLabel:'上级岗位',
			xtype:'treepicker',
			displayField:'text',
			anchor:'40%',
			hiddenName:'xt_post_parentId',
			name:'xt_post_parentId',
			minPickerHeight:200,
			maxHeight:200,
			editable:false,
			rootVisible:false, 
			store:Ext.create('Ext.data.TreeStore',{
				fields:['id','text'],
				root:{
					text:'一级岗位',
					id:'0',
					expanded:true
				},
				proxy:{
					type:'ajax',
					url:'../xtPostController/getXtPostTree',
					reader:{
						type:'json'
					}
				}
			})
		},
		{
			fieldLabel:'岗位名称',
			xtype:'textfield',
			name:'xt_post_name',
			allowBlank:false,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'岗位描述',
			xtype:'textareafield',
			name:'xt_post_desc',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'最大人数',
			xtype:'numberfield',
			name:'xt_post_maxNum',
			anchor:'40%'
		},
		{
			fieldLabel:'岗位级别',
			xtype:'numberfield',
			name:'xt_post_grade',
			anchor:'40%'
		}
		]
	});
}
