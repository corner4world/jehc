package jehc.xtmodules.xtcore.base;

import java.io.Serializable;

/**
 * 基类JSON
 * @author 邓纯杰
 *
 */
public class BaseJson  implements Serializable{
	private static final long serialVersionUID = 7063637441613949288L;
	private Boolean success;/**成功标志**/
	private String msg;/**提示消息**/
	private String jsonID;/**临时ID**/
	private String jsonValue;/**临时值**/
	private String fileType;/**返回上传文件之后的类型**/
	
	public BaseJson(){}
	
	public BaseJson(Boolean success,String msg){
		this.success =success;
		this.msg =msg;
	}
	
	public BaseJson(String msg,String jsonID,String jsonValue,String fileType){
		this.msg =msg;
		this.jsonID =jsonID;
		this.jsonValue =jsonValue;
		this.fileType =fileType;
	}
	
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getJsonID() {
		return jsonID;
	}
	public void setJsonID(String jsonID) {
		this.jsonID = jsonID;
	}
	public String getJsonValue() {
		return jsonValue;
	}
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
