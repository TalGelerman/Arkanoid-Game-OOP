// * Tal Gelerman 322280850

/**
 * The velocity class, velocity creation and different functions.
 */
public class Velocity {
    private double dx;
    private double dy;



    /**
     * The velocity constructor.
     * @param dx dx for velocity
     * @param dy dy for velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * moves the point to another point.
     * @param p the new points value
     * @return the point with new values
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * calculating the dx and dy.
     * @param angle the angle the ball comes with
     * @param speed the speed teh ball comes with
     * @return the converted dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * access to the point dx.
     * @return the dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * access to the point dy.
     * @return the dy value
     */
    public double getDy() {
        return this.dy;
    }
}