package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_warehouse_location 基础仓库库位 
* 2016-01-27 14:25:28  邓纯杰
*/
public class BWarehouseLocation extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_warehouse_location_id;/**仓库库位编号**/
	private String b_warehouse_location_name;/**仓库库位名称**/
	private String b_warehouse_location_space;/**仓库库位空间大小**/
	private double b_warehouse_location_width;/**宽度**/
	private double b_warehouse_location_height;/**高度**/
	private double b_warehouse_location_length;/**长度**/
	private String b_warehouse_id;/**仓库编号**/
	private String b_warehouse_name;/**仓库名称**/
	private String b_seller_name;/**商户、卖家名称**/
	public void setB_warehouse_location_id(String b_warehouse_location_id){
		this.b_warehouse_location_id=b_warehouse_location_id;
	}
	public String getB_warehouse_location_id(){
		return b_warehouse_location_id;
	}
	public void setB_warehouse_location_name(String b_warehouse_location_name){
		this.b_warehouse_location_name=b_warehouse_location_name;
	}
	public String getB_warehouse_location_name(){
		return b_warehouse_location_name;
	}
	public void setB_warehouse_location_space(String b_warehouse_location_space){
		this.b_warehouse_location_space=b_warehouse_location_space;
	}
	public String getB_warehouse_location_space(){
		return b_warehouse_location_space;
	}
	public void setB_warehouse_location_width(double b_warehouse_location_width){
		this.b_warehouse_location_width=b_warehouse_location_width;
	}
	public double getB_warehouse_location_width(){
		return b_warehouse_location_width;
	}
	public void setB_warehouse_location_height(double b_warehouse_location_height){
		this.b_warehouse_location_height=b_warehouse_location_height;
	}
	public double getB_warehouse_location_height(){
		return b_warehouse_location_height;
	}
	public void setB_warehouse_location_length(double b_warehouse_location_length){
		this.b_warehouse_location_length=b_warehouse_location_length;
	}
	public double getB_warehouse_location_length(){
		return b_warehouse_location_length;
	}
	public void setB_warehouse_id(String b_warehouse_id){
		this.b_warehouse_id=b_warehouse_id;
	}
	public String getB_warehouse_id(){
		return b_warehouse_id;
	}
	public String getB_warehouse_name() {
		return b_warehouse_name;
	}
	public void setB_warehouse_name(String b_warehouse_name) {
		this.b_warehouse_name = b_warehouse_name;
	}
	public String getB_seller_name() {
		return b_seller_name;
	}
	public void setB_seller_name(String b_seller_name) {
		this.b_seller_name = b_seller_name;
	}
}
