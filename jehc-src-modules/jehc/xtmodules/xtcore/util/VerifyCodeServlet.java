package jehc.xtmodules.xtcore.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码 
 * @author 邓纯杰
 *
 */
@SuppressWarnings("serial")
public class VerifyCodeServlet extends HttpServlet {
	public static final int WIDTH = 200;// 生成的图片的宽度
	public static final int HEIGHT = 30;// 生成的图片的高度
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// 设置响应头通知浏览器以图片的形式打开
		response.setContentType("image/jpeg");// 等同于response.setHeader("Content-Type",
		// 设置响应头控制浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 1.在内存中创建一张图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		// 2.虚拟画笔得到图片
		Graphics g = image.getGraphics();
		// 3.设置图片的背影色
		setBackGround(g);
		// 4.设置图片的边框
		setBorder(g);
		// 5.在图片上画干扰线
		drawRandomLine(g);
		// 6.写在图片上随机数
		String random = drawRandomNum((Graphics2D) g);// 生成数字和字母组合的验证码图片

		// 7.将随机数存在session中
		HttpSession session=request.getSession(true);
		session.setAttribute("validateCode", random);
		//两分钟之内失效
		//session.setMaxInactiveInterval(2*60);

		// 8.将图片写给浏览器
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	// 验证码图片的宽度。  
    private int width = 160;  
  
    // 验证码图片的高度。  
    private int height = 26;  
  
    // 验证码字符个数  
    private int codeCount = 6;  
  
    private int x = 0;  
  
    // 字体高度  
    private int fontHeight;  
  
    private int codeY;  
  
    char[] codeSequence = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',  
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; 
//    char[] codeSequence = {'1', '2', '3', '4',  
//            '5', '6', '7', '8', '9', '0' }; 
    	
	public void init() throws ServletException {
        x = width / (codeCount + 1);  
        fontHeight = height - 2;  
        codeY = height - 4;  
  
    }  
	private Random random = new Random();
	private Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		// 定义图像buffer  
//        BufferedImage buffImg = new BufferedImage(width, height,  
//                BufferedImage.TYPE_INT_RGB); 
//		BufferedImage buffImg = new BufferedImage(width, height,  
//                BufferedImage.TYPE_BYTE_GRAY); 
//		BufferedImage buffImg = new BufferedImage(width, height,  
//                BufferedImage.SCALE_SMOOTH); 
		BufferedImage buffImg = new BufferedImage(width, height,  
                BufferedImage.TYPE_USHORT_565_RGB); 
        Graphics2D g = buffImg.createGraphics();  
  
        // 创建一个随机数生成器类  
        Random random = new Random();  
  
        // 将图像填充为白色  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width, height);  
  
        // 创建字体，字体的大小应该根据图片的高度来定。  
        //Font font = new Font("Fixedsys", Font.ITALIC, fontHeight); 
//        Font font = new Font("楷体", Font.LAYOUT_RIGHT_TO_LEFT, fontHeight); 
//        Font font = new Font("宋体",Font.ITALIC+Font.BOLD, fontHeight); 
//        Font font = new Font("Courier", Font.ITALIC, fontHeight); 
        Font font = new Font("TimesRoman", Font.ITALIC, fontHeight); 
        
        
        // 设置字体。  
        g.setFont(font);  
  
        // 画边框。  
        //g.setColor(Color.GRAY);  
        g.drawRect(0, 0, width-3, height - 2);  
  
        // 随机产生15条干扰线，使图象中的认证码不易被其它程序探测到。  
        g.setColor(getRandColor(160,200));
        for (int i = 0; i < 50; i++) {  
            int x = random.nextInt(width);  
            int y = random.nextInt(height);  
            int xl = random.nextInt(12);  
            int yl = random.nextInt(12);  
            g.drawLine(x, y, x + xl, y + yl);  
        }  
  
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。  
        StringBuffer randomCode = new StringBuffer();  
        int red = 0, green = 0, blue = 0;  
  
        // 随机产生codeCount数字的验证码。  
        for (int i = 0; i < codeCount; i++) {  
            // 得到随机产生的验证码数字。  
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]).toLowerCase();  
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。  
            red = random.nextInt(255);  
            green = random.nextInt(255);  
            blue = random.nextInt(255);  
  
            // 用随机产生的颜色将验证码绘制到图像中。  
            g.setColor(new Color(red, green, blue));             
            g.drawString(strRand, (i + 1) * x, codeY);  
            
            // 将产生的四个随机数组合在一起。  
            randomCode.append(strRand);  
        }  
        // 将四位数字的验证码保存到Session中。  
        HttpSession session = request.getSession();  
        session.setAttribute("validateCode", randomCode.toString());  
  
        // 禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
  
        response.setContentType("image/jpeg");  
  
        // 将图像输出到Servlet输出流中。  
        ServletOutputStream sos = response.getOutputStream();  
        ImageIO.write(buffImg, "jpeg", sos);  
        sos.close();
	}
	**/
	/**
	 * 设置图片的背景色
	 * @param g
	 */
	private void setBackGround(Graphics g) {
		// 设置颜色
		g.setColor(Color.WHITE);
		// 填充区域
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * 设置图片的边框
	 * @param g
	 */
	private void setBorder(Graphics g) {
		// 设置边框颜色
		g.setColor(Color.WHITE);
		// 边框区域
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	/**
	 * 在图片上画随机线条
	 * @param g
	 */
	private void drawRandomLine(Graphics g) {
		// 设置颜色
//		g.setColor(Color.GREEN);
		Random r=new Random();
		// 设置线条个数并画线
		for (int i = 0; i < 10; i++) {
			int x1 = r.nextInt(WIDTH);
			int y1 = r.nextInt(HEIGHT);
			int x2 = r.nextInt(WIDTH);
			int y2 = r.nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
			//设置干扰线颜色
			Color color = new Color(20 + r.nextInt(210), 20 + r.nextInt(210), 20 + r.nextInt(210));
			g.setColor(color);
		}

	}

	/**
	 * 画随机字符
	 * @param g
	 */
	private String drawRandomNum(Graphics2D g) {
		// 设置颜色
		g.setColor(Color.RED);
		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, 30));
		
		// 数字和字母的组合
		String baseNumLetter = "Aa0Bb1CcDd3EeFf5Gg6HhJjKkLl7MmN9nOoPp8QqRrSs2TtUuVv4WwXxYyZz";
		return createRandomChar(g, baseNumLetter);
	}

	/**
	 * 创建随机字符
	 * @param g
	 * @param baseChar
	 * @return 随机字符
	 */
	private String createRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
		 Random r=new Random();
		int x = 5;
		String ch = "";
		// 控制字数
		for (int i = 0; i < 6; i++) {
			// 设置字体旋转角度
			int degree = r.nextInt() % 30;
			ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
			//设置随机数的颜色
			 Color color = new Color(20 + r.nextInt(210), 20 + r.nextInt(210), 20 + r.nextInt(210));  
	           g.setColor(color); 
			sb.append(ch);
			// 正向角度
			g.rotate(degree * Math.PI / 180, x, 30);
			g.drawString(ch, x, 20);
			// 反向角度
			g.rotate(-degree * Math.PI / 180, x, 30);
			x += 30;
		}
		return sb.toString();
	}
}
