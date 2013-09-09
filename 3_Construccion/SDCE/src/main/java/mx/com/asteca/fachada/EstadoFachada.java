package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.PaisDTO;

public interface EstadoFachada extends BaseFachada<EstadoDTO, Integer> {

	public List<PaisDTO> getPaises() throws FachadaException;
	public List<EstadoDTO> getFromPais(int idPais) throws FachadaException;
}
