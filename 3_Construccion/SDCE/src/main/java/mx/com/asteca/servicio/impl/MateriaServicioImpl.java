/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MateriaDAO;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;
import mx.com.asteca.servicio.MateriaServicio;
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
public class MateriaServicioImpl extends
		BaseServiceImpl<MateriaDTO, Integer, Materias> implements
		MateriaServicio {

	@Autowired
	private MateriaDAO daoMateria;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS)
	private Assembler assemblerMateria;

	@Override
	BaseDAO getDAO() {
		return daoMateria;
	}

	@Override
	Assembler getAssembler() {
		return assemblerMateria;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MateriaDTO> findMateriasPorCurso(int idCurso) throws ServicioException{
		try{
			Cursos cursosMapping = new Cursos();
			cursosMapping.setIdCurso(idCurso);
			List<Materias> listaMapping = daoMateria.findMateriasPorCurso(cursosMapping);
			List<MateriaDTO> listaDTOs = assemblerMateria.getDTOListTransform(listaMapping);
			return listaDTOs;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en xx:"+e.getMessage(),e);
		}
	}

}
