package jehc.xtmodules.xtcore.base;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Entity支持类
 * @author邓纯杰
 */
public class BaseEntity  implements Serializable{
	private Object item;
	private Long version;/**乐观锁使用**/ 
	private static final long serialVersionUID = 1L;
	private String xt_userinfo_realName;/**操作人名称**/
	private String hsimg_base_url;/**图片工程资源统一URL**/
	private String hsimg_base_path_url;/**图片路径统一URL+路径（完整路径）**/
	private String hssources_base_url;/**平台默认资源统一URL**/
	private String hssources_base_path_url;/**平台默认资源路径统一URL+路径（完整路径）**/
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getHsimg_base_path_url() {
		return hsimg_base_path_url;
	}

	public void setHsimg_base_path_url(String hsimg_base_path_url) {
		this.hsimg_base_path_url = hsimg_base_path_url;
	}

	public String getXt_userinfo_realName() {
		return xt_userinfo_realName;
	}

	public void setXt_userinfo_realName(String xt_userinfo_realName) {
		this.xt_userinfo_realName = xt_userinfo_realName;
	}

	public String getHsimg_base_url() {
		return hsimg_base_url;
	}

	public void setHsimg_base_url(String hsimg_base_url) {
		this.hsimg_base_url = hsimg_base_url;
	}

	public String getHssources_base_url() {
		return hssources_base_url;
	}

	public void setHssources_base_url(String hssources_base_url) {
		this.hssources_base_url = hssources_base_url;
	}

	public String getHssources_base_path_url() {
		return hssources_base_path_url;
	}

	public void setHssources_base_path_url(String hssources_base_path_url) {
		this.hssources_base_path_url = hssources_base_path_url;
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
}
