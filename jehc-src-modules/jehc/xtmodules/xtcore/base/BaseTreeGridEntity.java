package jehc.xtmodules.xtcore.base;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeGrid封装树
 * @author邓纯杰
 *
 */
public class BaseTreeGridEntity {
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
    private ArrayList<BaseTreeGridEntity> children = new ArrayList<BaseTreeGridEntity>();/**子节点集合**/
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
	public ArrayList<BaseTreeGridEntity> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<BaseTreeGridEntity> children) {
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
//	/**
//	 * 格式化节点
//	 * @param list
//	 * @return
//	 */
//	public static List<BaseTreeGridEntity> formatTree(List<BaseTreeGridEntity> rootList,List<BaseTreeGridEntity> childList) {
//        List<BaseTreeGridEntity> treelist = new ArrayList<BaseTreeGridEntity>();//拼凑好的json格式的数据
//        List<BaseTreeGridEntity> parentnodes = new ArrayList<BaseTreeGridEntity>();//parentnodes存放所有的父节点
//        /**
//        if(list != null && list.size() > 0) {
//            root = list.get(0) ;
//            //循环遍历结果集树查询的所有节点
//            for(int i = 1; i < list.size(); i++) {
//                node = list.get(i);
//                if(node.getPid().equals(root.getId())){
//                    //为tree root 增加子节点
//                    parentnodes.add(node) ;
//                    root.getChildren().add(node) ;
//                }else{//获取root子节点的孩子节点
//                    getChildrenNodes(parentnodes, node);
//                    parentnodes.add(node) ;
//                }
//            }    
//        }
//        **/
//        for(int j = 0; j < rootList.size(); j++){
//        	BaseTreeGridEntity root = rootList.get(j) ;
//    		//循环遍历结果集树查询的所有节点
//            for(int i = 0; i < childList.size(); i++) {
//            	BaseTreeGridEntity node = childList.get(i);
//                if(node.getPid().equals(root.getId())){
//                    //为tree root 增加子节点
//                    //parentnodes.add(node);
////                	childList.remove(i);
//                	System.out.println("----------------------------------------------"+node.getText());
//                	node.setChildren(getChildrenNodes(childList,node));
//                    root.getChildren().add(node);
//                }
//                /**else{
//                	root.setChildren(getChildrenNodes(childList,node));
//                }
//                **/
//                /**
//                else{
//                	//获取root子节点的孩子节点
//                    parentnodes.add(node);
//                }
//                **/
//                //root.getChildren().add(getChildrenNodes(childList,node));
//            }  
//            treelist.add(root) ;
//    	}
//        return null;
//
//    }
//
//	/**
//	 * 获取子节点
//	 * @param parentnodes
//	 * @param node
//	 */
//    private static ArrayList<BaseTreeGridEntity> getChildrenNodes(List<BaseTreeGridEntity> parentnodes, BaseTreeGridEntity node) {
//    	ArrayList<BaseTreeGridEntity> treelist = new ArrayList<BaseTreeGridEntity>();//拼凑好的json格式的数据
//    	//循环遍历所有父节点和node进行匹配，确定父子关系
//        for(int i = parentnodes.size() - 1; i >= 0; i--) {
//        	BaseTreeGridEntity pnode = parentnodes.get(i);
//            //如果是父子关系，为父节点增加子节点，退出for循环
//            if(pnode.getPid().equals(node.getId())){
//            	System.out.println("-------------------"+pnode.getText());
//            	node.getChildren().add(pnode);
//            	parentnodes.remove(i);
//                treelist.add(node);
//            }
//            /**else{
//                //如果不是父子关系，删除父节点栈里当前的节点，
//                //继续此次循环，直到确定父子关系或不存在退出for循环
//                parentnodes.remove(i) ;
//            }
//            **/
//        }
//        return treelist;
//    }
    
//    public static String buildTree(List<BaseTreeGridEntity> list){  
//    	StringBuffer html = new StringBuffer();
//    	html.append("[");  
//        for(int i = 0; i < list.size(); i++) { 
//        	BaseTreeGridEntity node = list.get(i);
//            if ("0".equals(node.getPid())) {  
//            	html.append("{text:'" + node.getText() + "',id:'" + node.getId()+ "',checked:"+node.getChecked()+",singleClickExpand:"+node.getSingleClickExpand()+",pid:'"+node.getPid()+"',leaf:"+node.getLeaf()+",icon:'"+node.getIcon()+"',qtip:'"+node.getText()+"',expanded:"+node.getExpanded());  
//            	html.append(build(list,node));
//            	if(i < list.size()-1){
//            		html.append("},");
//            	}else{
//            		html.append("}");
//            	}
//            }  
//        }  
//        html.append("]");  
//        System.out.println(html.toString());
//        return html.toString();
//    }  
	  public static String buildTree(List<BaseTreeGridEntity> list,boolean isHasChecbox){  
    	StringBuffer html = new StringBuffer();
    	html.append("[");  
        for(int i = 0; i < list.size(); i++) { 
        	BaseTreeGridEntity node = list.get(i);
            if ("0".equals(node.getPid())) {  
            	if(isHasChecbox){
            		html.append("{text:'" + node.getText() + "',id:'" + node.getId()+ "',checked:"+node.getChecked()+",singleClickExpand:"+node.getSingleClickExpand()+",pid:'"+node.getPid()+"',leaf:"+isLeaf(list,node)+",icon:'"+node.getIcon()+"',qtip:'"+node.getText()+"',expanded:"+node.getExpanded()+",tempObject:'"+node.getTempObject()+"',content:'"+node.getContent()+"',integerappend:'"+node.getIntegerappend()+"'");
            	}else{
            		html.append("{text:'" + node.getText() + "',id:'" + node.getId()+ "',singleClickExpand:"+node.getSingleClickExpand()+",pid:'"+node.getPid()+"',leaf:"+isLeaf(list,node)+",icon:'"+node.getIcon()+"',qtip:'"+node.getText()+"',expanded:"+node.getExpanded()+",tempObject:'"+node.getTempObject()+"',content:'"+node.getContent()+"',integerappend:'"+node.getIntegerappend()+"'");
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
    private static String build(List<BaseTreeGridEntity> list,BaseTreeGridEntity node,boolean isHasChecbox){  
    	StringBuffer html = new StringBuffer();
    	List<BaseTreeGridEntity> children = getChildren(list,node,isHasChecbox);  
        if (!children.isEmpty() && children.size()>0) {
        	html.append(",children:[");  
        	for(int i = 0; i < children.size(); i++){
        		BaseTreeGridEntity child = children.get(i);
        		if(isHasChecbox){
        			//如果当前节点下面不存在节点则设置leaf为true 否则设置false
        			html.append("{text:'" + child.getText() + "',id:'" + child.getId()+ "',checked:"+child.getChecked()+",singleClickExpand:"+child.getSingleClickExpand()+",pid:'"+child.getPid()+"',leaf:"+isLeaf(list,child)+",icon:'"+child.getIcon()+"',qtip:'"+child.getText()+"',expanded:"+child.getExpanded()+",tempObject:'"+child.getTempObject()+"',content:'"+child.getContent()+"',integerappend:'"+child.getIntegerappend()+"'");   
        		}else{
        			//如果当前节点下面不存在节点则设置leaf为true 否则设置false
        			html.append("{text:'" + child.getText() + "',id:'" + child.getId()+ "',singleClickExpand:"+child.getSingleClickExpand()+",pid:'"+child.getPid()+"',leaf:"+isLeaf(list,child)+",icon:'"+child.getIcon()+"',qtip:'"+child.getText()+"',expanded:"+child.getExpanded()+",tempObject:'"+child.getTempObject()+"',content:'"+child.getContent()+"',integerappend:'"+child.getIntegerappend()+"'");   
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
    private static List<BaseTreeGridEntity> getChildren(List<BaseTreeGridEntity> list,BaseTreeGridEntity node,boolean isHasChecbox){  
        List<BaseTreeGridEntity> children = new ArrayList<BaseTreeGridEntity>();  
        for(BaseTreeGridEntity child : list) { 
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
    public static String isLeaf(List<BaseTreeGridEntity> list,BaseTreeGridEntity node){
    	for(BaseTreeGridEntity child : list) { 
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
