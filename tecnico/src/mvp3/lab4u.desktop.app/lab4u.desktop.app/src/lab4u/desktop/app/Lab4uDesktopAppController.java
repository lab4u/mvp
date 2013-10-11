/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app;

import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lab4u.desktop.app.bussines.Lab4uBC;
import lab4u.desktop.app.view.c.TreeViewDevicesController;

/**
 *
 * @author ajperalt
 */
public class Lab4uDesktopAppController implements Initializable {

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private TreeView treeViewDevices;
    private TreeViewDevicesController treeViewDevicesController;
    
    /**
     * Handle action related to "About" menu item.
     *
     * @param event Event on "About" menu item.
     */
    @FXML
    private void handleAboutAction(final ActionEvent event) {
        provideAboutFunctionality();
    }

    /**
     * Handle action related to input (in this case specifically only responds
     * to keyboard event CTRL-A).
     *
     * @param event Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                provideAboutFunctionality();
            }
        }
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void handlebtnSearchDevicesAction(final ActionEvent event){
        this.getTreeViewDevicesController().refreshDevices();
    }
    
    
    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality() {
        System.out.println("You clicked on About!");
    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        menuBar.setFocusTraversable(true);

    }
    
    private TreeViewDevicesController getTreeViewDevicesController(){
        if(treeViewDevicesController == null){
            treeViewDevicesController = new TreeViewDevicesController(treeViewDevices);
        }
        return treeViewDevicesController;
    }
}
