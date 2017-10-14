package jehc.xtmodules.xtcore.util.excel.poi;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.DateUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

public class ExportExcel{
	Logger log = LoggerFactory.getLogger(this.getClass());
	public void exportExcel(String excleData,String excleHeader,String excleText,HttpServletResponse response){
		try {
			excleData =  URLDecoder.decode(excleData,"GBK");
			JSONArray excle = JSONArray.fromObject(excleData);
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet();
	        // 解析表头
	        JSONArray dataHeader = JSONArray.fromObject(URLDecoder.decode(excleText));
	        HSSFRow headrow = sheet.createRow(0);
	        for (int col = 0; col < dataHeader.size(); col++) {
	        	sheet.autoSizeColumn(col);
	            String mycell = dataHeader.getString(col);
	            HSSFCell cell = headrow.createCell(col);
	            // 定义单元格为字符串类型
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	            // 在单元格中输入一些内容
	            cell.setCellValue(mycell);
	        }
	        int r=0;
	        for(Object o : excle) {
	        	JSONObject excleObj = (JSONObject)o;
	        	 HSSFRow row=sheet.createRow((short)r+1);
		            //解析列
		            JSONArray dataIndex = JSONArray.fromObject(URLDecoder.decode(excleHeader));
		            for(int col=0;col<dataIndex.size();col++){
		                String mycell=dataIndex.getString(col);
		                if(null != excleObj && null != mycell){
		                	mycell=""+excleObj.get(mycell);
			                HSSFCell cell = row.createCell(col);
			                // 定义单元格为字符串类型
			                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			                // 在单元格中输入一些内容
			                cell.setCellValue(mycell);
			        	}
		            }
		            r++;
	        };
	        //通过Response把数据以Excel格式保存
	        response.setContentType("application/msexcel;charset=GBK");
	        response.setCharacterEncoding("GBK");
	        try {
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                    + new String((DateUtil.getSimpleDateFormat() + ".xls").getBytes("GBK"),
	                            "ISO8859_1") + "\"");
	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            log.error("导出xls出现异常---"+e.getMessage());
	            throw new ExceptionUtil("导出Excel出现异常:"+e.getMessage());
	        }
		} catch (UnsupportedEncodingException e1) {
			  log.error("导出xls URLDecoder.decode(excleData,\"GBK\")出现异常---"+e1.getMessage());
			  throw new ExceptionUtil("导出Excel出现异常:"+e1.getMessage());
		}
    }
}
