package jehc.xtmodules.xtcore.allutils.file.word;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.style.RtfFont;

public class WordCss {
	
public final static RtfFont titleFont =  new RtfFont("仿宋_GB2312", 20, Font.BOLD, Color.BLACK);
	
	public final static RtfFont kFont =  new RtfFont("隶 书", 14, Font.BOLD, Color.BLACK);
	
	public final static RtfFont zFont =  new RtfFont("隶 书", 15, Font.BOLD, Color.BLACK);
	
	public final static RtfFont vFont =  new RtfFont("隶 书", 12, Font.NORMAL, Color.BLACK);
	
	public final static RtfFont rec_a_no =  new RtfFont("仿宋_GB2312", 12, Font.NORMAL, Color.BLACK);
	
	public final static RtfFont rec_b_no =  new RtfFont("仿宋_GB2312", 14, Font.BOLD, Color.BLACK);
	
	public final static RtfFont footFont =  new RtfFont("仿宋_GB2312", 15, Font.NORMAL, Color.BLACK);

	public final static RtfFont fFont =  new RtfFont("黑 体", 14, Font.UNDERLINE, Color.BLACK);
	
	public final static RtfFont send_t_Font =  new RtfFont("华 文 中 宋", 24, Font.BOLD, Color.RED);
	
	public final static RtfFont send_n_Font =  new RtfFont("仿宋_GB2312", 14, Font.UNDERLINE, Color.RED);
	
	public final static RtfFont send_show_title_Font =  new RtfFont("仿宋_GB2312", 14, Font.NORMAL, Color.RED);
	
	public final static RtfFont send_small_title_Font =  new RtfFont("仿宋_GB2312", 10, Font.NORMAL, Color.RED);
	
	/**
	 * 放在标题的前面
	 */
	public final static int before_titile = 1;
	/**
	 * 放在标题的后面
	 */
	public final static int after_titile = 2;
	
	public static Paragraph getParagraph(String content,Font font,int align){
		Paragraph p = new Paragraph(content,font);
		p.setAlignment(align);		
		return p;
	}
}
