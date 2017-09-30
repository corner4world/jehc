package jehc.xtmodules.xtcore.base;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity支持类
 * @author邓纯杰
 */
public class BaseEntity  implements Serializable{
	private Object item;
	private Long version;/**乐观锁使用**/ 
	private static final long serialVersionUID = 1L;
	private String xt_userinfo_realName;/**操作人名称**/
	private String jehcimg_base_url;/**图片工程资源统一URL**/
	private String jehcimg_base_path_url;/**图片路径统一URL+路径（完整路径）**/
	private String jehcsources_base_url;/**平台默认资源统一URL**/
	private String jehcsources_base_path_url;/**平台默认资源路径统一URL+路径（完整路径）**/
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getXt_userinfo_realName() {
		return xt_userinfo_realName;
	}

	public void setXt_userinfo_realName(String xt_userinfo_realName) {
		this.xt_userinfo_realName = xt_userinfo_realName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

	public String getJehcimg_base_url() {
		return jehcimg_base_url;
	}

	public void setJehcimg_base_url(String jehcimg_base_url) {
		this.jehcimg_base_url = jehcimg_base_url;
	}

	public String getJehcimg_base_path_url() {
		return jehcimg_base_path_url;
	}

	public void setJehcimg_base_path_url(String jehcimg_base_path_url) {
		this.jehcimg_base_path_url = jehcimg_base_path_url;
	}

	public String getJehcsources_base_url() {
		return jehcsources_base_url;
	}

	public void setJehcsources_base_url(String jehcsources_base_url) {
		this.jehcsources_base_url = jehcsources_base_url;
	}

	public String getJehcsources_base_path_url() {
		return jehcsources_base_path_url;
	}

	public void setJehcsources_base_path_url(String jehcsources_base_path_url) {
		this.jehcsources_base_path_url = jehcsources_base_path_url;
	}
	
}
