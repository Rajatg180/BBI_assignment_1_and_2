package src.test.java;

import Classes.SmartSpeaker;
import org.junit.Test;
import static org.junit.Assert.*;

public class SmartSpeakerTest {

    @Test
    public void testPowerControl() {

        SmartSpeaker speaker = new SmartSpeaker();

        
        assertFalse("SmartSpeaker should be OFF initially.", speaker.getPowerStatus());

        
        speaker.turnOn();
        assertTrue("SmartSpeaker should be ON.", speaker.getPowerStatus());

        
        speaker.turnOff();
        assertFalse("SmartSpeaker should be OFF.", speaker.getPowerStatus());
    }

    @Test
    public void testWiFiConnection() {
        SmartSpeaker speaker = new SmartSpeaker();

    
        assertFalse("SmartSpeaker should not connect to WiFi when OFF.", speaker.getConnection());

       
        speaker.turnOn();

    
        speaker.connectToWiFi("rajat-wifi");
        assertTrue("SmartSpeaker should connect to WiFi when ON.", speaker.getConnection());
    }

    @Test
    public void testVolumeControl() {

        SmartSpeaker speaker = new SmartSpeaker();

        
        assertEquals("Volume should be -1 when speaker is OFF.", -1, speaker.getVolumeStatus());

        
        speaker.turnOn();

        
        assertEquals("Volume should be -2 when speaker is not connected to WiFi.", -2, speaker.getVolumeStatus());

    
        speaker.connectToWiFi("rajat-wifi");

        
        speaker.setVolume(50);
        assertEquals("Volume should be set to 50.", 50, speaker.getVolumeStatus());

        
        speaker.setVolume(110);
        assertEquals("Volume should remain 50 after an invalid volume setting.", 50, speaker.getVolumeStatus());

        speaker.setVolume(-10);
        assertEquals("Volume should remain 50 after an invalid volume setting.", 50, speaker.getVolumeStatus());
        
    }

    @Test
    public void testMuteAndUnmute() {

        SmartSpeaker speaker = new SmartSpeaker();

        assertEquals("Volume should be -1 when speaker is OFF.", false, speaker.getMutedStatus());

        
        speaker.turnOn();
        assertEquals("Volume should be -2 when speaker is not connected to WiFi.", false, speaker.getMutedStatus());
        

        
        speaker.turnOn();
        speaker.connectToWiFi("rajat-wifi");

        
        speaker.mute();
        assertTrue("SmartSpeaker should be muted.", speaker.getMutedStatus());

    
        speaker.unMute();
        assertFalse("SmartSpeaker should be unmuted.", speaker.getMutedStatus());
    }

}
