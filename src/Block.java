//213073497 Ariel Elbaz.

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block - the block class implements methods of Collidable and Sprite .
 * a block shape is a rectangle, block have a color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Color color;
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param upperLeft - the upper left point of the block
     * @param width     - the width of the block
     * @param height    - the height of the block.
     * @param color     - the color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.hitListeners = new ArrayList<>();
        block = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param rectangle - get a rectangle object and assign the block as rectangle.
     * @param color     - the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.hitListeners = new ArrayList<>();
        this.color = color;
        this.block = rectangle;
    }

    /**
     * getter of the rectangle shape.
     *
     * @return Rectangle - this.block.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getter of the color.
     *
     * @return this object color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * call setNewBorders to move the block to another position.
     *
     * @param upperLeft - the upper left point of the block.
     */
    public void setNewPlace(Point upperLeft) {
        this.block.setNewBorders(upperLeft);
    }

    /**
     * @param collisionPoint  - point of the collision with the object.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - the ball hits.
     * @return the new velocity of the ball depends on what line he hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle collisionObject = getCollisionRectangle();
        double dxNew = currentVelocity.getDx();
        double dyNew = currentVelocity.getDy();
        /*
        check which side of the object the collision is and check the velocity direction accordingly.
         */
        if (collisionObject.getLeftLine().isIntersectionPointOnLine(collisionPoint)) {
            dxNew = -1 * dxNew;
        }
        if (collisionObject.getRightLine().isIntersectionPointOnLine(collisionPoint)) {
            dxNew = -1 * dxNew;

        }
        if (collisionObject.getTopLine().isIntersectionPointOnLine(collisionPoint)) {

            dyNew = -1 * dyNew;
        }
        if (collisionObject.getBottomLine().isIntersectionPointOnLine(collisionPoint)) {

            dyNew = -1 * dyNew;
        }
        this.notifyHit(hitter);
        return new Velocity(dxNew, dyNew);
    }

    /**
     * drawOn - drawn the block on a given surface.
     *
     * @param surface - the given surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame - adding this block to the game.
     *
     * @param game - the game its self.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * remove collidable and sprite from the game.
     * @param game - game object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
