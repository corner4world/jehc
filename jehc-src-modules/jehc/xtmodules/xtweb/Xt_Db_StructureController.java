package jehc.xtmodules.xtweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_TableSize;
import jehc.xtmodules.xtservice.Xt_Db_StructureService;
/**
 * 数据库表
 * @author 邓纯杰
 */
@Controller
@RequestMapping("/xtDbStructureController")
public class Xt_Db_StructureController extends BaseAction{
	@Autowired
	private Xt_Db_StructureService xt_Db_StructureService;
	/**
	* 载入初始化页面
	* @param xt_functioninfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDbStructure",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDbStructure(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-db-structure/xt-db-structure-list");
	}
	
	/**
	 * 读取库中所有表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDbTableAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbTableAttribute(HttpServletRequest request) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dbName", CommonUtils.getXtConstantCache("XtDbname").getXt_constantValue());
		List<Xt_Db_TableAttribute> xt_Db_TableAttributeList = xt_Db_StructureService.getXtDbTableAttribute(condition);
		return outItemsStr(xt_Db_TableAttributeList);
	}
	
	/**
	 * 读取数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDbStructureByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbStructureByCondition(HttpServletRequest request){
		String tableName = request.getParameter("tableName");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", tableName);
		List<Xt_Db_Structure> xt_Db_StructureList = xt_Db_StructureService.getXtDbStructureByCondition(condition);
		return outItemsStr(xt_Db_StructureList);
	}
	/**
	 * 显示建表语句
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTablePhrases",method={RequestMethod.POST,RequestMethod.GET})
	public String getTablePhrases(HttpServletRequest request){
		String tableName = request.getParameter("tableName");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", tableName.toLowerCase());
		Xt_Db_Structure xt_Db_Structure = xt_Db_StructureService.getTablePhrases(condition);
		return outItemsStr(xt_Db_Structure);
	}
	
	/**
	 * 表索引
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDbTableIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbTableIndex(HttpServletRequest request){
		String tableName = request.getParameter("tableName");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", tableName.toLowerCase());
		List<Xt_Db_TableIndex> xt_Db_TableIndexList = xt_Db_StructureService.getXtDbTableIndex(condition);
		return outItemsStr(xt_Db_TableIndexList);
	}
	/**
	 * 表大小
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDbTableSize",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbTableSize(HttpServletRequest request){
		String tableName = request.getParameter("tableName");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", tableName.toLowerCase());
		condition.put("dbName", CommonUtils.getXtConstantCache("XtDbname").getXt_constantValue());
		Xt_Db_TableSize xt_Db_TableSize = xt_Db_StructureService.getXtDbTableSize(condition);
		return outDataStr(xt_Db_TableSize);
	}
	/**
	 * 创建索引
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addXtDbTableIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDbTableIndex(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		String tableName = request.getParameter("tableName"); 
		String indext_name = request.getParameter("indext_name");
		String type = request.getParameter("type");
		String field = request.getParameter("field");
		if(null != type && !"".equals(type)){
			if(type.equals("普通索引[INDEX]"))type="INDEX";;
			if(type.equals("唯一索引[UNIQUE]"))type="UNIQUE";
			if(type.equals("主键索引[PRIMARY KEY]"))type="PRIMARY KEY";;
			if(type.equals("全文索引[FULLTEXT]"))type="FULLTEXT";;
		}
		String sql = "ALTER TABLE "+tableName+" ADD "+type+" "+indext_name+" ("+field+")";
		condition.put("sql", sql);
		int i = xt_Db_StructureService.addXtDbTableIndex(condition);
		if(i > 0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 删除索引
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delXtDbTableIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDbTableIndex(HttpServletRequest request){
		String tableName = request.getParameter("tableName"); 
		String filedStr = request.getParameter("filedStr");
		String[] index_name = filedStr.split(",");
		for(int i = 0; i < index_name.length; i++){
			Map<String, Object> condition = new HashMap<String, Object>();
			String sql = "ALTER TABLE "+tableName+" DROP INDEX "+index_name[i]+"";
			condition.put("sql", sql);
			int j = xt_Db_StructureService.delXtDbTableIndex(condition);
			if(j > 0){
			}else{
				return outAudStr(false);
			}
		}
		return outAudStr(true);
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtDbStructure",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDbStructure(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
