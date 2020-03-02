/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arms extends SubsystemBase {

  PWMVictorSPX armMotor = new PWMVictorSPX(Constants.armMotorPWM);
  NetworkTableEntry powerToHoldUpArm;
  NetworkTableEntry powerToHoldDownArm;
  NetworkTableEntry powerToMoveArm;
  double defaultPowerWhenNetworkTableIsEmpty = 0.25;

  public Arms(NetworkTableEntry powerToHoldUpArm, NetworkTableEntry powerToHoldDownArm,
      NetworkTableEntry powerToMoveArm) {
    this.powerToHoldUpArm = powerToHoldUpArm;
    this.powerToHoldDownArm = powerToHoldDownArm;
    this.powerToMoveArm = powerToMoveArm;
  }

  public void moveUp() {
    this.armMotor.set(this.powerToMoveArm.getDouble(this.defaultPowerWhenNetworkTableIsEmpty));
  }

  public void moveDown() {
    this.armMotor.set(-1 * this.powerToMoveArm.getDouble(this.defaultPowerWhenNetworkTableIsEmpty));
  }

  public void holdUp() {
    this.armMotor.set(this.powerToHoldUpArm.getDouble(this.defaultPowerWhenNetworkTableIsEmpty));
  }

  public void holdDown() {
    this.armMotor.set(-1 * this.powerToHoldDownArm.getDouble(this.defaultPowerWhenNetworkTableIsEmpty));
  }

}
