package jehc.xtmodules.xtcore.allutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	/**
	 * 将List<String>转字符串
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	/**
	 * 判断字符串是否全部由数值组成
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @return
	 */
	public final static boolean isNumeric(String sCheck) {
		if (sCheck == null) {
			return false;
		}
		if (sCheck.length() == 0) {
			return false;
		}
		String numStr = "0123456789";
		for (int i = 0; i < sCheck.length(); i++) {
			if (numStr.indexOf(sCheck.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否为空 null/空值/全部由空字符串组成都视为空值
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @return
	 */
	public final static boolean isEmpty(String sCheck) {
		return (sCheck == null || replace(sCheck, " ", "").equals(""));
	}

	/**
	 * 判断字符串是否为整数 包括正负
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @return
	 */
	public final static boolean isInt(String sCheck) {
		if (isEmpty(sCheck))
			return false;

		if (sCheck.substring(0, 1).equals("-"))
			sCheck = sCheck.substring(1);

		if (!isNumeric(sCheck))
			return false;

		if (Long.parseLong(sCheck) > 2147483647) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否为实数
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @return
	 */
	public final static boolean isFloat(String sCheck) {
		if (isEmpty(sCheck)) {
			return false;
		}
		if (sCheck.indexOf(".") != -1) {
			int dotPos = sCheck.indexOf(".");
			sCheck = sCheck.substring(0, dotPos) + sCheck.substring(dotPos + 1);
		}
		if (!isNumeric(sCheck))
			return false;
		return true;
	}

	/**
	 * 获取字符串，如果传入的字符串为null或空，使用默认值作为返回值
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @param sReplace
	 *            如果字符串为空时的替换值
	 * @return
	 */
	public final static String getString(Object obj, String sReplace) {
		if (obj == null) {
			return sReplace;
		}
		String sRet = obj.toString();
		if (sRet.length() == 0) {
			return sReplace;
		}
		return sRet;
	}

	/**
	 * 获取字符串，如果传入的字符串为null或空，返回空值
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @return
	 */
	public final static String getString(String sCheck) {
		return getString(sCheck, "");
	}

	/**
	 * 获取整数，如果传入的字符串不是整数数值，使用默认值作为返回值
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @param iReplace
	 *            如果字符串为空时的替换数值
	 * @return
	 */
	public final static int getInt(String sCheck, int iReplace) {
		if (!isInt(sCheck))
			return iReplace;
		return Integer.parseInt(sCheck);
	}

	/**
	 * 获取实数，如果传入的字符串不是数值，使用默认值作为返回值
	 * 
	 * @param sCheck
	 *            被检测的字符串
	 * @param nReplace
	 *            如果字符串为空时的替换数值
	 * @return
	 */
	public final static float getFloat(String sCheck, float nReplace) {
		if (!isFloat(sCheck))
			return nReplace;
		return Float.parseFloat(sCheck);
	}

	/**
	 * 将编码不准确的中文字符串转换为可以正常显示的中文输出 目前仅能转换符合iso8859-1编码的字符串到GBK <该方法需要重新修订>
	 * 
	 * @param sOrigin
	 *            被转换的字符串
	 * @return
	 */
	public final static String str2Chs(String sOrigin) {
		byte[] arrayByte;
		try {
			int i = 0;
			arrayByte = sOrigin.getBytes("iso8859-1");
			for (i = 0; i < arrayByte.length; i++)
				if (arrayByte[i] + 0 < 0)
					return new String(arrayByte, "GBK");
			arrayByte = sOrigin.getBytes("GBK");
			for (i = 0; i < arrayByte.length; i++)
				if (arrayByte[i] + 0 < 0)
					return new String(arrayByte, "GBK");
		} catch (Exception e) {
		}
		return sOrigin;
	}

	public final static String number2Chs(int nOrigin, boolean bCaptial) {
		String[] aNumList = { "○", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String[] aBigNumList = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌",
				"玖" };
		if (bCaptial) {
			return aBigNumList[nOrigin];
		} else {
			return aNumList[nOrigin];
		}
	}

	/**
	 * 将数值转换为十进制中文，例如:123.45转换为一百二十三点四五
	 * 
	 * @param nVariant
	 * @return
	 */
	public final static String num2Chs(float nVariant) {
		return "";
	}

	/**
	 * 将数值转换为十进制中文表示的货币，例如123.45转换为壹佰贰拾叁圆肆角伍分
	 * 
	 * @param nVariant
	 * @return
	 */
	public final static String currency2Chs(float nVariant) {
		return "";
	}

	/**
	 * 替换字符串中指定的子串
	 * 
	 * @param sOrigin
	 *            被替换的字符串
	 * @param sPattern
	 *            被替换的子串
	 * @param sReplaceBy
	 *            替换后的子串
	 * @return
	 */
	public final static String replace(String sOrigin, String sPattern,
			String sReplaceBy) {
		if (sOrigin == null) {
			return "";
		}
		int s = 0;
		int e = 0;
		StringBuffer sbResult = new StringBuffer();
		while ((e = sOrigin.indexOf(sPattern, s)) >= 0) {
			sbResult.append(sOrigin.substring(s, e));
			sbResult.append(sReplaceBy);
			s = e + sPattern.length();
		}
		sbResult.append(sOrigin.substring(s));
		return sbResult.toString();
	}

	/**
	 * 将字符串分割，返回数组
	 * 
	 * @param str
	 *            待分割的字符串
	 * @param pattern
	 *            分隔符
	 * @return 数组
	 */
	public static String[] split(String str, String pattern) {
		Vector<String> strset = new Vector<String>();
		int s = 0, e = 0;
		while ((e = str.indexOf(pattern, s)) >= 0) {
			strset.addElement(str.substring(s, e));
			s = e + pattern.length();
		}
		if (s != str.length())
			strset.addElement(str.substring(s, str.length()));
		else if (s == 0)
			strset.addElement("");
		else
			strset.addElement("");
		int len = strset.size();
		String result[] = new String[len];
		for (int i = 0; i < len; i++)
			result[i] = (String) strset.elementAt(i);
		return result;
	}

	/**
	 * 使用指定的字符填充在字符串的左面
	 * 
	 * @param sOrigin
	 *            未填充的字符串
	 * @param sRepeat
	 *            指定填充的字符串
	 * @param nLen
	 *            最终返回字符串的长度
	 * @return
	 */
	public final static String lpad(String sOrigin, String sRepeat, int nLen) {
		sOrigin = getString(sOrigin);
		if (sOrigin.length() >= nLen)
			return sOrigin;
		for (int i = 0; i < nLen - sOrigin.length(); i++) {
			sOrigin = sRepeat + sOrigin;
		}
		return sOrigin;
	}

	/**
	 * 使用指定的字符填充在字符串的右面
	 * 
	 * @param sOrigin
	 *            未填充的字符串
	 * @param sRepeat
	 *            指定填充的字符串
	 * @param nLen
	 *            最终返回字符串的长度
	 * @return
	 */
	public final static String rpad(String sOrigin, String sRepeat, int nLen) {
		sOrigin = getString(sOrigin);
		if (sOrigin.length() >= nLen)
			return sOrigin;
		for (int i = 0; i < nLen - sOrigin.length(); i++) {
			sOrigin += sRepeat;
		}
		return sOrigin;
	}

	/**
	 * 去除字符串两边的空格
	 * 
	 * @param sOrigin
	 *            原始字符串
	 * @return
	 */
	public final static String trim(String sOrigin) {
		if (sOrigin == null) {
			return null;
		}
		return sOrigin.trim();
	}

	/**
	 * 返回字符串左面定长的字符串
	 * 
	 * @param sOrigin
	 *            原始字符串
	 * @param iLen
	 *            需要返回的长度
	 * @return
	 */
	public static String left(String sOrigin, int iLen) {
		sOrigin = getString(sOrigin);
		if (isEmpty(sOrigin))
			return "";
		if (sOrigin.length() <= iLen)
			return sOrigin;
		return sOrigin.substring(0, iLen);
	}

	/**
	 * 返回字符串右面定长的字符串
	 * 
	 * @param sOrigin
	 *            原始字符串
	 * @param iLen
	 *            需要返回的长度
	 * @return
	 */
	public static String right(String sOrigin, int iLen) {
		sOrigin = getString(sOrigin);
		if (isEmpty(sOrigin))
			return "";
		if (sOrigin.length() <= iLen)
			return sOrigin;
		return sOrigin.substring(sOrigin.length() - iLen);
	}

	/**
	 * 返回定长的字符串 中文长度为2
	 * 
	 * @param sVariant
	 * @param iLen
	 * @return
	 */
	public static String getLenStr(String sVariant, int iLen) {
		if (sVariant == null || sVariant.length() == 0) {
			return "";
		}
		if (iLen < 0) {
			return "";
		}
		if (sVariant.length() > iLen) {
			return sVariant.substring(0, iLen);
		} else {
			return sVariant;
		}
	}

	/**
	 * 返回定长的字符串,并添加后缀 中文长度为2，长度包括后缀长度
	 * 
	 * @param sVariant
	 * @param iLen
	 * @param sSuffix
	 * @return
	 */
	public static String getLenStr(String sVariant, int iLen, String sSuffix) {
		return "";
	}

	/**
	 * 检测用指定分隔符分隔的字符串中有没有某个子串
	 */
	public static boolean foundStr(String sParent, String sChild,
			String sSeprator) {
		return ((sSeprator + sParent + sSeprator).indexOf(sSeprator + sChild
				+ sSeprator) != -1);
	}

	/**
	 * 检测用指定分隔符","分隔的字符串中有没有某个子串
	 */
	public static boolean foundStr(String sParent, String sChild) {
		return foundStr(sParent, sChild, ",");
	}

	/**
	 * 将字符串转换为%uABCD的格式，同javaScript的escape函数。
	 */
	public final static String escape(String str) {
		String stmp = "";

		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c < 128) {
				String s = Integer.toString(c, 16);
				while (s.length() < 2) {
					s = "0" + s;
				}
				stmp += "%" + s;
			} else {
				String s = Integer.toString(c, 16);
				while (s.length() < 4) {
					s = "0" + s;
				}
				stmp += "%u" + s;
			}
		}
		return stmp;
	}

	/**
	 * 同javascript里的unescape函数
	 * 
	 * @param s
	 * @return
	 */
	public final static String unescape(String s) {
		String stmp = "";
		for (int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i + 1);
			if (c.equals("%")) {
				if (i < s.length() - 1) {
					String c2 = s.substring(i + 1, i + 2);
					if (c2.equalsIgnoreCase("u")) {
						String sHex = s.substring(i + 2, i + 6);
						stmp += (char) Integer.parseInt(sHex, 16);
						i += 5;
					} else {
						String sHex = s.substring(i + 1, i + 3);
						stmp += (char) Integer.parseInt(sHex, 16);
						i += 2;
					}
				} else {
					stmp += "%";
				}
			} else {
				stmp += c;
			}
		}

		return stmp;
	}

	public static String getDivideValue(String sNum1, String sNum2, int nDotNum) {
		try {
			double nNum1 = Double.parseDouble(sNum1);
			double nNum2 = Double.parseDouble(sNum2);
			double nDivideValue = nNum1 / nNum2;
			String sDivideValue = nDivideValue + "";
			if (sDivideValue.indexOf("E") > 0) {
				String sTmp = sDivideValue.substring(
						sDivideValue.indexOf("E") + 2, sDivideValue.length());
				sDivideValue = sDivideValue.substring(0,
						sDivideValue.indexOf("E"));
				sDivideValue = replace(sDivideValue, ".", "");
				for (int i = 1; i <= Integer.parseInt(sTmp); i++) {
					if (i == Integer.parseInt(sTmp)) {
						sDivideValue = "0." + sDivideValue;
					} else {
						sDivideValue = "0" + sDivideValue;
					}
				}
			}
			if (sDivideValue.indexOf(".") + 1 + nDotNum <= sDivideValue
					.length() - 1) {
				return sDivideValue.substring(0, sDivideValue.indexOf(".") + 1
						+ nDotNum);
			} else {
				return sDivideValue;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String getPhysicalSize(String sSizeOfByte, int nDotNum) {
		String sSizeOfKB = getDivideValue(sSizeOfByte, "1024", nDotNum);
		if (sSizeOfKB != null) {
			if (sSizeOfKB.indexOf(".") >= 4) {
				int nTmp = 1024 * 1024;
				return getDivideValue(sSizeOfByte, String.valueOf(nTmp),
						nDotNum) + "MB";
			} else {
				return sSizeOfKB + "KB";
			}
		} else {
			return null;
		}
	}

	public final static String HTMLEncode(String str) {
		String sReturn = "";
		sReturn = replace(str, "&", "&amp;");
		sReturn = replace(sReturn, "<", "&lt;");
		sReturn = replace(sReturn, ">", "&gt;");
		sReturn = replace(sReturn, "\"", "&quot;");
		return sReturn;

	}

	/**
	 * 将英文字符转换为简体中文，如果输入的就是中文，则不会作改变
	 * 
	 * @param s
	 *            英文字符
	 * @return 转换后的字符
	 */
	public final static String toChinese(String s) {
		try {

			byte abyte0[] = s.getBytes("iso8859-1");
			for (int i = 0; i < abyte0.length; i++)
				if (abyte0[i] + 0 < 0)
					return new String(abyte0, "GBK");

			abyte0 = s.getBytes("GBK");
			for (int j = 0; j < abyte0.length; j++)
				if (abyte0[j] + 0 < 0)
					return new String(abyte0, "GBK");

		} catch (Exception exception) {
		}
		return s;
	}

	public final static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 将null转换为空字符串，不是null的字符串将截去头尾的空格
	 * 
	 * @param s
	 * @return
	 */
	public final static String chgNullToAEmptyStr(String s) {
		if (s == null)
			s = "";
		return s.trim();
	}

	/**
	 * 把字符串数组转成字符串;
	 * 
	 * @param arr
	 *            数组;
	 * @param joinCode
	 *            连接符
	 * @return 返回字符串；
	 */
	public static String arrToString(String[] arr, String joinCode) {
		if (arr == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0, n = arr.length; i < n; i++) {
			sb.append(arr[i]);

			if (i < (n - 1)) {
				sb.append(joinCode);
			}
		}

		return sb.toString();
	}

	/**
	 * @param str
	 * @param joinCode
	 * @return String
	 */
	public static String toCharStr(String str, String joinCode) {
		String[] arr = str.split(joinCode);

		return arrToCharStr(arr, joinCode);
	}

	/**
	 * 
	 * @param arr
	 * @param joinCode
	 * @return string
	 */
	public static String arrToCharStr(String[] arr, String joinCode) {
		StringBuffer sb = new StringBuffer();

		if ((arr != null) && (arr.length > 0)) {
			for (int i = 0, n = arr.length; i < n; i++) {
				sb.append("'").append(arr[i]).append("'");

				if (i < (n - 1)) {
					sb.append(joinCode);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 分解带有连接符的字符串数组；
	 * @param arr
	 * @param joinTag
	 * @param index
	 * @return String[]
	 */
	public static String[] joinStrArrToSingleStrArr(String[] arr,
			String joinTag, int index) {
		String[] rtArr;

		if ((arr == null) || (arr.length == 0) || (index < 0)) {
			return new String[0];
		}

		if (StringUtil.isEmpty(joinTag)) {
			rtArr = arr;
		} else {
			rtArr = new String[arr.length];

			for (int i = 0, n = arr.length; i < n; i++) {
				String[] temp = arr[i].split(joinTag);

				if (index > (temp.length - 1)) {
					index = temp.length - 1;
				}

				rtArr[i] = temp[index];
			}
		}

		return rtArr;
	}
	
	/**
	 * 根据分隔符 返回LIST集合
	 * @param string
	 * @param split
	 * @return
	 */
	public List<String> doResultStrList(String string,String split){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isEmpty(string)){
			String [] array = string.split(split);
			for(int i = 0; i < array.length; i++){
				list.add(array[i]);
			}
		}
		return list;
	}
	
	public static void main(String[]args){
	}
}
