var setting = {
   view:{
       selectedMulti:false
   },
   check:{
       enable:false
   },
   async:{
       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
       url:"../xtOrgController/getXtOrgTree",//Ajax获取数据的 URL地址  
       otherParam:{ 
    	 　　'expanded':function(){return 'true'} 
       } //异步参数
   },
   data:{
	   //必须使用data  
       simpleData:{
           enable:true,
           idKey:"id",//id编号命名 默认  
           pIdKey:"pId",//父id编号命名 默认   
           rootPId:0 //用于修正根节点父节点数据，即 pIdKey 指定的属性值  
       }
   },
   edit:{
       enable:false
   },  
   callback:{  
       onClick:onClick,//单击事件
       onAsyncSuccess:onAsyncSuccess//加载数据完成事件
   }  
};
$(document).ready(function(){
	InitztData();
});
//初始数据
var dialogWating;
function InitztData() {
	dialogWating = showWating({msg:'正在拼命的加载中...'});
	$.fn.zTree.init($("#tree"), setting);
}

//刷新
function refreshAll(){
	InitztData();
}

//加载数据完成事件
function onAsyncSuccess(event, treeId, treeNode, msg){  
	var length = $('#keyword').val().length;
	if(length > 0){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
	    var nodeList = zTree.getNodesByParamFuzzy("name", $('#keyword').val());
	    //将找到的nodelist节点更新至Ztree内
	    $.fn.zTree.init($("#"+treeId), setting, nodeList);
	}
	closeWating(null,dialogWating);
}  

//单击事件
function onClick(event, treeId, treeNode, msg){  
	var id = treeNode.id;
	var tempObject = treeNode.tempObject;
	if(tempObject == 'DEPART'){
		$('#postFieldSet').hide();
		$('#departFieldSet').show();
		ajaxBRequestCallFn('../xtDepartinfoController/getXtDepartinfoById?xt_departinfo_id='+id,null,function(result){
			var data = eval("(" + result + ")");  
			data = data.data;
			var tempObject = data.tempObject;
			$('#xt_departinfo_name').val(data.xt_departinfo_name);
			$('#xt_departinfo_type').val(data.xt_departinfo_type);
			$('#xt_departinfo_connectTelNo').val(data.xt_departinfo_connectTelNo);
			$('#xt_departinfo_mobileTelNo').val(data.xt_departinfo_mobileTelNo);
			$('#xt_departinfo_faxes').val(data.xt_departinfo_faxes);
			$('#xt_departinfo_desc').val(data.xt_departinfo_desc);
			$('#xt_departinfo_time').val(data.xt_departinfo_time);
		});
	}
	if(tempObject == 'POST'){
		$('#postFieldSet').show();
		$('#departFieldSet').hide();
		ajaxBRequestCallFn('../xtPostController/getXtPostById?xt_post_id='+id,null,function(result){
			var data = eval("(" + result + ")");  
			data = data.data;
			$('#xt_departinfo_name_').val(data.xt_departinfo_name);
			$('#xt_post_name').val(data.xt_post_name);
			$('#xt_post_desc').val(data.xt_post_desc);
			$('#xt_post_maxNum').val(data.xt_post_maxNum);
			$('#xt_post_grade').val(data.xt_post_grade);
			$('#xt_post_desc').val(data.xt_post_desc);
		});
	}
}  

/**
 * 搜索树，显示并展示
 * @param treeId
 * @param text文本框的id
 */
function filter(){
	InitztData();
}










//Ext.require([
//    'Ext.data.*',
//    'Ext.grid.*',
//    'Ext.tip.*',
//    'Ext.tree.*'
//]);
//var grid;
//var store;
//Ext.onReady(function() {
//    store = Ext.create('Ext.data.TreeStore',{
//    	root:{
//			name:'一级',
//			id:'0',
//			expanded:true
//		},
//		/**此处一定要设置否则全部展开节点无效**/
//		autoLoad:true,
//        proxy:{
//            type:'ajax',
//            method:'post',
//			url:'../xtOrgController/getXtOrgTree',
//			reader:{
//				type:'json'
//			},
//			extraParams:{id:'0',type:encodeURI('部门')}
//        },
//        lazyFill:true
//    });
//    grid = Ext.create('Ext.tree.Panel', {
//        renderTo:Ext.getBody(),
//        reserveScrollbar:true,
//        loadMask:true,
//        useArrows:true,
//        rootVisible:false,
//        store:store,
//        id:'treeGrid',
//        animate:false,
//        title:'组织机构列表',
//        columnLines:true,
//        frame:true,
//        listeners:{  
//            beforeitemexpand:function(node,optd){
//                var id=node.data.id; 
//                var type=node.data.type; 
//                store.setProxy({   
//		       	   //异步从服务器上加载数据extjs会自动帮我们解析  
//		           type:'ajax',  
//		           url:'../xtOrgController/getXtOrgTree',  
//		           extraParams:{id:id,type:encodeURI(type)}
//		        });  
//            },
//            beforeitemcollapse:function(node,optd){
//                return false;  
//            },
//            itemclick:function(node,optd){
//            	var leaf = optd.data.leaf;
//            	if(leaf == true){
//            		
//            	}
//            }
//        },
//        viewConfig:{
//			emptyText:'暂无数据',
//			stripeRows:true
//		},
//		tbar:[
//			 {
//			   width:310,
//			   xtype:'triggerfield',
//			   emptyText:'请输入关键字（如研发部、技术总监等）',
//		       triggerCls:'x-form-clear-trigger',
//		       onTriggerClick:function(){
//		           this.reset();
//		       },
//		       listeners:{
//		           change:function(){
//		           	filterBy(grid,this.getValue(),'name');
//		           },
//		           buffer:250
//		       }
//			 },
//			 {
//				text:'添加一级信息',
//				tooltip:'添加一级信息',
//				icon:addIcon,
//				cls:'addBtn',
//				handler:function(){
//					addXtDepartinfo(0);
//				}
//			 },
//			 {
//				text:'刷新',
//				tooltip:'刷新',
//				cls:'updateBtn',
//				icon:refreshIcon,
//				handler:function(){
//					grid.expandAll();
//				}
//			 }
//		],
//        columns:[{
//            text:'ID',
//            flex:2,
//            hideable:false,
//            hidden:true,
//            sortable:true,
//            dataIndex:'id'
//        },{
//            xtype:'treecolumn',
//            text:'名称',
//            flex:1,
//            sortable:true,
//            dataIndex:'name'
//        },{
//            text:'性质',
//            dataIndex:'type',
//            sortable:true,
//            renderer:function(value){
//            	if(value == '部门'){
//            		return value;
//            	}else{
//            		return "<font color='red'>"+value+"</font>";
//            	}
//            }
//        },/**{
//            text:'上级名称',
//            flex:1,
//            dataIndex:'upname'
//        },**/{
//            text:'备注',
//            flex:1,
//            dataIndex:'remark',
//            renderer:function(value){
//            	return value;
//            }
//        },{
//        	header:'操 作',
//        	columns:[{
//				header:'编 辑',
//				align:'center',
//				xtype:'widgetcolumn',
//				width:140,
//				widget:{
//					xtype:'button',
//	                text:'编 辑',
//	                icon:editIcon,
//	                listeners:{
//					    render:function(rec) {
//					        var record = rec.getWidgetRecord();
//					        //console.log();
//					        var type = record.data.type;
//					        if(type == '部门'){
//					        	rec.setText("<font color=''>编辑部门</font>");
//					        }else{
//					        	rec.setText("<font color='green'>编辑岗位</font>");
//					        }
//					        //label.setText(record.data.asgmt, false);
//					    } 
//					}, 
//					/**
//	                xtype:'label',
//	                text:'编 辑',
//	                glyph:0xf016,
//	                listeners:{
//					    resize:function(label) {
//					        var parent = label.up();
//					        while(typeof parent.up() !== 'undefined') {
//					            parent = parent.up();
//					        }  
//					        var record = parent.getWidgetRecord();
//					        if (typeof record === 'undefined') return;
//					        label.setText(record.data.asgmt, false);
//					    } 
//					}, 
//					**/
//	                /**style:{background:'blue'},**/
//	                handler:function(rec){
//	                	console.log(rec.getWidgetRecord());
//	                	var id = rec.getWidgetRecord().data.id;
//	                	var type = rec.getWidgetRecord().data.type;
//	                	var xt_post_parentId = rec.getWidgetRecord().data.xt_post_parentId;
//	                	var text = rec.getWidgetRecord().parentNode.data.name;
//	                	if(type == '部门'){
//	                		updateXtDepartinfo(id,xt_post_parentId,text);
//	                	}else if(type == '岗位'){
//	                		var xt_post_parentId = rec.getWidgetRecord().data.xt_post_parentId;
//	                		var xt_depart_id = rec.getWidgetRecord().data.xt_departinfo_id;
//	                		updateXtPost(xt_depart_id,xt_post_parentId,id);
//	                	}
//				    }
//	            }
//			},{
//				header:'删 除',
//				align:'center',
//				xtype:'widgetcolumn',
//				width:140,
//				widget:{
//	                xtype:'button',
//	                text:'删 除',
//	                icon:delIcon,
//	                listeners:{
//					    render:function(rec) {
//					        var record = rec.getWidgetRecord();
//					        //console.log();
//					        var type = record.data.type;
//					        if(type == '部门'){
//					        	rec.setText("删除部门");
//					        }else{
//					        	rec.setText("<font color='green'>删除岗位</font>");
//					        }
//					        //label.setText(record.data.asgmt, false);
//					    } 
//					}, 
//	                /**style:{background:'blue'},**/
//	                handler:function(rec){
//	                	var id = rec.getWidgetRecord().data.id;
//	                	var type = rec.getWidgetRecord().data.type;
//	                	if(type == '部门'){
//	                	}else{
//	                	}
//				    }
//	            }
//			},{
//				header:'操作部门',
//				align:'center',
//				xtype:'widgetcolumn',
//				width:180,
//				widget:{
//	                xtype:'button',
//	                text:'添加下级信息',
//	                icon:addIcon,
//	                listeners:{
//					    render:function(rec) {
//					        var record = rec.getWidgetRecord();
//					        //console.log();
//					        var type = record.data.type;
//					        if(type == '部门'){
//					        	rec.setText("<font color='green'>添加下级部门</font>");
//					        }else{
//					        	rec.disable();
//					        	rec.setIcon();
//					        	rec.setText("<font color='red'>该按钮不能操作</font>");
//					        }
//					        //label.setText(record.data.asgmt, false);
//					    } 
//					}, 
//	                /**style:{background:'blue'},**/
//	                handler:function(rec){
//	                	var id = rec.getWidgetRecord().data.id;
//	                	var type = rec.getWidgetRecord().data.type;
//	                	var text = rec.getWidgetRecord().data.name;
//	                	if(type == '部门'){
//	                		var xt_departinfo_parentId = rec.getWidgetRecord().data.xt_departinfo_parentId
//	                		addXtDepartinfo(id,xt_departinfo_parentId,text);
//	                	}else{
//	                		var xt_departinfo_id = rec.getWidgetRecord().data.xt_departinfo_id;
//	                		var xt_departinfo_name = rec.getWidgetRecord().data.xt_departinfo_name;
//	                		addXtPost(xt_departinfo_id,id,xt_departinfo_name);
//	                	}
//				    }
//	            }
//			},{
//				header:'操作岗位',
//				align:'center',
//				xtype:'widgetcolumn',
//				width:170,
//				widget:{
//	                xtype:'button',
//	                text:'添加岗位',
//	                icon:addIcon,
//	                listeners:{
//					    render:function(rec) {
//					        var record = rec.getWidgetRecord();
//					        //console.log();
//					        var type = record.data.type;
//					        if(type == '部门'){
//					        	rec.setText("添加一级岗位");
//					        }else{
//					        	rec.setText("<font color='green'>添加下级岗位</font>");
//					        }
//					        //label.setText(record.data.asgmt, false);
//					    } 
//					},
//	                /**style:{background:'blue'},**/
//	                handler:function(rec){
//	                	var id = rec.getWidgetRecord().data.id;
//	                	var type = rec.getWidgetRecord().data.type;
//	                	var xt_post_id = "";
//	                	var xt_departinfo_id;
//	                	var xt_departinfo_name;
//	                	if(type == '部门'){
//	                		xt_departinfo_id = id;
//	                		//一级岗位
//	                		xt_departinfo_name = rec.getWidgetRecord().data.name;
//	                		addXtPost(xt_departinfo_id,'0',xt_departinfo_name);
//	                	}else{
//	                		//下级岗位
//	                		xt_departinfo_name = rec.getWidgetRecord().data.xt_departinfo_name;
//	                		var xt_departinfo_id = rec.getWidgetRecord().data.xt_departinfo_id;
//	                		addXtPost(xt_departinfo_id,id,xt_departinfo_name);
//	                	}
//	                }
//	            }
//			}]
//        }]
//    });
//    grid.on('beforeload',function(treeloader,node) { 
//   		/**
//   		如果node为一个参数情况下取属性值如下:
//   		var id = node.data.items[0].data.id;
//           var type = node.data.items[0].data.type;
//   		**/
//   		/**
//   		如果node为第二个参数情况下取属性值如下:
//   		var id = node.node.data.id;
//           var type = node.node.data.type;
//   		**/
//        var id = node.node.data.id;
//        var type = node.node.data.type;
//        store.setProxy({   
//       	   //异步从服务器上加载数据extjs会自动帮我们解析  
//           type:'ajax',  
//           url:'../xtOrgController/getXtOrgTree',  
//           extraParams:{id:id,type:encodeURI(type)}
//        });
//    });
//    //grid.expandAll();
//    Ext.create('Ext.Viewport',{
//		layout:'fit',
//		xtype:'viewport',
//		items:grid
//	});
//	showWaitMsg("正在展开数据...");
//	new Ext.util.DelayedTask(function(){  
//       grid.expandAll();
//       hideWaitMsg();
//    }).delay(1000);
//});