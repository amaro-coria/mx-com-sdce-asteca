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
 * EstudiosAlumno generated by hbm2java
 */
@Entity
@Table(name="estudios_alumno"
    ,catalog="astecadb"
)
public class EstudiosAlumno  implements java.io.Serializable {


     private int idEstAlu;
     private Alumnos alumnos;
     private Estudios estudios;

    public EstudiosAlumno() {
    }

	
    public EstudiosAlumno(int idEstAlu) {
        this.idEstAlu = idEstAlu;
    }
    public EstudiosAlumno(int idEstAlu, Alumnos alumnos, Estudios estudios) {
       this.idEstAlu = idEstAlu;
       this.alumnos = alumnos;
       this.estudios = estudios;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_EST_ALU", unique=true, nullable=false)
    public int getIdEstAlu() {
        return this.idEstAlu;
    }
    
    public void setIdEstAlu(int idEstAlu) {
        this.idEstAlu = idEstAlu;
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
    @JoinColumn(name="ID_ESTUDIO")
    public Estudios getEstudios() {
        return this.estudios;
    }
    
    public void setEstudios(Estudios estudios) {
        this.estudios = estudios;
    }




}


