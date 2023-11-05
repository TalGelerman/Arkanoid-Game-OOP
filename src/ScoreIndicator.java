// * Tal Gelerman 322280850
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * score indicator class.
 */
public class ScoreIndicator implements Sprite, HitNotifier {
    private Counter scoreCounter;
    private String levelName;

    /**
     * score indicator constructor.
     *
     * @param scoreCounter score counter
     * @param levelName    level name
     */
    public ScoreIndicator(Counter scoreCounter, String levelName) {
        this.scoreCounter = scoreCounter;
        this.levelName = levelName;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit listener
     */
    public void addHitListener(HitListener hl) {
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener
     */
    public void removeHitListener(HitListener hl) {
    }

    /**
     * draws the score on a draw surface.
     *
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(367, 15, "Score: " + this.scoreCounter.getValue(), 17);
        d.drawText(600, 15, "Level: " + this.levelName, 17);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
