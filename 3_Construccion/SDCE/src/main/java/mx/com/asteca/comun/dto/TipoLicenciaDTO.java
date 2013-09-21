/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;

/**
 * @author JAMARO
 *
 */
public class TipoLicenciaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private short idTipoLic;
    private String descTipoLic;
	/**
	 * @return the idTipoLic
	 */
	public short getIdTipoLic() {
		return idTipoLic;
	}
	/**
	 * @param idTipoLic the idTipoLic to set
	 */
	public void setIdTipoLic(short idTipoLic) {
		this.idTipoLic = idTipoLic;
	}
	/**
	 * @return the descTipoLic
	 */
	public String getDescTipoLic() {
		return descTipoLic;
	}
	/**
	 * @param descTipoLic the descTipoLic to set
	 */
	public void setDescTipoLic(String descTipoLic) {
		this.descTipoLic = descTipoLic;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoLic;
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
		TipoLicenciaDTO other = (TipoLicenciaDTO) obj;
		if (idTipoLic != other.idTipoLic)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoLicenciaDTO [idTipoLic=" + idTipoLic + ", descTipoLic="
				+ descTipoLic + "]";
	}
	
	
}
