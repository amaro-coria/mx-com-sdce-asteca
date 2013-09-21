package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.asteca.util.FechaUtil;

public class FamiliaDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idFam;
     private String conyugueNombre;
     private Date conyugueFechaNac;
     private String conyugueFechaNacStr;
     private String hijo1Nombre;
     private Date hijo1FechaNac;
     private String hijo1FechaNacStr;
     private String hijo2Nombre;
     private Date hijo2FechaNac;
     private String hijo2FechaNacStr;
     private String padreNombre;
     private Date padreFechaNac;
     private String padreFechaNacStr;
     private String madreNombre;
     private Date madreFechaNac;
     private String madreFechaNacStr;
	/**
	 * @return the idFam
	 */
	public int getIdFam() {
		return idFam;
	}
	/**
	 * @param idFam the idFam to set
	 */
	public void setIdFam(int idFam) {
		this.idFam = idFam;
	}
	/**
	 * @return the conyugueNombre
	 */
	public String getConyugueNombre() {
		return conyugueNombre;
	}
	/**
	 * @param conyugueNombre the conyugueNombre to set
	 */
	public void setConyugueNombre(String conyugueNombre) {
		this.conyugueNombre = conyugueNombre;
	}
	/**
	 * @return the conyugueFechaNac
	 */
	public Date getConyugueFechaNac() {
		return conyugueFechaNac;
	}
	/**
	 * @param conyugueFechaNac the conyugueFechaNac to set
	 */
	public void setConyugueFechaNac(Date conyugueFechaNac) {
		this.conyugueFechaNac = conyugueFechaNac;
	}
	/**
	 * @return the hijo1Nombre
	 */
	public String getHijo1Nombre() {
		return hijo1Nombre;
	}
	/**
	 * @param hijo1Nombre the hijo1Nombre to set
	 */
	public void setHijo1Nombre(String hijo1Nombre) {
		this.hijo1Nombre = hijo1Nombre;
	}
	/**
	 * @return the hijo1FechaNac
	 */
	public Date getHijo1FechaNac() {
		return hijo1FechaNac;
	}
	/**
	 * @param hijo1FechaNac the hijo1FechaNac to set
	 */
	public void setHijo1FechaNac(Date hijo1FechaNac) {
		this.hijo1FechaNac = hijo1FechaNac;
	}
	/**
	 * @return the hijo2Nombre
	 */
	public String getHijo2Nombre() {
		return hijo2Nombre;
	}
	/**
	 * @param hijo2Nombre the hijo2Nombre to set
	 */
	public void setHijo2Nombre(String hijo2Nombre) {
		this.hijo2Nombre = hijo2Nombre;
	}
	/**
	 * @return the hijo2FechaNac
	 */
	public Date getHijo2FechaNac() {
		return hijo2FechaNac;
	}
	/**
	 * @param hijo2FechaNac the hijo2FechaNac to set
	 */
	public void setHijo2FechaNac(Date hijo2FechaNac) {
		this.hijo2FechaNac = hijo2FechaNac;
	}
	/**
	 * @return the padreNombre
	 */
	public String getPadreNombre() {
		return padreNombre;
	}
	/**
	 * @param padreNombre the padreNombre to set
	 */
	public void setPadreNombre(String padreNombre) {
		this.padreNombre = padreNombre;
	}
	/**
	 * @return the padreFechaNac
	 */
	public Date getPadreFechaNac() {
		return padreFechaNac;
	}
	/**
	 * @param padreFechaNac the padreFechaNac to set
	 */
	public void setPadreFechaNac(Date padreFechaNac) {
		this.padreFechaNac = padreFechaNac;
	}
	/**
	 * @return the madreNombre
	 */
	public String getMadreNombre() {
		return madreNombre;
	}
	/**
	 * @param madreNombre the madreNombre to set
	 */
	public void setMadreNombre(String madreNombre) {
		this.madreNombre = madreNombre;
	}
	/**
	 * @return the madreFechaNac
	 */
	public Date getMadreFechaNac() {
		return madreFechaNac;
	}
	/**
	 * @param madreFechaNac the madreFechaNac to set
	 */
	public void setMadreFechaNac(Date madreFechaNac) {
		this.madreFechaNac = madreFechaNac;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFam;
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
		FamiliaDTO other = (FamiliaDTO) obj;
		if (idFam != other.idFam)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FamiliaDTO [idFam=" + idFam + ", conyugueNombre="
				+ conyugueNombre + ", conyugueFechaNac=" + conyugueFechaNac
				+ ", hijo1Nombre=" + hijo1Nombre + ", hijo1FechaNac="
				+ hijo1FechaNac + ", hijo2Nombre=" + hijo2Nombre
				+ ", hijo2FechaNac=" + hijo2FechaNac + ", padreNombre="
				+ padreNombre + ", padreFechaNac=" + padreFechaNac
				+ ", madreNombre=" + madreNombre + ", madreFechaNac="
				+ madreFechaNac + "]";
	}
	/**
	 * @return the conyugueFechaNacStr
	 */
	public String getConyugueFechaNacStr() {
		String conyugueFechaNacStr = FechaUtil.getInstance().parseDateMM_dd_yy(conyugueFechaNac);
		return conyugueFechaNacStr;
	}
	/**
	 * @param conyugueFechaNacStr the conyugueFechaNacStr to set
	 */
	public void setConyugueFechaNacStr(String conyugueFechaNacStr) {
		this.conyugueFechaNacStr = conyugueFechaNacStr;
	}
	/**
	 * @return the hijo1FechaNacStr
	 */
	public String getHijo1FechaNacStr() {
		String hijo1FechaNacStr = FechaUtil.getInstance().parseDateMM_dd_yy(hijo1FechaNac);
		return hijo1FechaNacStr;
	}
	/**
	 * @param hijo1FechaNacStr the hijo1FechaNacStr to set
	 */
	public void setHijo1FechaNacStr(String hijo1FechaNacStr) {
		this.hijo1FechaNacStr = hijo1FechaNacStr;
	}
	/**
	 * @return the hijo2FechaNacStr
	 */
	public String getHijo2FechaNacStr() {
		String hijo2FechaNacStr = FechaUtil.getInstance().parseDateMM_dd_yy(hijo2FechaNac);
		return hijo2FechaNacStr;
	}
	/**
	 * @param hijo2FechaNacStr the hijo2FechaNacStr to set
	 */
	public void setHijo2FechaNacStr(String hijo2FechaNacStr) {
		this.hijo2FechaNacStr = hijo2FechaNacStr;
	}
	/**
	 * @return the padreFechaNacStr
	 */
	public String getPadreFechaNacStr() {
		String padreFechaNacStr = FechaUtil.getInstance().parseDateMM_dd_yy(padreFechaNac);
		return padreFechaNacStr;
	}
	/**
	 * @param padreFechaNacStr the padreFechaNacStr to set
	 */
	public void setPadreFechaNacStr(String padreFechaNacStr) {
		this.padreFechaNacStr = padreFechaNacStr;
	}
	/**
	 * @return the madreFechaNacStr
	 */
	public String getMadreFechaNacStr() {
		String madreFechaNacStr = FechaUtil.getInstance().parseDateMM_dd_yy(madreFechaNac);
		return madreFechaNacStr;
	}
	/**
	 * @param madreFechaNacStr the madreFechaNacStr to set
	 */
	public void setMadreFechaNacStr(String madreFechaNacStr) {
		this.madreFechaNacStr = madreFechaNacStr;
	}
	
	
	
}
