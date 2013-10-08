package mx.com.asteca.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AulaDAO;
import mx.com.asteca.persistencia.entidades.Aulas;
import mx.com.asteca.persistencia.entidades.Materias;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
@Repository
public class AulaDAOImpl extends BaseDAOImpl<Aulas, Integer> implements AulaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Aulas> findAulasDisponibles(Date timeStampInicial,
			Date timeStampFinal, int idSede) throws PersistenciaException {
		try {
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(timeStampInicial);
			Calendar calendar2 = new GregorianCalendar();
			calendar2.setTime(timeStampInicial);
			int horaInicial = calendar1.get(Calendar.HOUR_OF_DAY);
			int horaFinal = calendar2.get(Calendar.HOUR_OF_DAY);

			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Materias.class);
			criteria.createCriteria("aulas").add(Restrictions.eq("catGral.idCatGral", idSede));
			criteria.add(Restrictions.between("fhInicial", timeStampInicial,
					timeStampFinal));
			List<Materias> result1 = criteria.list();
			Criteria criteria2 = getSessionFactory().getCurrentSession()
					.createCriteria(Materias.class);
			criteria2.createCriteria("aulas").add(Restrictions.eq("catGral.idCatGral", idSede));
			criteria2.add(Restrictions.between("fhFinal", timeStampInicial,
					timeStampFinal));
			List<Materias> result2 = criteria2.list();

			Set<Aulas> setAulasListaNegra = new HashSet<Aulas>();

			List<Materias> lista = new ArrayList<Materias>();
			if(!CollectionUtils.isEmpty(result1)){
				for(Materias mat : result1){
					lista.add(mat);
				}
			}
			if(!CollectionUtils.isEmpty(result2)){
				for(Materias mat : result2){
					lista.add(mat);
				}
			}
			
			if (!CollectionUtils.isEmpty(lista)) {
				for (Materias materia : lista) {
					Calendar temp1 = new GregorianCalendar();
					temp1.setTime(materia.getFhInicial());
					Calendar temp2 = new GregorianCalendar();
					temp2.setTime(materia.getFhFinal());
					int horaTempInicial = temp1.get(Calendar.HOUR_OF_DAY);
					int horaTempFinal = temp2.get(Calendar.HOUR_OF_DAY);
					if ((horaInicial >= horaTempInicial && horaInicial <= horaTempFinal)
							|| (horaFinal >= horaTempInicial && horaFinal <= horaTempFinal)) {
						setAulasListaNegra.add(materia.getAulas());
					}
				}
			}
			
			Criteria criteriaAulas = getSessionFactory().getCurrentSession().createCriteria(Aulas.class);
			List<Aulas> listaAulas = criteriaAulas.list();
			List<Aulas> listaResultado = new ArrayList<Aulas>();
			if(!CollectionUtils.isEmpty(listaAulas)){
				for(Aulas aula : listaAulas){
					if(!setAulasListaNegra.contains(aula)){
						listaResultado.add(aula);
					}
				}
			}
			
			return listaResultado;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a la bd para Aulas:sedes :: "
							+ ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.asteca.persistencia.dao.AulaDAO#findBySede(java.lang.String)
	 */
	@Override
	public List<Aulas> findBySede(String sede) throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Aulas.class);
			criteria.createCriteria("catGral").add(
					Restrictions.ilike("dsc", sede));
			List<Aulas> listaAulas = criteria.list();
			return listaAulas;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a la bd para Aulas:sedes :: "
							+ ex.getMessage(), ex);
		}
	}
}
