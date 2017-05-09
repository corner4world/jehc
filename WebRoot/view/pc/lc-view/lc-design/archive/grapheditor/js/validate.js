//////////////验证////////////////
function validate(graph, history){
	cell = graph.getSelectionCell();
	if(cell != null){
		if(!cell.edge){
			//非连线节点
			/**
			var cxml = new mxCodec(mxUtils.createXmlDocument());   
           	var cnode = cxml.encode(graph.getModel());  
           	var cxml = mxUtils.getPrettyXml(cnode);
           	var cxml_num=(cxml.split('node_type="startEvent"')).length-1;
           	if(cxml_num>2){
           		msgTishi("一个流程中只能存在一个开始节点!");
              	history.undo();
              	return;
           	}
           	**/
		}else{
			//连线事件验证情况
			//if(null == cell.source || "" == cell.source){
		 		//Ext.MessageBox.alert("提示","该连线没有指定源节点!");
		 		//return;
		 	//}else if(null == cell.target || "" == cell.target){
		 		//Ext.MessageBox.alert("提示","该连线没有指定目标节点!");
		 		//return;
		 	//}
		 	if(null != cell.source && "" != cell.source && null != cell.target && "" != cell.target){
			    var childsCellEdgs=cell.source.edges;
			    //开始节点到目标节点的数目
			    var start_to_target_line_number=0;
		        for(var i=0;i<childsCellEdgs.length;i++){
		           validateEdgeAttribute(graph, history,childsCellEdgs[i]);
		           if(childsCellEdgs[i].source.id==cell.source.id){
		           	  start_to_target_line_number = start_to_target_line_number+1;
		              //连线的上一个节点类型
		              var node_pre_type = childsCellEdgs[i].source.node_type;
		              //连线的下一个节点类型
		              var node_next_type = childsCellEdgs[i].target.node_type;
		              /**
		              if(node_next_type == 'startEvent'){
		              	msgTishi("开始节点不能被指向!");
		              	history.undo();
		              	return;
		              }
		              if(node_pre_type == 'startEvent' && node_next_type == 'endEvent'){
		              	msgTishi("开始节点不能指向结束节点!");
		              	history.undo();
		              	return;
		              }
		              if(node_pre_type == 'startEvent' && start_to_target_line_number > 1){
		              	msgTishi("开始节点不能指向多个节点!");
		              	history.undo();
		              	return;
		              }
		              /**
		              if(node_pre_type == 'endEvent' && start_to_target_line_number > 1){
		              	msgTishi("结束节点不能指向其他节点!");
		              	history.undo();
		              	return;
		              }
		              **/
		              //1结束节点不能指向其他节点
		              if(node_pre_type == 'endEvent' && start_to_target_line_number > 0){
		              	history.undo();
		              	return;
		              }
		              //2开始节点不能指向启动消息，启动信号，启动时间,错误启动
		              if(node_pre_type == 'startEvent' && (node_next_type == 'timerStartEvent' || node_next_type == 'messageStartEvent' || node_next_type == 'errorStartEvent' || node_next_type == 'signalStartEvent')){
		              	history.undo();
		              	return;
		              }
		           }
		       }
		    }
	     }
	}
}

//验证并设置连线属性
function validateEdgeAttribute(graph_refresh, history,edge){
	var graph = new mxGraph();
    graph.getModel().beginUpdate();
    var nums = new Date().getTime();
	try{
		if(null != edge && "" != edge && (null == edge.nodeID || '' == edge.nodeID)){
			edge.nodeID ='连线编号'+nums;
		}
		if(null != edge && "" != edge && (null == edge.value || '' == edge.value)){
			edge.value = '名称';
		}
		var sourceRoot = resultRootCell(graph,edge.source);
	    var targetRoot = resultRootCell(graph,edge.target);
		validateSourceRootAndTargetRoot(graph_refresh,sourceRoot,targetRoot,edge)
	}finally{
		graph.getModel().endUpdate();
		graph_refresh.refresh();
	}
}
//从菜单栏目拖拽元素的时候验证
function validateMoveCell(graph, history,cell){
	/**
	if(typeof(cell) != 'undefined' && !cell.edge){
		//非连线节点
		var cxml = new mxCodec(mxUtils.createXmlDocument());   
	   	var cnode = cxml.encode(graph.getModel());  
	   	var cxml = mxUtils.getPrettyXml(cnode);
	   	var cxml_num=(cxml.split('node_type="startEvent"')).length-1;
	   	if(cxml_num>2){
	   		msgTishi("一个流程中只能存在一个开始节点!");
	      	history.undo();
	      	return;
	   	}
	}
	**/
}

//获取所有连线
function resultAllEdge(graph){
	var parent = graph.getDefaultParent();
	var cells = resultAllCellList(graph,parent);
	var arrEdge = [];
　　 for(var i = 0; i < cells.length; i++) {
　　　　var cell = cells[i];
　　　　if(cell.isEdge()) {
　　　　　　arrEdge.push(cell)
　　　　}
　　 }
	return arrEdge;
}
//验证所有节点拖拽时候 连线想消息传递转换验证
function validateDragsCellFlowToMessage(graph){
	var edges = resultAllEdge(graph);
	for(var i = 0; i < edges.length; i++){
		var edge = edges[i];
		var sourceRoot = resultRootCell(graph,edge.source);
	    var targetRoot = resultRootCell(graph,edge.target);
		validateSourceRootAndTargetRoot(graph,sourceRoot,targetRoot,edge);
	}
}
///////////////////////供连线由flow转成message判断使用开始///////////////////
//根据节点获取该节点的根节点对象（parent=1除外）
function resultRootCell(graph,cell){
	if(null != cell){
		if(null != cell.parent && cell.parent.id == '1'){
			return cell;
		}else{
			return resultRootCell(graph,cell.parent);
		}
	}else{
		return cell;
	}
}

//获取所有节点
function resultAllCellList(graph,cell){
	var children = cell.children;
	var cells =[];
	if(typeof(children) != 'undefined' && null != children){
		for(var i = 0; i < children.length; i++) {
			var mcell = children[i];
			cells.push(mcell);
			if(typeof(mcell.children) != 'undefined' && null != mcell.children){
				//递归调用
				var childCell = resultAllCellList(graph,mcell);
				for(var l = 0; l < childCell.length; l++){
					cells.push(childCell[l]);
				}
			}
		}
	}
	return cells;
}

//判断源节点的根节点与目标节点的根节点
function validateSourceRootAndTargetRoot(graph_refresh,sourceRoot,targetRoot,edge){
	var graph = new mxGraph();
    graph.getModel().beginUpdate();
	try{
		if(null != edge && "" != edge){
			//如果起点在非泳道池中 终点在泳道池中（并且终点所在的泳道池中不能为第一个泳道池）
			if(sourceRoot.node_type != 'pool' && targetRoot.node_type == 'pool'&& !cellIsInFristPool(graph_refresh,targetRoot)){
				edge.isflow ='2';
				edge.setStyle('dashed=1;startArrow=oval;endArrow=block;sourcePerimeterSpacing=4;startFill=0;endFill=0;fontSize=8;fontColor=#000');
			}else if(sourceRoot.node_type == 'pool' && !cellIsInFristPool(graph_refresh,sourceRoot) && targetRoot.node_type != 'pool'){
				//如果起点在泳道池中（并且不在第一个泳道池中） 终点在非泳道池中
				edge.isflow ='2';
				edge.setStyle('dashed=1;startArrow=oval;endArrow=block;sourcePerimeterSpacing=4;startFill=0;endFill=0;fontSize=8;fontColor=#000');
			}else if(sourceRoot.node_type == 'pool' && sourceRoot.id != targetRoot.id && targetRoot.node_type == 'pool'){
				//如果两个节点都在泳道池中（两个节点在不同泳道池中则满足MessageFlow）
				edge.isflow ='2';
				edge.setStyle('dashed=1;startArrow=oval;endArrow=block;sourcePerimeterSpacing=4;startFill=0;endFill=0;fontSize=8;fontColor=#000');
			}else{
				edge.isflow ='1';
				edge.setStyle('dashed=1;');
			}
		}
	}finally{
		graph.getModel().endUpdate();
		graph_refresh.refresh();
	}
}

//判断节点是否在第一个泳道中
function cellIsInFristPool(graph,cell){
	var fristPool = resultFristPool(graph);
	if(null != fristPool && cell.id == fristPool.id){
		return true;
	}else{
		return false;
	}
}

//获取第一个泳道
function resultFristPool(graph){
	var pool = [];
	var poolId = [];
	var cellList =resultAllCellList(graph,graph.getDefaultParent());
	//1筛选出所有泳道
	for(var i = 0; i < cellList.length; i++) {
		var mcell = cellList[i];
		if(mcell.node_type == 'pool'){
			pool.push(mcell);
		}
	}
	//2将所有泳道排序
	if(null != pool){
		for(var i = 0; i < pool.length; i++){
			poolId.push(pool[i].id);
		}
		poolId.sort(sortNumber)
		//3获取第一个泳道
		for(var i = 0; i < cellList.length; i++) {
			var mcell = cellList[i];
			if(mcell.id == poolId[0]){
				return mcell;
			}
		}
	}
	return null;
}
//排序调用
function sortNumber(a,b){
	return a - b
}
///////////////////////供连线由flow转成message判断使用结束///////////////////


/////////获取所有子节点/////////
function resultAllCELL(graph){
   var parent = graph.getDefaultParent();
　　 var parentChildren = parent.children;
　　 var arrVertex = [];//节点
   //获取所有信息
　　 for(var i = 0; i < parentChildren.length; i++) {
　　　　var child = parentChildren[i];
　　　　if(!child.isVisible()) {
　　　　　　continue;
　　　　}
　　　　//区分连接线、节点
　　　　if(child.isEdge()) {
　　　　　　var obj = new Object();
　　　　　　obj.ID = child.id;
　　　　　　obj.SourceID = child.source.id;
　　　　　　obj.TargetID = child.target.id;
　　　　　　arrEdge.push(obj)
　　　　} else if (child.isVertex()) {
　　　　　　var obj = new Object();
　　　　　　obj.ID = child.id;
　　　　　　obj.Name = child.value;
　　　　　　obj.LeftTopX = child.geometry.x;
　　　　　　obj.LeftTopY = child.geometry.y;
　　　　　　arrVertex.push(obj);
　　　　}
　　 }
	return arrVertex;
}

//获取所有泳道
function resultPOOL(graph){
	var parent = graph.getDefaultParent();
　　	var parentChildren = parent.children;
	var arrVertex = [];
	if(null != parentChildren){
　　    for(var i = 0; i < parentChildren.length; i++) {
	　　　　var child = parentChildren[i];
	　　　　if(!child.isVisible()) {
	　　　　　　continue;
	　　　　}
	　　　　if(child.isVertex()) {
			if(child.node_type == 'pool'){
				arrVertex.push(child);
			}
	　　　　}
　　    }
	}
	return arrVertex;
}

//返回真正相对坐标
function resultXy(x,y,graph,cell){
	var xy = [];
	var cells = resultAllParent(graph,cell);
	var x_ = 0;
	var y_ = 0;
	for(var i = 0; i < cells.length; i++){
		var mxcell = cells[i];
		x_ = x_ + mxcell.geometry.x;
		y_ = y_ + mxcell.geometry.y;
	}
	x = x - x_;
	y = y - y_;
	xy.push(x);
	xy.push(y);
	return xy;
}
//返回指定节点上级，上上级....一直到根节点坐标
function resultAllParent(graph,cell){
	var cells = [];
	if(null != cell){
		if(null != cell.parent && cell.parent.id == '1'){
			cells.push(cell)
		}else{
			cells = resultAllParent(graph,cell.parent);
			cells.push(cell)
			return cells;
		}
	}
	return cells;
}
//验证泳道
function validatePOOL(graph){
	var pcell = resultPOOL(graph);
	if(null != pcell && pcell.length > 0){
		pcell = resultFristPool(graph);
		Ext.getCmp("processId").setReadOnly(true);
		Ext.getCmp("processName").setReadOnly(true);
		Ext.getCmp("mainNameSpace").setReadOnly(true);
		Ext.getCmp("candidateStarterUsers_Text").setReadOnly(true);
		Ext.getCmp("candidateStarterGroups_Text").setReadOnly(true);
		Ext.getCmp("processId").setValue(pcell.processId_);
		Ext.getCmp("processName").setValue(pcell.processName_);
		if(null == pcell.poolnameSpace){
			Ext.getCmp("mainNameSpace").setValue('http://www.activiti.org/test');
		}else{
			Ext.getCmp("mainNameSpace").setValue(pcell.poolnameSpace);
		}
		Ext.getCmp("candidateStarterUsers").setValue(pcell.candidateStarterUsers_);
		Ext.getCmp("candidateStarterGroups").setValue(pcell.candidateStarterGroups_);
		Ext.getCmp("candidateStarterUsers_Text").setValue(pcell.candidateStarterUsers_Text_);
		Ext.getCmp("candidateStarterGroups_Text").setValue(pcell.candidateStarterGroups_Text_);
	}else{
		Ext.getCmp("processId").setReadOnly(false);
		Ext.getCmp("processName").setReadOnly(false);
		Ext.getCmp("mainNameSpace").setReadOnly(false);
		Ext.getCmp("candidateStarterUsers_Text").setReadOnly(false);
		Ext.getCmp("candidateStarterGroups_Text").setReadOnly(false);
		//Ext.getCmp("processId").setValue("");
		//Ext.getCmp("processName").setValue("");
		Ext.getCmp("mainNameSpace").setValue("http://www.activiti.org/test");
		//Ext.getCmp("candidateStarterUsers").setValue("");
		//Ext.getCmp("candidateStarterGroups").setValue("");
		//Ext.getCmp("candidateStarterUsers_Text").setValue("");
		//Ext.getCmp("candidateStarterGroups_Text").setValue("");
	}
}

//点击泳道确定按钮验证
function clickPOOLSureBtn(graph,cell){
	var arrVertex = resultPOOL(graph);
	if(null != arrVertex && arrVertex.length > 0){
		if(arrVertex[0].id == cell.id){
			return true;
		}
	}
	return false;
}