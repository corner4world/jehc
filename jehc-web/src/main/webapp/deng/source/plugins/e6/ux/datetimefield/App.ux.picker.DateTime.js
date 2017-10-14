Ext.define('App.ux.picker.DateTime', {  
    extend: 'Ext.picker.Date',
    alias: 'widget.dateptimeicker',
    okText :'确定',
    timeLabel: '时分秒',
    childEls: [
		'innerEl', 'eventEl', 'prevEl', 'nextEl', 'middleBtnEl', 'timeEl', 'footerEl'
	],
  	renderTpl: [
        '<div id="{id}-innerEl" data-ref="innerEl">',
            '<div class="{baseCls}-header">',
                '<div id="{id}-prevEl" data-ref="prevEl" class="{baseCls}-prev {baseCls}-arrow" role="button" title="{prevText}"></div>',
                '<div id="{id}-middleBtnEl" data-ref="middleBtnEl" class="{baseCls}-month" role="heading">{%this.renderMonthBtn(values, out)%}</div>',
                '<div id="{id}-nextEl" data-ref="nextEl" class="{baseCls}-next {baseCls}-arrow" role="button" title="{nextText}"></div>',
            '</div>',
            '<table role="grid" id="{id}-eventEl" data-ref="eventEl" class="{baseCls}-inner" {%',
                // If the DatePicker is focusable, make its eventEl tabbable.
                // Note that we're looking at the `focusable` property because
                // calling `isFocusable()` will always return false at that point
                // as the picker is not yet rendered.
                'if (values.$comp.focusable) {out.push("tabindex=\\\"0\\\"");}',
            '%} cellspacing="0">',
                '<thead><tr role="row">',
                    '<tpl for="dayNames">',
                        '<th role="columnheader" class="{parent.baseCls}-column-header" aria-label="{.}">',
                            '<div role="presentation" class="{parent.baseCls}-column-header-inner">{.:this.firstInitial}</div>',
                        '</th>',
                    '</tpl>',
                '</tr></thead>',
                '<tbody><tr role="row">',
                    '<tpl for="days">',
                        '{#:this.isEndOfWeek}',
                        '<td role="gridcell">',
                            '<div hidefocus="on" class="{parent.baseCls}-date"></div>',
                        '</td>',
                    '</tpl>',
                '</tr></tbody>',
            '</table>',
            // 指定时间渲染
            '<div id="{id}-timeEl" data-ref="timeEl" role="presentation" class="{baseCls}-time"></div>',
            '<tpl if="showToday">',
                '<div id="{id}-footerEl" data-ref="footerEl" role="presentation" class="{baseCls}-footer">{%this.renderOkBtn(values, out)%}{%this.renderTodayBtn(values, out)%}</div>',
            '</tpl>',
        '</div>',
        {
            firstInitial: function(value) {
                return Ext.picker.Date.prototype.getDayInitial(value);
            },
            isEndOfWeek: function(value) {
                // convert from 1 based index to 0 based
                // by decrementing value once.
                value--;
                var end = value % 7 === 0 && value !== 0;
                return end ? '</tr><tr role="row">' : '';
            },
            renderTodayBtn: function(values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.todayBtn.getRenderTree(), out);
            },
            renderMonthBtn: function(values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.monthBtn.getRenderTree(), out);
            },
            renderOkBtn: function(values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.okBtn.getRenderTree(), out);  
            }  
        }
    ],
    initComponent: function() {
        
	    // keep time part for value
	    var value = this.value || this.config.value || new Date();
      
	    this.callParent();
      
	    this.value = value;
      
	},
    beforeRender: function() {
		var timeWidth = 70;
		var timeLabelWidth = 45;
		var labelPosition='top';
        if(getCookie("css") == 'crisp'){
        	timeWidth = 50;
        	timeLabelWidth =45;
		}else if(getCookie("css") =='triton'){
			timeWidth = 70;
			labelPosition = 'left';
		}else if(getCookie("css") == 'crisp-touch'){
			timeWidth = 90;
			timeLabelWidth = 55;
			labelPosition = 'left';
		}else{
			timeWidth = 70;
		}
        var me = this;

        me.timePicker = Ext.create('Ext.form.field.Base', {
			ownerCt: me,
			ownerLayout: me.getComponentLayout(),
			fieldLabel: me.timeLabel,
			labelWidth: timeLabelWidth,
			labelAlign: labelPosition,
            inputType: 'hidden',
            style: 'padding: 5px 0; margin-bottom: 0;'
		});
        var cfg = Ext.apply({}, {
            ownerCt: me,
			ownerLayout: me.getComponentLayout(),
            readOnly: me.readOnly,
            disabled: me.disabled,
            width:timeWidth,
            step:2,
            style: 'float:left;margin-left:10px;margin-right:0px;',
            listeners: {
                afterrender: function() {
                    this.inputEl.on('click', function() {
                        this.focus();
                    });
                }
            }
        });
        
        me.hourSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 23
        }));
  
        me.minuteSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 59
        }));
  
        me.secondSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 59,
        }));

        me.okBtn = new Ext.button.Button({
            ownerCt: me,
			ownerLayout: me.getComponentLayout(),
            text: me.okText,
            handler: me.okHandler,
            scope: me
        });
        me.callParent();
    },
    onRender: function(container, position) {
        var me = this;
        this.callParent(arguments);
		this.timePicker.render(this.el.child('div div.x-datepicker-time'));
        
        var wrap = Ext.get(Ext.DomQuery.selectNode('div', this.timePicker.el.dom));
        // 组件timePicker渲染完成后，需要调用子元素的render，从而获得事件绑定
        me.hourSpinner.render(wrap);
        me.minuteSpinner.render(wrap);
        me.secondSpinner.render(wrap);
    },
    privates: {
        finishRenderChildren: function() {
            var me = this;
            me.callParent();
            
            me.okBtn.finishRender();
        }
    },
  
    /** 
     * 确认 按钮触发的调用
     */  
    okHandler: function() {
        
        var me = this,
            btn = me.okBtn;
            
        if(btn && !btn.disabled) {
            
            me.setValue(this.getValue());

            me.fireEvent('select', me, me.value);
            me.onSelect();
            
            // 因为 pickerField 复写了onSelect方法，去掉了collapse调用，所以这里单独隐藏选择器
            me.pickerField.collapse();
        }
        return me;
    },
    
    /** 
     * 覆盖了父类的方法，因为父类中是根据时间的getTime判断的，因此需要对时、分、秒分别值为0才能保证当前值的日期选择 
     * @private 
     * @param {Date} date The new date 
     */  
    selectedUpdate: function(date) {
        this.callParent([Ext.Date.clearTime(date, true)]);
    },
    /**
     * 复写selectToday方法使其支持时间设置,默认的清除了时间
     */
    selectToday: function() {
        var me = this,
            btn = me.todayBtn,
            handler = me.handler;
            
        if (btn && !btn.disabled) {
            
            // 默认这里会清除日期的时间部分
            me.setValue(new Date(), true);
            
            me.fireEvent('select', me, me.value);
            
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
            
            // 因为 pickerField 复写了onSelect方法，去掉了collapse调用，所以这里单独隐藏选择器
            me.pickerField.collapse();
            
        }
        return me;
    },
    /** 
     * 更新picker的显示内容，需要同时更新时、分、秒输入框的值 
     * @private 
     * @param {Date} date The new date 
     * @param {Boolean} forceRefresh True to force a full refresh 
     */  
    update: function(date, forceRefresh) {
        var me = this;
        me.hourSpinner.setValue(date.getHours());
        me.minuteSpinner.setValue(date.getMinutes());
        me.secondSpinner.setValue(date.getSeconds());
        
        return this.callParent(arguments);  
    },  
  
    /** 
     * 从picker选中后，赋值时，需要从时、分、秒也获得当前值 
     * datetimefield也会调用这个方法对picker初始化，因此添加一个isfixed参数。 
     * @param {Date} date The new date 
     * @param {Boolean} isfixed True 时，忽略从时分秒中获取值 
    */  
    setValue: function(date, isfixed) {
        var me = this;  
        if(isfixed !== true) {
            date.setHours(me.hourSpinner.getValue());
            date.setMinutes(me.minuteSpinner.getValue());
            date.setSeconds(me.secondSpinner.getValue());
        }  
        me.value = date;
        me.update(me.value);
        return me;  
    },  
  
    // @private
    // @inheritdoc
    beforeDestroy: function() {
        
        var me = this;
  
        if (me.rendered) {
            // 销毁组件时，也需要销毁自定义的控件
            Ext.destroy(
                me.timePicker,
                me.hourSpinner,
                me.minuteSpinner,
                me.secondSpinner,
                me.okBtn  
            );  
        }  
        me.callParent();  
    }  
});