package frc.robot.subsystems.Drive;

import org.littletonrobotics.junction.AutoLog;

public interface DriveIO {

    @AutoLog
    public class DriveIOInputs {
        /**
         * right motor data
         */
        public double driveRightLeadPositionRad;
        public double driveRightLeadVelocityRadPerSec;
        public double driveRightLeadAppliedVolts;

        /**
         * left motor data
         */
        public double driveLeftLeadPositionRad;
        public double driveLeftLeadVelocityRadPerSec;
        public double driveLeftLeadAppliedVolts;

        /**
         * gyro data
         */
        public static float yaw;
        public static float pitch;
        public static float roll;
    }

    public default void updateInputs(DriveIOInputs inputs) {}

    public default void setPower(double powerLeft, double powerRight) {}
}
