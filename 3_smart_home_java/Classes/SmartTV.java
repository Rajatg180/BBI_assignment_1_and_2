package Classes;

import Interface.AudioControl;
import Interface.NetworkConnected;
import Interface.PowerControl;
import Interface.TemperatureControl;

public class SmartTV implements PowerControl, NetworkConnected, TemperatureControl, AudioControl {

    public boolean powerOn=false;
    public boolean connected=false;
    public int temperature=0;
    public  int volume=0;
    public boolean isMuted=false;


    @Override
    public void turnOn() {
        powerOn = true;
        System.out.println("SmartTV is now ON.");
    }

    @Override
    public void turnOff() {
        powerOn = false;
        System.out.println("SmartTV is now OFF.");
    }

    @Override
    public void connectToWiFi(String networkName) {
        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before connecting to WiFi.");
            return;
        }
        connected = true;
        System.out.println("SmartTV connected to WiFi: " + networkName);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setTemperature(int temperature) {
        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before setting the temperature.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartTV is not connected to wifi. Connect to first");
            return;
        }

        if (temperature < 16 || temperature > 30) {
            System.out.println("Invalid temperature. Please set a temperature between 16 and 30 degrees.");
            return;
        }
        this.temperature = temperature;
        System.out.println("SmartTV temperature set to " + temperature + " degrees.");
    }

    @Override
    public void getTemperature() {
        
        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before setting the temperature.");
            return;
        }

        System.out.println("The temperature of SmartTV is: " + this.temperature + " degrees.");
    }

    @Override
    public void setVolume(int volume) {
        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before setting the volume.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartTV is not connected to wifi. Connect to wifi first");
            return;
        }

        if (volume < 0 || volume > 100) {
            System.out.println("Invalid volume. Please set the volume between 0 and 100.");
            return;
        }
        this.volume = volume;
        System.out.println("SmartTV volume set to " + volume + ".");
    }

    @Override
    public void mute() {

        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before muting.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartTV is not connected to wifi. Connect to wifi first");
            return;
        }

        this.isMuted = true;

        System.out.println("SmartTV is muted.");

    }

    @Override
    public void unMute() {

        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before muting.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartTV is not connected to wifi. Connect to wifi first");
            return;
        }

        this.isMuted = false;

        System.out.println("SmartTV is muted.");
        
    }

    @Override
    public Object getVolume() {

        if (!powerOn) {
            System.out.println("SmartTV is OFF. Please turn it on before muting.");
            return null;
        }

        if(!isConnected()){
            System.out.println("SmartTV is not connected to wifi. Connect to wifi first");
            return null;
        }

        System.out.println("The Volume of SmartTv is : "+ this.volume);

        return null;
    }
    
}
