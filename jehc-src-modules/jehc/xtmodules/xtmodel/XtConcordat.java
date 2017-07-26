package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_concordat 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
public class XtConcordat implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_concordat_id;/**合同编号**/
	private String xt_concordatName;/**合同名称**/
	private String xt_concordatDesc;/**合同描述**/
	public void setXt_concordat_id(String xt_concordat_id){
		this.xt_concordat_id=xt_concordat_id;
	}
	public String getXt_concordat_id(){
		return xt_concordat_id;
	}
	public void setXt_concordatName(String xt_concordatName){
		this.xt_concordatName=xt_concordatName;
	}
	public String getXt_concordatName(){
		return xt_concordatName;
	}
	public void setXt_concordatDesc(String xt_concordatDesc){
		this.xt_concordatDesc=xt_concordatDesc;
	}
	public String getXt_concordatDesc(){
		return xt_concordatDesc;
	}
}
