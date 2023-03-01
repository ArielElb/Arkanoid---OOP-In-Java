// 213073497 Ariel Elbaz.

/**
 * Point class - point has x,y coordinates.
 * u can check if two points are equal and even calculate
 * the distance between two points.
 */
public class Point {
    // EPSILON - to avoid Precision deviations of the language.
    private static final double EPSILON = Math.pow(10, -10);
    private double xCord;
    private double yCord;

    /**
     * constructor of Point.
     *
     * @param x - the xCord.
     * @param y - the yCord.
     */
    public Point(double x, double y) {
        this.xCord = x;
        this.yCord = y;
    }

    /**
     * distance - calculate the distance between two points ant return it.
     *
     * @param other - Point type , calculate the distance from the current point to other point.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        double x2 = other.xCord;
        double y2 = other.yCord;
        double x1 = this.xCord;
        double y1 = this.yCord;
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }


    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other - check if this pointer equals to the other point.
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.xCord - other.getX()) < EPSILON && Math.abs(this.yCord - other.getY()) < EPSILON;
    }

    /**
     * getter of x cord.
     *
     * @return this x coordinate of the point .
     */
    public double getX() {
        return this.xCord;
    }

    /**
     * getter of y cord.
     *
     * @return this y coordinate of the point.
     */
    public double getY() {
        return this.yCord;
    }

    /**
     * setX -  setter of x cord.
     *
     * @param x - set the x to the xCord
     */
    public void setX(double x) {
        this.xCord = x;
    }

    /**
     * setY -  setter of y cord.
     *
     * @param y - set the y to the xCord
     */
    public void setY(double y) {
        this.yCord = y;
    }

}