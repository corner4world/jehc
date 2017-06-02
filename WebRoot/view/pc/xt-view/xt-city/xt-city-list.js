var store;
var grid;
var parm;
Ext.onReady(function(){
	var xt_provinceID = document.getElementById('xt_provinceID').value;
	store = getGridJsonStore('../xtCityController/getXtCityListByCondition',{xt_provinceID:xt_provinceID});
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
				header:'城市序列号',
				hidden:true,
				dataIndex:'xt_cityID'
			},
			{
				header:'市城名称',
				flex:1,
				dataIndex:'xt_cityName'
			},
			{
				header:'上级编号',
				hidden:true,
				dataIndex:'xt_cityUpID'
			},
			{
				header:'路径',
				flex:1,
				dataIndex:'xt_cityPath'
			},
			{
				header:'简称',
				dataIndex:'xt_cityShortName'
			},
			{
				header:'拼音',
				flex:1,
				dataIndex:'xt_citySpell'
			},
			{
				header:'邮编',
				flex:1,
				dataIndex:'xt_cityPostCode'
			},
			{
				header:'操作',
				flex:1,
				columns:[{
					header:'区县信息',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'区县信息',
		                icon:detailIcon,
		                /**style:{background:'blue'},**/
		                handler:function(rec){
		  					var xt_cityID = rec.getWidgetRecord().data.xt_cityID;
		                	document.location.href="../xtDistrictController/loadXtDistrict?xt_cityID="+xt_cityID+"&xt_provinceID="+xt_provinceID;
					    }
		            }
				},{
					header:'操作区县',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'添加区县',
		                icon:addIcon,
		                /**style:{background:'blue'},**/
		                handler:function(rec){
		                	var xt_cityID = rec.getWidgetRecord().data.xt_cityID;
		                	addXtDistrict(xt_cityID);
		                }
		            }
				}]
			}
		],
		tbar:[
			 {
				tooltip:'添 加',
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtCity(xt_provinceID);
				}
			 },
			 {
				tooltip:'编 辑',
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtCity();
				}
			 },
			 {
				tooltip:'删 除',
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtCity();
				}
			 },
			 {
				tooltip:'返回省份列表',
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../xtProvinceController/loadXtProvince";
				}
			 }
		],
		bbar:getGridBBar(store)
	});
	/**特例**/
	parm = {start:getCP(store)[0],limit:getCP(store)[1],xt_provinceID:xt_provinceID};
	beforeloadstore(parm);
	load(grid,parm);
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
});
/**删除操作**/
function delXtCity(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_cityID;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_cityID){
			xt_cityID=model.selected.items[i].data.xt_cityID;
		}else{
			xt_cityID=xt_cityID+","+model.selected.items[i].data.xt_cityID;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_cityID:xt_cityID};
			ajaxRequest('../xtCityController/delXtCity',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
