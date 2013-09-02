package mx.com.asteca.vista;

import javax.swing.tree.DefaultMutableTreeNode;

import com.icesoft.faces.component.tree.IceUserObject;


public class SelectUserObject extends IceUserObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String displayPanel;

	
    public SelectUserObject(DefaultMutableTreeNode wrapper) {
        super(wrapper);
    }

    public String getDisplayPanel() {
        return (displayPanel == null ? "default.iface" : displayPanel);
    }

    public void setDisplayPanel(String displayPanel) {
        this.displayPanel = displayPanel;
    }
}
