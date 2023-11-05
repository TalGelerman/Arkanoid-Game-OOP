// * Tal Gelerman 322280850
/**
 * score tracking class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * score counter constructor.
     * @param scoreCounter score constructor
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * dealing with hit events.
     * @param beingHit block
     * @param hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }

}
