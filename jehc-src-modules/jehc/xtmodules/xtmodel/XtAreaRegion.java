package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_area_region 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
public class XtAreaRegion extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ID;/**ID编号**/
	private int PARENT_ID;/**父级ID**/
	private String CODE;/**政行编码**/
	private String NAME;/**名称**/
	private int REGION_LEVEL;/**行政级别**/
	private String NAME_EN;/**英文**/
	private double LONGITUDE;/**经度**/
	private double LATITUDE;/**纬度**/
	public void setID(String ID){
		this.ID=ID;
	}
	public String getID(){
		return ID;
	}
	public void setPARENT_ID(int PARENT_ID){
		this.PARENT_ID=PARENT_ID;
	}
	public int getPARENT_ID(){
		return PARENT_ID;
	}
	public void setCODE(String CODE){
		this.CODE=CODE;
	}
	public String getCODE(){
		return CODE;
	}
	public void setNAME(String NAME){
		this.NAME=NAME;
	}
	public String getNAME(){
		return NAME;
	}
	public void setREGION_LEVEL(int REGION_LEVEL){
		this.REGION_LEVEL=REGION_LEVEL;
	}
	public int getREGION_LEVEL(){
		return REGION_LEVEL;
	}
	public void setNAME_EN(String NAME_EN){
		this.NAME_EN=NAME_EN;
	}
	public String getNAME_EN(){
		return NAME_EN;
	}
	public void setLONGITUDE(double LONGITUDE){
		this.LONGITUDE=LONGITUDE;
	}
	public double getLONGITUDE(){
		return LONGITUDE;
	}
	public void setLATITUDE(double LATITUDE){
		this.LATITUDE=LATITUDE;
	}
	public double getLATITUDE(){
		return LATITUDE;
	}
}
