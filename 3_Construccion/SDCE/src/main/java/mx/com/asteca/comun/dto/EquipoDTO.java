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
	private CatGralDTO catGralDTO;
	private String clave;
	private String dsc;
	private Short activo;

	public EquipoDTO() {
	}

	public EquipoDTO(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public EquipoDTO(int idEquipo, CatGralDTO catGralDTO, String clave,
			String dsc, Short activo) {
		this.idEquipo = idEquipo;
		this.catGralDTO = catGralDTO;
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

	public CatGralDTO getCatGral() {
		return this.catGralDTO;
	}

	public void setCatGralDTO(CatGralDTO catGralDTO) {
		this.catGralDTO = catGralDTO;
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
