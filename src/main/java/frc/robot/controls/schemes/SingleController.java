package frc.robot.controls.schemes;

import frc.robot.controls.Deadband;
import frc.robot.controls.controllers.Controller;
import frc.robot.subsystems.drive.SwerveDrive;

public class SingleController implements ControlScheme {

    private static final Deadband kStickDeadband = new Deadband(0.05);

    private final Controller primary;

    public SingleController(Controller primary) {
        this.primary = primary;
    }

    @Override
    public double getHorizontalVelocity() {
        return kStickDeadband.apply(primary.getLeftStickX()) * (SwerveDrive.kMaxVelocity / Math.sqrt(2));
    }

    @Override
    public double getVerticalVelocity() {
        return kStickDeadband.apply(primary.getLeftStickY()) * (SwerveDrive.kMaxVelocity / Math.sqrt(2));
    }

    @Override
    public double getAngularVelocity() {
        return kStickDeadband.apply(primary.getRightStickX()) * SwerveDrive.kMaxAngularVelocity;
    }

}
