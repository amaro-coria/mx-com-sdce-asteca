package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ClienteDAO;
import mx.com.asteca.persistencia.entidades.Clientes;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ClientesDAOImpl extends BaseDAOImpl<Clientes, Integer> implements
		ClienteDAO {

	@Override
	public List<Clientes> findByClave(String clave)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Clientes.class);
			criteria.add(Restrictions.eq("clave", clave));
			List<Clientes> listaClientes = criteria.list();
			return listaClientes;
		} catch (Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD",
					ex);
		}

	}

	@Override
	public List<Clientes> findByClaveAndNombre(String clave, String nombre)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Clientes.class);
			criteria.add(Restrictions.eq("clave", clave));
			criteria.add(Restrictions.eq("nombre", nombre));
			List<Clientes> listaClientes = criteria.list();
			return listaClientes;
		} catch (Exception ex) {
			throw new PersistenciaException("Error obteniendo la lista en BD",
					ex);
		}

	}

}
