package mx.com.asteca.comun.dto;

/**
 * Clase que representa un objeto de transferencia de datos para los Equipos.
 * 
 * @author Rabelt Ibarra Godinez.
 * 
 */
@SuppressWarnings("serial")
public class EquipoDTO implements java.io.Serializable {

	private int idEquipo;
	private long idCatGralDTO;
	private String clave;
	private String dsc;
	private Short activo;

	public EquipoDTO() {
	}

	public EquipoDTO(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public EquipoDTO(int idEquipo, long idCatGralDTO, String clave,
			String dsc, Short activo) {
		this.idEquipo = idEquipo;
		this.idCatGralDTO = idCatGralDTO;
		this.clave = clave;
		this.dsc = dsc;
		this.activo = activo;
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public long getIdCatGral() {
		return this.idCatGralDTO;
	}

	public void setIdCatGralDTO(long idCatGralDTO) {
		this.idCatGralDTO = idCatGralDTO;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDsc() {
		return this.dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public Short getActivo() {
		return this.activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}

}
