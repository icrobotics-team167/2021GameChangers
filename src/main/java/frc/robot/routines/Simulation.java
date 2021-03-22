package frc.robot.routines;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class Simulation extends Action {

    private final Field2d field;
    private final Timer timer;
    private final Trajectory path;

    public Simulation(Trajectory path) {
        super();
        field = new Field2d();
        timer = new Timer();
        this.path = path;
    }

    @Override
    public void init() {
        timer.reset();
        SmartDashboard.putNumber("Total Path Time", path.getTotalTimeSeconds());
        SmartDashboard.putData("Target Robot Path", field);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Path Time", timer.get());
        if (timer.hasElapsed(path.getTotalTimeSeconds())) {
            timer.reset();
        } else {
            Pose2d pose = path.sample(timer.get()).poseMeters;
            field.setRobotPose(pose);
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void cleanup() {}

}
