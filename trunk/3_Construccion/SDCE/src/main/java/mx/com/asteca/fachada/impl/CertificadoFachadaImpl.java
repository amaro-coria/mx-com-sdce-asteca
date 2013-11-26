/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.fachada.CertificadoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.AlumnoServicio;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CertificadoServicio;
import mx.com.asteca.servicio.EstatusServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component
public class CertificadoFachadaImpl extends
		BaseFachadaImpl<CertificadoDTO, Integer> implements CertificadoFachada {

	@Autowired
	private CertificadoServicio servicioCertificado;
	@Autowired
	private AlumnoServicio servicioAlumno;
	@Autowired
	private EstatusServicio servicioEstatus;

	@Override
	BaseService getBaseService() {
		return servicioCertificado;
	}

	@Override
	public List<CertificadoDTO> buscarFiltrado(String certificadoStr,
			Integer idAlumno, Short idEstatus) throws FachadaException {
		boolean certificado = false;
		boolean alumno = false;
		boolean estatus = false;
		List<CertificadoDTO> list = null;
		try {
			if (certificadoStr != null && !certificadoStr.isEmpty()) {
				certificado = true;
			}
			if (idAlumno != null && idAlumno != 0) {
				alumno = true;
			}
			if (idEstatus != null && idEstatus != 0) {
				estatus = true;
			}
			if (certificado && alumno && estatus) {
				list = servicioCertificado.findByNoCertAlumnoEstatus(
						certificadoStr, idAlumno, idEstatus);
			} else if (certificado && alumno) {
				list = servicioCertificado.findByNoCertAlumno(certificadoStr, idAlumno);
			} else if (certificado && estatus) {
				list = servicioCertificado.findByNoCertEstatus(certificadoStr, idEstatus);
			} else if (alumno && estatus) {
				list = servicioCertificado.findByAlumnoEstatus(idAlumno, idEstatus);
			} else if (certificado) {
				list = servicioCertificado.findByNoCert(certificadoStr);
			} else if (alumno) {
				list = servicioCertificado.findByAlumno(idAlumno);
			} else if (estatus) {
				list = servicioCertificado.findByEstatus(idEstatus);
			}
		} catch (ServicioException e) {
			throw new FachadaException("Error en buscarFiltrado : "
					+ e.getMessage(), e);
		}
		return list;
	}

	@Override
	public void cancelarCertificado(CertificadoDTO dto) throws FachadaException {
		if (dto == null) {
			return;
		}
		dto.setFechaCancelacion(new Date(System.currentTimeMillis()));
		try {
			servicioCertificado.cancelaCertificado(dto.getIdCert(), dto.getMotivoCancelacion());
		} catch (ServicioException e) {
			throw new FachadaException("Error en cancelarCertificado: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public void cancelarAccionCertificado(CertificadoDTO dto)
			throws FachadaException {
		if (dto == null) {
			return;
		}
		try {
			dto.setFechaCancelacion(null);
			dto.setMotivoCancelacion("");
			servicioCertificado.update(dto);
		} catch (ServicioException e) {
			throw new FachadaException("Error en cancelarAccionCertificado: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public List<AlumnoDTO> getAlumnos() throws FachadaException {
		try {
			List<AlumnoDTO> listaAlumnos = servicioAlumno.getAll();
			return listaAlumnos;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getAlumnos:" + e.getMessage(),
					e);
		}
	}

	@Override
	public List<EstatusDTO> getEstatus() throws FachadaException {
		try {
			List<EstatusDTO> listaEstatus = servicioEstatus.getAll();
			return listaEstatus;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getEstatus:" + e.getMessage(),
					e);
		}
	}

}
