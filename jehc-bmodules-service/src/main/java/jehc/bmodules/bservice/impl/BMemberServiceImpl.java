package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BMemberDao;
import jehc.bmodules.bmodel.BMember;
import jehc.bmodules.bservice.BMemberService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础会员 
* 2016-01-08 22:35:33  邓纯杰
*/
@Service("bMemberService")
public class BMemberServiceImpl extends BaseService implements BMemberService{
	@Autowired
	private BMemberDao bMemberDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMember> getBMemberListByCondition(Map<String,Object> condition){
		try{
			return bMemberDao.getBMemberListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_member_id 
	* @return
	*/
	public BMember getBMemberById(String b_member_id){
		try{
			return bMemberDao.getBMemberById(b_member_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_member 
	* @return
	*/
	public int addBMember(BMember b_Member){
		int i = 0;
		try {
			i = bMemberDao.addBMember(b_Member);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_member 
	* @return
	*/
	public int updateBMember(BMember b_Member){
		int i = 0;
		try {
			i = bMemberDao.updateBMember(b_Member);
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
	public int delBMember(Map<String,Object> condition){
		int i = 0;
		try {
			i = bMemberDao.delBMember(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
