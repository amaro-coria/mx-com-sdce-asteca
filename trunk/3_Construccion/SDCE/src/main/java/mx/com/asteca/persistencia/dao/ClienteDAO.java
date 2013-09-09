package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Clientes;


public interface ClienteDAO extends BaseDAO<Clientes, Integer>{

	List<Clientes> findByClave(String clave) throws PersistenciaException;

	List<Clientes> findByClaveAndNombre(String clave, String nombre)
			throws PersistenciaException;

}
