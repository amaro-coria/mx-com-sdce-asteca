/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoLicenciaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.TipoLicenciaDAO;
import mx.com.asteca.persistencia.entidades.TiposLicencia;
import mx.com.asteca.servicio.TipoLicenciaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class TipoLicenciaServicioImpl extends
		BaseServiceImpl<TipoLicenciaDTO, Short, TiposLicencia> implements
		TipoLicenciaServicio {

	@Autowired
	private TipoLicenciaDAO daoTipoLicencia;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_TIPO_LICENCIA)
	private Assembler assemblerTipoLicencia;

	@Override
	BaseDAO getDAO() {
		return daoTipoLicencia;
	}

	@Override
	Assembler getAssembler() {
		return assemblerTipoLicencia;
	}

}
