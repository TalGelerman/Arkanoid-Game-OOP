// * Tal Gelerman 322280850

import java.util.List;

/**
 * The line class, line creation and different functions.
 */
public class Line {
    private Point start;
    private Point middle;
    private Point end;
    private double m;
    private double b;

    /**
     * The line constructor.
     *
     * @param start The line's starting point
     * @param end   The line's ending point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if ((end.getX() - start.getX() != 0)) {
            this.m = ((end.getY() - start.getY()) / (end.getX() - start.getX()));
        }
        this.b = start.getY() - (m * start().getX());
    }

    /**
     * Another line constructor.
     *
     * @param x1 x value of starting point.
     * @param y1 y value of starting point.
     * @param x2 x value of ending point.
     * @param y2 y value of ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        ///checks if the slope is not zero
        if ((end.getX() - start.getX() != 0)) {
            this.m = ((end.getY() - start.getY()) / (end.getX() - start.getX()));
        }
        this.b = y1 - (m * x1);
    }

    /**
     * Calculation of the line length.
     *
     * @return the line's length.
     */
    public double length() {
        double length = ((start.getX() - end.getX()) * (start.getX() - end.getX())) + ((start.getY()
                - end.getY()) * (start.getY() - end.getY()));
        return Math.sqrt(length);
    }

    /**
     * Calculates the line's middle point.
     *
     * @return the line's middle point.
     */
    public Point middle() {
        Point point = new Point(((start.getX() + end.getX()) / 2), ((start.getY() + end.getY()) / 2));
        return point;
    }

    /**
     * Access to the line's starting point.
     *
     * @return the line's starting point.
     */
    public Point start() {
        return this.start;
    }


    /**
     * Access to the line's ending point.
     *
     * @return the line's ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function calculates if the lines are intersecting.
     *
     * @param other a second line for the intersecting check.
     * @return true if they are intersecting and false if they aren't.
     */
    public boolean isIntersecting(Line other) {
        //finding the min and max of line1
        double minX1 = this.start.getX();
        double maxX1 = this.end.getX();
        if (this.end.getX() < minX1) {
            minX1 = this.end.getX();
            maxX1 = this.start.getX();
        }
        double minY1 = this.start.getY();
        double maxY1 = this.end.getY();
        if (this.end.getY() < minY1) {
            minY1 = this.end.getY();
            maxY1 = this.start.getY();
        }
        //finding the min and max of line2
        double minX2 = other.start.getX();
        double maxX2 = other.end.getX();
        if (other.end.getX() < minX2) {
            minX2 = other.end.getX();
            maxX2 = other.start.getX();
        }
        double minY2 = other.start.getY();
        double maxY2 = other.end.getY();
        if (other.end.getY() < minY2) {
            minY2 = other.end.getY();
            maxY2 = other.start.getY();
        }


        //parallel to the y scope cases
        //if both lines are parallel to the y scope
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
            ////parallel completely to each other
            if (this.start.getX() != other.start.getX()) {
                return false;
            }
            /////if it's on the same line
            if ((minY1 > maxY2) || (minY2 > maxY1)) {
                return false;
            }
            return true;
        }
        ////one is parallel and the other doesn't
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() != other.end.getX())) {
            //if the x value is between the other line's x values
            if (((minX2 <= this.start.getX()) && (this.start.getX() <= maxX2))) {
                double interY = this.start.getX() * other.m + other.b;
                if (((minY1 <= interY) && (interY <= maxY1)) || ((maxY1 <= interY) && (interY <= minY1))) {
                    return true;
                }
            }
            return false;
        }
        //other case that one is parallel and the other doesn't
        if ((this.start.getX() != this.end.getX()) && (other.start.getX() == other.end.getX())) {
            //if the x value is between the other line's x values
            if ((((minX1 <= other.start.getX()) && (other.start.getX() <= maxX1)))) {
                double interY = other.start.getX() * this.m + this.b;
                if (((minY2 <= interY) && (interY <= maxY2)) || ((maxY2 <= interY) && (interY <= minY2))) {
                    return true;
                }
            }
            return false;
        }

        //Cases that there is no chance they are intersecting
        if ((maxX1 < minX2) || (maxX2 < minX1) || (maxY1 < minY2) || (maxY2 < minY1)) {
            return false;
        } else {
            //case of same slope
            if (this.m == other.m) {
                //b's are different means they are completely parallel
                if (this.b != other.b) {
                    return false;
                }
                //if they have one connecting points
                if ((minX2 <= maxX1) && (minX1 <= maxX2)) {
                    return true;
                }
                if ((minX1 <= minX2) && (minX2 <= maxX1)) {
                    return true;
                }
                return false;
            }
            //Slopes are different
            //finding the theoretical intersecting point
            double interX = Math.round((((other.b - this.b) / (this.m - other.m)) * 100.0) / 100.0);
            double interY = Math.round((((this.m * interX) + this.b) * 100.0) / 100.0);
            //checks if the theoretical x's values we just found is in range
            if (((minX2 <= interX) && (interX <= maxX2) && (minX1 <= interX) && (interX <= maxX1))) {
                return true;
            }
            return false;
        }
    }

    /**
     * The function calculates the intersecting point between two lines.
     *
     * @param other a second line in order to find the intersecting point between these two lines.
     * @return the intersecting point between two lines
     */
    public Point intersectionWith(Line other) {
        //if there is no intersecting point between the two lines
        if (!isIntersecting(other)) {
            return null;
        } else {
            //finding the min and max of line1
            double minX1 = this.start.getX();
            double maxX1 = this.end.getX();
            if (this.end.getX() < minX1) {
                minX1 = this.end.getX();
                maxX1 = this.start.getX();
            }
            double minY1 = this.start.getY();
            double maxY1 = this.end.getY();
            if (this.end.getY() < minY1) {
                minY1 = this.end.getY();
                maxY1 = this.start.getY();
            }
            //finding the min and max of line2
            double minX2 = other.start.getX();
            double maxX2 = other.end.getX();
            if (other.end.getX() < minX2) {
                minX2 = other.end.getX();
                maxX2 = other.start.getX();
            }
            double minY2 = other.start.getY();
            double maxY2 = other.end.getY();
            if (other.end.getY() < minY2) {
                minY2 = other.end.getY();
                maxY2 = other.start.getY();
            }

            /////parallel to y scope
            if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
                ////checks if the have one connecting point
                if ((minY1 == maxY2)) {
                    return new Point(this.start.getX(), minY1);
                } else if ((minY2 == maxY1)) {
                    return new Point(this.start.getX(), minY2);
                }
                return null;
            }
            ////one is parallel and the other doesn't
            if ((this.start.getX() == this.end.getX()) && (other.start.getX() != other.end.getX())) {
                return new Point(this.start.getX(), this.start.getX() * other.m + other.b);
            }
            if ((this.start.getX() != this.end.getX()) && (other.start.getX() == other.end.getX())) {
                return new Point(other.start.getX(), other.start.getX() * this.m + this.b);
            }
            //if the slopes are equal
            if (this.m == other.m) {
                //if they are parallel completely
                if (this.b != other.b) {
                    return null;
                }
                if ((minX2 <= minX1) && (minX1 <= maxX2)) {
                    //checks if they have one connection point
                    if ((minX1 == maxX2)) {
                        return new Point(minX1, this.m * minX1 + this.b);
                    }
                }
                if ((minX1 <= minX2) && (minX2 <= maxX1)) {
                    if ((minX2 == maxX1)) {
                        return new Point(minX2, minX2 * other.m + other.b);
                    }
                }
                return null;
            }
        }
        //slopes are different
        //finding the theoretical intersecting point
        double interX = (other.b - this.b) / (this.m - other.m);
        double interY = (this.m * interX) + this.b;
        return new Point(interX, interY);
    }

    /**
     * The function compares two lines.
     *
     * @param other another line for the comparison.
     * @return true if the lines are equal and false if they aren't
     */
    public boolean equals(Line other) {
        if ((start.getX() == other.start.getX()) && (start.getY() == other.start.getY()) && (end.getX()
                == other.end.getX()) && (end.getY() == other.end.getY())) {
            return true;
        } else if ((start.getX() == other.end.getX()) && (start.getY() == other.end.getY()) && (end.getX()
                == other.start.getX()) && (end.getY() == other.start.getY())) {
            return true;
        }
        return false;
    }

    /**
     * check if a point is on a line.
     *
     * @param point for the check
     * @return true if the point is on the line, false id doesn't
     */
    public boolean pointInLine(Point point) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());

        if (point.getX() > maxX || point.getX() < minX) {
            return false;
        }
        if (point.getY() > maxY || point.getY() < minY) {
            return false;
        }
        return true;
    }

    /**
     * finds the closest intersection point to start of the line.
     *
     * @param rect the rectangle
     * @return the closest intersection point to start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        Point minP = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance(this.start) < minP.distance(this.start)) {
                minP = list.get(i);
            }
        }
        return minP;
    }

    /**
     * equals doubles.
     * @param a a double
     * @param b a double
     * @return true if they are equal, false if they aren't
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < 0.00001;
    }

}
