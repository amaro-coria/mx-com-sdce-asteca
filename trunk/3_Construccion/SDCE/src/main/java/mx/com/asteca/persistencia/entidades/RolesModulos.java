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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RolesModulos generated by hbm2java
 */
@Entity
@Table(name="ROLES_MODULOS"
    ,catalog="astecadb"
)
public class RolesModulos  implements java.io.Serializable {


     private int idRolMod;
     private Permisos permisos;
     private Integer idRol;
     private Integer idModulo;
     private Short activo;
     private Set<RolesModPermisosUsr> rolesModPermisosUsrs = new HashSet<RolesModPermisosUsr>(0);

    public RolesModulos() {
    }

	
    public RolesModulos(int idRolMod) {
        this.idRolMod = idRolMod;
    }
    public RolesModulos(int idRolMod, Permisos permisos, Integer idRol, Integer idModulo, Short activo, Set<RolesModPermisosUsr> rolesModPermisosUsrs) {
       this.idRolMod = idRolMod;
       this.permisos = permisos;
       this.idRol = idRol;
       this.idModulo = idModulo;
       this.activo = activo;
       this.rolesModPermisosUsrs = rolesModPermisosUsrs;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ROL_MOD", unique=true, nullable=false)
    public int getIdRolMod() {
        return this.idRolMod;
    }
    
    public void setIdRolMod(int idRolMod) {
        this.idRolMod = idRolMod;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PERMISOS")
    public Permisos getPermisos() {
        return this.permisos;
    }
    
    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }
    
    @Column(name="ID_ROL")
    public Integer getIdRol() {
        return this.idRol;
    }
    
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
    
    @Column(name="ID_MODULO")
    public Integer getIdModulo() {
        return this.idModulo;
    }
    
    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }
    
    @Column(name="ACTIVO")
    public Short getActivo() {
        return this.activo;
    }
    
    public void setActivo(Short activo) {
        this.activo = activo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="rolesModulos")
    public Set<RolesModPermisosUsr> getRolesModPermisosUsrs() {
        return this.rolesModPermisosUsrs;
    }
    
    public void setRolesModPermisosUsrs(Set<RolesModPermisosUsr> rolesModPermisosUsrs) {
        this.rolesModPermisosUsrs = rolesModPermisosUsrs;
    }




}

