/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.persistencia.entidades.AutorizacionesProgrEst;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_AUTORIZACION_PROGR_EST)
public class ProgramaEstudioAutorizacionAssembler extends Assembler<ProgramaEstudiosAutorizacionDTO, AutorizacionesProgrEst> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOCS)
	private Assembler assemblerDocs;
	
	@Override
	public ProgramaEstudiosAutorizacionDTO getDTOTransform(
			AutorizacionesProgrEst mapping) {
		if(mapping == null){
			return null;
		}
		ProgramaEstudiosAutorizacionDTO dto = new ProgramaEstudiosAutorizacionDTO();
			dto.setDoc((DocumentoDTO) assemblerDocs.getDTOTransform(mapping.getDocs()));
			dto.setIdAutorizacion(mapping.getIdAutorizacion());
			dto.setIdProgramaEstudios(mapping.getProgramaEstudios().getIdProgEstudio());
		return dto;
	}

	@Override
	public AutorizacionesProgrEst getMappingTransform(
			ProgramaEstudiosAutorizacionDTO dto) {
		if(dto == null){
			return null;
		}
		AutorizacionesProgrEst mapping = new AutorizacionesProgrEst();
			if(dto.getIdAutorizacion() != 0){
				mapping.setIdAutorizacion(dto.getIdAutorizacion());
			}
			if(dto.getIdProgramaEstudios() != 0){
				ProgramaEstudios est = new ProgramaEstudios();
				est.setIdProgEstudio(dto.getIdProgramaEstudios());
				mapping.setProgramaEstudios(est);
			}
			mapping.setDocs((Docs) assemblerDocs.getMappingTransform(dto.getDoc()));
		return mapping;
	}

}
