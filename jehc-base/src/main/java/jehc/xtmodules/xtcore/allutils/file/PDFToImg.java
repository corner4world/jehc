package jehc.xtmodules.xtcore.allutils.file;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * PDF转图片
 * @author邓纯杰
 * 
 */
public class PDFToImg {
	public final static String IMG_TYPE_JPG = "jpg";
	public final static String IMG_TYPE_PNG = "png";
	public static void main(String[] args) throws IOException {
		PDFToImg PDFToImg = new PDFToImg();
		PDFToImg.pdf2img("D:\\test\\1.pdf", "D:\\test\\", IMG_TYPE_PNG);
		/**
		PDDocument pdDocument = PDFToImg.pdfInfo("D:\\test\\1.pdf");
		**/
	}
	/**
	 * 
	 * @param pdfPath pdf文件的路径
	 * @param savePath  图片保存的地址
	 * @param imgType  图片保存方式
	 */
	@SuppressWarnings("unchecked")
	public void pdf2img(String pdfPath, String savePath, String imgType) {
		String fileName = pdfPath.substring(pdfPath.lastIndexOf("\\") + 1,pdfPath.length());
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		InputStream is = null;
		PDDocument pdDocument = null;
		try{
			is = new BufferedInputStream(new FileInputStream(pdfPath));
			PDFParser parser = new PDFParser(is);
			parser.parse();
			pdDocument = parser.getPDDocument();
			List<PDPage> pages = pdDocument.getDocumentCatalog().getAllPages();
			for(int i = 0; i < pages.size(); i++) {
				String saveFileName = savePath + "\\" + fileName + i + "."+ imgType;
				PDPage page = pages.get(i);
				pdfPage2Img(page, saveFileName, imgType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pdDocument != null){
				try{
					pdDocument.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * pdf页转换成图片
	 * @param page
	 * @param saveFileName
	 * @throws IOException
	 */
	public void pdfPage2Img(PDPage page, String saveFileName, String imgType)throws IOException {
		BufferedImage img_temp = page.convertToImage();
		Iterator<ImageWriter> it = ImageIO.getImageWritersBySuffix(imgType);
		ImageWriter writer = (ImageWriter) it.next();
		ImageOutputStream imageout = ImageIO.createImageOutputStream(new FileOutputStream(saveFileName));
		writer.setOutput(imageout);
		writer.write(new IIOImage(img_temp, null, null));
	}

	/**
	 * 获取pdf信息
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public PDDocument pdfInfo(String filePath) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filePath));
		PDFParser parser = new PDFParser(is);
		parser.parse();
		PDDocument pdDocument = parser.getPDDocument();
		System.out.println("pageNum:" + pdDocument.getNumberOfPages());
		return pdDocument;
	}

	/**
	 * 创建pdf
	 * @throws COSVisitorException
	 * @throws IOException
	 */
	public void createPdf() throws COSVisitorException, IOException {
		PDDocument document = new PDDocument();
		PDPage blankPage = new PDPage();
		document.addPage(blankPage);
		document.save("D:\\test.pdf");
		document.close();
	}
}
