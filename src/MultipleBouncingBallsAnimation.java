// * Tal Gelerman 322280850

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * the class creates and draws multiple balls on a surface.
 */
public class MultipleBouncingBallsAnimation {

    /**
     * converting an array of strings to ints.
     * @param numbers an array of string
     * @return an array of strings
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
     * creating a ball.
     * @param size the size of the ball
     * @return a new ball
     */
    public static Ball createBall(int size) {
        Random rand = new Random();
        int x = rand.nextInt(500 - (size * 2)) + size; ////////////////////להוסיף במסגרת
        int y = rand.nextInt(500 - (size * 2)) + size;
        int rgb = rand.nextInt();
        Color color = new Color(rgb);

        return new Ball(x, y, size, color);
    }

    /**
     * setting the balls borders.
     * @param ball a ball for the border setting
     */
    private static void setBorders(Ball ball) {
        ball.setTop(0);
        ball.setBottom(500);
        ball.setRight(500);
        ball.setLeft(0);
    }

    /**
     * draw balls on a surface.
     * @param numbers an array of size for the bal creation
     */
    private static void drawAnimation(int[] numbers) {
        GUI gui = new GUI("Multiple Balls Animation", 500, 500);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            balls[i] = createBall(numbers[i]);
            setBorders(balls[i]);
            //case that the ball is very big
            if (balls[i].getSize() > 50) {
                balls[i].setVelocity(1, 1);
            }
            balls[i].setVelocity((double) 100 / balls[i].getSize(), (double) 100 / balls[i].getSize());
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);

            }
            sleeper.sleepFor(20);
            gui.show(d);
        }
    }

    /**
     * converting an array of strings to int and draws the balls on a surface.
     * @param args an array of string
     */
    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);
        drawAnimation(numbers);
    }
}
