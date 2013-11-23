package mx.com.asteca.vista;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.BitacoraFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ModulosFachada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

	@ManagedProperty("#{modulosFachadaImpl}")
	private transient ModulosFachada modulosFachada;
	@ManagedProperty("#{bitacoraFachadaImpl}")
	private transient BitacoraFachada fachadaBitacora;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	private boolean alta;
	private boolean cambios;
	private boolean borrar;
	private boolean consulta;
	private boolean impresion;

	abstract String getModulo();

	public void addBitacora(String accion, int idUsr, String ip, String mensaje){
		BitacoraDTO dto = new BitacoraDTO();
		dto.setAccion(accion);
		dto.setFecha(new Date());
		dto.setIdUsr(idUsr);
		dto.setIp(ip);
		dto.setMensaje(mensaje);
		try {
			fachadaBitacora.save(dto);
		} catch (FachadaException e) {
			LOGGER.error("No se pudo agregar registro a bitacora::"+dto+":::"+e.getMessage(), e);
		}
	}
	
	public void addBitacora(String accion, String mensaje){
		HttpServletRequest servletReq = (HttpServletRequest) getFacesContext().getCurrentInstance().getExternalContext().getRequest();		
		Integer idUsr = (Integer) servletReq.getAttribute(Constantes.SESION_ATRIBUTO_USUARIO);
		if(idUsr == null){
			idUsr = (Integer) servletReq.getSession().getAttribute(Constantes.SESION_ATRIBUTO_USUARIO);
		}
		String ip = servletReq.getRemoteAddr();
		BitacoraDTO dto = new BitacoraDTO();
		dto.setAccion(accion);
		dto.setFecha(new Date());
		dto.setIdUsr(idUsr);
		dto.setIp(ip);
		dto.setMensaje(mensaje);
		try {
			fachadaBitacora.save(dto);
		} catch (FachadaException e) {
			LOGGER.error("No se pudo agregar registro a bitacora::"+dto+":::"+e.getMessage(), e);
		}
	}
	
	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public boolean isCambios() {
		return cambios;
	}

	public void setCambios(boolean cambios) {
		this.cambios = cambios;
	}

	public boolean isBorrar() {
		return borrar;
	}

	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}

	public boolean isConsulta() {
		return consulta;
	}

	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}

	public boolean isImpresion() {
		return impresion;
	}

	public void setImpresion(boolean impresion) {
		this.impresion = impresion;
	}

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

	private void setSessionPermissions() {
		try {
			ModulosDTO idModulo = modulosFachada.buscarPorNombre(getModulo());
			HttpServletRequest request = (HttpServletRequest) this
					.getFacesContext().getExternalContext().getRequest();
			HashMap<Integer, ModulosDTO> permisos = (HashMap<Integer, ModulosDTO>) request
					.getSession()
					.getAttribute("permisos");

			ModulosDTO permiso = permisos.get(idModulo.getIdModulo());

			setAlta(permiso.isAlta());
			setBorrar(permiso.isBorrar());
			setCambios(permiso.isEditar());
			setConsulta(permiso.isConsulta());
			setImpresion(permiso.isImprimir());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public PermisosBooleanDTO stablishSessionPermissions() {
		try {
			ModulosDTO idModulo = modulosFachada.buscarPorNombre(getModulo());
			HttpServletRequest request = (HttpServletRequest) this
					.getFacesContext().getExternalContext().getRequest();
			HashMap<Integer, ModulosDTO> permisos = (HashMap<Integer, ModulosDTO>) request
					.getSession()
					.getAttribute("permisos");

			ModulosDTO permiso = permisos.get(idModulo.getIdModulo());

			PermisosBooleanDTO permisosBoolean = new PermisosBooleanDTO(); 
			
			permisosBoolean.setAlta(permiso.isAlta());
			permisosBoolean.setBorrar(permiso.isBorrar());
			permisosBoolean.setEdicion(permiso.isEditar());
			permisosBoolean.setConsulta(permiso.isConsulta());
			permisosBoolean.setImpresion(permiso.isImprimir());		
			return permisosBoolean;
		} catch (Exception e) {
			return null;
		}

	}

	public ModulosFachada getModulosFachada() {
		return modulosFachada;
	}

	public void setModulosFachada(ModulosFachada modulosFachada) {
		this.modulosFachada = modulosFachada;
	}

	/**
	 * @return the fachadaBitacora
	 */
	public BitacoraFachada getFachadaBitacora() {
		return fachadaBitacora;
	}

	/**
	 * @param fachadaBitacora the fachadaBitacora to set
	 */
	public void setFachadaBitacora(BitacoraFachada fachadaBitacora) {
		this.fachadaBitacora = fachadaBitacora;
	}

}
