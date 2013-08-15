package mx.com.asteca.comun.dto;

import java.io.Serializable;
/**
 * 
 * @author Javier
 *
 */
public class CatGralDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cveRegistro;
	private String dsc;
	private String estatus;
	private Short activo;
	private int idCatGral;
	private int idTipoCatGral;
	
	
	public String getCveRegistro() {
		return cveRegistro;
	}
	public void setCveRegistro(String cveRegistro) {
		this.cveRegistro = cveRegistro;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Short getActivo() {
		return activo;
	}
	public void setActivo(Short activo) {
		this.activo = activo;
	}
	public int getIdCatGral() {
		return idCatGral;
	}
	public void setIdCatGral(int idCatGral) {
		this.idCatGral = idCatGral;
	}
	public int getIdTipoCatGral() {
		return idTipoCatGral;
	}
	public void setIdTipoCatGral(int idTipoCatGral) {
		this.idTipoCatGral = idTipoCatGral;
	}
}
