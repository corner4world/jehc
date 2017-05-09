package jehc.lcmodules.lcmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* lc_process 流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表） 
* 2016-11-22 10:16:39  邓纯杰
*/
public class Lc_Process extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lc_process_id;/**流程编号**/
	private String lc_process_path;/**打包后的ZIP路径**/
	private String lc_process_mtime;/**最后修改时间**/
	private String lc_process_ctime;/**创建时间**/
	private String xt_userinfo_id;/**创建人**/
	private String lc_process_uid;/**流程定义中id（uuid）**/
	private String lc_process_uk;/**流程uk（键）**/
	private String lc_process_bpmn;/**bpmn xml内容**/
	private String lc_process_title;/**流程标题**/
	private String lc_process_bpmn_path;/**bpmn文件路径**/
	private String lc_process_img_path;/**图片路径**/
	private String lc_process_status;/**0待发布1发布中2已关闭**/
	private String lc_process_mxgraphxml;/**mxgraphxml字符串**/
	private String lc_process_mxgraph_style;/**样式风格0直线1曲线**/
	private String lc_processment_isdelete;/**是否删除0正常1已删除**/
	private String xt_constant_id;/**常量编号**/
	private String lc_process_flag;/**标识:0通过平台设计器设计1通过上传部署**/
	private String xt_attachment;/**附件编号**/
	private String lc_process_remark;/**备注**/
	public void setLc_process_id(String lc_process_id){
		this.lc_process_id=lc_process_id;
	}
	public String getLc_process_id(){
		return lc_process_id;
	}
	public void setLc_process_path(String lc_process_path){
		this.lc_process_path=lc_process_path;
	}
	public String getLc_process_path(){
		return lc_process_path;
	}
	public void setLc_process_mtime(String lc_process_mtime){
		this.lc_process_mtime=lc_process_mtime;
	}
	public String getLc_process_mtime(){
		return lc_process_mtime;
	}
	public void setLc_process_ctime(String lc_process_ctime){
		this.lc_process_ctime=lc_process_ctime;
	}
	public String getLc_process_ctime(){
		return lc_process_ctime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setLc_process_uid(String lc_process_uid){
		this.lc_process_uid=lc_process_uid;
	}
	public String getLc_process_uid(){
		return lc_process_uid;
	}
	public void setLc_process_uk(String lc_process_uk){
		this.lc_process_uk=lc_process_uk;
	}
	public String getLc_process_uk(){
		return lc_process_uk;
	}
	public void setLc_process_bpmn(String lc_process_bpmn){
		this.lc_process_bpmn=lc_process_bpmn;
	}
	public String getLc_process_bpmn(){
		return lc_process_bpmn;
	}
	public void setLc_process_title(String lc_process_title){
		this.lc_process_title=lc_process_title;
	}
	public String getLc_process_title(){
		return lc_process_title;
	}
	public void setLc_process_bpmn_path(String lc_process_bpmn_path){
		this.lc_process_bpmn_path=lc_process_bpmn_path;
	}
	public String getLc_process_bpmn_path(){
		return lc_process_bpmn_path;
	}
	public void setLc_process_img_path(String lc_process_img_path){
		this.lc_process_img_path=lc_process_img_path;
	}
	public String getLc_process_img_path(){
		return lc_process_img_path;
	}
	public void setLc_process_status(String lc_process_status){
		this.lc_process_status=lc_process_status;
	}
	public String getLc_process_status(){
		return lc_process_status;
	}
	public void setLc_process_mxgraphxml(String lc_process_mxgraphxml){
		this.lc_process_mxgraphxml=lc_process_mxgraphxml;
	}
	public String getLc_process_mxgraphxml(){
		return lc_process_mxgraphxml;
	}
	public void setLc_process_mxgraph_style(String lc_process_mxgraph_style){
		this.lc_process_mxgraph_style=lc_process_mxgraph_style;
	}
	public String getLc_process_mxgraph_style(){
		return lc_process_mxgraph_style;
	}
	public void setLc_processment_isdelete(String lc_processment_isdelete){
		this.lc_processment_isdelete=lc_processment_isdelete;
	}
	public String getLc_processment_isdelete(){
		return lc_processment_isdelete;
	}
	public void setXt_constant_id(String xt_constant_id){
		this.xt_constant_id=xt_constant_id;
	}
	public String getXt_constant_id(){
		return xt_constant_id;
	}
	public void setLc_process_flag(String lc_process_flag){
		this.lc_process_flag=lc_process_flag;
	}
	public String getLc_process_flag(){
		return lc_process_flag;
	}
	public void setXt_attachment(String xt_attachment){
		this.xt_attachment=xt_attachment;
	}
	public String getXt_attachment(){
		return xt_attachment;
	}
	public void setLc_process_remark(String lc_process_remark){
		this.lc_process_remark=lc_process_remark;
	}
	public String getLc_process_remark(){
		return lc_process_remark;
	}
}
