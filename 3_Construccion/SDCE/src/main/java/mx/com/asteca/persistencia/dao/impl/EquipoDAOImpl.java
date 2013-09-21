package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.EquipoDAO;
import mx.com.asteca.persistencia.entidades.Equipos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EquipoDAOImpl extends BaseDAOImpl<Equipos, Integer> implements
		EquipoDAO {

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.EquipoDAO#findByClave(java.lang.String)
	 */
	@Override
	public List<Equipos> findByClave(String cve) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Equipos.class);
			criteria.add(Restrictions.like("clave", cve));
			List<Equipos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error obteniendo equipos por clave:" + ex.getMessage(), ex);
		}

	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.EquipoDAO#findByClaveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Equipos> findByClaveAndDsc(String cve, String dsc) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Equipos.class);
			criteria.add(Restrictions.like("clave", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			List<Equipos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error obteniendo equipos por clave:" + ex.getMessage(), ex);
		}

	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.EquipoDAO#findByClaveDscAndTipo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Equipos> findByClaveDscAndTipo(String cve, String dsc, String tipo) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Equipos.class);
			criteria.add(Restrictions.like("clave", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			//criteria.add(Restrictions.ilike("catGral.dsc", tipo));
			criteria.createCriteria("catGral").add(Restrictions.eq("dsc", tipo));
			List<Equipos> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error obteniendo equipos por clave:" + ex.getMessage(), ex);
		}

	}

}
