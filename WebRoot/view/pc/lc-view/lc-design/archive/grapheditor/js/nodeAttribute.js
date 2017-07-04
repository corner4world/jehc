//自定义属性邓纯杰
//选择当前节点编辑属性
function setNodeAttribute(cell,graph_refresh){
	var node_type=cell.node_type;
	if(null != node_type && "" != node_type){
		if(node_type == 'startEvent'){
			//开始节点
			startNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'timerStartEvent'){
			//定时启动事件
			timerStartEventWin_(cell,graph_refresh);
		}else if(node_type == 'messageStartEvent'){
			//消息开始事件
			messageStartEventWin_(cell,graph_refresh);
		}else if(node_type == 'errorStartEvent'){
			//错误启动事件
			errorStartEventWin_(cell,graph_refresh);
		}else if(node_type == 'signalStartEvent'){
			//信号启动事件
			signalStartEventWin_(cell,graph_refresh);
		}else if(node_type == 'endEvent'){
			//结束节点
			endNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'errorEndEvent'){
			//错误结束事件
			errorEndEventWin_(cell,graph_refresh);
		}else if(node_type == 'terminateEndEvent'){
			//终止结束事件
			terminateEndEventWin_(cell,graph_refresh);
		}else if(node_type == 'cancelEndEvent'){
			//取消结束事件
			cancelEndEventWin_(cell,graph_refresh);
		}else if(node_type == 'userTask'){
			//用户任务节点
			userTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'manualTask'){
			//手动任务节点
			showManualTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'scriptTask'){
			//脚本任务节点
			showScriptTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'serviceTask'){
			//服务任务节点
			showServiceTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'mailTask'){
			//邮件任务节点
			showMailTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'receiveTask'){
			//接收任务节点
			showRceiveTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'businessRuleTask'){
			//业务规则
			showBusinessRuleTaskNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'callActivity'){
			//调用子流程
			showCallActivityNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'parallelGateway'){
			//分支节点
			showParallelGatewayNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'exclusiveGateway'){
			//同步节点
			showExclusiveGatewayNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'inclusiveGateway'){
			//包括网关
			inclusiveGatewayWin_(cell,graph_refresh);
		}else if(node_type == 'eventGateway'){
			//事件网关
			eventGatewayWin_(cell,graph_refresh);
		}else if(node_type == 'timerBoundaryEvent'){
			//时间边界节点
			showTimerBoundaryEventNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'errorBoundaryEvent'){
			//错误边界节点
			showErrorBoundaryEventNodeAttributeWin(cell,graph_refresh);
		}else if(node_type == 'messageBoundaryEvent'){
			//消息边界
			messageBoundaryEventWin_(cell,graph_refresh);
		}else if(node_type == 'cancelBoundaryEvent'){
			//取消边界
			cancelBoundaryEventWin_(cell,graph_refresh);
		}else if(node_type == 'compensationBoundaryEvent'){
			//补偿边界
			compensationBoundaryEventWin_(cell,graph_refresh);
		}else if(node_type == 'signalBoundaryEvent'){
			//信号边界
			signalBoundaryEventWin_(cell,graph_refresh);
		}else if(node_type == 'eventSubProcess'){
			//事件子流程
			eventSubProcessWin_(cell,graph_refresh);
		}else if(node_type == 'transactionProcess'){
			//事物流程
			transactionProcessWin_(cell,graph_refresh);
		}else if(node_type == 'subProcess'){
			//子流程
			subProcessWin_(cell,graph_refresh);
		}else if(node_type == 'pool'){
			//泳道
			poolWin_(cell,graph_refresh);
		}else if(node_type == 'lane'){
			//泳道池
			laneWin_(cell,graph_refresh);
		}else if(node_type == 'timerCatchingEvent'){
			//定时捕捉事件
			timerCatchingEventWin_(cell,graph_refresh);
		}else if(node_type == 'signalCatchingEvent'){
			//信号捕捉事件
			signalCatchingEventWin_(cell,graph_refresh);
		}else if(node_type == 'messageCatchingEvent'){
			//消息捕捉事件
			messageCatchingEventWin_(cell,graph_refresh);
		}else if(node_type == 'signalThrowingEvent'){
			//信号抛出事件
			signalThrowingEventWin_(cell,graph_refresh);
		}else if(node_type == 'compensationThrowingEvent'){
			//补偿抛出事件
			compensationThrowingEventWin_(cell,graph_refresh);
		}else if(node_type == 'noneThrowingEvent'){
			//非抛出事件
			noneThrowingEventWin_(cell,graph_refresh);
		}else if(node_type == 'transition'){
			//连线节点
			showTransitionNodeAttributeWin(cell,graph_refresh);
		}
	}
}
