/**
 * 
 */
package mx.com.asteca.vista;

import java.io.File;
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
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ProgramaEstudiosFachada;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@ManagedBean(name = Constantes.BEAN_PROGRAMA_ESTUDIOS)
@ViewScoped
public class ProgramaEstudiosControlador extends BaseController implements
		Serializable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgramaEstudiosControlador.class);
	private static final String modulo = Constantes.MODULO_PROGRAMA_ESTUDIOS; 

	@ManagedProperty("#{programaEstudiosFachadaImpl}")
	private transient ProgramaEstudiosFachada fachada;

	private List<ProgramaEstudiosDTO> listItems;
	private List<SelectItem> listSelect1;
	private List<SelectItem> listSelect2;
	private List<SelectItem> listSelect3;

	private ProgramaEstudiosDTO selectedItem;
	private ProgramaEstudiosDTO nuevoItem;

	private String selectedClave;
	private String selectedDsc;
	private int selectedTipo;

	private List<ProgramaEstudiosDTO> filteredList;
	private List<ProgramaEstudiosMateriasDTO> listMateriasVer;
	private List<ProgramaEstudiosMateriasDTO> listMateriasNuevo;
	private List<ProgramaEstudiosMateriasDTO> listMateriasEditar;
	private ProgramaEstudiosMateriasDTO materiaSelected;
	private List<ProgramaEstudiosAutorizacionDTO> listAutVer;
	private List<ProgramaEstudiosAutorizacionDTO> listAutNuevo;
	private List<ProgramaEstudiosAutorizacionDTO> listAutEditar;
	private ProgramaEstudiosAutorizacionDTO autSelected;

	private String nuevoClave;
	private String nuevoDsc;
	private int nuevoIdTipo;
	private String nuevoNoAut;
	private String nuevoHorasPractica;
	private String nuevoHorasTeoria;
	private Date nuevoFechaAut;
	private String nuevoMateriaNombre;
	private int nuevoMateriaId;
	private String nuevoMateriaHorasPracticas;
	private String nuevoMateriaHorasTeoria;
	private String nuevoDocRuta;
	private String nuevoDocNombre;

	private String editarClave;
	private String editarDsc;
	private int editarIdTipo;
	private String editarNoAut;
	private String editarHorasPractica;
	private String editarHorasTeoria;
	private Date editarFechaAut;
	private String editarMateriaNombre;
	private int editarMateriaId;
	private String editarMateriaHorasPracticas;
	private String editarMateriaHorasTeoria;
	private String editarDocRuta;
	private String editarDocNombre;

	private List<SelectItem> listTipos;
	private List<SelectItem> listMateriasDisponibles;

	public ProgramaEstudiosControlador() {
		selectedItem = new ProgramaEstudiosDTO();
		nuevoItem = new ProgramaEstudiosDTO();
		listMateriasNuevo = new ArrayList<ProgramaEstudiosMateriasDTO>();
		listAutNuevo = new ArrayList<ProgramaEstudiosAutorizacionDTO>();
	}

	private void initListMateriasDisponibles() {
		if (CollectionUtils.isEmpty(listMateriasDisponibles)) {
			listMateriasDisponibles = new ArrayList<SelectItem>();
			try {
				List<MateriaRegistroDTO> lista = fachada.findMaterias();
				if (!CollectionUtils.isEmpty(lista)) {
					for (MateriaRegistroDTO dto : lista) {
						SelectItem item = new SelectItem(dto.getIdMateria(),
								dto.getNombre());
						listMateriasDisponibles.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
				LOGGER.error("Error catalogo", e);
				
			}
		}
	}

	private void initSelectListTipos() {
		try {
			if (CollectionUtils.isEmpty(listTipos)) {
				List<CatGralDTO> lista = fachada.findTiposClasifCursos();
				listTipos = new ArrayList<SelectItem>();
				if (!CollectionUtils.isEmpty(lista)) {
					for (CatGralDTO dto : lista) {
						SelectItem item = new SelectItem(dto.getIdCatGral(),
								dto.getDsc());
						listTipos.add(item);
					}
				}
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			LOGGER.error("Error catalogo", e);
		}
	}

	private void initListItems() {
		if (CollectionUtils.isEmpty(listItems)) {
			try {
				listItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
				LOGGER.error("Error catalogo", e);
			}
		}
	}

	private void initListSelect1() {
		if (CollectionUtils.isEmpty(listSelect1)) {
			listSelect1 = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListItems())) {
				for (ProgramaEstudiosDTO dto : getListItems()) {
					SelectItem item = new SelectItem(dto.getClave(),
							dto.getClave());
					listSelect1.add(item);
				}
			}
		}
	}

	private void initListSelect2() {
		if (CollectionUtils.isEmpty(listSelect2)) {
			listSelect2 = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListItems())) {
				for (ProgramaEstudiosDTO dto : getListItems()) {
					SelectItem item = new SelectItem(dto.getDsc(), dto.getDsc());
					listSelect2.add(item);
				}
			}
		}
	}

	private void initListSelect3() {
		if (CollectionUtils.isEmpty(listSelect3)) {
			listSelect3 = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListItems())) {
				for (ProgramaEstudiosDTO dto : getListItems()) {
					SelectItem item = new SelectItem(dto.getTipo(),
							dto.getTipo());
					listSelect3.add(item);
				}
			}
		}
	}
	
	public void limpiarFiltrado(){
		try {
			listItems = fachada.getAll();
			selectedClave = "";
			selectedDsc = "";
			selectedTipo = 0;
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}

	public void buscarFiltrado(){
		try{
			boolean cve = selectedClave == null || selectedClave.isEmpty() ? false : true;
			boolean dsc = selectedDsc == null || selectedDsc.isEmpty() ? false : true;
			boolean tipo = selectedTipo == 0 ? false : true;
			if(cve && dsc && tipo){
				listItems = fachada.findByClaveDscAndTipo(selectedClave, selectedDsc, selectedTipo);
			}else if(cve && dsc){
				listItems = fachada.findByClaveAndDsc(selectedClave, selectedDsc);
			}else if(dsc && tipo){
				listItems = fachada.findByDscAndTipo(selectedDsc, selectedTipo);
			}else if(cve && tipo){
				listItems = fachada.findByClaveAndTipo(selectedClave, selectedTipo);
			}else if(cve){
				listItems = fachada.findByClave(selectedClave);
			}else if(dsc){
				listItems = fachada.findByDsc(selectedDsc);
			}else if(tipo){
				listItems = fachada.findByTipo(selectedTipo);
			}else{
				listItems = fachada.getAll();
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private boolean validaDatos(){
		if (!(nuevoClave == null) || !nuevoClave.isEmpty()) {
			return false;
		}else if (!(nuevoDsc == null) || !nuevoDsc.isEmpty()) {
			return false;
		}else if (!(nuevoHorasPractica == null) || !nuevoHorasPractica.isEmpty()) {
			return false;
		}else if (!(nuevoHorasTeoria == null) || !nuevoHorasTeoria.isEmpty()) {
			return false;
		}else if (!(nuevoNoAut == null) || !nuevoNoAut.isEmpty()) {
			return false;
		}else if (!(nuevoItem == null)) {
			return false;
		}else if (CollectionUtils.isEmpty(listAutNuevo)) {
			return false;
		}else if (nuevoIdTipo == 0) {
			return false;
		}
		return true;
	}
	
	public void save() {
		if (validaDatos()) {
			try {
				nuevoItem.setClave(nuevoClave);
				nuevoItem.setDsc(nuevoDsc);
				nuevoItem.setFechaAut(nuevoFechaAut);
				nuevoItem.setHorasPractica(Integer.parseInt(nuevoHorasPractica));
				nuevoItem.setHorasTeoria(Integer.parseInt(nuevoHorasTeoria));
				nuevoItem.setIdTipo(nuevoIdTipo);
				nuevoItem.setNoAut(nuevoNoAut);
				int pk = fachada.save(nuevoItem);
				if (!CollectionUtils.isEmpty(listAutNuevo)) {
					for (ProgramaEstudiosAutorizacionDTO dto : listAutNuevo) {
						dto.setIdProgramaEstudios(pk);
						int pkTemp = fachada.saveAutorizacion(dto);
						dto.setIdAutorizacion(pkTemp);
					}
				}
				if (!CollectionUtils.isEmpty(listMateriasNuevo)) {
					for (ProgramaEstudiosMateriasDTO dto : listMateriasNuevo) {
						dto.setIdProgrEstudios(pk);
						int pkTemp = fachada.saveEstudioMateria(dto);
						dto.setIdProgrEstMateria(pkTemp);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_NUEVO_REGISTRO);
			}
		} else {
			super.addWarningMessage(Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
		}
		
	}

	public void update() {
		try {
			if(editarClave != null && !editarClave.isEmpty()){
				selectedItem.setClave(editarClave);
			}
			if(editarDsc != null && !editarDsc.isEmpty()){
				selectedItem.setDsc(editarDsc);
			}
			if(editarFechaAut != null){
				selectedItem.setFechaAut(editarFechaAut);
			}
			if(editarHorasPractica != null && !editarHorasPractica.isEmpty()){
				selectedItem.setHorasPractica(Integer.parseInt(editarHorasPractica));
			}
			if(editarHorasTeoria != null && !editarHorasTeoria.isEmpty()){
				selectedItem.setHorasTeoria(Integer.parseInt(editarHorasTeoria));
			}
			if(editarIdTipo != 0){
				selectedItem.setIdTipo(editarIdTipo);
			}
			if(editarNoAut != null && !editarNoAut.isEmpty()){
				selectedItem.setNoAut(editarNoAut);
			}
			fachada.update(selectedItem);
			if (!CollectionUtils.isEmpty(listAutEditar)) {// TODO obtener las
															// listas
															// originales,
															// eliminar las
															// residuales
				for (ProgramaEstudiosAutorizacionDTO dto : listAutEditar) {
					dto.setIdProgramaEstudios(selectedItem.getIdProgEstudio());
					fachada.saveOrUpdateAutorizacion(dto);
				}
			}
			if (!CollectionUtils.isEmpty(listMateriasNuevo)) {// TODO obtener
																// las listas
																// originales,
																// eliminar las
																// residuales
				for (ProgramaEstudiosMateriasDTO dto : listMateriasNuevo) {
					dto.setIdProgrEstudios(selectedItem.getIdProgEstudio());
					fachada.saveOrUpdateEstudioMateria(dto);
				}
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
		}
	}

	public void delete() {
		try {
			List<ProgramaEstudiosAutorizacionDTO> list1 = selectedItem
					.getListAutorizaciones();
			List<ProgramaEstudiosMateriasDTO> list2 = selectedItem
					.getListMaterias();
			if (!CollectionUtils.isEmpty(list1)) {
				for(ProgramaEstudiosAutorizacionDTO dto : list1){
					fachada.removeAutorizacion(dto);
				}
			}
			if (!CollectionUtils.isEmpty(list2)) {
				for(ProgramaEstudiosMateriasDTO dto : list2){
					fachada.removeEstudioMateria(dto);
				}
			}
			fachada.remove(selectedItem);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
		}
	}

	public void saveCancel() {
		nuevoItem = new ProgramaEstudiosDTO();
		listAutNuevo = new ArrayList<ProgramaEstudiosAutorizacionDTO>();
		listMateriasNuevo = new ArrayList<ProgramaEstudiosMateriasDTO>();
		nuevoClave = "";
		nuevoDsc = "";
		nuevoIdTipo = 0;
		nuevoNoAut = "";
		nuevoHorasPractica = "";
		nuevoHorasTeoria = "";
		nuevoFechaAut = null;
		nuevoMateriaNombre = "";
		nuevoMateriaId = 0;
		nuevoMateriaHorasPracticas = "";
		nuevoMateriaHorasTeoria = "";
		nuevoDocRuta = "";
		nuevoDocNombre = "";
	}

	public void cancelDelete() {
		editarClave = "";
		editarDsc = "";
		editarIdTipo = 0;
		editarNoAut = "";
		editarHorasPractica = "";
		editarHorasTeoria = "";
		editarFechaAut = null;
		editarMateriaNombre = "";
		editarMateriaId = 0;
		editarMateriaHorasPracticas = "";
		editarMateriaHorasTeoria = "";
		editarDocRuta = "";
		editarDocNombre = "";
	}

	public void addEditarMateriaALista() {
		ProgramaEstudiosMateriasDTO dto = new ProgramaEstudiosMateriasDTO();
		MateriaRegistroDTO materiaDTO = null;
		try {
			materiaDTO = fachada.findMateriaById(editarMateriaId);
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dto.setMateria(materiaDTO);
		try {
			int horasPracticas = Integer.parseInt(editarHorasPractica);
			int horasTeoria = Integer.parseInt(editarHorasTeoria);
			dto.setHorasPractica(horasPracticas);
			dto.setHorasTeoria(horasTeoria);
		} catch (NumberFormatException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_FORMATO_NUMERO);
		}
		listMateriasEditar.add(dto);
	}

	public void addNuevoMateriaALista() {
		ProgramaEstudiosMateriasDTO dto = new ProgramaEstudiosMateriasDTO();
		MateriaRegistroDTO materiaDTO = null;
		try {
			materiaDTO = fachada.findMateriaById(nuevoMateriaId);
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		dto.setMateria(materiaDTO);
		try {
			int horasPracticas = Integer.parseInt(nuevoHorasPractica);
			int horasTeoria = Integer.parseInt(nuevoHorasTeoria);
			dto.setHorasPractica(horasPracticas);
			dto.setHorasTeoria(horasTeoria);
		} catch (NumberFormatException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_FORMATO_NUMERO);
		}
		listMateriasNuevo.add(dto);
	}

	public void addNuevoDocumento() {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setNombre(nuevoDocNombre);
		documentoDTO.setRuta(nuevoDocRuta);
		List<ProgramaEstudiosAutorizacionDTO> listTemp = listAutNuevo;
		ProgramaEstudiosAutorizacionDTO dtoTemp = new ProgramaEstudiosAutorizacionDTO();
		if (CollectionUtils.isEmpty(listTemp)) {
			listTemp = new ArrayList<ProgramaEstudiosAutorizacionDTO>();
		}
		dtoTemp.setDoc(documentoDTO);
		setListAutNuevo(listTemp);
	}

	public void addEditarDocumento() {
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setNombre(editarDocNombre);
		documentoDTO.setRuta(editarDocRuta);
		List<ProgramaEstudiosAutorizacionDTO> listTemp = listAutEditar;
		ProgramaEstudiosAutorizacionDTO dtoTemp = new ProgramaEstudiosAutorizacionDTO();
		if (CollectionUtils.isEmpty(listTemp)) {
			listTemp = new ArrayList<ProgramaEstudiosAutorizacionDTO>();
		}
		dtoTemp.setDoc(documentoDTO);
		setListAutEditar(listTemp);
	}

	public void handlerNuevoRegistroFileUpload(FileUploadEvent event) {
		try {
			String ruta = fachada.getRuta();
			File targetFolder = new File(ruta);
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			nuevoDocRuta = targetFolder.getAbsolutePath()
					+ targetFolder.separator + event.getFile().getFileName();
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
			e.printStackTrace();
		} catch (FachadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handlerEditarRegistroFileUpload(FileUploadEvent event) {
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

	// --------------- Getters & Setters -----------------------------//
	/**
	 * @return the fachada
	 */
	public ProgramaEstudiosFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(ProgramaEstudiosFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the listItems
	 */
	public List<ProgramaEstudiosDTO> getListItems() {
		initListItems();
		return listItems;
	}

	/**
	 * @param listItems
	 *            the listItems to set
	 */
	public void setListItems(List<ProgramaEstudiosDTO> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the listSelect1
	 */
	public List<SelectItem> getListSelect1() {
		initListSelect1();
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
		initListSelect2();
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
		initListSelect3();
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
	 * @return the selectedItem
	 */
	public ProgramaEstudiosDTO getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem
	 *            the selectedItem to set
	 */
	public void setSelectedItem(ProgramaEstudiosDTO selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return the nuevoItem
	 */
	public ProgramaEstudiosDTO getNuevoItem() {
		return nuevoItem;
	}

	/**
	 * @param nuevoItem
	 *            the nuevoItem to set
	 */
	public void setNuevoItem(ProgramaEstudiosDTO nuevoItem) {
		this.nuevoItem = nuevoItem;
	}

	/**
	 * @return the selectedClave
	 */
	public String getSelectedClave() {
		return selectedClave;
	}

	/**
	 * @param selectedClave
	 *            the selectedClave to set
	 */
	public void setSelectedClave(String selectedClave) {
		this.selectedClave = selectedClave;
	}

	/**
	 * @return the selectedDsc
	 */
	public String getSelectedDsc() {
		return selectedDsc;
	}

	/**
	 * @param selectedDsc
	 *            the selectedDsc to set
	 */
	public void setSelectedDsc(String selectedDsc) {
		this.selectedDsc = selectedDsc;
	}

	/**
	 * @return the selectedTipo
	 */
	public int getSelectedTipo() {
		return selectedTipo;
	}

	/**
	 * @param selectedTipo
	 *            the selectedTipo to set
	 */
	public void setSelectedTipo(int selectedTipo) {
		this.selectedTipo = selectedTipo;
	}

	/**
	 * @return the filteredList
	 */
	public List<ProgramaEstudiosDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<ProgramaEstudiosDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the listMateriasVer
	 */
	public List<ProgramaEstudiosMateriasDTO> getListMateriasVer() {
		try {
			listMateriasVer = selectedItem.getListMaterias();
		} catch (NullPointerException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listMateriasVer;
	}

	/**
	 * @param listMateriasVer
	 *            the listMateriasVer to set
	 */
	public void setListMateriasVer(
			List<ProgramaEstudiosMateriasDTO> listMateriasVer) {
		this.listMateriasVer = listMateriasVer;
	}

	/**
	 * @return the listMateriasNuevo
	 */
	public List<ProgramaEstudiosMateriasDTO> getListMateriasNuevo() {
		return listMateriasNuevo;
	}

	/**
	 * @param listMateriasNuevo
	 *            the listMateriasNuevo to set
	 */
	public void setListMateriasNuevo(
			List<ProgramaEstudiosMateriasDTO> listMateriasNuevo) {
		this.listMateriasNuevo = listMateriasNuevo;
	}

	/**
	 * @return the listMateriasEditar
	 */
	public List<ProgramaEstudiosMateriasDTO> getListMateriasEditar() {
		try {
			listMateriasEditar = selectedItem.getListMaterias();
		} catch (NullPointerException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listMateriasEditar;
	}

	/**
	 * @param listMateriasEditar
	 *            the listMateriasEditar to set
	 */
	public void setListMateriasEditar(
			List<ProgramaEstudiosMateriasDTO> listMateriasEditar) {
		this.listMateriasEditar = listMateriasEditar;
	}

	/**
	 * @return the materiaSelected
	 */
	public ProgramaEstudiosMateriasDTO getMateriaSelected() {
		return materiaSelected;
	}

	/**
	 * @param materiaSelected
	 *            the materiaSelected to set
	 */
	public void setMateriaSelected(ProgramaEstudiosMateriasDTO materiaSelected) {
		this.materiaSelected = materiaSelected;
	}

	/**
	 * @return the listAutVer
	 */
	public List<ProgramaEstudiosAutorizacionDTO> getListAutVer() {
		try {
			listAutVer = selectedItem.getListAutorizaciones();
		} catch (NullPointerException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listAutVer;
	}

	/**
	 * @param listAutVer
	 *            the listAutVer to set
	 */
	public void setListAutVer(List<ProgramaEstudiosAutorizacionDTO> listAutVer) {
		this.listAutVer = listAutVer;
	}

	/**
	 * @return the listAutNuevo
	 */
	public List<ProgramaEstudiosAutorizacionDTO> getListAutNuevo() {
		return listAutNuevo;
	}

	/**
	 * @param listAutNuevo
	 *            the listAutNuevo to set
	 */
	public void setListAutNuevo(
			List<ProgramaEstudiosAutorizacionDTO> listAutNuevo) {
		this.listAutNuevo = listAutNuevo;
	}

	/**
	 * @return the listAutEditar
	 */
	public List<ProgramaEstudiosAutorizacionDTO> getListAutEditar() {
		try {
			listAutEditar = selectedItem.getListAutorizaciones();
		} catch (NullPointerException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listAutEditar;
	}

	/**
	 * @param listAutEditar
	 *            the listAutEditar to set
	 */
	public void setListAutEditar(
			List<ProgramaEstudiosAutorizacionDTO> listAutEditar) {
		this.listAutEditar = listAutEditar;
	}

	/**
	 * @return the autSelected
	 */
	public ProgramaEstudiosAutorizacionDTO getAutSelected() {
		return autSelected;
	}

	/**
	 * @param autSelected
	 *            the autSelected to set
	 */
	public void setAutSelected(ProgramaEstudiosAutorizacionDTO autSelected) {
		this.autSelected = autSelected;
	}

	/**
	 * @return the nuevoClave
	 */
	public String getNuevoClave() {
		return nuevoClave;
	}

	/**
	 * @param nuevoClave
	 *            the nuevoClave to set
	 */
	public void setNuevoClave(String nuevoClave) {
		this.nuevoClave = nuevoClave;
	}

	/**
	 * @return the nuevoDsc
	 */
	public String getNuevoDsc() {
		return nuevoDsc;
	}

	/**
	 * @param nuevoDsc
	 *            the nuevoDsc to set
	 */
	public void setNuevoDsc(String nuevoDsc) {
		this.nuevoDsc = nuevoDsc;
	}

	/**
	 * @return the nuevoIdTipo
	 */
	public int getNuevoIdTipo() {
		return nuevoIdTipo;
	}

	/**
	 * @param nuevoIdTipo
	 *            the nuevoIdTipo to set
	 */
	public void setNuevoIdTipo(int nuevoIdTipo) {
		this.nuevoIdTipo = nuevoIdTipo;
	}

	/**
	 * @return the nuevoNoAut
	 */
	public String getNuevoNoAut() {
		return nuevoNoAut;
	}

	/**
	 * @param nuevoNoAut
	 *            the nuevoNoAut to set
	 */
	public void setNuevoNoAut(String nuevoNoAut) {
		this.nuevoNoAut = nuevoNoAut;
	}

	/**
	 * @return the nuevoHorasPractica
	 */
	public String getNuevoHorasPractica() {
		return nuevoHorasPractica;
	}

	/**
	 * @param nuevoHorasPractica
	 *            the nuevoHorasPractica to set
	 */
	public void setNuevoHorasPractica(String nuevoHorasPractica) {
		this.nuevoHorasPractica = nuevoHorasPractica;
	}

	/**
	 * @return the nuevoHorasTeoria
	 */
	public String getNuevoHorasTeoria() {
		return nuevoHorasTeoria;
	}

	/**
	 * @param nuevoHorasTeoria
	 *            the nuevoHorasTeoria to set
	 */
	public void setNuevoHorasTeoria(String nuevoHorasTeoria) {
		this.nuevoHorasTeoria = nuevoHorasTeoria;
	}

	/**
	 * @return the nuevoFechaAut
	 */
	public Date getNuevoFechaAut() {
		return nuevoFechaAut;
	}

	/**
	 * @param nuevoFechaAut
	 *            the nuevoFechaAut to set
	 */
	public void setNuevoFechaAut(Date nuevoFechaAut) {
		this.nuevoFechaAut = nuevoFechaAut;
	}

	/**
	 * @return the listTipos
	 */
	public List<SelectItem> getListTipos() {
		initSelectListTipos();
		return listTipos;
	}

	/**
	 * @param listTipos
	 *            the listTipos to set
	 */
	public void setListTipos(List<SelectItem> listTipos) {
		this.listTipos = listTipos;
	}

	/**
	 * @return the nuevoMateriaNombre
	 */
	public String getNuevoMateriaNombre() {
		return nuevoMateriaNombre;
	}

	/**
	 * @param nuevoMateriaNombre
	 *            the nuevoMateriaNombre to set
	 */
	public void setNuevoMateriaNombre(String nuevoMateriaNombre) {
		this.nuevoMateriaNombre = nuevoMateriaNombre;
	}

	/**
	 * @return the nuevoMateriaHorasPracticas
	 */
	public String getNuevoMateriaHorasPracticas() {
		return nuevoMateriaHorasPracticas;
	}

	/**
	 * @param nuevoMateriaHorasPracticas
	 *            the nuevoMateriaHorasPracticas to set
	 */
	public void setNuevoMateriaHorasPracticas(String nuevoMateriaHorasPracticas) {
		this.nuevoMateriaHorasPracticas = nuevoMateriaHorasPracticas;
	}

	/**
	 * @return the nuevoMateriaHorasTeoria
	 */
	public String getNuevoMateriaHorasTeoria() {
		return nuevoMateriaHorasTeoria;
	}

	/**
	 * @param nuevoMateriaHorasTeoria
	 *            the nuevoMateriaHorasTeoria to set
	 */
	public void setNuevoMateriaHorasTeoria(String nuevoMateriaHorasTeoria) {
		this.nuevoMateriaHorasTeoria = nuevoMateriaHorasTeoria;
	}

	/**
	 * @return the listMateriasDisponibles
	 */
	public List<SelectItem> getListMateriasDisponibles() {
		initListMateriasDisponibles();
		return listMateriasDisponibles;
	}

	/**
	 * @param listMateriasDisponibles
	 *            the listMateriasDisponibles to set
	 */
	public void setListMateriasDisponibles(
			List<SelectItem> listMateriasDisponibles) {
		this.listMateriasDisponibles = listMateriasDisponibles;
	}

	/**
	 * @return the nuevoMateriaId
	 */
	public int getNuevoMateriaId() {
		return nuevoMateriaId;
	}

	/**
	 * @param nuevoMateriaId
	 *            the nuevoMateriaId to set
	 */
	public void setNuevoMateriaId(int nuevoMateriaId) {
		this.nuevoMateriaId = nuevoMateriaId;
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
	 * @return the editarClave
	 */
	public String getEditarClave() {
		return editarClave;
	}

	/**
	 * @param editarClave
	 *            the editarClave to set
	 */
	public void setEditarClave(String editarClave) {
		this.editarClave = editarClave;
	}

	/**
	 * @return the editarDsc
	 */
	public String getEditarDsc() {
		return editarDsc;
	}

	/**
	 * @param editarDsc
	 *            the editarDsc to set
	 */
	public void setEditarDsc(String editarDsc) {
		this.editarDsc = editarDsc;
	}

	/**
	 * @return the editarIdTipo
	 */
	public int getEditarIdTipo() {
		return editarIdTipo;
	}

	/**
	 * @param editarIdTipo
	 *            the editarIdTipo to set
	 */
	public void setEditarIdTipo(int editarIdTipo) {
		this.editarIdTipo = editarIdTipo;
	}

	/**
	 * @return the editarNoAut
	 */
	public String getEditarNoAut() {
		return editarNoAut;
	}

	/**
	 * @param editarNoAut
	 *            the editarNoAut to set
	 */
	public void setEditarNoAut(String editarNoAut) {
		this.editarNoAut = editarNoAut;
	}

	/**
	 * @return the editarHorasPractica
	 */
	public String getEditarHorasPractica() {
		return editarHorasPractica;
	}

	/**
	 * @param editarHorasPractica
	 *            the editarHorasPractica to set
	 */
	public void setEditarHorasPractica(String editarHorasPractica) {
		this.editarHorasPractica = editarHorasPractica;
	}

	/**
	 * @return the editarHorasTeoria
	 */
	public String getEditarHorasTeoria() {
		return editarHorasTeoria;
	}

	/**
	 * @param editarHorasTeoria
	 *            the editarHorasTeoria to set
	 */
	public void setEditarHorasTeoria(String editarHorasTeoria) {
		this.editarHorasTeoria = editarHorasTeoria;
	}

	/**
	 * @return the editarFechaAut
	 */
	public Date getEditarFechaAut() {
		return editarFechaAut;
	}

	/**
	 * @param editarFechaAut
	 *            the editarFechaAut to set
	 */
	public void setEditarFechaAut(Date editarFechaAut) {
		this.editarFechaAut = editarFechaAut;
	}

	/**
	 * @return the editarMateriaNombre
	 */
	public String getEditarMateriaNombre() {
		return editarMateriaNombre;
	}

	/**
	 * @param editarMateriaNombre
	 *            the editarMateriaNombre to set
	 */
	public void setEditarMateriaNombre(String editarMateriaNombre) {
		this.editarMateriaNombre = editarMateriaNombre;
	}

	/**
	 * @return the editarMateriaId
	 */
	public int getEditarMateriaId() {
		return editarMateriaId;
	}

	/**
	 * @param editarMateriaId
	 *            the editarMateriaId to set
	 */
	public void setEditarMateriaId(int editarMateriaId) {
		this.editarMateriaId = editarMateriaId;
	}

	/**
	 * @return the editarMateriaHorasPracticas
	 */
	public String getEditarMateriaHorasPracticas() {
		return editarMateriaHorasPracticas;
	}

	/**
	 * @param editarMateriaHorasPracticas
	 *            the editarMateriaHorasPracticas to set
	 */
	public void setEditarMateriaHorasPracticas(
			String editarMateriaHorasPracticas) {
		this.editarMateriaHorasPracticas = editarMateriaHorasPracticas;
	}

	/**
	 * @return the editarMateriaHorasTeoria
	 */
	public String getEditarMateriaHorasTeoria() {
		return editarMateriaHorasTeoria;
	}

	/**
	 * @param editarMateriaHorasTeoria
	 *            the editarMateriaHorasTeoria to set
	 */
	public void setEditarMateriaHorasTeoria(String editarMateriaHorasTeoria) {
		this.editarMateriaHorasTeoria = editarMateriaHorasTeoria;
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

	@Override
	String getModulo() {
		return modulo;
	}

}
