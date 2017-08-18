var bCategoryWinDetail;
var bCategoryFormDetail;
function detailBCategory(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBCategoryFormDetail();
	bCategoryWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bCategoryFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bCategoryWinDetail.show();
	loadFormData(bCategoryFormDetail,'../bCategoryController/getBCategoryById?b_category_id='+ record.items[0].data.id);
}
function initBCategoryFormDetail(){
	bCategoryFormDetail = Ext.create('Ext.FormPanel',{
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
		items:[
		{
			fieldLabel:'分类编号',
			xtype:'textfield',
			hidden:true,
			name:'b_category_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'分类名称',
			xtype:'textfield',
			name:'b_category_name',
			allowBlank:false,
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'displayfield',
			format:'Y-m-d H:i:s',
			name:'b_category_ctime',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'displayfield',
			format:'Y-m-d H:i:s',
			name:'b_category_mtime',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:"combo",
            store:[["0","可用"],["1","禁用"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_category_status',
			name:'b_category_status',
			anchor:'100%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'displayfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
