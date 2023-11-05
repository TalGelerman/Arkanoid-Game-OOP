import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * access to gui.
     * @return gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * constructor.
     * @param gui gui
     * @param sleeper sleeper
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.sleeper = sleeper;
        this.framesPerSecond = 60;
    }

    /**
     * set frames per second.
     * @param num number
     */
    public void setSleeper(int num) {
        this.sleeper.sleepFor(num);
    }

    /**
     * runs the animation.
     * @param animation animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}