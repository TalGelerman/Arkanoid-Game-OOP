/*
Tal Gelerman 322280850
 */
import biuoop.DrawSurface;

/**
 * The Animation interface.
 */
public interface Animation {
    /**
     * Do one frame.
     * @param d  the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop.
     * @return true if should stop, false otherwise
     */
    boolean shouldStop();
}