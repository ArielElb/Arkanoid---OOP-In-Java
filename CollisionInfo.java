//213073497 Ariel Elbaz.

/**
 * CollisionInfo - holds the collisionPoint of the object and the collidable.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructor.
     */
    public CollisionInfo() {
        this.collisionPoint = null;
        this.collidable = null;
    }

    /**
     * setter for the collision point.
     *
     * @param collisionPoint - new point to set.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * setter for the new collidable.
     *
     * @param collidable - new collidable to set.
     */
    public void setCollidable(Collidable collidable) {
        this.collidable = collidable;
    }


    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}