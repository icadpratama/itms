package id.gts.itms.profile.model;

import java.io.Serializable;

public class Profile implements Serializable {
	private static final long serialVersionUID = -8337397750780937134L;
	
	private String firstname;
	private String lastname;
	private String email;
	private String no_hp;
	private String address;
	private String city;
	private String identitas;
	private long userid;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNo_hp() {
		return no_hp;
	}
	public void setNo_hp(String no_hp) {
		this.no_hp = no_hp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIdentitas() {
		return identitas;
	}
	public void setIdentitas(String identitas) {
		this.identitas = identitas;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
