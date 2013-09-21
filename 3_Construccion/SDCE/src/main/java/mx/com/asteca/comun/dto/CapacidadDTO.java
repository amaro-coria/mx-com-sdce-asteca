/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;


/**
 * @author JAMARO
 *
 */
public class CapacidadDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCapacidad;
    private int idTipoLicencia;
    private String tipoLicencia;
    private String tipoCapacidad;
    private String autorizada;
    private Integer horas;
    private String fechaInic;
    private String fechaFin;
    private String acreditada;
	/**
	 * @return the idCapacidad
	 */
	public int getIdCapacidad() {
		return idCapacidad;
	}
	/**
	 * @param idCapacidad the idCapacidad to set
	 */
	public void setIdCapacidad(int idCapacidad) {
		this.idCapacidad = idCapacidad;
	}
	/**
	 * @return the idTipoLicencia
	 */
	public int getIdTipoLicencia() {
		return idTipoLicencia;
	}
	/**
	 * @param idTipoLicencia the idTipoLicencia to set
	 */
	public void setIdTipoLicencia(int idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	/**
	 * @return the tipoLicencia
	 */
	public String getTipoLicencia() {
		return tipoLicencia;
	}
	/**
	 * @param tipoLicencia the tipoLicencia to set
	 */
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	/**
	 * @return the tipoCapacidad
	 */
	public String getTipoCapacidad() {
		return tipoCapacidad;
	}
	/**
	 * @param tipoCapacidad the tipoCapacidad to set
	 */
	public void setTipoCapacidad(String tipoCapacidad) {
		this.tipoCapacidad = tipoCapacidad;
	}
	/**
	 * @return the autorizada
	 */
	public String getAutorizada() {
		return autorizada;
	}
	/**
	 * @param autorizada the autorizada to set
	 */
	public void setAutorizada(String autorizada) {
		this.autorizada = autorizada;
	}
	/**
	 * @return the horas
	 */
	public Integer getHoras() {
		return horas;
	}
	/**
	 * @param horas the horas to set
	 */
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	/**
	 * @return the fechaInic
	 */
	public String getFechaInic() {
		return fechaInic;
	}
	/**
	 * @param fechaInic the fechaInic to set
	 */
	public void setFechaInic(String fechaInic) {
		this.fechaInic = fechaInic;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the acreditada
	 */
	public String getAcreditada() {
		return acreditada;
	}
	/**
	 * @param acreditada the acreditada to set
	 */
	public void setAcreditada(String acreditada) {
		this.acreditada = acreditada;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCapacidad;
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
		CapacidadDTO other = (CapacidadDTO) obj;
		if (idCapacidad != other.idCapacidad)
			return false;
		return true;
	}
    
}
