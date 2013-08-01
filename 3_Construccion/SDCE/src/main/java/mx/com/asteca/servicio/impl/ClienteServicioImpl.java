package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ClienteDAO;
import mx.com.asteca.persistencia.entidades.Clientes;
import mx.com.asteca.servicio.ClienteServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImpl extends
		BaseServiceImpl<ClienteDTO, Integer, Clientes> implements
		ClienteServicio {

	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CLIENTE)
	private Assembler assemblerCliente;

	@Override
	BaseDAO getDAO() {
		return clienteDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerCliente;
	}

}
