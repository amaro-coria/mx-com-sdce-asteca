package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.EquipoDTO;

public interface EquipoFachada {

	public Integer saveEquipo(EquipoDTO Equipos) throws FachadaException;

	public List<EquipoDTO> getAllEquipo() throws FachadaException;
}
