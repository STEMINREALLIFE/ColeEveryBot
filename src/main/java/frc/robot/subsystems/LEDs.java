package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
    AddressableLED leds; // Notes by Cole - variable leds is created
    AddressableLEDBuffer buffer; // Notes by Cole - variable buffer is created

    public LEDs(int portNum, int length) {
        this.leds = new AddressableLED(portNum); // Notes by Cole - assigns led to port number
                                                 // "portNum"
        this.buffer = new AddressableLEDBuffer(length); // Notes by Cole - assigns buffer to led
                                                        // buffer "length"
        leds.setLength(buffer.getLength()); // Notes by Cole - sets led length to buffer length
        leds.setData(buffer); // Notes by Cole - sets led data to buffer
        leds.start(); // Notes by Cole - initiates leds
    }

    public void setColor(Color colors) { // Notes by Cole - sets leds to desired color
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, colors);
        }
        leds.setData(buffer);
    }

    public Command setAllianceColor() { // Notes by Cole - command to change color

        return Commands.run(() -> {
            Color color = Color.kGreen; // Notes by Cole - color object instantiated led green if no
                                        // alliance present
            if (DriverStation.getAlliance().isPresent()) { // Notes by Cole - if alliance present
                if (DriverStation.getAlliance().get() == Alliance.Red) { // Notes by Cole - if
                                                                         // alliance red
                    color = Color.kRed; // Notes by Cole - set color to red
                } else {
                    color = Color.kBlue; // Notes by Cole - set color to blue
                }
            }
            setColor(color);// Notes by Cole - use method setColor to set the leds to the desired
                            // color

        }, this);
    }

    public Command call() {
        Color callClr = Color.kDarkBlue; // Notes by Cole - object callClr is created and defined as
                                         // dark blue
        return Commands.run(() -> setColor(callClr)); // Notes by Cole - sets leds to dark blue
                                                      // color
    }

    public Command hatchTrue() {
        Color clr = Color.kLavender;// Notes by Cole - obejct clr is created and defiend as lavendar
                                    // color
        return Commands.run(() -> setColor(clr)); // Notes by Cole - if touch sensor is true on
                                                  // hatch, change lights to lavender color
    }
}
