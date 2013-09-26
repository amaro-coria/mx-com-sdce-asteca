/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;

/**
 * @author JAMARO
 *
 */
public class ProgramaEstudiosAutorizacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAutorizacion;
	private int idProgramaEstudios;
	private DocumentoDTO doc;
	/**
	 * @return the idAutorizacion
	 */
	public int getIdAutorizacion() {
		return idAutorizacion;
	}
	/**
	 * @param idAutorizacion the idAutorizacion to set
	 */
	public void setIdAutorizacion(int idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}
	/**
	 * @return the idProgramaEstudios
	 */
	public int getIdProgramaEstudios() {
		return idProgramaEstudios;
	}
	/**
	 * @param idProgramaEstudios the idProgramaEstudios to set
	 */
	public void setIdProgramaEstudios(int idProgramaEstudios) {
		this.idProgramaEstudios = idProgramaEstudios;
	}
	/**
	 * @return the doc
	 */
	public DocumentoDTO getDoc() {
		return doc;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDoc(DocumentoDTO doc) {
		this.doc = doc;
	}
	
}
