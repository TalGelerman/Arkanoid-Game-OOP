// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * A class that represents a countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int count;
    private SpriteCollection gameScreen;
    private boolean running = true;
    private Sleeper sleeper;

    /**
     * countdown animation.
     *
     * @param numOfSeconds number of seconds
     * @param countFrom    number to count from
     * @param gameScreen   game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.sleeper = new Sleeper();
        this.count = this.countFrom;
    }

    /**
     * do one frame.
     * @param d  the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.count == 1) {
            this.running = false;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW);
        d.drawText(350, 350, this.count + "...", 28);
        if (this.count != this.countFrom) {
            this.sleeper.sleepFor((int) (this.numOfSeconds * 1000) / this.countFrom);
        }
        this.count--;
    }

    /**
     * check if the animation should stop.
     * @return true if the animation is running, false otherwise.
     */
    public boolean shouldStop() {
        return !running;
    }
}