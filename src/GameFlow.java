// * Tal Gelerman 322280850
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter counter;

    /**
     * constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param gui gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.animationRunner = new AnimationRunner(this.gui, new Sleeper());
        this.keyboardSensor = ks;
        this.counter = new Counter();
    }

    /**
     * runs the levels.
     * @param levels levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWinning = false;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.counter);

            level.initialize();

            //while ( level has more blocks and balls){
            while ((level.getBlockCounter().getValue() > 0) && level.getBallsCounter().getValue() > 0) {
                level.run();
            }

            if (level.getBallsCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                        new EndScreen(!(level.getBallsCounter().getValue() == 0), this.counter, this.keyboardSensor)));
                level.getGui().close();
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY,
                new EndScreen(true, this.counter, this.keyboardSensor)));
        gui.close();
    }
}