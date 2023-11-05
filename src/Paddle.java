// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the puddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private GUI gui;
    private ArrayList<Ball> balls;
    private double width;
    private double height;
    private int speed;
    private Rectangle rec;
    private Color color;

    /**
     * paddle constructor.
     *
     * @param keyboard keyboard sensor
     * @param gui      a gui
     * @param speed    speed
     * @param rec      rectangle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, GUI gui, int speed, Rectangle rec) {
        this.gui = gui;
        this.keyboard = keyboard;
        DrawSurface d = gui.getDrawSurface();
        double x = rec.getUpperLeft().getX();
        double y = rec.getUpperLeft().getY();
        this.paddle = new Block(rec);
        this.balls = new ArrayList<>();
        this.width = rec.getWidth();
        this.speed = speed;
        this.height = rec.getHeight();
        this.rec = rec;
        this.color = rec.getColor();
    }

    /**
     * moves the paddle left.
     */
    public void moveLeft() {
        Point p = this.getCollisionRectangle().getUpperLeft();
        if (p.getX() - speed >= 0) {
            this.paddle = new Block(new Rectangle(new Point(p.getX() - speed, p.getY()), width,
                    height, color));
        } else {
            this.paddle = new Block(new Rectangle(new Point(0, p.getY()), width, height, color));
        }
    }

    /**
     * moves the paddle right.
     */
    public void moveRight() {
        Point p = this.getCollisionRectangle().getUpperLeft();
        //checks if the next step won't be over the border
        if (p.getX() + width + speed <= GameLevel.WIDTH) {
            this.paddle = new Block(new Rectangle(new Point(p.getX() + speed, p.getY()), width, height, color));
        } else {
            //if the next step will be over the border
            this.paddle = new Block(new Rectangle(new Point(
                    GameLevel.WIDTH - width - 10, p.getY()), width, height, color));
        }
    }

    /**
     * moves the paddle accordingly.
     */
    public void timePassed() {
        isInPaddle();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * adds ball to the balls list.
     *
     * @param ball a ball
     */
    public void setBalls(Ball ball) {
        this.balls.add(ball);
    }

    /**
     * draws the paddle on a surface.
     *
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {

        d.setColor(this.getCollisionRectangle().getColor());
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        d.setColor((Color.black));
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * access to collision rectangle.
     *
     * @return collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return paddle.getCollisionRectangle();
    }

    /**
     * calculates to new velocity according to the hit.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity current velocity
     * @param hitter          ball
     * @return updated velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifth = width / 5.0;
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        double px = this.getCollisionRectangle().getUpperLeft().getX();
        //if it hits the first fifth
        if ((px <= collisionPoint.getX() && (collisionPoint.getX() <= fifth))) {
            return Velocity.fromAngleAndSpeed(-60, speed);
        } else if ((px + fifth <= collisionPoint.getX()) && (collisionPoint.getX() <= px + (2 * fifth))) {
            return Velocity.fromAngleAndSpeed(-30, speed);
        } else if ((px + (2 * fifth) <= collisionPoint.getX()) && (collisionPoint.getX() <= px + (3 * fifth))) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        } else if (((px + (3 * fifth) <= collisionPoint.getX()) && (collisionPoint.getX() <= px + (4 * fifth)))) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if ((px + (4 * fifth) <= collisionPoint.getX()) && (collisionPoint.getX() <= px + width)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        return currentVelocity;
    }

    /**
     * adds the paddle to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * check if a ball is in a paddle.
     */
    public void isInPaddle() {
        for (int i = 0; i < this.balls.size(); i++) {
            Ball ball = balls.get(i);
            double xUpperLeft = this.getCollisionRectangle().getUpperLeft().getX();
            double yUpperRight = this.getCollisionRectangle().getUpperLeft().getY()
                    + this.getCollisionRectangle().getHeight();
            double yLowerRight = this.getCollisionRectangle().getUpperLeft().getY();
            //if the ball's center is in the paddle borders
            //if it's on the left half of the paddle
            if ((xUpperLeft <= ball.getCenter().getX())
                    && (ball.getCenter().getX() <= (xUpperLeft + this.getCollisionRectangle().getWidth() / 2))
                    && (yLowerRight <= ball.getCenter().getY() && ball.getCenter().getY() <= yUpperRight)) {
                ball.setCenter(ball.getCenter().getX() - speed * 2, yLowerRight - ball.getSize());
                ball.setVelocity(ball.getVelocity().getDx() * -1, ball.getVelocity().getDy());
            } else if ((xUpperLeft + this.getCollisionRectangle().getWidth() / 2 <= ball.getCenter().getX())
                    && (ball.getCenter().getX() <= xUpperLeft + this.getCollisionRectangle().getWidth())
                    && (yLowerRight <= ball.getCenter().getY() && ball.getCenter().getY() <= yUpperRight)) {
                ball.setCenter(ball.getCenter().getX() + speed * 2, yLowerRight - ball.getSize());
                ball.setVelocity(ball.getVelocity().getDx() * -1, ball.getVelocity().getDy());
            }
        }
    }
}