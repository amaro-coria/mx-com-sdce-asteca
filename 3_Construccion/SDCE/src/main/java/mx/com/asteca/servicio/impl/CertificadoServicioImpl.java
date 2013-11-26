/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CertificadoDAO;
import mx.com.asteca.persistencia.entidades.Certificados;
import mx.com.asteca.servicio.CertificadoServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JAMARO
 *
 */
@Service
public class CertificadoServicioImpl extends
		BaseServiceImpl<CertificadoDTO, Integer, Certificados> implements
		CertificadoServicio {

	@Autowired
	private CertificadoDAO daoCert;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CERTIFICADO)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoCert;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}
	
	@Override
	@Transactional
	public void cancelaCertificado(int idCert, String motivo) throws ServicioException{
		try{
			daoCert.cancelaCertificado(idCert, motivo);
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNoCertAlumnoEstatus: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByNoCertAlumnoEstatus(String noCert, int idAlumno, short idEstatus)throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByNoCertAlumnoEstatus(noCert, idAlumno, idEstatus);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNoCertAlumnoEstatus: "+e.getMessage(), e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByNoCertAlumno(String noCert, int idAlumno) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByNoCertAlumno(noCert, idAlumno);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNoCertAlumno: "+e.getMessage(), e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByNoCertEstatus(String noCert, short idEstatus) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByNoCertEstatus(noCert, idEstatus);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNoCertEstatus: "+e.getMessage(), e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByAlumnoEstatus(int idAlumno, short idEstatus) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByAlumnoEstatus(idAlumno, idEstatus);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByAlumnoEstatus: "+e.getMessage(), e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByAlumno(int idAlumno) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByAlumno(idAlumno);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByAlumno: "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByNoCert(String noCert) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByNoCert(noCert);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByNoCert: "+e.getMessage(), e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public List<CertificadoDTO> findByEstatus(short idEstatus) throws ServicioException{
		try{
			List<Certificados> mappingList = daoCert.findByEstatus(idEstatus);
			List<CertificadoDTO> dtoList = assembler.getDTOListTransform(mappingList);
			return dtoList;
		}catch(PersistenciaException e){
			throw new ServicioException("Error en findByEstatus: "+e.getMessage(), e);
		}
	}

}
