package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class ModuloDTO implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idModulo;
     private String nombre;
     private String dsc;
     private String ruta;
     private String columna_1;
     private String columna_2;
     private String columna_3;
	/**
	 * @return the idModulo
	 */
	public int getIdModulo() {
		return idModulo;
	}
	/**
	 * @param idModulo the idModulo to set
	 */
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the dsc
	 */
	public String getDsc() {
		return dsc;
	}
	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the columna_1
	 */
	public String getColumna_1() {
		return columna_1;
	}
	/**
	 * @param columna_1 the columna_1 to set
	 */
	public void setColumna_1(String columna_1) {
		this.columna_1 = columna_1;
	}
	/**
	 * @return the columna_2
	 */
	public String getColumna_2() {
		return columna_2;
	}
	/**
	 * @param columna_2 the columna_2 to set
	 */
	public void setColumna_2(String columna_2) {
		this.columna_2 = columna_2;
	}
	/**
	 * @return the columna_3
	 */
	public String getColumna_3() {
		return columna_3;
	}
	/**
	 * @param columna_3 the columna_3 to set
	 */
	public void setColumna_3(String columna_3) {
		this.columna_3 = columna_3;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idModulo;
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
		ModuloDTO other = (ModuloDTO) obj;
		if (idModulo != other.idModulo)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModuloDTO [idModulo=" + idModulo + ", nombre=" + nombre
				+ ", dsc=" + dsc + ", ruta=" + ruta + ", columna_1="
				+ columna_1 + ", columna_2=" + columna_2 + ", columna_3="
				+ columna_3 + "]";
	}
     
	

}
