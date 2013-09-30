/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ModulosDAO;
import mx.com.asteca.persistencia.entidades.Modulos;

/**
 * @author Javier
 * 
 */
@Repository
public class ModulosDAOImpl extends BaseDAOImpl<Modulos, Integer> implements
		ModulosDAO {

	@Override
	public Modulos findByNombre(String nombre) throws PersistenceException {
		Session session = super.getSessionFactory().openSession();
		Modulos result = null;
		
		Criteria criteria = session.createCriteria(Modulos.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		
		result = (Modulos) criteria.uniqueResult();
		
		if (session.isOpen())
			session.close();
		return result;
	}

}
