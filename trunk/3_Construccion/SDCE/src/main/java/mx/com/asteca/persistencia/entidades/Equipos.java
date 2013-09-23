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
 * Equipos generated by hbm2java
 */
@Entity
@Table(name="EQUIPOS"
    ,catalog="astecadb"
)
public class Equipos  implements java.io.Serializable {


     private int idEquipo;
     private CatGral catGral;
     private String clave;
     private String dsc;
     private Short activo;

    public Equipos() {
    }

	
    public Equipos(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    public Equipos(int idEquipo, CatGral catGral, String clave, String dsc, Short activo) {
       this.idEquipo = idEquipo;
       this.catGral = catGral;
       this.clave = clave;
       this.dsc = dsc;
       this.activo = activo;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_EQUIPO", unique=true, nullable=false)
    public int getIdEquipo() {
        return this.idEquipo;
    }
    
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TIPO_EQUIPO")
    public CatGral getCatGral() {
        return this.catGral;
    }
    
    public void setCatGral(CatGral catGral) {
        this.catGral = catGral;
    }
    
    @Column(name="CLAVE", length=20)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Column(name="DSC", length=50)
    public String getDsc() {
        return this.dsc;
    }
    
    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
    
    @Column(name="ACTIVO")
    public Short getActivo() {
        return this.activo;
    }
    
    public void setActivo(Short activo) {
        this.activo = activo;
    }




}


