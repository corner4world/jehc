/**
  *配置会签
**/
var multiInstanceLoopCharacteristicForm;
function initMultiInstance(cell){
	var isSequentialStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["0","不启动多实例"],["true","顺序"],["false","并行"]]
	});
	multiInstanceLoopCharacteristicForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
       	    xtype:'combo',
            hiddenName:'name',
            valueField:"value",
            displayField:"name",
            store:isSequentialStore,
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false,
            fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',  
           	name:'isSequential', 
           	id:'isSequential', 
           	anchor:'40%'
         },{
       	    xtype:'textfield',  
            fieldLabel:'循环数量',  
           	name:'loopCardinality', 
           	id:'loopCardinality', 
           	anchor:'40%'
         },{
       	    xtype:'textareafield',  
            fieldLabel:'循环集合',  
           	name:'collection', 
           	id:'collection', 
           	anchor:'100%'
         },{
       	    xtype:'textfield',  
            fieldLabel:'元素名称',  
           	name:'elementVariable', 
           	id:'elementVariable', 
           	anchor:'100%'
         },{
       	    xtype:'textareafield',  
            fieldLabel:'结束条件',  
           	name:'completionCondition', 
           	id:'completionCondition', 
           	anchor:'100%'
         }]
    }); 
    /**取值**/
	var isSequential = cell.isSequential;
 	var loopCardinality = cell.loopCardinality;
 	var collection = cell.collection;
 	var elementVariable = cell.elementVariable;
 	var completionCondition = cell.completionCondition;
 	/**赋值**/
 	Ext.getCmp('isSequential').setValue(isSequential);
    Ext.getCmp('loopCardinality').setValue(loopCardinality);
    Ext.getCmp('collection').setValue(collection);
    Ext.getCmp('elementVariable').setValue(elementVariable);
    Ext.getCmp('completionCondition').setValue(completionCondition);
}

/**赋值**/
function multi_instance_setvalue(cell){
	var isSequential = Ext.getCmp('isSequential').getValue();
	var loopCardinality = Ext.getCmp('loopCardinality').getValue();
	var collection = Ext.getCmp('collection').getValue();
	var elementVariable = Ext.getCmp('elementVariable').getValue();
	var completionCondition = Ext.getCmp('completionCondition').getValue();
 	if(null != isSequential && '' != isSequential){
    	cell.isSequential = isSequential;
    }
    if(null != loopCardinality && '' != loopCardinality){
    	cell.loopCardinality = loopCardinality;
    }
    if(null != collection && '' != collection){
    	cell.collection = collection;
    }
    if(null != elementVariable && '' != elementVariable){
    	cell.elementVariable = elementVariable;
    }
    if(null != completionCondition && '' != completionCondition){
    	cell.completionCondition = completionCondition;
    }
}