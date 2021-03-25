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

public class GalacticSearch {

    public enum Path {
        RED_A,
        RED_B,
        BLUE_A,
        BLUE_B,
    }

    public static Path path = null;

    private static final TrajectoryConfig config = new TrajectoryConfig(SwerveDrive.kMaxVelocity, SwerveDrive.kMaxAcceleration)
        .setKinematics(SwerveDrive.getInstance().getKinematics());

    // TODO Add real paths and a mechanism for following them with varying angles
    public static final Trajectory kRedAPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kRedBPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kBlueAPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kBlueBPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

}
