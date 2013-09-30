package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;






import mx.com.asteca.persistencia.dao.RolesModPermisosSobreDAO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosSobre;

@Repository
public class RolesModPermisosSobreDAOImpl extends
		BaseDAOImpl<RolesModPermisosSobre, Integer> implements
		RolesModPermisosSobreDAO {

	@Override
	public List<RolesModPermisosSobre> findByModPermisosUsr(
			int idRolModPermisosUsr) {
		Session session = super.getSessionFactory().openSession();
		List<RolesModPermisosSobre> result = null;
		try {
			Criteria criteria = session.createCriteria(RolesModPermisosSobre.class);
			Criteria rolModPermisoUsr = criteria.createCriteria("rolesModPermisosUsr");
			rolModPermisoUsr.add(Restrictions.eq("idRolModPerUsr",idRolModPermisosUsr));
			
			result = (List<RolesModPermisosSobre>) criteria.list();
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
