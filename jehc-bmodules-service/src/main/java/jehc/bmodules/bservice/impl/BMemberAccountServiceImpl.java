package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BMemberAccountDao;
import jehc.bmodules.bmodel.BMemberAccount;
import jehc.bmodules.bservice.BMemberAccountService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础会员余额账户 
* 2016-03-24 20:30:14  邓纯杰
*/
@Service("bMemberAccountService")
public class BMemberAccountServiceImpl extends BaseService implements BMemberAccountService{
	@Autowired
	private BMemberAccountDao bMemberAccountDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccount> getBMemberAccountListByCondition(Map<String,Object> condition){
		try{
			return bMemberAccountDao.getBMemberAccountListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_member_account_id 
	* @return
	*/
	public BMemberAccount getBMemberAccountById(String b_member_account_id){
		try{
			return bMemberAccountDao.getBMemberAccountById(b_member_account_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_member_account 
	* @return
	*/
	public int addBMemberAccount(BMemberAccount b_Member_Account){
		int i = 0;
		try {
			i = bMemberAccountDao.addBMemberAccount(b_Member_Account);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_member_account 
	* @return
	*/
	public int updateBMemberAccount(BMemberAccount b_Member_Account){
		int i = 0;
		try {
			i = bMemberAccountDao.updateBMemberAccount(b_Member_Account);
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
	public int delBMemberAccount(Map<String,Object> condition){
		int i = 0;
		try {
			i = bMemberAccountDao.delBMemberAccount(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
