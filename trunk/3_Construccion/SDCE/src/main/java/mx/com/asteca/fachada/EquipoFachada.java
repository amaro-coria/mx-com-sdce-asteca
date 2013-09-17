package mx.com.asteca.fachada;

import mx.com.asteca.comun.dto.EquipoDTO;

public interface EquipoFachada extends BaseFachada<EquipoDTO, Integer>{

	public Integer saveEquipo(EquipoDTO Equipos) throws FachadaException;

}
