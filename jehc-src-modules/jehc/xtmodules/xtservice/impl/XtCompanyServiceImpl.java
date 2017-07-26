package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtCompanyDao;
import jehc.xtmodules.xtmodel.XtCompany;
import jehc.xtmodules.xtservice.XtCompanyService;

/**
* 系统公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
@Service("xtCompanyService")
public class XtCompanyServiceImpl extends BaseService implements XtCompanyService{
	@Autowired
	private XtCompanyDao xtCompanyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtCompany> getXtCompanyListByCondition(Map<String,Object> condition){
		try {
			return xtCompanyDao.getXtCompanyListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_company_id 
	* @return
	*/
	public XtCompany getXtCompanyById(String xt_company_id){
		try {
			return xtCompanyDao.getXtCompanyById(xt_company_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_company 
	* @return
	*/
	public int addXtCompany(XtCompany xt_Company){
		int i = 0;
		try {
			i = xtCompanyDao.addXtCompany(xt_Company);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_company 
	* @return
	*/
	public int updateXtCompany(XtCompany xt_Company){
		int i = 0;
		try {
			i = xtCompanyDao.updateXtCompany(xt_Company);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtCompany(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtCompanyDao.delXtCompany(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
