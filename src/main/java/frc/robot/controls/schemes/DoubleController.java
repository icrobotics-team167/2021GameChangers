package frc.robot.controls.schemes;

import frc.robot.controls.Deadband;
import frc.robot.controls.controllers.Controller;
import frc.robot.subsystems.drive.SwerveDrive;

public class DoubleController implements ControlScheme {

    private static final Deadband kStickDeadband = new Deadband(0.04);

    private final Controller primary;
    private final Controller secondary;

    public DoubleController(Controller primary, Controller secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public double getHorizontalVelocity() {
        return kStickDeadband.apply(-primary.getLeftStickX()) * SwerveDrive.kMaxVelocity;
    }

    @Override
    public double getVerticalVelocity() {
        return kStickDeadband.apply(primary.getLeftStickY()) * SwerveDrive.kMaxVelocity;
    }

    @Override
    public double getAngularVelocity() {
        return kStickDeadband.apply(-primary.getRightStickX()) * SwerveDrive.kMaxAngularVelocity;
    }

}
