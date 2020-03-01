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
    Gamepad driverGamepad = new Gamepad(Constants.driverGamepadPort);
    Gamepad operatorGamepad = new Gamepad(Constants.operatorGamepadPort);
    DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    Arms arms = new Arms();
    PowerCellHandler powerCellHandler = new PowerCellHandler();

    // Describe when the commands should be scheduled.
    this.autonomousCommand = new DriveForTime(drivetrainSubsystem, 2, 0.2, 0.2);
    operatorGamepad.buttonWest.whenPressed(new RaiseArmUp(arms));
    operatorGamepad.buttonSouth.whenPressed(new LowerArmDown(arms));
    operatorGamepad.leftBumper.whileHeld(new IntakePowercells(powerCellHandler));
    operatorGamepad.rightBumper.whileHeld(new ReleasePowercells(powerCellHandler));

    driverGamepad.leftBumper.whileHeld(new IntakePowercells(powerCellHandler));
    driverGamepad.rightBumper.whileHeld(new ReleasePowercells(powerCellHandler));

    arms.setDefaultCommand(new RaiseArmUp(arms));
    drivetrainSubsystem.setDefaultCommand(
        new DriveAsTank(drivetrainSubsystem, driverGamepad.leftYAxis, driverGamepad.rightYAxis, driverGamepad.rightAnalogTrigger));
  }

  public Command getAutonomousCommand() {
    return this.autonomousCommand;
  }

}
