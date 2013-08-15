package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;

public interface CatGralFachada {
	
	public Integer saveCatGral(CatGralDTO catGral)throws FachadaException;
	
	public List<CatGralDTO> getAllCatGral() throws FachadaException;
}
