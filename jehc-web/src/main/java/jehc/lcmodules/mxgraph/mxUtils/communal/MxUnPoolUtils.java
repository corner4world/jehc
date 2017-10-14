package jehc.lcmodules.mxgraph.mxUtils.communal;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import jehc.lcmodules.mxgraph.MxGraphModel;
import jehc.lcmodules.mxgraph.MxGraphToBPMN;

/**
 * 不存在泳道情况
 * @author 邓纯杰
 *
 */
public class MxUnPoolUtils {
	public static String bpmnxml(MxGraphModel mxGraphModel,List<Element> mxCellList,Element root){
		StringBuffer bpmnxml = new StringBuffer();
		Map<String,Object> cellChildMap = MxUtils.resultcellbpmnxml(mxCellList, root, MxUtils.resultUnPoolLevelFristList(mxCellList));
		bpmnxml.append("<process "+MxGraphToBPMN.basically(mxGraphModel)+" isExecutable='true'>");
		bpmnxml.append(cellChildMap.get("bpmnxml"));
        bpmnxml.append("</process>"); 
        //////////////////////////////////////////BPMNDI模块////////////////////////////////////////
        bpmnxml.append("<bpmndi:BPMNDiagram id='BPMNDiagram_myProcess'>");
        bpmnxml.append("<bpmndi:BPMNPlane bpmnElement='myProcess' id='BPMNPlane_myProcess'>");
        bpmnxml.append(cellChildMap.get("bpmndi"));
		bpmnxml.append("</bpmndi:BPMNPlane>");
		bpmnxml.append("</bpmndi:BPMNDiagram>");
		return bpmnxml.toString();
	}
}
