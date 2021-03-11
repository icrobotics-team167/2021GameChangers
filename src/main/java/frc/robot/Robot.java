package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.controls.controllers.PSController;
import frc.robot.controls.schemes.ControlScheme;
import frc.robot.controls.schemes.SingleController;
import frc.robot.subsystems.drive.SwerveDrive;

public class Robot extends TimedRobot {

    private ControlScheme controller;
    private SwerveDrive drivetrain;

    @Override
    public void robotInit() {
        controller = new SingleController(new PSController(0));

        drivetrain = SwerveDrive.getInstance();
        drivetrain.updateSensors();
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {
        drivetrain.updateSensors();
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        drivetrain.updateSensors();
    }

    @Override
    public void teleopPeriodic() {
        drivetrain.drive(
            controller.getHorizontalVelocity(),
            controller.getVerticalVelocity(),
            controller.getAngularVelocity()
        );
    }

}
