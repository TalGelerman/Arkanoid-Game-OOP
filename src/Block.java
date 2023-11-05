// * Tal Gelerman 322280850

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * the block constructor.
     *
     * @param rectangle a rectangle
     */
    public Block(Rectangle rectangle) {
        this.collisionRectangle = rectangle;
    }

    /**
     * access to collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * check if a point is on a rectangle.
     *
     * @param collisionPoint a point for the checking
     * @return true if the
     */
    public boolean isOnTheRectangle(Point collisionPoint) {
        if (collisionPoint.getX() > this.collisionRectangle.getRightLine().start().getX()
                || collisionPoint.getX() > this.collisionRectangle.getLeftLine().start().getX()
                || collisionPoint.getY() < this.collisionRectangle.getTopLine().start().getY()
                || collisionPoint.getY() > this.collisionRectangle.getBotLine().start().getY()) {
            return false;
        }
        return true;
    }

    /**
     * check is a point is on a corner of a rectangle.
     *
     * @param collisionPoint the point for checking
     * @return true is the point in on a corner and false if it doesn't
     */
    public boolean isCorner(Point collisionPoint) {
        //check the top corners options
        if (this.collisionRectangle.getTopLine().pointInLine(collisionPoint)) {
            if (this.collisionRectangle.getRightLine().pointInLine(collisionPoint)
                    || (this.collisionRectangle.getLeftLine().pointInLine(collisionPoint))) {
                return true;
            }
        }
        //checks the bottom corners options
        if (this.collisionRectangle.getBotLine().pointInLine(collisionPoint)) {
            if (this.collisionRectangle.getRightLine().pointInLine(collisionPoint)
                    || (this.collisionRectangle.getLeftLine().pointInLine(collisionPoint))) {
                return true;
            }
        }
        return false;
    }

    /**
     * check is a velocity is vertical or horizontal.
     *
     * @param v the velocity
     * @return new velocity according to the cases
     */
    public Velocity cornerCase(Velocity v) {
        if (v.getDy() == 0) {
            return new Velocity(-v.getDx(), v.getDy());
        } else if (v.getDx() == 0) {
            return new Velocity(v.getDx(), -v.getDy());
        } else {
            return new Velocity(-v.getDx(), v.getDy());
        }
    }

    /**
     * draw a block on a surface.
     *
     * @param surface a surface that the block will be drawn on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.collisionRectangle.getColor());
        surface.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(), (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * indicates the block the time had passed.
     */
    public void timePassed() {
    }

    /**
     * adds block to collidables and sprites.
     *
     * @param game game
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * remove a collidable from a game.
     *
     * @param game game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * update the velocity after the hit.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity current velocity
     * @param hitter          ball
     * @return velocity expected after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        if (isCorner(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else {
            if (Line.doubleEquals(collisionPoint.getX(), this.collisionRectangle.getUpperLeft().getX())
                    || (Line.doubleEquals(collisionPoint.getX(), this.collisionRectangle.getUpperLeft().getX()
                    + this.collisionRectangle.getWidth()))) {
                newDx = -newDx;
            }
            if (Line.doubleEquals(collisionPoint.getY(), this.collisionRectangle.getUpperLeft().getY())
                    || (Line.doubleEquals(collisionPoint.getY(), this.collisionRectangle.getUpperLeft().getY()
                    + this.collisionRectangle.getHeight()))) {
                newDy = -newDy;
            }
            this.notifyHit(hitter);
            return new Velocity(newDx, newDy);
        }
    }

    /**
     * sets color.
     *
     * @param color a color
     */
    public void setColor(Color color) {
        this.collisionRectangle.setColor(color);
        this.collisionRectangle.setColor(color);
    }

    /**
     * notifies the hits.
     *
     * @param hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds a hit listener.
     *
     * @param hl hit listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes a hit listener.
     *
     * @param hl hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
