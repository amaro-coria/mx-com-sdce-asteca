package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import mx.com.asteca.comun.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_TREE)
@ViewScoped
public class TreeControlador extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LoggerFactory.getLogger(TreeControlador.class);
	public static final String IMAGE_ICON = "tree_document.gif";
    public static final String IMAGE_CLOSED = "tree_folder_closed.gif";
    public static final String IMAGE_OPEN = "tree_folder_open.gif";
    public static final String IMAGE_PATH = "/xmlhttp/css/rime/css-images/";

	private DefaultTreeModel model;
	private String url;

	public TreeControlador() {
		armaArbol();
	}

	
	/**
	 * 
	 * @return DefaultTreeModel
	 */
	private void armaArbol() {
		model = null;
		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
		SelectUserObject panel = new SelectUserObject(rootTreeNode);
		panel.setText("ASTECA");
        panel.setExpanded(true);
        panel.setLeafIcon(makeIcon(IMAGE_ICON));
        panel.setBranchContractedIcon(makeIcon(IMAGE_CLOSED));
        panel.setBranchExpandedIcon(makeIcon(IMAGE_OPEN));
		
        rootTreeNode.setUserObject(panel);
		
		model = new DefaultTreeModel(rootTreeNode);

		SelectUserObject panelSubmodulo = new SelectUserObject(rootTreeNode);
		panelSubmodulo.setText("MENU");
		panelSubmodulo.setDisplayPanel("./Ejemplo.xhtml");
		panelSubmodulo.setLeafIcon(makeIcon(IMAGE_ICON));
		panelSubmodulo.setBranchContractedIcon(makeIcon(IMAGE_CLOSED));
		panelSubmodulo.setBranchExpandedIcon(makeIcon(IMAGE_OPEN));
		DefaultMutableTreeNode nodoSubmodulos = agregarNodo(rootTreeNode, panelSubmodulo, true);
		
		
		/********************NODOS******************/
		SelectUserObject panelFuncion1 = new SelectUserObject(nodoSubmodulos);
		panelFuncion1.setText("clientes");
		panelFuncion1.setDisplayPanel("../clientes.xhtml");
		panelFuncion1.setLeaf(true);
		panelFuncion1.setLeafIcon(makeIcon(IMAGE_ICON));
		panelFuncion1.setBranchContractedIcon(makeIcon(IMAGE_CLOSED));
		panelFuncion1.setBranchExpandedIcon(makeIcon(IMAGE_OPEN));
		agregarNodo(nodoSubmodulos, panelFuncion1, false);
		
		SelectUserObject panelFuncion2 = new SelectUserObject(nodoSubmodulos);
		panelFuncion2.setText("Notificacion");
		panelFuncion2.setDisplayPanel("../notificacion.xhtml");
		panelFuncion2.setLeaf(true);
		panelFuncion2.setLeafIcon(makeIcon(IMAGE_ICON));
		panelFuncion2.setBranchContractedIcon(makeIcon(IMAGE_CLOSED));
		panelFuncion2.setBranchExpandedIcon(makeIcon(IMAGE_OPEN));
		agregarNodo(nodoSubmodulos, panelFuncion2, false);
	}
	
	public DefaultMutableTreeNode agregarNodo(DefaultMutableTreeNode nodoPadre, SelectUserObject panelNode, boolean esHoja){
    	
    	//Inicia busqueda para saber si existe el sistemas
		DefaultMutableTreeNode nodeHijo = new DefaultMutableTreeNode();
        nodeHijo.setUserObject(panelNode);
        nodeHijo.setAllowsChildren(esHoja);
		
        Enumeration<?> e = nodoPadre.children();
        if(e.hasMoreElements() == false){
        	nodoPadre.add(nodeHijo);
        }
        else{
        	DefaultMutableTreeNode def = null;
        	boolean existe = false;
	        while (e.hasMoreElements()) {
				def = (DefaultMutableTreeNode) e.nextElement();
				if(((SelectUserObject)def.getUserObject()).getText().equals(panelNode.getText()) == true){
					existe = true;
					break;
				}
			}
	        if(existe == false){
	        	nodoPadre.add(nodeHijo);
	        }
	        else{
	        	nodeHijo = null;
	        	nodeHijo = def;
	        }
        }
    	
    	return nodeHijo;
    }
	
	public static String makeIcon(String image) {
        return IMAGE_PATH + image;
    }
	
	public String actualizaUrl(){
		url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
		LOGGER.debug("ACTUALIZA URL.. " + url);
		
		return null;
	}

	/**
	 * @return the model
	 */
	public DefaultTreeModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(DefaultTreeModel model) {
		this.model = model;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		LOGGER.debug("GET URL.. " + url);
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		LOGGER.debug("SET URL.. " + url);
		this.url = url;
	}

}
