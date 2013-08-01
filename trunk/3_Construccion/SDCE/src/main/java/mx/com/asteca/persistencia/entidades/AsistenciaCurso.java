package mx.com.asteca.persistencia.entidades;
// Generated 31/07/2013 11:41:38 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AsistenciaCurso generated by hbm2java
 */
@Entity
@Table(name="asistencia_curso"
    ,catalog="astecadb"
)
public class AsistenciaCurso  implements java.io.Serializable {


     private long idAsistenciaCurso;
     private Alumnos alumnos;
     private Cursos cursos;
     private Date fecha;
     private Short presente;

    public AsistenciaCurso() {
    }

	
    public AsistenciaCurso(long idAsistenciaCurso) {
        this.idAsistenciaCurso = idAsistenciaCurso;
    }
    public AsistenciaCurso(long idAsistenciaCurso, Alumnos alumnos, Cursos cursos, Date fecha, Short presente) {
       this.idAsistenciaCurso = idAsistenciaCurso;
       this.alumnos = alumnos;
       this.cursos = cursos;
       this.fecha = fecha;
       this.presente = presente;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ASISTENCIA_CURSO", unique=true, nullable=false)
    public long getIdAsistenciaCurso() {
        return this.idAsistenciaCurso;
    }
    
    public void setIdAsistenciaCurso(long idAsistenciaCurso) {
        this.idAsistenciaCurso = idAsistenciaCurso;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ALUMNO")
    public Alumnos getAlumnos() {
        return this.alumnos;
    }
    
    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_CURSO")
    public Cursos getCursos() {
        return this.cursos;
    }
    
    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="PRESENTE")
    public Short getPresente() {
        return this.presente;
    }
    
    public void setPresente(Short presente) {
        this.presente = presente;
    }




}


