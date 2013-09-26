/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MateriaDAO;
import mx.com.asteca.persistencia.entidades.Materias;
import mx.com.asteca.servicio.MateriaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class MateriaServicioImpl extends
		BaseServiceImpl<MateriaDTO, Integer, Materias> implements
		MateriaServicio {

	@Autowired
	private MateriaDAO daoMateria;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS)
	private Assembler assemblerMateria;

	@Override
	BaseDAO getDAO() {
		return daoMateria;
	}

	@Override
	Assembler getAssembler() {
		return assemblerMateria;
	}

}
