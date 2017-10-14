package jehc.xtmodules.xtcore.base;
/**
 * 基类JSON
 * @author 邓纯杰
 *
 */
public class BaseJson {
	private String msg;/**提示消息**/
	private String jsonID;/**临时ID**/
	private String jsonValue;/**临时值**/
	private String fileType;/**返回上传文件之后的类型**/
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
