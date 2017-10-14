///////////////创建索引////////////////////
function addXtDbTableIndex(xtDbTableName){
	reGetWidthAndHeight();
	initXtDbTableIndexForm(xtDbTableName);
	xtDbTableIndexWinAdd = new Ext.Window({
		layout:'fit',                    
		width:800,      
		height:400,
		modal:true,               
		plain:true,   
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},                 
		title:'创建索引【如果选择字段为多个则为聚集索引(MyISAM不支持)】',
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		items:xtDbTableIndexFormAdd,
		buttons:[{                        
				  text:'选择字段',                        
				  handler:function(){getXtDbTableIndexFiledByCondition(xtDbTableName)}
			     },{                        
				  text:'保 存',                        
				  disabled:false,                        
				  handler:function(){
				  	    doAddXtDbTableIndex(xtDbTableName);
				  }
			     },{
			      	text:'关 闭',
			      	handler:function(){xtDbTableIndexFormAdd.form.reset();xtDbTableIndexWinAdd.close();}                    
		   	     }]
	});
	xtDbTableIndexWinAdd.show();
}

function initXtDbTableIndexForm(xtDbTableName){
	xtDbTableIndexFormAdd = Ext.create('Ext.FormPanel',{
		labelWidth:57,
		region:'center',
		frame:true,
		waitMsgTarget:true,
		defaults:{ 
		   anchor:'100%'
		},
		defaultType:'textfield',
		items:[{
				    fieldLabel:'索引名称',
				    name:'indext_name'
		   	   },{
				    fieldLabel:'索引类型',
				    store:new Ext.data.SimpleStore({ 
					    fields:['value','text'], 
					    data:[['INDEX','普通索引[INDEX]'],['UNIQUE','唯一索引[UNIQUE]'],['PRIMARY KEY','主键索引[PRIMARY KEY]'],['FULLTEXT','全文索引[FULLTEXT]']]
				    }), 
			        id:"type",
			        name:"type",
				    emptyText:'---请选择---', 
				    allowBlank:false,
				    xtype:"combo",
				    mode:'local', 
				    allowBlank:false,
				    triggerAction:'all', 
				    valueFiled:'value', 
				    displayField:'text'
		   	   },{
				    fieldLabel:'所选字段',
				    name:'field',
				    id:'field',
				    allowBlank:false,
				    height:180,
				    readOnly:true,
				    emptyText:'请选择字段',
				    xtype:'textarea'
		   	   }]
	});
}

function doAddXtDbTableIndex(xtDbTableName){
	if(xtDbTableIndexFormAdd.form.isValid()){ 
  		var url='../xtDbStructureController/addXtDbTableIndex?tableName='+xtDbTableName;
		submitForm(xtDbTableIndexFormAdd,url,xtDbTableIndexGrid,xtDbTableIndexWinAdd,false,true);            
	}else{
		msgTishi('请输入必填项');
	} 
}