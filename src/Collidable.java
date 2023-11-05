// * Tal Gelerman 322280850
/**
 * things that can be collided with.
 */
public interface Collidable {
    /**
     * check the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * calculates the new velocity after the hit.
     * @param collisionPoint the collision point
     * @param currentVelocity current velocity
     * @param hitter ball
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}