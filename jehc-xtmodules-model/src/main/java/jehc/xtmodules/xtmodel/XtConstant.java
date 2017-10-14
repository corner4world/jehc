package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_constant 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
public class XtConstant implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_constant_id;/**编号**/
	private String xt_constantValue;/****/
	private int xt_constantType;/**0平台常量1业务常量2工作流常量**/
	private String xt_constantRemark;/**述描**/
	private String xt_constantName;/**常量名称**/
	private String xt_constantURL;/**流程常量URL可缺省**/
	public void setXt_constant_id(String xt_constant_id){
		this.xt_constant_id=xt_constant_id;
	}
	public String getXt_constant_id(){
		return xt_constant_id;
	}
	public void setXt_constantValue(String xt_constantValue){
		this.xt_constantValue=xt_constantValue;
	}
	public String getXt_constantValue(){
		return xt_constantValue;
	}
	public void setXt_constantType(int xt_constantType){
		this.xt_constantType=xt_constantType;
	}
	public int getXt_constantType(){
		return xt_constantType;
	}
	public void setXt_constantRemark(String xt_constantRemark){
		this.xt_constantRemark=xt_constantRemark;
	}
	public String getXt_constantRemark(){
		return xt_constantRemark;
	}
	public void setXt_constantName(String xt_constantName){
		this.xt_constantName=xt_constantName;
	}
	public String getXt_constantName(){
		return xt_constantName;
	}
	public void setXt_constantURL(String xt_constantURL){
		this.xt_constantURL=xt_constantURL;
	}
	public String getXt_constantURL(){
		return xt_constantURL;
	}
}
