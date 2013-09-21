/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.EstudioDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Estudios;
import mx.com.asteca.servicio.EstudiosServicio;
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
public class EstudioServicioImpl extends
		BaseServiceImpl<EstudioDTO, Integer, Estudios> implements
		EstudiosServicio {

	@Autowired
	private EstudioDAO daoEstudio;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ESTUDIO)
	private Assembler assemblerEstudio;
	
	@Override
	BaseDAO getDAO() {
		return daoEstudio;
	}

	@Override
	Assembler getAssembler() {
		return assemblerEstudio;
	}
	
	@Override
	@Transactional
	public int saveEstudioPorAlumno(EstudioDTO estudio, int alumnoID) throws ServicioException{
		try{
			Estudios mapping = (Estudios) assemblerEstudio.getMappingTransform(estudio);
			int pk = daoEstudio.save(mapping);
			mapping.setIdEstudios(pk);
			Alumnos alumnos = new Alumnos(alumnoID);
			int id = daoEstudio.saveEstudioPorAlumno(mapping, alumnos);
			return id;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en saveEstudioPorAlumno:"+e.getMessage(), e);
		}
	}

}
