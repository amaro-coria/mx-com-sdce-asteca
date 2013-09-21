/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.Date;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.DocsDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.DocsAlumnos;

import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class DocsDAOImpl extends BaseDAOImpl<Docs, Integer> implements DocsDAO {

	@Override
	public int saveDocPorAlumno(Docs doc, int idAlumno) throws PersistenciaException{
		try{
			DocsAlumnos docPorAlumno = new DocsAlumnos();
			docPorAlumno.setDocs(doc);
			docPorAlumno.setAlumnos(new Alumnos(idAlumno));
			docPorAlumno.setFechaUpload(new Date());
			int pk = (Integer) getSessionFactory().getCurrentSession().save(docPorAlumno);
			return pk;
		}catch(Exception ex){
			throw new PersistenciaException("Exception en saveDocPorAlumno : "+ex.getMessage(), ex);
		}
	}
	
}
