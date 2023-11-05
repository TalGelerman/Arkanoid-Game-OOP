// * Tal Gelerman 322280850

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * creating bouncing ball animation in two frames.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * converting an array of strings to ints.
     *
     * @param numbers an array of strings
     * @return an array of ints
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            arr[i] = n;
        }
        return arr;
    }

    /**
     * creating balls.
     *
     * @param ballSize    ball size for creation
     * @param borderSize  the border size
     * @param borderSize2 the other border size
     * @return new ball
     */
    public static Ball createBall(int ballSize, int borderSize, int borderSize2) {
        Random rand = new Random();
        int x = borderSize + (int) (Math.random() * ((borderSize2 - borderSize) + 1));
        int y = borderSize + (int) (Math.random() * ((borderSize2 - borderSize) + 1));
        int rgb = rand.nextInt();
        Color color = new Color(rgb);
        return new Ball(x, y, ballSize, color);
    }

    /**
     * sets the ball's borders.
     *
     * @param ball        the ball for setting
     * @param borderSize  one border
     * @param borderSize2 second border
     */
    private static void setBorders(Ball ball, int borderSize, int borderSize2) {
        ball.setTop(borderSize);
        ball.setBottom(borderSize2);
        ball.setRight(borderSize2);
        ball.setLeft(borderSize);
    }

    /**
     * draws balls on a surface.
     *
     * @param numbers an array of balls sizes
     */
    private static void drawAnimation(int[] numbers) {
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", 600, 600);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[numbers.length];
        //half for big frame, the others for little frame
        int half = numbers.length / 2;
        //big frame
        //if the size is bigger than screen
        for (int i = 0; i < half; i++) {
            if (numbers[i] > 225) {
                numbers[i] = 225;
            }
            balls[i] = createBall(numbers[i], 50, 500);
            setBorders(balls[i], 50, 500);
            //if it's out of the left border
            if (balls[i].getCenter().getX() < (balls[i].getSize() + balls[i].getLeft())) {
                balls[i].getCenter().setX(balls[i].getLeft() + balls[i].getSize());
                //out of right
            } else if (balls[i].getCenter().getX() > (balls[i].getSize() + balls[i].getRight())) {
                balls[i].getCenter().setX(balls[i].getRight() - balls[i].getSize());
            }
            //out of top
            if (balls[i].getCenter().getY() < (balls[i].getSize() + balls[i].getTop())) {
                balls[i].getCenter().setY(balls[i].getTop() + balls[i].getSize());
                //out of bottom
            } else if (balls[i].getCenter().getY() > (balls[i].getSize() + balls[i].getBottom())) {
                balls[i].getCenter().setY(balls[i].getBottom() - balls[i].getSize());
            }
            if (balls[i].getSize() >= 50) {
                balls[i].setVelocity(0.8, 0.8);
            } else {
                balls[i].setVelocity((double) 20 / balls[i].getSize(), (double) 20 / balls[i].getSize());
            }
        }
        //little frame
        for (int j = half; j < numbers.length; j++) {
            if (numbers[j] > 75) {
                numbers[j] = 75;
            }
            balls[j] = createBall(numbers[j], 450, 600);
            setBorders(balls[j], 450, 600);
            //if it's out of the left border
            if (balls[j].getCenter().getX() < (balls[j].getSize() + balls[j].getLeft())) {
                balls[j].getCenter().setX(balls[j].getLeft() + balls[j].getSize());
                //out of right
            } else if (balls[j].getCenter().getX() > (balls[j].getSize() + balls[j].getRight())) {
                balls[j].getCenter().setX(balls[j].getRight() - balls[j].getSize());
            }
            //out of top
            if (balls[j].getCenter().getY() < (balls[j].getSize() + balls[j].getTop())) {
                balls[j].getCenter().setY(balls[j].getTop() + balls[j].getSize());
                //out of bottom
            } else if (balls[j].getCenter().getY() > (balls[j].getSize() + balls[j].getBottom())) {
                balls[j].getCenter().setY(balls[j].getBottom() - balls[j].getSize());
            }
            if (balls[j].getSize() >= 50) {
                balls[j].setVelocity(0.8, 0.8);
            } else {
                balls[j].setVelocity((double) 20 / balls[j].getSize(), (double) 20 / balls[j].getSize());
            }
        }
        //the printing
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.yellow);
            d.fillRectangle(450, 450, 150, 150);
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            sleeper.sleepFor(20);
            gui.show(d);
        }

    }

    /**
     * converting and printing the balls on a surface.
     *
     * @param args an array of string
     */
    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);
        drawAnimation(numbers);

        drawAnimation(numbers);
    }


}
