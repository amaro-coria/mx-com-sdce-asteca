package mx.com.asteca.servicio.impl;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(readOnly=true)
	public List<ClienteDTO> findByClave(String clave) throws ServicioException {
		try {
			List<Clientes> listaClientes = clienteDAO.findByClave(clave);
			List<ClienteDTO> listaDTOs = assemblerCliente
					.getDTOListTransform(listaClientes);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<ClienteDTO> findByClaveAndNombre(String clave, String nombre)
			throws ServicioException {
		try {
			List<Clientes> listaClientes = clienteDAO.findByClaveAndNombre(
					clave, nombre);
			List<ClienteDTO> listaDTOs = assemblerCliente
					.getDTOListTransform(listaClientes);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}

	}

}
