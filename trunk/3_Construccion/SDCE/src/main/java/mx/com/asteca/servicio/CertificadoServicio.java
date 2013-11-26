/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.persistencia.entidades.Certificados;

/**
 * @author JAMARO
 *
 */
public interface CertificadoServicio extends
		BaseService<CertificadoDTO, Integer, Certificados> {

	List<CertificadoDTO> findByNoCertAlumnoEstatus(String noCert, int idAlumno,
			short idEstatus) throws ServicioException;

	List<CertificadoDTO> findByNoCertAlumno(String noCert, int idAlumno)
			throws ServicioException;

	List<CertificadoDTO> findByNoCertEstatus(String noCert, short idEstatus)
			throws ServicioException;

	List<CertificadoDTO> findByAlumnoEstatus(int idAlumno, short idEstatus)
			throws ServicioException;

	List<CertificadoDTO> findByAlumno(int idAlumno) throws ServicioException;

	List<CertificadoDTO> findByNoCert(String noCert) throws ServicioException;

	List<CertificadoDTO> findByEstatus(short idEstatus)
			throws ServicioException;

	void cancelaCertificado(int idCert, String motivo) throws ServicioException;

}
