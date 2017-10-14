//package jehc.xtmodules.xtcore.allutils.file;
//import java.awt.Color;  
//import java.io.File;  
//import java.io.FileOutputStream;  
//import java.util.regex.Pattern;  
//import org.apache.commons.io.FileUtils;  
//import org.artofsolving.jodconverter.OfficeDocumentConverter;  
//import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;  
//import org.artofsolving.jodconverter.office.OfficeManager;  
//import com.lowagie.text.Document;  
//import com.lowagie.text.Element;  
//import com.lowagie.text.Font;  
//import com.lowagie.text.Phrase;  
//import com.lowagie.text.pdf.BaseFont;  
//import com.lowagie.text.pdf.ColumnText;  
//import com.lowagie.text.pdf.PdfContentByte;  
//import com.lowagie.text.pdf.PdfCopy;  
//import com.lowagie.text.pdf.PdfCopy.PageStamp;  
//import com.lowagie.text.pdf.PdfImportedPage;  
//import com.lowagie.text.pdf.PdfReader;  
//public class OfficeToPdf {
//	public static void main(String[] args) {  
//        String inputFilePath = "F://officeToPdf/WordToPdf测试.docx";  
//        //String inputFilePath = "F://officeToPdf/pptToPdf测试.pptx";  
//        //String inputFilePath = "F://officeToPdf/xlsxToPdf测试.xlsx";  
//        String outputFilePath = getOutputFilePath(inputFilePath);  
//        //Office转换成Pdf  
//        OfficeToPdf.office2pdf(inputFilePath,outputFilePath);  
//        //添加水印、页眉、页脚  
//        addFooterAndWater("F://officeToPdf//WordToPdf测试.pdf", "F://officeToPdf//WordToPdf测试2.pdf", "WordToPdf水印严禁复制", "WordToPdf页眉", "WordToPdf页脚");  
//    }  
//    /**  
//     * 将Office文档转换为PDF. 需要安装OpenOffice 
//     * @param inputFilePath源文件,绝对路径. 可以是Office2003-2007全部格式的文档, 包括.doc, .docx, .xls, .xlsx, .ppt, .pptx等.  
//     * @param outputFilePath目标文件.绝对路径.  
//     */   
//    public static void office2pdf(String inputFilePath,String outputFilePath) {    
//        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();    
//        String officeHome = getOfficeHome();    
//        //设置OpenOffice.org安装目录  
//        config.setOfficeHome(officeHome);    
//        //设置转换端口，默认为8100  
//        //config.setPortNumbers(8100);    
//        //设置任务执行超时为60分钟  
//        config.setTaskExecutionTimeout(1000 * 60 * 60L);  
//         //设置任务队列超时为24小时  
//        config.setTaskQueueTimeout(1000 * 60 * 60 * 24L);   
//        OfficeManager officeManager = config.buildOfficeManager();    
//        officeManager.start();    
//        System.out.println("office转换服务启动成功!");  
//        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);    
//        File inputFile = new File(inputFilePath);    
//        if (inputFile.exists()) {// 找不到源文件, 则返回    
//            File outputFile = new File(outputFilePath);    
//            if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径    
//                outputFile.getParentFile().mkdirs();    
//            }    
//            converter.convert(inputFile, outputFile);    
//        }    
//        if (null != officeManager){  
//            officeManager.stop();    
//            System.out.println("office转换服务完成。");  
//        }  
//          
//    }    
//    /** 
//     * 根据源文件路径获取PDF文件路径 
//     * @param inputFilePath 
//     * @return 
//     */  
//    public static String getOutputFilePath(String inputFilePath) {    
//        String outputFilePath = "";  
//        String temp = inputFilePath.substring(inputFilePath.lastIndexOf(".")) ;  
//        outputFilePath = inputFilePath.replaceAll(temp, ".pdf");    
//        return outputFilePath;    
//    }    
//    /** 
//      * 获取OpenOffice安装目录 
//      * @return 
//     */  
//    public static String getOfficeHome() {    
//        String osName = System.getProperty("os.name");    
//        if(Pattern.matches("Linux.*", osName)) {    
//            return "/opt/openoffice.org3";    
//        }else if (Pattern.matches("Windows.*", osName)) {    
//            return "E:/software/OpenOffice 4";    
//        }else if (Pattern.matches("Mac.*", osName)) {    
//            return "/Application/OpenOffice.org.app/Contents";    
//        }    
//        return null;    
//    }    
//    /** 
//     *  添加水印、页眉、页脚 
//     * @param fileName 源文件路径 
//     * @param savepath 目标文件路径 
//     * @param waterMarkName 文字水印 
//     * @param pageHeade 页眉 
//     * @param foot 页脚 
//     * @return 
//     */  
//    public static int addFooterAndWater(String fileName, String savepath,String waterMarkName, String pageHeade, String foot){  
//        // 文档总页数  
//        int num = 0;  
//        Document document = new Document();  
//        try {  
//            PdfReader reader = new PdfReader(fileName);  
//            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",  BaseFont.EMBEDDED);  
//            num = reader.getNumberOfPages();  
//            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));  
//            document.open();  
//            for (int i = 0; i < num;){  
//                PdfImportedPage page = copy.getImportedPage(reader, ++i);  
//                PageStamp stamp = copy.createPageStamp(page);  
//                Font f = new Font(base);  
//                // 添加页脚，左侧文字，右侧页码  
//                ColumnText.showTextAligned(stamp.getUnderContent(),Element.ALIGN_RIGHT,new Phrase(String.format("第 %d 页/共 %d 页", i, num), f), 550f, 28, 0);  
//                ColumnText.showTextAligned(stamp.getUnderContent(),Element.ALIGN_LEFT, new Phrase(foot, f), 50f, 28, 0);  
//                // 添加页眉 (文字页眉，居中)  
//                ColumnText.showTextAligned(stamp.getUnderContent(),Element.ALIGN_CENTER, new Phrase(pageHeade, f), 150f,800, 0);  
//                // 页眉添加logo （图片页眉，居右）  
//                /*Image img = Image.getInstance("template/logo.png");// 选择图片 
//                img.setAlignment(1); 
//                img.scaleAbsolute(436 / 5, 96 / 5);// 控制图片大小 
//                img.setAbsolutePosition(450f, 800);// 控制图片位置 
//                stamp.getUnderContent().addImage(img);*/  
//                // 添加水印  
//                PdfContentByte under = stamp.getUnderContent();  
//                under.beginText();  
//                under.setColorFill(Color.LIGHT_GRAY);  
//                // 字符越长，字体越小，设置字体  
//                int fontSize = getFontSize(waterMarkName);  
//                under.setFontAndSize(base, fontSize);  
//                // 设置水印文字字体倾斜 开始  
//                float pageWidth = reader.getPageSize(i).getWidth();  
//                float pageHeight = reader.getPageSize(i).getHeight();  
//                under.showTextAligned(Element.ALIGN_CENTER, waterMarkName,pageWidth / 2, pageHeight / 2, 60);// 水印文字成60度角倾斜,且页面居中展示  
//                // 字体设置结束  
//                under.endText();  
//                stamp.alterContents();  
//                copy.addPage(page);  
//            }  
//        }catch (Exception e){  
//            e.printStackTrace();  
//            return -1;  
//        }finally{  
//            if (null != document){  
//                document.close();  
//            }  
//        }  
//        System.out.println("pdf totalpages:" + num);  
//        return num;  
//  
//    }  
//    /** 
//     * 根据水印文字长度计算获取字体大小 
//     * @param waterMarkName 
//     * @return 
//     */  
//    private static int getFontSize(String waterMarkName){  
//        int fontSize = 80;  
//        if(null != waterMarkName && !"".equals(waterMarkName)){  
//            int length = waterMarkName.length();  
//            if(length <=26 && length >= 18){  
//                fontSize = 26;  
//            }else if(length <18 && length >= 8){  
//                fontSize = 40;  
//            }else if(length <8 && length >= 1){  
//                fontSize = 80;  
//            }else {  
//                fontSize = 16;  
//            }  
//        }         
//        return fontSize;  
//    }  
//}
