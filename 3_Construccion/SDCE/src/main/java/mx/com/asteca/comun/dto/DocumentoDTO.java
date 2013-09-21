package mx.com.asteca.comun.dto;

import java.io.Serializable;


public class DocumentoDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstatus;
	 private String estatus;
     private String nombre;
     private String ruta;
     private int idAlumno;
     private int idDoc;
	/**
	 * @return the idEstatus
	 */
	public int getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
	 * @return the idAlumno
	 */
	public int getIdAlumno() {
		return idAlumno;
	}
	/**
	 * @param idAlumno the idAlumno to set
	 */
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	/**
	 * @return the idDoc
	 */
	public int getIdDoc() {
		return idDoc;
	}
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}
     
}
