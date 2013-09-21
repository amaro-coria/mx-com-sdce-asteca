/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CatGralDAO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.util.CatGralTipo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Javier
 * 
 */
/**
 * @author JAMARO
 *
 */
@Repository
public class CatGralDAOImpl extends BaseDAOImpl<CatGral, Integer> implements
		CatGralDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.CatGralDAO#findByCve(java.lang.String)
	 */
	@Override
	public List<CatGral> findByCve(String cve) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException("Error accediendo a findByCve:"
					+ ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.CatGralDAO#findByCveAndDsc(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public List<CatGral> findByCveAndDsc(String cve, String dsc)
			throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a findByCveAndDsc:" + ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.persistencia.dao.CatGralDAO#findByCveDscAndEstatus(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatGral> findByCveDscAndEstatus(String cve, String dsc,
			String estatus) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(CatGral.class);
			criteria.add(Restrictions.like("cveRegistro", cve));
			criteria.add(Restrictions.like("dsc", dsc));
			criteria.add(Restrictions.like("estatus", estatus));
			List<CatGral> lista = criteria.list();
			return lista;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a findByCveAndDsc:" + ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findTiposEquipo()
	 */
	@Override
	public List<CatGral> findTiposEquipo() throws PersistenciaException {
		try {
			List<CatGral> results = findTipoCatGral(CatGralTipo.TIPO_EQUIPO);
			return results;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de equipo:" + ex.getMessage(),
					ex);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findSedes()
	 */
	@Override
	public List<CatGral> findSedes() throws PersistenciaException {
		List<CatGral> lista;
		try {
			lista = findTipoCatGral(CatGralTipo.TIPO_SEDE);
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de sede:" + e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findAreas()
	 */
	@Override
	public List<CatGral> findAreas() throws PersistenciaException {
		List<CatGral> lista;
		try {
			lista = findTipoCatGral(CatGralTipo.TIPO_AREA);
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de area:" + e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findPaises()
	 */
	@Override
	public List<CatGral> findPaises() throws PersistenciaException {
		List<CatGral> lista;
		try {
			lista = findTipoCatGral(CatGralTipo.TIPO_PAIS);
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de paises:" + e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findClasifCurso()
	 */
	@Override
	public List<CatGral> findClasifCurso() throws PersistenciaException {
		List<CatGral> lista;
		try {
			lista = findTipoCatGral(CatGralTipo.TIPO_CLASIFICACION_CURSO);
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de clsif curso:"
							+ e.getMessage(), e);
		}

	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CatGralDAO#findMaterias()
	 */
	@Override
	public List<CatGral> findMaterias() throws PersistenciaException {
		List<CatGral> lista;
		try {
			lista = findTipoCatGral(CatGralTipo.TIPO_MATERIAS);
			return lista;
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error obteniendo los tipos de materias:" + e.getMessage(),
					e);
		}

	}

	/**
	 * Obtiene la lista de CatGral de acuerdo al tipo además de los tipoOTro (idTipo = 7)
	 * @param catGralTipo
	 * @return
	 * @throws Exception
	 */
	private List<CatGral> findTipoCatGral(CatGralTipo catGralTipo)
			throws Exception {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CatGral.class);
		Object[] values = { catGralTipo.getTipo(),
				CatGralTipo.TIPO_OTRO.getTipo() };
		criteria.add(Restrictions.in("tiposCatGral.idCatGral", values));
		List<CatGral> results = criteria.list();
		return results;
	}

}
