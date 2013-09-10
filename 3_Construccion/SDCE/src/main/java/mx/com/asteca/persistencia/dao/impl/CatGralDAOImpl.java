/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CatGralDAO;
import mx.com.asteca.persistencia.entidades.CatGral;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Javier
 * 
 */
@Repository
public class CatGralDAOImpl extends BaseDAOImpl<CatGral, Integer> implements
		CatGralDAO {

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findByCve(java.lang.String)
	 */
	@Override
	public List<CatGral> findByCve(String cve) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException("Error accediendo a findByCve:"
					+ ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findByCveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatGral> findByCveAndDsc(String cve, String dsc)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a findByCveAndDsc:" + ex.getMessage(), ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findByCveDscAndEstatus(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatGral> findByCveDscAndEstatus(String cve, String dsc, String estatus) throws PersistenciaException{
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			criteria.add(Restrictions.like("estatus", estatus));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a findByCveAndDsc:" + ex.getMessage(), ex);
		}
	}

}
