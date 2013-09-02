/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AsentamientoDAO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.persistencia.entidades.Municipios;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class AsentamientoDAOImpl extends
		BaseDAOImpl<Asentamientos, AsentamientosId> implements AsentamientoDAO {

	public List<Asentamientos> findByMunicipio(Municipios municipio)
			throws PersistenciaException {
		List<Asentamientos> listAsentamientos = null;
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Asentamientos.class).add(Restrictions.eq("municipios", municipio));
			listAsentamientos = criteria.list();
		return listAsentamientos;
	}

	
}
