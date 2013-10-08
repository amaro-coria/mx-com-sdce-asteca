/**
 * 
 */
package mx.com.asteca.vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.comun.dto.FamiliaDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.ReferenciaDTO;
import mx.com.asteca.comun.dto.TipoEstudioDTO;
import mx.com.asteca.comun.dto.TipoLicenciaDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.fachada.BaseFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.util.FechaUtil;
import mx.com.asteca.util.FileExtensionUtil;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
@ManagedBean(name = Constantes.BEAN_ALUMNO)
@ViewScoped
public class AlumnoControlador extends BaseController implements Serializable {

	// TODO obtener ruta de grabado desde un properties o tabla
	// TODO reemplazar el llenado de la tabla de getAll por un get mas simple
	// que no se traiga todo el alumno,
	// solo lo que se despliega en la tabla

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_ALUMNO;
	@ManagedProperty("#{alumnoFachadaImpl}")
	private transient AlumnoFachada fachada;
	private AlumnoDTO itemSelected;
	private AlumnoDTO itemNuevo;
	private List<AlumnoDTO> listaItems;

	private List<SelectItem> listaSelect;
	private List<SelectItem> listaSelect2;
	private List<SelectItem> listaSelect3;

	private List<EstatusDTO> listaEstatus;
	private List<SelectItem> listaSelectEstatus;

	private String selectedItemFilter;
	private String selectedItemFilter2;
	private String selectedItemFilter3;

	private List<AlumnoDTO> filteredList;
	private List<CapacidadDTO> filteredListCapacidades;
	private List<CapacidadDTO> filteredListCapacidadesNuevo;
	private List<CapacidadDTO> filteredListCapacidadesEditar;
	private ReferenciaDTO referenciaSelected;
	private DomicilioDTO domiclioReferenciaSelected;
	private DocumentoDTO documentoSelected;

	private StreamedContent file;

	private String nuevoAlumnoNombre;
	private String nuevoAlumnoMatricula;
	private String nuevoAlumnoApellidoP;
	private String nuevoAlumnoApellidoM;
	private Date nuevoAlumnoFechaNac;
	private String nuevoAlumnoLugarNac;
	private String nuevoAlumnoCurp;
	private String nuevoAlumnoRfc;
	private String nuevoAlumnoPasaporte;
	private String nuevoAlumnoIfe;
	private String nuevoAlumnoEmail;
	private String nuevoAlumnoCalle;
	private String nuevoAlumnoNoExt;
	private String nuevoAlumnoColonia;
	private int nuevoAlumnoColoniaId;
	private String nuevoAlumnoDelegacion;
	private int nuevoAlumnoDelegacionId;
	private String nuevoAlumnoCiudad;
	private String nuvoAlumnoCiudadId;
	private String nuevoAlumnoEntidadFederativa;
	private int nuevoAlumnoEntidadFederetivaId;
	private String nuevoAlumnoTelefono;
	private String nuevoAlumnoNoInt;
	private String nuevoAlumnoCp;
	private int nuevoAlumnoCpIdAsentamiento;
	private List<SelectItem> nuevoAlumnoSelectListColonias;
	private String nuevoAlumnoIdAsentamientoMunicipioEstado;

	private String editarAlumnoNombre;
	private String editarAlumnoMatricula;
	private String editarAlumnoApellidoP;
	private String editarAlumnoApellidoM;
	private Date editarAlumnoFechaNac;
	private String editarAlumnoLugarNac;
	private String editarAlumnoCurp;
	private String editarAlumnoRfc;
	private String editarAlumnoPasaporte;
	private String editarAlumnoIfe;
	private String editarAlumnoEmail;
	private String editarAlumnoCalle;
	private String editarAlumnoNoExt;
	private String editarAlumnoColonia;
	private int editarAlumnoColoniaId;
	private String editarAlumnoDelegacion;
	private int editarAlumnoDelegacionId;
	private String editarAlumnoCiudad;
	private String editarAlumnoCiudadId;
	private String editarAlumnoEntidadFederativa;
	private int editarAlumnoEntidadFederetivaId;
	private String editarAlumnoTelefono;
	private String editarAlumnoNoInt;
	private String editarAlumnoCp;
	private int editarAlumnoCpIdAsentamiento;
	private List<SelectItem> editarAlumnoSelectListColonias;
	private String editarAlumnoIdAsentamientoMunicipioEstado;
	private short editarAlumnoIdEstatusSelected;

	private List<SelectItem> listaCP;
	private List<AsentamientoDTO> listaAsentamientos;
	private List<Integer> listaCPs;

	private int nuevoAlumnoTipoLicenciaSelected;
	private int editarAlumnoTipoLicenciaSelected;
	private List<SelectItem> nuevoAlumnoListSelectTipoLicencia;
	private String nuevoAlumnoCapacidad;
	private String editarAlumnoCapacidad;
	private String nuevoAlumnoCapacidadAutorizada;
	private String editarAlumnoCapacidadAutorizada;
	private String nuevoAlumnoCapacidadHoras;
	private String editarAlumnoCapacidadHoras;
	private Date nuevoAlumnoCapacidadFechaInicio;
	private Date editarAlumnoCapacidadFechaInicio;
	private Date nuevoAlumnoCapacidadFechaFin;
	private Date editarAlumnoCapacidadFechaFin;
	private String nuevoAlumnoCapacidadAcreditada;
	private String editarAlumnoCapacidadAcreditada;
	private CapacidadDTO nuevoAlumnoCapacidadSelected;
	private CapacidadDTO editarAlumnoCapacidadSelected;

	private List<TipoEstudioDTO> nuevoAlumnoListaTipoEstudios;
	private List<EstudioDTO> nuevoAlumnoListaEstudios;
	private List<EstudioDTO> editarAlumnoListaEstudios;
	
	private List<ReferenciaDTO> nuevoAlumnoListaReferencias;
	private String nuevoAlumnoConyugueNombre;
	private String editarAlumnoConyugueNombre;
	private Date nuevoAlumnoConyugueFechaNac;
	private Date editarAlumnoConyugueFechaNac;
	private String nuevoAlumnoHijo1Nombre;
	private String editarAlumnoHijo1Nombre;
	private Date nuevoAlumnoHijo1FechaNac;
	private Date editarAlumnoHijo1FechaNac;
	private String nuevoAlumnoHijo2Nombre;
	private String editarAlumnoHijo2Nombre;
	private Date nuevoAlumnoHijo2FechaNac;
	private Date editarAlumnoHijo2FechaNac;
	private String nuevoAlumnoPadreNombre;
	private String editarAlumnoPadreNombre;
	private Date nuevoAlumnoPadreFechaNac;
	private Date editarAlumnoPadreFechaNac;
	private String nuevoAlumnoMadreNombre;
	private String editarAlumnoMadreNombre;
	private Date nuevoAlumnoMadreFechaNac;
	private Date editarAlumnoMadreFechaNac;
	private ReferenciaDTO nuevoAlumnoReferenciaSelected;
	private ReferenciaDTO editarAlumnoReferenciaSelected;
	private String nuevoAlumnoReferenciaNuevaNombre;
	private String editarAlumnoReferenciaNuevaNombre;
	private String nuevoAlumnoReferenciaNuevaApellidoP;
	private String editarAlumnoReferenciaNuevaApellidoP;
	private String nuevoAlumnoReferenciaNuevaApellidoM;
	private String editarAlumnoReferenciaNuevaApellidoM;
	private int nuevoAlumnoCpIdAsentamientoReferenciaNueva;
	private int editarAlumnoCpIdAsentamientoReferenciaNueva;
	private String nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	private String editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	private List<SelectItem> nuevoAlumnoSelectListColoniasReferenciaNueva;
	private List<SelectItem> editarAlumnoSelectListColoniasReferenciaNueva;
	private String nuevoAlumnoReferenciaNuevaDelegacion;
	private String editarAlumnoReferenciaNuevaDelegacion;
	private String nuevoAlumnoReferenciaNuevaEntidadFederativa;
	private String editarAlumnoReferenciaNuevaEntidadFederativa;
	private String nuevoAlumnoReferenciaNuevaCiudad;
	private String editarAlumnoReferenciaNuevaCiudad;
	private String nuevoAlumnoReferenciaNuevaNoExt;
	private String editarAlumnoReferenciaNuevaNoExt;
	private String nuevoAlumnoReferenciaNuevaNoInt;
	private String editarAlumnoReferenciaNuevaNoInt;
	private String nuevoAlumnoReferenciaNuevaCalle;
	private String editarAlumnoReferenciaNuevaCalle;
	private String nuevoAlumnoReferenciaNuevaTel;
	private String editarAlumnoReferenciaNuevaTel;

	private DocumentoDTO nuevoAlumnoDocumentoSelected;
	private String nuevoAlumnoDocumentoNuevoNombre;
	private String editarAlumnoDocumentoNuevoNombre;
	private List<EstatusDTO> nuevoAlumnoDocListaEstatus;
	private List<SelectItem> nuevoAlumnoDocListaSelectEstatus;
	private short nuevoAlumnoDocEstatusSelected;
	private short editarAlumnoDocEstatusSelected;
	private String nuevoAlumnoDocRuta;

	private DocumentoDTO documentoTempDownload;
	private short nuevoAlumnoIdEstatusSelected;

	private String nuevoAlumnoCPAutoComplete;
	private String nuevoAlumnoReferenciaCPAutoComplete;

	private String editarAlumnoCPAutoComplete;
	private String editarAlumnoReferenciaCPAutoComplete;

	private List<String> listCPString;

	public AlumnoControlador() {
		referenciaSelected = new ReferenciaDTO();
		domiclioReferenciaSelected = new DomicilioDTO();
		itemSelected = new AlumnoDTO();
		itemNuevo = new AlumnoDTO();
		documentoSelected = new DocumentoDTO();
	}

	private void initListaCPString() {
		if (CollectionUtils.isEmpty(listCPString)) {
			listCPString = new ArrayList<String>();
			for (Integer i : getListaCPs()) {
				String s = String.valueOf(i);
				listCPString.add(s);
			}
		}
	}

	private void initListaEstatus() {
		if (CollectionUtils.isEmpty(listaEstatus)) {
			try {
				listaEstatus = fachada.getEstatus();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaSelectEstatus() {
		if (CollectionUtils.isEmpty(listaSelectEstatus)) {
			listaSelectEstatus = new ArrayList<SelectItem>();
			for (EstatusDTO dto : getListaEstatus()) {
				SelectItem item = new SelectItem(dto.getIdEstatus(),
						dto.getDescEstatus());
				listaSelectEstatus.add(item);
			}
		}

	}

	private void initNuevoAlumnoDocListaSelectEstatus() {
		if (CollectionUtils.isEmpty(nuevoAlumnoDocListaSelectEstatus)) {
			nuevoAlumnoDocListaSelectEstatus = new ArrayList<SelectItem>();
			for (EstatusDTO dto : getNuevoAlumnoDocListaEstatus()) {
				SelectItem item = new SelectItem(dto.getIdEstatus(),
						dto.getDescEstatus());
				nuevoAlumnoDocListaSelectEstatus.add(item);
			}
		}
	}

	private void initNuevoAlumnoDocListaEstatus() {
		if (CollectionUtils.isEmpty(nuevoAlumnoDocListaEstatus)) {
			try {
				nuevoAlumnoDocListaEstatus = fachada.getEstatus();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initNuevoAlumnoListaEstudios() {
		if (CollectionUtils.isEmpty(nuevoAlumnoListaEstudios)) {
			nuevoAlumnoListaEstudios = new ArrayList<EstudioDTO>();
			for (TipoEstudioDTO dto : getNuevoAlumnoListaTipoEstudios()) {
				EstudioDTO estudioDTO = new EstudioDTO();
				estudioDTO.setIdTipoEstudio(dto.getIdTipoEstudios());
				estudioDTO.setTipoEstudio(dto.getNombre());
				nuevoAlumnoListaEstudios.add(estudioDTO);
			}
		}
	}
	
	private void initEditarAlumnoListaEstudios(){
		if(itemSelected != null){
			editarAlumnoListaEstudios = itemSelected.getListaEstudios();
			if (CollectionUtils.isEmpty(editarAlumnoListaEstudios)) {
				editarAlumnoListaEstudios = new ArrayList<EstudioDTO>();
				for (TipoEstudioDTO dto : getNuevoAlumnoListaTipoEstudios()) {
					EstudioDTO estudioDTO = new EstudioDTO();
					estudioDTO.setIdTipoEstudio(dto.getIdTipoEstudios());
					estudioDTO.setTipoEstudio(dto.getNombre());
					editarAlumnoListaEstudios.add(estudioDTO);
				}
			}
		}
	}

	private void initNuevoAlumnoListaTipoEstudios() {
		if (CollectionUtils.isEmpty(nuevoAlumnoListaTipoEstudios)) {
			try {
				nuevoAlumnoListaTipoEstudios = fachada.getTiposEstudios();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initNuevoAlumnoSelectTipoLicencia() {
		if (CollectionUtils.isEmpty(nuevoAlumnoListSelectTipoLicencia)) {
			nuevoAlumnoListSelectTipoLicencia = new ArrayList<SelectItem>();
			List<TipoLicenciaDTO> listaTiposLicencia = null;
			try {
				listaTiposLicencia = fachada.getTiposLicencia();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
			for (TipoLicenciaDTO dto : listaTiposLicencia) {
				SelectItem item = new SelectItem(dto.getIdTipoLic(),
						dto.getDescTipoLic());
				nuevoAlumnoListSelectTipoLicencia.add(item);
			}
		}
	}

	private void initListaCP() {
		if (CollectionUtils.isEmpty(listaCP)) {
			listaCP = new ArrayList<SelectItem>();
			for (Integer dto : getListaCPs()) {
				SelectItem item = new SelectItem(dto, String.valueOf(dto));
				listaCP.add(item);
			}
		}
	}

	private void initListaCPs() {
		if (CollectionUtils.isEmpty(listaCPs)) {
			try {
				listaCPs = fachada.getDistincCPs();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaItems() {
		if (CollectionUtils.isEmpty(listaItems)) {
			try {
				// listaItems = fachada.getAll();
				listaItems = fachada.getAlumnosDatosBasicos();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaSelect() {
		if (CollectionUtils.isEmpty(listaSelect)) {
			listaSelect = new ArrayList<SelectItem>();
			for (AlumnoDTO dto : getListaItems()) {
				SelectItem item = new SelectItem(dto.getMatricula(),
						dto.getMatricula());
				listaSelect.add(item);
			}
		}
	}

	private void initListaSelect2() {
		if (CollectionUtils.isEmpty(listaSelect2)) {
			listaSelect2 = new ArrayList<SelectItem>();
			for (AlumnoDTO dto : getListaItems()) {
				SelectItem item = new SelectItem(dto.getNombre(),
						dto.getNombre());
				listaSelect2.add(item);
			}
		}
	}

	private void initListaSelect3() {
		if (CollectionUtils.isEmpty(listaSelect3)) {
			listaSelect3 = new ArrayList<SelectItem>();
			for (AlumnoDTO dto : getListaItems()) {
				SelectItem item = new SelectItem(dto.getEstatus(),
						dto.getEstatus());
				listaSelect3.add(item);
			}
		}
	}

	public List<String> complete(String cp) {
		List<String> results = new ArrayList<String>();
		for (String s : getListCPString()) {
			if (s.startsWith(cp)) {
				results.add(s);
			}
		}
		return results;
	}

	public void editAlumno() {
		showVer();
	}

	public void addAlumno() {
		itemNuevo.setListaEstudios(nuevoAlumnoListaEstudios);
		itemNuevo.setMatricula(nuevoAlumnoMatricula);
		itemNuevo.setIdEstatus(nuevoAlumnoIdEstatusSelected);
		PersonaDTO personaDTO = new PersonaDTO();
		personaDTO.setApellidoM(nuevoAlumnoApellidoM);
		personaDTO.setApellidoP(nuevoAlumnoApellidoP);
		personaDTO.setCurp(nuevoAlumnoCurp);
		personaDTO.setEmail(nuevoAlumnoEmail);
		personaDTO.setFechaNac(nuevoAlumnoFechaNac);
		personaDTO.setIfe(nuevoAlumnoIfe);
		personaDTO.setLugarNac(nuevoAlumnoLugarNac);
		personaDTO.setNombre(nuevoAlumnoNombre);
		personaDTO.setPasaporte(nuevoAlumnoPasaporte);
		personaDTO.setRfc(nuevoAlumnoRfc);
		DomicilioDTO domicilioDTO = new DomicilioDTO();
		domicilioDTO.setCalle(nuevoAlumnoCalle);
		domicilioDTO.setCp(nuevoAlumnoCpIdAsentamiento);
		String[] datosDemograficos = nuevoAlumnoIdAsentamientoMunicipioEstado
				.split("\\|");
		int idAsentamientoTemp = Integer.parseInt(datosDemograficos[0]);
		int idMunicipioTemp = Integer.parseInt(datosDemograficos[1]);
		int idEstadoTemp = Integer.parseInt(datosDemograficos[2]);
		domicilioDTO.setIdAsentamiento(idAsentamientoTemp);
		domicilioDTO.setIdEstado(idEstadoTemp);
		domicilioDTO.setIdMunicipio(idMunicipioTemp);
		domicilioDTO.setNoExterior(nuevoAlumnoNoExt);
		domicilioDTO.setNoInterior(nuevoAlumnoNoInt);
		domicilioDTO.setTelefono(nuevoAlumnoTelefono);
		FamiliaDTO familiaDTO = new FamiliaDTO();
		familiaDTO.setConyugueFechaNac(nuevoAlumnoConyugueFechaNac);
		familiaDTO.setConyugueNombre(nuevoAlumnoConyugueNombre);
		familiaDTO.setHijo1FechaNac(nuevoAlumnoHijo1FechaNac);
		familiaDTO.setHijo1Nombre(nuevoAlumnoHijo1Nombre);
		familiaDTO.setHijo2FechaNac(nuevoAlumnoHijo2FechaNac);
		familiaDTO.setHijo2Nombre(nuevoAlumnoHijo2Nombre);
		familiaDTO.setMadreFechaNac(nuevoAlumnoMadreFechaNac);
		familiaDTO.setMadreNombre(nuevoAlumnoMadreNombre);
		familiaDTO.setPadreFechaNac(nuevoAlumnoPadreFechaNac);
		familiaDTO.setPadreNombre(nuevoAlumnoPadreNombre);
		itemNuevo.setDtoDomicilio(domicilioDTO);
		itemNuevo.setDtoPersona(personaDTO);
		itemNuevo.setDtoFamilia(familiaDTO);
		try {
			int alumnoID = fachada.save(itemNuevo);
			itemNuevo.setIdAlumno(alumnoID);
			listaItems.add(itemNuevo);
			listaSelect = null;
			initListaSelect();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
		}

	}

	public void handlerNuevoAlumnoFileUpload(FileUploadEvent event) {
		try {
			String ruta = fachada.getRuta();
			File targetFolder = new File(ruta);
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			nuevoAlumnoDocRuta = targetFolder.getAbsolutePath()
					+ targetFolder.separator + event.getFile().getFileName();
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FachadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleSelectCambiaCpEditar(SelectEvent e) {
		String selection = e.getObject().toString();
		editarAlumnoCPAutoComplete = selection;
		handlerCambiaCpEditar();
	}

	public void handleSelectCambiaCp(SelectEvent e) {
		String selection = e.getObject().toString();
		nuevoAlumnoCPAutoComplete = selection;
		handlerCambiaCp();
	}

	public void handleSelectCambiaCPReferenciaEditar(SelectEvent e){
		String selection = e.getObject().toString();
		editarAlumnoReferenciaCPAutoComplete = selection;
		handlerCambiaCpReferenciaEditar();
	}
	
	public void handleSelectCambiaCPReferenciaNueva(SelectEvent e) {
		String selection = e.getObject().toString();
		nuevoAlumnoReferenciaCPAutoComplete = selection;
		handlerCambiaCpReferenciaNueva();
	}

	public void handlerCambiaCpEditar() {
		try {
			editarAlumnoCpIdAsentamiento = Integer
					.parseInt(editarAlumnoCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (editarAlumnoCpIdAsentamiento != 0) {
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(editarAlumnoCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				editarAlumnoSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				editarAlumnoDelegacion = municipio.getNombre();
				editarAlumnoEntidadFederativa = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					editarAlumnoSelectListColonias.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void handlerCambiaCp() {
		try {
			nuevoAlumnoCpIdAsentamiento = Integer
					.parseInt(nuevoAlumnoCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (nuevoAlumnoCpIdAsentamiento != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(nuevoAlumnoCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				nuevoAlumnoSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				nuevoAlumnoDelegacion = municipio.getNombre();
				nuevoAlumnoEntidadFederativa = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					nuevoAlumnoSelectListColonias.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void handlerCambiaCpReferenciaEditar(){
		try {
			editarAlumnoCpIdAsentamientoReferenciaNueva = Integer
					.parseInt(editarAlumnoReferenciaCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
		}
		if (editarAlumnoCpIdAsentamientoReferenciaNueva != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(editarAlumnoCpIdAsentamientoReferenciaNueva);
				// if(listaAsentamientos.size() > 1){
				editarAlumnoSelectListColoniasReferenciaNueva = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				editarAlumnoReferenciaNuevaDelegacion = municipio.getNombre();
				editarAlumnoReferenciaNuevaEntidadFederativa = estado
						.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					editarAlumnoSelectListColoniasReferenciaNueva.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	public void handlerCambiaCpReferenciaNueva() {
		try {
			nuevoAlumnoCpIdAsentamientoReferenciaNueva = Integer
					.parseInt(nuevoAlumnoReferenciaCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
		}
		if (nuevoAlumnoCpIdAsentamientoReferenciaNueva != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(nuevoAlumnoCpIdAsentamientoReferenciaNueva);
				// if(listaAsentamientos.size() > 1){
				nuevoAlumnoSelectListColoniasReferenciaNueva = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				nuevoAlumnoReferenciaNuevaDelegacion = municipio.getNombre();
				nuevoAlumnoReferenciaNuevaEntidadFederativa = estado
						.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					nuevoAlumnoSelectListColoniasReferenciaNueva.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaItems = null;
		initListaItems();
		setSelectedItemFilter("");
		setSelectedItemFilter2("");
		setSelectedItemFilter3("");

	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			boolean matriculaB = (selectedItemFilter != null && !selectedItemFilter
					.isEmpty());
			boolean nombreB = (selectedItemFilter2 != null && !selectedItemFilter2
					.isEmpty());
			boolean estatusB = (selectedItemFilter3 != null && !selectedItemFilter3
					.isEmpty());
			if (matriculaB && nombreB && estatusB) {
				listaItems = fachada.findByMatriculaNombreAndStatus(
						selectedItemFilter, selectedItemFilter2,
						selectedItemFilter3);
			} else if (matriculaB && nombreB) {
				listaItems = fachada.findByMatriculaAndNombre(
						selectedItemFilter3, selectedItemFilter2);
			} else if (matriculaB && estatusB) {
				listaItems = fachada.findByMatriculaAndStatus(
						selectedItemFilter, selectedItemFilter3);
			} else if (nombreB && estatusB) {
				listaItems = fachada.findByNombreAndStatus(selectedItemFilter2,
						selectedItemFilter3);
			} else if (matriculaB) {
				listaItems = fachada.findByMatricula(selectedItemFilter);
			} else if (nombreB) {
				listaItems = fachada.findByNombre(selectedItemFilter2);
			} else if (estatusB) {
				listaItems = fachada.findByEstatus(selectedItemFilter3);
			} else {
				listaItems = fachada.getAll();
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}

	/**
	 * Cancela el borrado del registro seleccionado.
	 * 
	 * @param e
	 */
	public void cancelDelete(ActionEvent e) {
		setSelectedItemFilter("");
		setSelectedItemFilter2("");
		setSelectedItemFilter3("");
	}

	/**
	 * Borra el item seleccionado
	 * 
	 * @param e
	 */
	public void delete(ActionEvent e) {
		try {
			fachada.remove(itemSelected);
			listaItems.remove(itemSelected);
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		setSelectedItemFilter("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}

	/**
	 * Cancela el guardar nuevo registro
	 * 
	 * @param e
	 */
	public void saveCancel(ActionEvent e) {
		itemNuevo = new AlumnoDTO();
	}

	public void deleteAlumnoNuevoCapacidad() {
		List<CapacidadDTO> listaCapacidades = itemNuevo.getListaCapacidades();
		listaCapacidades.remove(nuevoAlumnoCapacidadSelected);
		itemNuevo.setListaCapacidades(listaCapacidades);
	}
	
	public void deleteAlumnoEditarCapacidad(){
		List<CapacidadDTO> listaCapacidades = itemSelected.getListaCapacidades();
		listaCapacidades.remove(editarAlumnoCapacidadSelected);
		itemSelected.setListaCapacidades(listaCapacidades);
	}

	public void addAlumnoNuevoDocumento() {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setIdEstatus(nuevoAlumnoDocEstatusSelected);
		documentoDTO.setNombre(nuevoAlumnoDocumentoNuevoNombre);
		documentoDTO.setRuta(nuevoAlumnoDocRuta);
		List<DocumentoDTO> listaTempDocs = itemNuevo.getListaDocumentos();
		if (CollectionUtils.isEmpty(listaTempDocs)) {
			listaTempDocs = new ArrayList<DocumentoDTO>();
		}
		listaTempDocs.add(documentoDTO);
		itemNuevo.setListaDocumentos(listaTempDocs);
	}

	public void addAlumnoEditarReferencia(){
		ReferenciaDTO dtoReferencia = new ReferenciaDTO();
		DomicilioDTO dtoDomicilioDTO = new DomicilioDTO();
		PersonaDTO dtoPersona = new PersonaDTO();
		dtoPersona.setApellidoM(editarAlumnoReferenciaNuevaApellidoM);
		dtoPersona.setApellidoP(editarAlumnoReferenciaNuevaApellidoP);
		dtoPersona.setNombre(editarAlumnoReferenciaNuevaNombre);
		String[] datosDemograficos = editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva
				.split("\\|");
		int asentamientoTempId = Integer.parseInt(datosDemograficos[0]);
		int municipioTempId = Integer.parseInt(datosDemograficos[1]);
		int estadoTempId = Integer.parseInt(datosDemograficos[2]);
		AsentamientoDTO asentamientoDTO;
		try {
			asentamientoDTO = fachada.findAsentamiento(asentamientoTempId,
					municipioTempId, estadoTempId);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dtoDomicilioDTO.setAsentamiento(asentamientoDTO.getNombre());
		dtoDomicilioDTO.setCalle(editarAlumnoReferenciaNuevaCalle);
		dtoDomicilioDTO.setCp(editarAlumnoCpIdAsentamientoReferenciaNueva);
		dtoDomicilioDTO.setEstado(editarAlumnoReferenciaNuevaEntidadFederativa);
		dtoDomicilioDTO.setIdAsentamiento(asentamientoTempId);
		dtoDomicilioDTO.setIdEstado(estadoTempId);
		dtoDomicilioDTO.setIdMunicipio(municipioTempId);
		dtoDomicilioDTO.setMunicipio(editarAlumnoReferenciaNuevaDelegacion);
		dtoDomicilioDTO.setNoExterior(editarAlumnoReferenciaNuevaNoExt);
		dtoDomicilioDTO.setNoInterior(editarAlumnoReferenciaNuevaNoInt);
		dtoDomicilioDTO.setTelefono(editarAlumnoReferenciaNuevaTel);
		dtoReferencia.setDtoDomicilio(dtoDomicilioDTO);
		dtoReferencia.setDtoPersona(dtoPersona);
		List<ReferenciaDTO> listaReferencias = itemSelected.getListaReferencias();
		if (CollectionUtils.isEmpty(listaReferencias)) {
			listaReferencias = new ArrayList<ReferenciaDTO>();
		}
		listaReferencias.add(dtoReferencia);
		itemSelected.setListaReferencias(listaReferencias);
		editarAlumnoReferenciaNuevaApellidoM = "";
		editarAlumnoReferenciaNuevaApellidoP = "";
		editarAlumnoReferenciaNuevaNombre = "";
		editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva = "";
		editarAlumnoReferenciaNuevaCalle = "";
		editarAlumnoCpIdAsentamientoReferenciaNueva = 0;
		editarAlumnoReferenciaNuevaEntidadFederativa = "";
		editarAlumnoReferenciaNuevaDelegacion = "";
		editarAlumnoReferenciaNuevaNoExt = "";
		editarAlumnoReferenciaNuevaNoInt = "";
		editarAlumnoReferenciaNuevaTel = "";
		editarAlumnoReferenciaNuevaEntidadFederativa = "";
	}
	
	public void addAlumnoNuevoReferencia() {
		ReferenciaDTO dtoReferencia = new ReferenciaDTO();
		DomicilioDTO dtoDomicilioDTO = new DomicilioDTO();
		PersonaDTO dtoPersona = new PersonaDTO();
		dtoPersona.setApellidoM(nuevoAlumnoReferenciaNuevaApellidoM);
		dtoPersona.setApellidoP(nuevoAlumnoReferenciaNuevaApellidoP);
		dtoPersona.setNombre(nuevoAlumnoReferenciaNuevaNombre);
		String[] datosDemograficos = nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva
				.split("\\|");
		int asentamientoTempId = Integer.parseInt(datosDemograficos[0]);
		int municipioTempId = Integer.parseInt(datosDemograficos[1]);
		int estadoTempId = Integer.parseInt(datosDemograficos[2]);
		AsentamientoDTO asentamientoDTO;
		try {
			asentamientoDTO = fachada.findAsentamiento(asentamientoTempId,
					municipioTempId, estadoTempId);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dtoDomicilioDTO.setAsentamiento(asentamientoDTO.getNombre());
		dtoDomicilioDTO.setCalle(nuevoAlumnoReferenciaNuevaCalle);
		dtoDomicilioDTO.setCp(nuevoAlumnoCpIdAsentamientoReferenciaNueva);
		dtoDomicilioDTO.setEstado(nuevoAlumnoReferenciaNuevaEntidadFederativa);
		dtoDomicilioDTO.setIdAsentamiento(asentamientoTempId);
		dtoDomicilioDTO.setIdEstado(estadoTempId);
		dtoDomicilioDTO.setIdMunicipio(municipioTempId);
		dtoDomicilioDTO.setMunicipio(nuevoAlumnoReferenciaNuevaDelegacion);
		dtoDomicilioDTO.setNoExterior(nuevoAlumnoReferenciaNuevaNoExt);
		dtoDomicilioDTO.setNoInterior(nuevoAlumnoReferenciaNuevaNoInt);
		dtoDomicilioDTO.setTelefono(nuevoAlumnoReferenciaNuevaTel);
		dtoReferencia.setDtoDomicilio(dtoDomicilioDTO);
		dtoReferencia.setDtoPersona(dtoPersona);
		List<ReferenciaDTO> listaReferencias = itemNuevo.getListaReferencias();
		if (CollectionUtils.isEmpty(listaReferencias)) {
			listaReferencias = new ArrayList<ReferenciaDTO>();
		}
		listaReferencias.add(dtoReferencia);
		itemNuevo.setListaReferencias(listaReferencias);
		nuevoAlumnoReferenciaNuevaApellidoM = "";
		nuevoAlumnoReferenciaNuevaApellidoP = "";
		nuevoAlumnoReferenciaNuevaNombre = "";
		nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva = "";
		nuevoAlumnoReferenciaNuevaCalle = "";
		nuevoAlumnoCpIdAsentamientoReferenciaNueva = 0;
		nuevoAlumnoReferenciaNuevaEntidadFederativa = "";
		nuevoAlumnoReferenciaNuevaDelegacion = "";
		nuevoAlumnoReferenciaNuevaNoExt = "";
		nuevoAlumnoReferenciaNuevaNoInt = "";
		nuevoAlumnoReferenciaNuevaTel = "";
		nuevoAlumnoReferenciaNuevaEntidadFederativa = "";
	}

	/**
	 * Agrega una nueva capacidad al alumno temporalmente, aun no se baja a BD,
	 * se mantiene en el DTO de AlumnoDTO
	 */
	public void addAlumnoNuevoCapacidad() {
		CapacidadDTO dtoCapacidad = new CapacidadDTO();
		dtoCapacidad.setTipoCapacidad(nuevoAlumnoCapacidad);
		dtoCapacidad.setAcreditada(nuevoAlumnoCapacidadAcreditada);
		dtoCapacidad.setAutorizada(nuevoAlumnoCapacidadAutorizada);
		dtoCapacidad.setFechaFin(FechaUtil.getInstance().parseDateMM_dd_yy(
				nuevoAlumnoCapacidadFechaFin));
		dtoCapacidad.setFechaInic(FechaUtil.getInstance().parseDateMM_dd_yy(
				nuevoAlumnoCapacidadFechaInicio));
		try {
			dtoCapacidad.setHoras(Integer.parseInt(nuevoAlumnoCapacidadHoras));
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		dtoCapacidad.setIdTipoLicencia(nuevoAlumnoTipoLicenciaSelected);
		TipoLicenciaDTO tipoLicencia;
		try {
			tipoLicencia = fachada.getLicencia(nuevoAlumnoTipoLicenciaSelected);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dtoCapacidad.setTipoLicencia(tipoLicencia.getDescTipoLic());
		List<CapacidadDTO> listaCapacidades = itemNuevo.getListaCapacidades();
		if (CollectionUtils.isEmpty(listaCapacidades)) {
			listaCapacidades = new ArrayList<CapacidadDTO>();
		}
		listaCapacidades.add(dtoCapacidad);
		itemNuevo.setListaCapacidades(listaCapacidades);
		nuevoAlumnoCapacidad = "";
		nuevoAlumnoCapacidadAcreditada = "";
		nuevoAlumnoCapacidadAutorizada = "";
		nuevoAlumnoCapacidadFechaFin = null;
		nuevoAlumnoCapacidadFechaInicio = null;
		nuevoAlumnoCapacidadHoras = "";
	}
	
	public void addAlumnoEditarCapacidad(){
		CapacidadDTO dtoCapacidad = new CapacidadDTO();
		dtoCapacidad.setTipoCapacidad(editarAlumnoCapacidad);
		dtoCapacidad.setAcreditada(editarAlumnoCapacidadAcreditada);
		dtoCapacidad.setAutorizada(editarAlumnoCapacidadAutorizada);
		dtoCapacidad.setFechaFin(FechaUtil.getInstance().parseDateMM_dd_yy(
				editarAlumnoCapacidadFechaFin));
		dtoCapacidad.setFechaInic(FechaUtil.getInstance().parseDateMM_dd_yy(
				editarAlumnoCapacidadFechaInicio));
		try {
			dtoCapacidad.setHoras(Integer.parseInt(editarAlumnoCapacidadHoras));
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		dtoCapacidad.setIdTipoLicencia(editarAlumnoTipoLicenciaSelected);
		TipoLicenciaDTO tipoLicencia;
		try {
			tipoLicencia = fachada.getLicencia(editarAlumnoTipoLicenciaSelected);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dtoCapacidad.setTipoLicencia(tipoLicencia.getDescTipoLic());
		List<CapacidadDTO> listaCapacidades = itemSelected.getListaCapacidades();
		if (CollectionUtils.isEmpty(listaCapacidades)) {
			listaCapacidades = new ArrayList<CapacidadDTO>();
		}
		listaCapacidades.add(dtoCapacidad);
		itemSelected.setListaCapacidades(listaCapacidades);
		editarAlumnoCapacidad = "";
		editarAlumnoCapacidadAcreditada = "";
		editarAlumnoCapacidadAutorizada = "";
		editarAlumnoCapacidadFechaFin = null;
		editarAlumnoCapacidadFechaInicio = null;
		editarAlumnoCapacidadHoras = "";
	}

	/**
	 * Inicializa el componente para que el panel de Ver pueda ser accedido
	 */
	public void showVer() {
		try {
			if(itemSelected==null || itemSelected.getIdAlumno() == 0){
				super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,"Necesita seleccionar un elemento");
			}else{
				itemSelected = fachada.populate(itemSelected.getIdAlumno());
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_SELECT_ITEM);
			return;
		}
	}

	public void onReferenciaSelect(SelectEvent event) {
		referenciaSelected = (ReferenciaDTO) event.getObject();
		domiclioReferenciaSelected = referenciaSelected.getDtoDomicilio();
	}

	public void onDocumentoSelect(SelectEvent event) {
		documentoSelected = (DocumentoDTO) event.getObject();
		documentoTempDownload = (DocumentoDTO) event.getObject();
	}

	// ---------------------------- GETTERS AND SETTERS
	// ---------------------------------------- //

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(AlumnoFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the itemSelected
	 */
	public AlumnoDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected
	 *            the itemSelected to set
	 */
	public void setItemSelected(AlumnoDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the itemNuevo
	 */
	public AlumnoDTO getItemNuevo() {
		return itemNuevo;
	}

	/**
	 * @param itemNuevo
	 *            the itemNuevo to set
	 */
	public void setItemNuevo(AlumnoDTO itemNuevo) {
		this.itemNuevo = itemNuevo;
	}

	/**
	 * @return the listaItems
	 */
	public List<AlumnoDTO> getListaItems() {
		initListaItems();
		return listaItems;
	}

	/**
	 * @param listaItems
	 *            the listaItems to set
	 */
	public void setListaItems(List<AlumnoDTO> listaItems) {
		this.listaItems = listaItems;
	}

	/**
	 * @return the selectedItemFilter
	 */
	public String getSelectedItemFilter() {
		return selectedItemFilter;
	}

	/**
	 * @param selectedItemFilter
	 *            the selectedItemFilter to set
	 */
	public void setSelectedItemFilter(String selectedItemFilter) {
		this.selectedItemFilter = selectedItemFilter;
	}

	/**
	 * @return the selectedItemFilter2
	 */
	public String getSelectedItemFilter2() {
		return selectedItemFilter2;
	}

	/**
	 * @param selectedItemFilter2
	 *            the selectedItemFilter2 to set
	 */
	public void setSelectedItemFilter2(String selectedItemFilter2) {
		this.selectedItemFilter2 = selectedItemFilter2;
	}

	/**
	 * @return the selectedItemFilter3
	 */
	public String getSelectedItemFilter3() {
		return selectedItemFilter3;
	}

	/**
	 * @param selectedItemFilter3
	 *            the selectedItemFilter3 to set
	 */
	public void setSelectedItemFilter3(String selectedItemFilter3) {
		this.selectedItemFilter3 = selectedItemFilter3;
	}

	/**
	 * @return the filteredList
	 */
	public List<AlumnoDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<AlumnoDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the listaSelect
	 */
	public List<SelectItem> getListaSelect() {
		initListaSelect();
		return listaSelect;
	}

	/**
	 * @param listaSelect
	 *            the listaSelect to set
	 */
	public void setListaSelect(List<SelectItem> listaSelect) {
		this.listaSelect = listaSelect;
	}

	/**
	 * @return the listaSelect2
	 */
	public List<SelectItem> getListaSelect2() {
		initListaSelect2();
		return listaSelect2;
	}

	/**
	 * @param listaSelect2
	 *            the listaSelect2 to set
	 */
	public void setListaSelect2(List<SelectItem> listaSelect2) {
		this.listaSelect2 = listaSelect2;
	}

	/**
	 * @return the listaSelect3
	 */
	public List<SelectItem> getListaSelect3() {
		initListaSelect3();
		return listaSelect3;
	}

	/**
	 * @param listaSelect3
	 *            the listaSelect3 to set
	 */
	public void setListaSelect3(List<SelectItem> listaSelect3) {
		this.listaSelect3 = listaSelect3;
	}

	/**
	 * @return the filteredListCapacidades
	 */
	public List<CapacidadDTO> getFilteredListCapacidades() {
		return filteredListCapacidades;
	}

	/**
	 * @param filteredListCapacidades
	 *            the filteredListCapacidades to set
	 */
	public void setFilteredListCapacidades(
			List<CapacidadDTO> filteredListCapacidades) {
		this.filteredListCapacidades = filteredListCapacidades;
	}

	/**
	 * @return the referenciaSelected
	 */
	public ReferenciaDTO getReferenciaSelected() {
		return referenciaSelected;
	}

	/**
	 * @param referenciaSelected
	 *            the referenciaSelected to set
	 */
	public void setReferenciaSelected(ReferenciaDTO referenciaSelected) {
		this.referenciaSelected = referenciaSelected;
	}

	/**
	 * @return the domiclioReferenciaSelected
	 */
	public DomicilioDTO getDomiclioReferenciaSelected() {
		return domiclioReferenciaSelected;
	}

	/**
	 * @param domiclioReferenciaSelected
	 *            the domiclioReferenciaSelected to set
	 */
	public void setDomiclioReferenciaSelected(
			DomicilioDTO domiclioReferenciaSelected) {
		this.domiclioReferenciaSelected = domiclioReferenciaSelected;
	}

	/**
	 * @return the documentoSelected
	 */
	public DocumentoDTO getDocumentoSelected() {
		return documentoSelected;
	}

	/**
	 * @param documentoSelected
	 *            the documentoSelected to set
	 */
	public void setDocumentoSelected(DocumentoDTO documentoSelected) {
		this.documentoSelected = documentoSelected;
	}

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		if (documentoTempDownload != null
				&& documentoTempDownload.getRuta() != null
				&& !documentoTempDownload.getRuta().isEmpty()) {
			String ruta = documentoTempDownload.getRuta();
			String nombre = documentoTempDownload.getNombre();
			// TODO String extension ....
			String[] paths = ruta.split("\\.");
			String realExtension = paths[paths.length - 1];
			String mimeMapping = FileExtensionUtil.getInstance()
					.getMimeMapping(realExtension);
			InputStream stream = null;
			try {
				stream = new FileInputStream(ruta);
			} catch (FileNotFoundException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, "Archivo no disponible");
			}
			file = new DefaultStreamedContent(stream, mimeMapping, nombre + "."
					+ realExtension);
		}
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}

	/**
	 * @return the listaCP
	 */
	public List<SelectItem> getListaCP() {
		initListaCP();
		return listaCP;
	}

	/**
	 * @param listaCP
	 *            the listaCP to set
	 */
	public void setListaCP(List<SelectItem> listaCP) {
		this.listaCP = listaCP;
	}

	/**
	 * @return the nuevoAlumnoNombre
	 */
	public String getNuevoAlumnoNombre() {
		return nuevoAlumnoNombre;
	}

	/**
	 * @param nuevoAlumnoNombre
	 *            the nuevoAlumnoNombre to set
	 */
	public void setNuevoAlumnoNombre(String nuevoAlumnoNombre) {
		this.nuevoAlumnoNombre = nuevoAlumnoNombre;
	}

	/**
	 * @return the nuevoAlumnoMatricula
	 */
	public String getNuevoAlumnoMatricula() {
		return nuevoAlumnoMatricula;
	}

	/**
	 * @param nuevoAlumnoMatricula
	 *            the nuevoAlumnoMatricula to set
	 */
	public void setNuevoAlumnoMatricula(String nuevoAlumnoMatricula) {
		this.nuevoAlumnoMatricula = nuevoAlumnoMatricula;
	}

	/**
	 * @return the nuevoAlumnoApellidoP
	 */
	public String getNuevoAlumnoApellidoP() {
		return nuevoAlumnoApellidoP;
	}

	/**
	 * @param nuevoAlumnoApellidoP
	 *            the nuevoAlumnoApellidoP to set
	 */
	public void setNuevoAlumnoApellidoP(String nuevoAlumnoApellidoP) {
		this.nuevoAlumnoApellidoP = nuevoAlumnoApellidoP;
	}

	/**
	 * @return the nuevoAlumnoApellidoM
	 */
	public String getNuevoAlumnoApellidoM() {
		return nuevoAlumnoApellidoM;
	}

	/**
	 * @param nuevoAlumnoApellidoM
	 *            the nuevoAlumnoApellidoM to set
	 */
	public void setNuevoAlumnoApellidoM(String nuevoAlumnoApellidoM) {
		this.nuevoAlumnoApellidoM = nuevoAlumnoApellidoM;
	}

	/**
	 * @return the nuevoAlumnoFechaNac
	 */
	public Date getNuevoAlumnoFechaNac() {
		return nuevoAlumnoFechaNac;
	}

	/**
	 * @param nuevoAlumnoFechaNac
	 *            the nuevoAlumnoFechaNac to set
	 */
	public void setNuevoAlumnoFechaNac(Date nuevoAlumnoFechaNac) {
		this.nuevoAlumnoFechaNac = nuevoAlumnoFechaNac;
	}

	/**
	 * @return the nuevoAlumnoLugarNac
	 */
	public String getNuevoAlumnoLugarNac() {
		return nuevoAlumnoLugarNac;
	}

	/**
	 * @param nuevoAlumnoLugarNac
	 *            the nuevoAlumnoLugarNac to set
	 */
	public void setNuevoAlumnoLugarNac(String nuevoAlumnoLugarNac) {
		this.nuevoAlumnoLugarNac = nuevoAlumnoLugarNac;
	}

	/**
	 * @return the nuevoAlumnoCurp
	 */
	public String getNuevoAlumnoCurp() {
		return nuevoAlumnoCurp;
	}

	/**
	 * @param nuevoAlumnoCurp
	 *            the nuevoAlumnoCurp to set
	 */
	public void setNuevoAlumnoCurp(String nuevoAlumnoCurp) {
		this.nuevoAlumnoCurp = nuevoAlumnoCurp;
	}

	/**
	 * @return the nuevoAlumnoRfc
	 */
	public String getNuevoAlumnoRfc() {
		return nuevoAlumnoRfc;
	}

	/**
	 * @param nuevoAlumnoRfc
	 *            the nuevoAlumnoRfc to set
	 */
	public void setNuevoAlumnoRfc(String nuevoAlumnoRfc) {
		this.nuevoAlumnoRfc = nuevoAlumnoRfc;
	}

	/**
	 * @return the nuevoAlumnoPasaporte
	 */
	public String getNuevoAlumnoPasaporte() {
		return nuevoAlumnoPasaporte;
	}

	/**
	 * @param nuevoAlumnoPasaporte
	 *            the nuevoAlumnoPasaporte to set
	 */
	public void setNuevoAlumnoPasaporte(String nuevoAlumnoPasaporte) {
		this.nuevoAlumnoPasaporte = nuevoAlumnoPasaporte;
	}

	/**
	 * @return the nuevoAlumnoIfe
	 */
	public String getNuevoAlumnoIfe() {
		return nuevoAlumnoIfe;
	}

	/**
	 * @param nuevoAlumnoIfe
	 *            the nuevoAlumnoIfe to set
	 */
	public void setNuevoAlumnoIfe(String nuevoAlumnoIfe) {
		this.nuevoAlumnoIfe = nuevoAlumnoIfe;
	}

	/**
	 * @return the nuevoAlumnoEmail
	 */
	public String getNuevoAlumnoEmail() {
		return nuevoAlumnoEmail;
	}

	/**
	 * @param nuevoAlumnoEmail
	 *            the nuevoAlumnoEmail to set
	 */
	public void setNuevoAlumnoEmail(String nuevoAlumnoEmail) {
		this.nuevoAlumnoEmail = nuevoAlumnoEmail;
	}

	/**
	 * @return the nuevoAlumnoCalle
	 */
	public String getNuevoAlumnoCalle() {
		return nuevoAlumnoCalle;
	}

	/**
	 * @param nuevoAlumnoCalle
	 *            the nuevoAlumnoCalle to set
	 */
	public void setNuevoAlumnoCalle(String nuevoAlumnoCalle) {
		this.nuevoAlumnoCalle = nuevoAlumnoCalle;
	}

	/**
	 * @return the nuevoAlumnoNoExt
	 */
	public String getNuevoAlumnoNoExt() {
		return nuevoAlumnoNoExt;
	}

	/**
	 * @param nuevoAlumnoNoExt
	 *            the nuevoAlumnoNoExt to set
	 */
	public void setNuevoAlumnoNoExt(String nuevoAlumnoNoExt) {
		this.nuevoAlumnoNoExt = nuevoAlumnoNoExt;
	}

	/**
	 * @return the nuevoAlumnoColonia
	 */
	public String getNuevoAlumnoColonia() {
		return nuevoAlumnoColonia;
	}

	/**
	 * @param nuevoAlumnoColonia
	 *            the nuevoAlumnoColonia to set
	 */
	public void setNuevoAlumnoColonia(String nuevoAlumnoColonia) {
		this.nuevoAlumnoColonia = nuevoAlumnoColonia;
	}

	/**
	 * @return the nuevoAlumnoColoniaId
	 */
	public int getNuevoAlumnoColoniaId() {
		return nuevoAlumnoColoniaId;
	}

	/**
	 * @param nuevoAlumnoColoniaId
	 *            the nuevoAlumnoColoniaId to set
	 */
	public void setNuevoAlumnoColoniaId(int nuevoAlumnoColoniaId) {
		this.nuevoAlumnoColoniaId = nuevoAlumnoColoniaId;
	}

	/**
	 * @return the nuevoAlumnoDelegacion
	 */
	public String getNuevoAlumnoDelegacion() {
		return nuevoAlumnoDelegacion;
	}

	/**
	 * @param nuevoAlumnoDelegacion
	 *            the nuevoAlumnoDelegacion to set
	 */
	public void setNuevoAlumnoDelegacion(String nuevoAlumnoDelegacion) {
		this.nuevoAlumnoDelegacion = nuevoAlumnoDelegacion;
	}

	/**
	 * @return the nuevoAlumnoDelegacionId
	 */
	public int getNuevoAlumnoDelegacionId() {
		return nuevoAlumnoDelegacionId;
	}

	/**
	 * @param nuevoAlumnoDelegacionId
	 *            the nuevoAlumnoDelegacionId to set
	 */
	public void setNuevoAlumnoDelegacionId(int nuevoAlumnoDelegacionId) {
		this.nuevoAlumnoDelegacionId = nuevoAlumnoDelegacionId;
	}

	/**
	 * @return the nuevoAlumnoCiudad
	 */
	public String getNuevoAlumnoCiudad() {
		return nuevoAlumnoCiudad;
	}

	/**
	 * @param nuevoAlumnoCiudad
	 *            the nuevoAlumnoCiudad to set
	 */
	public void setNuevoAlumnoCiudad(String nuevoAlumnoCiudad) {
		this.nuevoAlumnoCiudad = nuevoAlumnoCiudad;
	}

	/**
	 * @return the nuvoAlumnoCiudadId
	 */
	public String getNuvoAlumnoCiudadId() {
		return nuvoAlumnoCiudadId;
	}

	/**
	 * @param nuvoAlumnoCiudadId
	 *            the nuvoAlumnoCiudadId to set
	 */
	public void setNuvoAlumnoCiudadId(String nuvoAlumnoCiudadId) {
		this.nuvoAlumnoCiudadId = nuvoAlumnoCiudadId;
	}

	/**
	 * @return the nuevoAlumnoEntidadFederativa
	 */
	public String getNuevoAlumnoEntidadFederativa() {
		return nuevoAlumnoEntidadFederativa;
	}

	/**
	 * @param nuevoAlumnoEntidadFederativa
	 *            the nuevoAlumnoEntidadFederativa to set
	 */
	public void setNuevoAlumnoEntidadFederativa(
			String nuevoAlumnoEntidadFederativa) {
		this.nuevoAlumnoEntidadFederativa = nuevoAlumnoEntidadFederativa;
	}

	/**
	 * @return the nuevoAlumnoEntidadFederetivaId
	 */
	public int getNuevoAlumnoEntidadFederetivaId() {
		return nuevoAlumnoEntidadFederetivaId;
	}

	/**
	 * @param nuevoAlumnoEntidadFederetivaId
	 *            the nuevoAlumnoEntidadFederetivaId to set
	 */
	public void setNuevoAlumnoEntidadFederetivaId(
			int nuevoAlumnoEntidadFederetivaId) {
		this.nuevoAlumnoEntidadFederetivaId = nuevoAlumnoEntidadFederetivaId;
	}

	/**
	 * @return the nuevoAlumnoTelefono
	 */
	public String getNuevoAlumnoTelefono() {
		return nuevoAlumnoTelefono;
	}

	/**
	 * @param nuevoAlumnoTelefono
	 *            the nuevoAlumnoTelefono to set
	 */
	public void setNuevoAlumnoTelefono(String nuevoAlumnoTelefono) {
		this.nuevoAlumnoTelefono = nuevoAlumnoTelefono;
	}

	/**
	 * @return the nuevoAlumnoNoInt
	 */
	public String getNuevoAlumnoNoInt() {
		return nuevoAlumnoNoInt;
	}

	/**
	 * @param nuevoAlumnoNoInt
	 *            the nuevoAlumnoNoInt to set
	 */
	public void setNuevoAlumnoNoInt(String nuevoAlumnoNoInt) {
		this.nuevoAlumnoNoInt = nuevoAlumnoNoInt;
	}

	/**
	 * @return the nuevoAlumnoCp
	 */
	public String getNuevoAlumnoCp() {
		return nuevoAlumnoCp;
	}

	/**
	 * @param nuevoAlumnoCp
	 *            the nuevoAlumnoCp to set
	 */
	public void setNuevoAlumnoCp(String nuevoAlumnoCp) {
		this.nuevoAlumnoCp = nuevoAlumnoCp;
	}

	/**
	 * @return the nuevoAlumnoCpIdAsentamiento
	 */
	public int getNuevoAlumnoCpIdAsentamiento() {
		return nuevoAlumnoCpIdAsentamiento;
	}

	/**
	 * @param nuevoAlumnoCpIdAsentamiento
	 *            the nuevoAlumnoCpIdAsentamiento to set
	 */
	public void setNuevoAlumnoCpIdAsentamiento(int nuevoAlumnoCpIdAsentamiento) {
		this.nuevoAlumnoCpIdAsentamiento = nuevoAlumnoCpIdAsentamiento;
	}

	/**
	 * @return the nuevoAlumnoSelectListColonias
	 */
	public List<SelectItem> getNuevoAlumnoSelectListColonias() {
		return nuevoAlumnoSelectListColonias;
	}

	/**
	 * @param nuevoAlumnoSelectListColonias
	 *            the nuevoAlumnoSelectListColonias to set
	 */
	public void setNuevoAlumnoSelectListColonias(
			List<SelectItem> nuevoAlumnoSelectListColonias) {
		this.nuevoAlumnoSelectListColonias = nuevoAlumnoSelectListColonias;
	}

	/**
	 * @return the nuevoAlumnoIdAsentamientoMunicipioEstado
	 */
	public String getNuevoAlumnoIdAsentamientoMunicipioEstado() {
		return nuevoAlumnoIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param nuevoAlumnoIdAsentamientoMunicipioEstado
	 *            the nuevoAlumnoIdAsentamientoMunicipioEstado to set
	 */
	public void setNuevoAlumnoIdAsentamientoMunicipioEstado(
			String nuevoAlumnoIdAsentamientoMunicipioEstado) {
		this.nuevoAlumnoIdAsentamientoMunicipioEstado = nuevoAlumnoIdAsentamientoMunicipioEstado;
	}

	public List<Integer> getListaCPs() {
		initListaCPs();
		return listaCPs;
	}

	public void setListaCPs(List<Integer> listaCPs) {
		this.listaCPs = listaCPs;
	}

	public List<CapacidadDTO> getFilteredListCapacidadesNuevo() {
		return filteredListCapacidadesNuevo;
	}

	public void setFilteredListCapacidadesNuevo(
			List<CapacidadDTO> filteredListCapacidadesNuevo) {
		this.filteredListCapacidadesNuevo = filteredListCapacidadesNuevo;
	}

	public List<SelectItem> getNuevoAlumnoListSelectTipoLicencia() {
		initNuevoAlumnoSelectTipoLicencia();
		return nuevoAlumnoListSelectTipoLicencia;
	}

	public void setNuevoAlumnoListSelectTipoLicencia(
			List<SelectItem> nuevoAlumnoListSelectTipoLicencia) {
		this.nuevoAlumnoListSelectTipoLicencia = nuevoAlumnoListSelectTipoLicencia;
	}

	public int getNuevoAlumnoTipoLicenciaSelected() {
		return nuevoAlumnoTipoLicenciaSelected;
	}

	public void setNuevoAlumnoTipoLicenciaSelected(
			int nuevoAlumnoTipoLicenciaSelected) {
		this.nuevoAlumnoTipoLicenciaSelected = nuevoAlumnoTipoLicenciaSelected;
	}

	public String getNuevoAlumnoCapacidad() {
		return nuevoAlumnoCapacidad;
	}

	public void setNuevoAlumnoCapacidad(String nuevoAlumnoCapacidad) {
		this.nuevoAlumnoCapacidad = nuevoAlumnoCapacidad;
	}

	public String getNuevoAlumnoCapacidadAutorizada() {
		return nuevoAlumnoCapacidadAutorizada;
	}

	public void setNuevoAlumnoCapacidadAutorizada(
			String nuevoAlumnoCapacidadAutorizada) {
		this.nuevoAlumnoCapacidadAutorizada = nuevoAlumnoCapacidadAutorizada;
	}

	public String getNuevoAlumnoCapacidadHoras() {
		return nuevoAlumnoCapacidadHoras;
	}

	public void setNuevoAlumnoCapacidadHoras(String nuevoAlumnoCapacidadHoras) {
		this.nuevoAlumnoCapacidadHoras = nuevoAlumnoCapacidadHoras;
	}

	public List<AsentamientoDTO> getListaAsentamientos() {
		return listaAsentamientos;
	}

	public void setListaAsentamientos(List<AsentamientoDTO> listaAsentamientos) {
		this.listaAsentamientos = listaAsentamientos;
	}

	public Date getNuevoAlumnoCapacidadFechaInicio() {
		return nuevoAlumnoCapacidadFechaInicio;
	}

	public void setNuevoAlumnoCapacidadFechaInicio(
			Date nuevoAlumnoCapacidadFechaInicio) {
		this.nuevoAlumnoCapacidadFechaInicio = nuevoAlumnoCapacidadFechaInicio;
	}

	public Date getNuevoAlumnoCapacidadFechaFin() {
		return nuevoAlumnoCapacidadFechaFin;
	}

	public void setNuevoAlumnoCapacidadFechaFin(
			Date nuevoAlumnoCapacidadFechaFin) {
		this.nuevoAlumnoCapacidadFechaFin = nuevoAlumnoCapacidadFechaFin;
	}

	public String getNuevoAlumnoCapacidadAcreditada() {
		return nuevoAlumnoCapacidadAcreditada;
	}

	public void setNuevoAlumnoCapacidadAcreditada(
			String nuevoAlumnoCapacidadAcreditada) {
		this.nuevoAlumnoCapacidadAcreditada = nuevoAlumnoCapacidadAcreditada;
	}

	public CapacidadDTO getNuevoAlumnoCapacidadSelected() {
		return nuevoAlumnoCapacidadSelected;
	}

	public void setNuevoAlumnoCapacidadSelected(
			CapacidadDTO nuevoAlumnoCapacidadSelected) {
		this.nuevoAlumnoCapacidadSelected = nuevoAlumnoCapacidadSelected;
	}

	public List<TipoEstudioDTO> getNuevoAlumnoListaTipoEstudios() {
		initNuevoAlumnoListaTipoEstudios();
		return nuevoAlumnoListaTipoEstudios;
	}

	public void setNuevoAlumnoListaTipoEstudios(
			List<TipoEstudioDTO> nuevoAlumnoListaTipoEstudios) {
		this.nuevoAlumnoListaTipoEstudios = nuevoAlumnoListaTipoEstudios;
	}

	public List<EstudioDTO> getNuevoAlumnoListaEstudios() {
		initNuevoAlumnoListaEstudios();
		return nuevoAlumnoListaEstudios;
	}

	public void setNuevoAlumnoListaEstudios(
			List<EstudioDTO> nuevoAlumnoListaEstudios) {
		this.nuevoAlumnoListaEstudios = nuevoAlumnoListaEstudios;
	}

	public String getNuevoAlumnoConyugueNombre() {
		return nuevoAlumnoConyugueNombre;
	}

	public void setNuevoAlumnoConyugueNombre(String nuevoAlumnoConyugueNombre) {
		this.nuevoAlumnoConyugueNombre = nuevoAlumnoConyugueNombre;
	}

	public Date getNuevoAlumnoConyugueFechaNac() {
		return nuevoAlumnoConyugueFechaNac;
	}

	public void setNuevoAlumnoConyugueFechaNac(Date nuevoAlumnoConyugueFechaNac) {
		this.nuevoAlumnoConyugueFechaNac = nuevoAlumnoConyugueFechaNac;
	}

	public String getNuevoAlumnoHijo1Nombre() {
		return nuevoAlumnoHijo1Nombre;
	}

	public void setNuevoAlumnoHijo1Nombre(String nuevoAlumnoHijo1Nombre) {
		this.nuevoAlumnoHijo1Nombre = nuevoAlumnoHijo1Nombre;
	}

	public Date getNuevoAlumnoHijo1FechaNac() {
		return nuevoAlumnoHijo1FechaNac;
	}

	public void setNuevoAlumnoHijo1FechaNac(Date nuevoAlumnoHijo1FechaNac) {
		this.nuevoAlumnoHijo1FechaNac = nuevoAlumnoHijo1FechaNac;
	}

	public String getNuevoAlumnoHijo2Nombre() {
		return nuevoAlumnoHijo2Nombre;
	}

	public void setNuevoAlumnoHijo2Nombre(String nuevoAlumnoHijo2Nombre) {
		this.nuevoAlumnoHijo2Nombre = nuevoAlumnoHijo2Nombre;
	}

	public Date getNuevoAlumnoHijo2FechaNac() {
		return nuevoAlumnoHijo2FechaNac;
	}

	public void setNuevoAlumnoHijo2FechaNac(Date nuevoAlumnoHijo2FechaNac) {
		this.nuevoAlumnoHijo2FechaNac = nuevoAlumnoHijo2FechaNac;
	}

	public String getNuevoAlumnoPadreNombre() {
		return nuevoAlumnoPadreNombre;
	}

	public void setNuevoAlumnoPadreNombre(String nuevoAlumnoPadreNombre) {
		this.nuevoAlumnoPadreNombre = nuevoAlumnoPadreNombre;
	}

	public Date getNuevoAlumnoPadreFechaNac() {
		return nuevoAlumnoPadreFechaNac;
	}

	public void setNuevoAlumnoPadreFechaNac(Date nuevoAlumnoPadreFechaNac) {
		this.nuevoAlumnoPadreFechaNac = nuevoAlumnoPadreFechaNac;
	}

	public String getNuevoAlumnoMadreNombre() {
		return nuevoAlumnoMadreNombre;
	}

	public void setNuevoAlumnoMadreNombre(String nuevoAlumnoMadreNombre) {
		this.nuevoAlumnoMadreNombre = nuevoAlumnoMadreNombre;
	}

	public Date getNuevoAlumnoMadreFechaNac() {
		return nuevoAlumnoMadreFechaNac;
	}

	public void setNuevoAlumnoMadreFechaNac(Date nuevoAlumnoMadreFechaNac) {
		this.nuevoAlumnoMadreFechaNac = nuevoAlumnoMadreFechaNac;
	}

	public ReferenciaDTO getNuevoAlumnoReferenciaSelected() {
		return nuevoAlumnoReferenciaSelected;
	}

	public void setNuevoAlumnoReferenciaSelected(
			ReferenciaDTO nuevoAlumnoReferenciaSelected) {
		this.nuevoAlumnoReferenciaSelected = nuevoAlumnoReferenciaSelected;
	}

	public List<ReferenciaDTO> getNuevoAlumnoListaReferencias() {
		return nuevoAlumnoListaReferencias;
	}

	public void setNuevoAlumnoListaReferencias(
			List<ReferenciaDTO> nuevoAlumnoListaReferencias) {
		this.nuevoAlumnoListaReferencias = nuevoAlumnoListaReferencias;
	}

	public String getNuevoAlumnoReferenciaNuevaNombre() {
		return nuevoAlumnoReferenciaNuevaNombre;
	}

	public void setNuevoAlumnoReferenciaNuevaNombre(
			String nuevoAlumnoReferenciaNuevaNombre) {
		this.nuevoAlumnoReferenciaNuevaNombre = nuevoAlumnoReferenciaNuevaNombre;
	}

	public String getNuevoAlumnoReferenciaNuevaApellidoP() {
		return nuevoAlumnoReferenciaNuevaApellidoP;
	}

	public void setNuevoAlumnoReferenciaNuevaApellidoP(
			String nuevoAlumnoReferenciaNuevaApellidoP) {
		this.nuevoAlumnoReferenciaNuevaApellidoP = nuevoAlumnoReferenciaNuevaApellidoP;
	}

	public String getNuevoAlumnoReferenciaNuevaApellidoM() {
		return nuevoAlumnoReferenciaNuevaApellidoM;
	}

	public void setNuevoAlumnoReferenciaNuevaApellidoM(
			String nuevoAlumnoReferenciaNuevaApellidoM) {
		this.nuevoAlumnoReferenciaNuevaApellidoM = nuevoAlumnoReferenciaNuevaApellidoM;
	}

	public int getNuevoAlumnoCpIdAsentamientoReferenciaNueva() {
		return nuevoAlumnoCpIdAsentamientoReferenciaNueva;
	}

	public void setNuevoAlumnoCpIdAsentamientoReferenciaNueva(
			int nuevoAlumnoCpIdAsentamientoReferenciaNueva) {
		this.nuevoAlumnoCpIdAsentamientoReferenciaNueva = nuevoAlumnoCpIdAsentamientoReferenciaNueva;
	}

	public String getNuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva() {
		return nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	}

	public void setNuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva(
			String nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva) {
		this.nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva = nuevoAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	}

	public List<SelectItem> getNuevoAlumnoSelectListColoniasReferenciaNueva() {
		return nuevoAlumnoSelectListColoniasReferenciaNueva;
	}

	public void setNuevoAlumnoSelectListColoniasReferenciaNueva(
			List<SelectItem> nuevoAlumnoSelectListColoniasReferenciaNueva) {
		this.nuevoAlumnoSelectListColoniasReferenciaNueva = nuevoAlumnoSelectListColoniasReferenciaNueva;
	}

	public String getNuevoAlumnoReferenciaNuevaDelegacion() {
		return nuevoAlumnoReferenciaNuevaDelegacion;
	}

	public void setNuevoAlumnoReferenciaNuevaDelegacion(
			String nuevoAlumnoReferenciaNuevaDelegacion) {
		this.nuevoAlumnoReferenciaNuevaDelegacion = nuevoAlumnoReferenciaNuevaDelegacion;
	}

	public String getNuevoAlumnoReferenciaNuevaEntidadFederativa() {
		return nuevoAlumnoReferenciaNuevaEntidadFederativa;
	}

	public void setNuevoAlumnoReferenciaNuevaEntidadFederativa(
			String nuevoAlumnoReferenciaNuevaEntidadFederativa) {
		this.nuevoAlumnoReferenciaNuevaEntidadFederativa = nuevoAlumnoReferenciaNuevaEntidadFederativa;
	}

	public String getNuevoAlumnoReferenciaNuevaCiudad() {
		return nuevoAlumnoReferenciaNuevaCiudad;
	}

	public void setNuevoAlumnoReferenciaNuevaCiudad(
			String nuevoAlumnoReferenciaNuevaCiudad) {
		this.nuevoAlumnoReferenciaNuevaCiudad = nuevoAlumnoReferenciaNuevaCiudad;
	}

	public String getNuevoAlumnoReferenciaNuevaNoExt() {
		return nuevoAlumnoReferenciaNuevaNoExt;
	}

	public void setNuevoAlumnoReferenciaNuevaNoExt(
			String nuevoAlumnoReferenciaNuevaNoExt) {
		this.nuevoAlumnoReferenciaNuevaNoExt = nuevoAlumnoReferenciaNuevaNoExt;
	}

	public String getNuevoAlumnoReferenciaNuevaNoInt() {
		return nuevoAlumnoReferenciaNuevaNoInt;
	}

	public void setNuevoAlumnoReferenciaNuevaNoInt(
			String nuevoAlumnoReferenciaNuevaNoInt) {
		this.nuevoAlumnoReferenciaNuevaNoInt = nuevoAlumnoReferenciaNuevaNoInt;
	}

	public String getNuevoAlumnoReferenciaNuevaCalle() {
		return nuevoAlumnoReferenciaNuevaCalle;
	}

	public void setNuevoAlumnoReferenciaNuevaCalle(
			String nuevoAlumnoReferenciaNuevaCalle) {
		this.nuevoAlumnoReferenciaNuevaCalle = nuevoAlumnoReferenciaNuevaCalle;
	}

	public String getNuevoAlumnoReferenciaNuevaTel() {
		return nuevoAlumnoReferenciaNuevaTel;
	}

	public void setNuevoAlumnoReferenciaNuevaTel(
			String nuevoAlumnoReferenciaNuevaTel) {
		this.nuevoAlumnoReferenciaNuevaTel = nuevoAlumnoReferenciaNuevaTel;
	}

	/**
	 * @return the nuevoAlumnoDocumentoSelected
	 */
	public DocumentoDTO getNuevoAlumnoDocumentoSelected() {
		return nuevoAlumnoDocumentoSelected;
	}

	/**
	 * @param nuevoAlumnoDocumentoSelected
	 *            the nuevoAlumnoDocumentoSelected to set
	 */
	public void setNuevoAlumnoDocumentoSelected(
			DocumentoDTO nuevoAlumnoDocumentoSelected) {
		this.nuevoAlumnoDocumentoSelected = nuevoAlumnoDocumentoSelected;
	}

	/**
	 * @return the nuevoAlumnoDocumentoNuevoNombre
	 */
	public String getNuevoAlumnoDocumentoNuevoNombre() {
		return nuevoAlumnoDocumentoNuevoNombre;
	}

	/**
	 * @param nuevoAlumnoDocumentoNuevoNombre
	 *            the nuevoAlumnoDocumentoNuevoNombre to set
	 */
	public void setNuevoAlumnoDocumentoNuevoNombre(
			String nuevoAlumnoDocumentoNuevoNombre) {
		this.nuevoAlumnoDocumentoNuevoNombre = nuevoAlumnoDocumentoNuevoNombre;
	}

	/**
	 * @return the nuevoAlumnoDocListaEstatus
	 */
	public List<EstatusDTO> getNuevoAlumnoDocListaEstatus() {
		initNuevoAlumnoDocListaEstatus();
		return nuevoAlumnoDocListaEstatus;
	}

	/**
	 * @param nuevoAlumnoDocListaEstatus
	 *            the nuevoAlumnoDocListaEstatus to set
	 */
	public void setNuevoAlumnoDocListaEstatus(
			List<EstatusDTO> nuevoAlumnoDocListaEstatus) {
		this.nuevoAlumnoDocListaEstatus = nuevoAlumnoDocListaEstatus;
	}

	/**
	 * @return the nuevoAlumnoDocListaSelectEstatus
	 */
	public List<SelectItem> getNuevoAlumnoDocListaSelectEstatus() {
		initNuevoAlumnoDocListaSelectEstatus();
		return nuevoAlumnoDocListaSelectEstatus;
	}

	/**
	 * @param nuevoAlumnoDocListaSelectEstatus
	 *            the nuevoAlumnoDocListaSelectEstatus to set
	 */
	public void setNuevoAlumnoDocListaSelectEstatus(
			List<SelectItem> nuevoAlumnoDocListaSelectEstatus) {
		this.nuevoAlumnoDocListaSelectEstatus = nuevoAlumnoDocListaSelectEstatus;
	}

	/**
	 * @return the nuevoAlumnoDocEstatusSelected
	 */
	public short getNuevoAlumnoDocEstatusSelected() {
		return nuevoAlumnoDocEstatusSelected;
	}

	/**
	 * @param nuevoAlumnoDocEstatusSelected
	 *            the nuevoAlumnoDocEstatusSelected to set
	 */
	public void setNuevoAlumnoDocEstatusSelected(
			short nuevoAlumnoDocEstatusSelected) {
		this.nuevoAlumnoDocEstatusSelected = nuevoAlumnoDocEstatusSelected;
	}

	/**
	 * @return the nuevoAlumnoDocRuta
	 */
	public String getNuevoAlumnoDocRuta() {
		return nuevoAlumnoDocRuta;
	}

	/**
	 * @param nuevoAlumnoDocRuta
	 *            the nuevoAlumnoDocRuta to set
	 */
	public void setNuevoAlumnoDocRuta(String nuevoAlumnoDocRuta) {
		this.nuevoAlumnoDocRuta = nuevoAlumnoDocRuta;
	}

	/**
	 * @return the listaEstatus
	 */
	public List<EstatusDTO> getListaEstatus() {
		initListaEstatus();
		return listaEstatus;
	}

	/**
	 * @param listaEstatus
	 *            the listaEstatus to set
	 */
	public void setListaEstatus(List<EstatusDTO> listaEstatus) {
		this.listaEstatus = listaEstatus;
	}

	/**
	 * @return the listaSelectEstatus
	 */
	public List<SelectItem> getListaSelectEstatus() {
		initListaSelectEstatus();
		return listaSelectEstatus;
	}

	/**
	 * @param listaSelectEstatus
	 *            the listaSelectEstatus to set
	 */
	public void setListaSelectEstatus(List<SelectItem> listaSelectEstatus) {
		this.listaSelectEstatus = listaSelectEstatus;
	}

	/**
	 * @return the nuevoAlumnoIdEstatusSelected
	 */
	public short getNuevoAlumnoIdEstatusSelected() {
		return nuevoAlumnoIdEstatusSelected;
	}

	/**
	 * @param nuevoAlumnoIdEstatusSelected
	 *            the nuevoAlumnoIdEstatusSelected to set
	 */
	public void setNuevoAlumnoIdEstatusSelected(
			short nuevoAlumnoIdEstatusSelected) {
		this.nuevoAlumnoIdEstatusSelected = nuevoAlumnoIdEstatusSelected;
	}

	/**
	 * @return the documentoTempDownload
	 */
	public DocumentoDTO getDocumentoTempDownload() {
		return documentoTempDownload;
	}

	/**
	 * @param documentoTempDownload
	 *            the documentoTempDownload to set
	 */
	public void setDocumentoTempDownload(DocumentoDTO documentoTempDownload) {
		this.documentoTempDownload = documentoTempDownload;
	}

	/**
	 * @return the nuevoAlumnoCPAutoComplete
	 */
	public String getNuevoAlumnoCPAutoComplete() {
		return nuevoAlumnoCPAutoComplete;
	}

	/**
	 * @param nuevoAlumnoCPAutoComplete
	 *            the nuevoAlumnoCPAutoComplete to set
	 */
	public void setNuevoAlumnoCPAutoComplete(String nuevoAlumnoCPAutoComplete) {
		this.nuevoAlumnoCPAutoComplete = nuevoAlumnoCPAutoComplete;
	}

	/**
	 * @return the nuevoAlumnoReferenciaCPAutoComplete
	 */
	public String getNuevoAlumnoReferenciaCPAutoComplete() {
		return nuevoAlumnoReferenciaCPAutoComplete;
	}

	/**
	 * @param nuevoAlumnoReferenciaCPAutoComplete
	 *            the nuevoAlumnoReferenciaCPAutoComplete to set
	 */
	public void setNuevoAlumnoReferenciaCPAutoComplete(
			String nuevoAlumnoReferenciaCPAutoComplete) {
		this.nuevoAlumnoReferenciaCPAutoComplete = nuevoAlumnoReferenciaCPAutoComplete;
	}

	/**
	 * @return the listCPString
	 */
	public List<String> getListCPString() {
		initListaCPString();
		return listCPString;
	}

	/**
	 * @param listCPString
	 *            the listCPString to set
	 */
	public void setListCPString(List<String> listCPString) {
		this.listCPString = listCPString;
	}

	/**
	 * @return the editarAlumnoNombre
	 */
	public String getEditarAlumnoNombre() {
		return editarAlumnoNombre;
	}

	/**
	 * @param editarAlumnoNombre
	 *            the editarAlumnoNombre to set
	 */
	public void setEditarAlumnoNombre(String editarAlumnoNombre) {
		this.editarAlumnoNombre = editarAlumnoNombre;
	}

	/**
	 * @return the editarAlumnoMatricula
	 */
	public String getEditarAlumnoMatricula() {
		return editarAlumnoMatricula;
	}

	/**
	 * @param editarAlumnoMatricula
	 *            the editarAlumnoMatricula to set
	 */
	public void setEditarAlumnoMatricula(String editarAlumnoMatricula) {
		this.editarAlumnoMatricula = editarAlumnoMatricula;
	}

	/**
	 * @return the editarAlumnoApellidoP
	 */
	public String getEditarAlumnoApellidoP() {
		return editarAlumnoApellidoP;
	}

	/**
	 * @param editarAlumnoApellidoP
	 *            the editarAlumnoApellidoP to set
	 */
	public void setEditarAlumnoApellidoP(String editarAlumnoApellidoP) {
		this.editarAlumnoApellidoP = editarAlumnoApellidoP;
	}

	/**
	 * @return the editarAlumnoApellidoM
	 */
	public String getEditarAlumnoApellidoM() {
		return editarAlumnoApellidoM;
	}

	/**
	 * @param editarAlumnoApellidoM
	 *            the editarAlumnoApellidoM to set
	 */
	public void setEditarAlumnoApellidoM(String editarAlumnoApellidoM) {
		this.editarAlumnoApellidoM = editarAlumnoApellidoM;
	}

	/**
	 * @return the editarAlumnoFechaNac
	 */
	public Date getEditarAlumnoFechaNac() {
		return editarAlumnoFechaNac;
	}

	/**
	 * @param editarAlumnoFechaNac
	 *            the editarAlumnoFechaNac to set
	 */
	public void setEditarAlumnoFechaNac(Date editarAlumnoFechaNac) {
		this.editarAlumnoFechaNac = editarAlumnoFechaNac;
	}

	/**
	 * @return the editarAlumnoLugarNac
	 */
	public String getEditarAlumnoLugarNac() {
		return editarAlumnoLugarNac;
	}

	/**
	 * @param editarAlumnoLugarNac
	 *            the editarAlumnoLugarNac to set
	 */
	public void setEditarAlumnoLugarNac(String editarAlumnoLugarNac) {
		this.editarAlumnoLugarNac = editarAlumnoLugarNac;
	}

	/**
	 * @return the editarAlumnoCurp
	 */
	public String getEditarAlumnoCurp() {
		return editarAlumnoCurp;
	}

	/**
	 * @param editarAlumnoCurp
	 *            the editarAlumnoCurp to set
	 */
	public void setEditarAlumnoCurp(String editarAlumnoCurp) {
		this.editarAlumnoCurp = editarAlumnoCurp;
	}

	/**
	 * @return the editarAlumnoRfc
	 */
	public String getEditarAlumnoRfc() {
		return editarAlumnoRfc;
	}

	/**
	 * @param editarAlumnoRfc
	 *            the editarAlumnoRfc to set
	 */
	public void setEditarAlumnoRfc(String editarAlumnoRfc) {
		this.editarAlumnoRfc = editarAlumnoRfc;
	}

	/**
	 * @return the editarAlumnoPasaporte
	 */
	public String getEditarAlumnoPasaporte() {
		return editarAlumnoPasaporte;
	}

	/**
	 * @param editarAlumnoPasaporte
	 *            the editarAlumnoPasaporte to set
	 */
	public void setEditarAlumnoPasaporte(String editarAlumnoPasaporte) {
		this.editarAlumnoPasaporte = editarAlumnoPasaporte;
	}

	/**
	 * @return the editarAlumnoIfe
	 */
	public String getEditarAlumnoIfe() {
		return editarAlumnoIfe;
	}

	/**
	 * @param editarAlumnoIfe
	 *            the editarAlumnoIfe to set
	 */
	public void setEditarAlumnoIfe(String editarAlumnoIfe) {
		this.editarAlumnoIfe = editarAlumnoIfe;
	}

	/**
	 * @return the editarAlumnoEmail
	 */
	public String getEditarAlumnoEmail() {
		return editarAlumnoEmail;
	}

	/**
	 * @param editarAlumnoEmail
	 *            the editarAlumnoEmail to set
	 */
	public void setEditarAlumnoEmail(String editarAlumnoEmail) {
		this.editarAlumnoEmail = editarAlumnoEmail;
	}

	/**
	 * @return the editarAlumnoCalle
	 */
	public String getEditarAlumnoCalle() {
		return editarAlumnoCalle;
	}

	/**
	 * @param editarAlumnoCalle
	 *            the editarAlumnoCalle to set
	 */
	public void setEditarAlumnoCalle(String editarAlumnoCalle) {
		this.editarAlumnoCalle = editarAlumnoCalle;
	}

	/**
	 * @return the editarAlumnoNoExt
	 */
	public String getEditarAlumnoNoExt() {
		return editarAlumnoNoExt;
	}

	/**
	 * @param editarAlumnoNoExt
	 *            the editarAlumnoNoExt to set
	 */
	public void setEditarAlumnoNoExt(String editarAlumnoNoExt) {
		this.editarAlumnoNoExt = editarAlumnoNoExt;
	}

	/**
	 * @return the editarAlumnoColonia
	 */
	public String getEditarAlumnoColonia() {
		return editarAlumnoColonia;
	}

	/**
	 * @param editarAlumnoColonia
	 *            the editarAlumnoColonia to set
	 */
	public void setEditarAlumnoColonia(String editarAlumnoColonia) {
		this.editarAlumnoColonia = editarAlumnoColonia;
	}

	/**
	 * @return the editarAlumnoColoniaId
	 */
	public int getEditarAlumnoColoniaId() {
		return editarAlumnoColoniaId;
	}

	/**
	 * @param editarAlumnoColoniaId
	 *            the editarAlumnoColoniaId to set
	 */
	public void setEditarAlumnoColoniaId(int editarAlumnoColoniaId) {
		this.editarAlumnoColoniaId = editarAlumnoColoniaId;
	}

	/**
	 * @return the editarAlumnoDelegacion
	 */
	public String getEditarAlumnoDelegacion() {
		return editarAlumnoDelegacion;
	}

	/**
	 * @param editarAlumnoDelegacion
	 *            the editarAlumnoDelegacion to set
	 */
	public void setEditarAlumnoDelegacion(String editarAlumnoDelegacion) {
		this.editarAlumnoDelegacion = editarAlumnoDelegacion;
	}

	/**
	 * @return the editarAlumnoDelegacionId
	 */
	public int getEditarAlumnoDelegacionId() {
		return editarAlumnoDelegacionId;
	}

	/**
	 * @param editarAlumnoDelegacionId
	 *            the editarAlumnoDelegacionId to set
	 */
	public void setEditarAlumnoDelegacionId(int editarAlumnoDelegacionId) {
		this.editarAlumnoDelegacionId = editarAlumnoDelegacionId;
	}

	/**
	 * @return the editarAlumnoCiudad
	 */
	public String getEditarAlumnoCiudad() {
		return editarAlumnoCiudad;
	}

	/**
	 * @param editarAlumnoCiudad
	 *            the editarAlumnoCiudad to set
	 */
	public void setEditarAlumnoCiudad(String editarAlumnoCiudad) {
		this.editarAlumnoCiudad = editarAlumnoCiudad;
	}

	/**
	 * @return the editarAlumnoCiudadId
	 */
	public String getEditarAlumnoCiudadId() {
		return editarAlumnoCiudadId;
	}

	/**
	 * @param editarAlumnoCiudadId
	 *            the editarAlumnoCiudadId to set
	 */
	public void setEditarAlumnoCiudadId(String editarAlumnoCiudadId) {
		this.editarAlumnoCiudadId = editarAlumnoCiudadId;
	}

	/**
	 * @return the editarAlumnoEntidadFederativa
	 */
	public String getEditarAlumnoEntidadFederativa() {
		return editarAlumnoEntidadFederativa;
	}

	/**
	 * @param editarAlumnoEntidadFederativa
	 *            the editarAlumnoEntidadFederativa to set
	 */
	public void setEditarAlumnoEntidadFederativa(
			String editarAlumnoEntidadFederativa) {
		this.editarAlumnoEntidadFederativa = editarAlumnoEntidadFederativa;
	}

	/**
	 * @return the editarAlumnoEntidadFederetivaId
	 */
	public int getEditarAlumnoEntidadFederetivaId() {
		return editarAlumnoEntidadFederetivaId;
	}

	/**
	 * @param editarAlumnoEntidadFederetivaId
	 *            the editarAlumnoEntidadFederetivaId to set
	 */
	public void setEditarAlumnoEntidadFederetivaId(
			int editarAlumnoEntidadFederetivaId) {
		this.editarAlumnoEntidadFederetivaId = editarAlumnoEntidadFederetivaId;
	}

	/**
	 * @return the editarAlumnoTelefono
	 */
	public String getEditarAlumnoTelefono() {
		return editarAlumnoTelefono;
	}

	/**
	 * @param editarAlumnoTelefono
	 *            the editarAlumnoTelefono to set
	 */
	public void setEditarAlumnoTelefono(String editarAlumnoTelefono) {
		this.editarAlumnoTelefono = editarAlumnoTelefono;
	}

	/**
	 * @return the editarAlumnoNoInt
	 */
	public String getEditarAlumnoNoInt() {
		return editarAlumnoNoInt;
	}

	/**
	 * @param editarAlumnoNoInt
	 *            the editarAlumnoNoInt to set
	 */
	public void setEditarAlumnoNoInt(String editarAlumnoNoInt) {
		this.editarAlumnoNoInt = editarAlumnoNoInt;
	}

	/**
	 * @return the editarAlumnoCp
	 */
	public String getEditarAlumnoCp() {
		return editarAlumnoCp;
	}

	/**
	 * @param editarAlumnoCp
	 *            the editarAlumnoCp to set
	 */
	public void setEditarAlumnoCp(String editarAlumnoCp) {
		this.editarAlumnoCp = editarAlumnoCp;
	}

	/**
	 * @return the editarAlumnoCpIdAsentamiento
	 */
	public int getEditarAlumnoCpIdAsentamiento() {
		return editarAlumnoCpIdAsentamiento;
	}

	/**
	 * @param editarAlumnoCpIdAsentamiento
	 *            the editarAlumnoCpIdAsentamiento to set
	 */
	public void setEditarAlumnoCpIdAsentamiento(int editarAlumnoCpIdAsentamiento) {
		this.editarAlumnoCpIdAsentamiento = editarAlumnoCpIdAsentamiento;
	}

	/**
	 * @return the editarAlumnoSelectListColonias
	 */
	public List<SelectItem> getEditarAlumnoSelectListColonias() {
		return editarAlumnoSelectListColonias;
	}

	/**
	 * @param editarAlumnoSelectListColonias
	 *            the editarAlumnoSelectListColonias to set
	 */
	public void setEditarAlumnoSelectListColonias(
			List<SelectItem> editarAlumnoSelectListColonias) {
		this.editarAlumnoSelectListColonias = editarAlumnoSelectListColonias;
	}

	/**
	 * @return the editarAlumnoIdAsentamientoMunicipioEstado
	 */
	public String getEditarAlumnoIdAsentamientoMunicipioEstado() {
		return editarAlumnoIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param editarAlumnoIdAsentamientoMunicipioEstado
	 *            the editarAlumnoIdAsentamientoMunicipioEstado to set
	 */
	public void setEditarAlumnoIdAsentamientoMunicipioEstado(
			String editarAlumnoIdAsentamientoMunicipioEstado) {
		this.editarAlumnoIdAsentamientoMunicipioEstado = editarAlumnoIdAsentamientoMunicipioEstado;
	}

	/**
	 * @return the editarAlumnoIdEstatusSelected
	 */
	public short getEditarAlumnoIdEstatusSelected() {
		return editarAlumnoIdEstatusSelected;
	}

	/**
	 * @param editarAlumnoIdEstatusSelected
	 *            the editarAlumnoIdEstatusSelected to set
	 */
	public void setEditarAlumnoIdEstatusSelected(
			short editarAlumnoIdEstatusSelected) {
		this.editarAlumnoIdEstatusSelected = editarAlumnoIdEstatusSelected;
	}

	/**
	 * @return the editarAlumnoCPAutoComplete
	 */
	public String getEditarAlumnoCPAutoComplete() {
		return editarAlumnoCPAutoComplete;
	}

	/**
	 * @param editarAlumnoCPAutoComplete the editarAlumnoCPAutoComplete to set
	 */
	public void setEditarAlumnoCPAutoComplete(String editarAlumnoCPAutoComplete) {
		this.editarAlumnoCPAutoComplete = editarAlumnoCPAutoComplete;
	}

	/**
	 * @return the editarAlumnoReferenciaCPAutoComplete
	 */
	public String getEditarAlumnoReferenciaCPAutoComplete() {
		return editarAlumnoReferenciaCPAutoComplete;
	}

	/**
	 * @param editarAlumnoReferenciaCPAutoComplete the editarAlumnoReferenciaCPAutoComplete to set
	 */
	public void setEditarAlumnoReferenciaCPAutoComplete(
			String editarAlumnoReferenciaCPAutoComplete) {
		this.editarAlumnoReferenciaCPAutoComplete = editarAlumnoReferenciaCPAutoComplete;
	}

	/**
	 * @return the filteredListCapacidadesEditar
	 */
	public List<CapacidadDTO> getFilteredListCapacidadesEditar() {
		return filteredListCapacidadesEditar;
	}

	/**
	 * @param filteredListCapacidadesEditar the filteredListCapacidadesEditar to set
	 */
	public void setFilteredListCapacidadesEditar(
			List<CapacidadDTO> filteredListCapacidadesEditar) {
		this.filteredListCapacidadesEditar = filteredListCapacidadesEditar;
	}

	/**
	 * @return the editarAlumnoTipoLicenciaSelected
	 */
	public int getEditarAlumnoTipoLicenciaSelected() {
		return editarAlumnoTipoLicenciaSelected;
	}

	/**
	 * @param editarAlumnoTipoLicenciaSelected the editarAlumnoTipoLicenciaSelected to set
	 */
	public void setEditarAlumnoTipoLicenciaSelected(
			int editarAlumnoTipoLicenciaSelected) {
		this.editarAlumnoTipoLicenciaSelected = editarAlumnoTipoLicenciaSelected;
	}

	/**
	 * @return the editarAlumnoCapacidad
	 */
	public String getEditarAlumnoCapacidad() {
		return editarAlumnoCapacidad;
	}

	/**
	 * @param editarAlumnoCapacidad the editarAlumnoCapacidad to set
	 */
	public void setEditarAlumnoCapacidad(String editarAlumnoCapacidad) {
		this.editarAlumnoCapacidad = editarAlumnoCapacidad;
	}

	/**
	 * @return the editarAlumnoCapacidadAutorizada
	 */
	public String getEditarAlumnoCapacidadAutorizada() {
		return editarAlumnoCapacidadAutorizada;
	}

	/**
	 * @param editarAlumnoCapacidadAutorizada the editarAlumnoCapacidadAutorizada to set
	 */
	public void setEditarAlumnoCapacidadAutorizada(
			String editarAlumnoCapacidadAutorizada) {
		this.editarAlumnoCapacidadAutorizada = editarAlumnoCapacidadAutorizada;
	}

	/**
	 * @return the editarAlumnoCapacidadHoras
	 */
	public String getEditarAlumnoCapacidadHoras() {
		return editarAlumnoCapacidadHoras;
	}

	/**
	 * @param editarAlumnoCapacidadHoras the editarAlumnoCapacidadHoras to set
	 */
	public void setEditarAlumnoCapacidadHoras(String editarAlumnoCapacidadHoras) {
		this.editarAlumnoCapacidadHoras = editarAlumnoCapacidadHoras;
	}

	/**
	 * @return the editarAlumnoCapacidadFechaInicio
	 */
	public Date getEditarAlumnoCapacidadFechaInicio() {
		return editarAlumnoCapacidadFechaInicio;
	}

	/**
	 * @param editarAlumnoCapacidadFechaInicio the editarAlumnoCapacidadFechaInicio to set
	 */
	public void setEditarAlumnoCapacidadFechaInicio(
			Date editarAlumnoCapacidadFechaInicio) {
		this.editarAlumnoCapacidadFechaInicio = editarAlumnoCapacidadFechaInicio;
	}

	/**
	 * @return the editarAlumnoCapacidadFechaFin
	 */
	public Date getEditarAlumnoCapacidadFechaFin() {
		return editarAlumnoCapacidadFechaFin;
	}

	/**
	 * @param editarAlumnoCapacidadFechaFin the editarAlumnoCapacidadFechaFin to set
	 */
	public void setEditarAlumnoCapacidadFechaFin(Date editarAlumnoCapacidadFechaFin) {
		this.editarAlumnoCapacidadFechaFin = editarAlumnoCapacidadFechaFin;
	}

	/**
	 * @return the editarAlumnoCapacidadAcreditada
	 */
	public String getEditarAlumnoCapacidadAcreditada() {
		return editarAlumnoCapacidadAcreditada;
	}

	/**
	 * @param editarAlumnoCapacidadAcreditada the editarAlumnoCapacidadAcreditada to set
	 */
	public void setEditarAlumnoCapacidadAcreditada(
			String editarAlumnoCapacidadAcreditada) {
		this.editarAlumnoCapacidadAcreditada = editarAlumnoCapacidadAcreditada;
	}

	/**
	 * @return the editarAlumnoCapacidadSelected
	 */
	public CapacidadDTO getEditarAlumnoCapacidadSelected() {
		return editarAlumnoCapacidadSelected;
	}

	/**
	 * @param editarAlumnoCapacidadSelected the editarAlumnoCapacidadSelected to set
	 */
	public void setEditarAlumnoCapacidadSelected(
			CapacidadDTO editarAlumnoCapacidadSelected) {
		this.editarAlumnoCapacidadSelected = editarAlumnoCapacidadSelected;
	}

	/**
	 * @return the editarAlumnoConyugueNombre
	 */
	public String getEditarAlumnoConyugueNombre() {
		return editarAlumnoConyugueNombre;
	}

	/**
	 * @param editarAlumnoConyugueNombre the editarAlumnoConyugueNombre to set
	 */
	public void setEditarAlumnoConyugueNombre(String editarAlumnoConyugueNombre) {
		this.editarAlumnoConyugueNombre = editarAlumnoConyugueNombre;
	}

	/**
	 * @return the editarAlumnoConyugueFechaNac
	 */
	public Date getEditarAlumnoConyugueFechaNac() {
		return editarAlumnoConyugueFechaNac;
	}

	/**
	 * @param editarAlumnoConyugueFechaNac the editarAlumnoConyugueFechaNac to set
	 */
	public void setEditarAlumnoConyugueFechaNac(Date editarAlumnoConyugueFechaNac) {
		this.editarAlumnoConyugueFechaNac = editarAlumnoConyugueFechaNac;
	}

	/**
	 * @return the editarAlumnoHijo1Nombre
	 */
	public String getEditarAlumnoHijo1Nombre() {
		return editarAlumnoHijo1Nombre;
	}

	/**
	 * @param editarAlumnoHijo1Nombre the editarAlumnoHijo1Nombre to set
	 */
	public void setEditarAlumnoHijo1Nombre(String editarAlumnoHijo1Nombre) {
		this.editarAlumnoHijo1Nombre = editarAlumnoHijo1Nombre;
	}

	/**
	 * @return the editarAlumnoHijo1FechaNac
	 */
	public Date getEditarAlumnoHijo1FechaNac() {
		return editarAlumnoHijo1FechaNac;
	}

	/**
	 * @param editarAlumnoHijo1FechaNac the editarAlumnoHijo1FechaNac to set
	 */
	public void setEditarAlumnoHijo1FechaNac(Date editarAlumnoHijo1FechaNac) {
		this.editarAlumnoHijo1FechaNac = editarAlumnoHijo1FechaNac;
	}

	/**
	 * @return the editarAlumnoHijo2Nombre
	 */
	public String getEditarAlumnoHijo2Nombre() {
		return editarAlumnoHijo2Nombre;
	}

	/**
	 * @param editarAlumnoHijo2Nombre the editarAlumnoHijo2Nombre to set
	 */
	public void setEditarAlumnoHijo2Nombre(String editarAlumnoHijo2Nombre) {
		this.editarAlumnoHijo2Nombre = editarAlumnoHijo2Nombre;
	}

	/**
	 * @return the editarAlumnoHijo2FechaNac
	 */
	public Date getEditarAlumnoHijo2FechaNac() {
		return editarAlumnoHijo2FechaNac;
	}

	/**
	 * @param editarAlumnoHijo2FechaNac the editarAlumnoHijo2FechaNac to set
	 */
	public void setEditarAlumnoHijo2FechaNac(Date editarAlumnoHijo2FechaNac) {
		this.editarAlumnoHijo2FechaNac = editarAlumnoHijo2FechaNac;
	}

	/**
	 * @return the editarAlumnoPadreNombre
	 */
	public String getEditarAlumnoPadreNombre() {
		return editarAlumnoPadreNombre;
	}

	/**
	 * @param editarAlumnoPadreNombre the editarAlumnoPadreNombre to set
	 */
	public void setEditarAlumnoPadreNombre(String editarAlumnoPadreNombre) {
		this.editarAlumnoPadreNombre = editarAlumnoPadreNombre;
	}

	/**
	 * @return the editarAlumnoPadreFechaNac
	 */
	public Date getEditarAlumnoPadreFechaNac() {
		return editarAlumnoPadreFechaNac;
	}

	/**
	 * @param editarAlumnoPadreFechaNac the editarAlumnoPadreFechaNac to set
	 */
	public void setEditarAlumnoPadreFechaNac(Date editarAlumnoPadreFechaNac) {
		this.editarAlumnoPadreFechaNac = editarAlumnoPadreFechaNac;
	}

	/**
	 * @return the editarAlumnoMadreNombre
	 */
	public String getEditarAlumnoMadreNombre() {
		return editarAlumnoMadreNombre;
	}

	/**
	 * @param editarAlumnoMadreNombre the editarAlumnoMadreNombre to set
	 */
	public void setEditarAlumnoMadreNombre(String editarAlumnoMadreNombre) {
		this.editarAlumnoMadreNombre = editarAlumnoMadreNombre;
	}

	/**
	 * @return the editarAlumnoMadreFechaNac
	 */
	public Date getEditarAlumnoMadreFechaNac() {
		return editarAlumnoMadreFechaNac;
	}

	/**
	 * @param editarAlumnoMadreFechaNac the editarAlumnoMadreFechaNac to set
	 */
	public void setEditarAlumnoMadreFechaNac(Date editarAlumnoMadreFechaNac) {
		this.editarAlumnoMadreFechaNac = editarAlumnoMadreFechaNac;
	}

	/**
	 * @return the editarAlumnoListaEstudios
	 */
	public List<EstudioDTO> getEditarAlumnoListaEstudios() {
		initEditarAlumnoListaEstudios();
		return editarAlumnoListaEstudios;
	}

	/**
	 * @param editarAlumnoListaEstudios the editarAlumnoListaEstudios to set
	 */
	public void setEditarAlumnoListaEstudios(
			List<EstudioDTO> editarAlumnoListaEstudios) {
		this.editarAlumnoListaEstudios = editarAlumnoListaEstudios;
	}

	/**
	 * @return the editarAlumnoReferenciaSelected
	 */
	public ReferenciaDTO getEditarAlumnoReferenciaSelected() {
		return editarAlumnoReferenciaSelected;
	}

	/**
	 * @param editarAlumnoReferenciaSelected the editarAlumnoReferenciaSelected to set
	 */
	public void setEditarAlumnoReferenciaSelected(
			ReferenciaDTO editarAlumnoReferenciaSelected) {
		this.editarAlumnoReferenciaSelected = editarAlumnoReferenciaSelected;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaNombre
	 */
	public String getEditarAlumnoReferenciaNuevaNombre() {
		return editarAlumnoReferenciaNuevaNombre;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaNombre the editarAlumnoReferenciaNuevaNombre to set
	 */
	public void setEditarAlumnoReferenciaNuevaNombre(
			String editarAlumnoReferenciaNuevaNombre) {
		this.editarAlumnoReferenciaNuevaNombre = editarAlumnoReferenciaNuevaNombre;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaApellidoP
	 */
	public String getEditarAlumnoReferenciaNuevaApellidoP() {
		return editarAlumnoReferenciaNuevaApellidoP;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaApellidoP the editarAlumnoReferenciaNuevaApellidoP to set
	 */
	public void setEditarAlumnoReferenciaNuevaApellidoP(
			String editarAlumnoReferenciaNuevaApellidoP) {
		this.editarAlumnoReferenciaNuevaApellidoP = editarAlumnoReferenciaNuevaApellidoP;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaApellidoM
	 */
	public String getEditarAlumnoReferenciaNuevaApellidoM() {
		return editarAlumnoReferenciaNuevaApellidoM;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaApellidoM the editarAlumnoReferenciaNuevaApellidoM to set
	 */
	public void setEditarAlumnoReferenciaNuevaApellidoM(
			String editarAlumnoReferenciaNuevaApellidoM) {
		this.editarAlumnoReferenciaNuevaApellidoM = editarAlumnoReferenciaNuevaApellidoM;
	}

	/**
	 * @return the editarAlumnoCpIdAsentamientoReferenciaNueva
	 */
	public int getEditarAlumnoCpIdAsentamientoReferenciaNueva() {
		return editarAlumnoCpIdAsentamientoReferenciaNueva;
	}

	/**
	 * @param editarAlumnoCpIdAsentamientoReferenciaNueva the editarAlumnoCpIdAsentamientoReferenciaNueva to set
	 */
	public void setEditarAlumnoCpIdAsentamientoReferenciaNueva(
			int editarAlumnoCpIdAsentamientoReferenciaNueva) {
		this.editarAlumnoCpIdAsentamientoReferenciaNueva = editarAlumnoCpIdAsentamientoReferenciaNueva;
	}

	/**
	 * @return the editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva
	 */
	public String getEditarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva() {
		return editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	}

	/**
	 * @param editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva the editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva to set
	 */
	public void setEditarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva(
			String editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva) {
		this.editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva = editarAlumnoIdAsentamientoMunicipioEstadoReferenciaNueva;
	}

	/**
	 * @return the editarAlumnoSelectListColoniasReferenciaNueva
	 */
	public List<SelectItem> getEditarAlumnoSelectListColoniasReferenciaNueva() {
		return editarAlumnoSelectListColoniasReferenciaNueva;
	}

	/**
	 * @param editarAlumnoSelectListColoniasReferenciaNueva the editarAlumnoSelectListColoniasReferenciaNueva to set
	 */
	public void setEditarAlumnoSelectListColoniasReferenciaNueva(
			List<SelectItem> editarAlumnoSelectListColoniasReferenciaNueva) {
		this.editarAlumnoSelectListColoniasReferenciaNueva = editarAlumnoSelectListColoniasReferenciaNueva;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaDelegacion
	 */
	public String getEditarAlumnoReferenciaNuevaDelegacion() {
		return editarAlumnoReferenciaNuevaDelegacion;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaDelegacion the editarAlumnoReferenciaNuevaDelegacion to set
	 */
	public void setEditarAlumnoReferenciaNuevaDelegacion(
			String editarAlumnoReferenciaNuevaDelegacion) {
		this.editarAlumnoReferenciaNuevaDelegacion = editarAlumnoReferenciaNuevaDelegacion;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaEntidadFederativa
	 */
	public String getEditarAlumnoReferenciaNuevaEntidadFederativa() {
		return editarAlumnoReferenciaNuevaEntidadFederativa;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaEntidadFederativa the editarAlumnoReferenciaNuevaEntidadFederativa to set
	 */
	public void setEditarAlumnoReferenciaNuevaEntidadFederativa(
			String editarAlumnoReferenciaNuevaEntidadFederativa) {
		this.editarAlumnoReferenciaNuevaEntidadFederativa = editarAlumnoReferenciaNuevaEntidadFederativa;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaCiudad
	 */
	public String getEditarAlumnoReferenciaNuevaCiudad() {
		return editarAlumnoReferenciaNuevaCiudad;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaCiudad the editarAlumnoReferenciaNuevaCiudad to set
	 */
	public void setEditarAlumnoReferenciaNuevaCiudad(
			String editarAlumnoReferenciaNuevaCiudad) {
		this.editarAlumnoReferenciaNuevaCiudad = editarAlumnoReferenciaNuevaCiudad;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaNoExt
	 */
	public String getEditarAlumnoReferenciaNuevaNoExt() {
		return editarAlumnoReferenciaNuevaNoExt;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaNoExt the editarAlumnoReferenciaNuevaNoExt to set
	 */
	public void setEditarAlumnoReferenciaNuevaNoExt(
			String editarAlumnoReferenciaNuevaNoExt) {
		this.editarAlumnoReferenciaNuevaNoExt = editarAlumnoReferenciaNuevaNoExt;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaNoInt
	 */
	public String getEditarAlumnoReferenciaNuevaNoInt() {
		return editarAlumnoReferenciaNuevaNoInt;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaNoInt the editarAlumnoReferenciaNuevaNoInt to set
	 */
	public void setEditarAlumnoReferenciaNuevaNoInt(
			String editarAlumnoReferenciaNuevaNoInt) {
		this.editarAlumnoReferenciaNuevaNoInt = editarAlumnoReferenciaNuevaNoInt;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaCalle
	 */
	public String getEditarAlumnoReferenciaNuevaCalle() {
		return editarAlumnoReferenciaNuevaCalle;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaCalle the editarAlumnoReferenciaNuevaCalle to set
	 */
	public void setEditarAlumnoReferenciaNuevaCalle(
			String editarAlumnoReferenciaNuevaCalle) {
		this.editarAlumnoReferenciaNuevaCalle = editarAlumnoReferenciaNuevaCalle;
	}

	/**
	 * @return the editarAlumnoReferenciaNuevaTel
	 */
	public String getEditarAlumnoReferenciaNuevaTel() {
		return editarAlumnoReferenciaNuevaTel;
	}

	/**
	 * @param editarAlumnoReferenciaNuevaTel the editarAlumnoReferenciaNuevaTel to set
	 */
	public void setEditarAlumnoReferenciaNuevaTel(
			String editarAlumnoReferenciaNuevaTel) {
		this.editarAlumnoReferenciaNuevaTel = editarAlumnoReferenciaNuevaTel;
	}

	/**
	 * @return the editarAlumnoDocumentoNuevoNombre
	 */
	public String getEditarAlumnoDocumentoNuevoNombre() {
		return editarAlumnoDocumentoNuevoNombre;
	}

	/**
	 * @param editarAlumnoDocumentoNuevoNombre the editarAlumnoDocumentoNuevoNombre to set
	 */
	public void setEditarAlumnoDocumentoNuevoNombre(
			String editarAlumnoDocumentoNuevoNombre) {
		this.editarAlumnoDocumentoNuevoNombre = editarAlumnoDocumentoNuevoNombre;
	}

	/**
	 * @return the editarAlumnoDocEstatusSelected
	 */
	public short getEditarAlumnoDocEstatusSelected() {
		return editarAlumnoDocEstatusSelected;
	}

	/**
	 * @param editarAlumnoDocEstatusSelected the editarAlumnoDocEstatusSelected to set
	 */
	public void setEditarAlumnoDocEstatusSelected(
			short editarAlumnoDocEstatusSelected) {
		this.editarAlumnoDocEstatusSelected = editarAlumnoDocEstatusSelected;
	}

	@Override
	String getModulo() {
		return modulo;
	}

}
