package jehc.xtmodules.xtcore.base;

import java.io.Serializable;

/**
 * 错位对象存放位置
 * @author dengcj
 *
 */
public class BaseExceptionEntity implements Serializable{
	private static final long serialVersionUID = 623298376561808650L;
	private String xt_pt_status;/**错位状态**/
	private String xt_pt_code;/**错位码   默认 缺省**/
	private String xt_pt_error_msg;/**错位信息**/
	
	public BaseExceptionEntity(){}
	
	public BaseExceptionEntity(String xt_pt_status){
		this.xt_pt_status = xt_pt_status;
	}
	
	public BaseExceptionEntity(String xt_pt_status,String xt_pt_error_msg){
		this.xt_pt_status = xt_pt_status;
		this.xt_pt_error_msg = xt_pt_error_msg;
	}
	
	public BaseExceptionEntity(String xt_pt_status,String xt_pt_code,String xt_pt_error_msg){
		this.xt_pt_status = xt_pt_status;
		this.xt_pt_error_msg = xt_pt_error_msg;
		this.xt_pt_code = xt_pt_code;
	}
	public String getXt_pt_status() {
		return xt_pt_status;
	}
	public void setXt_pt_status(String xt_pt_status) {
		this.xt_pt_status = xt_pt_status;
	}
	public String getXt_pt_code() {
		return xt_pt_code;
	}
	public void setXt_pt_code(String xt_pt_code) {
		this.xt_pt_code = xt_pt_code;
	}
	public String getXt_pt_error_msg() {
		return xt_pt_error_msg;
	}
	public void setXt_pt_error_msg(String xt_pt_error_msg) {
		this.xt_pt_error_msg = xt_pt_error_msg;
	}
	
}
