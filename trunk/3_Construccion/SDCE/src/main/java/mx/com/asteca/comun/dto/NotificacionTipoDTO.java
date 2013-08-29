package mx.com.asteca.comun.dto;

public class NotificacionTipoDTO  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private short idNotifTipo;
    private String nombre;

    public NotificacionTipoDTO() {
    }
	
    public NotificacionTipoDTO(short idNotifTipo) {
        this.idNotifTipo = idNotifTipo;
    }
    public NotificacionTipoDTO(short idNotifTipo, String nombre) {
       this.idNotifTipo = idNotifTipo;
       this.nombre = nombre;
    }
   
    public short getIdNotifTipo() {
        return this.idNotifTipo;
    }
    
    public void setIdNotifTipo(short idNotifTipo) {
        this.idNotifTipo = idNotifTipo;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}