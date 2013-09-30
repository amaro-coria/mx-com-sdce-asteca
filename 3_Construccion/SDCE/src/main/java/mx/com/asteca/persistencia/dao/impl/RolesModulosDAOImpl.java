package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.dao.RolesModulosDAO;
import mx.com.asteca.persistencia.entidades.RolesModulos;

@Repository
public class RolesModulosDAOImpl extends BaseDAOImpl<RolesModulos, Integer>
		implements RolesModulosDAO {

	@Override
	public List<RolesModulos> findByIdRol(int idRol)
			throws PersistenceException {
		Session session = this.getSessionFactory().openSession();
		List<RolesModulos> result = null;
		Criteria criteria = session.createCriteria(RolesModulos.class);
		criteria.add(Restrictions.eq("idRol",idRol));
		result = (List<RolesModulos>) criteria.list();
		if(session.isOpen())
			session.close();
		
		return result;
	}


}
