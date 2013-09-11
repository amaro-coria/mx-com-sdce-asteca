package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Notificaciones;
/**
 * 
 * @author ribarra
 *
 */
public interface NotificacionDAO extends BaseDAO<Notificaciones, Integer>{

	List<Notificaciones> findByEstado(Long estado) throws PersistenciaException;
	
	List<Notificaciones> findByTipo(Long tipo) throws PersistenciaException;
	
	List<Notificaciones> findByEstadoAndTipo(Long estado, Long tipo) throws PersistenciaException;
	
}
