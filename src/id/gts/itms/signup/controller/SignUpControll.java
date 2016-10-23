package id.gts.itms.signup.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import id.gts.itms.common.CommonControll;
import id.gts.itms.common.CommonUtil;
import id.gts.itms.profile.model.Profile;
import id.gts.itms.sign.dao.SignUpDAO;
import id.gts.itms.sign.model.SignUp;
import id.gts.itms.utility.Message;

@ManagedBean(name="signUp")
@RequestScoped
public class SignUpControll extends CommonControll implements Serializable {
	private static final long serialVersionUID = 8212310016456102392L;
	private SignUp user = new SignUp();
	private Profile profile = new Profile();
	private SignUpDAO dao = new SignUpDAO();
	private String errorMsg;
	
	@PostConstruct
	void init() {
	    
	}
	
	public void onLoad(){
		String alert = (String) getExternalContext().getFlash().get("alert");
		
		if (CommonUtil.isNotNullOrEmpty(alert)) {
			if ("success".equalsIgnoreCase(alert)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sign Up is success","Please check your email to activate your account"));
			}
			
			if ("failed".equalsIgnoreCase(alert)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign Up is failed","Make sure all forms is filled"));
			}
			
			if ("warning".equalsIgnoreCase(alert)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"E-Mail activation","Your account is not activated. Check your email for account activation!"));
			}
			
			if ("fatal".equalsIgnoreCase(alert)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Password Failed","Your password is invalid"));
			}
		}
	}
	
	public String doSave(){
		if (!CommonUtil.isNotNullOrEmpty(profile)) {
			if (CommonUtil.isNullOrEmpty(profile.getFirstname())) {
				errorMsg = Message.getMessage("message.required", Message.getMessage("label.form.firstname"));
				return "";
			}
			
			if (!CommonUtil.isNotNullOrEmpty(profile.getLastname())) {
				errorMsg = Message.getMessage("message.required", Message.getMessage("label.form.lastname"));
				return "";
			}
			
			if (!CommonUtil.isNotNullOrEmpty(profile.getEmail())) {
				errorMsg = Message.getMessage("message.required", Message.getMessage("label.form.email"));
				return "";
			}
			
			if (!CommonUtil.isNotNullOrEmpty(user.getPassword())) {
				errorMsg = Message.getMessage("message.required", Message.getMessage("label.form.password"));
				return "";
			}
		}
		
		try{
			user.setUsername(profile.getEmail());
			user.setCreatedby(profile.getEmail());
			user.setUpdatedby(profile.getEmail());
			
			boolean insert = dao.insertData(user);
			if (insert) {
				getExternalContext().getFlash().put("alert", "success");
			} else {
				getExternalContext().getFlash().put("alert", "failed");
			}
			
			return "goToLoginLogout";
		} catch (Exception e){
			return "goToLoginLogout";
		}
	}

	public SignUp getUser() {
		return user;
	}

	public void setUser(SignUp user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public SignUpDAO getDao() {
		return dao;
	}

	public void setDao(SignUpDAO dao) {
		this.dao = dao;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
