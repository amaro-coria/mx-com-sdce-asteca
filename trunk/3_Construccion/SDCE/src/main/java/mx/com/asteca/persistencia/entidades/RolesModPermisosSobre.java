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
 * RolesModPermisosSobre generated by hbm2java
 */
@Entity
@Table(name="roles_mod_permisos_sobre"
    ,catalog="astecadb"
)
public class RolesModPermisosSobre  implements java.io.Serializable {


     private int idRolModPermSobre;
     private Permisos permisos;
     private RolesModPermisosUsr rolesModPermisosUsr;

    public RolesModPermisosSobre() {
    }

	
    public RolesModPermisosSobre(int idRolModPermSobre) {
        this.idRolModPermSobre = idRolModPermSobre;
    }
    public RolesModPermisosSobre(int idRolModPermSobre, Permisos permisos, RolesModPermisosUsr rolesModPermisosUsr) {
       this.idRolModPermSobre = idRolModPermSobre;
       this.permisos = permisos;
       this.rolesModPermisosUsr = rolesModPermisosUsr;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ROL_MOD_PERM_SOBRE", unique=true, nullable=false)
    public int getIdRolModPermSobre() {
        return this.idRolModPermSobre;
    }
    
    public void setIdRolModPermSobre(int idRolModPermSobre) {
        this.idRolModPermSobre = idRolModPermSobre;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PERMISOS")
    public Permisos getPermisos() {
        return this.permisos;
    }
    
    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ROL_MOD_PERM_USR")
    public RolesModPermisosUsr getRolesModPermisosUsr() {
        return this.rolesModPermisosUsr;
    }
    
    public void setRolesModPermisosUsr(RolesModPermisosUsr rolesModPermisosUsr) {
        this.rolesModPermisosUsr = rolesModPermisosUsr;
    }




}


