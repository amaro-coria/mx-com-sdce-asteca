package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class ConfDescargasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ruta;

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
