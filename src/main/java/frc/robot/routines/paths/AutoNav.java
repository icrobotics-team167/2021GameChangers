package frc.robot.routines.paths;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.subsystems.drive.SwerveDrive;

import java.util.List;

public class AutoNav {

    private static final TrajectoryConfig config = new TrajectoryConfig(SwerveDrive.kMaxVelocity, SwerveDrive.kMaxAcceleration)
        .setKinematics(SwerveDrive.getInstance().getKinematics());

    public static final Trajectory kBarrelRacingPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            // First loop
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(10), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(7.5)),
            // Second loop
            new Translation2d(Units.feetToMeters(20), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(10)),
            new Translation2d(Units.feetToMeters(20), Units.feetToMeters(12.5)),
            new Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(10)),
            // Third loop
            new Translation2d(Units.feetToMeters(25), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(25), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kSlalomPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(2.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(25), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(27.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(25), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(5))
        ),
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kBouncePath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(12.5)),
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(12.5)),
            new Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(20), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(12.5))
        ),
        new Pose2d(Units.feetToMeters(27.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kTestLine = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(5), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(10), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kTestCurve = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(2.5 + (5 / Math.sqrt(2))), Units.feetToMeters(2.5 + (5 / Math.sqrt(2))))
        ),
        new Pose2d(Units.feetToMeters(7.5), Units.feetToMeters(2.5), new Rotation2d(0)),
        config
    );

}
