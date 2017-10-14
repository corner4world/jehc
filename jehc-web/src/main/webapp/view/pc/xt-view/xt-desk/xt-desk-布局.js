/**Ext.onReady(function() {
	reGetWidthAndHeight();
	var form = Ext.create("Ext.form.Panel",{
	    margin:10,
	    title:"平台信息",
	    region:'center',
	    renderTo:Ext.getBody(),
	    collapsible:true,
	    autoScroll:true,
	    frame:true,
	    defaultType:'textfield',
	    defaults:{
	        anchor:'100%',
	    },
	    fieldDefaults:{
	        labelWidth:70,
	        labelAlign:"left",
	        flex:1,
	        margin:'4 5 4 5'
	    },
	    items:[{
	            xtype:"container",
	            layout:"hbox",
	            items:[
	                {xtype:"textfield",name:"name",fieldLabel:"用户姓名",allowBlank:false,emptyText:"请录入用户姓名"},
	                {xtype:"numberfield",name:"age",fieldLabel:"年 龄",emptyText:"请录入年龄",decimalPrecision:0,vtype:"age"}
	            ]
	        },
	        {
	            xtype:"container",
	            layout:"hbox",
	            items:[
	                {xtype:"textfield",name:"phone",fieldLabel:"联系电话",allowBlank:false,emptyText:"电话或手机号码"},
	                {xtype:"textfield",name:"phone",fieldLabel:"邮 箱",allowBlank:false,emptyText:"请输入电子邮件地址",vtype:"email"}
	            ]
	        },
	        {
	            xtype:"container",
	            layout:"hbox",
	            items:[
	                {xtype:"datefield",name:"borthday",format:'Y-m-d H:i:s',fieldLabel:"出生年月",allowBlank:false,emptyText:"请选择出生年月"}
	            ]
	        },
	        {
	            xtype:"container",
	            layout:"hbox",
	            items:[
	                {xtype:"timefield",name:"regtime",fieldLabel:"注册时间",allowBlank:false,emptyText:"请选择注册时间"}
	            ]
	        }
//	        {
//	            xtype:"container",
//	            layout:"hbox",
//	            items:[{xtype:"htmleditor",name:"remark",fieldLabel:"详细内容",allowBlank:false,emptyText:"请填写详细内容",
//	                	height:150,
//						width:600,
//						createLinkText:'创建超链接',//创建连接的提示信息
//						defaultLinkValue:"http://www.",//连接的默认格式
//						enableAlignments:true,//起用左、中、右对齐按钮
//						enableColors:true,//起用前景色、背景色选择按钮
//						enableFont:true,//起用字体选择按钮
//						enableFontSize:true,//起用字体增大和缩写按钮
//						enableFormat:true,//起用粗体、斜体、下划线按钮
//						enableLinks:true,//起用创建连接按钮
//						enableLists:true,//起用列表按钮
//						enableSourceEdit:true,//起用源代码编辑按钮
//						fontFamilies:['宋体','隶书','黑体'],//字体列表
//						buttonTips:{
//							bold:{
//								title:'Bold (Ctrl+B)',
//								text:'粗体'
//							},
//							italic:{
//								title:'Italic (Ctrl+I)',
//								text:'斜体'
//							},
//							underline:{
//								title:'Underline (Ctrl+U)',
//								text:'下划线'
//							},
//							increasefontsize:{
//								title:'Grow Text',
//								text:'增大字体'
//							},
//							decreasefontsize:{
//								title:'Shrink Text',
//								text:'缩小字体'
//							},
//							backcolor:{
//								title:'Text Highlight Color',
//								text:'背景色'
//							},
//							forecolor:{
//								title:'Font Color',
//								text:'前景色'
//							},
//							justifyleft:{
//								title:'Align Text Left',
//								text:'左对齐'
//							},
//							justifycenter:{
//								title:'Center Text',
//								text:'居中对齐'
//							},
//							justifyright:{
//								title:'Align Text Right',
//								text:'右对齐'
//							},
//							insertunorderedlist:{
//								title:'Bullet List',
//								text:'项目符号'
//							},
//							insertorderedlist:{
//								title:'Numbered List',
//								text:'数字编号'
//							},
//							createlink:{
//								title:'Hyperlink',
//								text:'超连接'
//							},
//							sourceedit:{
//								title:'Source Edit',
//								text:'切换源代码编辑模式'
//							}
//						}
//	                }]
//	        	}
			],
	    buttons:[{xtype:"button",text:"保 存"},{xtype:"button",text:"重 置"}
	    ]
	});
	
})
**/
Ext.onReady(function(){
	reGetWidthAndHeight();
    Ext.create('Ext.Panel', {
	    //title:'平台信息',
	    renderTo:Ext.getBody(),
	    width:clientWidth*0.8,
	    height:clientHeight*0.8,
	    style:'margin-left:auto;margin-right:auto;margin-top:auto;',
	    layout:{
	        type:'table',
	        columns:4
	    },
	    defaults:{frame:true,width:clientWidth*0.2,/**height:clientHeight*0.2,**/style:'margin-left:auto;margin-right:10px;margin-top:10px;'},
	    items:[
	          {title:'在线用户',html:'1.邓纯杰',rowspan:3,height:clientHeight*0.8},
	          {title:'待办事项',html:'1.16个待办事项未处理', rowspan:2,height:clientHeight*0.6},
	          {title:'未读邮件',html:'1.10封邮件未读',height:(clientHeight*0.2-10)},
	          {title:'今日常务',html:'暂无信息',height:(clientHeight*0.2-10)},
	          {title:'公司新闻',html:'1.范冰冰今天穿的还不错',colspan:2,width:clientWidth*0.2,height:clientHeight*0.4},
	          {title:'公告管理',html:'暂无信息',height:(clientHeight*0.2-10)},
	          {title:'个人考勤',html:'暂无信息',height:(clientHeight*0.2-10)},
	          {title:'个人计划',html:'暂无信息',height:(clientHeight*0.2-10)}
	    ]
	});
});