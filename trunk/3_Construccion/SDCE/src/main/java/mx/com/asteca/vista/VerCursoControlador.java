/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoAsistenciaDTO;
import mx.com.asteca.comun.dto.AlumnoAsistenciaDiaDTO;
import mx.com.asteca.comun.dto.AlumnoCalifMateriaSingle;
import mx.com.asteca.comun.dto.AlumnoCalificacionMateriaDTO;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.CursoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.util.FechaUtil;

import org.primefaces.event.SelectEvent;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@ManagedBean(name = Constantes.BEAN_VER_CURSOS)
@ViewScoped
public class VerCursoControlador extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String modulo = Constantes.MODULO_VER_CURSO;

	@ManagedProperty("#{cursoFachadaImpl}")
	private transient CursoFachada fachada;

	private CursoDTO itemSelected;
	private List<CursoDTO> listItems;
	private List<SelectItem> listSelectItem1;
	private String referenciaSelected;
	private boolean displayTab;
	private List<MateriaDTO> listMaterias;
	private List<AlumnoDTO> listaAlumnosAgregados;
	private List<AlumnoAsistenciaDTO> listaAlumnosAsistencia;
	private List<String> encabezadoDias;
	private List<String> encabezadoMaterias;
	private Date minDate;
	private Date maxDate;
	private Date registrarAsistenciaDate;
	private List<AlumnoAsistenciaDiaDTO> listaAsistenciaTemp;
	private List<AlumnoAsistenciaDiaDTO> listaAsistenciaSelecteds;
	private boolean showTableAsistencias;
	private List<AlumnoCalificacionMateriaDTO> listaAlumnosCalificaciones;
	private List<MateriaDTO> listaMateriasPorCurso;
	private List<SelectItem> listaSelectMateriasCurso;
	private int idMateriaSelected;
	private List<AlumnoCalifMateriaSingle> listaCalifMateriaSingle;

	public VerCursoControlador() {
		itemSelected = new CursoDTO();
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
	
	private void initListSelectItem1() {
		if (CollectionUtils.isEmpty(listSelectItem1)) {
			listSelectItem1 = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListItems())) {
				for (CursoDTO dto : getListItems()) {
					SelectItem item = new SelectItem(dto.getReferencia(),
							dto.getReferencia());
					listSelectItem1.add(item);
				}
			}
		}
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
	
	public void initSelectMaterias(){
		listaSelectMateriasCurso = new ArrayList<SelectItem>();
		if(!CollectionUtils.isEmpty(listaMateriasPorCurso)){
			for(MateriaDTO dto : listaMateriasPorCurso){
				SelectItem item = new SelectItem(dto.getIdMateria(), dto.getNombre());
				listaSelectMateriasCurso.add(item);
			}
		}
	}

	public void handleMateriaChange(){
		if(idMateriaSelected != 0){
			listaCalifMateriaSingle = new ArrayList<AlumnoCalifMateriaSingle>();
			if(!CollectionUtils.isEmpty(listaAlumnosAgregados)){
				for(AlumnoDTO dto : listaAlumnosAgregados){
					AlumnoCalifMateriaSingle ca = new AlumnoCalifMateriaSingle();
					ca.setIdAlumno(dto.getIdAlumno());
					ca.setIdCurso(itemSelected.getIdCurso());
					ca.setIdMateria(idMateriaSelected);
					ca.setMatricula(dto.getMatricula());
					ca.setNombre(dto.getNombre());
					try {
						String calificacion = fachada.getCalificacion(dto.getIdAlumno(), itemSelected.getIdCurso(), idMateriaSelected);
						ca.setCalificacion(Double.parseDouble(calificacion));
					} catch (FachadaException e) {
						super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
					}
					listaCalifMateriaSingle.add(ca);
				}
			}
		}
	}
	
	public void cancelSaveCalificaciones(){
		handleMateriaChange();
	}
	
	public void saveCalificaciones(){
		if(!CollectionUtils.isEmpty(listaCalifMateriaSingle)){
			for(AlumnoCalifMateriaSingle dto : listaCalifMateriaSingle){
				try {
					fachada.saveCalificacion(dto.getIdAlumno(), itemSelected.getIdCurso(), dto.getIdMateria(), dto.getCalificacion());
					super.addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVA_CALIFICACION_REGISTRADA);
					super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				} catch (FachadaException e) {
					super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
					super.addBitacora(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
				}
			}
			buildAlumnosMaterias(listaAlumnosAgregados, itemSelected);
		}
	}
	
	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		referenciaSelected = "";
		itemSelected = new CursoDTO();
		displayTab = false;
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			if (referenciaSelected != null && !referenciaSelected.isEmpty()) {
				CursoDTO dto = fachada.findByReferencia(referenciaSelected);
				if (dto != null) {
					itemSelected = dto;
					listMaterias = fachada.findMateriasPorCurso(dto
							.getIdCurso());
					listaMateriasPorCurso = listMaterias;
					listaAlumnosAgregados = fachada.findAlumnosPorCurso(dto
							.getIdCurso());
					buildAlumnosAsistencia(listaAlumnosAgregados, dto);
					buildAlumnosMaterias(listaAlumnosAgregados, dto);
					displayTab = true;
				}
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}

	private void buildAlumnosMaterias(List<AlumnoDTO> listaAlumos, CursoDTO dtoCurso){
		try {
			List<MateriaDTO> listaMaterias = fachada.findMateriasPorCurso(dtoCurso.getIdCurso());
			if(!CollectionUtils.isEmpty(listaMaterias)){
				encabezadoMaterias = new ArrayList<String>();
				if(!CollectionUtils.isEmpty(listaMaterias)){
					for(MateriaDTO dto : listaMaterias){
						encabezadoMaterias.add(dto.getNombre());
					}
				}
				listaAlumnosCalificaciones = new ArrayList<AlumnoCalificacionMateriaDTO>();
				String calificacion = "";
				if(!CollectionUtils.isEmpty(listaAlumos)){
					for(AlumnoDTO dto2 : listaAlumos){
						List<String> listaCalificaciones = new ArrayList<String>();
						int idMateria = 0;
						if(!CollectionUtils.isEmpty(listaMaterias)){
							for(MateriaDTO dto : listaMaterias){
								calificacion = fachada.getCalificacion(dto2.getIdAlumno(), dtoCurso.getIdCurso(), dto.getIdMateria());
								listaCalificaciones.add(calificacion);
							}
						}
						AlumnoCalificacionMateriaDTO califAlumno = new AlumnoCalificacionMateriaDTO();
						califAlumno.setListaMaterias(encabezadoMaterias);
						califAlumno.setListCalificaciones(listaCalificaciones);
						califAlumno.setIdAlumno(dto2.getIdAlumno());
						califAlumno.setMatricula(dto2.getMatricula());
						califAlumno.setNombre(dto2.getNombre());
						listaAlumnosCalificaciones.add(califAlumno);
					}
				}
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private void buildAlumnosAsistencia(List<AlumnoDTO> listaAlumnos,
			CursoDTO dtoCurso) {
		if(!CollectionUtils.isEmpty(listaAlumnos)){
			Date fechaInicio = dtoCurso.getFechaIni();
			Date fechaFin = dtoCurso.getFechaFin();
			minDate = fechaInicio;
			maxDate = fechaFin;
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.setTime(fechaFin);
			Date fechaTemp = fechaInicio;
			List<String> dias = null;
			List<String> asistencias = null;
			Calendar cal = Calendar.getInstance();
			List<AlumnoAsistenciaDTO> listaAsistencia = new ArrayList<AlumnoAsistenciaDTO>();
			for (AlumnoDTO dto : listaAlumnos) {
				asistencias = new ArrayList<String>();
				dias = new ArrayList<String>();
				AlumnoAsistenciaDTO asistenciaDTO = new AlumnoAsistenciaDTO();
				asistenciaDTO.setMatricula(dto.getMatricula());
				asistenciaDTO.setNombre(dto.getNombre());
				fechaTemp = fechaInicio;
				while (fechaFin.after(fechaTemp) || (cal.get(Calendar.DAY_OF_YEAR) == calendarFin.get(Calendar.DAY_OF_YEAR))) {
					String asistencia;
					try {
						asistencia = fachada.findAsistencia(dto.getIdAlumno(), dtoCurso.getIdCurso(), fechaTemp);
					} catch (FachadaException e) {
						asistencia = "";
					}
					String dia = FechaUtil.getInstance().parseDateMM_dd_yy(
							fechaTemp);
					dias.add(dia);
					cal.setTime(fechaTemp);
					cal.add(Calendar.DATE, 1);
					fechaTemp = cal.getTime();
					//String asistencia = "S";
					asistencias.add(asistencia);
				}
				asistenciaDTO.setDias(dias);
				asistenciaDTO.setAsistencias(asistencias);
				listaAsistencia.add(asistenciaDTO);
			}
			setListaAlumnosAsistencia(listaAsistencia);			
			setEncabezadoDias(listaAsistencia.get(0).getDias());
		}
	}
	
	public void registraAsistencia() {
		if(!CollectionUtils.isEmpty(listaAsistenciaSelecteds)){
			for(AlumnoAsistenciaDiaDTO dto : listaAsistenciaSelecteds){
				try {
					fachada.registraAsistencia(dto.getIdAlumno(), itemSelected.getIdCurso() , dto.getFechaAsistencia(), (short) 1);
					super.addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVA_ASISTENCIA_REGISTRADA);
					super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				} catch (FachadaException e) {
					super.addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVA_ASISTENCIA_FALLO);
					super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
				}
			}
			buildAlumnosAsistencia(listaAlumnosAgregados, itemSelected);
			
		}
	}
	
	 public void handleDateSelect(SelectEvent event) {  
		 try{
			 buildListaAlumnosAsistenciaDia(listaAlumnosAgregados, registrarAsistenciaDate);
			 showTableAsistencias = true;
		 }catch(Exception e){
			 super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, "Error al seleccionar la fecha");
		 }
	 }
	
	public void buildListaAlumnosAsistenciaDia(List<AlumnoDTO> listaAlumnos, Date fecha){
		listaAsistenciaTemp = new ArrayList<AlumnoAsistenciaDiaDTO>();
		if(!CollectionUtils.isEmpty(listaAlumnos)){
			for(AlumnoDTO dto : listaAlumnos){
				AlumnoAsistenciaDiaDTO asistenciaDTO = new AlumnoAsistenciaDiaDTO();
					asistenciaDTO.setAlumno(dto.getNombre());
					asistenciaDTO.setFechaAsistencia(fecha);
					asistenciaDTO.setIdAlumno(dto.getIdAlumno());
					asistenciaDTO.setMatricula(dto.getMatricula());
					listaAsistenciaTemp.add(asistenciaDTO);
			}
		}
	}
	

	@Override
	String getModulo() {
		return modulo;
	}

	/**
	 * @return the fachada
	 */
	public CursoFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada
	 *            the fachada to set
	 */
	public void setFachada(CursoFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the itemSelected
	 */
	public CursoDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected
	 *            the itemSelected to set
	 */
	public void setItemSelected(CursoDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the listItems
	 */
	public List<CursoDTO> getListItems() {
		initListaItems();
		return listItems;
	}

	/**
	 * @param listItems
	 *            the listItems to set
	 */
	public void setListItems(List<CursoDTO> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the listSelectItem1
	 */
	public List<SelectItem> getListSelectItem1() {
		initListSelectItem1();
		return listSelectItem1;
	}

	/**
	 * @param listSelectItem1
	 *            the listSelectItem1 to set
	 */
	public void setListSelectItem1(List<SelectItem> listSelectItem1) {
		this.listSelectItem1 = listSelectItem1;
	}

	/**
	 * @return the referenciaSelected
	 */
	public String getReferenciaSelected() {
		return referenciaSelected;
	}

	/**
	 * @param referenciaSelected
	 *            the referenciaSelected to set
	 */
	public void setReferenciaSelected(String referenciaSelected) {
		this.referenciaSelected = referenciaSelected;
	}

	/**
	 * @return the displayTab
	 */
	public boolean isDisplayTab() {
		return displayTab;
	}

	/**
	 * @param displayTab
	 *            the displayTab to set
	 */
	public void setDisplayTab(boolean displayTab) {
		this.displayTab = displayTab;
	}

	/**
	 * @return the listMaterias
	 */
	public List<MateriaDTO> getListMaterias() {
		return listMaterias;
	}

	/**
	 * @param listMaterias
	 *            the listMaterias to set
	 */
	public void setListMaterias(List<MateriaDTO> listMaterias) {
		this.listMaterias = listMaterias;
	}

	/**
	 * @return the listaAlumnosAgregados
	 */
	public List<AlumnoDTO> getListaAlumnosAgregados() {
		return listaAlumnosAgregados;
	}

	/**
	 * @param listaAlumnosAgregados
	 *            the listaAlumnosAgregados to set
	 */
	public void setListaAlumnosAgregados(List<AlumnoDTO> listaAlumnosAgregados) {
		this.listaAlumnosAgregados = listaAlumnosAgregados;
	}

	/**
	 * @return the listaAlumnosAsistencia
	 */
	public List<AlumnoAsistenciaDTO> getListaAlumnosAsistencia() {
		return listaAlumnosAsistencia;
	}

	/**
	 * @param listaAlumnosAsistencia
	 *            the listaAlumnosAsistencia to set
	 */
	public void setListaAlumnosAsistencia(
			List<AlumnoAsistenciaDTO> listaAlumnosAsistencia) {
		this.listaAlumnosAsistencia = listaAlumnosAsistencia;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the encabezadoDias
	 */
	public List<String> getEncabezadoDias() {
		return encabezadoDias;
	}

	/**
	 * @param encabezadoDias the encabezadoDias to set
	 */
	public void setEncabezadoDias(List<String> encabezadoDias) {
		this.encabezadoDias = encabezadoDias;
	}

	/**
	 * @return the minDate
	 */
	public Date getMinDate() {
		return minDate;
	}

	/**
	 * @param minDate the minDate to set
	 */
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	/**
	 * @return the maxDate
	 */
	public Date getMaxDate() {
		return maxDate;
	}

	/**
	 * @param maxDate the maxDate to set
	 */
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	/**
	 * @return the registrarAsistenciaDate
	 */
	public Date getRegistrarAsistenciaDate() {
		return registrarAsistenciaDate;
	}

	/**
	 * @param registrarAsistenciaDate the registrarAsistenciaDate to set
	 */
	public void setRegistrarAsistenciaDate(Date registrarAsistenciaDate) {
		this.registrarAsistenciaDate = registrarAsistenciaDate;
	}

	/**
	 * @return the listaAsistenciaTemp
	 */
	public List<AlumnoAsistenciaDiaDTO> getListaAsistenciaTemp() {
		return listaAsistenciaTemp;
	}

	/**
	 * @param listaAsistenciaTemp the listaAsistenciaTemp to set
	 */
	public void setListaAsistenciaTemp(
			List<AlumnoAsistenciaDiaDTO> listaAsistenciaTemp) {
		this.listaAsistenciaTemp = listaAsistenciaTemp;
	}

	/**
	 * @return the listaAsistenciaSelecteds
	 */
	public List<AlumnoAsistenciaDiaDTO> getListaAsistenciaSelecteds() {
		return listaAsistenciaSelecteds;
	}

	/**
	 * @param listaAsistenciaSelecteds the listaAsistenciaSelecteds to set
	 */
	public void setListaAsistenciaSelecteds(
			List<AlumnoAsistenciaDiaDTO> listaAsistenciaSelecteds) {
		this.listaAsistenciaSelecteds = listaAsistenciaSelecteds;
	}

	/**
	 * @return the showTableAsistencias
	 */
	public boolean isShowTableAsistencias() {
		return showTableAsistencias;
	}

	/**
	 * @param showTableAsistencias the showTableAsistencias to set
	 */
	public void setShowTableAsistencias(boolean showTableAsistencias) {
		this.showTableAsistencias = showTableAsistencias;
	}

	/**
	 * @return the encabezadoMaterias
	 */
	public List<String> getEncabezadoMaterias() {
		return encabezadoMaterias;
	}

	/**
	 * @param encabezadoMaterias the encabezadoMaterias to set
	 */
	public void setEncabezadoMaterias(List<String> encabezadoMaterias) {
		this.encabezadoMaterias = encabezadoMaterias;
	}

	/**
	 * @return the listaAlumnosCalificaciones
	 */
	public List<AlumnoCalificacionMateriaDTO> getListaAlumnosCalificaciones() {
		return listaAlumnosCalificaciones;
	}

	/**
	 * @param listaAlumnosCalificaciones the listaAlumnosCalificaciones to set
	 */
	public void setListaAlumnosCalificaciones(
			List<AlumnoCalificacionMateriaDTO> listaAlumnosCalificaciones) {
		this.listaAlumnosCalificaciones = listaAlumnosCalificaciones;
	}

	/**
	 * @return the listaMateriasPorCurso
	 */
	public List<MateriaDTO> getListaMateriasPorCurso() {
		return listaMateriasPorCurso;
	}

	/**
	 * @param listaMateriasPorCurso the listaMateriasPorCurso to set
	 */
	public void setListaMateriasPorCurso(List<MateriaDTO> listaMateriasPorCurso) {
		this.listaMateriasPorCurso = listaMateriasPorCurso;
	}

	/**
	 * @return the listaSelectMateriasCurso
	 */
	public List<SelectItem> getListaSelectMateriasCurso() {
		return listaSelectMateriasCurso;
	}

	/**
	 * @param listaSelectMateriasCurso the listaSelectMateriasCurso to set
	 */
	public void setListaSelectMateriasCurso(
			List<SelectItem> listaSelectMateriasCurso) {
		this.listaSelectMateriasCurso = listaSelectMateriasCurso;
	}

	/**
	 * @return the idMateriaSelected
	 */
	public int getIdMateriaSelected() {
		return idMateriaSelected;
	}

	/**
	 * @param idMateriaSelected the idMateriaSelected to set
	 */
	public void setIdMateriaSelected(int idMateriaSelected) {
		this.idMateriaSelected = idMateriaSelected;
	}

	/**
	 * @return the listaCalifMateriaSingle
	 */
	public List<AlumnoCalifMateriaSingle> getListaCalifMateriaSingle() {
		return listaCalifMateriaSingle;
	}

	/**
	 * @param listaCalifMateriaSingle the listaCalifMateriaSingle to set
	 */
	public void setListaCalifMateriaSingle(
			List<AlumnoCalifMateriaSingle> listaCalifMateriaSingle) {
		this.listaCalifMateriaSingle = listaCalifMateriaSingle;
	}

}
