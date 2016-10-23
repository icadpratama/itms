package id.gts.itms.sign.model;

import java.io.Serializable;
import java.util.Date;

public class SignUp implements Serializable {
	private static final long serialVersionUID = 4316942118536886462L;
	
	private long userid;
	private String username;
	private String password;
	private Character privilege;
	private Character status;
	private Date createddate;
	private String createdby;
	private Date updatedate;
	private String updatedby;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Character getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Character privilege) {
		this.privilege = privilege;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
}
