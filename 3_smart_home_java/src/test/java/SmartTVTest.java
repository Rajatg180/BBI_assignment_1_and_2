package src.test.java;

import Classes.SmartTV;
import org.junit.Test;
import static org.junit.Assert.*;

public class SmartTVTest {

    @Test
    public void testPowerControl() {

        SmartTV tv = new SmartTV();


        assertFalse("SmartTV should be OFF initially.", tv.powerOn);


        tv.turnOn();
        assertTrue("SmartTV should be ON.", tv.powerOn);


        tv.turnOff();
        assertFalse("SmartTV should be OFF.", tv.powerOn);
    }


    @Test
    public void testWiFiConnection() {

        SmartTV tv = new SmartTV();


        tv.connectToWiFi("rajat-wifi");
        assertFalse("SmartTV should not connect to WiFi when OFF.", tv.connected);

        tv.turnOn();
        tv.connectToWiFi("rajat-wifi");
        assertTrue("SmartTV should connect to WiFi when ON.", tv.connected);

    }

    @Test
    public void testTemperatureControl() {

        SmartTV tv = new SmartTV();


        tv.setTemperature(24);
        assertEquals("Temperature should not change when the TV is OFF.", 0, tv.temperature);


        tv.turnOn();
        tv.setTemperature(24);
        assertEquals("Temperature should not change without WiFi connection.", 0, tv.temperature);

        tv.connectToWiFi("rajat-wifi");
        tv.setTemperature(24);
        assertEquals("Temperature should be set to 24.", 24, tv.temperature);


        tv.setTemperature(35);
        assertEquals("Temperature should remain 24 after setting an invalid value.", 24, tv.temperature);
    }

    @Test
    public void testVolumeControl() {

        SmartTV tv = new SmartTV();

        
        tv.setVolume(50);
        assertEquals("Volume should not change when the TV is OFF.", 0, tv.volume);

       
        tv.turnOn();
        tv.setVolume(50);
        assertEquals("Volume should not change without WiFi connection.", 0, tv.volume);

        
        tv.connectToWiFi("rajat-wifi");
        tv.setVolume(50);
        assertEquals("Volume should be set to 50.", 50, tv.volume);

        
        tv.setVolume(120); // bcoz the range is between 0 to 120 
        assertEquals("Volume should remain 50 after setting an invalid value.", 50,tv.volume);
    }

    @Test
    public void testMuteAndUnmute() {

        SmartTV tv = new SmartTV();

        
        tv.mute();
        assertFalse("SmartTV should not be muted when OFF.", tv.isMuted);

        
        tv.turnOn();
        tv.connectToWiFi("rajat-wifi");
        assertFalse("SmartTV should not be muted  without WiFi connection.", tv.isMuted);



        tv.mute();
        assertTrue("SmartTV should be muted.", tv.isMuted);

        
        tv.unMute();
        assertFalse("SmartTV should be unmuted.", tv.isMuted);
    }

}
