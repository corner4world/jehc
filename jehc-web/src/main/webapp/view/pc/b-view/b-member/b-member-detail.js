var bMemberWinDetail;
var bMemberFormDetail;
function detailBMember(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBMemberFormDetail();
	xtCityList.load({params:{parentId:record.items[0].data.xt_provinceID}});
	var parm = {parentId:record.items[0].data.xt_provinceID};
    beforeloadstoreByStore(xtCityList,parm);
	xtDistrictList.load({params:{parentId:record.items[0].data.xt_cityID}});
	parms = {parentId:record.items[0].data.xt_cityID};
    beforeloadstoreByStore(xtDistrictList,parms);
    
	bMemberWinDetail = Ext.create('Ext.Window',{
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
		items:bMemberFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bMemberWinDetail.show();
	loadFormData(bMemberFormDetail,'../bMemberController/getBMemberById?b_member_id='+ record.items[0].data.b_member_id);
}
function initBMemberFormDetail(){
	bMemberFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'会员编号',
			xtype:'textfield',
			hidden:true,
			name:'b_member_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'会员名称',
			xtype:'textfield',
			name:'b_member_name',
			allowBlank:false,
			maxLength:64,
			anchor:'25%'
		},
		{
			fieldLabel:'性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别',
			name:'b_member_sex',
			xtype:"combo",
            store:[["0","男"],["1","女"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_member_sex',
			allowBlank:false,
			maxLength:2,
			anchor:'25%'
		},
		{
			fieldLabel:'会员电话',
			xtype:'textfield',
			name:'b_member_tel',
			maxLength:32,
			anchor:'25%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			name:'b_member_status',
			xtype:"combo",
            store:[["0","可用"],["1","禁用"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_member_status',
			allowBlank:false,
			maxLength:2,
			anchor:'25%'
		},
		{
			fieldLabel:'等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级',
			xtype:'numberfield',
			value:'0',
			name:'b_member_level',
			anchor:'25%'
		},
		{
			fieldLabel:'省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份',
			name:'xt_provinceID',
			xtype:"combo",
			store:xtProvinceList, 
            emptyText:'请选择',  
            mode:'local',  
            triggerAction:'all',  
            valueField:'ID',  
		    displayField:'NAME',  
            editable:false, 
			allowBlank:false,
			maxLength:32,
			anchor:'60%',
			listeners:{
                 select:function(combo,records,options){
                 }
             }
		},
		{
			fieldLabel:'城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市',
			id:'xt_cityID',
			name:'xt_cityID',
			xtype:"combo",
			store:xtCityList, 
            emptyText:'请选择',  
            mode:'local',  
            triggerAction:'all',  
            valueField:'ID',  
		    displayField:'NAME',  
            editable:false, 
			allowBlank:false,
			maxLength:32,
			anchor:'60%',
			listeners:{
                 select:function(combo,records,options){
                 }
             }
		},
		{
			fieldLabel:'区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县',
			xtype:'textfield',
			id:'xt_districtID',
			name:'xt_districtID',
			xtype:"combo",
			store:xtDistrictList, 
            emptyText:'请选择',  
            mode:'local',  
            triggerAction:'all',  
            valueField:'ID',  
		    displayField:'NAME',  
            editable:false, 
			allowBlank:false,
			maxLength:32,
			anchor:'60%'
		},
		{
			fieldLabel:'详细地址',
			xtype:'textfield',
			name:'b_member_address',
			maxLength:200,
			anchor:'60%'
		},
		{
			fieldLabel:'邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱',
			xtype:'textfield',
			name:'b_member_email',
			maxLength:100,
			anchor:'40%'
		},
		{
			fieldLabel:'qq&nbsp;&nbsp;账号',
			xtype:'textfield',
			name:'b_member_qq',
			maxLength:16,
			anchor:'25%'
		},
		{
			fieldLabel:'微博账号',
			xtype:'textfield',
			name:'b_member_wb',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型',
			name:'b_member_type',
			xtype:"combo",
            store:[["0","普通会员"],["1","VIP会员"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_member_type',
			allowBlank:false,
			anchor:'25%'
		},
		{
			fieldLabel:'注册时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_brand_mtime',
			name:'b_member_ctime',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_brand_mtime',
			name:'b_member_mtime',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}