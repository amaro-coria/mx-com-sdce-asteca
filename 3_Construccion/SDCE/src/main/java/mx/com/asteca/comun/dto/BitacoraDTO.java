package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.asteca.util.FechaUtil;

public class BitacoraDTO implements Serializable{

	 private long idBitacora;
     private Date fecha;
     private String ip;
     private int idUsr;
     private String mensaje;
     private String accion;
     private String fechaString;
     private String usuario;
     
	/**
	 * @return the idBitacora
	 */
	public long getIdBitacora() {
		return idBitacora;
	}
	/**
	 * @param idBitacora the idBitacora to set
	 */
	public void setIdBitacora(long idBitacora) {
		this.idBitacora = idBitacora;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the idUsr
	 */
	public int getIdUsr() {
		return idUsr;
	}
	/**
	 * @param idUsr the idUsr to set
	 */
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the fechaString
	 */
	public String getFechaString() {
		if(fecha != null){
			fechaString = FechaUtil.getInstance().parseDateMM_dd_yy(fecha);
		}
		return fechaString;
	}
	/**
	 * @param fechaString the fechaString to set
	 */
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBitacora ^ (idBitacora >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BitacoraDTO other = (BitacoraDTO) obj;
		if (idBitacora != other.idBitacora)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BitacoraDTO [idBitacora=" + idBitacora + ", fecha=" + fecha
				+ ", ip=" + ip + ", idUsr=" + idUsr + ", mensaje=" + mensaje
				+ ", accion=" + accion + ", fechaString=" + fechaString
				+ ", usuario=" + usuario + "]";
	}
	
	
}
