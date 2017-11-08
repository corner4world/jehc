Ext.require([
    'Ext.window.Window',
    'Ext.tab.*',
    'Ext.toolbar.Spacer',
    'Ext.layout.container.Card',
    'Ext.layout.container.Border',
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);
var store;
var grid;
var xtGeneratorTableStore;
var xtGeneratorTableColumnStore;
var disksList;
Ext.onReady(function (){
	disksList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtGeneratorController/getDisks',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['disks', 'disksName'],
		autoLoad:true 
	});
    //1.创建store
    store = getGridJsonStore('../xtGeneratorController/getXtGeneratorListByCondition',['xt_generator_id', 'xt_generator_tbname','xt_generator_tbcomment','xt_userinfo_id','xt_generator_time','xt_generator_state','xt_generator_path','xt_generator_model_package','xt_userinfo_realName']);
    //2.创建grid
    grid = Ext.create("Ext.grid.Panel",{
        region:'center',
        xtype:'panel',
        store:store,
   	 	columnLines:true,
        multiSelect:true,
        title:'代码生成器信息列表',
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
	                header:'编号',
	                dataIndex:'xt_generator_id',
	                hidden:true
            	},
            	{
	                header:'表名',
	                dataIndex:'xt_generator_tbname'
            	},
            	{
	                header:'表名备注',
	                flex:1,
	                dataIndex:'xt_generator_tbcomment'
            	},
            	{
	                header:'状态',
	                dataIndex:'xt_generator_state',
	                renderer:function(value){
						if(value == 1){
							return '成功';
						}else if(value == 2){
							return '失败';
						}
					}
            	},
            	{
	                header:'生成类型',
	                dataIndex:'is_one_to_many',
	                renderer:function(value){
						if(value == 1){
							return '<font color=red>一对多表生成</font>';
						}else if(value == 0){
							return '单表生成';
						}else{
							return '<font color=green>缺省</font>';
						}
					}
            	},
            	{
	                header:'生成日期',
	                flex:1,
	                dataIndex:'xt_generator_time'
            	},
            	{
	                header:'操作人',
	                dataIndex:'xt_userinfo_realName'
            	}],
        tbar:[{
	            text:'单表（<font color="red">E模式</font>）',
	            tooltip:'单表快速生成（前端采用Extjs模式开发）',
				cls:'addBtn',
				icon:addIcon,
	            handler:function(){
	            	addXtGenerator(0,1);
            	}
         	 },
         	 {
	            text:'一对多（<font color="red">E模式</font>）',
	            tooltip:'一对多快速生成（前端采用Extjs模式开发）',
				cls:'updateBtn',
				icon:editIcon,
	            handler:function(){
	            	addXtGenerator(1,2);
            	}
         	 },
         	 {
 	            text:'单表（<font color="green">B模式</font>）',
 	            tooltip:'单表快速生成（前端采用Bootstrap模式开发）',
 				cls:'addBtn',
 				icon:addIcon,
 	            handler:function(){
 	            	addXtGenerator(0,3);
             	}
          	 },
          	 {
 	            text:'一对多（<font color="green">B模式</font>）',
 	            tooltip:'一对多快速生成（前端采用Bootstrap模式开发）',
 				cls:'updateBtn',
 				icon:editIcon,
 	            handler:function(){
 	            	addXtGenerator(1,4);
             	}
          	 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtGenerator();
				}
			 }
         	 /**,
			 {
				text:'搜 索',
				tooltip:'搜 索',
				scope:this,
				icon:searchIcon,
				handler:function(){
					grid.getStore().reload();
				}
			 },
			 {
				text:'清空搜索条件',
				tooltip:'清空搜索条件',
				scope:this,
				icon:clearSearchIcon,
				handler:function(){
					searchForm.reset();
				}
			  },{
	            text:'删除',
	            scope:this,
	            glyph:0xf014,  
	            handler:function () {
  					var model = grid.getSelectionModel();
  					if(model.selected.length == 0){
  						msgTishi('请选择后在删除');
  						return;
  					}
  					var rowData = model.selected.items[0].data;
                	 Ext.Msg.confirm("提示", "确定删除该行数据？", function(btn) {
 						if(btn == 'yes'){
 							
 						}
 					});
            	}
         	},{
	            text:'刷新',
	            scope:this,
	            glyph:0xf021,
	            handler:function () {
	            	grid.getStore().reload();
            	}
         	},{
	            fieldLabel:'表名',
	            labelWidth:60,
	            xtype:'textfield',
	            name:'xt_generator_tbname',
	            id:'xt_generator_tbname',
	            width:220
        	},{
	            fieldLabel:'日期',
	            labelWidth:50,
	            xtype:'datefield',
	            name:'xt_generator_time',
	            id:'xt_generator_time',
	            width:150
        	},{
	            fieldLabel:'至',
	            align:'center',
	            labelWidth:10,
	            xtype:'datefield',
	            name:'xt_generator_time',
	            width:150
        	},{
	            text:'开始检索',
	            scope:this,
	            glyph:0xf002,
	            handler:function(){
	            	var xt_menuinfo_title = Ext.getCmp('xt_menuinfo_title').getValue();
	            	store.proxy.extraParams.xt_menuinfo_title = xt_menuinfo_title;
	            	grid.reload();
	        	}
     		},'->',{
	            text:'批量生成代码',
	            scope:this,
	            glyph:0xf0c6,
				xtype:'splitbutton',
				menu:[{
						text:'数据访问层',
						glyph:0xf093,
						handler:function(){
			        	}
					 },'-',{
						text:'业务逻辑层',
						glyph:0xf03e,
						handler:function(){
			        	}
					 },'-',{
						text:'视图控制层',
						glyph:0xf019,
						handler:function(){
			        	}
					 },'-',{
						text:'前端页面层',
						glyph:0xf019,
						handler:function(){
			        	}
					 }]
     		}**/],
	        bbar:getGridBBar(store),
	        listeners:{
	        	'rowdblclick':function(grid, rowIndex, e){
	        		
	        	}
	        }
	    });
	    Ext.create('Ext.Viewport', {
			layout:'border',
			xtype:'viewport',
			items:[grid]
		});
});

function delXtGenerator(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_generator_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_generator_id){
			xt_generator_id=model.selected.items[i].data.xt_generator_id;
		}else{
			xt_generator_id=xt_generator_id+","+model.selected.items[i].data.xt_generator_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_generator_id:xt_generator_id};
			ajaxRequest('../xtGeneratorController/delXtGenerator',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
