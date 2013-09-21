/**
 * 
 */
package mx.com.asteca.util;

/**
 * Enumeracion para controlar los tipos de catalogo general
 * @author JAMARO
 *
 */
public enum CatGralTipo {
	
	TIPO_EQUIPO((short)1,"EQUIPO"),
	TIPO_AREA((short)2, "AREA"),
	TIPO_SEDE((short)3, "SEDE"),
	TIPO_PAIS((short)4, "PAIS"),
	TIPO_CLASIFICACION_CURSO((short)5, "CLASIFICACION DE CURSO"),
	TIPO_MATERIAS((short)6, "MATERIAS"),
	TIPO_OTRO((short)7, "OTRO");

	private CatGralTipo(short tipo, String descripcion){
		this.tipo =  tipo;
		this.dsc = descripcion;
	}
	
	private short tipo;
	private String dsc;
	/**
	 * @return the tipo
	 */
	public short getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(short tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the dsc
	 */
	public String getDsc() {
		return dsc;
	}
	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	
}
