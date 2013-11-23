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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.TipoInstructorDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.InstructorFachada;
import mx.com.asteca.util.FileExtensionUtil;
import mx.com.asteca.util.RandomString;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@ManagedBean(name = Constantes.BEAN_INSTRUCTORES)
@ViewScoped
public class InstructorControlador extends BaseController implements
		Serializable {

	private static final String modulo = Constantes.MODULO_INSTRUCTORES;

	@ManagedProperty("#{instructorFachadaImpl}")
	private transient InstructorFachada fachada;

	private List<InstructorDTO> listaItems;
	private List<InstructorDTO> filteredList;

	private InstructorDTO itemSelected;
	private InstructorDTO itemNuevo;

	private List<SelectItem> listSelect1;
	private List<SelectItem> listSelect2;
	private List<SelectItem> listSelect3;
	private List<SelectItem> listSelectTipoInstructor;
	private List<SelectItem> listSelectEstatus;

	private Short nuevoIdEstatus = 0;
	private Short editarIdEstatus = 0;

	private String selectedTipo;
	private String selectedNombre;
	private String selectedEstatus;

	private List<MateriaRegistroDTO> listaMateriasRegistros;

	private String nuevoNoEmpleado;
	private String nuevoNombre;
	private String nuevoApellidoP;
	private String nuevoApellidoM;
	private Date nuevoFechaNac;
	private String nuevoLugarNac;
	private String nuevoCURP;
	private String nuevoRFC;
	private String nuevoIFE;
	private Short nuevoIdTipoInstructor = 0;
	private String nuevoCalle;
	private String nuevoNoExt;
	private String nuevoNoInt;
	private Integer nuevoCp = 0;
	private String nuevoCpString;
	private Integer nuevoAsentamiento = 0;
	private List<MateriaRegistroDTO> nuevoListaMaterias;
	private List<MateriaRegistroDTO> nuevoListaMateriasTarget;
	private List<DocumentoDTO> nuevoListaDocumentos;
	private DualListModel<MateriaRegistroDTO> nuevoDualListModel;

	private String editarNoEmpleado;
	private String editarNombre;
	private String editarApellidoP;
	private String editarApellidoM;
	private Date editarFechaNac;
	private String editarLugarNac;
	private String editarCURP;
	private String editarRFC;
	private String editarIFE;
	private Short editarIdTipoInstructor = 0;
	private String editarCalle;
	private String editarNoExt;
	private String editarNoInt;
	private Integer editarCp = 0;
	private String editarCpString;
	private Integer editarAsentamiento = 0;
	private List<MateriaRegistroDTO> editarListaMaterias;
	private List<MateriaRegistroDTO> editarListaMateriasTarget;
	private List<DocumentoDTO> editarListaDocumentos;
	private DualListModel<MateriaRegistroDTO> editarDualListModel;

	private Short nuevoDocEstatusSelected =  0;
	private Short editarDocEstatusSelected = 0;
	private String nuevoDocRuta;
	private String nuevoDocNombre;
	private String editarDocRuta;
	private String editarDocNombre;
	private List<EstatusDTO> docListaEstatus;
	private List<SelectItem> listaSelectEstatusDocs;

	private String nuevoIdAsentamientoMunicipioEstado;
	private List<SelectItem> nuevoSelectListColonias;
	private String nuevoDelegacion;
	private String nuevoEstado;
	private String nuevoCPAutoComplete;
	private Integer nuevoCpIdAsentamiento = 0;
	private List<String> listCPString;
	private List<Integer> listaCPs;

	private String editarIdAsentamientoMunicipioEstado;
	private List<SelectItem> editarSelectListColonias;
	private String editarDelegacion;
	private String editarEstado;
	private String editarCPAutoComplete;
	private Integer editarCpIdAsentamiento = 0;

	private StreamedContent file;
	private DocumentoDTO documentoTempDownload;
	private InstructorDocumentoDTO documentoSelected;

	public InstructorControlador() {
		itemNuevo = new InstructorDTO();
		itemSelected = new InstructorDTO();
		nuevoListaDocumentos = new ArrayList<DocumentoDTO>();
		nuevoListaMaterias = new ArrayList<MateriaRegistroDTO>();
		editarListaDocumentos = new ArrayList<DocumentoDTO>();
		editarListaMaterias = new ArrayList<MateriaRegistroDTO>();
	}

private PermisosBooleanDTO permisos;
	
	@PostConstruct
	public void populate(){
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}



	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(PermisosBooleanDTO permisos) {
		this.permisos = permisos;
		super.setAlta(permisos.isAlta());
		super.setBorrar(permisos.isBorrar());
		super.setCambios(permisos.isEdicion());
		super.setConsulta(permisos.isConsulta());
		super.setImpresion(permisos.isImpresion());
	}
	private void initListaSelectEstatus() {
		try {
			if (CollectionUtils.isEmpty(listSelectEstatus)) {
				listSelectEstatus = new ArrayList<SelectItem>();
				List<EstatusDTO> lista = fachada.getEstatus();
				if (!CollectionUtils.isEmpty(lista)) {
					for (EstatusDTO dto : lista) {
						SelectItem item = new SelectItem(dto.getIdEstatus(),
								dto.getDescEstatus());
						listSelectEstatus.add(item);
					}
				}
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}

	private void initDocListaSelectEstatus() {
		if (CollectionUtils.isEmpty(listaSelectEstatusDocs)) {
			listaSelectEstatusDocs = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getDocListaEstatus())) {
				for (EstatusDTO dto : getDocListaEstatus()) {
					SelectItem item = new SelectItem(dto.getIdEstatus(),
							dto.getDescEstatus());
					listaSelectEstatusDocs.add(item);
				}
			}
		}
	}

	private void initDocListaEstatus() {
		if (CollectionUtils.isEmpty(docListaEstatus)) {
			try {
				docListaEstatus = fachada.getEstatus();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initNuevoDualListModel() {
		nuevoListaMaterias = getListaMateriasRegistros();
		nuevoListaMateriasTarget = new ArrayList<MateriaRegistroDTO>();
		nuevoDualListModel = new DualListModel<MateriaRegistroDTO>(
				nuevoListaMaterias, nuevoListaMateriasTarget);
	}

	private void initEditarDualListModel() {
		if (itemSelected != null) {
			List<MateriaRegistroDTO> lista = getListaMateriasRegistros();
			editarListaMaterias = new ArrayList<MateriaRegistroDTO>();
			editarListaMateriasTarget = new ArrayList<MateriaRegistroDTO>();
			List<InstructorMateriaDTO> listaSelected = itemSelected
					.getListInstructoresMaterias();
			if (!CollectionUtils.isEmpty(listaSelected)) {
				for (InstructorMateriaDTO dto : listaSelected) {
					editarListaMateriasTarget.add(dto.getMateria());
				}
				for (MateriaRegistroDTO dto : editarListaMateriasTarget) {
					if (!lista.contains(dto)) {
						editarListaMaterias.add(dto);
					}
				}
				editarDualListModel = new DualListModel<MateriaRegistroDTO>(
						editarListaMaterias, editarListaMateriasTarget);
			} else {
				editarListaMaterias = lista;
				editarDualListModel = new DualListModel<MateriaRegistroDTO>(
						editarListaMaterias, editarListaMateriasTarget);
			}
		}
	}

	private void initListaSelectTipoInstructor() {
		if (CollectionUtils.isEmpty(listSelectTipoInstructor)) {
			listSelectTipoInstructor = new ArrayList<SelectItem>();
			try {
				List<TipoInstructorDTO> lista = fachada.findTiposInstructores();
				if (!CollectionUtils.isEmpty(lista)) {
					for (TipoInstructorDTO dto : lista) {
						SelectItem item = new SelectItem(
								dto.getIdTipoInstructor(), dto.getNombre());
						listSelectTipoInstructor.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaItems() {
		if (CollectionUtils.isEmpty(listaItems)) {
			try {
				listaItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
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

	private void initSelectLista1() {
		if (CollectionUtils.isEmpty(listSelect1)) {
			listSelect1 = new ArrayList<SelectItem>();
			try {
				List<InstructorDTO> lista = fachada.getAll();
				if (!CollectionUtils.isEmpty(lista)) {
					for (InstructorDTO dto : lista) {
						SelectItem item = new SelectItem(dto.getTipo(),
								dto.getTipo());
						listSelect1.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initSelectLista2() {
		if (CollectionUtils.isEmpty(listSelect2)) {
			listSelect2 = new ArrayList<SelectItem>();
			try {
				List<InstructorDTO> lista = fachada.getAll();
				if (!CollectionUtils.isEmpty(lista)) {
					for (InstructorDTO dto : lista) {
						String nombre = dto.getNombre();
						SelectItem item = new SelectItem(nombre, nombre);
						listSelect2.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initSelectLista3() {
		if (CollectionUtils.isEmpty(listSelect3)) {
			listSelect3 = new ArrayList<SelectItem>();
			try {
				List<InstructorDTO> lista = fachada.getAll();
				if (!CollectionUtils.isEmpty(lista)) {
					for (InstructorDTO dto : lista) {
						SelectItem item = new SelectItem(dto.getEstatus(),
								dto.getEstatus());
						listSelect3.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaMateriaRegistros() {
		if (CollectionUtils.isEmpty(listaMateriasRegistros)) {
			try {
				listaMateriasRegistros = fachada.findMateriasRegistros();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void onDocumentoSelect(SelectEvent event) {
		documentoSelected = (InstructorDocumentoDTO) event.getObject();
		documentoTempDownload = documentoSelected.getDocumento();
	}

	public void handleSelectCambiaCp(SelectEvent e) {
		String selection = e.getObject().toString();
		nuevoCPAutoComplete = selection;
		handlerCambiaCp();
	}

	public void handleSelectCambiaCpEditar(SelectEvent e) {
		String selection = e.getObject().toString();
		editarCPAutoComplete = selection;
		handlerCambiaCpEditar();
	}

	public void handlerCambiaCp() {
		try {
			nuevoCpIdAsentamiento = Integer.parseInt(nuevoCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (nuevoCpIdAsentamiento != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(nuevoCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				nuevoSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				nuevoDelegacion = municipio.getNombre();
				nuevoEstado = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					nuevoSelectListColonias.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void handlerCambiaCpEditar() {
		try {
			editarCpIdAsentamiento = Integer.parseInt(editarCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (editarCpIdAsentamiento != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(editarCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				editarSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				editarDelegacion = municipio.getNombre();
				editarEstado = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					editarSelectListColonias.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
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

	public void handlerNuevoFileUpload(FileUploadEvent event) {
		try {
			String ruta = fachada.getRuta();
			File targetFolder = new File(ruta);
			String prefijo = RandomString.getRandomString();
			String fileName = prefijo + "_" + event.getFile().getFileName();
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					fileName));
			nuevoDocRuta = targetFolder.getAbsolutePath()
					+ targetFolder.separator +fileName;
			nuevoDocNombre = event.getFile().getFileName();
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
			addNuevoDocumento();			
		} catch (IOException e) {
			super.addErrorMessage(Constantes.ERROR_SUBIDA_ARCHIVO);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_SUBIDA_ARCHIVO);
		}
	}

	public void handlerEditarFileUpload(FileUploadEvent event) {
		try {
			String ruta = fachada.getRuta();
			File targetFolder = new File(ruta);
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			editarDocRuta = targetFolder.getAbsolutePath()
					+ targetFolder.separator + event.getFile().getFileName();
			editarDocNombre = event.getFile().getFileName();
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
			addEditarDocumento();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FachadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNuevoDocumento() {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setIdEstatus(nuevoDocEstatusSelected);
		documentoDTO.setNombre(nuevoDocNombre);
		documentoDTO.setRuta(nuevoDocRuta);
		if (CollectionUtils.isEmpty(nuevoListaDocumentos)) {
			nuevoListaDocumentos = new ArrayList<DocumentoDTO>();
		}
		nuevoListaDocumentos.add(documentoDTO);
	}

	public void addEditarDocumento() {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setIdEstatus(editarDocEstatusSelected);
		documentoDTO.setNombre(editarDocNombre);
		documentoDTO.setRuta(editarDocRuta);
		if (CollectionUtils.isEmpty(editarListaDocumentos)) {
			editarListaDocumentos = new ArrayList<DocumentoDTO>();
		}
		editarListaDocumentos.add(documentoDTO);
	}

	private boolean validaDatos() {
		if (nuevoApellidoP == null || nuevoApellidoP.isEmpty()) {
			return false;
		} else if (nuevoNombre == null || nuevoNombre.isEmpty()) {
			return false;
		} else if (nuevoNoEmpleado == null || nuevoNoEmpleado.isEmpty()) {
			return false;
		} else if (nuevoIdTipoInstructor == 0) {
			return false;
		} else if (nuevoFechaNac == null) {
			return false;
		} else if (nuevoIdEstatus == 0) {
			return false;
		} else if (nuevoCalle == null || nuevoCalle.isEmpty()) {
			return false;
		} else if (nuevoIdAsentamientoMunicipioEstado == null
				|| nuevoIdAsentamientoMunicipioEstado.isEmpty()) {
			return false;
		} else if (nuevoEstado == null || nuevoEstado.isEmpty()) {
			return false;
		} else if (nuevoDelegacion == null || nuevoDelegacion.isEmpty()) {
			return false;
		} else if (nuevoCPAutoComplete == null || nuevoCPAutoComplete.isEmpty()) {
			return false;
		}
		return true;
	}

	public void limpiarFiltrado(){
		listaItems = null;
		initListaItems();
	}
	
	public void delete(){
		try{
			fachada.remove(itemSelected);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE+":Instructor "+itemSelected.getIdInstructor()+":");
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE+":Instructor "+itemSelected.getIdInstructor()+":");
			return;
		}
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}
	
	public void save() {
		boolean b = validaDatos();
		if (b) {
			itemNuevo.setNoEmpleado(nuevoNoEmpleado);
			itemNuevo.setIdTipo(nuevoIdTipoInstructor);
			PersonaDTO dtoPersona = new PersonaDTO();
			dtoPersona.setActivo((short) 1);
			dtoPersona.setApellidoM(nuevoApellidoM);
			dtoPersona.setApellidoP(nuevoApellidoP);
			dtoPersona.setCurp(nuevoCURP);
			dtoPersona.setFechaNac(nuevoFechaNac);
			dtoPersona.setIfe(nuevoIFE);
			dtoPersona.setLugarNac(nuevoLugarNac);
			dtoPersona.setNombre(nuevoNombre);
			dtoPersona.setRfc(nuevoRFC);
			DomicilioDTO domicilioDTO = new DomicilioDTO();
			domicilioDTO.setCalle(nuevoCalle);
			domicilioDTO.setCp(nuevoCpIdAsentamiento);
			String[] datosDemograficos = nuevoIdAsentamientoMunicipioEstado
					.split("\\|");
			int idAsentamientoTemp = Integer.parseInt(datosDemograficos[0]);
			int idMunicipioTemp = Integer.parseInt(datosDemograficos[1]);
			int idEstadoTemp = Integer.parseInt(datosDemograficos[2]);
			domicilioDTO.setIdAsentamiento(idAsentamientoTemp);
			domicilioDTO.setIdEstado(idEstadoTemp);
			domicilioDTO.setIdMunicipio(idMunicipioTemp);
			domicilioDTO.setNoExterior(nuevoNoExt);
			domicilioDTO.setNoInterior(nuevoNoInt);
			itemNuevo.setDtoDomicilio(domicilioDTO);
			itemNuevo.setDtoPersona(dtoPersona);
			itemNuevo.setIdTipo(nuevoIdTipoInstructor);
			itemNuevo.setIdEstatus(nuevoIdEstatus);
			// itemNuevo.setEstatus(nuev)
			try {
				int pk = fachada.save(itemNuevo);
				itemNuevo.setIdInstructor(pk);
				for (DocumentoDTO dto : nuevoListaDocumentos) {
					int pkDoc = fachada.saveDocumento(dto);
					dto.setIdDoc(pkDoc);
					InstructorDocumentoDTO doc = new InstructorDocumentoDTO();
					doc.setDocumento(dto);
					doc.setIdInstructor(pk);
					int pkDoc2 = fachada.saveInstructorDocumento(doc);
					doc.setId(pkDoc2);
				}
				List<MateriaRegistroDTO> listaTarget = nuevoDualListModel
						.getTarget();
				List<MateriaRegistroDTO> listaSource = nuevoDualListModel
						.getSource();
				for (Object o : listaTarget) {
					Integer idMateria = Integer.parseInt(o.toString());
					InstructorMateriaDTO mat = new InstructorMateriaDTO();
					mat.setIdInstructor(pk);
					MateriaRegistroDTO materia = new MateriaRegistroDTO();
					materia.setIdMateria(idMateria);
					mat.setMateria(materia);
					int pkMat = fachada.saveInstructorMateria(mat);
					mat.setId(pkMat);
				}
				/*
				itemNuevo.setNombre(nuevoNombre + " " + nuevoApellidoP + " " +nuevoApellidoM);
				TipoInstructorDTO tipo = fachada.findTipoInstructorById(nuevoIdTipoInstructor);
				EstatusDTO estatus = fachada.findEstatusById(nuevoIdEstatus);
				itemNuevo.setTipo(tipo.getNombre());
				itemNuevo.setEstatus(estatus.getDescEstatus());
				if (!CollectionUtils.isEmpty(listaItems)) {
					listaItems.add(itemNuevo);
				} else {
					listaItems = new ArrayList<InstructorDTO>();
					listaItems.add(itemNuevo);
				}*/
				listaItems = null;
				initListaItems();
				RequestContext.getCurrentInstance().execute("nuevoDialog.hide()");
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE+":Instructor "+pk+":");
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_NUEVO_REGISTRO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE+":Instructor:");
			}
		} else {
			super.addWarningMessage(Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
		}

	}

	public void update() {
		if (editarNoEmpleado != null && !editarNoEmpleado.isEmpty()) {
			itemSelected.setNoEmpleado(editarNoEmpleado);
		}
		if (editarIdTipoInstructor != 0) {
			itemSelected.setIdTipo(editarIdTipoInstructor);
		}
		PersonaDTO dtoPersona = itemSelected.getDtoPersona();
		dtoPersona.setActivo((short) 1);
		if (editarApellidoM != null && !editarApellidoM.isEmpty()) {
			dtoPersona.setApellidoM(editarApellidoM);
		}
		if (editarApellidoP != null && !editarApellidoP.isEmpty()) {
			dtoPersona.setApellidoP(editarApellidoP);
		}
		if (editarCURP != null && !editarCURP.isEmpty()) {
			dtoPersona.setCurp(editarCURP);
		}
		if (editarFechaNac != null) {
			dtoPersona.setFechaNac(editarFechaNac);
		}
		if (editarIFE != null && !editarIFE.isEmpty()) {
			dtoPersona.setIfe(editarIFE);
		}
		if (editarLugarNac != null && !editarLugarNac.isEmpty()) {
			dtoPersona.setLugarNac(editarLugarNac);
		}
		if (editarNombre != null && !editarNombre.isEmpty()) {
			dtoPersona.setNombre(editarNombre);
		}
		if (editarRFC != null && !editarRFC.isEmpty()) {
			dtoPersona.setRfc(editarRFC);
		}
		DomicilioDTO domicilioDTO = itemSelected.getDtoDomicilio();
		if (editarCalle != null && !editarCalle.isEmpty()) {
			domicilioDTO.setCalle(editarCalle);
		}
		if (editarCpIdAsentamiento != 0) {
			domicilioDTO.setCp(editarCpIdAsentamiento);
		}
		if (editarIdAsentamientoMunicipioEstado != null
				&& !editarIdAsentamientoMunicipioEstado.isEmpty()) {
			String[] datosDemograficos = editarIdAsentamientoMunicipioEstado
					.split("\\|");
			int idAsentamientoTemp = Integer.parseInt(datosDemograficos[0]);
			int idMunicipioTemp = Integer.parseInt(datosDemograficos[1]);
			int idEstadoTemp = Integer.parseInt(datosDemograficos[2]);
			domicilioDTO.setIdAsentamiento(idAsentamientoTemp);
			domicilioDTO.setIdEstado(idEstadoTemp);
			domicilioDTO.setIdMunicipio(idMunicipioTemp);
		}
		if (editarNoExt != null && !editarNoExt.isEmpty()) {
			domicilioDTO.setNoExterior(editarNoExt);
		}
		if (editarNoInt != null && !editarNoInt.isEmpty()) {
			domicilioDTO.setNoInterior(editarNoInt);
		}
		itemSelected.setDtoDomicilio(domicilioDTO);
		itemSelected.setDtoPersona(dtoPersona);
		if (editarIdTipoInstructor != 0) {
			itemSelected.setIdTipo(editarIdTipoInstructor);
		}
		if (editarIdEstatus != 0) {
			itemSelected.setIdEstatus(editarIdEstatus);
		}
		// itemNuevo.setEstatus(nuev)
		try {
			fachada.update(itemNuevo);
			for (DocumentoDTO dto : editarListaDocumentos) {
				int pk = fachada.saveDocumento(dto);
				dto.setIdDoc(pk);
				InstructorDocumentoDTO doc = new InstructorDocumentoDTO();
				doc.setDocumento(dto);
				doc.setIdInstructor(itemSelected.getIdInstructor());
				int pkDoc = fachada.saveInstructorDocumento(doc);
				doc.setId(pkDoc);
			}
			for (MateriaRegistroDTO dto : editarListaMateriasTarget) {
				InstructorMateriaDTO mat = new InstructorMateriaDTO();
				mat.setIdInstructor(itemSelected.getIdInstructor());
				mat.setMateria(dto);
				int pkMat = fachada.saveInstructorMateria(mat);
				mat.setId(pkMat);
			}
			super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.UPDATE_REGISTRO_EXITOSO);		
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE+":Instructor "+itemSelected.getIdInstructor()+":");
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE+":Instructor:");
		}
	}

	public void saveCancel() {

	}

	// ---------- Getters & Setters --------------- //
	/**
	 * @return the fachada
	 */
	public InstructorFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(InstructorFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the listaItems
	 */
	public List<InstructorDTO> getListaItems() {
		initListaItems();
		return listaItems;
	}

	/**
	 * @param listaItems
	 *            the listaItems to set
	 */
	public void setListaItems(List<InstructorDTO> listaItems) {
		this.listaItems = listaItems;
	}

	/**
	 * @return the filteredList
	 */
	public List<InstructorDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<InstructorDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the itemSelected
	 */
	public InstructorDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected
	 *            the itemSelected to set
	 */
	public void setItemSelected(InstructorDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the itemNuevo
	 */
	public InstructorDTO getItemNuevo() {
		return itemNuevo;
	}

	/**
	 * @param itemNuevo
	 *            the itemNuevo to set
	 */
	public void setItemNuevo(InstructorDTO itemNuevo) {
		this.itemNuevo = itemNuevo;
	}

	/**
	 * @return the listSelect1
	 */
	public List<SelectItem> getListSelect1() {
		initSelectLista1();
		return listSelect1;
	}

	/**
	 * @param listSelect1
	 *            the listSelect1 to set
	 */
	public void setListSelect1(List<SelectItem> listSelect1) {
		this.listSelect1 = listSelect1;
	}

	/**
	 * @return the listSelect2
	 */
	public List<SelectItem> getListSelect2() {
		initSelectLista2();
		return listSelect2;
	}

	/**
	 * @param listSelect2
	 *            the listSelect2 to set
	 */
	public void setListSelect2(List<SelectItem> listSelect2) {
		this.listSelect2 = listSelect2;
	}

	/**
	 * @return the listSelect3
	 */
	public List<SelectItem> getListSelect3() {
		initSelectLista3();
		return listSelect3;
	}

	/**
	 * @param listSelect3
	 *            the listSelect3 to set
	 */
	public void setListSelect3(List<SelectItem> listSelect3) {
		this.listSelect3 = listSelect3;
	}

	/**
	 * @return the nuevoNoEmpleado
	 */
	public String getNuevoNoEmpleado() {
		return nuevoNoEmpleado;
	}

	/**
	 * @param nuevoNoEmpleado
	 *            the nuevoNoEmpleado to set
	 */
	public void setNuevoNoEmpleado(String nuevoNoEmpleado) {
		this.nuevoNoEmpleado = nuevoNoEmpleado;
	}

	/**
	 * @return the nuevoNombre
	 */
	public String getNuevoNombre() {
		return nuevoNombre;
	}

	/**
	 * @param nuevoNombre
	 *            the nuevoNombre to set
	 */
	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}

	/**
	 * @return the nuevoApellidoP
	 */
	public String getNuevoApellidoP() {
		return nuevoApellidoP;
	}

	/**
	 * @param nuevoApellidoP
	 *            the nuevoApellidoP to set
	 */
	public void setNuevoApellidoP(String nuevoApellidoP) {
		this.nuevoApellidoP = nuevoApellidoP;
	}

	/**
	 * @return the nuevoApellidoM
	 */
	public String getNuevoApellidoM() {
		return nuevoApellidoM;
	}

	/**
	 * @param nuevoApellidoM
	 *            the nuevoApellidoM to set
	 */
	public void setNuevoApellidoM(String nuevoApellidoM) {
		this.nuevoApellidoM = nuevoApellidoM;
	}

	/**
	 * @return the nuevoFechaNac
	 */
	public Date getNuevoFechaNac() {
		return nuevoFechaNac;
	}

	/**
	 * @param nuevoFechaNac
	 *            the nuevoFechaNac to set
	 */
	public void setNuevoFechaNac(Date nuevoFechaNac) {
		this.nuevoFechaNac = nuevoFechaNac;
	}

	/**
	 * @return the nuevoLugarNac
	 */
	public String getNuevoLugarNac() {
		return nuevoLugarNac;
	}

	/**
	 * @param nuevoLugarNac
	 *            the nuevoLugarNac to set
	 */
	public void setNuevoLugarNac(String nuevoLugarNac) {
		this.nuevoLugarNac = nuevoLugarNac;
	}

	/**
	 * @return the nuevoCURP
	 */
	public String getNuevoCURP() {
		return nuevoCURP;
	}

	/**
	 * @param nuevoCURP
	 *            the nuevoCURP to set
	 */
	public void setNuevoCURP(String nuevoCURP) {
		this.nuevoCURP = nuevoCURP;
	}

	/**
	 * @return the nuevoRFC
	 */
	public String getNuevoRFC() {
		return nuevoRFC;
	}

	/**
	 * @param nuevoRFC
	 *            the nuevoRFC to set
	 */
	public void setNuevoRFC(String nuevoRFC) {
		this.nuevoRFC = nuevoRFC;
	}

	/**
	 * @return the nuevoIFE
	 */
	public String getNuevoIFE() {
		return nuevoIFE;
	}

	/**
	 * @param nuevoIFE
	 *            the nuevoIFE to set
	 */
	public void setNuevoIFE(String nuevoIFE) {
		this.nuevoIFE = nuevoIFE;
	}

	/**
	 * @return the nuevoIdTipoInstructor
	 */
	public Short getNuevoIdTipoInstructor() {
		return nuevoIdTipoInstructor;
	}

	/**
	 * @param nuevoIdTipoInstructor
	 *            the nuevoIdTipoInstructor to set
	 */
	public void setNuevoIdTipoInstructor(Short nuevoIdTipoInstructor) {
		this.nuevoIdTipoInstructor = nuevoIdTipoInstructor;
	}

	/**
	 * @return the nuevoCalle
	 */
	public String getNuevoCalle() {
		return nuevoCalle;
	}

	/**
	 * @param nuevoCalle
	 *            the nuevoCalle to set
	 */
	public void setNuevoCalle(String nuevoCalle) {
		this.nuevoCalle = nuevoCalle;
	}

	/**
	 * @return the nuevoNoExt
	 */
	public String getNuevoNoExt() {
		return nuevoNoExt;
	}

	/**
	 * @param nuevoNoExt
	 *            the nuevoNoExt to set
	 */
	public void setNuevoNoExt(String nuevoNoExt) {
		this.nuevoNoExt = nuevoNoExt;
	}

	/**
	 * @return the nuevoNoInt
	 */
	public String getNuevoNoInt() {
		return nuevoNoInt;
	}

	/**
	 * @param nuevoNoInt
	 *            the nuevoNoInt to set
	 */
	public void setNuevoNoInt(String nuevoNoInt) {
		this.nuevoNoInt = nuevoNoInt;
	}

	/**
	 * @return the nuevoCp
	 */
	public Integer getNuevoCp() {
		return nuevoCp;
	}

	/**
	 * @param nuevoCp
	 *            the nuevoCp to set
	 */
	public void setNuevoCp(Integer nuevoCp) {
		this.nuevoCp = nuevoCp;
	}

	/**
	 * @return the nuevoCpString
	 */
	public String getNuevoCpString() {
		return nuevoCpString;
	}

	/**
	 * @param nuevoCpString
	 *            the nuevoCpString to set
	 */
	public void setNuevoCpString(String nuevoCpString) {
		this.nuevoCpString = nuevoCpString;
	}

	/**
	 * @return the nuevoAsentamiento
	 */
	public Integer getNuevoAsentamiento() {
		return nuevoAsentamiento;
	}

	/**
	 * @param nuevoAsentamiento
	 *            the nuevoAsentamiento to set
	 */
	public void setNuevoAsentamiento(Integer nuevoAsentamiento) {
		this.nuevoAsentamiento = nuevoAsentamiento;
	}

	/**
	 * @return the nuevoListaMaterias
	 */
	public List<MateriaRegistroDTO> getNuevoListaMaterias() {
		return nuevoListaMaterias;
	}

	/**
	 * @param nuevoListaMaterias
	 *            the nuevoListaMaterias to set
	 */
	public void setNuevoListaMaterias(
			List<MateriaRegistroDTO> nuevoListaMaterias) {
		this.nuevoListaMaterias = nuevoListaMaterias;
	}

	/**
	 * @return the nuevoListaDocumentos
	 */
	public List<DocumentoDTO> getNuevoListaDocumentos() {
		return nuevoListaDocumentos;
	}

	/**
	 * @param nuevoListaDocumentos
	 *            the nuevoListaDocumentos to set
	 */
	public void setNuevoListaDocumentos(List<DocumentoDTO> nuevoListaDocumentos) {
		this.nuevoListaDocumentos = nuevoListaDocumentos;
	}

	/**
	 * @return the editarNoEmpleado
	 */
	public String getEditarNoEmpleado() {
		return editarNoEmpleado;
	}

	/**
	 * @param editarNoEmpleado
	 *            the editarNoEmpleado to set
	 */
	public void setEditarNoEmpleado(String editarNoEmpleado) {
		this.editarNoEmpleado = editarNoEmpleado;
	}

	/**
	 * @return the editarNombre
	 */
	public String getEditarNombre() {
		return editarNombre;
	}

	/**
	 * @param editarNombre
	 *            the editarNombre to set
	 */
	public void setEditarNombre(String editarNombre) {
		this.editarNombre = editarNombre;
	}

	/**
	 * @return the editarApellidoP
	 */
	public String getEditarApellidoP() {
		return editarApellidoP;
	}

	/**
	 * @param editarApellidoP
	 *            the editarApellidoP to set
	 */
	public void setEditarApellidoP(String editarApellidoP) {
		this.editarApellidoP = editarApellidoP;
	}

	/**
	 * @return the editarApellidoM
	 */
	public String getEditarApellidoM() {
		return editarApellidoM;
	}

	/**
	 * @param editarApellidoM
	 *            the editarApellidoM to set
	 */
	public void setEditarApellidoM(String editarApellidoM) {
		this.editarApellidoM = editarApellidoM;
	}

	/**
	 * @return the editarFechaNac
	 */
	public Date getEditarFechaNac() {
		return editarFechaNac;
	}

	/**
	 * @param editarFechaNac
	 *            the editarFechaNac to set
	 */
	public void setEditarFechaNac(Date editarFechaNac) {
		this.editarFechaNac = editarFechaNac;
	}

	/**
	 * @return the editarLugarNac
	 */
	public String getEditarLugarNac() {
		return editarLugarNac;
	}

	/**
	 * @param editarLugarNac
	 *            the editarLugarNac to set
	 */
	public void setEditarLugarNac(String editarLugarNac) {
		this.editarLugarNac = editarLugarNac;
	}

	/**
	 * @return the editarCURP
	 */
	public String getEditarCURP() {
		return editarCURP;
	}

	/**
	 * @param editarCURP
	 *            the editarCURP to set
	 */
	public void setEditarCURP(String editarCURP) {
		this.editarCURP = editarCURP;
	}

	/**
	 * @return the editarRFC
	 */
	public String getEditarRFC() {
		return editarRFC;
	}

	/**
	 * @param editarRFC
	 *            the editarRFC to set
	 */
	public void setEditarRFC(String editarRFC) {
		this.editarRFC = editarRFC;
	}

	/**
	 * @return the editarIFE
	 */
	public String getEditarIFE() {
		return editarIFE;
	}

	/**
	 * @param editarIFE
	 *            the editarIFE to set
	 */
	public void setEditarIFE(String editarIFE) {
		this.editarIFE = editarIFE;
	}

	/**
	 * @return the editarIdTipoInstructor
	 */
	public Short getEditarIdTipoInstructor() {
		return editarIdTipoInstructor;
	}

	/**
	 * @param editarIdTipoInstructor
	 *            the editarIdTipoInstructor to set
	 */
	public void setEditarIdTipoInstructor(Short editarIdTipoInstructor) {
		this.editarIdTipoInstructor = editarIdTipoInstructor;
	}

	/**
	 * @return the editarCalle
	 */
	public String getEditarCalle() {
		return editarCalle;
	}

	/**
	 * @param editarCalle
	 *            the editarCalle to set
	 */
	public void setEditarCalle(String editarCalle) {
		this.editarCalle = editarCalle;
	}

	/**
	 * @return the editarNoExt
	 */
	public String getEditarNoExt() {
		return editarNoExt;
	}

	/**
	 * @param editarNoExt
	 *            the editarNoExt to set
	 */
	public void setEditarNoExt(String editarNoExt) {
		this.editarNoExt = editarNoExt;
	}

	/**
	 * @return the editarNoInt
	 */
	public String getEditarNoInt() {
		return editarNoInt;
	}

	/**
	 * @param editarNoInt
	 *            the editarNoInt to set
	 */
	public void setEditarNoInt(String editarNoInt) {
		this.editarNoInt = editarNoInt;
	}

	/**
	 * @return the editarCp
	 */
	public Integer getEditarCp() {
		return editarCp;
	}

	/**
	 * @param editarCp
	 *            the editarCp to set
	 */
	public void setEditarCp(Integer editarCp) {
		this.editarCp = editarCp;
	}

	/**
	 * @return the editarCpString
	 */
	public String getEditarCpString() {
		return editarCpString;
	}

	/**
	 * @param editarCpString
	 *            the editarCpString to set
	 */
	public void setEditarCpString(String editarCpString) {
		this.editarCpString = editarCpString;
	}

	/**
	 * @return the editarAsentamiento
	 */
	public Integer getEditarAsentamiento() {
		return editarAsentamiento;
	}

	/**
	 * @param editarAsentamiento
	 *            the editarAsentamiento to set
	 */
	public void setEditarAsentamiento(Integer editarAsentamiento) {
		this.editarAsentamiento = editarAsentamiento;
	}

	/**
	 * @return the editarListaMaterias
	 */
	public List<MateriaRegistroDTO> getEditarListaMaterias() {
		return editarListaMaterias;
	}

	/**
	 * @param editarListaMaterias
	 *            the editarListaMaterias to set
	 */
	public void setEditarListaMaterias(
			List<MateriaRegistroDTO> editarListaMaterias) {
		this.editarListaMaterias = editarListaMaterias;
	}

	/**
	 * @return the editarListaDocumentos
	 */
	public List<DocumentoDTO> getEditarListaDocumentos() {
		return editarListaDocumentos;
	}

	/**
	 * @param editarListaDocumentos
	 *            the editarListaDocumentos to set
	 */
	public void setEditarListaDocumentos(
			List<DocumentoDTO> editarListaDocumentos) {
		this.editarListaDocumentos = editarListaDocumentos;
	}

	/**
	 * @return the listaMateriasRegistros
	 */
	public List<MateriaRegistroDTO> getListaMateriasRegistros() {
		initListaMateriaRegistros();
		return listaMateriasRegistros;
	}

	/**
	 * @param listaMateriasRegistros
	 *            the listaMateriasRegistros to set
	 */
	public void setListaMateriasRegistros(
			List<MateriaRegistroDTO> listaMateriasRegistros) {
		this.listaMateriasRegistros = listaMateriasRegistros;
	}

	/**
	 * @return the selectedTipo
	 */
	public String getSelectedTipo() {
		return selectedTipo;
	}

	/**
	 * @param selectedTipo
	 *            the selectedTipo to set
	 */
	public void setSelectedTipo(String selectedTipo) {
		this.selectedTipo = selectedTipo;
	}

	/**
	 * @return the selectedNombre
	 */
	public String getSelectedNombre() {
		return selectedNombre;
	}

	/**
	 * @param selectedNombre
	 *            the selectedNombre to set
	 */
	public void setSelectedNombre(String selectedNombre) {
		this.selectedNombre = selectedNombre;
	}

	/**
	 * @return the selectedEstatus
	 */
	public String getSelectedEstatus() {
		return selectedEstatus;
	}

	/**
	 * @param selectedEstatus
	 *            the selectedEstatus to set
	 */
	public void setSelectedEstatus(String selectedEstatus) {
		this.selectedEstatus = selectedEstatus;
	}

	/**
	 * @return the listSelectTipoInstructor
	 */
	public List<SelectItem> getListSelectTipoInstructor() {
		initListaSelectTipoInstructor();
		return listSelectTipoInstructor;
	}

	/**
	 * @param listSelectTipoInstructor
	 *            the listSelectTipoInstructor to set
	 */
	public void setListSelectTipoInstructor(
			List<SelectItem> listSelectTipoInstructor) {
		this.listSelectTipoInstructor = listSelectTipoInstructor;
	}

	/**
	 * @return the nuevoListaMateriasTarget
	 */
	public List<MateriaRegistroDTO> getNuevoListaMateriasTarget() {
		return nuevoListaMateriasTarget;
	}

	/**
	 * @param nuevoListaMateriasTarget
	 *            the nuevoListaMateriasTarget to set
	 */
	public void setNuevoListaMateriasTarget(
			List<MateriaRegistroDTO> nuevoListaMateriasTarget) {
		this.nuevoListaMateriasTarget = nuevoListaMateriasTarget;
	}

	/**
	 * @return the nuevoDualListModel
	 */
	public DualListModel<MateriaRegistroDTO> getNuevoDualListModel() {
		initNuevoDualListModel();
		return nuevoDualListModel;
	}

	/**
	 * @param nuevoDualListModel
	 *            the nuevoDualListModel to set
	 */
	public void setNuevoDualListModel(
			DualListModel<MateriaRegistroDTO> nuevoDualListModel) {
		this.nuevoDualListModel = nuevoDualListModel;
	}

	/**
	 * @return the editarListaMateriasTarget
	 */
	public List<MateriaRegistroDTO> getEditarListaMateriasTarget() {
		return editarListaMateriasTarget;
	}

	/**
	 * @param editarListaMateriasTarget
	 *            the editarListaMateriasTarget to set
	 */
	public void setEditarListaMateriasTarget(
			List<MateriaRegistroDTO> editarListaMateriasTarget) {
		this.editarListaMateriasTarget = editarListaMateriasTarget;
	}

	/**
	 * @return the editarDualListModel
	 */
	public DualListModel<MateriaRegistroDTO> getEditarDualListModel() {
		initEditarDualListModel();
		return editarDualListModel;
	}

	/**
	 * @param editarDualListModel
	 *            the editarDualListModel to set
	 */
	public void setEditarDualListModel(
			DualListModel<MateriaRegistroDTO> editarDualListModel) {
		this.editarDualListModel = editarDualListModel;
	}

	/**
	 * @return the nuevoDocEstatusSelected
	 */
	public Short getNuevoDocEstatusSelected() {
		return nuevoDocEstatusSelected;
	}

	/**
	 * @param nuevoDocEstatusSelected
	 *            the nuevoDocEstatusSelected to set
	 */
	public void setNuevoDocEstatusSelected(Short nuevoDocEstatusSelected) {
		this.nuevoDocEstatusSelected = nuevoDocEstatusSelected;
	}

	/**
	 * @return the editarDocEstatusSelected
	 */
	public Short getEditarDocEstatusSelected() {
		return editarDocEstatusSelected;
	}

	/**
	 * @param editarDocEstatusSelected
	 *            the editarDocEstatusSelected to set
	 */
	public void setEditarDocEstatusSelected(Short editarDocEstatusSelected) {
		this.editarDocEstatusSelected = editarDocEstatusSelected;
	}

	/**
	 * @return the docListaEstatus
	 */
	public List<EstatusDTO> getDocListaEstatus() {
		initDocListaEstatus();
		return docListaEstatus;
	}

	/**
	 * @param docListaEstatus
	 *            the docListaEstatus to set
	 */
	public void setDocListaEstatus(List<EstatusDTO> docListaEstatus) {
		this.docListaEstatus = docListaEstatus;
	}

	/**
	 * @return the listaSelectEstatusDocs
	 */
	public List<SelectItem> getListaSelectEstatusDocs() {
		initDocListaSelectEstatus();
		return listaSelectEstatusDocs;
	}

	/**
	 * @param listaSelectEstatusDocs
	 *            the listaSelectEstatusDocs to set
	 */
	public void setListaSelectEstatusDocs(
			List<SelectItem> listaSelectEstatusDocs) {
		this.listaSelectEstatusDocs = listaSelectEstatusDocs;
	}

	/**
	 * @return the nuevoDocRuta
	 */
	public String getNuevoDocRuta() {
		return nuevoDocRuta;
	}

	/**
	 * @param nuevoDocRuta
	 *            the nuevoDocRuta to set
	 */
	public void setNuevoDocRuta(String nuevoDocRuta) {
		this.nuevoDocRuta = nuevoDocRuta;
	}

	/**
	 * @return the nuevoDocNombre
	 */
	public String getNuevoDocNombre() {
		return nuevoDocNombre;
	}

	/**
	 * @param nuevoDocNombre
	 *            the nuevoDocNombre to set
	 */
	public void setNuevoDocNombre(String nuevoDocNombre) {
		this.nuevoDocNombre = nuevoDocNombre;
	}

	/**
	 * @return the nuevoIdAsentamientoMunicipioEstado
	 */
	public String getNuevoIdAsentamientoMunicipioEstado() {
		return nuevoIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param nuevoIdAsentamientoMunicipioEstado
	 *            the nuevoIdAsentamientoMunicipioEstado to set
	 */
	public void setNuevoIdAsentamientoMunicipioEstado(
			String nuevoIdAsentamientoMunicipioEstado) {
		this.nuevoIdAsentamientoMunicipioEstado = nuevoIdAsentamientoMunicipioEstado;
	}

	/**
	 * @return the nuevoSelectListColonias
	 */
	public List<SelectItem> getNuevoSelectListColonias() {
		return nuevoSelectListColonias;
	}

	/**
	 * @param nuevoSelectListColonias
	 *            the nuevoSelectListColonias to set
	 */
	public void setNuevoSelectListColonias(
			List<SelectItem> nuevoSelectListColonias) {
		this.nuevoSelectListColonias = nuevoSelectListColonias;
	}

	/**
	 * @return the nuevoDelegacion
	 */
	public String getNuevoDelegacion() {
		return nuevoDelegacion;
	}

	/**
	 * @param nuevoDelegacion
	 *            the nuevoDelegacion to set
	 */
	public void setNuevoDelegacion(String nuevoDelegacion) {
		this.nuevoDelegacion = nuevoDelegacion;
	}

	/**
	 * @return the nuevoEstado
	 */
	public String getNuevoEstado() {
		return nuevoEstado;
	}

	/**
	 * @param nuevoEstado
	 *            the nuevoEstado to set
	 */
	public void setNuevoEstado(String nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}

	/**
	 * @return the nuevoCPAutoComplete
	 */
	public String getNuevoCPAutoComplete() {
		return nuevoCPAutoComplete;
	}

	/**
	 * @param nuevoCPAutoComplete
	 *            the nuevoCPAutoComplete to set
	 */
	public void setNuevoCPAutoComplete(String nuevoCPAutoComplete) {
		this.nuevoCPAutoComplete = nuevoCPAutoComplete;
	}

	/**
	 * @return the nuevoCpIdAsentamiento
	 */
	public Integer getNuevoCpIdAsentamiento() {
		return nuevoCpIdAsentamiento;
	}

	/**
	 * @param nuevoCpIdAsentamiento
	 *            the nuevoCpIdAsentamiento to set
	 */
	public void setNuevoCpIdAsentamiento(Integer nuevoCpIdAsentamiento) {
		this.nuevoCpIdAsentamiento = nuevoCpIdAsentamiento;
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
	 * @return the listaCPs
	 */
	public List<Integer> getListaCPs() {
		initListaCPs();
		return listaCPs;
	}

	/**
	 * @param listaCPs
	 *            the listaCPs to set
	 */
	public void setListaCPs(List<Integer> listaCPs) {
		this.listaCPs = listaCPs;
	}

	/**
	 * @return the editarDocRuta
	 */
	public String getEditarDocRuta() {
		return editarDocRuta;
	}

	/**
	 * @param editarDocRuta
	 *            the editarDocRuta to set
	 */
	public void setEditarDocRuta(String editarDocRuta) {
		this.editarDocRuta = editarDocRuta;
	}

	/**
	 * @return the editarDocNombre
	 */
	public String getEditarDocNombre() {
		return editarDocNombre;
	}

	/**
	 * @param editarDocNombre
	 *            the editarDocNombre to set
	 */
	public void setEditarDocNombre(String editarDocNombre) {
		this.editarDocNombre = editarDocNombre;
	}

	/**
	 * @return the editarIdAsentamientoMunicipioEstado
	 */
	public String getEditarIdAsentamientoMunicipioEstado() {
		return editarIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param editarIdAsentamientoMunicipioEstado
	 *            the editarIdAsentamientoMunicipioEstado to set
	 */
	public void setEditarIdAsentamientoMunicipioEstado(
			String editarIdAsentamientoMunicipioEstado) {
		this.editarIdAsentamientoMunicipioEstado = editarIdAsentamientoMunicipioEstado;
	}

	/**
	 * @return the editarSelectListColonias
	 */
	public List<SelectItem> getEditarSelectListColonias() {
		return editarSelectListColonias;
	}

	/**
	 * @param editarSelectListColonias
	 *            the editarSelectListColonias to set
	 */
	public void setEditarSelectListColonias(
			List<SelectItem> editarSelectListColonias) {
		this.editarSelectListColonias = editarSelectListColonias;
	}

	/**
	 * @return the editarDelegacion
	 */
	public String getEditarDelegacion() {
		return editarDelegacion;
	}

	/**
	 * @param editarDelegacion
	 *            the editarDelegacion to set
	 */
	public void setEditarDelegacion(String editarDelegacion) {
		this.editarDelegacion = editarDelegacion;
	}

	/**
	 * @return the editarEstado
	 */
	public String getEditarEstado() {
		return editarEstado;
	}

	/**
	 * @param editarEstado
	 *            the editarEstado to set
	 */
	public void setEditarEstado(String editarEstado) {
		this.editarEstado = editarEstado;
	}

	/**
	 * @return the editarCPAutoComplete
	 */
	public String getEditarCPAutoComplete() {
		return editarCPAutoComplete;
	}

	/**
	 * @param editarCPAutoComplete
	 *            the editarCPAutoComplete to set
	 */
	public void setEditarCPAutoComplete(String editarCPAutoComplete) {
		this.editarCPAutoComplete = editarCPAutoComplete;
	}

	/**
	 * @return the editarCpIdAsentamiento
	 */
	public Integer getEditarCpIdAsentamiento() {
		return editarCpIdAsentamiento;
	}

	/**
	 * @param editarCpIdAsentamiento
	 *            the editarCpIdAsentamiento to set
	 */
	public void setEditarCpIdAsentamiento(Integer editarCpIdAsentamiento) {
		this.editarCpIdAsentamiento = editarCpIdAsentamiento;
	}

	@Override
	String getModulo() {
		return modulo;
	}

	/**
	 * @return the listSelectEstatus
	 */
	public List<SelectItem> getListSelectEstatus() {
		initListaSelectEstatus();
		return listSelectEstatus;
	}

	/**
	 * @param listSelectEstatus
	 *            the listSelectEstatus to set
	 */
	public void setListSelectEstatus(List<SelectItem> listSelectEstatus) {
		this.listSelectEstatus = listSelectEstatus;
	}

	/**
	 * @return the nuevoIdEstatus
	 */
	public Short getNuevoIdEstatus() {
		return nuevoIdEstatus;
	}

	/**
	 * @param nuevoIdEstatus
	 *            the nuevoIdEstatus to set
	 */
	public void setNuevoIdEstatus(Short nuevoIdEstatus) {
		this.nuevoIdEstatus = nuevoIdEstatus;
	}

	/**
	 * @return the editarIdEstatus
	 */
	public Short getEditarIdEstatus() {
		return editarIdEstatus;
	}

	/**
	 * @param editarIdEstatus
	 *            the editarIdEstatus to set
	 */
	public void setEditarIdEstatus(Short editarIdEstatus) {
		this.editarIdEstatus = editarIdEstatus;
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
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						"Archivo no disponible");
			}
			file = new DefaultStreamedContent(stream, mimeMapping, nombre);
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
	 * @return the documentoSelected
	 */
	public InstructorDocumentoDTO getDocumentoSelected() {
		return documentoSelected;
	}

	/**
	 * @param documentoSelected
	 *            the documentoSelected to set
	 */
	public void setDocumentoSelected(InstructorDocumentoDTO documentoSelected) {
		this.documentoSelected = documentoSelected;
	}

}
