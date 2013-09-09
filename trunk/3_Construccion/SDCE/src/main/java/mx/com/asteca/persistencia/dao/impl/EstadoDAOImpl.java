/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.EstadoDAO;
import mx.com.asteca.persistencia.entidades.Estados;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 * 
 */
@Repository
public class EstadoDAOImpl extends BaseDAOImpl<Estados, Integer> implements
		EstadoDAO {

	@Override
	public List<Estados> getFromPais(int idPais) throws PersistenciaException {
		try {
			Criteria criteria = super.getSessionFactory().getCurrentSession()
					.createCriteria(Estados.class);
			criteria.add(Restrictions.eq("paises.idPais", idPais));
			List<Estados> lista = criteria.list();
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo la lista persistente", e);
		}
	}

}
