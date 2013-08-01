package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Referencias generated by hbm2java
 */
@Entity
@Table(name="referencias"
    ,catalog="astecadb"
)
public class Referencias  implements java.io.Serializable {


     private int idReferencia;
     private Domicilios domicilios;
     private Alumnos alumnos;
     private Personas personas;

    public Referencias() {
    }

	
    public Referencias(int idReferencia) {
        this.idReferencia = idReferencia;
    }
    public Referencias(int idReferencia, Domicilios domicilios, Alumnos alumnos, Personas personas) {
       this.idReferencia = idReferencia;
       this.domicilios = domicilios;
       this.alumnos = alumnos;
       this.personas = personas;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_REFERENCIA", unique=true, nullable=false)
    public int getIdReferencia() {
        return this.idReferencia;
    }
    
    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
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
    @JoinColumn(name="ID_ALUMNO")
    public Alumnos getAlumnos() {
        return this.alumnos;
    }
    
    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PERSONA")
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }




}


