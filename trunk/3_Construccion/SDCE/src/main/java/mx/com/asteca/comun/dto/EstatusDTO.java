package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class EstatusDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short idEstatus;
     private String descEstatus;
     private String obsEstatus;
	/**
	 * @return the idEstatus
	 */
	public short getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(short idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}
	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}
	/**
	 * @return the obsEstatus
	 */
	public String getObsEstatus() {
		return obsEstatus;
	}
	/**
	 * @param obsEstatus the obsEstatus to set
	 */
	public void setObsEstatus(String obsEstatus) {
		this.obsEstatus = obsEstatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstatus;
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
		EstatusDTO other = (EstatusDTO) obj;
		if (idEstatus != other.idEstatus)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstatusDTO [idEstatus=" + idEstatus + ", descEstatus="
				+ descEstatus + ", obsEstatus=" + obsEstatus + "]";
	}
	
	
     
}
