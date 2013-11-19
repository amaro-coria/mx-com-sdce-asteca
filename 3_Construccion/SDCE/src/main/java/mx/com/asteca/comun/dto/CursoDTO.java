package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

public class CursoDTO implements Serializable{


    private int idCurso;
    private Integer idArea;
    private Integer idProgrEstudios;
    private Date fechaIni;
    private Date fechaFin;
    private Integer idSede;
    private String horaIni;
    private String horaFin;
    private Integer grupo;
    private String referencia;
    private String nombre;

   public CursoDTO() {
   }

	
   public CursoDTO(int idCurso) {
       this.idCurso = idCurso;
   }
   public CursoDTO(int idCurso, Integer idArea, Integer idProgrEstudios, Date fechaIni, Date fechaFin, Integer idSede, String horaIni, String horaFin, Integer grupo, String referencia) {
      this.idCurso = idCurso;
      this.idArea = idArea;
      this.idProgrEstudios = idProgrEstudios;
      this.fechaIni = fechaIni;
      this.fechaFin = fechaFin;
      this.idSede = idSede;
      this.horaIni = horaIni;
      this.horaFin = horaFin;
      this.grupo = grupo;
      this.referencia = referencia;
   }
  
   public int getIdCurso() {
       return this.idCurso;
   }
   
   public void setIdCurso(int idCurso) {
       this.idCurso = idCurso;
   }
   
   public Integer getIdArea() {
       return this.idArea;
   }
   
   public void setIdArea(Integer idArea) {
       this.idArea = idArea;
   }
   
   public Integer getIdProgrEstudios() {
       return this.idProgrEstudios;
   }
   
   public void setIdProgrEstudios(Integer idProgrEstudios) {
       this.idProgrEstudios = idProgrEstudios;
   }
   public Date getFechaIni() {
       return this.fechaIni;
   }
   
   public void setFechaIni(Date fechaIni) {
       this.fechaIni = fechaIni;
   }
   public Date getFechaFin() {
       return this.fechaFin;
   }
   
   public void setFechaFin(Date fechaFin) {
       this.fechaFin = fechaFin;
   }
   
   public Integer getIdSede() {
       return this.idSede;
   }
   
   public void setIdSede(Integer idSede) {
       this.idSede = idSede;
   }
   
   public String getHoraIni() {
       return this.horaIni;
   }
   
   public void setHoraIni(String horaIni) {
       this.horaIni = horaIni;
   }
   
   public String getHoraFin() {
       return this.horaFin;
   }
   
   public void setHoraFin(String horaFin) {
       this.horaFin = horaFin;
   }
   
   public Integer getGrupo() {
       return this.grupo;
   }
   
   public void setGrupo(Integer grupo) {
       this.grupo = grupo;
   }
   
   public String getReferencia() {
       return this.referencia;
   }
   
   public void setReferencia(String referencia) {
       this.referencia = referencia;
   }


/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + idCurso;
	return result;
}


/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CursoDTO other = (CursoDTO) obj;
	if (idCurso != other.idCurso)
		return false;
	return true;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}
   
   
}
