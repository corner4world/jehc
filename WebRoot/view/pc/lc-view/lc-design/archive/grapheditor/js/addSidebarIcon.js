function initAddCellIcon(graph,history){
	/**
    addSidebarIcon(graph, linebar, '选择','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/select.gif',false,'select',1);  
    addSidebarIcon(graph, linebar, '直线','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/zxline.gif', false,'zxline',1);  
    addSidebarIcon(graph, linebar, '直角','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/zjline.gif', false,'zjline',1);  
    addSidebarIcon(graph, linebar, '曲线','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/qxline.gif', false,'qxline',1);  
    addSidebarIcon(graph, linebar, '虚线','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/xxline.gif', false,'xxline',1);  
    **/
    
    addSidebarIcon(graph, starteventbar, '开始','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/startEvent.png', false,'startEvent',2);  
    addSidebarIcon(graph, starteventbar, '定时启动事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/timerStartEvent.png', false,'timerStartEvent',2);  
    addSidebarIcon(graph, starteventbar, '消息开始事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/messageStartEvent.png', false,'messageStartEvent',2);  
    addSidebarIcon(graph, starteventbar, '错误启动事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/errorStartEvent.png', false,'errorStartEvent',2);  
    addSidebarIcon(graph, starteventbar, '信号启动事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalStartEvent.png', false,'signalStartEvent',2);  
    
    addSidebarIcon(graph, endeventbar, '结束','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/endEvent.png', false,'endEvent',3);  
    addSidebarIcon(graph, endeventbar, '错误结束事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/errorEndEvent.png', false,'errorEndEvent',3);  
    addSidebarIcon(graph, endeventbar, '终止结束事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/terminateEndEvent.png', false,'terminateEndEvent',3);  
    addSidebarIcon(graph, endeventbar, '取消结束事件','../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/cancelEndEvent.png', false,'cancelEndEvent',3);  
   
    addSidebarIcon(graph, taskbar,'人工任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/userTask.png', false,'userTask',4);
    addSidebarIcon(graph, taskbar,'手动任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/manualTask.png', false,'manualTask',4);
    addSidebarIcon(graph, taskbar,'脚本任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/scriptTask.png', false,'scriptTask',4);
    addSidebarIcon(graph, taskbar,'服务任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/serviceTask.png', false,'serviceTask',4);
    addSidebarIcon(graph, taskbar,'邮件任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/mailTask.png', false,'mailTask',4);
    addSidebarIcon(graph, taskbar,'接收任务', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/receiveTask.png', false,'receiveTask',4);
    addSidebarIcon(graph, taskbar,'业务规则', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/businessRuleTask.png', false,'businessRuleTask',4);
    addSidebarIcon(graph, taskbar,'调用子流程', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/callActivity.png', false,'callActivity',4);
   
    addSidebarIcon(graph, defaultwebbar,'并行网关', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/parallelGateway.png', false,'parallelGateway',5);
    addSidebarIcon(graph, defaultwebbar,'排他网关', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/exclusiveGateway.png', false,'exclusiveGateway',5);
    addSidebarIcon(graph, defaultwebbar,'包括网关', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/inclusiveGateway.png', false,'inclusiveGateway',5);
    addSidebarIcon(graph, defaultwebbar,'事件网关', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/eventGateway.png', false,'eventGateway',5);
  	
  	addSidebarIcon(graph, rqbar,'事件子流程', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/eventSubProcess.png', false,'eventSubProcess',6);
    addSidebarIcon(graph, rqbar,'事物流程', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/transactionProcess.png', false,'transactionProcess',6);
    addSidebarIcon(graph, rqbar,'子流程', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/subProcess.png', false,'subProcess',6);
    addSidebarIcon(graph, rqbar,'泳道池', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/pool.png', false,'pool',6);
    addSidebarIcon(graph, rqbar,'泳道', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/lane.png', false,'lane',6);
    
    addSidebarIcon(graph, bjwebbar,'时间边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/boundaryEventTime.png', false,'timerBoundaryEvent',7);
    addSidebarIcon(graph, bjwebbar,'错误边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/exclusiveGatewayError.png', false,'errorBoundaryEvent',7);
    addSidebarIcon(graph, bjwebbar,'消息边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/messageBoundaryEvent.png', false,'messageBoundaryEvent',7);
    addSidebarIcon(graph, bjwebbar,'取消边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/cancelBoundaryEvent.png', false,'cancelBoundaryEvent',7);
    addSidebarIcon(graph, bjwebbar,'补偿边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/compensationBoundaryEvent.png', false,'compensationBoundaryEvent',7);
    addSidebarIcon(graph, bjwebbar,'信号边界', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalBoundaryEvent.png', false,'signalBoundaryEvent',7);
    
    addSidebarIcon(graph, zjwebbar,'定时捕捉事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/timerCatchingEvent.png', false,'timerCatchingEvent',8);
    addSidebarIcon(graph, zjwebbar,'信号捕捉事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalCatchingEvent.png', false,'signalCatchingEvent',8);
    addSidebarIcon(graph, zjwebbar,'消息捕捉事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/messageCatchingEvent.png', false,'messageCatchingEvent',8);
    addSidebarIcon(graph, zjwebbar,'信号抛出事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalThrowingEvent.png', false,'signalThrowingEvent',8);
    addSidebarIcon(graph, zjwebbar,'补偿抛出事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/compensationThrowingEvent.png', false,'compensationThrowingEvent',8);
    addSidebarIcon(graph, zjwebbar,'非抛出事件', '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/noneThrowingEvent.png', false,'noneThrowingEvent',8);
    
    
    //添加边栏图标  
    function addSidebarIcon(graph, sidebar, label, image, rhombus,node_type,menuType){  
       var cells = [new mxCell(label, new mxGeometry(0, 0, 32, 32), image),'symbol;image='+image];
       cells[0].vertex = true;
       cells[0].node_type = node_type;
       var funct = function(graph, evt, target,x,y){  
       		//创建默认窗体  
    		var parent = graph.getDefaultParent();  
    		graph.getModel().beginUpdate();
    		try{
	       		cells = graph.getImportableCells(cells);
	       		//graph.getDefaultParent().node_type = node_type;/////////////dengcj注释暂时不用设置
	            var model = graph.getModel();  
	            var mcell;
	            //使用时候image4gray为上述定义
	            //console.log(node_type);
	            if(node_type == 'select'){
	            	//重新选择
	            	graph.panningHandler.useLeftButtonForPanning = false;
					graph.setConnectable(false);
					msgTishi("不支持该操作");
	            	return;
	            }else if(node_type == 'zxline'){
	            	//直线连接
	            	graph.panningHandler.useLeftButtonForPanning = false;
					graph.setConnectable(true);
	            	connectEdge('strokeWidth=3;labelBackgroundColor=white;fontStyle=1');
	            	msgTishi("不支持该操作");
	           		return;
	            }else if(node_type == 'zjline'){
	            	//直角连接
	            	graph.panningHandler.useLeftButtonForPanning = false;
					graph.setConnectable(true);
	            	connectEdge('edgeStyle=orthogonalEdgeStyle;');
	            	msgTishi("不支持该操作");
	            	return;
	            }else if(node_type == 'qxline'){
	            	//曲线连接
	            	graph.panningHandler.useLeftButtonForPanning = false;
					graph.setConnectable(true);
	            	connectEdge('edgeStyle=elbowEdgeStyle;elbow=horizontal;orthogonal=0;entryPerimeter=1;');
	            	msgTishi("不支持该操作");
	            	return;
	            }else if(node_type == 'xxline'){
	            	//虚线连接
	            	graph.panningHandler.useLeftButtonForPanning = false;
					graph.setConnectable(true);
	            	connectEdge('crossover');
	            	msgTishi("不支持该操作");
	            	return;
	            }else if(node_type == 'startEvent' || node_type == 'timerStartEvent' || node_type == 'messageStartEvent' || node_type == 'errorStartEvent' || node_type == 'signalStartEvent'){
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道池中
	            		if(node_type == 'startEvent'){
	            			//排除开始节点不能放任事件子流程中
	            			if(target.node_type == 'eventSubProcess'){
	            				msgTishi("开始事件不能放入事件子流程节点中、请重新选择!");
			            		return;
	            			}else{
	            				var xyArray = resultXy(x,y,graph,target);
	            				mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'start_image4gray;rounded=true;strokeColor=none;fillColor=yellow;size=12'); 
	            			}
	            		}
	            		if(node_type == 'timerStartEvent'){
	            			//排除定时启动事件不能放任事件子流程中
	            			if(typeof(target) != "undefined" && target.node_type == 'eventSubProcess'){
	            				msgTishi("定时启动事件不能放入事件子流程节点中、请重新选择!");
			            		return;
	            			}else{
	            				var xyArray = resultXy(x,y,graph,target);
	            				mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'timerStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            			}
	            		}
	            		if(node_type == 'messageStartEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'messageStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		}
	            		if(node_type == 'errorStartEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			if(typeof(target) != "undefined" && null != target && (target.node_type == 'eventSubProcess')){
			            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'errorStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
			            	}else{
			            		msgTishi("错误启动事件只能放入事件子流程节点中、请重新选择!");
			            		return;
			            	}
	            		}
	            		if(node_type == 'signalStartEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'signalStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		}
	            	}else{
	            		if(node_type == 'startEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'start_image4gray;rounded=true;strokeColor=none;fillColor=yellow;'); 
	            		}
	            		if(node_type == 'timerStartEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'timerStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		}
	            		if(node_type == 'messageStartEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'messageStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		}
	            		if(node_type == 'errorStartEvent'){
	            			if(typeof(target) != "undefined" && null != target && (target.node_type == 'eventSubProcess')){
			            		mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'errorStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
			            	}else{
			            		msgTishi("错误启动事件只能放入事件子流程节点中、请重新选择!");
			            		return;
			            	}
	            		}
	            		if(node_type == 'signalStartEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'signalStartEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		}
	            	}
	            }else if(node_type == 'endEvent' || node_type == 'errorEndEvent' || node_type == 'terminateEndEvent' || node_type == 'cancelEndEvent'){
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		if(node_type == 'endEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'end_image4gray;rounded=true;strokeColor=none;fillColor=yellow;');
	            		}
	            		if(node_type == 'errorEndEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'errorEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            		if(node_type == 'terminateEndEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'terminateEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            		if(node_type == 'cancelEndEvent'){
	            			var xyArray = resultXy(x,y,graph,target);
	            			mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'cancelEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            	}else{
	            		if(node_type == 'endEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'end_image4gray;rounded=true;strokeColor=none;fillColor=yellow;');
	            		}
	            		if(node_type == 'errorEndEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'errorEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            		if(node_type == 'terminateEndEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'terminateEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            		if(node_type == 'cancelEndEvent'){
	            			mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'cancelEndEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            		}
	            	} 
	            }else if(node_type == 'timerBoundaryEvent'){
	            	//时间边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'userTask' || target.node_type == 'manualTask' 
	            		|| target.node_type == 'scriptTask' 
	            		|| target.node_type == 'serviceTask' || target.node_type == 'mailTask' 
	            		|| target.node_type == 'receiveTask' || target.node_type == 'businessRuleTask' 
	            		|| target.node_type == 'callActivity' || target.node_type == 'eventSubProcess' 
	            		|| target.node_type == 'transactionProcess' || target.node_type == 'subProcess')){
	            		/**左边**/
	            		//mcell = graph.insertVertex(target, image, label, 0, 0, 24, 24,'timerBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;labelPosition=left;align=right'); 
	            		/**右边**/
	            		//mcell = graph.insertVertex(target, image, label, 0, 0, 24, 24,'timerBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		/**下边**/
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;timerBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            		/**上边**/
	            		//mcell = graph.insertVertex(target, image, label, 0, 0, 24, 24,'timerBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=top;verticalAlign=bottom'); 
	            	}else{
	            		//msgTishi("时间边界事件只能放入任务节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'errorBoundaryEvent'){
	            	//错误边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'scriptTask' || target.node_type == 'serviceTask' 
	            		|| target.node_type == 'mailTask' || target.node_type == 'callActivity'
	            		|| target.node_type == 'eventSubProcess' 
	            		|| target.node_type == 'transactionProcess' || target.node_type == 'subProcess')){
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;errorBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            		
	            	}else{
	            		//msgTishi("错误边界事件只能放入任务节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'messageBoundaryEvent'){
	            	//消息边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'userTask' || target.node_type == 'manualTask' 
	            		|| target.node_type == 'scriptTask' || target.node_type == 'serviceTask' 
	            		|| target.node_type == 'mailTask' || target.node_type == 'receiveTask' 
	            		|| target.node_type == 'businessRuleTask' || target.node_type == 'callActivity'
	            		|| target.node_type == 'eventSubProcess' 
	            		|| target.node_type == 'transactionProcess' || target.node_type == 'subProcess')){
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;messageBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            	}else{
	            		//msgTishi("消息边界事件只能放入任务节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'cancelBoundaryEvent'){
	            	//取消边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'transactionProcess')){
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;cancelBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            	}else{
	            		//msgTishi("取消边界事件只能放入事物流程节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'compensationBoundaryEvent'){
	            	//补偿边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'userTask' 
	            		|| target.node_type == 'manualTask' || target.node_type == 'scriptTask' || target.node_type == 'serviceTask' 
	            		|| target.node_type == 'mailTask' || target.node_type == 'receiveTask' || target.node_type == 'businessRuleTask' 
	            		|| target.node_type == 'callActivity')){
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;compensationBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            	}else{
	            		//msgTishi("补偿边界事件只能放入任务节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'signalBoundaryEvent'){
	            	//信号边界
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'userTask' 
	            		|| target.node_type == 'manualTask' || target.node_type == 'scriptTask' || target.node_type == 'serviceTask' 
	            		|| target.node_type == 'mailTask' || target.node_type == 'receiveTask' || target.node_type == 'businessRuleTask' 
	            		|| target.node_type == 'callActivity' || target.node_type == 'eventSubProcess' || target.node_type == 'subProcess' 
	            		|| target.not_type == 'transactionProcess')){
	            		mcell = graph.insertVertex(target, null, label, 0, 0.5, 24, 24,'gradientColor=#ffefbb;signalBoundaryEvent_image4gray;rounded=true;strokeColor=none;fillColor=ffefbb;verticalLabelPosition=bottom;verticalAlign=top'); 
	            		mcell.geometry.offset = new mxPoint(-10, -10);
						mcell.geometry.relative = true;
	            	}else{
	            		//msgTishi("信号边界事件只能放入任务，事件子流程，事物流程或子流程节点中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'eventSubProcess' || node_type == 'transactionProcess' || node_type == 'subProcess'){
	            	if(typeof(target) != "undefined" && null != target && target.node_type == 'lane'){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 420, 180,'other_style;gradientColor=#fff;fillColor=#fff;');  
	            	}else if(typeof(target) != "undefined" && null != target 
	            		&&(target.node_type == 'eventSubProcess' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess')){
	            		//如果在节点在事物子流程 事件子流程 子流程等节点中则也可以
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 220, 120,'other_style;gradientColor=#fff;fillColor=#fff;');  
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 540, 180,'other_style;gradientColor=#fff;fillColor=#fff;'); 
	            	}
	            	mcell.setConnectable(true); 
	            }else if(node_type == 'pool'){
			        mcell = graph.insertVertex(parent, null, label, x, y, 540, 180,'gradientColor=#fff;fillColor=#fff;pool_style;');  
			        mcell.setConnectable(false);  
			        var nums = new Date().getTime();
	           		mcell.processId_ = 'process'+nums;
	           		mcell.processName_ = '流程名称';
	            }else if(node_type == 'lane'){
	            	if(typeof(target) != "undefined" && null != target && target.node_type == 'pool'){
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 540, 180,'gradientColor=#fff;fillColor=#fff;pool_style');  
			        	mcell.setConnectable(false);
	            	}else{
	            		msgTishi("泳道必须放入泳道池中、请重新选择!");
	            		return;
	            	}
	            }else if(node_type == 'timerCatchingEvent' || node_type == 'signalCatchingEvent' || node_type == 'messageCatchingEvent' || node_type == 'signalThrowingEvent' || node_type == 'compensationThrowingEvent' || node_type == 'noneThrowingEvent'){
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道池中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageAlign=center;verticalAlign=bottom;fontSize=8;fontColor=#000'); 
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageAlign=center;verticalAlign=bottom;fontSize=8;fontColor=#000'); 
	            	}
	            }else if(node_type == 'userTask' || node_type == 'manualTask' || node_type == 'scriptTask' || node_type == 'serviceTask' || node_type == 'mailTask' || node_type == 'receiveTask' || node_type == 'businessRuleTask' || node_type == 'callActivity'){
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道池中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageWidth=12;imageHeight=12;imageAlign=center;verticalAlign=bottom;fontSize=8;fontColor=#000'); 
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageWidth=12;imageHeight=12;imageAlign=center;verticalAlign=bottom;fontSize=8;fontColor=#000;'); 
	            	}
	            }else if(node_type == 'exclusiveGateway'){
	            	//并行网关
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'exclusiveGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'exclusiveGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	} 
	            }else if(node_type == 'parallelGateway'){
	            	//排他网关
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'parallelGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'parallelGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	} 
	            }else if(node_type == 'inclusiveGateway'){
	            	//包括网关
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'inclusiveGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'inclusiveGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	} 
	            }else if(node_type == 'eventGateway'){
	            	//事件网关
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 32, 32,'eventGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 32, 32,'eventGateway_image4gray;rounded=true;strokeColor=none;fillColor=yellow;verticalLabelPosition=bottom;verticalAlign=top');
	            	} 
	            }else{
	            	if(typeof(target) != "undefined" && null != target && (target.node_type == 'lane' || target.node_type == 'transactionProcess' || target.node_type == 'subProcess' || target.node_type == 'eventSubProcess')){
	            		//在泳道中
	            		var xyArray = resultXy(x,y,graph,target);
	            		mcell = graph.insertVertex(target, null, label, xyArray[0], xyArray[1], 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageAlign=center;verticalAlign=bottom;fontSize=8'); 
	            	}else{
	            		mcell = graph.insertVertex(parent, null, label, x, y, 108, 50,'gradientColor=#ffefbb;rounded=true;strokeColor=none;fillColor=#ffefbb;image='+image+';imageAlign=center;verticalAlign=bottom;fontSize=8'); 
	            	}
	            	////////////////////////////////以下为添加边界图片///////////////////////
	            	/**
	            	// 复写按钮图片  
	                var overlay = new mxCellOverlay(  
	                    new mxImage('../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/boundaryEventTime.png', 32, 32), '边界事件');
	                    // 在图形中覆盖  
	                    graph.addCellOverlay(mcell, overlay);  
	                    // 单击显示提示 
	                    overlay.addListener(mxEvent.CLICK, function(sender, evt2){  
	                        mxUtils.alert('单击');  
	                    });
	                 overlay.cursor = 'hand';  
	   				 overlay.align = mxConstants.ALIGN_CENTER;  
	   				 console.info(overlay);
	                 // 复写按钮图片  
	                 var overlay1 = new mxCellOverlay(  
	                    new mxImage('../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/exclusiveGatewayError.png', 32, 32), '错误');
	                    // 在图形中覆盖  
	                    graph.addCellOverlay(mcell, overlay1);  
	                    // 单击显示提示 
	                    overlay1.addListener(mxEvent.CLICK, function(sender, evt2){  
	                        mxUtils.alert('单击');  
	                    });
	                	///////////////////////////////////以上为添加边界事件图片/////////////////////////////
	                	**/
	                    //双击显示提示
	            		//graph.insertVertex(mcell, null, '边界事件', x, y, 32, 32,'start_image4gray;rounded=true;strokeColor=none;fillColor=yellow;'); 
	            }
	            if(null != mcell){
	            	var nums = new Date().getTime();
	           		mcell.node_type = node_type;
	           		mcell.nodeID = node_type+nums;
	           		mcell.value = label;
	            }
	            validateMoveCell(graph, history,mcell);
	            graph.stopEditing(false);
	            //验证泳道是否存在 如果存在则基本信息为第一个泳道中流程信息  
	            validatePOOL(graph);
	    	}finally{
				graph.getModel().endUpdate();
			}
	    	
        }  
        //创建用来拖动的侧边栏图标 
        var br = document.createElement('br');  
        //1.创建DIV
        var div = document.createElement('div');
        //div.id="round";//圆角
		div.style.height = "50px";
		div.style.width = "120px";
		div.style.border = "1px";
		div.style.left = '20px';
		div.style.background="#157fcc";
		div.style.marginTop="-10px";
		sidebar.appendChild(br);
		//创建图片
        var img = document.createElement('img');  
        img.setAttribute('src', image);  
        img.style.width = '16px';  
        img.style.height = '16px'; 
        img.title=label;
        img.style.position = 'absolute'; 
        var height = div.style.height;  
    	var width = div.style.width;  
    	img.style.marginTop = "10px";  
    	img.style.marginLeft = "54px";  
        div.appendChild(img); 
        
        //创建文字
        var font = document.createElement('span');
        font.style.color = 'white'; 
        font.style.position = 'absolute'; 
        if('userTask' == node_type 
            || 'manualTask' == node_type 
        	|| 'decision' == node_type 
        	|| 'serviceTask' == node_type
        	|| 'inclusiveGateway' == node_type
        	|| 'scriptTask' == node_type
        	|| 'mailTask' == node_type
        	|| 'receiveTask' == node_type
        	|| 'businessRuleTask' == node_type
        	|| 'timerBoundaryEvent' == node_type
        	|| 'parallelGateway' == node_type
        	|| 'exclusiveGateway' == node_type
        	|| 'errorBoundaryEvent' == node_type
        	|| 'transactionProcess' == node_type
        	|| 'inclusiveGateway' == node_type
        	|| 'eventGateway' == node_type
        	|| 'messageBoundaryEvent' == node_type
        	|| 'cancelBoundaryEvent' == node_type
        	|| 'compensationBoundaryEvent' == node_type
        	|| 'signalBoundaryEvent' == node_type){
        	font.style.padding="30px 0px 0px 30px";
        }else if('select' == node_type
        		 || 'zxline' == node_type
        		 || 'zjline' == node_type
        		 || 'qxline' == node_type
        		 || 'xxline' == node_type
        		 || 'startEvent' == node_type
        		 || 'endEvent' == node_type
        		 || 'lane' == node_type
        		){
        	font.style.padding="30px 30px 0px 48px";
        }else if('subProcess' == node_type || 'pool' == node_type){
        	font.style.padding="30px 30px 0px 42px";
        }else if('eventSubProcess' == node_type  || 'callActivity' == node_type || 'noneThrowingEvent' == node_type){
        	font.style.padding="30px 0px 0px 24px";
        }else if('timerStartEvent' == node_type 
        		 || 'messageStartEvent' == node_type 
        		 || 'signalStartEvent' == node_type
        		 || 'errorStartEvent' == node_type
        		 || 'cancelEndEvent' == node_type
        		 || 'errorEndEvent' == node_type
        		 || 'terminateEndEvent' == node_type
        		 || 'timerStartEvent' == node_type
        		 || 'timerCatchingEvent' == node_type
        		 || 'signalCatchingEvent' == node_type
        		 || 'messageCatchingEvent' == node_type
        		 || 'signalThrowingEvent' == node_type
        		 || 'compensationThrowingEvent' == node_type){
        	font.style.padding="30px 0px 0px 18px";
        }
        font.style.fontWeight="bold";//加粗
        font.innerHTML = label;
        div.appendChild(font);
        sidebar.appendChild(div); 
        var dragElt = document.createElement('div');  
        dragElt.style.border = 'dashed black 1px';  
        dragElt.style.width = '110px';  
        dragElt.style.height = '50px';  
        //创建的图像，它被用作拖动图标（预览）  
        var ds = mxUtils.makeDraggable(div, graph, funct, dragElt, 0, 0, true, true);  
        ds.setGuidesEnabled(true);  
    };  
}
