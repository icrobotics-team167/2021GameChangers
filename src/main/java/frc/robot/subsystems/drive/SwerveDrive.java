package frc.robot.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.util.Units;

public class SwerveDrive {

    public static final double kLength = Units.inchesToMeters(30); // m
    public static final double kWidth = Units.inchesToMeters(30); // m
    // NOTE Limit these values during early testing rather than directly slowing the output (after a test on blocks to avoid an accident).
    public static final double kMaxVelocity = 3; // m/s
    public static final double kMaxAngularVelocity = Math.PI; // rad/s
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

        navx.calibrate();
    }

    public void drive(double vx, double vy, double omega) {
        updateSensors();
        updateState();

        ChassisSpeeds speeds = new ChassisSpeeds(vx, vy, omega);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);

        frontLeft.setTargetVelocity(states[0].speedMetersPerSecond, states[0].angle.getRadians());
        frontRight.setTargetVelocity(states[1].speedMetersPerSecond, states[1].angle.getRadians());
        backLeft.setTargetVelocity(states[2].speedMetersPerSecond, states[2].angle.getRadians());
        backRight.setTargetVelocity(states[3].speedMetersPerSecond, states[3].angle.getRadians());
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

}
