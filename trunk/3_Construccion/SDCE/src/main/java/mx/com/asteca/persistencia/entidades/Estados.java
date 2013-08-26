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
 * Estados generated by hbm2java
 */
@Entity
@Table(name="estados"
    ,catalog="astecadb"
)
public class Estados  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstado;
     private String nombre;
     private Short activo;
     private String clave;
     private Set<Municipios> municipioses = new HashSet<Municipios>(0);

    public Estados() {
    }

	
    public Estados(int idEstado) {
        this.idEstado = idEstado;
    }
    public Estados(int idEstado, String nombre, Short activo, String clave, Set<Municipios> municipioses) {
       this.idEstado = idEstado;
       this.nombre = nombre;
       this.activo = activo;
       this.clave = clave;
       this.municipioses = municipioses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ESTADO", unique=true, nullable=false)
    public int getIdEstado() {
        return this.idEstado;
    }
    
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    @Column(name="NOMBRE", length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="ACTIVO")
    public Short getActivo() {
        return this.activo;
    }
    
    public void setActivo(Short activo) {
        this.activo = activo;
    }
    
    @Column(name="CLAVE", length=15)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="estados")
    public Set<Municipios> getMunicipioses() {
        return this.municipioses;
    }
    
    public void setMunicipioses(Set<Municipios> municipioses) {
        this.municipioses = municipioses;
    }




}

