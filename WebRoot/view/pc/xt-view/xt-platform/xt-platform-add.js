var xtPlatformWinAdd;
var xtPlatformFormAdd;
function addXtPlatform(){
	initXtPlatformFormAdd();
	xtPlatformWinAdd = Ext.create('Ext.Window',{
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
		items:xtPlatformFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				var jsonArray = [];
				for(var i=0;i<xt_platform_feedback_grid.getStore().getCount();i++){
					jsonArray.push(xt_platform_feedback_grid.store.getAt(i).data); 
				}
				var xt_Platform_FeedbackList=Ext.encode(jsonArray);
			    Ext.getCmp('xt_Platform_FeedbackList').setValue(xt_Platform_FeedbackList);
				submitForm(xtPlatformFormAdd,'../xtPlatformController/addXtPlatform',grid,xtPlatformWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtPlatformWinAdd.show();
	
}
function initXtPlatformFormAdd(){
	init();
	xtPlatformFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		title:'<font color=#fff>基本信息</font>',
		/**自定义样式**/
		header:{
			cls:'x-panel-header-defined'
		},
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
			fieldLabel:'反馈信息',
			xtype:'textfield',
			hidden:true,
			id:'xt_Platform_FeedbackList',
			name:'xt_Platform_FeedbackList',
			anchor:'80%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_platform_title',
			maxLength:100,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_platform_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_platform_status',
			valueField:'value',
			displayField:'text',
			name:'xt_platform_status',
			maxLength:10,
			anchor:'40%'
		},
		{
			fieldLabel:'注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备',
			xtype:'textareafield',
			name:'xt_platform_remark',
			maxLength:500,
			anchor:'100%'
		},
		xt_platform_feedback_grid
		]
	});
}
var xt_platform_feedback_grid;
var xt_platform_feedback_store;
function init(){
	var xt_platform_id;
	xt_platform_feedback_store = getGridJsonStore('../xtPlatformFeedbackController/getXtPlatformFeedbackListByCondition?xt_platform_id='+xt_platform_id,[]);
	xt_platform_feedback_grid = Ext.create('Ext.grid.Panel',{
			store:xt_platform_feedback_store,
			requires:[
		        'Ext.grid.plugin.CellEditing',
		        'Ext.form.field.Text',
		        'Ext.form.field.TextArea',
		        'Ext.toolbar.TextItem'
		    ],
			columnLines:true,
	        multiSelect:true,
	        border:true,
         	scrollable:true,  
         	scrollable:'x',
         	scrollable:'y',
         	region:'center',
         	title:'<font color=#fff>反馈信息</font>',
	        plugins:{
				ptype:'cellediting',
	        	clicksToEdit:1
			},
			viewConfig:{
				emptyText:'暂无数据',
				stripeRows:true
			},
			/**自定义样式**/
			header:{
				cls:'x-panel-header-defined'
			},
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
					header:'主键',
					dataIndex:'xt_platform_feedback_id',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'备注',
					dataIndex:'xt_platform_feedback_remark',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'状态',
					dataIndex:'xt_platform_feedback_status',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				xt_platform_feedback_grid.down('#del_button').setDisabled(!records.length);
			},
			bbar:getGridBBar(xt_platform_feedback_store),
			tbar:[{
				  text:'增 加',
				  icon:addIcon,
				  handler:function(){
		            var model = Ext.create(xt_platform_feedback_grid.getStore().model);  
				    /**model.set('','');**/  
				    xt_platform_feedback_grid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  icon:delIcon,
				  id:'del_button',
				  handler:function(){
					 var model = xt_platform_feedback_grid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           xt_platform_feedback_grid.getStore().remove(xt_platform_feedback_grid.getSelectionModel().getSelection());  
				           xt_platform_feedback_grid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	xt_platform_feedback_store.load(); 
}	
