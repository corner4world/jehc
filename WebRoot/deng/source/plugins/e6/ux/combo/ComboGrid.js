Ext.define('Ext.ux.form.TEST',{
	extend:'Ext.form.field.Picker',
	requires:['Ext.grid.Panel'],
	alias:['widget.comboGrid'],
	xtype:'gridpicker',
	uses:[
        'Ext.grid.Panel'
    ], 
    initComponent:function(){
    	var me = this;
		this.addListener('render',me.onLoad);
    },
	createPicker:function(){
		//如果多选则显示checkbox
		var checkboxmodel='checkboxmodel';
		var mode="MULTI";
		var checkOnly = false;
		if(!this.multiSelect){
			checkboxmodel ='';
			mode = "SINGLE";
			checkOnly = false;
		}
		var me = this,
            picker = Ext.create('Ext.grid.Panel',{
            	title:me.title,
				store:me.store,
				columnLines:true,
				collapsible:me.collapsible,
				border:me.border,
				selType:checkboxmodel,
				selModel:{
				    selection:"rowmodel",
				    mode:mode,
				    checkOnly:checkOnly
				},
				viewConfig:{
					emptyText:'暂无数据',
					stripeRows:true
				},
				loadMask:{
					msg:'正在加载...'
				},
				columns:me.columns,
				bbar:me.bbar,
                shrinkWrapDock:2,
                floating:true,
                valueField:me.valueField,
                displayField:me.displayField,
                width:me.gridWidth,
                height:me.gridHeight,
                items:me.items,
                minHeight:me.minHeight,
                maxHeight:me.maxHeight,
                manageHeight:false,
                shadow:false,
                listeners:{
                    scope:me,
                    itemclick:me.onItemClick
                }
            });
		return picker;
	},
	onItemClick:function(picker, record){
		var me = this;
		var rawValues = this.getRawValue().split(','); 
		var rawValues_ = "";
		var isselected = picker.isSelected(record);
		var selection = picker.getSelectionModel().getSelection(),valueField = this.valueField,displayField=this.displayField;  
		//如果点击该行是取消该行选择行为 则判断已选的值是否有该值 如果有则去除该值
		if(!isselected){
			if(this.getValue() != '' && this.getValue() != null){
				for(var i = 0; i < rawValues.length; i++){
					if(record.get(this.valueField) == rawValues[i]){
						rawValues.splice(i,1);
					}
				}
			}
			for(var i = 0; i < rawValues.length; i++){
				if(rawValues == ''){
					rawValues_ = rawValues[i];
				}else{
					rawValues_ = rawValues_+ ","+ rawValues[i];
				}
			}
		}else{
			//如果点击该行是选中该行行为 则判断已选的值是否有该值 如果有则不做任何操作 没有则添加
			var isExist=false;
			if(this.getValue() != '' && this.getValue() != null){
				for(var i = 0; i < rawValues.length; i++){
					if(record.get(this.valueField) == rawValues[i]){
						isExist = true;
						break;
					}
				}
				//如果找不到该值 则添加
				if(!isExist){
					if(rawValues_ == ''){
						if(this.getValue() == '' || this.getValue() == null){
							rawValues_ = record.get(this.valueField);
						}else{
							rawValues_ = this.getRawValue()+","+record.get(this.valueField);
						}
					}else{
						if(this.getValue() == '' ||this.getValue() == null){
							rawValues_ = rawValues_+ ","+ record.get(this.valueField);
						}else{
							rawValues_ = this.getRawValue()+","+rawValues_+ ","+ record.get(this.valueField);
						}
					}
				}
			}else{
				//如果历史记录没有一条的话则直接添加
				rawValues_ = record.get(this.valueField);
			}
		}
  		//如果combo中设置多选 则点击不关闭
  		if(this.multiSelect){
  			var model = picker.getSelectionModel();
  			this.displayTplData = [record.data];  
            this.setRawValue(rawValues_);
  		}else{
  			this.displayTplData = [record];  
  			this.setValue(record.get(this.valueField));
  			this.setRawValue(record.get(this.displayField));
  			this.collapse();
  			console.info(this.getValue());
  		}
  		this.fireEvent('select', this, record);
  		/**
        if (!this.multiSelect && selection.length) {  
            if (record.get(this.valueField) === selection[0].get(this.valueField)) {  
                // Make sure we also update the display value if it's only partial  
                displayTplData = [record.data];  
                this.setRawValue(record ? record.get(this.displayField) : '');
                this.collapse();  
            }  
        }  
        **/
        return this;
    },  
    matchFieldWidth:false,  
    /**
    setValue:function(value){
        var me = this,
            record;
        me.value = value;
        me.setRawValue(record ? record.get(me.displayField):'');
        return me;
    },
    selectItem:function(picker,record){
        var me = this;
        me.setValue(record.getId());
        me.fireEvent('select', me, record);
        me.collapse();
    },
    **/
    onLoad:function(picker){
    	/**
        var value = this.value;
        if(value){
           this.setValue(value);
        }
        **/
    }
});

