package frc.robot.helpers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Gamepad {

  public final Button buttonSouth;
  public final Button buttonEast;
  public final Button buttonWest;
  public final Button buttonNorth;
  public final Button leftBumper;
  public final Button rightBumper;
  public final Axis leftYAxis;
  public final Axis leftXAxis;
  public final Axis rightYAxis;
  public final Axis leftAnalogTrigger;
  public final Axis rightAnalogTrigger;
  // TODO: add other axes and buttons

  public Gamepad(int driverStationPort)  {

    Joystick gamepad = new Joystick(driverStationPort);

    // TODO: allow different gamepad types
    // if (gamepadType == "LogitechF310") {

      buttonSouth = new JoystickButton(gamepad, 1);
      buttonEast = new JoystickButton(gamepad, 2);
      buttonWest = new JoystickButton(gamepad, 3);
      buttonNorth = new JoystickButton(gamepad, 4);

      leftBumper = new JoystickButton(gamepad, 5);
      rightBumper = new JoystickButton(gamepad, 6);

      leftXAxis = new Axis(gamepad, 0);
      leftYAxis = new Axis(gamepad, 1);
      rightYAxis = new Axis(gamepad, 5);

      leftAnalogTrigger = new Axis(gamepad, 2);
      rightAnalogTrigger = new Axis(gamepad, 3);
    // }

    // throw new InvalidGamepadTypeException(String.format("'%s' is not a recognized gamepad type", gamepadType));

  }

  public static class Axis {

    private Joystick _gamepad;
    private int _axis;

    Axis(Joystick gamepad, int axis) {
      _gamepad = gamepad;
      _axis = axis;
    }

    public double get() {
      return _gamepad.getRawAxis(_axis);
    }

  }

  public static class InvalidGamepadTypeException extends RuntimeException {

    public InvalidGamepadTypeException(String message) {
      super(message);
    }

    private static final long serialVersionUID = -6464474179450099666L;

  }

}