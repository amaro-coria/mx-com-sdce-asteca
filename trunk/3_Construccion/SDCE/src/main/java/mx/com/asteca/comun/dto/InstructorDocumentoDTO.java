package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class InstructorDocumentoDTO implements Serializable{

	private int id;
	private int idInstructor;
	private DocumentoDTO documento;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the idInstructor
	 */
	public int getIdInstructor() {
		return idInstructor;
	}
	/**
	 * @param idInstructor the idInstructor to set
	 */
	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}
	/**
	 * @return the documento
	 */
	public DocumentoDTO getDocumento() {
		return documento;
	}
	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}
	
	
}
