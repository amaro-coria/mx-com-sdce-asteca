/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoCatGralDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.TipoCatGralDAO;
import mx.com.asteca.persistencia.entidades.TiposCatGral;
import mx.com.asteca.servicio.TipoCatGralServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class TipoCatGralServicioImpl extends
		BaseServiceImpl<TipoCatGralDTO, Short, TiposCatGral> implements
		TipoCatGralServicio {

	@Autowired
	private TipoCatGralDAO dao;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_TIPOS_CAT_GRAL)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return dao;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
