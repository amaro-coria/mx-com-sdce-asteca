/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.PaisDAO;
import mx.com.asteca.persistencia.entidades.Paises;
import mx.com.asteca.servicio.PaisServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class PaisServicioImpl extends BaseServiceImpl<PaisDTO, Integer, Paises>
		implements PaisServicio {

	@Autowired
	private PaisDAO daoPais;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PAISES)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoPais;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
