/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ProgramaEstudiosFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.ConfDescargasServicio;
import mx.com.asteca.servicio.DocsServicio;
import mx.com.asteca.servicio.MateriaRegistroServicio;
import mx.com.asteca.servicio.ProgramaEstudioAutorizacionServicio;
import mx.com.asteca.servicio.ProgramaEstudiosMateriaServicio;
import mx.com.asteca.servicio.ProgramaEstudiosServicio;
import mx.com.asteca.servicio.ServicioException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component
public class ProgramaEstudiosFachadaImpl extends
		BaseFachadaImpl<ProgramaEstudiosDTO, Integer> implements
		ProgramaEstudiosFachada {

	@Autowired
	private ProgramaEstudiosServicio servicePrograma;
	
	@Autowired
	private CatGralServicio servicioCatGral;

	@Autowired
	private MateriaRegistroServicio servicioMateriaRegistro;
	
	@Autowired
	private DocsServicio servicioDocs;
	
	@Autowired
	private ConfDescargasServicio servicioDescargas;
	
	@Autowired
	private ProgramaEstudioAutorizacionServicio servicioAutorizaciones;
	
	@Autowired
	private ProgramaEstudiosMateriaServicio servicioEstudioMaterias;
	
	@Override
	BaseService getBaseService() {
		return servicePrograma;
	}
	
	@Override
	public int saveEstudioMateria(ProgramaEstudiosMateriasDTO dto) throws FachadaException{
		try{
			int pk = servicioEstudioMaterias.save(dto);
			return pk;
		}catch(ServicioException e){
			throw new FachadaException("Error en saveEstudioMateria::"+e.getMessage(), e);
		}
	}
	
	@Override
	public void saveOrUpdateEstudioMateria(ProgramaEstudiosMateriasDTO dto) throws FachadaException{
		try{
			servicioEstudioMaterias.saveOrUpdate(dto);
		}catch(ServicioException e){
			throw new FachadaException("Error en saveOrUpdateEstudioMateria::"+e.getMessage(), e);
		}
	}
	
	@Override
	public void removeEstudioMateria(ProgramaEstudiosMateriasDTO dto) throws FachadaException{
		try{
			servicioEstudioMaterias.remove(dto);
		}catch(ServicioException e){
			throw new FachadaException("Error en removeEstudioMateria::"+e.getMessage(), e);
		}
	}
	
	@Override
	public int saveAutorizacion(ProgramaEstudiosAutorizacionDTO dto) throws FachadaException{
		try{
			int pk = servicioAutorizaciones.save(dto);
			return pk;
		}catch(ServicioException e){
			throw new FachadaException("Error en saveAutorizacion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void saveOrUpdateAutorizacion(ProgramaEstudiosAutorizacionDTO dto) throws FachadaException{
		try{
			servicioAutorizaciones.saveOrUpdate(dto);
		}catch(ServicioException e){
			throw new FachadaException("Error en saveOrUpdateAutorizacion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void removeAutorizacion(ProgramaEstudiosAutorizacionDTO dto) throws FachadaException{
		try{
			servicioAutorizaciones.remove(dto);
		}catch(ServicioException e){
			throw new FachadaException("Error en removeAutorizacion:"+e.getMessage(), e);
		}
	}
	
	@Override
	public MateriaRegistroDTO findMateriaById(int idMateria) throws FachadaException{
		try{
			MateriaRegistroDTO dto = servicioMateriaRegistro.findByPK(idMateria);
			return dto;
		}catch(ServicioException e){
			throw new FachadaException("Error en findById:"+e.getMessage(), e);
		}
	}
	
	@Override
	public String getRuta() throws FachadaException{
		try {
			List<ConfDescargasDTO> lista = servicioDescargas.getAll();
			if(!CollectionUtils.isEmpty(lista)){
				ConfDescargasDTO dto = lista.get(0);
				return dto.getRuta();
			}else{
				return "./";
			}
		} catch (ServicioException e) {
			throw new FachadaException("Error en getAlumnosDatosBasicos Fachada : "
					+ e.getMessage(), e);
		}
		
	}
	
	@Override
	public List<CatGralDTO> findTiposClasifCursos() throws FachadaException{
		try{
			List<CatGralDTO> lista = servicioCatGral.findTiposClasifCurso();
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findTiposClasifCursos:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<MateriaRegistroDTO> findMaterias() throws FachadaException{
		try{
			List<MateriaRegistroDTO> lista = servicioMateriaRegistro.getAll();
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findMaterias:"+e.getMessage(), e);
		}
	}

}
