package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.persistencia.entidades.Clientes;
import mx.com.asteca.persistencia.entidades.TiposClientes;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_CLIENTE)
public class ClienteAssembler extends Assembler<ClienteDTO, Clientes>{

	@Override
	public ClienteDTO getDTOTransform(Clientes mapping) {
		if(mapping == null || mapping.getIdCliente() == 0){
			return null;
		}
		ClienteDTO dto = new ClienteDTO();
			dto.setIdCliente(mapping.getIdCliente());
			dto.setClave(mapping.getClave());
			dto.setEmail(mapping.getEmail());
			dto.setNombre(mapping.getNombre());
			dto.setResponsable(mapping.getResponsable());
			dto.setTelefono(mapping.getTelefono());
			TiposClientes tipoCliente = mapping.getTiposClientes();
			dto.setTipoCliente(tipoCliente.getIdTipoCliente());
			dto.setTipoClienteString(tipoCliente.getNombre());
		return dto;
	}

	@Override
	public Clientes getMappingTransform(ClienteDTO dto) {
		if(dto == null){
			return null;
		}
		Clientes mapping = new Clientes();
			mapping.setClave(dto.getClave());
			mapping.setEmail(dto.getEmail());
			mapping.setNombre(dto.getNombre());
			mapping.setResponsable(dto.getResponsable());
			mapping.setTelefono(dto.getTelefono());
			if(dto.getTipoCliente() != 0){
				TiposClientes tipoCliente = new TiposClientes((short) dto.getTipoCliente());
				mapping.setTiposClientes(tipoCliente);
			}
			if(dto.getIdCliente() != 0){
				mapping.setIdCliente(dto.getIdCliente());
			}
		return mapping;
	}

}
