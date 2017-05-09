var grid;
var store;
var record;
var xtDataAuthorityTabPanel;
Ext.onReady(function(){
	xtDataAuthorityTabPanel = Ext.create('Ext.TabPanel',{
        title:'各种按钮设置说明',
        activeTab:0,
        tabPosition:'bottom',
        split:false,
        region:"south",
        frame:true, 
        autoHeight:true, 
        
        items:[
            {title:'按人员数据权限',html:'<font color=red>权限最高</font><br>使用说明:<br>如设置人员为"邓纯杰",即被选择人员为邓纯杰,邓益帝。这种含义指邓纯杰拥有"邓纯杰,邓益帝"权限。比如邓纯杰可设置拥有邓纯杰数据的编辑，删除功能功能，拥有读取邓益帝数据权限<br>以上功能可完成业务为:比如一个公司销售员录入自己的客户,但是自己不能修改自己客户,只能由销售经理修改,但销售经理录入的客户自己不能修改,只能由销售主管来修改同比类推,该权限可以精确到针对每一个人员进行设置'},
            {title:'按部门设置数据权限',html:'<font color=red>权限适中</font><br>使用说明:<br>如设置部门为"销售部",即被选择部门为销售部,采购部。这种含义指销售部拥有"销售部,采购部"权限。比如销售部可设置拥有销售部门中所有员工数据的编辑，删除功能功能，拥有读取采购部门中所有员工数据权限<br>以上功能可完成业务为:同上'},
            {title:'按岗位设置数据权限',html:'<font color=red>权限适中</font><br>使用说明:<br>如设置岗位为"销售经理",即被选择岗位为销售经理,销售主管。这种含义指销售经理岗位拥有"销售经理,销售主管"权限。比如销售经理可设置拥有销售经理岗位中所有员工数据的编辑，删除功能功能，拥有读取销售主管岗位中所有员工数据权限<br>以上功能可完成业务为:同上'},
            {title:'启动初始化设置权限',html:'<font color=red>权限基本</font><br>使用说明:<br>默认公司所有员工或所有部门中员工或所有岗位中拥有自己的基础数据权限,即每个人或每个部门或每个岗位只能拥有查看自己,部门,岗位数据,操作自己数据如增删改。按部门启动上下级基本数据权限即上级部门人员可以对子级员工数据操作,按岗位启动上下级基本数据权限即上级岗位人员可以对子级员工数据操作<br>以上功能可完成业务为:个人基本权限'},
            {title:'清空按人员设置数据权限',html:'<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按人员设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。'},
            {title:'清空按部门设置数据权限',html:'<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按部门设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。'},
            {title:'清空按岗位设置数据权限',html:'<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按岗位设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。'}            
        ]
    });
    store = getGridJsonStore('../xtDataAuthorityController/getXtFunctioninfoListForData',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		title:'数据权限列表',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{header:'ID',width:5,dataIndex:'xt_functioninfo_id',sortable:true,hidden:true},
			{header:'模块名称',dataIndex:'xt_menuinfo_title',flex:1},
			{header:'模块ID',width:10,dataIndex:'xt_menuinfo_id',hideable:false,hidden:true}
		],
		store:store,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
		border:true,
		selType:'checkboxmodel',
		bbar:getGridBBar(store)
			
	});
	new Ext.Viewport({	
		enableTabScroll:true,	
		layout:'border',	
		layoutConfig:{
               titleCollapse:true,
               animate:true,
               activeOnTop:false
        },
		items:[xtDataAuthorityTabPanel,grid]
	});
	initRight();
});

//清空按人员设置数据权限
function delXtDataAuthorityAll(){
	Ext.MessageBox.confirm('提示', '确定要清空按人员数据权限设置吗?',showDelXtDataAuthorityAllBtn);
}
function showDelXtDataAuthorityAllBtn(btn){
	if(btn == 'yes'){
		var url = "saDataAuthority/saDataAuthority_delSaDataAuthorityAll.nzy";
		var params = {};
		ajaxRequest(url,grid,params,'正在执行清空操作,请稍等...'); 
	}
}
//清空按部门设置数据权限
function delXtDataAuthorityDepartAll(){
	Ext.MessageBox.confirm('提示', '确定要清空按部门数据权限设置吗?',showDelXtDataAuthorityDepartAllBtn);
}
function showDelXtDataAuthorityDepartAllBtn(btn){
	if(btn == 'yes'){
		var url = "saDataAuthority/saDataAuthority_delSaDataAuthorityDepartAll.nzy";
		var params = {};
		ajaxRequest(url,grid,params,'正在执行清空操作,请稍等...'); 
	}
}
//清空按岗位设置数据权限
function delXtDataAuthorityPostAll(){
	Ext.MessageBox.confirm('提示', '确定要清空按岗位数据权限设置吗?',showDelXtDataAuthorityPostAllBtn);
}
function showDelXtDataAuthorityPostAllBtn(btn){
	if(btn == 'yes'){
		var url = "saDataAuthority/saDataAuthority_delSaDataAuthorityPostAll.nzy";
		var params = {};
		ajaxRequest(url,grid,params,'正在执行清空操作,请稍等...'); 
	}
}
//清空按初始化数据权限设置
var xtDataAuthorityDefaultPromptWin;
function delSaDataAuthorityDefaultAll(){
	record = grid.getSelectionModel().getSelected();
	if(!record){
		Ext.example.msg('提示', '请选择要清空初始化数据权限的一项！');
		return;
	}
	xtDataAuthorityDefaultPromptWin = new Ext.Window({
		layout:'fit', 
		width:400,                    
		height:100, 
		modal:true,       
		closeAction:'close',    
		plain:true,  
		closable:false, 
		title:'提示',
		animateTarget:document.body,
		/**      
		items:[new top.Ext.ux.form.LovCombo({
					xtype:"combo",
					width:150,
		            store:new Ext.data.SimpleStore({
		             fields:['Value', 'Name'],
		             data:[["1","按人员初始化设置基本数据权限"],["2","按部门初始化设置基本数据权限"],["3","按岗位初始化设置基本数据权限"],["4","按部门启动上下级基本数据权限"],["5","按岗位启动上下级基本数据权限"]]
		            }),
		            emptyText:"-----------------------------请选择初始化数据权限类型-----------------------------",
		            mode:"local",
		            triggerAction:"all",
		            editable:false,
		            valueField:"Value",
		            displayField:"Name",
			        autoSelect:true,
			        showSelectAll:true, 
			        id:'promptID',
			        resizable:true,
		            listeners:{
		         		'blur':function(field) {
		                    var newValue = field.getValue();
		                }
	            	}
			   })
		],  
		**/
		buttons:[{
				 text:'确 定', 
	             width:80,
	             iconCls:'save-icon',  
	             handler:function(){
					if(Ext.getCmp("promptID").getValue() == null || Ext.getCmp("promptID").getValue() == ""){
						Ext.example.msg('提示', "请选择清空类型");//提示之后消失
						return;
					}
	   	 			Ext.MessageBox.confirm('提示', '确定要清空按初始化数据权限设置吗?',
	   	 				function(btn){
		   	 				if(btn == 'yes'){
								var type = Ext.getCmp("promptID").getValue();
								xtDataAuthorityDefaultPromptWin.close(); 
								var url = "saDataAuthority/saDataAuthority_delSaDataAuthorityDefaultAll.nzy";
								var params = {type:type,xt_menuinfo_id:record.get("xt_menuinfo_id")};
								ajaxRequest(url,grid,params,'正在执行清空操作,请稍等...'); 
							}
	   	 				}
	   	 			);
	            } 
	   	 		},{
	               text:'关 闭', 
		           width:80,
		           iconCls:'cancel-icon',  
		           handler:function(){
			          xtDataAuthorityDefaultPromptWin.close();                        
		           }  
	   	 		}]                
	});
	xtDataAuthorityDefaultPromptWin.show();
}

/**导出**/
function exportXtDataAuthority(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'启动初始化设置权限',
			glyph:0xf016,
			handler:function(){delSaDataAuthorityDefaultAll()}
		},{
			text:'按人员设置数据权限',
			glyph:0xf044,
			handler:function(){
				var record = grid.getSelectionModel().selected;
				var xt_menuinfo_id = record.items[0].data.xt_menuinfo_id;
				var xt_menuinfo_title = record.items[0].data.xt_menuinfo_title;
				showUserWin(xt_menuinfo_id,xt_menuinfo_title);
			}
		},{
			text:'按部门设置数据权限',
			glyph:0xf0ea,
			handler:function(){
				var record = grid.getSelectionModel().selected;
				var xt_menuinfo_id = record.items[0].data.xt_menuinfo_id;
				var xt_menuinfo_title = record.items[0].data.xt_menuinfo_title;
				showDepartWin(xt_menuinfo_id,xt_menuinfo_title);
			}
		},{
			text:'按岗位设置数据权限',
			glyph:0xf03b,
			handler:function(){
				var record = grid.getSelectionModel().selected;
				var xt_menuinfo_id = record.items[0].data.xt_menuinfo_id;
				var xt_menuinfo_title = record.items[0].data.xt_menuinfo_title;
				showPostWin(xt_menuinfo_id,xt_menuinfo_title);
			}
		},{
			text:'清空初始化数据权限',
			glyph:0xf014,
			handler:function(){delSaDataAuthorityDefaultAll()}
		},{
			text:'清空按人员数据权限',
			glyph:0xf014,
			handler:function(){delXtDataAuthorityAll()}
		},{
			text:'清空按部门数据权限',
			glyph:0xf014,
			handler:function(){delXtDataAuthorityDepartAll()}
		},{
			text:'清空按岗位数据权限',
			glyph:0xf014,
			handler:function(){delXtDataAuthorityPostAll()}
		},'-',{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtDataAuthority(grid,'../xtDataAuthorityController/exportXtDataAuthority');
			}
		},{
			text:'打 印',
			glyph:0xf02f,
			handler:function(){printerGrid(grid);}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			handler:function(){refreshGrid(grid);}
		}]
	});
	grid.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault(); 
		contextmenu.showAt(e.getXY());
	});
}