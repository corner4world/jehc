package jehc.xtmodules.xtcore.allutils;

import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 清空html标签
 * @author邓纯杰
 *
 */
public class HtmlUtils{
	public static String returnTextFromTHML(String htmlStr) {
		Document doc = Jsoup.parse(htmlStr);
		String text = doc.text();
		// remove extra white space
		StringBuilder builder = new StringBuilder(text);
		int index = 0;
		while(builder.length()>index){
			char tmp = builder.charAt(index);
			if(Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)){
				builder.setCharAt(index, ' ');
			}
			index++;
		}
		text = builder.toString().replaceAll(" +", " ").trim();
		return text;
	}
	
	
	/**
	 * 转码
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
	
	public static String Html2Text(String inputString){
	     String htmlStr = inputString; //含html标签的字符串
	     String textStr ="";
	     java.util.regex.Pattern p_script;
	     java.util.regex.Matcher m_script;
	     java.util.regex.Pattern p_style;
	     java.util.regex.Matcher m_style;
	     java.util.regex.Pattern p_html;
	     java.util.regex.Matcher m_html;

	    try{
	          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
	          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
	          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

	          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
	          m_script = p_script.matcher(htmlStr);
	          htmlStr = m_script.replaceAll(""); //过滤script标签

	          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
	          m_style = p_style.matcher(htmlStr);
	          htmlStr = m_style.replaceAll(""); //过滤style标签

	          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	          m_html = p_html.matcher(htmlStr);
	          htmlStr = m_html.replaceAll(""); //过滤html标签

	          textStr = htmlStr;
	     }catch(Exception e){
	     }
	     return textStr;//返回文本字符串
	 } 
	
	public static String changeHtml(String text){
		text=text.replace("\n","");//回车
		text=text.replace("\t","");//换行
		text=text.replace("&","&amp;");
		text=text.replace(" ","&nbsp;");
		text=text.replace("<","&lt;");
		text=text.replace(">","&gt;");
		text=text.replace("\"","&quot;");//双引号转义
		text=text.replace("public","<b>public</b>");
		text=text.replace("class","<b>class</b>");
		text=text.replace("static","<b>static</b>");
		text=text.replace("void","<b>void</b>");
		String t=text.replace("//","<font color=green>//");
		if(!text.equals(t)){
			text = t+"</font>";
		}
		return text;
	}
}
