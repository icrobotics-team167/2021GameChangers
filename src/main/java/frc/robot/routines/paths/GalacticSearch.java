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

    public static Trajectory getPath() {
        switch (path) {
            case RED_A:
                return kRedAPath;
            case RED_B:
                return kRedBPath;
            case BLUE_A:
                return kBlueAPath;
            case BLUE_B:
                return kBlueBPath;
            default:
                // This should be unreachable
                return new Trajectory();
        }
    }

    private static final TrajectoryConfig config = SwerveDrive.getInstance().getTrajectoryConfig();

    public static final Trajectory kRedAPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(10), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(12.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(13.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kRedBPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(10), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(10)),
            new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(10))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(12.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kBlueAPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(2.5)),
            new Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(10)),
            new Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(3.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kBlueBPath = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(1.25), Units.feetToMeters(5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(15), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(20), Units.feetToMeters(10)),
            new Translation2d(Units.feetToMeters(25), Units.feetToMeters(5))
        ),
        new Pose2d(Units.feetToMeters(28.75), Units.feetToMeters(4.5), new Rotation2d(0)),
        config
    );

}
