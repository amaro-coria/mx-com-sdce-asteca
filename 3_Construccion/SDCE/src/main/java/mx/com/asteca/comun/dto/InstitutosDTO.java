package mx.com.asteca.comun.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Institutos generated by hbm2java
 */
public class InstitutosDTO implements java.io.Serializable {

	private int idInstituto;
	private String nombre;
	private String razonSocial;
	private String rfc;
	private Integer idDomicilio;
	private String fax;
	private Set<InstitutoContactosDTO> institutoContactoses = new HashSet<InstitutoContactosDTO>(
			0);

	public InstitutosDTO() {
	}

	public InstitutosDTO(int idInstituto) {
		this.idInstituto = idInstituto;
	}

	public InstitutosDTO(int idInstituto, String nombre, String razonSocial,
			String rfc, Integer idDomicilio, String fax,
			Set<InstitutoContactosDTO> institutoContactoses) {
		this.idInstituto = idInstituto;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.rfc = rfc;
		this.idDomicilio = idDomicilio;
		this.fax = fax;
		this.institutoContactoses = institutoContactoses;
	}

	public int getIdInstituto() {
		return this.idInstituto;
	}

	public void setIdInstituto(int idInstituto) {
		this.idInstituto = idInstituto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getIdDomicilio() {
		return this.idDomicilio;
	}

	public void setIdDomicilio(Integer idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Set<InstitutoContactosDTO> getInstitutoContactoses() {
		return this.institutoContactoses;
	}

	public void setInstitutoContactoses(
			Set<InstitutoContactosDTO> institutoContactoses) {
		this.institutoContactoses = institutoContactoses;
	}

}