package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_CompanyDao;
import jehc.xtmodules.xtmodel.Xt_Company;
import jehc.xtmodules.xtservice.Xt_CompanyService;

/**
* 系统公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
@Service("xt_CompanyService")
public class Xt_CompanyServiceImpl extends BaseService implements Xt_CompanyService{
	@Autowired
	private Xt_CompanyDao xt_CompanyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Company> getXtCompanyListByCondition(Map<String,Object> condition){
		try {
			return xt_CompanyDao.getXtCompanyListByCondition(condition);
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
	public Xt_Company getXtCompanyById(String xt_company_id){
		try {
			return xt_CompanyDao.getXtCompanyById(xt_company_id);
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
	public int addXtCompany(Xt_Company xt_Company){
		int i = 0;
		try {
			i = xt_CompanyDao.addXtCompany(xt_Company);
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
	public int updateXtCompany(Xt_Company xt_Company){
		int i = 0;
		try {
			i = xt_CompanyDao.updateXtCompany(xt_Company);
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
			i = xt_CompanyDao.delXtCompany(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
