/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Certificados;
import mx.com.asteca.persistencia.entidades.Estatus;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_CERTIFICADO)
public class CertificadoAssembler extends
		Assembler<CertificadoDTO, Certificados> {

	@Override
	public CertificadoDTO getDTOTransform(Certificados mapping) {
		if (mapping == null) {
			return null;
		}
		CertificadoDTO dto = new CertificadoDTO();
		dto.setFechaCancelacion(mapping.getFechaCancelacion());
		dto.setFechaEmision(mapping.getFechaEmision());
		dto.setIdAlumno(mapping.getAlumnos().getIdAlumno());
		dto.setIdCert(mapping.getIdCert());
		dto.setIdStatus(mapping.getEstatus().getIdEstatus());
		if(mapping.getMotivoCancelacion() != null){
			dto.setMotivoCancelacion(mapping.getMotivoCancelacion());
		}else{
			dto.setMotivoCancelacion("");
		}
		String nombreAlumno = mapping.getAlumnos().getPersonas().getNombre()
				+ " " + mapping.getAlumnos().getPersonas().getApellidoP() + " "
				+ mapping.getAlumnos().getPersonas().getApellidoM();
		dto.setNombreAlumno(nombreAlumno);
		dto.setStatus(mapping.getEstatus().getDescEstatus());
		dto.setNoCertificado(mapping.getNoCertificado());
		return dto;
	}

	@Override
	public Certificados getMappingTransform(CertificadoDTO dto) {
		if (dto == null) {
			return null;
		}
		Certificados mapping = new Certificados();
		Alumnos alumnos = new Alumnos();
		alumnos.setIdAlumno(dto.getIdAlumno());
		mapping.setAlumnos(alumnos);
		Estatus estatus = new Estatus();
		estatus.setIdEstatus((short) dto.getIdStatus());
		mapping.setEstatus(estatus);
		mapping.setFechaCancelacion(dto.getFechaCancelacion());
		mapping.setFechaEmision(dto.getFechaEmision());
		mapping.setMotivoCancelacion(dto.getMotivoCancelacion());
		mapping.setNoCertificado(dto.getNoCertificado());
		return mapping;
	}

}
