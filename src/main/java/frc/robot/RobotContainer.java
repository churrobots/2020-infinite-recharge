/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Climberdown;
import frc.robot.commands.Climberup;
import frc.robot.commands.DriveAsTank;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PowerCellHandler;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.IntakePowercells;
import frc.robot.commands.LowerArmDown;
import frc.robot.commands.RaiseArmUp;
import frc.robot.commands.ReleasePowercells;
import frc.robot.helpers.Gamepad;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
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

    // Default select the Tuner tab
    // TODO: select a game-time tab later
    Shuffleboard.selectTab("Tuner");

    // Connect to all the inputs (gamepads and shuffleboard).
    Gamepad driverGamepad = new Gamepad(Constants.driverGamepadPort);
    Gamepad operatorGamepad = new Gamepad(Constants.operatorGamepadPort);

    // Connect to all the outputs.
    Arms arms = new Arms();
    PowerCellHandler powerCellHandler = new PowerCellHandler();
    Drivetrain drivetrain = new Drivetrain();
    Climber climber = new Climber();

    // Describe when the commands should be scheduled.
    this.autonomousCommand = new DriveForTime(drivetrain, 2, 0.2, 0.2);

    operatorGamepad.xButton.whenPressed(new RaiseArmUp(arms));
    operatorGamepad.aButton.whenPressed(new LowerArmDown(arms));
    operatorGamepad.leftBumper.whileHeld(new IntakePowercells(powerCellHandler));
    operatorGamepad.rightBumper.whileHeld(new ReleasePowercells(powerCellHandler));
    operatorGamepad.povUp.whileHeld(new Climberup(climber));
    operatorGamepad.getDualButton(operatorGamepad.backButton, operatorGamepad.startButton)
        .whileHeld(new Climberdown(climber));
    arms.setDefaultCommand(new RaiseArmUp(arms));
    drivetrain.setDefaultCommand(
        new DriveAsTank(drivetrain, driverGamepad.leftYAxis, driverGamepad.rightYAxis, driverGamepad.rightAnalogTrigger));
  }

  public Command getAutonomousCommand() {
    return this.autonomousCommand;
  }

}
