Ext.onReady(function(){
var gdCbxSt1 = new Ext.data.ArrayStore({
	pageSize: 50,
	autoLoad : false,
	fields : [ {
		name: 'no'
	}, {
		name: 'subject'
	}, {
		name: 'credit'
	}, {
		name: 'debit'
	}, {
		name: 'summary'
	} ],
	proxy : {
		type : 'ajax',
		url : 'data.json',
		reader : {
			type : 'json',
			root : 'data.list',
			totalProperty: 'data.total'
		}
	}
});
var gdCbxSt2 = new Ext.data.ArrayStore({
	pageSize: 50,
	autoLoad : false,
	fields : [ {
		name: 'no'
	}, {
		name: 'subject'
	}, {
		name: 'credit'
	}, {
		name: 'debit'
	}, {
		name: 'summary'
	} ],
	proxy : {
		type : 'ajax',
		url : 'data.json',
		reader : {
			type : 'json',
			root : 'data.list',
			totalProperty: 'data.total'
		}
	}
});
var gdCbx1 = new Ext.form.field.GridComboBox({
	fieldLabel : 'Grid ComboBox',
	multiSelect : false,
	displayField : 'subject',
	valueField : 'no',
	width : 300,
	labelWidth : 100,
	labelAlign: 'right',
	store : gdCbxSt1,
	typeAhead : true,
	queryMode : 'remote',
	matchFieldWidth : false,
	pickerAlign: 'bl',
	gridCfg : {
		store : gdCbxSt1,
		height: 200,
		width: 400,
		columns : [ {
			text : 'No',
			width : 40,
			dataIndex : 'no'
		}, {
			text : 'Subject',
			width : 120,
			dataIndex : 'subject'
		}, {
			text : 'Credit',
			width : 60,
			dataIndex : 'credit'
		}, {
			text : 'Debit',
			width : 60,
			dataIndex : 'debit'
		}, {
			text : 'Summary',
			width : 200,
			dataIndex : 'summary'
		} ],
		bbar : Ext.create('Ext.PagingToolbar', {
			store : gdCbxSt1,
			displayInfo : true,
			displayMsg : 'Displaying {0} - {1} of {2}',
			emptyMsg : "No data to display"
		})
	}
});
var gdCbx2 = new Ext.form.field.GridComboBox({
	fieldLabel : 'Grid ComboBox',
	multiSelect : true,
	displayField : 'subject',
	valueField : 'no',
	width : 300,
	labelWidth : 100,
	labelAlign: 'right',
	store : gdCbxSt2,
	queryMode : 'remote',
	matchFieldWidth : false,
	pickerAlign: 'bl',
	gridCfg : {
		store : gdCbxSt2,
		selModel : new Ext.selection.CheckboxModel({
			checkOnly: true
		}),
		height: 200,
		width: 400,
		columns : [ {
			text : 'No',
			width : 40,
			dataIndex : 'no'
		}, {
			text : 'Subject',
			width : 120,
			dataIndex : 'subject'
		}, {
			text : 'Credit',
			width : 60,
			dataIndex : 'credit'
		}, {
			text : 'Debit',
			width : 60,
			dataIndex : 'debit'
		}, {
			text : 'Summary',
			width : 200,
			dataIndex : 'summary'
		} ],
		bbar : Ext.create('Ext.PagingToolbar', {
			store : gdCbxSt2,
			displayInfo : true,
			displayMsg : 'Displaying {0} - {1} of {2}',
			emptyMsg : "No data to display"
		})
	}
});
new Ext.panel.Panel({
	renderTo: 'demo',
	items: [
		new Ext.form.Panel({
			frame : true,
			title: 'Grid ComboBox Demo, Single Selection.',
			layout: 'hbox',
			defaults: {
				xtype: 'button', margin: 2, width: 60
			},
			items : [ gdCbx1, {
				text: 'get', handler: function(){
					alert(gdCbx1.getSubmitValue());
				}
			},{
				text: 'set', handler: function(){
					gdCbx1.setValue([{"no": "2", "subject": "Subject 002" }]);
					//Now you can do set value like this
					//gdCbx1.setSubmitValue('2');
				}
			} ]
		}),
		new Ext.form.Panel({
			frame : true,
			title: 'Grid ComboBox Demo, Multi Selection.',
			layout: 'hbox',
			defaults: {
				xtype: 'button', margin: 2, width: 60
			},
			items : [ gdCbx2, {
				text: 'get', handler: function(){
					alert(gdCbx2.getSubmitValue());
				}
			},{
				text: 'set', handler: function(){
					gdCbx2.setValue([
						{"no": "1", "subject":"Subject 001" },
						{"no": "101", "subject":"Subject 101" }
					]);
					//Now you can do set value like this
					//gdCbx2.setSubmitValue(['2', '101']);
				}
			} ]
		})
	]
});
})