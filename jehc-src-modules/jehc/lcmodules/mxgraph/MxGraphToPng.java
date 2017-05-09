package jehc.lcmodules.mxgraph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.canvas.mxICanvas2D;
import com.mxgraph.reader.mxSaxOutputHandler;
import com.mxgraph.util.mxUtils;

import jehc.xtmodules.xtcore.base.BaseAction;

/**
 * 导出图片格式为PNG
 * @author邓纯杰
 * 
 */
public class MxGraphToPng extends BaseAction{
	private static final long serialVersionUID = 1L;
	private transient SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	private transient Hashtable<String, Image> imageCache = new Hashtable<String, Image>();
	/**
	 * 使用xmlimg生成png图片
	 * @param imgxml
	 * @throws UnsupportedEncodingException 
	 */
	public int mxgraphxml_to_png(String url,String imgxml,String imgpath,String w,String h,HttpServletResponse response){
		int i = 0;
		try{
			imgxml = URLDecoder.decode(imgxml, "UTF-8");
			handleRequest(url,imgpath,imgxml,w,h,response);
			i = 1;
		}catch (Exception e) {
			i = 0;
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 创建并返回请求的图片
	 * @param request
	 * @return
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	protected void parseXmlSax(String xml, mxICanvas2D canvas){
		mxSaxOutputHandler handler = new mxSaxOutputHandler(canvas);
		XMLReader reader;
		try{
			reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new StringReader(xml)));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void handleRequest(String url,String imgpath,String imgxml,String width,String height,HttpServletResponse response) throws Exception{
		int w = Integer.parseInt(width);
		int h = Integer.parseInt(height);
		if(w > 0 && h > 0 && imgxml != null && imgxml.length() > 0){
			Color bg = Color.WHITE;
			writeImage(url, w, h, bg, imgxml, response,imgpath);
		}
	}

	protected void writeImage(String url, int w, int h, Color bg, String xml, HttpServletResponse response,String imgpath)
			throws IOException, SAXException, ParserConfigurationException{
		BufferedImage image = mxUtils.createBufferedImage(w, h, bg);
		if(image != null){
			Font font = new Font("楷体", Font.BOLD, 12); 
			Graphics2D g2 = image.createGraphics();
			g2.setFont(font);
			g2.setColor(Color.WHITE);//图片背景颜色 
			mxUtils.setAntiAlias(g2, true, true);
			renderXml(xml, createCanvas(url, g2));
			ImageIO.write(image, "png", new File(imgpath));
		}
	}
	
	protected void renderXml(String xml, mxICanvas2D canvas) throws SAXException, ParserConfigurationException, IOException{
		XMLReader reader = parserFactory.newSAXParser().getXMLReader();
		reader.setContentHandler(new mxSaxOutputHandler(canvas));
		reader.parse(new InputSource(new StringReader(xml)));
	}
	
	protected mxGraphicsCanvas2D createCanvas(String url, Graphics2D g2){
		final Hashtable<String, Image> shortCache = new Hashtable<String, Image>();
		final String domain = url.substring(0, url.lastIndexOf("/"));
		mxGraphicsCanvas2D g2c = new mxGraphicsCanvas2D(g2){
			public Image loadImage(String src){
				Hashtable<String, Image> cache = shortCache;
				if(src.startsWith(domain)){
					cache = imageCache;
				}
				Image image = cache.get(src);
				if(image == null){
					image = super.loadImage(src);
					if(image != null){
						cache.put(src, image);
					}else{
						cache.put(src, null);
					}
				}
				return image;
			}
		};
		return g2c;
	}
}
