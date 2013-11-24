package mx.com.asteca.comun.dto;


public class ReporteAulasDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idReporteAulas;
	private String instructor;
	private String horas;
	private String mes;

	public int getIdReporteAulas() {
		return idReporteAulas;
	}

	public void setIdReporteAulas(int idReporteAulas) {
		this.idReporteAulas = idReporteAulas;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}