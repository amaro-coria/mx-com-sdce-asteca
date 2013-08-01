package mx.com.asteca.servicio.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementaci&oacute;n del Servicio
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
public abstract class BaseServiceImpl <DTO, PK extends Serializable, Mapping> implements BaseService<DTO, PK, Mapping> {

    abstract BaseDAO getDAO();
    abstract Assembler getAssembler();
    
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    
    @Transactional
    public List<DTO> getAll() throws ServicioException{
        try {
            List<Mapping> listMapping = getDAO().getAll();
            return getAssembler().getDTOListTransform(listMapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public DTO findByPK(PK id) throws ServicioException{
        try {
            Mapping mapping = (Mapping) getDAO().findByPK(id);
            return (DTO) getAssembler().getDTOTransform(mapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public void update(DTO object) throws ServicioException{
        try {
            Mapping mapping = (Mapping) getAssembler().getMappingTransform(object);
            getDAO().update(mapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public void remove(DTO object) throws ServicioException{
        try {
            Mapping mapping = (Mapping) getAssembler().getMappingTransform(object);
            getDAO().remove(mapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public PK save(DTO object) throws ServicioException{
        try {
            Mapping mapping = (Mapping) getAssembler().getMappingTransform(object);
            return (PK) getDAO().save(mapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public void saveOrUpdate(DTO object) throws ServicioException{
        try {
            Mapping mapping = (Mapping) getAssembler().getMappingTransform(object);
            getDAO().saveOrUpdate(mapping);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
        }
    }
    @Transactional
    public boolean exists(PK paramPK) throws ServicioException{
        try {
            return getDAO().exists(paramPK);
        } catch (PersistenciaException ex) {
            throw new ServicioException("Hubo error en BD");
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
