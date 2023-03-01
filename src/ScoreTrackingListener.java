/**
 * ScoreTrackingListener - A listener class to update the score while playing the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * track how much score there is.
     * @param scoreCounter - counter to track how much core there is.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit - the object being hit
     * @param hitter - parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}