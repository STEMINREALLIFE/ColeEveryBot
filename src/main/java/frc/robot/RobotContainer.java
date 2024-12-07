package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot.RobotRunType;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Drive.Drive;
import frc.robot.subsystems.Drive.DriveIO;
import frc.robot.subsystems.Drive.DriveReal;
import frc.robot.subsystems.Hatch.Hatch;
import frc.robot.subsystems.Hatch.HatchIO;
import frc.robot.subsystems.Hatch.HatchReal;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Intake.IntakeIO;
import frc.robot.subsystems.Intake.IntakeReal;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Notes by Cole - assigns two controller diffrent names, one "driver", and one "operator"
    private final CommandXboxController driver = new CommandXboxController(Constants.driverID); // Notes
                                                                                                // by
                                                                                                // Cole
                                                                                                // -
                                                                                                // creates
                                                                                                // a
                                                                                                // new
                                                                                                // CommandXboxController
                                                                                                // object
                                                                                                // called
                                                                                                // driver
                                                                                                // and
                                                                                                // is
                                                                                                // given
                                                                                                // its
                                                                                                // id
                                                                                                // fro
                                                                                                // constants
    private final CommandXboxController operator = new CommandXboxController(Constants.operatorID); // Notes
                                                                                                    // by
                                                                                                    // Cole
                                                                                                    // -
                                                                                                    // creates
                                                                                                    // a
                                                                                                    // new
                                                                                                    // CommandXboxController
                                                                                                    // operator
                                                                                                    // called
                                                                                                    // driver
                                                                                                    // and
                                                                                                    // is
                                                                                                    // given
                                                                                                    // its
                                                                                                    // id
                                                                                                    // fro
                                                                                                    // constants

    // Initialize AutoChooser Sendable
    private final SendableChooser<String> autoChooser = new SendableChooser<>();

    /* Subsystems */
    private Drive drive; // Notes by Cole - assigns each subsystem a variable
    private Hatch hatch;
    private Intake intake;
    private LEDs leds = new LEDs(9, 100); // Notes by Cole - for leds, port 9, length 100, per
                                          // requierments

    private Trigger hatchTrue = new Trigger(() -> hatch.touchSensorStatus()).debounce(.25); // Notes
                                                                                            // by
                                                                                            // Cole
                                                                                            // -
                                                                                            // makes
                                                                                            // new
                                                                                            // trigger
                                                                                            // object
                                                                                            // "hatchTrue"
                                                                                            // and
                                                                                            // gets
                                                                                            // the
                                                                                            // touchSensorStatus
                                                                                            // value
                                                                                            // from
                                                                                            // the
                                                                                            // hatch


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer(RobotRunType runtimeType) {

        switch (runtimeType) {
            case kReal: // Notes by Cole - during real
                drive = new Drive(new DriveReal()); // Notes by Cole - real is assigned a variable
                hatch = new Hatch(new HatchReal(), operator);
                intake = new Intake(new IntakeReal());
                break;
            case kSimulation: // Notes by Cole - during simulation
                drive = new Drive(new DriveIO() {}); // Notes by Cole - IO is assigned its variable
                hatch = new Hatch(new HatchIO() {}, operator);
                intake = new Intake(new IntakeIO() {});
                break;
            default:

        }
        leds.setDefaultCommand(leds.setAllianceColor().ignoringDisable(true)); // Notes by Cole -
                                                                               // sets led color
                                                                               // based on alliance
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {

        hatchTrue.onTrue((leds.hatchTrue().withTimeout(3)).andThen(Commands.startEnd(() -> { // Notes
                                                                                             // by
                                                                                             // Cole
                                                                                             // -
                                                                                             // when
                                                                                             // hatch
                                                                                             // true,
                                                                                             // make
                                                                                             // leds
                                                                                             // the
                                                                                             // color
                                                                                             // lavender
            operator.getHID().setRumble(RumbleType.kBothRumble, 1);
        }, () -> {
            operator.getHID().setRumble(RumbleType.kBothRumble, 0);
        })));


        /** driver controlls */
        drive.setDefaultCommand(drive.driveCMD(driver));


        /** operator controlls */
        operator.povDown().onTrue(leds.call().andThen(Commands.startEnd(() -> {
            operator.getHID().setRumble(RumbleType.kBothRumble, 1);
        }, () -> {
            operator.getHID().setRumble(RumbleType.kBothRumble, 0);
        })).withTimeout(5));
        operator.a().onTrue(hatch.homePosition()); // Notes by Cole - when button "a" pushed, hatch
                                                   // goes to home position
        operator.x().onTrue(hatch.intake()); // Notes by Cole - when button "x" pushed, hatch
                                             // intakes
        operator.leftTrigger().onTrue(intake.intake()); // Notes by Cole - when left trigger
                                                        // pressed, main intake starts intaking
        operator.rightTrigger().onTrue(intake.shoot()); // Notes by Cole - when right trigger
                                                        // pressed, main intake starts outaking
        operator.b().onTrue(intake.move()); // Notes by Cole - when button b is pressed, same as
                                            // intake intaking (leftTrigger)

    }

    /**
     * Gets the user's selected autonomous command.
     *
     * @return Returns autonomous command selected.
     */
    public Command getAutonomousCommand() { // Notes by Cole - autonomous stuff
        Command autocommand;
        String stuff = autoChooser.getSelected();
        switch (stuff) {
            case "wait":
                autocommand = new WaitCommand(1.0);
                break;
            default:
                autocommand = new InstantCommand();
        }
        return autocommand;
    }
}
