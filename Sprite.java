import biuoop.DrawSurface;

/**
 * Sprite class ,A sprite is a type of "stand-alone"
 * computer graphic element that has evolved along with modern computer graphics technologies.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param surface - the surface we're drawing on.
     */
    void drawOn(DrawSurface surface);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}