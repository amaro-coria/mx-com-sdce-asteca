package mx.com.asteca.fachada;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;

public interface CertificadoFachada extends BaseFachada<CertificadoDTO, Integer> {

	List<AlumnoDTO> getAlumnos() throws FachadaException;

	List<EstatusDTO> getEstatus() throws FachadaException;

	void cancelarCertificado(CertificadoDTO dto) throws FachadaException;

	void cancelarAccionCertificado(CertificadoDTO dto) throws FachadaException;

	List<CertificadoDTO> buscarFiltrado(String certificadoStr,
			Integer idAlumno, Short idEstatus) throws FachadaException;

}
