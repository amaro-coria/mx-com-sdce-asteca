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
 * TiposEstudios generated by hbm2java
 */
@Entity
@Table(name="tipos_estudios"
    ,catalog="astecadb"
)
public class TiposEstudios  implements java.io.Serializable {


     private short idTipoEstudios;
     private String nombre;
     private Set<Estudios> estudioses = new HashSet<Estudios>(0);

    public TiposEstudios() {
    }

	
    public TiposEstudios(short idTipoEstudios) {
        this.idTipoEstudios = idTipoEstudios;
    }
    public TiposEstudios(short idTipoEstudios, String nombre, Set<Estudios> estudioses) {
       this.idTipoEstudios = idTipoEstudios;
       this.nombre = nombre;
       this.estudioses = estudioses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_TIPO_ESTUDIOS", unique=true, nullable=false)
    public short getIdTipoEstudios() {
        return this.idTipoEstudios;
    }
    
    public void setIdTipoEstudios(short idTipoEstudios) {
        this.idTipoEstudios = idTipoEstudios;
    }
    
    @Column(name="NOMBRE", length=20)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tiposEstudios")
    public Set<Estudios> getEstudioses() {
        return this.estudioses;
    }
    
    public void setEstudioses(Set<Estudios> estudioses) {
        this.estudioses = estudioses;
    }




}


