package mx.com.asteca.persistencia.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.com.asteca.persistencia.entidades.RolesModUsuarios;

public interface RolesModUsuariosDAO extends BaseDAO<RolesModUsuarios, Integer> {
	
	RolesModUsuarios findByUser(int idUsuario) throws PersistenceException;

}
