// * Tal Gelerman 322280850

/**
 * The point class, point creation and different functions.
 */

public class Point {
    public static final double EPSI = Math.pow(10, -10);
    private double x;
    private double y;

    /**
     * The point constructor.
     *
     * @param x (the point's x value)
     * @param y (the point's y value)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function calculates the distance between two points.
     *
     * @param other ( a point
     * @return the distance between to points
     */
    public double distance(Point other) {
        return Math.sqrt(((other.x - this.x) * (other.x - this.x)) + ((other.y - this.y) * (other.y - this.y)));
    }

    /**
     * The function compares between two points.
     *
     * @param other (other point to compare with)
     * @return true if the points are equal and false if they aren't
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.x - other.getX()) < EPSI) && (Math.abs(this.y - other.getY()) < EPSI)) {
            return true;
        }
        return false;
    }

    /**
     * Gives access to the x value of the point.
     *
     * @return the point's x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gives access to the y value of the point.
     *
     * @return the point's y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * changing the value of x.
     *
     * @param x new value for x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * changing the value of y.
     *
     * @param y new value for y
     */
    public void setY(double y) {
        this.y = y;
    }

//    public String toString() {
//        return "(" + this.getX() + " , " + this.getY() + ")";
//    }

}
