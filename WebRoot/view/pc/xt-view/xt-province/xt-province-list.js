var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
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
			fieldLabel:'省份名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_provinceName',
			name:'xt_provinceName',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	store = getGridJsonStore('../xtProvinceController/getXtProvinceListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'省份序列号',
				hidden:true,
				dataIndex:'xt_provinceID'
			},
			{
				header:'省份名称',
				flex:1,
				dataIndex:'xt_provinceName'
			},
			{
				header:'上级编号',
				hidden:true,
				dataIndex:'xt_provinceUpID'
			},
			{
				header:'路径',
				flex:1,
				dataIndex:'xt_provincePath'
			},
			{
				header:'简称',
				flex:1,
				dataIndex:'xt_provinceShortName'
			},
			{
				header:'拼音',
				flex:1,
				dataIndex:'xt_provinceSpell'
			},
			{
				header:'邮编',
				flex:1,
				dataIndex:'xt_provincePostCode'
			},
			{
				header:'操作',
				flex:1,
				columns:[{
					header:'城市信息',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'城市信息',
		                icon:detailIcon,
		                /**style:{background:'blue'},**/
		                handler:function(rec){	
		  					var xt_provinceID = rec.getWidgetRecord().data.xt_provinceID;
		                	document.location.href="../xtCityController/loadXtCity?xt_provinceID="+xt_provinceID;
					    }
		            }
				},{
					header:'操作城市',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'添加城市',
		                icon:addIcon,
		                /**style:{background:'blue'},**/
		                handler:function(rec){
		                	var xt_provinceID = rec.getWidgetRecord().data.xt_provinceID;
		                	addXtCity(xt_provinceID);
		                }
		            }
				}]
			}
		],
		tbar:[
			 {
				text:'添 加',
				tooltip:'添 加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtProvince();
				}
			 },
			 {
				text:'编 辑',
				tooltip:'编 辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtProvince();
				}
			 },
			 {
				text:'删 除',
				tooltip:'删 除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtProvince();
				}
			 }
		],
		bbar:getGridBBar(store)
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtProvince(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_provinceID;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_provinceID){
			xt_provinceID=model.selected.items[i].data.xt_provinceID;
		}else{
			xt_provinceID=xt_provinceID+","+model.selected.items[i].data.xt_provinceID;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_provinceID:xt_provinceID};
			ajaxRequest('../xtProvinceController/delXtProvince',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}

/**查询操作**/
function search(){
	initSearch(store,'../xtProvinceController/getXtProvinceListByCondition',searchForm); 
}