package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.dao.RolesModUsuariosDAO;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;

@Repository
public class RolesModUsuariosDAOImpl extends BaseDAOImpl<RolesModUsuarios, Integer>
		implements RolesModUsuariosDAO {

	@Override
	public RolesModUsuarios findByUser(int idUsuario)
			throws PersistenceException {
		Session session = null;
		RolesModUsuarios result = null;
		try {
			session = super.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(RolesModUsuarios.class);
			Criteria persona = criteria.createCriteria("personas");
			persona.add(Restrictions.eq("idPersona", idUsuario));
			
			result = (RolesModUsuarios) criteria.uniqueResult();
			
		} catch(Exception e) {
			
		}finally {
			if(session.isOpen())
				session.close();
		}
		return result;
	}

}
