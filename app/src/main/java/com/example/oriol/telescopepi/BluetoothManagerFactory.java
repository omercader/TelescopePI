package com.example.oriol.telescopepi;

public class BluetoothManagerFactory {

    public static BluetoothManager manager = null;

    public static BluetoothManager getManager(){

        if(manager != null){
            return manager;
        } else{

            manager = new BluetoothManager();
            return manager;
        }

    }

}
