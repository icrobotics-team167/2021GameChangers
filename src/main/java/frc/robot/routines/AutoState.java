package frc.robot.routines;

public enum AutoState {
    STARTUP, // Initialization of the Action
    RUNNING, // Periodic updates of the Action
    CLEANUP, // Cleanup of the Action
    CONTINUE, // The Action is completed, and the next Action should begin
    EXIT, // The Action failed, and no further Actions should run
}
