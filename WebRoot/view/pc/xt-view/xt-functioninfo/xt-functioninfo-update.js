var xtFunctioninfoWinEdit;
var xtFunctioninfoFormEdit;
function updateXtFunctioninfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	if(record.items[0].data.tempObject == '菜单'){
		msgTishi('您选择的是菜单不能修改，请选择功能!');
		return;
	}
	initXtFunctioninfoFormEdit();
	reGetWidthAndHeight();
	xtFunctioninfoWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,
		height:clientHeight,
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
		items:xtFunctioninfoFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtFunctioninfoFormEdit,'../xtFunctioninfoController/updateXtFunctioninfo',grid,xtFunctioninfoWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtFunctioninfoWinEdit.show();
	loadFormData(xtFunctioninfoFormEdit,'../xtFunctioninfoController/getXtFunctioninfoById?xt_functioninfo_id='+ record.items[0].data.id);
}
function initXtFunctioninfoFormEdit(){
	xtFunctioninfoFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'4 5 4 5'
		},
		items:[
		{
			fieldLabel:'ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_functioninfo_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'功能名称',
			xtype:'textfield',
			name:'xt_functioninfoName',
			allowBlank:false,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'功能标题',
			xtype:'textfield',
			name:'xt_functioninfoTitle',
			maxLength:200,
			anchor:'40%'
		},
		{
			fieldLabel:'功能URL',
			xtype:'textfield',
			name:'xt_functioninfoURL',
			maxLength:200,
			anchor:'80%'
		},
		{
			fieldLabel:'所属模块',
			maxLength:32,
			anchor:'40%',
			xtype:'treepicker',
			displayField:'text',
			hiddenName:'xt_menuinfo_id',
			name:'xt_menuinfo_id',
			minPickerHeight:200,
			maxHeight:200,
			editable:false,
			defaults:{
	            rootVisible:false,
	            flex:1
	        },
			store:Ext.create('Ext.data.TreeStore',{
				fields:['id','text'],
				root:{
					text:'一级菜单',
					id:'0',
					expanded:true
				},
				proxy:{
					type:'ajax',
					url:'../xtMenuinfoController/getXtMenuinfoTree',
					reader:{
						type:'json'
					}
				}
			})
		},
		{
			fieldLabel:'前端方法',
			xtype:'textfield',
			name:'xt_functioninfoMethod',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'是否拦截',
	        xtype:'radiogroup',
	        columns:[80,80],
	        items:[
	               {boxLabel:'是',inputValue:'1',name:'xt_functioninfoType'},
	               {boxLabel:'否',inputValue:'0',checked:true,name:'xt_functioninfoType'}
	              ]
		},
		{
			fieldLabel:'数据权限',
			xtype:'radiogroup',
	        columns:[80,80],
	        items:[{boxLabel:'是',inputValue:'0',checked:true,name:'xt_functioninfoIsAuthority'},
	               {boxLabel:'否',inputValue:'1',name:'xt_functioninfoIsAuthority'}
	              ]
		},
		{
			fieldLabel:'是否可用',
			xtype:'radiogroup',
	        columns:[80,80],
	        items:[{boxLabel:'是',inputValue:'0',name:'xt_functioninfoStatus'},
	               {boxLabel:'否',inputValue:'1',checked:true,name:'xt_functioninfoStatus'}
	              ]
		}
		]
	});
}
