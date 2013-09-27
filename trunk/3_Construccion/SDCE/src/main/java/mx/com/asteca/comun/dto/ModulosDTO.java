package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class ModulosDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idModulo;
    private String nombre;
    private String dsc;
    private boolean alta;
    private boolean editar;
    private boolean borrar;
    private boolean consulta;
    private boolean imprimir;
    
    
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	public boolean isBorrar() {
		return borrar;
	}
	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}
	public boolean isConsulta() {
		return consulta;
	}
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}
	public boolean isImprimir() {
		return imprimir;
	}
	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}
    
}
