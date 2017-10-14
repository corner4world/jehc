package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_attachment 附件管理 
* 2016-01-11 15:39:16  邓纯杰
*/
public class XtAttachment extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_attachment_id;/**附件编号**/
	private String xt_attachmentType;/**件文类型**/
	private String xt_attachmentCtime;/**文件上传时间**/
	private String xt_attachmentSize;/**件文大小**/
	private String xt_attachmentPath;/**文件相对路径**/
	private int xt_attachmentIsDelete;/**0正常1删除**/
	private String xt_attachmentName;/**附件名称**/
	private String xt_userinfo_id;/**传上者编号**/
	private String xt_modules_id;/**传上模块编号**/
	private int xt_modules_order;/**顺序**/
	private String xt_attachmentTitle;/**附件原名称**/
	private String xt_path_absolutek;/**平台路径配置中心键（自定义上传绝对路径使用）**/
	private String xt_path_relativek;/**平台路径配置中心键（自定义上传相对路径使用）**/
	private String xt_path_urlk;/**平台路径配置中心键（自定义上传路径 自定义URL地址）**/
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setXt_attachmentType(String xt_attachmentType){
		this.xt_attachmentType=xt_attachmentType;
	}
	public String getXt_attachmentType(){
		return xt_attachmentType;
	}
	public void setXt_attachmentCtime(String xt_attachmentCtime){
		this.xt_attachmentCtime=xt_attachmentCtime;
	}
	public String getXt_attachmentCtime(){
		return xt_attachmentCtime;
	}
	public void setXt_attachmentSize(String xt_attachmentSize){
		this.xt_attachmentSize=xt_attachmentSize;
	}
	public String getXt_attachmentSize(){
		return xt_attachmentSize;
	}
	public void setXt_attachmentPath(String xt_attachmentPath){
		this.xt_attachmentPath=xt_attachmentPath;
	}
	public String getXt_attachmentPath(){
		return xt_attachmentPath;
	}
	public void setXt_attachmentIsDelete(int xt_attachmentIsDelete){
		this.xt_attachmentIsDelete=xt_attachmentIsDelete;
	}
	public int getXt_attachmentIsDelete(){
		return xt_attachmentIsDelete;
	}
	public void setXt_attachmentName(String xt_attachmentName){
		this.xt_attachmentName=xt_attachmentName;
	}
	public String getXt_attachmentName(){
		return xt_attachmentName;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_modules_id(String xt_modules_id){
		this.xt_modules_id=xt_modules_id;
	}
	public String getXt_modules_id(){
		return xt_modules_id;
	}
	public void setXt_modules_order(int xt_modules_order){
		this.xt_modules_order=xt_modules_order;
	}
	public int getXt_modules_order(){
		return xt_modules_order;
	}
	public void setXt_attachmentTitle(String xt_attachmentTitle){
		this.xt_attachmentTitle=xt_attachmentTitle;
	}
	public String getXt_attachmentTitle(){
		return xt_attachmentTitle;
	}
	public String getXt_path_absolutek() {
		return xt_path_absolutek;
	}
	public void setXt_path_absolutek(String xt_path_absolutek) {
		this.xt_path_absolutek = xt_path_absolutek;
	}
	public String getXt_path_urlk() {
		return xt_path_urlk;
	}
	public void setXt_path_urlk(String xt_path_urlk) {
		this.xt_path_urlk = xt_path_urlk;
	}
	public String getXt_path_relativek() {
		return xt_path_relativek;
	}
	public void setXt_path_relativek(String xt_path_relativek) {
		this.xt_path_relativek = xt_path_relativek;
	}
}
