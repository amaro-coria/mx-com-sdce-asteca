package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class AsentamientoDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCp;
     private String nombre;
     private short activo;
     private String clave;
     private int idAsentamiento;
     private int idMunicipio;
     private int idEstado;
	public int getIdCp() {
		return idCp;
	}
	public void setIdCp(int idCp) {
		this.idCp = idCp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public short getActivo() {
		return activo;
	}
	public void setActivo(short activo) {
		this.activo = activo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getIdAsentamiento() {
		return idAsentamiento;
	}
	public void setIdAsentamiento(int idAsentamiento) {
		this.idAsentamiento = idAsentamiento;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAsentamiento;
		result = prime * result + idEstado;
		result = prime * result + idMunicipio;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsentamientoDTO other = (AsentamientoDTO) obj;
		if (idAsentamiento != other.idAsentamiento)
			return false;
		if (idEstado != other.idEstado)
			return false;
		if (idMunicipio != other.idMunicipio)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AsentamientoDTO [idCp=" + idCp + ", nombre=" + nombre
				+ ", activo=" + activo + ", clave=" + clave
				+ ", idAsentamiento=" + idAsentamiento + ", idMunicipio="
				+ idMunicipio + ", idEstado=" + idEstado + "]";
	}
     
     
}
