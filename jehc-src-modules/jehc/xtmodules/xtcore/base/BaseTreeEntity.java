package jehc.xtmodules.xtcore.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 通用树实体类
 * @author邓纯杰
 *
 */
public class BaseTreeEntity implements Serializable {
	private static final long serialVersionUID = 7261712903068621559L;
	private String id;/**ID编号**/
	private String pid;/**父级编号**/
	private String text;/**显示值**/
	private String icon;/**图片**/
	private String qtip;/**提示**/
	private Boolean leaf;/**叶子**/
	private Boolean expanded = true;/**是否展开默认展开**/
    private String checked;/**是否支持全选**/
    private Boolean singleClickExpand = false;/**单击展开默认否false为双击节点展开 true单击节点展开**/
	private ArrayList<BaseTreeEntity> children = new ArrayList<BaseTreeEntity>();/**子节点集合**/
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
	public ArrayList<BaseTreeEntity> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<BaseTreeEntity> children) {
		this.children = children;
	}
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public Boolean getSingleClickExpand() {
		return singleClickExpand;
	}
	public void setSingleClickExpand(Boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}
//	/**
//	 * 格式化节点
//	 * @param list
//	 * @return
//	 */
//	public static List<BaseTreeEntity> formatTree(List<BaseTreeEntity> rootList,List<BaseTreeEntity> childList) {
//        List<BaseTreeEntity> treelist = new ArrayList<BaseTreeEntity>();//拼凑好的json格式的数据
//        List<BaseTreeEntity> parentnodes = new ArrayList<BaseTreeEntity>();//parentnodes存放所有的父节点
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
//        	BaseTreeEntity root = rootList.get(j) ;
//    		//循环遍历结果集树查询的所有节点
//            for(int i = 0; i < childList.size(); i++) {
//            	BaseTreeEntity node = childList.get(i);
//                if(node.getPid().equals(root.getId())){
//                    //为tree root 增加子节点
//                    parentnodes.add(node) ;
//                    root.getChildren().add(node) ;
//                }else{
//                	//获取root子节点的孩子节点
//                    getChildrenNodes(parentnodes, node);
//                    parentnodes.add(node) ;
//                }
//            }  
//            treelist.add(root) ;
//    	}
//        return treelist ;
//
//    }
//
//	/**
//	 * 获取子节点
//	 * @param parentnodes
//	 * @param node
//	 */
//    private static void getChildrenNodes(List<BaseTreeEntity> parentnodes, BaseTreeEntity node) {
//        //循环遍历所有父节点和node进行匹配，确定父子关系
//        for(int i = parentnodes.size() - 1; i >= 0; i--) {
//        	BaseTreeEntity pnode = parentnodes.get(i);
//            //如果是父子关系，为父节点增加子节点，退出for循环
//            if(pnode.getId().equals(node.getPid())){
//                pnode.getChildren().add(node) ;
//                return;
//            } else{
//                //如果不是父子关系，删除父节点栈里当前的节点，
//                //继续此次循环，直到确定父子关系或不存在退出for循环
//                parentnodes.remove(i) ;
//            }
//        }
//    }
	
	public static String buildTree(List<BaseTreeEntity> list){  
    	StringBuffer html = new StringBuffer();
    	html.append("[");  
        for(int i = 0; i < list.size(); i++) { 
        	BaseTreeEntity node = list.get(i);
            if ("0".equals(node.getPid())) {  
            	html.append("{text:'" + node.getText() + "',id:'" + node.getId()+ "',checked:"+node.getChecked()+",singleClickExpand:"+node.getSingleClickExpand()+",pid:'"+node.getPid()+"',leaf:"+node.getLeaf()+",icon:'"+node.getIcon()+"',qtip:'"+node.getText()+"',expanded:"+node.getExpanded());  
            	html.append(build(list,node));
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
    private static String build(List<BaseTreeEntity> list,BaseTreeEntity node){  
    	StringBuffer html = new StringBuffer();
    	List<BaseTreeEntity> children = getChildren(list,node);  
        if (!children.isEmpty()) {
        	html.append(",children:[");  
        	for(int i = 0; i < children.size(); i++){
        		BaseTreeEntity child = children.get(i);
            	html.append("{text:'" + child.getText() + "',id:'" + child.getId()+ "',checked:"+child.getChecked()+",singleClickExpand:"+child.getSingleClickExpand()+",pid:'"+child.getPid()+"',leaf:"+child.getLeaf()+",icon:'"+child.getIcon()+"',qtip:'"+child.getText()+"',expanded:"+child.getExpanded());   
            	html.append(build(list,child)); 
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
     * 
     * @param nodes
     * @param node
     * @return
     */
    private static List<BaseTreeEntity> getChildren(List<BaseTreeEntity> list,BaseTreeEntity node){  
        List<BaseTreeEntity> children = new ArrayList<BaseTreeEntity>();  
        for(BaseTreeEntity child : list) {  
            if (node.getId().equals(child.getPid())) {  
                children.add(child);  
            }  
        }  
        return children;  
    } 
}
