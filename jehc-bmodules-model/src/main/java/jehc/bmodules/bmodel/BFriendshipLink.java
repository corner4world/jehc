package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_friendship_link 基础友情链接 
* 2016-01-10 12:35:06  邓纯杰
*/
public class BFriendshipLink extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_friendship_link_id;/**编号**/
	private String b_friendship_link_name;/**名称**/
	private String b_friendship_link_url;/**链接地址**/
	private String b_friendship_link_ctime;/**创建时间**/
	private String b_friendship_link_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String b_friendship_link_status;/**状态0正常1禁用**/
	private int b_friendship_link_sort;/**排序**/
	public void setB_friendship_link_id(String b_friendship_link_id){
		this.b_friendship_link_id=b_friendship_link_id;
	}
	public String getB_friendship_link_id(){
		return b_friendship_link_id;
	}
	public void setB_friendship_link_name(String b_friendship_link_name){
		this.b_friendship_link_name=b_friendship_link_name;
	}
	public String getB_friendship_link_name(){
		return b_friendship_link_name;
	}
	public void setB_friendship_link_url(String b_friendship_link_url){
		this.b_friendship_link_url=b_friendship_link_url;
	}
	public String getB_friendship_link_url(){
		return b_friendship_link_url;
	}
	public void setB_friendship_link_ctime(String b_friendship_link_ctime){
		this.b_friendship_link_ctime=b_friendship_link_ctime;
	}
	public String getB_friendship_link_ctime(){
		return b_friendship_link_ctime;
	}
	public void setB_friendship_link_mtime(String b_friendship_link_mtime){
		this.b_friendship_link_mtime=b_friendship_link_mtime;
	}
	public String getB_friendship_link_mtime(){
		return b_friendship_link_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_friendship_link_status(String b_friendship_link_status){
		this.b_friendship_link_status=b_friendship_link_status;
	}
	public String getB_friendship_link_status(){
		return b_friendship_link_status;
	}
	public void setB_friendship_link_sort(int b_friendship_link_sort){
		this.b_friendship_link_sort=b_friendship_link_sort;
	}
	public int getB_friendship_link_sort(){
		return b_friendship_link_sort;
	}
}
