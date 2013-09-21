package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.persistencia.entidades.Capacidades;
import mx.com.asteca.persistencia.entidades.TiposLicencia;
import mx.com.asteca.util.FechaUtil;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_CAPACIDADES)
public class CapacidadAssembler extends Assembler<CapacidadDTO, Capacidades>{

	private FechaUtil fechaUtil;
	
	public CapacidadAssembler(){
		fechaUtil = FechaUtil.getInstance();
	}
	
	@Override
	public CapacidadDTO getDTOTransform(Capacidades mapping) {
		if(mapping == null){
			return null;
		}
		CapacidadDTO dto = new CapacidadDTO();
			dto.setAcreditada(mapping.getAcreditada());
			dto.setAutorizada(mapping.getAutorizada());
			dto.setFechaFin(fechaUtil.parseDateMM_dd_yy(mapping.getFechaFin()));
			dto.setFechaInic(fechaUtil.parseDateMM_dd_yy(mapping.getFechaInic()));
			dto.setHoras(mapping.getHoras());
			dto.setIdCapacidad(mapping.getIdCapacidad());//llave PK
			if(mapping.getTiposLicencia() != null){
				dto.setIdTipoLicencia(mapping.getTiposLicencia().getIdTipoLic());
				dto.setTipoLicencia(mapping.getTiposLicencia().getDescTipoLic());
			}
			dto.setTipoCapacidad(mapping.getTipoCapacidad());
		return dto;
	}

	@Override
	public Capacidades getMappingTransform(CapacidadDTO dto) {
		if(dto == null){
			return null;
		}
		Capacidades mapping = new Capacidades();
			mapping.setAcreditada(dto.getAcreditada());
			mapping.setAutorizada(dto.getAutorizada());
			mapping.setFechaFin(fechaUtil.parseDateMM_dd_yy(dto.getFechaFin()));
			mapping.setFechaInic(fechaUtil.parseDateMM_dd_yy(dto.getFechaInic()));
			mapping.setHoras(dto.getHoras());
			if(dto.getIdCapacidad() != 0){
				mapping.setIdCapacidad(dto.getIdCapacidad());//llave PK
			}
			mapping.setTipoCapacidad(dto.getTipoCapacidad());
			if(dto.getIdTipoLicencia() != 0){
				TiposLicencia tiposLicencia = new TiposLicencia();
				tiposLicencia.setIdTipoLic((short) dto.getIdTipoLicencia());
				mapping.setTiposLicencia(tiposLicencia);
			}
			return mapping;
	}

}
