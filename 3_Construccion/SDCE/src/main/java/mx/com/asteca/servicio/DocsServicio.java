/**
 * 
 */
package mx.com.asteca.servicio;

import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.persistencia.entidades.Docs;

/**
 * @author JAMARO
 *
 */
public interface DocsServicio extends BaseService<DocumentoDTO, Integer, Docs> {

	int saveDocPorAlumno(DocumentoDTO doc, int idAlumno)
			throws ServicioException;

}
