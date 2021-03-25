package frc.robot.routines.actions;

import frc.robot.routines.Action;
import frc.robot.routines.paths.GalacticSearch;
import frc.robot.subsystems.Limelight;

public class ChooseGalacticSearchPath extends Action {

    // TODO Play around with this value
    // Lower values are quicker but less confident in the choice
    // Higher values are slower but more confident in the choice
    private static final int kPathChoiceThreshold = 5;

    private final Limelight limelight;
    private int redAGuesses;
    private int redBGuesses;
    private int blueAGuesses;
    private int blueBGuesses;

    public ChooseGalacticSearchPath() {
        super(2);
        limelight = Limelight.getInstance();
        redAGuesses = 0;
        redBGuesses = 0;
        blueAGuesses = 0;
        blueBGuesses = 0;
    }

    @Override
    public void init() {
        limelight.setPipeline(Limelight.Pipeline.POWER_CELL);
        limelight.setLEDMode(Limelight.LEDMode.PIPELINE);
        limelight.setOperationMode(Limelight.OperationMode.VISION);
    }

    @Override
    public void periodic() {
        limelight.update();

        // TODO Ensure these values work in real life
        // These values assume that in the red configuration, the robot starts on B1,
        // and in the blue configuration, the robot starts on D1. They also assume
        // that the Limelight pipeline is set to target the closest object.
        if (limelight.getDistanceInches() < 12 * 9) {
            if (limelight.getTx() >= 10) {
                redAGuesses++;
                if (redAGuesses >= kPathChoiceThreshold) {
                    GalacticSearch.path = GalacticSearch.Path.RED_A;
                }
            } else {
                redBGuesses++;
                if (redBGuesses >= kPathChoiceThreshold) {
                    GalacticSearch.path = GalacticSearch.Path.RED_B;
                }
            }
        } else {
            if (limelight.getTx() >= 5.5) {
                blueAGuesses++;
                if (blueAGuesses >= kPathChoiceThreshold) {
                    GalacticSearch.path = GalacticSearch.Path.BLUE_A;
                }
            } else {
                blueBGuesses++;
                if (blueBGuesses >= kPathChoiceThreshold) {
                    GalacticSearch.path = GalacticSearch.Path.BLUE_B;
                }
            }
        }
    }

    @Override
    public boolean isDone() {
        return GalacticSearch.path != null;
    }

    @Override
    public void cleanup() {

    }

}
