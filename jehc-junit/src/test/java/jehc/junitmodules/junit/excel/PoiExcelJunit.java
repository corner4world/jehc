package jehc.junitmodules.junit.excel;

import java.util.List;
import java.util.ArrayList;

import jehc.xtmodules.xtcore.allutils.file.excel.PoiExcel2007UpUtil;
import jehc.xtmodules.xtcore.allutils.file.excel.PoiExcel97To23Util;
import jehc.xtmodules.xtcore.allutils.file.excel.PoiExcelUtil;
import jehc.xtmodules.xtcore.base.BaseJunit;

public class PoiExcelJunit  extends BaseJunit{
    public static void main(String[] args){  
        // 获取Excel文件的sheet列表  
        testGetSheetList("c:/test.xlsx");  
          
        // 获取Excel文件的第1个sheet的内容  
        testReadExcel("c:/test.xls", 0);  
          
        // 获取Excel文件的第2个sheet的第2、4-7行和第10行及以后的内容  
        testReadExcel("c:/test.xlsx", 1, "2,4-7,10-");  
          
        // 获取Excel文件的第3个sheet中a,b,g,h,i,j等列的所有内容  
        testReadExcel("c:/test.xls", 2, new String[] {"a","b","g","h","i","j"});  
          
        // 获取Excel文件的第4个sheet的第2、4-7行和第10行及以后，a,b,g,h,i,j等列的内容  
        testReadExcel("c:/test.xlsx", 3, "2,4-7,10-", new String[] {"a","b","g","h","i","j"});  
    }  
      
    // 测试获取sheet列表  
    private static void testGetSheetList(String filePath) {  
        PoiExcelUtil helper = getPoiExcelHelper(filePath);  
          
        // 获取Sheet列表  
        ArrayList<String> sheets = helper.getSheetList(filePath);  
          
        // 打印Excel的Sheet列表  
        printList(filePath, sheets);  
    }  
      
    // 测试Excel读取  
    private static void testReadExcel(String filePath, int sheetIndex) {  
    	PoiExcelUtil helper = getPoiExcelHelper(filePath);  
          
        // 读取excel文件数据  
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex);  
          
        // 打印单元格数据  
        printBody(dataList);  
    }  
      
    // 测试Excel读取  
    private static void testReadExcel(String filePath, int sheetIndex, String rows) {  
    	PoiExcelUtil helper = getPoiExcelHelper(filePath);  
          
        // 读取excel文件数据  
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows);  
          
        // 打印单元格数据  
        printBody(dataList);  
    }  
      
    // 测试Excel读取  
    private static void testReadExcel(String filePath, int sheetIndex, String[] columns) {  
    	PoiExcelUtil helper = getPoiExcelHelper(filePath);  
          
        // 读取excel文件数据  
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, columns);  
          
        // 打印列标题  
        printHeader(columns);  
          
        // 打印单元格数据  
        printBody(dataList);  
    }  
      
    // 测试Excel读取  
    private static void testReadExcel(String filePath, int sheetIndex, String rows, String[] columns) {  
    	PoiExcelUtil helper = getPoiExcelHelper(filePath);  
          
        // 读取excel文件数据  
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows, columns);  
          
        // 打印列标题  
        printHeader(columns);  
          
        // 打印单元格数据  
        printBody(dataList);  
    }  
      
    // 获取Excel处理类  
    private static PoiExcelUtil getPoiExcelHelper(String filePath) {  
    	PoiExcelUtil helper;  
        if(filePath.indexOf(".xlsx")!=-1) {  
            helper = new PoiExcel2007UpUtil();
        }else {  
            helper = new PoiExcel97To23Util();
        }  
        return helper;  
    }  
  
    // 打印Excel的Sheet列表  
    private static void printList(String filePath, ArrayList<String> sheets) {  
        System.out.println();  
        for(String sheet : sheets) {  
            System.out.println(filePath + " ==> " + sheet);  
        }  
    }  
  
    // 打印列标题  
    private static void printHeader(String[] columns) {  
        System.out.println();  
        for(String column : columns) {  
            System.out.print("\t\t" + column.toUpperCase());  
        }  
    }  
  
    // 打印单元格数据  
    private static void printBody(ArrayList<ArrayList<String>> dataList) {  
        int index = 0;  
        for(ArrayList<String> data : dataList) {  
            index ++;  
            System.out.println();  
            System.out.print(index);  
            for(String v : data) {  
                System.out.print("\t\t" + v);  
            }  
        }  
    }  
}
