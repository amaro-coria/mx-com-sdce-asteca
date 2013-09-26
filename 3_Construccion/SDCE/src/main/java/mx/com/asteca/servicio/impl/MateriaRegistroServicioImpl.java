/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MateriaRegistroDAO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;
import mx.com.asteca.servicio.MateriaRegistroServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class MateriaRegistroServicioImpl extends
		BaseServiceImpl<MateriaRegistroDTO, Integer, MateriasRegistros>
		implements MateriaRegistroServicio {

	@Autowired
	private MateriaRegistroDAO daoMateriaReg;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS_REGISTROS)
	private Assembler assemblerMAteriaReg;
	
	@Override
	BaseDAO getDAO() {
		return daoMateriaReg;
	}

	@Override
	Assembler getAssembler() {
		return assemblerMAteriaReg;
	}

}
