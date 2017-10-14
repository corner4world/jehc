Ext.define('App.ux.form.field.DateTime', {  
    extend:'Ext.form.field.Date',  
    alias: 'widget.datetimefield',
    requires: ['App.ux.picker.DateTime'],
  
    /** 
     * @cfg {String} format 
     * The default date format string which can be overriden for localization support. The format must be valid 
     * according to {@link Ext.Date#parse}. 
     */  
    format : "Y-m-d H:i:s",
   
    /** 
     * @cfg {String} altFormats 
     * Multiple date formats separated by "|" to try when parsing a user input value and it does not match the defined 
     * format. 
     */  
    altFormats : "Y-m-d H:i:s",
    
    editable: false,
    
    /**
    * Do not close picker when clicking on time controls.
    */
    /*
	collapseIf: function(e) {
        var me = this;
        me.callParent(arguments);
        // 点击了事件
        if (e.within(me.picker.footerEl)) {
            //me.collapse();
            console.log("collapse");
        }
    },
    */
    createPicker: function() {
        
        var me = this,  
            format = Ext.String.format;
  
        return new App.ux.picker.DateTime({
            pickerField: me,
            floating: true,
            focusable: false,
			hidden: true,
			minDate: me.minValue,
			maxDate: me.maxValue,
			disabledDatesRE: me.disabledDatesRE,
			disabledDatesText: me.disabledDatesText,
			disabledDays: me.disabledDays,
			disabledDaysText: me.disabledDaysText,
			format: me.format,
			showToday: me.showToday,
			startDay: me.startDay,
			minText: format(me.minText, me.formatDate(me.minValue)),
			maxText: format(me.maxText, me.formatDate(me.maxValue)),
			listeners: {
				scope: me,
				select: me.onSelect,
                // 双击日历天隐藏选择器
                dblclick: {
                    element: 'eventEl',
                    fn: function() {
                        me.collapse();
                    }
                }
			},
			keyNavConfig: {
				esc: function() {
					me.collapse();
				}
			}
        });
    },
    /**
     * 复写方法，去掉collapse调用
     */
    onSelect: function(m, d) {
        var me = this;
        me.setValue(d);
        me.fireEvent('select', me, d);
    },
  
    /** 
     * @private 
     */  
    onExpand: function() {
        var value = this.getValue();
        // 多传一个参数，从而避免时分秒被忽略。
        this.picker.setValue(Ext.isDate(value) ? value : new Date(), true);  
    }
});  