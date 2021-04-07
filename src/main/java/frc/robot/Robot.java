package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.controllers.PSController;
import frc.robot.controls.schemes.ControlScheme;
import frc.robot.controls.schemes.SingleController;
import frc.robot.routines.Action;
import frc.robot.routines.Routine;
import frc.robot.routines.Simulation;
import frc.robot.routines.Teleop;
import frc.robot.routines.actions.ChooseGalacticSearchPath;
import frc.robot.routines.actions.FollowPath;
import frc.robot.routines.actions.TuneMotorSpeeds;
import frc.robot.routines.paths.AutoNav;
import frc.robot.routines.paths.GalacticSearch;
import frc.robot.routines.paths.TestPaths;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.drive.SwerveDrive;

public class Robot extends TimedRobot {

    private ControlScheme controls;
    private Action auto;
    private Teleop teleop;
    private Simulation simulation;

    @Override
    public void robotInit() {
        controls = new SingleController(new PSController(0));

        auto = new Routine(new Action[]{
            // Galactic Search Challenge
//            new ChooseGalacticSearchPath(),
//            new FollowPath(GalacticSearch.getPath()),

            // AutoNav Challenge
           new FollowPath(AutoNav.kBarrelRacingPath, false),

            // Testing
//            new TuneMotorSpeeds(1.5, 5, 1)
//            new FollowPath(TestPaths.kLine),
        });

        teleop = new Teleop(controls);

        if (RobotBase.isSimulation()) {
            simulation = new Simulation(TestPaths.kLine);
        }
    }

    @Override
    public void robotPeriodic() {
        // Odometry
        SmartDashboard.putNumber("X (+ = forward)", SwerveDrive.getInstance().getPose().getX());
        SmartDashboard.putNumber("Y (+ = left)", SwerveDrive.getInstance().getPose().getY());
        SmartDashboard.putNumber("Angle (+ = CCW)", SwerveDrive.getInstance().getPose().getRotation().getDegrees());
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
        Limelight.getInstance().setLEDMode(Limelight.LEDMode.OFF);
    }

    @Override
    public void simulationInit() {
        simulation.exec();
    }

    @Override
    public void simulationPeriodic() {
        simulation.exec();
    }

}
