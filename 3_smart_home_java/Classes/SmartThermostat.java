package Classes;

import Interface.NetworkConnected;
import Interface.PowerControl;
import Interface.TemperatureControl;

public class SmartThermostat implements PowerControl, NetworkConnected, TemperatureControl {

    public boolean powerOn=false;
    public  boolean connected=false;
    public int temperature=0;


    @Override
    public void turnOn() {
        powerOn = true;
        System.out.println("SmartThermostat is now ON.");
    }


    @Override
    public void turnOff() {
        powerOn = false;
        System.out.println("SmartThermostat is now OFF.");
    }

    @Override
    public void connectToWiFi(String networkName) {

        if (!powerOn) {
            System.out.println("SmartThermostat is OFF. Please turn it on before connecting to WiFi.");
            return;
        }

        connected = true;

        System.out.println("SmartThermostat connected to WiFi: " + networkName);
    }


    @Override
    public boolean isConnected() {
        return connected;
    }
    

    @Override
    public void setTemperature(int temperature) {
        if (!powerOn) {
            System.out.println("SmartThermostat is OFF. Please turn it on before setting the temperature.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartThermostat is not connted to wifi.");
            return;
        }

        if (temperature < 16 || temperature > 30) {
            System.out.println("Invalid temperature. Please set a temperature between 16 and 30 degrees.");
            return;
        }
        this.temperature = temperature;
        System.out.println("SmartThermostat temperature set to " + temperature + " degrees.");
    }

    @Override
    public void getTemperature() {

        if (!powerOn) {
            System.out.println("SmartThermostat is OFF. Please turn it on before setting the temperature.");
            return;
        }

        if(!isConnected()){
            System.out.println("SmartThermostat is not connted to wifi.");
            return;
        }

        System.out.println("The temperature of SmartThermostat is: " + this.temperature + " degrees.");
    }
}
