/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.Iterator;
import java.util.Set;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.InstructorDAO;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.InstructoresDocumentos;
import mx.com.asteca.persistencia.entidades.InstructoresMaterias;

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
	
}
