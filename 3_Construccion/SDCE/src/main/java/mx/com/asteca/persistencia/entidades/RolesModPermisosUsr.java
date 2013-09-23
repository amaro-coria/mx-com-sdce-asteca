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
 * RolesModPermisosUsr generated by hbm2java
 */
@Entity
@Table(name="ROLES_MOD_PERMISOS_USR"
    ,catalog="astecadb"
)
public class RolesModPermisosUsr  implements java.io.Serializable {


     private int idRolModPerUsr;
     private RolesModUsuarios rolesModUsuarios;
     private RolesModulos rolesModulos;
     private Set<RolesModPermisosSobre> rolesModPermisosSobres = new HashSet<RolesModPermisosSobre>(0);

    public RolesModPermisosUsr() {
    }

	
    public RolesModPermisosUsr(int idRolModPerUsr) {
        this.idRolModPerUsr = idRolModPerUsr;
    }
    public RolesModPermisosUsr(int idRolModPerUsr, RolesModUsuarios rolesModUsuarios, RolesModulos rolesModulos, Set<RolesModPermisosSobre> rolesModPermisosSobres) {
       this.idRolModPerUsr = idRolModPerUsr;
       this.rolesModUsuarios = rolesModUsuarios;
       this.rolesModulos = rolesModulos;
       this.rolesModPermisosSobres = rolesModPermisosSobres;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ROL_MOD_PER_USR", unique=true, nullable=false)
    public int getIdRolModPerUsr() {
        return this.idRolModPerUsr;
    }
    
    public void setIdRolModPerUsr(int idRolModPerUsr) {
        this.idRolModPerUsr = idRolModPerUsr;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ROL_MOD_USR")
    public RolesModUsuarios getRolesModUsuarios() {
        return this.rolesModUsuarios;
    }
    
    public void setRolesModUsuarios(RolesModUsuarios rolesModUsuarios) {
        this.rolesModUsuarios = rolesModUsuarios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ROL_MOD")
    public RolesModulos getRolesModulos() {
        return this.rolesModulos;
    }
    
    public void setRolesModulos(RolesModulos rolesModulos) {
        this.rolesModulos = rolesModulos;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="rolesModPermisosUsr")
    public Set<RolesModPermisosSobre> getRolesModPermisosSobres() {
        return this.rolesModPermisosSobres;
    }
    
    public void setRolesModPermisosSobres(Set<RolesModPermisosSobre> rolesModPermisosSobres) {
        this.rolesModPermisosSobres = rolesModPermisosSobres;
    }




}


