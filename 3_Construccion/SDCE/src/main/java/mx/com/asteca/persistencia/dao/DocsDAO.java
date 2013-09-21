package mx.com.asteca.persistencia.dao;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Docs;

public interface DocsDAO extends BaseDAO<Docs, Integer> {

	int saveDocPorAlumno(Docs doc, int idAlumno) throws PersistenciaException;

}
