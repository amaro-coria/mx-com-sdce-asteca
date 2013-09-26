/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.fachada.AdministrativoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.primefaces.event.SelectEvent;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@ManagedBean(name = Constantes.BEAN_ADMINISTRATIVOS)
@ViewScoped
public class AdministrativoControlador extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{administrativoFachadaImpl}")
	private transient AdministrativoFachada fachada;
	
	private List<AdministrativoDTO> listItems;
	private List<AdministrativoDTO> filteredList;

	private List<SelectItem> listSelectItems1;
	private List<SelectItem> listSelectItems2;

	private String selectedClave;
	private String selectedNombre;

	private AdministrativoDTO itemSelected;
	private AdministrativoDTO itemNuevo;
	
	private String nuevoAdminNoEmpleado;
	private String nuevoAdminNombre;
	private String nuevoAdminApaterno;
	private String nuevoAdminAmaterno;
	private Date nuevoAdminFechaNac;
	private String nuevoAdminLugarNac;
	private String nuevoAdminCURP;
	private String nuevoAdminRFC;
	private String nuevoAdminIFE;
	private String nuevoAdminPasaporte;
	private String nuevoAdminEmail;
	private String nuevoAdminCalle;
	private String nuevoAdminNoExt;
	private String nuevoAdminNoInt;
	
	private String editarAdminNoEmpleado;
	private String editarAdminNombre;
	private String editarAdminApaterno;
	private String editarAdminAmaterno;
	private Date editarAdminFechaNac;
	private String editarAdminLugarNac;
	private String editarAdminCURP;
	private String editarAdminRFC;
	private String editarAdminIFE;
	private String editarAdminPasaporte;
	private String editarAdminEmail;
	private String editarAdminCalle;
	private String editarAdminNoExt;
	private String editarAdminNoInt;
	
	private List<String> listCPString;
	private List<Integer> listaCPs;
	
	private String nuevoAdminCPAutoComplete;
	private String nuevoAdminDelegacion;
	private String nuevoAdminEntidadFederativa;
	private String nuevoAdminPais;
	private String nuevoAdminIdAsentamientoMunicipioEstado;
	
	private String editarAdminCPAutoComplete;
	private String editarAdminDelegacion;
	private String editarAdminEntidadFederativa;
	private String editarAdminPais;
	private String editarAdminIdAsentamientoMunicipioEstado;
	
	private int nuevoAdminCpIdAsentamiento;
	private int editarAdminCpIdAsentamiento;
	
	private List<SelectItem> nuevoAdminSelectListColonias;
	private List<SelectItem> editarAdminSelectListColonias;
	
	public AdministrativoControlador(){
		itemNuevo = new AdministrativoDTO();
		itemSelected = new AdministrativoDTO();
	}
	
	public void testButtonActionListener(ActionEvent event) {
        System.out.println("testButtonActionListener invoked");
    }
	
	private void initListaItems() {
		if (CollectionUtils.isEmpty(listItems)) {
			try {
				listItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaSelectItems1() {
		if (CollectionUtils.isEmpty(listSelectItems1)) {
			listSelectItems1 = new ArrayList<SelectItem>();
			for (AdministrativoDTO dto : getListItems()) {
				SelectItem item = new SelectItem(dto.getNoEmpleado(),
						dto.getNoEmpleado());
				listSelectItems1.add(item);
			}
		}
	}

	private void initListaSelectItems2() {
		if (CollectionUtils.isEmpty(listSelectItems2)) {
			listSelectItems2 = new ArrayList<SelectItem>();
			for (AdministrativoDTO dto : getListItems()) {
				SelectItem item = new SelectItem(dto.getNombreCompleto(), dto.getNombreCompleto());
				listSelectItems2.add(item);
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
	
	public void verItem(){
		System.out.println("SelectedElement:"+itemSelected);
	}
	
	/**
	 * Inicializa el componente para que el panel de Ver pueda ser accedido
	 */
	public void showVer() {
		System.out.println("SelectedElement:"+itemSelected);
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
	
	public void handleSelectCambiaCpEditar(SelectEvent e) {
		String selection = e.getObject().toString();
		editarAdminCPAutoComplete = selection;
		handlerCambiaCpEditar();
	}
	
	public void handleSelectCambiaCp(SelectEvent e) {
		String selection = e.getObject().toString();
		nuevoAdminCPAutoComplete = selection;
		handlerCambiaCp();
	}
	
	public void handlerCambiaCpEditar() {
		try {
			editarAdminCpIdAsentamiento = Integer
					.parseInt(editarAdminCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (editarAdminCpIdAsentamiento != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(editarAdminCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				editarAdminSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				editarAdminPais = estado.getPais();
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				editarAdminDelegacion = municipio.getNombre();
				editarAdminEntidadFederativa = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					editarAdminSelectListColonias.add(item);
				}
				// }

			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	public void handlerCambiaCp() {
		try {
			nuevoAdminCpIdAsentamiento = Integer
					.parseInt(nuevoAdminCPAutoComplete);
		} catch (NumberFormatException nex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		if (nuevoAdminCpIdAsentamiento != 0) {
			// nuevoAlumnoSelectListColonias
			try {
				List<AsentamientoDTO> listaAsentamientos = fachada
						.findAsentamientosByCp(nuevoAdminCpIdAsentamiento);
				// if(listaAsentamientos.size() > 1){
				nuevoAdminSelectListColonias = new ArrayList<SelectItem>();
				AsentamientoDTO primerResultado = listaAsentamientos.get(0);
				int estadoID = primerResultado.getIdEstado();
				int municipioId = primerResultado.getIdMunicipio();
				EstadoDTO estado = fachada.findEstado(estadoID);
				nuevoAdminPais = estado.getPais();
				MunicipioDTO municipio = fachada.findMunicipio(municipioId,
						estadoID);
				nuevoAdminDelegacion = municipio.getNombre();
				nuevoAdminEntidadFederativa = estado.getNombre();
				for (AsentamientoDTO dto : listaAsentamientos) {
					String llaveTemp = dto.getIdAsentamiento() + "|"
							+ dto.getIdMunicipio() + "|" + dto.getIdEstado();
					SelectItem item = new SelectItem(llaveTemp, dto.getNombre());
					nuevoAdminSelectListColonias.add(item);
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
		listItems = null;
		initListaItems();
	}
	
	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			boolean buscarNombre = selectedNombre == null || selectedNombre.isEmpty();
			boolean buscarClave = selectedClave == null || selectedClave.isEmpty();
			buscarNombre = !buscarNombre;
			buscarClave = !buscarClave;
			if(buscarNombre && buscarClave){
				List<AdministrativoDTO> results = fachada.findByNombreClave(selectedNombre, selectedClave);
				listItems = results;
			}else if(buscarNombre){
				List<AdministrativoDTO> results = fachada.findByNombre(selectedNombre);
				listItems = results;
			}else if(buscarClave){
				List<AdministrativoDTO> results = fachada.findByClave(selectedClave);
				listItems = results;
			}else{
				listItems = null;
				initListaItems();
				return;
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}

	/**
	 * @param fachadaAmin
	 *            the fachadaAmin to set
	 */
	public void setFachada(AdministrativoFachada fachadaAmin) {
		this.fachada = fachadaAmin;
	}

	/**
	 * @return the listItems
	 */
	public List<AdministrativoDTO> getListItems() {
		initListaItems();
		return listItems;
	}

	/**
	 * @param listItems
	 *            the listItems to set
	 */
	public void setListItems(List<AdministrativoDTO> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the listSelectItems1
	 */
	public List<SelectItem> getListSelectItems1() {
		initListaSelectItems1();
		return listSelectItems1;
	}

	/**
	 * @param listSelectItems1
	 *            the listSelectItems1 to set
	 */
	public void setListSelectItems1(List<SelectItem> listSelectItems1) {
		this.listSelectItems1 = listSelectItems1;
	}

	/**
	 * @return the listSelectItems2
	 */
	public List<SelectItem> getListSelectItems2() {
		initListaSelectItems2();
		return listSelectItems2;
	}

	/**
	 * @param listSelectItems2
	 *            the listSelectItems2 to set
	 */
	public void setListSelectItems2(List<SelectItem> listSelectItems2) {
		this.listSelectItems2 = listSelectItems2;
	}

	/**
	 * @return the itemSelected
	 */
	public AdministrativoDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected
	 *            the itemSelected to set
	 */
	public void setItemSelected(AdministrativoDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the itemNuevo
	 */
	public AdministrativoDTO getItemNuevo() {
		return itemNuevo;
	}

	/**
	 * @param itemNuevo
	 *            the itemNuevo to set
	 */
	public void setItemNuevo(AdministrativoDTO itemNuevo) {
		this.itemNuevo = itemNuevo;
	}

	/**
	 * @return the selectedClave
	 */
	public String getSelectedClave() {
		return selectedClave;
	}

	/**
	 * @param selectedClave the selectedClave to set
	 */
	public void setSelectedClave(String selectedClave) {
		this.selectedClave = selectedClave;
	}

	/**
	 * @return the selectedNombre
	 */
	public String getSelectedNombre() {
		return selectedNombre;
	}

	/**
	 * @param selectedNombre the selectedNombre to set
	 */
	public void setSelectedNombre(String selectedNombre) {
		this.selectedNombre = selectedNombre;
	}

	/**
	 * @return the filteredList
	 */
	public List<AdministrativoDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<AdministrativoDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the nuevoAdminNoEmpleado
	 */
	public String getNuevoAdminNoEmpleado() {
		return nuevoAdminNoEmpleado;
	}

	/**
	 * @param nuevoAdminNoEmpleado the nuevoAdminNoEmpleado to set
	 */
	public void setNuevoAdminNoEmpleado(String nuevoAdminNoEmpleado) {
		this.nuevoAdminNoEmpleado = nuevoAdminNoEmpleado;
	}

	/**
	 * @return the nuevoAdminNombre
	 */
	public String getNuevoAdminNombre() {
		return nuevoAdminNombre;
	}

	/**
	 * @param nuevoAdminNombre the nuevoAdminNombre to set
	 */
	public void setNuevoAdminNombre(String nuevoAdminNombre) {
		this.nuevoAdminNombre = nuevoAdminNombre;
	}

	/**
	 * @return the nuevoAdminApaterno
	 */
	public String getNuevoAdminApaterno() {
		return nuevoAdminApaterno;
	}

	/**
	 * @param nuevoAdminApaterno the nuevoAdminApaterno to set
	 */
	public void setNuevoAdminApaterno(String nuevoAdminApaterno) {
		this.nuevoAdminApaterno = nuevoAdminApaterno;
	}

	/**
	 * @return the nuevoAdminAmaterno
	 */
	public String getNuevoAdminAmaterno() {
		return nuevoAdminAmaterno;
	}

	/**
	 * @param nuevoAdminAmaterno the nuevoAdminAmaterno to set
	 */
	public void setNuevoAdminAmaterno(String nuevoAdminAmaterno) {
		this.nuevoAdminAmaterno = nuevoAdminAmaterno;
	}

	/**
	 * @return the nuevoAdminFechaNac
	 */
	public Date getNuevoAdminFechaNac() {
		return nuevoAdminFechaNac;
	}

	/**
	 * @param nuevoAdminFechaNac the nuevoAdminFechaNac to set
	 */
	public void setNuevoAdminFechaNac(Date nuevoAdminFechaNac) {
		this.nuevoAdminFechaNac = nuevoAdminFechaNac;
	}

	/**
	 * @return the nuevoAdminLugarNac
	 */
	public String getNuevoAdminLugarNac() {
		return nuevoAdminLugarNac;
	}

	/**
	 * @param nuevoAdminLugarNac the nuevoAdminLugarNac to set
	 */
	public void setNuevoAdminLugarNac(String nuevoAdminLugarNac) {
		this.nuevoAdminLugarNac = nuevoAdminLugarNac;
	}

	/**
	 * @return the nuevoAdminCURP
	 */
	public String getNuevoAdminCURP() {
		return nuevoAdminCURP;
	}

	/**
	 * @param nuevoAdminCURP the nuevoAdminCURP to set
	 */
	public void setNuevoAdminCURP(String nuevoAdminCURP) {
		this.nuevoAdminCURP = nuevoAdminCURP;
	}

	/**
	 * @return the nuevoAdminRFC
	 */
	public String getNuevoAdminRFC() {
		return nuevoAdminRFC;
	}

	/**
	 * @param nuevoAdminRFC the nuevoAdminRFC to set
	 */
	public void setNuevoAdminRFC(String nuevoAdminRFC) {
		this.nuevoAdminRFC = nuevoAdminRFC;
	}

	/**
	 * @return the nuevoAdminIFE
	 */
	public String getNuevoAdminIFE() {
		return nuevoAdminIFE;
	}

	/**
	 * @param nuevoAdminIFE the nuevoAdminIFE to set
	 */
	public void setNuevoAdminIFE(String nuevoAdminIFE) {
		this.nuevoAdminIFE = nuevoAdminIFE;
	}

	/**
	 * @return the nuevoAdminPasaporte
	 */
	public String getNuevoAdminPasaporte() {
		return nuevoAdminPasaporte;
	}

	/**
	 * @param nuevoAdminPasaporte the nuevoAdminPasaporte to set
	 */
	public void setNuevoAdminPasaporte(String nuevoAdminPasaporte) {
		this.nuevoAdminPasaporte = nuevoAdminPasaporte;
	}

	/**
	 * @return the nuevoAdminEmail
	 */
	public String getNuevoAdminEmail() {
		return nuevoAdminEmail;
	}

	/**
	 * @param nuevoAdminEmail the nuevoAdminEmail to set
	 */
	public void setNuevoAdminEmail(String nuevoAdminEmail) {
		this.nuevoAdminEmail = nuevoAdminEmail;
	}

	/**
	 * @return the nuevoAdminCalle
	 */
	public String getNuevoAdminCalle() {
		return nuevoAdminCalle;
	}

	/**
	 * @param nuevoAdminCalle the nuevoAdminCalle to set
	 */
	public void setNuevoAdminCalle(String nuevoAdminCalle) {
		this.nuevoAdminCalle = nuevoAdminCalle;
	}

	/**
	 * @return the nuevoAdminNoExt
	 */
	public String getNuevoAdminNoExt() {
		return nuevoAdminNoExt;
	}

	/**
	 * @param nuevoAdminNoExt the nuevoAdminNoExt to set
	 */
	public void setNuevoAdminNoExt(String nuevoAdminNoExt) {
		this.nuevoAdminNoExt = nuevoAdminNoExt;
	}

	/**
	 * @return the nuevoAdminNoInt
	 */
	public String getNuevoAdminNoInt() {
		return nuevoAdminNoInt;
	}

	/**
	 * @param nuevoAdminNoInt the nuevoAdminNoInt to set
	 */
	public void setNuevoAdminNoInt(String nuevoAdminNoInt) {
		this.nuevoAdminNoInt = nuevoAdminNoInt;
	}

	/**
	 * @return the listCPString
	 */
	public List<String> getListCPString() {
		initListaCPString();
		return listCPString;
	}

	/**
	 * @param listCPString the listCPString to set
	 */
	public void setListCPString(List<String> listCPString) {
		this.listCPString = listCPString;
	}

	/**
	 * @return the listaCPs
	 */
	public List<Integer> getListaCPs() {
		return listaCPs;
	}

	/**
	 * @param listaCPs the listaCPs to set
	 */
	public void setListaCPs(List<Integer> listaCPs) {
		this.listaCPs = listaCPs;
	}

	/**
	 * @return the nuevoAdminCPAutoComplete
	 */
	public String getNuevoAdminCPAutoComplete() {
		return nuevoAdminCPAutoComplete;
	}

	/**
	 * @param nuevoAdminCPAutoComplete the nuevoAdminCPAutoComplete to set
	 */
	public void setNuevoAdminCPAutoComplete(String nuevoAdminCPAutoComplete) {
		this.nuevoAdminCPAutoComplete = nuevoAdminCPAutoComplete;
	}

	/**
	 * @return the editarAdminNoEmpleado
	 */
	public String getEditarAdminNoEmpleado() {
		return editarAdminNoEmpleado;
	}

	/**
	 * @param editarAdminNoEmpleado the editarAdminNoEmpleado to set
	 */
	public void setEditarAdminNoEmpleado(String editarAdminNoEmpleado) {
		this.editarAdminNoEmpleado = editarAdminNoEmpleado;
	}

	/**
	 * @return the editarAdminNombre
	 */
	public String getEditarAdminNombre() {
		return editarAdminNombre;
	}

	/**
	 * @param editarAdminNombre the editarAdminNombre to set
	 */
	public void setEditarAdminNombre(String editarAdminNombre) {
		this.editarAdminNombre = editarAdminNombre;
	}

	/**
	 * @return the editarAdminApaterno
	 */
	public String getEditarAdminApaterno() {
		return editarAdminApaterno;
	}

	/**
	 * @param editarAdminApaterno the editarAdminApaterno to set
	 */
	public void setEditarAdminApaterno(String editarAdminApaterno) {
		this.editarAdminApaterno = editarAdminApaterno;
	}

	/**
	 * @return the editarAdminAmaterno
	 */
	public String getEditarAdminAmaterno() {
		return editarAdminAmaterno;
	}

	/**
	 * @param editarAdminAmaterno the editarAdminAmaterno to set
	 */
	public void setEditarAdminAmaterno(String editarAdminAmaterno) {
		this.editarAdminAmaterno = editarAdminAmaterno;
	}

	/**
	 * @return the editarAdminFechaNac
	 */
	public Date getEditarAdminFechaNac() {
		return editarAdminFechaNac;
	}

	/**
	 * @param editarAdminFechaNac the editarAdminFechaNac to set
	 */
	public void setEditarAdminFechaNac(Date editarAdminFechaNac) {
		this.editarAdminFechaNac = editarAdminFechaNac;
	}

	/**
	 * @return the editarAdminLugarNac
	 */
	public String getEditarAdminLugarNac() {
		return editarAdminLugarNac;
	}

	/**
	 * @param editarAdminLugarNac the editarAdminLugarNac to set
	 */
	public void setEditarAdminLugarNac(String editarAdminLugarNac) {
		this.editarAdminLugarNac = editarAdminLugarNac;
	}

	/**
	 * @return the editarAdminCURP
	 */
	public String getEditarAdminCURP() {
		return editarAdminCURP;
	}

	/**
	 * @param editarAdminCURP the editarAdminCURP to set
	 */
	public void setEditarAdminCURP(String editarAdminCURP) {
		this.editarAdminCURP = editarAdminCURP;
	}

	/**
	 * @return the editarAdminRFC
	 */
	public String getEditarAdminRFC() {
		return editarAdminRFC;
	}

	/**
	 * @param editarAdminRFC the editarAdminRFC to set
	 */
	public void setEditarAdminRFC(String editarAdminRFC) {
		this.editarAdminRFC = editarAdminRFC;
	}

	/**
	 * @return the editarAdminIFE
	 */
	public String getEditarAdminIFE() {
		return editarAdminIFE;
	}

	/**
	 * @param editarAdminIFE the editarAdminIFE to set
	 */
	public void setEditarAdminIFE(String editarAdminIFE) {
		this.editarAdminIFE = editarAdminIFE;
	}

	/**
	 * @return the editarAdminPasaporte
	 */
	public String getEditarAdminPasaporte() {
		return editarAdminPasaporte;
	}

	/**
	 * @param editarAdminPasaporte the editarAdminPasaporte to set
	 */
	public void setEditarAdminPasaporte(String editarAdminPasaporte) {
		this.editarAdminPasaporte = editarAdminPasaporte;
	}

	/**
	 * @return the editarAdminEmail
	 */
	public String getEditarAdminEmail() {
		return editarAdminEmail;
	}

	/**
	 * @param editarAdminEmail the editarAdminEmail to set
	 */
	public void setEditarAdminEmail(String editarAdminEmail) {
		this.editarAdminEmail = editarAdminEmail;
	}

	/**
	 * @return the editarAdminCalle
	 */
	public String getEditarAdminCalle() {
		return editarAdminCalle;
	}

	/**
	 * @param editarAdminCalle the editarAdminCalle to set
	 */
	public void setEditarAdminCalle(String editarAdminCalle) {
		this.editarAdminCalle = editarAdminCalle;
	}

	/**
	 * @return the editarAdminNoExt
	 */
	public String getEditarAdminNoExt() {
		return editarAdminNoExt;
	}

	/**
	 * @param editarAdminNoExt the editarAdminNoExt to set
	 */
	public void setEditarAdminNoExt(String editarAdminNoExt) {
		this.editarAdminNoExt = editarAdminNoExt;
	}

	/**
	 * @return the editarAdminNoInt
	 */
	public String getEditarAdminNoInt() {
		return editarAdminNoInt;
	}

	/**
	 * @param editarAdminNoInt the editarAdminNoInt to set
	 */
	public void setEditarAdminNoInt(String editarAdminNoInt) {
		this.editarAdminNoInt = editarAdminNoInt;
	}

	/**
	 * @return the nuevoAdminDelegacion
	 */
	public String getNuevoAdminDelegacion() {
		return nuevoAdminDelegacion;
	}

	/**
	 * @param nuevoAdminDelegacion the nuevoAdminDelegacion to set
	 */
	public void setNuevoAdminDelegacion(String nuevoAdminDelegacion) {
		this.nuevoAdminDelegacion = nuevoAdminDelegacion;
	}

	/**
	 * @return the nuevoAdminEntidadFederativa
	 */
	public String getNuevoAdminEntidadFederativa() {
		return nuevoAdminEntidadFederativa;
	}

	/**
	 * @param nuevoAdminEntidadFederativa the nuevoAdminEntidadFederativa to set
	 */
	public void setNuevoAdminEntidadFederativa(String nuevoAdminEntidadFederativa) {
		this.nuevoAdminEntidadFederativa = nuevoAdminEntidadFederativa;
	}

	/**
	 * @return the nuevoAdminPais
	 */
	public String getNuevoAdminPais() {
		return nuevoAdminPais;
	}

	/**
	 * @param nuevoAdminPais the nuevoAdminPais to set
	 */
	public void setNuevoAdminPais(String nuevoAdminPais) {
		this.nuevoAdminPais = nuevoAdminPais;
	}

	/**
	 * @return the nuevoAdminIdAsentamientoMunicipioEstado
	 */
	public String getNuevoAdminIdAsentamientoMunicipioEstado() {
		return nuevoAdminIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param nuevoAdminIdAsentamientoMunicipioEstado the nuevoAdminIdAsentamientoMunicipioEstado to set
	 */
	public void setNuevoAdminIdAsentamientoMunicipioEstado(
			String nuevoAdminIdAsentamientoMunicipioEstado) {
		this.nuevoAdminIdAsentamientoMunicipioEstado = nuevoAdminIdAsentamientoMunicipioEstado;
	}

	/**
	 * @return the editarAdminCPAutoComplete
	 */
	public String getEditarAdminCPAutoComplete() {
		return editarAdminCPAutoComplete;
	}

	/**
	 * @param editarAdminCPAutoComplete the editarAdminCPAutoComplete to set
	 */
	public void setEditarAdminCPAutoComplete(String editarAdminCPAutoComplete) {
		this.editarAdminCPAutoComplete = editarAdminCPAutoComplete;
	}

	/**
	 * @return the editarAdminDelegacion
	 */
	public String getEditarAdminDelegacion() {
		return editarAdminDelegacion;
	}

	/**
	 * @param editarAdminDelegacion the editarAdminDelegacion to set
	 */
	public void setEditarAdminDelegacion(String editarAdminDelegacion) {
		this.editarAdminDelegacion = editarAdminDelegacion;
	}

	/**
	 * @return the editarAdminEntidadFederativa
	 */
	public String getEditarAdminEntidadFederativa() {
		return editarAdminEntidadFederativa;
	}

	/**
	 * @param editarAdminEntidadFederativa the editarAdminEntidadFederativa to set
	 */
	public void setEditarAdminEntidadFederativa(String editarAdminEntidadFederativa) {
		this.editarAdminEntidadFederativa = editarAdminEntidadFederativa;
	}

	/**
	 * @return the editarAdminPais
	 */
	public String getEditarAdminPais() {
		return editarAdminPais;
	}

	/**
	 * @param editarAdminPais the editarAdminPais to set
	 */
	public void setEditarAdminPais(String editarAdminPais) {
		this.editarAdminPais = editarAdminPais;
	}

	/**
	 * @return the editarAdminIdAsentamientoMunicipioEstado
	 */
	public String getEditarAdminIdAsentamientoMunicipioEstado() {
		return editarAdminIdAsentamientoMunicipioEstado;
	}

	/**
	 * @param editarAdminIdAsentamientoMunicipioEstado the editarAdminIdAsentamientoMunicipioEstado to set
	 */
	public void setEditarAdminIdAsentamientoMunicipioEstado(
			String editarAdminIdAsentamientoMunicipioEstado) {
		this.editarAdminIdAsentamientoMunicipioEstado = editarAdminIdAsentamientoMunicipioEstado;
	}

	/**
	 * @return the nuevoAdminCpIdAsentamiento
	 */
	public int getNuevoAdminCpIdAsentamiento() {
		return nuevoAdminCpIdAsentamiento;
	}

	/**
	 * @param nuevoAdminCpIdAsentamiento the nuevoAdminCpIdAsentamiento to set
	 */
	public void setNuevoAdminCpIdAsentamiento(int nuevoAdminCpIdAsentamiento) {
		this.nuevoAdminCpIdAsentamiento = nuevoAdminCpIdAsentamiento;
	}

	/**
	 * @return the editarAdminCpIdAsentamiento
	 */
	public int getEditarAdminCpIdAsentamiento() {
		return editarAdminCpIdAsentamiento;
	}

	/**
	 * @param editarAdminCpIdAsentamiento the editarAdminCpIdAsentamiento to set
	 */
	public void setEditarAdminCpIdAsentamiento(int editarAdminCpIdAsentamiento) {
		this.editarAdminCpIdAsentamiento = editarAdminCpIdAsentamiento;
	}

	/**
	 * @return the nuevoAdminSelectListColonias
	 */
	public List<SelectItem> getNuevoAdminSelectListColonias() {
		return nuevoAdminSelectListColonias;
	}

	/**
	 * @param nuevoAdminSelectListColonias the nuevoAdminSelectListColonias to set
	 */
	public void setNuevoAdminSelectListColonias(
			List<SelectItem> nuevoAdminSelectListColonias) {
		this.nuevoAdminSelectListColonias = nuevoAdminSelectListColonias;
	}

	/**
	 * @return the editarAdminSelectListColonias
	 */
	public List<SelectItem> getEditarAdminSelectListColonias() {
		return editarAdminSelectListColonias;
	}

	/**
	 * @param editarAdminSelectListColonias the editarAdminSelectListColonias to set
	 */
	public void setEditarAdminSelectListColonias(
			List<SelectItem> editarAdminSelectListColonias) {
		this.editarAdminSelectListColonias = editarAdminSelectListColonias;
	}
}
