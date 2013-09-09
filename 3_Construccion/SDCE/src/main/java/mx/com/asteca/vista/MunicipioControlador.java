package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MunicipioFachada;

import org.apache.commons.collections.CollectionUtils;

@ManagedBean(name = Constantes.BEAN_MUNICIPIOS)
@ViewScoped
public class MunicipioControlador extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{municipioFachadaImpl}")
	private MunicipioFachada fachadaMunicipio;

	private MunicipioDTO municipioSelected;
	private MunicipioDTO municipioNuevo;
	private PaisDTO paisSelected;
	private PaisDTO paisBuscarSelected;
	private int paisSelectedId;
	private int estadoSelectedId;
	private int municipioSelectedId;
	private EstadoDTO estadoBuscarSelected;
	private MunicipioDTO municipioBuscarSelected;
	private EstadoDTO estadoSelected;
	private String activoNuevo;
	private String activoEditar;
	private List<EstadoDTO> listaEstados;
	private List<PaisDTO> listaPaises;
	private List<MunicipioDTO> listaMunicipios;
	private List<SelectItem> listaSelectPaises;
	private List<SelectItem> listaSelectEstados;
	private List<SelectItem> listaSelectMunicipios;
    private transient DataModel<MunicipioDTO> model;
    private UIData municipioTable;

	public MunicipioControlador() {
		municipioNuevo = new MunicipioDTO();
		municipioSelected = new MunicipioDTO();
		estadoSelected = new EstadoDTO();
		paisSelected = new PaisDTO();
		paisBuscarSelected = new PaisDTO();
		estadoBuscarSelected = new EstadoDTO();
		municipioBuscarSelected = new MunicipioDTO();
	}
	
	private void initModel(){
		if(model == null){
			model = new ListDataModel<MunicipioDTO>(getListaMunicipios());
		}
	}

	private void initListaSelectPaises() {
		if (CollectionUtils.isEmpty(listaSelectPaises)) {
			listaSelectPaises = new ArrayList<SelectItem>();
			for (PaisDTO pais : getListaPaises()) {
				SelectItem select = new SelectItem(pais.getIdPais(),
						pais.getNombrePais());
				listaSelectPaises.add(select);
			}
		}
	}

	private void initListaSelectEstados() {
		if (CollectionUtils.isEmpty(listaSelectEstados)) {
			listaSelectEstados = new ArrayList<SelectItem>();
			for (EstadoDTO estado : getListaEstados()) {
				SelectItem select = new SelectItem(estado.getIdEstado(),
						estado.getNombre());
				listaSelectEstados.add(select);
			}
		}
	}

	private void initListaSelectMunicipios() {
		if (CollectionUtils.isEmpty(listaSelectMunicipios)) {
			listaSelectMunicipios = new ArrayList<SelectItem>();
			for (MunicipioDTO municipio : getListaMunicipios()) {
				SelectItem select = new SelectItem(municipio.getIdMunicipio(),
						municipio.getNombre());
				listaSelectMunicipios.add(select);
			}
		}
	}

	private void initListaMunicipios() {
		if (CollectionUtils.isEmpty(listaMunicipios)) {
			try {
				listaMunicipios = fachadaMunicipio.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaEstados() {
		if (CollectionUtils.isEmpty(listaEstados)) {
			try {
				listaEstados = fachadaMunicipio.getListaEstados();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaPaises() {
		if (CollectionUtils.isEmpty(listaPaises)) {
			try {
				listaPaises = fachadaMunicipio.getListaPaises();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public String saveMunicipio() {
		try {
			fachadaMunicipio.save(municipioNuevo);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}
		return "municipios.xhtml";
	}

	public String deleteEstado() {
		try {
			fachadaMunicipio.remove(municipioSelected);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}
		return "municipios.xhtml";
	}

	public String showMunicipio() {		
		System.out.println("Entrando a show municipio");
		Object o = getMunicipioTable().getRowData();
		System.out.println("RECIBIENDO:::"+o);

		
		
		return "municipios.xhtml";
	}

	public void showMunicipio2(ActionEvent e) {
		System.out.println("Entrando a show municipio22222");
		MunicipioDTO municipioSelectedTemp = model.getRowData();
		System.out.println("Setteando: "+municipioSelectedTemp);
		setMunicipioSelected(municipioSelectedTemp);
	}

	public String cancelarAccion() {
		return "municipios.xhtml";
	}


	public String updateEstado() {

		try {
			fachadaMunicipio.save(municipioSelected);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}
		return "municipios.xhtml";
	}

	/**
	 * @return the municipioSelected
	 */
	public MunicipioDTO getMunicipioSelected() {
		return municipioSelected;
	}

	/**
	 * @param municipioSelected
	 *            the municipioSelected to set
	 */
	public void setMunicipioSelected(MunicipioDTO municipioSelected) {
		this.municipioSelected = municipioSelected;
	}

	/**
	 * @return the municipioNuevo
	 */
	public MunicipioDTO getMunicipioNuevo() {
		return municipioNuevo;
	}

	/**
	 * @param municipioNuevo
	 *            the municipioNuevo to set
	 */
	public void setMunicipioNuevo(MunicipioDTO municipioNuevo) {
		this.municipioNuevo = municipioNuevo;
	}

	/**
	 * @return the paisSelected
	 */
	public PaisDTO getPaisSelected() {
		return paisSelected;
	}

	/**
	 * @param paisSelected
	 *            the paisSelected to set
	 */
	public void setPaisSelected(PaisDTO paisSelected) {
		this.paisSelected = paisSelected;
	}


	/**
	 * @return the listaEstados
	 */
	public List<EstadoDTO> getListaEstados() {
		initListaEstados();
		return listaEstados;
	}

	/**
	 * @param listaEstados
	 *            the listaEstados to set
	 */
	public void setListaEstados(List<EstadoDTO> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return the listaPaises
	 */
	public List<PaisDTO> getListaPaises() {
		initListaPaises();
		return listaPaises;
	}

	/**
	 * @param listaPaises
	 *            the listaPaises to set
	 */
	public void setListaPaises(List<PaisDTO> listaPaises) {
		this.listaPaises = listaPaises;
	}

	/**
	 * @return the listaMunicipios
	 */
	public List<MunicipioDTO> getListaMunicipios() {
		initListaMunicipios();
		return listaMunicipios;
	}

	/**
	 * @param listaMunicipios
	 *            the listaMunicipios to set
	 */
	public void setListaMunicipios(List<MunicipioDTO> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	/**
	 * @param fachadaMunicipio
	 *            the fachadaMunicipio to set
	 */
	public void setFachadaMunicipio(MunicipioFachada fachadaMunicipio) {
		this.fachadaMunicipio = fachadaMunicipio;
	}

	/**
	 * @return the estadoSelected
	 */
	public EstadoDTO getEstadoSelected() {
		return estadoSelected;
	}

	/**
	 * @param estadoSelected
	 *            the estadoSelected to set
	 */
	public void setEstadoSelected(EstadoDTO estadoSelected) {
		this.estadoSelected = estadoSelected;
	}

	/**
	 * @return the activoNuevo
	 */
	public String getActivoNuevo() {
		return activoNuevo;
	}

	/**
	 * @param activoNuevo
	 *            the activoNuevo to set
	 */
	public void setActivoNuevo(String activoNuevo) {
		this.activoNuevo = activoNuevo;
	}

	/**
	 * @return the activoEditar
	 */
	public String getActivoEditar() {
		return activoEditar;
	}

	/**
	 * @param activoEditar
	 *            the activoEditar to set
	 */
	public void setActivoEditar(String activoEditar) {
		this.activoEditar = activoEditar;
	}

	/**
	 * @return the paisBuscarSelected
	 */
	public PaisDTO getPaisBuscarSelected() {
		return paisBuscarSelected;
	}

	/**
	 * @param paisBuscarSelected
	 *            the paisBuscarSelected to set
	 */
	public void setPaisBuscarSelected(PaisDTO paisBuscarSelected) {
		this.paisBuscarSelected = paisBuscarSelected;
	}

	/**
	 * @return the estadoBuscarSelected
	 */
	public EstadoDTO getEstadoBuscarSelected() {
		return estadoBuscarSelected;
	}

	/**
	 * @param estadoBuscarSelected
	 *            the estadoBuscarSelected to set
	 */
	public void setEstadoBuscarSelected(EstadoDTO estadoBuscarSelected) {
		this.estadoBuscarSelected = estadoBuscarSelected;
	}

	/**
	 * @return the municipioBuscarSelected
	 */
	public MunicipioDTO getMunicipioBuscarSelected() {
		return municipioBuscarSelected;
	}

	/**
	 * @param municipioBuscarSelected
	 *            the municipioBuscarSelected to set
	 */
	public void setMunicipioBuscarSelected(MunicipioDTO municipioBuscarSelected) {
		this.municipioBuscarSelected = municipioBuscarSelected;
	}

	/**
	 * @return the listaSelectPaises
	 */
	public List<SelectItem> getListaSelectPaises() {
		initListaSelectPaises();
		return listaSelectPaises;
	}

	/**
	 * @param listaSelectPaises
	 *            the listaSelectPaises to set
	 */
	public void setListaSelectPaises(List<SelectItem> listaSelectPaises) {
		this.listaSelectPaises = listaSelectPaises;
	}

	/**
	 * @return the listSelectEstados
	 */
	public List<SelectItem> getListaSelectEstados() {
		initListaSelectEstados();
		return listaSelectEstados;
	}

	/**
	 * @param listSelectEstados
	 *            the listSelectEstados to set
	 */
	public void setListaSelectEstados(List<SelectItem> listSelectEstados) {
		this.listaSelectEstados = listSelectEstados;
	}

	/**
	 * @return the listSelectMunicipios
	 */
	public List<SelectItem> getListaSelectMunicipios() {
		initListaSelectMunicipios();
		return listaSelectMunicipios;
	}

	/**
	 * @param listSelectMunicipios
	 *            the listSelectMunicipios to set
	 */
	public void setListaSelectMunicipios(List<SelectItem> listSelectMunicipios) {
		this.listaSelectMunicipios = listSelectMunicipios;
	}

	/**
	 * @return the paisSelectedId
	 */
	public int getPaisSelectedId() {
		return paisSelectedId;
	}

	/**
	 * @param paisSelectedId
	 *            the paisSelectedId to set
	 */
	public void setPaisSelectedId(int paisSelectedId) {
		this.paisSelectedId = paisSelectedId;
	}

	/**
	 * @return the estadoSelectedId
	 */
	public int getEstadoSelectedId() {
		return estadoSelectedId;
	}

	/**
	 * @param estadoSelectedId
	 *            the estadoSelectedId to set
	 */
	public void setEstadoSelectedId(int estadoSelectedId) {
		this.estadoSelectedId = estadoSelectedId;
	}

	/**
	 * @return the municipioSelectedId
	 */
	public int getMunicipioSelectedId() {
		return municipioSelectedId;
	}

	/**
	 * @param municipioSelectedId
	 *            the municipioSelectedId to set
	 */
	public void setMunicipioSelectedId(int municipioSelectedId) {
		this.municipioSelectedId = municipioSelectedId;
	}

	public DataModel<MunicipioDTO> getModel() {
		initModel();
		return model;
	}

	public void setModel(DataModel<MunicipioDTO> model) {
		this.model = model;
	}

	public UIData getMunicipioTable() {
		return municipioTable;
	}

	public void setMunicipioTable(UIData municipioTable) {
		this.municipioTable = municipioTable;
	}
}
