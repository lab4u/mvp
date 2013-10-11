/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app.model;

import lab4u.desktop.app.model.ifaces.ILab4uDevices;

/**
 *
 * @author ajperalt
 */
public class Lab4uGenericDevices implements ILab4uDevices{
    private String name;

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
