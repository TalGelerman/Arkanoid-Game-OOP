import java.util.List;

/**
 * level information class.
 */
public interface LevelInformation {
    /**
     * numbers of balls.
     *
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle speed.
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * paddle width.
     * @return paddle width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * he Blocks that make up this level.
     * @return he Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
