package jehc.crmmodules.crmmodel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* crm_customer 客户基础资料 
* 2018-06-27 13:45:48  邓纯杰
*/
public class CrmCustomer extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String customerId;/**Id主键**/
	private String name;/**客户名称**/
	private String shortName;/**客户简称**/
	private String provinceId;/**省份Id**/
	private String cityId;/**城市Id**/
	private String districtId;/**区县Id**/
	private String address;/**详细地址**/
	private int levelnum;/**客户等级如：1表示A ，2表示AA以此类推**/
	private String industryId;/**所属行业，注：数据字典读取**/
	private String scaleId;/**公司规模，注：数据字典读取**/
	private String ageScope;/**年龄结构，注：数据字典读取**/
	private String corporation;/**公司法人**/
	private String tel;/**公司电话**/
	private Date cdate;/**创建时间**/
	private Date mdate;/**最后修改时间**/
	private String createUser;/**创建人（客户所属人）**/
	private String modifyUser;/**最后修改人**/
	private String status;/**状态0潜在客户1正式客户**/
	private Date endTime;/**合同到期日期**/
	private List<CrmCustomerAttach> crmCustomerAttach;/**客户附件**/
	private String crmCustomerAttach_removed_flag;/**客户附件**/
	public void setCustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getCustomerId(){
		return customerId;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setShortName(String shortName){
		this.shortName=shortName;
	}
	public String getShortName(){
		return shortName;
	}
	public void setProvinceId(String provinceId){
		this.provinceId=provinceId;
	}
	public String getProvinceId(){
		return provinceId;
	}
	public void setCityId(String cityId){
		this.cityId=cityId;
	}
	public String getCityId(){
		return cityId;
	}
	public void setDistrictId(String districtId){
		this.districtId=districtId;
	}
	public String getDistrictId(){
		return districtId;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	
	public int getLevelnum() {
		return levelnum;
	}
	public void setLevelnum(int levelnum) {
		this.levelnum = levelnum;
	}
	public void setIndustryId(String industryId){
		this.industryId=industryId;
	}
	public String getIndustryId(){
		return industryId;
	}
	public void setScaleId(String scaleId){
		this.scaleId=scaleId;
	}
	public String getScaleId(){
		return scaleId;
	}
	public void setAgeScope(String ageScope){
		this.ageScope=ageScope;
	}
	public String getAgeScope(){
		return ageScope;
	}
	public void setCorporation(String corporation){
		this.corporation=corporation;
	}
	public String getCorporation(){
		return corporation;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setCdate(Date cdate){
		this.cdate=cdate;
	}
	public Date getCdate(){
		return cdate;
	}
	public void setMdate(Date mdate){
		this.mdate=mdate;
	}
	public Date getMdate(){
		return mdate;
	}
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setModifyUser(String modifyUser){
		this.modifyUser=modifyUser;
	}
	public String getModifyUser(){
		return modifyUser;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public List<CrmCustomerAttach> getCrmCustomerAttach() {
		return crmCustomerAttach;
	}
	public void setCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttach) {
		this.crmCustomerAttach = crmCustomerAttach;
	}
	public String getCrmCustomerAttach_removed_flag() {
		return crmCustomerAttach_removed_flag;
	}
	public void setCrmCustomerAttach_removed_flag(String crmCustomerAttach_removed_flag) {
		this.crmCustomerAttach_removed_flag = crmCustomerAttach_removed_flag;
	}
	
}
