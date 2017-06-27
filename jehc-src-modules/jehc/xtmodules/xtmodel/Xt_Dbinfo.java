package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_dbinfo 数据库信息表 
* 2015-05-24 08:13:15  邓纯杰
*/
public class Xt_Dbinfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_dbinfo_id;/**数据库信息表**/
	private String xt_dbinfoName;/**据库数名称**/
	private String xt_dbinfoUName;/**数据库用户名**/
	private String xt_dbinfoPwd;/**据库数密码**/
	private String xt_dbinfoIp;/**份备IP**/
	private String xt_dbinfoPort;/**端口号**/
	private String xt_dbinfoType;/**数据库类型**/
	public void setXt_dbinfo_id(String xt_dbinfo_id){
		this.xt_dbinfo_id=xt_dbinfo_id;
	}
	public String getXt_dbinfo_id(){
		return xt_dbinfo_id;
	}
	public void setXt_dbinfoName(String xt_dbinfoName){
		this.xt_dbinfoName=xt_dbinfoName;
	}
	public String getXt_dbinfoName(){
		return xt_dbinfoName;
	}
	public void setXt_dbinfoUName(String xt_dbinfoUName){
		this.xt_dbinfoUName=xt_dbinfoUName;
	}
	public String getXt_dbinfoUName(){
		return xt_dbinfoUName;
	}
	public void setXt_dbinfoPwd(String xt_dbinfoPwd){
		this.xt_dbinfoPwd=xt_dbinfoPwd;
	}
	public String getXt_dbinfoPwd(){
		return xt_dbinfoPwd;
	}
	public void setXt_dbinfoIp(String xt_dbinfoIp){
		this.xt_dbinfoIp=xt_dbinfoIp;
	}
	public String getXt_dbinfoIp(){
		return xt_dbinfoIp;
	}
	public void setXt_dbinfoPort(String xt_dbinfoPort){
		this.xt_dbinfoPort=xt_dbinfoPort;
	}
	public String getXt_dbinfoPort(){
		return xt_dbinfoPort;
	}
	public String getXt_dbinfoType() {
		return xt_dbinfoType;
	}
	public void setXt_dbinfoType(String xt_dbinfoType) {
		this.xt_dbinfoType = xt_dbinfoType;
	}
	
	
}
