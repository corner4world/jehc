package jehc.xtmodules.xtcore.allutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 工具类
 * @author 邓纯杰
 * @version 1.0
 */
public class AllUtils {
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param filePath  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	/**
	 * 检测KEY是否正确
	 * @param paraname  传入参数
	 * @param FKEY		接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY){
		paraname = (null == paraname)? "":paraname;
		return MD5.md5(paraname+DateUtil.getDays()+",fh,").equals(FKEY);
	}
	 
	/**
	 * 读取txt里的单行内容
	 * @param filePath  文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	/**
	 * @return WebRoot目录的绝对路径
	*/
	public static String getWebRootAbsolutePath() {
		String path = null;
		String folderPath = AllUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (folderPath.indexOf("WEB-INF") > 0) {
			path = folderPath.substring(0, folderPath.indexOf("WEB-INF/classes"));
		}
		return path;
	}
	
	/**
	 * 获得随机文件名,保证在同一个文件夹下不同名
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static String getRandom() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Random random = new Random();
		int rd = random.nextInt(1000000); //产生随机数10000以内
		return sdf.format(new Date())+rd;
	}
	
	/**
	 * 取出List<string>
	 * @param ls
	 * @return
	 */
	public static List<String> getNList(List<String> ls){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<ls.size(); i++){
            String str = ls.get(i);
            if(!list.contains(str)){  
                list.add(str);
            }
        }
        return list;
    }
	
	/**
	 * 格式化XML
	 * @param inputXML
	 * @return
	 * @throws Exception
	 */
	public static String formatXML(String inputXML) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(inputXML));
		String requestXML = null;
		XMLWriter writer = null;
		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = new OutputFormat(" ", true);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXML = stringWriter.getBuffer().toString();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return requestXML;
	}
	
	/**
	 * 检测URL是否合法
	 * @param urlStr
	 * @return
	 */
	public boolean isConnect(String urlStr){
		if(urlStr == null || urlStr.length() <= 0){
			return false;
		}
		try{
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			int state = con.getResponseCode();
			if(state == 200){
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
     * 获取十六进制的颜色代码
     * @return String
     */
	public static String getRandColorCode(){
	  String r,g,b;
	  Random random = new Random();
	  r = Integer.toHexString(random.nextInt(256)).toUpperCase();
	  g = Integer.toHexString(random.nextInt(256)).toUpperCase();
	  b = Integer.toHexString(random.nextInt(256)).toUpperCase();
	  r = r.length()==1 ? "0" + r : r ;
	  g = g.length()==1 ? "0" + g : g ;
	  b = b.length()==1 ? "0" + b : b ;
	  return r+g+b;
	 }
	
	private static final double PI = 3.14159265; // 圆周率
	private static final double EARTH_RADIUS = 6378137; // 地球半径
	private static final double RAD = Math.PI / 180.0; // 一百八十度角

	/**
	 * @param raidus
	 * 单位米 return minLat 
	 * 最小经度 minLng 
	 * 最小纬度 maxLat 
	 * 最大经度 maxLng 
	 * 最大纬度 minLat
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;// 传值给经度
		Double longitude = lon;// 传值给纬度

		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLat = latitude - radiusLat;
		// 获取最大纬度
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		//获取最小经度
		Double minLng = longitude - radiusLng;
		// 获取最大经度
		Double maxLng = longitude + radiusLng;
		
		System.out.println("jingdu" + minLat + "weidu" + minLng + "zuidajingdu"
				+ maxLat + "zuidaweidu" + maxLng);

		return new double[] { minLat, minLng, maxLat, maxLng };
	}
	
	
    private static double rad(double d) {  
        return d * Math.PI / 180.0;  
    }
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 用户经度
     * @param lng1 用户纬度
     * @param lat2 商家经度
     * @param lng2 商家纬度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
    	Double lat1 = Double.parseDouble(lat1Str);
    	Double lng1 = Double.parseDouble(lng1Str);
    	Double lat2 = Double.parseDouble(lat2Str);
    	Double lng2 = Double.parseDouble(lng2Str);
    	double patm = 2;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = patm * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / patm), patm)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / patm), patm)));
        distance = distance * EARTH_RADIUS;
        String distanceStr = String.valueOf(distance);
        return distanceStr;
    }
    
    /**
	 * 获取当前用户一定距离以内的经纬度值
	 * 单位米 return minLat 
	 * 最小经度 minLng 
	 * 最小纬度 maxLat 
	 * 最大经度 maxLng 
	 * 最大纬度 minLat
	 */
	public static Map getAround(String latStr, String lngStr, String raidus) {
		Map map = new HashMap();
		Double latitude = Double.parseDouble(latStr);// 传值给经度
		Double longitude = Double.parseDouble(lngStr);// 传值给纬度

		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = Double.parseDouble(raidus);
		
		Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		//获取最小经度
		Double minLat = longitude - radiusLng;
		// 获取最大经度
		Double maxLat = longitude + radiusLng;
		
		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLng = latitude - radiusLat;
		// 获取最大纬度
		Double maxLng = latitude + radiusLat;
		
		map.put("minLat", minLat+"");
		map.put("maxLat", maxLat+"");
		map.put("minLng", minLng+"");
		map.put("maxLng", maxLng+"");
		return map;
	}
	
	
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	private final static String[] ABC = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U","V","W","X","Y","Z"};
	private final static String[] NUM = new String[]{"0","1","2","3","4","5","6","7","8","9"};
	/**
	 * 返回长度45的唯一值
	 * @return String
	 */
	public static String getID45(){
		Random random = new Random();		
		String id = UUID.randomUUID().toString() + "-";
		for(int i=0;i<8;i++){
			id += ABC[random.nextInt(24)];
		}
		return id.toLowerCase();
	}
	/**
	 * 
	 * @return
	 */
	public static String getID20(){
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		String id = null;
		for(int i=0;i<10;i++){
			if((random.nextInt(10)%2==1)){
				sb.append(ABC[random.nextInt(24)]);
			}else{
				sb.append(NUM[random.nextInt(10)]);
			}
		}
		for(int i=0;i<10;i++){
			if((random.nextInt(10)%2==0)){
				sb.append(ABC[random.nextInt(24)]);
			}else{
				sb.append(NUM[random.nextInt(10)]);
			}
		}		
		if(sb != null){
			id = sb.toString().toLowerCase();
		}
		return id;
	}
	
//	/**  
//     * 将汉字转换为全拼  
//     * @param src  
//     * @return  
//     */
//    public static String getPinYin(String src) {  
//        char[] t1 = null;  
//        t1 = src.toCharArray();  
//        String[] t2 = new String[t1.length];  
//        // 设置汉字拼音输出的格式  
//        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
//        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
//        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
//        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
//        String t4 = "";  
//        int t0 = t1.length;  
//        try {  
//            for (int i = 0; i < t0; i++) {  
//                // 判断能否为汉字字符  
//                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
//                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中  
//                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后  
//                } else {  
//                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后  
//                    t4 += Character.toString(t1[i]);  
//                }  
//            }  
//        } catch (BadHanyuPinyinOutputFormatCombination e) {  
//            e.printStackTrace();  
//        }  
//        return t4;  
//    }  
//  
//    /**  
//     * 提取每个汉字的首字母  
//     *   
//     * @param str  
//     * @return  
//     */
//    public static String getPinYinHeadChar(String str) {  
//        String convert = "";  
//        for (int j = 0; j < str.length(); j++) {  
//            char word = str.charAt(j);  
//            // 提取汉字的首字母  
//            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
//            if (pinyinArray != null) {  
//                convert += pinyinArray[0].charAt(0);  
//            } else {  
//                convert += word;  
//            }  
//        }  
//        return convert;  
//    } 
  
    /**  
     * 将字符串转换成ASCII码  
     *   
     * @param cnStr  
     * @return  
     */  
    public static String getCnASCII(String cnStr) {  
        StringBuffer strBuf = new StringBuffer();  
        // 将字符串转换成字节序列  
        byte[] bGBK = cnStr.getBytes();  
        for (int i = 0; i < bGBK.length; i++) {  
            // 将每个字符转换成ASCII码  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
        }  
        return strBuf.toString();  
    }
   /* public static void main(String[] args) {  
        String cnStr = "地鼠系统";  
        System.out.println(getPinYin(cnStr));//全拼
        System.out.println(getPinYinHeadChar(cnStr));//简拼  
        System.out.println(getCnASCII(cnStr)); //ASCII码
    }*/
}
