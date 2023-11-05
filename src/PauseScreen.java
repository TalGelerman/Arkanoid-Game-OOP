// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * pause screen Constructor.
     *
     * @param k the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}