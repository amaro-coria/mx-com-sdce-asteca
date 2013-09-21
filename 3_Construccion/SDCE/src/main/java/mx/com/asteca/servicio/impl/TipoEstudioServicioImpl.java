/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoEstudioDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.TipoEstudiosDAO;
import mx.com.asteca.persistencia.entidades.TiposEstudios;
import mx.com.asteca.servicio.TipoEstudioServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class TipoEstudioServicioImpl extends
		BaseServiceImpl<TipoEstudioDTO, Short, TiposEstudios> implements
		TipoEstudioServicio {

	@Autowired
	private TipoEstudiosDAO daoTipoEstudios;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_TIPO_ESTUDIO)
	private Assembler assemblerTipoEstudios;

	@Override
	BaseDAO getDAO() {
		return daoTipoEstudios;
	}

	@Override
	Assembler getAssembler() {
		return assemblerTipoEstudios;
	}

}
