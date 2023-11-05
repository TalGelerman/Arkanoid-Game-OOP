/**
 * ball remover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * ball remover constructor.
     * @param game
     * @param remainingBalls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * dealing with a hit event.
     * @param beingHit block
     * @param hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.game.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
