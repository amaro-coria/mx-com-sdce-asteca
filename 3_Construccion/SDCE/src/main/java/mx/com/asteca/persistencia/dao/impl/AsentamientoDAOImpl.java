/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.Collections;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AsentamientoDAO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.persistencia.entidades.Municipios;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 * 
 */
@Repository
public class AsentamientoDAOImpl extends
		BaseDAOImpl<Asentamientos, AsentamientosId> implements AsentamientoDAO {

	@Override
	public List<Asentamientos> findByMunicipio(Municipios municipio)
			throws PersistenciaException {
		try {
			List<Asentamientos> listAsentamientos = null;
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Asentamientos.class)
					.add(Restrictions.eq("municipios", municipio));
			listAsentamientos = criteria.list();
			return listAsentamientos;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByMunicipio :"
					+ ex.getMessage(), ex);
		}
	}

	@Override
	public List<Integer> getDistinctCPs() throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Asentamientos.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("idCp")));
			List<Integer> list = criteria.list();
			Collections.sort(list);
			return list;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en getDistinctCPs :"
					+ ex.getMessage(), ex);
		}
	}
	
	@Override
	public List<Asentamientos> findAsentamientosByCp(int idCp) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Asentamientos.class);
			criteria.add(Restrictions.eq("idCp", idCp));
			List<Asentamientos> lista = criteria.list();
			return lista;
		}catch(Exception ex){
			throw new PersistenciaException("Error en xx :"+ex.getMessage(), ex);
		}
	}

}
