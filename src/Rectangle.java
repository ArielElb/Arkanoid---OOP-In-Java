import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle {
    //top , right , bottom , left
    private Line[] linesRectangle;
    //upperLeft , upperRight , bottomRight, bottomLeft
    private Point[] points;
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.

    /**
     * Constructor.
     *
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.points = new Point[]{upperLeft, upperRight, bottomRight, bottomLeft};

        Line upperLine = new Line(upperLeft, upperRight);
        Line leftLine = new Line(bottomLeft, upperLeft);
        Line rightLine = new Line(upperRight, bottomRight);
        Line bottomLine = new Line(bottomRight, bottomLeft);
        //top , right , bottom , left
        this.linesRectangle = new Line[4];
        this.linesRectangle[0] = upperLine;
        this.linesRectangle[1] = rightLine;
        this.linesRectangle[2] = bottomLine;
        this.linesRectangle[3] = leftLine;
        this.width = width;
        this.height = height;
    }


    /**
     * intersectionPoints - find the intersection with a given line.
     *
     * @param line - get the intersectionPoints with a given line.
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        for (Line lineRec : linesRectangle) {
            if (line.isIntersecting(lineRec)) {
                intersectionPoints.add(line.intersectionWith(lineRec));
            }
        }

        return intersectionPoints;
    }


    /**
     * getWidth - getter.
     *
     * @return the Width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight - getter.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * getter - for upperLeft.
     *
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getter - for upperRight.
     *
     * @return returns the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.points[1];
    }

    /**
     * setUpperLeft - setter.
     *
     * @param p - the new point to set.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * getter.
     *
     * @return TopLine of the rectangle
     */
    public Line getTopLine() {
        return this.linesRectangle[0];
    }

    /**
     * getter.
     *
     * @return Right Line of the rectangle
     */
    public Line getRightLine() {
        return this.linesRectangle[1];
    }

    /**
     * getter.
     *
     * @return Bottom Line of the rectangle
     */
    public Line getBottomLine() {
        return this.linesRectangle[2];
    }

    /**
     * getter.
     *
     * @return Left Line of the rectangle
     */
    public Line getLeftLine() {
        return this.linesRectangle[3];
    }

    /**
     * set the new borders of a given rectangle.
     *
     * @param updatedPoint - the new upper left.
     */
    public void setNewBorders(Point updatedPoint) {
        this.setUpperLeft(updatedPoint);
        //upperLeft , upperRight , bottomRight, bottomLeft
        this.points = new Point[]{updatedPoint, new Point(updatedPoint.getX()
                + getWidth(), updatedPoint.getY()),
                new Point(updatedPoint.getX() + getWidth(), upperLeft.getY()
                        + getHeight()), new Point(updatedPoint.getX(), upperLeft.getY() + getHeight())};
        //top , right , bottom , left
        this.linesRectangle = new Line[]{new Line(this.points[0],
                this.points[1]), new Line(this.points[1], this.points[2]),
                new Line(this.points[2], this.points[3]), new Line(this.points[3], this.points[0])};

    }

}
