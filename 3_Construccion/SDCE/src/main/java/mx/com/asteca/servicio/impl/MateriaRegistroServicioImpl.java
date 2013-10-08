/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MateriaRegistroDAO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;
import mx.com.asteca.servicio.MateriaRegistroServicio;
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
public class MateriaRegistroServicioImpl extends
		BaseServiceImpl<MateriaRegistroDTO, Integer, MateriasRegistros>
		implements MateriaRegistroServicio {

	@Autowired
	private MateriaRegistroDAO daoMateriaReg;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS_REGISTROS)
	private Assembler assemblerMAteriaReg;
	
	@Override
	BaseDAO getDAO() {
		return daoMateriaReg;
	}

	@Override
	Assembler getAssembler() {
		return assemblerMAteriaReg;
	}

	@Override
	@Transactional(readOnly=true)
	public List<MateriaRegistroDTO> findByIdProgramaEstudio(int idProgramaEstudio) throws ServicioException{
		try{
			List<MateriasRegistros> listMapping = daoMateriaReg.findByProgramaEstudios(idProgramaEstudio);
			List<MateriaRegistroDTO> listDTOs = assemblerMAteriaReg.getDTOListTransform(listMapping);
			return listDTOs;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByIdProgramaEstudio: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MateriaRegistroDTO> findByNombre(String nombre) throws ServicioException{
		try{
			List<MateriasRegistros> listMapping = daoMateriaReg.findByNombre(nombre);
			List<MateriaRegistroDTO> listDTOs = assemblerMAteriaReg.getDTOListTransform(listMapping);
			return listDTOs;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNombre: "+e.getMessage(), e);
		}
	}
	
}
