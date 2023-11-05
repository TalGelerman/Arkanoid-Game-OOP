// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The ball class, ball creation and different functions.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int topBorder;
    private int bottomBorder;
    private int rightBorder;
    private int leftBorder;
    private GameEnvironment gameEnvironment;

    /**
     * A ball constructor.
     *
     * @param x     the x value of the ball's center
     * @param y     the x value of the ball's center
     * @param r     the radios of the ball
     * @param color the ball's color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r;
        this.color = color;
    }

    /**
     * another ball constructor.
     *
     * @param center the ball's center
     * @param r      the ball's radios
     * @param color  the ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * access to the ball's center x value.
     *
     * @return the ball's center x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * access to the ball's center y value.
     *
     * @return the ball's center y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * access to the ball's radios size.
     *
     * @return the ball's radios size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * access to the ball's color.
     *
     * @return the ball's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw a ball on a surface.
     *
     * @param surface a surface that the ball will be drawn on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), r);
    }

    /**
     * Sets velocity to the ball.
     *
     * @param v the ball's new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * other setting for a new velocity.
     *
     * @param dx new dx for velocity
     * @param dy new dy for velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * access to the ball's velocity.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * sets the ball's top border.
     *
     * @param topB the ball's new top border
     */
    public void setTop(int topB) {
        this.topBorder = topB;
    }

    /**
     * sets the ball's bottom border.
     *
     * @param bottB the ball's new bottom border
     */
    public void setBottom(int bottB) {
        this.bottomBorder = bottB;
    }

    /**
     * sets the ball's right border.
     *
     * @param rightB the ball's new right border
     */
    public void setRight(int rightB) {
        this.rightBorder = rightB;
    }

    /**
     * sets the ball's left border.
     *
     * @param leftB the ball's new left border
     */
    public void setLeft(int leftB) {
        this.leftBorder = leftB;
    }

    /**
     * access to the right border.
     *
     * @return the right border value
     */
    public int getRight() {
        return this.rightBorder;
    }

    /**
     * access to the left border.
     *
     * @return the left border value
     */
    public int getLeft() {
        return this.leftBorder;
    }

    /**
     * access to the top border.
     *
     * @return the top border value
     */
    public int getTop() {
        return this.topBorder;
    }

    /**
     * access to the bottom border.
     *
     * @return the bottom border value
     */
    public int getBottom() {
        return this.bottomBorder;
    }

    /**
     * access to the ball's center.
     *
     * @return the ball's center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * sets the center.
     *
     * @param x the x value
     * @param y the y value
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * sets the game environment.
     *
     * @param gameEnvironment game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * moves the ball one step, according to the velocity.
     */
    public void moveOneStep() {
        Point endPoint = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo colInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (colInfo != null) {
            endPoint = new Point(this.center.getX() + this.velocity.getDx(),
                    this.center.getY() + this.velocity.getDy());
            trajectory = new Line(this.center, endPoint);
            colInfo = this.gameEnvironment.getClosestCollision(trajectory);
            if (colInfo != null) {
                Velocity newVelocity = colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.velocity);
                this.setVelocity(newVelocity);
            }
        }
        //checks the border cases
        if (this.getY() < 10) {
            this.setCenter(this.getX(), 10 + this.getSize());
        }
        if (this.getX() > 790) {
            this.setCenter(790 - this.getSize(), this.getY());
        } else if (this.getX() < 10) {
            this.setCenter(10 + this.getSize(), this.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * notify that the time had passed by move one step.
     */
    public void timePassed() {
        moveOneStep();
    }


    /**
     * The function draws balls animation on a surface.
     *
     * @param start starting point of the ball animation
     * @param dx    dx of velocity
     * @param dy    dy of velocity
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
        ball.setTop(0);
        ball.setBottom(200);
        ball.setRight(200);
        ball.setLeft(0);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * adds a sprite to a game.
     *
     * @param game game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * access to game environment.
     *
     * @return game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * removes the ball from the game.
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        }
}

