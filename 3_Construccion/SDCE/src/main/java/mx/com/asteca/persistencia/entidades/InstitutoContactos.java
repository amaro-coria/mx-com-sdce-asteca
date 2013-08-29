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
 * InstitutoContactos generated by hbm2java
 */
@Entity
@Table(name="instituto_contactos"
    ,catalog="astecadb"
)
public class InstitutoContactos  implements java.io.Serializable {


     private int idInstCont;
     private InstitutoPuestos institutoPuestos;
     private Personas personas;
     private Institutos institutos;

    public InstitutoContactos() {
    }

	
    public InstitutoContactos(int idInstCont) {
        this.idInstCont = idInstCont;
    }
    public InstitutoContactos(int idInstCont, InstitutoPuestos institutoPuestos, Personas personas, Institutos institutos) {
       this.idInstCont = idInstCont;
       this.institutoPuestos = institutoPuestos;
       this.personas = personas;
       this.institutos = institutos;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_INST_CONT", unique=true, nullable=false)
    public int getIdInstCont() {
        return this.idInstCont;
    }
    
    public void setIdInstCont(int idInstCont) {
        this.idInstCont = idInstCont;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PUESTO")
    public InstitutoPuestos getInstitutoPuestos() {
        return this.institutoPuestos;
    }
    
    public void setInstitutoPuestos(InstitutoPuestos institutoPuestos) {
        this.institutoPuestos = institutoPuestos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PERSONA")
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_INSTITUTO")
    public Institutos getInstitutos() {
        return this.institutos;
    }
    
    public void setInstitutos(Institutos institutos) {
        this.institutos = institutos;
    }




}

