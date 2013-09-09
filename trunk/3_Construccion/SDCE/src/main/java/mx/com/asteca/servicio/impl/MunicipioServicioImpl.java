package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.MunicipioDAO;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(readOnly=true)
	public List<MunicipioDTO> getFromEstado(int estadoID)
			throws ServicioException {
		try {
			List<Municipios> listaMunicipios = daoMunicipio
					.findByIdEstado(estadoID);
			List<MunicipioDTO> listaDTOs = assemblerMunicipios
					.getDTOListTransform(listaMunicipios);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public MunicipioDTO getFromMunicipioEdo(int estadoID, int municipioID) throws ServicioException{
		MunicipiosId id = new MunicipiosId(municipioID, estadoID);
		try {
			Municipios mpio = daoMunicipio.findByPK(id);
			MunicipioDTO dto = (MunicipioDTO) assemblerMunicipios.getDTOTransform(mpio);
			return dto;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
		
	}

}
