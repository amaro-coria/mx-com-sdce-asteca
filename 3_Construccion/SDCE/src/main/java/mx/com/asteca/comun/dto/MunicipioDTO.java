package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class MunicipioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMunicipio;
	private int idEstado;
	private int idPais;
	private String nombre;
	private Short activo;
	private String clave;
	private String estado;
	private String pais;
	private int idUnico;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Short getActivo() {
		return activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MunicipioDTO other = (MunicipioDTO) obj;
		if (idEstado != other.idEstado)
			return false;
		if (idMunicipio != other.idMunicipio)
			return false;
		return true;
	}

	/**
	 * @return the idPais
	 */
	public int getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(int idPais) {
		this.idPais = idPais;
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

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MunicipioDTO [idMunicipio=" + idMunicipio + ", idEstado="
				+ idEstado + ", idPais=" + idPais + ", nombre=" + nombre
				+ ", activo=" + activo + ", clave=" + clave + ", estado="
				+ estado + ", pais=" + pais + "]";
	}

	/**
	 * @return the idUnico
	 */
	public int getIdUnico() {
		return idUnico;
	}

	/**
	 * @param idUnico the idUnico to set
	 */
	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}

}
