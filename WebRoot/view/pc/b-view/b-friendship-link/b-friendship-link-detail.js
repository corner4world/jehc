var bFriendshipLinkWinDetail;
var bFriendshipLinkFormDetail;
function detailBFriendshipLink(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBFriendshipLinkFormDetail();
	bFriendshipLinkWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
//		maximized:true,
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
		items:bFriendshipLinkFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bFriendshipLinkWinDetail.show();
	loadFormData(bFriendshipLinkFormDetail,'../bFriendshipLinkController/getBFriendshipLinkById?b_friendship_link_id='+ record.items[0].data.b_friendship_link_id);
}
function initBFriendshipLinkFormDetail(){
	bFriendshipLinkFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'编号',
			xtype:'textfield',
			hidden:true,
			name:'b_friendship_link_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',
			xtype:'textfield',
			name:'b_friendship_link_name',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			fieldLabel:'链接地址',
			xtype:'textfield',
			name:'b_friendship_link_url',
			maxLength:500,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_friendship_link_ctime',
			anchor:'25%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_friendship_link_mtime',
			anchor:'25%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'25%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			name:'b_friendship_link_status',
			xtype:"combo",
            store:[["0","正常"],["1","禁用"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_friendship_link_status',
			allowBlank:false,
			maxLength:2,
			anchor:'25%'
		},
		{
			fieldLabel:'排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序',
			xtype:'numberfield',
			value:'0',
			name:'b_friendship_link_sort',
			anchor:'25%'
		}
		]
	});
}
