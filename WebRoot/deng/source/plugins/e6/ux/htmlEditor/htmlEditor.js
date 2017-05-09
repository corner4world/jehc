Ext.ns('deng');
/**
* 获取项目根路径
* */
deng.bp = function(){
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
};
/**
* 获取必填项提示
* */
deng.getStar = function (){
    return '<span style="color:#FF0000;">*</span>';
};
Ext.define('Ext.deng.form.HtmlEditorImage', {
    extend:'Ext.util.Observable',
    alias:'widget.deng_form_HtmlEditorImage',
    langTitle:'插入图片',
    langIconCls:'heditImgIcon',
    init:function(view) {
        var scope = this;
        view.on('render', function () {
            scope.onRender(view);
        });
    },
    /**
    * 添加"插入图片"按钮
    * */
    onRender:function (view) {
        var scope = this;
        view.getToolbar().add('-',{
            iconCls:scope.langIconCls,
            tooltip:{
                title:scope.langTitle,
                width:250
            },
            glyph:0xf03e,
            handler:function (){
                scope.showImgWindow(view);
            }
        });
    },
    /**
    * 显示"插入图片"窗体
    * */
    showImgWindow:function(view) {
        var scope = this;
        Ext.create('Ext.window.Window',{
            width:400,
            /**height:305,**/
			closable:false, 
            title:scope.langTitle,
            layout:'fit',
            autoShow:true,
            modal:true,
            resizable:false,
            maximizable:false,
            constrain:true,
            plain:true,
            enableTabScroll:true,
            border:false,
            items:[{
                xtype:'tabpanel',
                enableTabScroll:true,
                bodyPadding:'1 1 1 1',
                tabPosition:'left',
                items:[{
                    title:'上传本地图片',
                    items:[{
                        xtype:'form',
                        layout:'column',
                        autoScroll:true,
                        border:false,
                        defaults:{
                            columnWidth:1,
                            labelWidth:70,
                            labelAlign:'right',
                            padding:'5 5 5 5',
                            allowBlank:false
                        },
                        items:[{
                            xtype:'fileuploadfield',
                            fieldLabel:'选择文件',
                            beforeLabelTextTpl: deng.getStar(),
                            buttonText:'请选择...',
                            name:'upload',
                            emptyText:'请选择图片',
                            blankText:'图片不能为空',
                            listeners:{
                                change:function (view, value, eOpts) {
                                    scope.uploadImgCheck(view, value);
                                }
                            }
                        }, 
                        {
                            xtype:'fieldcontainer',
                            fieldLabel:'图片大小',
                            layout:'hbox',
                            defaultType:'numberfield',
                            defaults:{
                                flex:1,
                                labelWidth:20,
                                labelAlign:'right',
                                allowBlank:true
                            },
                            items:[{
                                /**fieldLabel:'宽',**/
                                emptyText:'宽度',
                                name:'width',
                                minValue:1
                            },{
                                /**fieldLabel:'高',**/
                                emptyText:'高度',
                                name:'height',
                                minValue:1
                            }]
                        },{
                            xtype:'textfield',
                            fieldLabel:'图片说明',
                            name:'content',
                            allowBlank:true,
                            maxLength:100,
                            emptyText:'请输入图片说明'
                        },{
                            columnWidth:1,
                            xtype:'fieldset',
                            title:'上传说明',
                            padding:'5 5 5 15',
                            layout:{
                                type:'table',
                                columns:1
                            },
                            collapsible:false,/**是否可折叠**/
                            defaultType:'label',/**默认的Form表单组件**/
                            items:[{
                                html:'1.上传图片不超过100KB'
                            },{
                                html:'2.支持以下格式:jpg,jpeg,png,gif'
                            },{
                                html:'3.上传图片之后耐心等待'
                            }]
                        }],
                        buttons:[{
                            text:'保存',
                            action:'btn_save',
                            icon:saveIcon,
                            handler:function(btn){
                                scope.saveUploadImg(btn, view);
                            }
                        },{
                            text:'取消',
                            icon:backIcon,
                            handler:function (btn) {
                                btn.up('window').close();
                            }
                        }]
                    }]
                }, {
                    title:'链接网络图片',
                    items:[{
                        xtype:'form',
                        layout:'column',
                        autoScroll:true,
                        border:false,
                        defaults:{
                            columnWidth:1,
                            labelWidth:70,
                            labelAlign:'right',
                            padding:'5 5 5 5',
                            allowBlank:false
                        },
                        items:[{
                            xtype:'textfield',
                            fieldLabel:'图片地址',
                            beforeLabelTextTpl:deng.getStar(),
                            name:'url',
                            emptyText:'请输入支持外链的长期有效的图片URL',
                            blankText:'图片地址不能为空',
                            vtype:'url'
                        },{
                            xtype:'fieldcontainer',
                            fieldLabel:'图片大小',
                            layout:'hbox',
                            defaultType:'numberfield',
                            defaults:{
                                flex:1,
                                labelWidth:20,
                                labelAlign:'right',
                                allowBlank:true
                            },
                            items:[{
                                /**fieldLabel:'宽',**/
                                emptyText:'宽度',
                                name:'width',
                                minValue:1
                            },{
                                /**fieldLabel:'高',**/
                                emptyText:'高度',
                                name:'height',
                                minValue:1
                            }]
                        },{
                            xtype:'textfield',
                            fieldLabel:'图片说明',
                            name:'content',
                            allowBlank:true,
                            maxLength:100,
                            emptyText:'请输入图片说明'
                        },{
                            columnWidth:1,
                            xtype:'fieldset',
                            title:'上传说明',
                            padding:'5 5 5 15',
                            layout:{
                                type:'table',
                                columns:1
                            },
                            collapsible:false,/**是否可折叠**/
                            defaultType:'label',/**默认的Form表单组件**/
                            items:[{
                                html:'1.上传图片不超过100KB'
                            },{
                                html:'2.支持以下格式:jpg,jpeg,png,gif'
                            },{
                                html:'3.上传图片之后耐心等待'
                            }]
                        }],
                        buttons:[{
                            text:'保存',
                            action:'btn_save',
                            icon:saveIcon,
                            handler:function (btn) {
                                scope.saveRemoteImg(btn, view);
                            }
                        },{
                            text:'取消',
                            icon:backIcon,
                            handler:function (btn) {
                                btn.up('window').close();
                            }
                        }]
                    }]
                }]
            }]
        });
    },
    /**
    * 上传图片验证
    **/
    uploadImgCheck:function(fileObj, fileName){
        var scope = this;
        /**图片类型验证**/
        if(!(scope.getImgTypeCheck(scope.getImgHZ(fileName)))) {
            msgTishi("上传图片类型有误，请重新上传!");
            fileObj.reset();/**清空上传内容**/
            return;
        }
    },
    /**
    * 获取图片后缀(小写)
    * */
    getImgHZ:function(imgName){
        /**后缀**/
        var hz = '';
        /**图片名称中最后一个.的位置**/
        var index = imgName.lastIndexOf('.');
        if(index != -1){
            /**后缀转成小写**/
            hz = imgName.substr(index + 1).toLowerCase();
        }
        return hz;
    },
    /**
    * 图片类型验证
    * */
    getImgTypeCheck:function (hz){
        var typestr = 'jpg,jpeg,png,gif';
        /**图片类型**/
        var types = typestr.split(','); 
        for(var i = 0; i < types.length; i++){
            if (hz == types[i]) {
                return true;
            }
        }
        return false;
    },
    /**
    * 上传图片
    * */
    saveUploadImg:function (btn, view){
        var scope = this;
        var windowObj = btn.up('window');/**获取Window对象**/
        var formObj = btn.up('form');/**获取Form对象**/
        if(formObj.isValid()){ 
        	/**验证Form表单**/
            formObj.form.doAction('submit',{
                url:uploadURL() + '/',
                method:'POST',
                submitEmptyText:false,
                waitMsg:'正在上传图片,请稍候...',
                timeout:60000,
                success:function(response, options){
                    var result = options.result;
                    if(!result.success){
                        msgTishi(result.msg);
                        return;
                    }
                    scope.insertImg(view, result.data);
                    windowObj.close();/**关闭窗体**/
                },
                failure:function(response, options){
                    msgTishi(options.result.msg);
                }
            });
        }else{
        	msgTishi("请选择图片文件");
        }
    },
    /**
    * 保存远程的图片
    * */
    saveRemoteImg:function(btn, view){
        var scope = this;
        var windowObj = btn.up('window');/**获取Window对象**/
        var formObj = btn.up('form');/**获取Form对象**/
        if(formObj.isValid()){
        	//验证Form表单
            var values = formObj.getValues();/**获取Form表单的值**/
            scope.insertImg(view, values);
            windowObj.close();/**关闭窗体**/
        }else{
        	msgTishi("请输入图片地址");
        }
    },
    /**
    * 插入图片
    * */
    insertImg:function(view, data){
        var url = data.url;
        var content = data.content;
        var width = data.width;
        var height = data.height;
        var str = '<img src="' + url + '" border="0" ';
        if (content != undefined && content != null && content != '') {
            str += ' title="' + content + '" ';
        }
        if (width != undefined && width != null && width != 0) {
            str += ' width="' + width + '" ';
        }
        if (height != undefined && height != null && height != 0) {
            str += ' height="' + height + '" ';
        }
        str += ' />';
        view.insertAtCursor(str);
    }
});