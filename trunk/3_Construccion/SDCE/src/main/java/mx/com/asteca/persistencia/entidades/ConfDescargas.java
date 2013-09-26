package mx.com.asteca.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CONF_DESCARGAS"
    ,catalog="astecadb"
)
public class ConfDescargas implements Serializable{

	private String ruta;

	/**
	 * @return the ruta
	 */
	@Id 
	@Column(name="RUTA", length=100)
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
