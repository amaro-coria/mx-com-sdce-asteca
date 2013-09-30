package mx.com.asteca.persistencia.dao.impl;

import java.util.List;





import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.dao.RolesModPermisosUsrDAO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

@Repository
public class RolesModPermisosUsrDAOImpl extends
		BaseDAOImpl<RolesModPermisosUsr, Integer> implements
		RolesModPermisosUsrDAO {

	@Override
	public List<RolesModPermisosUsr> findByRolModUsr(int idModRolUsr) {
		Session session = super.getSessionFactory().openSession();
		List<RolesModPermisosUsr> result = null;
		try {
			Criteria criteria = session.createCriteria(RolesModPermisosUsr.class);
			Criteria rolModUsuario = criteria.createCriteria("rolesModUsuarios");
			
			rolModUsuario.add(Restrictions.eq("idRolModUsr", idModRolUsr));
			
			result = (List<RolesModPermisosUsr>) criteria.list();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
}
