var xtKwordsWinDetail;
var xtKwordsFormDetail;
function detailXtKwords(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtKwordsFormDetail();
	xtKwordsWinDetail = Ext.create('Ext.Window',{
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
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtKwordsFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtKwordsWinDetail.show();
	
	loadFormData(xtKwordsFormDetail,'../xtKwordsController/getXtKwordsById?xt_kwords_id='+ record.items[0].data.xt_kwords_id);
}
function initXtKwordsFormDetail(){
	xtKwordsFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'平台关键字编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_kwords_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'关键字内容',
			xtype:'textareafield',
			name:'xt_kwords_content',
			maxLength:65535,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_kwords_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_kwords_mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_kwords_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_kwords_status',
			valueField:'value',
			displayField:'text',
			name:'xt_kwords_status',
			maxLength:10,
			anchor:'40%'
		}
		]
	});
}
