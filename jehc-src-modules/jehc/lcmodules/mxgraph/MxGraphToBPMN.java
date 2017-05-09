package jehc.lcmodules.mxgraph;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import jehc.lcmodules.mxgraph.mxUtils.communal.MxUtils;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
/**
 * 生成BPMN文件
 * @author邓纯杰
 *
 */
public class MxGraphToBPMN extends BaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 生成BPMN文件 返回BPMN+MaxGraph信息对象
	 * @param mxGraphModel
	 * @return
	 */
	public MxGraphModel createBPMN(MxGraphModel mxGraphModel){
		String mxgraphxml=null;
		try {
			mxgraphxml = formatXML(mxGraphModel.getMxgraphxml());
			String bpmnxml = MxUtils.mxgraphxml_to_bpmnxml(mxgraphxml,mxGraphModel);
			bpmnxml = formatXML(bpmnxml);
//			bpmnxml = paseJson(bpmnxml);
			if(null != bpmnxml && !"".equals(bpmnxml)){
				mxGraphModel.setBpmn(bpmnxml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mxGraphModel;
	}
	
	/**
	 * 流程的基本信息
	 * @return
	 */
	public static String basically(MxGraphModel mxGraphModel){
		String candidateStarterUsers = "";
		if(!StringUtils.isEmpty(mxGraphModel.getCandidateStarterUsers())){
			candidateStarterUsers = " activiti:candidateStarterUsers='"+mxGraphModel.getCandidateStarterUsers()+"'";
		}
		String candidateStarterGroups ="";
		if(!StringUtils.isEmpty(mxGraphModel.getCandidateStarterGroups())){
			candidateStarterGroups = " activiti:candidateStarterGroups='"+mxGraphModel.getCandidateStarterGroups()+"'";
		}
		String processId ="";
		if(StringUtils.isEmpty(mxGraphModel.getProcessId())){
			processId = UUID.toUUID();
		}else{
			processId = mxGraphModel.getProcessId();
		}
		String name = "";
		if(StringUtils.isEmpty(mxGraphModel.getProcessName())){
			name = UUID.toUUID();
		}else{
			name = mxGraphModel.getProcessName();
		}
		String basically = " id='"+ processId+"' name='"+name+"' "+candidateStarterUsers+candidateStarterGroups;
		return basically;
	}
	
	/**
	 * 流程的基本信息
	 * @return
	 */
	public static String basically(MxGraphModel mxGraphModel,Element pool){
		String candidateStarterUsers = "";
		if(!StringUtils.isEmpty(pool.attributeValue("candidateStarterUsers_"))){
			candidateStarterUsers = " activiti:candidateStarterUsers='"+pool.attributeValue("candidateStarterUsers_")+"'";
		}
		String candidateStarterGroups ="";
		if(!StringUtils.isEmpty(pool.attributeValue("candidateStarterGroups_"))){
			candidateStarterGroups = " activiti:candidateStarterGroups='"+pool.attributeValue("candidateStarterGroups_")+"'";
		}
		String basically = " id='"+ pool.attributeValue("processId_")+"' name='"+pool.attributeValue("processName_")+"' "+candidateStarterUsers+candidateStarterGroups;
		return basically;
	}
	/**
	 * 转换特殊符号
	 * @param str
	 * @return
	 */
	public String paseJson(String str) {
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		return str;
	}

	/**
	 * 格式化XML
	 * @param inputXML
	 * @return
	 * @throws Exception
	 */
	public String formatXML(String inputXML) throws Exception {
		if(StringUtils.isEmpty(inputXML)){
			return "";
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(inputXML));
		String requestXML = null;
		XMLWriter writer = null;
		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = new OutputFormat(" ", true);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXML = stringWriter.getBuffer().toString();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return requestXML;
	}
}
