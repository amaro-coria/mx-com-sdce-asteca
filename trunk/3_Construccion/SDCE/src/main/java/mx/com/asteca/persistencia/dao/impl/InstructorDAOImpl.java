/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.InstructorDAO;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.InstructoresDocumentos;
import mx.com.asteca.persistencia.entidades.InstructoresMaterias;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class InstructorDAOImpl extends BaseDAOImpl<Instructores, Integer>
		implements InstructorDAO {

	
	@Override
	public void removeDependencias(Instructores instructores) throws PersistenciaException{
		try{
			Set<InstructoresMaterias> materias  = instructores.getInstructoresMateriases();
			Set<InstructoresDocumentos> documentos = instructores.getInstructoresDocumentoses();
			Iterator<InstructoresMaterias> iterator = materias.iterator();
			while(iterator.hasNext()){
				InstructoresMaterias materia = iterator.next();
				getSessionFactory().getCurrentSession().delete(materia);
			}
			Iterator<InstructoresDocumentos> iterator2 = documentos.iterator();
			while(iterator2.hasNext()){
				InstructoresDocumentos doc = iterator2.next();
				getSessionFactory().getCurrentSession().delete(doc);
			}
		}catch(Exception e){
			throw new PersistenciaException("Error en removeDependencias: "+e.getMessage(), e); 
		}
	}
	
	@Override
	public List<Instructores> findInstructoresHabilitados(int idMateriaRegistro) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Instructores.class);
			criteria.createCriteria("instructoresMateriases").add(Restrictions.eq("materias.idMateria",idMateriaRegistro));
			List<Instructores> lista = criteria.list();
			return lista;
		}catch(Exception e){
			throw new PersistenciaException("Error en findInstructoresHabilitados: "+e.getMessage(), e); 
		}
	}
	
}
