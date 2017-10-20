package jehc.xtmodules.xtcore.allutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
     * 判断时间是否在时间段内（不包含边界点）
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        if(nowTime.getTime() > beginTime.getTime() && nowTime.getTime() < endTime.getTime()){
     	   return true;
        }else{
     	   return false;
        }
    }
    
    /**
     * 判断时间是否在时间段内（包含边界点）
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendarInOv(Date nowTime, Date beginTime, Date endTime) {
        if(nowTime.getTime() >= beginTime.getTime() && nowTime.getTime() <= endTime.getTime()){
     	   return true;
        }else{
     	   return false;
        }
    }
    /* 
     * 将时间转换为时间戳
     */    
    public static long dateToStamp(String s) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(long start){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(start);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    /**
     * 平均计算开始时间 结束时间的平均时间段
     * @param startTime开始时间
     * @param endtime结束时间
     * @param num 段数
     * @return
     */
    public List<Map<String,Object>> avgTimes(String startTime,String endtime,int num){
    	//平均划分时间段
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			long start = DateUtil.dateToStamp(startTime);
			long end = DateUtil.dateToStamp(endtime);
			long start_end = end-start;//当前时间与开始时间差
			long ceTims = start_end/num;//当前时间距离结束时间的需要执行的次数
			for(int i = 0; i < num; i++){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("st1", DateUtil.stampToDate(start));
				map.put("st2", DateUtil.stampToDate(start+ceTims));
				list.add(map);
				start = start+ceTims;
			}
		} catch (ParseException e) {
		}
		return list;
    }
    
    /**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatHMDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
    public static void main(String[] args) {
    	System.out.println(getDays());
    	System.out.println(getAfterDayWeek("3"));
    	
    	//平均划分时间段
    	try {
			int num = 10;
			long start = DateUtil.dateToStamp("2017-10-20 00:00");
			long end = DateUtil.dateToStamp("2017-10-20 10:00");
			long start_end = end-start;//当前时间与开始时间差
			long ceTims = start_end/num;//当前时间距离结束时间的需要执行的次数
			for(int i = 0; i < num; i++){
				System.out.println("时间段:-----------"+DateUtil.stampToDate(start)+"-----"+DateUtil.stampToDate(start+ceTims)+"------------");
				start = start+ceTims;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
    	try {
			Date date = sdf.parse("2017-10-19 11:35:01");
			boolean flag = DateUtil.belongCalendar(date, DateUtil.fomatHMDate("2017-10-19 11:15"), DateUtil.fomatHMDate("2017-10-19 11:33"));
	    	System.out.println(flag);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
