package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class DomicilioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String calle;
	private String noExterior;
	private String noInterior;
	private Short cp;
	private String telefono;
	private int idDomicilio;
	private int idAsentamiento;
	private String asentamiento;
	private int idMunicipio;
	private String municipio;
	private int idEstado;
	private String estado;

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public Short getCp() {
		return cp;
	}

	public void setCp(Short cp) {
		this.cp = cp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
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
		result = prime * result + idDomicilio;
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
		DomicilioDTO other = (DomicilioDTO) obj;
		if (idAsentamiento != other.idAsentamiento)
			return false;
		if (idDomicilio != other.idDomicilio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DomicilioDTO [calle=" + calle + ", noExterior=" + noExterior
				+ ", noInterior=" + noInterior + ", cp=" + cp + ", telefono="
				+ telefono + ", idDomicilio=" + idDomicilio
				+ ", idAsentamiento=" + idAsentamiento + ", idMunicipio="
				+ idMunicipio + ", idEstado=" + idEstado + "]";
	}

	/**
	 * @return the asentamiento
	 */
	public String getAsentamiento() {
		return asentamiento;
	}

	/**
	 * @param asentamiento the asentamiento to set
	 */
	public void setAsentamiento(String asentamiento) {
		this.asentamiento = asentamiento;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
