/**
 * 
 */
package mx.com.asteca.persistencia.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	 private Set<Materias> materiases = new HashSet<Materias>(0);
     private Set<ProgramaEstMaterias> programaEstMateriases = new HashSet<ProgramaEstMaterias>(0);
     private Set<InstructoresMaterias> instructoresMateriases = new HashSet<InstructoresMaterias>(0);
	
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

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materiasRegistros")
    public Set<Materias> getMateriases() {
        return this.materiases;
    }
    
    public void setMateriases(Set<Materias> materiases) {
        this.materiases = materiases;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materias")
    public Set<ProgramaEstMaterias> getProgramaEstMateriases() {
        return this.programaEstMateriases;
    }
    
    public void setProgramaEstMateriases(Set<ProgramaEstMaterias> programaEstMateriases) {
        this.programaEstMateriases = programaEstMateriases;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materias")
    public Set<InstructoresMaterias> getInstructoresMateriases() {
        return this.instructoresMateriases;
    }
    
    public void setInstructoresMateriases(Set<InstructoresMaterias> instructoresMateriases) {
        this.instructoresMateriases = instructoresMateriases;
    }

    
}
