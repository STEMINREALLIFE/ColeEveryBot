package frc.robot.subsystems.Drive;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class DriveReal implements DriveIO {

    private final TalonFX driveFrontRightLead = new TalonFX(Constants.Drive.FRONTRIGHT, "CANivore");// Notes
                                                                                                    // by
                                                                                                    // Cole
                                                                                                    // -
                                                                                                    // motors
                                                                                                    // are
                                                                                                    // defined,
                                                                                                    // uses
                                                                                                    // a
                                                                                                    // variable
                                                                                                    // from
                                                                                                    // the
                                                                                                    // constant
                                                                                                    // file
                                                                                                    // for
                                                                                                    // their
                                                                                                    // ids
    private final TalonFX driveFrontLeftLead = new TalonFX(Constants.Drive.FRONTLEFT, "CANivore");
    private final TalonFX driveBackRightFollow = new TalonFX(Constants.Drive.BACKRIGHT, "CANivore");
    private final TalonFX driveBackLeftFollow = new TalonFX(Constants.Drive.BACKLEFT, "CANivore");

    private final AHRS gyro = new AHRS(Constants.Drive.navXID);
    private final StatusSignal<Double> driveRightLeadPositon; // Notes by Cole - diffrent types of
                                                              // variabels that store data about the
                                                              // motors are defined here, such as
                                                              // position, velocity and voltage.
    private final StatusSignal<Double> driveRightLeadVelocity;
    private final StatusSignal<Double> driveRightLeadVoltage;
    private final StatusSignal<Double> driveLeftLeadPositon;
    private final StatusSignal<Double> driveLeftLeadVelocity;
    private final StatusSignal<Double> driveLeftLeadVoltage;

    public DriveReal() {

        driveBackLeftFollow.setControl(new StrictFollower(driveFrontLeftLead.getDeviceID())); // Notes
                                                                                              // by
                                                                                              // Cole
                                                                                              // -
                                                                                              // in
                                                                                              // tank
                                                                                              // drive,
                                                                                              // both
                                                                                              // of
                                                                                              // the
                                                                                              // lfet
                                                                                              // motors
                                                                                              // spin
                                                                                              // at
                                                                                              // the
                                                                                              // same
                                                                                              // time,
                                                                                              // as
                                                                                              // well
                                                                                              // as
                                                                                              // the
                                                                                              // right
        driveBackRightFollow.setControl(new StrictFollower(driveFrontRightLead.getDeviceID())); // Notes
                                                                                                // by
                                                                                                // Cole
                                                                                                // -
                                                                                                // this
                                                                                                // code
                                                                                                // sets
                                                                                                // the
                                                                                                // front
                                                                                                // motors
                                                                                                // of
                                                                                                // each
                                                                                                // side
                                                                                                // to
                                                                                                // do
                                                                                                // the
                                                                                                // exact
                                                                                                // same
                                                                                                // thing
                                                                                                // as
                                                                                                // the
                                                                                                // back
                                                                                                // motors
                                                                                                // (for
                                                                                                // example
                                                                                                // spin
                                                                                                // at
                                                                                                // the
                                                                                                // same
                                                                                                // time)


        driveRightLeadPositon = driveFrontRightLead.getPosition(); // Notes by Cole - sets the data
                                                                   // equal to these variables
        driveRightLeadVelocity = driveFrontRightLead.getVelocity();
        driveRightLeadVoltage = driveFrontRightLead.getMotorVoltage();
        driveLeftLeadPositon = driveFrontLeftLead.getPosition();
        driveLeftLeadVelocity = driveFrontLeftLead.getVelocity();
        driveLeftLeadVoltage = driveFrontLeftLead.getMotorVoltage();



        driveFrontLeftLead.setNeutralMode(NeutralModeValue.Brake); // Notes by Cole - stops the
                                                                   // motors from spinning
        driveFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        driveBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        driveBackRightFollow.setNeutralMode(NeutralModeValue.Brake);



    }

    @Override

    public void setPower(double powerLeft, double powerRight) {
        driveBackLeftFollow.set(powerLeft); // Notes by Cole - sets the motor the the speed of
                                            // powerLeft
        driveBackRightFollow.set(powerRight);
    }

    public void periodic() {} // Notes by Cole - every 20 ms

    public void updateInputs(DriveIOInputs inputs) {

        inputs.yaw = gyro.getYaw(); // Notes by Cole - gets the gyro data
        inputs.roll = gyro.getRoll();
        inputs.pitch = gyro.getPitch();


        /**
         * refreshs motor data
         */
        BaseStatusSignal.refreshAll(driveRightLeadPositon, driveRightLeadVelocity,
            driveRightLeadVoltage, driveLeftLeadPositon, driveLeftLeadVelocity,
            driveLeftLeadVoltage); // Notes by Cole - updates the drive train variables to the
                                   // current status
    }
}
