package frc.robot.subsystems.Drive;

import org.littletonrobotics.junction.AutoLog;

public interface DriveIO {

    @AutoLog // Notes by Cole - AdvantageKit logs the stuff below
    public class DriveIOInputs {
        // right motor data

        public double driveRightLeadPositionRad; // Notes by Cole - Data that advantage scope can
                                                 // use
        public double driveRightLeadVelocityRadPerSec;
        public double driveRightLeadAppliedVolts;

        // left motor data

        public double driveLeftLeadPositionRad; // Notes by Cole - more data.
        public double driveLeftLeadVelocityRadPerSec;
        public double driveLeftLeadAppliedVolts;


        // gyro data

        public float yaw; // Notes by Cole - around the z axis
        public static float pitch; // Notes by Cole - around the y axis
        public static float roll; // Notes by Cole - around the x axis
    }

    public default void updateInputs(DriveIOInputs inputs) {} // Notes by Cole - the inputs are
                                                              // updated

    public default void setPower(double powerLeft, double powerRight) {}
}
