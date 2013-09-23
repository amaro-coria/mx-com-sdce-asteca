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
 * TiposLicencia generated by hbm2java
 */
@Entity
@Table(name="TIPOS_LICENCIA"
    ,catalog="astecadb"
)
public class TiposLicencia  implements java.io.Serializable {


     private short idTipoLic;
     private String descTipoLic;
     private Set<Capacidades> capacidadeses = new HashSet<Capacidades>(0);

    public TiposLicencia() {
    }

	
    public TiposLicencia(short idTipoLic) {
        this.idTipoLic = idTipoLic;
    }
    public TiposLicencia(short idTipoLic, String descTipoLic, Set<Capacidades> capacidadeses) {
       this.idTipoLic = idTipoLic;
       this.descTipoLic = descTipoLic;
       this.capacidadeses = capacidadeses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_TIPO_LIC", unique=true, nullable=false)
    public short getIdTipoLic() {
        return this.idTipoLic;
    }
    
    public void setIdTipoLic(short idTipoLic) {
        this.idTipoLic = idTipoLic;
    }
    
    @Column(name="DESC_TIPO_LIC", length=20)
    public String getDescTipoLic() {
        return this.descTipoLic;
    }
    
    public void setDescTipoLic(String descTipoLic) {
        this.descTipoLic = descTipoLic;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tiposLicencia")
    public Set<Capacidades> getCapacidadeses() {
        return this.capacidadeses;
    }
    
    public void setCapacidadeses(Set<Capacidades> capacidadeses) {
        this.capacidadeses = capacidadeses;
    }




}


