package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoClienteDTO;
import mx.com.asteca.persistencia.entidades.TiposClientes;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_TIPO_CLIENTE)
public class TipoClienteAssembler extends Assembler<TipoClienteDTO, TiposClientes> {

	@Override
	public TipoClienteDTO getDTOTransform(TiposClientes mapping) {
		if(mapping == null){
			return null;
		}
		TipoClienteDTO dto = new TipoClienteDTO();
			dto.setIdTipoCliente(mapping.getIdTipoCliente());
			dto.setNombre(mapping.getNombre());
		return dto;
	}

	@Override
	public TiposClientes getMappingTransform(TipoClienteDTO dto) {
		if(dto == null){
			return null;
		}
		TiposClientes mapping = new TiposClientes();
			mapping.setNombre(dto.getNombre());
			if(dto.getIdTipoCliente() != 0){
				mapping.setIdTipoCliente(dto.getIdTipoCliente());
			}
		return mapping;
	}

}
