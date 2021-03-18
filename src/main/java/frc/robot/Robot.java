package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.controllers.PSController;
import frc.robot.controls.schemes.ControlScheme;
import frc.robot.controls.schemes.SingleController;
import frc.robot.routines.Action;
import frc.robot.routines.Routine;
import frc.robot.routines.Teleop;
import frc.robot.routines.actions.FollowAutoNavPath;
import frc.robot.routines.paths.AutoNav;
import frc.robot.subsystems.drive.SwerveDrive;

public class Robot extends TimedRobot {

    private ControlScheme controls;
    private Action auto;
    private Teleop teleop;

    @Override
    public void robotInit() {
        controls = new SingleController(new PSController(0));

        auto = new Routine(new Action[]{
            new FollowAutoNavPath(AutoNav.kTestLine),
        });
        teleop = new Teleop(controls);
    }

    @Override
    public void robotPeriodic() {
        // TODO Add dashboard logging here
        SmartDashboard.putNumber("X", SwerveDrive.getInstance().getPose().getX());
        SmartDashboard.putNumber("Y", SwerveDrive.getInstance().getPose().getY());
        SmartDashboard.putNumber("Theta", SwerveDrive.getInstance().getPose().getRotation().getDegrees());
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
