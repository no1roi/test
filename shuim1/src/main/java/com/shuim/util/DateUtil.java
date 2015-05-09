/**
 * @class DateUtils ver 1.0.0
 * @brief 날짜와 시간을 제어함
 *
 * registered date 20081217
 * programmed by Seok Kyun. Choi. (최석균)
 * http://syaku.tistory.com
 */

package com.shuim.util;

import java.util.*;
import java.util.regex.*;
import java.text.*;

import org.apache.commons.lang.StringUtils;

public class DateUtil extends StringUtils {

  private static SimpleDateFormat formatter;
  private static DecimalFormat df;

  /**
  * @method : getDate(parameter,parameter2)
  * @brief : 정규표현식을 이용하여, 날짜를 제어한다. 각 시분초는 구분자가 있어야 한다.
  * @parameters {
        parameter : (String) $1 = 년 , $2 = 월 , $3 = 일 , $4 = 시 , $5 = 분 , $6 = 초
        parameter2 : (String) 구분자(-_:./\s) 를 가진 날짜
      }
  * @return : (String) 정규표현식 시간 || 00:00:00
  */
  public static String getDate(String patten,String date) {
    if (isEmpty(date)) return date;
    String sysdate_patten = "(^[0-9]*)[-_:.\\/\\s]?([0-9]*)[-_:.\\/\\s]?([0-9]*)[-_:.\\/\\s]?([0-9]*)[-_:.\\/\\s]?([0-9]*)[-_:.\\/\\s]?([0-9]*)(.*)$";
    Pattern date_comp = Pattern.compile(sysdate_patten);
    if (date_comp.matcher(date).find()) return date.replaceAll(sysdate_patten,patten);
    else return getDate(patten,"00:00:00");
  }

  /**
  * @method : setDate(parameter)
  * @brief : 날짜를 설정하고 Date 객체로 반환한다.
  * @parameters {
        parameter : (String) 날짜
      }
  * @return : (Date)
  */
  public static Date setDate(String date) throws Exception {
    date = date("yyyy-MM-dd HH:mm:ss",date);
    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return (Date) formatter.parse(date);
  }

  /**
  * @method : getTime(parameter,parameter2)
  * @brief : 정규표현식을 이용하여, 시간을 제어한다. 각 시분초는 구분자가 있어야 한다.
  * @parameters {
        parameter : (String) $1 = 시 , $2 = 분 , $3 = 초
        parameter2 : (String) 구분자(-_:./\s) 를 가진 시간
      }
  * @return : (String) 정규표현식 시간 || 00:00:00
  */
  public static String getTime(String patten,String time) {
    if (isEmpty(time)) return time;
    String time_patten = "(^[0-9]*)[-_:.\\/\\s]?([0-9]*)[-_:.\\/\\s]?([0-9]*)(.*)$";
    Pattern time_comp = Pattern.compile(time_patten);
    if (time_comp.matcher(time).find()) return time.replaceAll(time_patten,patten);
    else return getTime(patten,"00:00:00");
  }

  /**
  * @method : setTime(parameter)
  * @brief : 시간을 설정하고 Date 객체로 반환한다.
  * @parameters {
        parameter : (String) 시간
      }
  * @return : (Date)
  */
  public static Date setTime(String time) throws Exception {
    time = time("HH:mm:ss",time);
    formatter = new SimpleDateFormat("HH:mm:ss");
    return (Date) formatter.parse(time);
  }

  /**
  * @method : date(parameter, parameter2)
  * @brief : SimpleDateFormat 이용하여 날짜를 반환한다.
  * @parameters {
       parameter : (String) SimpleDateFormat 클래스의 포맷
       parameter2 : (String || Date) SimpleDateFormat 포맷 시간
     }
  * @return : (String) 날짜
  */
  public static String date() throws Exception {
    return date("yyyy-MM-dd HH:mm:ss");
  }
  public static String date(String format) throws Exception {
    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = formatter.format(new Date());
    return date(format,date);
  }
  public static String date(String format, Date date) throws Exception {
    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return date(format,formatter.format(date));
  }
  public static String date(String format, String date) throws Exception {
    if (isEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";
    if (isEmpty(date)) return null;

    date = date.replaceAll("[^0-9]+","");
    date = rightPad(date,14,'0');
    date = date.replaceAll("(^[0-9]{4})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})","$1-$2-$3 $4:$5:$6");
    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date redate = formatter.parse(date);
    formatter = new SimpleDateFormat(format);
    return formatter.format(redate);
  }


  /**
  * @method : time(parameter, parameter2)
  * @brief SimpleDateFormat 이용하여 날짜를 반환한다.
  * @parameters {
        parameter : (String) SimpleDateFormat 클래스의 포맷
        parameter2 : (String || Date) SimpleDateFormat 포맷 시간
    }
  * @return : (String) 시간
  */
  public static String time() throws Exception {
    return time("HH:mm:ss");
  }
  public static String time(String format) throws Exception {
    formatter = new SimpleDateFormat("HH:mm:ss");
    String time = formatter.format(new Date());
    return time(format,time);
  }
  public static String time(String format, String time) throws Exception {
    time = time.replaceAll("[^0-9]+","");
    time = rightPad(time,6,'0');
    time = leftPad(time,14,'0');
    return date(format,time);
  }
  /**
  * @method : timespace(parameter, parameter2,parameter3)
  * @brief 시작시간과 종료시간의 간격을 구합니다.
  * @parameters {
        parameter : (String) 시작시간
        parameter2 : (String) 종료시간
        parameter3 : (String) SimpleDateFormat 클래스의 포맷
    }
  * @return : (String) SimpleDateFormat 포맷 시간
  */
  public static String timespace(String stime, String etime) throws Exception {
    return timespace(stime,etime,"HH:mm:ss");
  }
  public static String timespace(String stime, String etime, String format) throws Exception {
    try {
      if (isEmpty(stime) || isEmpty(etime)) throw new Exception("parameter null");

      stime = stime.replaceAll("[^0-9]+","");
      etime = etime.replaceAll("[^0-9]+","");

      int s = Integer.parseInt(stime);
      int e = Integer.parseInt(etime);
      stime = rightPad(stime,6,'0');
      etime = rightPad(etime,6,'0');
      stime = date("yyyy-MM-dd HH:mm:ss","19700101" + stime);
      etime = date("yyyy-MM-dd HH:mm:ss","19700101" + etime);

      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date sdate = formatter.parse(stime);
      Date edate = formatter.parse(etime);
      // 9시간 (540*60*1000 = 32400000) 오차 제거하기
      Long ret = (edate.getTime() - sdate.getTime()) - 32400000;
      String ext = formatter.format(ret);

      return date(format,ext);
    } catch (Exception e) {
      return time(format,"000000");
    }
  }
  /**
  * @method : timeAdd(parameter, parameter2,parameter3)
  * @brief 구분자를 포함한 두 시간을 합산합니다. 분 초 중에 60 이상인 경우 시간 혹은 분을 반올림합니다.
  * @parameters {
        parameter : (String) 구분자(-_:./\s)를 가진 사간
        parameter2 : (String) 구분자(-_:./\s)를 가진 시간
        parameter3 : (String)  $1 = 시 , $2 = 분 , $3 = 초
    }
  * @return : (String) 정규표현식 시간 : 00:00:00
  */
  public static String timeAdd(String time,String time2,String patten) throws Exception {
    String ret = "00:00:00";
    if (isEmpty(patten)) patten = "$1:$2:$3";

    int sh = Integer.parseInt(getTime("$1",time));
    int sm = Integer.parseInt(getTime("$2",time));
    int ss = Integer.parseInt(getTime("$3",time));
    int eh = Integer.parseInt(getTime("$1",time2));
    int em = Integer.parseInt(getTime("$2",time2));
    int es = Integer.parseInt(getTime("$3",time2));

    try {
      int h = sh + eh;
      int s = ss + es;
      int m = 0;
      if (s > 60) { 
        Double mm = Math.floor(s/60);
        df = new DecimalFormat("0"); 
        int mmm = Integer.parseInt(df.format(mm));

        s = s - (mmm * 60); 
        m = m + mmm;
      }

      m = m + sm + em;
      if (m > 60) { 
        Double hh = Math.floor(m/60);
        df = new DecimalFormat("0"); 
        int hhh = Integer.parseInt(df.format(hh));

        m = m - (hhh * 60); 
        h = h + hhh;
      }

      ret = leftPad(Integer.toString(h),2,'0') + ":" + leftPad(Integer.toString(m),2,'0') + ":" + leftPad(Integer.toString(s),2,'0');
    } catch (Exception e) {
      ret = "00:00:00";
    }

    return getTime(patten,ret);
  }

}


