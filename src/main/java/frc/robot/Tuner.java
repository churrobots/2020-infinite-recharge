package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public final class Tuner {

  public static final double getPowerToHoldUpArm() {
    return powerToHoldUpArm.getDouble(defaultArmHoldPower);
  }

  public static final double getPowerToHoldDownArm() {
    return powerToHoldDownArm.getDouble(defaultArmHoldPower);
  }

  public static final double getPowerToMoveArm() {
    return powerToMoveArm.getDouble(defaultArmMovePower);
  }

  public static final double getSecondsToMoveArm() {
    return secondsToMoveArm.getDouble(defaultArmMoveSeconds);
  }

  public static final double getPowerToIntakePowercells() {
    return powerToIntakePowercells.getDouble(defaultIntakePower);
  }

  public static final double getPowerToEjectPowercells() {
    return powerToEjectPowercells.getDouble(defaultIntakePower);
  }

  public static final double getMaxNonBoostDrivePower() {
    return maxPowerToNonBoostDrive.getDouble(defaultDrivePower);
  }

  // Set sane defaults.
  // TODO: should we log when networktables is empty and has to return a default value?
  protected static final double defaultArmHoldPower = 0.10;
  protected static final double defaultArmMovePower = 0.40;
  protected static final double defaultArmMoveSeconds = 2;
  protected static final double defaultIntakePower = 1.00;
  protected static final double defaultDrivePower = 0.70;

  protected static final ShuffleboardTab tunerTab = Shuffleboard.getTab("Tuner");

  protected static final ComplexWidget pdpViewer = tunerTab
      .add(new PowerDistributionPanel(0))
      .withPosition(0, 0)
      .withSize(4, 4);

  protected static final ShuffleboardLayout armsPanel = tunerTab
      .getLayout("Arms", BuiltInLayouts.kList)
      .withPosition(4, 0)
      .withSize(4, 4);

  protected static final ShuffleboardLayout intakePanel = tunerTab
      .getLayout("Intake", BuiltInLayouts.kList)
      .withPosition(8, 0)
      .withSize(4, 4);

  protected static final ShuffleboardLayout drivetrainPanel = tunerTab
      .getLayout("Drivetrain", BuiltInLayouts.kList)
      .withPosition(12, 0)
      .withSize(4, 4);

  protected static final NetworkTableEntry powerToHoldUpArm = armsPanel
      .addPersistent("Power to hold arm UP", defaultArmHoldPower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

  protected static final NetworkTableEntry powerToHoldDownArm = armsPanel
      .addPersistent("Power to hold arm DOWN", defaultArmHoldPower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

  protected static final NetworkTableEntry powerToMoveArm = armsPanel
      .addPersistent("Power to MOVE arm", defaultArmMovePower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

  protected static final NetworkTableEntry secondsToMoveArm = armsPanel
      .addPersistent("Seconds for arm to FINISH move", defaultArmMoveSeconds)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 4))
      .getEntry();

  protected static final NetworkTableEntry powerToEjectPowercells = intakePanel
      .addPersistent("Roller power to EJECT", defaultIntakePower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

  protected static final NetworkTableEntry powerToIntakePowercells = intakePanel
      .addPersistent("Roller power to INTAKE", defaultIntakePower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

  protected static final NetworkTableEntry maxPowerToNonBoostDrive = drivetrainPanel
      .addPersistent("Max non-boost drive power", defaultDrivePower)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("min", 0, "max", 1))
      .getEntry();

}
