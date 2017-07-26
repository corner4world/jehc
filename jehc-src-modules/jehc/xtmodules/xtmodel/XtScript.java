package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_script 平台脚本 
* 2016-06-14 15:08:50  邓纯杰
*/
public class XtScript extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_script_id;/**脚本编号**/
	private String xt_script_key;/**脚本键**/
	private String xt_script_text;/**脚本内容**/
	private String xt_script_type;/**脚本类型1javaScript2Sql3html4其他**/
	private String xt_script_status;/**状态0正常1禁用**/
	private String xt_script_ctime;/**创建时间**/
	private String xt_script_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_script_title;/**标题**/
	private String xt_script_content;/**描述**/
	private String xt_script_search_filed;/**是否参与代码生成器查询配置0是1否**/
	private String xt_script_valuefield;/**真实值**/
	private String xt_script_displayfield;/**显示值**/
	public void setXt_script_id(String xt_script_id){
		this.xt_script_id=xt_script_id;
	}
	public String getXt_script_id(){
		return xt_script_id;
	}
	public void setXt_script_key(String xt_script_key){
		this.xt_script_key=xt_script_key;
	}
	public String getXt_script_key(){
		return xt_script_key;
	}
	public void setXt_script_text(String xt_script_text){
		this.xt_script_text=xt_script_text;
	}
	public String getXt_script_text(){
		return xt_script_text;
	}
	public void setXt_script_type(String xt_script_type){
		this.xt_script_type=xt_script_type;
	}
	public String getXt_script_type(){
		return xt_script_type;
	}
	public void setXt_script_status(String xt_script_status){
		this.xt_script_status=xt_script_status;
	}
	public String getXt_script_status(){
		return xt_script_status;
	}
	public void setXt_script_ctime(String xt_script_ctime){
		this.xt_script_ctime=xt_script_ctime;
	}
	public String getXt_script_ctime(){
		return xt_script_ctime;
	}
	public void setXt_script_mtime(String xt_script_mtime){
		this.xt_script_mtime=xt_script_mtime;
	}
	public String getXt_script_mtime(){
		return xt_script_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_script_title(String xt_script_title){
		this.xt_script_title=xt_script_title;
	}
	public String getXt_script_title(){
		return xt_script_title;
	}
	public void setXt_script_content(String xt_script_content){
		this.xt_script_content=xt_script_content;
	}
	public String getXt_script_content(){
		return xt_script_content;
	}
	public void setXt_script_search_filed(String xt_script_search_filed){
		this.xt_script_search_filed=xt_script_search_filed;
	}
	public String getXt_script_search_filed(){
		return xt_script_search_filed;
	}
	public String getXt_script_valuefield() {
		return xt_script_valuefield;
	}
	public void setXt_script_valuefield(String xt_script_valuefield) {
		this.xt_script_valuefield = xt_script_valuefield;
	}
	public String getXt_script_displayfield() {
		return xt_script_displayfield;
	}
	public void setXt_script_displayfield(String xt_script_displayfield) {
		this.xt_script_displayfield = xt_script_displayfield;
	}
}
