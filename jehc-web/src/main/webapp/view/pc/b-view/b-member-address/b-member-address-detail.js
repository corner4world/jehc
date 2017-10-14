var bMemberAddressWinDetail;
var bMemberAddressFormDetail;
function detailBMemberAddress(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBMemberAddressFormDetail();
	xtCityList.load({params:{parentId:record.items[0].data.xt_provinceID}});
	var parm = {parentId:record.items[0].data.xt_provinceID};
    beforeloadstoreByStore(xtCityList,parm);
	xtDistrictList.load({params:{parentId:record.items[0].data.xt_cityID}});
	parms = {parentId:record.items[0].data.xt_cityID};
    beforeloadstoreByStore(xtDistrictList,parms);
	bMemberAddressWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
//		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'left',
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bMemberAddressFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bMemberAddressWinDetail.show();
	loadFormData(bMemberAddressFormDetail,'../bMemberAddressController/getBMemberAddressById?b_member_address_id='+ record.items[0].data.b_member_address_id);
}
function initBMemberAddressFormDetail(){
	bMemberAddressFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		fieldDefaults:{
			labelWidth:170,
			labelAlign:'right',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'会员常用地址编号',
			xtype:'textfield',
			hidden:true,
			name:'b_member_address_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'会员编号',
			xtype:'textfield',
			id:'b_member_id_',
			hidden:true,
			name:'b_member_id',
			maxLength:32,
			width:300
		},
		{
			fieldLabel:'邮编地址',
			xtype:'textfield',
			name:'postcode',
			maxLength:16,
			width:300
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
                	Ext.getCmp('xt_cityID').setValue("");
		         	Ext.getCmp('xt_districtID').setValue("");
		            xtCityList.load({params:{parentId:this.value}});
		            parms = {parentId:this.value};
		    	    beforeloadstoreByStore(xtCityList,parms);
		            xtDistrictList.load();
		            parms = {parentId:null};
		    	    beforeloadstoreByStore(xtDistrictList,parms);
                    /**设置默认选中第一行的值
                    xtCityList.on('load',function(store,record,opts){                                    
                     var xt_cityID = record[0].data.xt_cityID;
                     var xt_cityName=record[0].data.xt_cityName;
                     xtCityList.setValue(xt_cityID);
                     xtCityList.setDisplayValue(xt_cityName);
                    });
                    **/
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
                	Ext.getCmp('xt_districtID').setValue("");
		            xtDistrictList.load({params:{parentId:this.value}});
		            parms = {parentId:this.value};
		    	    beforeloadstoreByStore(xtDistrictList,parms);
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
			maxLength:32,
			anchor:'60%'
		},
		{
			fieldLabel:'详细地址',
			xtype:'textareafield',
			name:'b_member_address_detail',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'是否设为常用收货地址',
			xtype:"combo",
            store:[["0","是"],["1","否"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
            name:'b_member_address_status',
			hiddenName:'b_member_address_status',
			width:300,
		}
		]
	});
}
