package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_knowledge 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
public class XtKnowledge extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_knowledge_id;/**ID**/
	private String xt_knowledge_title;/**标题**/
	private String xt_knowledge_content;/**内容**/
	private String xt_userinfo_id;/**创建者**/
	private String xt_type;/**0平台问题1学习知识**/
	private String xt_time;/**建创时间**/
	private String xt_uptime;/**更新时间**/
	private int xt_state;/**0待解决1已解决**/
	private int xt_level;/**1紧急2正常3一般**/
	public void setXt_knowledge_id(String xt_knowledge_id){
		this.xt_knowledge_id=xt_knowledge_id;
	}
	public String getXt_knowledge_id(){
		return xt_knowledge_id;
	}
	public void setXt_knowledge_title(String xt_knowledge_title){
		this.xt_knowledge_title=xt_knowledge_title;
	}
	public String getXt_knowledge_title(){
		return xt_knowledge_title;
	}
	public void setXt_knowledge_content(String xt_knowledge_content){
		this.xt_knowledge_content=xt_knowledge_content;
	}
	public String getXt_knowledge_content(){
		return xt_knowledge_content;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_type(String xt_type){
		this.xt_type=xt_type;
	}
	public String getXt_type(){
		return xt_type;
	}
	public void setXt_time(String xt_time){
		this.xt_time=xt_time;
	}
	public String getXt_time(){
		return xt_time;
	}
	public void setXt_uptime(String xt_uptime){
		this.xt_uptime=xt_uptime;
	}
	public String getXt_uptime(){
		return xt_uptime;
	}
	public void setXt_state(int xt_state){
		this.xt_state=xt_state;
	}
	public int getXt_state(){
		return xt_state;
	}
	public void setXt_level(int xt_level){
		this.xt_level=xt_level;
	}
	public int getXt_level(){
		return xt_level;
	}
}
