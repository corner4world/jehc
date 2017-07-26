package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_city 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
public class XtCity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_cityID;/**城市序列号**/
	private String xt_cityName;/**市城名称**/
	private String xt_cityUpID;/**上级编号**/
	private int xt_cityUpIdNum;/****/
	private String xt_cityPath;/**路径**/
	private String xt_cityType;/**类型**/
	private int xt_cityTypeNum;/****/
	private String xt_cityShortName;/**简称**/
	private String xt_citySpell;/**拼音**/
	private String xt_cityAreaID;/****/
	private String xt_cityPostCode;/**邮编**/
	public void setXt_cityID(String xt_cityID){
		this.xt_cityID=xt_cityID;
	}
	public String getXt_cityID(){
		return xt_cityID;
	}
	public void setXt_cityName(String xt_cityName){
		this.xt_cityName=xt_cityName;
	}
	public String getXt_cityName(){
		return xt_cityName;
	}
	public void setXt_cityUpID(String xt_cityUpID){
		this.xt_cityUpID=xt_cityUpID;
	}
	public String getXt_cityUpID(){
		return xt_cityUpID;
	}
	public void setXt_cityUpIdNum(int xt_cityUpIdNum){
		this.xt_cityUpIdNum=xt_cityUpIdNum;
	}
	public int getXt_cityUpIdNum(){
		return xt_cityUpIdNum;
	}
	public void setXt_cityPath(String xt_cityPath){
		this.xt_cityPath=xt_cityPath;
	}
	public String getXt_cityPath(){
		return xt_cityPath;
	}
	public void setXt_cityType(String xt_cityType){
		this.xt_cityType=xt_cityType;
	}
	public String getXt_cityType(){
		return xt_cityType;
	}
	public void setXt_cityTypeNum(int xt_cityTypeNum){
		this.xt_cityTypeNum=xt_cityTypeNum;
	}
	public int getXt_cityTypeNum(){
		return xt_cityTypeNum;
	}
	public void setXt_cityShortName(String xt_cityShortName){
		this.xt_cityShortName=xt_cityShortName;
	}
	public String getXt_cityShortName(){
		return xt_cityShortName;
	}
	public void setXt_citySpell(String xt_citySpell){
		this.xt_citySpell=xt_citySpell;
	}
	public String getXt_citySpell(){
		return xt_citySpell;
	}
	public void setXt_cityAreaID(String xt_cityAreaID){
		this.xt_cityAreaID=xt_cityAreaID;
	}
	public String getXt_cityAreaID(){
		return xt_cityAreaID;
	}
	public void setXt_cityPostCode(String xt_cityPostCode){
		this.xt_cityPostCode=xt_cityPostCode;
	}
	public String getXt_cityPostCode(){
		return xt_cityPostCode;
	}
}
