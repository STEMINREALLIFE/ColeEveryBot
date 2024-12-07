package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {

    @AutoLog
    public class IntakeIOInputs {

        public boolean intake; // Notes by Cole - intake and outtake variables are created
        public boolean outtake;
    }

    public default void updateInputs(IntakeIOInputs inputs) {}

    public default void setPower(double Power) {}
}
