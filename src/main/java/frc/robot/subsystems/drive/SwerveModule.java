package frc.robot.subsystems.drive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;

public class SwerveModule {

    public static final double kWheelDiameter = Units.inchesToMeters(4.0);
    public static final double kDriveMotorReduction = 8.31;
    public static final double kAngleMotorReduction = 18.0;

    // TODO Tune these gains
    private static final double kDriveP = 0.25;
    private static final double kDriveI = 0;
    private static final double kDriveD = 0;
    private static final double kDriveFF = 0.265;

    private static final double kAngleP = 0.5;
    private static final double kAngleI = 0;
    private static final double kAngleD = 0.25;
    private static final double kAngleFF = 0;

    private final Translation2d location;

    private final int drivePort;
    private final CANSparkMax driveMotor;
    private final CANEncoder driveEncoder;
    private final CANPIDController driveController;

    private final int anglePort;
    private final CANSparkMax angleMotor;
    private final CANEncoder angleEncoder;
    private final CANPIDController angleController;

    private double currentDistance = 0;
    private double currentAngle = 0;

    private double targetSpeed = 0;
    private double targetAngle = 0;

    private Translation2d currentPosition = new Translation2d();
    private double previousDistance = 0;

    public SwerveModule(Translation2d location, int drivePort, int anglePort) {
        this.location = location;

        this.drivePort = drivePort;
        driveMotor = new CANSparkMax(drivePort, MotorType.kBrushless);
        driveMotor.setIdleMode(IdleMode.kBrake);
        driveMotor.setInverted(false);
        driveMotor.setSmartCurrentLimit(40);
        driveMotor.setSecondaryCurrentLimit(55);
        driveEncoder = driveMotor.getEncoder();
        driveEncoder.setPositionConversionFactor(kWheelDiameter * Math.PI / kDriveMotorReduction);
        driveEncoder.setVelocityConversionFactor(kWheelDiameter * Math.PI / kDriveMotorReduction * (1 / 60D));

        driveController = driveMotor.getPIDController();
        driveController.setP(kDriveP);
        driveController.setI(kDriveI);
        driveController.setD(kDriveD);
        driveController.setFF(kDriveFF);

        this.anglePort = anglePort;
        angleMotor = new CANSparkMax(anglePort, MotorType.kBrushless);
        angleMotor.setIdleMode(IdleMode.kBrake);
        angleMotor.setInverted(false);
        // TODO Check whether these current limits can be lowered to 30 A without issue
        angleMotor.setSmartCurrentLimit(40);
        angleMotor.setSecondaryCurrentLimit(55);
        angleEncoder = angleMotor.getEncoder();
        angleEncoder.setPositionConversionFactor(2 * Math.PI / kAngleMotorReduction);
        angleEncoder.setVelocityConversionFactor(2 * Math.PI / kAngleMotorReduction * (1 / 60D));

        angleController = angleMotor.getPIDController();
        angleController.setP(kAngleP);
        angleController.setI(kAngleI);
        angleController.setD(kAngleD);
        angleController.setFF(kAngleFF);
    }

    public void updateState() {
        double currentAngle = getCurrentAngle();
        double delta = targetAngle - currentAngle;

        if (delta >= Math.PI) {
            targetAngle -= 2 * Math.PI;
        } else if (delta < -Math.PI) {
            targetAngle += 2 * Math.PI;
        }

        delta = targetAngle - currentAngle;
        if (delta > Math.PI / 2 || delta < -Math.PI / 2) {
            targetSpeed *= -1;
            targetAngle += Math.PI;
        }

        targetAngle %= 2 * Math.PI;
        if (targetAngle < 0) {
            targetAngle += 2 * Math.PI;
        }

        setTargetAngle(targetAngle);
        setDriveOutput(targetSpeed);
    }

    public void updateSensors() {
        currentAngle = getAngle();
        currentDistance = getDistance();
    }

    public void setDriveOutput(double speed) {
        driveController.setReference(speed, ControlType.kVelocity);

        if (drivePort == 5) {
            SmartDashboard.putNumber("module/actualSpeed", getVelocity());
            SmartDashboard.putNumber("module/targetSpeed", speed);
        }
    }

    public void setTargetAngle(double angle) {
        double currentAngle = angleEncoder.getPosition();
        double currentAngleSimplified = getAngle();
        double newTargetAngle = currentAngle + angle - currentAngleSimplified;

        if (newTargetAngle - currentAngle >= Math.PI) {
            newTargetAngle -= 2 * Math.PI;
        } else if (newTargetAngle - currentAngle < -Math.PI) {
            newTargetAngle += 2 * Math.PI;
        }

        angleController.setReference(newTargetAngle, ControlType.kPosition);

        if (anglePort == 6) {
            SmartDashboard.putNumber("module/actualAngle", currentAngle);
            SmartDashboard.putNumber("module/targetAngle", newTargetAngle);
        }
    }

    public void setTargetVelocity(Translation2d velocity) {
        setTargetVelocity(velocity.getNorm(), new Rotation2d(velocity.getX(), velocity.getY()).getRadians());
    }

    public void setTargetVelocity(double speed, double angle) {
        if (speed < 0) {
            speed *= -1;
            angle += Math.PI;
        }

        angle %= 2 * Math.PI;
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        targetSpeed = speed;
        targetAngle = angle;
    }

    public void resetKinematics() {
        setKinematics(new Translation2d());
    }

    public void updateKinematics(double rotation) {
        double currentDistance = getCurrentDistance();
        double deltaDistance = currentDistance - previousDistance;
        double currentAngle = getCurrentAngle() + rotation;

        Translation2d deltaPosition = new Translation2d(deltaDistance, new Rotation2d(currentAngle));

        currentPosition = getCurrentPosition().plus(deltaPosition);
        previousDistance = currentDistance;
    }

    public void setKinematics(Translation2d position) {
        currentPosition = position;
    }

    public double getDistance() {
        return driveEncoder.getPosition();
    }

    public double getVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getAngle() {
        double angle = angleEncoder.getPosition();
        angle %= 2 * Math.PI;
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public double getAngularVelocity() {
        return angleEncoder.getVelocity();
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public Translation2d getTargetVelocity() {
        return new Translation2d(Math.cos(targetAngle) * targetSpeed, Math.sin(targetAngle) * targetSpeed);
    }

    public Translation2d getCurrentPosition() {
        return currentPosition;
    }

    public SwerveModuleState getCurrentState() {
        return new SwerveModuleState(getVelocity(), new Rotation2d(angleEncoder.getPosition()));
    }

}
