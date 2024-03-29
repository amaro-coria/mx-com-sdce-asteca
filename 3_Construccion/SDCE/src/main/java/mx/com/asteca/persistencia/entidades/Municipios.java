package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Municipios generated by hbm2java
 */
@Entity
@Table(name="MUNICIPIOS"
    ,catalog="astecadb"
)
public class Municipios  implements java.io.Serializable {


     private MunicipiosId id;
     private Estados estados;
     private String nombre;
     private Short activo;
     private String clave;
     private Set<Asentamientos> asentamientoses = new HashSet<Asentamientos>(0);
     private int idUnico;

    public Municipios() {
    }

	
    public Municipios(MunicipiosId id, Estados estados) {
        this.id = id;
        this.estados = estados;
    }
    public Municipios(MunicipiosId id, Estados estados, String nombre, Short activo, String clave, Set<Asentamientos> asentamientoses) {
       this.id = id;
       this.estados = estados;
       this.nombre = nombre;
       this.activo = activo;
       this.clave = clave;
       this.asentamientoses = asentamientoses;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="idMunicipio", column=@Column(name="ID_MUNICIPIO", nullable=false) ), 
        @AttributeOverride(name="idEstado", column=@Column(name="ID_ESTADO", nullable=false) ) } )
    public MunicipiosId getId() {
        return this.id;
    }
    
    public void setId(MunicipiosId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ESTADO", nullable=false, insertable=false, updatable=false)
    public Estados getEstados() {
        return this.estados;
    }
    
    public void setEstados(Estados estados) {
        this.estados = estados;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="municipios")
    public Set<Asentamientos> getAsentamientoses() {
        return this.asentamientoses;
    }
    
    public void setAsentamientoses(Set<Asentamientos> asentamientoses) {
        this.asentamientoses = asentamientoses;
    }


    @Column(name="ID_UNICO")
	public int getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}




}


