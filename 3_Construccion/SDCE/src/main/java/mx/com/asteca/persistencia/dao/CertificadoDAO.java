package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Certificados;

public interface CertificadoDAO extends BaseDAO<Certificados, Integer> {

	List<Certificados> findByNoCert(String noCert) throws PersistenciaException;

	List<Certificados> findByAlumno(int alumno) throws PersistenciaException;

	List<Certificados> findByEstatus(short idEstatus)
			throws PersistenciaException;

	List<Certificados> findByNoCertAlumnoEstatus(String noCert, int idAlumno,
			short idEstatus) throws PersistenciaException;

	List<Certificados> findByNoCertAlumno(String noCert, int idAlumno)
			throws PersistenciaException;

	List<Certificados> findByNoCertEstatus(String noCert, short idEstatus)
			throws PersistenciaException;

	List<Certificados> findByAlumnoEstatus(int idAlumno, short idEstatus)
			throws PersistenciaException;

	void cancelaCertificado(int idCertificado, String motivo)
			throws PersistenciaException;

}
