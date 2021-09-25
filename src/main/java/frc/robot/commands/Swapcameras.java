/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Swapcameras extends CommandBase {
  UsbCamera camera;
  boolean cameraOn;
  
  public Swapcameras() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.cameraOn = true;
    this.camera = CameraServer.getInstance().startAutomaticCapture(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.camera.close();

    if (this.cameraOn) {
        this.cameraOn = false;
        this.camera = CameraServer.getInstance().startAutomaticCapture(1);
    }
    else {
        this.camera = CameraServer.getInstance().startAutomaticCapture(0);
        this.cameraOn = true;
    }
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
