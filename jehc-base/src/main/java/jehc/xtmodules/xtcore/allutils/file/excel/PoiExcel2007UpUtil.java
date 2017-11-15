package jehc.xtmodules.xtcore.allutils.file.excel;
import java.io.File;
import java.io.FileInputStream;  
import java.util.ArrayList;  
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
/**
 *  Excel 读取（2007+新格式） 
 * @author Administrator
 *
 */
public class PoiExcel2007UpUtil extends PoiExcelUtil{
	
	/** 1-1获取sheet列表 */  
    public ArrayList<String> getSheetList(String filePath) {  
        ArrayList<String> sheetList = new ArrayList<String>(0);  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));  
            Iterator<Sheet> iterator = wb.iterator();  
            while (iterator.hasNext()) {  
                sheetList.add(iterator.next().getSheetName());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sheetList;  
    }  
    /** 1-2获取sheet列表 */  
    public ArrayList<String> getSheetList(File file) {  
        ArrayList<String> sheetList = new ArrayList<String>(0);  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
            Iterator<Sheet> iterator = wb.iterator();  
            while (iterator.hasNext()) {  
                sheetList.add(iterator.next().getSheetName());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sheetList;  
    }  
  
    
    /** 2-1读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex, String rows, String columns) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
            dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return dataList;  
    }  
    /** 2-1读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(File file, int sheetIndex, String rows, String columns) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
            dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return dataList;  
    }  
    
    
      
    /** 3-1读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex, String rows, int[] cols) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
            dataList = readExcel(sheet, rows, cols);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return dataList;  
    }  
    /** 3-2读取Excel文件内容 */  
    public ArrayList<ArrayList<String>> readExcel(File file, int sheetIndex, String rows, int[] cols) {  
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();  
        try {  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));  
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);  
            dataList = readExcel(sheet, rows, cols);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return dataList;  
    }  
}
