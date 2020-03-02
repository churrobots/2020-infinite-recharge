/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DriveAsTank;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.PowerCellHandler;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.IntakePowercells;
import frc.robot.commands.LowerArmDown;
import frc.robot.commands.RaiseArmUp;
import frc.robot.commands.ReleasePowercells;
import frc.robot.helpers.Gamepad;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public final Command autonomousCommand;

  public RobotContainer() {

    // Connect to all the inputs (gamepads and shuffleboard).
    Gamepad driverGamepad = new Gamepad(Constants.driverGamepadPort);
    Gamepad operatorGamepad = new Gamepad(Constants.operatorGamepadPort);

    ShuffleboardLayout armsTuner = Shuffleboard.getTab("Tuning")
        .getLayout("Arms", BuiltInLayouts.kList)
        .withSize(5, 4)
        .withPosition(0, 0);
    NetworkTableEntry powerToHoldUpArm = armsTuner.add("Power to hold arm UP", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
    NetworkTableEntry powerToHoldDownArm = armsTuner.add("Power to hold arm DOWN", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
    NetworkTableEntry powerToMoveArm = armsTuner.add("Power to MOVE arm", 0.25)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();

    // Connect to all the sensors.
    PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    Shuffleboard.getTab("Tuning").add(pdp);

    // Connect to all the outputs.
    Arms arms = new Arms(powerToHoldUpArm, powerToHoldDownArm, powerToMoveArm);
    PowerCellHandler powerCellHandler = new PowerCellHandler();
    DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

    // Describe when the commands should be scheduled.
    this.autonomousCommand = new DriveForTime(drivetrainSubsystem, 2, 0.2, 0.2);

    operatorGamepad.buttonWest.whenPressed(new RaiseArmUp(arms));
    operatorGamepad.buttonSouth.whenPressed(new LowerArmDown(arms));
    operatorGamepad.leftBumper.whileHeld(new IntakePowercells(powerCellHandler));
    operatorGamepad.rightBumper.whileHeld(new ReleasePowercells(powerCellHandler));

    arms.setDefaultCommand(new RaiseArmUp(arms));
    drivetrainSubsystem.setDefaultCommand(
        new DriveAsTank(drivetrainSubsystem, driverGamepad.leftYAxis, driverGamepad.rightYAxis, driverGamepad.rightAnalogTrigger));
  }

  public Command getAutonomousCommand() {
    return this.autonomousCommand;
  }

}
