/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {

  protected DifferentialDrive differentialDrive;

  public DrivetrainSubsystem() {

    PWMVictorSPX leftMotors = new PWMVictorSPX(Constants.leftMotorsPWM);
    PWMVictorSPX rightMotors = new PWMVictorSPX(Constants.rightMotorsPWM);
    this.differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

}
