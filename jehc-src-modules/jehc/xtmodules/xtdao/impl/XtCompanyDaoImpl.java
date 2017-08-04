package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtCompanyDao;
import jehc.xtmodules.xtmodel.XtCompany;

/**
* 系统公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
@Repository("xtCompanyDao")
public class XtCompanyDaoImpl  extends BaseDaoImpl implements XtCompanyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtCompany> getXtCompanyListByCondition(Map<String,Object> condition){
		return (List<XtCompany>)this.getList("getXtCompanyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_company_id 
	* @return
	*/
	public XtCompany getXtCompanyById(String xt_company_id){
		return (XtCompany)this.get("getXtCompanyById", xt_company_id);
	}
	/**
	* 添加
	* @param xt_company 
	* @return
	*/
	public int addXtCompany(XtCompany xt_Company){
		return this.add("addXtCompany", xt_Company);
	}
	/**
	* 修改
	* @param xt_company 
	* @return
	*/
	public int updateXtCompany(XtCompany xt_Company){
		return this.update("updateXtCompany", xt_Company);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtCompany(Map<String,Object> condition){
		return this.del("delXtCompany", condition);
	}
}
