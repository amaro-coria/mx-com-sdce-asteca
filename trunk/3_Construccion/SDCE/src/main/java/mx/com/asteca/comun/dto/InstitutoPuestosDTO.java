package mx.com.asteca.comun.dto;

import java.util.HashSet;
import java.util.Set;

import mx.com.asteca.persistencia.entidades.InstitutoContactos;

public class InstitutoPuestosDTO implements java.io.Serializable {

	private short idPuesto;
	private String nombre;
	private Set<InstitutoContactos> institutoContactoses = new HashSet<InstitutoContactos>(
			0);

	public InstitutoPuestosDTO() {
	}

	public InstitutoPuestosDTO(short idPuesto) {
		this.idPuesto = idPuesto;
	}

	public InstitutoPuestosDTO(short idPuesto, String nombre,
			Set<InstitutoContactos> institutoContactoses) {
		this.idPuesto = idPuesto;
		this.nombre = nombre;
		this.institutoContactoses = institutoContactoses;
	}

	public short getIdPuesto() {
		return this.idPuesto;
	}

	public void setIdPuesto(short idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<InstitutoContactos> getInstitutoContactoses() {
		return this.institutoContactoses;
	}

	public void setInstitutoContactoses(
			Set<InstitutoContactos> institutoContactoses) {
		this.institutoContactoses = institutoContactoses;
	}

}
