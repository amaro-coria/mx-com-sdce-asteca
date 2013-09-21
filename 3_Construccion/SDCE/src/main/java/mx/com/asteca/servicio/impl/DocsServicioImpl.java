/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.DocsDAO;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.servicio.DocsServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;
import mx.com.asteca.servicio.assembler.DocsAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JAMARO
 *
 */
@Service
public class DocsServicioImpl extends
		BaseServiceImpl<DocumentoDTO, Integer, Docs> implements DocsServicio {

	@Autowired
	private DocsDAO daoDocs;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOCS)
	private DocsAssembler assemblerDocs;
	
	@Override
	BaseDAO getDAO() {
		return daoDocs;
	}

	@Override
	Assembler getAssembler() {
		return assemblerDocs;
	}
	
	@Override
	@Transactional
	public int saveDocPorAlumno(DocumentoDTO doc, int idAlumno) throws ServicioException{
		try{
			Docs mapping = assemblerDocs.getMappingTransform(doc);
			int pk = daoDocs.saveDocPorAlumno(mapping, idAlumno);
			return pk;
		}catch(PersistenciaException e){
			throw new ServicioException("Exception in saveDocPorAlumno : "+e.getMessage(), e);
		}
	}

}
