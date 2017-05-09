package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_district 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
public class Xt_District implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_districtID;/**区县列序号**/
	private String xt_districtName;/**县区名称**/
	private String xt_districtUpID;/**级上编号**/
	private int xt_districtUpIdNum;/****/
	private String xt_districtPath;/**路径**/
	private String xt_districtType;/**类型**/
	private int xt_districtTypeNum;/****/
	private String xt_districtShortName;/**简写**/
	private String xt_districtSpell;/**拼音**/
	private String xt_districtAreaId;/****/
	private String xt_districtPostCode;/**邮编**/
	public void setXt_districtID(String xt_districtID){
		this.xt_districtID=xt_districtID;
	}
	public String getXt_districtID(){
		return xt_districtID;
	}
	public void setXt_districtName(String xt_districtName){
		this.xt_districtName=xt_districtName;
	}
	public String getXt_districtName(){
		return xt_districtName;
	}
	public void setXt_districtUpID(String xt_districtUpID){
		this.xt_districtUpID=xt_districtUpID;
	}
	public String getXt_districtUpID(){
		return xt_districtUpID;
	}
	public void setXt_districtUpIdNum(int xt_districtUpIdNum){
		this.xt_districtUpIdNum=xt_districtUpIdNum;
	}
	public int getXt_districtUpIdNum(){
		return xt_districtUpIdNum;
	}
	public void setXt_districtPath(String xt_districtPath){
		this.xt_districtPath=xt_districtPath;
	}
	public String getXt_districtPath(){
		return xt_districtPath;
	}
	public void setXt_districtType(String xt_districtType){
		this.xt_districtType=xt_districtType;
	}
	public String getXt_districtType(){
		return xt_districtType;
	}
	public void setXt_districtTypeNum(int xt_districtTypeNum){
		this.xt_districtTypeNum=xt_districtTypeNum;
	}
	public int getXt_districtTypeNum(){
		return xt_districtTypeNum;
	}
	public void setXt_districtShortName(String xt_districtShortName){
		this.xt_districtShortName=xt_districtShortName;
	}
	public String getXt_districtShortName(){
		return xt_districtShortName;
	}
	public void setXt_districtSpell(String xt_districtSpell){
		this.xt_districtSpell=xt_districtSpell;
	}
	public String getXt_districtSpell(){
		return xt_districtSpell;
	}
	public void setXt_districtAreaId(String xt_districtAreaId){
		this.xt_districtAreaId=xt_districtAreaId;
	}
	public String getXt_districtAreaId(){
		return xt_districtAreaId;
	}
	public void setXt_districtPostCode(String xt_districtPostCode){
		this.xt_districtPostCode=xt_districtPostCode;
	}
	public String getXt_districtPostCode(){
		return xt_districtPostCode;
	}
}
