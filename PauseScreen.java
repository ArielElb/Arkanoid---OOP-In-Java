import biuoop.DrawSurface;

/**
 * PauseScreen - class for pausing the game.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * drawing one frame.
     * @param d - a given surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * stop the game when needed.
     * @return boolean , true or false. if you need to stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
