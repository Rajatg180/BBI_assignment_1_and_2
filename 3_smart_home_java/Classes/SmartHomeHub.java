package Classes;

import Interface.AudioControl;
import Interface.NetworkConnected;
import Interface.PowerControl;
import Interface.TemperatureControl;

public class SmartHomeHub {

    public void controlPower(PowerControl device, boolean turnOn) {
        if (turnOn) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }

    public void connectToWiFi(NetworkConnected device, String networkName) {

        device.connectToWiFi(networkName);
    }

    public void setTemperature(TemperatureControl device, int temperature) {

        device.setTemperature(temperature);
    }

    public void getTemperature(TemperatureControl device) {

        device.getTemperature();
    }

    public void setVolume(AudioControl device, int volume) {

        device.setVolume(volume);
    }

    public void mute(AudioControl device) {

        device.mute();
    }

    public void unMute(AudioControl device) {

        device.unMute();
    }

    public void getVolume(AudioControl device) {

        device.getVolume();
    }
}
