package jehc.lcmodules.mxgraph.mxUtils.communal;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import jehc.lcmodules.mxgraph.MxGraphModel;
import jehc.lcmodules.mxgraph.mxUtils.MxBusinessRuleTask;
import jehc.lcmodules.mxgraph.mxUtils.MxCalcelBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxCallActivity;
import jehc.lcmodules.mxgraph.mxUtils.MxCancelEndEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxCompensationBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxCompensationThrowingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxEndEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxErrorBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxErrorEndEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxErrorStartEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxEventGateway;
import jehc.lcmodules.mxgraph.mxUtils.MxEventSubProcess;
import jehc.lcmodules.mxgraph.mxUtils.MxExclusiveGateway;
import jehc.lcmodules.mxgraph.mxUtils.MxInclusiveGateway;
import jehc.lcmodules.mxgraph.mxUtils.MxMailTask;
import jehc.lcmodules.mxgraph.mxUtils.MxManualTask;
import jehc.lcmodules.mxgraph.mxUtils.MxMessageBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxMessageCatchingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxMessageStartEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxNoneThrowingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxParallelGateway;
import jehc.lcmodules.mxgraph.mxUtils.MxReceiveTask;
import jehc.lcmodules.mxgraph.mxUtils.MxScriptTask;
import jehc.lcmodules.mxgraph.mxUtils.MxServiceTask;
import jehc.lcmodules.mxgraph.mxUtils.MxSignalBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxSignalCatchingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxSignalStartEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxSignalThrowingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxStartEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxSubProcess;
import jehc.lcmodules.mxgraph.mxUtils.MxTerminateEndEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxTimerBoundaryEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxTimerCatchingEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxTimerStartEvent;
import jehc.lcmodules.mxgraph.mxUtils.MxTransactionProcess;
import jehc.lcmodules.mxgraph.mxUtils.MxUserTask;
import jehc.xtmodules.xtcore.allutils.Dom4jUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
/**
 * 解析MxGraph设计器XML至BPMN文件
 * BPMN文件为Activiti5.18版本
 * @author 邓纯杰
 *
 */
public class MxUtils {
	
	/**
	 * 返回Mxgraph根节点下的所有节点
	 * @param mxgraphxml
	 * @param mxGraphModel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> resultMxgraphRootChild(String mxgraphxml,MxGraphModel mxGraphModel){
		try {
			//去除头部开始||去除mxgraph中mxcell的开头两个节点
			mxgraphxml = mxgraphxml.replace("<mxGraphModel>", "");
			mxgraphxml = mxgraphxml.replace("<mxCell id='0'/>", "");
			mxgraphxml = mxgraphxml.replace("<mxCell id='1' parent='0'/>", "");
			mxgraphxml = mxgraphxml.replace("</mxGraphModel>", "");
			//去除头部结束||...............................
	        InputSource in = new InputSource(new StringReader(mxgraphxml));     
	        in.setEncoding("UTF-8");     
	        SAXReader reader = new SAXReader();     
	        Document document;
			document = DocumentHelper.parseText(mxgraphxml);
			document = reader.read(in);  
            //获取根节点  
            Element root = document.getRootElement(); 
            validate(root);
            List<Element> mxCellList = root.elements("mxCell"); 
            return mxCellList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取Mxgraph根节点
	 * @param mxgraphxml
	 * @param mxGraphModel
	 * @return
	 */
	public static Element resultMxgraphRoot(String mxgraphxml,MxGraphModel mxGraphModel){
		try {
			//去除头部开始||去除mxgraph中mxcell的开头两个节点
			mxgraphxml = mxgraphxml.replace("<mxGraphModel>", "");
			mxgraphxml = mxgraphxml.replace("<mxCell id='0'/>", "");
			mxgraphxml = mxgraphxml.replace("<mxCell id='1' parent='0'/>", "");
			mxgraphxml = mxgraphxml.replace("</mxGraphModel>", "");
			//去除头部结束||...............................
	        InputSource in = new InputSource(new StringReader(mxgraphxml));     
	        in.setEncoding("UTF-8");     
	        SAXReader reader = new SAXReader();     
	        Document document;
			document = DocumentHelper.parseText(mxgraphxml);
			document = reader.read(in);  
            //获取根节点  
            Element root = document.getRootElement(); 
            return root;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 转换方法
	 * @param mxGraphXml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String mxgraphxml_to_bpmnxml(String mxgraphxml,MxGraphModel mxGraphModel){
		//定义bpmn标记
		StringBuffer bpmnxml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">");
		Element root = resultMxgraphRoot(mxgraphxml, mxGraphModel);
		List<Element> mxCellList = resultMxgraphRootChild(mxgraphxml, mxGraphModel);
		bpmnxml.append(MxPoolUtils.bpmnxml(mxGraphModel,mxCellList, root));
		bpmnxml.append("</definitions>");
        return bpmnxml.toString(); 
	}
	
	/**
	 * 根据节点集合遍历出每个节点的的bpmn和bpmndi
	 * @param mxCellList
	 * @param root
	 * @param cellList
	 * @return
	 */
	public static Map<String,Object> resultcellbpmnxml(List<Element> mxCellList,Element root,List<Element> cellList){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer bpmnxml = new StringBuffer();
		StringBuffer bpmndi = new StringBuffer();
		for(int i = 0; i < cellList.size(); i++){
    		Element mxCell = cellList.get(i);
        	//该mxCell节点为非连线节点情况
            if(null != mxCell.attributeValue("vertex") && !"".equals(mxCell.attributeValue("vertex"))){
            	//////////////0001:【开始节点】////////////
            	if(mxCell.attributeValue("node_type").equals("startEvent")){
            		bpmnxml.append(MxStartEvent.startEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxStartEvent.startBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0002:【定时启动事件】///////////
            	if(mxCell.attributeValue("node_type").equals("timerStartEvent")){
            		bpmnxml.append(MxTimerStartEvent.timerStartEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxTimerStartEvent.timerStartEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0003:【消息开始事件】///////////
            	if(mxCell.attributeValue("node_type").equals("messageStartEvent")){
            		bpmnxml.append(MxMessageStartEvent.messageStartEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxMessageStartEvent.messageStartEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0004:【错误开始事件】///////////
            	if(mxCell.attributeValue("node_type").equals("errorStartEvent")){
            		bpmnxml.append(MxErrorStartEvent.errorStartEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxErrorStartEvent.errorStartEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0005:【信号启动事件】///////////
            	if(mxCell.attributeValue("node_type").equals("signalStartEvent")){
            		bpmnxml.append(MxSignalStartEvent.signalStartEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxSignalStartEvent.signalStartEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0006:【结束节点】////////////
            	if(mxCell.attributeValue("node_type").equals("endEvent")){
            		bpmnxml.append(MxEndEvent.endEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxEndEvent.endBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0007:【错误结束事件】///////////
            	if(mxCell.attributeValue("node_type").equals("errorEndEvent")){
            		bpmnxml.append(MxErrorEndEvent.errorEndEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxErrorEndEvent.errorEndEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0008:【终止结束事件】///////////
            	if(mxCell.attributeValue("node_type").equals("terminateEndEvent")){
            		bpmnxml.append(MxTerminateEndEvent.terminateEndEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxTerminateEndEvent.terminateEndEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0009:【取消结束事件】///////////
            	if(mxCell.attributeValue("node_type").equals("cancelEndEvent")){
            		bpmnxml.append(MxCancelEndEvent.cancelEndEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxCancelEndEvent.cancelEndEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0010:【人工任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("userTask")){
            		bpmnxml.append(MxUserTask.userTask(root, mxCellList, mxCell));
            		bpmndi.append(MxUserTask.userTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0011:【手动任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("manualTask")){
            		bpmnxml.append(MxManualTask.manualTask(root, mxCellList, mxCell));
            		bpmndi.append(MxManualTask.manualTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0012:【脚本任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("scriptTask")){
            		bpmnxml.append(MxScriptTask.scriptTask(root, mxCellList, mxCell));
            		bpmndi.append(MxScriptTask.scriptTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0013:【服务任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("serviceTask")){
            		bpmnxml.append(MxServiceTask.serviceTask(root, mxCellList, mxCell));
            		bpmndi.append(MxServiceTask.serviceTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0014:【邮件任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("mailTask")){
            		bpmnxml.append(MxMailTask.mailTask(root, mxCellList, mxCell));
            		bpmndi.append(MxMailTask.mailTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0015:【接收任务节点】///////////
            	if(mxCell.attributeValue("node_type").equals("receiveTask")){
            		bpmnxml.append(MxReceiveTask.receiveTask(root, mxCellList, mxCell));
            		bpmndi.append(MxReceiveTask.receiveTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0016:【业务规则节点】///////////
            	if(mxCell.attributeValue("node_type").equals("businessRuleTask")){
            		bpmnxml.append(MxBusinessRuleTask.businessRuleTask(root, mxCellList, mxCell));
            		bpmndi.append(MxBusinessRuleTask.businessRuleTaskBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0017:【调用子流程节点】///////////
            	if(mxCell.attributeValue("node_type").equals("callActivity")){
            		bpmnxml.append(MxCallActivity.callActivity(root, mxCellList, mxCell));
            		bpmndi.append(MxCallActivity.callActivityBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0018:【并行网关】///////////
            	if(mxCell.attributeValue("node_type").equals("parallelGateway")){
            		bpmnxml.append(MxParallelGateway.parallelGateway(root, mxCellList, mxCell));
            		bpmndi.append(MxParallelGateway.parallelGatewayBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0019:【排他网关】///////////
            	if(mxCell.attributeValue("node_type").equals("exclusiveGateway")){
            		bpmnxml.append(MxExclusiveGateway.exclusiveGateway(root, mxCellList, mxCell));
            		bpmndi.append(MxExclusiveGateway.exclusiveGatewayBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0020:【包括网关】///////////
            	if(mxCell.attributeValue("node_type").equals("inclusiveGateway")){
            		bpmnxml.append(MxInclusiveGateway.inclusiveGateway(root, mxCellList, mxCell));
            		bpmndi.append(MxInclusiveGateway.inclusiveGatewayBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0021:【事件网关】///////////
            	if(mxCell.attributeValue("node_type").equals("eventGateway")){
            		bpmnxml.append(MxEventGateway.eventGateway(root, mxCellList, mxCell));
            		bpmndi.append(MxEventGateway.eventGatewayBpmndi(root, mxCellList, mxCell));
            	}
            	
            	//////////////0022:【事件子流程】///////////
            	if(mxCell.attributeValue("node_type").equals("eventSubProcess")){
            		bpmnxml.append(MxEventSubProcess.eventSubProcess(root, mxCellList, mxCell));
            		bpmndi.append(MxEventSubProcess.eventSubProcessBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0023:【事物流程】///////////
            	if(mxCell.attributeValue("node_type").equals("transactionProcess")){
            		bpmnxml.append(MxTransactionProcess.transactionProcess(root, mxCellList, mxCell));
            		bpmndi.append(MxTransactionProcess.transactionProcessBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0024:【子流程】///////////
            	if(mxCell.attributeValue("node_type").equals("subProcess")){
            		bpmnxml.append(MxSubProcess.subProcess(root, mxCellList, mxCell));
            		bpmndi.append(MxSubProcess.subProcessBpmndi(root, mxCellList, mxCell));
            	}
            	
            	//////////////0025:【泳道池（注：该泳道池单独处理其节点属性）】/////////////
            	//////////////0026:【泳道（注：该泳道单独处理其节点属性）】//////////////
            	
            	//////////////0027:【时间边界（注：该节点不在该地方处理）】///////////
            	//////////////0028:【错误边界（注：该节点不在该地方处理）】///////////
				//////////////0029:【消息边界（注：该节点不在该地方处理）】///////////
            	//////////////0030:【取消边界（注：该节点不在该地方处理）】///////////
			    //////////////0031:【补偿边界（注：该节点不在该地方处理）】///////////
				//////////////0032:【信号边界（注：该节点不在该地方处理）】///////////
            	
				//////////////0033:【定时捕捉事件】///////////
            	if(mxCell.attributeValue("node_type").equals("timerCatchingEvent")){
            		bpmnxml.append(MxTimerCatchingEvent.timerCatchingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxTimerCatchingEvent.timerCatchingEventBpmndi(root, mxCellList, mxCell));
            	}
				//////////////0034:【信号捕捉事件】///////////
            	if(mxCell.attributeValue("node_type").equals("signalCatchingEvent")){
            		bpmnxml.append(MxSignalCatchingEvent.signalCatchingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxSignalCatchingEvent.signalCatchingEventBpmndi(root, mxCellList, mxCell));
            	}
				//////////////0035:【消息捕捉事件】///////////
            	if(mxCell.attributeValue("node_type").equals("messageCatchingEvent")){
            		bpmnxml.append(MxMessageCatchingEvent.messageCatchingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxMessageCatchingEvent.messageCatchingEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0036:【信号抛出事件】///////////
            	if(mxCell.attributeValue("node_type").equals("signalThrowingEvent")){
            		bpmnxml.append(MxSignalThrowingEvent.signalThrowingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxSignalThrowingEvent.signalThrowingEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0037:【补偿抛出事件】///////////
            	if(mxCell.attributeValue("node_type").equals("compensationThrowingEvent")){
            		bpmnxml.append(MxCompensationThrowingEvent.compensationThrowingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxCompensationThrowingEvent.compensationThrowingEventBpmndi(root, mxCellList, mxCell));
            	}
            	//////////////0038:【非抛出事件】////////////
            	if(mxCell.attributeValue("node_type").equals("noneThrowingEvent")){
            		bpmnxml.append(MxNoneThrowingEvent.noneThrowingEvent(root, mxCellList, mxCell));
            		bpmndi.append(MxNoneThrowingEvent.noneThrowingEventBpmndi(root, mxCellList, mxCell));
            	}
            }
		}
		map.put("bpmnxml", bpmnxml.toString());
		map.put("bpmndi", bpmndi.toString());
		return map;
	}
	
	/**
	 * 返回子节点bpmn文件
	 * @param mxCellList
	 * @param mxCell
	 * @param flag 1表示（人工任务，手动任务，接收任务，业务规则）拥有的属性 2表示（脚本任务，服务任务，邮件任务，调用子流程）
	 * @return
	 */
	public static Map<String, Object> resultTaskChildBpmn(Element root,List<Element> mxCellList,Element mxCell,int flag){
		/////////////////////判断其下面是否拥有（时间边界，消息边界，补偿边界，信号边界）等节点
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer bpmnxml = new StringBuffer();
		StringBuffer bpmndi = new StringBuffer();
        List<Element> childList = MxUtils.getChildElementList(mxCellList, mxCell);
        for(int i = 0; i < childList.size(); i++){
        	Element child = childList.get(i);
        	//该mxCell节点为非连线节点情况
            if(null != mxCell.attributeValue("vertex") && !"".equals(mxCell.attributeValue("vertex"))){
	        	//////////////0027:【时间边界】///////////
	        	if(child.attributeValue("node_type").equals("boundaryEventTime")){
	            	bpmnxml.append(MxTimerBoundaryEvent.timerBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxTimerBoundaryEvent.timerBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        	if(flag == 2){
	        		//////////////0028:【错误边界】///////////
		        	if(child.attributeValue("node_type").equals("exclusiveGatewayError")){
		            	bpmnxml.append(MxErrorBoundaryEvent.errorBoundaryEvent(root, mxCellList, child));
		        		bpmndi.append(MxErrorBoundaryEvent.errorBoundaryEventBpmndi(root, mxCellList, child));
		        	}
	        	}
				//////////////0029:【消息边界】///////////
	        	if(child.attributeValue("node_type").equals("messageBoundaryEvent")){
	            	bpmnxml.append(MxMessageBoundaryEvent.messageBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxMessageBoundaryEvent.messageBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        	//////////////0030:【取消边界】///////////
	        	if(child.attributeValue("node_type").equals("cancelBoundaryEvent")){
	            	bpmnxml.append(MxCalcelBoundaryEvent.calcelBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxCalcelBoundaryEvent.calcelBoundaryEventBpmndi(root, mxCellList, child));
	        	}
			    //////////////0031:【补偿边界】///////////
	        	if(child.attributeValue("node_type").equals("compensationBoundaryEvent")){
	            	bpmnxml.append(MxCompensationBoundaryEvent.compensationBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxCompensationBoundaryEvent.compensationBoundaryEventBpmndi(root, mxCellList, child));
	        	}
				//////////////0032:【信号边界】///////////
	        	if(child.attributeValue("node_type").equals("signalBoundaryEvent")){
	            	bpmnxml.append(MxSignalBoundaryEvent.signalBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxSignalBoundaryEvent.signalBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        }
        }
        map.put("bpmnxml", bpmnxml.toString());
		map.put("bpmndi", bpmndi.toString());
		return map;
	}
	
	/**
	 * 子流程下级节点
	 * @param root
	 * @param mxCellList
	 * @param mxCell
	 * @param flag 1事件子流程 2事物子流程 3子流程
	 * @return
	 */
	public static Map<String, Object> resultSubProcessChildBpmn(Element root,List<Element> mxCellList,Element mxCell,int flag){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer bpmnxml = new StringBuffer();
		StringBuffer bpmndi = new StringBuffer();
        List<Element> childList = MxUtils.getChildElementList(mxCellList, mxCell);
        for(int i = 0; i < childList.size(); i++){
        	Element child = childList.get(i);
        	//该mxCell节点为非连线节点情况
            if(null != child.attributeValue("vertex") && !"".equals(child.attributeValue("vertex"))){
            	//事件子流程
            	if(flag == 1){
            		//////////////0004:【错误开始事件】///////////
                	if(child.attributeValue("node_type").equals("errorStartEvent")){
                		bpmnxml.append(MxErrorStartEvent.errorStartEvent(root, mxCellList, child));
                		bpmndi.append(MxErrorStartEvent.errorStartEventBpmndi(root, mxCellList, child));
                	}
            	}
            	//事物子流程
            	if(flag == 2){
            		//////////////0001:【开始节点】////////////
                	if(child.attributeValue("node_type").equals("startEvent")){
                		bpmnxml.append(MxStartEvent.startEvent(root, mxCellList, child));
                		bpmndi.append(MxStartEvent.startBpmndi(root, mxCellList, child));
                	}
            		//////////////0030:【取消边界（注：该节点不在该地方处理）】///////////
    	        	if(child.attributeValue("node_type").equals("cancelBoundaryEvent")){
    	            	bpmnxml.append(MxCalcelBoundaryEvent.calcelBoundaryEvent(root, mxCellList, child));
    	        		bpmndi.append(MxCalcelBoundaryEvent.calcelBoundaryEventBpmndi(root, mxCellList, child));
    	        	}
            	}
            	//子流程
            	if(flag == 3){
            		//////////////0001:【开始节点】////////////
                	if(child.attributeValue("node_type").equals("startEvent")){
                		bpmnxml.append(MxStartEvent.startEvent(root, mxCellList, child));
                		bpmndi.append(MxStartEvent.startBpmndi(root, mxCellList, child));
                	}
            	}
            	/////////////////////////以下为公共节点//////////////////////////
            	//////////////0003:【消息开始事件】///////////
            	if(child.attributeValue("node_type").equals("messageStartEvent")){
            		bpmnxml.append(MxMessageStartEvent.messageStartEvent(root, mxCellList, child));
            		bpmndi.append(MxMessageStartEvent.messageStartEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0005:【信号启动事件】///////////
            	if(child.attributeValue("node_type").equals("signalStartEvent")){
            		bpmnxml.append(MxSignalStartEvent.signalStartEvent(root, mxCellList, child));
            		bpmndi.append(MxSignalStartEvent.signalStartEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0006:【结束节点】////////////
            	if(child.attributeValue("node_type").equals("endEvent")){
            		bpmnxml.append(MxEndEvent.endEvent(root, mxCellList, child));
            		bpmndi.append(MxEndEvent.endBpmndi(root, mxCellList, child));
            	}
            	//////////////0007:【错误结束事件】///////////
            	if(child.attributeValue("node_type").equals("errorEndEvent")){
            		bpmnxml.append(MxErrorEndEvent.errorEndEvent(root, mxCellList, child));
            		bpmndi.append(MxErrorEndEvent.errorEndEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0008:【终止结束事件】///////////
            	if(child.attributeValue("node_type").equals("terminateEndEvent")){
            		bpmnxml.append(MxTerminateEndEvent.terminateEndEvent(root, mxCellList, child));
            		bpmndi.append(MxTerminateEndEvent.terminateEndEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0009:【取消结束事件】///////////
            	if(child.attributeValue("node_type").equals("cancelEndEvent")){
            		bpmnxml.append(MxCancelEndEvent.cancelEndEvent(root, mxCellList, child));
            		bpmndi.append(MxCancelEndEvent.cancelEndEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0010:【人工任务节点】///////////
            	if(child.attributeValue("node_type").equals("userTask")){
            		bpmnxml.append(MxUserTask.userTask(root, mxCellList, child));
            		bpmndi.append(MxUserTask.userTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0011:【手动任务节点】///////////
            	if(child.attributeValue("node_type").equals("manualTask")){
            		bpmnxml.append(MxManualTask.manualTask(root, mxCellList, child));
            		bpmndi.append(MxManualTask.manualTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0012:【脚本任务节点】///////////
            	if(child.attributeValue("node_type").equals("scriptTask")){
            		bpmnxml.append(MxScriptTask.scriptTask(root, mxCellList, child));
            		bpmndi.append(MxScriptTask.scriptTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0013:【服务任务节点】///////////
            	if(child.attributeValue("node_type").equals("serviceTask")){
            		bpmnxml.append(MxServiceTask.serviceTask(root, mxCellList, child));
            		bpmndi.append(MxServiceTask.serviceTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0014:【邮件任务节点】///////////
            	if(child.attributeValue("node_type").equals("mailTask")){
            		bpmnxml.append(MxMailTask.mailTask(root, mxCellList, child));
            		bpmndi.append(MxMailTask.mailTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0015:【接收任务节点】///////////
            	if(child.attributeValue("node_type").equals("receiveTask")){
            		bpmnxml.append(MxReceiveTask.receiveTask(root, mxCellList, child));
            		bpmndi.append(MxReceiveTask.receiveTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0016:【业务规则节点】///////////
            	if(child.attributeValue("node_type").equals("businessRuleTask")){
            		bpmnxml.append(MxBusinessRuleTask.businessRuleTask(root, mxCellList, child));
            		bpmndi.append(MxBusinessRuleTask.businessRuleTaskBpmndi(root, mxCellList, child));
            	}
            	//////////////0017:【调用子流程节点】///////////
            	if(child.attributeValue("node_type").equals("callActivity")){
            		bpmnxml.append(MxCallActivity.callActivity(root, mxCellList, child));
            		bpmndi.append(MxCallActivity.callActivityBpmndi(root, mxCellList, child));
            	}
            	//////////////0018:【并行网关】///////////
            	if(child.attributeValue("node_type").equals("parallelGateway")){
            		bpmnxml.append(MxParallelGateway.parallelGateway(root, mxCellList, child));
            		bpmndi.append(MxParallelGateway.parallelGatewayBpmndi(root, mxCellList, child));
            	}
            	//////////////0019:【排他网关】///////////
            	if(child.attributeValue("node_type").equals("exclusiveGateway")){
            		bpmnxml.append(MxExclusiveGateway.exclusiveGateway(root, mxCellList, child));
            		bpmndi.append(MxExclusiveGateway.exclusiveGatewayBpmndi(root, mxCellList, child));
            	}
            	//////////////0020:【包括网关】///////////
            	if(child.attributeValue("node_type").equals("inclusiveGateway")){
            		bpmnxml.append(MxInclusiveGateway.inclusiveGateway(root, mxCellList, child));
            		bpmndi.append(MxInclusiveGateway.inclusiveGatewayBpmndi(root, mxCellList, child));
            	}
            	//////////////0021:【事件网关】///////////
            	if(child.attributeValue("node_type").equals("eventGateway")){
            		bpmnxml.append(MxEventGateway.eventGateway(root, mxCellList, child));
            		bpmndi.append(MxEventGateway.eventGatewayBpmndi(root, mxCellList, child));
            	}
            	
            	//////////////0022:【事件子流程】///////////
            	if(child.attributeValue("node_type").equals("eventSubProcess")){
            		bpmnxml.append(MxEventSubProcess.eventSubProcess(root, mxCellList, child));
            		bpmndi.append(MxEventSubProcess.eventSubProcessBpmndi(root, mxCellList, child));
            	}
            	//////////////0023:【事物流程】///////////
            	if(child.attributeValue("node_type").equals("transactionProcess")){
            		bpmnxml.append(MxTransactionProcess.transactionProcess(root, mxCellList, child));
            		bpmndi.append(MxTransactionProcess.transactionProcessBpmndi(root, mxCellList, child));
            	}
            	//////////////0024:【子流程】///////////
            	if(child.attributeValue("node_type").equals("subProcess")){
            		bpmnxml.append(MxSubProcess.subProcess(root, mxCellList, child));
            		bpmndi.append(MxSubProcess.subProcessBpmndi(root, mxCellList, child));
            	}
	        	//////////////0027:【时间边界（注：该节点不在该地方处理）】///////////
	        	if(child.attributeValue("node_type").equals("boundaryEventTime")){
	            	bpmnxml.append(MxTimerBoundaryEvent.timerBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxTimerBoundaryEvent.timerBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        	//////////////0028:【错误边界（注：该节点不在该地方处理）】///////////
	        	if(child.attributeValue("node_type").equals("exclusiveGatewayError")){
	            	bpmnxml.append(MxErrorBoundaryEvent.errorBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxErrorBoundaryEvent.errorBoundaryEventBpmndi(root, mxCellList, child));
	        	}
				//////////////0029:【消息边界（注：该节点不在该地方处理）】///////////
	        	if(child.attributeValue("node_type").equals("messageBoundaryEvent")){
	            	bpmnxml.append(MxMessageBoundaryEvent.messageBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxMessageBoundaryEvent.messageBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        	
			    //////////////0031:【补偿边界（注：该节点不在该地方处理）】///////////
	        	if(child.attributeValue("node_type").equals("compensationBoundaryEvent")){
	            	bpmnxml.append(MxCompensationBoundaryEvent.compensationBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxCompensationBoundaryEvent.compensationBoundaryEventBpmndi(root, mxCellList, child));
	        	}
				//////////////0032:【信号边界（注：该节点不在该地方处理）】///////////
	        	if(child.attributeValue("node_type").equals("signalBoundaryEvent")){
	            	bpmnxml.append(MxSignalBoundaryEvent.signalBoundaryEvent(root, mxCellList, child));
	        		bpmndi.append(MxSignalBoundaryEvent.signalBoundaryEventBpmndi(root, mxCellList, child));
	        	}
	        	//////////////0033:【定时捕捉事件】///////////
            	if(child.attributeValue("node_type").equals("timerCatchingEvent")){
            		bpmnxml.append(MxTimerCatchingEvent.timerCatchingEvent(root, mxCellList, child));
            		bpmndi.append(MxTimerCatchingEvent.timerCatchingEventBpmndi(root, mxCellList, child));
            	}
				//////////////0034:【信号捕捉事件】///////////
            	if(child.attributeValue("node_type").equals("signalCatchingEvent")){
            		bpmnxml.append(MxSignalCatchingEvent.signalCatchingEvent(root, mxCellList, child));
            		bpmndi.append(MxSignalCatchingEvent.signalCatchingEventBpmndi(root, mxCellList, child));
            	}
				//////////////0035:【消息捕捉事件】///////////
            	if(child.attributeValue("node_type").equals("messageCatchingEvent")){
            		bpmnxml.append(MxMessageCatchingEvent.messageCatchingEvent(root, mxCellList, child));
            		bpmndi.append(MxMessageCatchingEvent.messageCatchingEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0036:【信号抛出事件】///////////
            	if(child.attributeValue("node_type").equals("signalThrowingEvent")){
            		bpmnxml.append(MxSignalThrowingEvent.signalThrowingEvent(root, mxCellList, child));
            		bpmndi.append(MxSignalThrowingEvent.signalThrowingEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0037:【补偿抛出事件】///////////
            	if(child.attributeValue("node_type").equals("compensationThrowingEvent")){
            		bpmnxml.append(MxCompensationThrowingEvent.compensationThrowingEvent(root, mxCellList, child));
            		bpmndi.append(MxCompensationThrowingEvent.compensationThrowingEventBpmndi(root, mxCellList, child));
            	}
            	//////////////0038:【非抛出事件】////////////
            	if(child.attributeValue("node_type").equals("noneThrowingEvent")){
            		bpmnxml.append(MxNoneThrowingEvent.noneThrowingEvent(root, mxCellList, child));
            		bpmndi.append(MxNoneThrowingEvent.noneThrowingEventBpmndi(root, mxCellList, child));
            	}
	        }
        }
        map.put("bpmnxml", bpmnxml.toString());
		map.put("bpmndi", bpmndi.toString());
		return map;
	}
	/**
	 * 验证基本信息
	 * @param root
	 */
	public static void validate(Element root){
		int endNum = root.selectNodes("/root/mxCell[@node_type='end']").size();
		if(endNum<1){
			//提示
			System.out.println("一个流程中必须存在结束节点");
			//需要终止
		}
		//验证开始节点只能存在一个
		int startNum = root.selectNodes("/root/mxCell[@node_type='start']").size();
		if(startNum>1){
			//提示
			System.out.println("一个流程中开始节点只能存在一个");
			//需要终止
		}
		if(startNum<1){
			//提示
			System.out.println("一个流程中必须存在一个开始节点");
			//需要终止
		}
	}
	
	/**
	 * 监听器配置
	 * @param mxCell
	 * @return
	 */
	public static String eventListenerNode(Element mxCell){
		String eventListenerNode="";
		String event_node_value = mxCell.attributeValue("event_node_value");/**监听事件属性**/ 
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
        		eventListenerNode += "<activiti:executionListener event='start' "+excuteStr+">";
                //1-1字段开始 子循环操作
        		if(null != cell[1] && !"".equals(cell[1]) && ("javaclass".equals(cell[1]) || "express".equals(cell[1]))){
        			//此时存在字段 字段位置在最后一个
        			String[] field = cell[3].split("$");
        			for(int j = 0; j < field.length; j++){
        				String[] fieldV = field[j].split("&");
        				eventListenerNode += "<activiti:field name='"+fieldV[0]+"'>";
        				eventListenerNode += "<activiti:string><![CDATA["+fieldV[1]+"]]></activiti:string>";
        				eventListenerNode += "</activiti:field>";
        			}
        		}
                //1-1字段结束
        		eventListenerNode += "</activiti:executionListener>";
                //1监听的类结束
        	}
        }        
        //监听器配置结束
        return eventListenerNode;
	}
	
	/**
	 * 统一连线配置
	 * 再次进行循环 目的获取连接线
	 * @param mxCellList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sequenceFlow(Element root,List<Element> mxCellList,Element mxCell){
		mxCellList = root.elements();
		String id = mxCell.attributeValue("id");
		String nodeID = mxCell.attributeValue("nodeID");
		String skipexpression = mxCell.attributeValue("skipexpression");
		if(null != skipexpression && !"".equals(skipexpression)){
			skipexpression = " skipExpression='"+skipexpression+"'";
		}else{
			skipexpression = "";
		}
		
		String sequenceFlow="";//连线指向
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
            		List<Element> target_target_list = root.selectNodes("/root/mxCell[@id='"+mxCell_agin.attributeValue("target")+"']");
        			if(!target_target_list.isEmpty()){
        				Element mxCell_target = (Element)target_target_list.get(0);
        				if(sequenceFlowIsInMessageFlow(mxCellList, mxCell_agin)){
        					sequenceFlow += "<sequenceFlow id='"+mxCell_agin.attributeValue("nodeID")+"' name='"+mxCell_agin.attributeValue("value")+"' sourceRef='"+nodeID+"' targetRef='"+mxCell_target.attributeValue("nodeID")+"' "+skipexpression+"></sequenceFlow>";
        				}
        			}
            	}
            }
		}
		return sequenceFlow;
	}
	
	/**
	 * 判断指定节点 是否存在消息连线中
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	public static boolean sequenceFlowIsInMessageFlow(List<Element> mxCellList,Element mxCell){
		List<Element> flowEdgeList = resultMessageFlowEdgeList(mxCellList);
		for(int i = 0; i < flowEdgeList.size(); i++){
			Element flowEdge = flowEdgeList.get(i);
			if(mxCell.attributeValue("nodeID").equals(flowEdge.attributeValue("nodeID"))){
				return false;
			}
		}
		return true;
	}
	/**
	 * 备注配置
	 * @param mxCell
	 * @return
	 */
	public static String documentation(Element mxCell){
		String node="";
		String documentation = mxCell.attributeValue("documentation");
		 if(null != documentation && !"".equals(documentation)){
			 node += "<documentation>"+documentation+"</documentation>";
	     }
		 return node;
	}
	
	/**
	 * 表单配置
	 * @param mxCell
	 * @return
	 */
	public static String form(Element mxCell){
		String node="";
		String form_node_value = mxCell.attributeValue("form_node_value");/**表单属性**/
		//表单配置开始
        if(null != form_node_value && !"".equals(form_node_value)){
        	String[] form_node_valueGrid = form_node_value.split("#");
        	for(int i = 0; i < form_node_valueGrid.length; i++){
        		String[] cell = form_node_valueGrid[i].split("@");
        		node += "<activiti:formProperty id='"+cell[0]+"' name='"+cell[1]+"' type='"+cell[2]+"' expression='"+cell[3]+"' variable='"+cell[4]+"' default='"+cell[5]+"' datePattern='"+cell[6]+"' readable='"+cell[7]+"' writable='"+cell[8]+"' required='"+cell[9]+"'>";
                //字段
        		String[] field = cell[10].split("$");
        		for(int j = 0; j < field.length; j++){
        			String [] fieldValue = field[j].split("&");
        			node += "<activiti:value id='"+fieldValue[0]+"' name='"+fieldValue[1]+"'></activiti:value>";
        		}
        		node += "</activiti:formProperty>";
        	}
        }
        //表单配置结束
        return node;
	}
	
	/**
	 * 一般配置
	 * @return
	 */
	public static String normal(Element mxCell){
		String asynchronous = mxCell.attributeValue("asynchronous");
 		String isForCompensation =  mxCell.attributeValue("isForCompensation");//是否补偿边界
 		String exclusive = mxCell.attributeValue("isForCompensation");//是否补偿边界
 		if(null != asynchronous && !"".equals(asynchronous) && "0".equals(asynchronous)){
        	asynchronous = " activiti:async='"+asynchronous+"'";
        }else{
        	asynchronous="";
        }
 		if(null != exclusive && !"".equals(exclusive) && "0".equals(exclusive)){
 			exclusive = " activiti:exclusive='"+asynchronous+"'";
        }else{
        	exclusive="";
        }
        if(null != isForCompensation && !"".equals(isForCompensation) && "1".equals(isForCompensation)){
        	isForCompensation = " isForCompensation='true'";
        }else{
        	isForCompensation=" ";
        }
        return asynchronous+isForCompensation+exclusive;
	}
	
	/**
	 * 输入参数（调用子流程）
	 * @param mxCell
	 * @return
	 */
	public static String callActivityInputNode(Element mxCell){
		String inputNode="";
		String callActivity_input_value = mxCell.attributeValue("callActivity_input_value");
        if(null != callActivity_input_value && !"".equals(callActivity_input_value)){
        	//主列表
        	String[] inputGrid = callActivity_input_value.split("#");
        	for(int i = 0; i < inputGrid.length; i++){
        		String[] cell = inputGrid[i].split("@");
        		inputNode += "<activiti:in source='"+cell[0]+"' sourceExpression='"+cell[1]+"' target='"+cell[2]+"'></activiti:in>";
        	}
        }        
        return inputNode;
	}
	
	/**
	 * 输出参数（调用子流程）
	 * @param mxCell
	 * @return
	 */
	public static String callActivityOutNode(Element mxCell){
		String outNode="";
		String callActivity_out_value = mxCell.attributeValue("callActivity_out_value");
        if(null != callActivity_out_value && !"".equals(callActivity_out_value)){
        	//主列表
        	String[] outGrid = callActivity_out_value.split("#");
        	for(int i = 0; i < outGrid.length; i++){
        		String[] cell = outGrid[i].split("@");
        		outNode += "<activiti:in source='"+cell[0]+"' sourceExpression='"+cell[1]+"' target='"+cell[2]+"'></activiti:in>";
        	}
        }        
        return outNode;
	}
	
	/**
	 * 服务任务字段描述
	 * @param mxCell
	 * @return
	 */
	public static String serviceFiledDec(Element mxCell){
		String outNode="";
		String serviceNodeAttributeField_value = mxCell.attributeValue("serviceNodeAttributeField_value");
        if(null != serviceNodeAttributeField_value && !"".equals(serviceNodeAttributeField_value)){
        	//主列表
        	String[] outGrid = serviceNodeAttributeField_value.split("#");
        	for(int i = 0; i < outGrid.length; i++){
        		String[] cell = outGrid[i].split("@");
        		outNode += "<activiti:field name='"+cell[0]+"'>";
        		outNode += "<activiti:string><![CDATA["+cell[1]+"]]></activiti:string>";
        		outNode += "</activiti:field>";
        	}
        }        
        return outNode;
	}
	
	/**
	 * 返回泳道集合
	 * @param element
	 * @return
	 */
	public static List<Element> resultPoolList(List<Element> mxCellList){
		List<Element> mxElementPoolList = new ArrayList<Element>();
		MxElement mxElement = resultFiveMxElement(mxCellList);
		List<Element> mxCellOneList = mxElement.getMxCellOneList();
		for(int i = 0; i < mxCellOneList.size(); i++){
			Element e = mxCellOneList.get(i);
			if(e.attributeValue("node_type").equals("pool")){
				mxElementPoolList.add(e);
			}
		}
		return mxElementPoolList;
	}
	
	/**
	 * 返回泳道
	 * @param mxCellList
	 * @return
	 */
	public static String resultP(List<Element> mxCellList){
		List<Element> mxElementPoolList = new ArrayList<Element>();
		MxElement mxElement = resultFiveMxElement(mxCellList);
		List<Element> mxCellOneList = mxElement.getMxCellOneList();
		//筛选泳道
		for(int i = 0; i < mxCellOneList.size(); i++){
			Element e = mxCellOneList.get(i);
			if(e.attributeValue("node_type").equals("pool")){
				mxElementPoolList.add(e);
			}
		}
		StringBuffer sbf = new StringBuffer();
		sbf.append("<collaboration id='Collaboration'>");
		//遍历泳道基本信息
		for(int i = 0; i < mxElementPoolList.size(); i++){
			Element e = mxElementPoolList.get(i);
			String name = e.attributeValue("value");
	 		String nodeID = e.attributeValue("nodeID");
	 		String processId_ = e.attributeValue("processId_");//流程编号
			sbf.append("<participant id='"+nodeID+"' name='"+name+"' processRef='"+processId_+"'></participant>");
		}
		
		//追加messageFlow（特地说明：非泳道中节点连接泳道中元素时分两种情况：
		//1连接第一个泳道池【即最小编号泳道池】此时连线为非MessageFlow类型
		//2连接第二个泳道池 则连线为MessageFlow类型）
		//3泳道与泳道之间的元素连接 连接类型为MessageFlow
		List<Element> flowEdgeList = resultMessageFlowEdgeList(mxCellList);
		for(int i = 0; i < flowEdgeList.size(); i++){
			Element flowEdge = flowEdgeList.get(i);
			Element sourceElement = getElement(mxCellList, flowEdge.attributeValue("source"));
			Element targetElement = getElement(mxCellList, flowEdge.attributeValue("target"));
			sbf.append("<messageFlow id='"+flowEdge.attributeValue("nodeID")+"' name='"+flowEdge.attributeValue("value")+"' sourceRef='"+sourceElement.attributeValue("nodeID")+"' targetRef='"+targetElement.attributeValue("nodeID")+"'></messageFlow>");
		}
		sbf.append("</collaboration>");
		return sbf.toString();
	}
	
	/**
	 * 返回非根节点的五级目录（其实有N级目前只筛选5级）
	 * @param element
	 * @return
	 */
	public static MxElement resultFiveMxElement(List<Element> mxCellList){
		MxElement mxElement = new MxElement();
		List<Element> listEdgeElement = new ArrayList<Element>();
		List<Element> listVertexElement = new ArrayList<Element>();
		List<Element> listOneElement= new ArrayList<Element>();
		List<Element> listTwoElement = new ArrayList<Element>();
		List<Element> listThreeElement = new ArrayList<Element>();
		List<Element> listFourElement = new ArrayList<Element>();
		List<Element> listFiveElement = new ArrayList<Element>();
		for(int i = 0; i < mxCellList.size(); i++){
			Element mxCell = (Element) mxCellList.get(i); 
			//连线情况
			if(null != mxCell.attributeValue("edge") && !"".equals(mxCell.attributeValue("edge"))){
				listEdgeElement.add(mxCell);
			}else{
				//正常节点
				listVertexElement.add(mxCell);
			}
		}
		//装配一级 二级 三级 四级 五级节点
		//装配一级
		for(int i = 0; i < listVertexElement.size(); i++){
			Element mxCell = (Element) listVertexElement.get(i); 
			if(null == mxCell.attributeValue("edge") || "".equals(mxCell.attributeValue("edge"))){
				if(null != mxCell.attributeValue("parent") && !"".equals(mxCell.attributeValue("parent")) && "1".equals(mxCell.attributeValue("parent"))){
					listOneElement.add(mxCell);
				}
			}
		}
		//装配二级
		for(int i = 0; i < listOneElement.size(); i++){
			Element mxCell = (Element) listOneElement.get(i); 
			for(int j = 0; j < mxCellList.size(); j++){
				Element cell = mxCellList.get(j);
				//该mxCell节点为非连线节点情况
	            if(null == cell.attributeValue("edge") || "".equals(cell.attributeValue("edge"))){
					if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && null != cell.attributeValue("parent") && cell.attributeValue("parent").equals(mxCell.attributeValue("id"))){
						listTwoElement.add(cell);
					}
	            }
			}
		}
		//装配三级
		for(int i = 0; i < listTwoElement.size(); i++){
			Element mxCell = (Element) listTwoElement.get(i); 
			for(int j = 0; j < mxCellList.size(); j++){
				Element cell = mxCellList.get(j);
				//该mxCell节点为非连线节点情况
				if(null == cell.attributeValue("edge") || "".equals(cell.attributeValue("edge"))){
					if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && null != cell.attributeValue("parent") && cell.attributeValue("parent").equals(mxCell.attributeValue("id"))){
						listThreeElement.add(cell);
					}
	            }
			}
		}
		//装配四级
		for(int i = 0; i < listThreeElement.size(); i++){
			Element mxCell = (Element) listThreeElement.get(i); 
			for(int j = 0; j < mxCellList.size(); j++){
				Element cell = mxCellList.get(j);
				//该mxCell节点为非连线节点情况
				if(null == cell.attributeValue("edge") || "".equals(cell.attributeValue("edge"))){
					if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && null != cell.attributeValue("parent") && cell.attributeValue("parent").equals(mxCell.attributeValue("id"))){
						listFourElement.add(cell);
					}
	            }
			}
		}
		//装配五级
		for(int i = 0; i < listFourElement.size(); i++){
			Element mxCell = (Element) listFourElement.get(i); 
			for(int j = 0; j < mxCellList.size(); j++){
				Element cell = mxCellList.get(j);
				//该mxCell节点为非连线节点情况
				if(null == cell.attributeValue("edge") || "".equals(cell.attributeValue("edge"))){
	            	if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && null != cell.attributeValue("parent") && cell.attributeValue("parent").equals(mxCell.attributeValue("id"))){
						listFiveElement.add(cell);
					}
	            }
			}
		}
		mxElement.setMxCellOneList(listOneElement);
		mxElement.setMxCellTwoList(listTwoElement);
		mxElement.setMxCellThreeList(listThreeElement);
		mxElement.setMxCellFourList(listFourElement);
		mxElement.setMxCellFiveList(listFiveElement);
		mxElement.setMxCellEdgeList(listEdgeElement);
		return mxElement;
	}
	
	/**
	 * 获取孩子节点
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	public static List<Element> getChildElementList(List<Element> mxCellList,Element mxCell){
		List<Element> list = new ArrayList<Element>();
		for(int j = 0; j < mxCellList.size(); j++){
			Element cell = mxCellList.get(j);
			if(null == cell.attributeValue("edge") || "".equals(cell.attributeValue("edge"))){
				if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && null != cell.attributeValue("parent") && cell.attributeValue("parent").equals(mxCell.attributeValue("id"))){
					list.add(cell);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据当前连线节点获取子节点（即坐标）
	 * @param mxCellList
	 * @param edge
	 * @return
	 */
	public static List<Map<String, Object>> getChildEdgeXYList(List<Element> mxCellList,Element edge){
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		List<Element> edgeList = Dom4jUtils.getElement(edge);
		for(int i = 0; i < edgeList.size(); i++){
			Element ele = edgeList.get(i);
			List<Element> eleList = Dom4jUtils.getElement(ele);
			for(int j = 0; j < eleList.size(); j++){
				Element e = eleList.get(j);
				List<Element> elList = Dom4jUtils.getElement(e);
				for(int l = 0; l < elList.size(); l++){
					Element el = elList.get(l);
					Map<String, Object> map = new HashMap<String, Object>();
					String x = el.attributeValue("x");
					String y = el.attributeValue("y");
					if(null != x && !"".equals(x)){
						map.put("x", x);
					}else{
						map.put("x","0");
					}
					if(null != y && !"".equals(y)){
						map.put("y", y);
					}else{
						map.put("y","0");
					}
					listMap.add(map);
				}
			}
		}
		return listMap;
	}
	
	/**
	 * 返回连线坐标
	 * @param mxCellList
	 * @param edge
	 * @return
	 */
	public static String resultChildEdgeXy(List<Element> mxCellList,Element edge){
		List<Map<String, Object>> listMap = getChildEdgeXYList(mxCellList, edge);
		Element sourceMxCell =getElement(mxCellList, edge.attributeValue("source"));
		Element targetMxCell = getElement(mxCellList, edge.attributeValue("target"));
		StringBuffer sbf = new StringBuffer();
		if(null != listMap && listMap.size()>0 && !listMap.isEmpty()){
			for(int i = 0; i < listMap.size(); i++){
				sbf.append("<omgdi:waypoint x='"+listMap.get(i).get("x")+"' y='"+listMap.get(i).get("y")+"'></omgdi:waypoint>");
			}
		}else{
			Element mxGeometrySource = sourceMxCell.element("mxGeometry"); 
			Element mxGeometryTarget = targetMxCell.element("mxGeometry"); 
	        String s_x = mxGeometrySource.attributeValue("x");
	        String s_y = mxGeometrySource.attributeValue("y");
	        String t_x = mxGeometryTarget.attributeValue("x");
	        String t_y = mxGeometryTarget.attributeValue("y");
	        if(null == s_x || "".equals(s_x)){
	        	s_x = "0";
	        }
	        if(null == s_y || "".equals(s_y)){
	        	s_y = "0";
	        }
	        if(null == t_x || "".equals(t_x)){
	        	t_x = "0";
	        }
	        if(null == t_y || "".equals(t_y)){
	        	t_y = "0";
	        }
	        sbf.append("<omgdi:waypoint x='"+s_x+"' y='"+s_y+"'></omgdi:waypoint>");
	        sbf.append("<omgdi:waypoint x='"+t_x+"' y='"+t_y+"'></omgdi:waypoint>");
		}
		return sbf.toString();
	}
	/**
	 * 根据当前节点加深度 获取当前下的第N级节点集合 共4级
	 * @param mxCellList
	 * @param mxCell
	 * @param path深度（即1,2,3,4）
	 * @return
	 */
	public static List<Element> getElementList(List<Element> mxCellList,Element mxCell,int path){
		List<Element> list = new ArrayList<Element>();
		//如果为1 则获取当前节点儿子即第二级
		if(path == 1){
			for(int i = 0; i < mxCellList.size(); i++){
				Element cell = mxCellList.get(i);
				if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && cell.attributeValue("parent").equals(mxCell.attributeValue("parent"))){
					list.add(cell);
				}
			}
			return list;
			
		}else if(path == 2){
			//如果为2 则获取当前节点孙子即第三级
			for(int i = 0; i < mxCellList.size(); i++){
				Element cell = mxCellList.get(i);
				if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && cell.attributeValue("parent").equals(mxCell.attributeValue("parent"))){
					list.add(cell);
				}
			}
			List<Element> list2 = new ArrayList<Element>();
			for(int i = 0; i < list.size(); i++){
				Element cell = list.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					Element cellAll = mxCellList.get(j);
					if(null != cellAll.attributeValue("parent") && !"".equals(cellAll.attributeValue("parent")) && cell.attributeValue("id").equals(cellAll.attributeValue("parent"))){
						list2.add(cellAll);
					}
				}
			}
			return list2;
			
		}else if(path == 3){
			//如果为3 则获取当前节点重孙子即第四级
			for(int i = 0; i < mxCellList.size(); i++){
				Element cell = mxCellList.get(i);
				if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && cell.attributeValue("parent").equals(mxCell.attributeValue("parent"))){
					list.add(mxCell);
				}
			}
			//孙子集合
			List<Element> list2 = new ArrayList<Element>();
			for(int i = 0; i < list.size(); i++){
				Element cell = list.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					if(null != mxCell.attributeValue("parent") && !"".equals(mxCell.attributeValue("parent")) && cell.attributeValue("id").equals(mxCell.attributeValue("parent"))){
						list2.add(mxCell);
					}
				}
			}
			//重孙子
			List<Element> list3 = new ArrayList<Element>();
			for(int i = 0; i < list2.size(); i++){
				Element cell = list2.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					Element cellAll = mxCellList.get(j);
					if(null != cellAll.attributeValue("parent") && !"".equals(cellAll.attributeValue("parent")) && cell.attributeValue("id").equals(cellAll.attributeValue("parent"))){
						list3.add(cellAll);
					}
				}
			}
			return list3;
			
		}else if(path == 4){
			//如果为4 则获取当前节点后代即第五级
			for(int i = 0; i < mxCellList.size(); i++){
				Element cell = mxCellList.get(i);
				if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && cell.attributeValue("parent").equals(mxCell.attributeValue("parent"))){
					list.add(mxCell);
				}
			}
			//孙子集合
			List<Element> list2 = new ArrayList<Element>();
			for(int i = 0; i < list.size(); i++){
				Element cell = list.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					if(null != mxCell.attributeValue("parent") && !"".equals(mxCell.attributeValue("parent")) && cell.attributeValue("id").equals(mxCell.attributeValue("parent"))){
						list2.add(mxCell);
					}
				}
			}
			//重孙子
			List<Element> list3 = new ArrayList<Element>();
			for(int i = 0; i < list2.size(); i++){
				Element cell = list2.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					Element cellAll = mxCellList.get(j);
					if(null != cellAll.attributeValue("parent") && !"".equals(cellAll.attributeValue("parent")) && cell.attributeValue("id").equals(cellAll.attributeValue("parent"))){
						list3.add(cellAll);
					}
				}
			}
			//后代
			List<Element> list4 = new ArrayList<Element>();
			for(int i = 0; i < list3.size(); i++){
				Element cell = list3.get(i);
				for(int j = 0;j < mxCellList.size(); j++){
					Element cellAll = mxCellList.get(j);
					if(null != cellAll.attributeValue("parent") && !"".equals(cellAll.attributeValue("parent")) && cell.attributeValue("id").equals(cellAll.attributeValue("parent"))){
						list4.add(cellAll);
					}
				}
			}
			return list4;
		}else{
			throw new ExceptionUtil("MxUtils number line 817 输入的深度不合法"); 
		}
	}
	
	/**
	 * 根据ID获取节点对象
	 * @param mxCellList
	 * @param id
	 * @return
	 */
	public static Element getElement(List<Element> mxCellList,String id){
		for(int i = 0; i < mxCellList.size(); i++){
			Element mxCell = (Element) mxCellList.get(i); 
			if(null != mxCell.attributeValue("id") && !"".equals(mxCell.attributeValue("id")) && id.equals(mxCell.attributeValue("id"))){
				return mxCell;
			}
		}
		return null;
	}
	
	
	/**
	 * 返回Map 第一个KEY：Element返回的是节点 如果第二个Key：IsRootElement等于true则表示返回是根节点 false话表示非根节点 节点为父节点
	 * @param mxCellList
	 * @param id
	 * @return
	 */
	public static Map<String, Object> getParentOrElement(List<Element> mxCellList,String id){
		Element cell = getElement(mxCellList, id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != cell){
			//根节点的下一级充当根节点（一级节点）
			if(null != cell.attributeValue("id") && !"".equals(cell.attributeValue("id")) && "1".equals(cell.attributeValue("parent"))){
				map.put("Element", cell);
				map.put("IsRootElement", true);
				return map;
			}else{
				cell = getElement(mxCellList, cell.attributeValue("parent"));
				map.put("Element", cell);
				map.put("IsRootElement", false);
				return map;
			}
		}
		return null;
	}
	
	/**
	 * 返回根节点
	 * @param mxCellList
	 * @param id
	 * @return
	 */
	public static Element getRootElement(List<Element> mxCellList,String id){
		Map<String, Object> map = getParentOrElement(mxCellList, id);
		if(null != map){
			if((Boolean)map.get("IsRootElement")){
				return (Element)map.get("Element");
			}else{
				return getRootElement(mxCellList, ((Element)map.get("Element")).attributeValue("id"));
			}
		}
		return null;
	}
	
	/**
	 * 返回满足所有messageFlow的连线
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	public static List<Element> resultMessageFlowEdgeList(List<Element> mxCellList){
		MxElement mxElement = resultFiveMxElement(mxCellList);
		List<Element> listEdge = mxElement.getMxCellEdgeList();
		List<Element> listMessageFlowEdge = new ArrayList<Element>();
		for(int i = 0; i < listEdge.size(); i++){
			Element edge = listEdge.get(i);
			String source = edge.attributeValue("source");
			String target = edge.attributeValue("target");
			//验证起点和终点是否满足如下条件：
			//1起点在非泳道中 终点在泳道中（第一个泳道除外，即第一个泳道池拖入到面板中的） 此时存在MessageFlow条件
			//2起点在泳道中（第一个泳道除外，即第一个泳道池拖入到面板中的），终点在非泳道中此时存在MessageFlow条件
			//3起点在泳道中 终点也在泳道中 同时起点所在的泳道与终点所在的泳道池不同 此时存在MessageFlow条件
			Element sourceRootElement = getRootElement(mxCellList, source);//源节点所在的一级节点
			Element targetRootElement = getRootElement(mxCellList, target);//目标节点所在的一级节点
			if(null != sourceRootElement && null != targetRootElement){
				if(resultPoolList(mxCellList).size() >= 2){
					//如果起点在非泳道池中 终点在泳道池中（并且终点所在的泳道池中不能为第一个泳道池）
					if(!sourceRootElement.attributeValue("node_type").equals("pool") && targetRootElement.attributeValue("node_type").equals("pool") && !cellIsInFristPool(mxCellList, targetRootElement)){
						listMessageFlowEdge.add(edge);
					}
					//如果起点在泳道池中（并且不在第一个泳道池中） 终点在非泳道池中
					if(sourceRootElement.attributeValue("node_type").equals("pool") && !cellIsInFristPool(mxCellList, sourceRootElement) && !targetRootElement.attributeValue("node_type").equals("pool")){
						listMessageFlowEdge.add(edge);
					}
					//如果两个节点都在泳道池中
					if(sourceRootElement.attributeValue("node_type").equals("pool") && targetRootElement.attributeValue("node_type").equals("pool")){
						//两个节点在不同泳道池中则满足MessageFlow
						if(!sourceRootElement.attributeValue("id").equals(targetRootElement.attributeValue("id"))){
							listMessageFlowEdge.add(edge);
						}
					}
				}
			}
		}
		return listMessageFlowEdge;
	}
	
	/**
	 * 判断节点是否为第一个泳道池
	 * @param mxCellList
	 * @return
	 */
	public static boolean cellIsInFristPool(List<Element> mxCellList,Element cell){
		List<Element> poolList = resultPoolList(mxCellList);
		List<Integer> list = new ArrayList<Integer>();
		if(null == poolList || poolList.size()==0){
			return false;
		}
		for(int i = 0; i < poolList.size(); i++){
			list.add(new Integer(poolList.get(i).attributeValue("id")));
		}
		Collections.sort(list);
		if(list.get(0).equals(new Integer(cell.attributeValue("id")))){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取第一个泳道池
	 * @param mxCellList
	 * @return
	 */
	public static Element resultFristPool(List<Element> mxCellList){
		List<Element> poolList = resultPoolList(mxCellList);
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < poolList.size(); i++){
			list.add(new Integer(poolList.get(i).attributeValue("id")));
		}
		Collections.sort(list);
		return getElement(mxCellList, ""+list.get(0));
	}
	
	/**
	 * 获取非第一个泳道池的其他泳道池集合
	 * @param mxCellList
	 * @return
	 */
	public static List<Element> resultUnFristPoolList(List<Element> mxCellList){
		List<Element> poolList = resultPoolList(mxCellList);
		List<Integer> list = new ArrayList<Integer>();
		List<Element> unFristPoolList = new ArrayList<Element>();
		if(!poolList.isEmpty() && poolList.size()>1){
			for(int i = 0; i < poolList.size(); i++){
				list.add(new Integer(poolList.get(i).attributeValue("id")));
			}
			Collections.sort(list);
			for(int i = 0; i < poolList.size(); i++){
				
				if(Integer.parseInt(poolList.get(i).attributeValue("id")) != (list.get(0).intValue())){
					unFristPoolList.add(poolList.get(i));
				}
			}
		}
		return unFristPoolList;
	}
	
	/**
	 * 获取非泳道外的一级节点集合
	 * @param mxCellList
	 * @return
	 */
	public static List<Element> resultUnPoolLevelFristList(List<Element> mxCellList){
		MxElement mxElement = resultFiveMxElement(mxCellList);
		List<Element> mxCellOneList = mxElement.getMxCellOneList();
		for(int i = 0; i < mxCellOneList.size(); i++){
			Element cell = mxCellOneList.get(i);
			if(cell.attributeValue("node_type").equals("pool")){
				mxCellOneList.remove(i);
				i--;
			}
		}
		return mxCellOneList;
	}
	
	/**
	 * 返回该节点相对于一级节点的坐标
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	public static Map<String, Object> resultBoundsXY(List<Element> mxCellList,Element mxCell){
		Map<String, Object> xyMap = new HashMap<String, Object>();
		Element parent = getElement(mxCellList, mxCell.attributeValue("parent"));
		//如果当前节点的上级是根节点 则直接返回该节点的坐标
		Element mxGeometry = mxCell.element("mxGeometry"); 
		String width = mxGeometry.attributeValue("width");
        String height = mxGeometry.attributeValue("height");
		if(null != parent && "1".equals(parent.attributeValue("id"))){
	        String x = mxGeometry.attributeValue("x");
	        String y = mxGeometry.attributeValue("y");
	        if(null == y || "".equals(y)){
	        	y = "0";
	        }
	        if(null == x || "".equals(x)){
	        	x = "0";
	        }
			xyMap.put("x", x);
			xyMap.put("y", y);
			if(null == xyMap.get("width")){
				xyMap.put("width", width);
			}
			if(null == xyMap.get("height")){
				xyMap.put("height", height);
			}
			return xyMap;
		}else{
			String x = mxGeometry.attributeValue("x");
	        String y = mxGeometry.attributeValue("y");
	        if(null == y || "".equals(y)){
	        	y = "0";
	        }
	        if(null == x || "".equals(x)){
	        	x = "0";
	        }
	        xyMap = resultBounds_XY(mxCellList, parent,x,y);
			xyMap.put("width", width);
			xyMap.put("height", height);
			return xyMap;
		}
	}
	
	
	/**
	 * 返回该节点相对于一级节点的坐标
	 * @param mxCellList
	 * @param mxCell
	 * @return
	 */
	public static Map<String, Object> resultBounds_XY(List<Element> mxCellList,Element mxCell,String pointX,String pointY){
		Map<String, Object> xyMap = new HashMap<String, Object>();
		Element parent = getElement(mxCellList, mxCell.attributeValue("parent"));
		//如果当前节点的上级是根节点 则直接返回该节点的坐标
		Element mxGeometry = mxCell.element("mxGeometry"); 
		if(null != mxCell && "1".equals(mxCell.attributeValue("id"))){
	        if(null == pointY || "".equals(pointY)){
	        	pointY = "0";
	        }
	        if(null == pointX || "".equals(pointX)){
	        	pointX = "0";
	        }
			xyMap.put("x", pointX);
			xyMap.put("y", pointY);
			return xyMap;
		}else{
			String x = mxGeometry.attributeValue("x");
	        String y = mxGeometry.attributeValue("y");
	        if(null == x || "".equals(x)){
	        	x = "0";
	        }
	        if(null == y || "".equals(y)){
	        	y = "0";
	        }
	        x = ""+(new Integer(pointX)+new Integer(x));
	        y = ""+(new Integer(pointY)+new Integer(y));
			return resultBounds_XY(mxCellList, parent,x,y);
		}
	}
	
	/**
	 * 返回所有lane Bpmndi
	 * @param mxCellList
	 * @return
	 */
	public static String resultLaneBpmndi(List<Element> mxCellList){
		StringBuffer sbf = new StringBuffer();
		List<Element> poolList = resultPoolList(mxCellList);
		for(int i = 0; i < poolList.size(); i++){
			Element pool = poolList.get(i);
			//获取该泳道池下面泳道集合
			List<Element> laneList = getChildElementList(mxCellList, pool);
			for(int j = 0; j < laneList.size(); j++){
				Element lane = laneList.get(j);
				Map<String, Object> xyMap = MxUtils.resultBoundsXY(mxCellList, lane);
		        String x = xyMap.get("x").toString();
		        String y = xyMap.get("y").toString();
		        String width = xyMap.get("width").toString();
		        String height = xyMap.get("height").toString();
		        sbf.append("<bpmndi:BPMNShape bpmnElement='"+lane.attributeValue("nodeID")+"' id='BPMNShape_"+lane.attributeValue("nodeID")+"'>");
		        sbf.append("<omgdc:Bounds height='"+height+"' width='"+width+"' x='"+x+"' y='"+y+"'></omgdc:Bounds>");
		        sbf.append("</bpmndi:BPMNShape>");
			}
		}
		return sbf.toString();
	}
}
