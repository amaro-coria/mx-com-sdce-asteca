package mx.com.asteca.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.ServicioException;

@Component
public class CatGralFachadaImpl implements CatGralFachada {
	@Autowired
	private CatGralServicio catGralServicio;

	public Integer saveCatGral(CatGralDTO catGral) throws FachadaException {
		// TODO validar reglas extras de negocio
		try {
			Integer pk = catGralServicio.save(catGral);
			return pk;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::",
					e);
		}
	}

	public List<CatGralDTO> getAllCatGral() throws FachadaException {
		try {
			List<CatGralDTO> listaCatGral = catGralServicio.getAll();
			return listaCatGral;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::",
					e);
		}
	}

}
