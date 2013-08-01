package mx.com.asteca.servicio.assembler;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

/**
 * Clase que define m&eacute;todos para transformar DTO's a Mappings (BD) y viceversa.
 * Define m&eacute;todos &uacute;tiles para transformar colecciones de objetos
 * @param <D> Clase del DTO
 * @param <M> Clase del Mapping (Objeto mapping de BD)
 * @author JAMARO
 * @version 1.0
 */
public abstract class Assembler<D, M> {

    /**
     * Transforma un objeto Mapping en un objeto DTO
     * @param mapping - Objeto de clase Mapping
     * @return Regresa un objeto de la clase de DTO
     * @since 1.0
     */
    public abstract D getDTOTransform(M mapping);

    /**
     * Transforma un objeto DTO en un objeto Mapping
     * @param dto - Objeto de clase DTO
     * @return  Regresa un objeto de clase Mapping
     * @since 1.0
     */
    public abstract M getMappingTransform(D dto);

    /**
     * Transforma una lista de objetos Mapping en una lista de objetos DTO
     * @param mappingList - Lista de objetos tipo Mapping
     * @return - Regrsa una lista de objetos tipo DTO. Regresa null si la lista 
     * de entrada es nula o no contiene objetos
     * @since 1.0 
     */
    public List<D> getDTOListTransform(List<M> mappingList) {
        List<D> listaDTO = null;
        if (CollectionUtils.isNotEmpty(mappingList)) {
            listaDTO = new ArrayList<D>();
            for (M mapping : mappingList) {
                D entity = this.getDTOTransform(mapping);
                listaDTO.add(entity);
            }
        }
        return listaDTO;
    }

    /**
     * Transforma una lista de objetos DTO en una lista de objetos tipo Mapping
     * @param dtoList - Lista de objetos tipo DTO
     * @return - Regresa una lista de objetos tipo Mapping. Regresa null si la
     * lita de entrada es nula o no contiene objetos
     * @since 1.0
     */
    public List<M> getMappingListTransform(List<D> dtoList) {
        List<M> listaMapping = null;
        if (CollectionUtils.isNotEmpty(dtoList)) {
            listaMapping = new ArrayList<M>();
            for (D dto : dtoList) {
                M mapping = this.getMappingTransform(dto);
                listaMapping.add(mapping);
            }
        }
        return listaMapping;
    }

    /**
     * Transforma un Set de objetos tipo Mapping en uno de objetos DTO
     * @param mappingSet - Set de objetos tipo Mapping
     * @return - Regresa un Set de objetos tipo DTO. Regresa null si el Set de 
     * entrada es nulo o no contiene elementos
     * @since 1.0
     */
    public Set<D> getDTOSetTransform(Set<M> mappingSet) {
        Set<D> setDTO = null;
        if (CollectionUtils.isNotEmpty(mappingSet)) {
            setDTO = new HashSet<D>();
            for (M mapping : mappingSet) {
                D dto = this.getDTOTransform(mapping);
                setDTO.add(dto);
            }
        }
        return setDTO;
    }

    /**
     * Transforma un Set de objetos DTO en un Set de objetos tipo Mapping
     * @param dtoSet - Set de objetos tipo DTO
     * @return - Regresa un Set de objetos tipo Mapping. Regresa null si el Set 
     * de entrada es nulo o no contiene elementos
     */
    public Set<M> getMappingSetTransform(Set<D> dtoSet) {
        Set<M> setMapping = null;
        if (CollectionUtils.isNotEmpty(dtoSet)) {
            setMapping = new HashSet<M>();
            for (D dto : dtoSet) {
                M mapping = this.getMappingTransform(dto);
                setMapping.add(mapping);
            }
        }
        return setMapping;
    }
}
