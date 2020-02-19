/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DriveAsTank;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.DriveForTime;
import frc.robot.helpers.Gamepad;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public final Command autonomousCommand;

  public RobotContainer() {

    // Connect to all the gamepads and subsystems on the robot.
    Constants constants = new Constants();
    Gamepad driverGamepad = new Gamepad(constants.driverGamepadPort);
    DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem(constants);

    // Create commands.
    Command driveForwardALittleBit = new DriveForTime(drivetrainSubsystem, 2, 0.2, 0.2);
    Command driveWithJoysticks = new DriveAsTank(
      drivetrainSubsystem, driverGamepad.leftYAxis, driverGamepad.rightYAxis);

    // Map the commands to the controller, autonomous mode, and defaults.
    this.autonomousCommand = driveForwardALittleBit;
    drivetrainSubsystem.setDefaultCommand(driveWithJoysticks);
    driverGamepad.buttonSouth.whenPressed(driveForwardALittleBit);

  }

  public Command getAutonomousCommand() {
    return this.autonomousCommand;
  }

}
