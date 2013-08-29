package mx.com.asteca.comun.dto;

import java.util.Date;

public class NotificacionDTO  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idNotificacion;
    private long idNotificacionesEstados;
    private long idNotificacionesTipos;
    private String notificacionesTiposDesc;
    private Date fechaAlta;
    private Date fechaLeido;
    private String mensaje;

    public NotificacionDTO() {
    }

	
    public NotificacionDTO(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    public NotificacionDTO(long idNotificacion, int idNotificacionesEstados, int idNotificacionesTipos, String notificacionesTiposDesc, 
    		Date fechaAlta, Date fechaLeido, String mensaje) {
       this.idNotificacion = idNotificacion;
       this.idNotificacionesEstados = idNotificacionesEstados;
       this.idNotificacionesTipos = idNotificacionesTipos;
       this.notificacionesTiposDesc = notificacionesTiposDesc;
       this.fechaAlta = fechaAlta;
       this.fechaLeido = fechaLeido;
       this.mensaje = mensaje;
    }
   
    public long getIdNotificacion() {
        return this.idNotificacion;
    }
    
    public void setIdNotificacion(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    public long getIdNotificacionesEstados() {
        return this.idNotificacionesEstados;
    }
    
    public void setIdNotificacionesEstados(long idNotificacionesEstados) {
        this.idNotificacionesEstados = idNotificacionesEstados;
    }
    public long getIdNotificacionesTipos() {
        return this.idNotificacionesTipos;
    }
    
    public void setIdNotificacionesTipos(long idNotificacionesTipos) {
        this.idNotificacionesTipos = idNotificacionesTipos;
    }
    public String getNotificacionesTiposDesc() {
        return this.notificacionesTiposDesc;
    }
    
    public void setNotificacionesTiposDesc(String notificacionesTiposDesc) {
        this.notificacionesTiposDesc = notificacionesTiposDesc;
    }
    
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    public Date getFechaLeido() {
        return this.fechaLeido;
    }
    
    public void setFechaLeido(Date fechaLeido) {
        this.fechaLeido = fechaLeido;
    }
    
    public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}