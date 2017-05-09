package jehc.xtmodules.xtcore.util.excel.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;

public class ExcelUtil {
	public static String outputFile = "c:\\test.xls";  
	private void cteateCell(HSSFWorkbook wb, HSSFRow row, short col, String val) {  
		HSSFCell cell = row.createCell(col);  
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);  
		cell.setCellValue(val);  
		HSSFCellStyle cellstyle = wb.createCellStyle();  
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);  
		cell.setCellStyle(cellstyle);  
	} 
	public static void main(String[]args) throws IOException{
		/////////////////////////设置单元格边框样式开始//////////////////////
		/**
		try {  
			// 创建新的Excel 工作簿  
			HSSFWorkbook workbook = new HSSFWorkbook();  
			  
			// 设置字体  
			HSSFFont font = workbook.createFont();  
			// font.setColor(HSSFFont.COLOR_RED);  
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
			font.setFontHeightInPoints((short) 14);  
			  
			// HSSFFont font2 = workbook.createFont();  
			// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
			// font.setFontHeightInPoints((short)14);  
			// 设置样式  
			HSSFCellStyle cellStyle = workbook.createCellStyle();  
			cellStyle.setFont(font);  
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

			cellStyle.setBottomBorderColor(HSSFColor.RED.index);

			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			cellStyle.setLeftBorderColor(HSSFColor.RED.index);

			cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

			cellStyle.setRightBorderColor(HSSFColor.RED.index);

			cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

			cellStyle.setTopBorderColor(HSSFColor.RED.index);
			// HSSFCellStyle cellStyle2= workbook.createCellStyle();  
			// cellStyle.setFont(font2);  
			// cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			  
			// 在Excel工作簿中建一工作表，其名为缺省值  
			// 如要新建一名为"月报表"的工作表，其语句为：  
			HSSFSheet sheet = workbook.createSheet("月报表");  
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,  
			11);  
			sheet.addMergedRegion(cellRangeAddress);  
			  
			//第一行  
			// 在索引0的位置创建行（最顶端的行）  
			HSSFRow row = sheet.createRow(0);  
			// 在索引0的位置创建单元格（左上端）  
			HSSFCell cell = row.createCell(0);  
			// 定义单元格为字符串类型  
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);  
			// 在单元格中输入一些内容  
			cell.setCellValue(new HSSFRichTextString("北京亿卡联科技发展有限公司小区门禁维修月报表"));  
			  
			//第二行  
			cellRangeAddress = new CellRangeAddress(1, 1, 3, 6);  
			sheet.addMergedRegion(cellRangeAddress);  
			row = sheet.createRow(1);  
			HSSFCell datecell = row.createCell(3);  
			datecell.setCellType(HSSFCell.CELL_TYPE_STRING);  
			datecell.setCellStyle(cellStyle);  
			datecell.setCellValue("时间间隔xxxxx");  
			  
			cellRangeAddress = new CellRangeAddress(1, 1, 9,  
			10);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(9).setCellValue("单位：元");  
			  
			//第三行  
			row=sheet.createRow(2);  
			row.createCell(0).setCellValue("一、");  
			row.createCell(1).setCellValue("基本资料");  
			  
			//第4行  
			row=sheet.createRow(3);  
			row.createCell(1).setCellValue("小区名称：");  
			cellRangeAddress=new CellRangeAddress(3,3,2,11);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(2).setCellValue("xxxxx");  
			  
			//第5行  
			row=sheet.createRow(4);  
			row.createCell(1).setCellValue("座落地点：");  
			cellRangeAddress=new CellRangeAddress(4,4,2,11);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(2).setCellValue("xxxxx");  
			  
			//第6行  
			row=sheet.createRow(5);  
			row.createCell(1).setCellValue("建成年月：");  
			cellRangeAddress=new CellRangeAddress(5,5,2,4);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(2).setCellValue("年月日：xxxxx");  
			row.createCell(5).setCellValue("联系人");  
			cellRangeAddress=new CellRangeAddress(5,5,6,8);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(6).setCellValue("XXX");  
			row.createCell(9).setCellValue("电话");  
			cellRangeAddress=new CellRangeAddress(5,5,10,11);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(10).setCellValue("XXX");  
			  
			//第7行  
			row=sheet.createRow(6);  
			row.createCell(1).setCellValue("住户：");  
			row.createCell(2).setCellValue("(XX)");  
			row.createCell(3).setCellValue("(户)");  
			cellRangeAddress=new CellRangeAddress(6,6,4,5);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(4).setCellValue("共计（      ）");         
			row.createCell(6).setCellValue("幢");  
			cellRangeAddress=new CellRangeAddress(6,6,7,8);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(7).setCellValue("发卡张数");     
			cellRangeAddress=new CellRangeAddress(6,6,9,10);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(9).setCellValue("xxxx");     
			  
			  
			//第9行  
			row=sheet.createRow(8);  
			row.createCell(0).setCellValue("二、");  
			cellRangeAddress=new CellRangeAddress(8,8,1,2);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(1).setCellValue("维修用材料台账");  
			row.createCell(6).setCellValue("三、");  
			cellRangeAddress=new CellRangeAddress(8,8,7,9);  
			sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(7).setCellValue("维修工时记录");  
			//第10行  
			row=sheet.createRow(9);  
			row.createCell(0).setCellValue("日期");  
			row.createCell(0).setCellStyle(cellStyle);
			row.createCell(1).setCellValue("维修事项");  
			row.createCell(2).setCellValue("材料清单");  
			row.createCell(3).setCellValue("数量");  
			row.createCell(4).setCellValue("单价");  
			row.createCell(5).setCellValue("材料金额");  
			  
			row.createCell(7).setCellValue("日期");  
			row.createCell(8).setCellValue("技工");  
			row.createCell(9).setCellValue("工时数");  
			row.createCell(10).setCellValue("单价");  
			row.createCell(11).setCellValue("工时金额");  
			  
			//填充数据  
			for (int i = 0; i < 10; i++) {  
			row=sheet.createRow(9+i+1);  
			row.createCell(0).setCellValue("日期");  
			row.createCell(1).setCellValue("维修事项");  
			row.createCell(2).setCellValue("材料清单");  
			row.createCell(3).setCellValue("数量");  
			row.createCell(4).setCellValue("单价");  
			row.createCell(5).setCellValue("材料金额");  
			  
			row.createCell(7).setCellValue("日期");  
			row.createCell(8).setCellValue("技工");  
			row.createCell(9).setCellValue("工时数");  
			row.createCell(10).setCellValue("单价");  
			row.createCell(11).setCellValue("工时金额");  
			}  
			  
			  
			//第n+10行  
			row=sheet.createRow(9+10+1);  
			//cellRangeAddress=new CellRangeAddress(19,19,0,4);  
			//sheet.addMergedRegion(cellRangeAddress);  
			row.createCell(0).setCellValue("累计:");  
			row.createCell(1).setCellValue("xxx");  
			row.createCell(7).setCellValue("累计:");  
			row.createCell(8).setCellValue("xxx");  
			  
			  
			  
			  
			// 新建一输出文件流  
			FileOutputStream fOut = new FileOutputStream(outputFile);  
			// 把相应的Excel 工作簿存盘  
			workbook.write(fOut);  
			fOut.flush();  
			// 操作结束，关闭文件  
			fOut.close();  
			System.out.println("文件生成...");  
			} catch (Exception e) {  
			System.out.println("已运行 xlCreate() : " + e);  
		}
		**/
		/////////////////////////设置单元格边框样式结束//////////////////////
		
		
		/////////////////////////设置单元格提示开始//////////////////////
		 HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象  
	     HSSFSheet sheetlist = wb.createSheet("sheetlist");// 工作表对象  
	     FileOutputStream out = new FileOutputStream("d:\\success.xls");  
//	     String[] textlist = { "列表1", "列表2", "列表3", "列表4", "列表5" };  
//	     sheetlist = setHSSFValidation(sheetlist, textlist, 0, 500, 0, 0);// 第一列的前501行都设置为选择列表形式.  
	     sheetlist = setHSSFPrompt(sheetlist, "promt Title", "prompt Content",0, 500, 1, 1);// 第二列的前501行都设置提示.  
	     wb.write(out);  
	     out.close();  
	     /////////////////////////设置单元格提示结束//////////////////////
	}  
	  
    /** 
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @param sheet要设置的sheet. 
     * @param textlist下拉框显示的内容 
     * @param firstRow开始行 
     * @param endRow结束行 
     * @param firstCol开始列 
     * @param endCol结束列 
     * @return 设置好的sheet. 
     */  
    @SuppressWarnings("deprecation")
	public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow, int firstCol,int endCol) {  
        // 加载下拉列表内容  
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
        sheet.addValidationData(data_validation_list);  
        return sheet;  
	   
	}
    /** 
     * 设置单元格上提示 
     * @param sheet要设置的sheet. 
     * @param promptTitle标题 
     * @param promptContent内容 
     * @param firstRow开始行 
     * @param endRow结束行 
     * @param firstCol开始列 
     * @param endCol结束列 
     * @return 设置好的sheet. 
     */  
    @SuppressWarnings("deprecation")
	public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,String promptContent, int firstRow, int endRow, int firstCol, int endCol) {  
        // 构造constraint对象  
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("BB1");  
        // 四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);  
        data_validation_view.createPromptBox(promptTitle, promptContent);  
        data_validation_view.setSuppressDropDownArrow(false);  
        data_validation_view.createPromptBox("输入提示", "请从下拉列表中选择！");  
        data_validation_view.setShowPromptBox(true);  
        data_validation_view.setErrorStyle(100);
        data_validation_view.setShowErrorBox(true);
        sheet.addValidationData(data_validation_view);  
        return sheet;  
    }
}
