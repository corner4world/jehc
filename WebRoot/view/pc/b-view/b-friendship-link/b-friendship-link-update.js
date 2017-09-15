var bFriendshipLinkWinEdit;
var bFriendshipLinkFormEdit;
function updateBFriendshipLink(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initBFriendshipLinkFormEdit();
	bFriendshipLinkWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
//		maximized:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'编辑信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bFriendshipLinkFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(bFriendshipLinkFormEdit,'../bFriendshipLinkController/updateBFriendshipLink',grid,bFriendshipLinkWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bFriendshipLinkWinEdit.show();
	loadFormData(bFriendshipLinkFormEdit,'../bFriendshipLinkController/getBFriendshipLinkById?b_friendship_link_id='+ record.items[0].data.b_friendship_link_id);
}
function initBFriendshipLinkFormEdit(){
	bFriendshipLinkFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
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
