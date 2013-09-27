package mx.com.asteca.persistencia.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.com.asteca.persistencia.entidades.RolesModulos;

public interface RolesModulosDAO extends BaseDAO<RolesModulos, Integer> {
	
	List<RolesModulos> findByIdRol(int idRol) throws PersistenceException;

}
