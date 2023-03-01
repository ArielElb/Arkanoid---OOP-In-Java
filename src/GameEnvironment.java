import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment - The environment has collectibles.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * getter.
     *
     * @return getter of the list collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c - the collidable to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * getClosestCollision - return the object and the closest collision point and the object.
     *
     * @param trajectory - Line - line win end point of the ball after one step.
     * @return CollisionInfo - closestCollision - the closest collision point and the object colonising with.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        /*
        if there are no objects ,return.
         */
        if (collidables == null) {
            return null;
        }
        /*
        find the closest collision point to the object from the center of the ball.
         */
        for (Collidable collidable : this.collidables) {
            Rectangle collObject = collidable.getCollisionRectangle();
            Point currentCollision = trajectory.closestIntersectionToStartOfLine(collObject);
            /*
            if there is some of potential intersection.
             */
            if (currentCollision != null) {
                /*
                if there is no collision set one.
                 */
                if (closestCollision == null) {
                    closestCollision = new CollisionInfo();
                    closestCollision.setCollisionPoint(currentCollision);
                    closestCollision.setCollidable(collidable);
                }
                /*
                if the distance between the currentColl point is shorter updated the closestCollision point.
                 */
                if (currentCollision.distance(trajectory.start())
                        <= closestCollision.collisionPoint().distance(trajectory.start())) {
                    closestCollision.setCollisionPoint(currentCollision);
                    closestCollision.setCollidable(collidable);
                }
            }
        }
        return closestCollision;
    }

    /**
     * remove  a collidable.
     *
     * @param c - the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

}
