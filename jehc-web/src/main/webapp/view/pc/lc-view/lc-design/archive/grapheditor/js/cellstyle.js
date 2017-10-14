function initCellsSytle(graph){ 
	//style的使用，插入背景图
	//开始节点
	//1-1.开始样式
	var start_style = new Object();
	start_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	start_style[mxConstants.STYLE_FONTSIZE] = '8';
	start_style[mxConstants.STYLE_FONTCOLOR] = '#f5f5f5';
	start_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	start_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/startEvent.png';
	graph.getStylesheet().putCellStyle('start_image4gray', start_style);
	
	//1-2.定时启动事件样式
	var timerStartEvent_style = new Object();
	timerStartEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	timerStartEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	timerStartEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	timerStartEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	timerStartEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/timerStartEvent.png';
	graph.getStylesheet().putCellStyle('timerStartEvent_image4gray', timerStartEvent_style);
	
	//1-3.消息开始事件样式
	var messageStartEvent_style = new Object();
	messageStartEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	messageStartEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	messageStartEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	messageStartEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	messageStartEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/messageStartEvent.png';
	graph.getStylesheet().putCellStyle('messageStartEvent_image4gray', messageStartEvent_style);
	
	//1-4.错误启动事件样式
	var errorStartEvent_style = new Object();
	errorStartEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	errorStartEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	errorStartEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	errorStartEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	errorStartEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/errorStartEvent.png';
	graph.getStylesheet().putCellStyle('errorStartEvent_image4gray', errorStartEvent_style);
	
	//1-5.信号启动事件样式
	var signalStartEvent_style = new Object();
	signalStartEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	signalStartEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	signalStartEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	signalStartEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	signalStartEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalStartEvent.png';
	graph.getStylesheet().putCellStyle('signalStartEvent_image4gray', signalStartEvent_style);
	
	//2结束样式
	//2-1.结束样式
	var end_style = new Object();
	end_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	end_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	end_style[mxConstants.STYLE_FONTSIZE] = '8';
	end_style[mxConstants.STYLE_FONTCOLOR] = '#f5f5f5';
	end_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/endEvent.png';
	graph.getStylesheet().putCellStyle('end_image4gray', end_style);
	//2-2.错误结束事件样式
	var errorEndEvent_style = new Object();
	errorEndEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	errorEndEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	errorEndEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	errorEndEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	errorEndEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/errorEndEvent.png';
	graph.getStylesheet().putCellStyle('errorEndEvent_image4gray', errorEndEvent_style);
	//2-3.终止结束事件样式
	var terminateEndEvent_style = new Object();
	terminateEndEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	terminateEndEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	terminateEndEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	terminateEndEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	terminateEndEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/terminateEndEvent.png';
	graph.getStylesheet().putCellStyle('terminateEndEvent_image4gray', terminateEndEvent_style);
	//2-4.取消结束事件样式
	var cancelEndEvent_style = new Object();
	cancelEndEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	cancelEndEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	cancelEndEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	cancelEndEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	cancelEndEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/cancelEndEvent.png';
	graph.getStylesheet().putCellStyle('cancelEndEvent_image4gray', cancelEndEvent_style);
	
	
	//3任务节点
	var task_style = new Object();
	task_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;  
	task_style[mxConstants.STYLE_GRADIENTCOLOR] = '#f5f5f5';
	task_style[mxConstants.STYLE_FONTSIZE] = '8';
	task_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;  
	task_style[mxConstants.STYLE_FONTCOLOR] = '#000';  
	graph.getStylesheet().putCellStyle('tak_image4gray', task_style);  
	
	//4边界事件
	//4-1时间边界
	var timerBoundaryEvent_style = new Object();
	timerBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	timerBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	timerBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	timerBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	timerBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/boundaryEventTime.png';
	graph.getStylesheet().putCellStyle('timerBoundaryEvent_image4gray', timerBoundaryEvent_style);
	
	//4-2错误边界
	var errorBoundaryEvent_style = new Object();
	errorBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	errorBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	errorBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	errorBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	errorBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/exclusiveGatewayError.png';
	graph.getStylesheet().putCellStyle('errorBoundaryEvent_image4gray', errorBoundaryEvent_style);
	
	//4-3消息边界
	var messageBoundaryEvent_style = new Object();
	messageBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	messageBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	messageBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	messageBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	messageBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/messageBoundaryEvent.png';
	graph.getStylesheet().putCellStyle('messageBoundaryEvent_image4gray', messageBoundaryEvent_style);
	
	//4-4取消边界
	var cancelBoundaryEvent_style = new Object();
	cancelBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	cancelBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	cancelBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	cancelBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	cancelBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/cancelBoundaryEvent.png';
	graph.getStylesheet().putCellStyle('cancelBoundaryEvent_image4gray', cancelBoundaryEvent_style);
	
	//4-5补偿边界
	var compensationBoundaryEvent_style = new Object();
	compensationBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	compensationBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	compensationBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	compensationBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	compensationBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/compensationBoundaryEvent.png';
	graph.getStylesheet().putCellStyle('compensationBoundaryEvent_image4gray', compensationBoundaryEvent_style);
	
	//4-6信号边界
	var signalBoundaryEvent_style = new Object();
	signalBoundaryEvent_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	signalBoundaryEvent_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	signalBoundaryEvent_style[mxConstants.STYLE_FONTSIZE] = '8';
	signalBoundaryEvent_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	signalBoundaryEvent_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/signalBoundaryEvent.png';
	graph.getStylesheet().putCellStyle('signalBoundaryEvent_image4gray', signalBoundaryEvent_style);
	
	//5泳道池
	var pool_style = new Object();
	pool_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_SWIMLANE;
	pool_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;  
	pool_style[mxConstants.STYLE_VERTICAL_ALIGN] = 'middle';
	pool_style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'white';
	pool_style[mxConstants.STYLE_FONTSIZE] = '10';
	pool_style[mxConstants.STYLE_HORIZONTAL] = false;
	pool_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	pool_style[mxConstants.STYLE_STROKECOLOR] = '#000';//边框颜色
	pool_style[mxConstants.STYLE_STROKEWIDTH] = '0';//边框宽度
	delete pool_style[mxConstants.STYLE_FILLCOLOR];
	graph.getStylesheet().putCellStyle('pool_style', pool_style);
	
	//6其他样式
	var other_style = new Object();
	other_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
	other_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;  
	other_style[mxConstants.STYLE_VERTICAL_ALIGN] = 'left';
	other_style[mxConstants.STYLE_GRADIENTCOLOR] = '#fffef9';
	other_style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'white';
	other_style[mxConstants.STYLE_FONTSIZE] = '10';
	other_style[mxConstants.STYLE_HORIZONTAL] = false;
	other_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	other_style[mxConstants.STYLE_STROKECOLOR] = '#000';//边框颜色
	other_style[mxConstants.STYLE_STROKEWIDTH] = '0';//边框宽度
	graph.getStylesheet().putCellStyle('other_style', other_style);
	
	//7排他网关
	var exclusiveGateway_style = new Object();
	exclusiveGateway_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	exclusiveGateway_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	exclusiveGateway_style[mxConstants.STYLE_FONTSIZE] = '8';
	exclusiveGateway_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	exclusiveGateway_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/exclusiveGateway.png';
	graph.getStylesheet().putCellStyle('exclusiveGateway_image4gray', exclusiveGateway_style);
	
	//8并行网关
	var parallelGateway_style = new Object();
	parallelGateway_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	parallelGateway_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	parallelGateway_style[mxConstants.STYLE_FONTSIZE] = '8';
	parallelGateway_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	parallelGateway_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/parallelGateway.png';
	graph.getStylesheet().putCellStyle('parallelGateway_image4gray', parallelGateway_style);
	
	//9包括网关
	var inclusiveGateway_style = new Object();
	inclusiveGateway_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	inclusiveGateway_style[mxConstants.STYLE_FONTSIZE] = '8';
	inclusiveGateway_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	inclusiveGateway_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	inclusiveGateway_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/inclusiveGateway.png';
	graph.getStylesheet().putCellStyle('inclusiveGateway_image4gray', inclusiveGateway_style);
	
	//10事件网关
	var eventGateway_style = new Object();
	eventGateway_style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
	eventGateway_style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
	eventGateway_style[mxConstants.STYLE_FONTSIZE] = '8';
	eventGateway_style[mxConstants.STYLE_FONTCOLOR] = '#000';
	eventGateway_style[mxConstants.STYLE_IMAGE] = '../view/pc/lc-view/lc-design/archive/grapheditor/images/activities/48/eventGateway.png';
	graph.getStylesheet().putCellStyle('eventGateway_image4gray', eventGateway_style);
}