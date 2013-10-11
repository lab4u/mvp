/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app.view.c;

import java.util.List;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lab4u.desktop.app.bussines.Lab4uBC;
import lab4u.desktop.app.model.ifaces.ILab4uDevices;
import lab4u.desktop.app.view.m.RootItemDevices;

/**
 *
 * @author ajperalt
 */
public final class TreeViewDevicesController {
    private TreeView treeViewDevices;

    public TreeViewDevicesController(TreeView treeViewDevices) {
       this.treeViewDevices = treeViewDevices;
    }

    public void refreshDevices() {
        List<ILab4uDevices> listDevices =  Lab4uBC.getInstance().sendSearchDevicesEvent();
        TreeItem<ILab4uDevices> rootItem = new TreeItem<ILab4uDevices> (new RootItemDevices());
        rootItem.setExpanded(true);
        for (ILab4uDevices device : listDevices) {
            TreeItem<ILab4uDevices> item = new TreeItem<ILab4uDevices> (device);            
            rootItem.getChildren().add(item);
        }        
        
        treeViewDevices.setRoot(rootItem);
    }

    
}
