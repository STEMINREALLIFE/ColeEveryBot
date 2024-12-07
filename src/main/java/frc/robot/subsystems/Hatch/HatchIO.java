package frc.robot.subsystems.Hatch;

import org.littletonrobotics.junction.AutoLog;

public interface HatchIO {
    @AutoLog
    public class HatchIOInputs {
        public double hatchAbsoluteENCRawValue; // Notes by Cole - io variables are created
        public boolean touchSensor;
    }

    public default void updateInputs(HatchIOInputs inputs) {}

    public default void setVolatge(double v) {}
}
