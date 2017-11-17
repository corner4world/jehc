package jehc.lcmodules.mxgraph.mxUtils;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;

/**
 * 定时捕捉事件
 * @author邓纯杰
 *
 */
public class MxTimerCatchingEvent {
	/**
	 * 定时捕捉事件操作
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String timerCatchingEvent(Element root,List mxCellList,Element mxCell){
		String task_node = "";
		/////////////基本属性开始/////////////
 		String name = mxCell.attributeValue("value");
 		String nodeID = mxCell.attributeValue("nodeID");
 		/////////////基本属性结束/////////////
 		//节点中其他基本属性
 		String excluded = mxCell.attributeValue("excluded");
 		String ruleName = mxCell.attributeValue("ruleName");
 		String ruleVariablesInput = mxCell.attributeValue("ruleVariablesInput");
 		String resultVariables = mxCell.attributeValue("resultVariables");
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
        if(null != excluded && !"".equals(excluded) && "1".equals(excluded)){
        	excluded = " activiti:exclude='true'";
        }else{
        	excluded=" ";
        }
        if(null != ruleName && !"".equals(ruleName)){
        	ruleName = " activiti:rules='"+ruleName+"'";
        }else{
        	ruleName=" ";
        }
        if(null != ruleVariablesInput && !"".equals(ruleVariablesInput)){
        	ruleVariablesInput = " activiti:ruleVariablesInput='"+ruleVariablesInput+"'";
        }else{
        	ruleVariablesInput=" ";
        }
        if(null != resultVariables && !"".equals(resultVariables)){
        	ruleVariablesInput = " activiti:resultVariable='"+resultVariables+"'";
        }else{
        	ruleVariablesInput=" ";
        }
		//开区间
        task_node+="<intermediateCatchEvent id='"+nodeID+"' name='"+name+"' "+MxUtils.normal(mxCell)+excluded+ruleName+ruleVariablesInput+resultVariables+">";
        
        //****开始区间与闭区间属性 开始****//
        //2备注配置开始
        task_node+=MxUtils.documentation(mxCell);
        //2备注配置结束
        
        task_node += "<extensionElements>";
        //1监听的类开始
        task_node+=MxUtils.eventListenerNode(mxCell);
        //1监听器配置结束
        
        task_node += "</extensionElements>";
        //****开始区间与闭区间属性 结束****//
        
		//闭区间
		task_node += "</intermediateCatchEvent>";
		//3连线配置开始
        task_node+=MxUtils.sequenceFlow(root, mxCellList, mxCell);
        //3连线配置结束
		return task_node;
	}
	
	
	/**
	 * 定时捕捉事件bpmndi
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String timerCatchingEventBpmndi(Element root,List mxCellList,Element mxCell){
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
		return bpmndi;
	}
}
