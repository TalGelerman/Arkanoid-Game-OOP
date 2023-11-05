// * Tal Gelerman 322280850
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * level 1 class.
 */
public class Level1 implements LevelInformation {
    private int numBalls;
    private List<Velocity> ballsVel;
    private int paddleSpeed;
    private int paddleWidth;
    private String name;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Level 1 constructor.
     */
    public Level1() {
        this.numBalls = 1;
        this.ballsVel = new ArrayList<Velocity>(Arrays.asList(Velocity.fromAngleAndSpeed(0.0, 5)));
        this.paddleSpeed = 7;
        this.paddleWidth = 85;
        this.name = "Level1";
        this.blocks = new ArrayList<Block>();
        this.blocks = new ArrayList<Block>(Arrays.asList(new Block(new Rectangle(new Point(385, 150),
                30, 30, Color.RED))));
        this.numberOfBlocksToRemove = 1;
    }

    /**
     * numbers of balls.
     *
     * @return number of balls
     */
    public int numberOfBalls() {
        return numBalls;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return The initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballsVel;
    }

    /**
     * paddle speed.
     *
     * @return paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * paddle width.
     *
     * @return paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return level name
     */
    public String levelName() {
        return name;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.GRAY);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
            }

            @Override
            public void timePassed() {
                return;
            }
        };
        return background;
    }

    /**
     * color setter.
     *
     * @param i color number
     * @return a color
     */
    public Color color(int i) {
        if (i == 0) {
            return Color.CYAN;
        } else if (i == 1) {
            return Color.blue;
        } else if (i == 2) {
            return Color.RED;
        } else if (i == 3) {
            return Color.PINK;
        } else if (i == 4) {
            return Color.GREEN;
        } else if (i == 5) {
            return Color.yellow;
        }
        return Color.black;
    }

    /**
     * he Blocks that make up this level.
     *
     * @return he Blocks that make up this level
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Number of blocks that should be removed.
     *
     * @return Number of blocks that should be removed
     */
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
