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
 * Docs generated by hbm2java
 */
@Entity
@Table(name="docs"
    ,catalog="astecadb"
)
public class Docs  implements java.io.Serializable {


     private int idDoc;
     private Estatus estatus;
     private String nombre;
     private String ruta;
     private Integer idAlumno;
     private Set<AutorizacionesProgrEst> autorizacionesProgrEsts = new HashSet<AutorizacionesProgrEst>(0);
     private Set<DocsAlumnos> docsAlumnoses = new HashSet<DocsAlumnos>(0);

    public Docs() {
    }

	
    public Docs(int idDoc) {
        this.idDoc = idDoc;
    }
    public Docs(int idDoc, Estatus estatus, String nombre, String ruta, Integer idAlumno, Set<AutorizacionesProgrEst> autorizacionesProgrEsts, Set<DocsAlumnos> docsAlumnoses) {
       this.idDoc = idDoc;
       this.estatus = estatus;
       this.nombre = nombre;
       this.ruta = ruta;
       this.idAlumno = idAlumno;
       this.autorizacionesProgrEsts = autorizacionesProgrEsts;
       this.docsAlumnoses = docsAlumnoses;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_DOC", unique=true, nullable=false)
    public int getIdDoc() {
        return this.idDoc;
    }
    
    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ESTATUS")
    public Estatus getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
    
    @Column(name="NOMBRE", length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="RUTA", length=100)
    public String getRuta() {
        return this.ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    @Column(name="ID_ALUMNO")
    public Integer getIdAlumno() {
        return this.idAlumno;
    }
    
    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="docs")
    public Set<AutorizacionesProgrEst> getAutorizacionesProgrEsts() {
        return this.autorizacionesProgrEsts;
    }
    
    public void setAutorizacionesProgrEsts(Set<AutorizacionesProgrEst> autorizacionesProgrEsts) {
        this.autorizacionesProgrEsts = autorizacionesProgrEsts;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="docs")
    public Set<DocsAlumnos> getDocsAlumnoses() {
        return this.docsAlumnoses;
    }
    
    public void setDocsAlumnoses(Set<DocsAlumnos> docsAlumnoses) {
        this.docsAlumnoses = docsAlumnoses;
    }




}


