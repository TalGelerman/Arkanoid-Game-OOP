// * Tal Gelerman 322280850
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * level 2 class.
 */
public class Level2 implements LevelInformation {
    private int numBalls;
    private List<Velocity> ballsVel;
    private int paddleSpeed;
    private int paddleWidth;
    private String name;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Level 2 constructor.
     */
    public Level2() {
        this.numBalls = 10;
        this.ballsVel = new LinkedList<Velocity>();
        final double speed = 5.0;
        this.ballsVel.add(Velocity.fromAngleAndSpeed(50.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(40.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(30.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(20.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(10.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-10.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-20.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-30.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-40.0, speed));
        this.ballsVel.add(Velocity.fromAngleAndSpeed(-50.0, speed));
        this.paddleSpeed = 8;
        this.paddleWidth = 600;
        this.name = "Level2";
        this.blocks = new LinkedList<Block>();
        final int y = 250;
        final int blockHeight = 25;
        blocks.add(new Block(new Rectangle(new Point(25, y), 50, blockHeight, Color.RED)));
        blocks.add(new Block(new Rectangle(new Point(75, y), 50, blockHeight, Color.RED)));
        blocks.add(new Block(new Rectangle(new Point(125, y), 50, blockHeight, Color.ORANGE)));
        blocks.add(new Block(new Rectangle(new Point(175, y), 50, blockHeight, Color.ORANGE)));
        blocks.add(new Block(new Rectangle(new Point(225, y), 50, blockHeight, Color.YELLOW)));
        blocks.add(new Block(new Rectangle(new Point(275, y), 50, blockHeight, Color.YELLOW)));
        blocks.add(new Block(new Rectangle(new Point(325, y), 50, blockHeight, Color.GREEN)));
        blocks.add(new Block(new Rectangle(new Point(375, y), 50, blockHeight, Color.GREEN)));
        blocks.add(new Block(new Rectangle(new Point(425, y), 50, blockHeight, Color.GREEN)));
        blocks.add(new Block(new Rectangle(new Point(475, y), 50, blockHeight, Color.BLUE)));
        blocks.add(new Block(new Rectangle(new Point(525, y), 50, blockHeight, Color.BLUE)));
        blocks.add(new Block(new Rectangle(new Point(575, y), 50, blockHeight, Color.PINK)));
        blocks.add(new Block(new Rectangle(new Point(625, y), 50, blockHeight, Color.PINK)));
        blocks.add(new Block(new Rectangle(new Point(675, y), 50, blockHeight, Color.CYAN)));
        blocks.add(new Block(new Rectangle(new Point(725, y), 50, blockHeight, Color.CYAN)));
        this.numberOfBlocksToRemove = 15;
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
                d.setColor(Color.WHITE);
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
