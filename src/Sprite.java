import biuoop.DrawSurface;

/**
 * a game object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

}