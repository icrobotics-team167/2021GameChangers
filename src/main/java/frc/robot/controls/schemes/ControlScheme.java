package frc.robot.controls.schemes;

public interface ControlScheme {

    // Driving
    double getHorizontalVelocity();
    double getVerticalVelocity();
    double getAngularVelocity();

}
