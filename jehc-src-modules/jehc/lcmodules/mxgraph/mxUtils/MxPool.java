package jehc.lcmodules.mxgraph.mxUtils;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;

/**
 * 泳道池
 * @author邓纯杰
 *
 */
public class MxPool {
	/**
	 *  泳道池操作（泳道池没有连线）
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String pool(Element root,List mxCellList,Element mxCell){
		String pool_node = "";
 		String processId_ = mxCell.attributeValue("processId_");
 		String processName_ = mxCell.attributeValue("processName_");
 		String candidateStarterUsers_ = mxCell.attributeValue("candidateStarterUsers_");
 		String candidateStarterGroups_ = mxCell.attributeValue("candidateStarterGroups_");
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
        if(null != candidateStarterUsers_ && !"".equals(candidateStarterUsers_)){ 
        	candidateStarterUsers_ =" activiti:candidateStarterUsers='"+candidateStarterUsers_+"' ";
        }else{
        	candidateStarterUsers_ = "";
        }
        if(null != candidateStarterGroups_ && !"".equals(candidateStarterGroups_)){
        	candidateStarterGroups_ =" activiti:candidateStarterGroups='"+candidateStarterGroups_+"' ";
        }else{
        	candidateStarterGroups_ = "";
        }
		//开区间
        pool_node+="<process id='"+processId_+"' name='"+processName_+"' "+candidateStarterUsers_+candidateStarterGroups_+">";
        //****开始区间与闭区间属性 开始****//
        
        //2备注配置开始
        pool_node+=MxUtils.documentation(mxCell);
        //2备注配置结束
        
        pool_node += "<extensionElements>";
        //1监听的类开始
        pool_node+=MxUtils.eventListenerNode(mxCell);
        //1监听器配置结束
       
        pool_node += "</extensionElements>";
        //****开始区间与闭区间属性 结束****//
		//闭区间
        pool_node += "</process>";
		return pool_node;
	}
	
	/**
	 *  泳道bpmndi(泳道池没有连线)
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String poolBpmndi(Element root,List mxCellList,Element mxCell){
		String nodeID = mxCell.attributeValue("nodeID");
		Map<String, Object> xyMap = MxUtils.resultBoundsXY(mxCellList, mxCell);
        String x = xyMap.get("x").toString();
        String y = xyMap.get("y").toString();
        String width = xyMap.get("width").toString();
        String height = xyMap.get("height").toString();
		String bpmndi="";
		bpmndi += "<bpmndi:BPMNShape bpmnElement='"+nodeID+"' id='BPMNShape_"+nodeID+"'>";
        bpmndi += "<omgdc:Bounds height='"+height+"' width='"+width+"' x='"+x+"' y='"+y+"'></omgdc:Bounds>";
        bpmndi += "</bpmndi:BPMNShape>";
		return bpmndi;
	}
}
