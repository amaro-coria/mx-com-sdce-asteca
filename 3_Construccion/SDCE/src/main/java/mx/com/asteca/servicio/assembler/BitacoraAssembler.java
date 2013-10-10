/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.PersonaDAO;
import mx.com.asteca.persistencia.entidades.Bitacora;
import mx.com.asteca.persistencia.entidades.Personas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_BITACORA)
public class BitacoraAssembler extends Assembler<BitacoraDTO, Bitacora> {

	@Autowired
	private PersonaDAO daoPersona;
	
	@Override
	public BitacoraDTO getDTOTransform(Bitacora mapping) {
		if(mapping == null){
			return null;
		}
		BitacoraDTO dto = new BitacoraDTO();
			dto.setAccion(mapping.getAccion());
			dto.setFecha(mapping.getFecha());
			dto.setIdBitacora(mapping.getIdBitacora());
			dto.setIp(mapping.getIp());
			dto.setMensaje(mapping.getMensaje());
			if(mapping.getIdUsr() != null){
				dto.setIdUsr(mapping.getIdUsr());
				try {
					Personas personaMapping = daoPersona.findByPK(mapping.getIdUsr());
					String nombre = personaMapping.getNombre() + " " + personaMapping.getApellidoP() + " " + personaMapping.getApellidoM();
					dto.setUsuario(nombre);
				} catch (PersistenciaException e) {
					dto.setUsuario("");
				}
				
			}
		return dto;
	}

	@Override
	public Bitacora getMappingTransform(BitacoraDTO dto) {
		if(dto == null){
			return null;
		}
		Bitacora mapping = new Bitacora();
			mapping.setAccion(dto.getAccion());
			mapping.setFecha(dto.getFecha());
			if(dto.getIdBitacora() != 0){
				mapping.setIdBitacora(dto.getIdBitacora());
			}
			if(dto.getIdUsr() != 0){
				mapping.setIdUsr(dto.getIdUsr());
			}
			mapping.setIp(dto.getIp());
			mapping.setMensaje(dto.getMensaje());
		return mapping;
	}

}
