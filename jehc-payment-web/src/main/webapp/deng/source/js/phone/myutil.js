/*公共类*/
Ext.define('myUtil', {
     statics: {
        /*为Ext.Viewport添加一个消息提示组件*/
         addMessage: function () {
              Ext.Viewport.setMasked({
                 xtype: 'loadmask',
                  cls: 'message',
                 transparent: true,
                 indicator: false
             });
             this.hideMessage();
         },
         /*显示一个消息提示*/
        showMessage: function (mes, autoHide) {
            var me = this, message = this.getMessage();
             message.setMessage(mes);
             message.show();
             //是否自动关闭提示
             if (autoHide) {
                 setTimeout(function () {
                    message.hide();
                },
                500);
            }
         },
         /*隐藏消息提示*/
         hideMessage: function () {
             this.getMessage().hide();
         },
         //消息组件
         getMessage: function () {
             return Ext.Viewport.getMasked();
         },
         //验证模型
         valid: function (model, from) {
             var tmpModel = Ext.create(model),
             me = this,
             errors, valid;
             from.updateRecord(tmpModel);
             errors = tmpModel.validate();
            valid = errors.isValid();
            if (!valid) {
                errors.each(function (err) {
                     me.showMessage(err.getMessage(), true);
                     return;
                });
            }
             return valid;
         }
     }
 });