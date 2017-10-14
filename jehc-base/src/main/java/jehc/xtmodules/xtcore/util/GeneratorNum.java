package jehc.xtmodules.xtcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 产生单号
 * @author 邓纯杰
 * 
 */
public class GeneratorNum {
	public static String generatorOrderID() {
		Date date = new Date();
		// 年+月+日+时+分+秒+毫秒
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddkkmmssSS");
		String sdate = format.format(date);
		Random random = new Random();
		int number = random.nextInt(999999999);
		String randomName = sdate + number;
		return randomName;
	}
	public static void main(String[] args){
		System.out.println(GeneratorNum.generatorOrderID());
	}
}
