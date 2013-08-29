package mx.com.asteca.fachada;

import java.io.Serializable;
import java.util.List;

public interface BaseFachada <DTO, PK extends Serializable>{
	
	/**
     * Método génerico utilizado para obtener todos los objetos de
     * un tipo particular.
     * 
     * @return Listado de objetos
     */
    List<DTO> getAll() throws FachadaException;

    /**
     * Método genérico para obtener un objeto basandose en su clase
     * e identificador.
     * 
     * @param id Identificador (clave primaria) del objeto a obtener
     * @return Objeto cargado
     */
    DTO findByPK(PK id) throws FachadaException;


    /**
     * Método genérico para salvar un objeto - maneja 
     * actualización e inserción.
     * 
     * @param object Objeto a guardar
     */
    void update(DTO object) throws FachadaException;

    /**
     * Método genérico para borrar un objeto basado en su clase e
     * identificador
     * 
     * @param id el identificador (llave primaria) del objeto a eliminar
     */
    void remove(DTO object) throws FachadaException;

    /**
     * Método genérico para insertar un objeto.
     */
    PK save(DTO object) throws FachadaException;

    /**
     * Guarda o actualiza un registro
     * 
     * @param object
     */
    void saveOrUpdate(DTO object) throws FachadaException;
    
    /**
     * Verifica la existencia de un objeto por clave primaria
     * 
     * @param paramPK Clave primaria
     * @return Verdadero si existe un objeto relacionado con la llave primaria 
     */
    boolean exists(PK paramPK) throws FachadaException;

}