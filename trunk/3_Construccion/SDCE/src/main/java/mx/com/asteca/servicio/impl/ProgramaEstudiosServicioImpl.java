/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ProgramaEstudiosDAO;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;
import mx.com.asteca.servicio.ProgramaEstudiosServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JAMARO
 *
 */
@Service
public class ProgramaEstudiosServicioImpl extends
		BaseServiceImpl<ProgramaEstudiosDTO, Integer, ProgramaEstudios>
		implements ProgramaEstudiosServicio {

	@Autowired
	private ProgramaEstudiosDAO daoPrograma;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PROGRAMA_ESTUDIOS)
	private Assembler assembelrPrograma;
	
	@Override
	BaseDAO getDAO() {
		return daoPrograma;
	}

	@Override
	Assembler getAssembler() {
		return assembelrPrograma;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByDsc(String dsc) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByDsc(dsc);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByDsc: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByClave(String cve) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByClave(cve);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByClave: "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByTipo(int idTipo) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByTipo(idTipo);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByTipo: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByClaveDscAndTipo(String cve, String dsc, int idTipo) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByClaveDscAndTipo(cve, dsc, idTipo);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByClaveDscAndTipo: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByClaveAndDsc(String cve, String dsc) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByCveAndDsc(cve, dsc);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByClaveAndDsc: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByClaveAndTipo(String cve, int idTipo) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByCveAndTipo(cve, idTipo);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByClaveAndTipo: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ProgramaEstudiosDTO> findByDscAndTipo(String dsc, int idTipo) throws ServicioException{
		try{
			List<ProgramaEstudios> listMapping = daoPrograma.findByDscAndTipo(dsc, idTipo);
			List<ProgramaEstudiosDTO> listDTO = assembelrPrograma.getDTOListTransform(listMapping);
			return listDTO;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByDscAndTipo: "+e.getMessage(), e);
		}
	}
	
}
