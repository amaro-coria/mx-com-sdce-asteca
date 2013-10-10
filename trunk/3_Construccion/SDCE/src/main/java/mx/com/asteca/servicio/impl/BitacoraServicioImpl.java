/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.BitacoraDAO;
import mx.com.asteca.persistencia.entidades.Bitacora;
import mx.com.asteca.servicio.BitacoraServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JAMARO
 *
 */
@Service
public class BitacoraServicioImpl extends
		BaseServiceImpl<BitacoraDTO, Long, Bitacora> implements
		BitacoraServicio {
	
	@Autowired
	private BitacoraDAO daoBitacora;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_BITACORA)
	private Assembler assemblerBitacora;

	@Override
	BaseDAO getDAO() {
		return daoBitacora;
	}

	@Override
	Assembler getAssembler() {
		return assemblerBitacora;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<BitacoraDTO> findByAccion(String accion) throws ServicioException{
		try{
			List<Bitacora> listMapping = daoBitacora.findByAccion(accion);
			List<BitacoraDTO> listBitacora = assemblerBitacora.getDTOListTransform(listMapping);
			return listBitacora;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByAccion:"+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<BitacoraDTO> findByIdUsuario(int  idUsuario) throws ServicioException{
		try{
			List<Bitacora> listMapping = daoBitacora.findByIdUsuario(idUsuario);
			List<BitacoraDTO> listBitacora = assemblerBitacora.getDTOListTransform(listMapping);
			return listBitacora;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByIdUsuario:"+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<BitacoraDTO> findByIdUsuarioAndAccion(int idUsuario, String accion) throws ServicioException{
		try{
			List<Bitacora> listMapping = daoBitacora.findByUsuarioAndAccion(idUsuario, accion);
			List<BitacoraDTO> listBitacora = assemblerBitacora.getDTOListTransform(listMapping);
			return listBitacora;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByIdUsuarioAndAccion:"+e.getMessage(), e);
		}
	}

}
