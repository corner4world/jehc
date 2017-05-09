package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_encoderqrcode 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
public class Xt_Encoderqrcode extends Xt_Attachment implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_encoderqrcode_id;/**二维码编号**/
	private String xt_encoderqrcode_url;/**二维码链接地址**/
	private String xt_encoderqrcode_ctime;/**创建时间**/
	private String xt_encoderqrcode_mtime;/**修改时间**/
	private String xt_encoderqrcode_title;/**标题**/
	private String xt_encoderqrcode_content;/**备注**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_attachment_id;/**图片编号**/
	public String getXt_encoderqrcode_id() {
		return xt_encoderqrcode_id;
	}
	public void setXt_encoderqrcode_id(String xt_encoderqrcode_id) {
		this.xt_encoderqrcode_id = xt_encoderqrcode_id;
	}
	public String getXt_encoderqrcode_url() {
		return xt_encoderqrcode_url;
	}
	public void setXt_encoderqrcode_url(String xt_encoderqrcode_url) {
		this.xt_encoderqrcode_url = xt_encoderqrcode_url;
	}
	public String getXt_encoderqrcode_ctime() {
		return xt_encoderqrcode_ctime;
	}
	public void setXt_encoderqrcode_ctime(String xt_encoderqrcode_ctime) {
		this.xt_encoderqrcode_ctime = xt_encoderqrcode_ctime;
	}
	public String getXt_encoderqrcode_mtime() {
		return xt_encoderqrcode_mtime;
	}
	public void setXt_encoderqrcode_mtime(String xt_encoderqrcode_mtime) {
		this.xt_encoderqrcode_mtime = xt_encoderqrcode_mtime;
	}
	public String getXt_encoderqrcode_title() {
		return xt_encoderqrcode_title;
	}
	public void setXt_encoderqrcode_title(String xt_encoderqrcode_title) {
		this.xt_encoderqrcode_title = xt_encoderqrcode_title;
	}
	public String getXt_encoderqrcode_content() {
		return xt_encoderqrcode_content;
	}
	public void setXt_encoderqrcode_content(String xt_encoderqrcode_content) {
		this.xt_encoderqrcode_content = xt_encoderqrcode_content;
	}
	public String getXt_userinfo_id() {
		return xt_userinfo_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id) {
		this.xt_userinfo_id = xt_userinfo_id;
	}
	public String getXt_attachment_id() {
		return xt_attachment_id;
	}
	public void setXt_attachment_id(String xt_attachment_id) {
		this.xt_attachment_id = xt_attachment_id;
	}
	
}
