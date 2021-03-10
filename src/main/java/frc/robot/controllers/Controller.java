package frc.robot.controllers;

/**
 * For conversions between Xbox and PlayStation buttons, see the
 * <a href="https://support.xbox.com/en-US/help/hardware-network/controller/xbox-one-wireless-controller">Xbox controller documentation</a> and the
 * <a href="https://manuals.playstation.net/document/en/ps4/basic/pn_controller.html">PlayStation controller documentation</a>.
 */
public interface Controller {

    // Metadata
    int getPort();
    boolean isPSController();
    boolean isXBController();

    // Bumpers and triggers
    double getL2Value();
    boolean getL2();
    boolean getL2Toggled();
    boolean getL1();
    boolean getL1Toggled();
    double getR2Value();
    boolean getR2();
    boolean getR2Toggled();
    boolean getR1();
    boolean getR1Toggled();

    // Analog sticks
    double getLeftStickX();
    double getLeftStickY();
    boolean getLeftStickButton();
    boolean getLeftStickButtonToggled();
    double getRightStickX();
    double getRightStickY();
    boolean getRightStickButton();
    boolean getRightStickButtonToggled();

    // Right buttons
    boolean getXButton();
    boolean getXButtonToggled();
    boolean getCircleButton();
    boolean getCircleButtonToggled();
    boolean getSquareButton();
    boolean getSquareButtonToggled();
    boolean getTriangleButton();
    boolean getTriangleButtonToggled();

    // D-pad
    boolean getDpadNeutral();
    boolean getDpadUp();
    boolean getDpadUpToggled();
    boolean getDpadUpRight();
    boolean getDpadRight();
    boolean getDpadRightToggled();
    boolean getDpadDownRight();
    boolean getDpadDown();
    boolean getDpadDownToggled();
    boolean getDpadDownLeft();
    boolean getDpadLeft();
    boolean getDpadLeftToggled();
    boolean getDpadUpLeft();

    // Central buttons
    boolean getShareButton();
    boolean getShareButtonToggled();
    boolean getOptionsButton();
    boolean getOptionsButtonToggled();

}
