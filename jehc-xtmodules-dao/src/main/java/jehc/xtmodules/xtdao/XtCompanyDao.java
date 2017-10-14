package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtCompany;

/**
* 系统公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
public interface XtCompanyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtCompany> getXtCompanyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_company_id 
	* @return
	*/
	public XtCompany getXtCompanyById(String xt_company_id);
	/**
	* 添加
	* @param xt_company 
	* @return
	*/
	public int addXtCompany(XtCompany xt_Company);
	/**
	* 修改
	* @param xt_company 
	* @return
	*/
	public int updateXtCompany(XtCompany xt_Company);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtCompany(Map<String,Object> condition);
}
