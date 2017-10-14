var impWin;//导入流程Win窗体
GraphEditor = {};
var eastPanel;
var leftPanel;
var editor;
function main(){
	Ext.onReady(function(){
    Ext.QuickTips.init();
	mxEvent.disableContextMenu(document.body);	
	mxConstants.DEFAULT_HOTSPOT = 0.3;
    editor = new mxEditor();  
    var graph = editor.graph;  
    graph.setConnectable(true);
	graph.setDropEnabled(true);//从工具栏拖动到目标细胞时细胞边界是否产生光圈 
    graph.setTooltips(true);
    //点击节点连线 不生成新的节点出来 false true为生成新节点
    graph.connectionHandler.setCreateTarget(false);
    //重复连接
	graph.setMultigraph(false);
	//指定图表是否应允许将单元格放入其他单元格中或其他单元格中。
	graph.setSplitEnabled(true);
    //是否可以移动连线 重新连接其他cell，主要用来展现中使用
    //graph.setCellsLocked(true);
    //节点不可改变大小
    graph.setCellsResizable(true);
    /**
      *邓纯杰
      *创建连线
    **/
    //定义新连接的图标  
    //mxConnectionHandler.prototype.connectImage = new mxImage('../view/pc/lc-view/lc-design/archive/grapheditor/images/connector.gif', 16, 16); 
    //节点变化情况事件主要用于验证节点杰连线及节点
    graph.getSelectionModel().addListener(mxEvent.CHANGE, function(sender, evt){  
         selectionChanged(graph);  
    });  
    //初始化这个页面就验证
    selectionChanged(graph); 
    function selectionChanged(graph){  
    	//验证方法
        validate(graph, history);
        /**该处处理连线Flow与Message转换 不放在该处了 放在moveCells
        validateDragsCellFlowToMessage(graph);
        **/
    }  
    //移动元素触发事件  
	graph.addListener(mxEvent.CELLS_MOVED,function(sender, evt){  
	    var cell = evt.getProperty('cell');   
	    validateDragsCellFlowToMessage(graph);
	    if(cell==null&&sender.graphHandler.cells!=null){  
	        cell = sender.graphHandler.cells[0];//保证cell有值，否则移动时cell  
	    }   
	    if(cell != null && cell.vertex == 1) {//代表鼠标点击的是节点  
	    }    
	});  
    //禁止连接线晃动即不可以离开节点 
	graph.setAllowDanglingEdges(false);   
	//允许连线的目标和源是同一元素
	graph.setAllowLoops(false);
    //导航线 显示细胞位置标尺
    mxGraphHandler.prototype.guidesEnabled = true; 
    //自动导航目标  
    mxEdgeHandler.prototype.snapToTerminals = true;  
    //去锯齿效果   
	mxRectangleShape.prototype.crisp = true; 
    /**
      *邓纯杰
      *节点拖动事件包括移动 拖动 复制 粘贴（注意不包括在节点上生成新的节点）
     **/
    mxGraph.prototype.moveCells = function(cells, dx, dy, clone, target, evt) {
	    if (cells != null && (dx != 0 || dy != 0 || clone || target != null)) {
	        this.model.beginUpdate();
	        cells = graph.getImportableCells(cells);
	        try {
	        	//通过克隆
	            if (clone) {
	                cells = this.cloneCells(cells, this.isCloneInvalidEdges());
	                if (target == null) {
	                    target = this.getDefaultParent();
	                }
	            }
	            this.cellsMoved(cells, dx, dy, !clone && this.isDisconnectOnMove() && this.isAllowDanglingEdges(), target == null);
	            if (target != null) {
	                var index = this.model.getChildCount(target);
	                this.cellsAdded(cells, target, index, null, null, true);
	            }
	            this.fireEvent(new mxEventObject(mxEvent.MOVE_CELLS, 'cells', cells, 'dx', dx, 'dy', dy, 'clone', clone, 'target', target, 'event', evt));
	        } finally {
	            this.model.endUpdate();
	        }
	    }
	    //处理flow与Message转换验证
	    validateDragsCellFlowToMessage(graph);
	    return cells;
	};
	
	/////////////////////////////边界事件节点只能在父元素边框1/2位置----开始/////////////////////
	graph.graphHandler.getDelta = function(me){
		var point = mxUtils.convertPoint(this.graph.container, me.getX(), me.getY());
		var delta = new mxPoint(point.x - this.first.x, point.y - this.first.y);
		if (this.cells != null && this.cells.length > 0 && this.cells[0] != null){
			var state = this.graph.view.getState(this.cells[0]);
			var rel = getRelativePosition(state, delta.x, delta.y);
			if (rel != null){
				var pstate = this.graph.view.getState(this.graph.model.getParent(state.cell));
				if (pstate != null){
					delta = new mxPoint(pstate.x + pstate.width * rel.x - state.getCenterX(),pstate.y + pstate.height * rel.y - state.getCenterY());
				}
			}
		}
		return delta;
	};
 	graph.graphHandler.shouldRemoveCellsFromParent = function(parent, cells, evt){
 		if(cells.length == 1){
 			if(cells[0].node_type == 'errorStartEvent'){
 				if(null != parent && typeof(parent.node_type) != "undefined" && parent.node_type == 'eventSubProcess'){
		 			return false;
		 		}
 			}
 		}else{
 			////////////////判断一起拖拽的是否存在错误启动事件
 			for(var i = 0; i < cells.length; i++){
 				if(cells[i].node_type == 'errorStartEvent'){
	 				if(null != parent && typeof(parent.node_type) != "undefined" && parent.node_type == 'eventSubProcess'){
			 			return false;
			 		}
 				}
 			}
 		}
		return cells.length > 0 && !cells[0].geometry.relative && mxGraphHandler.prototype.shouldRemoveCellsFromParent.apply(this, arguments);
	};
    function getRelativePosition(state, dx, dy){
		if(state != null){
			if(null != state.cell && null != state.cell.node_type && ('timerBoundaryEvent' == state.cell.node_type 
				|| 'errorBoundaryEvent' == state.cell.node_type 
				|| 'messageBoundaryEvent' == state.cell.node_type 
				|| 'cancelBoundaryEvent' == state.cell.node_type 
				|| 'compensationBoundaryEvent' == state.cell.node_type
				|| 'signalBoundaryEvent' == state.cell.node_type)){
				var model = graph.getModel();
				var geo = model.getGeometry(state.cell);
				if (geo != null && geo.relative && !model.isEdge(state.cell)){
					var parent = model.getParent(state.cell);
					if (model.isVertex(parent)){
						var pstate = graph.view.getState(parent);
						if (pstate != null){
							var scale = graph.view.scale;
							var x = state.x + dx;
							var y = state.y + dy;
							if (geo.offset != null){
								x -= geo.offset.x * scale;
								y -= geo.offset.y * scale;
							}
							x = (x - pstate.x) / pstate.width;
							y = (y - pstate.y) / pstate.height;
							if (Math.abs(y - 0.5) <= Math.abs((x - 0.5) / 2)){
								x = (x > 0.5) ? 1 : 0;
								y = Math.min(1, Math.max(0, y));
							}else{
								x = Math.min(1, Math.max(0, x));
								y = (y > 0.5) ? 1 : 0;
							}
							return new mxPoint(x, y);
						}
					}
				}
			}
		}
		return null;
	};
	graph.translateCell = function(cell, dx, dy){
		var rel = getRelativePosition(this.view.getState(cell), dx * graph.view.scale, dy * graph.view.scale);
		if(rel != null){
			var geo = this.model.getGeometry(cell);
			if (geo != null && geo.relative){
				geo = geo.clone();
				geo.x = rel.x;
				geo.y = rel.y;
				this.model.setGeometry(cell, geo);
			}
		}else{
			mxGraph.prototype.translateCell.apply(this, arguments);
		}
	};
	graph.isCellLocked = function(cell){
		return false;
	};
	new mxRubberband(graph);
	/////////////////////////////边界事件节点只能在父元素边框1/2位置----结束/////////////////////
	
    mxGraph.prototype.constrainRelativeChildren = true;
    ////////////////////////////////子标签操作开始////////////////////////////
    // 禁止折叠图标  用于泳道及子标签
    graph.isCellFoldable = function(cell){  
        return false;  
    } 
    var secondLabelVisible = true;  
    // 返回对于一个给定单元格的编号  
    graph.getSecondLabel = function(cell){  
        if(!this.model.isEdge(cell)){  
            // 可能返回一个字符串  
            return "ID="+cell.id;  
        }  
        return null;  
    };
    var relativeChildVerticesVisible = true;  
    // 隐藏相对于子元素的顶点  
    graph.isCellVisible = function(cell){  
        return !this.model.isVertex(cell) || cell.geometry == null ||  
            !cell.geometry.relative ||  
            cell.geometry.relative == relativeChildVerticesVisible;  
    };
    //创建历史
    var history = new mxUndoManager();
    // 移动/调整大小已被重新绘制的元素  
    ////////////////////////////////子标签操作结束////////////////////////////
    
    graph.flipEdge = function(edge){
		if(edge != null){
			var state = this.view.getState(edge);
			var style = (state != null) ? state.style : this.getCellStyle(edge);
			{
				var elbow = mxUtils.getValue(style, mxConstants.STYLE_ELBOW,
					mxConstants.ELBOW_HORIZONTAL);
				var value = (elbow == mxConstants.ELBOW_HORIZONTAL) ?
					mxConstants.ELBOW_VERTICAL : mxConstants.ELBOW_HORIZONTAL;
				this.setCellStyles(mxConstants.STYLE_ELBOW, value, [edge]);
			}
		}
    };
	//双击事件
	graph.addListener(mxEvent.DOUBLE_CLICK, function(sender, evt){
	  	var cell = evt.getProperty('cell');
	});
	//单击事件
	graph.addListener(mxEvent.CLICK, function(sender, evt){
	  	var cell = evt.getProperty('cell');
	  	/**
	  	if(typeof(cell) != "undefined" && null != cell && (cell.node_type == 'timerBoundaryEvent')){
			graph.setCellsResizable(false);
			return;
		}else{
			graph.setCellsResizable(true);
		}
		**/
	});
	//改变大小事件
	graph.addListener(mxEvent.CELLS_RESIZED, function(sender, evt){
		var cells = evt.getProperty('cells');
		/**
		if(typeof(cells) != "undefined" && null != cells && (cells[0].node_type == 'timerBoundaryEvent')){
			msgTishi('时间边界不能改变大小');
			graph.setCellsResizable(false);
			return;
		}else{
			graph.setCellsResizable(true);
		}
		**/
	});
	//捕获任务节点的鼠标点击事件
	graph.addListener(mxEvent.CLICK, function(sender, evt) {
	   var cell = evt.getProperty('cell');
//	   var nodeId = self.getTaskId(cell);
//	   if (nodeId.length > 0) {
//	    self.clickCell(self.graphId, nodeId);
//	   }
   	   if (cell!=null) { 
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
                       }
                       if(childsCellEdgs[i].target.id==cell.target.id){
                          //连线的下一个节点类型
                          var node_type = childsCellEdgs[i].target.node_type;
                       }
                   }
			 	}
			 }
       }else{
    	   var eItems =eastPanel.items;
//    	   console.info(eItems.length);
    	   for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
//				 console.info(eItems.get(le));
				 eastPanel.remove(eItems.get(le),true);
			 }
		   }
    	   basePanel.setHidden(false);
       }
	});
	//创建主要容器
	var mainPanel = new MainPanel(graph, history);
	//鼠标点击到节点上显示在节点周围显示图标
	initcellClickShowIcon(graph);
	//鼠标移动到节点上显示在节点周围显示图标
	initmouseOverIcon(graph);
	//时时预览（轮廓）
	//initOutlinePanel(graph);
    //载入默认样式
    var node = mxUtils.load('../view/pc/lc-view/lc-design/archive/grapheditor/resources/bight-style.xml').getDocumentElement();
	var dec = new mxCodec(node.ownerDocument);
	dec.decode(node, graph.getStylesheet());
	graph.alternateEdgeStyle = 'elbow=vertical;';
	var edgeStyle = graph.getStylesheet().getDefaultEdgeStyle(); 
	//edgeStyle[mxConstants.STYLE_EDGE] = mxEdgeStyle.TopToBottom;//直角
	edgeStyle['gradientColor'] = '#c0c0c0'; 
	edgeStyle['strokeColor'] = '#c0c0c0'; //更改连线默认样式此处为颜色
	edgeStyle['dashed'] = '1'; //虚线
	edgeStyle['fontSize'] = '8';
	edgeStyle['fontColor'] = '#000';
	edgeStyle['strokeWidth'] = 0.1;
	edgeStyle['arrowWidth'] = 0.1;  
    var updateHandler = function(){
		var data = [];
		var names = DiagramStore.getNames();
		for (var i = 0; i < names.length; i++){
			data.push([names[i]]);
		}
    };
    DiagramStore.addListener('put', updateHandler);
    DiagramStore.addListener('remove', updateHandler);
    updateHandler();
    initEastPanel(graph);
    initLeftPanel();
    initViewPortPanel(mainPanel);
   
    var lc_process_hideid = $('#lc_process_hideid').val();
	initImpfromform(graph,lc_process_hideid);
	graphPanel.body.dom.style.overflow = 'auto';
	var listener = function(sender, evt){
		history.undoableEditHappened(evt.getProperty('edit'));
	};
	graph.getModel().addListener(mxEvent.UNDO, listener);
	graph.getView().addListener(mxEvent.UNDO, listener);
	var undoHandler = function(sender, evt){
		var changes = evt.getProperty('edit').changes;
		graph.setSelectionCells(graph.getSelectionCellsForChanges(changes));
	};
	history.addListener(mxEvent.UNDO, undoHandler);
	history.addListener(mxEvent.REDO, undoHandler);
	//1连线类型菜单创建
	//var linebar = document.getElementById('linebar');
	//2开始事件菜单创建
	var starteventbar = document.getElementById('starteventbar');
	//3结束事件菜单创建
	var endeventbar = document.getElementById('endeventbar');
	//3任务类型菜单创建
	var taskbar = document.getElementById('taskbar');
	//4容器节点菜单创建
	var rqbar = document.getElementById('rqbar');
	//5默认网关菜单创建
	var defaultwebbar = document.getElementById('defaultwebbar');
	//6边界事件菜单创建
	var bjwebbar = document.getElementById('bjwebbar');
	//初始化mxGraph图形面板
	var container = document.getElementById("container");
	container.style.position = 'absolute';
	container.style.overflow = 'auto';
	container.style.left = '0px';
	container.style.top = '0px';
	container.style.right = '0px';
	container.style.bottom = '0px';
    graph.init(container);
    if (mxClient.IS_GC || mxClient.IS_SF){
    	graph.container.style.background = '-webkit-gradient(linear, 0% 0%, 100% 0%, from(#FFFFFF), to(#FFFFEE))';
    }else if (mxClient.IS_NS){
    	graph.container.style.background = '-moz-linear-gradient(left, #FFFFFF, #FFFFEE)';  
    }else if (mxClient.IS_IE){
    	graph.container.style.filter = 'progid:DXImageTransform.Microsoft.Gradient('+'StartColorStr=\'#FFFFFF\', EndColorStr=\'#FFFFEE\', GradientType=1)';
    }
    initSwimlane(graph);  
    initCellsSytle(graph);
	initAddCellIcon(graph,history);		
    var rubberband = new mxRubberband(graph);
	graph.getModel().beginUpdate();
	try{
		//加载初始化XML图形(注意最好放在这个地方进行加载，否则可能无法渲染出来图形)
    	//loadXml(graph);
	}finally{
		//更新
		graph.getModel().endUpdate();
	}
	//工具条按钮更新监听
	var toolbarItems = graphPanel.down('toolbar').items;
    //选择如剪切 拷贝 加粗等事件
    var selectionListener = function(){
    	var selected = !graph.isSelectionEmpty();
    	toolbarItems.get('cut').setDisabled(!selected);
    	toolbarItems.get('copy').setDisabled(!selected);
    	toolbarItems.get('delete').setDisabled(!selected);
//    	toolbarItems.get('italic').setDisabled(!selected);
//    	toolbarItems.get('bold').setDisabled(!selected);
//    	toolbarItems.get('underline').setDisabled(!selected);
    };
    graph.getSelectionModel().addListener(mxEvent.CHANGE, selectionListener);
    //重做 或 撤销
    var historyListener = function(){
    	toolbarItems.get('undo').setDisabled(!history.canUndo());
    	toolbarItems.get('redo').setDisabled(!history.canRedo());
    };
	history.addListener(mxEvent.ADD, historyListener);
	history.addListener(mxEvent.UNDO, historyListener);
	history.addListener(mxEvent.REDO, historyListener);
	selectionListener();
	historyListener();
    var previousCreateGroupCell = graph.createGroupCell;
    graph.createGroupCell = function(){
    	var group = previousCreateGroupCell.apply(this, arguments);
    	group.setStyle('group');
    	return group;
    };
    graph.connectionHandler.createEdgeState = function(me){
    	if (GraphEditor.edgeTemplate != null){
    		return graph.view.createState(GraphEditor.edgeTemplate);
    	}
    	return null;
    };
    graph.connectionHandler.factoryMethod = function(){
		if (GraphEditor.edgeTemplate != null){
    		return graph.cloneCells([GraphEditor.edgeTemplate])[0];
    	}
		return null;
    };
	var drillHandler = function(sender){
		var model = graph.getModel();
		var cell = graph.getCurrentRoot();
		var title = '';
		while (cell != null && model.getParent(model.getParent(cell)) != null){
			if (graph.isValidRoot(cell)){
				title = ' > ' +
				graph.convertValueToString(cell) + title;
			}
			cell = graph.getModel().getParent(cell);
		}
		document.title = 'Graph Editor' + title;
	};
	graph.getView().addListener(mxEvent.DOWN, drillHandler);
	graph.getView().addListener(mxEvent.UP, drillHandler);
	graph.container.focus();
    var keyHandler = new mxKeyHandler(graph);
    keyHandler.enter = function() {};
    keyHandler.bindKey(8, function(){
    	graph.foldCells(true);
    });
    keyHandler.bindKey(13, function(){
    	graph.foldCells(false);
    });
    keyHandler.bindKey(33, function(){
    	graph.exitGroup();
    });
    keyHandler.bindKey(34, function(){
    	graph.enterGroup();
    });
    keyHandler.bindKey(36, function(){
    	graph.home();
    });
    keyHandler.bindKey(35, function(){
    	graph.refresh();
    });
    keyHandler.bindKey(37, function(){
    	graph.selectPreviousCell();
    });
    keyHandler.bindKey(38, function(){
    	graph.selectParentCell();
    });
    keyHandler.bindKey(39, function(){
    	graph.selectNextCell();
    });
    keyHandler.bindKey(40, function(){
    	graph.selectChildCell();
    });
    keyHandler.bindKey(46, function(){
    	graph.removeCells();
    });
    keyHandler.bindKey(107, function(){
    	graph.zoomIn();
    });
    keyHandler.bindKey(109, function(){
    	graph.zoomOut();
    });
    keyHandler.bindKey(113, function(){
    	graph.startEditingAtCell();
    });
    keyHandler.bindControlKey(65, function(){
    	graph.selectAll();
    });
    keyHandler.bindControlKey(89, function(){
    	history.redo();
    });
    keyHandler.bindControlKey(90, function(){
    	history.undo();
    });
    keyHandler.bindControlKey(88, function(){
    	mxClipboard.cut(graph);
    });
    keyHandler.bindControlKey(67, function(){
    	mxClipboard.copy(graph);
    });
    keyHandler.bindControlKey(86, function(){
    	mxClipboard.paste(graph);
    });
    keyHandler.bindControlKey(71, function(){
    	graph.setSelectionCell(graph.groupCells(null, 20));
    });
    keyHandler.bindControlKey(85, function(){
    	graph.setSelectionCells(graph.ungroupCells());
    });
   });
};
