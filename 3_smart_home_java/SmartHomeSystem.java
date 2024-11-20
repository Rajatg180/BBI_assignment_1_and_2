import java.util.Scanner;

import Classes.SmartHomeHub;
import Classes.SmartSpeaker;
import Classes.SmartTV;
import Classes.SmartThermostat;
import Interface.AudioControl;
import Interface.NetworkConnected;
import Interface.PowerControl;
import Interface.TemperatureControl;

public class SmartHomeSystem {

    public static void main(String[] args) {

        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTV tv = new SmartTV();
        
        SmartHomeHub hub = new SmartHomeHub();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Smart Home System ---");
            System.out.println("1. Select Device");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int deviceChoice = scanner.nextInt();

            if (deviceChoice == 2) {
                System.out.println("Exiting Smart Home System...");
                break;
            }

            System.out.println("Choose device for control:");
            System.out.println("1. Thermostat");
            System.out.println("2. Speaker");
            System.out.println("3. TV");
            System.out.print("Enter choice: ");
            int selectedDevice = scanner.nextInt();

            Object selected = null;

            switch (selectedDevice) {
                case 1:
                    selected = thermostat;
                    break;
                case 2:
                    selected = speaker;
                    break;
                case 3:
                    selected = tv;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            if (selected != null) {
                while (true) {
                    System.out.println("\n--- Actions for Selected Device ---");
                    if (selected instanceof PowerControl) {
                        System.out.println("1. Turn Power ON/OFF");
                    }
                    if (selected instanceof NetworkConnected) {
                        System.out.println("2. Connect to WiFi");
                    }
                    if (selected instanceof TemperatureControl) {
                        System.out.println("3. Set Temperature");
                        System.out.println("6. Get Temperature");
                    }
                    if (selected instanceof AudioControl) {
                        System.out.println("4. Set Volume");
                        System.out.println("5. Mute/Unmute Volume");
                        System.out.println("8. Get Volume");
                    }
                    System.out.println("7. Go Back to Device Selection");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            if (selected instanceof PowerControl) {
                                System.out.print("Enter 1 to turn ON, 0 to turn OFF: ");
                                int powerState = scanner.nextInt();
                                hub.controlPower((PowerControl) selected, powerState == 1);
                            } else {
                                System.out.println("This device cannot control power.");
                            }
                            break;

                        case 2:
                            if (selected instanceof NetworkConnected) {
                                System.out.print("Enter WiFi network name: ");
                                String networkName = scanner.next();
                                hub.connectToWiFi((NetworkConnected) selected, networkName);
                            } else {
                                System.out.println("This device does not support WiFi connection.");
                            }
                            break;

                        case 3:
                            if (selected instanceof TemperatureControl) {
                                System.out.print("Enter temperature value: ");
                                int tempValue = scanner.nextInt();
                                hub.setTemperature((TemperatureControl) selected, tempValue);
                            } else {
                                System.out.println("This device does not support temperature control.");
                            }
                            break;

                        case 4:
                            if (selected instanceof AudioControl) {
                                System.out.print("Enter volume value (0-100): ");
                                int volumeValue = scanner.nextInt();
                                hub.setVolume((AudioControl) selected, volumeValue);
                            } else {
                                System.out.println("This device does not support volume control.");
                            }
                            break;

                        case 5:
                            if (selected instanceof AudioControl) {
                                System.out.print("Enter 1 to Mute, 0 to Unmute: ");
                                int muteChoice = scanner.nextInt();
                                if (muteChoice == 1) {
                                    hub.mute((AudioControl) selected);
                                } else if (muteChoice == 0) {
                                    hub.unMute((AudioControl) selected);
                                } else {
                                    System.out.println("Invalid input for mute/unmute.");
                                }
                            } else {
                                System.out.println("This device does not support muting.");
                            }
                            break;

                        case 6:
                            if (selected instanceof TemperatureControl) {
                                hub.getTemperature((TemperatureControl) selected);
                            } else {
                                System.out.println("This device does not support getting temperature.");
                            }
                            break;

                        case 8:
                            if (selected instanceof AudioControl) {
                                hub.getVolume((AudioControl) selected);
                            } else {
                                System.out.println("This device does not support volume control.");
                            }
                            break;

                        case 7:
                            System.out.println("Going back to device selection...");
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    if (choice == 7) {
                        break;
                    }
                }
            }
        }

        scanner.close();
    }
}
