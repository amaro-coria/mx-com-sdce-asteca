package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PaisDTO;

public interface MunicipioFachada extends BaseFachada<MunicipioDTO, Integer> {

	public List<EstadoDTO> getListaEstados() throws FachadaException;
	
	public List<PaisDTO> getListaPaises() throws FachadaException;
	
	public List<EstadoDTO> getFromPais(int paisId) throws FachadaException;
	
	public List<MunicipioDTO> getFromEstado(int edoId) throws FachadaException;

	MunicipioDTO getFromMunicipioEdo(int edoID, int mpioID)
			throws FachadaException;
}
