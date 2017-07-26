package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_province 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:37:07  邓纯杰
*/
public class XtProvince implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_provinceID;/**省份序列号**/
	private String xt_provinceName;/**省份名称**/
	private String xt_provinceUpID;/**上级编号**/
	private String xt_provinceUpIdNum;/****/
	private String xt_provincePath;/**路径**/
	private String xt_provinceType;/**型类**/
	private int xt_provinceTypeNum;/****/
	private String xt_provinceShortName;/**简称**/
	private String xt_provinceSpell;/**拼音**/
	private String xt_provinceAreaId;/****/
	private String xt_provincePostCode;/**邮编**/
	public void setXt_provinceID(String xt_provinceID){
		this.xt_provinceID=xt_provinceID;
	}
	public String getXt_provinceID(){
		return xt_provinceID;
	}
	public void setXt_provinceName(String xt_provinceName){
		this.xt_provinceName=xt_provinceName;
	}
	public String getXt_provinceName(){
		return xt_provinceName;
	}
	public void setXt_provinceUpID(String xt_provinceUpID){
		this.xt_provinceUpID=xt_provinceUpID;
	}
	public String getXt_provinceUpID(){
		return xt_provinceUpID;
	}
	public void setXt_provinceUpIdNum(String xt_provinceUpIdNum){
		this.xt_provinceUpIdNum=xt_provinceUpIdNum;
	}
	public String getXt_provinceUpIdNum(){
		return xt_provinceUpIdNum;
	}
	public void setXt_provincePath(String xt_provincePath){
		this.xt_provincePath=xt_provincePath;
	}
	public String getXt_provincePath(){
		return xt_provincePath;
	}
	public void setXt_provinceType(String xt_provinceType){
		this.xt_provinceType=xt_provinceType;
	}
	public String getXt_provinceType(){
		return xt_provinceType;
	}
	public void setXt_provinceTypeNum(int xt_provinceTypeNum){
		this.xt_provinceTypeNum=xt_provinceTypeNum;
	}
	public int getXt_provinceTypeNum(){
		return xt_provinceTypeNum;
	}
	public void setXt_provinceShortName(String xt_provinceShortName){
		this.xt_provinceShortName=xt_provinceShortName;
	}
	public String getXt_provinceShortName(){
		return xt_provinceShortName;
	}
	public void setXt_provinceSpell(String xt_provinceSpell){
		this.xt_provinceSpell=xt_provinceSpell;
	}
	public String getXt_provinceSpell(){
		return xt_provinceSpell;
	}
	public void setXt_provinceAreaId(String xt_provinceAreaId){
		this.xt_provinceAreaId=xt_provinceAreaId;
	}
	public String getXt_provinceAreaId(){
		return xt_provinceAreaId;
	}
	public void setXt_provincePostCode(String xt_provincePostCode){
		this.xt_provincePostCode=xt_provincePostCode;
	}
	public String getXt_provincePostCode(){
		return xt_provincePostCode;
	}
}
