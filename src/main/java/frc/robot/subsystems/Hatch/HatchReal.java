package frc.robot.subsystems.Hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class HatchReal implements HatchIO {
    /** initilize motors and sensors */
    private CANSparkMax hatchMotor =
        new CANSparkMax(Constants.Hatch.HATCHMOTOR, MotorType.kBrushless); // Notes by Cole - motor
                                                                           // is defined, id got
                                                                           // from constants
    private DigitalInput touchSensor = new DigitalInput(Constants.Hatch.TOUCHSENSOR); // Notes by
                                                                                      // Cole -
                                                                                      // touch
                                                                                      // sensor
                                                                                      // defined, id
                                                                                      // from
                                                                                      // constants
    private CANcoder hatchCancoder = new CANcoder(Constants.Hatch.CANCODER);// Notes by Cole -
                                                                            // cancoder defined, id
                                                                            // from constants
    private AbsoluteEncoder hatchWristEnc = hatchMotor.getAbsoluteEncoder(); // Notes by Cole -
                                                                             // absolute encoder
                                                                             // defined



    public HatchReal() {
        hatchWristEnc.setPositionConversionFactor(1); // Notes by Cole - sets position conversion
                                                      // factor for encoder to 1
        hatchMotor.setIdleMode(IdleMode.kBrake); // Notes by Cole - stops motor
    }

    @Override
    /** sets voltage */
    public void setVolatge(double v) {
        hatchMotor.setVoltage(v); // Notes by Cole - voltage for the motor is set
    }

    public void updateInputs(HatchIOInputs inputs) {
        // inputs.positon = hatchCancoder.getPosition();
        inputs.hatchAbsoluteENCRawValue = hatchCancoder.getPosition().getValueAsDouble(); // Notes
                                                                                          // by Cole
                                                                                          // - uses
                                                                                          // encoder
                                                                                          // to get
                                                                                          // position
                                                                                          // of
                                                                                          // motor
        inputs.touchSensor = touchSensor.get(); // Notes by Cole - gets the value of touchSensor
    }
}
