/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app.view.c;

import java.util.List;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lab4u.desktop.app.bussines.ILab4uBussinesListener;
import lab4u.desktop.app.bussines.Lab4uBC;
import lab4u.desktop.app.model.ifaces.ILab4uDevices;
import lab4u.desktop.app.view.m.RootItemDevices;

/**
 *
 * @author ajperalt
 */
public final class TreeViewDevicesController implements ILab4uBussinesListener{
    private TreeView treeViewDevices;

    public TreeViewDevicesController(TreeView treeViewDevices) {
       this.treeViewDevices = treeViewDevices;
    }

    public void refreshDevices() {
        Lab4uBC.getInstance().sendSearchDevicesEvent(this);   
    }

    @Override
    public void update(Object obj) {
        List<ILab4uDevices> listDevices =  (List<ILab4uDevices>)obj;
        TreeItem<ILab4uDevices> rootItem = new TreeItem<ILab4uDevices> (new RootItemDevices());
        rootItem.setExpanded(true);
        for (ILab4uDevices device : listDevices) {
            TreeItem<ILab4uDevices> item = new TreeItem<ILab4uDevices> (device);            
            rootItem.getChildren().add(item);
        }              
        treeViewDevices.setRoot(rootItem);
    }

    
}
