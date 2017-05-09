package jehc.lcmodules.mxgraph.mxUtils.communal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jehc.lcmodules.mxgraph.MxGraphModel;
import jehc.lcmodules.mxgraph.MxGraphToBPMN;
import jehc.lcmodules.mxgraph.mxUtils.MxPool;

/**
 * 存在泳道情况
 * @author邓纯杰
 *
 */
public class MxPoolUtils {
	public static String bpmnxml(MxGraphModel mxGraphModel,List<Element> mxCellList,Element root){
		StringBuffer bpmnxml = new StringBuffer();
		StringBuffer bpmndi = new StringBuffer();
		try {
            if(!MxUtils.resultPoolList(mxCellList).isEmpty() && MxUtils.resultPoolList(mxCellList).size() > 0){
            	//////////////////////一.泳道存在的情况，泳道充当流程根节点///////////////
            	//1.1追加collaboration字符串
            	bpmnxml.append(MxUtils.resultP(mxCellList));
            	//1.2第一个泳道池充当根节点
            	Element poolFrist = MxUtils.resultFristPool(mxCellList);
            	List<Element> unPoolLevelFristList = MxUtils.resultUnPoolLevelFristList(mxCellList);//非泳道外的一级节点集合
            	bpmnxml.append("<process "+MxGraphToBPMN.basically(mxGraphModel)+" isExecutable='true'>");
            	//1.2.1获取第一个泳道池的下级节点集合即泳道Lane节点集合
            	List<Element> laneList = MxUtils.getChildElementList(mxCellList, poolFrist);
            	//1.2.2定义泳道lane下面的节点集合
            	List<Element> threeList = new ArrayList<Element>();
            	bpmnxml.append("<laneSet id='laneSet_"+poolFrist.attributeValue("nodeID")+"'>");
            	for(int i = 0; i < laneList.size(); i++){
            		Element lane = laneList.get(i);
            		bpmnxml.append("<lane id='"+lane.attributeValue("nodeID")+"'>");
            		List<Element> cellLaneChildList = MxUtils.getChildElementList(mxCellList, lane);
            		for(int j = 0; j < cellLaneChildList.size(); j++){
            			Element cellLaneChild = cellLaneChildList.get(j);
            			bpmnxml.append("<flowNodeRef>"+cellLaneChild.attributeValue("nodeID")+"</flowNodeRef>");
            			threeList.add(cellLaneChild);
            		}
            		bpmnxml.append("</lane>");
            	}
            	bpmnxml.append("</laneSet>");
            	//1.3处理非泳道的一级节点 将其放入第一泳道中
            	Map<String,Object> unPoolLevelFristMap = MxUtils.resultcellbpmnxml(mxCellList, root, unPoolLevelFristList);
            	bpmnxml.append(unPoolLevelFristMap.get("bpmnxml"));
            	//1.4对泳道lane下面的节点进行遍历
            	Map<String,Object> laneChildMap = MxUtils.resultcellbpmnxml(mxCellList, root, threeList);
            	bpmnxml.append(laneChildMap.get("bpmnxml"));
                bpmnxml.append("</process>"); 
                
                
                /////////////////////////////二.处理泳道非第一个集合////////////////////////
                StringBuffer unpoolfristLaneChildBpmndi = new StringBuffer();
                List<Element> poolList = MxUtils.resultUnFristPoolList(mxCellList);
                for(int i = 0; i < poolList.size(); i++){
                	Element pool = poolList.get(i);
                	bpmnxml.append("<process "+MxGraphToBPMN.basically(mxGraphModel,pool)+" isExecutable='true'>");
                	//2.1获取泳道池的下级节点集合即泳道Lane节点集合
                	List<Element> unpoolFristLaneList = MxUtils.getChildElementList(mxCellList, pool);
                	//2.2定义泳道lane下面的节点集合
                	List<Element> unpoolFristThreeList = new ArrayList<Element>();
                	bpmnxml.append("<laneSet id='laneSet_"+pool.attributeValue("nodeID")+"'>");
                	for(int j = 0; j < unpoolFristLaneList.size(); j++){
                		Element lane = unpoolFristLaneList.get(j);
                		bpmnxml.append("<lane id='"+lane.attributeValue("nodeID")+"'>");
                		List<Element> cellLaneChildList = MxUtils.getChildElementList(mxCellList, lane);
                		for(int l = 0; l < cellLaneChildList.size(); l++){
                			Element cellLaneChild = cellLaneChildList.get(l);
                			bpmnxml.append("<flowNodeRef>"+cellLaneChild.attributeValue("nodeID")+"</flowNodeRef>");
                			unpoolFristThreeList.add(cellLaneChild);
                		}
                		bpmnxml.append("</lane>");
                	}
                	bpmnxml.append("</laneSet>");
                	//2.3对泳道lane下面的节点进行遍历
                	Map<String,Object> unpoolfristLaneChildMap = MxUtils.resultcellbpmnxml(mxCellList, root, unpoolFristThreeList);
                	bpmnxml.append(unpoolfristLaneChildMap.get("bpmnxml"));
                    bpmnxml.append("</process>");
                    unpoolfristLaneChildBpmndi.append(unpoolfristLaneChildMap.get("bpmndi"));
                }
                //////////////////////////////////////////BPMNDI模块////////////////////////////////////////
                bpmnxml.append("<bpmndi:BPMNDiagram id='BPMNDiagram_Collaboration'>");
                bpmnxml.append("<bpmndi:BPMNPlane bpmnElement='Collaboration' id='BPMNPlane_Collaboration'>");
                //此处还需要对泳道池及泳道单独进行遍历
                //其他节点正常操作
                bpmnxml.append(MxPool.poolBpmndi(root, mxCellList, poolFrist));
                bpmnxml.append(unPoolLevelFristMap.get("bpmndi"));
                bpmnxml.append(MxUtils.resultLaneBpmndi(mxCellList));
                bpmnxml.append(laneChildMap.get("bpmndi"));
                bpmnxml.append(unpoolfristLaneChildBpmndi.toString());
        		bpmnxml.append("</bpmndi:BPMNPlane>");
        		bpmnxml.append("</bpmndi:BPMNDiagram>");
    		}else{
    			//没有泳道情况则调用正常解析节点
    			bpmnxml.append(MxUnPoolUtils.bpmnxml(mxGraphModel,mxCellList, root));
    		}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		bpmnxml.append(bpmndi.toString());
		return bpmnxml.toString();
	}
}
