package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class PermisosDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPermiso;
	private Short alta;
	private Short cambios;
	private Short borrar;
	private Short consulta;
	private Short impresion;

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Short getAlta() {
		return alta;
	}

	public void setAlta(Short alta) {
		this.alta = alta;
	}

	public Short getCambios() {
		return cambios;
	}

	public void setCambios(Short cambios) {
		this.cambios = cambios;
	}

	public Short getBorrar() {
		return borrar;
	}

	public void setBorrar(Short borrar) {
		this.borrar = borrar;
	}

	public Short getConsulta() {
		return consulta;
	}

	public void setConsulta(Short consulta) {
		this.consulta = consulta;
	}

	public Short getImpresion() {
		return impresion;
	}

	public void setImpresion(Short impresion) {
		this.impresion = impresion;
	}
}
