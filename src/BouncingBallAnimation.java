// * Tal Gelerman 322280850

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * the class creating a drawing a ball on a surface.
 */
public class BouncingBallAnimation {

    /**
     * draws a ball on a surface.
     *
     * @param start the ball starting point
     * @param x     the x value for velocity
     * @param y     the y value for velocity
     */
    private static void drawAnimation(Point start, double x, double y) {
        GUI gui = new GUI("BouncingBallAnimation", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, 30, java.awt.Color.BLACK);
        ball.setVelocity(x, y);
        ball.setRight(200);
        ball.setLeft(0);
        ball.setTop(0);
        ball.setBottom(200);
        //if it's out of the left border
        if (start.getX() < (ball.getSize() + ball.getLeft())) {
            start.setX(ball.getLeft() + ball.getSize());
            //out of right
        } else if (start.getX() > (ball.getSize() + ball.getRight())) {
            start.setX(ball.getRight() - ball.getSize());
        }
    //out of top
        if (start.getY() < (ball.getSize() + ball.getTop())) {
            start.setY(ball.getTop() + ball.getSize());
            //out of bottom
        } else if (start.getY() > (ball.getSize() + ball.getBottom())) {
            start.setY(ball.getBottom() - ball.getSize());
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep();
            ball.drawOn(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            gui.show(d);
        }
    }

    /**
     * converting string array to int array.
     *
     * @param numbers array of strings
     * @return array of int
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
     * converting an array an drawing it on a surface.
     *
     * @param args an array of strings
     */
    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);
        Point p = new Point(numbers[0], numbers[1]);
        drawAnimation(p, numbers[2], numbers[3]);
    }
}
