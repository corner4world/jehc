package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BMemberAccountProprietaryDao;
import jehc.bmodules.bmodel.BMemberAccountProprietary;
import jehc.bmodules.bservice.BMemberAccountProprietaryService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础专有账户 
* 2016-03-24 20:33:38  邓纯杰
*/
@Service("bMemberAccountProprietaryService")
public class BMemberAccountProprietaryServiceImpl extends BaseService implements BMemberAccountProprietaryService{
	@Autowired
	private BMemberAccountProprietaryDao bMemberAccountProprietaryDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccountProprietary> getBMemberAccountProprietaryListByCondition(Map<String,Object> condition){
		try{
			return bMemberAccountProprietaryDao.getBMemberAccountProprietaryListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象 会员编号 或者专有账户编号
	* @param condition 
	* @return
	*/
	public BMemberAccountProprietary getBMemberAccountProprietaryById(Map<String, Object> condition){
		try{
			return bMemberAccountProprietaryDao.getBMemberAccountProprietaryById(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_member_account_proprietary 
	* @return
	*/
	public int addBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary){
		int i = 0;
		try {
			i = bMemberAccountProprietaryDao.addBMemberAccountProprietary(b_Member_Account_Proprietary);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_member_account_proprietary 
	* @return
	*/
	public int updateBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary){
		int i = 0;
		try {
			i = bMemberAccountProprietaryDao.updateBMemberAccountProprietary(b_Member_Account_Proprietary);
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
	public int delBMemberAccountProprietary(Map<String,Object> condition){
		int i = 0;
		try {
			i = bMemberAccountProprietaryDao.delBMemberAccountProprietary(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
