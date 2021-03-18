package frc.robot.routines;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.controls.schemes.ControlScheme;
import frc.robot.subsystems.drive.SwerveDrive;

public class Teleop extends Action {

    private ControlScheme controls;
    private SwerveDrive drivetrain;

    public Teleop(ControlScheme controls) {
        super();
        this.controls = controls;
        drivetrain = SwerveDrive.getInstance();
    }

    @Override
    public void init() {
        drivetrain.updateSensors();
        drivetrain.resetPose(new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
    }

    @Override
    public void periodic() {
        drivetrain.drive(controls.getHorizontalVelocity(), controls.getVerticalVelocity(), controls.getAngularVelocity());
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void cleanup() {

    }

}
