import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator is a sprite that indicates on what is the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param score - the current score in the game.
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(400, 15, "Score: " + currentScore.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add sprite to the game.
     *
     * @param game - the game object.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
