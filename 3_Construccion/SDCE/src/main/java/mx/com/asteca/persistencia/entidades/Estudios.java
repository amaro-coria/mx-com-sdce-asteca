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
 * Estudios generated by hbm2java
 */
@Entity
@Table(name="ESTUDIOS"
    ,catalog="astecadb"
)
public class Estudios  implements java.io.Serializable {


     private int idEstudios;
     private TiposEstudios tiposEstudios;
     private String institucion;
     private Short fechaIni;
     private Short fechaFin;
     private String cert;
     private Set<EstudiosAlumno> estudiosAlumnos = new HashSet<EstudiosAlumno>(0);

    public Estudios() {
    }

	
    public Estudios(int idEstudios) {
        this.idEstudios = idEstudios;
    }
    public Estudios(int idEstudios, TiposEstudios tiposEstudios, String institucion, Short fechaIni, Short fechaFin, String cert, Set<EstudiosAlumno> estudiosAlumnos) {
       this.idEstudios = idEstudios;
       this.tiposEstudios = tiposEstudios;
       this.institucion = institucion;
       this.fechaIni = fechaIni;
       this.fechaFin = fechaFin;
       this.cert = cert;
       this.estudiosAlumnos = estudiosAlumnos;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ESTUDIOS", unique=true, nullable=false)
    public int getIdEstudios() {
        return this.idEstudios;
    }
    
    public void setIdEstudios(int idEstudios) {
        this.idEstudios = idEstudios;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_ESTUDIO")
    public TiposEstudios getTiposEstudios() {
        return this.tiposEstudios;
    }
    
    public void setTiposEstudios(TiposEstudios tiposEstudios) {
        this.tiposEstudios = tiposEstudios;
    }
    
    @Column(name="INSTITUCION", length=50)
    public String getInstitucion() {
        return this.institucion;
    }
    
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    
    @Column(name="FECHA_INI")
    public Short getFechaIni() {
        return this.fechaIni;
    }
    
    public void setFechaIni(Short fechaIni) {
        this.fechaIni = fechaIni;
    }
    
    @Column(name="FECHA_FIN")
    public Short getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Short fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Column(name="CERT", length=50)
    public String getCert() {
        return this.cert;
    }
    
    public void setCert(String cert) {
        this.cert = cert;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="estudios")
    public Set<EstudiosAlumno> getEstudiosAlumnos() {
        return this.estudiosAlumnos;
    }
    
    public void setEstudiosAlumnos(Set<EstudiosAlumno> estudiosAlumnos) {
        this.estudiosAlumnos = estudiosAlumnos;
    }




}


