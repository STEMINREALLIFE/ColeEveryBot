package frc.robot;

/**
 * Constants file.
 */
public final class Constants {
    /**
     * Stick Deadband
     */
    public static final double stickDeadband = 0.1;
    /**
     * Driver ID
     */
    public static final int driverID = 0;
    /**
     * Operator ID
     */
    public static final int operatorID = 1;

    /**
     * Motor CAN id's.
     */
    public static final class Motors {
    }

    /**
     * Pneumatics CAN id constants.
     */
    public static final class Pneumatics {
    }

    public static final class Drive {
        public static final edu.wpi.first.wpilibj.SPI.Port navXID =
            edu.wpi.first.wpilibj.SPI.Port.kMXP;
        public static final boolean isFeildRelative = true;
    }

    public static final class Ball {
        public static final int BEAMBREAK1 = 0;
        public static final int BEAMBREAK2 = 1;
        public static final int BALLMOTOR = 12;
    }

    public static final class Hatch {
        public static final int HATCHMOTOR = 10;
        public static final int TOUCHSENSOR = 3;
        public static final int CANCODER = 15;

    }
}
