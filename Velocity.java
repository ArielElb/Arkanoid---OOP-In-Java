// 213073497 Ariel Elbaz.

/**
 * Velocity class - specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    public static final int VELOCITY = 25;
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx - the speed on x
     * @param dy - the speed on y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * setter for dx.
     *
     * @param dx - speed on x
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * setter for dy.
     *
     * @param dy - speed on y.
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * getter for dx.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter for dy.
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * function to calculate the velocity by the angle and the speed.
     *
     * @param angle - the angle in degrees.
     * @param speed - a double number scalar.
     * @return the Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRadians = Math.toRadians(angle);
        double dx = Math.sin(angleRadians) * speed;
        double dy = -Math.cos(angleRadians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p - point to apply on .
     * @return ball Point after moving.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}