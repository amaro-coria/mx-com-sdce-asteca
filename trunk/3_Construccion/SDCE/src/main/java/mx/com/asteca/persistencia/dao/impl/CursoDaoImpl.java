package mx.com.asteca.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CursoDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.AlumnosCursos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.CursosMaterias;
import mx.com.asteca.persistencia.entidades.Materias;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDaoImpl extends BaseDAOImpl<Cursos, Integer>  implements CursoDAO{

	
	@Override
	public Cursos findByReferencia(String referencia) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Cursos.class);
			criteria.add(Restrictions.eq("referencia", referencia));
			Cursos mapping = (Cursos) criteria.uniqueResult();
			return mapping;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en findByReferencia DAO:"
					+ ex.getMessage(), ex);
		}
	}
	
	@Override
	public void deleteAlumnoCurso(Alumnos alumno, Cursos curso) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(AlumnosCursos.class);
			criteria.add(Restrictions.eq("alumnos", alumno));
			criteria.add(Restrictions.eq("cursos", curso));
			AlumnosCursos result = (AlumnosCursos) criteria.uniqueResult();
			getSessionFactory().getCurrentSession().delete(result);
		} catch (Exception ex) {
			throw new PersistenciaException("Error en deleteAlumnoCurso DAO:"
					+ ex.getMessage(), ex);
		}
	}
	
	@Override
	public int saveAlumnoCurso(Alumnos alumno, Cursos curso) throws PersistenciaException{
		try{
			AlumnosCursos ac = new AlumnosCursos();
			ac.setAlumnos(alumno);
			ac.setCursos(curso);
			Integer pk = (Integer) getSessionFactory().getCurrentSession().save(ac);
			return pk;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en saveAlumnoCurso DAO:"
					+ ex.getMessage(), ex);
		}
	}
	
	@Override
	public void deleteMateriaCurso(Materias materias, Cursos cursos) throws PersistenciaException{
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CursosMaterias.class);
			criteria.add(Restrictions.eq("cursos", cursos));
			criteria.add(Restrictions.eq("materias", materias));
			CursosMaterias cm = (CursosMaterias) criteria.uniqueResult();
			getSessionFactory().getCurrentSession().delete(cm);
		} catch (Exception ex) {
			throw new PersistenciaException("Error en deleteAlumnoCurso DAO:"
					+ ex.getMessage(), ex);
		}
	}
	
	@Override
	public int saveMateriaCurso(Materias materia, Cursos curso) throws PersistenciaException{
		try{
			CursosMaterias cursosMaterias = new CursosMaterias();
				cursosMaterias.setCursos(curso);
				cursosMaterias.setMaterias(materia);
				Integer pk = (Integer) this.getSessionFactory().getCurrentSession().save(cursosMaterias);
				return pk;
		} catch (Exception ex) {
			throw new PersistenciaException("Error en saveMateriaCurso DAO:"
					+ ex.getMessage(), ex);
		}
	}
	
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
