/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arms;

public class LowerArmDown extends CommandBase {
  /**
   * Creates a new LowerArmDown.
   */
  Arms arms;
  double startTime;
  double millisecondsUntilDown = 2000;

  public LowerArmDown(Arms a) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(a);
    arms = a;
    startTime = System.currentTimeMillis();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arms.runArmMotor(-0.25);
    // double elapsedMilliseconds = System.currentTimeMillis() - startTime;
    // if (elapsedMilliseconds > millisecondsUntilDown) {
    //   arms.runArmMotor(-0.10);
    // } else {
    //   arms.runArmMotor(-0.40);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arms.runArmMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
