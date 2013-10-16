package com.lab4u.sensors.notifider;

import java.io.Serializable;

/**
 * Created by ajperalt on 28/09/13.
 */
public interface ILab4uNotifider {

    public void addInfo(Serializable obj);

    public void sendInfo();

}
