package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MunicipioDAO;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MunicipioServicioImpl extends
		BaseServiceImpl<MunicipioDTO, MunicipiosId, Municipios> implements
		MunicipioServicio {

	@Autowired
	private MunicipioDAO daoMunicipio;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MUNICIPIOS)
	private Assembler assemblerMunicipios;

	@Override
	BaseDAO getDAO() {
		return daoMunicipio;
	}

	@Override
	Assembler getAssembler() {
		return assemblerMunicipios;
	}

}
