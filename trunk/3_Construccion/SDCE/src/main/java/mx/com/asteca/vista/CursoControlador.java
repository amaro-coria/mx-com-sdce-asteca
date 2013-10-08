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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.fachada.CursoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.primefaces.event.SelectEvent;
import org.springframework.util.CollectionUtils;


/**
 * @author JAMARO
 *
 */
@ManagedBean(name = Constantes.BEAN_CURSO)
@ViewScoped
public class CursoControlador extends BaseController implements Serializable{

	private static final String modulo = Constantes.MODULO_CURSO;
	@ManagedProperty("#{cursoFachadaImpl}")
	private transient CursoFachada fachada;
	
	private List<SelectItem> listSelectClientes;
	private List<SelectItem> listSelectArea;
	private List<SelectItem> listSelectProgramaEstudio;
	private List<SelectItem> listSelectSede;
	private List<SelectItem> listSelectInstructor;
	private List<SelectItem> listSelectMaterias;
	private List<SelectItem> listSelectClasifCurso;
	
	private List<SelectItem> listaSelectAgregarAlumno;
	private List<AlumnoDTO> listaAlumnosInicial;
	private List<AlumnoDTO> listaAlumnosAgregados;
	private AlumnoDTO selectedAlumno;
	private int idAlumnoAgregar;
	
	private int idClienteSelected;
	private int idAreaSelected;
	private int idProgramaEstudioSelected;
	private int idSedeSelected;
	private int idInstructorSelected;
	private int idMateriaSelected;
	private int idTipoCursoSelected;
	
	private int idNuevoClienteSelected;
	private int idNuevoAreaSelected;
	private int idNuevoProgramaEstudioSelected;
	private int idNuevoSedeSelected;
	private int idNuevoInstructorSelected;
	private int idNuevoMateriaSelected;
	private int idNuevoTipoCursoSelected;
	
	private Date editarFechaHoraInicial;
	private Date editarFechaHoraFinal;
	private int idAulaEditarSelected;
	
	private Date nuevoFechaHoraInicial;
	private Date nuevoFechaHoraFinal;
	private int idAulaNuevoSelected;
	
	private List<MateriaDTO> listMaterias;
	private MateriaDTO selectedMateria;
	private MateriaDTO nuevoMateria;

	private List<MateriaRegistroDTO> listMateriasNuevo;
	
	private List<SelectItem> listSelectAulasDisponiblesEditar;
	private List<SelectItem> listSelectAulasDisponiblesNuevo;
	
	private Date nuevoCursoFechaInicio;
	private Date nuevoCursoFechaFin;
	private String nuevoCursoGrupoString;
	private Date nuevoCursoHoraInicio;
	private Date nuevoCursoHoraFin;
	private String nuevoCursoReferencia;
	private boolean tabViewShow;
	private CursoDTO dtoCursoNuevo;
	
	public void handleCambiaFechaEditar(SelectEvent event) {  
		initListAulasDisponibles1();
    } 
	
	public void handleCambiaFechaNuevo(SelectEvent event) {  
		initListAulasDisponibles2();
    }
	
	public void updateMateriasCursosSelectMenu(){
		handleChangeCurso();
	}
	
	public void deleteAlumno(){
		try {
			fachada.removeAlumnoCurso(selectedAlumno.getIdAlumno(), dtoCursoNuevo.getIdCurso());
			listaAlumnosAgregados.remove(selectedAlumno);
			SelectItem item = new SelectItem(selectedAlumno.getIdAlumno(), selectedAlumno.getNombre());
			listaSelectAgregarAlumno.add(item);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
		}
	}
	
	public void addAlumno(){
		try {
			fachada.saveAlumnoCurso(idAlumnoAgregar, dtoCursoNuevo.getIdCurso());
			AlumnoDTO dto = fachada.findAlumno(idAlumnoAgregar);
			listaAlumnosAgregados.add(dto);
			int indexToRemove = 0;
			for(int i = 0; i<listaSelectAgregarAlumno.size(); i++){
				SelectItem item = listaSelectAgregarAlumno.get(i);
				if(item.getValue().equals(dto.getIdAlumno())){
					indexToRemove = i;
					break;
				}
			}
			if(indexToRemove >= 0){
				listaSelectAgregarAlumno.remove(indexToRemove);
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
		}
	}
	
	public void deleteMateria(){
		try{
			fachada.removeMateriaCurso(selectedMateria.getIdMateria(), dtoCursoNuevo.getIdCurso());
			listMaterias.remove(selectedMateria);
			//TODO agregar la materia  al select items disponibles
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
		}
	}
	
	public void updateMateriaSelected(){
		selectedMateria.setFechaFinal(editarFechaHoraFinal);
		selectedMateria.setFechaInicial(editarFechaHoraInicial);
		selectedMateria.setIdAula(idAulaEditarSelected);
		selectedMateria.setIdInstructor(idInstructorSelected);
		selectedMateria.setIdMateriaRegistro(idMateriaSelected);
		selectedMateria.setIdTipo(idTipoCursoSelected);
		try {
			fachada.updateMateria(selectedMateria);			
			listMateriasNuevo = fachada.findMateriasPorProgramaEstudio(idProgramaEstudioSelected);
			listMaterias = fachada.findMateriasPorCurso(dtoCursoNuevo.getIdCurso());
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
		}
	}
	
	public void addMateriaNueva(){
		nuevoMateria = new MateriaDTO();
		nuevoMateria.setFechaFinal(nuevoFechaHoraFinal);
		nuevoMateria.setFechaInicial(nuevoFechaHoraInicial);
		nuevoMateria.setIdAula(idAulaNuevoSelected);
		nuevoMateria.setIdInstructor(idNuevoInstructorSelected);
		nuevoMateria.setIdMateriaRegistro(idNuevoMateriaSelected);
		nuevoMateria.setIdTipo(idNuevoTipoCursoSelected);
		int pk;
		try {
			pk = fachada.addMateriaCurso(nuevoMateria, dtoCursoNuevo.getIdCurso());
			//pk = fachada.saveMateriaCurso(idNuevoMateriaSelected, dtoCursoNuevo.getIdCurso());
			nuevoMateria.setIdMateria(pk);
			listMaterias = fachada.findMateriasPorCurso(dtoCursoNuevo.getIdCurso());
			//listMateriasNuevo = fachada.findMateriasPorProgramaEstudio(idProgramaEstudioSelected);
			/*
			int indexToRemove = 0;
		    for(int i = 0; i< listSelectMaterias.size(); i++){
		    	SelectItem item = listSelectMaterias.get(i);
		    	if(item.getValue().equals(idNuevoMateriaSelected)){
		    		indexToRemove = i;
		    	}
		    }
		    if(indexToRemove >= 0){
		    	listSelectMaterias.remove(indexToRemove);
		    }
		    */
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
		}
		
	}
	
	private void initListaAlumnosAgregados(){
		if(CollectionUtils.isEmpty(listaAlumnosAgregados)){
			listaAlumnosAgregados = new ArrayList<AlumnoDTO>();
		}
	}
	
	private void initListaSelectAgregarAlumnos(){
		if(CollectionUtils.isEmpty(listaSelectAgregarAlumno)){
			listaSelectAgregarAlumno = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListaAlumnosInicial())){
				for(AlumnoDTO dto : getListaAlumnosInicial()){
					SelectItem item = new SelectItem(dto.getIdAlumno(), dto.getNombre());
					listaSelectAgregarAlumno.add(item);
				}
			}
		}
	}
	
	private void initListaAlumnosInicial(){
		if(CollectionUtils.isEmpty(listaAlumnosInicial)){
			try {
				listaAlumnosInicial = fachada.findAlumnos();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListSelectClasifCurso(){
		try{
			if(CollectionUtils.isEmpty(listSelectClasifCurso)){
				List<CatGralDTO> lista = fachada.findClasifCursos();
				listSelectClasifCurso = new ArrayList<SelectItem>();
				if(!CollectionUtils.isEmpty(lista)){
					for(CatGralDTO dto : lista){
						SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getDsc());
						listSelectClasifCurso.add(item);
					}
				}
			}
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private void initListAulasDisponibles1(){
		if(editarFechaHoraFinal == null && editarFechaHoraInicial == null){
			listSelectAulasDisponiblesEditar = new ArrayList<SelectItem>();
			SelectItem item = new SelectItem(0,"Seleccionar fechas primero");
			listSelectAulasDisponiblesEditar.add(item);
		}else{
			listSelectAulasDisponiblesEditar = new ArrayList<SelectItem>();
			List<AulaDTO> listaAulas = findAulasDisponibles(editarFechaHoraInicial, editarFechaHoraFinal, idSedeSelected);
			if(CollectionUtils.isEmpty(listaAulas)){
				SelectItem item = new SelectItem(0,"No hay aulas disponibles");
				listSelectAulasDisponiblesEditar.add(item);
			}else{
				for(AulaDTO dto : listaAulas){
					SelectItem item = new SelectItem(dto.getIdAula(), dto.getDsc());
					listSelectAulasDisponiblesEditar.add(item);
				}
			}
		}
	}
	
	private void initListAulasDisponibles2(){
		if(nuevoFechaHoraFinal == null && nuevoFechaHoraInicial == null){
			listSelectAulasDisponiblesEditar = new ArrayList<SelectItem>();
			SelectItem item = new SelectItem(0,"Seleccionar fechas primero");
			listSelectAulasDisponiblesNuevo.add(item);
		}else{
			listSelectAulasDisponiblesNuevo = new ArrayList<SelectItem>();
			List<AulaDTO> listaAulas = findAulasDisponibles(nuevoFechaHoraInicial, nuevoFechaHoraFinal, idSedeSelected);
			if(CollectionUtils.isEmpty(listaAulas)){
				SelectItem item = new SelectItem(0,"No hay aulas disponibles");
				listSelectAulasDisponiblesNuevo.add(item);
			}else{
				for(AulaDTO dto : listaAulas){
					SelectItem item = new SelectItem(dto.getIdAula(), dto.getDsc());
					listSelectAulasDisponiblesNuevo.add(item);
				}
			}
		}
	}
	
	private void initListAulasDisponiblesNuevo(){
		if(nuevoFechaHoraFinal == null && nuevoFechaHoraInicial == null){
			listSelectAulasDisponiblesNuevo = new ArrayList<SelectItem>();
			SelectItem item = new SelectItem(0,"Seleccionar fechas primero");
			listSelectAulasDisponiblesNuevo.add(item);
		}else{
			List<AulaDTO> listaAulas = findAulasDisponibles(nuevoFechaHoraInicial, nuevoFechaHoraFinal, idSedeSelected);
			if(CollectionUtils.isEmpty(listaAulas)){
				SelectItem item = new SelectItem(0,"No hay aulas disponibles");
				listSelectAulasDisponiblesNuevo.add(item);
			}else{
				for(AulaDTO dto : listaAulas){
					SelectItem item = new SelectItem(dto.getIdAula(), dto.getDsc());
					listSelectAulasDisponiblesNuevo.add(item);
				}
			}
		}
	}
	
	
	private List<AulaDTO> findAulasDisponibles(Date timeStampInicial, Date timeStampFinal, int idSede){
		List<AulaDTO> lista;
		try {
			lista = fachada.getAulasDisponibles(timeStampInicial, timeStampFinal, idSede);
		} catch (FachadaException e) {
			lista = new ArrayList<AulaDTO>();
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return lista;
	}
	
	public void handleChangeCurso(){
		if(idProgramaEstudioSelected != 0){
			try {
				listMateriasNuevo = fachada.findMateriasPorProgramaEstudio(idProgramaEstudioSelected);
				handleChangeListMaterias();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	public void creaCurso(){
		CursoDTO dto = new CursoDTO();
		dto.setFechaFin(nuevoCursoFechaFin);
		dto.setFechaIni(nuevoCursoFechaInicio);
		try{
			dto.setGrupo(Integer.parseInt(nuevoCursoGrupoString));
		}catch(NumberFormatException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(nuevoCursoHoraInicio);
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		int mins = cal.get(Calendar.MINUTE);
		String horaInicio = String.valueOf(hora)+":"+String.valueOf(mins);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(nuevoCursoHoraFin);
		int hora2 = cal2.get(Calendar.HOUR_OF_DAY);
		int mins2 = cal2.get(Calendar.MINUTE);
		String horaFin = String.valueOf(hora2)+":"+String.valueOf(mins2);
		dto.setHoraFin(horaFin);
		dto.setHoraIni(horaInicio);
		dto.setIdArea(idAreaSelected);
		dto.setIdProgrEstudios(idProgramaEstudioSelected);
		dto.setIdSede(idSedeSelected);
		dto.setReferencia(nuevoCursoReferencia);
		try {
			int pk = fachada.save(dto);
			dto.setIdCurso(pk);
			dtoCursoNuevo = dto;
			handleChangeCurso();
			tabViewShow = true;
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
		}
	}
	
	public void handleChangeListMaterias(){
		listMaterias = new ArrayList<MateriaDTO>();
		if(!CollectionUtils.isEmpty(listMateriasNuevo)){
			for(MateriaRegistroDTO dto : listMateriasNuevo){
				MateriaDTO materia = new MateriaDTO();
				materia.setIdMateriaRegistro(dto.getIdMateria());
				materia.setNombre(dto.getNombre());
				listMaterias.add(materia);
			}
			addCursoMateriasDefault(listMaterias);
		}
	}
	
	private void addCursoMateriasDefault(List<MateriaDTO> listaMaterias){
		//Registrar las materias para el curso para poderlas editar
		try{
			List<MateriaDTO> listaMateriasTemp = new ArrayList<MateriaDTO>();
			for(MateriaDTO dto : listaMaterias){
				int pk = fachada.saveMateriaCurso(dto.getIdMateriaRegistro(), dtoCursoNuevo.getIdCurso());
				dto.setIdMateria(pk);
				listaMateriasTemp.add(dto);
			}
			listMaterias= listaMateriasTemp;
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		
	}
	
	private void initSelectListMaterias(){
		if(CollectionUtils.isEmpty(listSelectMaterias)){
			listSelectMaterias = new ArrayList<SelectItem>();
			try {
				List<MateriaRegistroDTO> lista = fachada.findAllMaterias();
				if(!CollectionUtils.isEmpty(lista)){
					for(MateriaRegistroDTO dto : lista){
						SelectItem item = new SelectItem(dto.getIdMateria(), dto.getNombre());
						listSelectMaterias.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListSelectInstructor(){
		if(CollectionUtils.isEmpty(listSelectInstructor)){
			listSelectInstructor = new ArrayList<SelectItem>();
			try {
				List<InstructorDTO> lista = fachada.findInstructores();
				if(!CollectionUtils.isEmpty(lista)){
					for(InstructorDTO dto : lista){
						SelectItem item = new SelectItem(dto.getIdInstructor(), dto.getNombre());
						listSelectInstructor.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListSelectSede(){
		if(CollectionUtils.isEmpty(listSelectSede)){
			try {
				List<CatGralDTO> listaSede = fachada.findSedes();
				listSelectSede = new ArrayList<SelectItem>();
				if(!CollectionUtils.isEmpty(listaSede)){
					for(CatGralDTO dto : listaSede){
						SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getDsc());
						listSelectSede.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListSelectProgramaEstudio(){
		if(CollectionUtils.isEmpty(listSelectProgramaEstudio)){
			try {
				List<ProgramaEstudiosDTO> lista = fachada.findProgramaEstudios();
				listSelectProgramaEstudio = new ArrayList<SelectItem>();
				if(!CollectionUtils.isEmpty(lista)){
					for(ProgramaEstudiosDTO dto : lista){
						SelectItem item = new SelectItem(dto.getIdProgEstudio(), dto.getDsc());
						listSelectProgramaEstudio.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaSelectArea(){
		if(CollectionUtils.isEmpty(listSelectArea)){
			try {
				List<CatGralDTO> listaAreas = fachada.findAreas();
				listSelectArea = new ArrayList<SelectItem>();
				if(!CollectionUtils.isEmpty(listaAreas)){
					for(CatGralDTO dto : listaAreas){
						SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getDsc());
						listSelectArea.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	@Override
	String getModulo() {
		return modulo;
	}
	
	private void initListSelectClientes(){
		if(CollectionUtils.isEmpty(listSelectClientes)){
			try {
				List<ClienteDTO> listaClientes = fachada.findClientes();
				listSelectClientes = new ArrayList<SelectItem>();
				if(!CollectionUtils.isEmpty(listaClientes)){
					for(ClienteDTO dto : listaClientes){
						SelectItem item = new SelectItem(dto.getIdCliente(), dto.getNombre());
						listSelectClientes.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * @return the fachada
	 */
	public CursoFachada getFachada() {
		return fachada;
	}

//--------------- Getters & Setters --------------------------//

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(CursoFachada fachada) {
		this.fachada = fachada;
	}



	/**
	 * @return the listSelectClientes
	 */
	public List<SelectItem> getListSelectClientes() {
		initListSelectClientes();
		return listSelectClientes;
	}



	/**
	 * @param listSelectClientes the listSelectClientes to set
	 */
	public void setListSelectClientes(List<SelectItem> listSelectClientes) {
		this.listSelectClientes = listSelectClientes;
	}



	/**
	 * @return the listSelectArea
	 */
	public List<SelectItem> getListSelectArea() {
		initListaSelectArea();
		return listSelectArea;
	}



	/**
	 * @param listSelectArea the listSelectArea to set
	 */
	public void setListSelectArea(List<SelectItem> listSelectArea) {
		this.listSelectArea = listSelectArea;
	}



	/**
	 * @return the listSelectProgramaEstudio
	 */
	public List<SelectItem> getListSelectProgramaEstudio() {
		initListSelectProgramaEstudio();
		return listSelectProgramaEstudio;
	}



	/**
	 * @param listSelectProgramaEstudio the listSelectProgramaEstudio to set
	 */
	public void setListSelectProgramaEstudio(
			List<SelectItem> listSelectProgramaEstudio) {
		this.listSelectProgramaEstudio = listSelectProgramaEstudio;
	}



	/**
	 * @return the listSelectSede
	 */
	public List<SelectItem> getListSelectSede() {
		initListSelectSede();
		return listSelectSede;
	}



	/**
	 * @param listSelectSede the listSelectSede to set
	 */
	public void setListSelectSede(List<SelectItem> listSelectSede) {
		this.listSelectSede = listSelectSede;
	}



	/**
	 * @return the listMaterias
	 */
	public List<MateriaDTO> getListMaterias() {
		return listMaterias;
	}



	/**
	 * @param listMaterias the listMaterias to set
	 */
	public void setListMaterias(List<MateriaDTO> listMaterias) {
		this.listMaterias = listMaterias;
	}

	/**
	 * @return the idClienteSelected
	 */
	public int getIdClienteSelected() {
		return idClienteSelected;
	}

	/**
	 * @param idClienteSelected the idClienteSelected to set
	 */
	public void setIdClienteSelected(int idClienteSelected) {
		this.idClienteSelected = idClienteSelected;
	}

	/**
	 * @return the idAreaSelected
	 */
	public int getIdAreaSelected() {
		return idAreaSelected;
	}

	/**
	 * @param idAreaSelected the idAreaSelected to set
	 */
	public void setIdAreaSelected(int idAreaSelected) {
		this.idAreaSelected = idAreaSelected;
	}

	/**
	 * @return the idProgramaEstudioSelected
	 */
	public int getIdProgramaEstudioSelected() {
		return idProgramaEstudioSelected;
	}

	/**
	 * @param idProgramaEstudioSelected the idProgramaEstudioSelected to set
	 */
	public void setIdProgramaEstudioSelected(int idProgramaEstudioSelected) {
		this.idProgramaEstudioSelected = idProgramaEstudioSelected;
	}

	/**
	 * @return the idSedeSelected
	 */
	public int getIdSedeSelected() {
		return idSedeSelected;
	}

	/**
	 * @param idSedeSelected the idSedeSelected to set
	 */
	public void setIdSedeSelected(int idSedeSelected) {
		this.idSedeSelected = idSedeSelected;
	}

	/**
	 * @return the listMateriasNuevo
	 */
	public List<MateriaRegistroDTO> getListMateriasNuevo() {
		return listMateriasNuevo;
	}

	/**
	 * @param listMateriasNuevo the listMateriasNuevo to set
	 */
	public void setListMateriasNuevo(List<MateriaRegistroDTO> listMateriasNuevo) {
		this.listMateriasNuevo = listMateriasNuevo;
	}

	/**
	 * @return the listSelectInstructor
	 */
	public List<SelectItem> getListSelectInstructor() {
		initListSelectInstructor();
		return listSelectInstructor;
	}

	/**
	 * @param listSelectInstructor the listSelectInstructor to set
	 */
	public void setListSelectInstructor(List<SelectItem> listSelectInstructor) {
		this.listSelectInstructor = listSelectInstructor;
	}

	/**
	 * @return the idInstructorSelected
	 */
	public int getIdInstructorSelected() {
		return idInstructorSelected;
	}

	/**
	 * @param idInstructorSelected the idInstructorSelected to set
	 */
	public void setIdInstructorSelected(int idInstructorSelected) {
		this.idInstructorSelected = idInstructorSelected;
	}

	/**
	 * @return the selectedMateria
	 */
	public MateriaDTO getSelectedMateria() {
		return selectedMateria;
	}

	/**
	 * @param selectedMateria the selectedMateria to set
	 */
	public void setSelectedMateria(MateriaDTO selectedMateria) {
		this.selectedMateria = selectedMateria;
	}

	/**
	 * @return the listSelectMaterias
	 */
	public List<SelectItem> getListSelectMaterias() {
		initSelectListMaterias();
		return listSelectMaterias;
	}

	/**
	 * @param listSelectMaterias the listSelectMaterias to set
	 */
	public void setListSelectMaterias(List<SelectItem> listSelectMaterias) {
		this.listSelectMaterias = listSelectMaterias;
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
	 * @return the editarFechaHoraInicial
	 */
	public Date getEditarFechaHoraInicial() {
		return editarFechaHoraInicial;
	}


	/**
	 * @param editarFechaHoraInicial the editarFechaHoraInicial to set
	 */
	public void setEditarFechaHoraInicial(Date editarFechaHoraInicial) {
		this.editarFechaHoraInicial = editarFechaHoraInicial;
	}


	/**
	 * @return the editarFechaHoraFinal
	 */
	public Date getEditarFechaHoraFinal() {
		return editarFechaHoraFinal;
	}


	/**
	 * @param editarFechaHoraFinal the editarFechaHoraFinal to set
	 */
	public void setEditarFechaHoraFinal(Date editarFechaHoraFinal) {
		this.editarFechaHoraFinal = editarFechaHoraFinal;
	}


	/**
	 * @return the idAulaEditarSelected
	 */
	public int getIdAulaEditarSelected() {
		return idAulaEditarSelected;
	}


	/**
	 * @param idAulaEditarSelected the idAulaEditarSelected to set
	 */
	public void setIdAulaEditarSelected(int idAulaEditarSelected) {
		this.idAulaEditarSelected = idAulaEditarSelected;
	}


	/**
	 * @return the listSelectAulasDisponiblesEditar
	 */
	public List<SelectItem> getListSelectAulasDisponiblesEditar() {
		initListAulasDisponibles1();
		return listSelectAulasDisponiblesEditar;
	}


	/**
	 * @param listSelectAulasDisponiblesEditar the listSelectAulasDisponiblesEditar to set
	 */
	public void setListSelectAulasDisponiblesEditar(
			List<SelectItem> listSelectAulasDisponiblesEditar) {
		this.listSelectAulasDisponiblesEditar = listSelectAulasDisponiblesEditar;
	}

	/**
	 * @return the listSelectClasifCurso
	 */
	public List<SelectItem> getListSelectClasifCurso() {
		initListSelectClasifCurso();
		return listSelectClasifCurso;
	}

	/**
	 * @param listSelectClasifCurso the listSelectClasifCurso to set
	 */
	public void setListSelectClasifCurso(List<SelectItem> listSelectClasifCurso) {
		this.listSelectClasifCurso = listSelectClasifCurso;
	}

	/**
	 * @return the idTipoCursoSelected
	 */
	public int getIdTipoCursoSelected() {
		return idTipoCursoSelected;
	}

	/**
	 * @param idTipoCursoSelected the idTipoCursoSelected to set
	 */
	public void setIdTipoCursoSelected(int idTipoCursoSelected) {
		this.idTipoCursoSelected = idTipoCursoSelected;
	}

	/**
	 * @return the idNuevoClienteSelected
	 */
	public int getIdNuevoClienteSelected() {
		return idNuevoClienteSelected;
	}

	/**
	 * @param idNuevoClienteSelected the idNuevoClienteSelected to set
	 */
	public void setIdNuevoClienteSelected(int idNuevoClienteSelected) {
		this.idNuevoClienteSelected = idNuevoClienteSelected;
	}

	/**
	 * @return the idNuevoAreaSelected
	 */
	public int getIdNuevoAreaSelected() {
		return idNuevoAreaSelected;
	}

	/**
	 * @param idNuevoAreaSelected the idNuevoAreaSelected to set
	 */
	public void setIdNuevoAreaSelected(int idNuevoAreaSelected) {
		this.idNuevoAreaSelected = idNuevoAreaSelected;
	}

	/**
	 * @return the idNuevoProgramaEstudioSelected
	 */
	public int getIdNuevoProgramaEstudioSelected() {
		return idNuevoProgramaEstudioSelected;
	}

	/**
	 * @param idNuevoProgramaEstudioSelected the idNuevoProgramaEstudioSelected to set
	 */
	public void setIdNuevoProgramaEstudioSelected(int idNuevoProgramaEstudioSelected) {
		this.idNuevoProgramaEstudioSelected = idNuevoProgramaEstudioSelected;
	}

	/**
	 * @return the idNuevoSedeSelected
	 */
	public int getIdNuevoSedeSelected() {
		return idNuevoSedeSelected;
	}

	/**
	 * @param idNuevoSedeSelected the idNuevoSedeSelected to set
	 */
	public void setIdNuevoSedeSelected(int idNuevoSedeSelected) {
		this.idNuevoSedeSelected = idNuevoSedeSelected;
	}

	/**
	 * @return the idNuevoInstructorSelected
	 */
	public int getIdNuevoInstructorSelected() {
		return idNuevoInstructorSelected;
	}

	/**
	 * @param idNuevoInstructorSelected the idNuevoInstructorSelected to set
	 */
	public void setIdNuevoInstructorSelected(int idNuevoInstructorSelected) {
		this.idNuevoInstructorSelected = idNuevoInstructorSelected;
	}

	/**
	 * @return the idNuevoMateriaSelected
	 */
	public int getIdNuevoMateriaSelected() {
		return idNuevoMateriaSelected;
	}

	/**
	 * @param idNuevoMateriaSelected the idNuevoMateriaSelected to set
	 */
	public void setIdNuevoMateriaSelected(int idNuevoMateriaSelected) {
		this.idNuevoMateriaSelected = idNuevoMateriaSelected;
	}

	/**
	 * @return the idNuevoTipoCursoSelected
	 */
	public int getIdNuevoTipoCursoSelected() {
		return idNuevoTipoCursoSelected;
	}

	/**
	 * @param idNuevoTipoCursoSelected the idNuevoTipoCursoSelected to set
	 */
	public void setIdNuevoTipoCursoSelected(int idNuevoTipoCursoSelected) {
		this.idNuevoTipoCursoSelected = idNuevoTipoCursoSelected;
	}

	/**
	 * @return the nuevoFechaHoraInicial
	 */
	public Date getNuevoFechaHoraInicial() {
		return nuevoFechaHoraInicial;
	}

	/**
	 * @param nuevoFechaHoraInicial the nuevoFechaHoraInicial to set
	 */
	public void setNuevoFechaHoraInicial(Date nuevoFechaHoraInicial) {
		this.nuevoFechaHoraInicial = nuevoFechaHoraInicial;
	}

	/**
	 * @return the nuevoFechaHoraFinal
	 */
	public Date getNuevoFechaHoraFinal() {
		return nuevoFechaHoraFinal;
	}

	/**
	 * @param nuevoFechaHoraFinal the nuevoFechaHoraFinal to set
	 */
	public void setNuevoFechaHoraFinal(Date nuevoFechaHoraFinal) {
		this.nuevoFechaHoraFinal = nuevoFechaHoraFinal;
	}

	/**
	 * @return the idAulaNuevoSelected
	 */
	public int getIdAulaNuevoSelected() {
		return idAulaNuevoSelected;
	}

	/**
	 * @param idAulaNuevoSelected the idAulaNuevoSelected to set
	 */
	public void setIdAulaNuevoSelected(int idAulaNuevoSelected) {
		this.idAulaNuevoSelected = idAulaNuevoSelected;
	}

	/**
	 * @return the listSelectAulasDisponiblesNuevo
	 */
	public List<SelectItem> getListSelectAulasDisponiblesNuevo() {
		initListAulasDisponiblesNuevo();
		return listSelectAulasDisponiblesNuevo;
	}

	/**
	 * @param listSelectAulasDisponiblesNuevo the listSelectAulasDisponiblesNuevo to set
	 */
	public void setListSelectAulasDisponiblesNuevo(
			List<SelectItem> listSelectAulasDisponiblesNuevo) {
		this.listSelectAulasDisponiblesNuevo = listSelectAulasDisponiblesNuevo;
	}

	/**
	 * @return the nuevoMateria
	 */
	public MateriaDTO getNuevoMateria() {
		return nuevoMateria;
	}

	/**
	 * @param nuevoMateria the nuevoMateria to set
	 */
	public void setNuevoMateria(MateriaDTO nuevoMateria) {
		this.nuevoMateria = nuevoMateria;
	}

	/**
	 * @return the nuevoCursoFechaInicio
	 */
	public Date getNuevoCursoFechaInicio() {
		return nuevoCursoFechaInicio;
	}

	/**
	 * @param nuevoCursoFechaInicio the nuevoCursoFechaInicio to set
	 */
	public void setNuevoCursoFechaInicio(Date nuevoCursoFechaInicio) {
		this.nuevoCursoFechaInicio = nuevoCursoFechaInicio;
	}

	/**
	 * @return the nuevoCursoFechaFin
	 */
	public Date getNuevoCursoFechaFin() {
		return nuevoCursoFechaFin;
	}

	/**
	 * @param nuevoCursoFechaFin the nuevoCursoFechaFin to set
	 */
	public void setNuevoCursoFechaFin(Date nuevoCursoFechaFin) {
		this.nuevoCursoFechaFin = nuevoCursoFechaFin;
	}

	/**
	 * @return the nuevoCursoGrupoString
	 */
	public String getNuevoCursoGrupoString() {
		return nuevoCursoGrupoString;
	}

	/**
	 * @param nuevoCursoGrupoString the nuevoCursoGrupoString to set
	 */
	public void setNuevoCursoGrupoString(String nuevoCursoGrupoString) {
		this.nuevoCursoGrupoString = nuevoCursoGrupoString;
	}

	/**
	 * @return the nuevoCursoHoraInicio
	 */
	public Date getNuevoCursoHoraInicio() {
		return nuevoCursoHoraInicio;
	}

	/**
	 * @param nuevoCursoHoraInicio the nuevoCursoHoraInicio to set
	 */
	public void setNuevoCursoHoraInicio(Date nuevoCursoHoraInicio) {
		this.nuevoCursoHoraInicio = nuevoCursoHoraInicio;
	}

	/**
	 * @return the nuevoCursoHoraFin
	 */
	public Date getNuevoCursoHoraFin() {
		return nuevoCursoHoraFin;
	}

	/**
	 * @param nuevoCursoHoraFin the nuevoCursoHoraFin to set
	 */
	public void setNuevoCursoHoraFin(Date nuevoCursoHoraFin) {
		this.nuevoCursoHoraFin = nuevoCursoHoraFin;
	}

	/**
	 * @return the nuevoCursoReferencia
	 */
	public String getNuevoCursoReferencia() {
		return nuevoCursoReferencia;
	}

	/**
	 * @param nuevoCursoReferencia the nuevoCursoReferencia to set
	 */
	public void setNuevoCursoReferencia(String nuevoCursoReferencia) {
		this.nuevoCursoReferencia = nuevoCursoReferencia;
	}

	/**
	 * @return the tabViewShow
	 */
	public boolean isTabViewShow() {
		return tabViewShow;
	}

	/**
	 * @param tabViewShow the tabViewShow to set
	 */
	public void setTabViewShow(boolean tabViewShow) {
		this.tabViewShow = tabViewShow;
	}

	/**
	 * @return the listaSelectAgregarAlumno
	 */
	public List<SelectItem> getListaSelectAgregarAlumno() {
		initListaSelectAgregarAlumnos();
		return listaSelectAgregarAlumno;
	}

	/**
	 * @param listaSelectAgregarAlumno the listaSelectAgregarAlumno to set
	 */
	public void setListaSelectAgregarAlumno(
			List<SelectItem> listaSelectAgregarAlumno) {
		this.listaSelectAgregarAlumno = listaSelectAgregarAlumno;
	}

	/**
	 * @return the listaAlumnosInicial
	 */
	public List<AlumnoDTO> getListaAlumnosInicial() {
		initListaAlumnosInicial();
		return listaAlumnosInicial;
	}

	/**
	 * @param listaAlumnosInicial the listaAlumnosInicial to set
	 */
	public void setListaAlumnosInicial(List<AlumnoDTO> listaAlumnosInicial) {
		this.listaAlumnosInicial = listaAlumnosInicial;
	}

	/**
	 * @return the listaAlumnosAgregados
	 */
	public List<AlumnoDTO> getListaAlumnosAgregados() {
		initListaAlumnosAgregados();
		return listaAlumnosAgregados;
	}

	/**
	 * @param listaAlumnosAgregados the listaAlumnosAgregados to set
	 */
	public void setListaAlumnosAgregados(List<AlumnoDTO> listaAlumnosAgregados) {
		this.listaAlumnosAgregados = listaAlumnosAgregados;
	}

	/**
	 * @return the dtoCursoNuevo
	 */
	public CursoDTO getDtoCursoNuevo() {
		return dtoCursoNuevo;
	}

	/**
	 * @param dtoCursoNuevo the dtoCursoNuevo to set
	 */
	public void setDtoCursoNuevo(CursoDTO dtoCursoNuevo) {
		this.dtoCursoNuevo = dtoCursoNuevo;
	}

	/**
	 * @return the selectedAlumno
	 */
	public AlumnoDTO getSelectedAlumno() {
		return selectedAlumno;
	}

	/**
	 * @param selectedAlumno the selectedAlumno to set
	 */
	public void setSelectedAlumno(AlumnoDTO selectedAlumno) {
		this.selectedAlumno = selectedAlumno;
	}

	/**
	 * @return the idAlumnoAgregar
	 */
	public int getIdAlumnoAgregar() {
		return idAlumnoAgregar;
	}

	/**
	 * @param idAlumnoAgregar the idAlumnoAgregar to set
	 */
	public void setIdAlumnoAgregar(int idAlumnoAgregar) {
		this.idAlumnoAgregar = idAlumnoAgregar;
	}

}
