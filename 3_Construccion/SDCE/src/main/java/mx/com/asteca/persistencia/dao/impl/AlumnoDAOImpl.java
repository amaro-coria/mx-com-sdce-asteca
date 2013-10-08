/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoTempDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AlumnoDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.AsistenciaCurso;
import mx.com.asteca.persistencia.entidades.CalifCursos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de la interfaz de acceso a datos para Alumnos
 * 
 * @author Jorge Amaro Coria
 * @since 1.0
 * @version 1.0
 * 
 */
@Repository
public class AlumnoDAOImpl extends BaseDAOImpl<Alumnos, Integer> implements
		AlumnoDAO {

	@Override
	public long saveCalificacion(Alumnos alumnos, Cursos cursos,
			Materias materias, BigDecimal calificacion)
			throws PersistenciaException {
		try {
			CalifCursos registro = findRegistro(alumnos, cursos, materias);
			if(registro == null){
				CalifCursos c = new CalifCursos();
				c.setAlumnos(alumnos);
				c.setCalificacion(calificacion);
				c.setCursos(cursos);
				c.setMaterias(materias);

				long pk = (Long) getSessionFactory().getCurrentSession().save(c);
				return pk;
			}else{
				registro.setCalificacion(calificacion);
				getSessionFactory().getCurrentSession().update(registro);
				return registro.getIdCalifCurso();
			}
		} catch (Exception ex) {
			throw new PersistenciaException("Error en getCalificacion : "
					+ ex.getMessage(), ex);
		}
	}

	private CalifCursos findRegistro(Alumnos alumnos, Cursos cursos,
			Materias materias) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CalifCursos.class);
			criteria.add(Restrictions.eq("alumnos", alumnos));
			criteria.add(Restrictions.eq("cursos", cursos));
			criteria.add(Restrictions.eq("materias", materias));
			CalifCursos calificacion = (CalifCursos) criteria.uniqueResult();
			return calificacion;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findRegistro : "
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public BigDecimal getCalificacion(Alumnos alumnos, Cursos cursos,
			Materias materias) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CalifCursos.class);
			criteria.add(Restrictions.eq("alumnos", alumnos));
			criteria.add(Restrictions.eq("cursos", cursos));
			criteria.add(Restrictions.eq("materias", materias));
			CalifCursos calificacion = (CalifCursos) criteria.uniqueResult();
			if (calificacion == null) {
				return BigDecimal.valueOf(0);
			} else {
				return calificacion.getCalificacion();
			}
		} catch (Exception ex) {
			throw new PersistenciaException("Error en getCalificacion : "
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public long registraAsistencia(Alumnos alumnos, Cursos cursos, Date fecha,
			short presente) throws PersistenciaException {
		try {
			AsistenciaCurso asistencia = new AsistenciaCurso();
			asistencia.setAlumnos(alumnos);
			asistencia.setCursos(cursos);
			asistencia.setFecha(fecha);
			asistencia.setPresente(presente);
			long id = (Long) getSessionFactory().getCurrentSession().save(
					asistencia);
			return id;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findAsistencia : "
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public String findAsistencia(Alumnos alumnos, Cursos cursos, Date fecha)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(AsistenciaCurso.class);
			criteria.add(Restrictions.eq("alumnos", alumnos));
			criteria.add(Restrictions.eq("cursos", cursos));
			criteria.add(Restrictions.eq("fecha", fecha));
			AsistenciaCurso asistencia = (AsistenciaCurso) criteria
					.uniqueResult();
			if (asistencia != null) {
				return asistencia.getPresente() == 0 ? "N" : "S";
			} else {
				return "";
			}
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findAsistencia : "
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public List<AlumnoTempDTO> getAlumnosPorCurso(Cursos cursos)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.createCriteria("alumnosCursoses").add(
					Restrictions.eq("cursos", cursos));
			criteria.createAlias("personas", "per");
			criteria.createAlias("estatus", "est");
			criteria.setProjection(
					Projections
							.projectionList()
							.add(Projections.property("idAlumno"), "idAlumno")
							.add(Projections.property("matricula"), "matricula")
							.add(Projections.property("per.nombre"), "nombre")
							.add(Projections.property("per.apellidoP"),
									"apellidoP")
							.add(Projections.property("per.apellidoM"),
									"apellidoM")
							.add(Projections.property("est.descEstatus"),
									"descEstatus")).setResultTransformer(
					Transformers.aliasToBean(AlumnoTempDTO.class));
			List<AlumnoTempDTO> list = criteria.list();
			return list;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en getAlumnosPorCurso : "
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public List<AlumnoTempDTO> getAlumnosDatosBasicos()
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.createAlias("personas", "per");
			criteria.createAlias("estatus", "est");
			criteria.setProjection(
					Projections
							.projectionList()
							.add(Projections.property("idAlumno"), "idAlumno")
							.add(Projections.property("matricula"), "matricula")
							.add(Projections.property("per.nombre"), "nombre")
							.add(Projections.property("per.apellidoP"),
									"apellidoP")
							.add(Projections.property("per.apellidoM"),
									"apellidoM")
							.add(Projections.property("est.descEstatus"),
									"descEstatus")).setResultTransformer(
					Transformers.aliasToBean(AlumnoTempDTO.class));
			List<AlumnoTempDTO> list = criteria.list();
			return list;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en getAlumnosDatosBasicos : " + ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByMatricula(java.lang.String
	 * )
	 */
	@Override
	public List<Alumnos> findByMatricula(String matricula)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.add(Restrictions.ilike("matricula", matricula));
			List<Alumnos> list = criteria.list();
			return list;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByMatricula DAO:"
					+ ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByNombre(java.lang.String)
	 */
	@Override
	public List<Alumnos> findByNombre(String nombreCompleto)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.createCriteria("personas")
					.add(Restrictions
							.sqlRestriction(
									"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
									nombreCompleto, Hibernate.STRING));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByNombre DAO:"
					+ ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByEstatus(java.lang.String)
	 */
	@Override
	public List<Alumnos> findByEstatus(String estatus)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.createCriteria("estatus").add(
					Restrictions.ilike("descEstatus", estatus));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByEstatus DAO:"
					+ ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByMatriculaAndNombre(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<Alumnos> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.add(Restrictions.ilike("matricula", matricula));
			criteria.createCriteria("personas")
					.add(Restrictions
							.sqlRestriction(
									"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
									nombreCompleto, Hibernate.STRING));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en findByMatriculaAndNombre DAO:" + ex.getMessage(),
					ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByMatriculaAndStatus(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<Alumnos> findByMatriculaAndStatus(String matricula,
			String estatus) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.add(Restrictions.ilike("matricula", matricula));
			criteria.createCriteria("estatus").add(
					Restrictions.ilike("descEstatus", estatus));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en findByMatriculaAndStatus DAO:" + ex.getMessage(),
					ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByNombreAndStatus(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public List<Alumnos> findByNombreAndStatus(String nombreCompleto,
			String estatus) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.createCriteria("estatus").add(
					Restrictions.ilike("descEstatus", estatus));
			criteria.createCriteria("personas")
					.add(Restrictions
							.sqlRestriction(
									"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
									nombreCompleto, Hibernate.STRING));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en findByNombreAndStatus DAO:" + ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.AlumnoDAO#findByMatriculaNombreAndStatus
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Alumnos> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.add(Restrictions.ilike("matricula", matricula));
			criteria.createCriteria("estatus").add(
					Restrictions.ilike("descEstatus", estatus));
			criteria.createCriteria("personas")
					.add(Restrictions
							.sqlRestriction(
									"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
									nombreCompleto, Hibernate.STRING));
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en findByMatriculaNombreAndStatus DAO:"
							+ ex.getMessage(), ex);
		}
	}

	@Override
	public List<Alumnos> findByArea(Integer area) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			if (area != null && area > 0) {
				criteria.add(Restrictions.eq("catGral.idCatGral", area));
			}
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByArea DAO:"
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public Alumnos populate(int idAlumno) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Alumnos.class);
			criteria.setFetchMode("personas", FetchMode.JOIN);
			criteria.add(Restrictions.eq("idAlumno", idAlumno));
			Alumnos alumno = (Alumnos) criteria.uniqueResult();
			return alumno;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en populate DAO:"
					+ ex.getMessage(), ex);
		}
	}
}
