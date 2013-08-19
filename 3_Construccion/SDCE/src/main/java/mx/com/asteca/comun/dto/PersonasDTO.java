package mx.com.asteca.comun.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.com.asteca.persistencia.entidades.Administrativos;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.InstitutoContactos;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.Referencias;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;

public class PersonasDTO {
	private int idPersona;
	private Date fechaNac;
	private String lugarNac;
	private String curp;
	private String rfc;
	private String ife;
	private String pasaporte;
	private String email;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private Set<Instructores> instructoreses = new HashSet<Instructores>(0);
	private Set<RolesModUsuarios> rolesModUsuarioses = new HashSet<RolesModUsuarios>(
			0);
	private Set<Alumnos> alumnoses = new HashSet<Alumnos>(0);
	private Set<Referencias> referenciases = new HashSet<Referencias>(0);
	private Set<Administrativos> administrativoses = new HashSet<Administrativos>(
			0);
	private Set<InstitutoContactos> institutoContactoses = new HashSet<InstitutoContactos>(
			0);

	public PersonasDTO() {
	}

	public PersonasDTO(int idPersona) {
		this.idPersona = idPersona;
	}

	public PersonasDTO(int idPersona, Date fechaNac, String lugarNac,
			String curp, String rfc, String ife, String pasaporte,
			String email, String nombre, String apellidoP, String apellidoM,
			Set<Instructores> instructoreses,
			Set<RolesModUsuarios> rolesModUsuarioses, Set<Alumnos> alumnoses,
			Set<Referencias> referenciases,
			Set<Administrativos> administrativoses,
			Set<InstitutoContactos> institutoContactoses) {
		this.idPersona = idPersona;
		this.fechaNac = fechaNac;
		this.lugarNac = lugarNac;
		this.curp = curp;
		this.rfc = rfc;
		this.ife = ife;
		this.pasaporte = pasaporte;
		this.email = email;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.instructoreses = instructoreses;
		this.rolesModUsuarioses = rolesModUsuarioses;
		this.alumnoses = alumnoses;
		this.referenciases = referenciases;
		this.administrativoses = administrativoses;
		this.institutoContactoses = institutoContactoses;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getLugarNac() {
		return this.lugarNac;
	}

	public void setLugarNac(String lugarNac) {
		this.lugarNac = lugarNac;
	}

	public String getCurp() {
		return this.curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getIfe() {
		return this.ife;
	}

	public void setIfe(String ife) {
		this.ife = ife;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return this.apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return this.apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public Set<Instructores> getInstructoreses() {
		return this.instructoreses;
	}

	public void setInstructoreses(Set<Instructores> instructoreses) {
		this.instructoreses = instructoreses;
	}

	public Set<RolesModUsuarios> getRolesModUsuarioses() {
		return this.rolesModUsuarioses;
	}

	public void setRolesModUsuarioses(Set<RolesModUsuarios> rolesModUsuarioses) {
		this.rolesModUsuarioses = rolesModUsuarioses;
	}

	public Set<Alumnos> getAlumnoses() {
		return this.alumnoses;
	}

	public void setAlumnoses(Set<Alumnos> alumnoses) {
		this.alumnoses = alumnoses;
	}

	public Set<Referencias> getReferenciases() {
		return this.referenciases;
	}

	public void setReferenciases(Set<Referencias> referenciases) {
		this.referenciases = referenciases;
	}

	public Set<Administrativos> getAdministrativoses() {
		return this.administrativoses;
	}

	public void setAdministrativoses(Set<Administrativos> administrativoses) {
		this.administrativoses = administrativoses;
	}

	public Set<InstitutoContactos> getInstitutoContactoses() {
		return this.institutoContactoses;
	}

	public void setInstitutoContactoses(
			Set<InstitutoContactos> institutoContactoses) {
		this.institutoContactoses = institutoContactoses;
	}

}
