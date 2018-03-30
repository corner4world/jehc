package jehc.lcmodules.mxgraph.mxUtils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;
import jehc.xtmodules.xtcore.allutils.StringUtil;

/**
 * 开始节点
 * @author邓纯杰
 *
 */
public class MxStartEvent {
	/**
	 * 开始节点操作
	 * @param mxCellList
	 * @param mxCel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String startEvent(Element root,List mxCellList,Element mxCell){
		String start_node = "";
		String start_sequenceFlow="";
		String id = mxCell.attributeValue("id");
		String nodeName = mxCell.attributeValue("value");
 		String nodeID = mxCell.attributeValue("nodeID");
		String asynchronous = mxCell.attributeValue("asynchronous");
		//String exclusive = mxCell.attributeValue("exclusive");
		String documentation = mxCell.attributeValue("documentation");
		String initiator = mxCell.attributeValue("initiator");
 		String formKey = mxCell.attributeValue("formKey");
 		String event_node_value = mxCell.attributeValue("event_node_value");/**监听事件属性**/
 		String form_node_value = mxCell.attributeValue("form_node_value");/**表单属性**/
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
        if(null != initiator && !"".equals(initiator)){
        	initiator = " activiti:initiator='"+initiator+"'";
        }else{
        	initiator=" ";
        }
        if(null != formKey && !"".equals(formKey)){
        	formKey = " activiti:formKey='"+formKey+"'";
        }else{
        	formKey=" ";
        }
        start_node += "<startEvent id='"+nodeID+"' name='"+nodeName+"' "+asynchronous+" "+initiator+" "+formKey+">";
		//****开始区间与闭区间属性****//
        if(null != documentation && !"".equals(documentation)){
        	start_node += "<documentation>"+documentation+"</documentation>";
        }
        start_node += "<extensionElements>";
        //表单配置开始
        if(null != form_node_value && !"".equals(form_node_value)){
        	String[] form_node_valueGrid = form_node_value.split("#",-1);
        	for(int i = 0; i < form_node_valueGrid.length; i++){
        		if(!StringUtil.isEmpty(form_node_valueGrid[i])){
        			String[] cell = form_node_valueGrid[i].split("@",-1);
            		String cell0 = cell[0];
            		String cell1 = cell[1];
            		String cell2 = cell[2];
            		String cell3 = cell[3];
            		String cell4 = cell[4];
            		String cell5 = cell[5];
            		String cell6 = cell[6];
            		String cell7 = cell[7];
            		String cell8 = cell[8];
            		String cell9 = cell[9];
            		if(!StringUtil.isEmpty(cell0) && !"undefined".equals(cell0)){
            			cell0 = " id='"+cell0+"'";
            		}else{
            			cell0 =" ";
            		}
            		if(!StringUtil.isEmpty(cell1) && !"undefined".equals(cell1)){
            			cell1 = " name='"+cell1+"'";
            		}else{
            			cell1 = " ";
            		}
            		if(!StringUtil.isEmpty(cell2) && !"undefined".equals(cell2)){
            			cell2 = " type='"+cell2+"'";
            		}else{
            			cell2 = " ";
            		}
            		if(!StringUtil.isEmpty(cell3) && !"undefined".equals(cell3)){
            			cell3 = " expression='"+cell3+"'";
            		}else{
            			cell3 = " ";
            		}
            		if(!StringUtil.isEmpty(cell4) && !"undefined".equals(cell4)){
            			cell4 = " variable='"+cell4+"'";
            		}else{
            			cell4 = " ";
            		}
            		if(!StringUtil.isEmpty(cell5) && !"undefined".equals(cell5)){
            			cell5 = " default='"+cell5+"'";
            		}else{
            			cell5 = " ";
            		}
            		if(!StringUtil.isEmpty(cell6) && !"undefined".equals(cell6)){
            			cell6 = " datePattern='"+cell6+"'";
            		}else{
            			cell6 =  " ";
            		}
            		if(!StringUtil.isEmpty(cell7) && !"undefined".equals(cell7)){
            			cell7 = " readable='"+cell7+"'";
            		}else{
            			cell7 = " ";
            		}
            		if(!StringUtil.isEmpty(cell8) && !"undefined".equals(cell8)){
            			cell8 = " writable='"+cell8+"'";
            		}else{
            			cell8 = " ";
            		}
            		if(!StringUtil.isEmpty(cell9) && !"undefined".equals(cell9)){
            			cell9 = " required='"+cell9+"'";
            		}else{
            			cell9 = " ";
            		}
            		String cellStr = cell0+cell1+cell2+cell3+cell4+cell5+cell6+cell7+cell8+cell9;
//            		start_node += "<activiti:formProperty id='"+cell[0]+"' name='"+cell[1]+"' type='"+cell[2]+"' expression='"+cell[3]+"' variable='"+cell[4]+"' default='"+cell[5]+"' datePattern='"+cell[6]+"' readable='"+cell[7]+"' writable='"+cell[8]+"' required='"+cell[9]+"'>";
            		start_node += "<activiti:formProperty "+cellStr+">";
                    //字段
            		if(cell.length == 11 && !StringUtil.isEmpty(cell[10]) && !"undefined".equals(cell[10])){
            			String[] field = cell[10].split("$",-1);
                		for(int j = 0; j < field.length; j++){
                			String [] fieldValue = field[j].split("&",-1);
                			if(null != fieldValue && fieldValue.length == 2){
                				String fieldValue0 = fieldValue[0];
                    			String fieldValue1 = fieldValue[1];
                    			if(!StringUtil.isEmpty(fieldValue0) && !"undefined".equals(fieldValue0)){
                    				fieldValue0 = " id='"+fieldValue0+"'";
                    			}else{
                    				fieldValue0 = " ";
                    			}
                    			if(!StringUtil.isEmpty(fieldValue1) && !"undefined".equals(fieldValue1)){
                    				fieldValue1 = " name='"+fieldValue1+"'";
                    			}else{
                    				fieldValue1 = " ";
                    			}
                    			start_node += "<activiti:value "+fieldValue0+fieldValue1+"></activiti:value>";
                			}
                		}
            		}
                    start_node += "</activiti:formProperty>";
        		}
        	}
        }
        //表单配置结束
        
        //监听器配置开始 总循环操作
        //1监听的类开始
        if(null != event_node_value && !"".equals(event_node_value)){
        	//主列表
        	String[] eventGrid = event_node_value.split("#",-1);
        	for(int i = 0; i < eventGrid.length; i++){
        		if(!StringUtil.isEmpty(eventGrid[i])){
        			String[] cell = eventGrid[i].split("@",-1);
            		String excuteStr=null;
            		//javaclass类型
            		if(null != cell[1] && !"".equals(cell[1]) && "javaclass".equals(cell[1])){
            			excuteStr = "class='"+cell[0]+"'";
            		}
            		//express类型
            		if(null != cell[1] && !"".equals(cell[1]) && "express".equals(cell[1])){
            			excuteStr = " expression='Expression'";
            		}
            		start_node += "<activiti:executionListener event='"+cell[2]+"' "+excuteStr+">";
                    //1-1字段开始 子循环操作
            		if(null != cell[1] && !"".equals(cell[1]) && ("javaclass".equals(cell[1]) || "express".equals(cell[1]))){
            			//此时存在字段 字段位置在最后一个
            			String[] field = cell[3].split("$",-1);
            			for(int j = 0; j < field.length; j++){
            				if(!StringUtil.isEmpty(field[j])){
            					String[] fieldV = field[j].split("&",-1);
                				start_node += "<activiti:field name='"+fieldV[0]+"'>";
                                start_node += "<activiti:string><![CDATA["+fieldV[2]+"]]></activiti:string>";
                                start_node += "</activiti:field>";
            				}
            			}
            		}
                    //1-1字段结束
                    start_node += "</activiti:executionListener>";
                    //1监听的类结束
        		}
        	}
        }        
        //监听器配置结束
        start_node += "</extensionElements>";
        
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
        				if("endEvent".equals(mxCell_target.attributeValue("node_type"))){
        					//提示
        					System.out.println("开始节点不能直接连接结束节点");
        					//该地方需要终止
        				}else{
        					start_sequenceFlow += "<sequenceFlow id='"+mxCell_agin.attributeValue("nodeID")+"' name='"+mxCell_agin.attributeValue("value")+"' sourceRef='"+nodeID+"' targetRef='"+mxCell_target.attributeValue("nodeID")+"' "+skipexpression+">"
        							+ condition
        							+"</sequenceFlow>";
        					/**
        					 * 
        					//连线备注显示
        					String transition_g = CoordinateUtils.getCoordinate(mxCell_agin);
        					start_node += "<transition name='"+mxCell_agin.attributeValue("value")+"' to='"+mxCell_target.attributeValue("value")+"' g='"+transition_g+"'>";
        					start_node += MxUtils.description(mxCell_agin.attributeValue("description"));
        					//连线中间的其他属性如连线配置事件,连线定时器配置
        					String duedate = mxCell_agin.attributeValue("duedate");
        					String event_node_transition_value = mxCell_agin.attributeValue("event_node_transition_value");
        					start_node += MxUtils.transition_timer_event(duedate, event_node_transition_value);
        					start_node += "</transition>";
        					**/
        				}
        			}
            	}
            }
		}
		//闭区间
		start_node += "</startEvent>";
		start_node += start_sequenceFlow;
		return start_node;
	}
	
	/**
	 * 开始节点bpmndi
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String startBpmndi(Element root,List mxCellList,Element mxCell){
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
