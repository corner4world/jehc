package jehc.xtmodules.xtweb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.XtDbFun;
import jehc.xtmodules.xtmodel.XtDbProc;
import jehc.xtmodules.xtmodel.XtDbStructure;
import jehc.xtmodules.xtmodel.XtDbTableAttribute;
import jehc.xtmodules.xtmodel.XtDbTri;
import jehc.xtmodules.xtmodel.XtDbView;
import jehc.xtmodules.xtservice.XtFlexSearchService;
import jehc.xtmodules.xtservice.impl.XtFlexSearchServiceImpl;
/**
 * 查询工具
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtFlexSearchController")
@Scope("prototype")
public class XtFlexSearchController extends BaseAction {
	/**
	 * 载入查询工具页面
	 * @return
	 */
	@RequestMapping(value="/loadXtFlexSearchForSelDb",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFlexSearchForSelDb(){
		return new ModelAndView("pc/xt-view/xt-flexsearch/xt-flexsearch-forselDb");
	} 
	
	/**
	 * 载入查询工具页面
	 * @return
	 */
	@RequestMapping(value="/loadXtFlexSearch",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFlexSearch(String xt_dbinfo_id,Model model){
		if(StringUtil.isEmpty(xt_dbinfo_id)){
			throw new ExceptionUtil("未能获取到所选数据库编号（xt_dbinfo_id）");
		}
		model.addAttribute("xt_dbinfo_id", xt_dbinfo_id);
		return new ModelAndView("pc/xt-view/xt-flexsearch/xt-flexsearch");
	} 
	
	
	/**
	 * 载入Sql编辑器页面
	 * @return
	 */
	@RequestMapping(value="/loadXtFlexSqlEditor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFlexSqlEditor(){
		return new ModelAndView("pc/xt-view/xt-flexsearch/xt-flexsearchSqlEditor");
	} 
	
	/**
	 * 执行查询语句
	 */
	@ResponseBody
	@RequestMapping(value="/flexQuery",method={RequestMethod.POST,RequestMethod.GET})
	public String flexQuery(String sqlType,String sql,String xt_dbinfo_id){
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		int i = validateSQL(sqlType,sql);
		String json = "";
		if(i == 1){
			json = xtFlexSearchService.getXtFlexSearchListQuery(sql, null,xt_dbinfo_id);
		}else if(i == 2){
			json = xtFlexSearchService.executeUpdate(sql, null,xt_dbinfo_id);
		}
		return outStr(json);
	}
	
	/**
	 * 读取所有表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFlexSearchTablesTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFlexSearchTablesTree(HttpServletRequest request,String xt_dbinfo_id, HttpServletResponse response){
		String id = request.getParameter("id");
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		List<XtDbTableAttribute> xt_Db_TableAttributeList = xtFlexSearchService.getXtDbTableAttributeForFlex(xt_dbinfo_id);
		if(null != xt_Db_TableAttributeList){
			StringBuffer jsonStr = new StringBuffer();  
			if(null != id && !"".equals(id) && "0".equals(id)){
				jsonStr.append("[");
				for(int i = 0; i < xt_Db_TableAttributeList.size(); i++){
					XtDbTableAttribute xt_Db_TableAttribute = xt_Db_TableAttributeList.get(i);
					if(i==(xt_Db_TableAttributeList.size()-1)){
						jsonStr.append("{id:'"+xt_Db_TableAttribute.getName()+"@0',text:'"+xt_Db_TableAttribute.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Table'}");  
					}else{
						jsonStr.append("{id:'"+xt_Db_TableAttribute.getName()+"@0',text:'"+xt_Db_TableAttribute.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Table'},");
					}
				}
				jsonStr.append("]");
			}else{
				if(id.split("@")[1].equals("0")){
					jsonStr.append("[");
					for(int i = 0; i < 2; i++){
						String leaf = "false";
						String icon = "../deng/images/icons/tickbox.png";
						if(i==(2-1)){
							jsonStr.append("{id:'"+id.split("@")[0]+"@1',text:'字段',leaf:"+leaf+",icon:'"+icon+"'}");  
						}else{
							jsonStr.append("{id:'"+id.split("@")[0]+"@2',text:'索引',leaf:"+leaf+",icon:'"+icon+"'},");  
						}
					}
					jsonStr.append("]");
				}else{
					jsonStr.append("[");
					if(id.split("@")[1].equals("1")){
						//字段
						List<XtDbStructure> xt_Db_StructureList = xtFlexSearchService.getXtDbStructureForFlex(xt_dbinfo_id, id.split("@")[0]);
						for(int i = 0; i < xt_Db_StructureList.size(); i++){
							XtDbStructure xt_Db_Structure = xt_Db_StructureList.get(i);
							String leaf = "true";
							String icon = "../deng/images/icons/sign.png";
							if(xt_Db_Structure.getKey().equals("PRI")){
								icon = "../deng/images/icons/key.png";
							}
							if(i==(xt_Db_StructureList.size()-1)){
								jsonStr.append("{id:'"+xt_Db_Structure.getField()+"',text:'"+xt_Db_Structure.getField()+","+xt_Db_Structure.getType()+","+xt_Db_Structure.getIsNull()+"',leaf:"+leaf+",icon:'"+icon+"'}");  
							}else{
								jsonStr.append("{id:'"+xt_Db_Structure.getField()+"',text:'"+xt_Db_Structure.getField()+","+xt_Db_Structure.getType()+","+xt_Db_Structure.getIsNull()+"',leaf:"+leaf+",icon:'"+icon+"'},");  
							}
						}
					}else if(id.split("@")[1].equals("2")){
						//索引
						List<XtDbStructure> xt_Db_StructureList = xtFlexSearchService.getXtDbStructureForFlex(xt_dbinfo_id, id.split("@")[0]);
						for(int i = 0; i < xt_Db_StructureList.size(); i++){
							XtDbStructure xt_Db_Structure = xt_Db_StructureList.get(i);
							String leaf = "true";
							String icon = "../deng/images/icons/sign.png";
							if(xt_Db_Structure.getKey().equals("PRI")){
								icon = "../deng/images/icons/key.png";
							}
							if(i==(xt_Db_StructureList.size()-1)){
								if(xt_Db_Structure.getKey().equals("PRI") || xt_Db_Structure.getKey().equals("UNI")|| xt_Db_Structure.getKey().equals("MUL")){
									jsonStr.append("{id:'"+xt_Db_Structure.getField()+"',text:'"+xt_Db_Structure.getField()+","+xt_Db_Structure.getType()+","+xt_Db_Structure.getIsNull()+"',leaf:"+leaf+",icon:'"+icon+"'}");  
								}
							}else{
								if(xt_Db_Structure.getKey().equals("PRI") || xt_Db_Structure.getKey().equals("UNI")|| xt_Db_Structure.getKey().equals("MUL")){
									jsonStr.append("{id:'"+xt_Db_Structure.getField()+"',text:'"+xt_Db_Structure.getField()+","+xt_Db_Structure.getType()+","+xt_Db_Structure.getIsNull()+"',leaf:"+leaf+",icon:'"+icon+"'},");  
								}
							}
						}
					}
					jsonStr.append("]");
				}
				
			}
			return outStr(jsonStr.toString());
		}else{
			return null;
		}
	}
	
	/**
	 * 视图
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFlexSearchViewTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFlexSearchViewTree(HttpServletRequest request,String xt_dbinfo_id, HttpServletResponse response){
		String id = request.getParameter("id");
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		List<XtDbView> xtDbViewList = xtFlexSearchService.getXtDbViewListForFlex(xt_dbinfo_id);
		if(null != xtDbViewList){
			StringBuffer jsonStr = new StringBuffer();  
			if(null != id && !"".equals(id) && "0".equals(id)){
				jsonStr.append("[");
				for(int i = 0; i < xtDbViewList.size(); i++){
					XtDbView xt_Db_View = xtDbViewList.get(i);
					if(i==(xtDbViewList.size()-1)){
						jsonStr.append("{id:'"+xt_Db_View.getTable_name()+"',text:'"+xt_Db_View.getTable_name()+"',icon:'../deng/images/icons/toolbox.png',type:'View',leaf:true}");  
					}else{
						jsonStr.append("{id:'"+xt_Db_View.getTable_name()+"',text:'"+xt_Db_View.getTable_name()+"',icon:'../deng/images/icons/toolbox.png',type:'View',leaf:true},");
					}
				}
				jsonStr.append("]");
			}
			return outStr(jsonStr.toString());
		}else{
			return null;
		}
	}
	
	/**
	 * 存储过程
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFlexSearchProTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFlexSearchProTree(HttpServletRequest request,String xt_dbinfo_id, HttpServletResponse response){
		String id = request.getParameter("id");
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		List<XtDbProc> xtDbProcList = xtFlexSearchService.getXtDbProcListForFlex(xt_dbinfo_id);
		if(null != xtDbProcList){
			StringBuffer jsonStr = new StringBuffer();  
			if(null != id && !"".equals(id) && "0".equals(id)){
				jsonStr.append("[");
				for(int i = 0; i < xtDbProcList.size(); i++){
					XtDbProc xt_Db_Proc = xtDbProcList.get(i);
					if(i==(xtDbProcList.size()-1)){
						jsonStr.append("{id:'"+xt_Db_Proc.getName()+"',text:'"+xt_Db_Proc.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Proc',leaf:true}");  
					}else{
						jsonStr.append("{id:'"+xt_Db_Proc.getName()+"',text:'"+xt_Db_Proc.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Proc',leaf:true},");
					}
				}
				jsonStr.append("]");
			}
			return outStr(jsonStr.toString());
		}else{
			return null;
		}
	}
	
	/**
	 * 函数
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFlexSearchFunTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFlexSearchFunTree(HttpServletRequest request,String xt_dbinfo_id, HttpServletResponse response){
		String id = request.getParameter("id");
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		List<XtDbFun> xtDbFunList =  xtFlexSearchService.getXtDbFunListForFlex(xt_dbinfo_id);
		if(null != xtDbFunList){
			StringBuffer jsonStr = new StringBuffer();  
			if(null != id && !"".equals(id) && "0".equals(id)){
				jsonStr.append("[");
				for(int i = 0; i < xtDbFunList.size(); i++){
					XtDbFun xt_Db_Fun = xtDbFunList.get(i);
					if(i==(xtDbFunList.size()-1)){
						jsonStr.append("{id:'"+xt_Db_Fun.getName()+"',text:'"+xt_Db_Fun.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Fun',leaf:true}");  
					}else{
						jsonStr.append("{id:'"+xt_Db_Fun.getName()+"',text:'"+xt_Db_Fun.getName()+"',icon:'../deng/images/icons/toolbox.png',type:'Fun',leaf:true},");
					}
				}
				jsonStr.append("]");
			}
			return outStr(jsonStr.toString());
		}else{
			return null;
		}
	}
	
	/**
	 * 触发器
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFlexSearchTriTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFlexSearchTriTree(HttpServletRequest request,String xt_dbinfo_id, HttpServletResponse response){
		String id = request.getParameter("id");
		XtFlexSearchService xtFlexSearchService = new XtFlexSearchServiceImpl();
		List<XtDbTri> xtDbTriList = xtFlexSearchService.getXtDbTriListForFlex(xt_dbinfo_id);
		if(null != xtDbTriList){
			StringBuffer jsonStr = new StringBuffer();  
			if(null != id && !"".equals(id) && "0".equals(id)){
				jsonStr.append("[");
				for(int i = 0; i < xtDbTriList.size(); i++){
					XtDbTri xt_Db_Tri = xtDbTriList.get(i);
					if(i==(xtDbTriList.size()-1)){
						jsonStr.append("{id:'"+xt_Db_Tri.getTrigger()+"',text:'"+xt_Db_Tri.getTrigger()+"',icon:'../deng/images/icons/toolbox.png',type:'Tri',leaf:true}");  
					}else{
						jsonStr.append("{id:'"+xt_Db_Tri.getTrigger()+"',text:'"+xt_Db_Tri.getTrigger()+"',icon:'../deng/images/icons/toolbox.png',type:'Tri',leaf:true},");
					}
				}
				jsonStr.append("]");
			}
			return outStr(jsonStr.toString());
		}else{
			return null;
		}
	}
	
	/**
	 * 返回值0 SQL为空 1结果集语句 2非结果集语句
	 * @param sql
	 * @return
	 */
	public int validateSQL(String sqlType,String sql){
		if(StringUtils.isEmpty(sql)){
			throw new ExceptionUtil("执行的操作语句不能为空！");
		}
		sql = sql.toUpperCase().trim();
		if(sqlType.equals("1")){
			return 1;
		}else if(sqlType.equals("2")){
			return 2;
		}else{
			if(sql.startsWith("INSERT") || sql.startsWith("DELETE") || sql.startsWith("DROP") || sql.startsWith("UPDATE") || sql.startsWith("CREATE") || sql.startsWith("MERGE") || sql.startsWith("ALERT ")){
				return 2;
			}else{
				return 1;
			}
		}
	}
}
