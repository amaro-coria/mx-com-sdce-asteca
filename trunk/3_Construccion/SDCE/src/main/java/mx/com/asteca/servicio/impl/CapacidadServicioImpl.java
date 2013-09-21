/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CapacidadDAO;
import mx.com.asteca.persistencia.dao.TipoLicenciaDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Capacidades;
import mx.com.asteca.servicio.CapacidadServicio;
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
public class CapacidadServicioImpl extends
		BaseServiceImpl<CapacidadDTO, Integer, Capacidades> implements
		CapacidadServicio {

	@Autowired
	private CapacidadDAO daoCapacidad;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CAPACIDADES)
	private Assembler assemblerCapacidad;
	
	@Override
	BaseDAO getDAO() {
		return daoCapacidad;
	}

	@Override
	Assembler getAssembler() {
		return assemblerCapacidad;
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.CapacidadServicio#saveCapacidadPorAlumno(mx.com.asteca.comun.dto.CapacidadDTO, int)
	 */
	@Override
	@Transactional
	public int saveCapacidadPorAlumno(CapacidadDTO capacidad, int alumnoID) throws ServicioException{
		Alumnos alumno = new Alumnos();
		alumno.setIdAlumno(alumnoID);
		Capacidades capacidades = (Capacidades) assemblerCapacidad.getMappingTransform(capacidad);
		try {
			int pk = daoCapacidad.save(capacidades);
			capacidad.setIdCapacidad(pk);
			int idCruce = daoCapacidad.saveCapacidadPorAlumno(capacidades, alumno);
			return idCruce;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en saveCapacidadPorAlumnoServicio:"+e.getMessage(), e);
		}
	}
	
	
	

}
