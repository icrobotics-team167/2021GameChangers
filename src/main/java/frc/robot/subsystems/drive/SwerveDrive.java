package frc.robot.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.util.Units;

public class SwerveDrive {

    public static final double kLength = Units.inchesToMeters(23 - 2 * 3.25); // 16.5 m
    public static final double kWidth = Units.inchesToMeters(23 - 2 * 3.25); // 16.5 m
    // NOTE Limit these values during early testing rather than directly slowing the output (after a test on blocks to avoid an accident).
    // NOTE We should test 3 m/s and 3 m/s^2
    public static final double kMaxVelocity = 1; // m/s
    public static final double kMaxAcceleration = 1; // m/s^2
    public static final double kMaxAngularVelocity = Math.PI; // rad/s
    public static final double kMaxAngularAcceleration = Math.PI; // rad/s^2
    public static final class Ports {
        public static final int kFrontLeftDrive = 8;
        public static final int kFrontLeftAngle = 7;
        public static final int kFrontRightDrive = 1;
        public static final int kFrontRightAngle = 2;
        public static final int kBackLeftDrive = 5;
        public static final int kBackLeftAngle = 6;
        public static final int kBackRightDrive = 3;
        public static final int kBackRightAngle = 4;
    }
    public static final class Locations {
        public static final Translation2d kFrontLeft = new Translation2d(kLength / 2, kWidth / 2);
        public static final Translation2d kFrontRight = new Translation2d(kLength / 2, -kWidth / 2);
        public static final Translation2d kBackLeft = new Translation2d(-kLength / 2, kWidth / 2);
        public static final Translation2d kBackRight = new Translation2d(-kLength / 2, -kWidth / 2);
    }

    private static SwerveDrive instance;
    public static SwerveDrive getInstance() {
        if (instance == null) {
            instance = new SwerveDrive();
        }
        return instance;
    }

    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;
    private final SwerveModule[] modules;

    private final SwerveDriveKinematics kinematics;

    private AHRS navx;

    private final SwerveDriveOdometry odometry;

    private SwerveDrive() {
        frontLeft = new SwerveModule(Locations.kFrontLeft, Ports.kFrontLeftDrive, Ports.kFrontLeftAngle);
        frontRight = new SwerveModule(Locations.kFrontRight, Ports.kFrontRightDrive, Ports.kFrontRightAngle);
        backLeft = new SwerveModule(Locations.kBackLeft, Ports.kBackLeftDrive, Ports.kBackLeftAngle);
        backRight = new SwerveModule(Locations.kBackRight, Ports.kBackRightDrive, Ports.kBackRightAngle);
        modules = new SwerveModule[]{frontLeft, frontRight, backLeft, backRight};

        for (SwerveModule module : modules) {
            module.resetKinematics();
        }

        kinematics = new SwerveDriveKinematics(Locations.kFrontLeft, Locations.kFrontRight, Locations.kBackLeft, Locations.kBackRight);

        try {
            navx = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException e) {
            DriverStation.reportError("Error initializing the navX over SPI: " + e.toString(), e.getStackTrace());
        }

        navx.reset();

        odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(getAngle()));
    }

    public void drive(double vx, double vy, double omega) {
        // Field-centric
        double angle = getAngle() % 360;
        drive(ChassisSpeeds.fromFieldRelativeSpeeds(vx, vy, omega, Rotation2d.fromDegrees(angle)));

        // Robot-centric
//        drive(new ChassisSpeeds(vx, vy, omega));
    }

    public void drive(ChassisSpeeds speeds) {
        updateSensors();
        updateState();

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);

        frontLeft.setTargetVelocity(states[0].speedMetersPerSecond, states[0].angle.getRadians());
        frontRight.setTargetVelocity(states[1].speedMetersPerSecond, states[1].angle.getRadians());
        backLeft.setTargetVelocity(states[2].speedMetersPerSecond, states[2].angle.getRadians());
        backRight.setTargetVelocity(states[3].speedMetersPerSecond, states[3].angle.getRadians());

        SwerveModuleState frontLeftState = frontLeft.getCurrentState();
        SwerveModuleState frontRightState = frontRight.getCurrentState();
        SwerveModuleState backLeftState = backLeft.getCurrentState();
        SwerveModuleState backRightState = backRight.getCurrentState();

        odometry.update(Rotation2d.fromDegrees(getAngle()), frontLeftState, frontRightState, backLeftState, backRightState);
    }

    public void resetPose(Pose2d pose) {
        odometry.resetPosition(pose, Rotation2d.fromDegrees(getAngle()));
    }

    public void updateSensors() {
        for (SwerveModule module : modules) {
            module.updateSensors();
        }
    }

    public void updateState() {
        for (SwerveModule module : modules) {
            module.updateState();
        }
    }

    public double getAngle() {
        // By default, the navX associates + with clockwise, but WPILib uses + for counterclockwise (like in math),
        // so we negate the angle to make the odometry work properly
        return -navx.getAngle();
    }

    public SwerveDriveKinematics getKinematics() {
        return kinematics;
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

}
