package com.lab4u.sensors.persistence;

/**
 * Created by ajperalt on 11/10/13.
 */
public final class Lab4uSensorEmptyPersistence implements ILab4uSensorPersistence {

    public static Lab4uSensorEmptyPersistence instance = null;

    private Lab4uSensorEmptyPersistence(){

    }

    public static Lab4uSensorEmptyPersistence getInstance(){
        if(instance == null){
            instance = new Lab4uSensorEmptyPersistence();
        }
        return instance;
    }
    @Override
    public void print(float[] values) {

    }

    @Override
    public void close() {

    }
}
