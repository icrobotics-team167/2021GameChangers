package frc.robot.actions;

public class Timer {

    private double startTime;
    private boolean hasElapsedOnceFlag;

    public Timer() {
        reset();
    }

    public void reset() {
        startTime = getAbsoluteTime();
        hasElapsedOnceFlag = false;
    }

    public boolean hasElapsed(double seconds) {
        return get() >= seconds;
    }

    public boolean hasElapsedOnce(double seconds) {
        if (!hasElapsedOnceFlag && hasElapsed(seconds)) {
            hasElapsedOnceFlag = true;
            return true;
        }
        return false;
    }

    public double get() {
        return getAbsoluteTime() - startTime;
    }

    private double getAbsoluteTime() {
        return edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
    }

}
