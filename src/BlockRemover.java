// * Tal Gelerman 322280850
/**
 *Block remover class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * block removal constructor.
     *
     * @param game          game
     * @param removedBlocks counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit block
     * @param hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.game.removeCollidable(beingHit);
        this.game.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}