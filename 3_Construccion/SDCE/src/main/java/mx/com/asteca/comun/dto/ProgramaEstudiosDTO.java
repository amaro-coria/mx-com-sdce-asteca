/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.com.asteca.util.FechaUtil;

/**
 * @author JAMARO
 *
 */
public class ProgramaEstudiosDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProgEstudio;
     private int idTipo;
     private String tipo;
     private String clave;
     private String dsc;
     private String noAut;
     private Date fechaAut;
     private String fechaAutString;
     private int horasPractica;
     private int horasTeoria;
     private List<ProgramaEstudiosMateriasDTO> listMaterias;
     private List<ProgramaEstudiosAutorizacionDTO> listAutorizaciones;
	/**
	 * @return the idProgEstudio
	 */
	public int getIdProgEstudio() {
		return idProgEstudio;
	}
	/**
	 * @param idProgEstudio the idProgEstudio to set
	 */
	public void setIdProgEstudio(int idProgEstudio) {
		this.idProgEstudio = idProgEstudio;
	}
	/**
	 * @return the idTipo
	 */
	public int getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the dsc
	 */
	public String getDsc() {
		return dsc;
	}
	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	/**
	 * @return the noAut
	 */
	public String getNoAut() {
		return noAut;
	}
	/**
	 * @param noAut the noAut to set
	 */
	public void setNoAut(String noAut) {
		this.noAut = noAut;
	}
	/**
	 * @return the fechaAut
	 */
	public Date getFechaAut() {
		return fechaAut;
	}
	/**
	 * @param fechaAut the fechaAut to set
	 */
	public void setFechaAut(Date fechaAut) {
		this.fechaAut = fechaAut;
	}
	/**
	 * @return the fechaAutString
	 */
	public String getFechaAutString() {
		if(fechaAut == null){
			return "";
		}else{
			fechaAutString = FechaUtil.getInstance().parseDateMM_dd_yy(fechaAut);
		}
		return fechaAutString;
	}
	/**
	 * @param fechaAutString the fechaAutString to set
	 */
	public void setFechaAutString(String fechaAutString) {
		this.fechaAutString = fechaAutString;
	}
	/**
	 * @return the horasPractica
	 */
	public int getHorasPractica() {
		return horasPractica;
	}
	/**
	 * @param horasPractica the horasPractica to set
	 */
	public void setHorasPractica(int horasPractica) {
		this.horasPractica = horasPractica;
	}
	/**
	 * @return the horasTeoria
	 */
	public int getHorasTeoria() {
		return horasTeoria;
	}
	/**
	 * @param horasTeoria the horasTeoria to set
	 */
	public void setHorasTeoria(int horasTeoria) {
		this.horasTeoria = horasTeoria;
	}
	/**
	 * @return the listMaterias
	 */
	public List<ProgramaEstudiosMateriasDTO> getListMaterias() {
		return listMaterias;
	}
	/**
	 * @param listMaterias the listMaterias to set
	 */
	public void setListMaterias(List<ProgramaEstudiosMateriasDTO> listMaterias) {
		this.listMaterias = listMaterias;
	}
	/**
	 * @return the listAutorizaciones
	 */
	public List<ProgramaEstudiosAutorizacionDTO> getListAutorizaciones() {
		return listAutorizaciones;
	}
	/**
	 * @param listAutorizaciones the listAutorizaciones to set
	 */
	public void setListAutorizaciones(List<ProgramaEstudiosAutorizacionDTO> listAutorizaciones) {
		this.listAutorizaciones = listAutorizaciones;
	}
     
	
}
