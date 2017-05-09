package jehc.xtmodules.xtcore.allutils;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
 
/**
 * Jdomxml工具类
 * @author邓纯杰
 *
 */
public class JdomXmlUtil {  
    private String fileUrl=null;  
    public String getFileUrl() {  
        return fileUrl;  
    }  
    public JdomXmlUtil(String filePath) {  
        this.fileUrl=filePath;  
    }  
     public org.jdom.Document load(){  
         org.jdom.Document document=null;  
         String url=this.getFileUrl();  
         try {  
             SAXBuilder reader = new SAXBuilder();   
             document=reader.build(new File(url));  
        } catch (Exception e) {  
             e.printStackTrace();  
        }  
         return document;  
     }  
     
     /**
      * 将xml转换成String
      * @return
      */
     public String XmlToString(){  
         org.jdom.Document document=null;  
         document=this.load();  
         Format format =Format.getPrettyFormat();      
         format.setEncoding("UTF-8");//设置编码格式   
         StringWriter out=null; //输出对象  
         String sReturn =""; //输出字符串  
         XMLOutputter outputter =new XMLOutputter();   
         out=new StringWriter();   
         try {  
            outputter.output(document,out);  
         } catch (IOException e) {  
            e.printStackTrace();  
         }   
         sReturn=out.toString();   
         return sReturn;  
     }  
  
}  