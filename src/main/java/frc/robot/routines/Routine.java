package frc.robot.routines;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Routine extends Action {

    private Queue<Action> actions;
    private Action current;

    public Routine(Action[] actions) {
        this(actions, -1);
    }

    public Routine(Action[] actions, double timeout) {
        super(timeout);
        this.actions = new LinkedList<>(Arrays.asList(actions));
        setState(AutoState.STARTUP);
    }

    @Override
    public void init() {
        current = actions.poll();
    }

    @Override
    public void periodic() {
        if (current != null) {
            if (current.getState() == AutoState.EXIT) {
                state = AutoState.EXIT;
            }
            if (current.getState() == AutoState.CONTINUE) {
                current = actions.poll();
                if (current == null) {
                    return;
                }
            }
            current.exec();
        }
    }

    @Override
    public boolean isDone() {
        return current == null;
    }

    @Override
    public void cleanup() {}

}
