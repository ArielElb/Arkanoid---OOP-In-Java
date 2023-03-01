//213073497 Ariel Elbaz.

/**
 * interface Collidable - the methods all collidable object has.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     */

    /**
     * getter.
     * get the object Rectangle.
     *
     * @return Rectangle of the collision object.
     */
    Rectangle getCollisionRectangle();

    /**
     * .
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          - the ball that hits.
     * @param collisionPoint  - the collision point of the object with  other collidable
     * @param currentVelocity - the current velocity of the object.
     * @return - the updated velocity after collide.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}