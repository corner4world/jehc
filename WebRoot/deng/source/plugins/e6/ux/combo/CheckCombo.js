/**  
 * begin for multi Select combobox  
 */  
Ext.define('Ext.ux.MultiComboBox', {  
    extend: 'Ext.form.ComboBox',  
    alias: 'widget.multicombobox',  
    xtype: 'multicombobox',  
    validate:function() {  
        var errs =[];   
        var val = this.getRawValue();  
        if (this.store.data && this.store.data.items.length>0){  
            if (this.xtype=="multicombobox"){  
                var ssRawValues=[];  
                if (val){  
                    if ((this.id==="projectList")||val.indexOf(", ")>0){  
                        ssRawValues=val.split(", ");  
                    }  
                    else{  
                        ssRawValues=val.split(",");  
                    }  
                }  
                for(var ii=0;ii<ssRawValues.length;ii++){  
                    var selectedValue=ssRawValues[ii];  
                    if (ssRawValues[ii].trim){  
                        selectedValue=ssRawValues[ii].trim();  
                    }else if (trim){  
                        selectedValue=trim(selectedValue);  
                    }  
                    var rec = this.findRecord(this.displayField, selectedValue);  
                    if(!rec)  
                        errs.push("Invalid Selection ["+ ssRawValues[ii]+"]");   
                }  
                  
            }  
            else{  
                var rec = this.findRecord(this.displayField, val);  
                if(!rec)  
                    errs.push("Invalid Selection");   
            }  
        }  
        if(errs && errs.length > 0)  
        {  
            var error = errs[0];  
            this.markInvalid(error);  
            return false;  
        }else if(!this.allowBlank && !val){  
            this.markInvalid(this.getErrors());  
            return false;  
        }      
        else{  
            this.clearInvalid();  
            return true;  
        }   
    },  
    clearValue:function(){        
     //var coboxhtml = this.getEl().getHTML();  
     try{  
         var coboxhtml = this.getEl().dom;  
         if(coboxhtml!=null)  
         {                
             var checkboxs = this.picker.listEl.el.dom.children;  
             if(checkboxs!=null)  
             {  
                 for(var i=0;i<checkboxs.length;i++)  
                 {  
                     var checkbox = checkboxs[i];  
                       
                     checkbox.children[0].checked = false;  
                 }  
             }  
          }   
     }catch(e)  
     {  
        if(typeof(console)!="undefined"&&console!=null)  
        {  
            console.log(e.toString());  
        }  
     }  
      this.setValue([]);  
   },  
    initComponent: function(){  
        var valueField = this.valueField;  
        this.multiSelect = true;  
        var me = this;     
        var thisid = this.id;  
        var allOptId = thisid+"_allOpt";  
        this.allOptId = allOptId;  
        this.listConfig = {        
             tpl:   '<div class="mt-boundlist-item" onclick="clickAllOptionDiv(\''+thisid+'\')"><input onclick="clickAllOptionInput(this)" type="checkbox" id="'+allOptId+'">全部</div><tpl for="."><div class="x-boundlist-item"><input type=checkbox>{'+this.displayField+'}</div></tpl>',  
             onItemSelect: function(record) {      
                  var node = this.getNode(record);  
                  if (node) {  
                     Ext.fly(node).addCls(this.selectedItemCls);  
                       
                     var checkboxs = node.getElementsByTagName("input");  
                     if(checkboxs!=null)  
                     {  
                         var checkbox = checkboxs[0];  
                         checkbox.checked = true;  
                     }  
                  }  
                 var isAllSelected = true;  
                 var store = this.getStore();  
                 if(store!=null&&store.getTotalCount()>0)  
                 {  
                     for(var i=0;i<store.getTotalCount();i++)  
                     {  
                         var recordTemp = store.getAt(i);  
                         var itemTemp = this.getNode(recordTemp);  
                         var isSelectedTemp = this.isSelected(itemTemp);  
                         if(!isSelectedTemp){  
                            isAllSelected = false;      
                            break;                    
                         }  
                     }  
                 }else{  
                    isAllSelected = false;  
                 }  
  
                 if(isAllSelected)  
                 {  
                    me.selectAllOpt();  
                 }                                                                                                      
              },  
            onItemDeselect: function(record) {  
                var node = this.getNode(record);  
                if (node) {  
                     Ext.fly(node).removeCls(this.selectedItemCls);  
                   
                 var checkboxs = node.getElementsByTagName("input");  
                 if(checkboxs!=null)  
                 {  
                     var checkbox = checkboxs[0];  
                         checkbox.checked = false;  
                     me.deselectAllOpt();  
                           
                 }  
                }  
            },  
              listeners:{  
                  itemclick:function(view, record, item, index, e, eOpts ){  
                      var isSelected = view.isSelected(item);  
                      var checkboxs = item.getElementsByTagName("input");  
                      if(checkboxs!=null)  
                      {  
                          var checkbox = checkboxs[0];  
                          if(!isSelected)  
                          {  
                              checkbox.checked = true;  
                          }else{  
                              checkbox.checked = false;  
                          }  
                      }                                            
                  }  
              }         
        }         
        this.callParent();  
    },  
      
    deselectAllOpt:function()  
    {  
        var allOptInput = document.getElementById(this.allOptId);  
        if(allOptInput!=null)  
        {  
            allOptInput.checked =false;  
        }  
    },  
    selectAllOpt:function()  
    {  
        var allOptInput = document.getElementById(this.allOptId);  
        if(allOptInput!=null)  
        {  
            allOptInput.checked =true;  
        }  
    }      
      
      
      
});  
  
function clickAllOptionDiv(comboxId)  
{     
    if(comboxId!=null&&comboxId.length>0)  
    {  
        var allOptInputId = comboxId+"_allOpt";  
        var allOptInput = document.getElementById(allOptInputId);  
        clickAllOptionInput(allOptInput);         
    }  
}  
  
function clickAllOptionInput(allOptInput)  
{  
    if(allOptInput!=null)  
    {  
        var allOptInputId = allOptInput.id;  
        var allOptInputIdArray = allOptInputId.split("_allOpt");  
        var comboxId = allOptInputIdArray[0];  
        var isChecked = allOptInput.checked;  
        var combobox = Ext.getCmp(comboxId);  
        var boundList = combobox.getPicker();   
        if(boundList!=null)  
        {     
            var selModel = boundList.getSelectionModel();  
            selModel.deselectOnContainerClick=false;  
        }  
        var allValueArray = getAllStoreValueArryByAtt(combobox.getStore(),combobox.valueField);  
        if(isChecked)  
        {  
            allOptInput.checked=false;            
            if(combobox!=null)  
            {  
                combobox.setValue([]);  
            }  
        }else{  
            allOptInput.checked=true;  
            if(combobox!=null)  
            {  
                combobox.setValue(allValueArray);                                     
            }  
        }  
    }     
}  
  
  
function getAllStoreValueArryByAtt(store,key)  
{  
    var valueArray = [];  
    if(store!=null)  
    {  
        for(var i=0;i<store.getTotalCount();i++)  
        {  
            var record = store.getAt(i);  
            if(record.get(key)!=null)  
            {  
                valueArray.push(record.get(key));  
            }  
        }  
    }  
    return valueArray;  
}  
  
  
/**  
 * end for multi Select combobox  
 */  