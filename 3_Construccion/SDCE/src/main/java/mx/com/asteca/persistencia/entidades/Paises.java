package mx.com.asteca.persistencia.entidades;
// Generated 1/09/2013 05:14:39 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Paises generated by hbm2java
 */
@Entity
@Table(name="PAISES"
    ,catalog="astecadb"
)
public class Paises  implements java.io.Serializable {


     private int idPais;
     private String nombrePais;
     private Short activo;
     private Set<Estados> estadoses = new HashSet<Estados>(0);

    public Paises() {
    }

	
    public Paises(int idPais) {
        this.idPais = idPais;
    }
    public Paises(int idPais, String nombrePais, Short activo, Set<Estados> estadoses) {
       this.idPais = idPais;
       this.nombrePais = nombrePais;
       this.activo = activo;
       this.estadoses = estadoses;
    }
   
     @Id 
    
    @Column(name="ID_PAIS", unique=true, nullable=false)
    public int getIdPais() {
        return this.idPais;
    }
    
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
    
    @Column(name="NOMBRE_PAIS", length=50)
    public String getNombrePais() {
        return this.nombrePais;
    }
    
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    @Column(name="ACTIVO")
    public Short getActivo() {
        return this.activo;
    }
    
    public void setActivo(Short activo) {
        this.activo = activo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paises")
    public Set<Estados> getEstadoses() {
        return this.estadoses;
    }
    
    public void setEstadoses(Set<Estados> estadoses) {
        this.estadoses = estadoses;
    }




}


