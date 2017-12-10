package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_path 文件路径设置 
* 2015-05-15 15:51:40  邓纯杰
*/
public class XtPath extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -1466479389299512377L;  
	private String xt_path_id;/**ID**/
	private String xt_path_name;/**称名**/
	private String xt_path;/**径路**/
	private String xt_value;/**量常值唯一**/
	private String xt_type;/**0系统模块1业务模块**/
	private String xt_time;/**建创时间**/
	public void setXt_path_id(String xt_path_id){
		this.xt_path_id=xt_path_id;
	}
	public String getXt_path_id(){
		return xt_path_id;
	}
	public void setXt_path_name(String xt_path_name){
		this.xt_path_name=xt_path_name;
	}
	public String getXt_path_name(){
		return xt_path_name;
	}
	public void setXt_path(String xt_path){
		this.xt_path=xt_path;
	}
	public String getXt_path(){
		return xt_path;
	}
	public void setXt_value(String xt_value){
		this.xt_value=xt_value;
	}
	public String getXt_value(){
		return xt_value;
	}
	public void setXt_type(String xt_type){
		this.xt_type=xt_type;
	}
	public String getXt_type(){
		return xt_type;
	}
	public void setXt_time(String xt_time){
		if(!StringUtils.isEmpty(xt_time)){
			this.xt_time=xt_time;
		}
	}
	public String getXt_time(){
		return xt_time;
	}
}
