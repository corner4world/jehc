package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_data_dictionary 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
public class XtDataDictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_data_dictionary_id;/**主键**/
	private String xt_data_dictionary_name;/**数据字典名称**/
	private String xt_data_dictionary_pid;/**父编号**/
	private String xt_data_dictionary_remark;/**注备**/
	private String xt_data_dictionary_value;/**数值**/
	private int xt_data_dictionary_state;/**态状0启用1暂停**/
	private String xt_data_dictionary_soft;/**排序号**/
	public void setXt_data_dictionary_id(String xt_data_dictionary_id){
		this.xt_data_dictionary_id=xt_data_dictionary_id;
	}
	public String getXt_data_dictionary_id(){
		return xt_data_dictionary_id;
	}
	public void setXt_data_dictionary_name(String xt_data_dictionary_name){
		this.xt_data_dictionary_name=xt_data_dictionary_name;
	}
	public String getXt_data_dictionary_name(){
		return xt_data_dictionary_name;
	}
	public void setXt_data_dictionary_pid(String xt_data_dictionary_pid){
		this.xt_data_dictionary_pid=xt_data_dictionary_pid;
	}
	public String getXt_data_dictionary_pid(){
		return xt_data_dictionary_pid;
	}
	public void setXt_data_dictionary_remark(String xt_data_dictionary_remark){
		this.xt_data_dictionary_remark=xt_data_dictionary_remark;
	}
	public String getXt_data_dictionary_remark(){
		return xt_data_dictionary_remark;
	}
	public void setXt_data_dictionary_value(String xt_data_dictionary_value){
		this.xt_data_dictionary_value=xt_data_dictionary_value;
	}
	public String getXt_data_dictionary_value(){
		return xt_data_dictionary_value;
	}
	public void setXt_data_dictionary_state(int xt_data_dictionary_state){
		this.xt_data_dictionary_state=xt_data_dictionary_state;
	}
	public int getXt_data_dictionary_state(){
		return xt_data_dictionary_state;
	}
	public String getXt_data_dictionary_soft() {
		return xt_data_dictionary_soft;
	}
	public void setXt_data_dictionary_soft(String xt_data_dictionary_soft) {
		this.xt_data_dictionary_soft = xt_data_dictionary_soft;
	}
}
