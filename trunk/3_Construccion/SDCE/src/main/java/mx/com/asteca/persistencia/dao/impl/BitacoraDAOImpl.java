/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BitacoraDAO;
import mx.com.asteca.persistencia.entidades.Bitacora;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class BitacoraDAOImpl extends BaseDAOImpl<Bitacora, Long> implements BitacoraDAO {

	@Override
	public List<Bitacora> findByAccion(String accion) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Bitacora.class);
			criteria.add(Restrictions.eq("accion", accion));
			List<Bitacora> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByAccion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Bitacora> findByIdUsuario(int idUsuario) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Bitacora.class);
			criteria.add(Restrictions.eq("idUsr", idUsuario));
			List<Bitacora> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByIdUsuario:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<Bitacora> findByUsuarioAndAccion(int idUsuario, String accion) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Bitacora.class);
			criteria.add(Restrictions.eq("idUsr", idUsuario));
			criteria.add(Restrictions.eq("accion", accion));
			List<Bitacora> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByUsuarioAndAccion:"+e.getMessage(), e);
		}
	}
	
}
