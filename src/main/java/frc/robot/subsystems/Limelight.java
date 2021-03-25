package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    public enum LEDMode {
        PIPELINE(0),
        OFF(1),
        BLINK(2),
        ON(3);

        final int value;

        LEDMode(int value) {
            this.value = value;
        }
    }

    public enum OperationMode {
        VISION(0),
        CAMERA(1);

        final int value;

        OperationMode(int value) {
            this.value = value;
        }
    }

    public enum Pipeline {
        POWER_CELL(0),
        POWER_PORT(1);

        final int id;

        Pipeline(int id) {
            this.id = id;
        }
    }

    private static Limelight instance;
    public static Limelight getInstance() {
        if (instance == null) {
            instance = new Limelight();
        }
        return instance;
    }

    private LEDMode ledMode;
    private OperationMode operationMode;
    private Pipeline pipeline;
    private NetworkTable table;
    private double tv;
    private double tx;
    private double ty;
    private double ta;
    private double distance;

    private Limelight() {
        setLEDMode(LEDMode.PIPELINE);
        setOperationMode(OperationMode.VISION);
        setPipeline(Pipeline.POWER_CELL);
        update();
    }

    public void update() {
        table = getTable();
        tv = table.getEntry("tv").getDouble(0);
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        ta = table.getEntry("ta").getDouble(0);
    }

    public void setLEDMode(LEDMode mode) {
        getTable().getEntry("ledMode").setNumber(mode.value);
        ledMode = mode;
    }

    public void setOperationMode(OperationMode mode) {
        getTable().getEntry("camMode").setNumber(mode.value);
        operationMode = mode;
    }

    public void setPipeline(Pipeline pipeline) {
        getTable().getEntry("pipeline").setNumber(pipeline.id);
        this.pipeline = pipeline;
    }

    public LEDMode getLEDMode() {
        return ledMode;
    }

    public OperationMode getOperationMode() {
        return operationMode;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public boolean getTv() {
        return tv != 0;
    }

    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public double getTa() {
        return ta;
    }

    public double getDistanceInches() {
        // TODO Tune these values
        switch (pipeline) {
            case POWER_CELL:
                return 20 / Math.tan(Math.toRadians(20 - ty));
            case POWER_PORT:
                return 66.5 / Math.tan(Math.toRadians(29.0436377382 + ty));
            default:
                // This should be unreachable
                return 0;
        }
    }

    private NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

}
