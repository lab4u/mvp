/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app.view.m;

import lab4u.desktop.app.model.ifaces.ILab4uDevices;

/**
 *
 * @author ajperalt
 */
public class RootItemDevices implements ILab4uDevices{

    @Override
    public String getName() {
        return "Devices";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
