/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ProgramaEstudiosDAO;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class ProgramaEstudiosDAOImpl extends
		BaseDAOImpl<ProgramaEstudios, Integer> implements ProgramaEstudiosDAO {

	@Override
	public List<ProgramaEstudios> findByClave(String cve) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("clave", cve));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByClave : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByDsc(String dsc) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("dsc", dsc));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByDsc : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByTipo(int idTipo) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.createCriteria("catGral").add(Restrictions.eq("idCatGral", idTipo));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByDsc : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByClaveDscAndTipo(String cve, String dsc, int idTipo) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("dsc", dsc));
			criteria.add(Restrictions.eq("clave", cve));
			criteria.createCriteria("catGral").add(Restrictions.eq("idCatGral", idTipo));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByClaveDscAndTipo : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByCveAndDsc(String cve, String dsc) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("dsc", dsc));
			criteria.add(Restrictions.eq("clave", cve));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByCveAndDsc : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByCveAndTipo(String cve, int idTipo) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("clave", cve));
			criteria.createCriteria("catGral").add(Restrictions.eq("idCatGral", idTipo));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByCveAndTipo : "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudios> findByDscAndTipo(String dsc, int idTipo) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProgramaEstudios.class);
			criteria.add(Restrictions.eq("dsc", dsc));
			criteria.createCriteria("catGral").add(Restrictions.eq("idCatGral", idTipo));
			List<ProgramaEstudios> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByDscAndTipo : "+e.getMessage(), e);
		}
	}
	
	
}
