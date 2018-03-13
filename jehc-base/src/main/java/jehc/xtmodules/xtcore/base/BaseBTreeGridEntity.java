package jehc.xtmodules.xtcore.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;

public class BaseBTreeGridEntity implements Serializable{
	private static final long serialVersionUID = 7261712903068621559L;
	private String id;/**ID编号**/
	private String pid;/**父级编号**/
	private String text;/**显示值**/
	private String icon;/**图片**/
	private String qtip;/**提示**/
	private Boolean leaf;/**叶子**/
	private String tempObject;/**临时值**/
	private String content;/**描述**/
	private String integerappend;/**int类型拼接使用可以使用@或逗号隔开**/
	private String buessid;/**业务id**/
	private ArrayList<BaseBTreeGridEntity> children = new ArrayList<BaseBTreeGridEntity>();/**子节点集合**/
	private Long idConvertInteger;/**将id为非整数类型动态转换为整型字段**/
	private Long pidConvertInteger;/**将pid为非整数类型动态转换为整型字段**/
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
	public ArrayList<BaseBTreeGridEntity> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<BaseBTreeGridEntity> children) {
		this.children = children;
	}
	public String getTempObject() {
		return tempObject;
	}
	public void setTempObject(String tempObject) {
		this.tempObject = tempObject;
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
	
	public String getBuessid() {
		return buessid;
	}
	public void setBuessid(String buessid) {
		this.buessid = buessid;
	}
	
	public Long getIdConvertInteger() {
		return idConvertInteger;
	}
	public void setIdConvertInteger(Long idConvertInteger) {
		this.idConvertInteger = idConvertInteger;
	}
	public Long getPidConvertInteger() {
		return pidConvertInteger;
	}
	public void setPidConvertInteger(Long pidConvertInteger) {
		this.pidConvertInteger = pidConvertInteger;
	}
	/**
	 * 返回JSON字符串
	 * @param list
	 * @return
	 */
	public static String buildTree(List<BaseBTreeGridEntity> list){  
		List<BaseBTreeGridEntity> BaseBTreeGridEntityList = new ArrayList<BaseBTreeGridEntity>();
        for(int i = 0; i < list.size(); i++) { 
        	BaseBTreeGridEntity node = list.get(i);
            if ("0".equals(node.getPid()) || StringUtils.isEmpty(node.getPid())) {  
            	node.setIdConvertInteger(new Long(i+1));//设置id转换器
            	node.setBuessid(node.getId());
            	node.setPid("0");
            	node.setPidConvertInteger(new Long(0));//设置pid转换器
            	BaseBTreeGridEntityList.addAll(build(list,node));
            	BaseBTreeGridEntityList.add(node);
            }
        }  
        
        //统一转换
        JSONArray jsonArray = new JSONArray();
        for(BaseBTreeGridEntity BaseBTreeGridEntity:BaseBTreeGridEntityList){
        	Map<String, Object> map = new HashMap<String,Object>();
        	map.put("id", BaseBTreeGridEntity.getIdConvertInteger());
        	map.put("pid", BaseBTreeGridEntity.getPidConvertInteger());
        	map.put("name", BaseBTreeGridEntity.getText());
        	map.put("tempObject", BaseBTreeGridEntity.getTempObject());
        	map.put("buessid", BaseBTreeGridEntity.getBuessid());
        	map.put("content", StringUtils.isEmpty(BaseBTreeGridEntity.getContent())?"":BaseBTreeGridEntity.getContent());
        	map.put("integerappend", StringUtils.isEmpty(BaseBTreeGridEntity.getIntegerappend())?"":BaseBTreeGridEntity.getIntegerappend());
        	jsonArray.add(map);
        }
        return  jsonArray.toString();
    } 
	/**
	 * 返回JSON字符串
	 * @param list
	 * @return
	 */
	public static String buildTreeF(List<BaseBTreeGridEntity> list){  
		List<BaseBTreeGridEntity> BaseBTreeGridEntityList = new ArrayList<BaseBTreeGridEntity>();
        for(int i = 0; i < list.size(); i++) { 
        	BaseBTreeGridEntity node = list.get(i);
            if ("0".equals(node.getPid()) || StringUtils.isEmpty(node.getPid())) {  
            	node.setIdConvertInteger(new Long(i+1));//设置id转换器
            	node.setBuessid(node.getId());
            	node.setPid("0");
            	node.setPidConvertInteger(new Long(0));//设置pid转换器
            	BaseBTreeGridEntityList.addAll(build(list,node));
            	BaseBTreeGridEntityList.add(node);
            }
        }  
        
        //统一转换
        JSONArray jsonArray = new JSONArray();
        for(BaseBTreeGridEntity BaseBTreeGridEntity:BaseBTreeGridEntityList){
        	Map<String, Object> map = new HashMap<String,Object>();
        	map.put("id", BaseBTreeGridEntity.getIdConvertInteger());
        	map.put("parentId", BaseBTreeGridEntity.getPidConvertInteger() == 0?null:BaseBTreeGridEntity.getPidConvertInteger());
        	map.put("name", BaseBTreeGridEntity.getText());
        	map.put("tempObject", BaseBTreeGridEntity.getTempObject());
        	map.put("buessid", BaseBTreeGridEntity.getBuessid());
        	map.put("content", StringUtils.isEmpty(BaseBTreeGridEntity.getContent())?"":BaseBTreeGridEntity.getContent());
        	map.put("integerappend", StringUtils.isEmpty(BaseBTreeGridEntity.getIntegerappend())?"":BaseBTreeGridEntity.getIntegerappend());
        	jsonArray.add(map);
        }
        return  jsonArray.toString();
    } 
    /**
     * 子节点遍历
     * @param childList
     * @param node
     */
    private static List<BaseBTreeGridEntity> build(List<BaseBTreeGridEntity> list,BaseBTreeGridEntity node){
    	 List<BaseBTreeGridEntity> children = new ArrayList<BaseBTreeGridEntity>(); 
         int size = list.size();
         for(int i = 0; i < size; i++){
         	BaseBTreeGridEntity child = list.get(i);
         	if (node.getId().equals(child.getPid())) {  
         		child.setBuessid(child.getId());
         		child.setPidConvertInteger(node.getIdConvertInteger());//设置pid转换器
         		child.setIdConvertInteger(new Long(node.getIdConvertInteger()+""+(i+1)));//设置id转换器
                children.add(child);  
                children.addAll(build(list, child));
             }
         }
         return children;  
    }  
}
