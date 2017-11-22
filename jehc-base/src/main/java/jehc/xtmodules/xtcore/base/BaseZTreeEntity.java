package jehc.xtmodules.xtcore.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * ZTree
 * @author 邓纯杰
 *
 */
public class BaseZTreeEntity {
	private static final long serialVersionUID = 7261712903068621559L;
	private String id;/**ID编号**/
	private String pid;/**父级编号**/
	private String text;/**显示值**/
	private String icon;/**图片**/
	private String qtip;/**提示**/
	private Boolean leaf;/**叶子**/
	private Boolean expanded = true;/**是否展开默认展开**/
	private Boolean checked = false;/**是否支持全选**/
 	private Boolean singleClickExpand = false;/**单击展开默认否false为双击节点展开 true单击节点展开**/
	private String tempObject;/**临时值**/
	private String content;/**描述**/
	private String integerappend;/**int类型拼接使用可以使用@或逗号隔开**/
	private ArrayList<BaseZTreeEntity> children = new ArrayList<BaseZTreeEntity>();/**子节点集合**/
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public ArrayList<BaseZTreeEntity> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<BaseZTreeEntity> children) {
		this.children = children;
	}
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getSingleClickExpand() {
		return singleClickExpand;
	}
	public void setSingleClickExpand(Boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}
	public String getTempObject() {
		return tempObject;
	}
	public void setTempObject(String tempObject) {
		this.tempObject = tempObject;
	}
	public static String buildTree(List<BaseZTreeEntity> list,boolean isHasChecbox){  
    	StringBuffer html = new StringBuffer();
    	html.append("[");  
        for(int i = 0; i < list.size(); i++) { 
        	BaseZTreeEntity node = list.get(i);
            if ("0".equals(node.getPid())) {  
            	String icons = "";
            	if(!StringUtils.isEmpty(node.getIcon())){
            		icons = " icon:'"+node.getIcon()+"',";
            	}
            	if(isHasChecbox){
            		html.append("{name:'" + node.getText() + "',id:'" + node.getId()+ "',checked:"+node.getChecked()+",singleClickExpand:"+node.getSingleClickExpand()+",pId:'"+node.getPid()+"',leaf:"+isLeaf(list,node)+","+icons+"qtip:'"+node.getText()+"',open:"+node.getExpanded()+",tempObject:'"+node.getTempObject()+"',content:'"+node.getContent()+"',integerappend:'"+node.getIntegerappend()+"'");
            	}else{
            		html.append("{name:'" + node.getText() + "',id:'" + node.getId()+ "',singleClickExpand:"+node.getSingleClickExpand()+",pId:'"+node.getPid()+"',leaf:"+isLeaf(list,node)+","+icons+"qtip:'"+node.getText()+"',open:"+node.getExpanded()+",tempObject:'"+node.getTempObject()+"',content:'"+node.getContent()+"',integerappend:'"+node.getIntegerappend()+"'");
            	}
            	html.append(build(list,node,isHasChecbox));
            	html.append("},");
            }
        }  
        html.append("]");  
        String jsonStr = html.toString().replaceAll("},]", "}]");
        return jsonStr;
    } 
    /**
     * 
     * @param childList
     * @param node
     */
    private static String build(List<BaseZTreeEntity> list,BaseZTreeEntity node,boolean isHasChecbox){  
    	StringBuffer html = new StringBuffer();
    	List<BaseZTreeEntity> children = getChildren(list,node,isHasChecbox);  
        if (!children.isEmpty() && children.size()>0) {
        	html.append(",children:[");  
        	for(int i = 0; i < children.size(); i++){
        		BaseZTreeEntity child = children.get(i);
        		String icons = "";
            	if(!StringUtils.isEmpty(child.getIcon())){
            		icons = " icon:'"+child.getIcon()+"',";
            	}
        		if(isHasChecbox){
        			//如果当前节点下面不存在节点则设置leaf为true 否则设置false
        			html.append("{name:'" + child.getText() + "',id:'" + child.getId()+ "',checked:"+child.getChecked()+",singleClickExpand:"+child.getSingleClickExpand()+",pId:'"+child.getPid()+"',leaf:"+isLeaf(list,child)+","+icons+"qtip:'"+child.getText()+"',open:"+child.getExpanded()+",tempObject:'"+child.getTempObject()+"',content:'"+child.getContent()+"',integerappend:'"+child.getIntegerappend()+"'");   
        		}else{
        			//如果当前节点下面不存在节点则设置leaf为true 否则设置false
        			html.append("{name:'" + child.getText() + "',id:'" + child.getId()+ "',singleClickExpand:"+child.getSingleClickExpand()+",pId:'"+child.getPid()+"',leaf:"+isLeaf(list,child)+","+icons+"qtip:'"+child.getText()+"',open:"+child.getExpanded()+",tempObject:'"+child.getTempObject()+"',content:'"+child.getContent()+"',integerappend:'"+child.getIntegerappend()+"'");   
        		}
            	html.append(build(list,child,isHasChecbox)); 
                if(i < children.size()-1){
            		html.append("},");
            	}else{
            		html.append("}");
            	}
            }  
            html.append("]");  
        }  
        return html.toString();
    }  
    /**
     * 获取子节点
     * @param nodes
     * @param node
     * @return
     */
    private static List<BaseZTreeEntity> getChildren(List<BaseZTreeEntity> list,BaseZTreeEntity node,boolean isHasChecbox){  
        List<BaseZTreeEntity> children = new ArrayList<BaseZTreeEntity>();  
        for(BaseZTreeEntity child : list) { 
        	if (node.getId().equals(child.getPid())) {  
                children.add(child);  
            }
        }  
        return children;  
    }
    
    /**
     * 判断是否存在子叶子
     * @param list
     * @param node
     * @return
     */
    public static String isLeaf(List<BaseZTreeEntity> list,BaseZTreeEntity node){
    	for(BaseZTreeEntity child : list) { 
        	if (node.getId().equals(child.getPid())) {  
        		return "false";
            }
        }  
    	return "true";
    }
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIntegerappend() {
		return integerappend;
	}
	public void setIntegerappend(String integerappend) {
		this.integerappend = integerappend;
	} 
}
