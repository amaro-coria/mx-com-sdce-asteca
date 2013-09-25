package mx.com.asteca.persistencia.dao.impl;

import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.dao.RolesDAO;
import mx.com.asteca.persistencia.entidades.Roles;

@Repository
public class RolesDAOImpl extends BaseDAOImpl<Roles, Integer> implements
		RolesDAO {

}
