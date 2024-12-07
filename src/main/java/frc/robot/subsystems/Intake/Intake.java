package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private IntakeIO io;
    private IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    public Intake(IntakeIO intakeIO) {
        this.io = intakeIO; // Notes by Cole - io variable is set to intakeIO
    }

    @Override
    public void periodic() { // Notes by Cole - every 20 ms, log intake
        Logger.processInputs("Intake", inputs); // Notes by Cole - the framework for advantageScope,
                                                // named "Intake"
        io.updateInputs(inputs); // Notes by Cole - inputs are updated every 20 ms
    }

    public void setPower(double power) {
        Logger.recordOutput("Intake/power", power); // Notes by Cole - power is logged, under
                                                    // "Intake/power"
        io.setPower(power); // Notes by Cole - io is set to power
    }

    public boolean getIntake() {
        return inputs.intake; // Notes by Cole - returns the value of intake
    }

    public boolean getOuttake() {
        return inputs.outtake; // Notes by Cole - returns the value of outtake
    }


    public Command intake() {
        return run(() -> {
            setPower(1);
        }).until(() -> inputs.intake).withTimeout(0); // Notes by Cole - this is the intake, the
                                                      // motors spin inward
    }


    public Command shoot() {
        return run(() -> {
            setPower(1);
        }).withTimeout(0); // Notes by Cole - this is the outake, shoots the ball out of the intake,
                           // the motors spin outward
    }

    public Command move() {
        return run(() -> {
            setPower(1);
        }).unless(() -> inputs.outtake).withTimeout(0); // Notes by Cole - same as intake, just
                                                        // difrent way to do it
    }
}
