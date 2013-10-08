package mx.com.asteca.comun.dto;

import java.io.Serializable;
/**
 * 
 * @author Javier
 *
 */
public class CatGralDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cveRegistro;
	private String dsc;
	private String estatus;
	private Short activo;
	private int idCatGral;
	private int idTipoCatGral;
	private String tipo;
	
	
	public String getCveRegistro() {
		return cveRegistro;
	}
	public void setCveRegistro(String cveRegistro) {
		this.cveRegistro = cveRegistro;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Short getActivo() {
		return activo;
	}
	public void setActivo(Short activo) {
		this.activo = activo;
	}
	public int getIdCatGral() {
		return idCatGral;
	}
	public void setIdCatGral(int idCatGral) {
		this.idCatGral = idCatGral;
	}
	public int getIdTipoCatGral() {
		return idTipoCatGral;
	}
	public void setIdTipoCatGral(int idTipoCatGral) {
		this.idTipoCatGral = idTipoCatGral;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCatGral;
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
		CatGralDTO other = (CatGralDTO) obj;
		if (idCatGral != other.idCatGral)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CatGralDTO [cveRegistro=" + cveRegistro + ", dsc=" + dsc
				+ ", estatus=" + estatus + ", activo=" + activo
				+ ", idCatGral=" + idCatGral + ", idTipoCatGral="
				+ idTipoCatGral + "]";
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
