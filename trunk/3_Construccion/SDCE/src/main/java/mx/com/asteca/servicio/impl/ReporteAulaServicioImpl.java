package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ReporteAulasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ReporteAulasDAO;
import mx.com.asteca.persistencia.entidades.ReporteAulas;
import mx.com.asteca.servicio.ReporteAulaServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 *
 */
@Service
public class ReporteAulaServicioImpl extends BaseServiceImpl<ReporteAulasDTO, Integer, ReporteAulas>
		implements ReporteAulaServicio {

	@Autowired
	private ReporteAulasDAO dao;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_REPORTE_AULAS)
	private Assembler assembler;
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.impl.BaseServiceImpl#getDAO()
	 */
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_AULAS)
	@Override
	BaseDAO getDAO() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.impl.BaseServiceImpl#getAssembler()
	 */
	@Override
	Assembler getAssembler() {
		return assembler;
	}

	@Override
	public List<ReporteAulasDTO> getAula() throws ServicioException {
		try{
			List<ReporteAulas> listaReporteAulas = dao.findAulas();
			List<ReporteAulasDTO> listaDTOs = assembler.getDTOListTransform(listaReporteAulas);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en servicio findAulasDisponibles:"+e.getMessage(), e);
		}
	}
	
}
