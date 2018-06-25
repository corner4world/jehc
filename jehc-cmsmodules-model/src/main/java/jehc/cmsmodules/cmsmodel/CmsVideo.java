package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_video 内容发布平台视频 
* 2018-06-25 21:50:52  邓纯杰
*/
public class CmsVideo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_video_id;/**主键**/
	private String title;/**标题**/
	private String content;/**内容**/
	private String imgPath;/**图片**/
	private String videoPath;/**视频路径**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0正常1关闭**/
	public void setCms_video_id(String cms_video_id){
		this.cms_video_id=cms_video_id;
	}
	public String getCms_video_id(){
		return cms_video_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setImgPath(String imgPath){
		this.imgPath=imgPath;
	}
	public String getImgPath(){
		return imgPath;
	}
	public void setVideoPath(String videoPath){
		this.videoPath=videoPath;
	}
	public String getVideoPath(){
		return videoPath;
	}
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	public void setMtime(Date mtime){
		this.mtime=mtime;
	}
	public Date getMtime(){
		return mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
}
