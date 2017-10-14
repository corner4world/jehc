package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtPathDao;
import jehc.xtmodules.xtmodel.XtPath;
import jehc.xtmodules.xtservice.XtPathService;

/**
* 文件路径设置 
* 2015-05-15 14:55:22  邓纯杰
*/
@Service("xtPathService")
public class XtPathServiceImpl extends BaseService implements XtPathService{
	@Autowired
	private XtPathDao xtPathDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtPath> getXtPathListByCondition(Map<String,Object> condition){
		try {
			return xtPathDao.getXtPathListByCondition(condition);
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
	public XtPath getXtPathById(String xt_path_id){
		try {
			return xtPathDao.getXtPathById(xt_path_id);
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
	public int addXtPath(XtPath xt_Path){
		int i = 0;
		try {
			i = xtPathDao.addXtPath(xt_Path);
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
	public int updateXtPath(XtPath xt_Path){
		int i = 0;
		try {
			i = xtPathDao.updateXtPath(xt_Path);
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
			i = xtPathDao.delXtPath(condition);
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
	public List<XtPath> getXtPathListAllByCondition(Map<String,Object> condition){
		try {
			return xtPathDao.getXtPathListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
