/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.fachada.CursoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CursoServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoFachadaImpl extends BaseFachadaImpl<CursoDTO, Integer>
		implements CursoFachada {

	@Autowired
	private CursoServicio cursoServicio;

	@Override
	BaseService getBaseService() {
		return cursoServicio;
	}

	@Override
	public List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)
			throws FachadaException {
		try {
			List<CursoDTO> lista = cursoServicio.findByInstructorSedeAndArea(
					instructor, sede, area, fechaIni, fachaFin);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByInstructorSedeAndArea Fachada : "
							+ e.getMessage(), e);
		}
	}
}
