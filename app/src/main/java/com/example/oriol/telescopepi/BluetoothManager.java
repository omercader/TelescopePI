package com.example.oriol.telescopepi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothManager extends Thread {

    private BluetoothDevice device = null;
    public Set<BluetoothDevice> pairedDevices = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothSocket socket = null;
    private Handler mHandler; // handler that gets info from Bluetooth service
    private static final String TAG = "MY_APP_DEBUG_TAG";

    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }

    public void BluetoothManager(){

         mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        pairedDevices = mBluetoothAdapter.getBondedDevices();

    }

    public void connect(String deviceName){
        for (BluetoothDevice dev:pairedDevices) {
            if(dev.getName().equals(deviceName)){
                device = dev;

            }
        }

        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
            socket.connect();

            //NOW start the thread in order to aquire or send info
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(){
        //Keep listening inputStream until exception occurs

        byte[] mmBuffer = new byte[1024];
        int numBytes; // bytes returned from read()

        while (true) {
            try {
                // Read from the InputStream.
                numBytes = socket.getInputStream().read(mmBuffer);
                // Send the obtained bytes to the UI activity.
                Message readMsg = mHandler.obtainMessage(
                        MessageConstants.MESSAGE_READ, numBytes, -1,
                        mmBuffer);
                readMsg.sendToTarget();
            } catch (IOException e) {
                Log.d(TAG, "Input stream was disconnected", e);
                break;
            }
        }

    }

    // Call this from the main activity to send data to the remote device.
    public void write(byte[] bytes) {
        try {
            socket.getOutputStream().write(bytes);

            byte[] mmBuffer = new byte[1024];
            // Share the sent message with the UI activity.
            Message writtenMsg = mHandler.obtainMessage(
                    MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
            writtenMsg.sendToTarget();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);

            // Send a failure message back to the activity.
            Message writeErrorMsg =
                    mHandler.obtainMessage(MessageConstants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString("toast",
                    "Couldn't send data to the other device");
            writeErrorMsg.setData(bundle);
            mHandler.sendMessage(writeErrorMsg);
        }
    }

    // Call this method from the main activity to shut down the connection.
    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}
