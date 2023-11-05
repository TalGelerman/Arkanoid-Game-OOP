// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line topLine;
    private Line botLine;
    private Line rightLine;
    private Line leftLine;
    private Color color;

    /**
     * Rectangle constructor.
     *
     * @param upperLeft the location
     * @param width     the width
     * @param height    the  height
     * @param color a color
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.topLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.botLine = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.rightLine = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.leftLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.color = color;
    }

    /**
     * the function check if two lines are intersecting and if they do, adds it to the list.
     *
     * @param line       the line
     * @param other      the rectangle line
     * @param pointsList the intersecting point list
     * @return the list with the intersecting point if there is
     */
    public List<Point> checkAndAdd(Line line, Line other, List<Point> pointsList) {
        if (line.intersectionWith(other) != null) {
            pointsList.add(line.intersectionWith(other));
        }
        return pointsList;
    }

    /**
     * the function checks all intersecting points with a rectangle and put them all in a list.
     *
     * @param line the line for the test
     * @return a list with all the line and rectangle intersecting points
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<>();
        pointsList = checkAndAdd(line, this.topLine, pointsList);
        pointsList = checkAndAdd(line, this.botLine, pointsList);
        pointsList = checkAndAdd(line, this.rightLine, pointsList);
        pointsList = checkAndAdd(line, this.leftLine, pointsList);
        return pointsList;
    }

    // Return the width and height of the rectangle

    /**
     * access to the width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * access to the height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * access to the upper left.
     *
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * access to the rectangle's top Line.
     *
     * @return the rectangle's top line
     */
    public Line getTopLine() {
        return this.topLine;
    }

    /**
     * access to the rectangle's bottom Line.
     *
     * @return the rectangle's bottom line
     */
    public Line getBotLine() {
        return this.botLine;
    }

    /**
     * access to the color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draws a rectangle on a surface.
     * @param surface a draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * sets color.
     * @param color a color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * access to the rectangle's right Line.
     *
     * @return the rectangle's right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * access to the rectangle's left Line.
     *
     * @return the rectangle's left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }


}
