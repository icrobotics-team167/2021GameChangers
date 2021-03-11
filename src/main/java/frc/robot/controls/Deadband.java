package frc.robot.controls;

public class Deadband {

    private final double size;

    /**
     * @param size The distance from minimum distance from 0 for which an input escapes the deadband; for instance, 0.02
     *             includes values in the interval (-0.02, 0.02) in the deadband.
     */
    public Deadband(double size) {
        this.size = Math.abs(size);
    }

    public double apply(double input) {
        return Math.abs(input) >= size ? input : 0;
    }

    public double getSize() {
        return size;
    }

}
