package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_product 基础_商品 
* 2016-01-08 21:21:26  邓纯杰
*/
public class BProduct extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_product_id;/**产品编号**/
	private String b_product_name;/**产品名称**/
	private String b_category_id;/**分类编号**/
	private String b_brand_id;/**品牌编号**/
	private String b_product_barcode;/**条形码**/
	private String b_product_code;/**商品货号**/
	private String b_product_qr_code;/**商品二维码**/
	private String b_product_status;/**0可用1禁用**/
	private String b_product_ctime;/**创建时间**/
	private String b_product_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String b_product_model;/**商品型号**/
	private String b_product_model_name;/**商品型号名称**/
	private String b_product_color;/**商品颜色**/
	private String b_product_features;/**商品功能简介**/
	private String b_product_origin;/**产地**/
	private String b_product_qualitylevel;/**质量等级**/
	private double b_product_gross_weight;/**商品毛重**/
	private double b_product_net_weight;/**商品净重**/
	private int b_product_size_length;/**商品尺寸长**/
	private int b_product_size_width;/**商品尺寸宽**/
	private int b_product_size_height;/**商品尺寸高**/
	
	private String b_category_name;/**分类名称**/
	private String b_brand_name;/**品牌名称**/
	public void setB_product_id(String b_product_id){
		this.b_product_id=b_product_id;
	}
	public String getB_product_id(){
		return b_product_id;
	}
	public void setB_product_name(String b_product_name){
		this.b_product_name=b_product_name;
	}
	public String getB_product_name(){
		return b_product_name;
	}
	public void setB_category_id(String b_category_id){
		this.b_category_id=b_category_id;
	}
	public String getB_category_id(){
		return b_category_id;
	}
	public void setB_brand_id(String b_brand_id){
		this.b_brand_id=b_brand_id;
	}
	public String getB_brand_id(){
		return b_brand_id;
	}
	public void setB_product_barcode(String b_product_barcode){
		this.b_product_barcode=b_product_barcode;
	}
	public String getB_product_barcode(){
		return b_product_barcode;
	}
	public void setB_product_code(String b_product_code){
		this.b_product_code=b_product_code;
	}
	public String getB_product_code(){
		return b_product_code;
	}
	public void setB_product_qr_code(String b_product_qr_code){
		this.b_product_qr_code=b_product_qr_code;
	}
	public String getB_product_qr_code(){
		return b_product_qr_code;
	}
	public void setB_product_status(String b_product_status){
		this.b_product_status=b_product_status;
	}
	public String getB_product_status(){
		return b_product_status;
	}
	public void setB_product_ctime(String b_product_ctime){
		this.b_product_ctime=b_product_ctime;
	}
	public String getB_product_ctime(){
		return b_product_ctime;
	}
	public void setB_product_mtime(String b_product_mtime){
		this.b_product_mtime=b_product_mtime;
	}
	public String getB_product_mtime(){
		return b_product_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_product_model(String b_product_model){
		this.b_product_model=b_product_model;
	}
	public String getB_product_model(){
		return b_product_model;
	}
	public void setB_product_model_name(String b_product_model_name){
		this.b_product_model_name=b_product_model_name;
	}
	public String getB_product_model_name(){
		return b_product_model_name;
	}
	public void setB_product_color(String b_product_color){
		this.b_product_color=b_product_color;
	}
	public String getB_product_color(){
		return b_product_color;
	}
	public void setB_product_features(String b_product_features){
		this.b_product_features=b_product_features;
	}
	public String getB_product_features(){
		return b_product_features;
	}
	public void setB_product_origin(String b_product_origin){
		this.b_product_origin=b_product_origin;
	}
	public String getB_product_origin(){
		return b_product_origin;
	}
	public void setB_product_qualitylevel(String b_product_qualitylevel){
		this.b_product_qualitylevel=b_product_qualitylevel;
	}
	public String getB_product_qualitylevel(){
		return b_product_qualitylevel;
	}
	public void setB_product_gross_weight(double b_product_gross_weight){
		this.b_product_gross_weight=b_product_gross_weight;
	}
	public double getB_product_gross_weight(){
		return b_product_gross_weight;
	}
	public void setB_product_net_weight(double b_product_net_weight){
		this.b_product_net_weight=b_product_net_weight;
	}
	public double getB_product_net_weight(){
		return b_product_net_weight;
	}
	public void setB_product_size_length(int b_product_size_length){
		this.b_product_size_length=b_product_size_length;
	}
	public int getB_product_size_length(){
		return b_product_size_length;
	}
	public void setB_product_size_width(int b_product_size_width){
		this.b_product_size_width=b_product_size_width;
	}
	public int getB_product_size_width(){
		return b_product_size_width;
	}
	public void setB_product_size_height(int b_product_size_height){
		this.b_product_size_height=b_product_size_height;
	}
	public int getB_product_size_height(){
		return b_product_size_height;
	}
	public String getB_category_name() {
		return b_category_name;
	}
	public void setB_category_name(String b_category_name) {
		this.b_category_name = b_category_name;
	}
	public String getB_brand_name() {
		return b_brand_name;
	}
	public void setB_brand_name(String b_brand_name) {
		this.b_brand_name = b_brand_name;
	}
}
