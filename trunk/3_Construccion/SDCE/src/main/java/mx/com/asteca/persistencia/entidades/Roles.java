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
 * Roles generated by hbm2java
 */
@Entity
@Table(name="ROLES"
    ,catalog="astecadb"
)
public class Roles  implements java.io.Serializable {


     private int idRol;
     private String clave;
     private String nombre;
     private Short activo;
     private Set<RolesModUsuarios> rolesModUsuarioses = new HashSet<RolesModUsuarios>(0);

    public Roles() {
    }

	
    public Roles(int idRol) {
        this.idRol = idRol;
    }
    public Roles(int idRol, String clave, String nombre, Short activo, Set<RolesModUsuarios> rolesModUsuarioses) {
       this.idRol = idRol;
       this.clave = clave;
       this.nombre = nombre;
       this.activo = activo;
       this.rolesModUsuarioses = rolesModUsuarioses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ROL", unique=true, nullable=false)
    public int getIdRol() {
        return this.idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    @Column(name="CLAVE", length=15)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Column(name="NOMBRE", length=25)
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="roles")
    public Set<RolesModUsuarios> getRolesModUsuarioses() {
        return this.rolesModUsuarioses;
    }
    
    public void setRolesModUsuarioses(Set<RolesModUsuarios> rolesModUsuarioses) {
        this.rolesModUsuarioses = rolesModUsuarioses;
    }




}


