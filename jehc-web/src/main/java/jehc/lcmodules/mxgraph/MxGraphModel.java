package jehc.lcmodules.mxgraph;
/**
 * MxGraphModel到JPDL属性
 * @author邓纯杰
 *
 */
public class MxGraphModel {
	private String w;
	private String h;
	private String mxgraphxml;
	private String imgxml;
	private String processName;
	private String processId;
	private String remark;
	private String m_event_value;
	private String candidateStarterUsers;//流程开始发起人集合 流程信息中
	private String candidateStarterGroups;//流程开始发起人组 流程中
	private String isExecutable;
	private String signals;
	private String messages;
	private String dataObject;
	
	private String bpmn;
	public String getMxgraphxml() {
		return mxgraphxml;
	}
	public void setMxgraphxml(String mxgraphxml) {
		this.mxgraphxml = mxgraphxml;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getM_event_value() {
		return m_event_value;
	}
	public void setM_event_value(String m_event_value) {
		this.m_event_value = m_event_value;
	}
	public String getCandidateStarterUsers() {
		return candidateStarterUsers;
	}
	public void setCandidateStarterUsers(String candidateStarterUsers) {
		this.candidateStarterUsers = candidateStarterUsers;
	}
	public String getCandidateStarterGroups() {
		return candidateStarterGroups;
	}
	public void setCandidateStarterGroups(String candidateStarterGroups) {
		this.candidateStarterGroups = candidateStarterGroups;
	}
	public String getIsExecutable() {
		return isExecutable;
	}
	public void setIsExecutable(String isExecutable) {
		this.isExecutable = isExecutable;
	}
	public String getSignals() {
		return signals;
	}
	public void setSignals(String signals) {
		this.signals = signals;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getDataObject() {
		return dataObject;
	}
	public void setDataObject(String dataObject) {
		this.dataObject = dataObject;
	}
	public String getImgxml() {
		return imgxml;
	}
	public void setImgxml(String imgxml) {
		this.imgxml = imgxml;
	}
	public String getBpmn() {
		return bpmn;
	}
	public void setBpmn(String bpmn) {
		this.bpmn = bpmn;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
}
