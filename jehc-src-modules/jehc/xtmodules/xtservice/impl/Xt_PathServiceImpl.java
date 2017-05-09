package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_PathDao;
import jehc.xtmodules.xtmodel.Xt_Path;
import jehc.xtmodules.xtservice.Xt_PathService;

/**
* 文件路径设置 
* 2015-05-15 14:55:22  邓纯杰
*/
@Service("xt_PathService")
public class Xt_PathServiceImpl extends BaseService implements Xt_PathService{
	@Autowired
	private Xt_PathDao xt_PathDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Path> getXtPathListByCondition(Map<String,Object> condition){
		try {
			return xt_PathDao.getXtPathListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_path_id 
	* @return
	*/
	public Xt_Path getXtPathById(String xt_path_id){
		try {
			return xt_PathDao.getXtPathById(xt_path_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_path 
	* @return
	*/
	public int addXtPath(Xt_Path xt_Path){
		int i = 0;
		try {
			i = xt_PathDao.addXtPath(xt_Path);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_path 
	* @return
	*/
	public int updateXtPath(Xt_Path xt_Path){
		int i = 0;
		try {
			i = xt_PathDao.updateXtPath(xt_Path);
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
	public int delXtPath(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_PathDao.delXtPath(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 查找所有平台路径
	 * @param condition
	 * @return
	 */
	public List<Xt_Path> getXtPathListAllByCondition(Map<String,Object> condition){
		try {
			return xt_PathDao.getXtPathListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
