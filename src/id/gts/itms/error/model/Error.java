package id.gts.itms.error.model;

import java.io.Serializable;
import java.util.Date;

public class Error implements Serializable {
	private static final long serialVersionUID = 6202705787586706082L;
	private long id;
	private String type;
	private String message;
	private String errorby;
	private Date errordate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorby() {
		return errorby;
	}
	public void setErrorby(String errorby) {
		this.errorby = errorby;
	}
	public Date getErrordate() {
		return errordate;
	}
	public void setErrordate(Date errordate) {
		this.errordate = errordate;
	}
}
