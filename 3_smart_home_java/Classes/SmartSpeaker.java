package Classes;

import Interface.AudioControl;
import Interface.NetworkConnected;
import Interface.PowerControl;

public class SmartSpeaker implements PowerControl, NetworkConnected, AudioControl {

    private boolean powerOn=false;
    private boolean connected=false;
    private int volume=0;
    private boolean isMuted=false;


    public boolean getPowerStatus(){
        return this.powerOn;
    }

    public boolean getConnection(){

        if(!powerOn){
            return false;
        }

        return this.connected;
    }

    public int getVolumeStatus(){

        if(!powerOn){
            return -1;
        }

        if(!connected){
            return -2;
        }

        return this.volume;

    }


    public boolean getMutedStatus(){

        if(!powerOn){
            return false;
        }

        if(!connected){
            return false;
        }

        
        return this.isMuted;
    }


    @Override
    public void turnOn() {
        powerOn = true;
        System.out.println("SmartSpeaker is now ON.");
    }

    @Override
    public void turnOff() {
        powerOn = false;
        System.out.println("SmartSpeaker is now OFF.");
    }

    @Override
    public void connectToWiFi(String networkName) {
        if (!powerOn) {
            System.out.println("SmartSpeaker is OFF. Please turn it on before connecting to WiFi.");
            return;
        }
        connected = true;
        System.out.println("SmartSpeaker connected to WiFi: " + networkName);
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setVolume(int volume) {
        if (!powerOn) {
            System.out.println("SmartSpeaker is OFF. Please turn it on before setting the volume.");
            return;
        }

        if (!isConnected()) {
            System.out.println("SmartSpeaker is not connected to WiFi. Connect to WiFi first.");
            return;
        }

        if (volume < 0 || volume > 100) {
            System.out.println("Invalid volume. Please set the volume between 0 and 100.");
            return;
        }
        
        this.volume = volume;

        System.out.println("SmartSpeaker volume set to " + volume + ".");
    }

    @Override
    public void mute() {
        if (!powerOn) {
            System.out.println("SmartSpeaker is OFF. Please turn it on before muting.");
            return;
        }

        if (!isConnected()) {
            System.out.println("SmartSpeaker is not connected to WiFi. Connect to WiFi first.");
            return;
        }

        this.isMuted = true;
        System.out.println("SmartSpeaker is muted.");
    }

    @Override
    public void unMute() {
        if (!powerOn) {
            System.out.println("SmartSpeaker is OFF. Please turn it on before unmuting.");
            return;
        }

        if (!isConnected()) {
            System.out.println("SmartSpeaker is not connected to WiFi. Connect to WiFi first.");
            return;
        }

        this.isMuted = false;
        System.out.println("SmartSpeaker is unmuted.");
    }

    @Override
    public Object getVolume() {

        if (!powerOn) {
            System.out.println("SmartSpeaker is OFF. Please turn it on before checking the volume.");
            return 0;
        }

        if (!isConnected()) {
            System.out.println("SmartSpeaker is not connected to WiFi. Connect to WiFi first.");
            return 0;
        }

        System.out.println("The current volume of the SmartSpeaker is: " + this.volume);

        return this.volume;

    }
}
