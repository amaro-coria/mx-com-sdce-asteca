package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


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

/**
 * Instructores generated by hbm2java
 */
@Entity
@Table(name="instructores"
    ,catalog="astecadb"
)
public class Instructores  implements java.io.Serializable {


     private int idInstructor;
     private Domicilios domicilios;
     private TiposInstructores tiposInstructores;
     private Personas personas;
     private String noEmpleado;
     private Set<Materias> materiases = new HashSet<Materias>(0);

    public Instructores() {
    }

	
    public Instructores(int idInstructor) {
        this.idInstructor = idInstructor;
    }
    public Instructores(int idInstructor, Domicilios domicilios, TiposInstructores tiposInstructores, Personas personas, String noEmpleado, Set<Materias> materiases) {
       this.idInstructor = idInstructor;
       this.domicilios = domicilios;
       this.tiposInstructores = tiposInstructores;
       this.personas = personas;
       this.noEmpleado = noEmpleado;
       this.materiases = materiases;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_INSTRUCTOR", unique=true, nullable=false)
    public int getIdInstructor() {
        return this.idInstructor;
    }
    
    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_DOMICILIO")
    public Domicilios getDomicilios() {
        return this.domicilios;
    }
    
    public void setDomicilios(Domicilios domicilios) {
        this.domicilios = domicilios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_INSTRUCTOR")
    public TiposInstructores getTiposInstructores() {
        return this.tiposInstructores;
    }
    
    public void setTiposInstructores(TiposInstructores tiposInstructores) {
        this.tiposInstructores = tiposInstructores;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PERSONA")
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    
    @Column(name="NO_EMPLEADO", length=20)
    public String getNoEmpleado() {
        return this.noEmpleado;
    }
    
    public void setNoEmpleado(String noEmpleado) {
        this.noEmpleado = noEmpleado;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="instructores")
    public Set<Materias> getMateriases() {
        return this.materiases;
    }
    
    public void setMateriases(Set<Materias> materiases) {
        this.materiases = materiases;
    }




}


