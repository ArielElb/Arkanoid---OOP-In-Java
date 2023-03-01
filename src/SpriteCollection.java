import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * class of all the sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * adding a new sprite object to the spritesList.
     * @param newSprite - new sprite object to add
     */
    public void addSprite(Sprite newSprite) {
        this.spritesList.add(newSprite);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<Sprite>(this.spritesList);

        for (Sprite sprite : spritesList) {
            sprite.timePassed();
        }
    }

    /**
     * draw all the sprites objects on the given surface.
     *
     * @param surface - the surface the sprites will be drawn on
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite : this.spritesList) {
            sprite.drawOn(surface);
        }
    }

    /**
     * remove a sprite.
     * @param s - sprite.
     */
    public void remove(Sprite s) {
        spritesList.remove(s);
    }
}