package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class TipoCatGralDTO implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short idCatGral;
     private String tipo;
     
     
	/**
	 * @return the idCatGral
	 */
	public short getIdCatGral() {
		return idCatGral;
	}
	/**
	 * @param idCatGral the idCatGral to set
	 */
	public void setIdCatGral(short idCatGral) {
		this.idCatGral = idCatGral;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCatGral;
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
		TipoCatGralDTO other = (TipoCatGralDTO) obj;
		if (idCatGral != other.idCatGral)
			return false;
		return true;
	}
	

	
}
