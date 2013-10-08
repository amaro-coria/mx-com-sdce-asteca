/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.MateriaDAO;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class MateriaDAOImpl extends BaseDAOImpl<Materias, Integer> implements
		MateriaDAO {

	@Override
	public List<Materias> findMateriasPorCurso(Cursos curso) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Materias.class);
			criteria.createCriteria("cursosMateriases").add(Restrictions.eq("cursos", curso));
			List<Materias> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findMateriasPorCurso:"+e.getMessage(), e);
		}
	}
	
}
