package jehc.xtmodules.xtcore.base;
/**
 * Extjs HTML编辑器插入图片返回的内容
 * @author邓纯杰
 *
 */
public class BaseHtmlEditorImg {
	private String url;
	private String content;
	private int width;
	private int height;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
