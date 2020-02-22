/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.helpers.Gamepad.Axis;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DriveAsTank extends CommandBase {

  protected final DrivetrainSubsystem drivetrainSubsystem;
  protected final Axis leftAxis;
  protected final Axis rightAxis;

  public DriveAsTank(DrivetrainSubsystem drivetrainSubsystem, Axis leftAxis, Axis rightAxis) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.leftAxis = leftAxis;
    this.rightAxis = rightAxis;
    this.addRequirements(this.drivetrainSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.drivetrainSubsystem.tankDrive(this.leftAxis.get(), this.rightAxis.get());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
