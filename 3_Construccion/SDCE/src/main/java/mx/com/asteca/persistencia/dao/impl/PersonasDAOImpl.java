/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.PersonaDAO;
import mx.com.asteca.persistencia.entidades.Personas;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class PersonasDAOImpl extends BaseDAOImpl<Personas, Integer> implements
		PersonaDAO {

	@Override
	public Personas findByUserName(String userName)
	throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Personas.class);
			criteria.add(Restrictions.eq("usuario", userName));
			Personas usuario =(Personas) criteria.uniqueResult();
			return usuario;
		} catch(Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD",
					ex);
		}
	}

}
