// * Tal Gelerman 322280850
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * level 3 class.
 */
public class Level3 implements LevelInformation {
    private int numBalls;
    private List<Velocity> ballsVel;
    private int paddleSpeed;
    private int paddleWidth;
    private String name;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Level 3 constructor.
     */
    public Level3() {
        this.numBalls = 2;
        this.ballsVel = new LinkedList<Velocity>();
        final double speed = 5.0;
        this.ballsVel.add(Velocity.fromAngleAndSpeed(40.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-40.0, speed));
        this.paddleSpeed = 7;
        this.paddleWidth = 85;
        this.name = "Level3";
        this.blocks = new LinkedList<Block>();
        int y = 150;
        final Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < 5; ++i) {
            for (int j = i + 5; j < 15; ++j) {
                this.blocks.add(new Block(new Rectangle(new Point(j * 50 + 25, y), 50, 25, colors[i])));
            }
            y += 25;
        }
        this.numberOfBlocksToRemove = this.blocks.size();
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
                d.setColor(Color.decode("#ccffee"));
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
