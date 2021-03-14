package frc.robot.actions;

import edu.wpi.first.wpilibj.DriverStation;

public abstract class Action {

    public abstract void init();
    public abstract void periodic();
    public abstract boolean isDone();
    public abstract void cleanup();

    protected AutoState state;
    protected double timeout;
    protected Timer timer;

    /**
     * Creates an Action without a timeout
     */
    public Action() {
        this(-1);
    }

    /**
     * Creates an Action with a timeout
     * @param timeout The seconds required to timeout
     */
    public Action(double timeout) {
        state = AutoState.STARTUP;
        this.timeout = timeout;
        timer = new Timer();
    }

    public void exec() {
        if (state == null) {
            return;
        }
        if (timeout >= 0 && state != AutoState.CLEANUP && state != AutoState.CONTINUE && state != AutoState.EXIT && timer.hasElapsed(timeout)) {
            setState(AutoState.EXIT);
            DriverStation.reportWarning("Action \"" + this.getClass().getName() + "\" timed out after " + timeout + " seconds", false);
        }
        switch (state) {
            case STARTUP:
                init();
                setState(AutoState.RUNNING);
                break;
            case RUNNING:
                periodic();
                if (isDone()) {
                    setState(AutoState.CLEANUP);
                }
                break;
            case CLEANUP:
                cleanup();
                setState(AutoState.CONTINUE);
                break;
            case CONTINUE:
                break;
            case EXIT:
                cleanup();
                state = null;
                DriverStation.reportWarning("Exiting Action \"" + this.getClass().getName() + "\"", false);
                break;
            default:
                break;
        }
    }

    public void setState(AutoState state) {
        this.state = state;
    }

    public AutoState getState() {
        return state;
    }

    public double getTimeout() {
        return timeout;
    }

}
