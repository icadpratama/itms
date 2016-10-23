package id.gts.itms.common;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;

public class CommonUtil {
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	
	public static Boolean isNotNullOrEmpty(Object object) {
		return !isNullOrEmpty(object);
	}
	
	@SuppressWarnings("rawtypes")
	public static Boolean isNullOrEmpty(Object object) {
		if (object == null) {
			return true;
		} else {
			if (object instanceof Collection) {
				if (((Collection) object).isEmpty()) {
					return true;
				}
			} else if (object instanceof AbstractMap) {
				if (((AbstractMap) object).isEmpty()) {
					return true;
				}
			} else if (object instanceof Long) {
				if (Long.parseLong(object.toString()) == 0) {
					return true;
				}
			} else {
				if (object.toString().trim().equals("")) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static String sqlDateFormat(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = "";
		
		if(CommonUtil.isNotNullOrEmpty(date)){
			sqlDate = "'" + df.format(date) + "'";
		} else{
			sqlDate = "'" + df.format(new Date()) + "'";
		}
		
		return sqlDate;
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = "";
		
		if(CommonUtil.isNotNullOrEmpty(date)){
			sqlDate = df.format(date);
		} else{
			sqlDate = df.format(new Date());
		}
		
		return sqlDate;
	}
	
	public static String sqlLikeFormat(String criteria){
		if(CommonUtil.isNotNullOrEmpty(criteria)){
			return "'%" + criteria + "%'";
		}
		
		return "";
	}
	
	public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
}
