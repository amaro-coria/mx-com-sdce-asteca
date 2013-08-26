/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoClienteDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.TipoClienteDAO;
import mx.com.asteca.persistencia.entidades.TiposClientes;
import mx.com.asteca.servicio.TipoClienteServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class TipoClienteServicioImpl extends
		BaseServiceImpl<TipoClienteDTO, Short, TiposClientes> implements
		TipoClienteServicio {

	@Autowired
	private TipoClienteDAO clienteDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_TIPO_CLIENTE)
	private Assembler clienteAssembler;
	
	@Override
	BaseDAO getDAO() {
		return clienteDAO;
	}

	@Override
	Assembler getAssembler() {
		return clienteAssembler;
	}

}
