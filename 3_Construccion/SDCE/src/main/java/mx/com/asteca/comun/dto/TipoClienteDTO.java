package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class TipoClienteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short idTipoCliente;
    private String nombre;
	/**
	 * @return the idTipoCliente
	 */
	public short getIdTipoCliente() {
		return idTipoCliente;
	}
	/**
	 * @param idTipoCliente the idTipoCliente to set
	 */
	public void setIdTipoCliente(short idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoCliente;
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
		TipoClienteDTO other = (TipoClienteDTO) obj;
		if (idTipoCliente != other.idTipoCliente)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoClienteDTO [idTipoCliente=" + idTipoCliente + ", nombre="
				+ nombre + "]";
	}

}
