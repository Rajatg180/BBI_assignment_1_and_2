package src.test.java;

import Classes.SmartThermostat;
import org.junit.Test;
import static org.junit.Assert.*;

public class SmartThermostatTest {

    @Test
    public void testPowerControl() {
        SmartThermostat thermostat = new SmartThermostat();

        
        assertFalse("SmartThermostat should be OFF initially.", thermostat.powerOn);

        
        thermostat.turnOn();
        assertTrue("SmartThermostat should be ON after turning it on.", thermostat.powerOn);

        
        thermostat.turnOff();
        assertFalse("SmartThermostat should be OFF after turning it off.", thermostat.powerOn);
    }

    @Test
    public void testWiFiConnection() {
        SmartThermostat thermostat = new SmartThermostat();

        
        thermostat.connectToWiFi("rajat-wifi");
        assertFalse("SmartThermostat should not connect to WiFi when OFF.", thermostat.powerOn);

    
        thermostat.turnOn();
        thermostat.connectToWiFi("rajat-wifi");
        assertTrue("SmartThermostat should connect to WiFi when ON.", thermostat.powerOn);
    }

    @Test
    public void testTemperatureControl() {

        SmartThermostat thermostat = new SmartThermostat();

        
        thermostat.setTemperature(24);
        assertEquals("Temperature should remain 0 when the thermostat is OFF.", 0, thermostat.temperature);

        
        thermostat.turnOn();
        thermostat.setTemperature(24);
        assertEquals("Temperature should remain 0 without WiFi connection.", 0, thermostat.temperature);

        
        thermostat.connectToWiFi("rajat-wifi");
        thermostat.setTemperature(24);
        assertEquals("Temperature should be set to 24.", 24, thermostat.temperature);

        thermostat.setTemperature(35);
        assertEquals("Temperature should remain 24 after setting an invalid value.", 24, thermostat.temperature);
    }

    @Test
    public void testGetTemperature() {

        SmartThermostat thermostat = new SmartThermostat();

    
        thermostat.getTemperature();
        assertEquals("Temperature should be 0 when the thermostat is OFF.", 0, thermostat.temperature);

    
        thermostat.turnOn();
        thermostat.connectToWiFi("rajat-wifi");
        thermostat.setTemperature(20);

        thermostat.getTemperature();
        assertEquals("Temperature should be 20.", 20, thermostat.temperature);
    }

}
