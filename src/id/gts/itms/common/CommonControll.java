package id.gts.itms.common;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import id.gts.itms.common.CommonUtil;

public class CommonControll {
	protected HtmlDataTable dataTable;
	
	public ExternalContext getExternalContext() {
		if (CommonUtil.isNotNullOrEmpty(FacesContext.getCurrentInstance())) 
			return FacesContext.getCurrentInstance().getExternalContext();
		return null;
	}
	
	public HttpSession getSession() {
		if (CommonUtil.isNotNullOrEmpty(getExternalContext())) 
			return (HttpSession) getExternalContext().getSession(false);
		return null;
	}
	
	public HttpServletRequest getRequest() {
		if (CommonUtil.isNotNullOrEmpty(getExternalContext()))
			return (HttpServletRequest) getExternalContext().getRequest();
		return null;
	}
	
	public HttpServletResponse getResponse() {
		if (CommonUtil.isNotNullOrEmpty(getExternalContext()))
			return (HttpServletResponse) getExternalContext().getResponse();
		return null;
	}
	
	public void resetDataScroller(ActionEvent e) {
        if(dataTable!= null) {
            dataTable.setFirst(0);
        }
    }

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
}
