package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class AlumnoDTO implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAlumno;
	private int idEstatus;
	private String matricula;
	private String nombre;
	private String estatus;
	private PersonaDTO dtoPersona;
	private DomicilioDTO dtoDomicilio;
	private FamiliaDTO dtoFamilia;
	private List<CapacidadDTO> listaCapacidades;
	private List<EstudioDTO> listaEstudios;
	private List<ReferenciaDTO> listaReferencias;
	private List<DocumentoDTO> listaDocumentos;
	
	public AlumnoDTO(){
		dtoPersona = new PersonaDTO();
		dtoDomicilio = new DomicilioDTO();
		dtoFamilia = new FamiliaDTO();
	}
	
	/**
	 * @return the idAlumno
	 */
	public int getIdAlumno() {
		return idAlumno;
	}
	/**
	 * @param idAlumno the idAlumno to set
	 */
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAlumno;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoDTO other = (AlumnoDTO) obj;
		if (idAlumno != other.idAlumno)
			return false;
		return true;
	}
	public PersonaDTO getDtoPersona() {
		return dtoPersona;
	}
	public void setDtoPersona(PersonaDTO dtoPersona) {
		this.dtoPersona = dtoPersona;
	}
	public DomicilioDTO getDtoDomicilio() {
		return dtoDomicilio;
	}
	public void setDtoDomicilio(DomicilioDTO dtoDomicilio) {
		this.dtoDomicilio = dtoDomicilio;
	}
	public FamiliaDTO getDtoFamilia() {
		return dtoFamilia;
	}
	public void setDtoFamilia(FamiliaDTO dtoFamilia) {
		this.dtoFamilia = dtoFamilia;
	}
	public List<CapacidadDTO> getListaCapacidades() {
		return listaCapacidades;
	}
	public void setListaCapacidades(List<CapacidadDTO> listaCapacidades) {
		this.listaCapacidades = listaCapacidades;
	}
	public List<EstudioDTO> getListaEstudiosOrdenada(){
		if(!CollectionUtils.isEmpty(listaEstudios)){
			Collections.sort(listaEstudios);
		}
		return listaEstudios;
	}
	/**
	 * @return the listaEstudios
	 */
	public List<EstudioDTO> getListaEstudios() {
		return listaEstudios;
	}
	/**
	 * @param listaEstudios the listaEstudios to set
	 */
	public void setListaEstudios(List<EstudioDTO> listaEstudios) {
		this.listaEstudios = listaEstudios;
	}
	/**
	 * @return the listaReferencias
	 */
	public List<ReferenciaDTO> getListaReferencias() {
		return listaReferencias;
	}
	/**
	 * @param listaReferencias the listaReferencias to set
	 */
	public void setListaReferencias(List<ReferenciaDTO> listaReferencias) {
		this.listaReferencias = listaReferencias;
	}
	/**
	 * @return the listaDocumentos
	 */
	public List<DocumentoDTO> getListaDocumentos() {
		return listaDocumentos;
	}
	/**
	 * @param listaDocumentos the listaDocumentos to set
	 */
	public void setListaDocumentos(List<DocumentoDTO> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	/**
	 * @return the idEstatus
	 */
	public int getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	
}
