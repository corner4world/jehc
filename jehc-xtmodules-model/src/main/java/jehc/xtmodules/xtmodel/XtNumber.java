package jehc.xtmodules.xtmodel;

import java.util.Date;
/**
 * 单号生成
 * @author 邓纯杰
 *
 */
public class XtNumber {
	private String xt_number_id;/**单号id**/	
	private int lastvalue;/**当前值**/
	private int version;/**最后版本号**/
	private Date createTime;/**创建时间**/
	private Date lastUpdateTime;/**最后修改时间**/
	private String modulesType;/**模块类型**/
	public String getXt_number_id() {
		return xt_number_id;
	}
	public void setXt_number_id(String xt_number_id) {
		this.xt_number_id = xt_number_id;
	}
	public int getLastvalue() {
		return lastvalue;
	}
	public void setLastvalue(int lastvalue) {
		this.lastvalue = lastvalue;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getModulesType() {
		return modulesType;
	}
	public void setModulesType(String modulesType) {
		this.modulesType = modulesType;
	}
	
}
