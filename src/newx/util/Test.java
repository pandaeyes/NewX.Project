package newx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;

public class Test {
	
	public static void main(String[] args) {
		testUrl();
	}
	
	public static void testException() {
		System.out.println("fdfds");
//		try {
//			System.out.println(4%0);
//		} catch(Exception e) {
//        	throw new NewXException(CommonErrorCode.SINGLE_RECORD_ERROR, e);
//        }
	}
	public static void testUrl() {
		System.out.println(Test.class.getResource("/java/util/ArrayList.class"));
	}

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		// TODO Auto-generated method stub

//		String s = "dir/action/MainAction?menuid=\"demo_demo2_menu1\"&actionT";
//		System.out.println(s.replaceAll("\"", "\\\\\""));
		
		String sql = "update OB04 set BOE559=$( BOE_559 ),AAE011= ${ AAE011 },AAE011= $[ AAE011],AAH011 = :AAH011, AAE011= ${AAE011}";
//		String regEx = "(\\$\\{(\\s*\\w+\\s*)\\})|(\\$\\((\\s*\\w+\\s*)\\))";
		String regEx = "(\\$\\{\\s*(\\w+)\\s*\\})|(\\$\\(\\s*(\\w+)\\s*\\))|(\\$\\[\\s*(\\w+)\\s*\\])|(:(\\w+))";
		String regEx2 = "(\\$\\{\\s*(\\w+)\\s*\\})|(\\$\\(\\s*(\\w+)\\s*\\))|(\\$\\[\\s*(\\w+)\\s*\\])";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(sql);
		List<String> l = new ArrayList<String>();
		while (m.find()) {
			if (m.group(1) != null) {
				l.add(m.group(2));
				sql = sql.replaceFirst(regEx2, ":" + m.group(2));
			} else if (m.group(3) != null) {
				l.add(m.group(4));
				sql = sql.replaceFirst(regEx2, ":" + m.group(4));
			} else if (m.group(5) != null) {
				l.add(m.group(6));
				sql = sql.replaceFirst(regEx2, ":" + m.group(6));
			} else if (m.group(7) != null) {
				l.add(m.group(8));
			}
			System.out.println(">" + m.group(1) + "<");
			System.out.println(">" + m.group(2) + "<");
			System.out.println(">" + m.group(3) + "<");
			System.out.println(">" + m.group(4) + "<");
			System.out.println(">" + m.group(5) + "<");
			System.out.println(">" + m.group(6) + "<");
			System.out.println(">" + m.group(7) + "<");
			System.out.println(">" + m.group(8) + "<");
			System.out.println("=========");
		}
//		for (String s : l) {
//			sql = sql.replaceFirst(regEx, ":" + s);
//		}
//		for (String s : l) {
//			s = s.replaceAll("\\$", "\\\\\\$")
//					.replaceAll("\\(", "\\\\\\(")
//					.replaceAll("\\)", "\\\\\\)")
//					.replaceAll("\\{", "\\\\\\{")
//					.replaceAll("\\}", "\\\\\\}");
//			sql = sql.replaceFirst(regEx, "\\?");
//		}
		System.out.println("==============sql:" + sql);
	}

}
