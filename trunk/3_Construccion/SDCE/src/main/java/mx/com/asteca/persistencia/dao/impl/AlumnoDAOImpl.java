/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.comun.dto.AlumnoTempDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AlumnoDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;

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
	public List<AlumnoTempDTO> getAlumnosDatosBasicos() throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Alumnos.class);
			criteria.createAlias("personas", "per");
			criteria.createAlias("estatus", "est");
			criteria.setProjection(
					Projections.projectionList()
					.add(Projections.property("idAlumno"),"idAlumno")
					.add(Projections.property("matricula"),"matricula")
					.add(Projections.property("per.nombre"),"nombre")
					.add(Projections.property("per.apellidoP"),"apellidoP")
					.add(Projections.property("per.apellidoM"),"apellidoM")
					.add(Projections.property("est.descEstatus"),"descEstatus")
					)
					.setResultTransformer(Transformers.aliasToBean(AlumnoTempDTO.class));
			List<AlumnoTempDTO> list = criteria.list();
			return list;
		}catch(Exception ex){
			throw new PersistenciaException("Error en getAlumnosDatosBasicos : "+ex.getMessage(), ex);
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
			Criteria criteria = getSessionFactory().getCurrentSession() .createCriteria(Alumnos.class);
			if(area != null && area > 0){
				criteria.add(Restrictions.eq("catGral.idCatGral", area));
			}
			List<Alumnos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error en findByArea DAO:" + ex.getMessage(), ex);
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
