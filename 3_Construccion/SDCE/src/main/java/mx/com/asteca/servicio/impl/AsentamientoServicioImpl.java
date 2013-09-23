/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AsentamientoDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.servicio.AsentamientoServicio;
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
public class AsentamientoServicioImpl extends
		BaseServiceImpl<AsentamientoDTO, AsentamientosId, Asentamientos>
		implements AsentamientoServicio {

	@Autowired
	private AsentamientoDAO daoAsentamiento;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ASENTAMIENTOS)
	private Assembler assembler;

	@Override
	BaseDAO getDAO() {
		return daoAsentamiento;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Integer> getDistinctCPs() throws ServicioException{
		try {
			List<Integer> listMapping = daoAsentamiento.getDistinctCPs();
			return listMapping;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en getDistinctCPs Servicio :"+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AsentamientoDTO> findAsentamientosByCp(int cp) throws ServicioException{
		try{
			List<Asentamientos> listMapping = daoAsentamiento.findAsentamientosByCp(cp);
			List<AsentamientoDTO> listaDTOs = assembler.getDTOListTransform(listMapping);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findAsentamientosByCp Servicio :"+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public AsentamientoDTO findAsentamiento(int idAsentamiento, int idMunicipio, int idEstado) throws ServicioException{
		try{
			AsentamientosId id = new AsentamientosId(idAsentamiento, idMunicipio, idEstado);
			Asentamientos mapping = daoAsentamiento.findByPK(id);
			AsentamientoDTO dto = (AsentamientoDTO) assembler.getDTOTransform(mapping);
			return dto;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findAsentamiento Servicio :"+e.getMessage(), e);
		}
	}

}
