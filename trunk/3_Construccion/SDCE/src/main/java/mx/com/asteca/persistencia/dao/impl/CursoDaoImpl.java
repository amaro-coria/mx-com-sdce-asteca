package mx.com.asteca.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CursoDAO;
import mx.com.asteca.persistencia.entidades.Cursos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDaoImpl extends BaseDAOImpl<Cursos, Integer>  implements CursoDAO{

	@Override
	public List<Cursos> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fechaFin)
			throws PersistenciaException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cursos.class);
			criteria.add(Restrictions.between("fechaIni", fechaIni, fechaFin));
			
			if(instructor != null && instructor > 0){
				criteria.add(Restrictions.eq("instructor.idInsturctor", fechaIni));
			}
			
			if(sede != null && sede > 0){
				criteria.add(Restrictions.eq("catGral.idCatGral", sede));
			}
			
			if(area != null && area > 0){
				criteria.add(Restrictions.eq("catGral.idCatGral", area));
			}
			
			List<Cursos> list = criteria.list();
			return list;
			
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByInstructorSedeAndArea DAO:"
					+ ex.getMessage(), ex);
		}
	}

}
