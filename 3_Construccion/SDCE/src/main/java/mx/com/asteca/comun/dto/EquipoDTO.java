package mx.com.asteca.comun.dto;

/**
 * Clase que representa un objeto de transferencia de datos para los Equipos.
 * 
 * @author Rabelt Ibarra Godinez.
 * 
 */
public class EquipoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEquipo;
	private String clave;
	private String dsc;
	private Short activo;
	private String dscTipo;
	private int idTipo;

	public EquipoDTO() {
	}

	public EquipoDTO(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public EquipoDTO(int idEquipo, String clave, String dsc, Short activo) {
		this.idEquipo = idEquipo;
		this.clave = clave;
		this.dsc = dsc;
		this.activo = activo;
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDsc() {
		return this.dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public Short getActivo() {
		return this.activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}

	/**
	 * @return the dscTipo
	 */
	public String getDscTipo() {
		return dscTipo;
	}

	/**
	 * @param dscTipo
	 *            the dscTipo to set
	 */
	public void setDscTipo(String dscTipo) {
		this.dscTipo = dscTipo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEquipo;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		EquipoDTO other = (EquipoDTO) obj;
		if (idEquipo != other.idEquipo)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EquipoDTO [idEquipo=" + idEquipo + ", clave=" + clave
				+ ", dsc=" + dsc + ", activo=" + activo + ", dscTipo="
				+ dscTipo + "]";
	}

	/**
	 * @return the idTipo
	 */
	public int getIdTipo() {
		return idTipo;
	}

	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

}
