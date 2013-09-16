package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonaDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
     private String usuario;
     private String password;
     
	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the fechaNac
	 */
	public Date getFechaNac() {
		return fechaNac;
	}
	/**
	 * @param fechaNac the fechaNac to set
	 */
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	/**
	 * @return the lugarNac
	 */
	public String getLugarNac() {
		return lugarNac;
	}
	/**
	 * @param lugarNac the lugarNac to set
	 */
	public void setLugarNac(String lugarNac) {
		this.lugarNac = lugarNac;
	}
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the ife
	 */
	public String getIfe() {
		return ife;
	}
	/**
	 * @param ife the ife to set
	 */
	public void setIfe(String ife) {
		this.ife = ife;
	}
	/**
	 * @return the pasaporte
	 */
	public String getPasaporte() {
		return pasaporte;
	}
	/**
	 * @param pasaporte the pasaporte to set
	 */
	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the apellidoP
	 */
	public String getApellidoP() {
		return apellidoP;
	}
	/**
	 * @param apellidoP the apellidoP to set
	 */
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}
	/**
	 * @return the apellidoM
	 */
	public String getApellidoM() {
		return apellidoM;
	}
	/**
	 * @param apellidoM the apellidoM to set
	 */
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersona;
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
		PersonaDTO other = (PersonaDTO) obj;
		if (idPersona != other.idPersona)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PersonaDTO [idPersona=" + idPersona + ", fechaNac=" + fechaNac
				+ ", lugarNac=" + lugarNac + ", curp=" + curp + ", rfc=" + rfc
				+ ", ife=" + ife + ", pasaporte=" + pasaporte + ", email="
				+ email + ", nombre=" + nombre + ", apellidoP=" + apellidoP
				+ ", apellidoM=" + apellidoM + ", usuario=" + usuario
				+ ", password=" + password + "]";
	}
	     
}
