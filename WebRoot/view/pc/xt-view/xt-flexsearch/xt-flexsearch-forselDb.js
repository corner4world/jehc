Ext.onReady(function(){
	reGetWidthAndHeight();
	var xt_dbinfo_id_combo = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtDbinfoController/getXtDbinfoListByCondition',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['xt_dbinfo_id', 'xt_dbinfoName'],
		autoLoad:true 
	});
	var xtDbinfoPanel = Ext.create('Ext.Window',{
		xtype:'form',
		waitMsgTarget:true,
		renderTo:Ext.getBody(),
		autoScroll:true,
		draggable:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'right',
			flex:1,
			margin:'4 5 4 5'
		},
		/**
		region:'center',
		layout:{
		    type:'hbox',
		    align:'middle ',
		    pack:'center'
		},
		**/
		collapsible:true,
		title:'查询选择器则需要选择指定数据库',
		titleAlign:'left',
		width:500,
		closable:false,
		buttonAlign:'right',
		buttons:[{
			text:'确认',
			handler:function(){
				var xt_dbinfo_id = gValue('xt_dbinfo_id');
				window.location.href="../xtFlexSearchController/loadXtFlexSearch?xt_dbinfo_id="+xt_dbinfo_id;
			}
		}],
		items:[{
				allowBlank:false,
				msgTarget:'side',/**qtip、title、under、side、none**/
				emptyText:'请选择数据库',
				fieldLabel:'数据库名',
				xtype:'combo',
				store:xt_dbinfo_id_combo,
				mode:'local',
				triggerAction:'all',
				editable:false,
				hiddenName:'xt_dbinfo_id',
				valueField:'xt_dbinfo_id',
				displayField:'xt_dbinfoName',
				id:'xt_dbinfo_id',
				name:'xt_dbinfo_id',
				listeners:{
	                select:function(combo,records,options){
	                	sValue('ip',"<font color=red>"+records.data.xt_dbinfoIp+"</font>");
	                	sValue('port',"<font color=red>"+records.data.xt_dbinfoPort+"</font>");
	                	sValue('xt_dbinfoUName',"<font color=red>"+records.data.xt_dbinfoUName+"</font>");
	                	sValue('xt_dbinfoPwd',"xxx xxx</font>");
	                	sValue('dbType',"<font color=red>"+records.data.xt_dbinfoType+"</font>");
	                }
	            }
			},
			{
				xtype:'displayfield',
				fieldLabel:'地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址',
				id:'ip',
				anchor:'100%'
			},
			{
				xtype:'displayfield',
				fieldLabel:'端&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;口',
				id:'port',
				anchor:'100%'
			},
			{
				xtype:'displayfield',
				fieldLabel:'用&nbsp;户&nbsp;&nbsp;名',
				id:'xt_dbinfoUName',
				anchor:'100%'
			},
			{
				xtype:'displayfield',
				fieldLabel:'密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码',
				id:'xt_dbinfoPwd',
				anchor:'100%'
			},
			{
				xtype:'displayfield',
				fieldLabel:'类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型',
				id:'dbType',
				anchor:'100%'
			}]
	}).show();
});
