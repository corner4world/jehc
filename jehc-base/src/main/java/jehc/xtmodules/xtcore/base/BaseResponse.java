package jehc.xtmodules.xtcore.base;
/**
 * 采用对象返回结果
 * @author 邓纯杰
 *
 */
public class BaseResponse {
	private boolean isSucess = true;
	private String msg;
	private String code;
	private Object data;
	private boolean responseFlag;
	public boolean isSucess() {
		return isSucess;
	}
	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isResponseFlag() {
		return responseFlag;
	}
	public void setResponseFlag(boolean responseFlag) {
		this.responseFlag = responseFlag;
	}
	
}
