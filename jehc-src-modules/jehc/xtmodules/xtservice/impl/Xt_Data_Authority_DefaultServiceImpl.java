package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.Xt_Data_Authority_DefaultService;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DefaultDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Default;

/**
* 数权限初始化默认设置 
* 2017-06-20 14:38:52  邓纯杰
*/
@Service("xt_Data_Authority_DefaultService")
public class Xt_Data_Authority_DefaultServiceImpl extends BaseService implements Xt_Data_Authority_DefaultService{
	@Autowired
	private Xt_Data_Authority_DefaultDao xt_Data_Authority_DefaultDao;
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority_Default> getXtDataAuthorityDefaultListByCondition(Map<String,Object> condition){
		try{
			return xt_Data_Authority_DefaultDao.getXtDataAuthorityDefaultListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_data_authority_default_id 
	* @return
	*/
	public Xt_Data_Authority_Default getXtDataAuthorityDefaultById(String xt_data_authority_default_id){
		try{
			Xt_Data_Authority_Default xt_Data_Authority_Default = xt_Data_Authority_DefaultDao.getXtDataAuthorityDefaultById(xt_data_authority_default_id);
			return xt_Data_Authority_Default;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_authority_default 
	* @return
	*/
	public int addXtDataAuthorityDefault(Xt_Data_Authority_Default xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.addXtDataAuthorityDefault(xt_Data_Authority_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefault(Xt_Data_Authority_Default xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.updateXtDataAuthorityDefault(xt_Data_Authority_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefaultBySelective(Xt_Data_Authority_Default xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.updateXtDataAuthorityDefaultBySelective(xt_Data_Authority_Default);
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
	public int delXtDataAuthorityDefault(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.delXtDataAuthorityDefault(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据情况删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDefaultAllByCondition(Map<String,Object> condition){
		int i = 0;
		try {
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			xt_Data_Authority_DefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int addBatchXtDataAuthorityDefault(List<Xt_Data_Authority_Default> xt_Data_Authority_DefaultList,String xt_menuinfo_id){
		int i = 0;
		try {
			//1删除 原先
			Map<String,Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xt_Data_Authority_DefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
			//2添加 最新
			if(null != xt_Data_Authority_DefaultList){
				xt_Data_Authority_DefaultDao.addBatchXtDataAuthorityDefault(xt_Data_Authority_DefaultList);
			}
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefault(List<Xt_Data_Authority_Default> xt_Data_Authority_DefaultList){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.updateBatchXtDataAuthorityDefault(xt_Data_Authority_DefaultList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefaultBySelective(List<Xt_Data_Authority_Default> xt_Data_Authority_DefaultList){
		int i = 0;
		try {
			i = xt_Data_Authority_DefaultDao.updateBatchXtDataAuthorityDefaultBySelective(xt_Data_Authority_DefaultList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
