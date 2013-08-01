package mx.com.asteca.persistencia.dao.impl;

import mx.com.asteca.persistencia.dao.ClienteDAO;
import mx.com.asteca.persistencia.entidades.Clientes;

import org.springframework.stereotype.Repository;

@Repository
public class ClientesDAOImpl extends BaseDAOImpl<Clientes, Integer> implements ClienteDAO{

}
