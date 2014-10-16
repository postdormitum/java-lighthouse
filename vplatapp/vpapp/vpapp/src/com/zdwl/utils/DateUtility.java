package com.zdwl.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 日期工具类
 * @author Administrator
 *
 */
@SuppressWarnings({"unchecked","unused"})
public class DateUtility {

	private static final SimpleDateFormat cnDateFormater = new SimpleDateFormat("yyyy年M月d日");
	
	private static final SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat fullTimeFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	 private int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化   
	 private int MaxDate; // 一月最大天数   
	 private int MaxYear; // 一年最大天数  
	 
	 /**
	  * 
	   
	 * getWeekByDate(获取日期是当前月的第几周)   
	 * @param   name   
	   
	 * @param  @return    设定文件   
	   
	 * @return String    DOM对象   
	   
	 * @Exception 异常对象   
	   
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	  */
	 public static int getWeekByDate(String day) throws ParseException{
		 Date date = dateFormater.parse(day);
		 Calendar ca = Calendar.getInstance();
		 ca.setFirstDayOfWeek(Calendar.MONDAY);
		 ca.setTime(date);
		 return ca.get(Calendar.WEEK_OF_MONTH);
	 }
	
	/**
	 * 获取本月第一天的日期
	 * @return
	 */
	public static Date getFirstDayOfLocalMonth() {
		Calendar cal = Calendar.getInstance();
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DATE, 1 - nowDay);
		return cal.getTime();
	}
	
	/**
	 * 通过日期字符串获取本月第一天的日期
	 * @return
	 */
	public static Date getFirstDayOfLocalMonth(String strDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(strDate));
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DATE, 1 - nowDay);
		return cal.getTime();
	}
	
	/**
	 * 日期格式化器 
	 * @param date
	 * @return 返回格式例如：“2010-11-02”
	 */
	public static String formatDate(Date date) {
		if(date != null) {
			return dateFormater.format(date);
		} else {
			return "";
		}
	}
	
	public static String fullFormater(Date date){
		if(date != null) {
			return fullTimeFormater.format(date);
		} else {
			return "";
		}
	}
	
	
	/**
	 * 日期格式化器 
	 * @param date
	 * @return 返回格式例如：“2010年1月12日”
	 */
	public static String formatDate_CN(Date date) {
		if(date != null) {
			return cnDateFormater.format(date);
		} else {
			return "";
		}
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 8:42:47 AM
	 * @Description: 将日期文字转换为Date日期对象 
	 * @param dateStr 格式如：“2010-08-15”
	 * @return    
	 * @return Date   
	 * @throws
	 */
	public static Date parseDate(String dateStr) {
		try {
			return dateFormater.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 8:42:47 AM
	 * @Description: 将日期文字转换为Date日期对象 
	 * @param dateStr 格式如：“2010-08-15”
	 * @return    
	 * @return Date   
	 * @throws
	 */
	public static Date parseFullDate(String dateStr) {
		try {
			return fullTimeFormater.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	/**
	 * 
	 * @date Dec 13, 2010 8:52:26 AM
	 * @Description: 获取昨天起始时间的字符串
	 * @return 例如：“2010-04-15 00:00:00”   
	 * @throws
	 */
	public static String getYesterdayBeginTimeStr() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return fullTimeFormater.format(cal.getTime());
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 8:52:26 AM
	 * @Description: 获取今天起始时间的字符串
	 * @return 例如：“2010-04-16 00:00:00”   
	 * @throws
	 */
	public static String getTodayBeginTimeStr() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return fullTimeFormater.format(cal.getTime());
	}
	
	/**
	 * 
	 * @Description: 获取今天起始时间的滴答值
	 * @return 例如：“2010-04-16 00:00:00”   
	 * @throws
	 */
	public static long getTodayBeginTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 8:52:26 AM
	 * @Description: 获取今天结束时间的字符串
	 * @return 例如：“2010-04-16 00:00:00”   
	 * @throws
	 */
	public static String getTodayEndTimeStr() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return fullTimeFormater.format(cal.getTime());
	}
	
	/**
	 * 
	 * @Description: 获取今天结束时间的滴答值
	 * @return 例如：“2010-04-16 00:00:00”   
	 * @throws
	 */
	public static long getTodayEndTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime().getTime();
	}
	
	/**
	 * 
	 * @date Dec 27, 2010 2:05:03 PM
	 * @Description: 获取现在的年份和月份
	 * @return 数组第一个元素是年份(array[0]) 第二个元素是月份(array[1])
	 * @throws
	 */
	public static int[] getYearAndMonthOfNowTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return new int[]{year, month};
	}
	
	/**
	 * 获取当前年和月份字符串
	 * @return
	 */
	public static String getCurYearAndMonth(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return year + "-" + (month < 10 ? ("0" + month) : month);
	}
	/**
	 * 
	 * @date Dec 13, 2010 9:33:06 AM
	 * @Description: 获取上个月的年份
	 * @return    
	 * @return int   
	 * @throws
	 */
	public static int getYearOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 9:33:30 AM
	 * @Description: 获取上个月的月份
	 * @return    
	 * @return int   
	 * @throws
	 */
	public static int getMonthOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	
	
	/**
	 * 
	 * @date Dec 13, 2010 9:33:06 AM
	 * @Description: 获取上两个月的年份
	 * @return    
	 * @return int   
	 * @throws
	 */
	public static int getYearOfLastsMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 
	 * @date Dec 13, 2010 9:33:30 AM
	 * @Description: 获取上两个月的月份
	 * @return    
	 * @return int   
	 * @throws
	 */
	public static int getMonthOfLastsMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	
	
	/**
	 * 
	 * @date Dec 13, 2010 9:43:57 AM
	 * @Description: 获取上个月的年和月组成的字符串
	 * @return 格式如："2010-09"   
	 * @return String   
	 * @throws
	 */
	public static String getYearMonthStrOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return year + "-" + (month < 10 ? ("0" + month) : month);
	}
	
	/**  
     * 获得当前月份  
     *   
     * @return  
     */  
    public static int getMonth() {   
        return Calendar.getInstance().get(Calendar.MONTH) + 1;   
    }  
	
    /**  
     * 获得今天是这个月的第几周  
     *   
     * @return  
     */  
    public static int getWeekOfMonth() {   
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);   
    } 
    
    /**  
     * 获取当天时间  
     *   
     * @param dateformat  
     * @return  
     */  
    public String getNowTime(String dateformat) {   
        Date now = new Date();   
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式   
        String hehe = dateFormat.format(now);   
        return hehe;   
    }
    
    /**  
     * 获得当前年份  
     *   
     * @return  
     */  
    public static int getYear() {   
        return Calendar.getInstance().get(Calendar.YEAR);   
    }   
    
    /**  
     * 获得本周一的日期  
     *   
     * @return  
     */  
    public  String getMondayOFWeek() {   
        weeks = 0;   
        int mondayPlus =this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus);   
        Date monday = currentDate.getTime();   
  
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    } 
    
    /**  
     * 获得当前日期与本周日相差的天数  
     *   
     * @return  
     */  
    private  int getMondayPlus() {   
        Calendar cd = Calendar.getInstance();   
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......   
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1   
        if (dayOfWeek == 1) {   
            return 0;   
        } else {   
            return 1 - dayOfWeek;   
        }   
    }  
    
    /**  
     * 获得上周星期一的日期  
     *   
     * @return  
     */  
    public String getPreviousWeekday() {   
        //weeks--;   
        int mondayPlus = this.getMondayPlus();   
        GregorianCalendar currentDate = new GregorianCalendar();   
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * -1);   
        Date monday = currentDate.getTime();   
        DateFormat df = DateFormat.getDateInstance();   
        String preMonday = df.format(monday);   
        return preMonday;   
    }  
    
    /**  
     * 计算当月最后一天,返回字符串  
     *   
     * @return  
     */  
    public static String getDefaultDay() {   
        String str = "";   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        
        Calendar lastDate = Calendar.getInstance();   
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号   
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号   
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天   
        
        str = sdf.format(lastDate.getTime());   
        return str;   
    }  
    
    /**  
     * 传入日期字符串计算当月最后一天,返回字符串  
     *   
     * @return  
     */  
    public static String getDefaultDay(String strDate) {   
        String str = "";   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        
        Calendar lastDate = Calendar.getInstance();   
        lastDate.setTime(parseDate(strDate));
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号   
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号   
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天   
        
        str = sdf.format(lastDate.getTime());   
        return str;   
    } 
    
    public static String weekEndDate(String date) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Date d = sdf.parse(date);
    	Calendar lastDate = Calendar.getInstance(); 
    	lastDate.setTime(d);
    	lastDate.add(Calendar.DAY_OF_MONTH,+6);
    	String str = sdf.format(lastDate.getTime());
    	return str;
    }
    
    /**
     * 中国周一
     * @return
     */
    public static String findWeekOfMondayByZh(){
    	Calendar cd = Calendar.getInstance(Locale.CHINA);
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        cd.setTimeInMillis(System.currentTimeMillis());
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dateFormater.format(cd.getTime());
    }
    
    /**
     * 返回Long类型的毫秒数
     * @param str
     * @return
     * @throws Exception
     */
    public static Long getTimeByDateStr(String str) throws Exception{
    	Long temp = 0L;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	Date date = sdf.parse(str);
    	temp = date.getTime();
    	return temp;
    }
    
    /**
     * 返回保质期""年""月""天
     * @param begin生产日期
     * @param end失效日期
     * @return
     * @throws Exception
     */
    public static String getDateByHms(Long begin,Long end) throws Exception{
    	Long cha=end-begin;
    	Long getDay=cha/(1000*60*60*24);//获取总天数(以天为单位)
    	
    	/*Long year=getDay/365;//年
    	
    	Long day1=getDay%365;//余得的天数
    	
    	Long month=day1/30;//月
    	
    	Long day=day1%30;//天	   	 		
*/    	
    	String str="";
    	/*if(year!=0){
    		str+=year+"年";
    	}
    	if(month!=0){
    		str+=month+"月";
    	}*/
    	if(getDay!=0){
    		str+=getDay+1+"天";
    	}
    	return str==""?"/":str;
    }
    
      /**
     * 根据日期显示 返回星期几
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date date) {
    	String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    	if (w < 0)
    	w = 0;
    	return weekDays[w];
    }
    
    /**
     * 根据日期显示 返回星期几数字
     * @param dt
     * @return
     */
    public static int getWeekForInt(Date date) {
    	int[] weekDays = {0, 1, 2, 3, 4, 5, 6};
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    	if (w < 0)
    	w = 0;
    	return weekDays[w];
    }
    
    /**
     * 根据日期字符串返回日期所在周的星期一的日期
     * @param strdate
     * @return
     */
    public static String convertWeekByDate(String strdate){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(DateUtility.parseDate(strdate));
    	
    	int dayWeek = cal.get(Calendar.DAY_OF_WEEK);	//获取当前日期是一个星期的第几天
    	if(1 == dayWeek){
    		cal.add(Calendar.DAY_OF_MONTH, -1);
    	}
    	
    	//System.out.println("要计算的日期"+formatDate(cal.getTime()));
    	cal.setFirstDayOfWeek(Calendar.MONDAY);		//设置一个星期的第一天，按照中国习惯是星期一
    	
    	int day = cal.get(Calendar.DAY_OF_WEEK);	//
    	cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
    	
    	String strBegin = formatDate(cal.getTime());
    	//System.out.println("所在周星期一的日期："+strBegin);
    	return strBegin;
    	
    }
    
    
	public static void main(String[] args) throws Throwable {
		getDay(4);
		//stem.out.println(getWeekOfDate(new Date()));1372003200000 
		Long long1=1372608000000L;
		Long long2=1375286400000L;
		Long cha=long2-long1;
		
		Long year=cha/(1000*60*60*24*365);
		Long year1=cha/(1000*60*60*24)/365;
		System.out.println("系统计算有误"+year+"======"+year1);
		
		System.out.println(1000/(2*10)/10);
		System.out.println(1000/(2*10*10));
		System.out.println(weekEndDate("2014-02-01"));
		System.out.println(convertWeekByDate("2014-02-11"));
		//1372608000000
		//1375286400000
		//System.out.println(getDateByHms(1372608000000l,1375286400000l));
	}
	
	/**
	 * parse a string "yyyy-MM-DD hh:mm:ss" to date
	 * parse a string "yyyy-MM-DD" to date ("00:00:00")
	 */
	  public static Date parseToDate(String value)
	  {
	    Calendar calendar = Calendar.getInstance();
	    String syear = null; String smonth = null; String sday = null; String shour = null; String sminute = null; String ssecond = null;
	    int iStart = 0; int iEnd = 0;

	    if ((value == null) || (value.length() < 8)) return null;

	    if ((value.indexOf("年") > 0) && (value.indexOf("月") > 0) && (value.indexOf("日") > 0)) {
	      iStart = 0;
	      iEnd = value.indexOf("年");
	      if ((iStart >= 0) && (iEnd > 0)) syear = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(1, toInt(syear));

	      iStart = iEnd + "年".length();
	      iEnd = value.indexOf("月", iStart);
	      if ((iStart > 0) && (iEnd > 0)) smonth = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(2, toInt(smonth) - 1);

	      iStart = iEnd + "月".length();
	      iEnd = value.indexOf("日", iStart);
	      if ((iStart > 0) && (iEnd > 0)) sday = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(5, toInt(sday));
	    } else {
	      if (value.length() >= 4) syear = value.substring(0, 4);
	      else return null;
	      calendar.set(1, toInt(syear));
	      if (value.length() >= 7) smonth = value.substring(5, 7);
	      else return null;
	      calendar.set(2, toInt(smonth) - 1);
	      if (value.length() >= 10) sday = value.substring(8, 10);
	      else return null;
	      calendar.set(5, toInt(sday));
	    }
	    if ((value.indexOf("时") > 0) && (value.indexOf("分") > 0) && (value.indexOf("秒") > 0)) {
	      iStart = value.indexOf(" ", iEnd + "日".length());
	      iEnd = value.indexOf("时", iStart);
	      if ((iStart > 0) && (iEnd > 0)) shour = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(11, toInt(shour));

	      iStart = iEnd + "时".length();
	      iEnd = value.indexOf("分", iStart);
	      if ((iStart > 0) && (iEnd > 0)) sminute = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(12, toInt(sminute));

	      iStart = iEnd + "分".length();
	      iEnd = value.indexOf("秒", iStart);
	      if ((iStart > 0) && (iEnd > 0)) ssecond = value.substring(iStart, iEnd);
	      else return null;
	      calendar.set(13, toInt(ssecond));
	    } else {
	      if (value.length() >= 13) {
	        shour = value.substring(11, 13);
	        calendar.set(11, toInt(shour));
	      }
	      if (value.length() >= 16) {
	        sminute = value.substring(14, 16);
	        calendar.set(12, toInt(sminute));
	      }
	      if (value.length() >= 19) {
	        ssecond = value.substring(17, 19);
	        calendar.set(13, toInt(ssecond));
	      }
	    }

	    Date date = calendar.getTime();
	    return date;
	  }

	  public static int toInt(String value)
	  {
	    if ((value != null) && (value.length() > 0) && (isDigit(value)))
	      return Integer.parseInt(value);

	    return -1;
	  }
	  
	  public static boolean isDigit(String value)
	  {
	    for (int i = 0; i < value.length(); ++i)
	      if ((value == null) || (!(Character.isDigit(value.charAt(i)))))
	        return false;

	    return true;
	  }
	  
  /**
	 * 按月份分割获取月份集合
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static List<String> getDateList(String beginTime,String endTime){
		//String beginTime="2014-08-31";
		//String endTime="2018-05-30";
		String b_year="";
		String b_month="";
		String e_year="";
		String e_month="";
		String temp_yrar="";//初始化年份临时变量
		List<String> datelist = new ArrayList();
	    if (beginTime.length() >= 4) b_year = beginTime.substring(0, 4);
        if (beginTime.length() >= 7) b_month = beginTime.substring(5, 7);
        
        if (endTime.length() >= 4) e_year = endTime.substring(0, 4);
        if (endTime.length() >= 7) e_month = endTime.substring(5, 7);
        
        temp_yrar=b_year;//给初始的年份赋值
        
        int y=0;//年数
        int tempM=0;//月数
        if(Integer.valueOf(e_year)>Integer.valueOf(b_year)){//跨年
        	y=Integer.valueOf(e_year)-Integer.valueOf(b_year);//年的差值
        	if(Integer.valueOf(e_month)>=Integer.valueOf(b_month)){//结束月份大于=开始月份
        		int kk=Integer.valueOf(e_month)-Integer.valueOf(b_month);
        		tempM=y*12+kk;
        	}else{//结束月份小于开始月份
        		y=y-1;
        		int totalM=12+Integer.valueOf(e_month);
        		tempM=y*12+(totalM-Integer.valueOf(b_month));
        	}
        }else{//同年
        	tempM=Integer.valueOf(e_month)-Integer.valueOf(b_month);
        }
        System.out.println(tempM+"月份......");
        System.out.println(y+"年份差值.....");
        int m=0;
        String g="";//月份字符串
        int y_cha=1;//累加过程的年份差值默认是一年
        int temYear=0;//临时变量存放开始年份
        int h=0;//临时变量存放月份
        for(int k=0;k<tempM+1;k++){
        	m=Integer.valueOf(b_month)+k;
        	h=m;//临时变量h
        	if(m<=12){
        		g=h+"";
        		if(g.length()==1) g="0"+g;
        	}
        	if(m>12){
        		temYear=Integer.valueOf(b_year);
        		if((m-1)%12==0){
        			temYear+=1;//如果跨过一年则加一年
        			y_cha=temYear-Integer.valueOf(temp_yrar);//如果跨过一年，年份差值加一
        		} 
        		h-=y_cha*12;
        		g=h+"";
        		b_year=temYear+"";
        		if(g.length()==1) g="0"+g;
        	}
        	System.out.println(b_year+"-"+g);
    		datelist.add(b_year+"-"+g);
        }
        
        System.out.println(datelist.size());
       
        return datelist;
	}
	//获取本周星期几
	public static Long getDay(int day) throws Exception{
		Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        switch (day) {
		case 0:
			//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//获取本周日的日期	
			//增加一个星期，才是我们中国人理解的本周日的日期
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			break;
		case 1:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期	
			break;
		case 2:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY); //获取本周二的日期	
			break;
		case 3:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY); //获取本周三的日期	
			break;
		case 4:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY); //获取本周四的日期	
			break;
		case 5:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); //获取本周五的日期	
			break;
		case 6:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); //获取本周六的日期	
			break;			
		default:
			break;
		}
        String str = df.format(cal.getTime());
        long strLong =  df.parse(str).getTime();
	    return strLong;
	}
	
	@SuppressWarnings("deprecation")
	public static int getdiffMonth(String begin,String end) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = df.parse(begin);
		Date endDate = df.parse(end);
		int beginYear = beginDate.getYear();
		int beginMonth = beginDate.getMonth();
		int endYear = endDate.getYear();
		int endMonth = endDate.getMonth();
		int difMonth = (endYear-beginYear)*12+(endMonth-beginMonth)+1;
		return difMonth;
		
	}
	
}
