package frc.robot.routines.actions;

import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import frc.robot.routines.Action;
import frc.robot.routines.Timer;
import frc.robot.subsystems.drive.SwerveDrive;

public class TuneMotorSpeeds extends Action {

    private final SwerveDrive drivetrain;
    private final Timer timer;
    private final double maxSpeed;
    private final double totalTime;
    private final double rampTime;

    public TuneMotorSpeeds(double maxSpeed, double totalTime) {
        this(maxSpeed, totalTime, 0);
    }

    public TuneMotorSpeeds(double maxSpeed, double totalTime, double rampTime) {
        super();

        drivetrain = SwerveDrive.getInstance();

        timer = new Timer();

        if (maxSpeed > SwerveDrive.kMaxVelocity) {
            maxSpeed = SwerveDrive.kMaxVelocity;
        }
        if (maxSpeed < 0) {
            maxSpeed = 0;
        }

        this.maxSpeed = maxSpeed;
        this.totalTime = totalTime;
        this.rampTime = rampTime;
    }

    @Override
    public void init() {
        timer.reset();
    }

    @Override
    public void periodic() {
        double percentageOutput = rampTime != 0 ? timer.get() / rampTime : 1;
        if (percentageOutput > 1) {
            percentageOutput = 1;
        }
        if (percentageOutput < 0) {
            percentageOutput = 0;
        }

        double output = percentageOutput * maxSpeed;

        drivetrain.drive(new ChassisSpeeds(output, 0, 0));
    }

    @Override
    public boolean isDone() {
        return timer.hasElapsed(totalTime);
    }

    @Override
    public void cleanup() {
        drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }

}
