package jehc.xtmodules.xtweb;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;

import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.allutils.file.word.WordCss;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtDbStructure;
import jehc.xtmodules.xtmodel.XtDbTableAttribute;
import jehc.xtmodules.xtmodel.XtDbTableIndex;
import jehc.xtmodules.xtmodel.XtDbTableSize;
import jehc.xtmodules.xtservice.XtDbStructureService;
/**
 * 数据库表
 * @author 邓纯杰
 */
@Controller
@RequestMapping("/xtDbStructureController")
public class XtDbStructureController extends BaseAction{
	@Autowired
	private XtDbStructureService xtDbStructureService;
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
		List<XtDbTableAttribute> xt_Db_TableAttributeList = xtDbStructureService.getXtDbTableAttribute(condition);
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
		List<XtDbStructure> xt_Db_StructureList = xtDbStructureService.getXtDbStructureByCondition(condition);
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
		XtDbStructure xt_Db_Structure = xtDbStructureService.getTablePhrases(condition);
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
		List<XtDbTableIndex> xt_Db_TableIndexList = xtDbStructureService.getXtDbTableIndex(condition);
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
		XtDbTableSize xt_Db_TableSize = xtDbStructureService.getXtDbTableSize(condition);
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
		int i = xtDbStructureService.addXtDbTableIndex(condition);
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
			int j = xtDbStructureService.delXtDbTableIndex(condition);
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
	
	
	
	
	private String[] xtDataBaseDictionaryTitle = {"编号","字段名称","类型","备注"};
	/**
	 * 导出数据字典
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	@ResponseBody
	@RequestMapping(value="/exportXtDbTableAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDbTableAttribute(HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dbName", CommonUtils.getXtConstantCache("XtDbname").getXt_constantValue());
		List<XtDbTableAttribute> xt_Db_TableAttributeList = xtDbStructureService.getXtDbTableAttribute(condition);
		
		String file_name = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		file_name = date+"数据字典"+".doc";
		Map<String, List<XtDbStructure>> tableAndFileListModel = new HashMap<String, List<XtDbStructure>>();
		for(int i = 0; i < xt_Db_TableAttributeList.size(); i++){
			XtDbTableAttribute saDataBaseTableAttribute = xt_Db_TableAttributeList.get(i);
			//获取表名
			condition = new HashMap<String, Object>();
			condition.put("tableName", saDataBaseTableAttribute.getName());
			List<XtDbStructure> xt_Db_StructureList = xtDbStructureService.getXtDbStructureByCondition(condition);
			if(null != saDataBaseTableAttribute.getComment() && !"".equals(saDataBaseTableAttribute.getComment())){
				tableAndFileListModel.put(saDataBaseTableAttribute.getName()+"("+saDataBaseTableAttribute.getComment()+")", xt_Db_StructureList);
			}else{
				tableAndFileListModel.put(saDataBaseTableAttribute.getName(), xt_Db_StructureList);
			}
		}
		Document document = new Document(PageSize.A4,40f,40f,40f,40f);
		String filePath = "";
		try {
			filePath = FileUtil.validOrCreateFile(CommonUtils.getXtPathCache(CacheConstant.XTPATHCACHE_XTDbSTRUCTURE_FILE_PATH).get(0).getXt_path());
			 RtfWriter2.getInstance(document, new FileOutputStream(filePath+file_name));
			 document.open();
			 document = createXtDataBaseDictionary(document,tableAndFileListModel,file_name);
			 document.close();
			 
			 int len=0;
			 byte []buffers = new byte[1024];
			 BufferedInputStream br = null; 
	         OutputStream ut = null;  
			 response.reset();
			 response.setContentType("application/x-msdownload");
			 response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(file_name,"UTF-8"));
			 File file= new File(filePath+file_name);
			 br = new BufferedInputStream(new FileInputStream(file));  
	         ut = response.getOutputStream();  
	         while((len=br.read(buffers))!=-1){  
	            ut.write(buffers, 0, len);  
	         } 
	         ut.flush();
	         ut.close();
	         br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建数据字典Word文档
	 * @return
	 */
	@SuppressWarnings("static-access")
	public Document createXtDataBaseDictionary(Document document,Map<String, List<XtDbStructure>> tableAndFileListModel,String fileName) throws DocumentException, IOException{
		RtfFont big_t_font = new RtfFont("仿宋_GB2312", 20, Font.BOLD, Color.BLACK);
		RtfFont t_font = new RtfFont("仿宋_GB2312", 12, Font.NORMAL, Color.BLACK);
		document.add(WordCss.getParagraph("数据字典",big_t_font,Element.ALIGN_CENTER));
		Set<String> key = tableAndFileListModel.keySet();
	    Iterator<String> it = key.iterator();  
	    while(it.hasNext()){
	    	String keystr = (String) it.next();
	    	List<XtDbStructure> saDataBaseStructureList = tableAndFileListModel.get(keystr);
	    	document.add(WordCss.getParagraph(keystr,t_font,Element.ALIGN_LEFT));
			Table table = new Table(4,2);
			int[] widths = {10,50,20,20};
			table.setWidths(widths);
			table.setWidth(100);
			table.setAutoFillEmptyCells(true);
			table.setPadding(0);    
			table.setSpacing(0);   
			table.setAlignment(table.ALIGN_CENTER);
			table.setAlignment(table.ALIGN_CENTER);
			table.setBorder(1);
			//1.创建标题
			for(int j = 0 ; j < xtDataBaseDictionaryTitle.length ; j++){
				Cell title = new Cell(WordCss.getParagraph(xtDataBaseDictionaryTitle[j], t_font, Element.ALIGN_CENTER));
				title.setRowspan(1);
				title.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(title);
			}
			//2.读取表内容
			for(int j = 0; j < saDataBaseStructureList.size(); j++){
				XtDbStructure saDataBaseStructure = saDataBaseStructureList.get(j);
				//(1)编号
				Cell sizaID = new Cell(WordCss.getParagraph(String.valueOf((j+1)), t_font, Element.ALIGN_CENTER));
				sizaID.setRowspan(1);
				sizaID.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(sizaID);
				
				//(2)字段名
				Cell filedModel = new Cell(WordCss.getParagraph(String.valueOf(saDataBaseStructure.getField()), t_font, Element.ALIGN_CENTER));
				filedModel.setRowspan(1);
				filedModel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(filedModel);
				
				//(3)类型
				Cell typeModel = new Cell(WordCss.getParagraph(String.valueOf(saDataBaseStructure.getType()), t_font, Element.ALIGN_CENTER));
				typeModel.setRowspan(1);
				typeModel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(typeModel);
				//(4)备注
				Cell commentModel = new Cell(WordCss.getParagraph(saDataBaseStructure.getComment(), t_font, Element.ALIGN_CENTER));//单位
				commentModel.setRowspan(1);
				commentModel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(commentModel);
			}
			document.add(table);
		}
		return document;
	}
}
