import biuoop.DrawSurface;

/**
 * Animation - responsible to do the animation of the game.
 */
public interface Animation {
    /**
     * doing one frame of the game each time.
     *
     * @param d - a given surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return boolean - true if animation should stop .
     * or false if not.
     */
    boolean shouldStop();
}