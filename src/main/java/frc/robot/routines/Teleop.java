package frc.robot.routines;

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
    }

    @Override
    public void periodic() {
        drivetrain.drive(controls.getVerticalVelocity(), controls.getHorizontalVelocity(), controls.getAngularVelocity());
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void cleanup() {

    }

}
