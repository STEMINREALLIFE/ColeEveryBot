package frc.robot.subsystems.Intake;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class IntakeReal implements IntakeIO {

    private final SparkMax intakeMotor =
        new SparkMax(Constants.Intake.INTAKEMOTOR, MotorType.kBrushless); // Notes by Cole -
                                                                          // intake motor is
                                                                          // defined, id is form
                                                                          // constants
    private final DigitalInput intakeBeamBreak = new DigitalInput(Constants.Intake.BEAMBREAK1); // Notes
                                                                                                // by
                                                                                                // Cole
                                                                                                // -
                                                                                                // beambreaks
                                                                                                // are
                                                                                                // defined,
                                                                                                // ids
                                                                                                // are
                                                                                                // gotten
                                                                                                // from
                                                                                                // constants
    private final DigitalInput outtakeBeamBreak = new DigitalInput(Constants.Intake.BEAMBREAK2);

    public IntakeReal() {}

    @Override


    public void setPower(double power) {
        intakeMotor.set(power); // Notes by Cole - intake motor power is set
    }


    public void periodic() {} // Notes by Cole - every 20 ms

    public void updateInputs(IntakeIOInputs inputs) {
        inputs.intake = intakeBeamBreak.get(); // Notes by Cole - both beam breaks triggerd (true or
                                               // false) is updated every 20 ms
        inputs.outtake = outtakeBeamBreak.get();
    }
}
