package mx.com.asteca.comun.dto;

public class NotificacionEstadoDTO  implements java.io.Serializable {
     
	private static final long serialVersionUID = 1L;
	private short idNotifEdo;
    private String nombre;

    public NotificacionEstadoDTO() {
    }

	
    public NotificacionEstadoDTO(short idNotifEdo) {
        this.idNotifEdo = idNotifEdo;
    }
    public NotificacionEstadoDTO(short idNotifEdo, String nombre) {
       this.idNotifEdo = idNotifEdo;
       this.nombre = nombre;
    }
   
    public short getIdNotifEdo() {
        return this.idNotifEdo;
    }
    
    public void setIdNotifEdo(short idNotifEdo) {
        this.idNotifEdo = idNotifEdo;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}