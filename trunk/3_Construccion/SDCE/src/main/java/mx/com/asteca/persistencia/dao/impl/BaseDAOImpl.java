package mx.com.asteca.persistencia.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JAMARO
 */
public class BaseDAOImpl<T, PK extends Serializable> implements BaseDAO<T, PK>  {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    @SuppressWarnings("unchecked")
	public List<T> getAll() throws PersistenciaException{
       List<T> entities = new ArrayList<T>();
       try{
           entities = getSessionFactory().getCurrentSession().createCriteria(this.getPersistentClass()).list();
       }catch(Exception e){
           LOGGER.error(e.getMessage(), e);
       }
       return entities;
    }
    
    @SuppressWarnings("unchecked")
	public T findByPK(PK id) throws PersistenciaException{
        try{
            return (T) getSessionFactory().getCurrentSession().get(this.getPersistentClass(), id);
        }catch(Exception e){
            throw new PersistenciaException("Excepción en BD", e);
        }
    }

    public void update(T object) throws PersistenciaException{
        try{
            getSessionFactory().getCurrentSession().update(object);
        }catch(Exception e){
            throw new PersistenciaException("Excepción en BD", e);
        }
    }

    public void remove(T object) throws PersistenciaException{
        try{
            getSessionFactory().getCurrentSession().delete(object);
            LOGGER.debug("Se ejecuto el borrado del objeto");
        }catch(Exception e){
            LOGGER.debug("Cayo en exception al borrar el objeto", e);
            throw new PersistenciaException("Excepción en BD", e);
        }
    }

    @SuppressWarnings("unchecked")
	public PK save(T object) throws PersistenciaException{
        try{
            return (PK) getSessionFactory().getCurrentSession().save(object);
        }catch(Exception e){
            throw new PersistenciaException("Excepción en BD", e);
        }
    }

    public void saveOrUpdate(T object) throws PersistenciaException{
        try{
            getSessionFactory().getCurrentSession().saveOrUpdate(object);
        }catch(Exception e){
            throw new PersistenciaException("Excepción en BD", e);
        }
    }
    
    public boolean exists(PK paramPK) throws PersistenciaException{
        try{
            Object entity = this.findByPK(paramPK);
            return (entity != null);
        }catch(Exception e){
            throw new PersistenciaException("Excepción en BD", e);
        }
    }
 
     /**
     * Método para obtener la clase que persiste el DAO
     * 
     * @return <T> clase a persistir.
     */
    @SuppressWarnings("unchecked")
    private Class<T> getPersistentClass() {

        Class<T> type = null;

        Class<?> clazz = getClass();

        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
            clazz = clazz.getSuperclass();
        }

        type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("type DO :: " + type);
        }
        return type;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
}
