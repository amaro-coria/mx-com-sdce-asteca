/**
 * 
 */
package mx.com.asteca.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JAMARO
 *
 */
@Entity
@Table(name="MATERIA_REGISTRO"
    ,catalog="astecadb"
)
public class MateriasRegistros implements Serializable{
	
	private int idMateria;
	private String nombre;
	
	/**
	 * @return the idMateria
	 */
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="ID_MATERIA", unique=true, nullable=false)
	public int getIdMateria() {
		return idMateria;
	}
	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	/**
	 * @return the nombre
	 */
	@Column(name="NOMBRE", length=50)
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
