package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class PermisosBooleanDTO implements Serializable{
	
	private boolean alta;
	private boolean edicion;
	private boolean borrar;
	private boolean consulta;
	private boolean impresion;
	/**
	 * @return the alta
	 */
	public boolean isAlta() {
		return alta;
	}
	/**
	 * @param alta the alta to set
	 */
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}
	/**
	 * @param edicion the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}
	/**
	 * @return the borrar
	 */
	public boolean isBorrar() {
		return borrar;
	}
	/**
	 * @param borrar the borrar to set
	 */
	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}
	/**
	 * @return the consulta
	 */
	public boolean isConsulta() {
		return consulta;
	}
	/**
	 * @param consulta the consulta to set
	 */
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}
	/**
	 * @return the impresion
	 */
	public boolean isImpresion() {
		return impresion;
	}
	/**
	 * @param impresion the impresion to set
	 */
	public void setImpresion(boolean impresion) {
		this.impresion = impresion;
	}
	
	

}
