import biuoop.DrawSurface;

/**
 * EndScreen of the game show a screen if u lose or win.
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean isWon;

    /**
     * Constructor.
     * @param score - the score of the game.
     * @param isWon - flag to check if user won or not.
     */
    public EndScreen(Counter score, boolean isWon) {
        this.isWon = isWon;
        this.score = score;
    }

    /**
     * doOneFrame.
     * @param d - a given surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(10, d.getHeight() / 2, "\"You Won. Your score is " + score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "\"Game Over. Your score is " + score.getValue(), 32);
        }

    }

    /**
     * shouldStop.
     * @return false.
     */
    public boolean shouldStop() {
        return false;
    }
}


