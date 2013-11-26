/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CertificadoDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Certificados;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class CertificadoDAOImpl extends BaseDAOImpl<Certificados, Integer>
		implements CertificadoDAO {

	@Override
	public void cancelaCertificado(int idCertificado, String motivo) throws PersistenciaException{
		try{
			Certificados cert = super.findByPK(idCertificado);
			cert.setFechaCancelacion(new Date(System.currentTimeMillis()));
			cert.setMotivoCancelacion(motivo);
			super.update(cert);
		}catch(Exception e){
			throw new PersistenciaException("Erroe en cancelaCertificado: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByNoCert(String noCert) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.like("noCertificado", noCert));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByNoCert: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByAlumno(int alumno) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.eq("alumnos.idAlumno", alumno));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByAlumno: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByEstatus(short idEstatus) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.eq("estatus.idEstatus", idEstatus));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByEstatus: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByNoCertAlumnoEstatus(String noCert, int idAlumno, short idEstatus) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.like("noCertificado", noCert));
			criteria.add(Restrictions.eq("alumnos.idAlumno", idAlumno));
			criteria.add(Restrictions.eq("estatus.idEstatus", idEstatus));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByNoCertAlumnoEstatus: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByNoCertAlumno(String noCert, int idAlumno) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.like("noCertificado", noCert));
			criteria.add(Restrictions.eq("alumnos.idAlumno", idAlumno));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByNoCertAlumno: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByNoCertEstatus(String noCert, short idEstatus) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.like("noCertificado", noCert));
			criteria.add(Restrictions.eq("estatus.idEstatus", idEstatus));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByNoCertEstatus: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Certificados> findByAlumnoEstatus(int idAlumno, short idEstatus) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Certificados.class);
			criteria.add(Restrictions.eq("alumnos.idAlumno", idAlumno));
			criteria.add(Restrictions.eq("estatus.idEstatus", idEstatus));
			List<Certificados> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Erroe en findByAlumnoEstatus: "+e.getMessage(), e);
		}
	}
	
}
