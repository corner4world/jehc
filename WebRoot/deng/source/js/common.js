//按钮白色图标
var addIcon = '../deng/images/grid/using/add.png';
var editIcon = '../deng/images/grid/using/edit.png';
var delIcon = '../deng/images/grid/using/del.png';
var copyPasteIcon = '../deng/images/grid/classic/copyPaste_new.png';
var detailIcon = '../deng/images/grid/using/detail.png';
var printIcon = '../deng/images/grid/using/print.png';
var dcIcon = '../deng/images/grid/using/dc.png';
var refreshIcon = '../deng/images/grid/using/refresh.png';
var searchIcon = '../deng/images/grid/using/search.png';
var clearSearchIcon = '../deng/images/grid/using/clearSearch.png';
var zkIcon = '../deng/images/grid/using/zk_new.png';
var cutIcon = '../deng/images/grid/using/cut_new.png';
var uploadIcon = '../deng/images/grid/using/upload.png';
var jhIcon = '../deng/images/grid/using/jh_new.png';
var backIcon = '../deng/images/grid/using/back.png';
var lingdangIcon = '../deng/images/grid/using/lingdang.png';
var clockIcon = '../deng/images/grid/using/clock_new.png';
var runIcon = '../deng/images/grid/using/run.png';
var saveIcon = '../deng/images/grid/using/save.png';
var listIcon = '../deng/images/grid/using/list_.png';
var indexlistIcon = '../deng/images/grid/otp/listround.png';
var payIcon = '../deng/images/grid/using/pay.png';
var phoneIcon = '../deng/images/grid/using/phone.png';
var collapsibleIcon = '../deng/images/grid/using/collapsible.png';
var taskIcon = '../deng/images/grid/using/task.png';
var homeIcon = '../deng/images/home/home.png';
var messageIcon = '../deng/images/grid/using/message.png';
var lockIcon = '../deng/images/grid/otp/lock.png';

var indexlist = '../deng/images/top/index_list.png';
var indexUserIcon = '../deng/images/top/index_user.png';
var indexCollapseIcon = '../deng/images/top/index_collapse.png';
var indexSearchIcon = '../deng/images/top/index_search.png';
var indexRopot = '../deng/images/top/indexRopot.png';
var indexLingdang = '../deng/images/top/lingdang16x16.png';
var indexMail = '../deng/images/top/indexMail.png';
var indexIcon = '../deng/images/home/index.png';

var chattingIcon = '../deng/images/icons/user.png';
var getpwdIcon = '../deng/images/grid/otp/getpwd.png';
var bsdefimg= basePath+"/deng/images/default/add_d.png";
var uploadimg = '../deng/images/common/uploadFFF.png';
var uploadfaliimg = '../deng/images/default/upload_fail.png';
var uploadsucessimg = '../deng/images/default/upload_sucess.png';

/**
 * 设置Cookie
 * @param {} name
 * @param {} value
 */
function setCookie(name, value, minuts) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
    var expiration = new Date((new Date()).getTime() + minuts * 60000 * 60);
	document.cookie = name
			+ "="
			+ escape(value)
			+ "; expires=" + expiration.toGMTString();
}
/**
 * 获取Cookie
 * @param {} Name
 * @return {}
 */
function getCookie(Name) {
	var search = Name + "="
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search)
		if (offset != -1) {
			offset += search.length
			end = document.cookie.indexOf(";", offset)
			if (end == -1)
				end = document.cookie.length
			return unescape(document.cookie.substring(offset, end))
		} else
			return ""
	}
}
/**
 * 从缓存中清除Cookie
 * @param {} name
 */
function clearCookie(name) {
	var expdate = new Date();
	expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));
	setCookie(name, "", expdate);
}

//获取元素对象
function gValue(id){
	return Ext.getCmp(id).getValue();
}
//获取TOP层元素
function gTopValue(id){
	return top.Ext.getCmp(id).getValue();
}
//赋值元素对象
function sValue(id,val){
	Ext.getCmp(id).setValue(val);
}
//赋值TOP层元素
function sTopValue(id,val){
	top.Ext.getCmp(id).setValue(val);
}
//隐藏TOP层按钮
function hiddenTopBtn(id){
	top.Ext.getCmp(id).setVisible(false);
}
//隐藏层按钮
function hiddenBtn(id){
	Ext.getCmp(id).setVisible(false);
}
//显示TOP层按钮
function showTopBtn(id){
	top.Ext.getCmp(id).setVisible(true);
}
//显示层按钮
function showBtn(id){
	Ext.getCmp(id).setVisible(true);
}
//获取form元素对象(条件根据form和itemId)
function gfiValue(form,itemId){
	return form.getComponent(itemId).getValue();
}

//赋值form元素对象(条件根据form和itemId)
function sfiValue(form,itemId,val){
	form.getComponent(itemId).setValue(val);
}

//通过form中id或name进行赋值
function sfValue(form){
	return form.getForm().findField(id_name).getValue();; 
}
//通过form中id或name进行取值
function sfValue(form,id_name,val){
	form.form.findField(id_name).setValue(val); 
}
//通过form中id或name进行设置只读或可读
function sfReadOnly(form,id_name,isReadOnly){
	return form.getForm().findField(id_name).setReadOnly(isReadOnly);
}

//返回随机数颜色
function randomColor(){
    return '#'+(function(h){
    return new Array(7-h.length).join("0")+h })((Math.random()*0x1000000<<0).toString(16))
}


//将内容加到中间面板中
function addTabContent(url,id,text,icon){
	//将内容加入到面板中
	var tabpanelCenter = Ext.getCmp('tabpanelCenter');
	var tabid = 'tab_' + id;
	if(tabpanelCenter.getComponent(tabid)){
        tabpanelCenter.remove(tabpanelCenter.getComponent(tabid));
        //$("#xtIframe"+id).attr('src', $("#xtIframe"+id).attr('src'));
    }
    var panel = Ext.create('Ext.Panel',{
         id:tabid,
         name:tabid,
         maskDiabled:true,
         title:text,
         closable:true,
         icon:icon,
         layoutOnTabChange:true,
		html:'<iframe width=100% height=100% id=xtIframe'+id+' name="xtIframe" src="../'+url+'" frameborder="0" style="overflow:hidden;"/>'
    });
    tabpanelCenter.add(panel).show();
}
var page_number;
var page_size;
var pagesize_combo;
var bbar;
//获取数据JsonStore
function getGridJsonStore(url,fields){
	return new Ext.data.JsonStore({
        autoLoad:{start:0,limit:30},
        pageSize:30,
        remoteSort:false,//true为远程排序
        proxy:{
            type:'ajax',
            url:url,
            timeout:600000,/**设置超时时间10十分钟**/
            //解决参数乱码问题
            actionMethods:{ 
				read:'POST' 
			},
            reader:{
                type:'json',
                totalProperty:'total',//数据条数
                root:'items',
                idProperty:'id'
            },
            success:function(response, options){
            	/**
            	console.info(response);
            	**/
            },
            failure:function(response, options){
            	/**
            	console.info(response);
            	**/
            }
        }
        /**,
        fields:fields
        **/
    });
}

//获取数据JsonStore 并根据字段进行分组
function getGridJsonGroupStore(url,fields,groupfields){
	return new Ext.data.JsonStore({
        autoLoad:{start:0,limit:30},
        pageSize:30,
        remoteSort:false,//true为远程排序
        groupField:groupfields,
        proxy:{
            type:'ajax',
            url:url,
            timeout:600000,/**设置超时时间10十分钟**/
            //解决参数乱码问题
            actionMethods:{ 
				read:'POST' 
			}, 
            reader:{
                type:'json',
                totalProperty:'total',//数据条数
                root:'items',
                idProperty:'id'
            },
            success:function(response, options){
            },
            failure:function(response, options){
            	/**
            	console.info(response);
            	**/
            }
        }
        /**,
        fields:fields
        **/
    });
}
//获取DataGrid的bbar
function getGridBBar(store){
	pagesize_combo = Ext.create('Ext.form.ComboBox',{
		name:'pagesize',
		hiddenName:'pagesize',
		typeAhead:true,
		triggerAction:'all',
		lazyRender:true,
		mode:'local',
		store:new Ext.data.ArrayStore({
			fields:['value','text'],
			data:[[30,'30条/页'],[50,'50条/页'], [100,'100条/页'],[250,'250条/页'], [500,'500条/页']]
		}),
		valueField:'value',
		displayField:'text',
		value:'30',
		editable:false,
		width:95
	});
	page_number = parseInt(pagesize_combo.getValue());
	pagesize_combo.on("select", function(comboBox) {
		/**注意Extjs4以下版本使用bbar.pageSize-------extjs5使用store.pageSize**/
		store.pageSize = parseInt(comboBox.getValue());
		page_number = parseInt(comboBox.getValue());
	    //store.load({params:{start:0,limit:bbar.pageSize}});
		//var parmas = {params:{start:0,limit:store.pageSize}};
		store.load();
		store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,null));});
	});
	bbar = Ext.create('Ext.PagingToolbar',{
        pageSize:page_number,
		store:store,
		firstText:'第一页',
		nextText:'下一页',
		prevText:'上一页',
		refreshText:'刷新',
		lastText:'最后一页',
		afterPageText:'页，共{0}页',
	    beforePageText:'当前第',
		displayInfo:true,
		displayMsg:'当前显示从{0}至{1}，共 {2}条记录',
		emptyMsg:'没有记录',
		items:['每页显示',pagesize_combo,'条记录']
    });
	return bbar;
}


//获取DataGrid的bbar 容器最外层使用
function getGridTopBBar(store){
	pagesize_combo = Ext.create('top.Ext.form.ComboBox',{
		name:'pagesize',
		hiddenName:'pagesize',
		typeAhead:true,
		triggerAction:'all',
		lazyRender:true,
		mode:'local',
		store:new Ext.data.ArrayStore({
			fields:['value','text'],
			data:[[30,'30条/页'],[50,'50条/页'], [100,'100条/页'],[250,'250条/页'], [500,'500条/页']]
		}),
		valueField:'value',
		displayField:'text',
		value:'30',
		editable:false,
		width:95
	});
	page_number = parseInt(pagesize_combo.getValue());
	pagesize_combo.on("select", function(comboBox) {
		/**注意Extjs4以下版本使用bbar.pageSize-------extjs5使用store.pageSize**/
		store.pageSize = parseInt(comboBox.getValue());
		page_number = parseInt(comboBox.getValue());
		//store.load({params:{start:0,limit:bbar.pageSize}});
		//var parmas = {params:{start:0,limit:store.pageSize}};
		//store.load(parmas);
		store.load();
		store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,null));});
	});
	bbar = Ext.create('top.Ext.PagingToolbar',{
        pageSize:page_number,
		store:store,
		firstText:'第一页',
		nextText:'下一页',
		prevText:'上一页',
		refreshText:'刷新',
		lastText:'最后一页',
		afterPageText:'页，共{0}页',
	    beforePageText:'当前第',
		displayInfo:true,
		displayMsg:'当前显示从{0}至{1}，共 {2}条记录',
		emptyMsg:'没有记录',
		items:['每页显示',pagesize_combo,'条记录']
    });
	return bbar;
}

//加载Store前参数设置
function beforeloadstore(parmas){
	/**
	store.on('beforeload', function (store,options) {
        Ext.apply(store.proxy.extraParams, parmas);
    });
    **/
	store.on('beforeload',function(thiz, options){
		/**  
	 	Ext.apply(thiz.proxy.extraParams,{  
			start:store.currentPage,
			limit:store.pageSize
		});  
		**/
		Ext.apply(thiz.proxy.extraParams,parmas);
    });
}
//加载objStore前参数设置
function beforeloadstoreByStore(objStore,parmas){
	/**
	objStore.on('beforeload', function (store,options) {
        Ext.apply(store.proxy.extraParams, parmas);
    });
    **/
	objStore.on('beforeload',function(thiz, options){
		/**  
	 	Ext.apply(thiz.proxy.extraParams,{  
			start:objStore.currentPage,
			limit:objStore.pageSize
		});  
		**/
		Ext.apply(thiz.proxy.extraParams,parmas);
    });
}
//刷新Grid列表
function load(grid,params){
	grid.getStore().reload({params:params});
}

//显示右下角提示消息
function showToast(title,message){
	Ext.ux.Toast.msg(title, message); 
}

//获取JsonStore
function getJsonStore(url,fields){
	return new Ext.data.JsonStore({
		autoLoad:true,
        proxy:{
            type:'ajax',
            url:url,
            reader:{
                type:'json'
            }
        },
        fields:fields
    });
}

//搜索页面参数
function getParmas(store,searchForm){
	var parmas;
	/**start采用后台计算**/
	var start = (store.currentPage-1)*store.pageSize;
	/**
	var start = store.currentPage;
	**/
	if(null != searchForm){
		//parmas = {start:start,limit:getGridBBar(store).pageSize,searchJson:Ext.encode(searchForm.getForm().getFieldValues())}
		parmas = {start:start,limit:store.pageSize,searchJson:Ext.encode(searchForm.getForm().getFieldValues())}
	}else{
		//parmas = {start:start,limit:getGridBBar(store).pageSize}
		parmas = {start:start,limit:store.pageSize}
	}
	return parmas;
};
/**
  *获取当前页
**/
function getCurr(store){
	return store.currentPage;
}
/**
  *获取当每页显示的数量
**/
function getPsize(store){
	return store.pageSize;
}
/**
  *获取当每页显示的数量 及当前页
**/
function getCP(store){
	var array = new Array();
	array.push(store.currentPage);
	array.push(store.pageSize);
	return array;
}


/**
 * 下拉树开始
 * @memberOf {TypeName} 
 */
Ext.define("Ext.ux.comboboxtree",{  
    extend:"Ext.form.field.Picker",  
    requires:["Ext.tree.Panel"],  
    initComponent: function(){  
        var self = this;  
        Ext.apply(self,{  
            fieldLabel:self.fieldLabel,  
            labelWidth:self.labelWidth  
        });  
        self.callParent();  
    },  
    createPicker:function(){  
        var self = this;  
        var store = Ext.create('Ext.data.TreeStore',{  
            proxy:{  
                type:'ajax',  
                url:self.storeUrl  
            },  
            sorters:[{  
                property:'leaf',  
                direction:'ASC'  
            },  
            {  
                property:'text',  
                direction:'ASC'  
            }],  
            root:{  
                id:self.rootId,  
                text:self.rootText  
            },  
            nodeParameter:self.treeNodeParameter  
        });  
        self.picker = new Ext.tree.Panel({  
            height:300,  
            autoScroll:true,  
            floating:true,  
            focusOnToFront:false,  
            shadow:true,  
            ownerCt:this.ownerCt,  
            useArrows:true,  
            store:store,  
            rootVisible:true  
        });  
        self.picker.on({  
            checkchange:function(record, checked){  
                var checkModel = self.checkModel;  
                if(checkModel == 'double'){  
                    var root = self.picker.getRootNode();  
                    root.cascadeBy(function(node){  
                        if(node.get('text') != record.get('text')){  
                            node.set('checked', false);  
                        }  
                    });  
                    if(record.get('leaf') && checked){  
                        self.setRawValue(record.get('id'));//隐藏值  
                        self.setValue(record.get('text'));//显示值  
                    }else{  
                        record.set('checked',false);  
                        self.setRawValue('');//隐藏值  
                        self.setValue('');//显示值  
                    }  
                }else{  
                    var cascade = self.cascade;  
                    if(checked == true){  
                        if(cascade == 'both' || cascade == 'child' || cascade == 'parent'){  
                            if(cascade == 'child' || cascade == 'both'){  
                                if(!record.get("leaf") && checked) record.cascadeBy(function(record){  
                                    record.set('checked', true);  
                                });  
                            }  
                            if(cascade == 'parent' || cascade == 'both'){  
                                pNode = record.parentNode;  
                                for(; pNode != null; pNode = pNode.parentNode){  
                                    pNode.set("checked", true);  
                                }  
                            }  
                        }  
                    } else if(checked == false){  
                        if(cascade == 'both' || cascade == 'child' || cascade == 'parent'){  
                            if (cascade == 'child' || cascade == 'both'){  
                                if(!record.get("leaf") && !checked) record.cascadeBy(function(record){  
                                    record.set('checked', false);  
                                });  
                            }  
                        }  
                    }  
                    var records = self.picker.getView().getChecked(),  
                    names = [],  
                    values = [];  
                    Ext.Array.each(records,  
                    function(rec){  
                        names.push(rec.get('text'));  
                        values.push(rec.get('id'));  
                    });  
                    self.setRawValue(values.join(';'));//隐藏值  
                    self.setValue(names.join(';'));//显示值  
                }  
            },  
            itemclick:function(tree, record, item, index, e, options){  
                var checkModel = self.checkModel;  
                if (checkModel == 'single'){  
                    if(record.get('leaf')){  
                        self.setRawValue(record.get('id'));//隐藏值  
                        self.setValue(record.get('text'));//显示值  
                    }else{  
                        self.setRawValue('');//隐藏值  
                        self.setValue('');//显示值  
                    }  
                }  
            }  
        });  
        return self.picker;  
    },  
    alignPicker:function(){  
        var me = this,  
        picker,isAbove,aboveSfx = '-above';  
        if (this.isExpanded){  
            picker = me.getPicker();  
            if(me.matchFieldWidth){  
                picker.setWidth(me.bodyEl.getWidth());  
            }  
            if(picker.isFloating()){  
                picker.alignTo(me.inputEl, "", me.pickerOffset); // ""->tl  
                isAbove = picker.el.getY() < me.inputEl.getY();  
                me.bodyEl[isAbove ? 'addCls': 'removeCls'](me.openCls + aboveSfx);  
                picker.el[isAbove ? 'addCls': 'removeCls'](picker.baseCls + aboveSfx);  
            }  
        }  
    }  
});  
/**下拉树结束 */

/**
 * 弹出窗体
 */
function pWindow(url, pWidth, pHeight){
	var initUrl = url+ "&time=" + new Date();
	if (Ext.isEmpty(pWidth)){pWidth = 800;}
		
	if (Ext.isEmpty(pHeight)){pHeight = 600;}
		
	var left = (screen.width - pWidth) / 2;
	var top = (screen.height - pHeight) / 2;
	var str = 'dialogWidth='
			+ pWidth
			+ ',dialogHeight='
			+ pHeight
			+ ',top='
			+ top
			+ ",left="
			+ left
			+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no';
	window.showModalDialog(initUrl, window, str);
}

/**
 * 显示请求等待进度条窗口
 * @param {}msg
 */
function showTopLoading(msg){
	var me = this,
             i = 0,
             fn;
	top.Ext.MessageBox.show({
		title:'提示',
		msg:msg == null ? '正在处理数据,请稍候...':msg,
		progressText:'正在处理数据,请稍候...',
		width:300,
		wait:true,
		waitConfig:{
			interval:150
		}
	});
	fn = function(){
            me.timer = null;
            ++i;
            if(i === 12){
                top.Ext.MessageBox.hide();
            }else{
                var val = i / 11;
                top.Ext.MessageBox.updateProgress(val, Math.round(100 * val) + '% 已完成进度');
                me.timer = Ext.defer(fn, 500);
            }
        };
        me.timer = Ext.defer(fn, 500);
}

/**
 * 显示请求等待进度条窗口
 * @param {}msg
 */
function showLoading(msg){
	var me = this,
             i = 0,
             fn;
	Ext.MessageBox.show({
		title:'提示',
		msg:msg == null ? '正在处理数据,请稍候...':msg,
		progressText:msg == null ? '正在处理数据,请稍候...':msg,
		width:300,
		icon:'saveing-icon',
		progress:true,
        closable:false,
		waitConfig:{
			interval:150
		}
	});
	fn = function(){
            me.timer = null;
            ++i;
            if(i === 12){
                Ext.MessageBox.hide();
            }else{
                var val = i / 11;
                Ext.MessageBox.updateProgress(val, Math.round(100 * val) + '% 已完成进度');
                me.timer = Ext.defer(fn, 500);
            }
        };
        me.timer = Ext.defer(fn, 500);
}

/**
 * 显示请求等待进度条窗口
 * @param {}msg
 */
function showWaitMsg(msg){
    var me = this,
             i = 0,
             fn;
	Ext.MessageBox.show({
		title:'提示',
		msg:msg == null ? '正在处理数据,请稍候...':msg,
		progressText:msg == null ? '正在处理数据,请稍候...':msg,
		width:300,
		progress:true,
        closable:false,
		icon:'saveing-icon',
		waitConfig:{
			interval:150
		}
	});
	fn = function(){
            me.timer = null;
            ++i;
            if(i === 12){
                Ext.MessageBox.hide();
            }else{
                var val = i / 11;
                Ext.MessageBox.updateProgress(val, Math.round(100 * val) + '% 已完成进度');
                me.timer = Ext.defer(fn, 500);
            }
        };
        me.timer = Ext.defer(fn, 500);
}

/**
 * 隐藏请求等待进度条窗口
 */
function hideWaitMsg(){
	Ext.MessageBox.hide();
}

/**
 * 隐藏请求等待进度条窗口
 */
function hideTopWaitMsg(){
	top.Ext.MessageBox.hide();
}
/**
  *刷新显示
  *@param {}objPanel
  */
function showMsg(objPanel){
	if(objPanel == null || objPanel == ""){
		new Ext.LoadMask(document.body,{ 
		    msg:'正在加载数据中...', 
		    removeMask:true//完成后移除 
		}).show(); 
	}else{
		new Ext.LoadMask(objPanel.el, { msg: "正在加载数据中..." } ).show();
	}
}
/**
  *刷新隐藏
  *@param {}objPanel
  */
function hideMsg(objPanel){
	if(objPanel == null || objPanel == ""){
		new Ext.LoadMask(document.body,{ 
		    msg:'正在加载数据中...', 
		    removeMask:true//完成后移除 
		}).hide();
	}else{
		new Ext.LoadMask(objPanel.el,{ 
		    msg:'正在加载数据...', 
		    removeMask:true//完成后移除 
		}).hide();
	}
}

/**
 *载入form表单数据
 *@param {} form,url
 */
function loadFormData(forms,url){
	try{
		Ext.Ajax.timeout=180000;
		forms.getForm().load({
			url:url,	
			waitTitle:'提示',
			method:'post', 
	        waitMsg:'正在载入数据，请稍后...',  			
		    success:function(form, action){},                      
	   	    failure:function(form, action){}                  
		});
	}catch(e){
    	console.info(e);
 	}
}

/**
 *载入form表单数据并回调
 *@param {} form,url
 */
function loadFormDataCallBack(forms,url,fn){
	try{
		Ext.Ajax.timeout=180000;
		forms.getForm().load({
			url:url,	
			waitTitle:'提示',
			method:'post', 
	        waitMsg:'正在载入数据，请稍后...',  			
		    success:function(form, action){
	    		//回调事件
           		fn(form, action);
	        },                      
	   	    failure:function(form, action){}                  
		});
	}catch(e){
    	console.info(e);
 	}
}
/**
 *提交form表单数据
 *@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单
 */
function submitForm(subForm,url,grids,win,isHide,isReset){
	try{
		var items;
		if(null != win){
			items = Ext.ComponentQuery.query('button',win); 
		}
		if(subForm.form.isValid()){   
			top.Ext.Msg.confirm('提示','确定要提交当前表单信息内容？',function(btn){
				if(btn == 'yes'){
					///////////禁用window中组件按钮
					for(var it in items){
						items[it].disable();
					}
					subForm.form.submit({                    
				        url:url,
				        timeout:600000,/**设置超时时间10十分钟**/
				        params:{ajaxform:0},
				        waitTitle:'提示',
				        actionMethods:{ 
							read:'POST' 
						},
						method:'post', 
				        waitMsg:'正在保存数据，请稍后...',                      
				        success:function(form, action){    
				   			top.Ext.example.msg('提示', action.result.msg);//提示之后消失
				      		if(isReset){
				      			subForm.form.reset();  
				      		}                       
			           		if(null != win && "" != win){
			           			if(isHide){
			           				///////////激活window中组件按钮
									for(var it in items){
										items[it].enable();
									}
			           				win.hide();  
			           			}else{
			           				win.close(); 
			           			}
			           		} 
			           		if(null != grids && "" != grids){
			           			grids.getStore().reload();
			           		}
				      	},
				        failure:function(form, action){
				        	///////////激活window中组件按钮
							for(var it in items){
								items[it].enable();
							}
				      	}                  
				      });  
				}
			});
		}else { 
			msgTishi('请输入必填项');
		}
	}catch(e){
    	console.info(e);
 	}
}

/**
 *提交form表单数据
 *@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,fn:回调事件
 */
function submitFormCallFn(subForm,url,grids,win,isHide,isReset,fn){
	try{
		var items;
		if(null != win){
			items = Ext.ComponentQuery.query('button',win); 
		}
		if(subForm.form.isValid()){
			top.Ext.Msg.confirm('提示','确定要提交当前表单信息内容？',function(btn){
				if(btn == 'yes'){
				 ///////////禁用window中组件按钮
				 for(var it in items){
					 items[it].disable();
				 }
			   	 subForm.form.submit({                    
			        url:url,
			        timeout:600000,/**设置超时时间10十分钟**/
			        params:{ajaxform:0},
			        waitTitle:'提示',
			        actionMethods:{ 
						read:'POST' 
					},
					method:'post', 
			        waitMsg:'正在保存数据，请稍后...',                      
			        success:function(form, action){    
			   			top.Ext.example.msg('提示', action.result.msg);//提示之后消失
			      		if(isReset){
			      			subForm.form.reset();  
			      		}                       
		           		if(null != win && "" != win){
		           			if(isHide){
		           				///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
		           				win.hide();  
		           			}else{
		           				win.close(); 
		           			}
		           		} 
		           		if(null != grids && "" != grids){
		           			grids.getStore().reload();
		           		}
		           		//回调事件
		           		fn(form, action);
			      	},
			        failure:function(form, action){
			        	///////////激活window中组件按钮
						for(var it in items){
							items[it].enable();
						}
			      	}                  
			      });  
				}
			});
		}else { 
			msgTishi('请输入必填项');
		}
	}catch(e){
    	console.info(e);
 	}
}

/**
 *提交form表单数据
 *@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,params:追加参数提交
 */
function submitFormIncludeParams(subForm,url,grids,win,isHide,isReset,params){
	try{
		var items;
		if(null != win){
			items = Ext.ComponentQuery.query('button',win); 
		}
		if(subForm.form.isValid()){
			top.Ext.Msg.confirm('提示','确定要提交当前表单信息内容？',function(btn){
				if(btn == 'yes'){
				 ///////////禁用window中组件按钮
				 for(var it in items){
					 items[it].disable();
				 }
			   	 subForm.form.submit({                    
			        url:url,
			        timeout:600000,/**设置超时时间10十分钟**/
			        params:params,
			        actionMethods:{ 
						read:'POST' 
					},
					method:'post', 
			        waitTitle:'提示',
			        waitMsg:'正在保存数据，请稍后...',                      
			        success:function(form, action){    
			   			top.Ext.example.msg('提示', action.result.msg);//提示之后消失
			      		if(isReset){
			      			subForm.form.reset();  
			      		}                       
		           		if(null != win && "" != win){
		           			if(isHide){
		           				///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
		           				win.hide();  
		           			}else{
		           				win.close(); 
		           			}
		           		} 
		           		if(null != grids && "" != grids){
		           			grids.getStore().reload();
		           		}
			      	},
			        failure:function(form, action){
			        	///////////激活window中组件按钮
						for(var it in items){
							items[it].enable();
						}
			      	}                  
			      }); 
				}
			});
		}else{ 
			msgTishi('请输入必填项');
		}
	}catch(e){
    	console.info(e);
 	}
}

/**
 *提交form表单数据 并且返回form信息
 *@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,isCallForm:是否回调加载表单,callUrl:加载表单URL地址,callForm:回调表单名称
 */
function submitFormCallBack(subForm,url,grids,win,isHide,isReset,isCallForm,callUrl,callForm){
	try{
		var items;
		if(null != win){
			items = Ext.ComponentQuery.query('button',win); 
		}
		if(subForm.form.isValid()){  
			top.Ext.Msg.confirm('提示','确定要提交当前表单信息内容？',function(btn){
				if(btn == 'yes'){
				 ///////////禁用window中组件按钮
				 for(var it in items){
					 items[it].disable();
				 }
			   	 subForm.form.submit({                    
			        url:url,
			        timeout:600000,/**设置超时时间10十分钟**/
			        params:{ajaxform:0},
			        actionMethods:{ 
						read:'POST' 
					},
					method:'post', 
			        waitTitle:'提示',
			        waitMsg:'正在保存数据，请稍后...',                      
			        success:function(form, action){    
			   			top.Ext.example.msg('提示', action.result.msg);//提示之后消失
			      		if(isReset){
			      			subForm.form.reset();  
			      		}                       
		           		if(null != win && "" != win){
		           			if(isHide){
		           				///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
		           				win.hide();  
		           			}else{
		           				win.close(); 
		           			}
		           		} 
		           		if(null != grids && "" != grids){
		           			grids.getStore().reload();
		           		}
		           		if(isCallForm){
		           			loadFormData(callForm,callUrl);
		           		}
			      	},
			        failure:function(form, action){
			        	///////////激活window中组件按钮
						for(var it in items){
							items[it].enable();
						}
			      	}                  
			      }); 
				}
			});
		  }else { 
			 	msgTishi('请输入必填项');
		  }
	}catch(e){
    	console.info(e);
	}
}

/**
 *提交form表单数据 刷新TreeGrid
 *@param {} form,url,treeGrid,win,closeActionType,isExpandAll:展开TreeGrid
 */
function submitForm(subForm,url,grids,win,isHide,isReset,isExpandAll){
	try{
		var items;
		if(null != win){
			items = Ext.ComponentQuery.query('button',win); 
		}
		if(subForm.form.isValid()){
			top.Ext.Msg.confirm('提示','确定要提交当前表单信息内容？',function(btn){
				if(btn == 'yes'){
				 ///////////禁用window中组件按钮
				 for(var it in items){
					 items[it].disable();
				 }
			   	 subForm.form.submit({                    
			        url:url,
			        timeout:600000,/**设置超时时间10十分钟**/
			        params:{ajaxform:0},
			        waitTitle:'提示',
			        actionMethods:{ 
						read:'POST' 
					},
					method:'post', 
			        waitMsg:'正在保存数据，请稍后...',                      
			        success:function(form, action){    
			   			top.Ext.example.msg('提示', action.result.msg);//提示之后消失
			      		if(isReset){
			      			subForm.form.reset();  
			      		}                       
		           		if(null != win && "" != win){
		           			if(isHide){
		           				///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
		           				win.hide();  
		           			}else{
		           				win.close(); 
		           			}
		           		} 
		           		if(null != grids && "" != grids){
		           			grids.getStore().load();
		           		}
		           		if(isExpandAll){
		           			new Ext.util.DelayedTask(function(){  
						       grids.expandAll();
						    }).delay(1000);
		           		}
			      	},
			        failure:function(form, action){
			        	///////////激活window中组件按钮
						for(var it in items){
							items[it].enable();
						}
			      	}                  
			      });
				}                  
		      });
		  }else{ 
			 	msgTishi('请输入必填项');
		  }
	}catch(e){
    	console.info(e);
	}
}


/**
 *提交AjaxRequest表单数据
 *@param {} url,grids,params
 */
function ajaxRequest(url,grids,params,msg){
	try{
		showWaitMsg(msg);
		Ext.Ajax.request({  
			params:params == null || '' == params ? {}:params, 
		    url:url,  
		    timeout:600000,/**设置超时时间10十分钟**/
		    method:'post',
		    success:function(response,opts){
		    	hideWaitMsg();
		        var obj=Ext.decode(response.responseText);  
		        if(null != grids && "" != grids){
		  			grids.getStore().reload();
		  		} 
		  		if(typeof(obj.xt_pt_status) != "undefined"){
		    		if((obj.xt_pt_status == 777 || obj.xt_pt_status == 001 || obj.xt_pt_status != 888)){
		    			//不提示
		    			return;
		    		}
		    	}
		  		if(typeof(obj.msg) != "undefined"){
		  			msgTishi(obj.msg);
		  		}
		    },  
		    failure:function(response,opts){  
		    	hideWaitMsg();
		    	console.info('ajaxRequest---异常');
		    }  
		});
	}catch(e){
    	console.info(e);
	}
}

/**
 *提交AjaxRequest表单数据并回调方法
 *@param {} url,grids,params
 */
function ajaxRequestCallFn(url,grids,params,msg,fn){
	try{
		showWaitMsg(msg);
		Ext.Ajax.request({  
			params:params == null || '' == params ? {}:params, 
		    url:url,  
		    timeout:600000,/**设置超时时间10十分钟**/
		    method:'post',
		    success:function(response,opts){
		    	hideWaitMsg();
		        var obj=Ext.decode(response.responseText);  
		        if(null != grids && "" != grids){
		  			grids.getStore().reload();
		  		} 
		  		if(typeof(obj.xt_pt_status) != "undefined"){
		    		if((obj.xt_pt_status == 777 || obj.xt_pt_status == 001 || obj.xt_pt_status != 888)){
		    			//不提示
		    			return;
		    		}
		    	}
		  		if(typeof(obj.msg) != "undefined"){
		  			msgTishi(obj.msg);
		  		}
		    	//回调事件
	           	fn(response, opts);
		    },  
		    failure:function(response,opts){  
		    	hideWaitMsg();
		    	console.info('ajaxRequest---异常');
		    	//回调事件
	           	fn(response, opts);
		    }  
		});
	}catch(e){
    	console.info(e);
	}
}

/**
 *提交AjaxRequest表单数据 多grid提交
 *@param {} url,grids,params
 */
function ajaxReq(url,gridArray,params,msg){
	try{
		showWaitMsg(msg);
		Ext.Ajax.request({  
			params:params == null || '' == params ? {}:params, 
		    url:url,  
		    method:'post',  
		    timeout:600000,/**设置超时时间10十分钟**/
		    success:function(response,opts){
		    	hideWaitMsg();
		        var obj=Ext.decode(response.responseText);  
		        for(var i = 0; i < gridArray.length; i++){
		        	if(null != gridArray[i] && "" != gridArray[i]){
			  			gridArray[i].getStore().reload();
			  		}
		        }
		        if(typeof(obj.xt_pt_status) != "undefined"){
		    		if((obj.xt_pt_status == 777 || obj.xt_pt_status == 001 || obj.xt_pt_status != 888)){
		    			//不提示
		    			return;
		    		}
		    	}
		  		msgTishi(obj.msg);
		    },  
		    failure:function(response,opts){  
		    	hideWaitMsg();
		    	console.info('ajaxReq---异常');
		    }  
		});
	}catch(e){
    	console.info(e);
	}
}

/**
 * 下拉框Grid带筛选  开始
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
Ext.define('Ext.ux.ComboGrid',{
	extend:'Ext.form.ComboBox',
	alias:['widget.combogrid'],
	createPicker:function(){
		var me = this,picker, menuCls = Ext.baseCSSPrefix + 'menu', opts = Ext.apply({
			selModel:{
				mode:me.multiSelect ? 'SIMPLE' : 'SINGLE'
			},
			floating:true,
			hidden:true,
			ownerCt:me.ownerCt,
			cls:me.el.up('.' + menuCls) ? menuCls : '',
			store:me.store,
			displayField:me.displayField,
			focusOnToFront:false,
			pageSize:me.pageSize,
			bbar:Ext.create('Ext.PagingToolbar',{
				store:me.store,
				displayInfo:true,
				displayMsg:'本页显示 {0} - {1}，共计{2}条',
				emptyMsg:"没有符合条件的数据"
			})
		},me.listConfig,me.defaultListConfig);
		picker = me.picker = Ext.create('Ext.grid.Panel', opts);
		picker.getNode = function() {
			picker.getView().getNode(arguments);
		};
		console.log(me);
		/**me.mon(picker,{
			itemclick:me.onItemClick,
			refresh:me.onListRefresh,
			scope:me
		});
		me.mon(picker.getSelectionModel(),{
			selectionChange:me.onListSelectionChange,
			scope:me
		});*/
		return picker;
	}
});
/**
 * 下拉框Grid带筛选  结束
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
 
//信息提示
function msgTishi(msg){
	top.Ext.example.msg('提示', msg);//提示之后消失
}

//确定提示
function confirmTishi(msg){
	var flag = false;
	top.Ext.Msg.confirm('提示',msg,function(btn){
		if(btn == 'yes'){
			flag = true;
		}else{
			flag = false;
		}
	});
	return flag;
}

//手机端校验
function phoneLogin(){
	if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i)){
		return 'phone';
	}else{
		return 'pc';
	}
}

/**
 * 获取高度和宽度
 */
var clientWidth = 0;
var clientHeight = 0;
function reGetWidthAndHeight(){
	var cvh = Ext.getBody().getViewSize();
	clientWidth = cvh.width;
	clientHeight = cvh.height;
}

/**
 * 从缓存中清除Cookie
 * @param {} name
 */
function clearCookie(name) {
	var expdate = new Date();
	expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));
	setCookie(name, "", expdate);
}

/**
 * 获取IE版本
 * return ieVison:0未获取到IE版本,-1非IE,其他返回IE版本 1表示火狐
 */
var ieVison = 6;
function getnavigator(){
	if(navigator.userAgent.indexOf("MSIE")>0){ 
		if(navigator.userAgent.indexOf("MSIE 6.0")>0){ 
			ieVison = 6;
		}else if(navigator.userAgent.indexOf("MSIE 7.0")>0){
			ieVison = 7;
		}else if(navigator.userAgent.indexOf("MSIE 8.0")>0){
			ieVison = 8;
		}else if(navigator.userAgent.indexOf("MSIE 9.0")>0){
			ieVison = 9;
		}else if(navigator.userAgent.indexOf("MSIE 10.0")>0){
			ieVison = 10;
		}else if(navigator.userAgent.indexOf("MSIE 11.0")>0){
			ieVison = 11;
		}else{
			ieVison = 0;
		}
	}else{
		if(navigator.userAgent.indexOf("Firefox")>0){
			ieVison = 1;
		}else{
			ieVison = -1;
		}
	} 
	return ieVison;
}

//全选
function selectAll(grid){
	grid.getSelectionModel().selectAll();
}
//反选
function unSelectAll(grid){
	grid.getSelectionModel().clearSelections();
	grid.getView().refresh();
}
//刷新
function refreshGrid(grid){
	grid.getStore().reload();
}
/**打印**/
function printerGrid(grid){
	Ext.ux.grid.Printer.printAutomatically = false;
	Ext.ux.grid.Printer.print(grid);
}

/**导出**/
function exportExcel(grid,url){
	//封装表格数据
    var data=[];
    var store = grid.getStore();
    var columns = grid.columns;
    store.each(function(record){
    	//console.info(encodeURI(record.data));
        data.push(record.data);
    },this);
    //获取表头的dataIndex
    var headerIndex=[];
	for(var i = 0; i < columns.length; i++){
		if(columns[i].xtype == "gridcolumn"&& !columns[i].hidden && !columns[i].hiddenAncestor) {
			headerIndex.push(columns[i].dataIndex);
			//console.info("字段名加别名:"+columns[i].dataIndex+"AS"+columns[i].text+"宽度:"+columns[i].width+"是否隐藏列:"+columns[i].hidden+"是否隐藏列并注销列但该列可以使用"+columns[i].hiddenAncestor);
		}
	}
    //获取表头的Text
    var headText=[];
    for(var i = 0; i < columns.length; i++){
    	if(columns[i].xtype == "gridcolumn"&& !columns[i].hidden && !columns[i].hiddenAncestor) {
			headText.push(columns[i].text);
		}
	}
    if(!Ext.fly('frmDummy')){
        var frm = document.createElement('form');
        frm.id = 'frmDummy';
        frm.name = grid.getId();
        frm.className = 'x-hidden';
        document.body.appendChild(frm);
    }
    showWaitMsg("正在导出数据...");
    Ext.Ajax.request({
        disableCaching:true ,
        url:url,
        method:'POST',
        isUpload:true,
        timeout:600000,//十分钟
        form:Ext.fly('frmDummy'),
        params:{
            excleData:encodeURIComponent(Ext.encode(data)),
            excleHeader:encodeURIComponent(Ext.encode(headerIndex)),
            excleText:encodeURIComponent(Ext.encode(headText))
        }
    });
    hideWaitMsg();
}

//设置panel标题
function resetTitle(panel,isSetBackGround){
	var rpanel=[];
	for(var i in panel.items.items){
		if(panel.items.items[i].xtype=='form'){
			rpanel.push(panel.items.items[i]);
		}
	}
	for(var i in rpanel){
		var num = parseInt(i)+1;
		rpanel[i].setTitle("编号."+num);
		if(isSetBackGround){
			if(i%2==0){
				rpanel[i].setBodyStyle('background-color:#f5f5f5');
			}
		}
	}
}

//////////////////基本form查询////////////////////////
var searchForm;
/**
	panelPosition上部(north) ,中部(center), 左边(west) ,右部(east) ,底部(south)
	items 表单中元素
	isTop 表单所属位置
	labelPosition标签位置'top'上面 ，'left'左边 ，'right'右边
**/
function initSearchForm(panelPosition,items,isTop,labelPosition){
	var panelTop = "";
	if(isTop){
		panelTop = "top";
	}
	searchForm = Ext.create(panelTop+'Ext.FormPanel',{
		collapsible:false,
		collapsed:false,
		region:panelPosition,
		defaultType:'textfield',
		title:'查询区域',
//		height:'auto',
		fieldDefaults:{
	        labelWidth:70,
	        style:'margin-left:10px;padding:5px 5px 5px 5px',
	        labelAlign:labelPosition,
	        margin:'4 5 4 5'
	    },
		items:[items]
	});
}

////////////自定义form查询扩展////////////////////
/**
	panelPosition上部(north) ,中部(center), 左边(west) ,右部(east) ,底部(south)
	items 表单中元素
	isTop 表单所属位置
	labelPosition标签位置'top'上面 ，'left'左边 ，'right'右边
**/
function initSearchFormByUserdefined(panelPosition,items,isTop,labelPosition){
	var panelTop = "";
	if(isTop){
		panelTop = "top";
	}
	return Ext.create(panelTop+'Ext.FormPanel',{
		collapsible:false,
		collapsed:false,
		region:panelPosition,
		defaultType:'textfield',
		title:'查询区域',
//		height:'auto',
		fieldDefaults:{
	        labelWidth:70,
	        style:'margin-left:10px;padding:5px 5px 5px 5px',
	        labelAlign:labelPosition,
	        margin:'4 5 4 5'
	    },
		items:[items]
	});
}

//调用查询方法
function initSearch(store,url,searchForm){
	store.load({
		url:url,
		method:'post',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize,
			searchJson:Ext.encode(searchForm.getForm().getFieldValues())
		}
	});
}

//callSearchData简写csData
function cSData(){
	var comboData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['1','等于'],['2','大于等于'],['3','小于等于'],['4','大于'],['5','小于']]  
	});
	return comboData;
}

//页面加载完成
function overLoad(){ 
	if(document.readyState == 'complete'){
		setTimeout(function(){
		    Ext.get("loading").remove();
		    Ext.get("loading-mask").fadeOut({
		    	opacity:0.3,
			    easing:'easeOut',
			    duration:500,
			    remove:true,
			    useDisplay:false
			});
		}, 300);
	} 
} 

function loading(){
	/**
	setTimeout(
		function(){
			Ext.get("loading").remove();
		},
		1000
	)
	**/
}

/////////////////////////开始////////////////////////
//重新设置Grid中列值与initData配合使用
function initDataGrid(grid){
	grid.getStore().on('load',function(s,records){
		setTimeout(function() {
	        for(var i=0;i<store.getCount();i++){  
				store.getAt(i).commit();
			}
	    },500);
	})
}
//设置数据字典值，供Grid中使用
function initData(dataStore,value){
	//var xt_data_dictionary_name="加载中...";
	var xt_data_dictionary_name="∨";
	for(var i = 0; i < dataStore.getCount();i++){
		var xt_data_dictionary_id = dataStore.getAt(i).get("xt_data_dictionary_id");
		if(xt_data_dictionary_id == value){
			xt_data_dictionary_name = dataStore.getAt(i).get("xt_data_dictionary_name");
			break;
		}
	}
	return xt_data_dictionary_name;
}
//设置下拉框值非数据 字典值，供Grid中使用
//dataStore下拉框对象（Store），value字段值，combo_value_field下拉框字段值，combo_text_field下拉框显示值
function initComBoData(dataStore,value,combo_value_field,combo_text_field){
	//var xt_data_dictionary_name="加载中...";
	var field_name_value="∨";
	for(var i = 0; i < dataStore.getCount();i++){
		var field_name_ = dataStore.getAt(i).get(combo_value_field);
		if(field_name_ == value){
			field_name = dataStore.getAt(i).get(combo_text_field);
			break;
		}
	}
	return field_name;
}
/////////////////////////结束////////////////////////


/////////////////////////通用上传开始////////////////////
/**
 * method:上传操作
 * fieldid:附件编号
 * picid:附件上传后回显图片对象编号
 * flag:标识符号1正常（即窗体在frame里面）2最外层显示（即窗体显示在最外层）
 * validateparameter:校验非法参数组装字符串
 * validateSize:校验大小
 * xt_path_absolutek:平台路径配置中心键（自定义上传对绝路径使用）
 * xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
 * xt_path_relativek:平台路径配置中心键（自定义上传相对路径）
**/
var optuploadWin;
var optuploadForm;
function optupload(fieldid,picid,flag,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	if(flag == 2){
		optuploadForm = Ext.create('top.Ext.FormPanel',{
			waitMsgTarget:true,
			defaultType:'textfield',
			autoScroll:true,
			fieldDefaults:{
				labelWidth:70,
				labelAlign:'left',
				margin:'5 5 5 5',
				flex:1
			},
			/**新方法使用开始**/  
	        scrollable:true,  
	        scrollable:'x',
	        scrollable:'y',
	        /**新方法使用结束**/ 
			items:[
				{
					fieldLabel:'校验参数（pdf,png,jpg,bmp等等以逗号分隔）',
					xtype:'textfield',
					hidden:true,
					id:'validateparameter',
					name:'validateparameter',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'大小（格式如"1024-10240" 表示最小1024KB最大10240KB）',
					xtype:'textfield',
					hidden:true,
					id:'validateSize',
					name:'validateSize',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传绝路径使用）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_absolutek',
					name:'xt_path_absolutek',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传相对路径使用）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_relativek',
					name:'xt_path_relativek',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传路径 自定义URL地址）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_urlk',
					name:'xt_path_urlk',
					maxLength:32,
					anchor:'100%'
				},
				{
				    xtype:'filefield',
				    /**
				    hideLabel:true,
				    buttonOnly:true,
				    **/
				    emptyText:'请选择要上传的文件',
				    name:'picFile',
				    buttonText:'选择文件...',
				    anchor:'100%',
				    allowBlank:false,
				    buttonConfig:{
				        icon:uploadimg
				    }
				}
			]
		});
		if(null != validateparameter && '' != validateparameter && typeof(validateparameter) != "undefined"){
			sTopValue('validateparameter',validateparameter);
		}
		if(null != validateSize && '' != validateSize && typeof(validateSize) != "undefined"){
			sTopValue('validateSize',validateSize);
		}
		if(null != xt_path_relativek && '' != xt_path_relativek && typeof(xt_path_relativek) != "undefined"){
			sTopValue('xt_path_relativek',xt_path_relativek);
		}
		if(null != xt_path_absolutek && '' != xt_path_absolutek && typeof(xt_path_absolutek) != "undefined"){
			sTopValue('xt_path_absolutek',xt_path_absolutek);
		}
		if(null != xt_path_urlk && '' != xt_path_urlk && typeof(xt_path_urlk) != "undefined"){
			sTopValue('xt_path_urlk',xt_path_urlk);
		}
	}else{
		optuploadForm = Ext.create('Ext.FormPanel',{
			waitMsgTarget:true,
			defaultType:'textfield',
			autoScroll:true,
			fieldDefaults:{
				labelWidth:70,
				labelAlign:'left',
				margin:'5 5 5 5',
				flex:1
			},
			/**新方法使用开始**/  
	        scrollable:true,  
	        scrollable:'x',
	        scrollable:'y',
	        /**新方法使用结束**/ 
			items:[
				{
					fieldLabel:'校验参数（pdf,png,jpg,bmp等等以逗号分隔）',
					xtype:'textfield',
					hidden:true,
					id:'validateparameter',
					name:'validateparameter',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'大小（格式如"1024-10240" 表示最小1024KB最大10240KB）',
					xtype:'textfield',
					hidden:true,
					id:'validateSize',
					name:'validateSize',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传对绝路径使用）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_absolutek',
					name:'xt_path_absolutek',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传相对路径使用）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_relativek',
					name:'xt_path_relativek',
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'平台路径配置中心键（自定义上传路径 自定义URL地址）',
					xtype:'textfield',
					hidden:true,
					id:'xt_path_urlk',
					name:'xt_path_urlk',
					maxLength:32,
					anchor:'100%'
				},
				{
				    xtype:'filefield',
				    /**
				    hideLabel:true,
				    buttonOnly:true,
				    **/
				    emptyText:'请选择要上传的文件',
				    name:'picFile',
				    buttonText:'选择文件...',
				    anchor:'100%',
				    allowBlank:false,
				    buttonConfig:{
				        icon:uploadimg
				    }
				}
			]
		});
		if(null != validateparameter && '' != validateparameter && typeof(validateparameter) != "undefined"){
			sValue('validateparameter',validateparameter);
		}
		if(null != validateSize && '' != validateSize && typeof(validateSize) != "undefined"){
			sValue('validateSize',validateSize);
		}
		if(null != xt_path_absolutek && '' != xt_path_absolutek && typeof(xt_path_absolutek) != "undefined"){
			sValue('xt_path_absolutek',xt_path_absolutek);
		}
		if(null != xt_path_relativek && '' != xt_path_relativek && typeof(xt_path_relativek) != "undefined"){
			sValue('xt_path_relativek',xt_path_relativek);
		}
		if(null != xt_path_urlk && '' != xt_path_urlk && typeof(xt_path_urlk) != "undefined"){
			sValue('xt_path_urlk',xt_path_urlk);
		}
	}
	if(flag == 2){
		optuploadWin = Ext.create('top.Ext.Window',{
			layout:'fit',
			width:400,
			autoHeight:true,
			closable:false, 
			animateTarget:document.body,
			plain:true,
			modal:true,
			buttonAlign:'right',
			title:'附件上传',
			fieldDefaults:{
				labelWidth:70,
				labelAlign:'left',
				flex:1,
				margin:'5 5 5 5'
			},
			listeners:{
				minimize:function(win,opts){
					win.collapse();
				}
			},
			items:optuploadForm,
			buttons:[{
				text:'开始上传',
				itemId:'save',
				handler:function(button){
					if(optuploadForm.form.isValid()) {
						var items = Ext.ComponentQuery.query('button',optuploadWin); 
						///////////激活window中组件按钮
						for(var it in items){
							items[it].disable();
						}
			            optuploadForm.submit({
			                url:basePath+'/xtCommonController/upload',
			                timeout:600000,/**设置超时时间10十分钟**/
			                waitMsg:'正在上传中，请稍后...',
			                success:function(form, action){
			                	/**
			                    var tpl = new Ext.XTemplate(
			                        'File processed on the server.<br />',
			                        'Name: {fileName}<br />',
			                        'Size: {fileSize:fileSize}'
			                    );
			                    Ext.Msg.alert('Success', tpl.apply(o.result));
			                    **/
			                    /**动态改变容器中路径用该方法**/
			                    msgTishi(action.result.data.msg);
			                    if(action.result.data.jsonID != 0){
				                    top.Ext.getCmp(picid).getEl().dom.src = action.result.data.jsonValue;
				                    top.Ext.getCmp(fieldid).setValue(action.result.data.jsonID);
				                    optuploadWin.close();
			                    }else{
			                    	///////////激活window中组件按钮
									for(var it in items){
										items[it].enable();
									}
			                    }
			                },  
						    failure:function(response,opts){  
						    	///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
						    }  
			            });
			        }else{
			        	msgTishi('请选择图片');
			        }
				}
			},{
				text:'关闭',
				itemId:'close',
				handler:function(button){
					 optuploadWin.close();
				}
			}]
		});
	}else{
		//否则正常
		optuploadWin = Ext.create('Ext.Window',{
			layout:'fit',
			width:400,
			autoHeight:true,
			closable:false, 
			title:'附件上传',
			animateTarget:document.body,
			plain:true,
			modal:true,
			buttonAlign:'right',
			fieldDefaults:{
				labelWidth:70,
				labelAlign:'left',
				flex:1,
				margin:'5 5 5 5'
			},
			listeners:{
				minimize:function(win,opts){
					win.collapse();
				}
			},
			items:optuploadForm,
			buttons:[{
				text:'开始上传',
				itemId:'save',
				handler:function(button){
					if(optuploadForm.form.isValid()) {
						var items = Ext.ComponentQuery.query('button',optuploadWin); 
						///////////激活window中组件按钮
						for(var it in items){
							items[it].disable();
						}
			            optuploadForm.submit({
			                url:basePath+'/xtCommonController/upload',
			                waitMsg:'正在上传中，请稍后...',
			                success:function(form, action){
			                	/**
			                    var tpl = new Ext.XTemplate(
			                        'File processed on the server.<br />',
			                        'Name: {fileName}<br />',
			                        'Size: {fileSize:fileSize}'
			                    );
			                    Ext.Msg.alert('Success', tpl.apply(o.result));
			                    **/
			                    /**动态改变容器中路径用该方法**/
			                    msgTishi(action.result.data.msg);
			                    if(action.result.data.jsonID != 0){
				                    Ext.getCmp(picid).getEl().dom.src = action.result.data.jsonValue;
			                   		Ext.getCmp(fieldid).setValue(action.result.data.jsonID);
				                    optuploadWin.close();
			                    }else{
			                    	///////////激活window中组件按钮
									for(var it in items){
										items[it].enable();
									}
			                    }
			                },  
						    failure:function(response,opts){  
						    	///////////激活window中组件按钮
								for(var it in items){
									items[it].enable();
								}
						    }
			            });
			        }else{
			        	msgTishi('请选择图片');
			        }
				}
			},{
				text:'关闭',
				itemId:'close',
				handler:function(button){
					 optuploadWin.close();
				}
			}]
		});
	}
	optuploadWin.show();
}
/////////////////////////通用上传结束////////////////////

/**
 *ajaxFilePathBackRequest表单中单个或多个附件路径回显
 *@param {} url,params,flag（标识符号1正常（即窗体在frame里面）2最外层显示（即窗体显示在最外层））
 */
function ajaxFilePathBackRequest(url,params,flag){
	Ext.Ajax.request({  
		params:params == null || '' == params ? {}:params, 
	    url:url,  
	    method:'post',  
	    timeout:600000,/**设置超时时间10十分钟**/
	    success:function(response,opts){
	        var obj=Ext.decode(response.responseText); 
	        for(var i = 0; i < obj.items.length; i++){
	        	if(flag == 2){
	        		if(typeof(top.Ext.getCmp(obj.items[i].field_name+'_pic')) != 'undefined'){
	        			top.Ext.getCmp(obj.items[i].field_name+'_pic').getEl().dom.src = obj.items[i].xt_attachmentPath;
	        		}
	        	}else{
	        		if(typeof(Ext.getCmp(obj.items[i].field_name+'_pic')) != 'undefined'){
	        			Ext.getCmp(obj.items[i].field_name+'_pic').getEl().dom.src = obj.items[i].xt_attachmentPath;
	        		}
	        	}
	        } 
	    },  
	    failure:function(response,opts){  
	    	var obj=Ext.decode(response.responseText); 
	    }  
	});
}
/**
  *初始化附件右键
  *flag:标识符号1正常（即窗体在frame里面）2最外层显示（即窗体显示在最外层）
  *isUpAndDelete:表示是否拥有上传和删除功能1是2否（即明细页面使用） 如明细不需要上传 和删除功能
  *validateparameter:校验非法参数组装字符串
  *validateSize:校验大小
  *xt_path_absolutek:平台路径配置中心键（自定义上传绝对路径使用）
  *xt_path_relativek:平台路径配置中心键（自定义上传相对路径使用）
  *xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
**/
function initFileRight(fieldid,picid,flag,isUpAndDelete,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	if(isUpAndDelete == 2){
		if(flag == 1){
			var contextmenuFile = new Ext.menu.Menu({
				items:[{
					text:'下 载',
					glyph:0xf019,
					handler:function(){
						var xt_attachment_id = Ext.getCmp(fieldid).getValue();
						downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
					}
				},
				'-',
				{
					text:'复制文件地址',
					glyph:0xf0c5,
					handler:function(){
						var url_path = Ext.getCmp(picid).getEl().dom.src;
						top.Ext.Msg.alert("复制文件地址",url_path);
					}
				},
				'-',
				{
					text:'查 看',
					glyph:0xf1c5,
					handler:function(){
						var url_path = Ext.getCmp(picid).getEl().dom.src;
						getimghw(url_path);
					}
				}]
			});
			Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
				e.preventDefault(); 
				contextmenuFile.showAt(e.getXY());
			});
		}else{
			var contextmenuFile = new top.Ext.menu.Menu({
				items:[{
					text:'下 载',
					glyph:0xf019,
					handler:function(){
						var xt_attachment_id = top.Ext.getCmp(fieldid).getValue();
						downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
					}
				},
				'-',
				{
					text:'复制文件地址',
					glyph:0xf0c5,
					handler:function(){
						var url_path = top.Ext.getCmp(picid).getEl().dom.src;
						top.Ext.Msg.alert("复制文件地址",url_path);
					}
				},
				'-',
				{
					text:'查 看',
					glyph:0xf1c5,
					handler:function(){
						var url_path = top.Ext.getCmp(picid).getEl().dom.src;
						getimghw(url_path);
					}
				}]
			});
			top.Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
				e.preventDefault(); 
				contextmenuFile.showAt(e.getXY());
			});
		}
	}else{
		if(flag == 2){
			var contextmenuFile = new top.Ext.menu.Menu({
				items:[{
					text:'上 传',
					glyph:0xf093,
					handler:function(){
						optupload(fieldid,picid,2,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
					}
				},{
					text:'下 载',
					glyph:0xf019,
					handler:function(){
						var xt_attachment_id = top.Ext.getCmp(fieldid).getValue();
						downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
					}
				},
				'-',
				{
					text:'删 除',
					glyph:0xf014,
					handler:function(){
						top.Ext.getCmp(picid).getEl().dom.src = bsdefimg;
				        top.Ext.getCmp(fieldid).setValue('');
					}
				},
				'-',
				{
					text:'复制文件地址',
					glyph:0xf0c5,
					handler:function(){
						var url_path = top.Ext.getCmp(picid).getEl().dom.src;
						top.Ext.Msg.alert("复制文件地址",url_path);
					}
				},
				'-',
				{
					text:'查 看',
					glyph:0xf1c5,
					handler:function(){
						var url_path = top.Ext.getCmp(picid).getEl().dom.src;
						getimghw(url_path);
					}
				}]
			});
			top.Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
				e.preventDefault(); 
				contextmenuFile.showAt(e.getXY());
			});
		}else{
			var contextmenuFile = new Ext.menu.Menu({
				items:[{
					text:'上 传',
					glyph:0xf093,
					handler:function(){
						optupload(fieldid,picid,1,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
					}
				},{
					text:'下 载',
					glyph:0xf019,
					handler:function(){
						var xt_attachment_id = Ext.getCmp(fieldid).getValue();
						downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
					}
				},
				'-',
				{
					text:'删 除',
					glyph:0xf014,
					handler:function(){
						Ext.getCmp(picid).getEl().dom.src = bsdefimg;
				        Ext.getCmp(fieldid).setValue('');
					}
				},
				'-',
				{
					text:'复制文件地址',
					glyph:0xf0c5,
					handler:function(){
						var url_path = Ext.getCmp(picid).getEl().dom.src;
						top.Ext.Msg.alert("复制文件地址",url_path);
					}
				},
				'-',
				{
					text:'查 看',
					glyph:0xf1c5,
					handler:function(){
						var url_path = Ext.getCmp(picid).getEl().dom.src;
						getimghw(url_path);
					}
				}]
			});
			Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
				e.preventDefault(); 
				contextmenuFile.showAt(e.getXY());
			});
		}
	}
}

/**
  *初始化附件右键
  *isUpAndDelete表示是否拥有上传和删除功能1是2否（即明细页面使用） 如明细不需要上传 和删除功能
  *validateparameter:校验非法参数组装字符串
  *validateSize:校验大小 格式：1024-10240
  *xt_path_absolutek:平台路径配置中心键（自定义上传绝对路径使用）
  *xt_path_relativek:平台路径配置中心键（自定义上传相对路径使用）
  *xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
**/
function initTopFileRight(fieldid,picid,flag,isUpAndDelete,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk){
	if(isUpAndDelete == 2){
		var contextmenuFile = new top.Ext.menu.Menu({
			items:[{
				text:'下 载',
				glyph:0xf019,
				handler:function(){
					var xt_attachment_id = top.Ext.getCmp(fieldid).getValue();
					downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
				}
			},
			'-',
			{
				text:'复制文件地址',
				glyph:0xf0c5,
				handler:function(){
					var url_path = top.Ext.getCmp(picid).getEl().dom.src;
					top.Ext.Msg.alert("复制文件地址",url_path);
				}
			},
			'-',
			{
				text:'查 看',
				glyph:0xf1c5,
				handler:function(){
					var url_path = top.Ext.getCmp(picid).getEl().dom.src;
					getimghw(url_path);
				}
			}]
		});
		top.Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
			e.preventDefault(); 
			contextmenuFile.showAt(e.getXY());
		});
	}else{
		var contextmenuFile = new top.Ext.menu.Menu({
			items:[{
				text:'上 传',
				glyph:0xf093,
				handler:function(){
					optupload(fieldid,picid,2,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
				}
			},{
				text:'下 载',
				glyph:0xf019,
				handler:function(){
					var xt_attachment_id = top.Ext.getCmp(fieldid).getValue();
					downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment_id);
				}
			},
			'-',
			{
				text:'删 除',
				glyph:0xf014,
				handler:function(){
					top.Ext.getCmp(picid).getEl().dom.src = bsdefimg;
			        top.Ext.getCmp(fieldid).setValue('');
				}
			},
			'-',
			{
				text:'复制文件地址',
				glyph:0xf0c5,
				handler:function(){
					var url_path = top.Ext.getCmp(picid).getEl().dom.src;
					top.Ext.Msg.alert("复制文件地址",url_path);
				}
			},
			'-',
			{
				text:'查 看',
				glyph:0xf1c5,
				handler:function(){
					var url_path = top.Ext.getCmp(picid).getEl().dom.src;
					getimghw(url_path);
				}
			}]
		});
		top.Ext.getCmp(picid).getEl().on('contextmenu',function(e){ 
			e.preventDefault(); 
			contextmenuFile.showAt(e.getXY());
		});
	}
}
/**
 * 通过iFrame实现类ajax文件下载
 */
function downOrExport(url) {
	var exportIframe = document.createElement('iframe');
	exportIframe.src = url;
	exportIframe.style.display = "none";
	document.body.appendChild(exportIframe);
}


///////////////初始化右键///////////////
function initrightgridcontextmenu(gd,contextmenu,items){
	gd.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault();
		for(var i = 0 ; i < items.length; i++){
			Ext.getCmp(items[i]).setDisabled(false);
		} 
		contextmenu.showAt(e.getXY());
	});
	gd.on("headercontextmenu", function(view,colIndex,e){
		gd.getSelectionModel().clearSelections();
		gd.getView().refresh();
    	e.preventDefault(); 
    	for(var i = 0 ; i < items.length; i++){
			Ext.getCmp(items[i]).setDisabled(true);
		} 
		contextmenu.showAt(e.getXY());
	});
	gd.on("containercontextmenu", function(view,e,opt){
		gd.getSelectionModel().clearSelections();
		gd.getView().refresh();
    	e.preventDefault(); 
    	for(var i = 0 ; i < items.length; i++){
			Ext.getCmp(items[i]).setDisabled(true);
		} 
		contextmenu.showAt(e.getXY());
	});
}

///////////////////////////////////////TreePanel筛选方法开始/////////////////////////////////
//清空筛选
function clearFilter(grid){
	var view = grid.getView();
    grid.getRootNode().cascadeBy(function(tree, view) {
        var uiNode = view.getNodeByRecord(this);
        if (uiNode) {
            Ext.get(uiNode).setDisplayed('table-row');
        }
    }, null,[this, view]);
}
//根据字段筛选
function filterBy(grid,text, by){
	clearFilter(grid);
    var view = grid.getView(),nodesAndParents = [];
    // 找到匹配的节点并展开.
    // 添加匹配的节点和他们的父节点到nodesAndParents数组.
    grid.getRootNode().cascadeBy(function(tree, view) {
        var currNode = this;

        if (currNode && currNode.data[by] && currNode.data[by].toString().toLowerCase().indexOf(text.toLowerCase()) > -1) {
            grid.expandPath(currNode.getPath());
            while(currNode.parentNode) {
                nodesAndParents.push(currNode.id);
                currNode = currNode.parentNode;
            }
        }
    },null,[grid, view]);
    //将不在nodesAndParents数组中的节点隐藏
    grid.getRootNode().cascadeBy(function(tree, view) {
        var uiNode = view.getNodeByRecord(this);
        if(uiNode && !Ext.Array.contains(nodesAndParents, this.id)) {
            Ext.get(uiNode).setDisplayed('none');
        }
    },null,[grid, view]);
}
//根据text筛选
function filterByText(grid,text){
	this.filterBy(text,'text');
}
///////////////////////////////////////TreePanel筛选方法结束/////////////////////////////////


function getimghw(src){
	try{
		showTopLoading("正在加载中...");
		var img_url = src+'?'+Date.parse(new Date());
		//创建对象
		var img = new Image();
		//改变图片的src
		img.src = img_url;
		// 加载完成执行
		img.onload = function(){
			var w = img.width;
			var h = img.height;
			if(w>1000){
				w = 800;
			}
			if(h > 400){
				h = 400;
			}
			if(w < 100){
			    w = 260;
			}
			if(h < 100){
				h = 200;
			}
			Ext.create('top.Ext.Window',{
				width:w,
				height:h,
				maximizable:true,
				minimizable:true,
				listeners:{
					minimize:function(win,opts){
						if(!win.collapse()){
							win.collapse();
						}else{
							win.expand();
						}
					}
				},
				animateTarget:document.body,
				plain:true,
				modal:true,
				autoScroll:true,
				buttonAlign:'right',
				title:'图片预览',
				headerPosition:'left',
				fieldDefaults:{
					flex:1,
					margin:'5 5 5 5'
				},
				html:'<img src="'+img_url+'">'
			}).show();
			hideTopWaitMsg();
		};
		img.onerror = function(){
			hideTopWaitMsg();
			msgTishi('该图片不能预览');
		};
	}catch(e){
		//非法即不满足图片
		msgTishi('该图片不能预览');
	}
}


//合并单元格待验证
/**
 * 
 * 合并单元格
 * @param {} grid  要合并单元格的grid对象
 * @param {} cols  要合并哪几列 [1,2,4]
 */
var mergeCells = function(grid,cols){
//var arrayTr=document.getElementById(grid.getId()+"-body").firstChild.firstChild.firstChild.getElementsByTagName('tr');	
var arrayTr=document.getElementById(grid.getId()+"-body").getElementsByTagName('tr');
var arrayTab = document.getElementById(grid.getId()).getElementsByTagName('table');//得到页面上的table
var tab ; //定义最外边的table
var addfCount = 0 ;//定义addf()调用的次数
if(arrayTab.length > 0 ){//表示该班该周有多个课表
	tab = arrayTab[0];
	var tabBody = tab.getElementsByTagName('tbody');
	for(var i = 0 ; i < arrayTr.length ; i++){
	}
}
var trCount = arrayTr.length;
var arrayTd;
var td;
var merge = function(rowspanObj,removeObjs){ //定义合并函数
	if(rowspanObj.rowspan != 1){
		addfCount = addfCount +1;
		arrayTd =arrayTr[rowspanObj.tr].getElementsByTagName("td"); //合并行
		td=arrayTd[rowspanObj.td-1];
		td.rowSpan=rowspanObj.rowspan;
		td.vAlign="middle";				
		td.style.color='rgb(255, 174, 2)';
		td.style.backgroundColor ='antiquewhite';
		if(addfCount==1){
			td.style.backgroundColor ='antiquewhite';
		}else if(addfCount==2){
			td.style.backgroundColor ='rgb(250, 215, 236)';
		}
		Ext.each(removeObjs,function(obj){ //隐身被合并的单元格
//        	arrayTab = arrayTr[obj].getElementsByTagName("table");
			arrayTd =arrayTr[obj.tr].getElementsByTagName("td");
//          arrayTd[obj.td-1].style.display='none';	
//			arrayTd[obj.td-1].style.color='rgb(255, 174, 2)';border-top-color: rgb(250, 235, 215);
			arrayTd[col-1].innerHTML='';
			if(addfCount==1){
				arrayTd[obj.td-1].style.backgroundColor='antiquewhite';
			}else if(addfCount==2){
				arrayTd[obj.td-1].style.backgroundColor ='rgb(250, 215, 236)';
			}
		});
	}	
};	
var rowspanObj = {}; //要进行跨列操作的td对象{tr:1,td:2,rowspan:5}	
var removeObjs = []; //要进行删除的td对象[{tr:2,td:2},{tr:3,td:2}]
var col;
Ext.each(cols,function(colIndex){ //逐列去操作tr
	var rowspan = 1;
	var divHtml = null;//单元格内的数值		
	for(var i=0;i<trCount;i++){  //i=0表示表头等没用的行,通常从1开始
		arrayTd = arrayTr[i].getElementsByTagName("td");
		var cold=0;
		Ext.each(arrayTd,function(Td){ //获取RowNumber列和check列
			if(Td.getAttribute("class").indexOf("x-grid-cell-special") != -1)
				cold++;								
		});
		col=colIndex+cold;//跳过RowNumber列和check列
		if(!divHtml){
			divHtml = arrayTd[col-1].innerHTML;
			rowspanObj = {tr:i,td:col,rowspan:rowspan}
		}else{
			var cellText = arrayTd[col-1].innerHTML;
			var addf=function(){ 
				rowspanObj["rowspan"] = rowspanObj["rowspan"]+1;
				removeObjs.push({tr:i,td:col});
				if(i==trCount-1)
					merge(rowspanObj,removeObjs);//执行合并函数
			};
			var mergef=function(){
				merge(rowspanObj,removeObjs);//执行合并函数
				divHtml = cellText;
				rowspanObj = {tr:i,td:col,rowspan:rowspan}
				removeObjs = [];
			};
			if(cellText == divHtml){
				if(colIndex!=cols[0]){ 
					var leftDisplay=arrayTd[col-2].style.display;//判断左边单元格值是否已display
					if(leftDisplay=='none')
							addf();	
						else
							mergef();							
					}else
						addf();											
				}else
					mergef();			
			}
		}
	});	
};

/////////////////////////////验证框开始////////////////////////
//平台自带如：vtype: "email"邮件    ，alpha：希腊数字 ，  alphanum：字母和数字，url：网址
//自定义验证ip地址
Ext.apply(Ext.form.field.VTypes, {
    IPAddress: function (v) {
        return /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(v);
    },
    IPAddressText: '只能输入ip地址',
    IPAddressMask: /[\d\.]/i
});
/**用法
{
    vtype: "IPAddress"
}
**/
/////////////////////////////验证框结束////////////////////////