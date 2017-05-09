package jehc.xtmodules.xtcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static void main(String[] args) throws ParseException
	{
//		System.out.println(getDateByShortStr("2007-2-6"));
		String str = "2017-01-19 11:30:04";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 String s = String.valueOf(sdf.parse(str).getTime());  
		 System.out.println(s);
	}
	/**
	 * 根据时间字符串返回Date对象
	 * @param dateStr,可以接受3种格式分别是:yyyy-MM-dd,yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateByStr(String dateStr)
	{
		SimpleDateFormat formatter = null;
		if(dateStr.length()==10) formatter=new SimpleDateFormat("yyyy-MM-dd");
		else if(dateStr.length()==16) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if(dateStr.length()==19) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else
		{
			System.out.println("日期字符串格式错误!");
			return null;
		}
		try
		{
			return formatter.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getMinMonthDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE,1);
        return DateUtil.get00_00_00Date(cal.getTime());
	}
	
	
	public static Date getMaxMonthDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE,1);
		cal.add(Calendar.MONTH,1);
		cal.add(Calendar.DATE,-1);
		return DateUtil.get23_59_59Date(cal.getTime());
	}
	
	
	
	public static Date getStartDayDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK,1);

		return DateUtil.get00_00_00Date(cal.getTime());

	}
	
	public static Date getEndDayDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK,7);

		return DateUtil.get23_59_59Date(cal.getTime());
	}
	
	/**
	 * 根据时间字符串返回最大Date对象
	 * @param dateStr,可以接受3种格式分别是:yyyy-MM-dd,yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getMaxDateByStr(String dateStr)
	{
		SimpleDateFormat formatter = null;
		if(dateStr.length()==10){
			formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr+=" 23:59:59";
		}
		else
		{
			System.out.println("日期字符串格式错误!");
			return null;
		}
		try
		{
			return formatter.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据时间字符串返回最小Date对象
	 * @param dateStr,可以接受3种格式分别是:yyyy-MM-dd
	 * @return
	 */
	public static Date getMinDateByStr(String dateStr)
	{
		SimpleDateFormat formatter = null;
		if(dateStr.length()==10){
			formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr+=" 00:00:00";
		}
		else
		{
			System.out.println("日期字符串格式错误!");
			return null;
		}
		try
		{
			return formatter.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据时间字符串返回Date对象
	 * @param dateStr,可以接受3种格式分别是:yyyy-MM-dd,yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateByStr(String dateStr,String formatters)
	{
		SimpleDateFormat formatter = null;
		formatter=new SimpleDateFormat(formatters);
	
		try
		{
			return formatter.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 返回日期的字符串
	 * @param date Date对象
	 * @param format 例如:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getStrByDate(Date date,String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	/**
	 * 返回日期的字符串,年-月-日
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getStrYMDByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd");
	}
	/**
	 * 返回日期的字符串,时:分:秒
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getStrHMSByDate(Date date)
	{
		return getStrByDate(date,"HH:mm:ss");
	}
	
	public static String getStrHMByDate(Date date)
	{
		return getStrByDate(date,"HH:mm");
	}
	/**
	 * 返回日期的字符串,年-月-日 时:分:秒
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getStrYMDHMSByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 返回日期的字符串,年月日 时:分
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getStrYMDHMByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd HH:mm");
	}
	/**
	 * 对天数进行加减运算
	 * @param date 原来的时间
	 * @param days 正数为加,负数为减
	 * @return 返回运算后的时间
	 */
	public static Date addDay(Date date,Integer days)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	/**
	 * 对月数进行加减运算
	 * @param date 原来的时间
	 * @param days 正数为加,负数为减
	 * @return 返回运算后的时间
	 */
	public static Date addMonth(Date date,Integer months)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	/**
	 * 返回中文时间格式
	 * @param object 可以为Date对象或2007-06-12格式的字符串
	 * @return
	 */
	public static String toChinese(Object object)
	{
		String dateStr=null;
		if(object instanceof Date) dateStr=getStrYMDByDate((Date)object);
		else if(object instanceof String) dateStr=(String)object;
		else return dateStr;
		String[] cnArray={"〇","一","二","三","四","五","六","七","八","九"};
		String year=dateStr.split("-")[0];
		String month=dateStr.split("-")[1];
		String date=dateStr.split("-")[2];
		dateStr="";
		for(int i=0;i<year.length();i++) dateStr+=cnArray[Integer.valueOf(String.valueOf(year.charAt(i)))];
		dateStr+="年";
		if("10".equals(month)) dateStr+="十";
		else
		{
			int num=Integer.valueOf(String.valueOf(month.charAt(1)));
			if("0".equals(String.valueOf(month.charAt(0)))) dateStr+=cnArray[num];
			else dateStr+="十"+cnArray[num];
		}
		dateStr+="月";
		if("10".equals(date)) dateStr+="十";
		else
		{
			String temp=String.valueOf(date.charAt(0));
			if("1".equals(temp)) dateStr+="十";
			else if("2".equals(temp)) dateStr+="二十";
			else if("3".equals(temp)) dateStr+="三十";
			if(!"0".equals(String.valueOf(date.charAt(1)))) dateStr+=cnArray[Integer.valueOf(String.valueOf(date.charAt(1)))];
		}
		dateStr+="日";
		return dateStr;
	}
    
    public static String toChineseLower(Date date,boolean hasHms){
        SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(sdf.format(date));
        String targetname = sdf.format(date).substring(0,4) + "年";
        targetname += sdf.format(date).substring(5,7) + "月";
        targetname += sdf.format(date).substring(8,10) + "日";
        if(hasHms){
            targetname += sdf.format(date).substring(11,13) + "时";
            targetname += sdf.format(date).substring(14,16) + "分";
            targetname += sdf.format(date).substring(17,19) + "秒";
        }
       // System.out.println(targetname);
        return targetname;
    }
    
	/**
	 * 返回星期几
	 * @param object Date对象或者字符串,yyyy-MM-dd
	 * @return 星期五
	 */
	@SuppressWarnings("deprecation")
	public static String getWeek(Object object)
	{
		Date date=null;
		if(object instanceof Date) date=(Date)object;
		else if(object instanceof String) date=getDateByStr((String)object);
		else return "";
		String[] cnWeek={"日","一","二","三","四","五","六"};
		return "星期"+cnWeek[date.getDay()];
	}
	public static Date get00_00_00Date(Date date)
	{
		return getDateByStr(getStrYMDByDate(date));
	}
	public static Date get23_59_59Date(Date date)
	{
		return getDateByStr(getStrYMDHMSByDate(date).substring(0,10)+" 23:59:59");
	}
	
	public static Date getWeekStartDate_Sunday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return cal.getTime();
	}
	public static Date getWeekStartDate_Monday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return cal.getTime();
	}
	public static Date getWeekEndDate_Sunday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(1==cal.get(Calendar.DAY_OF_WEEK)) return date;
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return DateUtil.addDay(cal.getTime(), 7);
	}
	public static Date getWeekEndDate_Saturday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(7==cal.get(Calendar.DAY_OF_WEEK)) return date;
		cal.set(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}
	
	public static Date getDateByShortStr(String dateStr)
	{
		try
		{
			String[] dateStrArray=dateStr.split("-");
			String dateStrYear="20"+dateStrArray[0];
			String dateStrMonth=dateStrArray[1];
			String dateStrDay=dateStrArray[2];
			if(1==dateStrMonth.length()) dateStrMonth="0"+dateStrMonth;
			if(1==dateStrDay.length()) dateStrDay="0"+dateStrDay;
			return getDateByStr(dateStrYear+"-"+dateStrMonth+"-"+dateStrDay);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回SimpleDateFormat
	 * @return
	 */
	public static String getSimpleDateFormat(){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		 return sdf.format(new Date());
	}
}
