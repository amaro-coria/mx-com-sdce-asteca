package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notificaciones generated by hbm2java
 */
@Entity
@Table(name="NOTIFICACIONES"
    ,catalog="astecadb"
)
public class Notificaciones  implements java.io.Serializable {


     private long idNotificacion;
     private NotificacionesEstados notificacionesEstados;
     private NotificacionesTipos notificacionesTipos;
     private Date fechaAlta;
     private Date fechaLeido;
     private String mensaje;

    public Notificaciones() {
    }

	
    public Notificaciones(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    public Notificaciones(long idNotificacion, NotificacionesEstados notificacionesEstados, NotificacionesTipos notificacionesTipos, Date fechaAlta, Date fechaLeido, String mensaje) {
       this.idNotificacion = idNotificacion;
       this.notificacionesEstados = notificacionesEstados;
       this.notificacionesTipos = notificacionesTipos;
       this.fechaAlta = fechaAlta;
       this.fechaLeido = fechaLeido;
       this.mensaje = mensaje;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_NOTIFICACION", unique=true, nullable=false)
    public long getIdNotificacion() {
        return this.idNotificacion;
    }
    
    public void setIdNotificacion(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ESTADO")
    public NotificacionesEstados getNotificacionesEstados() {
        return this.notificacionesEstados;
    }
    
    public void setNotificacionesEstados(NotificacionesEstados notificacionesEstados) {
        this.notificacionesEstados = notificacionesEstados;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO")
    public NotificacionesTipos getNotificacionesTipos() {
        return this.notificacionesTipos;
    }
    
    public void setNotificacionesTipos(NotificacionesTipos notificacionesTipos) {
        this.notificacionesTipos = notificacionesTipos;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_ALTA", length=19)
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_LEIDO", length=19)
    public Date getFechaLeido() {
        return this.fechaLeido;
    }
    
    public void setFechaLeido(Date fechaLeido) {
        this.fechaLeido = fechaLeido;
    }
    
    @Column(name="MENSAJE", length=50)
    public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }




}


