package newx.util;


import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

public class StringUtil {
	private StringUtil() {
	}
	
	public static boolean equals(String src, String dest) {
		if(src == null) {
			if(dest == null)
				return true;
			else
				return false;
		}
		return src.equals(dest);
	}

	public static boolean equalsIgnoreNull(String src, String dest) {
		if(src == null) {
			if(dest == null || dest.length() == 0)
				return true;
			else
				return false;
		}
		if(src.length() == 0)
			return dest == null || dest.length() == 0;
		return src.equals(dest);
	}
	
	public static boolean equalsIgnoreCase(String src, String dest) {
		if(src == null) {
			if(dest == null)
				return true;
			else
				return false;
		}
		return src.equalsIgnoreCase(dest);
	}
	
	public static String nullToEmpty(String str) {
		return str == null ? "" : str;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static int compareTo(String src, String dest) {
		int result = 0;
		if(src == null)
			result = dest == null ? 0 : 1;
		else
			result = dest == null ? 1 : src.compareTo(dest);
		return result;
	}
	
	/**
	 * 字符串替换函数。不使用规则表达式。
	 * @param str
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replace(String str, String from, String to) {
		StringBuilder buff = new StringBuilder();
		int fromLen = from.length();
		int start = 0;
		int index;

		while ((index = str.indexOf(from, start)) != -1) {
			buff.append(str.substring(start, index));
			buff.append(to);
			start = index + fromLen;
		}
		if(start == 0)
			return str;
		buff.append(str.substring(start));

		return buff.toString();
	}
	
	/**
	 * 替换了Str中包含的特殊Html字符
	 * @param str
	 * @return
	 */
	public static String replaceHTML(Object field) {
		if (field == null)
			return null;
		else
			return replaceHTML("" + field);
	}
	public static String replaceHTML(String str) {
		if (str == null)
			return null;
		
		StringBuilder buff = new StringBuilder();
		int start = 0;
		
		for(int i = 0; i < str.length(); i++) {
			switch(str.charAt(i)) {
				case '&':
					buff.append(str.substring(start, i)).append("&amp;");
					start = i + 1;
					break;
				case '<':
					buff.append(str.substring(start, i)).append("&lt;");
					start = i + 1;
					break;
				case '>':
					buff.append(str.substring(start, i)).append("&gt;");
					start = i + 1;
					break;
				case '\'':
					buff.append(str.substring(start, i)).append("&apos;");
					start = i + 1;
					break;
				case '"':
					buff.append(str.substring(start, i)).append("&quot;");
					start = i + 1;
					break;
			}
		}
		buff.append(str.substring(start));
		return buff.toString();
	}

	public static List<String> split(String src, String split) {
		List<String> result = new ArrayList<String>();
		int index = src.indexOf(split);
		int from  = 0;
		while(index >= 0) {
			result.add(src.substring(from, index));
			from = index + split.length();
			index = src.indexOf(split, from);
		}
		result.add(src.substring(from));
		return result;
	}

	public static String readFromStream(InputStream is,String charset) throws IOException{
        
        BufferedInputStream in = new BufferedInputStream(is);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();

        BufferedOutputStream bos = new BufferedOutputStream(bo);

        int readed = 0;

        byte[] tmpBytes = new byte[1024];
        while ( (readed = in.read(tmpBytes)) != -1) {
            bos.write(tmpBytes, 0, readed);
        }

        in.close();
        bos.close();

        byte[] bs = bo.toByteArray();

        String s = new String(bs, charset);
        return s;
    }
	
	public static int indexOf(List<String> lst, String value){
		for(int i=0; i<lst.size(); i++){
			if(lst.get(i).equalsIgnoreCase(value)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将普通字符转换成unicode字符；例如输入 "name=你好",返回 "name=\u4f60\u597d"
	 * @param str
	 * @return
	 */
	public static String asciiToUnicode(String str) {
		char[] chararray = str.toCharArray();
		StringBuffer unisb = new StringBuffer();
		for (int i = 0; i < chararray.length; i++) {
			int c = (int) chararray[i];
			if (c > 0xff) {
				unisb.append("\\u");
				unisb.append(Integer.toHexString((int) chararray[i]));
			} else {
				unisb.append(chararray[i]);
			}
		}
		return unisb.toString();
	}
	
	/**
	 * 将unicode形式的字符转换成ascii字符；例如输入："name=\u4f60\u597d" 返回"name=你好"
	 * @param str
	 * @return
	 */
	public static String unicodeToAscii(String str){
		return new String(str.getBytes());
	}
	
	/**
	 * 判断name里面是否含有不合法字符  !"#$%&'()*+,-./:;<=>?@[\]^`{|}~和中文符号 ，。、《》？；‘：”】【『』！·～、|——  
	 * 中的某个或某些,
	 * @param name
	 * @return 含有那个不合法的字符,如果返回null则意味着所以字符均合法
	 */
	public static String checkStringValid(String name) {
//		if(name == null || name.trim().equals("")){
//			return "输入不允许为空";
//		}

		Pattern p = Pattern.compile("(?!\\_)\\p{Punct}");
		Matcher m = p.matcher(name);
		if(m.find())
			return "输入包含以下非法字符： " + name.substring(m.start(),m.start() + 1);
		else{
			Pattern pp=Pattern.compile("，|。|、|《|》|？|；|‘|：|”|】|【|『|』|！|·|～|、|\\||——|…|（|）|￥");
		    Matcher mm=pp.matcher(name);
		    if(mm.find())
				return "输入包含以下非法字符： " + name.substring(mm.start(),mm.start() + 1);
		}
		return null;
	}
	
	/**
	 * 判断字符串中是否含有中文
	 * @param name
	 * @return
	 */
	public static boolean isIncludesChinese(String name){
		Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
	    Matcher m=p.matcher(name);
	    return m.find();

	}
	
	// 将 str 进行 BASE64 编码
	public static String getBASE64(String str) {
		if (str == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(str.getBytes());
	}

	// 将 BASE64 编码的字符串 str 进行解码
	public static String getFromBASE64(String str) {
		if (str == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(str);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	// 将 str 进行 BASE64 编码
	public static String encodeBASE64(String str) {
		if (str == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(str.getBytes());
	}
	
	/**
	 * 判断输入的字符串首字母是否为英文字符
	 * @param str
	 * @return
	 */
	public static boolean isCharAtFirst(String str) {
		char ch = str.toLowerCase().charAt(0);
		if((ch >='a' && ch <= 'z'))
			return true;
		else
			return false;
	}
	
	/**
	 * 判断输入的字符串首字母是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumberAtFirst(String str) {
		char ch = str.toLowerCase().charAt(0);
		if((ch >='0' && ch <= '9'))
			return true;
		else
			return false;
	}
	
	/**
	 * 去除中文空格
	 * @param str
	 * @return
	 */
	public static String trimChineseBlank(String str) {
		if(isNullOrEmpty(str))
			return str;
		String ret = str;
		Pattern p=Pattern.compile("　");
		Matcher m = p.matcher(str);
		while(m.find()){
			ret = str.replace(str.substring(m.start(), m.start()+1), "");
		}
		return ret.trim();
	}
	
	/**
	 * 验证日期字符串
	 * @param validStr 检验字符串 默认格式为 yyyy-MM-dd
	 * @return
	 */
	public static boolean isDateStr(String validStr) {
		return isDateStr(validStr, "yyyy-MM-dd");
	}
	
	/**
	 * 验证日期字符串
	 * @param validStr 检验字符串
	 * @param format   日期格式
	 * @return
	 */
	public static boolean isDateStr(String validStr, String format) {
		try {
			SimpleDateFormat simpleDate = new SimpleDateFormat(format);
			simpleDate.setLenient(false);
			Date date1 = simpleDate.parse(validStr);
			String vat = simpleDate.format(date1);
			if(!vat.equals(validStr))
				return false;
			else 
				return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	/**
	 * 判断字符串checkStr是否在字符串数组checkSet之中
	 * 
	 * @param checkStr
	 *            待判断的字符串
	 * @param checkSet
	 *            待检查的字符串数组
	 * @return
	 */
	public static boolean isInArray(String checkStr, String[] checkSet) {
		return (checkSet == null) ? false : Arrays.asList(checkSet).contains(checkStr);
	}

	/**
	 * 将字符串数组转换为字符串
	 * 
	 * @param strArray
	 * @param separator
	 * @return
	 */
	public static String arrayToString(String[] strArray, String separator) {
		if (strArray == null)
			return null;
		return listToString(Arrays.asList(strArray), separator);
	}

	/**
	 * 将字符串列表转换为字符串
	 * 
	 * @param strArray
	 * @param separator
	 * @return
	 */
	public static String listToString(List<String> strList, String separator) {
		if (strList == null)
			return null;
		if (separator == null)
			separator = "";
		StringBuffer buffer = new StringBuffer();
		for (String str : strList)
			buffer.append(str).append(separator);
		String ret = buffer.toString();
		return ret.substring(0, ret.length() - separator.length());
	}
	
	public static String decodeHTML(String str) {
		if (str == null)
			return null;
		String newString = str;
		if(newString.indexOf("&amp;") != -1)
			newString = replace(newString, "&amp;", "&");
		
		if(newString.indexOf("&lt;") != -1)
			newString = replace(newString, "&lt;", "<");
		
		if(newString.indexOf("&gt;") != -1)
			newString = replace(newString, "&gt;", ">");
	
		if(newString.indexOf("&apos;") != -1)
			newString = replace(newString, "&apos;", "'");
		
		if(newString.indexOf("&quot;") != -1)
			newString = replace(newString, "&quot;", "\"");
		
		if(newString.indexOf("&nbsp;") != -1)
			newString = replace(newString, "&nbsp;", " ");
		return newString;
	}
	
	public static boolean parseDoubleEquals(String src, String dest) {
		double dsrc = 0;
		double ddest = 0;
		try {
			dsrc = Double.parseDouble(src);
			ddest = Double.parseDouble(dest);
		} catch (Exception e) {
			return false;
		}
		return dsrc == ddest;
	}
	
	public static Color parseToColor(final String c) {
		return parseToColor(c, Color.WHITE);
	}

	public static Color parseToColor(final String c, Color defaultColor) {
		if (isNullOrEmpty(c))
			return defaultColor;
		String sc = c.trim();
		if (sc.charAt(0) == '#')
			sc = sc.substring(1);
		try {
			return new Color(Integer.parseInt(sc, 16));
		} catch (NumberFormatException e) {
			return defaultColor;
		}
	}
	
	private static Map<String, Long> profileMap = new HashMap<String, Long>();

	public static void profile(String label) {
		if (label == null)
			label = "default";
		long t = System.currentTimeMillis();
		if (profileMap.containsKey(label)) {
			long t0 = profileMap.remove(label);
			System.out.println("profile[" + label + "]: " + (t - t0) + " ms.");
		} else {
			profileMap.put(label, t);
		}
	}
	
	public static String MD5(String str){
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
