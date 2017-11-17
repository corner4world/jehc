package jehc.lcmodules.mxgraph.mxUtils;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;
/**
 * 人工任务
 * @author邓纯杰
 *
 */
public class MxUserTask {
	/**
	 * 任务节点操作
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String userTask(Element root,List mxCellList,Element mxCell){
		String task_node = "";
		/////////////基本属性开始/////////////
 		String name = mxCell.attributeValue("value");
 		String nodeID = mxCell.attributeValue("nodeID");
 		/////////////基本属性结束/////////////
 		
 		/////////////主要配置开始/////////////
 		String assignee = mxCell.attributeValue("assignee");
 		String candidateUsers = mxCell.attributeValue("candidateUsers");
 		String candidateGroups = mxCell.attributeValue("candidateGroups");
 		String Expression = mxCell.attributeValue("Expression");
 		String formKey = mxCell.attributeValue("formKey");
 		String dueDate = mxCell.attributeValue("dueDate"); 
 		String priority = mxCell.attributeValue("priority");
 		String asynchronous = mxCell.attributeValue("asynchronous");
 		String isForCompensation =  mxCell.attributeValue("isForCompensation");//是否补偿边界
 		/////////////主要配置结束/////////////
 		
 		//节点中其他基本属性
 		//获取mxCell节点下的mxGeometry节点
        Element mxGeometry = mxCell.element("mxGeometry"); 
        String x = mxGeometry.attributeValue("x");
        String y = mxGeometry.attributeValue("y");
        if(null == y || "".equals(y)){
        	y = "0";
        }
        if(null == x || "".equals(x)){
        	x = "0";
        }
        
        if(null != assignee && !"".equals(assignee)){
        	assignee = " activiti:assignee='"+assignee+"'";
        }else{
        	assignee=" ";
        }
        if(null != candidateUsers && !"".equals(candidateUsers)){
        	candidateUsers = " activiti:candidateUsers='"+candidateUsers+"'";
        }else{
        	candidateUsers=" ";
        }
        if(null != candidateGroups && !"".equals(candidateGroups)){
        	candidateGroups = " activiti:candidateGroups='"+candidateGroups+"'";
        }else{
        	candidateGroups=" ";
        }
        if(null != Expression && !"".equals(Expression)){
        	Expression = " aactiviti:skipExpression='"+Expression+"'";
        }else{
        	Expression=" ";
        }
        if(null != dueDate && !"".equals(dueDate)){
        	dueDate = " activiti:dueDate='"+dueDate+"'";
        }else{
        	dueDate="";
        }
        if(null != formKey && !"".equals(formKey)){
        	formKey = " activiti:formKey='"+formKey+"'";
        }else{
        	formKey=" ";
        }
        if(null != priority && !"".equals(priority)){
        	priority = " activiti:priority='"+priority+"'";
        }else{
        	priority=" ";
        }
        if(null != asynchronous && !"".equals(asynchronous) && "0".equals(asynchronous)){
        	asynchronous = " activiti:async='true'";
        }else{
        	asynchronous=" ";
        }
        if(null != isForCompensation && !"".equals(isForCompensation) && "1".equals(isForCompensation)){
        	isForCompensation = " isForCompensation='true'";
        }else{
        	isForCompensation=" ";
        }
        
		//开区间
        task_node+="<userTask id='"+nodeID+"' name='"+name+"' "+asynchronous+assignee+candidateUsers+candidateGroups+Expression+dueDate+formKey+priority+isForCompensation+">";
        
        //2备注配置开始
        task_node+=MxUtils.documentation(mxCell);
        //2备注配置结束
        
        //****开始区间与闭区间属性 开始****//
        task_node += "<extensionElements>";
        //1监听的类开始
        task_node+=MxUtils.eventListenerNode(mxCell);
        //1监听器配置结束
        
        //3表单配置开始
        task_node+=MxUtils.form(mxCell);
        //3表单配置结束
        task_node += "</extensionElements>";
        //****开始区间与闭区间属性 结束****//
        
		//闭区间
		task_node += "</userTask>";
		
		//4连线配置开始
        task_node+=MxUtils.sequenceFlow(root, mxCellList, mxCell);
        //4连线配置结束
        /////////////////////判断其下面是否拥有（时间边界，消息边界，补偿边界，信号边界）等节点
        Map<String,Object> map = MxUtils.resultTaskChildBpmn(root, mxCellList, mxCell,1);
        if(null != map && "".equals(map) && !map.isEmpty()){
        	 task_node += map.get("bpmnxml");
        }
		return task_node;
	}
	
	/**
	 * 人工任务节点bpmndi
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String userTaskBpmndi(Element root,List mxCellList,Element mxCell){
		String nodeID = mxCell.attributeValue("nodeID");
		String id = mxCell.attributeValue("id");
		Map<String, Object> xyMap = MxUtils.resultBoundsXY(mxCellList, mxCell);
        String x = xyMap.get("x").toString();
        String y = xyMap.get("y").toString();
        String width = xyMap.get("width").toString();
        String height = xyMap.get("height").toString();
		String bpmndi="";
		bpmndi += "<bpmndi:BPMNShape bpmnElement='"+nodeID+"' id='BPMNShape_"+nodeID+"'>";
        bpmndi += "<omgdc:Bounds height='"+height+"' width='"+width+"' x='"+x+"' y='"+y+"'></omgdc:Bounds>";
        bpmndi += "</bpmndi:BPMNShape>";
        //再次进行循环 目的获取连接线
		for(int j = 0; j < mxCellList.size(); j++ ){
			//mxCell节点
            Element mxCell_agin = (Element) mxCellList.get(j); 
            //该mxCell节点为连线节点情况
            if(null != mxCell_agin.attributeValue("edge") && !"".equals(mxCell_agin.attributeValue("edge"))){
            	//如果第一层循环中的ID等于第二层循环中的source则说明开始节点有指向其他节点连线
            	String source = mxCell_agin.attributeValue("source");
            	if(source.equals(id)){
            		//此时需要取出target即目标节点ID的属性value
            		List target_target_list = root.selectNodes("/root/mxCell[@id='"+mxCell_agin.attributeValue("target")+"']");
        			if(!target_target_list.isEmpty()){
        				//任务节点可以连接多个节点即一个任务节点可以有多条连接线
        				for(int l = 0; l < target_target_list.size(); l++){
        					Element mxCell_target = (Element)target_target_list.get(l);
            				if("startEvent".equals(mxCell_target.attributeValue("node_type"))){
            					//提示
            					System.out.println("任务节点连线不能指向开始节点");
            					//该地方需要终止
            				}else{
            					bpmndi += "<bpmndi:BPMNEdge bpmnElement='"+mxCell_agin.attributeValue("nodeID")+"' id='BPMNEdge_"+mxCell_agin.attributeValue("nodeID")+"'>";
            					bpmndi += MxUtils.resultChildEdgeXy(mxCellList,mxCell_agin);
            					bpmndi += "</bpmndi:BPMNEdge>";
            				}
        				}
        			}
            	}
            }
		}
		/////////////////////判断其下面是否拥有（时间边界，消息边界，补偿边界，信号边界）等节点
        Map<String,Object> map = MxUtils.resultTaskChildBpmn(root, mxCellList, mxCell,1);
        if(null != map && "".equals(map) && !map.isEmpty()){
        	bpmndi += map.get("bpmndi");
        }
		return bpmndi;
	}
}
