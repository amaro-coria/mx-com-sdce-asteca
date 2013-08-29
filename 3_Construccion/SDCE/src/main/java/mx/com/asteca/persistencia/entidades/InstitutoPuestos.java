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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * InstitutoPuestos generated by hbm2java
 */
@Entity
@Table(name="instituto_puestos"
    ,catalog="astecadb"
)
public class InstitutoPuestos  implements java.io.Serializable {


     private short idPuesto;
     private String nombre;
     private Set<InstitutoContactos> institutoContactoses = new HashSet<InstitutoContactos>(0);

    public InstitutoPuestos() {
    }

	
    public InstitutoPuestos(short idPuesto) {
        this.idPuesto = idPuesto;
    }
    public InstitutoPuestos(short idPuesto, String nombre, Set<InstitutoContactos> institutoContactoses) {
       this.idPuesto = idPuesto;
       this.nombre = nombre;
       this.institutoContactoses = institutoContactoses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PUESTO", unique=true, nullable=false)
    public short getIdPuesto() {
        return this.idPuesto;
    }
    
    public void setIdPuesto(short idPuesto) {
        this.idPuesto = idPuesto;
    }
    
    @Column(name="NOMBRE", length=20)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="institutoPuestos")
    public Set<InstitutoContactos> getInstitutoContactoses() {
        return this.institutoContactoses;
    }
    
    public void setInstitutoContactoses(Set<InstitutoContactos> institutoContactoses) {
        this.institutoContactoses = institutoContactoses;
    }




}

