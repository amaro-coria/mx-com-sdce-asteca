/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CatGralDAO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.assembler.Assembler;
/**
 * @author Javier
 *
 */
@Service
public class CatGralServicioImpl extends
		BaseServiceImpl<CatGralDTO, Integer, CatGral> implements
		CatGralServicio {
	

	@Autowired
	private CatGralDAO catGralDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CATGRAL)
	private Assembler assemblerCatGral;
	
	@Override
	BaseDAO getDAO() {
		return catGralDAO;
	}

	@Override
	Assembler getAssembler() {
		// TODO Auto-generated method stub
		return assemblerCatGral;
	}

}
