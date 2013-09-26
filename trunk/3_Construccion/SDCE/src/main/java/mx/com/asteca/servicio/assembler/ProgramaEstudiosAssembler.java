/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;
import mx.com.asteca.persistencia.entidades.AutorizacionesProgrEst;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;
import mx.com.asteca.persistencia.entidades.ProgramaEstMaterias;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_PROGRAMA_ESTUDIOS)
public class ProgramaEstudiosAssembler extends
		Assembler<ProgramaEstudiosDTO, ProgramaEstudios> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOCS)
	private Assembler assemblerDocs;
	
	@Override
	public ProgramaEstudiosDTO getDTOTransform(ProgramaEstudios mapping) {
		if(mapping == null){
			return null;
		}
		ProgramaEstudiosDTO dto = new ProgramaEstudiosDTO();
			dto.setClave(mapping.getClave());
			dto.setDsc(mapping.getDsc());
			dto.setFechaAut(mapping.getFechaAut());
			dto.setHorasPractica(mapping.getHorasPractica());
			dto.setHorasTeoria(mapping.getHorasTeoria());
			dto.setIdProgEstudio(mapping.getIdProgEstudio());
			dto.setIdTipo(mapping.getCatGral().getIdCatGral());
			dto.setNoAut(mapping.getNoAut());
			dto.setTipo(mapping.getCatGral().getDsc());
			Set<AutorizacionesProgrEst> set = mapping.getAutorizacionesProgrEsts();
			List<ProgramaEstudiosAutorizacionDTO> listDocs = new ArrayList<ProgramaEstudiosAutorizacionDTO>();
			if(!CollectionUtils.isEmpty(set)){
				for(AutorizacionesProgrEst aut : set){
					DocumentoDTO doc = (DocumentoDTO) assemblerDocs.getDTOTransform(aut.getDocs());
					ProgramaEstudiosAutorizacionDTO autDTO = new ProgramaEstudiosAutorizacionDTO();
					autDTO.setDoc(doc);
					autDTO.setIdAutorizacion(aut.getIdAutorizacion());
					autDTO.setIdProgramaEstudios(mapping.getIdProgEstudio());
					listDocs.add(autDTO);
				}
			}
			Set<ProgramaEstMaterias> set2 = mapping.getProgramaEstMateriases();
			List<ProgramaEstudiosMateriasDTO> listEstudios = new ArrayList<ProgramaEstudiosMateriasDTO>();
			if(!CollectionUtils.isEmpty(set2)){
				for(ProgramaEstMaterias est : set2){
					ProgramaEstudiosMateriasDTO  estMat= new ProgramaEstudiosMateriasDTO();
					estMat.setHorasPractica(est.getHorasPractica());
					estMat.setHorasTeoria(est.getHorasTeoria());
					estMat.setIdProgrEstMateria(est.getIdProgrEstMat());
					estMat.setIdProgrEstudios(mapping.getIdProgEstudio());
					MateriasRegistros materia = est.getMaterias();
					MateriaRegistroDTO materiaDTO = new MateriaRegistroDTO();
					materiaDTO.setIdMateria(materia.getIdMateria());
					materiaDTO.setNombre(materia.getNombre());
					estMat.setMateria(materiaDTO);
					listEstudios.add(estMat);
				}
			}
			dto.setListMaterias(listEstudios);
			dto.setListAutorizaciones(listDocs);
		return dto;
			
	}

	@Override
	public ProgramaEstudios getMappingTransform(ProgramaEstudiosDTO dto) {
		if(dto == null){
			return null;
		}
		ProgramaEstudios mapping = new ProgramaEstudios();
			if(dto.getIdTipo() != 0){
				CatGral catGral = new CatGral();
				catGral.setIdCatGral(dto.getIdTipo());
				mapping.setCatGral(catGral);
			}
			mapping.setClave(dto.getClave());
			mapping.setDsc(dto.getDsc());
			mapping.setFechaAut(dto.getFechaAut());
			mapping.setHorasPractica(dto.getHorasPractica());
			mapping.setHorasTeoria(dto.getHorasTeoria());
			if(dto.getIdProgEstudio() != 0){
				mapping.setIdProgEstudio(dto.getIdProgEstudio());
			}
			mapping.setNoAut(dto.getNoAut());
			
			/*
			 * List<ProgramaEstudiosMateriasDTO> listEstudios = dto.getListMaterias();
			Set<ProgramaEstMaterias> setEstudios = new HashSet<ProgramaEstMaterias>();
			if(!CollectionUtils.isEmpty(listEstudios)){
				for(ProgramaEstudiosMateriasDTO est : listEstudios){
					ProgramaEstMaterias estMat = new ProgramaEstMaterias();
					estMat.setHorasPractica(est.getHorasPractica());
					estMat.setHorasTeoria(est.getHorasTeoria());
					if(est.getIdProgrEstMateria() != 0){
						estMat.setIdProgrEstMat(est.getIdProgrEstMateria());
					}
					if(dto.getIdProgEstudio() != 0){
						ProgramaEstudios prog = new ProgramaEstudios();
						prog.setIdProgEstudio(dto.getIdProgEstudio());
						estMat.setProgramaEstudios(prog);
					}
					MateriaRegistroDTO materiaDTO = est.getMateria();
					if(materiaDTO.getIdMateria() != 0){
						MateriasRegistros materias = new MateriasRegistros();
						materias.setIdMateria(materiaDTO.getIdMateria());
						estMat.setMaterias(materias);
					}
					setEstudios.add(estMat);
				}
			}
			mapping.setProgramaEstMateriases(setEstudios);
			*/
			/*
			List<ProgramaEstudiosAutorizacionDTO> listaAutorizaciones = dto.getListAutorizaciones();
			Set<AutorizacionesProgrEst> setAutorizaciones = new HashSet<AutorizacionesProgrEst>();
			if(!CollectionUtils.isEmpty(listaAutorizaciones)){
				for(ProgramaEstudiosAutorizacionDTO autDTO : listaAutorizaciones){
					AutorizacionesProgrEst aut = new AutorizacionesProgrEst();
					if(autDTO.getIdAutorizacion() != 0){
						aut.setIdAutorizacion(autDTO.getIdAutorizacion());
					}
					if(dto.getIdProgEstudio() != 0){
						ProgramaEstudios prog = new ProgramaEstudios();
						prog.setIdProgEstudio(dto.getIdProgEstudio());
						aut.setProgramaEstudios(prog);
					}
					Docs docs = (Docs) assemblerDocs.getMappingTransform(autDTO.getDoc());
					aut.setDocs(docs);
					setAutorizaciones.add(aut);
				}
			}
			mapping.setAutorizacionesProgrEsts(setAutorizaciones);
			*/
			return mapping;
	}

}
