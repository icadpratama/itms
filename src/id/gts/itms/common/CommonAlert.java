package id.gts.itms.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessages;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.Attribute;
import com.sun.faces.renderkit.AttributeManager;
import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.MessagesRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage.Severity;

@FacesRenderer(componentFamily="javax.faces.Messages", rendererType="javax.faces.Messages")
public class CommonAlert extends MessagesRenderer implements Serializable {
	private static final long serialVersionUID = -7297683940239862330L;
	
	private static final Attribute[] ATTRIBUTES = 
            AttributeManager.getAttributes(AttributeManager.Key.MESSAGESMESSAGES);

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

        rendererParamsNotNull(context, component);
        if (!shouldEncode(component)) return;

        boolean mustRender = shouldWriteIdAttribute(component);
        UIMessages messages = (UIMessages) component;
        ResponseWriter writer = context.getResponseWriter();

        String clientId = ((UIMessages) component).getFor();
        if (clientId == null && messages.isGlobalOnly()) {
            clientId = "";
        }

        Iterator<?> messageIt = getMessageIter(context, clientId, component);
        if (!messageIt.hasNext()) {
            if (mustRender) {
                if ("javax_faces_developmentstage_messages".equals(component.getId())) {
                    return;
                }
                writer.startElement("div", component);
                writeIdAttributeIfNecessary(context, writer, component);
                writer.endElement("div");
            }
            return;
        }

        writeIdAttributeIfNecessary(context, writer, component);
        RenderKitUtils.renderPassThruAttributes(context, writer, component, ATTRIBUTES);

        Map<Severity, List<FacesMessage>> msgs = new HashMap<Severity, List<FacesMessage>>();
        msgs.put(FacesMessage.SEVERITY_INFO, new ArrayList<FacesMessage>());
        msgs.put(FacesMessage.SEVERITY_WARN, new ArrayList<FacesMessage>());
        msgs.put(FacesMessage.SEVERITY_ERROR, new ArrayList<FacesMessage>());
        msgs.put(FacesMessage.SEVERITY_FATAL, new ArrayList<FacesMessage>());

        while (messageIt.hasNext()) {
            FacesMessage curMessage = (FacesMessage) messageIt.next();
            if (curMessage.isRendered() && !messages.isRedisplay()) {
                continue;
            }
            msgs.get(curMessage.getSeverity()).add(curMessage);
        }

        List<FacesMessage> severityMessages = msgs.get(FacesMessage.SEVERITY_FATAL);
        if (!severityMessages.isEmpty()){
            encodeSeverityMessages(context, messages, FacesMessage.SEVERITY_FATAL, severityMessages);
        }

        severityMessages = msgs.get(FacesMessage.SEVERITY_ERROR);
        if (!severityMessages.isEmpty()){
            encodeSeverityMessages(context, messages, FacesMessage.SEVERITY_ERROR, severityMessages);
        }

        severityMessages = msgs.get(FacesMessage.SEVERITY_WARN);
        if (!severityMessages.isEmpty()){
            encodeSeverityMessages(context, messages, FacesMessage.SEVERITY_WARN, severityMessages);
        }

        severityMessages = msgs.get(FacesMessage.SEVERITY_INFO);
        if (!severityMessages.isEmpty()){
            encodeSeverityMessages(context, messages, FacesMessage.SEVERITY_INFO, severityMessages);
        }
    }

    private void encodeSeverityMessages(FacesContext facesContext, UIMessages uiMessages, 
                                        Severity severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        String alertSeverityClass = "";

        if (FacesMessage.SEVERITY_INFO.equals(severity)) {
            alertSeverityClass = "alert-success";
        } else if (FacesMessage.SEVERITY_WARN.equals(severity)) {
            alertSeverityClass = "alert-attention";
        } else if (FacesMessage.SEVERITY_ERROR.equals(severity)) {
            alertSeverityClass = "alert-error";
        } else if (FacesMessage.SEVERITY_FATAL.equals(severity)) {
            alertSeverityClass = "alert-error";
        }

        writer.startElement("div", null);
        writer.writeAttribute("class", "alert-box " + alertSeverityClass, "alert " + alertSeverityClass);
        
        for (FacesMessage msg : messages){
            String summary = msg.getSummary() != null ? msg.getSummary() : "";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;
            
            if (uiMessages.isShowSummary()) {
                writer.startElement("h6", uiMessages);
                writer.writeText(summary, uiMessages, null);
                writer.endElement("h6");
            }

            if (uiMessages.isShowDetail()) {
            	writer.startElement("span", uiMessages);
                writer.writeText("" + detail, "");
                writer.endElement("span");
            }
            msg.rendered();
        }
        writer.endElement("div");
    }

}
