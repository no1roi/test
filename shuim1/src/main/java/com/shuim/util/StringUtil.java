/*******************************************************************************
 * 파일명 : StringUtil.java
 * 작성자 : 정성우
 * 작성일자 : 2014-09-02
 */
package com.shuim.util;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public StringUtil() {
	}

	
	/**
	 * text를 html로 변환
	 * 
	 * Chr(13)&Chr(10)
	 */
	public static String txtToHtml(String str) {
		//System.out.println("AAAAAAAAAAAAA"+str);
		String temp = str.replaceAll("\r\n", "<br>");
		//System.out.println("BBBBBBBBBBBB"+temp);
	

		return temp;
	}
	/**
	 * html를 text로 변환
	 * 
	 * 
	 */
	public static String htmlToTxt(String str) {
		String temp = str.replaceAll("<br>", "\r\n");	

		return temp;
	}
	
	/**
	 * 문자열을 특정문자로 분리하여 사용
	 * 
	 * @param String
	 *            str 분리될 원본 문자열
	 * @param String
	 *            delim 구분자로 사용될 문자
	 * @return String[]
	 * 
	 * @sample StringUtil.strSplit("aaa,bbb", "," )
	 */
	public static String[] strSplit(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		Vector vec = new Vector();
		int cnt = st.countTokens();
		String[] tokens = null;

		if (cnt == 1) {
			tokens = new String[1];
			tokens[0] = str;
		} else {
			for (int i = 0; i < cnt; i++)
				vec.addElement(st.nextToken());
			tokens = new String[cnt];
			vec.copyInto(tokens);
			vec.clear();
			vec = null;
		}
		return tokens;
	}

	/**
	 * 입력된 문자열이 null 일경우 기본문자열을 리턴한다.
	 * 
	 * @param str
	 *            문자열
	 * @param def
	 *            기본 문자열
	 * @return
	 */
	public static String convNull(String str, String def) {
		if (isNull(str))
			return def;
		return str;
	}
	
	/**
	 * Object가 null 일경우 기본문자열을 리턴한다.
	 * @author 신준호
	 * @since 20070802
	 * @param obj
	 * @param def 기본 문자열
	 * @return
	 */
	public static String convNullObj(Object obj, String def) {
		if (obj==null)
			return def;
		return obj.toString();
	}

	/**
	 * null 또는 공백문자인지 확인한다.
	 * 
	 * @param str
	 *            문자열
	 * @return
	 */
	public static boolean isNull(String str) {
		return (str == null || str.trim().length() < 1);
	}

	/**
	 * 문자 열 속에 포함 된 특정 문자를 새로운 문자로 변경
	 * 
	 * @param String
	 *            s1 문자열
	 * @param String
	 *            s2 이전문자
	 * @param String
	 *            s3 바뀔문자
	 * @return String 변환 된 문자열
	 */
	public static String strReplace(String s1, String s2, String s3) {
		if (s1 == null)
			return "";
		String res = "";
		StringTokenizer str = new StringTokenizer(s1, s2);

		while (str.hasMoreTokens()) {
			res += str.nextToken() + s3;
		}

		return res;
	}

	/**
	 * 문자열에서 HTML 태그 제거
	 * 
	 * @param s
	 * @return
	 */
	public static String removeTag(String s) {
		if (s == null)
			return "";
		final int NORMAL_STATE = 0;
		final int TAG_STATE = 1;
		final int START_TAG_STATE = 2;
		final int END_TAG_STATE = 3;
		final int SINGLE_QUOT_STATE = 4;
		final int DOUBLE_QUOT_STATE = 5;
		int state = NORMAL_STATE;
		int oldState = NORMAL_STATE;
		char[] chars = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		char a;
		for (int i = 0; i < chars.length; i++) {
			a = chars[i];
			switch (state) {
			case NORMAL_STATE:
				if (a == '<')
					state = TAG_STATE;
				else
					sb.append(a);
				break;
			case TAG_STATE:
				if (a == '>')
					state = NORMAL_STATE;
				else if (a == '\"') {
					oldState = state;
					state = DOUBLE_QUOT_STATE;
				} else if (a == '\'') {
					oldState = state;
					state = SINGLE_QUOT_STATE;
				} else if (a == '/')
					state = END_TAG_STATE;
				else if (a != ' ' && a != '\t' && a != '\n' && a != '\r'
						&& a != '\f')
					state = START_TAG_STATE;
				break;
			case START_TAG_STATE:
			case END_TAG_STATE:
				if (a == '>')
					state = NORMAL_STATE;
				else if (a == '\"') {
					oldState = state;
					state = DOUBLE_QUOT_STATE;
				} else if (a == '\'') {
					oldState = state;
					state = SINGLE_QUOT_STATE;
				} else if (a == '\"')
					state = DOUBLE_QUOT_STATE;
				else if (a == '\'')
					state = SINGLE_QUOT_STATE;
				break;
			case DOUBLE_QUOT_STATE:
				if (a == '\"')
					state = oldState;
				break;
			case SINGLE_QUOT_STATE:
				if (a == '\'')
					state = oldState;
				break;
			}
		}
		return sb.toString();

	}

	/**
	 * Method cropByte. 문자열 바이트수만큼 끊어주고, 생략표시하기
	 * 
	 * @param str
	 *            문자열
	 * @param i
	 *            바이트수
	 * @param trail
	 *            생략 문자열. 예) "..."
	 * @return String
	 */
	public static String cropByte(String str, int i, String trail) {
		if (str == null)
			return "";
		String tmp = str;
		int slen = 0, blen = 0;
		char c;
		try {
			// if(tmp.getBytes("MS949").length>i) {//2-byte character..
			if (tmp.getBytes("UTF-8").length > i) {// 3-byte character..
				while (blen + 1 < i) {
					c = tmp.charAt(slen);
					blen++;
					slen++;
					if (c > 127)
						blen++;
				}
				tmp = tmp.substring(0, slen) + trail;
			}
		} catch (Exception e) {
		}
		return tmp;
	}

	/**
	 * Method parseString	특정문자열을 기준으로 파싱한 후 문자열을 더한다.
	 * @param str		문자열
	 * @param seperate	구분기호
	 * @param len	    문자열을 얼마나 허용할 것인가
	 * @param trail		생략 문자열 예) "..."
	 * @return String
	 */	
	public static String parseString (String str, String seperate , int len , String trail) {
		if (str == null)
			return "";
		
		try 
		{
			String totString  = "";
		
			if(str.indexOf("|") > -1)
			{
				String[] strTemp = str.split(seperate);
				strTemp[1] = StringUtil.cropByte(strTemp[1], len, trail);
				for(int i=0; i<strTemp.length; i++)
				{
					totString += strTemp[i];
				}
			}
			else if(!"...".equals(trail))
			{
				String[] strTemp = str.split(seperate);
				for(int i=0; i<strTemp.length; i++)
				{
					totString += strTemp[i];
				}
			}
			else
			{
				totString = StringUtil.cropByte(str, len, trail);
			}
			return totString;
		}
		catch (Exception e){
			return "";
		}
	}
	
	/**
	 * Method parseString	특정문자열을 기준으로 파싱한 후 문자열을 더한다.
	 * @param str		문자열
	 * @param seperate	구분기호
	 * @param trail		생략 문자열 예) "..."
	 * @return String
	 */	
	public static String parseString (String str, String seperate, String trail) {
		if (str == null)
			return "";
		
		try 
		{
			String totString  = "";
		
			if(str.indexOf("|") > -1)
			{
				String[] strTemp = str.split(seperate);
				strTemp[1] = StringUtil.cropByte(strTemp[1], 48, trail);
				for(int i=0; i<strTemp.length; i++)
				{
					totString += strTemp[i];
				}
			}
			else if(!"...".equals(trail))
			{
				String[] strTemp = str.split(seperate);
				for(int i=0; i<strTemp.length; i++)
				{
					totString += strTemp[i];
				}
			}
			else
			{
				totString = StringUtil.cropByte(str, 48, trail);
			}
			return totString;
		}
		catch (Exception e){
			return "";
		}
	}
	
	/**
	 * DB에 저장된 enter key값을 <br>
	 * 로 변환한다.
	 * 
	 * @param String
	 * @return String
	 */
	public static String convertHtmlBr(String comment) {
		if (comment == null)
			return "";
		int length = comment.length();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			String comp = comment.substring(i, i + 1);
			if ("\r".compareTo(comp) == 0) {
				comp = comment.substring(++i, i + 1);
				if ("\n".compareTo(comp) == 0)
					sb.append("<BR>");
				else
					sb.append("\r");
			}
			sb.append(comp);
		}
		return sb.toString();

	}

	/**
	 * 각 인덱스의 문자가 소문자라면 대문자로 변환
	 * 
	 * @param str
	 *            문자열
	 * @return String
	 */
	public static String bigLetter(String str) {
		// String tmp = str;
		String convertStr = "";
		char[] charArray = str.toCharArray(); // Char[]로 변환

		for (int i = 0; i < str.length(); i++) {
			// System.out.println("Char["+i+"] = "+charArray[i]); //변환된 Char 출력
			if ((charArray[i] >= 97) && (charArray[i] <= 122)) {
				charArray[i] -= 32; // 각 인덱스의 문자가 소문자라면 대문자로 변환
				convertStr += charArray[i];
			}
		}

		return convertStr;
	}

	/**
	 * 각 인덱스의 문자가 대문자라면 소문자로 변환
	 * 
	 * @param str
	 *            문자열
	 * @return String
	 */
	public static String smallLetter(String str) {
		// String tmp = str;
		String convertStr = "";
		char[] charArray = str.toCharArray(); // Char[]로 변환

		for (int i = 0; i < str.length(); i++) {
			// System.out.println("Char["+i+"] = "+charArray[i]); //변환된 Char 출력
			if ((charArray[i] >= 65) && (charArray[i] <= 90)) {
				charArray[i] += 32; // 각 인덱스의 문자가 대문자라면 소문자로 변환
				convertStr += charArray[i];
			}
		}

		return convertStr;
	}
	
	/**
	 * 파라미터값을 체크 
	 * 한글이 넘어오면 URLEncoder
	 * @param str
	 *            변환할 문자
	 * @return String 변환문자
	 */
	public static String paramCheck(String str) {
		String tmp = new String("");
		if (str == null || str.length() == 0)
			return "";
		try {
			// 첫번째 문자값을 읽음..
	        int i = str != null && str.length() > 0 ? str.charAt(0) : -1;
	        // 만일 164(%A4)이상 200(%C8)이하라면 iso-8859-1로 인코딩 한것이므로,

	        // 이때는 다시 인코딩을 조정한다.
	        str = str != null ? (i >= 164 && i <= 200 ? new String (str.getBytes("iso-8859-1"), "UTF-8") : str) : null;

	        //tmp = URLEncoder.encode(str);
	        tmp = URLEncoder.encode(str, "UTF-8");
	        
		} catch (Exception e) {

		}
		return tmp;
	}
	
	/**
	 * Character Set를 UTF-8로 컨버전
	 * 
	 * @param str
	 *            변환할 문자
	 * @return String 변환문자
	 */
	public static String convUTF(String str) {
		String tmp = new String("");
		if (str == null || str.length() == 0)
			return "";
		try {
			tmp = new String(str.getBytes("MS-949"), "UTF-8");
		} catch (Exception e) {

		}
		return tmp;
	}

	/**
	 * getParameterMap() 으로 받은 파라미터를 HashMap으로 치환
	 * 
	 * @version
	 * @param Map
	 *            requestMap
	 * @return HashMap hm
	 * @throws Exception
	 * 
	 */
	public static Map paramsToHashMap(Map requestMap) throws Exception {
		HashMap hm = new HashMap();

		try {
			Iterator it = requestMap.keySet().iterator();
			Object key = null;
			String[] value = null;

			while (it.hasNext()) {
				key = it.next();
				value = (String[]) requestMap.get(key);

				for (int i = 0; i < value.length; i++) {
					hm.put(key, value[i].toString());
				}
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

		return hm;
	}

	/**
	 * getParameterMap() 으로 받은 파라미터를 QueryString으로 치환
	 * 
	 * @version
	 * @param Map
	 *            requestMap
	 * @return String (a=1&b=2&c=3....)
	 * @throws Exception
	 * 
	 */
	public static String paramsToQueryString(Map requestMap) throws Exception {
		StringBuffer sp = new StringBuffer();
		int loopCnt = 0;

		try {
			Iterator it = requestMap.keySet().iterator();
			Object key = null;
			String[] value = null;

			while (it.hasNext()) {
				key = it.next();
				value = (String[]) requestMap.get(key);

				for (int i = 0; i < value.length; i++) {
					if (!"cmd".equals(key.toString())
							&& !"returnCmd".equals(key.toString())) {
						if (loopCnt > 0)
							sp.append("&");
						sp.append(key.toString() + "=" + value[i].toString());
					}
				}

				loopCnt++;
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

		return sp.toString();
	}

	/**
	 * getParameterMap() 으로 받은 파라미터를 QueryString으로 치환 cmd포함
	 * 
	 * @version
	 * @param Map
	 *            requestMap
	 * @return String (a=1&b=2&c=3....)
	 * @throws Exception
	 * 
	 */
	public static String paramsToQueryStringWithCmd(Map requestMap)
			throws Exception {
		StringBuffer sp = new StringBuffer();
		int loopCnt = 0;

		try {
			Iterator it = requestMap.keySet().iterator();
			Object key = null;
			String[] value = null;

			while (it.hasNext()) {
				key = it.next();
				value = (String[]) requestMap.get(key);

				for (int i = 0; i < value.length; i++) {
					if (loopCnt > 0)
						sp.append("&");
					sp.append(key.toString() + "=" + paramCheck(value[i].toString()));
					//sp.append(key.toString() + "=" + convEUCKR(value[i].toString()));
				}

				loopCnt++;
			}
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

		return sp.toString();
	}

	/**
	 * 문자열을 토큰으로 구분해서 1차원 배열로 리턴한다.
	 * 
	 * @param s -
	 *            토큰을 가진 문자열
	 * @param token -
	 *            구분자
	 * @return 구분자로 분리된 1차원배열
	 */
	public static String[] cutTokenToArray(String s, String token) {
		StringTokenizer stz = new StringTokenizer(s, token);
		String result[] = new String[stz.countTokens()];
		try {
			for (int j = 0; j < result.length; j++)
				result[j] = stz.nextToken();
		} catch (Exception exception) {
		}
		return result;
	}

	/**
	 * XML 구조로 String 생성
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String processXML(List list, String formName, String targetName,
			String strSelectValue) {
		StringBuffer sb = new StringBuffer();
		String chkText = "";
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<cbo-list>\n");
		sb.append("<selectChoice>\n");
		sb.append("<selectElement>\n");
		sb.append("<formName>" + formName + "</formName>\n");
		sb.append("<targetName>" + targetName + "</targetName>\n");
		if (strSelectValue.equals("")) {
			sb.append("<strSelectValue>empty</strSelectValue>\n");
		} else {
			sb.append("<strSelectValue>" + strSelectValue
					+ "</strSelectValue>\n");
		}
		sb.append("</selectElement>\n");
		sb.append("</selectChoice>\n");
		
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				sb.append("<cbo>\n");
				sb.append("<code>" + map.get("CODE").toString() + "</code>\n");
				chkText = map.get("VALUE").toString();
		    	sb.append("<value>"+chkText.replaceAll("&", "/")+"</value>\n");
				sb.append("</cbo>\n");
			}
		}
		sb.append("</cbo-list>");
		// System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * XML 구조로 String 생성 20080225 by wonho
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String processListXML(List list) {
		StringBuffer sb = new StringBuffer();
		String chkText = "";
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<result-list>\n");
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				sb.append("<result>\n");
				if("99".equals(map.get("resultCode").toString())){
					sb.append("<value>" +"resultCode:"+ map.get("resultCode").toString() + "</value>\n");
					sb.append("<value>" +"errorCode:"+ map.get("errorCode").toString() + "</value>\n");
					sb.append("<value>" +"errorMsg:"+ map.get("errorMsg").toString() + "</value>\n");
				}else{
					sb.append("<value>" + "resultCode:"+ map.get("resultCode").toString() + "</value>\n");
			    	sb.append("<value>"+ "구분:"+ map.get("kn").toString() +"</value>\n");
			    	sb.append("<value>"+ "결제업체제휴처번호:"+ map.get("stpl_no").toString() +"</value>\n");
			    	sb.append("<value>"+ "고객번호:"+ map.get("cltn").toString() +"</value>\n");
			    	sb.append("<value>"+ "사업자번호:"+ map.get("bpr_no").toString() +"</value>\n");
			    	sb.append("<value>"+ "주문번호:"+ map.get("ord_no").toString() +"</value>\n");
			    	sb.append("<value>"+ "발행일자:"+ map.get("txbl_isd").toString() +"</value>\n");
			    	sb.append("<value>"+ "결제방법:"+ map.get("stmh_cd").toString() +"</value>\n");
			    	sb.append("<value>"+ "영수증구분:"+ map.get("rcpt_dckn_cd").toString() +"</value>\n");
			    	sb.append("<value>"+ "공급가액:"+ map.get("spa").toString() +"</value>\n");
			    	sb.append("<value>"+ "발행금액:"+ map.get("iss_a").toString() +"</value>\n");
			    	sb.append("<value>"+ "세액:"+ map.get("ccl_taxa").toString() +"</value>\n");
			    	sb.append("<value>"+ "저널번호:"+ map.get("jrno").toString() +"</value>\n");
			    	sb.append("<value>"+ "결과코드:"+ map.get("prsl_cd").toString() +"</value>\n");
				}
				sb.append("</result>\n");
			}
		}
		sb.append("</result-list>");
		// System.out.println(sb.toString());
		return sb.toString();
	}
	
    /**
     * XML 구조로 String 생성 
     * @param List list
     * @param String formName
     * @param String targetName
     * @param String codeColName		:	code 데이터가 들어있는 컬럼명 
     * @param String textColName	:	text가 들어있는 컬럼
     * @return  String
     */
	public String processXML(List list, String formName, String targetName, String strSelectValue, String codeColName, String textColName){
		StringBuffer sb = new StringBuffer();
		String chkText = "";
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	    sb.append("<cbo-list>\n");
	    sb.append("<selectChoice>\n");
	    sb.append("<selectElement>\n");
	    sb.append("<formName>"+formName+"</formName>\n");
	    sb.append("<targetName>"+targetName+"</targetName>\n");
	    if(strSelectValue.equals("")){
	    	sb.append("<strSelectValue>empty</strSelectValue>\n");
	    }else{
	    	sb.append("<strSelectValue>"+strSelectValue+"</strSelectValue>\n");
	    }
	    sb.append("</selectElement>\n");
	    sb.append("</selectChoice>\n");
    
	    for(int i=0;i<list.size();i++){
	    	Map map = (Map)list.get(i);
	    	sb.append("<cbo>\n");
	    	sb.append("<code>"+map.get(codeColName).toString()+"</code>\n");
	    	chkText = map.get(textColName).toString();
	    	sb.append("<value>"+chkText.replaceAll("&", "/")+"</value>\n");
	    	sb.append("<value>"+map.get(textColName).toString()+"</value>\n");
	    	sb.append("</cbo>\n");
	    }
	    sb.append("</cbo-list>");
	    return sb.toString();
	}	
	
	/**
	 * XML 구조로 String 생성
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String riaXML(List list, String category){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<result>\n");
		sb.append("<"+category+">\n");
		for(int i=0;i<list.size();i++){
			Map map = (Map)list.get(i);
			sb.append("<item name=\""+map.get("NAME").toString()+"\" code=\""+map.get("CODE").toString()+"\"/>\n");
		}
		sb.append("</"+category+">\n");
		sb.append("</result>\n");
//		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * XML 구조로 String 생성
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String riaSearchXML(Map map){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<result>\n");
		sb.append("<carsearch caramount=\""+map.get("CNT").toString()+"\">\n");
		if(map.get("XC_HOPE_SEL_PRC_L") == null && map.get("XC_HOPE_SEL_PRC_H") == null){
			sb.append("<price minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<price minimum=\""+map.get("XC_HOPE_SEL_PRC_L").toString()+"\" maximum=\""+map.get("XC_HOPE_SEL_PRC_H").toString()+"\"/>\n");
		}
		
		if(map.get("XC_DVML_L") == null && map.get("XC_DVML_H") == null){
			sb.append("<mileage minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<mileage minimum=\""+map.get("XC_DVML_L").toString()+"\" maximum=\""+map.get("XC_DVML_H").toString()+"\"/>\n");
		}
		
		if(map.get("XC_PYY_YY_L") == null && map.get("XC_PYY_YY_H") == null){
			sb.append("<years minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<years minimum=\""+map.get("XC_PYY_YY_L").toString()+"\" maximum=\""+map.get("XC_PYY_YY_H").toString()+"\"/>\n");
		}
		
		if(map.get("XC_M_LSFE_L") == null && map.get("XC_M_LSFE_H") == null){
			sb.append("<monthlease minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<monthlease minimum=\""+map.get("XC_M_LSFE_L").toString()+"\" maximum=\""+map.get("XC_M_LSFE_H").toString()+"\"/>\n");
		}
		
		if(map.get("XC_LS_RMM_L") == null && map.get("XC_LS_RMM_H") == null){
			sb.append("<leaseremainder minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<leaseremainder minimum=\""+map.get("XC_LS_RMM_L").toString()+"\" maximum=\""+map.get("XC_LS_RMM_H").toString()+"\"/>\n");
		}
		
		if(map.get("XC_CASH_SPRT_A_L") == null && map.get("XC_CASH_SPRT_A_H") == null){
			sb.append("<supportmoney minimum=\"0\" maximum=\"0\"/>\n");
		}else{
			sb.append("<supportmoney minimum=\""+map.get("XC_CASH_SPRT_A_L").toString()+"\" maximum=\""+map.get("XC_CASH_SPRT_A_H").toString()+"\"/>\n");
		}
		
		sb.append("</carsearch>\n");
		sb.append("</result>\n");
//		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 휴대폰번호국번을 배열로 리턴
	 * 
	 * @return 구분자로 분리된 1차원배열
	 */
	public static String[] getMobileNo() {
		String mobileNo = "010,011,016,017,018,019";

		return mobileNo.split(",");
	}

	/**
	 * 일반전화국번을 배열로 리턴
	 * 
	 * @return 구분자로 분리된 1차원배열
	 */
	public static String[] getTelNo() {
		String telNo = "02,031,032,033,041,042,043,050,051,052,053,054,055,061,062,063,064,070,080,0502,0505,0506";

		return telNo.split(",");
	}


	/**
	 * Email 한글리스트를 배열로 리턴
	 * 
	 * @return 1차원배열
	 */
	public static String[] getEmailKor() {
		String[] emailAddr = new String[11]; 
		emailAddr[0] = "한메일";
		emailAddr[1] = "네이버";
		emailAddr[2] = "네이트닷컴";
		emailAddr[3] = "야후";
		emailAddr[4] = "파란";
		emailAddr[5] = "핫메일";
		emailAddr[6] = "엠파스";
		emailAddr[7] = "라이코스";
		emailAddr[8] = "드림위즈";
		emailAddr[9] = "G메일";
		emailAddr[10] = "기타(직접입력)";
		return emailAddr;
	}

	/**
	 * Email 영문리스트를 배열로 리턴
	 * 
	 * @return 1차원배열
	 */
	public static String[] getEmailEng() {
		String[] emailAddr = new String[11]; 
		emailAddr[0] = "hanmail.net";
		emailAddr[1] = "naver.com";
		emailAddr[2] = "nate.com";
		emailAddr[3] = "yahoo.co.kr";
		emailAddr[4] = "paran.com";
		emailAddr[5] = "hotmail.com";
		emailAddr[6] = "empal.com";
		emailAddr[7] = "lycos.co.kr";
		emailAddr[8] = "dreamwiz.com";
		emailAddr[9] = "gmail.com";
		emailAddr[10] = "99";
		return emailAddr;
	}
	
	/**
	 * Email 영문리스트를 배열로 리턴
	 * 
	 */
	public static String[] getEmail() {
		String[] emailAddr = new String[11]; 
		
		emailAddr[0]="hanmail.net/한메일";
		emailAddr[1]="naver.com/네이버";
		emailAddr[2]="nate.com/네이트닷컴";
		emailAddr[3]="yahoo.co.kr/야후";
		emailAddr[4]="paran.com/파란";
		emailAddr[5]="hotmail.com/핫메일";
		emailAddr[6]="empal.com/엠파스";
		emailAddr[7]="lycos.co.kr/라이코스";
		emailAddr[8]="dreamwiz.com/드림위즈";
		emailAddr[9]="gmail.com/G메일";
		emailAddr[10]="99/기타(직접입력)";

		return emailAddr;
	}
	
	/**
	 * 가격을 ###,###,###,### 형태로 리턴
	 * 
	 * @return ###,###,###,### 형태의 String
	 */
	public static String comma1(int aa) {
		DecimalFormat fmt1 = new DecimalFormat("###,###,###,###");
		String str = fmt1.format(aa);
		return str;
	}
	
	/**
	 * 가격을 만원단위로 변환
	 * 
	 * @return ###,###,###,### 형태의 String
	 */
	public static String comma2(int aa) {
		return comma1(Math.round(aa / 10000));
	}
	
	/** // 20111006 실시간경매 front mind
	 * 가격을 ###,###,###,### 형태로 리턴
	 * @Param : long 타입
	 * @return ###,###,###,### 형태의 String
	 */
	public static String commaLong1(long aa) {
		DecimalFormat fmt1 = new DecimalFormat("###,###,###,###");
		String str = fmt1.format(aa);
		return str;
	}
	
	
	
	
	/**
	 * 파일이 서버에 실제로 있는지 확인 유무 후 이미지 소스를 리턴
	 * 
	 * @param String
	 *            path명 프로퍼티의 패스면
	 * @param String
	 *            fileName DB에서 가져오는 파일 네임(null 제외)
	 * @param String
	 *            noImg 서버에 실제 파일이 없을 경우 리턴될 노이미지
	 * @return String 
	 * 				이미지 소스
	 * @sample StringUtil.imgSrcCheck("imgPath", "fileName","noImg"  )
	 */
	public static String imgSrcCheck(String imgPath, String fileName, String noImg) {
		String imgSrc = "";		
		String[] imgTokens = StringUtil.strSplit(fileName, "/");		
		
		if(imgPath == null || "".equals(imgPath)){
			imgSrc = noImg;
		}else{
			if(imgTokens.length > 1){
				imgPath += imgTokens[imgTokens.length - 2] + "/";
				fileName = imgTokens[imgTokens.length -1];
			}
			String uploadImgPath = "/src" + imgPath;
			//String uploadImgPath = "/src" + imgPath + fileName;
			//System.out.println("uploadImgPath >>>>>" + uploadImgPath);
			
			File dir = new File(uploadImgPath);
			if (!dir.exists() || !dir.isDirectory()) {
				imgSrc = noImg;
	        }else{
	        	File aFile = new File(uploadImgPath,fileName);
				
				if (!aFile.exists()) {
					imgSrc = noImg;
				} else {
					imgSrc = imgPath + fileName;
				}
	        }			
			
		}
		
		return imgSrc;
	}
	
	 /**
     * 반각문자로 변경한다
     * @param src 변경할값
     * @return String 변경된값
     */
	public static String toHalfChar(String src)
    {
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= '！' && c <= '～')
            {
                c -= 0xfee0;
            }
            else if (c == '　')
            {
                c = 0x20;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }    

	/**
     * 전각문자로 변경한다.
     * @param src 변경할값
     * @return String 변경된값
     */
	public static String toFullChar(String src)
    {
        // 입력된 스트링이 null 이면 null 을 리턴
        if (src == null)
            return null;
        // 변환된 문자들을 쌓아놓을 StringBuffer 를 마련한다
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= 0x21 && c <= 0x7e)
            {
                c += 0xfee0;
            }
            //공백일경우
            else if (c == 0x20)
            {
                c = 0x3000;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }
	
	public static String lpad(String val, int len, String app) {
		StringBuffer value = new StringBuffer();

		for (int i = val.length(); i < len; ++i) {
			value.append(app);
		}

		value.append(val);

		return value.toString();
	}
	
	public static String urlEncoder( String s  ) {
		if( s == null ) return "";		 
		return URLEncoder.encode( s );
	}
	
	public static String urlDecoder( String s ){
		if( s == null ) return "";
		return URLDecoder.decode(s );
	}
	

	/**
	 * XML 구조로 String 생성
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String processXMLSidebarList(List list) {
		StringBuffer sb = new StringBuffer();

		java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("com/autoinside/properties/system"); 
		String xcVclImg = rb.getString("imagePath");
		String xcItmImg = rb.getString("itemImage");
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<lists>\n");
		Map m = null;
		if( list != null && list.size() > 0 ){
			
			int len = list.size();
			
			String imgFlnm = "";
			for( int i = 0 ; i < len ; i++ ){
				m = (Map)list.get(i);
				
				if(  m.get("XC_IMG_FLNM") != null && !"".equals(  m.get("XC_IMG_FLNM") ) ){
					imgFlnm = xcVclImg + m.get("XC_IMG_FLNM");       
				} else {
					imgFlnm = "";
				}
				
				sb.append("<list>\n");
				sb.append("<vclcd>").append( m.get("XC_VCL_CD") ).append("</vclcd>\n");
				sb.append("<img>").append( imgFlnm ).append("</img>\n");
				sb.append("<selprc>").append( m.get("SEL_PRC") ).append("</selprc>\n");
				sb.append("</list>\n");	
			}
		}
		sb.append("</lists>\n");
		// System.out.println(sb.toString());
		return sb.toString();
	}
	

	/**
	 * XML 구조로 String 생성
	 * 
	 * @param List
	 *            list
	 * @param String
	 *            formName
	 * @param String
	 *            targetName
	 * @return String
	 */
	public String processXMLAuctionSidebarList(List list) {
		StringBuffer sb = new StringBuffer();

		java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("com/autoinside/properties/system"); 
		String xcVclImg = rb.getString("imagePath");
		String xcItmImg = rb.getString("itemImage");
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<lists>\n");
		Map m = null;
		if( list != null && list.size() > 0 ){
			
			int len = list.size();
			
			String imgFlnm = "";
			for( int i = 0 ; i < len ; i++ ){
				m = (Map)list.get(i);
				
				if(  m.get("XC_IMG_FLNM") != null && !"".equals(  m.get("XC_IMG_FLNM") ) ){
					imgFlnm = xcVclImg + m.get("XC_IMG_FLNM");       
				} else {
					imgFlnm = "";
				}

				sb.append("<list>\n");
				sb.append("<img>").append( imgFlnm ).append("</img>\n");
				sb.append("<seq>").append( m.get("seq") ).append("</seq>\n");
				sb.append("<xcVclCd>").append( m.get("xcVclCd") ).append("</xcVclCd>\n");
				sb.append("<detailListVal>").append( m.get("detailListVal") ).append("</detailListVal>\n");
				sb.append("<reCurPage>").append( m.get("reCurPage") ).append("</reCurPage>\n");
				sb.append("<backUrl>").append( m.get("backUrl") ).append("</backUrl>\n");
				sb.append("</list>\n");	
			}
		}
		sb.append("</lists>\n");		
		return sb.toString();
	}
	
	public static String shoutCarName( String cnm , int len , String lastfix  ){
		try{
			String s = cnm.substring(cnm.indexOf(']')+1).trim();
			return cropByte( s , len , lastfix );
		}catch(Exception e){
			return cnm;
		}
	}
	
	
	
	/**
	 * HTML태그 및 특수문자를 변환해준다.
	 * @param str
	 * @return String
	 */
	public static String escapeXml(String str) {
		if (str==null) str = "";
		String resultString = str;
		resultString = resultString.replaceAll("&", "&amp;");
		resultString = resultString.replaceAll("\'", "&#039;");
		resultString = resultString.replaceAll("\"", "&#34;");
		resultString = resultString.replaceAll("<", "&lt;");
		resultString = resultString.replaceAll(">", "&gt;");
		return resultString;
	}
	
	/**
	 * HTML태그 및 특수문자를 변환해준다.
	 * @param str
	 * @return String
	 */
	public static String unEscapeXml(String str) {
		if (str==null) str = "";
		String resultString = str;
		resultString = resultString.replaceAll("&amp;","&");
		resultString = resultString.replaceAll("&#039;", "\'");
		resultString = resultString.replaceAll( "&#34;","\"");
		resultString = resultString.replaceAll("&lt;","<");
		resultString = resultString.replaceAll("&gt;", ">");
		return resultString;
	}
	
	public static String escapeXml(int str) {
		return escapeXml(""+str);
	}
	
	
	/**
	 * XML 구조로 String 생성
	 * @author HD1113 김명섭
	 * @param List
	 * @return String
	 */
	public static String makeXMLRecommendedCarSearch(HashMap map){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<result>\n");
		sb.append("<XCIE_PFRN_VCTP_CD1>"); sb.append(map.get("XCIE_PFRN_VCTP_CD1")); sb.append("</XCIE_PFRN_VCTP_CD1>\n");
		sb.append("<XCIE_PFRN_VCTP_CD2>"); sb.append(map.get("XCIE_PFRN_VCTP_CD2")); sb.append("</XCIE_PFRN_VCTP_CD2>\n");
		sb.append("<XCIE_PFRN_MDL_CD1>"); sb.append(map.get("XCIE_PFRN_MDL_CD1")); sb.append("</XCIE_PFRN_MDL_CD1>\n");
		sb.append("<XCIE_PFRN_MDL_CD2>"); sb.append(map.get("XCIE_PFRN_MDL_CD2")); sb.append("</XCIE_PFRN_MDL_CD2>\n");
		sb.append("<XCIE_AVG_VCL_PRC>"); sb.append(map.get("XCIE_AVG_VCL_PRC")); sb.append("</XCIE_AVG_VCL_PRC>\n");
		
		sb.append("<XCIC_AVG_IS_PRC>"); sb.append(map.get("XCIC_AVG_IS_PRC")); sb.append("</XCIC_AVG_IS_PRC>\n");
		
		sb.append("<XCIC_AVG_LS_PRC>"); sb.append(map.get("XCIC_AVG_LS_PRC")); sb.append("</XCIC_AVG_LS_PRC>\n");
		
		sb.append("</result>\n");
		return sb.toString();
	}
	
	/**
	 * Method cropByte. Host통신할때 앞에 Zero붙는 경우 제거
	 * @return String
	 */
	public static String cropZero(String str) {
		if (str == null)
			return "";
		StringBuffer tmp = new StringBuffer();
		char[] chars = str.toCharArray();
		boolean chk = false;
		for(int i = 0 ; i<chars.length ; i++){
			if(chk || '0' != chars[i]){
				chk = true;
				tmp.append(chars[i]);
			}
		}
		return tmp.toString();
	}
	
	
	public static String juminFormatter(String str){
		if(str == null || "".equals(str) ) return "";
		String tmp = str;
		int len = str.length();
		if(len == 10)
			str = tmp.substring(0,3) + "-" + tmp.substring(3,5) + "-" + tmp.substring(5,10);
		else if(len == 13)
			str = tmp.substring(0,6) +  "-*******";
		//str = tmp.substring(0,6) + "-" + tmp.substring(6,13);
		return str;
	}
	
    /**
	 * 특수문자 제거 
	 * @author mind 
	 * @version
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * // 20110907 front [정보수정] -> 인증가맹점 등록정보 mind 
	 */
	public static String removeSpecialString( String s ){
		if( s == null  || s == "") return s;
		s = s.replaceAll("[.][.][/]", "");
		s = s.replaceAll("script", "");
		s = s.replaceAll("Script", "");
		return s;
	}	
	/**
	 * 특정 태그 사이의 이미지 존재 여부 체크
	 * 
	 * @param sourceString  String 원본문자열
	 * @return String 추출문자열
	 * @author HD2238 정성우
	 */
	public static boolean extractImgCheck (String strSource){
		
		boolean matchResult = false; 
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); 
		Matcher captured = imgPattern.matcher(strSource);     
		while(captured.find()) {     
			matchResult = true;  // 글 내용의 이미지들 중 이미지가 있는 경우     
		}

		return matchResult;
	}
	/**
	 * 특정 태그 사이의 문자열 추출
	 * 
	 * @param sourceString  String 원본문자열
	 * @return String 추출문자열
	 * @author HD2238 정성우
	 */
	public static String extractString (String strSource){
		String matchResult = "";		 
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		Matcher captured = imgPattern.matcher(strSource);     
		int imgCnt = 0;  
		while(captured.find()) {     
			matchResult = captured.group(1);  // 글 내용의 이미지들 중 첫번째 이미지만 저장			
			//System.out.println("matchResult=================="+matchResult);
	        Pattern p = Pattern.compile("copyright|Copyright|copyRight|blank", Pattern.CASE_INSENSITIVE);
	        Matcher m = p.matcher(captured.group(1).toString());		
			while (m.find()) {
				matchResult="true";			
				//System.out.println("matchResult=================="+matchResult);
			}
		
			imgCnt++;     
			if(imgCnt == 1) {
				//글 내용 중 이미지가 1개 이상 일 경우에는 더 이상 노출되지 않도록함.
				break;                                      
				}
			}	
		return matchResult;
	}

	public static String subStringBytes(String str, int byteLength) {    
		 // String 을 byte 길이 만큼 자르기. 
		 int retLength = 0;        
		 int tempSize = 0;       
		 int asc;      
		 if(str == null || "".equals(str) || "null".equals(str)){   
		 str = "";     }     
		 int length = str.length();  
		 for (int i = 1; i <= length; i++) {    
		 asc = (int) str.charAt(i - 1);            
		 if (asc > 127) {                
		 if (byteLength >= tempSize + 2) {   
		 tempSize += 2;                             
		 retLength++;                     
		 } else {     
		 return str.substring(0, retLength) + "...";      
		 }          
		 } else {      
		 if (byteLength > tempSize) {     
		 tempSize++;            
		 retLength++;              
		 }          
		 }      
		 }     
		 return str.substring(0, retLength);
		 } 
	
	/**
	 * 본문 콘텐츠를 meta description content로 변환해 준다.
	 * <meta name="Description" CONTENT="">
	 * @author 김선학
	 * @param contents : 본문내용
	 * @param cutByte : 문자열 자를 길이(200byte)
	 * @param addString : 문자열 뒤에 붙일 텍스트 (ex : "..." 표시)
	 * @return
	 */
	public static String metaDescriptionCut(String contents, int cutByte, String addString){
		String replaceContents = StringUtil.removeHtmlTag(StringUtil.removeTag(StringUtil.unEscapeXml(String.valueOf(contents))));
		
		StringTokenizer st = new StringTokenizer(replaceContents);
		String returnContents = "";
		int count = st.countTokens();
		
		for(int i = 1; i < count + 1; i++){
			String splitContents = st.nextToken();
			if(count != i){
				returnContents += splitContents.trim() + " ";
			} else {
				returnContents += splitContents.trim();
			}
		}
		return  StringUtil.cropByte(returnContents,cutByte,addString);
	}
	
	/**
	 * 불필요한 html text를 삭제한다
	 * @param str
	 * @return
	 */
	public static String removeHtmlTag(String str){
		str = str.replaceAll("&quot;", "");
		str = str.replaceAll("&ldquo;", "");
		str = str.replaceAll("&rdquo;", "");
		str = str.replaceAll("&lsquo;", "");
		str = str.replaceAll("&rsquo;", "");
		str = str.replaceAll("&ndash;", "-");
		return str;
	}
	
	/**
	 * Method cropByte. 문자열 바이트수만큼 끊어주고, 생략표시하기
	 * sourceText: 입력받는 원본 문자열 
	 * startKeyword: 시작할 키워드 
	 * cutLength: 자를 길이 
	 * startKeywordPreviousLength: 시작할 키워드 이전 포함할 문자의 길이 
	 * isTag: HTML 태그 제거 여부 
	 * isDot: 생략 "..." 표시 출력 여부  
	 */
	
	public static String stringCut(String sourceText, String startKeyword, int cutLength,
			int startKewordPreviousLength, boolean isTag, boolean isDot) {
		String targetText = sourceText;
		int oF = 0;
		int oL = 0;
		int rF = 0;
		int rL = 0;
		int nLengthPrev = 0;


		// 태그 제거 패턴
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");   
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");   
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");   
		   
		Matcher m;   
		   
		m = TAGS.matcher(targetText);   
		targetText = m.replaceAll("");  
		m = ENTITY_REFS.matcher(targetText);   
		targetText = m.replaceAll(" ");  
		m = WHITESPACE.matcher(targetText);   
		targetText = m.replaceAll(" "); 

		
		targetText=StringUtil.subStringBytes(targetText, cutLength);

		return targetText;
	}
	
	/**
	 * Ajax 통신을 위한 Json생성
	 * @param response
	 * @param json
	 */
	
/*	public static void jsonOut(HttpServletResponse response,Map<String,Object> map) {
		PrintWriter pw = null;
		try {
			JSONObject json = new JSONObject(map);
			pw = response.getWriter();
			pw.print(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=pw){
				pw.close();
			}
		}
	}
*/	
	public static String commonMakeSelectBox(List<Map<String,Object>> list,String selectedCode) throws Exception {
    	StringBuffer sb = new StringBuffer();
        try {
        	if(list.size()!=0){
        		for (Map<String,Object> map : list) {
        			if(null!=selectedCode){
        				if(map.get("XC_CD").equals(selectedCode)){
        					sb.append("<option value='"+map.get("XC_CD")+"' selected>");
        				} else {
        					sb.append("<option value='"+map.get("XC_CD")+"'>");
        				}
        				sb.append(map.get("XC_CDNM"));
        				sb.append("</option>");
        			}else{
        				sb.append("<option value='"+map.get("XC_CD")+"'>");
	    				sb.append(map.get("XC_CDNM"));
	    				sb.append("</option>");
        			}
				}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
	
	
	public static String replace(String str, String oldStr, String newStr){
		if(null == str ) {
			return null;
		}
		if(null == oldStr || null == newStr || 0 == oldStr.length()) {
			return str;
		}
		int i = str.lastIndexOf(oldStr);
		if ( i<0) {
			return str;
		}
		StringBuffer sbuf = new StringBuffer(str);
		
		while (i >=0) {
			sbuf.replace(i, (i+oldStr.length()), newStr);
			i = str.lastIndexOf(oldStr, i -1);
		}
		
		
		return sbuf.toString();
	}
	
	
}
