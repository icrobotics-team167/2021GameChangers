package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.actions.Action;
import frc.robot.actions.Routine;
import frc.robot.actions.Teleop;
import frc.robot.controls.controllers.PSController;
import frc.robot.controls.schemes.ControlScheme;
import frc.robot.controls.schemes.SingleController;

public class Robot extends TimedRobot {

    private ControlScheme controls;
    private Action auto;
    private Teleop teleop;

    @Override
    public void robotInit() {
        controls = new SingleController(new PSController(0));

        auto = new Routine(new Action[]{});
        teleop = new Teleop(controls);
    }

    @Override
    public void robotPeriodic() {
        // TODO Add dashboard logging here
    }

    @Override
    public void autonomousInit() {
        auto.exec();
    }

    @Override
    public void autonomousPeriodic() {
        auto.exec();
    }

    @Override
    public void teleopInit() {
        teleop.exec();
    }

    @Override
    public void teleopPeriodic() {
        teleop.exec();
    }

    @Override
    public void disabledInit() {
        // TODO Disable Limelight here
    }

}
