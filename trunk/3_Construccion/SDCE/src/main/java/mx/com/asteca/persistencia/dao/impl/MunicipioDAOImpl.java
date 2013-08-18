/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.MunicipioDAO;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class MunicipioDAOImpl extends BaseDAOImpl<Municipios, MunicipiosId>
		implements MunicipioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipios> findByEstado(Estados estado)
			throws PersistenciaException {
		List<Municipios> listMunicipios = null;
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Municipios.class).add(Restrictions.eq("estados", estado));
			listMunicipios = criteria.list();
		return listMunicipios;
	}

}
