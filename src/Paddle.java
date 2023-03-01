import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Paddle - a block that can move right and left.
 */
public class Paddle implements Sprite, Collidable {
    static final int REIGON = 20;
    public static final int HEIGHT = 10;
    private final int movementSpeed;
    private KeyboardSensor keyboard;
    private Block block;
    private GUI gui;

    /**
     * constructor.
     *
     * @param block - the paddle.
     * @param ms - movement speed of the paddle.
     */
    public Paddle(Block block, int ms) {
        this.block = block;
        movementSpeed = ms;
    }

    /**
     * move the paddle right .
     */
    public void moveRight() {
        if (this.block.getCollisionRectangle().getUpperRight().getX() < 770) {
            Point updatedPoint = new Point(getCollisionRectangle().getUpperLeft().getX()
                    + movementSpeed, getCollisionRectangle().getUpperLeft().getY());
            this.block.setNewPlace(updatedPoint);
        } else {
            this.block.setNewPlace(this.block.getCollisionRectangle().getUpperLeft());
        }

    }


    /**
     * move the paddle left.
     */
    public void moveLeft() {
        if (this.block.getCollisionRectangle().getUpperLeft().getX() > 30) {
            Point updatedPoint = new Point(getCollisionRectangle().getUpperLeft().getX()
                    - movementSpeed, getCollisionRectangle().getUpperLeft().getY());
            this.block.setNewPlace(updatedPoint);

        } else {
            this.block.setNewPlace(this.block.getCollisionRectangle().getUpperLeft());
        }
    }

    /**
     * if pressed left arrow move left.
     * if pressed right arrow move right.
     */
    public void timePassed() {
        keyboard = gui.getKeyboardSensor();
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle on the surface.
     *
     * @param surface - the surface we're drawing on.
     */
    public void drawOn(DrawSurface surface) {
        this.block.drawOn(surface);
    }


    /**
     * Collidable.
     *
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }


    /**
     * @param hitter          - the ball who being hit.
     * @param collisionPoint  - point of the collision with the object.
     * @param currentVelocity - the current velocity of the ball.
     * @return the new velocity of the ball depends on what line he hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle collisionObject = getCollisionRectangle();
        double dxNew = currentVelocity.getDx();
        double dyNew = currentVelocity.getDy();
        Velocity velocityAfter = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        /*
        check which side of the object the collision is and check the velocity direction accordingly.
         */

        double newSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (collisionObject.getTopLine().isIntersectionPointOnLine(collisionPoint)) {
            velocityAfter.setDy((int) -dyNew);

            if ((collisionPoint.getX() >= collisionObject.getUpperLeft().getX())
                    && (collisionPoint.getX() <= collisionObject.getUpperLeft().getX() + REIGON)) {
                velocityAfter = Velocity.fromAngleAndSpeed(300, newSpeed);
            } else if ((collisionPoint.getX() >= collisionObject.getUpperLeft().getX())
                    && (collisionPoint.getX() <= collisionObject.getUpperLeft().getX() + 2 * REIGON)) {
                velocityAfter = Velocity.fromAngleAndSpeed(330, newSpeed);
            } else if ((collisionPoint.getX() >= collisionObject.getUpperLeft().getX())
                    && (collisionPoint.getX() <= collisionObject.getUpperLeft().getX() + 3 * REIGON)) {
                velocityAfter.setDy((int) -dyNew);

            } else if ((collisionPoint.getX() >= collisionObject.getUpperLeft().getX())
                    && (collisionPoint.getX() <= collisionObject.getUpperLeft().getX() + 4 * REIGON)) {
                velocityAfter = Velocity.fromAngleAndSpeed(30, newSpeed);
            } else {
                velocityAfter = Velocity.fromAngleAndSpeed(60, newSpeed);
            }
        } else if (collisionObject.getLeftLine().isIntersectionPointOnLine(collisionPoint)) {
            velocityAfter.setDx((int) -dxNew);
        } else if (collisionObject.getRightLine().isIntersectionPointOnLine(collisionPoint)) {
            velocityAfter.setDx((int) -dxNew);

        } else if (collisionObject.getBottomLine().isIntersectionPointOnLine(collisionPoint)) {

            velocityAfter.setDy((int) -dyNew);
        }
        return velocityAfter;
    }

    /**
     * add the paddle to the game.
     *
     * @param game - and instance of Game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
        this.gui = game.getGameGUI();
    }
}