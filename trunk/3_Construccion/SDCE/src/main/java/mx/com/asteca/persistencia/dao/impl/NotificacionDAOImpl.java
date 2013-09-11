package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.NotificacionDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class NotificacionDAOImpl extends BaseDAOImpl<Notificaciones, Integer> implements NotificacionDAO{

	@Override
	public List<Notificaciones> findByEstado(Long estado)
			throws PersistenciaException {

		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Notificaciones.class);
			criteria.add(Restrictions.eq("notificacionesEstados.idNotifEdo", estado.shortValue()));
			List<Notificaciones> listaNotificaciones = criteria.list();
			return listaNotificaciones;
		} catch (Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD", ex);
		}
	}

	@Override
	public List<Notificaciones> findByTipo(Long tipo)
			throws PersistenciaException {

		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Notificaciones.class);
			criteria.add(Restrictions.eq("notificacionesTipos.idNotifTipo", tipo.shortValue()));
			List<Notificaciones> listaNotificaciones = criteria.list();
			return listaNotificaciones;
		} catch (Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD", ex);
		}
	}

	@Override
	public List<Notificaciones> findByEstadoAndTipo(Long estado, Long tipo)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Notificaciones.class);
			criteria.add(Restrictions.eq("notificacionesEstados.idNotifEdo", estado.shortValue()))
				.add(Restrictions.eq("notificacionesTipos.idNotifTipo", tipo.shortValue()));
			List<Notificaciones> listaNotificaciones = criteria.list();
			return listaNotificaciones;
		} catch (Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD", ex);
		}
	}
	
}
