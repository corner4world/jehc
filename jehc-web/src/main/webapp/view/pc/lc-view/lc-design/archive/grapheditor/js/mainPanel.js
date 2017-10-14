var graphPanel;
var isShowGrid = 0;
MainPanel = function(graph, history){
	var executeLayout = function(layout, animate, ignoreChildCount){
		var cell = graph.getSelectionCell();
		if (cell == null || (!ignoreChildCount && graph.getModel().getChildCount(cell) == 0)){
			cell = graph.getDefaultParent();
		}
		graph.getModel().beginUpdate();
		try{
			layout.execute(cell);
		}catch (e){
			throw e;
		}finally{
			if (animate && navigator.userAgent.indexOf('Camino') < 0){
				var morph = new mxMorphing(graph);
				morph.addListener(mxEvent.DONE, function(){
					graph.getModel().endUpdate();
				});
				morph.startAnimation();
			}else{
				graph.getModel().endUpdate();
			}
		}
	};
    var fonts = new Ext.data.SimpleStore({
        fields: ['label', 'font'],
        data : [['宋体', '宋体'], ['幼圆', '幼圆'],
                ['楷体', '楷体'], ['微软雅黑', '微软雅黑'],
                ['黑体', '黑体']]
    });
    var fontCombo = new Ext.form.ComboBox({
        store: fonts,
        displayField:'label',
        mode: 'local',
        width:120,
        triggerAction: 'all',
        emptyText:'Select a font...',
        selectOnFocus:true,
        onSelect: function(entry){
        	if (entry != null){
				graph.setCellStyles(mxConstants.STYLE_FONTFAMILY, entry.data.font);
				this.collapse();
        	}
        }
    });
    fontCombo.on('specialkey', function(field, evt){
    	if (evt.keyCode == 10 || evt.keyCode == 13){
    		var family = field.getValue();
    		if (family != null && family.length > 0){
    			graph.setCellStyles(mxConstants.STYLE_FONTFAMILY, family);
    		}
    	}
    });
    var sizes = new Ext.data.SimpleStore({
        fields: ['label', 'size'],
        data : [['6pt', 6], ['8pt', 8], ['9pt', 9], ['10pt', 10], ['12pt', 12],
        	['14pt', 14], ['18pt', 18], ['24pt', 24], ['30pt', 30], ['36pt', 36],
        	['48pt', 48],['60pt', 60]]
    });
    //字体
    var sizeCombo = new Ext.form.ComboBox({
        store: sizes,
        displayField:'label',
        mode: 'local',
        width:50,
        triggerAction: 'all',
        emptyText:'12pt',
        selectOnFocus:true,
        onSelect: function(entry){
        	if (entry != null){
				graph.setCellStyles(mxConstants.STYLE_FONTSIZE, entry.data.size);
				this.collapse();
        	}
        }
    });
    sizeCombo.on('specialkey', function(field, evt){
    	if (evt.keyCode == 10 || evt.keyCode == 13){
    		var size = parseInt(field.getValue());
    		if (!isNaN(size) && size > 0){
    			graph.setCellStyles(mxConstants.STYLE_FONTSIZE, size);
    		}
    	}
    });
    var sizeCombo = new Ext.form.ComboBox({
        store: sizes,
        displayField:'label',
        mode: 'local',
        width:50,
        triggerAction: 'all',
        emptyText:'12pt',
        selectOnFocus:true,
        onSelect: function(entry){
        	if (entry != null){
				graph.setCellStyles(mxConstants.STYLE_FONTSIZE, entry.data.size);
				this.collapse();
        	}
        }
    });
    var sizeCombo = new Ext.form.ComboBox({
        store: sizes,
        displayField:'label',
        mode: 'local',
        width:50,
        triggerAction: 'all',
        emptyText:'12pt',
        selectOnFocus:true,
        onSelect: function(entry){
        	if (entry != null){
				graph.setCellStyles(mxConstants.STYLE_FONTSIZE, entry.data.size);
				this.collapse();
        	}
        }
    });
    sizeCombo.on('specialkey', function(field, evt){
    	if (evt.keyCode == 10 || evt.keyCode == 13){
    		var size = parseInt(field.getValue());
    		if (!isNaN(size) && size > 0){
    			graph.setCellStyles(mxConstants.STYLE_FONTSIZE, size);
    		}
    	}
    });
    var sizeCombo = new Ext.form.ComboBox({
        store: sizes,
        displayField:'label',
        mode: 'local',
        width:50,
        triggerAction: 'all',
        emptyText:'12pt',
        selectOnFocus:true,
        onSelect: function(entry){
        	if (entry != null){
				graph.setCellStyles(mxConstants.STYLE_FONTSIZE, entry.data.size);
				this.collapse();
        	}
        }
    });
    /**
     * 颜色模块设计
     */
    //1背景颜色
    var fillColorMenu = new Ext.menu.ColorPicker({
    	items: [{
    		text: 'None',
    		handler: function(){
    			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, mxConstants.NONE);
    		}
    	},
    	'-'
    	],
        handler:function(cm, color){
    		if (typeof(color) == "string"){
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, '#'+color);
			}
        }
    });
    //字体颜色
    var fontColorMenu = new Ext.menu.ColorPicker({
    	items: [{
    		text: 'None',
    		handler: function(){
    			graph.setCellStyles(mxConstants.STYLE_FONTCOLOR, mxConstants.NONE);
    		}
    	},
    	'-'
    	],
        handler : function(cm, color){
    		if (typeof(color) == "string"){
    			graph.setCellStyles(mxConstants.STYLE_FONTCOLOR, '#'+color);
			}
        }
    });
    //线条颜色
    var lineColorMenu = new Ext.menu.ColorPicker({
    	items: [{
			text: 'None',
			handler: function(){
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
			}
		},
		'-'
		],
        handler : function(cm, color){
    		if (typeof(color) == "string"){
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, '#'+color);
			}
        }
    });
    this.filename = null;
    this.modified = false;
	var updateTitle = mxUtils.bind(this, function(){
		//邓纯杰注释
	});
	var changeHandler = mxUtils.bind(this, function(sender, evt){
		this.modified = true;
		updateTitle();
	});
	graph.getModel().addListener(mxEvent.CHANGE, changeHandler);
    this.saveDiagram = function(forceDialog){
    	var name = this.filename;
    	if (name == null || forceDialog){
        	var defaultValue = this.filename;
        	if (defaultValue == null){
        		defaultValue = "MyDiagram";
	        	var current = defaultValue;
	        	var i = 2;
	        	while (DiagramStore.get(current) != null){
	        		current = defaultValue + i++;
	        	}
	        	defaultValue = current;
        	}
    		do
    		{
	    		name = mxUtils.prompt('Enter filename', defaultValue);
	    		if(name != null){
		    		if(name.length > 0){
		    			if(name != this.filename && DiagramStore.get(name) != null){
		    				defaultValue = name;
		    				name = '';
		    			}
		    		}else{}
	    		}
    		}
    		while (name != null && name.length == 0);
    	}
    	if(name != null){
    		var enc = new mxCodec(mxUtils.createXmlDocument());
			var node = enc.encode(graph.getModel());
			var xml = mxUtils.getXml(node);
			DiagramStore.put(name, xml);
			this.filename = name;
			this.modified = false;
			updateTitle();
			mxUtils.alert('Saved "'+name+'": '+xml.length+' byte(s)');
    	}else{
    	}
    };
    
    /**邓纯杰重写了在下面
    this.openDiagram = function(name)
    {
    	if (!this.modified ||
    		mxUtils.confirm('Lose changes?'))
   		{
			var xml = DiagramStore.get(name);
			
			if (xml != null && xml.length > 0)
			{
				var doc = mxUtils.parseXml(xml); 
				var dec = new mxCodec(doc); 
				dec.decode(doc.documentElement, graph.getModel());
				history.clear();
				this.filename = name;
				this.modified = false;
				updateTitle();
				mxUtils.alert('Opened "'+name+'": '+xml.length+' byte(s)');
			}
   		}
    };**/
    this.openDiagram = function(xml){
    	if (xml != null && xml.length > 0){
			var doc = mxUtils.parseXml(xml); 
			var dec = new mxCodec(doc); 
			dec.decode(doc.documentElement, graph.getModel());
			history.clear();
			this.filename = name;
			this.modified = false;
			updateTitle();
			Ext.MessageBox.alert(xml.length);
		}
    };
    /**邓纯杰注释
    this.newDiagram = function()
    {
    	if (!this.modified ||
    		Ext.MessageBox.confirm('提示', '流程已经变动是否确定删除清除?', function (btn) {
                if (btn == 'yes') {
                    var cell = new mxCell();
			    	cell.insert(new mxCell());
			    	graph.getModel().setRoot(cell);
			    	history.clear();
			    	this.filename = null;
					this.modified = false;
			    	updateTitle();
                }
           }))
   		{
            //邓纯杰把它们放在上面
   		}
    };
	**/
	//邓纯杰重写上面新建功能
	this.newProcess = function(){
    	if(!this.modified || Ext.MessageBox.confirm('提示', '流程已经变动是否确定删除清除?', function (btn) {
               if (btn == 'yes') {
               	/**
                   var cell = new mxCell();
		    	cell.insert(new mxCell());
		    	graph.getModel().setRoot(cell);
		    	**/
		    	graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));  
		    	history.clear();
		    	this.filename = null;
				this.modified = false;
				eastPanel.form.reset();//清空基础表单
				var eItems =eastPanel.items;
	    	    for(var le = 0; le < eItems.length; le++){
	    	    	if(le > 0){
	    	    		eastPanel.remove(eItems.get(le),true);
	    	    	}
			    }
	    	    basePanel.setHidden(false);
		    	updateTitle();
		    	//验证泳道是否存在 如果存在则基本信息为第一个泳道中流程信息  
				validatePOOL(graph);
               }
          })){
            //邓纯杰把它们放在上面
   		}
    };
	graphPanel = new Ext.Panel({
    	region:'center',
    	border:true,
    	frame:true,
    	split:true,
    	border:true,
    	autoScroll:true,
    	html:'<div id="container">',
        tbar:[
		{
			fieldLabel:'基本流程中事件配置',
			xtype:'textfield',
			hidden:true,
			id:'eventMainNode',
			name:'eventMainNode',
			anchor:'100%'
		},
		{
			fieldLabel:'基本流程数据属性配置',
			xtype:'textfield',
			hidden:true,
			id:'datamainProperties_node_value',
			name:'datamainProperties_node_value',
			anchor:'100%'
		},
		{
			fieldLabel:'基本流程信号配置',
			xtype:'textfield',
			hidden:true,
			id:'signal_node_value',
			name:'signal_node_value',
			anchor:'100%'
		},
		{
			fieldLabel:'基本流程消息配置',
			xtype:'textfield',
			hidden:true,
			id:'message_node_value',
			name:'message_node_value',
			anchor:'100%'
		},
		{
            text:'新 建',
            iconCls:'new-icon',
            tooltip:'新 建',
            handler:function(){
        		this.newProcess();
            },
            scope:this
        },
        {
       		 text:'导入流程',
             icon:basePath+'/view/pc/lc-view/lc-design/archive/grapheditor/images/paste.gif',
             handler:function() {
            	imp(graph,history);
            	return;
             }
       	},
       	{
            text:'保存流程',
            iconCls:'save-icon',
            tooltip:'保存流程',
            id:'saveButton',
            handler:function(){
            	//验证节点名称是否相同及是否节点存在为空现象
            	/**
            	if(validateCellsNameIsSameToSave(graph) == false){
            		return false;
            	}
            	**/
            	//获取mxgraph拓扑图数据  
				//var enc = new mxCodec(mxUtils.createXmlDocument());  
				//var node1 = enc.encode(graph.getModel());
            	//var mxgraphxml = mxUtils.getXml(node1); 
            	var enc = new mxCodec(mxUtils.createXmlDocument());
				var node = enc.encode(graph.getModel());
				var mxgraphxml = mxUtils.getPrettyXml(node);
					mxgraphxml = mxgraphxml.replace(/\"/g,"'");
					//mxgraphxml = encodeURIComponent(mxgraphxml);
					
				var xmlDoc = mxUtils.createXmlDocument(); 
		    	var root = xmlDoc.createElement('output'); 
		    	xmlDoc.appendChild(root); 
		    	var xmlCanvas = new mxXmlCanvas2D(root); 
		    	var imgExport = new mxImageExport(); 
		    	imgExport.drawState(graph.getView().getState(graph.model.root), xmlCanvas); 
		    	var bounds = graph.getGraphBounds(); 
		    	var w = Math.round(bounds.x + bounds.width + 4); 
		    	var h = Math.round(bounds.y + bounds.height + 4); 
		    	var imgxml = mxUtils.getXml(root);
		    		//imgxml = "<output>"+imgxml+"</output>";
	                //imgxml = encodeURIComponent(imgxml);
            	save_process(mxgraphxml,w,h,imgxml);
            },
            scope:this
        },
        {
        	id:'cut',
            text:'剪切',
            iconCls:'cut-icon',
            tooltip:'剪切',
            hidden:true,
            handler:function(){
        		mxClipboard.cut(graph);
        	},
            scope:this
        },
        {
       		id:'copy',
            text:'拷贝',
            hidden:true,
            iconCls:'copy-icon',
            tooltip:'拷贝',
            handler:function(){
        		mxClipboard.copy(graph);
        	},
            scope:this
        },
        {
            text:'粘贴',
            iconCls:'paste-icon',
            tooltip:'粘贴',
            hidden:true,
            handler:function(){
            	mxClipboard.paste(graph);
            },
            scope:this
        },
        {
       		id:'delete',
            text:'删除',
            iconCls:'delete-icon',
            tooltip:'删除',
            hidden:true,
            handler:function(){
        		graph.removeCells();
        		//验证泳道是否存在 如果存在则基本信息为第一个泳道中流程信息  
				validatePOOL(graph);
				var eItems =eastPanel.items;
	    	    for(var le = 0; le < eItems.length; le++){
	    	    	if(le > 0){
	    	    		eastPanel.remove(eItems.get(le),true);
	    	    	}
			    }
	    	    basePanel.setHidden(false);
        	},
            scope:this
        },
        {
        	id:'undo',
            text:'撤销',
            iconCls:'undo-icon',
            tooltip:'撤销',
            hidden:true,
            handler:function(){
            	history.undo();
            },
            scope:this
        },
        {
        	id:'redo',
            text:'重做',
            iconCls:'redo-icon',
            tooltip:'重做',
            hidden:true,
            handler:function(){
        		history.redo();
            },
            scope:this
        },
        {
            text:'全屏',
            iconCls:'zoom-icon',
            scope:this,
            hidden:true,
            handler:function(item)
            {
                graph.fit();
            }
         },
         {
              text:'全选',
              scope:this,
              hidden:true,
              iconCls:'select-all-icon',
              handler:function()
              {
               graph.selectAll();
              }
          },
          {
              text:'全选节点',
              scope:this,
              hidden:true,
              iconCls:'select-node-icon',
              handler:function()
              {
  				graph.selectVertices();
              }
           },
           {
              text:'全选连线',
              scope:this,
              hidden:true,
              iconCls:'select-line-icon',
              handler:function()
              {
         		graph.selectEdges();
              }
           },
           {
               text:'放大',
               iconCls:'zoomin-icon',
               scope:this,
               handler:function(item)
               {
    			graph.zoomIn();
               }
            },
            {
               text:'放小',
               iconCls:'zoomout-icon',
               scope:this,
               handler:function(item)
               {
                   graph.zoomOut();
               }
            },
            {
               text:'.实际尺寸',
               iconCls:'zoomactual-icon',
               scope:this,
               handler:function(item)
               {
                   graph.zoomActual();
               }
            }],
        bbar:[
//        {
//            text:'设计器脚步',
//            scope:this,
//            icon: '../view/lc-view/lc-design/archive/grapheditor/images/paste.gif',
//            handler: function(item){
//				var enc = new mxCodec(mxUtils.createXmlDocument());
//				var node = enc.encode(graph.getModel());
//				mxUtils.popup(mxUtils.getPrettyXml(node));
//            }
//        },
//        {
//            text:'工作流脚步',
//            scope:this,
//            icon:'../view/lc-view/lc-design/archive/grapheditor/images/paste.gif',
//            handler: function(item){
//            	var enc = new mxCodec(mxUtils.createXmlDocument());  
//				var node1 = enc.encode(graph.getModel());  
//				var mxgraphxml = mxUtils.getXml(node1); 
//					mxgraphxml = mxgraphxml.replace(/\"/g,"'");
//            	show_jpdl_xml(mxgraphxml,graph,history);
//				//var enc = new mxCodec(mxUtils.createXmlDocument());
//				//var node = enc.encode(graph.getModel());
//				//mxUtils.popup(mxUtils.getPrettyXml(node));
//            }
//      },
        {
            text:'直线',
            icon:'../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/zxline.gif',
            tooltip:'直线',
            handler:function(){
          	  linetostyle(0,graph);
            },
            scope:this
        },
        {
            text:'曲线',
            icon:'../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/qxline.gif',
            tooltip:'曲线',
            handler:function(){
          	  linetostyle(1,graph);
            },
            scope:this
        },
        {
			id:'fontcolor',
            text:'字体颜色',
            tooltip:'字体颜色',
            iconCls:'fontcolor-icon',
            menu:fontColorMenu
        },
        {
			id:'linecolor',
            text:'边框颜色',
            tooltip:'边框颜色',
            iconCls:'linecolor-icon',
            menu:lineColorMenu 
        },
        {
			id:'fillcolor',
            text:'背景颜色',
            tooltip:'背景颜色',
            iconCls:'fillcolor-icon',
            menu:fillColorMenu 
        },
        {
			id:'bold',
            text:'加粗',
            iconCls:'bold-icon',
            tooltip:'加粗',
            handler:function(){
        		graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        	},
            scope:this
        },
        {
			id:'italic',
            text:'倾斜',
            tooltip:'倾斜',
            iconCls:'italic-icon',
            handler: function(){
            	graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_ITALIC);
            },
            scope:this
        },
        {
			id:'underline',
            text:'下划线',
            tooltip:'下划线',
            iconCls:'underline-icon',
            handler: function(){
        		graph.toggleCellStyleFlags(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_UNDERLINE);
        	},
            scope:this
        },
        {
            id:'align',
            text:'文本属性',
            iconCls:'left-icon',
            tooltip:'文本属性',
            handler:function() { },
            menu:{
                id:'reading-menu',
                cls:'reading-menu',
                items:[{
                    text:'靠左',
                    checked:false,
                    group:'rp-group',
                    scope:this,
                    iconCls:'left-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
                	}
                },{
                    text:'居中',
                    checked:true,
                    group:'rp-group',
                    scope:this,
                    iconCls:'center-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
                	}
                },{
                    text:'靠右',
                    checked:false,
                    group:'rp-group',
                    scope:this,
                    iconCls:'right-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_RIGHT);
                	}
                },{
                    text:'向上',
                    checked:false,
                    group:'vrp-group',
                    scope:this,
                    iconCls:'top-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_TOP);
                	}
                },{
                    text:'中间',
                    checked:true,
                    group:'vrp-group',
                    scope:this,
                    iconCls:'middle-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
                	}
                },{
                    text:'靠下',
                    checked:false,
                    group:'vrp-group',
                    scope:this,
                    iconCls:'bottom-icon',
                    handler:function(){
                		graph.setCellStyles(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
                    }
                }]
            }
        }],
        onContextMenu:function(node, e){
    		var selected = !graph.isSelectionEmpty();
    		this.menu = new Ext.menu.Menu({
                items:[{
                    text:'对连线设置折点',
                    iconCls:'select-line-icon',
                    scope:this,
                    handler:function(){
                    	//记得重新获取该方法
		            	cell = graph.getSelectionCell();
		            	if(cell!=null && cell.edge){ 
							var point = mxUtils.convertPoint(graph.container, e.clientX, e.clientY);
							var x = point.x;
							var y = point.y;
							graph.getModel().beginUpdate();
							try{
								var view =graph.getView();
								var state = view.getState(cell,true);
								var mxPoints = state.absolutePoints;
								var mPoint = '';
								for(var i = 0; i < mxPoints.length; i++){
									var m_x = mxPoints[i].x;
									var m_y = mxPoints[i].y;
									if(i != 0 && i != mxPoints.length-1){
										if("undefined" != typeof(mPoint) && mPoint != null && mPoint != ''){
											mPoint = mPoint+','+'new mxPoint('+m_x+','+m_y+')';
										}else{
											mPoint = 'new mxPoint('+m_x+','+m_y+')';
										}
									}
								}
								//var data = '[new mxPoint(320, 50), new mxPoint(320, 230)]';
								//设置折点 如果多个就要以逗号分割
								//cell.geometry.points = [new mxPoint(x, y),new mxPoint(320, 50), new mxPoint(320, 230)]; 
								if("undefined" != typeof(mPoint) && mPoint != null && mPoint != ''){
									//cell.geometry.points = [new mxPoint(x, y),Ext.decode(mPoint)]; 
									//非要这样才可以
									var data = '[new mxPoint('+x+', '+y+'),'+mPoint+']';
										data=Ext.decode(data);
									cell.geometry.points = data; 
								}else{
									cell.geometry.points = [new mxPoint(x, y)]; 
								}
							}finally{
								graph.getModel().endUpdate();
								graph.refresh();
							}
		            	}else{
		            		Ext.example.msg("提示","请选中连线节点!");
		            	}
                    }
                },'-',
                {
                    text:'重做',
                    iconCls:'undo-icon',
                    disabled:!history.canUndo(),
                    scope:this,
                    hidden:true,
                    handler:function(){
                        history.undo();
                    }
                },
                {
                    text:'剪切',
                    iconCls:'cut-icon',
                    hidden:true,
                    disabled:!selected,
                    scope: this,
                    handler:function(){
                    	mxClipboard.cut(graph);
                    }
                },
                {
                    text:'拷贝',
                    iconCls:'copy-icon',
                    disabled:!selected,
                    hidden:true,
                    scope:this,
                    handler:function(){
                    	mxClipboard.copy(graph);
                    }
                },
                {
                    text:'粘贴',
                    iconCls:'paste-icon',
                    disabled:mxClipboard.isEmpty(),
                    scope:this,
                    hidden:true,
                    handler:function(){
                    	mxClipboard.paste(graph);
                    }
                },
                {
                    text:'放大',
                    iconCls:'zoomin-icon',
                    scope:this,
                    handler:function(item)
                    {
         			graph.zoomIn();
                    }
                 },
                 {
                    text:'放小',
                    iconCls:'zoomout-icon',
                    scope:this,
                    handler:function(item)
                    {
                        graph.zoomOut();
                    }
                 },
                {
                    text:'删除',
                    iconCls:'delete-icon',
                    disabled:!selected,
                    scope:this,
                    handler:function(){
                    	graph.removeCells();
                    	//验证泳道是否存在 如果存在则基本信息为第一个泳道中流程信息  
						validatePOOL(graph);
						
						 var eItems =eastPanel.items;
	//				    	   console.info(eItems.length);
				    	   for(var le = 0; le < eItems.length; le++){
							 if(le > 0){
	//								 console.info(eItems.get(le));
								 eastPanel.remove(eItems.get(le),true);
							 }
						   }
				    	   basePanel.setHidden(false);
                    }
                },
                {
                    text:'网格',
                    iconCls:'left-icon',
                    scope:this,
                    handler:function(){
                    	if(isShowGrid == 0){
                    		graph.container.style.background = 'url("../view/pc/lc-view/lc-design/archive/grapheditor/images/wires-grid.gif")'
                    		isShowGrid = 1;
                    	}else{
                    		graph.container.style.background = 'url()';
                    		isShowGrid = 0;
                    	}
                    }
                },
		       	{
		            text:'编辑',
		            scope:this,
		            iconCls:'editor-jbpm-icon',
		            handler:function(){
		            	//记得重新获取该方法
		            	cell = graph.getSelectionCell();
		            	//if (cell!=null && graph.isCellEditable(cell)) { 
		            	if (cell!=null) { 
                             //graph.getModel().beginUpdate();
							 //try
							 //{
								//cell.value="开始";
								//cell.jbpm_="开始节点";
								//graph.startEditing();
							 //}
							 //finally
							 //{
								//graph.getModel().endUpdate();
							 //}
							 if(!cell.edge){
							 	//非连线节点
							 	setNodeAttribute(cell,graph);
							 }else{
							 	//是连线节点
							 	if(null == cell.source || "" == cell.source){
							 		msgTishi("该连线没有指定源节点!");
							 	}else if(null == cell.target || "" == cell.target){
							 		msgTishi("该连线没有指定目标节点!");
							 	}else{
							 		var childsCellEdgs=cell.source.edges;
	                                for(var i=0;i<childsCellEdgs.length;i++){
	                                    if(childsCellEdgs[i].source.id==cell.source.id){
	                                       //连线的上一个节点类型
	                                       //var node_type = childsCellEdgs[i].source.node_type;
	                                       //参数1表示cell属性，
	                                       //参数2表示是否追加"条件"含义1表示追加条件2表示无需追加条件，
	                                       //if(node_type == 'decision'){
	                                       		//连线属性中追加"条件"
	                                       		//transitionNodeAttributeWin(cell,1,graph);
	                                       		//记得必须加上return不然连线会有问题的
	                                       		//return;
	                                       //}else{
	                                       		//transitionNodeAttributeWin(cell,2,graph);
	                                       		//记得必须加上return不然连线会有问题的
	                                       		var childEdge = childsCellEdgs[i];
	                                       		var sourceRoot = resultRootCell(graph,childEdge.source);
	                                       		var targetRoot = resultRootCell(graph,childEdge.target);
	                                       		validateSourceRootAndTargetRoot(graph,sourceRoot,targetRoot,childEdge);
	                                       		//isflow等于2则表示消息连线 否则为正常连线
	                                       		if(null != childEdge && '' != childEdge && null != childEdge.isflow && '' != childEdge.isflow && childEdge.isflow == '2'){
	                                       			showTransitionNodeAttributeWin(cell,graph,2);
	                                       		}else{
	                                       			showTransitionNodeAttributeWin(cell,graph,1);
	                                       		}
	                                       		return;
	                                       //}
	                                    }
	                                    if(childsCellEdgs[i].target.id==cell.target.id){
	                                       //连线的下一个节点类型
	                                       var node_type = childsCellEdgs[i].target.node_type;
	                                    }
	                                }
							 	}
							 }
                        }else{
                        	msgTishi("您没有选择节点!请选择!");
                        }
		            }
		        },
		        /**
		        {
                    text:'创建时间边界',
                    iconCls:'paste-icon',
                    scope: this,
                    handler:function(){
                    	//记得重新获取该方法
		            	cell = graph.getSelectionCell();
		            	if (cell!=null) { 
							 if(!cell.edge){
				                var overlay = new mxCellOverlay(  
				                    new mxImage('../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/boundaryEventTime.png', 32, 32), '边界事件');
				                    // 在图形中覆盖  
				                    graph.addCellOverlay(cell, overlay);  
				                    // 单击显示提示 
				                    overlay.addListener(mxEvent.CLICK, function(sender, evt2){  
				                        mxUtils.alert('单击');  
				                    });
				                 overlay.cursor = 'hand';  
				   				 overlay.align = mxConstants.ALIGN_CENTER;
							 	//非连线节点
							 	//graph.insertVertex(cell, '', '边界事件', e.x, e.y, 24, 24,'start_image4gray;rounded=true;strokeColor=none;fillColor=yellow;');
							 }
                        }else{
                        	msgTishi("您没有选择节点!请选择!");
                        }
                    }
                }**/,
                {
                    text:'导出',
                    iconCls:'select-line-icon',
                    scope: this,
                    hidden:true,
                    handler:function(){
                    	var enc = new mxCodec(mxUtils.createXmlDocument());  
						var node1 = enc.encode(graph.getModel());  
						var mxgraphxml = mxUtils.getXml(node1); 
						mxgraphxml = mxgraphxml.replace(/\"/g,"'");
						new Ext.Window({  
					         title:'文件格式',  
					         width:500, 
					         height:300,
					         layout:'fit',
					         resizable:true,  
					         modal:true,  
					         closable:true,  
					         maximizable:true,  
					         minimizable:true,
					         items:[{
					         	xtype:'textarea',
								name:'mxgraphxml_',
								id:'mxgraphxml_',
								allowBlank:false,
								grow:true,
								growMax:220,
								anchor:'100%'
					         }]
					    }).show(); 
					    Ext.getCmp('mxgraphxml_').setValue(mxgraphxml); 
                    }
                },
                {
	                text:'预览图片',
                    iconCls:'img-icon',
                    scope:this,
                    handler:function(){
                    	var url = editor.getUrlImage();
						if (url == null || mxClient.IS_LOCAL){
							editor.execute('show');
						}else{
							var node = mxUtils.getViewXml(editor.graph, 1);
							var xml = mxUtils.getXml(node, '\n');
							mxUtils.submit(url, graph.postParameterName + '=' +
							encodeURIComponent(xml), document, '_blank');
						}
                    }
                }]
            });
            this.menu.on('hide', this.onContextHide, this);
            this.menu.showAt([e.clientX + 1, e.clientY + 1]);
	    },
	    onContextHide:function(){
	        if(this.ctxNode){
	            this.ctxNode.ui.removeClass('x-node-ctx');
	            this.ctxNode = null;
	        }
	    }
    });
    graph.popupMenuHandler.popup = mxUtils.bind(this, function(x, y, cell, evt){
    	graphPanel.onContextMenu(null, evt);
    });
    graph.popupMenuHandler.hideMenu = mxUtils.bind(this, function(){
		if (graphPanel.menuPanel != null){
			graphPanel.menuPanel.hide();
    	}
    });
    graphPanel.on('resize', function(){
        graph.sizeDidChange();
    });
    //2015重写
	return graphPanel;
};

function linetostyle(flag,graph){
	var line_style = basePath+'/view/pc/lc-view/lc-design/archive/grapheditor/resources/default-style.xml';
	if(flag == 0){
		//如果为1直线
		line_style = basePath+'/view/pc/lc-view/lc-design/archive/grapheditor/resources/default-style.xml';
	}else if(flag == 1){
		//如果为1曲线
		line_style = basePath+'/view/pc/lc-view/lc-design/archive/grapheditor/resources/bight-style.xml';
	}
	var history = new mxUndoManager();
    //载入默认样式
    var node = mxUtils.load(line_style).getDocumentElement();
	var dec = new mxCodec(node.ownerDocument);
	dec.decode(node, graph.getStylesheet());
	var edgeStyle = graph.getStylesheet().getDefaultEdgeStyle(); 
	//edgeStyle[mxConstants.STYLE_EDGE] = mxEdgeStyle.TopToBottom; 
	edgeStyle['gradientColor'] = '#c0c0c0'; 
	edgeStyle['strokeColor'] = '#c0c0c0'; //更改连线默认样式此处为颜色
	edgeStyle['dashed'] = '1'; //虚线
	edgeStyle['strokeWidth'] = 0.1;
	edgeStyle['fontSize'] = '8';
	edgeStyle['fontColor'] = '#000';
	edgeStyle['arrowWidth'] = 0.1;  
	graph.alternateEdgeStyle = 'elbow=vertical';
	graph.refresh();
	Ext.getCmp('lc_process_mxgraph_style').setValue(flag);
}
//载入XML流程图
function loadXml(graph,history){
	graph.getModel().beginUpdate();
	try{
		/*
		 * 直接读取流程图的xml, 并展示流程图
		*/
		var xml = '<root><mxCell id="0"/><mxCell id="1" parent="0"/><mxCell id="2" value="开始" style="roundImage;image=../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/start.png" vertex="1" node_type="start" parent="1"><mxGeometry x="260" y="80" width="50" height="50" as="geometry"/></mxCell><mxCell id="3" value="状态" style="roundImage;image=../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/state.png" vertex="1" node_type="state" parent="1"><mxGeometry x="260" y="290" width="50" height="50" as="geometry"/></mxCell><mxCell id="4" value="开始到状态" edge="1" parent="1" source="2" target="3"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="5" value="结束" style="roundImage;image=../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/end.png" vertex="1" node_type="end" parent="1"><mxGeometry x="260" y="460" width="50" height="50" as="geometry"/></mxCell><mxCell id="6" value="状态到结束" edge="1" parent="1" source="3" target="5"><mxGeometry relative="1" as="geometry"/></mxCell></root>';
	    	xml = "<?xml version='1.0' encoding='utf-8'?><mxGraphModel>"+xml+"</mxGraphModel>"; 
	    if(xml != null && xml.length > 0){
			var doc = mxUtils.parseXml(xml); 
			var dec = new mxCodec(doc); 
			dec.decode(doc.documentElement, graph.getModel());
		}
	}finally{
		graph.getModel().endUpdate();
		graph.refresh();
	}
}
//保存流程
function save_process(mxgraphxml,w,h,imgxml){
	Ext.Msg.confirm('提示','确定保存该流程？',function(btn){
		if(btn == 'yes'){
			Ext.getCmp('mxgraphxml').setValue(mxgraphxml);
			Ext.getCmp('imgxml').setValue(imgxml);
			Ext.getCmp('w').setValue(w);
			Ext.getCmp('h').setValue(h);
		    if(eastPanel.form.isValid()){
			   	 eastPanel.form.submit({                    
			        url:'../lcProcessController/createBPMN',  
			        timeout:600000,/**设置超时时间10十分钟**/
			        params:{ajaxform:0},
			        waitTitle:'提示',
			        actionMethods:{ 
						read:'POST' 
					},
					method:'post', 
			        waitMsg:'正在保存数据，请稍后...',                      
			        success:function(form, action){    
			        	msgTishi(action.result.msg);
			        	var lc_Process_id = action.result.lc_Process_id;
			        	if(lc_Process_id != 0){
			        		Ext.getCmp('lc_process_id').setValue(lc_Process_id);
			        	}
			      	},
			        failure:function(form, action){
			      	}                  
			      });            
			  }else{ 
				 	msgTishi('请输入必填项');
			  }
		}
	});
}


//将字符串转换XML
function printString(xmlobj){
	var xmlDom;
    //IE
	if(document.all){ 
	   xmlDom=new ActiveXObject("Microsoft.XMLDOM"); 
	   xmlDom.loadXML(xmlobj); 
	} 
    //非IE
	else {
	   xmlDom = new DOMParser().parseFromString(xmlobj, "text/xml");
	} 
	return xmlDom;
}
