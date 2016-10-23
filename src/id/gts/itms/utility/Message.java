package id.gts.itms.utility;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "message")
@SessionScoped
public class Message implements Serializable {
	private static final long serialVersionUID = 5683823739624540315L;
	private static Properties prop;
	private static MessageFormat messageFormat;
	
	static{
		try{
	    	prop = new Properties();
	    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    	prop.load(cl.getResourceAsStream("\\resources\\message.properties"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String get(String message) {
		return prop.getProperty(message);
	}
	
	public int getInt(String message) {
		String msg = prop.getProperty(message).trim();
		return "".equals(msg) ? 0 : Integer.valueOf(msg);
	}
	
	public String get(String message, String param) {
		messageFormat = new MessageFormat(prop.getProperty(message));
		return messageFormat.format(new Object[] { param });
	}
	
	public static String getMessage(String message) {
		return prop.getProperty(message);
	}
	
	public static String getMessage(String message, String...params) {
		messageFormat = new MessageFormat(prop.getProperty(message));
		return messageFormat.format(params);
	}
	
	public static int getInteger(String message) {
		String msg = prop.getProperty(message).trim();
		return "".equals(msg) ? 0 : Integer.valueOf(msg);
	}
	
	public static long getLong(String message) {
		String msg = prop.getProperty(message).trim();
		return "".equals(msg) ? 0 : Long.valueOf(msg);
	}
}
