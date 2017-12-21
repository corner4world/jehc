package jehc.lcmodules.mxgraph.mxUtils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;

/**
 * 结束节点
 * @author邓纯杰
 *
 */
public class MxEndEvent {
	/**
	 * 结束节点
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String endEvent(Element root,List mxCellList,Element mxCell){
		String end_node = "";
		String end_sequenceFlow="";
		String id = mxCell.attributeValue("id");
		String nodeName = mxCell.attributeValue("value");
 		String nodeID = mxCell.attributeValue("nodeID");
		String asynchronous = mxCell.attributeValue("asynchronous");
		String documentation = mxCell.attributeValue("documentation");
 		String event_node_value = mxCell.attributeValue("event_node_value");/**监听事件属性**/
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
        if(null != asynchronous && !"".equals(asynchronous) && "0".equals(asynchronous)){
        	asynchronous = " activiti:async='true'";
        }else{
        	asynchronous="";
        }
        end_node += "<endEvent id='"+nodeID+"' name='"+nodeName+"' "+asynchronous+">";
		//****开始区间与闭区间属性****//
        if(null != documentation && !"".equals(documentation)){
        	end_node += "<documentation>"+documentation+"</documentation>";
        }
        end_node += "<extensionElements>";
        //监听器配置开始 总循环操作
        //1监听的类开始
        if(null != event_node_value && !"".equals(event_node_value)){
        	//主列表
        	String[] eventGrid = event_node_value.split("#");
        	for(int i = 0; i < eventGrid.length; i++){
        		String[] cell = eventGrid[i].split("@");
        		String excuteStr=null;
        		//javaclass类型
        		if(null != cell[1] && !"".equals(cell[1]) && "javaclass".equals(cell[1])){
        			excuteStr = "class='"+cell[0]+"'";
        		}
        		end_node += "<activiti:executionListener event='start' "+excuteStr+">";
                //1-1字段开始 子循环操作
        		if(null != cell[1] && !"".equals(cell[1]) && ("javaclass".equals(cell[1]) || "express".equals(cell[1]))){
        			//此时存在字段 字段位置在最后一个
        			String[] field = cell[3].split("$");
        			for(int j = 0; j < field.length; j++){
        				String[] fieldV = field[j].split("&");
        				end_node += "<activiti:field name='"+fieldV[0]+"'>";
        				end_node += "<activiti:string><![CDATA["+fieldV[1]+"]]></activiti:string>";
        				end_node += "</activiti:field>";
        			}
        		}
                //1-1字段结束
        		end_node += "</activiti:executionListener>";
                //1监听的类结束
        	}
        }        
        //监听器配置结束
        end_node += "</extensionElements>";
        
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
        				Element mxCell_target = (Element)target_target_list.get(0);
        				String skipexpression = mxCell_agin.attributeValue("skipexpression");
        				String condition =  mxCell_agin.attributeValue("condition");
        				if(null != skipexpression && !"".equals(skipexpression)){
        					skipexpression = " skipExpression='"+skipexpression+"'";
        				}else{
        					skipexpression = "";
        				}
        				if(!StringUtils.isEmpty(condition)){
        					condition = " <conditionExpression xsi:type='tFormalExpression'><![CDATA["+condition+"]]></conditionExpression> ";	
        				}else{
        					condition = "";
        				}
        				end_sequenceFlow += "<sequenceFlow id='"+mxCell_agin.attributeValue("value")+"' name='"+mxCell_agin.attributeValue("value")+"' sourceRef='"+nodeID+"' targetRef='"+mxCell_target.attributeValue("nodeID")+"' "+skipexpression+">"
        						+condition 
        						+"</sequenceFlow>";
        				/**
        				if("endEvent".equals(mxCell_target.attributeValue("node_type"))){
        					//提示
        					System.out.println("结束节点不能直接连接结束节点");
        					//该地方需要终止
        				}else{
        					
        				}
        				**/
        			}
            	}
            }
		}
		//闭区间
		end_node += "</endEvent>";
		end_node += end_sequenceFlow;
		return end_node;
	}
	
	/**
	 * 结束节点bpmndi
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String endBpmndi(Element root,List mxCellList,Element mxCell){
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
            		List target_source_list = root.selectNodes("/root/mxCell[@source='"+mxCell_agin.attributeValue("source")+"']");
            		if(target_source_list.size() > 1){
            			//提示
            			System.out.println("开始节点不能有多个出口");
            			//该地方需要终止
            		}else{
            			List target_target_list = root.selectNodes("/root/mxCell[@id='"+mxCell_agin.attributeValue("target")+"']");
            			if(!target_target_list.isEmpty()){
            				Element mxCell_target = (Element)target_target_list.get(0);
            				if("endEvent".equals(mxCell_target.attributeValue("node_type"))){
            					//提示
            					System.out.println("开始节点不能直接连接结束节点");
            					//该地方需要终止
            				}else{
            					bpmndi += "<bpmndi:BPMNEdge bpmnElement='"+mxCell_agin.attributeValue("nodeID")+"' id='BPMNEdge_"+mxCell_agin.attributeValue("nodeID")+"'>";
            					/**
            					<omgdi:waypoint x="85.0" y="57.0"></omgdi:waypoint>
            			        <omgdi:waypoint x="270.0" y="87.0"></omgdi:waypoint>
            			        <bpmndi:BPMNLabel>
            			          <omgdc:Bounds height="14.0" width="100.0" x="85.0" y="57.0"></omgdc:Bounds>
            			        </bpmndi:BPMNLabel>
            			        **/
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
