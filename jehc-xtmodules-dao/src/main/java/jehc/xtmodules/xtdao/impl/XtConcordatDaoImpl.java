package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtConcordatDao;
import jehc.xtmodules.xtmodel.XtConcordat;

/**
* 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
@Repository("xtConcordatDao")
public class XtConcordatDaoImpl  extends BaseDaoImpl implements XtConcordatDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtConcordat> getXtConcordatListByCondition(Map<String,Object> condition){
		return (List<XtConcordat>)this.getList("getXtConcordatListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_concordat_id 
	* @return
	*/
	public XtConcordat getXtConcordatById(String xt_concordat_id){
		return (XtConcordat)this.get("getXtConcordatById", xt_concordat_id);
	}
	/**
	* 添加
	* @param xt_concordat 
	* @return
	*/
	public int addXtConcordat(XtConcordat xt_Concordat){
		return this.add("addXtConcordat", xt_Concordat);
	}
	/**
	* 修改
	* @param xt_concordat 
	* @return
	*/
	public int updateXtConcordat(XtConcordat xt_Concordat){
		return this.update("updateXtConcordat", xt_Concordat);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtConcordat(Map<String,Object> condition){
		return this.del("delXtConcordat", condition);
	}
}
