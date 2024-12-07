package frc.robot.subsystems.Drive;


import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Drive extends SubsystemBase {
    private DriveIO io; // Notes by Cole - creates a variabel named "io"
    private DriveIOInputsAutoLogged inputs = new DriveIOInputsAutoLogged(); // Notes by Cole - new
                                                                            // DriveIOInputsAutoLogged
                                                                            // object called
                                                                            // "inputs"

    public Drive(DriveIO driveIO) {
        this.io = driveIO; // Notes by Cole - io is set to driveIO
    }

    @Override
    public void periodic() { // Notes by Cole - this is the part of the code that continuously loggs
                             // data every 20 miliseconds for the drive train
        Logger.processInputs("Drive", inputs); // Notes by Cole - The framework for advatangeScope,
                                               // named "Drive"
        io.updateInputs(inputs); // Notes by Cole - inputs are updated

    }

    public void setPower(double leftPower, double rightPower) { // Notes by Cole - in tank, 2 motors
                                                                // go at once, either the left side
                                                                // or the right side, or both. Left
                                                                // stick is left 2 motors, right
                                                                // stick is right 2 motors
        Logger.recordOutput("Drive/leftPower", leftPower); // Notes by Cole - Shows the set power of
                                                           // the left motors, in advantage scope
                                                           // called "Drive/leftPower"
        Logger.recordOutput("Drive/rightPower", rightPower); // Notes by Cole - Showsd the set power
                                                             // of the right motors, in advatange
                                                             // scope called "Drive/rightPower"
        io.setPower(leftPower, rightPower); // Notes by Cole - io.setPower is set to the paramaters
                                            // in setPower
    }

    public Command driveCMD(CommandXboxController driver) {
        return run(() -> {
            setPower(driver.getLeftY(), driver.getRightY()); // Notes by Cole - acesses the
                                                             // y-positions of the xbox controller
                                                             // joysticks.
        });
    }
}

