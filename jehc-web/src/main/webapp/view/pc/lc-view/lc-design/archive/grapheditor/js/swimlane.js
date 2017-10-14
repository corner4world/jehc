//////////////////////泳道图开始////////////////////////////
function initSwimlane(graph){
	graph.border = 80;  
    //自动调整大小的容器  
    //禁止子容器从父容器中移出
    graph.graphHandler.setRemoveCellsFromParent(false);  
    // 启用了图形添加了自动布局和各种开关  
    var model = graph.getModel(); 
    if (graph.isEnabled()){  
        graph.setConnectable(true);  
        graph.setAllowDanglingEdges(false);  
        /**
        var previousIsValidSource = graph.isValidSource;  
        graph.isValidSource = function(cell){  
            if(previousIsValidSource.apply(this, arguments)){  
                var style = this.getModel().getStyle(cell);  
                return style == null || !(style == 'end' || style.indexOf('end') == 0);  
            }  
            return false;  
        };  
        // 判断状态有无合法目标，不执行调用父类的功能，因为这会调用isValidSource  
        graph.isValidTarget = function(cell){ 
            var style = this.getModel().getStyle(cell);  
            return !this.getModel().isEdge(cell) &&  
                !this.isSwimlane(cell) &&  
                (style == null ||  
                !(style == 'state' ||  
                style.indexOf('state') == 0));  
        };  
        **/
        // 可以将新元素转换为新泳道，新泳道为新泳道池  
        graph.setDropEnabled(true);  
        graph.setSplitEnabled(false);  
        // 有效的拖放操作，则返回true  
        graph.isValidDropTarget = function(target, cells, evt){  
        	//////////////////自定义验证拖拽时候更新目标为父节点开始///////////////////
        	//1.如果选择的节点是向根节点拖拽则判断泳道池是否在选中的节点中即可 如果在则无效
        	var number = 0;
        	for (var i = 0; i < cells.length; i++){ 
           		var tmp = cells[i]; 
           		//1.0如果目标节点是连线则终止
           		if(target.edge){
           			graph.graphHandler.setRemoveCellsFromParent(false);  
           			return false;
           		}
           		//1.1如果当前节点是泳道节点拖拽情况 并且目标也是到泳道中则终止
           		if(tmp.node_type == 'pool'){
           			graph.graphHandler.setRemoveCellsFromParent(false);  
           			return false;
           		}
           		//1.2如果当前节点是边界事件 只能在任务或事物流程 事件流程 子流程节点中
           		if(tmp.node_type == 'timerBoundaryEvent'
           			|| tmp.node_type == 'errorBoundaryEvent'
           			|| tmp.node_type == 'messageBoundaryEvent'
           			|| tmp.node_type == 'compensationBoundaryEvent'
           			|| tmp.node_type == 'signalBoundaryEvent'){
           			//1.2.1特殊处理当前节点是信号事件或消息边界或时间边界 则目标只能是任务 事件子流程 事物子流程，子流程 此时才可通过（但是当前节点拖放到目标节点中依然不能离开父节点）
           			if(tmp.node_type == 'signalBoundaryEvent' || tmp.node_type == 'messageBoundaryEvent' || tmp.node_type == 'timerBoundaryEvent'){
           				if( target.node_type == 'userTask' 
	            			|| target.node_type == 'manualTask' 
	            			|| target.node_type == 'scriptTask'
	            			|| target.node_type == 'serviceTask'
	            			|| target.node_type == 'mailTask'
	            			|| target.node_type == 'receiveTask'
	            			|| target.node_type == 'businessRuleTask'
	            			|| target.node_type == 'callActivity'
	            			|| target.node_type == 'eventSubProcess'
	            			|| target.node_type == 'transactionProcess'
	            			|| target.node_type == 'subProcess'){
	            			///////////////////////此时需要注意判断如果该次选中的是多个节点进行拖拽那怎么办呢？所以需要判断该次一起拖拽的节点是否拥有不合法的节点，如果有则禁止拖拽即无效
	            			for(var j = 0; j < cells.length; j++){
	            				//如果当前边界有取消边界，错误边界则禁止
	            				if(cells[j].node_type == 'errorBoundaryEvent' 
	            					|| cells[j].node_type == 'cancelBoundaryEvent'){
	            					graph.graphHandler.setRemoveCellsFromParent(false);  
	            					return false
	            				}
	            			}
	            			//虽然放置节点有效 但是到新的目标中 依然不能离开父节点
	            			graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return true;
	           			}else{
	           				graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return false;
	           			}
           			}else if(tmp.node_type == 'errorBoundaryEvent'){
           				//1.2.3错误边界只能存在脚本任务，服务任务，邮件任务，调用子流程，事物子流程，事件流程，子流程中
           				if( target.node_type == 'scriptTask'
	            			|| target.node_type == 'serviceTask'
	            			|| target.node_type == 'mailTask'
	            			|| target.node_type == 'callActivity'
	            			|| target.node_type == 'eventSubProcess'
	            			|| target.node_type == 'transactionProcess'
	            			|| target.node_type == 'subProcess'){
	            			
	            			///////////////////////此时需要注意判断如果该次选中的是多个节点进行拖拽那怎么办呢？所以需要判断该次一起拖拽的节点是否拥有不合法的节点，如果有则禁止拖拽即无效
	            			for(var j = 0; j < cells.length; j++){
	            				//如果当前边界有取消边界
	            				if(cells[j].node_type == 'cancelBoundaryEvent'){
	            					graph.graphHandler.setRemoveCellsFromParent(false);  
	            					return false
	            				}
	            			}
	            			//虽然放置节点有效 但是到新的目标中 依然不能离开父节点
	            			graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return true;
	           			}else{
	           				graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return false;
	           			}
           			}else{
           				//1.2.4 除此之外即为补偿事件只能放在任务中
           				if( target.node_type == 'userTask' 
	            			|| target.node_type == 'manualTask' 
	            			|| target.node_type == 'scriptTask'
	            			|| target.node_type == 'serviceTask'
	            			|| target.node_type == 'mailTask'
	            			|| target.node_type == 'receiveTask'
	            			|| target.node_type == 'businessRuleTask'
	            			|| target.node_type == 'callActivity'){
	            			///////////////////////此时需要注意判断如果该次选中的是多个节点进行拖拽那怎么办呢？所以需要判断该次一起拖拽的节点是否拥有不合法的节点，如果有则禁止拖拽即无效
	            			for(var j = 0; j < cells.length; j++){
	            				//如果当前边界有取消边界，错误边界则禁止
	            				if(cells[j].node_type == 'errorBoundaryEvent' 
	            					|| cells[j].node_type == 'cancelBoundaryEvent'){
	            					graph.graphHandler.setRemoveCellsFromParent(false);  
	            					return false
	            				}
	            			}
	            			//虽然放置节点有效 但是到新的目标中 依然不能离开父节点
	            			graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return true;
	           			}else{
	           				graph.graphHandler.setRemoveCellsFromParent(false);  
	           				return false;
	           			}
           			}
           		}
           		//1.3如果当前节点为泳道 若目标不是泳道池则终止
           		if(tmp.node_type == 'lane'){
           			graph.graphHandler.setRemoveCellsFromParent(false);  
           			if(target.node_type == 'pool'){
           				return true;
           			}else{
           				return false;
           			}
           		}
           		//1.4如果当前节点为错误启动事件 若目标不是事件子流程或目标是泳道池则终止
           		if(tmp.node_type == 'errorStartEvent'){
           			if(typeof(target.node_type) == "undefined" 
           				|| target.node_type != 'eventSubProcess' 
           				|| target.node_type == 'pool'){
           				graph.graphHandler.setRemoveCellsFromParent(false);  
           				return false;
           			}
           		}
           		//1.5如果当前节点为开始事件 目标只能在子流程，事物事物流程或泳道池中
           		if((tmp.node_type == 'startEvent' || tmp.node_type == 'timerStartEvent') && 
           			(target.node_type == 'eventSubProcess' 
           				||target.node_type == 'startEvent'
           				||target.node_type == 'timerStartEvent'
           				||target.node_type == 'messageStartEvent'
           				||target.node_type == 'errorStartEvent'
           				||target.node_type == 'signalStartEvent'
           				
           				||target.node_type == 'endEvent'
           				||target.node_type == 'errorEndEvent'
           				||target.node_type == 'terminateEndEvent'
           				||target.node_type == 'cancelEndEvent'
           				
           				||target.node_type == 'userTask'
           				||target.node_type == 'manualTask'
           				||target.node_type == 'scriptTask'
           				||target.node_type == 'serviceTask'
           				||target.node_type == 'mailTask'
           				||target.node_type == 'receiveTask'
           				||target.node_type == 'businessRuleTask'
           				||target.node_type == 'callActivity'
           				
           				||target.node_type == 'parallelGateway'
           				||target.node_type == 'exclusiveGateway'
           				||target.node_type == 'inclusiveGateway'
           				||target.node_type == 'eventGateway'
           				
           				||target.node_type == 'pool'
           				
           				||target.node_type == 'timerBoundaryEvent'
           				||target.node_type == 'errorBoundaryEvent'
           				||target.node_type == 'messageBoundaryEvent'
           				||target.node_type == 'cancelBoundaryEvent'
           				||target.node_type == 'compensationBoundaryEvent'
           				||target.node_type == 'signalBoundaryEvent'
           				
           				||target.node_type == 'timerCatchingEvent'
           				||target.node_type == 'signalCatchingEvent'
           				||target.node_type == 'messageCatchingEvent'
           				||target.node_type == 'signalThrowingEvent'
           				||target.node_type == 'compensationThrowingEvent'
           				||target.node_type == 'noneThrowingEvent')){
           			graph.graphHandler.setRemoveCellsFromParent(false);  
           			return false;
           		}
           		//1.6取消边界事件只能放入事物节点中
           		if(tmp.node_type == 'cancelBoundaryEvent'){
           			if( target.node_type == 'transactionProcess'){
           				return true;
           			}else{
           				graph.graphHandler.setRemoveCellsFromParent(false);  
           				return false;
           			}
           		}
           		//1.7如果当前节点中间事件或网关或开始事件或错误事件或任务 若目标非子流程 事物子流程 事件子流程 泳道池则终止（即这些节点可以拖到根节点 也可以在泳道池中 也可以在子流程中 事物流程，事件子流程中）
           		if(tmp.node_type == 'timerCatchingEvent'
           			|| tmp.node_type == 'signalCatchingEvent'
           			|| tmp.node_type == 'messageCatchingEvent'
           			|| tmp.node_type == 'signalThrowingEvent'
           			|| tmp.node_type == 'compensationThrowingEvent'
           			|| tmp.node_type == 'noneThrowingEvent'
           			
           			|| tmp.node_type == 'parallelGateway'
           			|| tmp.node_type == 'inclusiveGateway'
           			|| tmp.node_type == 'eventGateway'
           			
           			/**不包含开始事件，错误启动事件与启动事件 原因开始事件，错误启动事件与启动事件在上面已经处理过了**/
           			|| tmp.node_type == 'messageStartEvent'
           			|| tmp.node_type == 'signalStartEvent'
           			
           			|| tmp.node_type == 'endEvent'
           			|| tmp.node_type == 'errorEndEvent'
           			|| tmp.node_type == 'terminateEndEvent'
           			|| tmp.node_type == 'cancelEndEvent'
           			
           			|| tmp.node_type == 'userTask'
           			|| tmp.node_type == 'manualTask'
           			|| tmp.node_type == 'scriptTask'
           			|| tmp.node_type == 'serviceTask'
           			|| tmp.node_type == 'mailTask'
           			|| tmp.node_type == 'receiveTask'
           			|| tmp.node_type == 'businessRuleTask'
           			|| tmp.node_type == 'callActivity'){
           			if(target.node_type == 'startEvent'
           				||target.node_type == 'timerStartEvent'
           				||target.node_type == 'messageStartEvent'
           				||target.node_type == 'errorStartEvent'
           				||target.node_type == 'signalStartEvent'
           				
           				||target.node_type == 'endEvent'
           				||target.node_type == 'errorEndEvent'
           				||target.node_type == 'terminateEndEvent'
           				||target.node_type == 'cancelEndEvent'
           				
           				||target.node_type == 'userTask'
           				||target.node_type == 'manualTask'
           				||target.node_type == 'scriptTask'
           				||target.node_type == 'serviceTask'
           				||target.node_type == 'mailTask'
           				||target.node_type == 'receiveTask'
           				||target.node_type == 'businessRuleTask'
           				||target.node_type == 'callActivity'
           				
           				||target.node_type == 'parallelGateway'
           				||target.node_type == 'exclusiveGateway'
           				||target.node_type == 'inclusiveGateway'
           				||target.node_type == 'eventGateway'
           				
           				||target.node_type == 'pool'
           				
           				||target.node_type == 'timerBoundaryEvent'
           				||target.node_type == 'errorBoundaryEvent'
           				||target.node_type == 'messageBoundaryEvent'
           				||target.node_type == 'cancelBoundaryEvent'
           				||target.node_type == 'compensationBoundaryEvent'
           				||target.node_type == 'signalBoundaryEvent'
           				
           				||target.node_type == 'timerCatchingEvent'
           				||target.node_type == 'signalCatchingEvent'
           				||target.node_type == 'messageCatchingEvent'
           				||target.node_type == 'signalThrowingEvent'
           				||target.node_type == 'compensationThrowingEvent'
           				||target.node_type == 'noneThrowingEvent'){  
           				graph.graphHandler.setRemoveCellsFromParent(false);  
           				return false;   
           			}
           		}
           		//1.8如果当前节点事件子流程，事物子流程，子流程 若目标也为事件子流程，事物子流程，子流程则终止，任务，开始事件，结束事件，泳道池，网关，边界事件，中间事件
           		if(tmp.node_type == 'eventSubProcess' || tmp.node_type == 'transactionProcess' || tmp.node_type == 'subProcess'){
           			if(/**target.node_type == 'eventSubProcess' 
           				|| target.node_type == 'transactionProcess' 
           				|| target.node_type == 'subProcess'||**/
           				target.node_type == 'pool'
           				
           				||target.node_type == 'parallelGateway'
           				||target.node_type == 'exclusiveGateway'
           				||target.node_type == 'inclusiveGateway'
           				||target.node_type == 'eventGateway'
           				
           				||target.node_type == 'timerBoundaryEvent'
           				||target.node_type == 'errorBoundaryEvent'
           				||target.node_type == 'messageBoundaryEvent'
           				||target.node_type == 'cancelBoundaryEvent'
           				||target.node_type == 'compensationBoundaryEvent'
           				||target.node_type == 'signalBoundaryEvent'
           				
           				||target.node_type == 'timerCatchingEvent'
           				||target.node_type == 'signalCatchingEvent'
           				||target.node_type == 'messageCatchingEvent'
           				||target.node_type == 'signalThrowingEvent'
           				||target.node_type == 'compensationThrowingEvent'
           				||target.node_type == 'noneThrowingEvent'
           				
           				||target.node_type == 'userTask'
           				||target.node_type == 'manualTask'
           				||target.node_type == 'scriptTask'
           				||target.node_type == 'serviceTask'
           				||target.node_type == 'mailTask'
           				||target.node_type == 'receiveTask'
           				||target.node_type == 'businessRuleTask'
           				||target.node_type == 'callActivity'
           				
           				||target.node_type == 'endEvent'
           				||target.node_type == 'errorEndEvent'
           				||target.node_type == 'terminateEndEvent'
           				||target.node_type == 'cancelEndEvent'
           				
           				|| target.node_type == 'startEvent'
           				|| target.node_type == 'timerStartEvent'
           				|| target.node_type == 'messageStartEvent'
           				|| target.node_type == 'errorStartEvent'
           				|| target.node_type == 'signalStartEvent'){
           				graph.graphHandler.setRemoveCellsFromParent(false);  
           				return false;
           			}
           		}
           		//1.9如果当前节点
           		graph.graphHandler.setRemoveCellsFromParent(true);  
        		return true;
           	}
        	//////////////////自定义验证拖拽时候更新目标为父节点结束///////////////////
        	///////////////////以下是判断泳道池更新/////////////////////////////
            if(this.isSplitEnabled() && this.isSplitTarget(target, cells, evt)){  
                return true;  
            }  
            var model = this.getModel();  
            var lane = false;  
            var pool = false;  
            var cell = false;  
            // 检查元素是否被选择  
            for (var i = 0; i < cells.length; i++){  
                var tmp = model.getParent(cells[i]);  
                lanelane = lane || this.isPool(tmp);  
                poolpool = pool || this.isPool(cells[i]);  
                cellcell = cell || !(lane || pool);  
            }  
            return !pool && cell != lane && ((lane && this.isPool(target)) || (cell && this.isPool(model.getParent(target))));  
        };  
        //添加新的方法识别泳道 
        graph.isPool = function(cell){  
            var model = this.getModel();  
            var parent = model.getParent(cell);  
            return parent != null && model.getParent(parent) == model.getRoot();  
        };  
        //删除元素时清除泳道池  
        graph.model.getStyle = function(cell){  
            var style = mxGraphModel.prototype.getStyle.apply(this, arguments);  
            if (graph.isCellCollapsed(cell)){  
                if (style != null){  
                    style += ';';  
                }else{  
                    style = '';  
                }  
                style += 'horizontal=1;align=left;spacingLeft=14;';  
            }  
            return style;  
        };  
        // 收缩窗体时保持宽度  
        var foldingHandler = function(sender, evt){  
            var cells = evt.getProperty('cells');  
            for (var i = 0; i < cells.length; i++){  
                var geo = graph.model.getGeometry(cells[i]);  
                if (geo.alternateBounds != null){  
                    geogeo.width = geo.alternateBounds.width;  
                }  
            }  
        };  
        graph.addListener(mxEvent.FOLD_CELLS, foldingHandler);  
    }  
    // 父元素和同级元素间跳转大小  
    var swimlane = new mxSwimlaneManager(graph);  
    // 创建一个堆栈布局  
    var layout = new mxStackLayout(graph, false); 
    // 确保所有的子元素都适应父泳道  
    layout.resizeParent = true;  
    // 子元素适用父元素的变化  
    layout.fill = true;  
    // 仅仅更新泳道的大小  
    layout.isVertexIgnored = function(vertex){ 
    	if(typeof(vertex) != "undefined" && null != vertex && vertex.node_type == 'pool'){
    		return graph.isSwimlane(vertex);  
    	}else{
    		return !graph.isSwimlane(vertex);  
    	}
    }   
    // 保持泳道池与泳道的层叠关系  
    var layoutMgr = new mxLayoutManager(graph);  
    layoutMgr.getLayout = function(cell){  
        if (!model.isEdge(cell) && graph.getModel().getChildCount(cell) > 0 && (model.getParent(cell) == model.getRoot() || graph.isPool(cell))){  
            layout.fill = graph.isPool(cell);  
            return layout;  
        }  
        return null;  
    };
}
//////////////////////泳道图结束////////////////////////////