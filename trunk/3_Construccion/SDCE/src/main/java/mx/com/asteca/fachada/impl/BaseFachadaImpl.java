package mx.com.asteca.fachada.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import mx.com.asteca.fachada.BaseFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ServicioException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseFachadaImpl<DTO, PK extends Serializable> implements BaseFachada<DTO, PK> {
	
	abstract BaseService getBaseService();
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BaseFachadaImpl.class);
	
	 @Transactional
	    public List<DTO> getAll() throws FachadaException{
	        try {
	            List<DTO> listaDTO = getBaseService().getAll();
	            return listaDTO;
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public DTO findByPK(PK id) throws FachadaException{
	        try {
	            DTO dto = (DTO) getBaseService().findByPK(id);
	            return dto;
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public void update(DTO object) throws FachadaException{
	        try {
	        	getBaseService().update(object);
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public void remove(DTO object) throws FachadaException{
	        try {
	        	getBaseService().remove(object);
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public PK save(DTO object) throws FachadaException{
	        try {
	            PK saved = (PK) getBaseService().save(object);
	            return saved;
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public void saveOrUpdate(DTO object) throws FachadaException{
	        try {
	        	getBaseService().saveOrUpdate(object);
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    @Transactional
	    public boolean exists(PK paramPK) throws FachadaException{
	        try {
	            return getBaseService().exists(paramPK);
	        } catch (ServicioException ex) {
	            throw new FachadaException("Hubo error en servicio");
	        }
	    }
	    
	      /**
	     * Método para obtener la clase que persiste el DAO
	     * 
	     * @return <T> clase a persistir.
	     */
	    @SuppressWarnings("unchecked")
	    private Class<DTO> getInvokingclass() {

	        Class<DTO> type = null;

	        Class<?> clazz = getClass();

	        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
	            clazz = clazz.getSuperclass();
	        }

	        type = (Class<DTO>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];

	        if (LOGGER.isDebugEnabled()) {
	            LOGGER.debug("type BS Class :: " + type);
	        }
	        return type;
	    }
}
