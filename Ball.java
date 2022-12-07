// 213073497 Ariel Elbaz.

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Ball class - ball have size(radius), center Point (x,y) and a velocity and a color :D.
 * the ball can move on the screen with diff velocities and in different places.
 */
public class Ball implements Sprite {
    private GameEnvironment gameEnvironment;
    private Point center;
    private int size;
    private final Color color;
    private Velocity velocity;

    /**
     * Constructor.
     *
     * @param center          - center point of the ball
     * @param r               - the radius of the ball.
     * @param color           - the color of the ball
     * @param gameEnvironment - the game environment the ball at.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;

    }

    /**
     * Constructor.
     *
     * @param x               - the x coordinate of the center ball.
     * @param y               - the y coordinate of the center ball.
     * @param r               - the radius of the ball.
     * @param color           - the color of the ball
     * @param gameEnvironment - the game environment the ball at.
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor.
     *
     * @param center - center point of the ball
     * @param r      - the radius of the ball
     * @param color  - color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * constructor.
     *
     * @param x     - x cord of the center point of the ball
     * @param y     - y cord of the center point of the ball
     * @param r     - the radius of the ball
     * @param color - color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
    }


    /**
     * setVelocity - a setter for the velocity of the ball .
     *
     * @param v - set v Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity - a setter for the velocity of the ball  using dx,dy.
     *
     * @param dx - set dx Velocity.
     * @param dy - set dy Velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getVelocity - a getter for the velocity.
     *
     * @return this.Velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moveOneStep - moving the ball close to the collision point and sets its velocity accord to his position.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo closestCollision = this.gameEnvironment.getClosestCollision(trajectory);
        if (closestCollision == null) {
            this.center = trajectory.end();
        } else {
            int dxBefore = (int) this.getVelocity().getDx();
            int dyBefore = (int) this.getVelocity().getDy();
            Point coloP = closestCollision.collisionPoint();

            Velocity velocityAfterHit = closestCollision.collisionObject().
                    hit(this, closestCollision.collisionPoint(), this.getVelocity());

            //make the ball spin back before hit
            Velocity brakeVelocity = new Velocity(coloP.getX() - this.getX(),
                    coloP.getY() - this.getY());

            if (dyBefore < 0) {
                brakeVelocity.setDy((int) (brakeVelocity.getDy() + 1));
            } else {
                brakeVelocity.setDy((int) (brakeVelocity.getDy() - 1));
            }
            if (dxBefore < 0) {
                brakeVelocity.setDx((int) (brakeVelocity.getDx() + 1));
            }
            if (dxBefore > 0) {
                brakeVelocity.setDx((int) (brakeVelocity.getDx() - 1));
            }
            this.center = brakeVelocity.applyToPoint(this.center);
            /*
            move the ball after he hit depend on the Velocity dictation before and after the hit.
             */
            setVelocity(velocityAfterHit);
        }
    }


    /**
     * getter of the x cord of the center point.
     *
     * @return the x cord of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getter of the y cord of the center point.
     *
     * @return the y cord of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * setX - a setter for the x coordinate.
     *
     * @param x - set the x of the center point.
     */
    public void setCenterX(int x) {
        this.center.setX(x);
    }

    /**
     * setY - a setter for the y coordinate.
     *
     * @param y - set the y of the center point.
     */
    public void setCenterY(int y) {
        this.center.setY(y);
    }

    /**
     * setter - sets a new gameEnvironment.
     *
     * @param gameEnvironment -the new game environment to set.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * getter of size (the ball radius).
     *
     * @return this.size of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getter of color.
     *
     * @return this.color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * drawOn - draw the ball on the given DrawSurface.
     *
     * @param surface - the screen the ball going to be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * drawLine - draw a line.
     *
     * @param surface - the surface the line drawn on.
     */
    public void drawLine(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawLine((int) this.center.getX(), (int) this.center.getY(),
                (int) (this.center.getX() + this.velocity.getDx()),
                (int) (this.center.getY() + this.velocity.getDy()));
    }

    /**
     * addToGame - add the ball to the game.
     *
     * @param game - an instance of the object Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remov a sprite from the game.
     *
     * @param game - game object
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


}