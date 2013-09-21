package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AulaDAO;
import mx.com.asteca.persistencia.entidades.Aulas;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
@Repository
public class AulaDAOImpl extends BaseDAOImpl<Aulas, Integer> implements AulaDAO {

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.AulaDAO#findBySede(java.lang.String)
	 */
	@Override
	public List<Aulas> findBySede(String sede) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Aulas.class);
			criteria.createCriteria("catGral").add(
					Restrictions.ilike("dsc", sede));
			List<Aulas> listaAulas = criteria.list();
			return listaAulas;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a la bd para Aulas:sedes :: "
							+ ex.getMessage(), ex);
		}
	}
}
