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

public class TestPaths {

    private static final TrajectoryConfig config = SwerveDrive.getInstance().getTrajectoryConfig();

    public static final Trajectory kLine = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(5), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(7.5))
        ),
        new Pose2d(Units.feetToMeters(10), Units.feetToMeters(7.5), new Rotation2d(0)),
        config
    );

    public static final Trajectory kQuarterCircle = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(0)),
        List.of(
            new Translation2d(Units.feetToMeters(2.5 + (5 / Math.sqrt(2))), Units.feetToMeters(2.5 + (5 / Math.sqrt(2))))
        ),
        new Pose2d(Units.feetToMeters(7.5), Units.feetToMeters(2.5), new Rotation2d(-Math.PI / 2)),
        config
    );

    public static final Trajectory kLetterS = TrajectoryGenerator.generateTrajectory(
        new Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), new Rotation2d(-Math.PI / 2)),
        List.of(
            new Translation2d(Units.feetToMeters(5), Units.feetToMeters(5)),
            new Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(7.5)),
            new Translation2d(Units.feetToMeters(10), Units.feetToMeters(10))
        ),
        new Pose2d(Units.feetToMeters(12.5), Units.feetToMeters(7.5), new Rotation2d(-Math.PI / 2)),
        config
    );

}
