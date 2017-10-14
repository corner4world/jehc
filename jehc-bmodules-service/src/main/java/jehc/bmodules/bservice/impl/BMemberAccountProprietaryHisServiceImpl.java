package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BMemberAccountProprietaryHisDao;
import jehc.bmodules.bmodel.BMemberAccountProprietaryHis;
import jehc.bmodules.bservice.BMemberAccountProprietaryHisService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础专有账户充值记录 
* 2016-03-24 20:48:25  邓纯杰
*/
@Service("bMemberAccountProprietaryHisService")
public class BMemberAccountProprietaryHisServiceImpl extends BaseService implements BMemberAccountProprietaryHisService{
	@Autowired
	private BMemberAccountProprietaryHisDao bMemberAccountProprietaryHisDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccountProprietaryHis> getBMemberAccountProprietaryHisListByCondition(Map<String,Object> condition){
		try{
			return bMemberAccountProprietaryHisDao.getBMemberAccountProprietaryHisListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_member_account_proprietary_his_id 
	* @return
	*/
	public BMemberAccountProprietaryHis getBMemberAccountProprietaryHisById(String b_member_account_proprietary_his_id){
		try{
			return bMemberAccountProprietaryHisDao.getBMemberAccountProprietaryHisById(b_member_account_proprietary_his_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int addBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His){
		int i = 0;
		try {
			i = bMemberAccountProprietaryHisDao.addBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int updateBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His){
		int i = 0;
		try {
			i = bMemberAccountProprietaryHisDao.updateBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
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
	public int delBMemberAccountProprietaryHis(Map<String,Object> condition){
		int i = 0;
		try {
			i = bMemberAccountProprietaryHisDao.delBMemberAccountProprietaryHis(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
