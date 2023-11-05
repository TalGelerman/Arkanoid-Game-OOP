// * Tal Gelerman 322280850

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    public static final int WIDTH = 800;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballsCounter;
    private Counter score;
    private ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
    private LevelInformation levelInformation;
    private boolean running;
    private Sleeper sleeper;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;

    /**
     * game constructor.
     *
     * @param levelInformation level information
     * @param ar               animation runner
     * @param ks               keyboard sensor
     * @param score            score
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.gui = ar.getGui();
        this.blockCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.levelInformation = levelInformation;
        this.sleeper = new Sleeper();
        this.runner = ar;
        this.keyboard = ks;
    }

    /**
     * access to gui.
     *
     * @return gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * adds collidalbe to the collidables array.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite to the sprites' collection.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes a collidable.
     *
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes a collidable.
     *
     * @param s a collidable
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * access to game environment.
     *
     * @return game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * set game environment.
     *
     * @param g game environment
     */
    public void setEnvironment(GameEnvironment g) {
        this.environment = g;
    }

    /**
     * generates balls.
     */
    public void generateBall() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 500), 5, color(i));
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.paddle.setBalls(ball);
            ball.setGameEnvironment(this.getGameEnvironment());
            ball.addToGame(this);
        }
        ballsCounter.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * sets the color.
     *
     * @param i the color number
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
     * creates blocks.
     */
    public void generateBlock() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            this.levelInformation.blocks().get(i).addHitListener(blockRemover);
            this.levelInformation.blocks().get(i).addToGame(this);
            this.levelInformation.blocks().get(i).addHitListener(scoreTrackingListener);
        }
        blockCounter.increase(this.levelInformation.numberOfBlocksToRemove());
        Block topBorder = new Block(new Rectangle(new Point(10, 20), 800, 20, Color.BLACK));
        Block rightBorder = new Block(new Rectangle(new Point(0, 0), 10, 600, Color.black));
        Block leftBorder = new Block(new Rectangle(new Point(790, 0), 10, 600, Color.black));
        Block scoreBorder = new Block(new Rectangle(new Point(0, 0), 800, 20, Color.GRAY));
        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 20, Color.black));
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        topBorder.addToGame(this);
        deathRegion.addToGame(this);
        scoreBorder.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, levelInformation.levelName());
        this.sprites.addSprite(scoreIndicator);

    }

    /**
     * generates paddle.
     */
    public void generatePaddle() {
        this.paddle = new Paddle(this.gui.getKeyboardSensor(), this.gui, levelInformation.paddleSpeed(),
                new Rectangle(new Point(WIDTH / 2 - levelInformation.paddleWidth() / 2, 577),
                        levelInformation.paddleWidth(), 25, Color.PINK));
        this.paddle.addToGame(this);
    }

    /**
     * access to block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * access balls counter.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * creates the Blocks and the balls.
     */
    public void initialize() {
        this.addSprite(this.levelInformation.getBackground());
        this.generatePaddle();
        this.generateBlock();
        this.generateBall();
    }

    /**
     * do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (blockCounter.getValue() == 0) {
            score.increase(100);
        }
        if ((this.ballsCounter.getValue() == 0) || (this.blockCounter.getValue() == 0)) {
            this.running = false;
            return;
        }
        this.sprites.drawAllOn(d);
        //gui.show(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }

    /**
     * checks if the game should stop.
     *
     * @return true if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * runs the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }
}

