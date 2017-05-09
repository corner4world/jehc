var store;
var grid;
var parm;
Ext.onReady(function(){
	var xt_cityID = document.getElementById('xt_cityID').value;
	var xt_provinceID = document.getElementById('xt_provinceID').value;
	store = getGridJsonStore('../xtDistrictController/getXtDistrictListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
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
				header:'区县列序号',
				flex:1,
				hidden:true,
				dataIndex:'xt_districtID'
			},
			{
				header:'县区名称',
				flex:1,
				dataIndex:'xt_districtName'
			},
			{
				header:'路径',
				flex:1,
				dataIndex:'xt_districtPath'
			},
			{
				header:'简写',
				flex:1,
				dataIndex:'xt_districtShortName'
			},
			{
				header:'拼音',
				flex:1,
				dataIndex:'xt_districtSpell'
			},
			{
				header:'邮编',
				flex:1,
				dataIndex:'xt_districtPostCode'
			}
		],
		tbar:[
			 {
				tooltip:'添 加',
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtDistrict(xt_cityID);
				}
			 },
			 {
				tooltip:'编 辑',
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtDistrict();
				}
			 },
			 {
				tooltip:'删 除',
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtDistrict();
				}
			 },
			 {
				tooltip:'返回城市列表',
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../xtCityController/loadXtCity?xt_provinceID="+xt_provinceID;
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
	parm = {start:getCP(store)[0],limit:getCP(store)[1],xt_cityID:xt_cityID};
	beforeloadstore(parm);
	load(grid,parm);
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
});
/**删除操作**/
function delXtDistrict(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_districtID;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_districtID){
			xt_districtID=model.selected.items[i].data.xt_districtID;
		}else{
			xt_districtID=xt_districtID+","+model.selected.items[i].data.xt_districtID;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_districtID:xt_districtID};
			ajaxRequest('../xtDistrictController/delXtDistrict',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
