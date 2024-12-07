package frc.robot.subsystems.Hatch;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Hatch extends SubsystemBase {
    public HatchIO io; // Notes by Cole - hatchIO is created
    public HatchIOInputsAutoLogged inputs = new HatchIOInputsAutoLogged(); // Notes by Cole - new
                                                                           // object of
                                                                           // HatchIOInputsAutoLogged
                                                                           // is creased called
                                                                           // inputs

    public Hatch(HatchIO io, CommandXboxController operator) {
        this.io = io; // Notes by Cole - this io is defined as io
        io.updateInputs(inputs); // Notes by Cole - inputs for autologging are updated
    }

    @Override
    public void periodic() { // Notes by Cole - every 20 ms
        io.updateInputs(inputs); // Notes by Cole - input object is updated
        Logger.processInputs("hatch", inputs); // Notes by Cole - framework/tab for advantageScope,
                                               // named "hatch", which takes the inputs of hatch.

        Rotation2d currentHatchAngle = Rotation2d.fromRotations(inputs.hatchAbsoluteENCRawValue); // Notes
                                                                                                  // by
                                                                                                  // Cole
                                                                                                  // -
                                                                                                  // assigns
                                                                                                  // inputs.hatchAbsoluteENCRawValue
                                                                                                  // the
                                                                                                  // current
                                                                                                  // hatch
                                                                                                  // angle
        double pid = hatchPIDController.calculate(currentHatchAngle.getDegrees()); // Notes by Cole
                                                                                   // - defines pid
                                                                                   // as the double
                                                                                   // version of the
                                                                                   // current hatch
                                                                                   // angle in
                                                                                   // degrees
        io.setVolatge(pid); // Notes by Cole - sets the voltage to pid
    }

    public boolean getTouch() {
        return inputs.touchSensor; // Notes by Cole - gets the touch sensor
    }

    PIDController hatchPIDController = new PIDController(Constants.Hatch.HATCH_KP,
        Constants.Hatch.HATCH_KI, Constants.Hatch.HATCH_KD); // Notes by Cole - creates the
                                                             // hatchPIDController obejct of
                                                             // PIDController and assigns it
                                                             // variables (1.0) from constants


    public Rotation2d getHatchAngleMesuremet() {
        return Rotation2d.fromRotations(
            Constants.Hatch.HATCH_M * inputs.hatchAbsoluteENCRawValue + Constants.Hatch.HATCH_B); // Notes
                                                                                                  // by
                                                                                                  // Cole
                                                                                                  // -
                                                                                                  // calculates
                                                                                                  // the
                                                                                                  // wrist
                                                                                                  // angle
                                                                                                  // and
                                                                                                  // returns
                                                                                                  // it
    }

    public Rotation2d getHatchAngle() {
        return Rotation2d.fromRotations(inputs.hatchAbsoluteENCRawValue); // Notes by Cole - returns
                                                                          // the hatch angle using
                                                                          // the raw absolute
                                                                          // encoder value
    }


    public Command setHatchAngle(Rotation2d angle) { // Notes by Cole - gets the wrist set point
        return Commands.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getDegrees());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }


    public Command homePosition() { // Notes by Cole - moves teh wrist back to the home postion
        Command checkHome = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goHome = goToPosition(frc.robot.Constants.Hatch.HATCH_HOME).withTimeout(1);
        return checkHome.andThen(goHome);
    }


    public Command goToPosition(Rotation2d angle) { // Notes by Cole - sets the hatch wrist at a
                                                    // certant angle using set points
        return Commands.runOnce(() -> {
            hatchPIDController.reset();
            hatchPIDController.setSetpoint(angle.getRotations());
        }).andThen(Commands.waitUntil(() -> atGoal()));
    }


    public Command intake() { // Notes by Cole - moves the wrist up in the intake position
        Command checkIntake = (goToPosition(Rotation2d.fromDegrees(1))
            .until(() -> getHatchAngle().getDegrees() > 1).withTimeout(1));
        Command goToIntake = (goToPosition(Constants.Hatch.INTAKE_POSITON).withTimeout(1));
        return checkIntake.andThen(goToIntake);
    }


    public Boolean atGoal() { // Notes by Cole - checks if the wrist is at the desired set point
        return hatchPIDController.atSetpoint();
    }

    public boolean touchSensorStatus() {
        return inputs.touchSensor; // Notes by Cole - returns the status of the touch sensor
    }
}
