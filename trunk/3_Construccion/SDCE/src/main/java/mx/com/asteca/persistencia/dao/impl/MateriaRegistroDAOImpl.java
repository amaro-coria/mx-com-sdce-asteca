/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.MateriaRegistroDAO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class MateriaRegistroDAOImpl extends
		BaseDAOImpl<MateriasRegistros, Integer> implements MateriaRegistroDAO {

	@Override
	public List<MateriasRegistros> findByNombre(String nombre) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MateriasRegistros.class);
			criteria.add(Restrictions.ilike("nombre", nombre));
			List<MateriasRegistros> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByNombre: "+e.getMessage(), e);
		}
	}
	
	@Override
	public List<MateriasRegistros> findByProgramaEstudios(int idProgramaEstudios) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MateriasRegistros.class);
			criteria.createCriteria("programaEstMateriases").add(Restrictions.eq("programaEstudios.idProgEstudio", idProgramaEstudios));
			List<MateriasRegistros> list = criteria.list();
			return list;
		}catch(Exception e){
			throw new PersistenciaException("Error en findByProgramaEstudios: "+e.getMessage(), e);
		}
	}
	
}
