package mx.com.asteca.comun.dto;

import java.io.Serializable;


public class EstudioDTO implements Serializable, Comparable<EstudioDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstudios;
    private int idTipoEstudio;
    private String tipoEstudio;
    private String institucion;
    private Short fechaIni;
    private Short fechaFin;
    private String cert;
	/**
	 * @return the idEstudios
	 */
	public int getIdEstudios() {
		return idEstudios;
	}
	/**
	 * @param idEstudios the idEstudios to set
	 */
	public void setIdEstudios(int idEstudios) {
		this.idEstudios = idEstudios;
	}
	/**
	 * @return the idTipoEstudio
	 */
	public int getIdTipoEstudio() {
		return idTipoEstudio;
	}
	/**
	 * @param idTipoEstudio the idTipoEstudio to set
	 */
	public void setIdTipoEstudio(int idTipoEstudio) {
		this.idTipoEstudio = idTipoEstudio;
	}
	/**
	 * @return the tipoEstudio
	 */
	public String getTipoEstudio() {
		return tipoEstudio;
	}
	/**
	 * @param tipoEstudio the tipoEstudio to set
	 */
	public void setTipoEstudio(String tipoEstudio) {
		this.tipoEstudio = tipoEstudio;
	}
	/**
	 * @return the institucion
	 */
	public String getInstitucion() {
		return institucion;
	}
	/**
	 * @param institucion the institucion to set
	 */
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	/**
	 * @return the fechaIni
	 */
	public Short getFechaIni() {
		return fechaIni;
	}
	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(Short fechaIni) {
		this.fechaIni = fechaIni;
	}
	/**
	 * @return the fechaFin
	 */
	public Short getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Short fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the cert
	 */
	public String getCert() {
		return cert;
	}
	/**
	 * @param cert the cert to set
	 */
	public void setCert(String cert) {
		this.cert = cert;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstudios;
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
		EstudioDTO other = (EstudioDTO) obj;
		if (idEstudios != other.idEstudios)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstudioDTO [idEstudios=" + idEstudios + ", idTipoEstudio="
				+ idTipoEstudio + ", tipoEstudio=" + tipoEstudio
				+ ", institucion=" + institucion + ", fechaIni=" + fechaIni
				+ ", fechaFin=" + fechaFin + ", cert=" + cert + "]";
	}
	@Override
	public int compareTo(EstudioDTO o) {
		if(this.idTipoEstudio == o.getIdTipoEstudio()){
			return 0;
		}else if(this.idTipoEstudio < o.getIdTipoEstudio()){
			return -1;
		}else{
			return 1;
		}
	}
    
}
