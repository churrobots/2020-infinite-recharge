/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveForTime extends CommandBase {

  protected DrivetrainSubsystem drivetrainSubsystem;
  protected double secondsToDrive;
  protected double leftSpeed;
  protected double rightSpeed;

  public DriveForTime(DrivetrainSubsystem drivetrainSubsystem, double secondsToDrive, double leftSpeed, double rightSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.addRequirements(drivetrainSubsystem);
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.secondsToDrive = secondsToDrive;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
