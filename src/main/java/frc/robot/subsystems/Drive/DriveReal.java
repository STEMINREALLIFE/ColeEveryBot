package frc.robot.subsystems.Drive;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class DriveReal implements DriveIO {
    /**
     * initilize motors and sensors
     */
    private final TalonFX driveFrontRightLead = new TalonFX(4);
    private final TalonFX driveFrontLeftLead = new TalonFX(2);
    private final TalonFX driveBackRightFollow = new TalonFX(5);
    private final TalonFX driveBackLeftFollow = new TalonFX(3);

    private final AHRS gyro = new AHRS(Constants.Drive.navXID);
    private final StatusSignal<Double> driveRightLeadPositon;
    private final StatusSignal<Double> driveRightLeadVelocity;
    private final StatusSignal<Double> driveRightLeadVoltage;
    private final StatusSignal<Double> driveLeftLeadPositon;
    private final StatusSignal<Double> driveLeftLeadVelocity;
    private final StatusSignal<Double> driveLeftLeadVoltage;

    public DriveReal() {
        /**
         * set motors to follow each other
         */
        driveBackLeftFollow.set(driveFrontLeftLead.getDeviceID());
        driveBackRightFollow.set(driveFrontRightLead.getDeviceID());

        /**
         * get data from motors
         */
        driveRightLeadPositon = driveFrontRightLead.getPosition();
        driveRightLeadVelocity = driveFrontRightLead.getVelocity();
        driveRightLeadVoltage = driveFrontRightLead.getMotorVoltage();
        driveLeftLeadPositon = driveFrontLeftLead.getPosition();
        driveLeftLeadVelocity = driveFrontLeftLead.getVelocity();
        driveLeftLeadVoltage = driveFrontLeftLead.getMotorVoltage();

        /**
         * change to closed loop
         */
        driveFrontLeftLead.getConfigurator();

        /**
         * sets motor to netural mode
         */
        driveFrontLeftLead.setNeutralMode(NeutralModeValue.Brake);
        driveFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        driveBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        driveBackRightFollow.setNeutralMode(NeutralModeValue.Brake);

        /**
         * reset encoders
         */

    }


    /**
     * sets motor power
     */
    public void setPower(double powerLeft, double powerRight) {
        driveBackLeftFollow.set(powerLeft);
        driveBackRightFollow.set(powerRight);
    }

    public void periodic() {}

    public void updateInputs(DriveIOInputs inputs) {
        /**
         * gets gyro data
         */
        inputs.yaw = gyro.getYaw();
        inputs.roll = gyro.getRoll();
        inputs.pitch = gyro.getPitch();

        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(driveRightLeadPositon, driveRightLeadVelocity,
            driveRightLeadVoltage, driveLeftLeadPositon, driveLeftLeadVelocity,
            driveLeftLeadVoltage);
    }
}
