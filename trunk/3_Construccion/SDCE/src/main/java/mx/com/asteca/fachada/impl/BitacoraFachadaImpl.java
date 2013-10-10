/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.fachada.BitacoraFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.BitacoraServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class BitacoraFachadaImpl extends BaseFachadaImpl<BitacoraDTO, Long>
		implements BitacoraFachada {

	@Autowired
	private BitacoraServicio servicioBitacora;
	
	@Override
	BaseService getBaseService() {
		return servicioBitacora;
	}

	@Override
	public List<BitacoraDTO> findByAccion(String accion) throws FachadaException{
		try{
			List<BitacoraDTO> lista = servicioBitacora.findByAccion(accion);
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByAccion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<BitacoraDTO> findByIdUsuario(int idUsuario) throws FachadaException{
		try{
			List<BitacoraDTO> lista = servicioBitacora.findByIdUsuario(idUsuario);
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByAccion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<BitacoraDTO> findByIdUsuarioAndAccion(int idUsuario, String accion) throws FachadaException{
		try{
			List<BitacoraDTO> lista = servicioBitacora.findByIdUsuarioAndAccion(idUsuario, accion);
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByAccion:"+e.getMessage(), e);
		}
	}
	
}
