/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PWMVictorSPX;


public class Climber extends SubsystemBase {
  PWMVictorSPX climbermotor = new PWMVictorSPX(Constants.climberMotorPWM);

  public Climber() {
  }

  public void reset() {
    // Resets the winch
    this.climbermotor.set(0.15);
    
  }

  public void run() {
    // Runs the winch out
    this.climbermotor.set(-1.0);
    
  }

  public void stop() {
    this.climbermotor.set(0);
  }
}

