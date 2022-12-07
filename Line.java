// 213073497 Ariel Elbaz.

import java.util.List;

/**
 * Line class -A line (actually a line-segment) connects two points -- a start point and an end point.
 * in Line, we can find methods such as calculate the distance between two lines,check if two lines are
 * intersecting and return the intersection point.
 * find the middle Point of the line,and check if two lines are equal.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * first constructor of Line class - setting the start Point and the end Point.
     *
     * @param start - the start point of Line.
     * @param end   - the end point of  Line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * second constructor of Line class - setting the start Point and
     * the end Point using x,y coordinates.
     *
     * @param x1 - x coordinate of start Point.
     * @param y1 - y coordinate of start Point.
     * @param x2 - x coordinate of end Point.
     * @param y2 - x coordinate of end Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length - calculate the length of the Line.
     *
     * @return length of the Line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the Middle point of Line.
     *
     * @return Middle point of the line
     */
    public Point middle() {
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        double xMid = (start.getX() + this.end.getX()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * getter of the Start point of the line.
     *
     * @return start Point of Line.
     */
    public Point start() {
        return start;
    }

    /**
     * getter of the end point of the line.
     *
     * @return end Point of Line.
     */
    public Point end() {
        return end;
    }

    /*
     * getSlope - find the slope of line
     *
     * @param line - the line to calculate his slope.
     * @return the slope of the line
     */
    private double getSlope(Line line) {
        if (line.start.getX() == line.end.getX()) {
            return 0;
        }
        return (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
    }

    /**
     * getLinearB - func to calculate the b in the equation - y = ax+b.
     *
     * @param line - get
     * @return the b parameter.
     */
    private double getLinearB(Line line) {
        return line.start.getY() - (getSlope(line) * line.start.getX());
    }

    /**
     * @param line          - one of the Lines.
     * @param xIntersection - the xIntersection between the Lines.
     * @param yIntersection - the yIntersection between the Lines.
     * @return true - if the intersection Point between the end/start .
     * false - of the intersection Point not in the range of the lines.
     */
    private boolean findInRange(Line line, double xIntersection, double yIntersection) {
        if (!((xIntersection >= this.start.getX()) && (xIntersection <= this.end.getX()))) {
            if (!((xIntersection <= this.start.getX()) && (xIntersection >= this.end.getX()))) {
                return false;
            }
        }
        if (!((xIntersection >= line.start.getX()) && (xIntersection <= line.end.getX()))) {
            if (!((xIntersection <= line.start.getX()) && (xIntersection >= line.end.getX()))) {
                return false;
            }
        }
        if (!((yIntersection >= this.start.getY()) && (yIntersection <= this.end.getY()))) {
            if (!((yIntersection <= this.start.getY()) && (yIntersection >= this.end.getY()))) {
                return false;
            }
        }
        if (!((yIntersection >= line.start.getY()) && (yIntersection <= line.end.getY()))) {
            if (!((yIntersection <= line.start.getY()) && (yIntersection >= line.end.getY()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * isVertical - check if line is vertical.
     *
     * @return true - line is vertical. false - is not vertical.
     */
    public boolean isVertical() {
        if ((this.start.getX() == this.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * findYintersection - find the y intersection coordinate between the Lines.
     *
     * @param other - find y intersection.
     * @return double - yCord of of the intersection.
     */
    private double findYintersection(Line other) {
        if (this.isVertical()) {
            return (getSlope(other) * this.start().getX() + getLinearB(other));
        }
        if (other.isVertical()) {
            return (getSlope(this) * other.start().getX() + getLinearB(this));
        }
        return (getSlope(other) * other.findXinterSection(this)) + getLinearB(other);
    }

    /**
     * findXintersection - find the x intersection coordinate between the Lines.
     *
     * @param other - find x intersection.
     * @return double - xCord of the intersection.
     */
    private double findXinterSection(Line other) {
        if (this.isVertical()) {
            return this.start().getX();
        }
        if (other.isVertical()) {
            return other.start().getX();
        }
        return (getLinearB(other) - getLinearB(this)) / (getSlope(this) - getSlope(other));
    }

    /**
     * isIntersecting - check if there is intersection between to Lines.
     *
     * @param other - the second Line.
     * @return true - if the lines are intersecting.
     * false - lines are not intersecting.
     */
    public boolean isIntersecting(Line other) {

        /*
        both lines are vertical to x.
         */
        if (this.isVertical() && other.isVertical()) {
            //if parallel and vertical on x
            if (this.start().getX() != other.start().getX()) {
                return false;
            } else if ((this.start().getY() <= other.end().getY()) && (other.end().getY() <= this.end().getY())) {
                return true;
            } else if ((other.start().getY() <= this.end().getY()) && (this.end().getY() <= other.end().getY())) {
                return true;
            } else if ((other.end().getY() <= this.start().getY()) && (this.start().getY() <= other.end().getY())) {
                return true;
            } else if ((this.end().getY() <= other.start().getY()) && (other.start().getY() <= this.end().getY())) {
                return true;
            }

        } else if (this.isVertical() && !other.isVertical()) {
            double xIntersection = this.start().getX();
            double yIntersection = this.findYintersection(other);
            if (findInRange(other, xIntersection, yIntersection)) {
                return true;
            }
        } else if (!this.isVertical() && other.isVertical()) {
            double xIntersection = other.start().getX();
            double yIntersection = this.findYintersection(other);
            return findInRange(other, xIntersection, yIntersection);
        /*
         if the lines are parallel and have valid slope (not vertical to x).
         */
        } else if (!this.isVertical() && !other.isVertical()) {
        /*
         if two lines are completely equals , return true.
         */
            if (this.equals(other)) {
                return true;
            }

            if ((getSlope(this) == getSlope(other)) && (this.getLinearB(this) == other.getLinearB(other))) {
                if ((this.start().getX() <= other.end().getX()) && (other.end().getX() <= this.end().getX())) {
                    return true;
                } else if ((other.start().getX() <= this.end().getX()) && (this.end().getX() <= other.end().getX())) {
                    return true;
                } else if ((other.end().getX() <= this.start().getX()) && (this.start().getX() <= other.end().getX())) {
                    return true;
                } else if ((this.end().getX() <= other.start().getX()) && (other.start().getX() <= this.end().getX())) {
                    return true;
                }
                return false;
            }
            double slopeOne = getSlope(this);
            double slopeTwo = getSlope(other);
            if (slopeOne == slopeTwo) {
                return false;
            }
            double xIntersection = findXinterSection(other);
            double yIntersection = findYintersection(other);
            if (findInRange(other, xIntersection, yIntersection)) {
                return true;
            }

        }
        return false;

    }

    /**
     * intersectionWith - func to return the Intersection Point.
     *
     * @param other - the other line.
     * @return if the lines are intersecting return the intersection Point.
     * otherwise , return NULL.
     */
    public Point intersectionWith(Line other) {

        if (!isIntersecting(other)) {
            return null;
        }
        //if contain each other
        if (this.isVertical() && other.isVertical()) {
            //if parallel and vertical on x
            if (this.start().getX() != other.start().getX()) {
                return new Point(other.end().getX(), other.end().getY());
            } else if ((this.start().getY() <= other.end().getY()) && (other.end().getY() <= this.end().getY())) {
                return new Point(other.end().getX(), other.end().getY());
            } else if ((other.start().getY() <= this.end().getY()) && (this.end().getY() <= other.end().getY())) {
                return new Point(this.end().getX(), this.end().getY());

            } else if ((other.end().getY() <= this.start().getY()) && (this.start().getY() <= other.end().getY())) {
                return new Point(this.start().getX(), this.start().getY());
            } else if ((this.end().getY() <= other.start().getY()) && (other.start().getY() <= this.end().getY())) {
                return new Point(other.start().getX(), other.start().getY());

            }
        }

        if ((getSlope(this) == getSlope(other)) && (this.getLinearB(this) == other.getLinearB(other))) {
            if ((this.start().getX() <= other.end().getX()) && (other.end().getX() <= this.end().getX())) {
                return new Point(other.end().getX(), other.end().getY());
            } else if ((other.start().getX() <= this.end().getX()) && (this.end().getX() <= other.end().getX())) {
                return new Point(this.end().getX(), this.end().getY());
            } else if ((other.end().getX() <= this.start().getX())
                    && (this.start().getX() <= other.end().getX())) {
                return new Point(this.start().getX(), this.start().getY());
            } else if ((this.end().getX() <= other.start().getX()) && (other.start().getX() <= this.end().getX())) {
                return new Point(other.start().getX(), other.start().getY());
            }
        }
        if (this.equals(other)) {
            return null;
        }
        double xIntersection = this.findXinterSection(other);
        double yIntersection = this.findYintersection(other);
        return new Point(xIntersection, yIntersection);
    }

    /**
     * equals - check if two lines are equal.
     *
     * @param other - the second line to compute.
     * @return true - if equal , false - if not.
     */
    public boolean equals(Line other) {

        return (this.start.equals(other.start()) && (this.end.equals(other.end()))
                || (this.start.equals(other.end())) && this.end.equals(other.start()));
    }

    /**
     * function to check if a given point is on this line.
     * @param collisionPoint - the point we want to check if it is on the line.
     * @return true - if the point on the line  ,else - false.
     */
    public boolean isIntersectionPointOnLine(Point collisionPoint) {
        return (((Math.min(this.start().getX(), this.end().getX())) <= collisionPoint.getX())
                && (collisionPoint.getX() <= Math.max(this.start().getX(), this.end().getX())))
                && (collisionPoint.getY() <= Math.max(this.start().getY(), this.end().getY()))
                && (collisionPoint.getY() >= Math.min(this.start().getY(), this.end().getY()));
    }

    /**
     * closestIntersectionToStartOfLine - get the closest intersection
     * point of a given rectangle with this line.
     *
     * @param rect -Rectangle we want to check the closest intersection point with,
     * @return Point -  the closest Intersection To Start Of Line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestPoint;
        // If this line does not intersect with the rectangle, return null.
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        // Otherwise, return the closest intersection point to the
        // start of the line.
        closestPoint = intersectionPoints.get(0);
        double minDistance = this.start().distance(intersectionPoints.get(0));
        // loop to find minimum from ArrayList
        for (int i = 1; i < intersectionPoints.size(); i++) {
            if (this.start().distance(intersectionPoints.get(i)) < minDistance) {
                minDistance = this.start().distance(intersectionPoints.get(i));
                closestPoint = intersectionPoints.get(i);
            }
        }
        return closestPoint;
    }

}
