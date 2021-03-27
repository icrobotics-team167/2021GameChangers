package frc.robot.routines.actions;

import edu.wpi.first.wpilibj.controller.HolonomicDriveController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import frc.robot.routines.Action;
import frc.robot.routines.Timer;
import frc.robot.subsystems.drive.SwerveDrive;

public class FollowPath extends Action {

    private static final double kHorizontalP = 1;
    private static final double kHorizontalI = 0;
    private static final double kHorizontalD = 0;
    private static final double kVerticalP = 1;
    private static final double kVerticalI = 0;
    private static final double kVerticalD = 0;
    private static final double kAngleP = 1;
    private static final double kAngleI = 0;
    private static final double kAngleD = 0;
    private static final double kMaxAngularVelocity = SwerveDrive.kMaxAngularVelocity;
    private static final double kMaxAngularAcceleration = SwerveDrive.kMaxAngularAcceleration;

    private final SwerveDrive drivetrain;
    private final Trajectory trajectory;
    private final HolonomicDriveController controller;
    private final Timer timer;
    private final boolean stayStraight;

    public FollowPath(Trajectory trajectory) {
        this(trajectory, false);
    }

    public FollowPath(Trajectory trajectory, boolean stayStraight) {
        super();

        drivetrain = SwerveDrive.getInstance();

        this.trajectory = trajectory;

        PIDController horizontalController = new PIDController(kHorizontalP, kHorizontalI, kHorizontalD);
        PIDController verticalController = new PIDController(kVerticalP, kVerticalI, kVerticalD);
        ProfiledPIDController angleController = new ProfiledPIDController(kAngleP, kAngleI, kAngleD, new TrapezoidProfile.Constraints(kMaxAngularVelocity, kMaxAngularAcceleration));
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        controller = new HolonomicDriveController(horizontalController, verticalController, angleController);

        timer = new Timer();

        this.stayStraight = stayStraight;
    }

    @Override
    public void init() {
        drivetrain.resetPose(trajectory.getInitialPose());
        timer.reset();
    }

    @Override
    public void periodic() {
        Trajectory.State goal = trajectory.sample(timer.get());
        ChassisSpeeds speeds = controller.calculate(drivetrain.getPose(), goal, stayStraight ? new Rotation2d() : goal.poseMeters.getRotation());
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isDone() {
        return timer.hasElapsed(trajectory.getTotalTimeSeconds());
    }

    @Override
    public void cleanup() {}

}
