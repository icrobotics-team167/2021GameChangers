package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * For conversions between Xbox and PlayStation buttons, see the
 * <a href="https://support.xbox.com/en-US/help/hardware-network/controller/xbox-one-wireless-controller">Xbox controller documentation</a> and the
 * <a href="https://manuals.playstation.net/document/en/ps4/basic/pn_controller.html">PlayStation controller documentation</a>.
 */
public class XBController implements Controller {

    private final XboxController controller;
    private final int port;

    public XBController(int port) {
        controller = new XboxController(port);
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isPSController() {
        return false;
    }

    @Override
    public boolean isXBController() {
        return true;
    }

    @Override
    public double getL2Value() {
        return controller.getTriggerAxis(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getL2() {
        return controller.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.2;
    }

    private boolean l2Previous = false;
    private boolean l2Current = false;
    @Override
    public boolean getL2Toggled() {
        l2Previous = l2Current;
        l2Current = getL2();
        return l2Current && !l2Previous;
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
        return controller.getTriggerAxis(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getR2() {
        return controller.getTriggerAxis(GenericHID.Hand.kRight) >= 0.2;
    }

    private boolean r2Previous = false;
    private boolean r2Current = false;
    @Override
    public boolean getR2Toggled() {
        r2Previous = r2Current;
        r2Current = getR2();
        return r2Current && !r2Previous;
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
        return controller.getX(GenericHID.Hand.kLeft);
    }

    @Override
    public double getLeftStickY() {
        return -controller.getY(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getLeftStickButton() {
        return controller.getStickButton(GenericHID.Hand.kLeft);
    }

    @Override
    public boolean getLeftStickButtonToggled() {
        return controller.getStickButtonPressed(GenericHID.Hand.kLeft);
    }

    @Override
    public double getRightStickX() {
        return controller.getX(GenericHID.Hand.kRight);
    }

    @Override
    public double getRightStickY() {
        return -controller.getY(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getRightStickButton() {
        return controller.getStickButton(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getRightStickButtonToggled() {
        return controller.getStickButtonPressed(GenericHID.Hand.kRight);
    }

    @Override
    public boolean getXButton() {
        return controller.getAButton();
    }

    @Override
    public boolean getXButtonToggled() {
        return controller.getAButtonPressed();
    }

    @Override
    public boolean getCircleButton() {
        return controller.getBButton();
    }

    @Override
    public boolean getCircleButtonToggled() {
        return controller.getBButtonPressed();
    }

    @Override
    public boolean getSquareButton() {
        return controller.getXButton();
    }

    @Override
    public boolean getSquareButtonToggled() {
        return controller.getXButtonPressed();
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
        return controller.getBackButton();
    }

    @Override
    public boolean getShareButtonToggled() {
        return controller.getBackButtonPressed();
    }

    @Override
    public boolean getOptionsButton() {
        return controller.getStartButton();
    }

    @Override
    public boolean getOptionsButtonToggled() {
        return controller.getStartButtonPressed();
    }

}
