// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndScreen class.
 */
public class EndScreen implements Animation {
    private boolean hasWon;
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * end screen constructor.
     *
     * @param hasWon   if it is a winning
     * @param score    score
     * @param keyboard keyboard
     */
    public EndScreen(Boolean hasWon, Counter score, KeyboardSensor keyboard) {
        this.hasWon = hasWon;
        this.score = score;
        this.keyboard = keyboard;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (hasWon) {
            d.drawText(10, d.getHeight() / 2, "You won. Your score is " + this.score.getValue(), 30);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        } else {
            d.drawText(10, d.getHeight() / 2, "You lost. Your score is " + this.score.getValue(), 30);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
