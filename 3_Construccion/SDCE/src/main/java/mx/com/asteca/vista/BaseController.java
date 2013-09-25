package mx.com.asteca.vista;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class BaseController {
	
	private short alta;
	private short cambios;
	private short borrar;
	private short consulta;
	private short impresion; 

	/**
	 * Devuelve el contexto Faces
	 */
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Set an info message in the Faces Context with parameters
	 * 
	 * @param messageID
	 *            message id
	 * @param params
	 *            message parameters
	 */
	protected void addInfoMessage(String messageID, Object[] params) {
		addMessage(FacesMessage.SEVERITY_INFO, messageID, params);
	}
	
	/**
	 * Set an info message in the Faces Context
	 * 
	 * @param messageID
	 *            message id
	 */
	protected void addInfoMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_INFO, messageID);
	}
	
	protected void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	

	/**
	 * Set an info message in the Faces Context with parameters
	 * 
	 * @param messageID
	 *            message id
	 * @param params
	 *            message parameters
	 */
	protected void addWarningMessage(String messageID, Object[] params) {
		addMessage(FacesMessage.SEVERITY_WARN, messageID, params);
	}

	/**
	 * Set an info message in the Faces Context
	 * 
	 * @param messageID
	 *            message id
	 */
	protected void addWarningMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_WARN, messageID);
	}
	
	protected void addWarningMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
	}

	
	
	
	/**
	 * Set an error message with parameters in the Faces Context
	 * 
	 * @param messageID
	 * @param params
	 */
	protected void addErrorMessage(String messageID, Object[] params) {
		addMessage(FacesMessage.SEVERITY_ERROR, messageID, params);
	}

	protected void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}
	
	/**
	 * Set an error message without parameters in the Faces Context
	 * 
	 * @param messageID
	 */
	protected void addErrorMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_ERROR, messageID);
	}

	private void addMessage(Severity severidad, String summary, String detail) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(severidad);
		message.setSummary(summary);
		message.setDetail(detail);
		getFacesContext().addMessage(null, message);
	}
	
	private void addMessage(Severity severidad, String messageID) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(severidad);
		message.setSummary(messageID);
		getFacesContext().addMessage(null, message);
	}
	
	/**
	 * Add message in Faces Context
	 * 
	 * @param severidad
	 * @param messageID
	 * @param params
	 */
	private void addMessage(Severity severidad, String messageID,
			Object[] params) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(severidad);
		message.setSummary(messageID);
		getFacesContext().addMessage(null, message);
	}

	/**
	 * Get message value from bundle resources without parameters
	 * 
	 * @param messageID
	 * @return
	 */
	protected String getMessage(String messageID) {
		return getMessage(messageID, null);
	}

	/**
	 * Get message value from bundle resources with parameters
	 * 
	 * @param messageId
	 * @param params
	 * @return
	 */
	public String getMessage(String messageId, String[] params) {

		String text;
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle(context
				.getApplication().getMessageBundle(), context.getViewRoot()
				.getLocale(), getCurrentClassLoader(params));

		try {
			text = bundle.getString(messageId);

			if (params != null) {
				MessageFormat mf = new MessageFormat(text, context
						.getViewRoot().getLocale());
				text = mf.format(params, new StringBuffer(), null).toString();
			}

		} catch (MissingResourceException e) {
			text = "?? key " + messageId + " not found ??";
		}
		return text;
	}

	/**
	 * Return a class loader. A class loader is an object that is responsible
	 * for loading classes.
	 * 
	 * @param defaultObject
	 * @return
	 */
	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	public boolean isAlta() {
		return alta==0?false:true;
	}

	public boolean isCambios() {
		return cambios==0?false:true;
	}

	public boolean isBorrar() {
		return borrar==0?false:true;
	}

	public boolean isConsulta() {
		return consulta==0?false:true;
	}

	public boolean isImpresion() {
		return impresion==0?false:true;
	}

	public void setAlta(short alta) {
		this.alta = alta;
	}

	public void setCambios(short cambios) {
		this.cambios = cambios;
	}

	public void setBorrar(short borrar) {
		this.borrar = borrar;
	}

	public void setConsulta(short consulta) {
		this.consulta = consulta;
	}

	public void setImpresion(short impresion) {
		this.impresion = impresion;
	}

}
