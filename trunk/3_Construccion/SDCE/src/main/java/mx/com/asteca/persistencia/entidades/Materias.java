package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Materias generated by hbm2java
 */
@Entity
@Table(name="MATERIAS"
    ,catalog="astecadb"
)
public class Materias  implements java.io.Serializable {


     private int idMateria;
     private Instructores instructores;
     private CatGral catGral;
     private Aulas aulas;
     private Date fhInicial;
     private Date fhFinal;
     private Set<CalifCursos> califCursoses = new HashSet<CalifCursos>(0);
     private Set<ProgramaEstMaterias> programaEstMateriases = new HashSet<ProgramaEstMaterias>(0);
     private Set<CursosMaterias> cursosMateriases = new HashSet<CursosMaterias>(0);
     private MateriasRegistros materiasRegistros;

    public Materias() {
    }

	
    public Materias(int idMateria) {
        this.idMateria = idMateria;
    }
    public Materias(int idMateria, Instructores instructores, CatGral catGral, Aulas aulas,  Date fhInicial, Date fhFinal, Set<CalifCursos> califCursoses, Set<ProgramaEstMaterias> programaEstMateriases, Set<CursosMaterias> cursosMateriases) {
       this.idMateria = idMateria;
       this.instructores = instructores;
       this.catGral = catGral;
       this.aulas = aulas;
       this.fhInicial = fhInicial;
       this.fhFinal = fhFinal;
       this.califCursoses = califCursoses;
       this.programaEstMateriases = programaEstMateriases;
       this.cursosMateriases = cursosMateriases;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_MATERIA", unique=true, nullable=false)
    public int getIdMateria() {
        return this.idMateria;
    }
    
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_INSTRUCTOR")
    public Instructores getInstructores() {
        return this.instructores;
    }
    
    public void setInstructores(Instructores instructores) {
        this.instructores = instructores;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_MATERIA")
    public CatGral getCatGral() {
        return this.catGral;
    }
    
    public void setCatGral(CatGral catGral) {
        this.catGral = catGral;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_AULA")
    public Aulas getAulas() {
        return this.aulas;
    }
    
    public void setAulas(Aulas aulas) {
        this.aulas = aulas;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FH_INICIAL", length=19)
    public Date getFhInicial() {
        return this.fhInicial;
    }
    
    public void setFhInicial(Date fhInicial) {
        this.fhInicial = fhInicial;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FH_FINAL", length=19)
    public Date getFhFinal() {
        return this.fhFinal;
    }
    
    public void setFhFinal(Date fhFinal) {
        this.fhFinal = fhFinal;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materias")
    public Set<CalifCursos> getCalifCursoses() {
        return this.califCursoses;
    }
    
    public void setCalifCursoses(Set<CalifCursos> califCursoses) {
        this.califCursoses = califCursoses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materias")
    public Set<ProgramaEstMaterias> getProgramaEstMateriases() {
        return this.programaEstMateriases;
    }
    
    public void setProgramaEstMateriases(Set<ProgramaEstMaterias> programaEstMateriases) {
        this.programaEstMateriases = programaEstMateriases;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materias")
    public Set<CursosMaterias> getCursosMateriases() {
        return this.cursosMateriases;
    }
    
    public void setCursosMateriases(Set<CursosMaterias> cursosMateriases) {
        this.cursosMateriases = cursosMateriases;
    }


	/**
	 * @return the materiasRegistros
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MATERIA_REGISTRO")
	public MateriasRegistros getMateriasRegistros() {
		return materiasRegistros;
	}


	/**
	 * @param materiasRegistros the materiasRegistros to set
	 */
	public void setMateriasRegistros(MateriasRegistros materiasRegistros) {
		this.materiasRegistros = materiasRegistros;
	}




}


