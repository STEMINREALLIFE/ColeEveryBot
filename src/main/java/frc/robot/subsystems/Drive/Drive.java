package frc.robot.subsystems.Drive;


import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Drive extends SubsystemBase {
    private DriveIO io;
    private DriveIOInputsAutoLogged inputs = new DriveIOInputsAutoLogged();

    public Drive(DriveIO driveIO) {
        this.io = driveIO;
    }

    @Override
    public void periodic() {
        Logger.processInputs("Drive", inputs);
        io.updateInputs(inputs);

    }

    public void setPower(double leftPower, double rightPower) {
        Logger.recordOutput("Drive/leftPower", leftPower);
        Logger.recordOutput("Drive/rightPower", rightPower);
        io.setPower(leftPower, rightPower);
    }

    public Command driveCMD(CommandXboxController driver) {
        return run(() -> {
            setPower(driver.getLeftY(), driver.getRightY());
        });
    }
}

