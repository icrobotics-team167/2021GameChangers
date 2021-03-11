package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * For conversions between Xbox and PlayStation buttons, see the
 * <a href="https://support.xbox.com/en-US/help/hardware-network/controller/xbox-one-wireless-controller">Xbox controller documentation</a> and the
 * <a href="https://manuals.playstation.net/document/en/ps4/basic/pn_controller.html">PlayStation controller documentation</a>.
 */
public class PSController implements Controller {

    private final XboxController controller;
    private final int port;

    public PSController(int port) {
        controller = new XboxController(port);
        this.port = port;
        System.out.println("PS POV Count: " + controller.getPOVCount());
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isPSController() {
        return true;
    }

    @Override
    public boolean isXBController() {
        return false;
    }

    @Override
    public double getL2Value() {
        return (controller.getRawAxis(3) + 1) / 2;
    }

    @Override
    public boolean getL2() {
        return controller.getBackButton();
    }

    public boolean getL2Toggled() {
        return controller.getBackButtonPressed();
    }

    @Override
    public boolean getL1() {
        return controller.getBumper(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getL1Toggled() {
        return controller.getBumperPressed(GenericHID.Hand.kLeft);
    }

    @Override
    public double getR2Value() {
        return (controller.getRawAxis(4) + 1) / 2;
    }

    @Override
    public boolean getR2() {
        return controller.getStartButton();
    }

    public boolean getR2Toggled() {
        return controller.getStartButtonPressed();
    }

    @Override
    public boolean getR1() {
        return controller.getBumper(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getR1Toggled() {
        return controller.getBumperPressed(GenericHID.Hand.kRight);
    }

    @Override
    public double getLeftStickX() {
        return controller.getRawAxis(0);
    }

    @Override
    public double getLeftStickY() {
        return -controller.getRawAxis(1);
    }

    @Override
    public boolean getLeftStickButton() {
        return controller.getRawButton(11);
    }

    @Override
    public boolean getLeftStickButtonToggled() {
        return controller.getRawButtonPressed(11);
    }

    @Override
    public double getRightStickX() {
        return controller.getRawAxis(2);
    }

    @Override
    public double getRightStickY() {
        return -controller.getRawAxis(5);
    }

    @Override
    public boolean getRightStickButton() {
        return controller.getRawButton(12);
    }

    @Override
    public boolean getRightStickButtonToggled() {
        return controller.getRawButtonPressed(12);
    }

    @Override
    public boolean getXButton() {
        return controller.getBButton();
    }

    @Override
    public boolean getXButtonToggled() {
        return controller.getBButtonPressed();
    }

    @Override
    public boolean getCircleButton() {
        return controller.getXButton();
    }

    @Override
    public boolean getCircleButtonToggled() {
        return controller.getXButtonPressed();
    }

    @Override
    public boolean getSquareButton() {
        return controller.getAButton();
    }

    @Override
    public boolean getSquareButtonToggled() {
        return controller.getAButtonPressed();
    }

    @Override
    public boolean getTriangleButton() {
        return controller.getYButton();
    }

    @Override
    public boolean getTriangleButtonToggled() {
        return controller.getYButtonPressed();
    }

    @Override
    public boolean getDpadNeutral() {
        return controller.getPOV(0) == -1;
    }

    @Override
    public boolean getDpadUp() {
        return controller.getPOV(0) == 0;
    }

    private boolean dpadUpPrevious = false;
    private boolean dpadUpCurrent = false;
    @Override
    public boolean getDpadUpToggled() {
        dpadUpPrevious = dpadUpCurrent;
        dpadUpCurrent = getDpadUp();
        return dpadUpCurrent && !dpadUpPrevious;
    }

    @Override
    public boolean getDpadUpRight() {
        return controller.getPOV(0) == 45;
    }

    @Override
    public boolean getDpadRight() {
        return controller.getPOV(0) == 90;
    }

    private boolean dpadRightPrevious = false;
    private boolean dpadRightCurrent = false;
    @Override
    public boolean getDpadRightToggled() {
        dpadRightPrevious = dpadRightCurrent;
        dpadRightCurrent = getDpadRight();
        return dpadRightCurrent && !dpadRightPrevious;
    }

    @Override
    public boolean getDpadDownRight() {
        return controller.getPOV(0) == 135;
    }

    @Override
    public boolean getDpadDown() {
        return controller.getPOV(0) == 180;
    }

    private boolean dpadDownPrevious = false;
    private boolean dpadDownCurrent = false;
    @Override
    public boolean getDpadDownToggled() {
        dpadDownPrevious = dpadDownCurrent;
        dpadDownCurrent = getDpadDown();
        return dpadDownCurrent && !dpadDownPrevious;
    }

    @Override
    public boolean getDpadDownLeft() {
        return controller.getPOV(0) == 225;
    }

    @Override
    public boolean getDpadLeft() {
        return controller.getPOV(0) == 270;
    }

    private boolean dpadLeftPrevious = false;
    private boolean dpadLeftCurrent = false;
    @Override
    public boolean getDpadLeftToggled() {
        dpadLeftPrevious = dpadLeftCurrent;
        dpadLeftCurrent = getDpadLeft();
        return dpadLeftCurrent && !dpadLeftPrevious;
    }

    @Override
    public boolean getDpadUpLeft() {
        return controller.getPOV(0) == 315;
    }

    @Override
    public boolean getShareButton() {
        return controller.getStickButton(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getShareButtonToggled() {
        return controller.getStickButtonPressed(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getOptionsButton() {
        return controller.getStickButton(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getOptionsButtonToggled() {
        return controller.getStickButtonPressed(GenericHID.Hand.kRight);
    }

    public boolean getPSButton() {
        return controller.getRawButton(13);
    }

    public boolean getPSButtonToggled() {
        return controller.getRawButtonPressed(13);
    }

    public boolean getTouchpadButton() {
        return controller.getRawButton(14);
    }

    public boolean getTouchpadButtonToggled() {
        return controller.getRawButtonPressed(14);
    }

}
