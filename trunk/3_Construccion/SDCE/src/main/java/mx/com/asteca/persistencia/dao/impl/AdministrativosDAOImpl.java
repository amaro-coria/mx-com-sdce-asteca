/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AdministrativoDAO;
import mx.com.asteca.persistencia.entidades.Administrativos;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class AdministrativosDAOImpl extends
		BaseDAOImpl<Administrativos, Integer> implements AdministrativoDAO {

	@Override
	public List<Administrativos> findByNombreAndClave(String nombreCompleto, String clave) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Administrativos.class);
			criteria.add(Restrictions.ilike("noEmpleado", clave));
			criteria.createCriteria("personas")
			.add(Restrictions
					.sqlRestriction(
							"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
							nombreCompleto, Hibernate.STRING));
			List<Administrativos> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Exception en findByNombreAndClave:"+e.getMessage(),e);
		}
	}
	
	@Override
	public List<Administrativos> findByNombre(String nombreCompleto) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Administrativos.class);
			criteria.createCriteria("personas")
			.add(Restrictions
					.sqlRestriction(
							"concat(NOMBRE,' ',APELLIDO_P,' ',APELLIDO_M) like (?)  ",
							nombreCompleto, Hibernate.STRING));
			List<Administrativos> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Exception en findByNombre:"+e.getMessage(),e);
		}
	}
	
	@Override
	public List<Administrativos> findByClave(String clave) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Administrativos.class);
			criteria.add(Restrictions.ilike("noEmpleado", clave));
			List<Administrativos> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Exception en findByClave:"+e.getMessage(),e);
		}
	}
	
}
