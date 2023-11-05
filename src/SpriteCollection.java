// * Tal Gelerman 322280850

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * sprites collection class.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * adds a sprite to the sprites' list.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes a sprite.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spriteArrayList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spriteArrayList) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d a draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
